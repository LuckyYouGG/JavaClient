package team.lj.Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * 
* @ClassName: SnakeJPanel 
* @Description:  贪吃蛇画布类
* @author 少天
* @date 2017年12月7日 上午10:54:02 
*
 */

public class SnakeJPanel extends JPanel implements KeyListener,ActionListener
{
	
	//创建图片资源对象
	ImageIcon up=new ImageIcon("snake/up.png");
	ImageIcon down=new ImageIcon("snake/down.png");
	ImageIcon left=new ImageIcon("snake/left.png");
	ImageIcon right=new ImageIcon("snake/right.png");

	ImageIcon title=new ImageIcon("snake/title.png");
	ImageIcon food=new ImageIcon("snake/food.png");
	ImageIcon body=new ImageIcon("snake/body.png");

	//创建数组，用来存储蛇的身体数据
	int[] snakex=new int[750];
	int[] snakey=new int[750];
	//创建方向变量   默认向右
	String fx="R";
	//舍得长度
	int len=3;
	
	int score=0;
	//创建对象，用来存储是否开始游戏
	boolean isStarted=false;
	//创建计时器对象
	Timer timer=new Timer(100, this);
	//创建随机数对象 
	Random random=new Random();
	//创建食物的随机坐标  横向 34和单元   纵向 24和单元
	int foodx=random.nextInt(34)*25+25;
	int foody=random.nextInt(24)*25+75;
	//定义变量  是否游戏结束
	boolean isFiled=false;
	
	
	public SnakeJPanel()
	{
		this.setFocusable(true);//可以获得焦点
		this.addKeyListener(this);//设置监听事件，监听本类里面 的监听
		//执行初始化方法
		initView();
		timer.start();//开始计时方法
	}
	/**
	 * 
	* @Title: initView 
	* @Description:初始化数据
	* @return void   无返回值
	* @throws
	 */
	public void  initView(){
		isStarted=false;
		isFiled=false;
		len=3;
		score=0;
		fx="R";
		snakex[0]=100;
		snakey[0]=100;
		snakex[1]=75;
		snakey[1]=100;
		snakex[2]=50;
		snakey[2]=100;
	}
	/**
	 * 画笔类
	 */
	public void paint(Graphics g) {
		/**
		 * this 代表那个画布
		 * g   代表用那个画笔画
		 * 25  横坐标
		 * 10 纵坐标
		 */
		title.paintIcon(this, g, 25, 10);//将title设置在画布上上
		//给画笔设置颜色  画出黑色区域
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 600);
		
		//判断方向画出蛇头，不同的方向使用不同的蛇头图片对象
		if (fx.equals("R")) {
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}
		if (fx.equals("L")) {
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}
		if (fx.equals("U")) {
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}
		if (fx.equals("D")) {
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		//画出蛇的身体，循环数组，从1 开始 到蛇得长度
		for(int i=1;i<len;i++){
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		//游戏暂停时 提示一下信息
		if (!isStarted) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 30));
			//这里有一个中文支持的问题
			g.drawString("Press Space To Start/Pause", 250, 350);
		}
		for (int i = 0; i < len; i++) {
			if (foodx==snakex[i]&&foody==snakey[i]) {
				foodx=random.nextInt(34)*25+25;
				foody=random.nextInt(24)*25+75;
			}
			food.paintIcon(this, g, foodx, foody);
		}
		
		//游戏结束时 提示一下信息
		if (isFiled) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("Game Over Press Space To Restart", 250, 350);
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 15));
		g.drawString("Score : "+score, 750, 30);
		g.drawString("     len : "+len, 750, 50);
		
		
	}
	//计时器方法
	@Override
	public void actionPerformed(ActionEvent e) {
		//重新开始计时
		timer.start();
		//判断  游戏运行中  并且 没有失败才进行运行
		if (isStarted&&!isFiled) {
			//在启动状态时，重置蛇的身体坐标
			for (int i =len; i > 0; i--) {
				snakex[i]=snakex[i-1];
				snakey[i]=snakey[i-1];
			}
			//根据方向，重置蛇头的坐标
			if (fx.equals("R")) {
				snakex[0]=snakex[0]+25;
				if (snakex[0]>850) {
					snakex[0]=25;
				}
			}
			if (fx.equals("L")) {
				snakex[0]=snakex[0]-25;
				if (snakex[0]<25) {
					snakex[0]=850;
				}
			}
			if (fx.equals("U")) {
				snakey[0]=snakey[0]-25;
				if (snakey[0]<75) {
					snakey[0]=650;
				}
			}
			if (fx.equals("D")) {
				snakey[0]=snakey[0]+25;
				if (snakey[0]>650) {
					snakey[0]=75;
				}
			}
			if (snakex[0]==foodx&&snakey[0]==foody) {
				len++;
				score++;
				foodx=random.nextInt(34)*25+25;
				foody=random.nextInt(24)*25+75;
			}
			for (int i = 3; i < len; i++) {
				if (snakex[0]==snakex[i]&&snakey[0]==snakey[i]) {
					isFiled=true;
				}
			}
		}
		repaint();
	}
	
	//按下之后执行此方法
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode=e.getKeyCode();
		//当点击空格时修改游戏状态，刷新页面
		if (keyCode==KeyEvent.VK_SPACE) {
			//判断游戏是否结束，如果结束，则重置游戏
			if (isFiled) {
				initView();
			}else {
				isStarted=!isStarted;
			}
		}
		if (keyCode==KeyEvent.VK_RIGHT&&fx!="L") {
			fx="R";
		}
		if (keyCode==KeyEvent.VK_LEFT&&fx!="R") {
			fx="L";
		}
		if (keyCode==KeyEvent.VK_UP&&fx!="D") {
			fx="U";
		}
		if (keyCode==KeyEvent.VK_DOWN&&fx!="U") {
			fx="D";
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	

}
