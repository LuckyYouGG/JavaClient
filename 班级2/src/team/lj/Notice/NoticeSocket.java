package team.lj.Notice;


import team.lj.Utils.*;
import team.lj.PersonalInfo.MyInfo;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NoticeSocket
{
   Socket socket;
   public NoticeSocket()
   {  try
      {
         socket = new Socket("10.27.211.216",7777);
         // socket = new Socket("127.0.0.1",7777);
      }catch (Exception e)
     {
       e.printStackTrace();
     }
   }


   public void sendNotice(String title,String noticeContent)
   {
      if(socket.isClosed())  new NoticeSocket();
      NoticeInfo content = new NoticeInfo();

      content.setDoWhat(1);
      content.setTitle(title);
      content.setContent(noticeContent);
      content.setSender(MyInfo.name);
      content.setDate(TimeUtil.getSysTime());

      try
      {
          ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
          objectOutputStream.writeObject(content);
          objectOutputStream.flush();
          socket.shutdownOutput();

         //读取一行数据 
          String str = null;
          while (str == null)
          {
              BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
              str = bufferedReader.readLine();

              if(str.equals("发布成功"))
              {
                 JOptionPane.showMessageDialog(null,"发布成功","提醒",JOptionPane.PLAIN_MESSAGE);
                 socket.shutdownInput();
                 socket.close();
              }
          }
       }
        catch (Exception e)
        {
           e.printStackTrace();
       }
   }

   public List getNotice()
   {
      if(socket.isClosed()) new NoticeSocket();
      List<NoticeInfo> noticeList = new ArrayList<>();
      try
      {
          NoticeInfo signal = new NoticeInfo();
          signal.setDoWhat(2);
          ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
          objectOutputStream.writeObject(signal);
          objectOutputStream.flush();
          socket.shutdownOutput();

          Object object = null;
         while(object == null)
         {
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             object = objectInputStream.readObject();
             if(object != null)
             {
                 NoticeInfo tmp = (NoticeInfo)object;
                 noticeList = tmp.getNoticeList();
                 socket.shutdownInput();
                 socket.close();
             }
         }
      }catch (Exception e)
      {
         e.printStackTrace();
      }
    //  System.out.println(noticeList.size());
      return noticeList;
   }

}
