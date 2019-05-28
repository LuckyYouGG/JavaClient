package team.lj.UpDownFiles;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TCPSingleClient {

    private static TcpSocketClient conn  = new TcpSocketClient();
    /**
     * 设置文件信息
     */
    private static FileSetting fileSetting = new FileSetting();
    /**
     * 启动文件工具类
     */
    private static FileUtils fileUtils = new FileUtils();
    private Socket socket = null;

    public TCPSingleClient(){
        try {
                socket =  new Socket("10.27.211.216",6666);
            }catch (Exception e){
               e.printStackTrace();
        }
    }

    /**
     * 上传文件
     * @param files
     * @throws Exception
     */
    public   void SendFile(File[] files) throws Exception {

        socket = TCPsetting.Connect();

        fileSetting.setDoWhat(2);//这个很重要！！！！
        List<String> filesNameList = new ArrayList<>();
        List<String> streams = new ArrayList<>();
        int l = files.length;
        for(int i = 0;i < l; i++){
            filesNameList.add(files[i].getName());
            streams.add(fileUtils.encryptToBase64(files[i].getAbsolutePath()));
        }
        fileSetting.setFilesNameList(filesNameList);
        fileSetting.setStreams(streams);
//-------------------------------------------------------------------------------------
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(fileSetting);
        oos.flush();
        socket.shutdownOutput();
        socket.shutdownInput();
//-------------------------------------------------------------------------------------
        JOptionPane.showMessageDialog(null,"上传成功","提示",JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * 获取文件下拉列表
     * @return
     */
    public List<String> getFilesNameList()
    {
        socket = TCPsetting.Connect();

        List<String> FilesNameList = new ArrayList<>();

        FileSetting fileSetting = new FileSetting();
        fileSetting.setDoWhat(1);

        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(fileSetting);

            objectOutputStream.flush();
            socket.shutdownOutput();//加了一个这个
            //objectOutputStream.close();//！！！！！

            Object obj = null;

            while(obj == null)
            {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                obj = objectInputStream.readObject();
                if(obj != null)
                {
                    FileSetting tmp = (FileSetting)obj;
                    FilesNameList =  tmp.getFilesNameList();
                    socket.shutdownInput();//关闭套接字的输出流//加了一个这个
              //      objectInputStream.close();//关闭对象输出流//加了一个这个
                    break;
                }
                socket.shutdownInput();
                objectInputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return FilesNameList;
    }
    /**
     * 下载文件
     */
     public void getFiles(List<String> tmp,String absPath){
        FileSetting fileSetting = new FileSetting();
        FileSetting fileSetting1 = null;

        fileSetting.setDoWhat(3);
        fileSetting.setFilesNameList(tmp);

         socket = TCPsetting.Connect();
        try{
            /**
             * 输出流
             */
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(fileSetting);
            objectOutputStream.flush();
            socket.shutdownOutput();

            Object obj = null;
            while(obj == null)
            {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                obj = objectInputStream.readObject();
                if(obj != null)
                {
                     fileSetting1 = (FileSetting) obj;
                     int l = fileSetting1.getFilesNameList().size();
                     System.out.println(l);
                     for(int i = 0 ; i < l; i++){
                         System.out.println(absPath);
                         System.out.println(fileSetting1.getFilesNameList().get(i));

                         File file = new File(absPath,fileSetting1.getFilesNameList().get(i));
                         fileUtils.decryptByBase64(fileSetting1.getStreams().get(i),file.getPath());
                     }
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
     }

}
