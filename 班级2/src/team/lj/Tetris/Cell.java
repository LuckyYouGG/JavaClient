package team.lj.Tetris;


import java.awt.image.BufferedImage;

/**
 * ����˹�����е���С��λ������(ϸ��)
 * ����(����)��
 *  row --�к�
 *  col--�к�
 *  image--��Ӧ��ͼƬ
 * 
 * ��Ϊ(����)
 *   left()
 *   right()
 *   drop();
 */
public class Cell {
	private int row;
	private int col;
	private BufferedImage image;
	public Cell() {}
	public Cell(int row, int col, BufferedImage image) {
		super();
		this.row = row;
		this.col = col;
		this.image = image;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "(" + row + ", " + col + ")";
	}
	
	/**�����ƶ�*/
	public void left() {
		col--;
	}
	public void right() {
		col++;
	}
	public void drop() {
		row++;
	}
}




