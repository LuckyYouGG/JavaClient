package team.lj.Communction;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.awt.event.*;
import java.util.LinkedList;

public class JChat extends  JFrame{
    private JFrame jFrame ;
    private JTabbedPane jTabbedPane;
    private   int mark = 1;
    private String sender;

    public int getMark() { return mark; }
  // public static void main(String args[]){
     //  new JChat("!") .addJComunction("@");;

  // }
    public JChat(String sender)
    {
         this.sender = sender;
         mark = 2;
         jFrame = new JFrame();
         jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         jFrame.setBounds(150,150,870,600);
         jTabbedPane = new JTabbedPane();

         jFrame.add(jTabbedPane);

         jFrame.setVisible(true);


         jFrame.addWindowListener(new WindowListener() {
             @Override
             public void windowOpened(WindowEvent e) { }

             @Override
             public void windowClosing(WindowEvent e) { }

             @Override
             public void windowClosed(WindowEvent e) {
                mark = 1;
             }

             @Override
             public void windowIconified(WindowEvent e) { }
             @Override
             public void windowDeiconified(WindowEvent e) { }
             @Override
             public void windowActivated(WindowEvent e) { }
             @Override
             public void windowDeactivated(WindowEvent e) {
             }
         });


        new Distribute_messages().start();
    }
    public void addJComunction(String recer)
    {
        JSplitPane jSplitPane = new JCommunction(recer).getJCommunction();//聊天界面

        jTabbedPane.addTab(recer,jSplitPane);

        JPanel jPanel = new JPanel();

        jPanel.setSize(50,30);
        jPanel.setBackground(new Color(200,221,242));
        jPanel.setOpaque(false);//设置为透明的 不透明为flase
        JButton jButton = new JButton(recer);
        jButton.setBackground(new Color(200,221,242));
        jButton.setBorderPainted(false);
        jButton.setSize(new Dimension(40,30));
        TabButton tabButton = new TabButton();

        jPanel.add(jButton);
        jPanel.add(tabButton);

        jTabbedPane.setTabComponentAt(jTabbedPane.indexOfComponent(jSplitPane),jPanel);//！！！！

        tabButton. addActionListener(new ActionListener() {//删除按钮听
        public void actionPerformed(ActionEvent evt) {
            int i = jTabbedPane.indexOfTabComponent(jPanel);
            if (i != -1) jTabbedPane.remove(i);
           }
         });

        jButton.addActionListener(new ActionListener() {//切换页面监听
            @Override
            public void actionPerformed(ActionEvent e) {
                jTabbedPane.setSelectedIndex(jTabbedPane.indexOfTabComponent(jPanel));
            }
        });
    }

   class Distribute_messages extends Thread{
       @Override
       public void run()
       {
           super.run();
           while(this.isAlive())
           {
           //    System.out.println("mnbv");
               LinkedList<Msginfo> msginfoList = CommClient.listMiginfo;
             //  System.out.println(msginfoList.size());
               while (!msginfoList.isEmpty())
               {
                   Msginfo msg = msginfoList.removeLast();
                      //  System.out.println(msg.getRecer()+"#");
                   System.out.println(msg.getRecer()+"^^^");
                   int length = jTabbedPane.getTabCount();
                   int bj = 1,where = 0;
                   for(int i = 0; i < length; i++)
                   {
                       System.out.println(((JButton)(((JPanel)(jTabbedPane.getTabComponentAt(i))).getComponent(0))).getText()+"@#@$");
                       if(((JButton)(((JPanel)(jTabbedPane.getTabComponentAt(i))).getComponent(0))).getText().equals(msg.getSender())||
                               ((JButton)(((JPanel)(jTabbedPane.getTabComponentAt(i))).getComponent(0))).getText().equals(msg.getRecer()))
                       {
                           bj = 2;
                           where = i;
                           break;
                       }
                   }
                   if(bj == 1)
                   {
                       if(msg.getRecer().equals("班级群聊"))
                           JChat.this.addJComunction(msg.getRecer());
                       else
                       JChat.this.addJComunction(msg.getSender());
                       where = jTabbedPane.getTabCount()-1;
                   }

                    int i = where;
                   {
                       JSplitPane jSplitPane = (JSplitPane)jTabbedPane.getComponentAt(i);
                       {
                            JScrollPane jScrollPane = (JScrollPane)jSplitPane.getTopComponent();
                            JTextPane jTextPane = (JTextPane)jScrollPane.getViewport().getView();
                            try
                            {
                                StyledDocument indoc = msg.getStyledDocument();
                                StyledDocument outdoc = jTextPane.getStyledDocument();
                                outdoc.insertString(outdoc.getLength(), "\n" + msg.getSendTime()+"\n",null);
                                outdoc.insertString(outdoc.getLength(),  msg.getSender() + ":",null);
                                System.out.println(msg.getSender()+"made");
                                jTextPane.setCaretPosition(jTextPane.getDocument().getLength());

                                for(int j = indoc.getLength() - 1;j >= 0;j--)
                                {
                                    if(indoc.getCharacterElement(j).getName().equals("icon"))
                                    {
                                        Element ele = indoc.getCharacterElement(j);
                                        ImageIcon icon = (ImageIcon) StyleConstants.getIcon(ele.getAttributes());
                                     //   if(icon.getIconHeight() > 30 && icon.getIconWidth() > 30)
                                      //  icon.setImage(icon.getImage().getScaledInstance(200,150, Image.SCALE_DEFAULT));
                                        jTextPane.insertIcon(icon);

                                    }else {
                                        try{
                                            String s = indoc.getText(j,1);
                                            outdoc.insertString(jTextPane.getCaretPosition(),s,null);
                                        } catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                       }
                   }
               }
           }
       }
   }
    private class TabButton extends JButton
    {
        public TabButton()
        {
            int size = 10;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("关闭");
            setUI(new BasicButtonUI());
            setContentAreaFilled(false);
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            //翻转效果
            setRolloverEnabled(true);
            //鼠标事件，进入时画边框，移出时取消边框
            addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseEntered(MouseEvent e)
                {
                    Component component = e.getComponent();
                    if (component instanceof AbstractButton)
                    {
                        AbstractButton button = (AbstractButton) component;
                        button.setBorderPainted(true);
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    Component component = e.getComponent();
                    if (component instanceof AbstractButton) {
                        AbstractButton button = (AbstractButton) component;
                        button.setBorderPainted(false);
                    }
                }
            });
            //单击关闭按钮事件
        }
        @Override
        public void updateUI() {
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            //鼠标按下时偏移一个坐标点
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);
            //鼠标在按钮上时为红色
            if (getModel().isRollover()) {
                g2.setColor(Color.RED);
            }
            int delta = 6;
            g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
            g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
            g2.dispose();
        }
    }


}
