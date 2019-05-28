package team.lj.Log;

import team.lj.Tetris.S;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LogClient {
    Socket socket;
    public LogClient()
    {
        try
        {
            socket = new Socket("10.27.211.216",10001);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public LogInfo getLog()
    {
         LogInfo logInfo = null;
         try
         {
             LogInfo tmp = new LogInfo();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             objectOutputStream.writeObject(tmp);
             objectOutputStream.flush();
             socket.shutdownOutput();
             while (logInfo == null)
             {
                 ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                 logInfo = (LogInfo) objectInputStream.readObject();
             }
             socket.shutdownInput();
             socket.close();
         }catch (Exception e)
         {
             e.printStackTrace();
         }
         return logInfo;
    }
}
