package team.lj.UpDownFiles;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

public class updownfiles extends JFrame {
    class MyPanel extends JPanel {
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Image image = new ImageIcon("background/updownfiles.png").getImage();
            g.drawImage(image, 0, 0, this);
        }
    }
    class MyJScrollPane extends JScrollPane{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Image image = new ImageIcon("background/updownfiles.png").getImage();
            g.drawImage(image, 0, 0, this);
        }
    }

    List<String> list_filesName = new ArrayList<String>();

    private JScrollPane jScrollPane = new MyJScrollPane();
    private JSplitPane jSplitPane_top = new JSplitPane();
    private JSplitPane jSplitPane_bottom = new JSplitPane();
    private JButton jButton_up = new JButton("上传");
    private JButton jButton_down = new JButton("下载");
    private JButton jButton_F5 = new JButton("刷新");
    private  List<JCheckBox>  list_checkbox;

    private  TCPSingleClient tcpSingleClient = new TCPSingleClient();


    private void addToJpanel()
    {
        int filesLength = list_filesName.size();
        list_checkbox = new ArrayList<>();
        for(int i = 0;i < filesLength; i++){
            JCheckBox jCheckBox_tmp = new JCheckBox(list_filesName.get(i));
            System.out.println(list_filesName.get(i));
            list_checkbox.add(jCheckBox_tmp);
        }

        int l = list_checkbox.size();
        JPanel jPanel = new MyPanel();//
      //  JPanel jPanel = new JPanel();//
        jPanel.setLayout(new GridLayout(60,1));
        for(int i = 0;i < l; i++){
            jPanel.add(list_checkbox.get(i));
        }
        jPanel.revalidate();
        jScrollPane.setViewportView(jPanel);
    }

    public  updownfiles()
    {

        JFrame jFrame = new JFrame();
        jFrame.setBounds(100,100,500,600);
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.add(jSplitPane_top);
        jSplitPane_top.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPane_top.setDividerLocation(500);
        jSplitPane_top.setEnabled(false);
        jSplitPane_top.setDividerSize(1);

        jSplitPane_top.setTopComponent(jScrollPane);
        jSplitPane_top.setBottomComponent(jSplitPane_bottom);

        jSplitPane_bottom.setTopComponent(jButton_up);
        jSplitPane_bottom.setDividerSize(1);
        JPanel jPanel_tmp = new JPanel(new GridLayout(1,2));
        jPanel_tmp.add(jButton_down);
        jPanel_tmp.add(jButton_F5);

        jSplitPane_bottom.setBottomComponent(jPanel_tmp);
        jSplitPane_bottom.setDividerLocation(160);
        jSplitPane_bottom.setEnabled(false);

        jFrame.setVisible(true);
        /**
         *上传文件按钮的监听
         */
        jButton_up.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser f = new JFileChooser(); // 查找文件

                f.setCurrentDirectory(new File("."));//设置默认显示为当前文件夹
                f.setFileSelectionMode(JFileChooser.FILES_ONLY);//设置选择模式（只选文件、只选文件夹、文件和文件均可选）
                f.setMultiSelectionEnabled(true);//是否允许多选

              //  f.addChoosableFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));//文件过滤器
              //  f.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));

                int result = f.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    File[] files = f.getSelectedFiles();//获取打开的文件
                    if(files.length == 0){
                        JOptionPane.showMessageDialog(null,"上传文件不能为空","警告",JOptionPane.WARNING_MESSAGE);
                    }else{
                        try{
                            tcpSingleClient.SendFile(files);
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                    }

                }
            }
        });

        /**
         * 文件列表更新
         */
        jButton_F5.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
                  list_filesName = tcpSingleClient.getFilesNameList();
                  if(list_filesName.size() == 0){
                      JOptionPane.showMessageDialog(null,"目前没有文件可共享","提示",JOptionPane.PLAIN_MESSAGE);
                  }else{ addToJpanel(); }

          }
      });
        jButton_down.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
               int l = list_filesName.size();
               int num = 0;
               List<String> tmp = new ArrayList<>();
                /**
                 *
                 */
                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = f.showDialog(null,"保存到这");
                f.setMultiSelectionEnabled(false);//是否允许多选

                if(result == JFileChooser.APPROVE_OPTION){
                    File file = f.getSelectedFile();
                    for(int i = 0;i < l; i++)
                    {
                        if(list_checkbox.get(i).isSelected()){
                            tmp.add(list_checkbox.get(i).getText());
                            num++;
                        }
                    }
                    if(num == 0 ) {
                        JOptionPane.showMessageDialog(null,"文件不能为空","错误",JOptionPane.ERROR_MESSAGE);
                    }else{
                         tcpSingleClient.getFiles(tmp,file.getAbsolutePath());
                         JOptionPane.showMessageDialog(null,"下载成功","提示",JOptionPane.PLAIN_MESSAGE);
                    }
                }

            }
        });
    }


  //  public  static void main(String args[]){ new updownfiles(); }
}













