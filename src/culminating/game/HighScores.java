//GLOBAL VARIABLE TO RECORD

package culminating.game;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import static org.newdawn.slick.Color.*;
import org.newdawn.slick.state.*;

public class HighScores extends BasicGameState{
    
    public static ArrayList<String> name=new ArrayList();
    public static ArrayList<Integer> scores=new ArrayList();
    public static ArrayList<String> time=new ArrayList();
    public static ArrayList<Integer> kills=new ArrayList();
    
    public Image background;
    public Image highscores;
    public int scoresY;
    
    public String space;
    public String titles;
    
    Random r=new Random();
    
    public HighScores(int state){
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        //for(int a=0; a<10; a++){scores.add(r.nextInt(20));}
        space="     ";
        background=new Image("assets/pausegame-background.jpg");
        highscores=new Image("assets/highscores.png");
        scoresY=gc.getHeight()+20;
        name.add("0");
        scores.add(0);
        time.add("0");
        kills.add(0);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        
            //g.drawString("String", x, y) TEXT
            //g.drawRect(x, y, widht, height) SHAPE
            //g.drawString("!!!!!UNDER CONSTRUCTION!!!!!\nTOLD YOU IT WAS COMING SOON\nNOW PRESS ESCAPE YOU BAKA", 900/2-150, 640/2);
        
        background.draw(-50, 0, gc.getWidth()+100, gc.getHeight());
        highscores.draw(gc.getWidth()/2-230, 20, 500, 80);
        g.setColor(white);
        g.drawString(titles, gc.getWidth()/2-225, scoresY-20);
        for(int a=0; a<scores.size(); a++){
            g.drawString(""+(a+1), gc.getWidth()/2-225, scoresY+20*a);
            g.drawString(name.get(a), gc.getWidth()/2-125, scoresY+20*a);
            g.drawString(""+scores.get(a), gc.getWidth()/2-25, scoresY+20*a);
            g.drawString(time.get(a), gc.getWidth()/2+75, scoresY+20*a);
            g.drawString(""+kills.get(a), gc.getWidth()/2+175, scoresY+20*a);
        }
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        if(ScoresFiles.read==true){
            
            try {    
                ScoresFiles.readScores();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
            }
            ScoresFiles.read=false;
        }
        
        titles="Rank  "+space+"Name  "+space+"Score"+space+" Time"+space+"  Kills";
        
        name=ScoresFiles.name;
        scores=ScoresFiles.scores;
        time=ScoresFiles.time;
        kills=ScoresFiles.kills;
        
        Input input=gc.getInput();
        if(input.isKeyDown(Input.KEY_ESCAPE)){scoresY=gc.getHeight()+20;sbg.enterState(0);}
        if(scoresY>=120)scoresY--;
    }
    
    @Override
    public int getID(){return 3;}
    
}
