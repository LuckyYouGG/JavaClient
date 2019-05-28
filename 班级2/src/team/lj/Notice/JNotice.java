package team.lj.Notice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JNotice extends JFrame {
    JPanel jPanel = new JPanel();
    JButton jButton = new JButton("发布公告");
    JTextArea jTextArea = new JTextArea();
    public JNotice()
    {
     this.setBounds(100,100,400,400);
     this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
     this.add(jPanel);
     jPanel.setLayout(null);


     JTextField jTextField = new JTextField();
     jTextField.addFocusListener(new JTextFieldHintListener(jTextField,"请在这里写标题(不超过45个字)"));
     jTextField.setBounds(72,1,210,50);

     JLabel jLabel = new JLabel("  标题：");
     jLabel.setBounds(0,0,50,50);

     jButton.setBounds(283,1,100,50);
     jTextArea.setBounds(0,50,400,350);
     jTextArea.addFocusListener(new JTextAreaHintListener(jTextArea,"请在这里写公告内容(不超过255个字)"));

     jPanel.add(jLabel);
     jPanel.add(jTextField);
     jPanel.add(jButton);
     jPanel.add(jTextArea);
     jTextArea.setAutoscrolls(true);
     this.setVisible(true);


     jButton.addActionListener(new ActionListener()
     {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            NoticeSocket socket = new NoticeSocket();
            socket.sendNotice(jTextField.getText(),jTextArea.getText());
            jTextField.setText("");
            jTextArea.setText("");
         }
     });
    }


}












