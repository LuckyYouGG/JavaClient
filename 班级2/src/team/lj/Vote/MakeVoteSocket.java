package team.lj.Vote;

import java.net.Socket;

public class MakeVoteSocket
{
    public static Socket getVoteSocket()
    {
        Socket socket = null;
        try
        {
            socket = new Socket("10.27.211.216",5555);
        }catch (Exception e){
            e.printStackTrace();
        }
        return socket;
    }
}
