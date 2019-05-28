package team.lj.JFrame;

import team.lj.Communction.Msginfo;
import team.lj.PersonalInfo.MyInfo;
import team.lj.PersonalInfo.PersonalInfo;
import team.lj.Tetris.J;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeInfo extends JFrame {
    class MyPanel extends JPanel{
        @Override
        public void paint(Graphics g) {
            super.paint(g);
           // Image image = new ImageIcon("background/login.png").getImage();
           // g.drawImage(image, 0, 0, this);
        }
    }
    private JPanel jPanel = new MyPanel();
    public ChangeInfo()
    {
        this.setSize(500,400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jPanel.setLayout(new GridLayout(7,1));
        //家乡
        Box boxFrom = Box.createHorizontalBox();
        JLabel jLabelFrom = new JLabel("家乡:    ");
        JTextField jTextFieldFrom = new JTextField(MyInfo.fromWhere);
        boxFrom.add(jLabelFrom);
        boxFrom.add(jTextFieldFrom);
        jPanel.add(boxFrom);

        //生日
        Box boxBirth = Box.createHorizontalBox();
        JLabel jLabelBirth = new JLabel("生日:    ");
        JTextField jTextFieldBirth = new JTextField(MyInfo.birthDate);
        boxBirth.add(jLabelBirth);
        boxBirth.add(jTextFieldBirth);
        jPanel.add(boxBirth);

        //星座
        Box boxXingZuo = Box.createHorizontalBox();
        JLabel jLabelXingZuo = new JLabel("星座:    ");
        JTextField jTextFieldXingZuo = new JTextField(MyInfo.constellation);
        boxXingZuo.add(jLabelXingZuo);
        boxXingZuo.add(jTextFieldXingZuo);
        jPanel.add(boxXingZuo);

        //个性签名
        Box boxGXQM = Box.createHorizontalBox();
        JLabel jLabelGXQM = new JLabel("个签:    ");
        JTextField jTextFieldGXQM = new JTextField(MyInfo.PersonalizedSignature);
        jTextFieldGXQM.setColumns(45);
        boxGXQM.add(jLabelGXQM);
        boxGXQM.add(jTextFieldGXQM);
        jPanel.add(boxGXQM);
        //密码
        Box boxPswFirst = Box.createHorizontalBox();
        JLabel jLabelFirst = new JLabel("密码： ");
        JPasswordField jPasswordFieldFirst = new JPasswordField();
        boxPswFirst.add(jLabelFirst);
        boxPswFirst.add(jPasswordFieldFirst);
        jPanel.add(boxPswFirst);



        Box boxPswSecond = Box.createHorizontalBox();
        JLabel jLabelSecond = new JLabel("确认密码:");
        JPasswordField jPasswordFieldSecond = new JPasswordField();
        boxPswSecond.add(jLabelSecond);
        boxPswSecond.add(jPasswordFieldSecond);
        jPanel.add(boxPswSecond);

        //按钮
        JButton jButtonFalse = new JButton("取消");
        jButtonFalse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeInfo.this.setVisible(false);
            }
        });

        JButton jButtonUpdata = new JButton("更新");
        jButtonUpdata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!String.valueOf(jPasswordFieldFirst.getPassword()).equals(String.valueOf(jPasswordFieldSecond.getPassword())))
                {
                     JOptionPane.showMessageDialog(null,"两次输入的密码不一致","警告",JOptionPane.ERROR_MESSAGE);
                }else
                 {
                    PersonalInfo tmp = new PersonalInfo();
                    tmp.setDoWhat(2);
                    tmp.setName(MyInfo.name);
                    tmp.setPersonalizedSignature(jTextFieldGXQM.getText());
                    tmp.setConstellation(jTextFieldXingZuo.getText());
                    tmp.setBirthDate(jTextFieldBirth.getText());
                    tmp.setFromWhere(jTextFieldFrom.getText());
                    if(jPasswordFieldSecond.getPassword().length != 0)
                      tmp.setPsw(String.valueOf(jPasswordFieldSecond.getPassword()));
                    else tmp.setPsw(MyInfo.psw);
                    new ManageInfoSocket().sendInfo(tmp);
                    JOptionPane.showMessageDialog(null,
                            "更新成功","提示",JOptionPane.PLAIN_MESSAGE);
                  }
            }
        });

        Box boxButton = Box.createHorizontalBox();
        boxButton.add(jButtonFalse);
        boxButton.add(jButtonUpdata);
        jPanel.add(boxButton);
        this.add(jPanel);
        this.setVisible(true);
    }
    public static void  main(String args[]){
        new ChangeInfo();
    }
}


















