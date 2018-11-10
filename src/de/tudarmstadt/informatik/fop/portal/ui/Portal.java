package de.tudarmstadt.informatik.fop.portal.ui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.portal.constants.GameParameters;
import de.tudarmstadt.informatik.fop.portal.states.GameplayState;
import de.tudarmstadt.informatik.fop.portal.states.MainMenuState;
import eea.engine.entity.StateBasedEntityManager;

/**
 * Portal main
 * @author Timo Küster, Diana Kolev
 *
 */
public class Portal extends StateBasedGame implements GameParameters {

	// Remember if the game runs in debug mode
	private static boolean debug = false;

	/**
	 * Creates a new Portal instance
	 * 
	 * @param debug if true, runs in debug mode
	 */
	public Portal(boolean debug) {
		super("Portal");
		Portal.debug = debug;
	}

	/**
	 * Getter for Debug
	 * @return debug
	 */
	public static boolean getDebug() {
		return debug;
	}

	public static void main(String[] args) throws SlickException {
		// Set the library path depending on the operating system
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + "/native/windows");
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + "/native/macosx");
		} else {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + "/native/"
							+ System.getProperty("os.name").toLowerCase());
		}

		// Add this StateBasedGame to an AppGameContainer
		AppGameContainer app = new AppGameContainer(new Portal(false));

		// Set the display mode and frame rate
		app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
		app.setTargetFrameRate(FRAME_RATE);

		// now start the game!
		app.start();
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {

		// Add the game states (the first added state will be started initially)
		// This may look as follows, assuming you use the associated class names and constants:
	  
		addState(new MainMenuState(MAINMENU_STATE));
		addState(new GameplayState(GAMEPLAY_STATE));
	/*	addState(new HighscoreState(HIGHSCORE_STATE));
		addState(new OptionState(OPTION_STATE));
		addState(new InfoState(INFO_STATE)); */

		// Add the states to the StateBasedEntityManager
		StateBasedEntityManager.getInstance().addState(MAINMENU_STATE);
		StateBasedEntityManager.getInstance().addState(GAMEPLAY_STATE);
/*		StateBasedEntityManager.getInstance().addState(HIGHSCORE_STATE);  
		StateBasedEntityManager.getInstance().addState(OPTION_STATE);
		StateBasedEntityManager.getInstance().addState(INFO_STATE); */
	}
}