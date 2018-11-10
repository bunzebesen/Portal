package de.tudarmstadt.informatik.fop.portal.player;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.portal.constants.GameParameters;
import de.tudarmstadt.informatik.fop.portal.ui.Portal;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;

/**
 * Player
 * @author Timo Küster, Diana Kolev
 *
 */
public class Player implements IEntityFactory, GameParameters{

	private String id; 
	
	/**
	 * Constructor
	 * @param id id
	 */
	public Player(String id){
		this.id = id;
	}
	
	@Override
	public Entity createEntity() {
		Entity player = new Entity(id);
		
    	player.setPosition(new Vector2f(PLAYER_WIDTH, WINDOW_HEIGHT - BORDER_HEIGHT - PLAYER_HEIGHT / 2));	
    	player.setPassable(false);
    	
    	
    	try {
    		if(!Portal.getDebug()){
    			player.addComponent(new ImageRenderComponent(new Image(PLAYER_IMAGE)));
    		}
		} catch (SlickException e) {
			e.getMessage();
			e.printStackTrace();
		}		
    	
    	return player;
	}
}
