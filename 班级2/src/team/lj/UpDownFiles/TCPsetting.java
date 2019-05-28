package team.lj.UpDownFiles;

import java.net.Socket;
import java.sql.Connection;

public class TCPsetting {
    /**
     * 设置上传服务器
     */
    public final static String conAddr = "10.27.211.216";
    /**
     * 设置要上传的端口
     */
    public final static int port = 6666;

    private static  Socket socket;

    public  static Socket Connect()
    {
        try{
            socket = new Socket(conAddr,port);
        }catch (Exception e){
            e.printStackTrace();
        }
        return socket;
    }
}
