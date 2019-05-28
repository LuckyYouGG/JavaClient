package team.lj.Communction;

import team.lj.DrawPic.ScreenSavers;
import team.lj.PersonalInfo.MyInfo;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;

public class JCommunction {
    private JSplitPane jSplitPane= null;
    private  JTextPane jTextPane1_1;
    private JTextPane jTextPane = new JTextPane();
    private String recver;
    private JPanel jPanel_emtion;
    public JCommunction(String rever){this.recver = rever; }
   // public static void main(String args[]){
       // new JCommunction("2");
   // }
    public JSplitPane getJCommunction  ()
    {


        jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        jSplitPane.setDividerLocation(350);
        jSplitPane.setDividerSize(1);
        jSplitPane.setEnabled(false);

        JScrollPane jScrollPane_message = new JScrollPane();

        jTextPane.setEditable(false);
        jScrollPane_message.setViewportView(jTextPane);

        jSplitPane.setTopComponent(jScrollPane_message);
        //----------------------------------------------------------
        JPanel jPanel_tool = new JPanel(new GridLayout(1,4,10,10));

        JButton jButton_emtion = new JButton("插入表情");

        jButton_emtion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFrame jFrame = new JFrame("插入表情");
                jFrame.setBounds(500,500,500,300);
                JPanel jPanel = new JPanel(new GridLayout(5,7));
                jPanel.setPreferredSize(new Dimension(500,300));
                for(int i = 1;i <= 72; i++)
                {
                    JLabel jLabel = new JLabel();
                    ImageIcon icon = new ImageIcon("emtionsRes/"+i+".png");

                    jLabel.setIcon(icon);

                    jLabel.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            jTextPane1_1.insertIcon(jLabel.getIcon());
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
                    jPanel.add(jLabel);
                }
                jFrame.add(jPanel);
                jFrame.setVisible(true);
            }
        });

        JButton jButton_pic = new JButton("插入图片");
        //-------------------------------------------------
        jButton_pic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser f = new JFileChooser();

                f.setCurrentDirectory(new File("."));
                f.setFileSelectionMode(JFileChooser.FILES_ONLY);
                f.setMultiSelectionEnabled(true);
                f.addChoosableFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));
                f.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));
                int result = f.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    File[] files = f.getSelectedFiles();
                    for(File file : files){
                        ImageIcon img = new ImageIcon(file.getAbsolutePath());
                    //    img.setImage(img.getImage().getScaledInstance(200,150,Image.SCALE_DEFAULT));
                        jTextPane1_1.insertIcon(img);

                    }
                }
            }
        });

        //-----------------------------------------------------
        JButton jButton_draw = new JButton("即时手绘");
        jButton_draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScreenSavers();
            }
        });

        //-----------------------------------------------------------------------
        JButton jButton_send = new JButton("发送");

        jButton_send.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Msginfo msg =  new Msginfo(2);
                    msg.setSendTime(TimeUtil.getSysTime());
                    msg.setStyledDocument(jTextPane1_1.getStyledDocument());

                    if(recver != "班级群聊")
                    {
                        msg.setMark(2);
                        {
                            StyledDocument indoc = jTextPane1_1.getStyledDocument();
                            StyledDocument outdoc = jTextPane.getStyledDocument();

                            outdoc.insertString(outdoc.getLength(), "\n" + msg.getSendTime()+"\n",null);
                            outdoc.insertString(jTextPane.getCaretPosition(),"我：",null);
                            jTextPane.setCaretPosition(jTextPane.getDocument().getLength());

                            for(int j  = 0;j <= indoc.getLength() - 1; j++)
                            {
                                if(indoc.getCharacterElement(j).getName().equals("icon"))
                                {
                                    Element ele = indoc.getCharacterElement(j);
                                    ImageIcon icon = (ImageIcon) StyleConstants.getIcon(ele.getAttributes());
                                 //   if(icon.getIconHeight() > 30 && icon.getIconWidth() > 30)
                                       // icon.setImage(icon.getImage().getScaledInstance(200,150, Image.SCALE_DEFAULT));
                                    jTextPane.insertIcon(icon);

                                }else {
                                    try{
                                        String s = indoc.getText(j,1);
                                        outdoc.insertString(jTextPane.getCaretPosition(),s,null);
                                    } catch (Exception e1){
                                        e1.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    else msg.setMark(3);

                    msg.setSender(MyInfo.name);
                    msg.setRecer(recver);
                    System.out.println(MyInfo.name+"$$$$$$$$$$$$$$$");
                    System.out.println(recver+"*****");

                    CommClient.sendMessage(msg);
                    jTextPane1_1.setText("");

                } catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });

        //-----------------------------------------------------------------------
        jPanel_tool.add(jButton_emtion);
        jPanel_tool.add(jButton_pic);
        jPanel_tool.add(jButton_draw);
        jPanel_tool.add(jButton_send);

        JSplitPane jSplitPane_bottom = new JSplitPane(JSplitPane.VERTICAL_SPLIT);//???????????
        jSplitPane_bottom.setEnabled(false);
        jSplitPane_bottom.setDividerSize(1);
        jSplitPane_bottom.setTopComponent(jPanel_tool);//??????????
        jSplitPane_bottom.setDividerLocation(40);


        JScrollPane  jScrollPane_edit = new JScrollPane();
        jTextPane1_1 = new JTextPane();//?????----------------------------------------------
        jScrollPane_edit.setViewportView(jTextPane1_1);
        jSplitPane_bottom.setBottomComponent(jScrollPane_edit);

        JPanel JPanel_bottom = new JPanel();
        JPanel_bottom.setLayout(new BorderLayout());//??????
        JPanel_bottom.add(jSplitPane_bottom);

        jSplitPane.setBottomComponent(JPanel_bottom);
        return jSplitPane;
    }
}
