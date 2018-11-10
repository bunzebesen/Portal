package de.tudarmstadt.informatik.fop.portal.io;

import de.tudarmstadt.informatik.fop.portal.constants.GameParameters;
import de.tudarmstadt.informatik.fop.portal.ui.Portal;
import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.entity.Entity;
import eea.engine.event.basicevents.KeyPressedEvent;
import eea.engine.interfaces.IEntityFactory;

/**
 * Vorlage für Spieler Eingaben per Tastatur
 * @author Timo Küster, Diana Kolev, Julia Kaiser
 *
 */
public class SpielerEingabeTastatur implements IEntityFactory, GameParameters{

	private String id;
	private String nextState;
	private int key;
	
	/**
	 * Konstruktor einer Spieler Eingabe per Tastatur
	 * @param id die ID des Feldes für die Eingabe
	 * @param nextState der State in den gewechselt werden soll
	 * @param key die Taste
	 */
	public SpielerEingabeTastatur(String id, String nextState, int key) {
		this.id = id;
		this.nextState = nextState;
		this.key = key;
	}

	@Override
	public Entity createEntity() {
		Entity input = new Entity(id);

		KeyPressedEvent pressed = new KeyPressedEvent(key);
    	Action Action = null;
    	
    	switch (nextState){
    	
    	case "menu":
    		Action = new ChangeStateInitAction(Portal.MAINMENU_STATE);
    		break;
    	default:
    		return null;
    	}
    	
    	pressed.addAction(Action);
    	input.addComponent(pressed); 
    	
		return input;
	}

}
