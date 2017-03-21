

package culminating.game;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Credits extends BasicGameState{
    
    private float creditsY;
    private Image background;
    
    
    public Credits(int state){
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        //creditsY=gc.getHeight()/2;
        creditsY=gc.getHeight()+5;
        background=new Image("assets/credits-background.jpg");
        
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        //g.drawString("String", x, y) TEXT
        //g.drawRect(x, y, widht, height) SHAPE
        //g.drawString("!!!!!UNDER CONSTRUCTION!!!!!\nTOLD YOU IT WAS COMING SOON\nNOW PRESS ESCAPE YOU BAKA", 900/2-150, 640/2-100);
        background.draw(-50, 0, gc.getWidth()+200, gc.getHeight());
        g.drawString("Developed By: \tMuhammad Syed",gc.getWidth()/2-100 ,creditsY+30);
        g.drawString("Tested By: \tNick, Ben, Ali, Jasper, Shuming", gc.getWidth()/2-100, creditsY+60);
        g.drawString("Special Thanks To: ", gc.getWidth()/2-100, creditsY+90);
        g.drawString("Slick2D Gaming Library - Development Team",gc.getWidth()/2-200,creditsY+120);
        g.drawString("TheNewBoston (www.youtube.com/thenewboston)", gc.getWidth()/2-200, creditsY+150);
        g.drawString("RealTutsGML (www.youtube.com/realtutsgml)", gc.getWidth()/2-200, creditsY+180);
        g.drawString("www.wallpaperbackgrounds.com - For the background images", gc.getWidth()/2-200, creditsY+210);
        g.drawString("Created using NetBeans IDE 8.0 and Slick 2D gaming library", gc.getWidth()/2-260, creditsY+270);
        g.drawString("Have fun playing the game :)",gc.getWidth()/2-110,creditsY+300);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        Input input=gc.getInput();
        if(input.isKeyDown(Input.KEY_ESCAPE)){sbg.enterState(0);creditsY=gc.getHeight()+5;}
        if(creditsY>-330)creditsY-=1;
        if(creditsY<-300){sbg.enterState(0);creditsY=gc.getHeight()+5;}
    }
    
    @Override
    public int getID(){return 4;}
    
}
