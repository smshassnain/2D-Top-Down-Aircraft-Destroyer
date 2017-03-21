
package culminating.game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import static org.newdawn.slick.Color.white;
import org.newdawn.slick.openal.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.*;
//import java.util.Random;

public class Menu extends BasicGameState{
    
    //Random r=new Random();
    public int selection=1;
    private Image title;
    private Image background;
    private Image point;
    public float pointX=100, pointY=100, pointH=30;
    private Image playText, howtoplayText, highscoresText, creditsText;
    private int menuX, menuY;
    private int spacing=60;
    private boolean increase=false;
    
    public Image mSelect;
    public int mSelectY, mSelectX;
    public boolean st=false;
    public int stime=0;
    
    //public Audio music;
    public Audio music;
    public Audio select;
    public Audio shatter;
    
    public Menu(int state){
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        background=new Image("assets/background.jpg");
        title=new Image("assets/skyfire.png");
        point=new Image("assets/pointer.png");        
        playText=new Image("assets/play.png");
        howtoplayText=new Image("assets/howtoplay.png");
        highscoresText=new Image("assets/highscores.png");
        creditsText=new Image("assets/credits.png");
        mSelect=new Image("assets/select.png");
        
        menuX=gc.getWidth()/2-117;
        menuY=gc.getHeight()/2-75;
        pointX=menuX-60;
        pointY=menuY+5;
        mSelectY=-100;
        mSelectX=-100;
        
        
        
        
        //music.loop(1.0f, 0.75f);//SoundStore.get().poll(0);  
        
        try {
            music = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("assets/Music.ogg"));
            select = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("assets/select.wav"));
            shatter = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("assets/shatter.wav"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        music.playAsMusic(1.0f, 0.5f, true);
        
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        //g.drawString("String", x, y) TEXT
        //g.drawRect(x, y, widht, height) SHAPE
        g.setColor(white);
        background.draw(-50,0, gc.getWidth()+100, gc.getHeight());
        title.draw(gc.getWidth()/2-600, 0, 1200, 200);
        point.draw(pointX, pointY, 43, pointH);
        playText.draw(menuX, menuY, 80, 45);
        howtoplayText.draw(menuX, menuY+spacing, 250, 40);
        highscoresText.draw(menuX, menuY+spacing*2, 250, 40);//g.drawString("(COMING SOON!!!)",menuX+257,menuY+spacing*2+5);
        creditsText.draw(menuX, menuY+spacing*3, 150, 40);
        g.drawString("Press Esc key on any Screen to return to the main menu", gc.getWidth()/2-216, gc.getHeight()-27);
        //g.drawString(String.valueOf(pointH), 400, 300);
        //music.loop(1.0f,1.0f);
        mSelect.draw(mSelectX, mSelectY, 95, 70);
                
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        Input input= gc.getInput();
        
        //music.playAsSoundEffect(1.0f, 1.0f, true);
        //if(!Thread.interrupted()){music.playAsSoundEffect(1.0f, 0.01f, true);}
       
        
        if(input.isKeyPressed(Input.KEY_UP) && selection>1){pointY-=60; selection--;}
        if(input.isKeyPressed(Input.KEY_DOWN) && selection<4){pointY+=60; selection++;}
        
                
        if(input.isKeyPressed(Input.KEY_ENTER)){
            
            if(selection==1){mSelectX=menuX+5;mSelectY=menuY-10;}
            else if(selection==2){mSelectX=menuX+75;mSelectY=menuY+spacing-10;}
            else if(selection==3){mSelectX=menuX+75;mSelectY=menuY+spacing*2-10;}
            else if(selection==4){mSelectX=menuX+40;mSelectY=menuY+spacing*3-10;}
            st=true;
            
            select.playAsSoundEffect(1.0f, 1.0f, false);
            shatter.playAsSoundEffect(1.0f, 1.0f, false);
        }
        
        if(st==true){stime++;}
        if(stime>=50){
                
                if(selection==3){
                    ScoresFiles.read=true;  
                    ScoresFiles.name.clear();
                    ScoresFiles.scores.clear();
                    ScoresFiles.time.clear();
                    ScoresFiles.kills.clear();
                                      
                }
            
                sbg.enterState(selection);/*GO TO THE STATE*/
                
                selection=1;
                pointX=menuX-60;
                pointY=menuY+15;
                mSelectX=-100;
                mSelectY=-100;
                st=false;
                stime=0;
            }
        
        
        if(increase==false){if(pointH<=2){increase=true;}pointH-=0.40;pointY+=0.20;}
        else if(increase==true){if(pointH>=30){increase=false;}pointH+=0.40;pointY-=0.20;}
        
        //music.loop(1.0f,1.0f);
        //SoundStore.get().poll(0);
        
        
    }
    
    @Override
    public int getID(){return 0;}
    
    
}
