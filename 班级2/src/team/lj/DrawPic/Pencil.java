package team.lj.DrawPic;

import java.awt.*;


/**
 * 此类封装选择铅笔时候鼠标的轨迹
 * @author xuqidong
 *
 */
public class Pencil
{
	private Point p;
	private Color color;
	private BasicStroke bs;
	
	Pencil(Point p,Color color,BasicStroke bs)
	{
		this.p = p;
		this.color = color;
		this.bs = bs;
	}
	public Color getColor() {
		return color;
	}

	public Point getPoint() {
		return p;
	}

	public BasicStroke getBs() {
		return bs;
	}
	public void setBs(BasicStroke bs) {
		this.bs = bs;
	}
	
}