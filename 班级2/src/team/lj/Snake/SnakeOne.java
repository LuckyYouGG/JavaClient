package team.lj.Snake;

import javax.swing.JFrame;

/**
 * 
* @ClassName: SnakeOne 
* @Description:  贪吃蛇1，基础版
* @author 少天
* @date 2017年12月7日 上午10:47:58 
*
 */
public class SnakeOne {
	
	public SnakeOne() {
		JFrame jFrame=new JFrame();//创建窗口
		jFrame.setBounds(10,10, 900, 720);//设置窗口大小  位置   宽高
		jFrame.setResizable(false);//是否可以进行拖拽
		jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//关闭
		
		SnakeJPanel snakeJPanel=new SnakeJPanel();//创建画布对象
		jFrame.add(snakeJPanel);	//将画布添加到窗口上
		
		jFrame.setVisible(true);//展示窗口
	}

}
