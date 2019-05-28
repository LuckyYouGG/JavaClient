package team.lj.Tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
public class Bullet {
	private Image bullet =Toolkit.getDefaultToolkit().getImage("src/Image/Tank/ammo2.png");
	public final static int XSPEED=10,YSPEED=10;
	int width=12,height=12;
	protected int damage;
	int x,y;
    String bulletDirection;
    protected boolean alive=true;

    
    boolean good=true;
  
    Game game;
    
    public boolean isAlive() {
		return alive;
		
    }
	
    public Bullet(int x,int y,String bulletDirection,int damage,boolean good,Game game){
    	this.x=x;
    	this.y=y;
    	this.bulletDirection=bulletDirection;
    	this.good=good;
    	this.game=game;
    	this.damage=damage;
    }
public void paint(Graphics g) {
		if(!alive){
			game.bullets.remove(this);
			
			return;
		}
	    g.drawImage(bullet,x,y,null);
		move();
    
   }
protected void move() {
	if(bulletDirection.equals("up")){
		y-=YSPEED;
	}
	else if(bulletDirection.equals("down")){
		y+=YSPEED;
	}
	else if(bulletDirection.equals("left")){
		x-=XSPEED;
	}
	else if(bulletDirection.equals("right")){
		x+=XSPEED;
	}
    if(x>800||x<0||y>600||y<0){
    	alive=false;
    }
}

 public Rectangle getRect(){
	 return new Rectangle(x,y,width,width);
 }

  public void hitBase(Base base){
	  if(getRect().intersects(base.getRect())){
		  alive=false;
		  
		  BulletExplode b=new BulletExplode(x-26,y-26,game);
		   game.explodes.add(b);
  		   base.number-=damage;
  	}
	  }		  
  
 
  public void hitEnemy(java.util.List<Enemy> enemys){
	  for(int j=0;j<enemys.size();j++){
	    Enemy e=enemys.get(j);
		  if(getRect().intersects(e.getRect())){
		  
			  alive=false;
          if(good){
        	 
        	BulletExplode b=new BulletExplode(x-26,y-26,game);
   		    game.explodes.add(b);
   		    if(e.level==1||e.level==4||e.level==5||e.level==6||e.level==7){e.number-=damage ;}
   		    if(e.level==2||e.level==3){
        		e.levelblood=e.levelblood-damage;
        		if(e.levelblood<=6){
        		     e.level=2;	
        	       }if(e.levelblood<=0){
        			e.level=1;
        		}
        	}  
   		    
        	e.setAlive();
        	if(!e.isAlive()){
            Explode explode=new Explode(e.x-45,e.y-45,game);
            game.explodes.add(explode);}
        }
	 }}}
	 	
	 public void hitTank(Tank t){   
		if(t.isAlive()){
		 if(getRect().intersects(t.getRect())){
			 
			 alive=false;
			 if(!good){
				 BulletExplode b=new BulletExplode(x-26,y-26,game);
				   game.explodes.add(b);
				 if(t.level==1){t.number-=damage ;}
				 else if(t.level>=1){
		        		t.levelblood=t.levelblood-damage;
		        		if(t.levelblood<=6){
		        		     t.level=2;	
		        	       }if(t.levelblood<=0){
		        			t.level=1;
		        		}
		        	}       		
			 t.setAlive();
			 if(!t.isAlive()){
				 Explode explode=new Explode(t.x-45,t.y-45,game);
			     game.explodes.add(explode);}
		 }}}
	 }
	 
	 public void collide(java.util.List<Bullet> bullets){
		 for(int i=0;i<bullets.size();i++){
			 Bullet b=bullets.get(i);
			 if(this!=b){
				 if(getRect().intersects(b.getRect())){
					 alive=false;
				 }
			 }
		 }
	 }
	public void hitWall(java.util.List<Wall> walls){
		for(int j=0;j<walls.size();j++){
			Wall w=walls.get(j);
			if(getRect().intersects(w.getRect())){
				BulletExplode b=new BulletExplode(x-26,y-26,game);
				   game.explodes.add(b);
				  alive=false;
			w.setAlive(false);}
		}
	}
	 
	public void hitSteel(java.util.List<Steel> steels){
		for(int j=0;j<steels.size();j++){
			Steel s=steels.get(j);
			if(getRect().intersects(s.getRect())){
				BulletExplode b=new BulletExplode(x-26,y-26,game);
				   game.explodes.add(b);
				alive=false;
			}
		}
	}
	 
}


class SuperBullet extends Bullet{
	public SuperBullet(int x, int y, String bulletDirection, int damage,
			boolean good, Game game) {
		super(x, y, bulletDirection, damage, good, game);
		
	}

	private Image bullet[] ={Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bFireU.png"),
			Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bFireD.png"),
			Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bFireL.png"),
			Toolkit.getDefaultToolkit().getImage("src/Image/Tank/bFireR.png")};
                                                      
	private int distance=0;
	private boolean alive=true;
	
    int width=10,height=33;
	


	
	
	public void paint(Graphics g) {
		if(!alive){
			game.bullets.remove(this);
			explode();
			return;
		}
		if(bulletDirection.equals("up")){
	    g.drawImage(bullet[0],x,y,null);
	    }else if(bulletDirection.equals("down")){
	    	g.drawImage(bullet[1],x,y,null);
	    }else if(bulletDirection.equals("left")){
	    	g.drawImage(bullet[2],x,y,null);
	    }else if(bulletDirection.equals("right")){
	    	g.drawImage(bullet[3],x,y,null);
	    }
		move();
    
   }
	
 protected void move(){
		if(bulletDirection.equals("up")){
			y-=YSPEED;
		}
		else if(bulletDirection.equals("down")){
			y+=YSPEED;
		}
		else if(bulletDirection.equals("left")){
			x-=XSPEED;
		}
		else if(bulletDirection.equals("right")){
			x+=XSPEED;
		}
		distance+=10;
		if(x>800||x<0||y>600||y<45){
	    	alive=false;
	    }else{
	    	if(distance>=300){
	    		alive=false;
	    	}
	    }
		
	}
 public void hitBase(Base base){
	 
 }
	public void hitTank(Tank t){   		
	 }
	public void hitEnemy(java.util.List<Enemy> enemys){
		
	 }
	 public void collide(java.util.List<Bullet> bullets){		
	 }
	public void hitWall(java.util.List<Wall> walls){		
	}	 
	public void hitSteel(java.util.List<Steel> steels){		

		
		
	}

    public void explode(){
    	SpecialExplode e=new SpecialExplode(x-120,y-120,game);
    	game.explodes.add(e);
    }


	
}

class SpecialBullet extends Bullet{
	public SpecialBullet(int x, int y, String bulletDirection, int damage,
			boolean good, Game game) {
		super(x, y, bulletDirection, damage, good, game);
		
	}

	private Image bullet =Toolkit.getDefaultToolkit().getImage("src/Image/Tank/SPE2.png");
	
	int width=10,height=10;
	
	
	public void paint(Graphics g) {
		
		if(!alive){
			game.bullets.remove(this);
			
			return;
		}
	    g.drawImage(bullet,x,y,null);
		move();
}
	
	public Rectangle getRect(){
		 return new Rectangle(x,y,width,width);
	 }
}

class BossBulletOne extends Bullet{
	public BossBulletOne(int x, int y, String bulletDirection, int damage,
			boolean good, Game game) {
		super(x, y, bulletDirection, damage, good, game);
	
	}
	
	private Image bullet =Toolkit.getDefaultToolkit().getImage("src/Image/Tank/boss1.png");

	
	public void paint(Graphics g) {
		
		if(!alive){
			game.bullets.remove(this);
			
			return;
		}
	    g.drawImage(bullet,x,y,null);
		move();
}
	
	
	
}

class BossBulletTwo extends Bullet{

	public BossBulletTwo(int x, int y, String bulletDirection, int damage,boolean good, Game game) {
		super(x, y, bulletDirection, damage, good, game);

	}
	
	private Image bullet =Toolkit.getDefaultToolkit().getImage("src/Image/Tank/boss2.png");
	
	int width=8,height=8;
	public Rectangle getRect(){
		 return new Rectangle(x,y,width,width);
	 }
	
      public void paint(Graphics g) {		
		if(!alive){
			game.bullets.remove(this);			
			return;
		}
	    g.drawImage(bullet,x,y,null);
		move();
      }
     
     
}
