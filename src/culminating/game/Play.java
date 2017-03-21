//MAKE SOMETHING THAT RESETS THIS STATE!!!
package culminating.game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import static org.newdawn.slick.Color.white;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.openal.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.ResourceLoader;

public class Play extends BasicGameState{
    //Bullet b=new Bullet();
    Controller c;
    
    private Image player;
    public static int playerX, playerY, playerW=60, playerH=85;
    private int speed=3;
    private Image background;
    public float backgroundY, backgroundH;
    Music mainMusic;
    public int time;
    public String timee="00:00";
    
    public int etimer;
    public boolean playerAlive;
    public int health;
    public String healthTemp="";
    public float healthP;
    public Image healthBar;
    public int barW;
    
    public int enemiesKilled;
    public String eKilledTemp="";
    public int totalScore;
    public String tScoreTemp="";
    public int spawnTime;
    
    public Image explode;
    public int explodeX, explodeY;
    public int stay;
    public boolean s;
    
    public LinkedList<Enemy> e=new LinkedList<Enemy>();
    public LinkedList<Bullet> b=new LinkedList<Bullet>();
    
    public Audio shoot;
    public Audio eKill;
    public Audio pKill;
    public boolean opPower;
    
    public Image shot;
    public Image GameOver;
    public Audio gameover;
    public int goX, goY;
    
    public Audio select;
    public Audio shatter;
    public int shot1X, shot1Y, shot2X, shot2Y;
    
    public Audio over9000;
    public boolean o9;
    
    public int triggerHappy;
    public int triggerlimit;
    
    public int exit;
    
    Menu m;
    PauseGame pg;
    
    public Play(int state){
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        background=new Image("assets/play-background.jpg");//SWITCH WITH NEW BACKGROUND
        c=new Controller();
        player=new Image("assets/jet2.png");
        playerY=gc.getHeight()/2;
        playerX=gc.getWidth()/2;
        backgroundH=gc.getHeight()*2;
        backgroundY=0-backgroundH+gc.getHeight();
        healthBar=new Image("assets/bar.png");
        barW=gc.getWidth()-40;
        
        explode = new Image("assets/explode.png");
        shot=new Image("assets/select.png");
        GameOver= new Image("assets/gameover.png");
        shot1X=-200;
        shot1Y=-200;
        shot2X=-200;
        shot2Y=-200;
        
        explodeX=-100;explodeY=-100;
        o9=true;
        
        health=9;
        etimer=0;
        time=0;
        healthP=100;
        speed=3;
        playerAlive=true;
        totalScore=0;
        enemiesKilled=0;
        spawnTime=300;
        stay=0;
        s=false;
        goX=-500;goY=-500;
        opPower=false;
        
        triggerlimit=10;
        triggerHappy=triggerlimit;
        //pg.pAlive=true;
        
        e=c.e;
        b=c.b;
        
        try {
            shoot = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("assets/shots.wav"));
            eKill = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("assets/eKill.wav"));
            pKill = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("assets/pKill.wav"));
            gameover = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("assets/gameover.wav"));
            select = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("assets/select.wav"));
            shatter = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("assets/shatter.wav"));
            over9000 = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("assets/over9000.wav"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        //g.drawString("String", x, y) TEXT
        //g.drawRect(x, y, widht, height) SHAPE
        //g.drawImage(jet, x, y);//g.drawImage(object, x, y) original size
        g.setColor(white);
        background.draw(0,backgroundY,(float)gc.getWidth(),backgroundH);
        player.draw(playerX, playerY, playerW, playerH);//object.draw(x, y, width, height)
        c.render(gc, sbg, g);
        g.drawString(timee,gc.getWidth()/2-15,10);
        //timee="";
        
        //g.drawString(healthTemp, gc.getWidth()-200, 7);
        g.drawString(eKilledTemp, gc.getWidth()-200, 7);
        g.drawString(tScoreTemp, gc.getWidth()-200, 27);
        explode.draw(explodeX, explodeY, 80, 60);
        
        g.drawRect(20, gc.getHeight()-30, gc.getWidth()-39, 25);
        healthBar.draw(21, gc.getHeight()-29, barW, 24);
        g.drawString(healthTemp, gc.getWidth()-90, gc.getHeight()-25);
        
        GameOver.draw(goX, goY, 800, 600);
        shot.draw(shot1X, shot1Y, 150, 125);
        shot.draw(shot2X, shot2Y, 150, 125);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        Input input=gc.getInput();
        Random r=new Random();
        
        health=PlayerAlive.phealth;
        
        //healthTemp="Lives Left: "+health;
        if(healthP>(float)(100-(11*(9-health)))){healthP-=0.15;if(health==0){healthP=0;}}
            healthTemp=String.format("%.2f%1s",healthP,"%");
            
        eKilledTemp="Enemies Killed: "+enemiesKilled;
        
        //PLAYER ENMEY COLLISION
        for(int i=0; i<e.size(); i++){
            if(getBounds().intersects(e.get(i).getBounds())){
                c.removeEnemy(e.get(i));health--;pKill.playAsSoundEffect(1.0f, 1.0f, false);
                PlayerAlive.phealth--;
            }
        }//COLLISION OF PLAYER WITH ENEMY
        if(health<=0){
            playerAlive=false;
            player=new Image("assets/explode.png");
            playerW=100;
            //m.music.stop();
            if(exit==0){
                shot1X=r.nextInt(gc.getWidth()-150)+1;
                shot1Y=r.nextInt(gc.getHeight()-125)+1;
                select.playAsSoundEffect(1.0f, 1.0f, false);
                shatter.playAsSoundEffect(1.0f, 0.75f, false);
            }
            if(exit==100){
                shot2X=r.nextInt(gc.getWidth()-150)+1;
                shot2Y=r.nextInt(gc.getHeight()-125)+1;
                select.playAsSoundEffect(1.0f, 1.0f, false);
                shatter.playAsSoundEffect(1.0f, 0.75f, false);
                gameover.playAsSoundEffect(1.0f, 50.0f, false);
            }
            if(exit==200){
                goX=40;
                goY=40;
                //gameover.playAsSoundEffect(1.0f, 1.0f, false);                        
            }
            
        }
        
        if(playerAlive==false){            
            exit++;
            for(int i=0; i<e.size(); i++){
                    if(e.get(i).enemyX<gc.getWidth() && e.get(i).enemyY<gc.getHeight()){
                        c.removeEnemy(e.get(i));
                    }
                }
            if(exit>=425){
                  
                ScoresFiles.name.clear();
                ScoresFiles.scores.clear();
                ScoresFiles.time.clear();
                ScoresFiles.kills.clear();
                
                ScoresFiles.name.add("YOU");
                ScoresFiles.scores.add(totalScore);
                ScoresFiles.time.add(timee);
                ScoresFiles.kills.add(enemiesKilled);
                try {    
                ScoresFiles.readScores();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(HighScores.class.getName()).log(Level.SEVERE, null, ex);
                }
                ScoresFiles.organize();
                try {
                    ScoresFiles.writeScores();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                player=new Image("assets/jet2.png");
                time=0;
                etimer=0;
                spawnTime=300;
                enemiesKilled=0;
                totalScore=0;
                
                health=9;
                PlayerAlive.phealth=9;
                playerY=gc.getHeight()/2;
                playerX=gc.getWidth()/2;
                playerW=60; playerH=85;
                speed=3;

                shot1X=-200;shot1Y=-200;
                shot2X=-200;shot2Y=-200;
                goX=-1000;goY=-1000;
                exit=0;
                playerAlive=true;
                barW=gc.getWidth()-40;
                healthP=100;
                
                o9=true;
                
                sbg.enterState(0);
            }
        }//ADD SCORES TO THE FILE HERE//RESET EVERYTHING
        
        //BULLET ENEMY COLLISION
        for(int i=0; i<b.size(); i++){
            for(int j=0; j<e.size(); j++){
                if(b.get(i).getBounds().intersects(e.get(j).getBounds())){
                    //c.removeBullet(b.get(i));
                    b.get(i).bulletY=-10;
                    e.get(j).eHealth--;
                    //e.get(j).enemy=new Image("assets/explode.png");
                    if(e.get(j).eHealth==0){
                    explodeX=e.get(j).enemyX;
                    explodeY=e.get(j).enemyY;
                    c.removeEnemy(e.get(j));
                    i=0;
                    j=0;
                    enemiesKilled++;
                    eKill.playAsSoundEffect(1.0f, 3.0f, false);
                    s=true;}
                    
                }
            }
        }
        
        //EXPLODE EFFECT
        if(s==true)stay++;
        if(stay>=15){explodeX=-100;explodeY=-100;stay=0;s=false;}
        
        //KEY INPUT
        if((input.isKeyDown(Input.KEY_UP)&&playerY>0)&&playerAlive==true){playerY-=speed;}
        if((input.isKeyDown(Input.KEY_DOWN)&&playerY<gc.getHeight()-playerH)&&playerAlive==true){playerY+=speed;}
        if((input.isKeyDown(Input.KEY_RIGHT)&&playerX<gc.getWidth()-playerW)&&playerAlive==true){playerX+=speed;}
        if((input.isKeyDown(Input.KEY_LEFT)&&playerX>0)&&playerAlive==true){playerX-=speed;}
        if(input.isKeyDown(Input.KEY_ESCAPE)){sbg.enterState(5);}
        
        c.update(gc, sbg, delta);
        
            //KEYDOWN FOR laser SPECIAL 5 SEC AFTER DONE GAME
        //triggerHappy restriction for bullets per sec
        if((input.isKeyPressed(Input.KEY_SPACE))&&playerAlive==true&&triggerHappy<=0){
            c.addBullet(new Bullet(playerX+24, playerY-20));
            shoot.playAsSoundEffect(1.0f, 0.50f, false);
            triggerHappy=triggerlimit;
        }
        
        if((input.isKeyPressed(Input.KEY_F)) && enemiesKilled>=10){
            enemiesKilled-=10;
            opPower=true;
        }
        if(opPower==true){
            for(int i=0; i<e.size(); i++){
                    if((e.get(i).enemyX>=0 && e.get(i).enemyX<=gc.getWidth()) && (e.get(i).enemyY>=0 && e.get(i).enemyY<=gc.getHeight())){
                        c.removeEnemy(e.get(i));
                        enemiesKilled++;
                    }
                }
            opPower=false;
        }
        
        //TO CHECK ENEMY SPAWN//if(input.isKeyDown(Input.KEY_F)){c.addEnemy(new Enemy(r.nextInt(4)));}
        etimer++;
        triggerHappy--;
        //1 SEC IS 100 ACCUMALATION
        time++;
        timee=""+time/6000;
        timee+=":"+(time%6000)/100;
        if(etimer>=spawnTime){//CHANGE WITH SPAWNTIME VARIABLE ACCORDING TO KILLS OR TIME
            c.addEnemy(new Enemy(r.nextInt(8)));
            etimer=0;
        }
        
        if(time>1500){spawnTime=250;}
        if(time>3000){spawnTime=200;}
        if(time>4500){spawnTime=150;speed=4;}
        if(time>6000){spawnTime=100;}
        if(time>7500){spawnTime=75;speed=5;}
        if(time>9000){spawnTime=50;}
        if(time>10500){spawnTime=45;}
        if(time>15000){spawnTime=35;}
        if(time>18000){spawnTime=25;}
        totalScore=(enemiesKilled*time)/100;
        tScoreTemp="Score: "+totalScore;
        
        if(backgroundY>=0){backgroundY=0-backgroundH+gc.getHeight();}
        if(backgroundY<0)backgroundY+=0.2;
        
        //HEALTH BAR
        if(health<9)
        if(barW>((gc.getWidth()-40)-(((gc.getWidth()-40)/9)*(9-health)))){barW--;}
        
        if(totalScore>=9000 && o9==true){over9000.playAsSoundEffect(1.0f, 100.0f, false);o9=false;}
        
    }
    
    @Override
    public int getID(){return 1;}
    
    
    public Rectangle getBounds(){
        return new Rectangle(playerX, playerY, playerW, playerH);    
    }
    
}

/*
    IS PAUSE STATE IS MADE
                player=new Image("assets/jet2.png");
                time=0;
                etimer=0;
                spawnTime=300;
                enemiesKilled=0;
                totalScore=0;
                
                health=9;
                playerY=gc.getHeight()/2;
                playerX=gc.getWidth()/2;
                playerW=60; playerH=85;
                speed=3;

                shot1X=-200;shot1Y=-200;
                shot2X=-200;shot2Y=-200;
                goX=-1000;goY=-1000;
                exit=0;
                playerAlive=true;
                barW=gc.getWidth()-40;
                healthP=100;
*/