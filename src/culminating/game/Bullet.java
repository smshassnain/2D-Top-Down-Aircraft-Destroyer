

package culminating.game;

import java.util.LinkedList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;

public class Bullet {
    
    public int bulletX;
    public int bulletY;
    
    public Image bullet;
    
    //public LinkedList<Enemy> e=new LinkedList<Enemy>();
    //Controller c;
    //Play p;
    
    public Bullet(int bulletX, int bulletY)throws SlickException{
        this.bulletX=bulletX;
        this.bulletY=bulletY;
        bullet = new Image("assets/bullet.png");
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        //initializing stuff//USUALLY GIVES A NULL POINT ERROR EXCEPTION
        //e=c.e;
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        //g.drawString("String", x, y) TEXT
        //g.drawRect(x, y, widht, height) SHAPE
        bullet.draw(bulletX, bulletY, 10, 20);
        
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        bulletY-=5;//bullet speed
    }
    
    public Rectangle getBounds(){
        return new Rectangle(bulletX, bulletY, 10, 20);
    }
    
}
