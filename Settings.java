import java.awt.Color;

public class Settings{
    
    //Settings for Time class --> Game Loop Update FPS Constants
    public static final int NANO_SEC = 1000000000;
    public static final double FPS = 60;
    public static final double TIME_BETWEEN_UPDATES = NANO_SEC / FPS;
    
    //Constants for the main viewing window
    public static final int CANVAS_WIDTH = 1200;
    public static final int CANVAS_HEIGHT = 800;
    public static final Color WHITE = Color.WHITE;
    public static final Color BLACK = Color.BLACK;
    public static final Color BLUE = Color.BLUE;

    //Player's Sprite  Constants
    public static final String PLAYER_FILE_NAME = "Images/Ghost.png";
    public static final int PLAYER_HEIGHT = 64;
    public static final int PLAYER_WIDTH = 64;
    public static final int PLAYER_START_X = CANVAS_WIDTH / 2;
    public static final int PLAYER_START_Y = CANVAS_HEIGHT / 2;
    public static final int PLAYER_SPEED = 5;

    //Enemy's Sprite  Constants
    public static final String ENEMY_FILE_NAME = "Images/Star.png";
    public static final int ENEMY_HEIGHT = 64;
    public static final int ENEMY_WIDTH = 64;
    public static final int[] ENEMY_CENTER = {ENEMY_WIDTH/2, ENEMY_HEIGHT/2};
    public static final double ENEMY_ACCELERATION = 0.005;
    public static final double ENEMY_ACCELERATION_CAP = 10;
    public static final int ENEMY_SPEED = 1;
    public static final int ENEMY_OUT = 100;
    public static final int ENEMY_OUT_X = CANVAS_WIDTH + ENEMY_OUT;
    public static final int ENEMY_OUT_Y = CANVAS_HEIGHT + ENEMY_OUT;
    

    

}