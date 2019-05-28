package team.lj.Tanke;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;  
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class UI extends JFrame implements ActionListener {
	
	private Container con;

	private Icon star=new ImageIcon("src/Image/UI/START.PNG");
	private Icon end=new ImageIcon("src/Image/UI/EXIT.png");
	private Icon opotion=new ImageIcon("src/Image/UI/OPOTION.png");;
	private Icon SinglePlay = new ImageIcon("src/Image/UI/SinglePlay.png");
	private Icon DoublePlay = new ImageIcon("src/Image/UI/DoublePlay.png");
	private Icon Level1 = new ImageIcon("src/Image/UI/Level1.png");
	private Icon Level2 = new ImageIcon("src/Image/UI/Level2.png");
	private Icon Level3 = new ImageIcon("src/Image/UI/Level3.png");
	private Icon Help = new ImageIcon("src/Image/UI/HELP.png");
	private Icon About = new ImageIcon("src/Image/UI/ABOUT.png");
	private Icon Back = new ImageIcon("src/Image/UI/BACK.png");
	

	
	
	
	private JButton bs=new JButton(star);
	private JButton bb=new JButton(end);;
	private JButton bo=new JButton(opotion);;
	private JButton ba=new JButton(Back);
	private JMenuBar jm;

	
	private int wPopup=235;
	private int hPopup=100;
	
	private JLabel labelS;
	private JLabel labelH;
	private JLabel labelA;

	

	  Button b1=new Button("return to main menu");
	  Button b2=new Button("end the game");
	  Button b3=new Button("resume");
	
	 
	  
	  JPopupMenu popup1 = new JPopupMenu();
	  JPopupMenu popup2 = new JPopupMenu();
	  JPopupMenu popup3 = new JPopupMenu();
	  
	   JMenuItem singlePlay = new JMenuItem(SinglePlay);
	   JMenuItem doublePlay = new JMenuItem(DoublePlay);   
	   JMenuItem  lv1=  new JMenuItem(Level1);
	   JMenuItem   lv2= new JMenuItem(Level2);
	   JMenuItem   lv3= new JMenuItem(Level3);
	   JMenuItem   help= new JMenuItem(Help);
	   JMenuItem   about= new JMenuItem(About);
	   JMenuItem   back= new JMenuItem(Back);
	  
	   
		public Music M= new Music();
		
		public Game g1=new Game();
		
		public Dialog  dlg ;
		
		public  Image aboutI=new ImageIcon("src/Image/UI/aboutI.PNG").getImage();
		public  Image helpI=new ImageIcon("src/Image/UI/helpI.jpg").getImage();
		
		
	  public UI(){};
	  public UI(Game g)
	  {
		
	
			this.g1=g;
			 dlg =new Dialog(g);
				g.paint=false;
				
				 dlg.setTitle("Choose");
				 
				 b1.setBackground(Color.red);
				 b2.setBackground(Color.red);
				 b3.setBackground(Color.red);
				   dlg.add(b1);
				   dlg.add(b2);
				   dlg.add(b3);
				   dlg.setSize(100, 200);
				   dlg.setLayout(new FlowLayout(FlowLayout.CENTER,5,30));
				   
				   dlg.setLocation(600, 200);
	  }
	  
	  public void setDlg()
	  {
		  dlg.setBackground(Color.GRAY);
		  dlg.show();
	
			dlg.addWindowListener(new WindowAdapter() {
	             public void windowClosing(WindowEvent e) {
	            	dlg.dispose();
	        		g1.paint = true;
				}	
			});
			
			b1.addActionListener(this);
			   b2.addActionListener(this);
			   b3.addActionListener(this);
			   
	}
	public void launchFrame (){
		singlePlay.addActionListener(this);
		doublePlay.addActionListener(this); 
	    help.addActionListener(this); 
		about.addActionListener(this); 
		back.addActionListener(this);
		popup1.setBackground(Color.red);
		popup2.setBackground(Color.red);
		popup3.setBackground(Color.DARK_GRAY);
			popup1.setPopupSize(wPopup, hPopup);
			popup1.add(singlePlay);
			popup1.add(doublePlay);
			this.add(popup1);
		popup2.setPopupSize(wPopup+150, hPopup+60);
		popup2.add(lv1);
		popup2.add(lv2);
		popup2.add(lv3);
		this.add(popup2);
		help.setBackground(Color.green);
		about.setBackground(Color.green);
		back.setBackground(Color.green);
		popup3.setPopupSize(150, 240);
		popup3.add(help);
	    popup3.add(about);
		popup3.add(back);
	    this.add(popup3);
	lv1.setBackground(Color.green);
	lv2.setBackground(Color.orange);
	lv3.setBackground(Color.red);
    bs.addActionListener(this);
    bb.addActionListener(this);
    bo.addActionListener(this);
    ba.addActionListener(this);
    
    ba.setVisible(false);
    
    
    lv1.addActionListener(this);
    lv2.addActionListener(this);
    lv3.addActionListener(this);
    
		con=this.getContentPane();
		con.setLayout(null);
		Cursor cs =new Cursor (Cursor.HAND_CURSOR);
		con.add(bb);
		JFrame.setDefaultLookAndFeelDecorated(true); 

		bs.setContentAreaFilled(false);
		bs.setBounds(10,10,140,100);
		bo.setContentAreaFilled(false);
		bo.setBounds(320,10,140,100);
		bb.setContentAreaFilled(false);
		bb.setVisible(true);
		bb.setCursor(cs);
		 ba.setContentAreaFilled(false);
		  ba.setBounds(650, 450, 140, 100);
            bb.setBackground(Color.red);
		    bb.setBounds(650,10,140,100);
		    this.add(bs);
		    this.add(bb);
		    this.add(bo);
			this.add(ba);
		    this.setResizable(false);
		    this.setFocusableWindowState(true);
		    this.setSize(g1. WIDTH,g1.HEIGHT);
		    this.setLocation(300, 100);
		
			try {
				M.musicRun("main");
			} catch (Exception e1) {
			
			}
		    JFrame.setDefaultLookAndFeelDecorated(true);  
		
		 this.setTitle("TankWar");  
	    this.setLayout(new BorderLayout());  
	    
		      ImageIcon imageIcon = new ImageIcon("src/Image/UI/开始界面.png");
		     
			     imageIcon.setImage(imageIcon.getImage().getScaledInstance(g1. WIDTH,g1.HEIGHT,
			    		    Image.SCALE_DEFAULT));

			    labelS = new JLabel(imageIcon);  
			    
			    
			      this.add(labelS);  
			   
			
			      
			  this.setVisible(true);

			  this.addWindowListener(new WindowAdapter()
		        {
		            public void windowClosing(WindowEvent e)
		            {
						UI.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		            }
		        });
	}
	
	public void showHelp(){
		
		bs.setVisible(false);
		bb.setVisible(false);
		bo.setVisible(false);
	    labelS.setVisible(false);
		
		ImageIcon imageIcon = new ImageIcon("src/Image/UI/helpI.jpg");
	     
	     imageIcon.setImage(imageIcon.getImage().getScaledInstance(g1. WIDTH,g1.HEIGHT-20,
	    		    Image.SCALE_AREA_AVERAGING));
	    labelH = new JLabel(imageIcon);
	      this.add(labelH);  
	      this.setVisible(true);
		ba.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		g1.gameM.musicRun("click");
		
		if(bs==e.getSource()){
			 int i;
		
			popup1.show(this,g1. WIDTH/2-wPopup/2,g1.HEIGHT/2-hPopup);
		 }
		if (bb==e.getSource()){
			  int i;
			  
	          i=JOptionPane.showConfirmDialog(null,"End The Game?");
	          
	          if(i==JOptionPane.YES_OPTION)
	          {
	        	  M.cilp.stop();
	         	          	 System.exit(0);
	          }
		 }
		if(bo==e.getSource()){
			 int i;
		
			popup3.show(this,g1. WIDTH/2-170/2,g1.HEIGHT/2-hPopup);
		 }
		if (b3==e.getSource()){
			dlg.dispose();
			g1.paint = true;
		}
		 if (b2==e.getSource()){
			 g1.gameM.cilp.stop();
			 System.exit(0);
			}
		if (b1==e.getSource()){
			 g1.gameM.cilp.stop();
			g1.setVisible(false);
			UI u= new UI();
			u.launchFrame();
		}
	if (singlePlay==e.getSource()){
			
			g1.numOfPlayer=1;
			
			popup2.show(this,g1. WIDTH/2-200,g1.HEIGHT/2-hPopup);
		}
		if (doublePlay==e.getSource()){
			popup2.show(this,g1. WIDTH/2-200,g1.HEIGHT/2-hPopup);
			g1.numOfPlayer=2;
		}
		if (lv1==e.getSource()){
			g1.level=1;
			g1.setFrame();
			this.setVisible(false);
			M.cilp.stop();
		}
		if (lv2==e.getSource()){
			g1.level=2;
			g1.setFrame();
			this.setVisible(false);
			M.cilp.stop();
		}
		if (lv3==e.getSource()){
			g1.level=3;
			g1.setFrame();
			this.setVisible(false);
			M.cilp.stop();
		}
		if(help==e.getSource()){
			  this.showHelp();
	}
	if(about==e.getSource()){
			
			bs.setVisible(false);
			bb.setVisible(false);
			bo.setVisible(false);
			labelS.setVisible(false);
		
			  ImageIcon imageIcon = new ImageIcon("src/Image/UI/aboutI.jpg");
			  imageIcon.setImage(imageIcon.getImage().getScaledInstance(g1. WIDTH,g1.HEIGHT-20,
			    		    Image.SCALE_AREA_AVERAGING));

			    labelH = new JLabel(imageIcon);  
			    this.add(labelH);  
			    this.setVisible(true); 
			    
			    ba.setVisible(true);
	}
	
		if(back==e.getSource()||ba==e.getSource()){
			bs.setVisible(true);
			bb.setVisible(true);
			bo.setVisible(true);
			labelS.setVisible(true);
			labelH.setVisible(false);  
			ba.setVisible(false);
			
			if(!g1.paint){
			g1.setVisible(true);
			g1.paint=true;
		    this.setVisible(false);
			}
		}
	
	}
	
}
