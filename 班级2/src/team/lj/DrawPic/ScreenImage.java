package team.lj.DrawPic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class ScreenImage extends JPanel implements MouseListener,MouseMotionListener{
        private BufferedImage image;   //当前整个屏幕的截图
        private int width,height;      //表示本窗口以及image的大小
        private Point startPoint,endPoint;//表示开始和结束时候鼠标所在位置
        private JFrame jf;
        private Rectangle select=new Rectangle(0,0,0,0);//表示选中的区域
        private Cursor cs;//表示一般情况下的鼠标状态
      
        private BufferedImage get;  //当前所截取的图片
        ScreenSavers owner; //该类的调用者
        boolean currentIsMove = false ;
        
        /**
         * 构造函数
         * @param owner
         */
        public ScreenImage(ScreenSavers owner)
        {
        	this.owner = owner;
        	try{
        		//初始化
                Robot ro=new Robot();
                Toolkit tk=Toolkit.getDefaultToolkit();
                Dimension di=tk.getScreenSize();
                Rectangle rec=new Rectangle(0,0,di.width,di.height);              
                               
                this.image=ro.createScreenCapture(rec);
                
            	this.width=di.width;
            	this.height=di.height;
            	
            	//设置鼠标的形状
            	Image icon=Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("icon.png"));
            	cs=Toolkit.getDefaultToolkit().createCustomCursor(icon,new Point(0,0),"icon");
            	this.setCursor(cs);
            	
            	jf=new JFrame();
                jf.getContentPane().add(this);
                
                jf.setUndecorated(true);
                jf.setSize(di);       
                jf.setVisible(true);
                jf.setAlwaysOnTop(true);
                
                this.addMouseListener(this);
            	this.addMouseMotionListener(this);
            	
        	} catch(Exception exe){
                exe.printStackTrace();
            }
        }

        //绘制图片
        public void paintComponent(Graphics g)
        {
        	super.paintComponent(g);
        	Graphics2D g2 = (Graphics2D)g;
            g.drawImage(image,0,0,width,height,this);
            g.setColor(Color.RED);
            if(startPoint == null || endPoint == null){
            	return;
            }
            select=new Rectangle(Math.min(startPoint.x, endPoint.x),Math.min(startPoint.y, endPoint.y),
            		Math.abs(endPoint.x-startPoint.x),Math.abs(endPoint.y-startPoint.y));
            g2.draw(select);
            
        }
        /* 鼠标移动
         * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
         */
        public void mouseMoved(MouseEvent me){
        	//如果鼠标进入选区则改变鼠标形状
            if(select.contains(me.getPoint())){
                this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                currentIsMove = true ;
            } else{
                this.setCursor(cs);
                currentIsMove = false ;
            }
        }
        public void mouseExited(MouseEvent me){
            
        }
        public void mouseEntered(MouseEvent me){
            
        }
        public void mouseDragged(MouseEvent me){
            endPoint = me.getPoint();
            
            this.repaint();
        }
        //鼠标按下
        public void mousePressed(MouseEvent me){
            startPoint = me.getPoint();
        }
        //鼠标释放
        public void mouseReleased(MouseEvent me){
        	endPoint = me.getPoint();
            if(me.isPopupTrigger()){
                if(currentIsMove==true){                  
                    repaint();
                } else{               	
                    jf.dispose();
                }
            }
            
        }
        public void mouseClicked(MouseEvent me)
        {
        	//对鼠标双击事件的处理
            if(me.getClickCount()==2){
                Point p=me.getPoint();
                //鼠标在选区内双击
                if(select.contains(p)){
                    if(select.x + select.width < this.getWidth() && 
                    		select.y + select.height < this.getHeight()){
                    	
                        get=image.getSubimage(select.x,select.y,select.width,select.height);                       
                        jf.dispose();                     
                        owner.drawImage(get);
                    }else{
                    	
                        int wid=select.width,het=select.height;
                        if(select.x+select.width>=this.getWidth()){
                            wid=this.getWidth()-select.x;
                        }
                        if(select.y+select.height>=this.getHeight()){
                            het=this.getHeight()-select.y;
                        }
                        get=image.getSubimage(select.x,select.y,wid,het);
                        owner.drawImage(get);
                        jf.dispose();
                       
                    }                   
                }               
            }
       }
}
