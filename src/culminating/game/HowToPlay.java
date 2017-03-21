

package culminating.game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class HowToPlay extends BasicGameState{
    
    public String[] keys={"assets/up_key.png","assets/down_key.png","assets/left_key.png","assets/right_key.png"};
    public Image key;
    public Image space;
    public Image player;
    public int playerX, playerY;
    public Image enemy;
    public Image enemy2;
    public Image bullet;
    public int bulletX, bulletY;
    
    public Image background;
    
    public int way=1;
    
    public HowToPlay(int state){
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        key= new Image(keys[0]);
        
        background= new Image("assets/howtoplay-background.jpg");
        
        player= new Image("assets/jet2.png");
        enemy= new Image("assets/jetN.png");
        enemy2= new Image("assets/jet3N.png");
        bullet= new Image("assets/bullet.png");
        space= new Image("assets/space_key.png");
        
        playerY=80;playerX=700;
        bulletX=718;bulletY=gc.getHeight()/2+137;
        
        
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        //g.drawString("String", x, y) TEXT
        //g.drawRect(x, y, widht, height) SHAPE
        //g.drawString("!!!!!UNDER CONSTRUCTION!!!!!\nTOLD YOU IT WAS COMING SOON\nNOW PRESS ESCAPE YOU BAKA", 900/2-150, 640/2);
        
        background.draw(-50, 0, gc.getWidth()+100, gc.getHeight());
        
        player.draw(10, 40, 60, 85);g.drawString("Player Aircraft", 100, 77);
        enemy.draw(10, 180, 60, 60);g.drawString("Enemy Aircraft (1 hit needed to kill)", 100, 195);
        enemy2.draw(10, 300, 75, 90);g.drawString("Enemy Aircraft 2 (2 hits needed to kill)", 100, 350);
        bullet.draw(34, 425, 10, 20);g.drawString("Bullet", 100, 430);
        g.drawString("POWER-UPS!", 10, 475);
        g.drawString("-Press F\nYou may sacrifice 10 kills to kill some random enemies on screen :)\nRecommended to be used after 3:00 min :P", 50, 500);
        
        
        key.draw(695, 250, 40, 40);
        player.draw(playerX, playerY, 45, 64);
        //player.draw(gc.getWidth()-100, gc.getHeight()-50, 85, 60);
        g.drawString("MOVEMENT",682,290);
        
        player.draw(700, gc.getHeight()/2+150, 45, 64);
        bullet.draw(bulletX, bulletY, 7, 14);
        space.draw(580, gc.getHeight()/2+230, 300, 40);
        g.drawString("SHOOTING", 680, 590);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        Input input=gc.getInput();
        if(input.isKeyDown(Input.KEY_ESCAPE)){sbg.enterState(0);}
        
        //MOVEMENT DEMO
        if(way==1){
            playerY--;
            if(playerY<=0){way=2;key=new Image(keys[1]);playerY=80;}
        }
        else if(way==2){
            playerY++;
            if(playerY>=190){way=3;key=new Image(keys[2]);playerY=80;}
        }
        else if(way==3){
            playerX--;
            if(playerX<=570){way=4;key=new Image(keys[3]);playerX=700;}
        }
        else if(way==4){
            playerX++;
            if(playerX>=gc.getWidth()-60){way=1;key=new Image(keys[0]); playerX=700;}
        }
        
        //BULLET DEMO
        bulletY--;
        if(bulletY<=350){bulletY=gc.getHeight()/2+137;}
        
        
    }
    
    @Override
    public int getID(){return 2;}
    
}
