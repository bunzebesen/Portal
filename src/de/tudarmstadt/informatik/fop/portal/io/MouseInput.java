package de.tudarmstadt.informatik.fop.portal.io;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import de.tudarmstadt.informatik.fop.portal.constants.GameParameters;
import de.tudarmstadt.informatik.fop.portal.ui.Portal;
import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.action.basicactions.QuitAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;
import eea.engine.interfaces.IEntityFactory;

/**
 * Mouse Input
 * @author Timo Küster, Diana Kolev
 *
 */
public class MouseInput implements IEntityFactory, GameParameters{

	private String id;
	private String nextState;
	private int x;
	private int y;
	
	/**
	 * Constructor
	 * @param id field id
	 * @param nextState next State
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public MouseInput(String id, String nextState, int x, int y) {
		this.id = id;
		this.nextState = nextState;
		this.x = x;
		this.y = y;
	}

	@Override
	public Entity createEntity() {
		Entity input = new Entity(id);
		input.setPosition(new Vector2f(x, y));
		input.setScale(0.4f);
		try {
			input.addComponent(new ImageRenderComponent(new Image(ENTRY_IMAGE)));
		} catch (SlickException e) {
			e.getMessage();
			e.printStackTrace();
		}
		
    	ANDEvent mainEvents = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
    	Action Action = null;
    	
    	switch (nextState){
    	
    	case "spiel":
    		Action = new ChangeStateInitAction(Portal.GAMEPLAY_STATE);
    		break;
    	case "beenden":
    		Action = new QuitAction();
    		break;
    	default:
    		return null;
    	}
    	
    	mainEvents.addAction(Action);
    	input.addComponent(mainEvents); 
    	
		return input;
	}

}
