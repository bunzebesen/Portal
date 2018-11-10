package de.tudarmstadt.informatik.fop.portal.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.portal.constants.GameParameters;
import de.tudarmstadt.informatik.fop.portal.io.MouseInput;
import de.tudarmstadt.informatik.fop.portal.ui.Portal;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

/**
 * Main menu
 * @author Timo Küster, Diana Kolev
 *
 */
public class MainMenuState extends BasicGameState implements GameParameters{

	private int stateID; 							
	private StateBasedEntityManager entityManager; 	
	
	private final int distance = 100;				
    private final int startPosition = 180;			
   
    /**
     * Constructor
     * @param sid state id
     */
	public MainMenuState(int sid) {
		stateID = sid;
	    entityManager = StateBasedEntityManager.getInstance();
	}
    
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		if(!Portal.getDebug()){
			
	    	/* Background */
			// Create entity
			Entity menu = new Entity(MENU_ID); 
			// Set position
			menu.setPosition(new Vector2f(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2)); 
			// Add image
			menu.addComponent(new ImageRenderComponent(new Image(MENU_IMAGE))); 
			// Add entity to the manager
			entityManager.addEntity(stateID, menu); 

			
			/* Input fields */
			// Start
	    	MouseInput new_Game = new MouseInput("Neues Spiel", "spiel", 170, 190);
	    	Entity new_Game_Entity = new_Game.createEntity();
	    	entityManager.addEntity(this.stateID, new_Game_Entity);
	    	
	    	// Quit
	    	MouseInput quit = new MouseInput("Beenden", "beenden", 170, 290);
	    	Entity quit_Entity = quit.createEntity();
	    	entityManager.addEntity(this.stateID, quit_Entity);
		}
    }

	@Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// Update all entities
		entityManager.updateEntities(container, game, delta);
	}
    
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// Render all entities
		entityManager.renderEntities(container, game, g);
		
		int counter = 0;
		
	    // Field content
	    String newGame = "Neues Spiel";
	    String quit = "Beenden";

		g.drawString(newGame, 130, startPosition + counter * distance); counter++;
		g.drawString(quit, 130, startPosition + counter * distance);
	}
	
	@Override
	public int getID() {
		return stateID;
	}
}
