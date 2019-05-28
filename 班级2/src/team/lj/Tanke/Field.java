package team.lj.Tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;




public class Field {
   int width=92,height=92;
  
   
   
   Image image;
   
   public void paint(Graphics g){
	   
   }
 
   
}

class TField extends Field{
   Tank t;
   Game game;
	public TField(Tank t,Game game) {		
		this.t=t;
		this.game=game;
	}
	Image image=Toolkit.getDefaultToolkit().getImage("src/Image/map/f1.png");
	
   public void paint(Graphics g){
		if(!t.field){
			game.fields.remove(this);
			return;
		}
		hitEnemy(game.enemys);
		g.drawImage(image,t.x-28,t.y-28,null);
	   }
	
	 public Rectangle getRect(){
			return new Rectangle(t.x-28,t.y-28,width,height);
		   }
	
	 public void hitEnemy(java.util.List<Enemy> enemys){
		 for(int j=0;j<enemys.size();j++){
			    Enemy e=enemys.get(j);
			   
			    
			       if(getRect().intersects(e.getRect())&&e.step==19){			    	
			    	e.number=e.number-2;		    	  			    	 
			         e.setAlive();		    	  
			        }
			    
	        	if(!e.isAlive()){
	            Explode explode=new Explode(e.x-45,e.y-45,game);
	            game.explodes.add(explode);}
			  }
	 }
	
	
}

class EField extends Field{
  Enemy e;Game game;
  int step=0;
  boolean exist=false;
	public EField(Enemy e,Game game) {		
		this.e=e;
		this.game=game;
	}
	Image image=Toolkit.getDefaultToolkit().getImage("src/Image/map/f2.png");
	public void paint(Graphics g){
		exist();
		if(!e.isAlive()||!exist){
			game.fields.remove(this);
			return;
		}
		
		hitTank(game.mytank1);
		if(game.numOfPlayer==2){
			hitTank(game.mytank2);
		}
		g.drawImage(image,e.x-19,e.y-19,null);
		step++;
	   }
	 public Rectangle getRect(){
			return new Rectangle(e.x-19,e.y-19,width,height);
		   }
	 public void hitTank(Tank t){			   		    		      
		        if(t.getRect().intersects(getRect())&&step>=10&&t.isAlive()){	
		        	
		    	     step=0;
		    	     t.number--;		    	  			    	 
			         t.setAlive();		    	  
			        			    
	        	if(!t.isAlive()){
	            Explode explode=new Explode(t.x-45,t.y-45,game);
	            game.explodes.add(explode);}			  
	 }
	 }
	 
	 public void exist(){
		 for(int i=0;i<game.enemys.size();i++){
			 Enemy e2=game.enemys.get(i);
			 if(this.e==e2){
				 exist=true;break;
			 }
		 }
	 }
	 
}