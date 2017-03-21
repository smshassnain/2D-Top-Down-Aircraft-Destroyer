

package culminating.game;

import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;
//import java.util.ArrayList;

public class Enemy {
    
    public int enemyX;
    public int enemyY;
    public int enemyW;
    public int enemyH;
    public int eHealth=0;
    
    public Image enemy;
    public String[] enemies={"assets/jetS.png","assets/jetN.png","assets/jetW.png","assets/jetE.png"};
    public String[] enemies2={"assets/jet3S.png","assets/jet3N.png","assets/jet3W.png","assets/jet3E.png"};
    Random r=new Random();
    public int i;
    
    public Enemy(int direction)throws SlickException{
        i=direction;
        //this.enemyX=enemyX;
        //this.enemyY=enemyY;
        if(i==0){enemyY=-20;enemyX=r.nextInt(840);}
        else if(i==1){enemyY=645;enemyX=r.nextInt(840);}
        else if(i==2){enemyY=r.nextInt(580);enemyX=920;}
        else if(i==3){enemyY=r.nextInt(580);enemyX=-20;}
        
        else if(i==4){enemyY=-20;enemyX=r.nextInt(840);}
        else if(i==5){enemyY=645;enemyX=r.nextInt(840);}
        else if(i==6){enemyY=r.nextInt(580);enemyX=920;}
        else if(i==7){enemyY=r.nextInt(580);enemyX=-20;}
        
        if(i>=0 && i<=3){
            enemy = new Image(enemies[i]);
            enemyW=60;
            enemyH=60;
            eHealth=1;
        }
        else if(i>=4 && i<=7){
            enemy =new Image(enemies2[i-4]);
            if(i==4 || i==5){enemyW=75; enemyH=90;}
            else if(i==6 || i==7){enemyW=90; enemyH=75;}
            eHealth=2;
        }
        
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        //initializing stuff//USUALLY GIVES A NULL POINT ERROR EXCEPTION
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        //g.drawString("String", x, y) TEXT
        //g.drawRect(x, y, widht, height) SHAPE
        enemy.draw(enemyX, enemyY, enemyW, enemyH);
        
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        Random r=new Random();
        if(i==0 || i==4){enemyY+=(r.nextInt(3)+1);}//enemy speed
        else if(i==1 || i==5){enemyY-=(r.nextInt(3)+1);}
        else if(i==2 || i==6){enemyX-=(r.nextInt(3)+1);}
        else if(i==3 || i==7){enemyX+=(r.nextInt(3)+1);}
        
    }
    
    public Rectangle getBounds(){
        return new Rectangle(enemyX, enemyY, enemyW, enemyH);
    }
    
    
    
}
