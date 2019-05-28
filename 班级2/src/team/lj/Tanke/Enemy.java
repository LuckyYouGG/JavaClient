package team.lj.Tanke;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

import Image.map.Blood;


public class Enemy {
	int x,y,oldX,oldY;;
	 public final static int XSPEED=5,YSPEED=5,WIDTH=40,HEIGHT=40;
	 
	 boolean u=false,d=false,l=false,r=false;
	 protected String direction="null";
	protected String bulletDirection="right";

	 protected boolean alive=true;
	 Game game;
	 protected int step=0;
	 int level,levelblood;
	
	 

	protected int distance=0;
	Blood blood;
	 int number;
	public boolean isBoss;

	 public boolean isAlive() {
		return alive;
	}
	 public void setAlive() {
			if(number<=0){
				alive=false;
			}
		}
	
	 public Enemy(int x,int y,Game g){
		 this.x=x;
		 this.y=y;
		 this.game=g;
	 }
	
	public Enemy(int x,int y,int level,int levelblood,int number,boolean b,Game g){
		this(x,y,g);
		this.levelblood=levelblood;
		this.level=level;
		this.number=number;
		this.isBoss=b;
	 }
	
	
	 public void paint(Graphics g) {
		
		 if(!alive){
			   game.enemys.remove(this);
			   game.totalNumOfEnemy--;
			  
			   setItem();
		 
			   return;
		   }
            step++;
		    oldX=x;oldY=y;
		    
			if(bulletDirection.equals("up")){
				g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bodyu"+level+".png"),x,y,null);
				   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/turretu"+level+".png"),x-14,y-14,null);
				}
				else if(bulletDirection.equals("down")){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bodyd"+level+".png"),x,y,null);
					   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/turretd"+level+".png"),x-14,y-12,null);
				}
				else if(bulletDirection.equals("left")){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bodyl"+level+".png"),x,y,null);
					   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/turretl"+level+".png"),x-14,y-14,null);
				}
				else if(bulletDirection.equals("right")){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bodyr"+level+".png"),x,y,null);
					   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/turretr"+level+".png"),x-12,y-14,null);
			}
			blood=new Blood(x-7,y-14,number,9);
			 blood.paint(g);
			if(!game.pause){
		    move();
		    fire();
			}
		 
		 
	 }
	 public Rectangle getRect(){
		 if(bulletDirection.equals("up")||bulletDirection.equals("down")){return new Rectangle(x,y,36,40);}
		 else{return new Rectangle(x,y,40,36);}
	 }
	 
	 public void move(){
			
			if(direction.equals("up")){
				
				y-=YSPEED;
				}
			else if(direction.equals("down")){
				y+=YSPEED;
			}
			else if(direction.equals("left")){
				x-=XSPEED;
			}
			else if(direction.equals("right")){
				x+=XSPEED;
			}
			else if(direction.equals("null")){
			
			}
			setDirection();
			if(x<0){x=0;direction="right";bulletDirection="right";distance=0;}
			if(y<45){y=45;direction="down";bulletDirection="down";distance=0;}
			if(x>800-WIDTH){x=800-WIDTH;direction="left";bulletDirection="left";distance=0;}
			if(y>600-HEIGHT){y=600-HEIGHT;direction="up";bulletDirection="up";distance=0;}
		    distance++;
	 }
	 
	 public void fire(){  
			int a=0,b=0;
			if(step>=20){
				Bullet bullet=new Bullet(a,b,bulletDirection,3,false,game);
				if(bulletDirection.equals("up")){
					 a=x+(36-bullet.width)/2;
					b=y-bullet.height;
				}else if(bulletDirection.equals("down")){
					 a=x+(36-bullet.width)/2;
						b=y+40;
				}else if(bulletDirection.equals("left")){
					 a=x-bullet.width;
						b=y+(36-bullet.height)/2;
				}else if(bulletDirection.equals("right")){
					    a=x+40;
						b=y+(36-bullet.height)/2;
				}			
			 bullet=new Bullet(a,b,bulletDirection,3,false,game);
			
			game.bullets.add(bullet);
			step=0;
			}
			
		}
		
	 public void setDirection(){
		 int a=(int)(Math.random()*20);
		 int b=(int)(Math.random()*5);
		 if(distance>=a+30){
			
			 switch(b){
			 case 0:direction="up";bulletDirection="up";break;
			 case 1:direction="down";bulletDirection="down";break;
			 case 2:direction="left";bulletDirection="left";break;
			 case 3:direction="right";bulletDirection="right";break;
			 default:direction="null";
			 }
			 distance=0;
		 }
	 }
	 
	 public void collide(Tank t){
		 if(t.isAlive()){
		 if(getRect().intersects(t.getRect())){
			 x=oldX;y=oldY;
			 if(direction.equals("up")){direction="down";bulletDirection="down";distance=0;}
			    else if(direction.equals("down")){direction="up";bulletDirection="up";distance=0;}
			    else if(direction.equals("left")){direction="right";bulletDirection="right";distance=0;}
			    else if(direction.equals("right")){direction="left";bulletDirection="left";distance=0;}
		 }
	 }
	 }
	 
	 public void collide(java.util.List<Enemy> enemys){		 
		 for(int i=0;i<enemys.size();i++){
				Enemy e=enemys.get(i);
				if(this!=e){
				if(getRect().intersects(e.getRect())){
					x=oldX;y=oldY;
					int b=(int)(Math.random()*4);
					switch(b){
					 case 0:direction="up";bulletDirection="up";break;
					 case 1:direction="down";bulletDirection="down";break;
					 case 2:direction="left";bulletDirection="left";break;
					 case 3:direction="right";bulletDirection="right";break;}
					
				}}
			}
	 }
	 
	 public void collideRiver(java.util.List<River> rivers){
    	 for(int i=0;i<rivers.size();i++){
    		 River r=rivers.get(i);
   		      if(getRect().intersects(r.getRect())){  		               
   		    	x=oldX;y=oldY; 
   		    	int b=(int)(Math.random()*4);
				switch(b){
				 case 0:direction="up";bulletDirection="up";break;
				 case 1:direction="down";bulletDirection="down";break;
				 case 2:direction="left";bulletDirection="left";break;
				 case 3:direction="right";bulletDirection="right";break;}
           
   	      }
    	 }
     }
	 
	 public void collideWall(java.util.List<Wall> walls){
    	 for(int i=0;i<walls.size();i++){
    		 Wall w=walls.get(i);
   		      if(getRect().intersects(w.getRect())){  		               
   		    	x=oldX;y=oldY; 
   		    	int b=(int)(Math.random()*4);
				switch(b){
				 case 0:direction="up";bulletDirection="up";break;
				 case 1:direction="down";bulletDirection="down";break;
				 case 2:direction="left";bulletDirection="left";break;
				 case 3:direction="right";bulletDirection="right";break;}
   	      }
    	 }
     }
	 
	 public void collideSteel(java.util.List<Steel> steels){
		 for(int j=0;j<steels.size();j++){
				Steel s=steels.get(j);
				if(getRect().intersects(s.getRect())){
					x=oldX;y=oldY; 
	   		    	int b=(int)(Math.random()*4);
					switch(b){
					 case 0:direction="up";bulletDirection="up";break;
					 case 1:direction="down";bulletDirection="down";break;
					 case 2:direction="left";bulletDirection="left";break;
					 case 3:direction="right";bulletDirection="right";break;}				
				}
			}
	 }
	 
	 public void collideBase(Base base){
		 if(getRect().intersects(base.getRect())){
			 x=oldX;y=oldY; 
			 int b=(int)(Math.random()*4);
				switch(b){
				 case 0:direction="up";bulletDirection="up";break;
				 case 1:direction="down";bulletDirection="down";break;
				 case 2:direction="left";bulletDirection="left";break;
				 case 3:direction="right";bulletDirection="right";break;}	
		 }
	 }
	 
	 public void setItem(){
		 int c=(int)(Math.random()*3);
		 if(c==0){
		 int a =(int)(Math.random()*6);
		 Item i = null;
		 if(a==0){
		     i=new HpItem(x,y,game);
		     game.items.add(i);
		 }else if(a==1){
			 int b =(int)(Math.random()*3);
			 if(b==1||b==0){
				 i=new ArmorItem(x,y,1,game);
				 game.items.add(i);
			 }else if(b==2){System.out.print("2");
				 i=new ArmorItem(x,y,2,game);
				 game.items.add(i);
			 }
		 }else if(a==2){
			 i=new SbItem(x,y,game);game.items.add(i);
		 }else if(a==3){
			 i=new DItem(x,y,game);game.items.add(i);
		 }else if(a==4){
			 i=new LifeItem(x,y,game);game.items.add(i);
		 }else if(a==5){
			 i=new PauseItem(x,y,game);game.items.add(i);
		 }
		}
	 }
	 
}

class SpecialEnemy extends Enemy{
    
  
	public SpecialEnemy(int x, int y,int level,int n ,boolean b,Game g) {
		super(x, y, g);
		 this.level=level;
		 number=n;
		 isBoss=b;
	}

	 public void paint(Graphics g) {
		   if(!alive){
			   game.totalNumOfEnemy--;
			   game.enemys.remove(this);
			   setItem();			 
			   return;
		   }
          step++;
		    oldX=x;oldY=y;
		    
			if(bulletDirection.equals("up")){
				g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/specialbodyu.png"),x,y,null);
				   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/specialturretu.png"),x-14,y-14,null);
				}
				else if(bulletDirection.equals("down")){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/specialbodyd.png"),x,y,null);
					   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/specialturretd.png"),x-14,y-12,null);
				}
				else if(bulletDirection.equals("left")){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/specialbodyl.png"),x,y,null);
					   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/specialturretl.png"),x-14,y-14,null);
				}
				else if(bulletDirection.equals("right")){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/specialbodyr.png"),x,y,null);
					   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/specialturretr.png"),x-12,y-14,null);
			}
			blood=new Blood(x-7,y-14,number,24);
			 blood.paint(g);
			 if(!game.pause){
				    move();
				    fire();}	 
	 }
	 
	 public void fire(){  
			int a=0,b=0;
			if(step>=20){
				SpecialBullet bullet=new SpecialBullet(a,b,bulletDirection,4,false,game);
				if(bulletDirection.equals("up")){
					 a=x+(36-bullet.width)/2;
					b=y-bullet.height;
				}else if(bulletDirection.equals("down")){
					 a=x+(36-bullet.width)/2;
						b=y+40;
				}else if(bulletDirection.equals("left")){
					 a=x-bullet.width;
						b=y+(36-bullet.height)/2;
				}else if(bulletDirection.equals("right")){
					    a=x+40;
						b=y+(36-bullet.height)/2;
				}			
			 bullet=new SpecialBullet(a,b,bulletDirection,4,false,game);
			
			game.bullets.add(bullet);
			step=0;
			}
			
		}
	 
	 public void setItem(){
		 int a =(int)(Math.random()*6);
		 Item i = null;
		 if(a==0){
		     i=new HpItem(x,y,game); game.items.add(i);
			
		 }else if(a==1){
			 int b =(int)(Math.random()*3);
			 if(b==1||b==0){
				 i=new ArmorItem(x,y,1,game); game.items.add(i);
			 }else if(b==2){
				 i=new ArmorItem(x,y,2,game); game.items.add(i);
			 }
		 }else if(a==2){
			 i=new SbItem(x,y,game); game.items.add(i);
		 }else if(a==3){
			 int b =(int)(Math.random()*3);
			 if(b==1||b==0){
			 i=new DItem(x,y,game); game.items.add(i);
			 }else if(b==2){
				 i=new FieldItem(x,y,game);game.items.add(i);
			 }
		 }else if(a==4){
			 i=new LifeItem(x,y,game);game.items.add(i);
		 }else if(a==5){
			 i=new PauseItem(x,y,game);game.items.add(i);
		 }
		
	 }
	
}

class BossEnemy extends Enemy{
 
	private int topNumber;
	public BossEnemy(int x, int y,int level,int n, int topNumber,boolean b,Game g) {
		super(x, y, g);
		this.level=level;
		this.number=n;
		this.topNumber=topNumber;
	    isBoss=b;
	}
	
	 public void paint(Graphics g) {
			
		 if(!alive){
			  game.totalNumOfEnemy--;
			   game.numOfBoss--;
			   game.enemys.remove(this);
			   setItem();		   
			   return;
		   }
            step++;
		    oldX=x;oldY=y;
		    
			if(bulletDirection.equals("up")){
				g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bossbodyu"+(level-4)+".png"),x,y,null);
				   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bossturretu"+(level-4)+".png"),x-21,y-24,null);
				}
				else if(bulletDirection.equals("down")){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bossbodyd"+(level-4)+".png"),x,y,null);
					   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bossturretd"+(level-4)+".png"),x-21,y-17,null);
				}
				else if(bulletDirection.equals("left")){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bossbodyl"+(level-4)+".png"),x,y,null);
					   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bossturretl"+(level-4)+".png"),x-24,y-21,null);
				}
				else if(bulletDirection.equals("right")){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bossbodyr"+(level-4)+".png"),x,y,null);
					   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bossturretr"+(level-4)+".png"),x-17,y-23,null);
			}
			blood=new Blood(x-7,y-14,number,topNumber);
			 blood.paint(g);
		
		    move();
		    fire();
	 
		 
	 }
	
	 public void fire(){  
			int a=0,b=0;
			int i=(int)(Math.random()*3);		
			if(i==0&&step>=20){
				BossBulletOne bullet=new BossBulletOne(a,b,bulletDirection,6,false,game);
				if(bulletDirection.equals("up")){
					 a=x+(54-bullet.width)/2;
					b=y-bullet.height;
				}else if(bulletDirection.equals("down")){
					 a=x+(54-bullet.width)/2;
						b=y+57;
				}else if(bulletDirection.equals("left")){
					 a=x-bullet.width;
						b=y+(54-bullet.height)/2;
				}else if(bulletDirection.equals("right")){
					    a=x+57;
						b=y+(54-bullet.height)/2;
				}			
			 bullet=new BossBulletOne(a,b,bulletDirection,6,false,game);
			
			game.bullets.add(bullet);
			step=0;
			}else if(i==1&&step>=20){
				
				for(int j=0;j<4;j++){
					if(j==0){
						    BossBulletTwo bullet=new BossBulletTwo(a,b,"up",4,false,game);
						    a=x+(54-bullet.width)/2;
						    b=y-bullet.height;
						    bullet=new BossBulletTwo(a,b,"up",4,false,game);
						    game.bullets.add(bullet);
					}else if(j==1){
						    BossBulletTwo bullet=new BossBulletTwo(a,b,"down",4,false,game);
						    a=x+(54-bullet.width)/2;
							b=y+57;
							 bullet=new BossBulletTwo(a,b,"down",4,false,game);
							   game.bullets.add(bullet);
					}else if(j==2){
						    BossBulletTwo bullet=new BossBulletTwo(a,b,"left",4,false,game);
						    a=x-bullet.width;
							b=y+(54-bullet.height)/2;
							 bullet=new BossBulletTwo(a,b,"left",4,false,game);
							   game.bullets.add(bullet);
					}else if(j==3){
						    BossBulletTwo bullet=new BossBulletTwo(a,b,"right",4,false,game);    
						    a=x+57;
							b=y+(54-bullet.height)/2;
							bullet=new BossBulletTwo(a,b,"right",4,false,game);
							 game.bullets.add(bullet);
					}	
					step=0;
				}
			}else if(i==2&&step>=20){
				if(bulletDirection.equals("up")){
					 a=x+(54-64)/2;
					b=y-64;
				}else if(bulletDirection.equals("down")){
					 a=x+(54-64)/2;
						b=y+57;
				}else if(bulletDirection.equals("left")){
					 a=x-64;
						b=y+(54-64)/2;
				}else if(bulletDirection.equals("right")){
					    a=x+57;
						b=y+(54-64)/2;
				}
				FireExplode fe=new FireExplode(a,b,bulletDirection,2,game);
				game.explodes.add(fe);
				step=0;
			}
			
		}
	 
	 public Rectangle getRect(){
		 if(bulletDirection.equals("up")||bulletDirection.equals("down")){return new Rectangle(x,y,54,57);}
		 else{return new Rectangle(x,y,57,54);}
	 }
	 
	 public void move(){
			
			if(direction.equals("up")){
				
				y-=YSPEED;
				}
			else if(direction.equals("down")){
				y+=YSPEED;
			}
			else if(direction.equals("left")){
				x-=XSPEED;
			}
			else if(direction.equals("right")){
				x+=XSPEED;
			}
			else if(direction.equals("null")){
			
			}
			setDirection();
			if(x<0){x=0;direction="right";bulletDirection="right";distance=0;}
			if(y<45){y=45;direction="down";bulletDirection="down";distance=0;}
			if(x>800-57){x=800-57;direction="left";bulletDirection="left";distance=0;}
			if(y>600-57){y=600-57;direction="up";bulletDirection="up";distance=0;}
		    distance++;
	 }
	 
	 public void setItem(){
		 for(int j=0;j<4;j++){
			 int a =(int)(Math.random()*6);
			 Item i = null;
			 if(a==0){
			     i=new HpItem(100+j*200,50,game); game.items.add(i);
				
			 }else if(a==1){
				 int b =(int)(Math.random()*3);
				 if(b==1||b==0){
					 i=new ArmorItem(100+j*200,50,1,game); game.items.add(i);
				 }else if(b==2){
					 i=new ArmorItem(100+j*200,50,2,game); game.items.add(i);
				 }
			 }else if(a==2){
				 i=new SbItem(100+j*200,50,game); game.items.add(i);
			 }else if(a==3){
				 int b =(int)(Math.random()*3);
				 if(b==1||b==0){
				 i=new DItem(100+j*200,50,game); game.items.add(i);
				 }else if(b==2){
					 i=new FieldItem(100+j*200,50,game);game.items.add(i);
				 }
			 }else if(a==4){
				 i=new LifeItem(100+j*200,50,game);game.items.add(i);
			 }else if(a==5){
				 i=new PauseItem(100+j*200,50,game);game.items.add(i);
			 }
			
		 }
		
		 }
	 }
	 
