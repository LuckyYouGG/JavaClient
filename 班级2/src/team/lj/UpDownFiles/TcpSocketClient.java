package team.lj.UpDownFiles;

import java.io.IOException;
import java.net.Socket;


public class TcpSocketClient {

    public  Socket ConnectTcpClient(){
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(TCPsetting.conAddr,TCPsetting.port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientSocket;
    }
    public  void Close(Socket socket){
        try {
            socket.close();
            System.out.println("关闭成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
