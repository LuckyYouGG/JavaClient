package team.lj.Tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Steel {
	private int x,y;
   	Game game;
   	public Steel(int x,int y,Game game){
   		this.x=x;
   		this.y=y;
   		this.game=game;
   	}
   	
   	Image image=Toolkit.getDefaultToolkit().getImage("src/Image/map/steel.jpg");
   	
   	public void paint(Graphics g){
   		g.drawImage(image,x,y,null);
   	}
   	
   	public Rectangle getRect(){
   		return new Rectangle(x,y,32,32);
   	}
   	
   	public static final void set(Game g){
   		if(Game.level==3){	
   	   		for(int i=0;i<2;i++){g.steels.add(new Steel(i*32,184,g));}
   	   		for(int i=0;i<3;i++){g.steels.add(new Steel(256+i*32,184,g));}
   	   		for(int i=0;i<2;i++){g.steels.add(new Steel(256,120+i*32,g));}
   	   		for(int i=0;i<3;i++){g.steels.add(new Steel(480+i*32,120,g));}
   	   		for(int i=0;i<4;i++){g.steels.add(new Steel(192,408+i*32,g));}
   	   		for(int i=0;i<2;i++){g.steels.add(new Steel(288+i*32,408,g));}
   	   		for(int i=0;i<2;i++){g.steels.add(new Steel(416+i*32,408,g));}
   	   		for(int i=0;i<2;i++){g.steels.add(new Steel(288+i*32,504,g));}
   	   		for(int i=0;i<2;i++){g.steels.add(new Steel(416+i*32,504,g));}
   	   		for(int i=0;i<4;i++){g.steels.add(new Steel(576+i*32,344,g));}
   	   		for(int i=0;i<4;i++){g.steels.add(new Steel(576+i*32,440,g));}
   	   	}else if(Game.level==2){
   	     	for(int i=0;i<5;i++){g.steels.add(new Steel(480+i*32,216,g));}
   	        for(int i=0;i<7;i++){g.steels.add(new Steel(160+i*32,312,g));}
   	        for(int i=0;i<3;i++){g.steels.add(new Steel(128,408+i*32,g));}
   	        for(int i=0;i<3;i++){g.steels.add(new Steel(640,408+i*32,g));}
   	   	}else if(Game.level==1){
   	        for(int i=0;i<6;i++){g.steels.add(new Steel(64+i*32,312,g));}
   	        for(int i=0;i<6;i++){g.steels.add(new Steel(512+i*32,312,g));}
   	        for(int i=0;i<2;i++){g.steels.add(new Steel(352+i*32,216,g));}
   	   	}
   		
   	}
   	
}
