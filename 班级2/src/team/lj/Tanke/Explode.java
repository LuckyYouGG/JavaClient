package team.lj.Tanke;

import java.awt.*;



public class Explode {
  int x,y;
  boolean alive=true;
  int step=0;
  Game game;
   
  
  public Explode(int x,int y,Game g){
	  this.x=x;
	  this.y=y;
	  this.game=g;
  }
  
  private Image IMGS[]=
	{
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/1.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/2.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/3.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/4.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/5.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/6.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/7.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/8.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/9.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/10.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/11.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/12.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/13.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/14.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/15.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/16.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/17.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/18.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/19.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/20.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/21.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/22.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/23.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/24.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/25.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/26.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/27.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/28.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/29.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/30.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/31.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/32.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/33.png"),
	  Toolkit.getDefaultToolkit().getImage("src/Image/TankBomb/34.png"),

	};
  
  static boolean init=false;
  
  public void draw(Graphics g){
		
	  if(!init)
		{
			for (int i=0;i<IMGS.length;i++)
			{
				g.drawImage(IMGS[i],x , y ,null);
				init=true;
			}
		}

	  if(step==0){
    	  game.gameM.musicRun("explode");
     } 
	 if(step==IMGS.length){
		 
		 game.explodes.remove(this);
		 alive=false;
		 return;
	 }
	 g.drawImage(IMGS[step],x,y,null);
		step++;
		
  }
  

}
  
   class BulletExplode extends Explode{
		
	 public BulletExplode(int x, int y, Game g) {
		super(x, y, g);}
	


	private Image IMGS[]=
	{
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/11.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/12.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/13.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/14.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/15.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/16.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/17.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/18.png"),
	
	};
	static boolean init=false;


	public void draw(Graphics g) {
		if(!init)
		{
			for (int i=0;i<IMGS.length;i++)
			{
				g.drawImage(IMGS[i],x , y ,null);
				init=true;
			}
		}

	  
	 if(step==IMGS.length){
		 game.explodes.remove(this);
		 step=0;
		 alive=false;
		 return;
	 }
	 g.drawImage(IMGS[step],x,y,null);
		step++;
		
	}
	
}

   class Fire extends Explode{
		int kind;
	 public Fire(int x, int y,int kind, Game g) {
		super(x, y, g);
		this.kind=kind;
	 }
	


	private Image IMGS1[]=
	{
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/18.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/17.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/16.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/15.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/14.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/13.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/12.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/11.png"),
	
	};
	private Image IMGS2[]=
	{
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/28.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/27.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/26.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/25.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/24.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/23.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/22.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/21.png"),

	};
	static boolean init=false;


	public void draw(Graphics g) {
		
	if(kind==1){	
		if(!init)
		{
			for (int i=0;i<IMGS1.length;i++)
			{
				g.drawImage(IMGS1[i],x , y ,null);
				init=true;
			}
		}
      
	  
	   if(step==IMGS1.length){
		 game.explodes.remove(this);
		 step=0;
		 alive=false;
		 return;
	 }
	 g.drawImage(IMGS1[step],x,y,null);
		step++;}
	else if(kind==2){
		if(!init)
		{
			for (int i=0;i<IMGS2.length;i++)
			{
				g.drawImage(IMGS2[i],x , y ,null);
				init=true;
			}
		}
    
	  
	   if(step==IMGS2.length){
		 game.explodes.remove(this);
		 step=0;
		 alive=false;
		 return;
	 }
	 g.drawImage(IMGS2[step],x,y,null);
		step++;
	}
	
	}
	
}
   class SpecialExplode extends Explode{
    
    int damage=6;

	 public SpecialExplode(int x, int y, Game g) {
		super(x, y, g);}
	
	   
	private Image IMGS[]=
	{
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/1.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/2.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/3.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/4.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/5.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/6.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/7.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/8.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/9.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/10.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/11.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/12.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/13.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/14.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/15.png"),
		Toolkit.getDefaultToolkit().getImage("src/Image/ShotBomb/SUPER/16.png"),
	};   
	
	static boolean init=false;


	public void draw(Graphics g) {
		if(!init)
		{
			for (int i=0;i<IMGS.length;i++)
			{
				g.drawImage(IMGS[i],x , y ,null);
				init=true;
			}
		}

	
	 if(step==IMGS.length){
		 game.explodes.remove(this);
		 step=0;
		 alive=false;
		 
		 return;
	 }
	 if(step==IMGS.length-9){
		 hitEnemy(game.enemys);
		 hitWall(game.walls);
		 hitBase(game.base);
	 }
	 g.drawImage(IMGS[step],x,y,null);
		step++;
		
	}
	
	public Rectangle getRect(){
		return new Rectangle(x+65,y+65,126,126);
	}
	
	public void hitEnemy(java.util.List<Enemy> enemys){
		for(int j=0;j<enemys.size();j++){
		    Enemy e=enemys.get(j);
			  if(getRect().intersects(e.getRect())){
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
			  }
	}
	
	public void hitWall(java.util.List<Wall> walls){
		for(int j=0;j<walls.size();j++){
			Wall w=walls.get(j);
			if(getRect().intersects(w.getRect())){
				w.setAlive(false);
			}
	}}
	public void hitBase(Base base){
		if(getRect().intersects(base.getRect())){
			base.number-=damage;
		}
	}
	
   }
   
   
   
   class FireExplode extends Explode{                //
       int n=0;
	   int damage=1;
	   int kind;
	
	   String bulletDirection;
		public FireExplode(int x, int y,String bulletDirection,int kind,Game g) {
			super(x, y, g);
			this.bulletDirection=bulletDirection;
			this.kind=kind;
		}
		
		public void draw(Graphics g) {
		
         if(n==10){
        	 game.explodes.remove(this);
         }
		  
	
			 n++;
			 
			 Fire f=new Fire(x,y,kind,game); game.explodes.add(f);
			 if(kind==1){
				
			 hitEnemy(game.enemys);
			 }else if(kind==2){
		     
			 hitTank(game.mytank1);
			   if(game.numOfPlayer==2){
				   hitTank(game.mytank2);
			   }
			 }
			 hitWall(game.walls);
			 hitBase(game.base);
			 move();
			
			 alive=false;
			 return;
		
			
		}
		
        private void move(){
        	if(bulletDirection.equals("up")){
        		y-=15;
        	}else if(bulletDirection.equals("down")){
        		y+=15;
        	}else if(bulletDirection.equals("left")){
        		x-=15;
        	}else if(bulletDirection.equals("right")){
        		x+=15;
        	}
        }
		
        private Rectangle getRect(){
        	return new Rectangle(x+16,y+16,32,32);
        }
        
        private void hitEnemy(java.util.List<Enemy> enemys){
        	for(int j=0;j<enemys.size();j++){
    		    Enemy e=enemys.get(j);
    			  if(getRect().intersects(e.getRect())){
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
    			  }
        }
        
        private void hitWall(java.util.List<Wall> walls){
        	for(int j=0;j<walls.size();j++){
    			Wall w=walls.get(j);
    			if(getRect().intersects(w.getRect())){
    				w.setAlive(false);
    			}
        	}
        }
        
        private void hitTank(Tank t){
        	if(t.isAlive()){	
        	if(getRect().intersects(t.getRect())){
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
        	}
        }
        } 	
        
        private void hitBase(Base base){
        	if(getRect().intersects(base.getRect())){
        		base.number-=damage;
        	}
        	
        }
        
        
	   }                                                       //