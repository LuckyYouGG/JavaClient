package team.lj.DrawPic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

public class ImageBoard extends JPanel{
	Point startPoint,endPoint,movePoint;
	String type ;
	Vector<ShapeImage> shapeArrary = new Vector<ShapeImage>();
	boolean isFull = false;//是否填充颜色
	boolean isMove = false;
	
	int degree = 2 ;//线条粗细
	
	BasicStroke basicStroke = new BasicStroke(1,
			BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,2);

	Vector<Pencil> pencilPoint = new Vector<Pencil>();
	
	Color color ;
	ShapeImage shapeImage;
	public ImageBoard()
	{						
		color = Color.black;
		
		addMouseListener(new MouseAdapter(){
			//鼠标按下
			public void mousePressed(MouseEvent e) {
				startPoint = e.getPoint();					
			}
			//鼠标释放
			public void mouseReleased(MouseEvent e)
			{
				isMove = false;			
				if(shapeImage != null)
				{
					shapeArrary.add(shapeImage);
					shapeImage = null ;
				}
				if(type.trim().equals("铅笔"))
					shapeArrary.add(new ShapeImage(null,color,pencilPoint));

				pencilPoint = new Vector<Pencil>();

				repaint();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter(){
			//鼠标拖动
			public void mouseDragged(MouseEvent e)
			{
				movePoint = e.getPoint();				
				isMove = true;
				if(type.trim().equals("铅笔"))
					pencilPoint.add(new Pencil(movePoint,color,basicStroke));
				drawImage();
			}
		});
		setSize(350,295);
	}
	/**
	 * 设置线条粗细
	 * @param bs 线条粗细
	 */
	public void setBasicStroke(int bs)
	{
		basicStroke = new BasicStroke(bs,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,2);
	}
	/**
	 * 绘制前的处理
	 */
	public void drawImage()
	{
		
		int x = Math.min(startPoint.x, movePoint.x);
		int y = Math.min(startPoint.y, movePoint.y);
		int w = Math.abs(startPoint.x - movePoint.x);
		int h = Math.abs(startPoint.y - movePoint.y);
		
		if(type.trim().equals("圆")){			
			shapeImage = new ShapeImage(new Ellipse2D.Double(x,y,w,h),color,null);
		}
		else if(type.trim().equals("方")){		
			shapeImage = new ShapeImage(new Rectangle2D.Double(x,y,w,h),color,null);
		}
		else if(type.trim().equals("线")){
			shapeImage = new ShapeImage(new Line2D.Double(startPoint,movePoint),color,null);
		}						
		repaint();

	}
	
	/**
	 * 设置图形的种类：圆，方，...
	 * @param t
	 */
	public void setType(String t)
	{
		type = t ;
	}
	
	/**
	 * 设置颜色
	 * @param c
	 */
	public void setColor(Color c)
	{
		color = c ;
	}
	public void setFull(boolean f)
	{
		isFull = f ;
	}
	

	/*
	 *  绘制图像
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;		
		
		
		if(shapeImage != null){
			shapeImage.draw(g2);
			
		}
		
		for(ShapeImage s :shapeArrary)
			s.draw(g2);
		
		if( pencilPoint != null && !pencilPoint.isEmpty()){
			
			Point point = pencilPoint.firstElement().getPoint();
			
			for(Pencil p : pencilPoint){
				Point pp = p.getPoint();
				g2.setStroke(g2.getStroke());
				g2.drawLine(point.x, point.y, pp.x, pp.y);
				point = pp;
			}			
		}		
	}
	
	/**
	 * 删除最后一个图像
	 */
	public void removeEnd()
	{
		if(!shapeArrary.isEmpty())
			shapeArrary.remove(shapeArrary.size() -1 );
		repaint();
	}
	
	/**
	 * 删除该面板上的所有图像
	 */
	public void removeAllImage()
	{
		if( !shapeArrary.isEmpty())
			shapeArrary.removeAllElements();
		repaint();
	}
	
	/**
	 * 封装一个图形的类
	 * @author xuqidong
	 *
	 */
	private class ShapeImage
	{
		Shape shape;
		Color color;
		boolean full ;//是否填充颜色
		Vector<Pencil> line;//铅笔的点
		BasicStroke basicStroke;
		public ShapeImage(Shape shape,Color color ,Vector<Pencil> l  )
		{
			this.basicStroke = ImageBoard.this.basicStroke;
			this.shape = shape ;
			this.color = color;
			if(	!type.trim().equals("线"))
				full = isFull;
			line =  l;
		}
		
		/**
		 * 绘制
		 * @param g2
		 */
		public void draw(Graphics2D g2)
		{
			g2.setColor(color);
			g2.setStroke(basicStroke);
			if( line != null && !line.isEmpty())
			{
				Point point = line.firstElement().getPoint();
				for(Pencil p : line){
					Point pp = p.getPoint();
					g2.setStroke(g2.getStroke());
					g2.drawLine(point.x, point.y, pp.x, pp.y);
					point = pp;
				}
				return ;
			}
			
			else if( shape == null)
				return ;
			
			if(full )
				g2.fill(shape);
			else
				g2.draw(shape);
		}
	}
	
}