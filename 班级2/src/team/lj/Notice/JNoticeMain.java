package team.lj.Notice;

import team.lj.PersonalInfo.MyInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JNoticeMain extends JFrame {
    private JScrollPane jScrollPane = new JScrollPane();
    private JToolBar jToolBar = new JToolBar();
    private JPanel jPanelMain = new JPanel();
    private NoticeSocket notice;
    public JNoticeMain()
    {
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setBounds(100,100,500,600);
          JButton jButtonAdd = new JButton("发布新公告");
          JButton jButtonDelete = new JButton("删除所选公告");
          JButton jButtonF5 = new JButton("刷新");

          jToolBar.add(jButtonAdd);
          jToolBar.add(jButtonDelete);
          jToolBar.add(jButtonF5);
          jPanelMain.setLayout(new BorderLayout());
          jPanelMain.add(jToolBar,BorderLayout.PAGE_START);


          jPanelMain.add(jScrollPane,BorderLayout.CENTER);

          this.add(jPanelMain);
          this.setVisible(true);
          jButtonAdd.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  if(MyInfo.isNB.equals("0"))
                      JOptionPane.showMessageDialog(null,"你没有权限","警告",JOptionPane.ERROR_MESSAGE);
                  else
                  new JNotice();
              }
          });
          //刷新公告监听
          jButtonF5.addActionListener(new ActionListener()
          {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                  notice = new NoticeSocket();;
                  List<NoticeInfo> noticeList = notice.getNotice();
                  int length = noticeList.size();
                  System.out.println("#" + length);
                  JPanel jPanel = new JPanel(new GridLayout(100,1));
                  for(int i = length - 1  ; i >= 0; i --)
                  {
                      NoticeInfo tmp = noticeList.get(i);
                      JTextArea jTextArea = new JTextArea();
                      jTextArea.append("公告编号:" + (length - i) + "\n");
                      jTextArea.append("发布者：" + tmp.getSender()+"\n");
                      jTextArea.append("发布时间:"+tmp.getData()+"\n");
                      jTextArea.append("标题：" + tmp.getTitle()+"\n");
                      jTextArea.append("公告内容:"+tmp.getContent()+"\n");
                      jTextArea.append("-----------------------------------------------------\n");
                      jPanel.add(jTextArea);
                  }
                  jScrollPane.setViewportView(jPanel);
              }
          });
    }
    public static void main(String args[]){
        new JNoticeMain();
    }
}
