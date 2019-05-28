package team.lj.JFrame;


import team.lj.Communction.CommClient;
import team.lj.Communction.JChat;
import team.lj.Communction.Msginfo;
import team.lj.Communction.TimeUtil;
import team.lj.Log.JLog;
import team.lj.Music.JMusic;
import team.lj.Notice.*;
import team.lj.PersonalInfo.MyInfo;
import team.lj.PersonalInfo.PersonalInfo;
import team.lj.Snake.SnakeOne;
import team.lj.Tanke.UI;
import team.lj.Tetris.TetrisJFrame;
import team.lj.UpDownFiles.updownfiles;
import team.lj.Vote.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainJFrame extends JFrame
{
    private JToolBar jToolBar = new JToolBar();
    private JMenuBar jMenuBar = new JMenuBar();
    private JPanel jPanelMain = new JPanel(new BorderLayout());
    private JPanel jPanelLeft = new JPanel();
    private JPanel jPanelRight = new JPanel();
    private JSplitPane jSplitPaneRight = new JSplitPane();
    private JScrollPane jScrollPaneUp = new JScrollPane();
    private JScrollPane jScrollPaneDown = new JScrollPane();
    private JPanel jPanelRightUp = new JPanel(new GridLayout(45,1));
    private JPanel jPanelRightDown = new JPanel(new GridLayout(45,1));

    private JSplitPane jSplitPane  = new JSplitPane();
    private JTextField  fromField;
    private JTextField  nameField;
    private JTextField  birthField;
    private JTextField  xzField;
    private JTextField gxqmField;
    private JChat jChat = null;
    private JLabel labelImage = new JLabel();
    private String[] nameList =
            {"冯浩杰","程龙","王焱","彭俊雄","胡启","杨德智","李文栋","韩宗胜","童一凡"
            ,"柴腾杰","陈二猛","陈诺","陈瑀","崔振兴","邓睿婷","丁浩正","傅显坤","高付杰"
            ,"苟李渊","郭宇昊","贾宏伟","姜喜友","李睿","李宗亮","林繁","马振向","毛朝炤"
            ,"苗雨润","那云昊","庞兴国","庞昭辰","沈业欣","谭馨怡","王成龙","王飞宇","王霆威"
            ,"谢颖","谢云","薛羽丰","杨成业","叶恒杰","张嘉颖","张金宇","张帅","张哲铭"};
    JRadioButton isNbNo;
    JRadioButton isNbYes;
    //-----------------------------------------------------------
    ManageInfoSocket manageInfoSocket = null; //获得信息的一个网络socket,每次调用后 socket 都会关闭
    CommClient client = null;
    //-----------------------------------------------------------
    public  static void main(String args[]){ new MainJFrame(); }

    public  MainJFrame()//在主页面上启动所有网络
    {
        client = new CommClient(MyInfo.name);
        this.setSize(670,570);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(jPanelMain);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                  CommClient.sendMessage(new Msginfo(5));
                  System.exit(0);
            }
        });
        this.setTitle("你好！"+MyInfo.name);
        //投票页面
        List<VoteInfo> voteInfos =  new VoteSocket().getAllVotes().getVoteInfos();
        for(int i = 0 ;i <= voteInfos.size() - 1; i++)
        {
            VoteInfo tmp = voteInfos.get(i);
            if(tmp.getEverView() == null||tmp.getEverView().indexOf(MyInfo.name) == -1)
                 new UpdateVote(tmp);
        }
        //公告页面
        List<NoticeInfo> noticeInfos = new NoticeSocket().getNotice();
        for(int i = 0;i < noticeInfos.size(); i++)
        {
            // 2019/04/17
            String timenow = TimeUtil.getSysTime();
            String timeold = noticeInfos.get(i).getData();
            if(Integer.parseInt(timenow.substring(8,10)) - Integer.parseInt(timeold.substring(8,10)) <= 1)
            new JNoticeView(noticeInfos.get(i));
        }
        //更新在线人数
        Msginfo msginfo = new Msginfo(4);
        msginfo.setRecer(MyInfo.name);
        CommClient.sendMessage(msginfo);

        //--------------设置分割面板
        jSplitPane.setDividerLocation(450);

        jToolBar.setPreferredSize(new Dimension(800,40));
        jPanelMain.add(jToolBar,BorderLayout.PAGE_START);

        jPanelMain.add(jSplitPane,BorderLayout.CENTER);
        jScrollPaneUp.setViewportView(jPanelRightUp);
        jScrollPaneDown.setViewportView(jPanelRightDown);
        jSplitPaneRight.setDividerLocation(0);
        jSplitPaneRight.setOrientation(JSplitPane.VERTICAL_SPLIT);

        jSplitPaneRight.setTopComponent(jScrollPaneUp);
        jSplitPaneRight.setBottomComponent(jScrollPaneDown);
        jSplitPane.setTopComponent(jPanelLeft);
        jSplitPane.setBottomComponent(jSplitPaneRight);

        jSplitPane.setBackground(Color.GRAY);
        jPanelLeft.setLayout(null);
        jPanelLeft.setBounds(100, 100, 468, 541);
        //----------------

        //1

            Box verticalBox = Box.createVerticalBox();
            verticalBox.setBounds(10, 27, 183, 68);
            jPanelLeft.add(verticalBox);
            ButtonGroup buttonGroup1 = new ButtonGroup();
            JLabel jLabel = new JLabel();
            jLabel.setPreferredSize(new Dimension(30,10));
            jLabel.setText("1:是否为管理员");
            verticalBox.add(jLabel);

            isNbYes = new JRadioButton("是");
            isNbYes.setEnabled(false);
            isNbNo = new JRadioButton("否");
            isNbNo.setEnabled(false);

            buttonGroup1.add(isNbYes);
            buttonGroup1.add(isNbNo);
            verticalBox.add(isNbYes);
            verticalBox.add(isNbNo);
        //2
        {
            Box verticalBox_6 = Box.createVerticalBox();
            verticalBox_6.setBounds(10, 105, 220, 43);
            jPanelLeft.add(verticalBox_6);
            JLabel label_4 = new JLabel("2：姓名          ");
            verticalBox_6.add(label_4);
            nameField = new JTextField();
            nameField.setColumns(10);
            verticalBox_6.add( nameField);
            nameField.setEditable(false);
        }

        //3
        {
            Box verticalBox_2 = Box.createVerticalBox();
            verticalBox_2.setBounds(10, 168, 220, 43);
            jPanelLeft.add(verticalBox_2);
            JLabel label_2 = new JLabel("3:家乡          ");
            verticalBox_2.add(label_2);
            fromField = new JTextField();
            verticalBox_2.add(fromField);
            fromField.setEditable(false);
            fromField.setColumns(10);
            fromField.setEditable(false);
        }
        //4
        {
            Box boxBirth = Box.createVerticalBox();
            boxBirth.setBounds(10, 221, 220, 43);
            jPanelLeft.add(boxBirth);
            JLabel labelBirth = new JLabel("4:生日          ");
            boxBirth.add(labelBirth);
            birthField = new JTextField();
            boxBirth.add( birthField);

            birthField.setColumns(10);
            birthField.setEditable(false);
        }

        //5
        {
            Box boxXingZuo = Box.createVerticalBox();
            boxXingZuo.setBounds(10, 274, 220, 43);
            jPanelLeft.add(boxXingZuo);
            JLabel labelBoxXingZuo = new JLabel("5:星座          ");
            boxXingZuo.add(labelBoxXingZuo);
            xzField = new JTextField();
            boxXingZuo.add(xzField);

            xzField.setColumns(10);
            xzField.setEditable(false);
        }
        //6 GXQM
        {
            Box boxGXQM = Box.createVerticalBox();
            boxGXQM.setBounds(10,320,220,70);
            jPanelLeft.add(boxGXQM);

            JLabel labelGXQM = new JLabel("6:个性签名");
            boxGXQM.add(labelGXQM);
            gxqmField = new JTextField();

            boxGXQM.add(gxqmField);
            gxqmField.setColumns(10);
            gxqmField.setEditable(false);
        }
        //6
        {
            JButton btnNewButto = new JButton("发起私聊");
            btnNewButto.setBounds(10, 410, 141, 57);
            btnNewButto.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(jChat == null || jChat.getMark() == 1)
                    {
                        jChat = new JChat(MainJFrame.this.nameField.getText());
                   //     System.out.println("34567");
                    }

                    jChat.addJComunction(MainJFrame.this.nameField.getText());
                }
            });
            jPanelLeft.add(btnNewButto);
        }
        //设置菜单项-------------------------------------------------------------------------
        //1
        JMenu jMenuPresonal = new JMenu("更改个人信息");
        jMenuPresonal.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                new ChangeInfo();
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });
        jMenuPresonal.setOpaque(false);
        //2
        JMenu jMenuNotice = new JMenu("公告");
        jMenuNotice.setOpaque(false);
        JMenuItem jNoticeNew = new JMenuItem("发布新的公告");
        jNoticeNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MyInfo.isNB.equals("0"))
                JOptionPane.showMessageDialog(null,"你没有权限","警告",JOptionPane.ERROR_MESSAGE);
            else
                new JNotice();
            }
        });
        JMenuItem jNoticeList =new JMenuItem("查看历史公告");
        jNoticeList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new JNoticeMain();
            }
        });
        jMenuNotice.add(jNoticeNew);
        jMenuNotice.add(jNoticeList);
        //3
        JMenu jMenuTouPiao = new JMenu("投票");
        jMenuTouPiao.setOpaque(false);
        JMenuItem jTouPiaoNew = new JMenuItem("发布新的投票");
        jTouPiaoNew.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(MyInfo.isNB.equals("0"))
                    JOptionPane.showMessageDialog(null,"你没有权限","警告",JOptionPane.ERROR_MESSAGE);
                else
                    new JVote();
            }
        });
        JMenuItem jTouPiaoList =new JMenuItem("查看历史投票");
        jTouPiaoList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JMainVote();
            }
        });
        jMenuTouPiao.add(jTouPiaoNew);
        jMenuTouPiao.add(jTouPiaoList);
        //4
        JMenu jMenuGame = new JMenu("娱乐游戏");
        jMenuGame.setOpaque(false);
        JMenuItem jGameTetris = new JMenuItem("俄罗斯方块");
        //添加俄罗斯方块监听
        {
            jGameTetris.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                   new TetrisJFrame().start();
                }
            });
        }
        JMenuItem jGameTank = new JMenuItem("坦克大战");
        jGameTank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI u=new UI();
                u.launchFrame ();
            }
        });
        JMenuItem jGameSnaker = new JMenuItem("贪吃蛇");
        {
            jGameSnaker.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new SnakeOne();
                }
            });
        }
        jMenuGame.add(jGameTank);
        jMenuGame.add(jGameTetris);
        jMenuGame.add(jGameSnaker);

        //5
        JMenu jActivityrecord = new JMenu("日志记录");
        jActivityrecord.setOpaque(false);
        jActivityrecord.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new JLog();
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });

        //6
        JMenu jF5 = new JMenu("刷新在线人数");
        jF5.setOpaque(false);
        jF5.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Msginfo msginfo = new Msginfo(4);
                msginfo.setRecer(MyInfo.name);
                CommClient.sendMessage(msginfo);
              /*  Font font = new Font("楷体",Font.BOLD,20);
                JPanel jPanel = new JPanel(new GridLayout(40,1));
                System.out.println(MyInfo.onlines.size()+"1615");
                for(int i = 0;i < MyInfo.onlines.size() ; i++)
                {
                    JLabel jLabel = new JLabel("编号"+(i+1)+": "+MyInfo.onlines.get(i));
                    System.out.println(MyInfo.onlines.get(i));
                    jLabel.setForeground(Color.BLACK);
                    jLabel.setFont(font);
                    jPanel.add(jLabel);
                }*/
                //  MyInfo.onlines.clear();
              //  jScrollPaneUp.setViewportView(jPanel);
                //给右侧面板加一个列表
                List<String> names = new ArrayList<>();
                for(int i = 0;i < MyInfo.onlines.size(); i++)
                  names.add(MyInfo.onlines.get(i));
                for(int i = 0;i <= 44; i++)
                {
                    if(names.indexOf(nameList[i]) == -1){
                        names.add(nameList[i]);
                    }
                }
                {
                    jPanelRightDown = new JPanel(new GridLayout(45,1));
                    for(int i = 0;i <= 44; i++)
                    {
                        ImageIcon icon = null;
                        if(MyInfo.onlines.indexOf(names.get(i)) == -1)//不在线
                        {
                            icon = new ImageIcon("picEveryone/"+"灰色小人"+".png");

                        }else{
                            icon = new ImageIcon("picEveryone/"+"彩色小人"+".png");
                        }
                        icon.setImage(icon.getImage().getScaledInstance(50, 50,
                                Image.SCALE_DEFAULT));
                        JLabel jLabel = new JLabel(names.get(i),icon,JLabel.HORIZONTAL);
                        jLabel.addMouseListener(new MouseListener()
                        {
                            @Override
                            public void mouseClicked(MouseEvent e)
                            {
                                PersonalInfo t = new ManageInfoSocket().getInfo(jLabel.getText());
                                setInfo(t.getIsNB(),t.getName(),t.getFromWhere(),t.getBirthDate(),t.getConstellation(),t.getPersonalizedSignature());
                            }
                            @Override
                            public void mousePressed(MouseEvent e) { }
                            @Override
                            public void mouseReleased(MouseEvent e) { }
                            @Override
                            public void mouseEntered(MouseEvent e) { }
                            @Override
                            public void mouseExited(MouseEvent e) { }
                        });
                        jPanelRightDown.add(jLabel);
                    }
                    jScrollPaneDown.setViewportView(jPanelRightDown);
                }

            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });

        //7
        JMenu jbanjiqunliao = new JMenu("班级群聊");
        jbanjiqunliao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                {
                    if(jChat == null || jChat.getMark() == 1)
                    {
                        jChat = new JChat("班级群聊");
                        //     System.out.println("34567");
                    }
                    jChat.addJComunction("班级群聊");
                }
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });
        jbanjiqunliao.setOpaque(false);

        //8
        JMenu jFileShare = new JMenu("文件共享");
        jFileShare.setOpaque(false);
        jFileShare.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e) { new updownfiles(); }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });
       //音乐播放
        JMenu jMenuMusic = new JMenu("音乐播放");
        jMenuMusic.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new JMusic();
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });
        //超链接
        JMenu jMenuWed = new JMenu("山大网站");
        JMenuItem jMenuItem1 = new JMenuItem("山东大学主页");
        jMenuItem1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Desktop desktop = Desktop.getDesktop();
                try{
                    URI uri = new URI("http://www.sdu.edu.cn/"); //创建URI统一资源标识符
                    desktop.browse(uri);
                }catch (Exception e2)
                {
                    e2.printStackTrace();
                }
            }
        });
        JMenuItem jMenuItem2 = new JMenuItem("学生在线");
        jMenuItem2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Desktop desktop = Desktop.getDesktop();
                try
                {
                    URI uri = new URI("https://online.sdu.edu.cn/"); //创建URI统一资源标识符
                    desktop.browse(uri);
                }catch (Exception e2)
                {
                    e2.printStackTrace();
                }
            }
        });
        JMenuItem jMenuItem3 = new JMenuItem("山东大学本科生院");
        jMenuItem3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                Desktop desktop = Desktop.getDesktop();
                try{
                    URI uri = new URI("http://www.bkjx.sdu.edu.cn/"); //创建URI统一资源标识符
                    desktop.browse(uri);
                }catch (Exception e2)
                {
                    e2.printStackTrace();
                }
            }
        });


        JMenuItem jMenuItem4 = new JMenuItem("山东大学软件学院");
        jMenuItem4.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                Desktop desktop = Desktop.getDesktop();
                try{
                    URI uri = new URI("http://www.sc.sdu.edu.cn/"); //创建URI统一资源标识符
                    desktop.browse(uri);
                }catch (Exception e2)
                {
                    e2.printStackTrace();
                }
            }
        });
        jMenuWed.add(jMenuItem1);
        jMenuWed.add(jMenuItem2);
        jMenuWed.add(jMenuItem3);
        jMenuWed.add(jMenuItem4);

        setInfo(MyInfo.isNB,MyInfo.name,MyInfo.fromWhere,MyInfo.birthDate,MyInfo.constellation,MyInfo.PersonalizedSignature);



        jMenuBar.add(jMenuPresonal);
        jMenuBar.add(jbanjiqunliao);
        jMenuBar.add(jFileShare);
        jMenuBar.add(jMenuNotice);
        jMenuBar.add(jMenuTouPiao);
        jMenuBar.add(jMenuWed);
        jMenuBar.add(jMenuGame);

        jMenuBar.add(jMenuMusic);
        jMenuBar.add(jActivityrecord);

        jMenuBar.add(jF5);

        jMenuBar.setBorderPainted(true);

        jToolBar.add(jMenuBar);

        labelImage.setBounds(250, 0,  200,  230); //设置 图片的横坐标、纵坐标、宽、高
        jPanelLeft.add(labelImage);

        this.setVisible(true);

    }

    public void setInfo(String isNB,String name,String from,String birth,String xz,String gxqm)
    {
        if(isNB.equals("2") || isNB.equals("1"))
        {
            isNbYes.setSelected(true);
        }else
        {
            isNbNo.setSelected(true);
        }
        fromField.setText(from);
        nameField.setText(name);
        birthField.setText(birth);
        xzField.setText(xz);
        gxqmField.setText(gxqm);
       try{
           Icon icon = new ImageIcon(ImageIO.read(new File("picEveryone/"+name+".jpg")));
           labelImage.setIcon(icon); //创建一个带图片的 JLabel
       }catch (Exception e){
           e.printStackTrace();
       }


        System.out.println(name+"&&&");


        JLabel labelName = new JLabel("(学籍库照片)");
        labelName.setBounds(320,235,100,15);
        jPanelLeft.add(labelName);

        jPanelLeft.revalidate();
        jPanelLeft.repaint();
    }

}
