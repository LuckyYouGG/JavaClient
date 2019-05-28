package team.lj.Tetris;

import javax.swing.*;

public class TetrisJFrame extends Thread
{
    @Override
    public void run() {
        super.run();
        JFrame frame = new JFrame("俄罗斯方块");

        // ������Ϸ���棬�����
        Tetris panel = new Tetris();
        // �����Ƕ�봰��
        frame.add(panel);


        frame.setSize(535, 580);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.setVisible(true);
        panel.start();
    }
}
