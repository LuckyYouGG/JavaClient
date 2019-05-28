package team.lj.JFrame;


import team.lj.PersonalInfo.PersonalInfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.*;
public class  register extends JFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	JRadioButton rdbtnNewRadioButton;
	JRadioButton rdbtnNewRadioButton_1;
	public register()
	{
	//	frame = new JFrame();
		this.setBounds(100, 100, 468, 541);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		//--------------------------------------
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(10, 27, 183, 68);
		this.getContentPane().add(verticalBox);

		{
			ButtonGroup buttonGroup1 = new ButtonGroup();
			JLabel jLabel = new JLabel();
			jLabel.setText("1:是否为班长?");
			jLabel.setPreferredSize(new Dimension(30,10));
			verticalBox.add(jLabel);

			 rdbtnNewRadioButton = new JRadioButton("是");
			 rdbtnNewRadioButton_1 = new JRadioButton("否");
			rdbtnNewRadioButton_1.setSelected(true);

			buttonGroup1.add(rdbtnNewRadioButton);
			buttonGroup1.add(rdbtnNewRadioButton_1);

			verticalBox.add(rdbtnNewRadioButton);
			verticalBox.add(rdbtnNewRadioButton_1);
		}


		{
			Box verticalBox_1 = Box.createVerticalBox();
			verticalBox_1.setBounds(10, 216, 220, 43);
			this.getContentPane().add(verticalBox_1);
			Box verticalBox_3 = Box.createVerticalBox();
			verticalBox_1.add(verticalBox_3);

			JLabel label_1 = new JLabel("4:学号?");
			verticalBox_1.add(label_1);

			textField = new JTextField();
			textField.setColumns(10);
			verticalBox_1.add(textField);

		}

		{
			Box verticalBox_2 = Box.createVerticalBox();
			verticalBox_2.setBounds(10, 158, 202, 48);
			this.getContentPane().add(verticalBox_2);

			JLabel label_2 = new JLabel("3:家乡");
			verticalBox_2.add(label_2);

			textField_1 = new JTextField();
			verticalBox_2.add(textField_1);
			textField_1.setColumns(10);
		}

		

		{
			Box verticalBox_4 = Box.createVerticalBox();
			verticalBox_4.setBounds(10, 269, 202, 43);
			this.getContentPane().add(verticalBox_4);

			JLabel lblNewLabel = new JLabel("5:登陆密码(请牢记)");
			verticalBox_4.add(lblNewLabel);

			passwordField = new JPasswordField();
			verticalBox_4.add(passwordField);
		}

		{
			Box verticalBox_5 = Box.createVerticalBox();
			verticalBox_5.setBounds(10, 322, 202, 43);
			this.getContentPane().add(verticalBox_5);

			JLabel label_3 = new JLabel("6:确认密码");
			verticalBox_5.add(label_3);

			passwordField_1 = new JPasswordField();
			verticalBox_5.add(passwordField_1);
		}
		{
			Box verticalBox_6 = Box.createVerticalBox();
			verticalBox_6.setBounds(10, 105, 220, 43);
			this.getContentPane().add(verticalBox_6);

			JLabel label_4 = new JLabel("2：真实姓名(将作为您的登陆账号)");
			verticalBox_6.add(label_4);

			textField_4 = new JTextField();
			textField_4.setColumns(10);
			verticalBox_6.add(textField_4);
		}
		{
			JButton btnNewButto = new JButton("点击提交注册");
			//--------------------------------------------------------------
			btnNewButto.addActionListener(new ActionListener()
            {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if(textField.getText().equals(""))//判断学号是否为空
					{
						JOptionPane.showMessageDialog(null,"学号不能为空","错误",JOptionPane.ERROR_MESSAGE);
					}else
                    //判断密码是否一样
					if(passwordField.getPassword().toString().equals( passwordField_1.getPassword().toString()))
					{
						JOptionPane.showMessageDialog(null,"两次输入的密码不一致","错误",JOptionPane.ERROR_MESSAGE);
						passwordField_1.setText("");
					}else
                    if(textField_1.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null,"家乡不能为空","错误",JOptionPane.ERROR_MESSAGE);
					}else
                    if(textField_4.getText().equals("")){
						JOptionPane.showMessageDialog(null,"姓名不能为空","错误",JOptionPane.ERROR_MESSAGE);
					}else
					{
						PersonalInfo personalInfo = new PersonalInfo();
						personalInfo.setName(textField_4.getText());
						personalInfo.setFromWhere(textField_1.getText());
						personalInfo.setPsw(String.valueOf(passwordField.getPassword()));
						personalInfo.setStudentID(textField.getText());

						if (rdbtnNewRadioButton.isSelected())
						   personalInfo.setIsNB("2");
						else personalInfo.setIsNB("0");
						personalInfo.setDoWhat(1);
                        ManageInfoSocket manageInfoSocket = new ManageInfoSocket();
                        manageInfoSocket.sendInfo(personalInfo);

						JOptionPane.showMessageDialog(null,"注册成功","",JOptionPane.PLAIN_MESSAGE);
						register.this.setVisible(false);

					}
				}
			});
			//-------------------------------------------------------------
			btnNewButto.setBounds(10, 409, 141, 57);
			this.getContentPane().add(btnNewButto);
		}
		this.setVisible(true);
	}

}
