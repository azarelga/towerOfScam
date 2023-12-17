package gamestates;

public enum Gamestate {
    PLAYING,MENU,LEVELSELECT,SETTINGS,PAUSE;
    
    public static Gamestate state = MENU;
    public static int level = 0;
    public static int control = 0;
}
