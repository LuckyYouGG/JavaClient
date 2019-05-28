package team.lj.Tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Wall {
	   private int x,y;
	   	Game game;
	   	private boolean alive=true;
	   

		public Wall(int x,int y,Game game){
	   		this.x=x;
	   		this.y=y;
	   		this.game=game;
	   	}
	   	
	   	Image image=Toolkit.getDefaultToolkit().getImage("src/Image/map/wall.jpg");
	   	
	   	public void paint(Graphics g){
	   		
	   		if(!alive){
	   			game.walls.remove(this);
	   			return;
	   		}
	   		
	   		g.drawImage(image,x,y,null);
	   	}
	   	
	   	public Rectangle getRect(){
	   		return new Rectangle(x,y,32,32);
	   	}
	   	
	   	public static final void set(Game g){
	   		if(Game.level==3){
	   	   		for(int i=0;i<6;i++){
	   	   			Wall w=new Wall(i*32,248,g);
	   	   			g.walls.add(w);
	   	   		}
	   	   		g.walls.add(new Wall(256,312,g));
	   	   		g.walls.add(new Wall(288,312,g));
	   	   		g.walls.add(new Wall(320,312,g));
	   	   		g.walls.add(new Wall(320,280,g));
	   	   		g.walls.add(new Wall(320,248,g));
	   	   		g.walls.add(new Wall(320,216,g));
	   	   		g.walls.add(new Wall(320,152,g));
	   	   		for(int i=0;i<5;i++){g.walls.add(new Wall(320+i*32,120,g));}
	   	   		for(int i=0;i<3;i++){g.walls.add(new Wall(160+i*32,120,g));}
	   	   		for(int i=0;i<5;i++){g.walls.add(new Wall(640+i*32,120,g));}
	   	   		for(int i=0;i<4;i++){g.walls.add(new Wall(576+i*32,248,g));}
	   	   		for(int i=0;i<5;i++){g.walls.add(new Wall(480,216+i*32,g));}
	   	   		for(int i=0;i<2;i++){g.walls.add(new Wall(512+i*32,344,g));}
	   	   		for(int i=0;i<3;i++){g.walls.add(new Wall(704+i*32,344,g));}
	   	   		for(int i=0;i<3;i++){g.walls.add(new Wall(i*32,472,g));}
	   	   		for(int i=0;i<1;i++){g.walls.add(new Wall(160+i*32,472,g));}
	   	   		for(int i=0;i<2;i++){g.walls.add(new Wall(352+i*32,504,g));}
	   	   		for(int i=0;i<2;i++){g.walls.add(new Wall(320,440+i*32,g));}
	   	   		for(int i=0;i<2;i++){g.walls.add(new Wall(320,536+i*32,g));}
	   	   		for(int i=0;i<2;i++){g.walls.add(new Wall(416,440+i*32,g));}
	   	   		for(int i=0;i<2;i++){g.walls.add(new Wall(416,536+i*32,g));}
	   	   		for(int i=0;i<2;i++){g.walls.add(new Wall(640,376+i*32,g));}
	   	   		for(int i=0;i<2;i++){g.walls.add(new Wall(640,472+i*32,g));}
	   	   	}else if(Game.level==2){
	   	   	    for(int i=0;i<5;i++){g.walls.add(new Wall(i*32,120,g));}
	   	      	for(int i=0;i<6;i++){g.walls.add(new Wall(288+i*32,120,g));}
	   	        for(int i=0;i<5;i++){g.walls.add(new Wall(640+i*32,120,g));}
	   	        for(int i=0;i<4;i++){g.walls.add(new Wall(160+i*32,216,g));}
	   	        for(int i=0;i<10;i++){g.walls.add(new Wall(224+i*32,408,g));}
	   	        for(int i=0;i<4;i++){g.walls.add(new Wall(i*32,472,g));}
	   	        for(int i=0;i<4;i++){g.walls.add(new Wall(672+i*32,472,g));}
	   	        for(int i=0;i<2;i++){g.walls.add(new Wall(352+i*32,504,g));}  	        
	   	        for(int i=0;i<3;i++){g.walls.add(new Wall(224,440+i*32,g));}
	   	        for(int i=0;i<3;i++){g.walls.add(new Wall(512,440+i*32,g));}  	        
	   	        for(int i=0;i<3;i++){g.walls.add(new Wall(320,504+i*32,g));}
	   	        for(int i=0;i<3;i++){g.walls.add(new Wall(416,504+i*32,g));}
	   	   	}else if(Game.level==1){
	   	     	for(int i=0;i<6;i++){g.walls.add(new Wall(64+i*32,216,g));}
	   	     	for(int i=0;i<6;i++){g.walls.add(new Wall(512+i*32,216,g));}
	   	     	for(int i=0;i<4;i++){g.walls.add(new Wall(64,88+i*32,g));}
	   	        for(int i=0;i<5;i++){g.walls.add(new Wall(320,88+i*32,g));}
	   	        for(int i=0;i<5;i++){g.walls.add(new Wall(416,88+i*32,g));}
	   	        for(int i=0;i<4;i++){g.walls.add(new Wall(672,88+i*32,g));}
	   	        for(int i=0;i<4;i++){g.walls.add(new Wall(320+i*32,312,g));}
	   	        for(int i=0;i<4;i++){g.walls.add(new Wall(320+i*32,408,g));}
	   	        for(int i=0;i<6;i++){g.walls.add(new Wall(64+i*32,408,g));}
	   	        for(int i=0;i<6;i++){g.walls.add(new Wall(512+i*32,408,g));}
	   	        for(int i=0;i<3;i++){g.walls.add(new Wall(64,440+i*32,g));}
	   	        for(int i=0;i<3;i++){g.walls.add(new Wall(672,440+i*32,g));}
	   	        for(int i=0;i<2;i++){g.walls.add(new Wall(352+i*32,504,g));}  
	   	        for(int i=0;i<3;i++){g.walls.add(new Wall(320,504+i*32,g));}
		        for(int i=0;i<3;i++){g.walls.add(new Wall(416,504+i*32,g));}
	   	   	}
	   	}
	   	
		public void setAlive(boolean alive) {
			this.alive = alive;
		}
	   	
	}
