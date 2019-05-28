package team.lj.Tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Grass {
	private int x,y;
   	Game game;
   	public Grass(int x,int y,Game game){
   		this.x=x;
   		this.y=y;
   		this.game=game;
   	}
   	
   	Image image=Toolkit.getDefaultToolkit().getImage("src/Image/map/grass.png");
   	
   	public void paint(Graphics g){
   		g.drawImage(image,x,y,null);
   	}
   	
   	public static final void set(Game g){
   		if(Game.level==3){		
   	   		for(int i=0;i<3;i++){g.grasses.add(new Grass(64+i*64,152,g));}
   	   		for(int i=0;i<6;i++){g.grasses.add(new Grass(352+i*64,152,g));}
   	   		for(int i=0;i<3;i++){g.grasses.add(new Grass(352,216+i*64,g));}
   	   		for(int i=0;i<3;i++){g.grasses.add(new Grass(416,216+i*64,g));}
   	   		for(int i=0;i<5;i++){g.grasses.add(new Grass(352-i*64,344,g));}
   	   		for(int i=0;i<2;i++){g.grasses.add(new Grass(512+i*64,376,g));}
   	   		for(int i=0;i<2;i++){g.grasses.add(new Grass(512,440+i*64,g));}
   	   		for(int i=0;i<2;i++){g.grasses.add(new Grass(96,408+i*64,g));}
   	   		for(int i=0;i<2;i++){g.grasses.add(new Grass(224,408+i*64,g));}
   	   	}else if(Game.level==2){
   	   	    for(int i=0;i<5;i++){g.grasses.add(new Grass(i*64,248,g));}
   	     	for(int i=0;i<3;i++){g.grasses.add(new Grass(640+i*64,248,g));}
   	   	}else if(Game.level==1){
   	   	    for(int i=0;i<3;i++){g.grasses.add(new Grass(64+i*64,248,g));}
   	        for(int i=0;i<3;i++){g.grasses.add(new Grass(512+i*64,248,g));}
   	   	}
   	}
   	
}
