package team.lj.Vote;

import team.lj.PersonalInfo.MyInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateVote extends JFrame
{
    JPanel jPanel = new JPanel(new GridLayout(11,1));
    List<JCheckBox> jCheckBoxes = new ArrayList<>();
    VoteInfo voteInfo ;
    private int selected = 0;
  //  public static void main(String args[]){//  new UpdateVote();}
    public UpdateVote(VoteInfo voteInfo)
    {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("有新投票");
        this.setBounds(100,100,400,500);
        this.add(jPanel);
        JButton jButton = new JButton("投票");
        JToolBar jToolBar = new JToolBar();
        jToolBar.add(jButton);
        jPanel.add(jToolBar,BorderLayout.BEFORE_FIRST_LINE);
        this.voteInfo = voteInfo;
        jPanel.add(new JLabel("标题："+ voteInfo.getTitle()));
        addOptions();
        jPanel.add(new JLabel("发布时间："+
                voteInfo.getTime() + " 发布者：" + voteInfo.getSender()));

     //    jPanel.add(jButton);
        jButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                VoteInfo tmp = new VoteInfo();
                int bj = 1;
                 for(int i = 0;i < jCheckBoxes.size(); i++)
                 {
                     if(jCheckBoxes.get(i).isSelected())
                     {
                         tmp.getNums().add("1");
                          bj = 0;
                     }

                     else
                           tmp.getNums().add("0");
                 }
                 if(bj ==  1){
                     JOptionPane.showMessageDialog(null,"至少选择一项","警告",JOptionPane.ERROR_MESSAGE);
                 }else {
                     tmp.setRank(voteInfo.getRank());
                     tmp.setDoWhat(3);
                     tmp.setSender(MyInfo.name);
                     new VoteSocket().updataVote(tmp);
                     JOptionPane.showMessageDialog(null,"投票成功","提示",JOptionPane.PLAIN_MESSAGE);
                     UpdateVote.this.setVisible(false);
                 }

            }
        });

        this.setVisible(true);
    }
    private void addOptions()
    {
        List<String> options = voteInfo.getOptions();
        List<String> nums = voteInfo.getNums();
        int length = options.size();
        for(int i = 0;i < length; i++)
        {
            JCheckBox jCheckbox = new JCheckBox(options.get(i)+" 票数" + nums.get(i));
            jCheckbox.addItemListener(new ItemListener()
            {
                @Override
                public void itemStateChanged(ItemEvent e)
                {
                    if(jCheckbox.isSelected())
                    {
                        selected++;
                        if (selected > voteInfo.getMaxSelected())
                        {
                            JOptionPane.showMessageDialog(null,
                                "最多只能选"+voteInfo.getMaxSelected()+"项","警告",JOptionPane.ERROR_MESSAGE);

                            jCheckbox.setSelected(false);
                        }
                    }
                    else selected--;
                }
            });
            jPanel.add(jCheckbox);
            jCheckBoxes.add(jCheckbox);
        }
    }
}
