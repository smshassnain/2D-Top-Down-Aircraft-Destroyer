

package culminating.game;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Game extends StateBasedGame{
    
    public static final String GameName="Sky Fire";
    public static final int menu=0;
    public static final int play=1;
    public static final int howtoplay=2;
    public static final int highscores=3;
    public static final int credits=4;
    public static final int pausegame=5;
    
    //more states after done main game ex. highscores/how to play
    
    public Game(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new HowToPlay(howtoplay));
        this.addState(new HighScores(highscores));
        this.addState(new Credits(credits));
        this.addState(new PauseGame(pausegame));
        //more states after done main game
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException{
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.getState(howtoplay).init(gc, this);
        this.getState(highscores).init(gc, this);
        this.getState(credits).init(gc, this);
        this.getState(pausegame).init(gc, this);
        this.enterState(menu);//CHANGE TO MAIN MENU AFTER
    }
    
    public static void main(String[] args) {
        AppGameContainer appgc;
        try{
            appgc= new AppGameContainer(new Game(GameName));
            appgc.setDisplayMode(900, 640, false);//width, height, fullscreen
            appgc.setTargetFrameRate(100);
            appgc.start();            
        }catch(SlickException e){
            e.printStackTrace();
        }
        
        
    }
    
}
