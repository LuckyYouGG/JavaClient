package team.lj.Tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class River {
    private int x,y,n;	
    Game game;
    public River(int x,int y,int n,Game game){
   	 this.x=x;
   	 this.y=y;
   	 this.n=n;
   	 this.game=game;
    }

    Image image[]={Toolkit.getDefaultToolkit().getImage("src/Image/map/river1.jpg"),
   		 Toolkit.getDefaultToolkit().getImage("src/Image/map/river2.jpg"),
   		 Toolkit.getDefaultToolkit().getImage("src/Image/map/river3.jpg")   
    };
    
    
    
    public void paint(Graphics g){
   	 if(n==1){
   		 g.drawImage(image[0],x,y,null);
   	 }else if(n==2){
   		 g.drawImage(image[1],x,y,null);
   	 }else if(n==3){
   		 g.drawImage(image[2],x,y,null);
   	 }
   	 
    }
    
    public static final void set(Game g){
   	 if(Game.level==3){		 
       	 for(int i=0;i<3;i++){
       	 River river=new River(i*64,280,1,g);
       	 g.rivers.add(river);
       	 }
            for(int i=0;i<4;i++){
            River river=new River(i*64+576,280,1,g);
            g.rivers.add(river);
            }
            River r1=new River(192,280,2,g);
            g.rivers.add(r1);
            River r2=new River(512,280,3,g);
            g.rivers.add(r2);
        }else if(Game.level==2){
       	 g.rivers.add(new River(320,248,3,g)); 
       	 g.rivers.add(new River(576,248,2,g));
       	 for(int i=0;i<3;i++){
           	 River river=new River(384+i*64,248,1,g);
           	 g.rivers.add(river);
           	 }        	 
        }else if(Game.level==1){
       	 
        }
    }
    
    public Rectangle getRect(){
   	 return new Rectangle(x,y,50,64);
    }
    
   
    
}
