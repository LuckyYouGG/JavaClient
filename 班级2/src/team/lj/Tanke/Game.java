package team.lj.Tanke;

import team.lj.Tetris.J;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;




public class Game extends Frame implements ActionListener{
	private Container con;
	int t=1;
	int h=0;
	 boolean paint =true;
	 boolean pause=false;
	    MenuBar mb=new MenuBar();
	    Menu me1 =new Menu("menu");
	    Menu me2 =new Menu("help");
	    MenuItem mi1 = new MenuItem ("restar");
	    MenuItem mi2 = new MenuItem ("paused");
	    MenuItem mi3 = new MenuItem ("showHelp");
	    
	    Image imageLose=new ImageIcon("lose.jpg").getImage();
	    Image imageWin=new ImageIcon("win.PNG").getImage();
	    Image imageHelp = new ImageIcon("helpI.jpg").getImage();
	    
	    public Music gameM=new Music();
	    
	    private boolean win =false;
	    private boolean lose =false;
	    private boolean judge=true;
	    
	    static  int numOfPlayer,level;
	Tank mytank1=new Tank(50,550,this,1);
	Tank mytank2=new Tank(600,550,this,2);
	public final static int XSPEED=5,YSPEED=5;
	public final static int WIDTH=800,HEIGHT=603;
	Image offscreenImage=null;
	 private int numOfEnemy,n=0;
	 Base base=new Base(352,536,this);
	public int  totalNumOfEnemy;
	public int  numOfBoss;
	
	 List<Explode> explodes = new ArrayList<Explode>();
	 List<Bullet> bullets=new ArrayList<Bullet>();
	 List<River> rivers=new ArrayList<River>();
	 List<Enemy> enemys = new ArrayList<Enemy>();	
	 List<Wall> walls=new ArrayList<Wall>();
	 List<Item>items=new ArrayList<Item>();
	 List<Grass> grasses=new ArrayList<Grass>();
	 List<Steel> steels=new ArrayList<Steel>();
	 List<Field> fields=new ArrayList<Field>();
	 
	 int step1=100;
	 int step2=100;
	 
	 public void paint(Graphics g) {				
	     
	    	  

			Image image=new ImageIcon("src/Image/map/map.jpg").getImage();
			 
		   g.drawImage(image,0,0,this);

		   if(enemys.size()<3+level&&numOfEnemy<(20*level)){
				
				boolean collide=false;
			    if(n==7){n=0;}
				int a=(int)(Math.random()*5)+1;
				 Enemy e; 
				if(numOfEnemy==19||numOfEnemy==39||numOfEnemy==59){								 
				     e=new BossEnemy(50+100*n,50,4+(numOfEnemy+1)/20,30*(numOfEnemy+1)/20,30*(numOfEnemy+1)/20,true,this);
			         fields.add(new EField(e,this));
				 }else{
				    if(a<=3){
				     e =new Enemy(50+100*n,50,a,a*6-6,12,false,this);
				    }else {
				     e=new SpecialEnemy(50+100*n,50,4,30,false,this);
				    }
				   }
				for(int i=0;i<enemys.size();i++){
					Enemy enemy=enemys.get(i);
					if(e.getRect().intersects(enemy.getRect())){
						collide=true;
					}
				}
				if(e.getRect().intersects(mytank1.getRect())){
					collide=true;
				}
				if(numOfPlayer==2){
				    if(e.getRect().intersects(mytank2.getRect())){
					   collide=true;
				     }}
			    if(!collide){
				enemys.add(e);
			    numOfEnemy++;
			
			    n++;}
			};
			for(int i=0;i<rivers.size();i++){
				River r=rivers.get(i);
				r.paint(g);
			}
			
			for(int i=0;i<fields.size();i++){
				Field f=fields.get(i);
                f.paint(g); 
			}
			
			for(int i=0;i<items.size();i++){
				Item item=items.get(i);
				item.eatItem(mytank1);
				if(numOfPlayer==2){
				item.eatItem(mytank2);}
				item.paint(g);
				
			}
		   
			for(int i=0;i<walls.size();i++){
				Wall w=walls.get(i);
				w.paint(g);
			}
			
			for(int i=0;i<steels.size();i++){
				Steel s=steels.get(i);
				s.paint(g);
			}
			  base.paint(g);
			for(int i=0;i<explodes.size();i++){
				Explode explode=explodes.get(i);
				explode.draw(g);
				}
			
			
			for(int i=0;i<bullets.size();i++){
			    Bullet bullet=bullets.get(i);
			    bullet.hitBase(base);
				bullet.hitEnemy(enemys);
			    bullet.hitTank(mytank1);
			    if(numOfPlayer==2){
			    bullet.hitTank(mytank2);}
			    
			    bullet.hitWall(walls);
			    bullet.hitSteel(steels);
			    bullet.collide(bullets);
			    bullet.paint(g);
			    
		}
		
			
			
			for(int j=0;j<enemys.size();j++){
			    Enemy enemy=enemys.get(j);	
			    enemy.collideBase(base);
			    enemy.collide(mytank1);
			    if(numOfPlayer==2){
			    enemy.collide(mytank2);}
			    if(!pause){enemy.collide(enemys);}
			    enemy.collideRiver(rivers);
			    enemy.collideWall(walls);
			    enemy.collideSteel(steels);
			    enemy.paint(g);}			    
			
			mytank1.paint(g);
			if(numOfPlayer==2){
			mytank1.collide(mytank2);
			
			mytank2.paint(g);
			mytank2.collide(mytank1); 
			}
			
		    for(int i=0;i<grasses.size();i++){
		    	Grass grass=grasses.get(i);
		    	grass.paint(g);
		    }
		    
		    if(judge){	
			    if(totalNumOfEnemy==0){
			    	win=true;
			    	judge=false;
			    	gameM.cilp.stop();
			    	gameM.musicRun("win");
			    
			    }

			    else if((!base.isAlive())||(mytank1.getLife()==0&&level==1)||(mytank1.getLife()==0&&level==2||(mytank2.getLife()==0)))
			    {
			    	lose=true;
			    	 gameM.cilp.stop();
			    	 gameM.musicRun("lose");
			    	 judge=false;
			    	}
					}
			    if(lose){
		 if(step1>0){
			  
			    g.drawImage(imageLose,WIDTH/2-10*step1-100,HEIGHT/2-100,this);
			    step1--;

				 }
				else if(step2>0){
					 g.drawImage(imageLose,WIDTH/2-100,HEIGHT/2-100,this);
						step2--;
					}
				else if(step2==0){
					
					UI u= new UI();
					u.launchFrame();
			
					gameM.cilp.stop();
					this.setVisible(false);
				}
					}
		else if(win){
			 if(step1>0){
			 g.drawImage(imageWin,WIDTH/2-10*step1-130,HEIGHT/2-100,this);
			    
			    step1--;
			 }
				else if(step2>0){
					
					 g.drawImage(imageWin,WIDTH/2-130,HEIGHT/2-100,this);
						step2--;
					}
				else if(step2==0){
					
			
					gameM.cilp.stop();
				
				this.setVisible(false);
				if(this.level<3){
				Game nextGame =new Game();
				nextGame.level=this.level+1;
				nextGame.setFrame();
				}
				else	
					{UI u= new UI();
				u.launchFrame();}
		 }
			 
		 }
			 	g.drawString("level: "+this.level, 370,60);
		    	 g.drawString("livesOfPlayer1: "+mytank1.getLife(), 5,60);
		    	 g.drawString("totalNumOfEnemy: "+totalNumOfEnemy, 5,80);  
		    	 g.drawString("numOfBoss: "+numOfBoss, 5,100);  
			    if( numOfPlayer==2){
			    	g.drawString("level: "+this.level, 370,60);
			    g.drawString("livesOfPlayer1: "+mytank1.getLife(),5,60);
			   
			    g.drawString("livesOfPlayer2: "+mytank2.getLife(), 700,60);
			    g.drawString("totalNumOfEnemy: "+totalNumOfEnemy, 5,80);
		 }
			    
			    }
	 public void showHelp(Graphics g) {		
		 g.drawImage(imageHelp,WIDTH/2-130,HEIGHT/2-100,this);
		 
	 }
	 public void update(Graphics g) {
		 if(paint)
			{
			if(offscreenImage==null){
				offscreenImage=createImage(WIDTH,HEIGHT);
			}
		Graphics offscreenGraphics=offscreenImage.getGraphics();
		Image image=new ImageIcon("src/Image/map/map.jpg").getImage();
	
		offscreenGraphics.drawImage(image,0,0,this);
		
		paint(offscreenGraphics);
		g.drawImage(offscreenImage,0,0,this);
		}
		 
	 }
		
		public void setFrame()
		{
			totalNumOfEnemy=20*level;
			numOfBoss=level;
			numOfEnemy=19;
			gameM.musicRun("game");

			mb.add(me1);
			mb.add(me2);

			me1.add(mi1);
			me1.add(mi2);
			me2.add(mi3);
			this.setMenuBar(mb);
			mi1.addActionListener(this);
			mi2.addActionListener(this);
			mi3.addActionListener(this);
		    River.set(this);
		    Wall.set(this);
		    Grass.set(this);
		    Steel.set(this);
			for(int i=0;i<3;i++){
		    	enemys.add(new Enemy(150+200*i,50,1,0,12,false,this));
		    }
			enemys.add(new Enemy(700,200,2,6,12,false,this));
			if(level>1){enemys.add(new Enemy(700,400,2,6,12,false,this));}
			if(level>2){enemys.add(new Enemy(700,550,3,12,12,false,this));}
		
			this.setLocation(300,100);
			this.setSize(WIDTH,HEIGHT);
			this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				Game.this.setVisible(false);
			}
		});

			this.setVisible(true);
			this.setResizable(false);
			this.addKeyListener(new KeyMoniter());
			new Thread(new RepaintThread()).start();
		}
  private class RepaintThread implements Runnable{
			public void run() {
				while(true){
					repaint();
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}	
			}
  }
		private class KeyMoniter extends KeyAdapter{
			public void keyReleased(KeyEvent e) {
			
				mytank1.keyReleased(e);
				mytank2.keyReleased(e);
			}

			public void keyPressed(KeyEvent e) {
			 mytank1.keyPressed(e);
			 mytank2.keyPressed(e);
			}
		}
		public void actionPerformed(ActionEvent e) {
			
			gameM.musicRun("click");
			
			paint=false;
				if(mi1==e.getSource()){
					gameM.cilp.stop();
					this.setVisible(false);
					Game g =new Game();
					g.setFrame();
				}
				if (mi2==e.getSource()){
					UI dlg = new UI(this);
					dlg.setDlg();
				}
				if (mi3==e.getSource()){
					
					UI u = new UI(this);
				
					u.launchFrame();
					
					u.M.cilp.stop();
				
					u.showHelp();
					this.setVisible(false);
				}
			}
}
