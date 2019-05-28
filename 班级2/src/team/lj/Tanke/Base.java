package team.lj.Tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import Image.map.Blood;


public class Base {
	private int x,y;
   	Game game;
   	private boolean alive=true;
    Blood blood;
    Explode explode;
    int number=84,topNumber=84;
	public boolean isAlive() {
		return alive;
	}


	public void setAlive(boolean alive) {
		this.alive = alive;
	}


	public Base(int x,int y,Game game){
   		this.x=x;
   		this.y=y;
   		this.game=game;
   	}
	
	Image image1=Toolkit.getDefaultToolkit().getImage("src/Image/map/base1.jpg");
	Image image2=Toolkit.getDefaultToolkit().getImage("src/Image/map/base2.jpg");
	
	public void paint(Graphics g){
   		 
		if(number<=0&&alive){
			alive=false;
		    explode=new Explode(x-32,y-32,game);
	        game.explodes.add(explode);
		}
		
   		if(alive||explode.step<25){
   			g.drawImage(image1,x,y,null);
   		}else if(!alive&&explode.step>=25){
            
   			g.drawImage(image2,x,y,null);
   		} 		
   		blood=new Blood(x+7,y-14,number,topNumber);
	    blood.paint(g);
	
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,64,64);
	}
	
	
}
