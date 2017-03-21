//WRITE SCORES!!!:(:(:(:)

package culminating.game;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;


public class ScoresFiles {
    
    public static ArrayList<String> name=new ArrayList();
    public static ArrayList<Integer> scores=new ArrayList();
    public static ArrayList<String> time=new ArrayList();
    public static ArrayList<Integer> kills=new ArrayList();
    
    public static boolean read=true;
    
    public static void writeScores() throws FileNotFoundException, UnsupportedEncodingException{
        
        PrintWriter writer = new PrintWriter("highScores.txt", "UTF-8");
        writer.println(name.size());
        for(int c=0; c<name.size(); c++){
            writer.println(name.get(c)+" "+scores.get(c)+" "+time.get(c)+" "+kills.get(c));
        }
        //writer.println("The second line");
        writer.close();
    
    
    }
    
    public static void readScores() throws FileNotFoundException{
        Scanner input=new Scanner(new File("highScores.txt"));
        int hm=Integer.parseInt(input.next());
        for(int a=0; a<hm; a++){
            //System.out.println(input.next());
            name.add(input.next());
            //System.out.println(input.next());
            scores.add(Integer.parseInt(input.next()));
            //System.out.println(input.next());
            time.add(input.next());
            //System.out.println(input.next());
            kills.add(Integer.parseInt(input.next()));
        }
        
    }
    
    public static void organize(){
        int tempscore, tempkill;
        String tempname, temptime;
        
        for(int a=0; a<scores.size(); a++){
            for(int b=0; b<scores.size()-1; b++){
                if(scores.get(b)<(scores.get(b+1))){
                   tempname=name.get(b);
                   name.set(b, name.get(b+1));
                   name.set(b+1,tempname);
                   
                   tempscore=scores.get(b);
                   scores.set(b, scores.get(b+1));
                   scores.set(b+1,tempscore);
                   
                   temptime=time.get(b);
                   time.set(b, time.get(b+1));
                   time.set(b+1,temptime);
                   
                   tempkill=kills.get(b);
                   kills.set(b, kills.get(b+1));
                   kills.set(b+1,tempkill);

                }
            }
        }
        //for(int a=0; a<scores.size(); a++){System.out.println(scores.get(a));}
        
    
    }
    
       
}
