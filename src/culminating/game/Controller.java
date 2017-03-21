

package culminating.game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.util.LinkedList;

public class Controller {
    
    public LinkedList<Bullet> b=new LinkedList<Bullet>();
    public LinkedList<Enemy> e=new LinkedList<Enemy>();
    
    Bullet tempB;
    Enemy tempE;
    
    public Controller(){}
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        for(int i=0; i<b.size(); i++){
            tempB=b.get(i);
            
            if(tempB.bulletY<0){removeBullet(tempB);}
            
            
            tempB.update(gc, sbg, delta);
        }
        
        for(int i=0; i<e.size(); i++){
            tempE=e.get(i);
            
            if(tempE.enemyY>gc.getHeight() && tempE.i==0){removeEnemy(tempE);}
            else if(tempE.enemyY<-70 && tempE.i==1){removeEnemy(tempE);}
            else if(tempE.enemyY<-70 && tempE.i==2){removeEnemy(tempE);}
            else if(tempE.enemyY>gc.getWidth() && tempE.i==3){removeEnemy(tempE);}
            
            tempE.update(gc, sbg, delta);
        }
        
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        //g.drawString("String", x, y) TEXT
        //g.drawRect(x, y, widht, height) SHAPE
        for(int i=0; i<b.size(); i++){
            tempB=b.get(i);
            tempB.render(gc, sbg, g);
        }
        
        for(int i=0; i<e.size(); i++){
            tempE=e.get(i);
            tempE.render(gc, sbg, g);
        }
        
    }
    
    public void addBullet(Bullet thing){
        b.add(thing);
    }
    
    public void removeBullet(Bullet thing){
        b.remove(thing);
    }
    
    
    
    public void addEnemy(Enemy thing){
        e.add(thing);
    }
    
    public void removeEnemy(Enemy thing){
        e.remove(thing);
    }
    
    
}
