package team.lj.Tanke;

import java.awt.*;






public class Item {
	   int x,y,width=32,height=32;
	   Game game;
	   boolean alive=true;
	   public Item(int x, int y, Game g){
		   this.x=x;
		   this.y=y;
		   game=g;
	   }
	   
	   Image image;
	   
	   public void paint(Graphics g){
		  
	   }
	  
	   public Rectangle getRect(){
		   return new Rectangle(x,y,width,height);
	   }
	   
	   public void eatItem(Tank t){
		   
	   }
	     
	}

	class HpItem extends Item{

		public HpItem(int x, int y, Game g) {
			super(x, y, g);		
		}
		
	  Image	image=Toolkit.getDefaultToolkit().getImage("src/Image/map/hp.png");
	  public void paint(Graphics g){
		   
		  if(!alive){
			   game.items.remove(this);
		   }
		   g.drawImage(image,x,y, null);
	  }
	  public void eatItem(Tank t){
		   if(getRect().intersects(t.getRect())){
			   alive=false;
			   game.base.number+=12;
			   t.number=12;
			  
		   }
	  }
		
	}
	  
	class ArmorItem extends Item{
	    int level;
		public ArmorItem(int x, int y,int level, Game g) {
			super(x, y, g);
			this.level=level;
		}
		
		Image	image[]={Toolkit.getDefaultToolkit().getImage("src/Image/map/armor1.png"),
		                Toolkit.getDefaultToolkit().getImage("src/Image/map/armor2.png")};
		
		 public void paint(Graphics g){
			   
			  if(!alive){
				   game.items.remove(this);
			   }
		
			  g.drawImage(image[level-1],x,y, null);
			  
		  }
		 
		 public void eatItem(Tank t){
			 if(getRect().intersects(t.getRect())){
				   alive=false;
		           if(level==1&&t.level!=3){
		        	  t.level=2;t.levelblood=6;
		           }else if(level==2){
		        	   t.level=3;t.levelblood=12;
		           }
			 
			 }}
		
	}

	class SbItem extends Item{

		public SbItem(int x, int y, Game g) {
			super(x, y, g);
		}
		
		 Image	image=Toolkit.getDefaultToolkit().getImage("src/Image/map/SPE.png");
		 
		 public void paint(Graphics g){
			   
			  if(!alive){
				   game.items.remove(this);
			   }
			   g.drawImage(image,x,y, null);
		  }
		 
		 public void eatItem(Tank t){
			 if(getRect().intersects(t.getRect())){
				   alive=false;
				   t.numOfSpecialBullet=10;
				   t.numOfFire=10;
		 }
		 }		 
		
	}

	class DItem extends Item{

		public DItem(int x, int y, Game g) {
			super(x, y, g);
		}
		
		Image	image=Toolkit.getDefaultToolkit().getImage("src/Image/map/dead.jpg");
		 
		 public void paint(Graphics g){
			   
			  if(!alive){
				   game.items.remove(this);
			   }
			   g.drawImage(image,x,y, null);
		  }
		
		 public void eatItem(Tank t){
			 if(getRect().intersects(t.getRect())){
				   alive=false;
			       for(int i=0;i<game.enemys.size();i++){
			    	   Enemy e=game.enemys.get(i);
			    	   if(!e.isBoss){
			    	   e.number=0;
			    	   e.setAlive();
			    	   Explode explode=new Explode(e.x-45,e.y-45,game);
			           game.explodes.add(explode);}
			       }
			 }
		 }
	}


	class LifeItem extends Item{

		public LifeItem(int x, int y, Game g) {
			super(x, y, g);
		}

		Image	image=Toolkit.getDefaultToolkit().getImage("src/Image/map/life.png");
		
		 public void paint(Graphics g){
			   
			  if(!alive){
				   game.items.remove(this);
			   }
			   g.drawImage(image,x,y, null);
		  }
		
		 public void eatItem(Tank t){
			 if(getRect().intersects(t.getRect())){
				   alive=false;
				   t.life++;		   
			 }}
		 
	}

	class PauseItem extends Item{

		public PauseItem(int x, int y, Game g) {
			super(x, y, g);
		}
		
		Image	image=Toolkit.getDefaultToolkit().getImage("src/Image/map/clock.jpg");
		
		public void paint(Graphics g){
			   
			  if(!alive){
				   game.items.remove(this);
			   }
			   g.drawImage(image,x,y, null);
		  }
		
		public void eatItem(Tank t){
			 if(getRect().intersects(t.getRect())){
				   alive=false;
				  game.pause=true;	
				  new Thread(new Pause(game)).start();
			 }}	
		
		private class Pause implements Runnable{
		       Game g;
	 	   public Pause(Game g){
	 		   this.g=g;
	 	   }
	 	   public void run(){
			      try{
		    		 Thread.sleep(5000);
		    	 }catch(Exception ex){}
		        g.pause=false;
		        
		      }
	     }
		
	}

	class FieldItem extends Item{

		public FieldItem(int x, int y, Game g) {
			super(x, y, g);	
		}
		
		Image	image=Toolkit.getDefaultToolkit().getImage("src/Image/map/field.jpg");
		
		public void paint(Graphics g){
			   
			  if(!alive){
				   game.items.remove(this);
			   }
			   g.drawImage(image,x,y, null);
		  }
		
		public void eatItem(Tank t){
			 if(getRect().intersects(t.getRect())){
				   alive=false;
				   t.field=true;   
				   t.topNumber=36;
				   t.number=t.topNumber;
				   TField tField=new TField(t,game);
				   game.fields.add(tField);
				 
			 }}	
		
		
		
	}
