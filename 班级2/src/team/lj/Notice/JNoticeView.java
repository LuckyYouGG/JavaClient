package team.lj.Notice;

import team.lj.Tetris.J;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class JNoticeView extends JFrame {
    public JNoticeView(NoticeInfo tmp)
    {
        this.setBounds(100,100,500,400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JScrollPane jScrollPane = new JScrollPane();
       // JPanel jPanel = new JPanel();
        this.add(jScrollPane);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                JOptionPane.showMessageDialog(null,
                        "请务必确保看完","提醒",JOptionPane.PLAIN_MESSAGE);
            }
        });
            JTextArea jTextArea = new JTextArea();
            jTextArea.setSize(500,400);
            jTextArea.append("发布者：" + tmp.getSender()+"\n");
            jTextArea.append("发布时间:"+tmp.getData()+"\n");
            jTextArea.append("标题：" + tmp.getTitle()+"\n");
            jTextArea.append("公告内容:"+tmp.getContent()+"\n");
            Font font=new Font("宋体",Font.PLAIN,18);
            jTextArea.setFont(font);
            jTextArea.setEnabled(false);


        jScrollPane.setViewportView(jTextArea);
        this.setVisible(true);
    }
}











