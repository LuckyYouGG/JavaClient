package team.lj.Tanke;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import Image.map.Blood;

public class Tank  {
	 int x,y,oldX,oldY,player;
	 public final static int XSPEED=5,YSPEED=5,WIDTH=40,HEIGHT=40;
	 boolean u=false,d=false,l=false,r=false;
	 private String direction="null",bulletDirection="right";
    int numOfSpecialBullet=10,numOfFire=10;
	 int life=4;
	 public int getLife() {
			return life;
		}
    private boolean a=false,s=false,f=false;
	 Game game;
	 int level=1,levelblood=0;
	  int step1=15,step2=80,step3=28;
	 public boolean alive=true;
	 boolean field=false;
	 private Music fireM= new Music();
	 
	 Blood blood;
	 int number=24;
	 int topNumber=24;
	 public boolean isAlive() {
		return alive;
	}


	public void setAlive() {
		
		if(number<=0){
			Explode explode=new Explode(this.x-45,this.y-45,game);
		     game.explodes.add(explode);
		     
			life--;
			field=false;
			if(life>0&&player==1){
				direction="null";
				bulletDirection="right";
				numOfSpecialBullet=10;
				numOfFire=10;
				number=12;topNumber=12;
				x=50;
				y=550;
			}else if(life>0&&player==2){
				direction="null";
				bulletDirection="right";
				numOfSpecialBullet=10;
				numOfFire=10;
				number=12;topNumber=12;
				x=600;
				y=550;

			}else if(life<=0){
				alive=false;
				
			}
		}
	}


	public Tank(int x,int y,Game g,int p){
		this.x=x;
		this.y=y;
	    this.game=g;
	    this.player=p;
	 }
	
	 
	public void paint(Graphics g) {
    if(alive){
		
   	 if(bulletDirection.equals("up")){
				g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/mybodyu"+level+".png"),x,y,null);
				   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/myturretu"+level+".png"),x-14,y-14,null);
				}
				else if(bulletDirection.equals("down")){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/mybodyd"+level+".png"),x,y,null);
					   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/myturretd"+level+".png"),x-14,y-12,null);
				}
				else if(bulletDirection.equals("left")){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/mybodyl"+level+".png"),x,y,null);
					   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/myturretl"+level+".png"),x-14,y-14,null);
				}
				else if(bulletDirection.equals("right")){
					g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/mybodyr"+level+".png"),x,y,null);
					   g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Tank/myturretr"+level+".png"),x-12,y-14,null);
				}
		oldX=x;oldY=y;
		blood=new Blood(x-7,y-14,number,topNumber);
		    blood.paint(g);
		
		move();
		step1++; step2++; step3++; 
	    fire();	
	    collide(game.enemys);
		collideRiver(game.rivers);
		collideWall(game.walls);
		collideSteel(game.steels);
		collideBase(game.base);
    }
	}
	
	    public void keyReleased(KeyEvent e){
	    	int code=e.getKeyCode();
			switch(code){
			case KeyEvent.VK_I:
					if(player==2)
					a=false;
					break;
			case KeyEvent.VK_O:	
				if(player==2)
				s=false;
				break;
			case KeyEvent.VK_P:
				if(player==2)
				f=false;
				break;
			case KeyEvent.VK_RIGHT:
					if(player==2)
					r=false;
					break;
			case KeyEvent.VK_LEFT:
					if(player==2)
					l=false;
					break;
			case KeyEvent.VK_UP:
					if(player==2)
					u=false;
					break;
			case KeyEvent.VK_DOWN:
					if(player==2)
					d=false;
					break;
			case KeyEvent.VK_F:
					if(player==1)
					a=false;
					break;
			case KeyEvent.VK_G:
				if(player==1)
				s=false;
				break;
			case KeyEvent.VK_H:
				if(player==1)
				f=false;
				break;
			case KeyEvent.VK_D:
					if(player==1)
					r=false;
					break;
			case KeyEvent.VK_A:
					if(player==1)
					l=false;
					break;
			case KeyEvent.VK_W:
					if(player==1)
					u=false;
					break;
			case KeyEvent.VK_S:
					if(player==1)
					d=false;
					break;
				}
				setDirection();
	    }
	
		public void keyPressed(KeyEvent e){
			int code=e.getKeyCode();
			switch(code){ 
			
			case KeyEvent.VK_I:
					if(player==2)
					a=true;
					break;
			case KeyEvent.VK_O:	
				if(player==2)
				s=true;
				break;
			case KeyEvent.VK_P:
				if(player==2)
				f=true;
				break;
			case KeyEvent.VK_RIGHT:
					if(player==2)
					r=true;
					break;
			case KeyEvent.VK_LEFT:
					if(player==2)
					l=true;
					break;
			case KeyEvent.VK_UP:
					if(player==2)
					u=true;
					break;
			case KeyEvent.VK_DOWN:
					if(player==2)
					d=true;
					break;
			case KeyEvent.VK_F:
					if(player==1)
					a=true;
					break;
			case KeyEvent.VK_G:
				if(player==1)
				s=true;
				break;
			case KeyEvent.VK_H:
				if(player==1)
				f=true;
				break;
			case KeyEvent.VK_D:
					if(player==1)
					r=true;
					break;
			case KeyEvent.VK_A:
					if(player==1)
					l=true;
					break;
			case KeyEvent.VK_W:
					if(player==1)
					u=true;
					break;
			case KeyEvent.VK_S:
					if(player==1)
					d=true;
					break;	
			}
			setDirection();
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
			if(x<0){x=0;}
			if(y<45){y=45;}
			if(x>800-WIDTH){x=800-WIDTH;}
			if(y>600-HEIGHT){y=600-HEIGHT;}
			
		}
		
		public void setDirection(){
			if(u){
				direction="up";
				bulletDirection="up";
			}
			else if(d){
				direction="down";
				bulletDirection="down";
			}
			else if(l){
				direction="left";
				bulletDirection="left";
			}
			else if(r){
				direction="right";
				bulletDirection="right";
			}
			else if(!u&&!d&&!r&&!r){
				direction="null";
			}
		}
		 
		public void fire(){  
		    int c=0,b=0;		
		    if(a&&step1>=15){
		    	fireM.musicRun("aFire");
		    	Bullet bullet=new Bullet(c,b,bulletDirection,3,true,game);
		    	if(bulletDirection.equals("up")){
				 c=x+(36-bullet.width)/2;
				b=y-bullet.height;
			}else if(bulletDirection.equals("down")){
				 c=x+(36-bullet.width)/2;
					b=y+40;
			}else if(bulletDirection.equals("left")){
				 c=x-bullet.width;
					b=y+(36-bullet.height)/2;
			}else if(bulletDirection.equals("right")){
				    c=x+40;
					b=y+(36-bullet.height)/2;
			}			
		    	bullet=new Bullet(c,b,bulletDirection,3,true,game);
			game.bullets.add(bullet);
			step1=0;
			}
			else if(s&&step2>=80&&numOfSpecialBullet>0){
				fireM.musicRun("bFire");
				SuperBullet bullet=new SuperBullet(c,b,bulletDirection,6,true,game);
				if(bulletDirection.equals("up")){
					 c=x+(36-bullet.width)/2;
					b=y-bullet.height;
				}else if(bulletDirection.equals("down")){
					 c=x+(36-bullet.width)/2;
						b=y+40;
				}else if(bulletDirection.equals("left")){
					 c=x-bullet.width;
						b=y+(36-bullet.width)/2;
				}else if(bulletDirection.equals("right")){
					    c=x+40;
						b=y+(36-bullet.width)/2;
				}	
				bullet=new SuperBullet(c,b,bulletDirection,6,true,game);
				game.bullets.add(bullet);
				numOfSpecialBullet--;
				step2=0;
			}else if(f&&step3>=28&&numOfFire>0){   
				fireM.musicRun("cFire");
				if(bulletDirection.equals("up")){
					 c=x+(36-64)/2;
					b=y-64;
				}else if(bulletDirection.equals("down")){
					 c=x+(36-64)/2;
						b=y+40;
				}else if(bulletDirection.equals("left")){
					 c=x-64;
						b=y+(36-64)/2;
				}else if(bulletDirection.equals("right")){
					    c=x+40;
						b=y+(36-64)/2;
				}
				FireExplode fe=new FireExplode(c,b,bulletDirection,1,game);
				game.explodes.add(fe);
				numOfFire--;
				step3=0;
				}
		}
		
		public Rectangle getRect(){
				
			 if(bulletDirection.equals("up")||bulletDirection.equals("down")){return new Rectangle(x,y,36,38);}
			 else{return new Rectangle(x,y,38,36);}			
					
		 }
     
		public void collide(java.util.List<Enemy> enemys){
			
			for(int i=0;i<enemys.size();i++){
				Enemy e=enemys.get(i);
				if(getRect().intersects(e.getRect())){
					x=oldX;y=oldY;
					
				}
			}
			
		}

		public void collideRiver(java.util.List<River> rivers){
	    	 for(int i=0;i<rivers.size();i++){
	    		 River r=rivers.get(i);
	   		      if(getRect().intersects(r.getRect())){  		               
	   		    	x=oldX;y=oldY; 
	   	      }
	    	 }
	     }
		
		public void collideWall(java.util.List<Wall> walls){
	    	 for(int i=0;i<walls.size();i++){
	    		 Wall w=walls.get(i);
	   		      if(getRect().intersects(w.getRect())){  		               
	   		    	x=oldX;y=oldY; 
	   	      }
	    	 }
	     }
		
		public void collideSteel(java.util.List<Steel> steels){
			for(int j=0;j<steels.size();j++){
				Steel s=steels.get(j);
				if(getRect().intersects(s.getRect())){
					x=oldX;y=oldY; 
				}
			}
		}
		
		 public void collide(Tank t){
			 if(t.isAlive()){
			 if(getRect().intersects(t.getRect())){
				 x=oldX;y=oldY;
				 if(getRect().intersects(t.getRect())){
					 move();
				 }
			 }
			 }
			 }
		 

		 public void collideBase(Base base){
			 if(getRect().intersects(base.getRect())){
				 x=oldX;y=oldY;
			 }
			 }
		
}
