package team.lj.Vote;

import team.lj.Notice.JNotice;
import team.lj.PersonalInfo.MyInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JMainVote extends JFrame
{

    JPanel jPanelMain = new JPanel(new BorderLayout());
    JPanel votes;
    JToolBar jToolBar = new JToolBar();
    JScrollPane jScrollPane = new JScrollPane();
    JButton addVote = new JButton("发布投票");
    JButton getAllVote = new JButton("查看历史投票");
    VoteSocket voteSocket;
    public JMainVote()
    {
          this.setDefaultCloseOperation(HIDE_ON_CLOSE);
          this.add(jPanelMain);
          this.setBounds(200,200,500,600);

          jPanelMain.add(jToolBar,BorderLayout.PAGE_START);
          jToolBar.add(addVote);
          jToolBar.add(getAllVote);
          jScrollPane.setViewportView(votes);
          jPanelMain.add(jScrollPane,BorderLayout.CENTER);
          //监听写新投票
           addVote.addActionListener(new ActionListener()
           {
               @Override
               public void actionPerformed(ActionEvent e)
               {
                   if(MyInfo.isNB.equals("0"))
                       JOptionPane.showMessageDialog(null,"你没有权限","警告",JOptionPane.ERROR_MESSAGE);
                   else
                       new  JNotice();
               }
           });
           getAllVote.addActionListener(new ActionListener()
           {
               @Override
               public void actionPerformed(ActionEvent e)
               {
                   voteSocket = new VoteSocket();
                   VoteInfo voteInfo = voteSocket.getAllVotes();

                   List<VoteInfo> voteList = voteInfo.getVoteInfos();

                   int length = voteList.size();
                   System.out.println(length);
                   if(length == 0)
                       JOptionPane.showMessageDialog(JMainVote.this,"没有历史投票","提示",JOptionPane.PLAIN_MESSAGE);
                   votes = new JPanel(new GridLayout(30,1));
                   for(int i = 0;i < length; i++)
                   {

                       VoteInfo tmp = voteList.get(i);
                     //  new UpdateVote(tmp);
                       List<String> options = tmp.getOptions();
                       List<String> nums = tmp.getNums();
                       int l = nums.size();
                       String ans = "";
                       for(int j = 0;j < l; j++)
                       {
                           ans = ans + options.get(j)+"  票数：" + nums.get(j)+"\n";
                       }

                       JTextArea jTextArea = new JTextArea();
                       jTextArea.append("标题：" + tmp.getTitle() + "\n");
                       jTextArea.append("投票结果：" + "\n"+ ans);
                       jTextArea.append("发布时间：" + tmp.getTime() + "  发布者：" + tmp.getSender()+"\n");
                       votes.add(jTextArea);
                   }
                   jScrollPane.setViewportView(votes);
               }
           });
          this.setVisible(true);
    }
  //  public static void main(String args[]){new JMainVote();}
}
