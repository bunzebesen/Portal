package de.tudarmstadt.informatik.fop.portal.gameelements;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.portal.constants.GameParameters;
import de.tudarmstadt.informatik.fop.portal.ui.Portal;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;

/**
 * Border class
 * @author Timo Küster, Diana Kolev
 *
 */
public class Border implements IEntityFactory, GameParameters{

	private String id;
	
	/**
	 * Constructor
	 * @param id border id
	 */
	public Border(String id){
		this.id = id;
	}

	@Override
	public Entity createEntity() {
		Entity border = new Entity(id);
		String image = BORDER_IMAGE;
		border.setPassable(false);
		
		try {
    		if(!Portal.getDebug()){
    			border.addComponent(new ImageRenderComponent(new Image(image)));
    		}
		} catch (SlickException e) {
			e.getMessage();
			e.printStackTrace();
		}
		
		return border;
	}
}
