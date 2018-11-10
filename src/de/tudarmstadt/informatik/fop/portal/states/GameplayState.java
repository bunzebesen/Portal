package de.tudarmstadt.informatik.fop.portal.states;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.portal.constants.GameParameters;
import de.tudarmstadt.informatik.fop.portal.gameelements.Border;
import de.tudarmstadt.informatik.fop.portal.map.MapParser;
import de.tudarmstadt.informatik.fop.portal.player.Player;
import de.tudarmstadt.informatik.fop.portal.ui.Portal;
import eea.engine.action.Action;
import eea.engine.action.basicactions.MoveDownAction;
import eea.engine.action.basicactions.MoveLeftAction;
import eea.engine.action.basicactions.MoveRightAction;
import eea.engine.action.basicactions.MoveUpAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.KeyPressedEvent;
import eea.engine.event.basicevents.LoopEvent;

/**
 * Game gui
 * @author Timo Küster, Diana Kolev
 *
 */
public class GameplayState extends BasicGameState implements GameParameters{

	private int stateID; 							
	private StateBasedEntityManager entityManager; 	
	
	// Main player
	private Player player;
	
	// Downwards movement
	private MoveDownAction gravityMovement;
	// Upwards movement
	private MoveUpAction jumpMovement;
	// Jumping Action
	private Action jumpingAction = new Action() {

		@Override
		public void update(GameContainer arg0, StateBasedGame arg1,	int arg2, Component arg3) {
			removeGravity();
			addJump();
		}
		
	};

	
	// Jump only once
	private boolean alreadyJumped;
	// Downwards movement?
	private boolean downwards;

	
	// Ground height
	private float groundHeight;
	
	/**
	 * Constructor
	 * @param sid state id
	 */
	public GameplayState(int sid) {
       stateID = sid;
       entityManager = StateBasedEntityManager.getInstance();
    }
    
    @Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
    	if(!Portal.getDebug()){
	    	/* Background */
			// Create entity
			Entity background = new Entity(BACKGROUND_ID); 
			// Set position
			background.setPosition(new Vector2f(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2)); 
			// Add image
			background.addComponent(new ImageRenderComponent(new Image(BACKGROUND_IMAGE))); 
			// Add entity to the manager
			entityManager.addEntity(stateID, background); 
			
			
			/* Actions */
			// Gravity
			gravityMovement = new MoveDownAction(PLAYER_GRAVITY);
			// Jumping
			jumpMovement = new MoveUpAction(PLAYER_GRAVITY);
			
			
			/* Player */
			// Create entity
			player = new Player(PLAYER_ID);
			// Create player
			Entity playerEntity = player.createEntity();
			// Add player
			entityManager.addEntity(stateID, playerEntity);
			// Gravity event
			LoopEvent gravity = new LoopEvent();
			// Give the player the gravity event
			playerEntity.addComponent(gravity);
			// Add gravity
			this.addGravity();
			
			
			/* Variables */
			// Not jumped before the game starts
			alreadyJumped = false;
			// Downwards is true (gravity)
			downwards = true;
			// Ground height
			groundHeight = entityManager.getEntity(stateID, PLAYER_ID).getPosition().y;
			
			/* User input */
			// Enable control
			this.enableControl(player);
			
			
			/* Map */
			// Read map
			MapParser map = null;
			try {
				map = new MapParser(MAP_NAME);
				// Add map
				this.addMap(map.getMap());
			} catch (IOException e) {
				e.getMessage();
				e.printStackTrace();
			}
    	}
    }

    @Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    	if(!Portal.getDebug()){
    		// Update all entities
        	entityManager.updateEntities(container, game, delta);
        	
        	/* Jumping */
        	
        	// On the floor
        	for(int i = 0; i < MAP_HEIGHT; i++){
        		for(int j = 0; j < MAP_WIDTH; j++){
        			// On collision
        			if(entityManager.getEntity(stateID, PLAYER_ID).collides(entityManager.getEntity(stateID, "ground"+j+"_"+i))
        					&& entityManager.getEntity(stateID, PLAYER_ID).getPosition().x >= entityManager.getEntity(stateID, "ground"+j+"_"+i).getPosition().x - BORDER_WIDTH / 2
        					&& entityManager.getEntity(stateID, PLAYER_ID).getPosition().x <= entityManager.getEntity(stateID, "ground"+j+"_"+i).getPosition().x + BORDER_WIDTH / 2
        					&& entityManager.getEntity(stateID, PLAYER_ID).getPosition().y <= entityManager.getEntity(stateID, "ground"+j+"_"+i).getPosition().y) {
        				// Do not go into the ground
        				if(entityManager.getEntity(stateID, PLAYER_ID).getPosition().y > entityManager.getEntity(stateID, "ground"+j+"_"+i).getPosition().y - BORDER_HEIGHT / 2 - PLAYER_HEIGHT / 2) {
        					entityManager.getEntity(stateID, PLAYER_ID).setPosition(new Vector2f(entityManager.getEntity(stateID, PLAYER_ID).getPosition().x, entityManager.getEntity(stateID, "ground"+j+"_"+i).getPosition().y - BORDER_HEIGHT / 2 - PLAYER_HEIGHT / 2));
        				}
        				
        				if(downwards){
                    		groundHeight = entityManager.getEntity(stateID, PLAYER_ID).getPosition().y;
        				}
        				
        				// Landing
        				if(alreadyJumped) {
            				this.addJumpKey();
            				this.alreadyJumped = false;
        				}
        			} else if(entityManager.getEntity(stateID, PLAYER_ID).collides(entityManager.getEntity(stateID, "ground"+j+"_"+i))
        					&& entityManager.getEntity(stateID, PLAYER_ID).getPosition().y >= entityManager.getEntity(stateID, "ground"+j+"_"+i).getPosition().y) {
        				this.removeJump();
        				this.addGravity();
        			}
        		}
        	}  
        	
        	// Do not jump twice
        	if(entityManager.getEntity(stateID, PLAYER_ID).getPosition().y < groundHeight && !alreadyJumped) {
        		this.removeJumpKey();
        		alreadyJumped = true;
        	}
        	
        	// Take care of jump heigth
			if(alreadyJumped) {
				if(entityManager.getEntity(stateID, PLAYER_ID).getPosition().y <= groundHeight - PLAYER_JUMP_HEIGHT) {
		        	this.removeJump();
		        	this.addGravity(); 
				}
			}	
			
			/* End Jumping */
    	}
	}
    
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// Render all entities
		entityManager.renderEntities(container, game, g);    
    }

	@Override
	public int getID() {
		return stateID;
	}
	
	/**
	 * Add the map to the level
	 * @param map map
	 */
	public void addMap(int[][] map) {
    	for(int i = 0; i < MAP_HEIGHT; i++){
    		for(int j = 0; j < MAP_WIDTH; j++){
    			// Bottom
		    	if(map[i][j] == 1){
		    		Border border = new Border("ground"+j+"_"+i);
		    		Entity borderEntity = border.createEntity();
    		    	borderEntity.setPosition(new Vector2f(j * BORDER_WIDTH + BORDER_WIDTH / 2, i * BORDER_HEIGHT + BORDER_HEIGHT / 2));
    		    	entityManager.addEntity(stateID, borderEntity);
		    	}
    		}
    	}
	}
	
	/**
	 * Enables user control
	 * @param player the player
	 */
	public void enableControl(Player player) {
    	// Left
		KeyDownEvent left = new KeyDownEvent(Input.KEY_A);
    	left.addAction(new MoveLeftAction(PLAYER_SPEED));
    	entityManager.getEntity(stateID, PLAYER_ID).addComponent(left);   
    	
    	// Right
    	KeyDownEvent right = new KeyDownEvent(Input.KEY_D);
    	right.addAction(new MoveRightAction(PLAYER_SPEED)); 	
    	entityManager.getEntity(stateID, PLAYER_ID).addComponent(right);
    	
    	// Jump
    	KeyPressedEvent spaceEvent = new KeyPressedEvent(Input.KEY_SPACE);
    	spaceEvent.addAction(jumpingAction);
    	entityManager.getEntity(stateID, PLAYER_ID).addComponent(spaceEvent);
    	
    	// Shoot
	}
	
	/**
	 * Adds gravity
	 */
	public void addGravity() {
		downwards = true;
		Event gravityEvent = (Event) entityManager.getEntity(stateID, PLAYER_ID).getEvent("LoopEvent");
		gravityEvent.addAction(gravityMovement);
	}
	
	/**
	 * Removes gravity
	 */
	public void removeGravity() {
		Event gravityEvent = (Event) entityManager.getEntity(stateID, PLAYER_ID).getEvent("LoopEvent");
		gravityEvent.clearActions();
	}
	
	/**
	 * Let the player jump
	 */
	public void addJump() {
		downwards = false;
		Event gravityEvent = (Event) entityManager.getEntity(stateID, PLAYER_ID).getEvent("LoopEvent");
		gravityEvent.addAction(jumpMovement);
	}
	
	/**
	 * Do not let the player jump anymore
	 */
	public void removeJump() {
		Event gravityEvent = (Event) entityManager.getEntity(stateID, PLAYER_ID).getEvent("LoopEvent");
		gravityEvent.clearActions();
	}
	
	/**
	 * Accept the jump key
	 */
	public void addJumpKey() {
		Event keyPressedEvent = (Event) entityManager.getEntity(stateID, PLAYER_ID).getEvent("KeyPressedEvent");
		keyPressedEvent.addAction(jumpingAction);
	}
	
	/**
	 * Do not accept the jump key
	 */
	public void removeJumpKey() {
		Event keyPressedEvent = (Event) entityManager.getEntity(stateID, PLAYER_ID).getEvent("KeyPressedEvent");
		keyPressedEvent.clearActions();
	}
}
