package team.lj.Log;

import javax.swing.*;
import java.awt.*;

public class JLog extends JFrame {

   public JLog()
   {
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       this.setBounds(100,100,350,400);

       JPanel jPanel = new JPanel(new GridLayout(10,1));
       LogInfo logInfo = new LogClient().getLog();
       if(logInfo == null || logInfo.getList().size() == 0)
       {
           JOptionPane.showMessageDialog(null,
                   "亲，还没有日志记录哦","提醒",JOptionPane.PLAIN_MESSAGE);
       }else
           {
           int length = logInfo.getList().size();
           for(int i = 0;i < length; i++)
           {
               JTextArea tmp = new JTextArea(logInfo.getList().get(i).getName()+" " +
                       logInfo.getList().get(i).getTime()+" "+ logInfo.getList().get(i).getDone());
               jPanel.add(tmp);
           }
           this.add(jPanel);

           this.setVisible(true);
       }

   }
}
