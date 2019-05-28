package Image.map;
import java.awt.Color;
import java.awt.Graphics;


public class Blood {
    int x,y;
    public static final int LENGTH=50;
    int topNumber,number;
    public Blood(int x,int y,int number,int topNumber){
    	this.x=x;
    	this.y=y;
    	this.number=number;
    	this.topNumber=topNumber;
    }
    
    public void paint(Graphics g){
    	if(number>topNumber){number=topNumber;}
    	else if(number<0){number=0;}
    	
    	Color c=g.getColor();

		g.setColor(Color.white);
		g.drawRect(x,y,LENGTH,5);
		g.setColor(Color.red);
		g.fillRect(x+1,y+1,number*LENGTH/topNumber-1,4);		
		
		g.setColor(c);
    }
    
}
