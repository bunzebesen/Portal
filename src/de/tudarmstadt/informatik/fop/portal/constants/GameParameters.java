package de.tudarmstadt.informatik.fop.portal.constants;

/**
 * Class for holding the game parameters and constants e.g. entity IDs or image
 * paths
 * 
 * @author Timo Küster, Diana Kolev
 * 
 */
public interface GameParameters{

	// Window Settings
	public static final int WINDOW_WIDTH = 1920;
	public static final int WINDOW_HEIGHT = 1080;
	public static final int FRAME_RATE = 60;

	// Game States
	public static final int MAINMENU_STATE = 0;
	public static final int GAMEPLAY_STATE = 1;
	public static final int INFO_STATE = 2;

	// Background
	public static final String BACKGROUND_ID = "background";
	public static final String BACKGROUND_IMAGE = "/images/background.png";
	
	// Menu
	public static final String MENU_ID = "menu";
	public static final String MENU_IMAGE = "/images/menu.png";
	
	// Player
	public static final String PLAYER_ID = "player";
	public static final String PLAYER_IMAGE = "/images/player.png";
	public static final float PLAYER_SPEED = 0.5f;
	public static final float PLAYER_GRAVITY = 0.4f;
	public static final float PLAYER_JUMP_HEIGHT = 250f;
	public static final int PLAYER_WIDTH = 55;
	public static final int PLAYER_HEIGHT = 69;
	
	// Entry
	public static final String ENTRY_IMAGE = "/images/entry.png";
	
	// Map
	public static final int MAP_WIDTH = 16;
	public static final int MAP_HEIGHT = 54;
	public static final String MAP_NAME = "maps/level.map";
	
	// Border
	public static final String BORDER_IMAGE = "/images/border.png";
	public static final int BORDER_WIDTH = 120;
	public static final int BORDER_HEIGHT = 20;

/*	// Blocks
	public static final String MAP_FILE1 = "maps/level1.map";
	public static final String MAP_FILE2 = "maps/level2.map";
	
	public static final String BLOCK_1_ID = "block1";
	public static final String BLOCK_2_ID = "block2";
	public static final String BLOCK_3_ID = "block3";
	public static final String BLOCK_4_ID = "block4";
	public static final String BLOCK_5_ID = "block5";
	
	public static final String BLOCK_1_IMAGE_CLASSIC = "/images/classic/block_1_classic.png";
	public static final String BLOCK_2_IMAGE_CLASSIC = "/images/classic/block_2_classic.png";
	public static final String BLOCK_3_IMAGE_CLASSIC = "/images/classic/block_3_classic.png";
	public static final String BLOCK_4_IMAGE_CLASSIC = "/images/classic/block_4_classic.png";
	
	public static final String BLOCK_1_IMAGE_MODERN = "/images/modern/block_1_modern.png";
	public static final String BLOCK_2_IMAGE_MODERN = "/images/modern/block_2_modern.png";
	public static final String BLOCK_3_IMAGE_MODERN = "/images/modern/block_3_modern.png";
	public static final String BLOCK_4_IMAGE_MODERN = "/images/modern/block_4_modern.png";
	
	public static final String BLOCK_1_IMAGE_PORO = "/images/poro/block_1_poro.png";
	public static final String BLOCK_2_IMAGE_PORO = "/images/poro/block_2_poro.png";
	public static final String BLOCK_3_IMAGE_PORO = "/images/poro/block_3_poro.png";
	public static final String BLOCK_4_IMAGE_PORO = "/images/poro/block_4_poro.png";

	// Ball
	public static final String BALL_ID = "ball";
	public static final float BALL_WEIGHT = 0.005f; // Masse = 5g	
	public static final float INITIAL_BALL_SPEED = 0.4f;
	public static final float SPEEDUP_VALUE = 0.1f;
	public static final String BALL_IMAGE_CLASSIC = "/images/classic/ball_classic.png";
	public static final String BALL_IMAGE_MODERN = "/images/modern/ball_modern.png";
	public static final String BALL_IMAGE_PORO = "/images/poro/ball_poro.png";

	// Stick
	public static final String STICK_ID = "stick";
	public static final float STICK_SPEED = 0.5f;
	public static final String STICK_IMAGE_CLASSIC = "/images/classic/stick_classic.png";
	public static final String STICK_BIGGER_IMAGE_CLASSIC = "/images/classic/stick_classic_bigger.png";
	public static final String STICK_SMALLER_IMAGE_CLASSIC = "/images/classic/stick_classic_smaller.png";
	public static final String STICK_IMAGE_MODERN = "/images/modern/stick_modern.png";
	public static final String STICK_BIGGER_IMAGE_MODERN = "/images/modern/stick_modern_bigger.png";
	public static final String STICK_SMALLER_IMAGE_MODERN = "/images/modern/stick_modern_smaller.png";
	public static final String STICK_IMAGE_PORO = "/images/poro/stick_poro.png";
	public static final String STICK_BIGGER_IMAGE_PORO = "/images/poro/stick_poro_bigger.png";
	public static final String STICK_SMALLER_IMAGE_PORO = "/images/poro/stick_poro_smaller.png";

	// Pause
	public static final String PAUSE_ID = "pause";
	public static final String PAUSE_IMAGE_CLASSIC = "/images/classic/pause_classic.png";
	public static final String PAUSE_IMAGE_MODERN = "/images/modern/pause_modern.png";
	public static final String PAUSE_IMAGE_PORO = "/images/poro/pause_poro.png";

	// Highscore
	public static final String HIGHSCORE_ID = "highscore";
	public static final String HIGHSCORE_FILE = "highscores/highscore.hsc";
	

	

	
	// Leben
	public static final String HP_IMAGE = "/images/herz.png";
	public static final String HP_IMAGE_CLASSIC = "/images/classic/herz_classic.png";
	public static final String HP_IMAGE_MODERN = "/images/modern/herz_modern.png";
	public static final String HP_IMAGE_PORO = "/images/poro/herz_poro.png";
	public static final int HP_AMOUNT_EASY = 5;
	public static final int HP_AMOUNT_NORMAL = 3;
	public static final int HP_AMOUNT_HARD = 1;
	
	// Zeit
	public static final int TIME_AMOUNT_EASY = 4 * 60000;
	public static final int TIME_AMOUNT_NORMAL = 3 * 60000;
	public static final int TIME_AMOUNT_HARD = 2 * 60000;
	
	// Mapbreite
	public static final int MAP_WIDTH = 16;
	public static final int MAP_HEIGHT = 10;
	
	// Sound
	public static final String HITBLOCK_SOUND = "/sounds/hitBlock.wav";
	public static final String HITSTICK_SOUND = "/sounds/hitStick.wav";
	
	// Items
	public static final String FASTER_IMAGE_CLASSIC = "/images/classic/faster_classic.png";
	public static final String FASTER_IMAGE_MODERN = "/images/modern/faster_modern.png";
	public static final String FASTER_IMAGE_PORO = "/images/poro/faster_poro.png";
	
	public static final String SLOWER_IMAGE_CLASSIC = "/images/classic/slower_classic.png";
	public static final String SLOWER_IMAGE_MODERN = "/images/modern/slower_modern.png";
	public static final String SLOWER_IMAGE_PORO = "/images/poro/slower_poro.png";
	
	public static final String BIGGER_IMAGE_CLASSIC = "/images/classic/bigger_classic.png";
	public static final String BIGGER_IMAGE_MODERN = "/images/modern/bigger_modern.png";
	public static final String BIGGER_IMAGE_PORO = "/images/poro/bigger_poro.png";
	
	public static final String SMALLER_IMAGE_CLASSIC = "/images/classic/smaller_classic.png";
	public static final String SMALLER_IMAGE_MODERN = "/images/modern/smaller_modern.png";
	public static final String SMALLER_IMAGE_PORO = "/images/poro/smaller_poro.png";
	
	public static final String HEART_IMAGE_CLASSIC = "/images/classic/herz_classic.png";
	public static final String HEART_IMAGE_MODERN = "/images/modern/herz_modern.png";
	public static final String HEART_IMAGE_PORO = "/images/poro/herz_poro.png";*/
}
