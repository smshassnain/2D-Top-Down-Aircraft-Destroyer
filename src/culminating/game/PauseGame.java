

package culminating.game;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;



public class PauseGame extends BasicGameState{
    
    public Image background;
    public Image gamepause;
    public Image resume;
    public Image exit;
    public Image pointer;
    public float pointerX, pointerY, pointerH=30;
    public boolean increase=false;
    //public boolean pAlive=true;
    public boolean up=false;
    //public int chealth=PlayerAlive.phealth;
    public int select=1;
    //Play p;
    
    public PauseGame(int state){
    }

        
    //@Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        background=new Image("assets/pausegame-background.jpg");
        gamepause=new Image("assets/gamepause.png");
        resume=new Image("assets/resume.png");
        exit=new Image("assets/exit.png");
        pointer=new Image("assets/pointer.png");
        
        pointerX=gc.getWidth()/2-177;
        pointerY=gc.getHeight()/2+5;
        increase=false;
    }
    
    //@Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        //g.drawString("String", x, y) TEXT
        //g.drawRect(x, y, widht, height) SHAPE
        //g.drawString("!!!!!UNDER CONSTRUCTION!!!!!\nTOLD YOU IT WAS COMING SOON\nNOW PRESS ESCAPE YOU BAKA", 900/2-150, 640/2);
        background.draw(-50, 0, gc.getWidth()+100, gc.getHeight());
        gamepause.draw(gc.getWidth()/2-220, 20, 500, 80);
        resume.draw(gc.getWidth()/2-100, gc.getHeight()/2, 135, 40);
        exit.draw(gc.getWidth()/2-100, gc.getHeight()/2+60, 80, 45);
        pointer.draw(pointerX, pointerY, 43, pointerH);
    }
    
    //@Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        Input input=gc.getInput();
        //if(input.isKeyDown(Input.KEY_ESCAPE)){sbg.enterState(0);}
        if(input.isKeyPressed(Input.KEY_UP)&&up==true){pointerY-=60;select=1;up=false;}
        if(input.isKeyPressed(Input.KEY_DOWN)&&up==false){pointerY+=60;select=2;up=true;}
        if(input.isKeyPressed(Input.KEY_ENTER)){
            if(select==2){PlayerAlive.phealth=0;}
            sbg.enterState(1);
            pointerY=gc.getHeight()/2+5;
            up=false;
        }
        
        if(increase==false){if(pointerH<=2){increase=true;}pointerH-=0.40;pointerY+=0.20;}
        else if(increase==true){if(pointerH>=30){increase=false;}pointerH+=0.40;pointerY-=0.20;}
        
    }
    
    //@Override
    public int getID(){return 5;}
    
    
}
