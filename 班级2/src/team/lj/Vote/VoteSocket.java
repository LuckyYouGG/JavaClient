package team.lj.Vote;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class VoteSocket {
    Socket socket ;
    public VoteSocket()
    {
        try
        {
            socket = new Socket("10.27.211.216",5555);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendVote(VoteInfo tmp)
    {
        try
        {
            if(socket.isClosed()) socket = MakeVoteSocket.getVoteSocket();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(tmp);
            objectOutputStream.flush();
            socket.shutdownOutput();
            String str = null;
            while (str == null)
            {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                str = bufferedReader.readLine();
            }
            socket.shutdownInput();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  VoteInfo  getAllVotes()
    {
        VoteInfo tmp = null;
        try
        {
            if(socket.isClosed()) socket = MakeVoteSocket.getVoteSocket();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            VoteInfo wcnm = new VoteInfo();
            wcnm.setDoWhat(2);

            objectOutputStream.writeObject(wcnm);
            objectOutputStream.flush();
            socket.shutdownOutput();

            Object object = null;
            while (object == null)
            {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                object = objectInputStream.readObject();
            }
            tmp = (VoteInfo)object;
            socket.shutdownInput();
            socket.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
      return tmp;
    }
    public void updataVote(VoteInfo voteInfo)
    {
         try
         {
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             objectOutputStream.writeObject(voteInfo);
             objectOutputStream.flush();
             socket.shutdownOutput();
             String str = null;
             while(str == null)
             {
                 try
                 {
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     str = bufferedReader.readLine();
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
    }
}









