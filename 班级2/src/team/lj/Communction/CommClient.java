package team.lj.Communction;

import team.lj.PersonalInfo.MyInfo;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class CommClient {

    ObjectOutputStream oos;
    ObjectInputStream ois;
    private  static  Socket socket;
    public  static LinkedList<Msginfo> listMiginfo = new LinkedList<>();
    String sender;

    public CommClient(String sender)
    {
        this.sender = sender;
        try
        {
            socket = new Socket("10.27.211.216",8888);
          //  socket = new Socket("127.0.0.1",8888);
            Msginfo msginfo = new Msginfo(1);
            msginfo.setSender(sender);
            CommClient.sendMessage(msginfo);
            new getMessage().start();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public synchronized static void sendMessage(Msginfo msg)
    {
        try
        {
          //  System.out.println(msg.getStyledDocument().getLength()+"*");//
            ObjectOutputStream objectOutputStream =  new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(msg);
            objectOutputStream.flush();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    class  getMessage extends Thread
    {
        public  void run()
        {
            while(this.isAlive())
            {
                try
                {
                    ois = new ObjectInputStream(socket.getInputStream());
                    Msginfo msg = (Msginfo) ois.readObject();

                    if(msg != null)
                    {
                        if(msg.getMark() == 4)
                        {
                            MyInfo.onlines = msg.getOnlines();

                            System.out.println("adsffgg,");
                            System.out.println(msg.getOnlines().size());
                        }
                        else
                        {
                            listMiginfo.add(msg);
                            System.out.println(msg.getSender()+"%%%");
                            System.out.println(msg.getRecer()+"$$");
                        }
                    }
                    Thread.sleep(50);
                    sleep(50);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }
}
