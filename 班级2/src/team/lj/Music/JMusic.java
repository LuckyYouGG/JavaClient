package team.lj.Music;

import team.lj.Tetris.L;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JMusic extends JFrame {
    //public static void main(String args[]){ new JMusic(); };
    int where = 0;
    String List[] = {"飞云之下","鱼大仙儿-我曾（Cover：隔壁老樊）","黑猫-PLANET（女版铃声）",
            "蔡耀轩-鞠文娴-病变（咚鼓版）（蔡耀轩-Remix）","粟宇航-黄梅戏（Cover：慕容晓晓）","RoiMarch-Sanlalala（Remix）"};
    Thread thread = null;
    int YesOrNo = 0;
    public JMusic()
    {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
             if(thread != null)  thread.stop();
            }
        });
        this.setBounds(100,100,300,100);

        JPanel jPanel = new JPanel(new GridLayout(1,3));
        JButton jButtonUp = new JButton("上一首");

        jButtonUp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(where == 0) where = List.length - 1;
                else where--;
                if (thread != null)thread.stop();
                thread = new Sound1(List[where]);
               YesOrNo = 1;
               thread.start();
            }
        });

        jButtonUp.setPreferredSize(new Dimension(50,40));
        JButton jButtonDown = new JButton("下一首");
        jButtonDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             if(where == List.length - 1) where = 0;
             else  where++;
                if (thread != null)thread.stop();
             thread = new Sound1(List[where]);
             YesOrNo = 1;
             thread.start();
            }
        });

        JButton jButtonPause = new JButton("播放/暂停");

        JPanel jPanelMain = new JPanel(new GridLayout(2,1));
        jPanel.add(jButtonUp);
        jPanel.add(jButtonPause);
        jPanel.add(jButtonDown);

        Box boxDown = Box.createHorizontalBox();
        JLabel jLabel = new JLabel("歌曲列表");
        JComboBox jComboBox = new JComboBox(List);
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            //    if (e.getStateChange() == ItemEvent.SELECTED)
                {
                    // 选择的下拉框选项
                    if (thread != null)thread.stop();
                    thread = new Sound1(e.getItem().toString());
                    where = e.getID();
                    YesOrNo = 1;
                    thread.start();
                    System.out.println(e.getItem());
                }
            }
        });
        boxDown.add(jLabel);
        boxDown.add(jComboBox);
        jButtonPause.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try{

                    if(YesOrNo == 1)
                    {
                        YesOrNo = 2;
                        thread.suspend();
                    }else
                    if(YesOrNo == 2)
                    {
                        YesOrNo = 1;
                        thread.resume();
                    }
                    if(YesOrNo == 0)
                    {
                        if (thread != null)thread.stop();
                        thread = new Sound1(jComboBox.getSelectedItem().toString());
                        where = e.getID();
                        YesOrNo = 1;
                        thread.start();
                    }
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });
       jPanelMain.add(jPanel);
       jPanelMain.add(boxDown);
       this.add(jPanelMain);
       this.setVisible(true);
    }
}
