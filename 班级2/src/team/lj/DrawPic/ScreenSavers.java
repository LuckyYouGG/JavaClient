package team.lj.DrawPic;/*
 * ScreenSavers.java
 *
 * Created on 2007??11??3??, ????8:21
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 *
 * @author  xuqidong
 */
public class ScreenSavers extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private JComboBox comboBox;
    private JToggleButton ellipseButton;
    private JButton jButton10;
    private JButton jButton11;
    private JButton saveButton;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton colorChooserButton;
    private JButton clearButton;
    private JButton backButton;
    private ImageBoard imageBoard;
    private JCheckBox checkBox = new JCheckBox("是否填充");
	BufferedImage image;
    public ScreenSavers()
    {
    	super("即时绘图");
    	setBounds(new Rectangle(100, 50, 578, 350));
    	
    	initComponents();
        imageBoard.setType("");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        checkBox.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		imageBoard.setFull(checkBox.isSelected());
        	}
        });

        comboBox = new JComboBox();
        comboBox.addItemListener(new ItemListener() {
        	public void itemStateChanged(final ItemEvent arg0) {
        		imageBoard.setBasicStroke(Integer.parseInt(arg0.getItem().toString()));
        	}
        });
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "3", "5", "7", "9", "11"}));

    	JLabel label;
    	label = new JLabel();
    	label.setText("设置线条粗细");
    	final GroupLayout groupLayout = new GroupLayout((JComponent) getContentPane());
    	groupLayout.setHorizontalGroup(
    		groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		.addGroup(groupLayout.createSequentialGroup()
		    		.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			    		.addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
				    		.addGap(12, 12, 12)
				    		.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					    		.addComponent(ellipseButton, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					    		.addComponent(jButton2, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					    		.addComponent(jButton3, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					    		.addComponent(jButton4, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					    		.addComponent(colorChooserButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					    		.addComponent(checkBox, GroupLayout.PREFERRED_SIZE, 86, Short.MAX_VALUE)))
			    		.addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
				    		.addContainerGap()
				    		.addComponent(comboBox, 0, 86, Short.MAX_VALUE))
			    		.addGroup(groupLayout.createSequentialGroup()
				    		.addContainerGap()
				    		.addComponent(label, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
		    		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		    		.addComponent(imageBoard, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
		    		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		    		.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			    		.addComponent(clearButton, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
			    		.addComponent(saveButton, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
			    		.addComponent(jButton11, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
			    		.addComponent(jButton10, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
			    		.addComponent(backButton, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
		    		.addContainerGap())
    	);
    	groupLayout.setVerticalGroup(
    		groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		.addGroup(groupLayout.createSequentialGroup()
		    		.addGap(17, 17, 17)
		    		.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			    		.addGroup(groupLayout.createSequentialGroup()
				    		.addComponent(jButton10)
				    		.addGap(17, 17, 17)
				    		.addComponent(jButton11)
				    		.addGap(25, 25, 25)
				    		.addComponent(saveButton)
				    		.addGap(60, 60, 60)
				    		.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				    		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				    		.addComponent(clearButton))
			    		.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
				    		.addGroup(groupLayout.createSequentialGroup()
					    		.addComponent(ellipseButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					    		.addGap(14, 14, 14)
					    		.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					    		.addGap(6, 6, 6)
					    		.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					    		.addGap(6, 6, 6)
					    		.addComponent(jButton4)
					    		.addGap(14, 14, 14)
					    		.addComponent(colorChooserButton)
					    		.addGap(10, 10, 10)
					    		.addComponent(checkBox)
					    		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					    		.addComponent(label, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					    		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					    		.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				    		.addComponent(imageBoard, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)))
		    		.addGap(81, 81, 81))
    	);
    	getContentPane().setLayout(groupLayout);
    	pack();
    }
    private void initComponents() {
        imageBoard = new ImageBoard();
        ellipseButton = new JToggleButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton2 = new JButton();
        jButton10 = new JButton();
        saveButton = new JButton();
        jButton11 = new JButton();
        colorChooserButton = new JButton();
        clearButton = new JButton();
        backButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        imageBoard.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        BorderLayout jPanel1Layout = new BorderLayout();
        imageBoard.setLayout(jPanel1Layout);
        

        
        ellipseButton.setText("\u5706");
        ellipseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ellipseButtonActionPerformed(evt);
            }
        });

        jButton3.setText("\u7ebf");
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("\u94c5\u7b14");
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("\u65b9");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton10.setText("\u5f00\u59cb\u622a\u53d6");
        jButton10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        saveButton.setText("\u4fdd\u5b58");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jButton11.setText("\u622a\u53d6\u5168\u5c4f");
        jButton11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        colorChooserButton.setText("\u9009\u62e9\u989c\u8272");
        colorChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });

        clearButton.setText("\u6e05\u9664");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        backButton.setText("\u64a4\u9500");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
    }

    private void backButtonActionPerformed(ActionEvent evt) {
    	imageBoard.removeEnd();

    }

    private void fileChooserActionPerformed(ActionEvent evt) {
    	Color color = JColorChooser.showDialog(this, "选择颜色", null);
    	imageBoard.setColor(color);

    }
    private void saveButtonActionPerformed(ActionEvent evt) {

    	try{
    		if( image == null){
    			Robot rb = new Robot();
    			int x = imageBoard.getLocation().x + this.getLocation().x + 5;
    			int y = imageBoard.getLocation().y + this.getLocation().y + 30;
    			
    			image = rb.createScreenCapture(new Rectangle(x,y,imageBoard.getSize().width,
    					imageBoard.getSize().height));   			
    		}

            JFileChooser jfc=new JFileChooser(".");
            FileNameExtensionFilter JPGfilter = new FileNameExtensionFilter(
            		"jpeg,png,bmp","jpeg","jpg","png","bmp");
            jfc.addChoosableFileFilter(JPGfilter);
            
            int i=jfc.showSaveDialog(this);
            if(i==JFileChooser.APPROVE_OPTION){
                File file=jfc.getSelectedFile();
                String about="PNG";
                String ext=file.toString().toLowerCase();

                if(!ext.endsWith(".jpg") && !ext.endsWith(".png") && !ext.endsWith("bmp")&&!ext.endsWith("jpeg")){
                	String ns=ext+".jpg";
                    file=new File(ns);
                    about="JPG";
                }      
                
                if(ImageIO.write(image,about,file)){
                    JOptionPane.showMessageDialog(this,"保存成功");
                } else
                    JOptionPane.showMessageDialog(this,"保存失败");
            }
        } catch(Exception exe){
            exe.printStackTrace();
        }
    }
   

    private void jButton11ActionPerformed(ActionEvent evt) {

    	try {
			Robot rb = new Robot();
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension di = tk.getScreenSize();
			image = rb.createScreenCapture(new Rectangle(0,0,di.width,di.height));
			drawImage(image);
		} catch (AWTException e) {
			e.printStackTrace();
		}
    }

    private void jButton10ActionPerformed(ActionEvent evt) {
    	new ScreenImage(this);    	
    }

    private void clearButtonActionPerformed(ActionEvent evt) {
    	imageBoard.removeAll();
    	imageBoard.removeAllImage();
    }
    

    private void jButton4ActionPerformed(ActionEvent evt) {
    	imageBoard.setType(evt.getActionCommand());
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
    	imageBoard.setType(evt.getActionCommand());
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
    	imageBoard.setType(evt.getActionCommand());
    }

    private void ellipseButtonActionPerformed(ActionEvent evt) {
    	imageBoard.setType(evt.getActionCommand());
    }
    
    

    public void drawImage(BufferedImage get)
    {
    	try{
    	if(get!=null){
            ImageIcon ii=new ImageIcon(get);
            JLabel jl=new JLabel(ii);
            imageBoard.removeAll();
            imageBoard.add(new JScrollPane(jl));
            SwingUtilities.updateComponentTreeUI(this);
            image = get;
            saveButton.setEnabled(true);
        }
    	}catch(Exception e){
    		System.out.println(e.toString());
    	}
    }

    public static void main(String args[]) {
               new ScreenSavers().setVisible(true);                   
    }
}