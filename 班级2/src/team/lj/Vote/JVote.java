package team.lj.Vote;

import team.lj.PersonalInfo.MyInfo;
import team.lj.Utils.TimeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class JVote extends JFrame
{
    private JSplitPane jSplitPaneTop = new JSplitPane();
    private JSplitPane jSplitPaneMain = new JSplitPane();
    private JPanel itemPanel;
    private JTextField titleField;
    private JButton sendVote;
    private JButton addItem;
    private JTextField jTextFieldRight;

   public JVote()
   {
       this.setSize(500,600);
       this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
       this.getContentPane().add(jSplitPaneMain);
       jSplitPaneMain.setOrientation(JSplitPane.VERTICAL_SPLIT);
       jSplitPaneMain.setDividerSize(1);
       jSplitPaneTop.setEnabled(false);
       jSplitPaneMain.setEnabled(false);
       jSplitPaneTop.setDividerSize(1);
       jSplitPaneMain.setDividerLocation(50);
       jSplitPaneMain.setTopComponent(jSplitPaneTop);
       jSplitPaneTop.setDividerLocation(350);

       {
           addItem = new JButton("添加选项"); //这个需要监听

           addItem.addActionListener(new ActionListener()
           {
               int i = 0;

               @Override
               public void actionPerformed(ActionEvent e)
               {
                   if(i > 7)
                   {
                     JOptionPane.showMessageDialog(null,"最多只能八个选项","提醒",JOptionPane.PLAIN_MESSAGE);
                   }else
                   {
                       JSplitPane wc = new JSplitPane();
                       wc.setTopComponent(new Label("Option " + ++i + ":"));
                       JTextField tmp = new JTextField();
                       wc.setBottomComponent(tmp);
                       itemPanel.add(wc);
                       itemPanel.revalidate();//666
                   }
               }
           });

          JSplitPane  tmp = new JSplitPane();
          tmp.setBottomComponent(addItem);
          //------------------------------------------
          JSplitPane tmp1 = new JSplitPane();
          tmp1.setDividerSize(1);
          tmp1.setEnabled(false);
          tmp.setTopComponent(tmp1);
          JTextField jTextFieldLeft = new JTextField("设置最多可以选几项(阿拉伯数字):");
          jTextFieldLeft.setEditable(false);
          jTextFieldRight = new JTextField();//这个需要监听
           //感觉自己天秀
           jTextFieldRight.addKeyListener(new KeyListener() {
               @Override
               public void keyTyped(KeyEvent e) { }
               @Override
               public void keyPressed(KeyEvent e) { }
               @Override
               public void keyReleased(KeyEvent e)
               {
                   String tmp = jTextFieldRight.getText();
                   String wc = "";
                   int length = tmp.length();
                   System.out.println(tmp);
                   System.out.println(length);
                   for(int i = 0;i < length; i++)
                   {
                       if(tmp.charAt(i) >= '0' && tmp.charAt(i) <= '9')
                       {
                           wc += tmp.charAt(i);
                           System.out.println(wc);
                       }
                   }
                   jTextFieldRight.setText(wc);
               }
           });
          jTextFieldRight.addFocusListener(new FocusListener()
          {
              @Override
              public void focusGained(FocusEvent e) { }
              @Override
              public void focusLost(FocusEvent e)
              {
                  String tmp = jTextFieldRight.getText();
                  String wc = "";
                  int length = tmp.length();
                  System.out.println(tmp);
                  System.out.println(length);
                  for(int i = 0;i < length; i++)
                  {
                      if(tmp.charAt(i) >= '0' && tmp.charAt(i) <= '9')
                      {
                          wc += tmp.charAt(i);
                          System.out.println(wc);
                      }
                  }
                  jTextFieldRight.setText(wc);
              }
          });



          tmp1.setTopComponent(jTextFieldLeft);
          tmp1.setBottomComponent(jTextFieldRight);
          //-------------------------------------------
          tmp.setEnabled(false);
          tmp.setDividerSize(1);
          tmp.setDividerLocation(250);
          jSplitPaneTop.setTopComponent(tmp);
          sendVote = new JButton("发布投票");//这个需要监听
          sendVote.addActionListener(new ActionListener()
           {
               @Override
               public void actionPerformed(ActionEvent e)
               {
                    String sender = MyInfo.name;
                    String time = TimeUtil.getSysTime();
                    String title = titleField.getText();

                    int length = 0;
                    List<String> options = new ArrayList<>();//选项列表
                    int maxSelected = Integer.parseInt(jTextFieldRight.getText());

                    Component[] cb = itemPanel.getComponents();
                    for (Component component : cb)
                    {               //遍历
                       JSplitPane jSplitPane = (JSplitPane) component;   //强制转换
                       JTextField jTextField = (JTextField) jSplitPane.getBottomComponent();
                       if(!jTextField.getText().equals(""))
                       {
                           length++;
                           options.add(jTextField.getText());
                       }
                    }
                    if(length < maxSelected){
                        JOptionPane.showMessageDialog(null,
                                "最大多选数量不能超过选项数","警告",JOptionPane.ERROR_MESSAGE);
                    }else
                        {
                        VoteInfo tmp = new VoteInfo();
                        tmp.setSender(sender);
                        tmp.setDoWhat(1);
                        tmp.setTime(time);
                        tmp.setOptions(options);
                        tmp.setTitle(title);
                        tmp.setMaxSelected(maxSelected);

                        new VoteSocket().sendVote(tmp);
                        JOptionPane.showMessageDialog(null,"投票成功","提示",JOptionPane.PLAIN_MESSAGE);
                    }

               }
           });
          jSplitPaneTop.setBottomComponent(sendVote);

          JSplitPane jSplitPaneBottomTop = new JSplitPane();
          jSplitPaneBottomTop.setDividerSize(1);
          jSplitPaneBottomTop.setDividerLocation(50);
          jSplitPaneBottomTop.setEnabled(false);
          jSplitPaneBottomTop.setTopComponent(new JLabel("标题："));

          titleField = new JTextField(); //这个需要监听
          jSplitPaneBottomTop.setBottomComponent(titleField);

          JSplitPane jSplitPaneBottomMain = new JSplitPane();
          jSplitPaneBottomMain.setOrientation(JSplitPane.VERTICAL_SPLIT);
          jSplitPaneBottomMain.setDividerSize(1);
          jSplitPaneBottomMain.setTopComponent(jSplitPaneBottomTop);
          jSplitPaneBottomMain.setDividerLocation(40);
          jSplitPaneBottomMain.setEnabled(false);

          itemPanel = new JPanel(new GridLayout(10,1));//需要修改
          jSplitPaneBottomMain.setBottomComponent(itemPanel);

          jSplitPaneMain.setBottomComponent(jSplitPaneBottomMain);
       }
      this.setVisible(true);
   }
   //public static void  main(String args[]){new JVote();}
}
