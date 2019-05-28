package team.lj.JFrame;

import team.lj.PersonalInfo.PersonalInfo;

import java.io.*;
import java.net.Socket;

public class ManageInfoSocket
{
    Socket socket;
    public ManageInfoSocket()
    {
        try
        {
        //    socket = new Socket("10.27.211.216",9999);
              socket = new Socket("127.0.0.1",9999);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public  int  sendInfo( PersonalInfo personalInfo)//注册信息 或者修改某人(自己或者他人(管理员)信息
    {
        if(personalInfo.getDoWhat() != 2)
         personalInfo.setDoWhat(1);
        String result = null;
        try
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(personalInfo);
            objectOutputStream.flush();
            socket.shutdownOutput();
            while(result == null){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                result = bufferedReader.readLine();
                System.out.println(result);
            }
           // System.out.println(result);
            socket.shutdownInput();
            socket.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        if(result == "注册成功" || result == "修改成功") return 1;
        else return -1;
    }

    public  int checkInfo(PersonalInfo personalInfo)//登陆检查
    {
        personalInfo.setDoWhat(4);
        String str = null;
        try
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(personalInfo);
            objectOutputStream.flush();
            socket.shutdownOutput();
            while(str == null)
            {
                try
                {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    str = bufferedReader.readLine();
                    System.out.println(str+"88");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            socket.shutdownInput();
            socket.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(str);

        if(str .equals("账号密码正确")) return 1;else return -1;
    }

    /**
     * try {
     *   ...
     * } catch (){
     *   ...
     * } finally {
     *   if (fis!=null) fis.close();
     *   if (os!=null) os.close();
     * }
     * @param name
     * @return
     */

    public PersonalInfo getInfo(String name)//获得某个人的信息
    {
          PersonalInfo personalInfo = new PersonalInfo();
          PersonalInfo personalInfo1 = null;
          personalInfo.setName(name);
          personalInfo.setDoWhat(3);
          try
          {
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             objectOutputStream.writeObject(personalInfo);
             objectOutputStream.flush();
             socket.shutdownOutput();

             while(personalInfo1 == null)
             {
                 try
                 {
                     ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                     personalInfo1 = (PersonalInfo) objectInputStream.readObject();
                 }catch (Exception e)
                 {
                     e.printStackTrace();
                 }
             }
             socket.shutdownInput();
             socket.close();
          }catch (Exception e)
          {
              e.printStackTrace();
          }
          return personalInfo1;
    }

}
