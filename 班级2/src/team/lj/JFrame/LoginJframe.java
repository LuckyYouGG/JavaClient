package team.lj.JFrame;


import team.lj.PersonalInfo.MyInfo;
import team.lj.PersonalInfo.PersonalInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

class MyPanel extends JPanel  {

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image image = new ImageIcon("background/login.png").getImage();
        g.drawImage(image, 0, 0, this);
    }
}
public class  LoginJframe extends JFrame {


    private MyPanel myPanel;
    private JLabel jLabel_zhanghao = new JLabel("账号:");
    private JTextField jTextField_text1 = new JTextField( );

    Font lab = new Font("楷体",1,30);
    private JLabel jLabel_password = new JLabel("密码:");

    Font lat  = new Font("楷体",1,30);
    private JPasswordField jTextField_text2 = new JPasswordField();

    private JButton btn_land = new JButton("登陆");
    Font btn = new Font("楷体",2,20);

    private JButton btn_zhuce = new JButton("注册");

    public static  int pd = 0;
    public static String ak1, ak2;
    private Container contentPane = this.getContentPane();

    ManageInfoSocket manageInfoSocket;
    public static void main(String args[]){

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //------------------------------------------------------------------------------------------------
         /*   String outLookAndFeel =" com.jtattoo.plaf.mint.MintLookAndFeel";
            try
            {
                UIManager.setLookAndFeel(outLookAndFeel);
            } catch (Exception e1)
            {
                e1.printStackTrace();
            }*/

        /**
         * com.jtattoo.plaf.noire.NoireLookAndFeel  柔和黑
         * com.jtattoo.plaf.smart.SmartLookAndFeel 木质感+xp风格
         * com.jtattoo.plaf.mint.MintLookAndFeel  椭圆按钮+黄色按钮背景
         * com.jtattoo.plaf.mcwin.McWinLookAndFeel 椭圆按钮+绿色按钮背景
         * com.jtattoo.plaf.luna.LunaLookAndFeel  纯XP风格
         * com.jtattoo.plaf.hifi.HiFiLookAndFeel  黑色风格
         * com.jtattoo.plaf.fast.FastLookAndFeel  普通swing风格+蓝色边框
         * com.jtattoo.plaf.bernstein.BernsteinLookAndFeel  黄色风格
         * com.jtattoo.plaf.aluminium.AluminiumLookAndFeel 椭圆按钮+翠绿色按钮背景+金属质感
         * com.jtattoo.plaf.aero.AeroLookAndFeel xp清新风格
         * com.jtattoo.plafacryl.AcrylLookAndFeel 布质感+swing纯风格
         * com.jtattoo.plaf.graphite.GraphiteLookAndFeel
         */
        //------------------------------------------------------------------------------------------------------------
      /*  try
        {
            // 设置外观
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
            JFrame.setDefaultLookAndFeelDecorated(true);
            // 设置主题
            SubstanceLookAndFeel.setCurrentTheme(new SubstanceEbonyTheme());
            // 设置按钮外观
            SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
            // 设置水印
            SubstanceLookAndFeel.setCurrentWatermark(new SubstanceWoodWatermark());
            // 设置边框
            SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
            // 设置渐变渲染
            SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());
            // 设置标题
            SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitlePainter());

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }*/
        new LoginJframe();
    }

    public LoginJframe()
    {
        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("班级管理系统");
        this.setResizable(false);
        setset();
        btn_land.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                 String s1 = jTextField_text1.getText();
                 String s2 = String.valueOf(jTextField_text2.getPassword());

                PersonalInfo tmp =  new PersonalInfo();

                tmp.setDoWhat(2);
                tmp.setName(s1);//姓名
                tmp.setPsw(s2);//密码

                manageInfoSocket = new ManageInfoSocket();
                int result =   manageInfoSocket.checkInfo(tmp);
                if(result == 1)
                 {
                     LoginJframe.this.setVisible(false);
                     manageInfoSocket = new ManageInfoSocket();
                     PersonalInfo myinfo = manageInfoSocket.getInfo(s1);
                     MyInfo.name = myinfo.getName();//姓名
                     MyInfo.StudentID = myinfo.getStudentID();//学号
                     MyInfo.fromWhere = myinfo.getFromWhere();//家乡
                     MyInfo.isNB = myinfo.getIsNB();// 是否是管理员
                     MyInfo.PersonalizedSignature = myinfo.getPersonalizedSignature();//个性签名
                     MyInfo.psw = myinfo.getPsw();
                     MyInfo.birthDate = myinfo.getBirthDate();//生日
                     MyInfo.constellation = myinfo.getConstellation();//星座

                     new MainJFrame();
                     System.out.println("打开主界面");
                 }else
                     JOptionPane.showMessageDialog(null,"账号密码错误","错误",JOptionPane.ERROR_MESSAGE);
            }
        });

        btn_zhuce.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new register();
            }
        });

        myPanel = new MyPanel();
        myPanel.add(jLabel_password);
        myPanel.add(jLabel_zhanghao);
        myPanel.add(jTextField_text1);
        myPanel.add(jTextField_text2);
        myPanel.add(btn_land);
        myPanel.add(btn_zhuce);
        myPanel.setLayout(null);
        getContentPane().add(myPanel);
        setVisible(true);

    }
    private void setset()
    {
        jLabel_zhanghao.setSize(200,100);
        jLabel_zhanghao.setLocation(80,20);
        jLabel_zhanghao.setFont(lab);
        jLabel_zhanghao.setForeground(Color.white);

        jLabel_password.setSize(200,100);
        jLabel_password.setLocation(80,80);
        jLabel_password.setFont(lat);
        jLabel_password.setForeground(Color.white);

        jTextField_text1.setSize(190,30);
        jTextField_text1.setLocation(160,55);

        jTextField_text2.setSize(190,30);
        jTextField_text2.setLocation(160,115);

        btn_land.setSize(80,30);
        btn_land.setLocation(300,180);
        btn_land.setFont(btn);

        btn_zhuce.setSize(80,30);
        btn_zhuce.setLocation(120,180);
        btn_zhuce.setFont(btn);
    }

}