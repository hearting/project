package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.*;

import BLL.ReaderAdmin;
import BLL.adminBLL;
import Model.Admin;
import Model.Reader;
import java.awt.event.KeyAdapter;
public class Login extends JFrame implements ActionListener{
	private adminBLL bll=new adminBLL();
	private ReaderAdmin rdBll=new ReaderAdmin();
	//public String type;
	public static Admin admin;
	public static Reader rd;
	private JPasswordField pwdField;
	private JTextField usnameField;
	private JLabel usnameLabel;
	private JLabel pwdLabel;
	private JLabel typeLabel;
	private JButton loginBut;
	private JButton exitBut;
	private JRadioButton tr;
	private JRadioButton ta;
	public Login(){
		setTitle("长江大学图书馆管理信息系统");
		Font font = new Font("宋体", Font.BOLD, 20);
		JPanel pane;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 600, 450);
		pane = (JPanel) getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pane.setLayout(null);
		
		usnameLabel = new JLabel("用户名");
		usnameLabel.setFont(new Font("华文楷体", Font.PLAIN, 20));
		usnameLabel.setBounds(120, 60, 80, 40);
		pane.add(usnameLabel);
		usnameField = new JTextField();
		// 添加按键监听
		usnameField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					int keyChar=e.getKeyChar();
					if (keyChar<KeyEvent.VK_0 || keyChar>KeyEvent.VK_9) {
						e.consume();
					} 
				}
			@Override
			public void keyReleased(KeyEvent e) {
				String txt=usnameField.getText();
				StringBuffer text=new StringBuffer("");
				System.out.print("123456");
				for(int i=0;i<txt.length();i++) {
					if(txt.charAt(i)>='0'&&txt.charAt(i)<='9') {
						text.append(txt.charAt(i));
					}
				}
				usnameField.setText(text.toString());
			}
			});
			
		usnameField.setColumns(10);
		//usnameField.setText("2017001");
		usnameField.setFont(font);
		usnameField.setBounds(240, 60, 174, 40);
		pane.add(usnameField);
		
			

		pwdLabel = new JLabel("密码");
		pwdLabel.setFont(new Font("华文楷体", Font.PLAIN, 20));
		pwdLabel.setBounds(120, 140, 80, 40);
		pane.add(pwdLabel);

		pwdField = new JPasswordField();
		//pwdField.setText("123456");
		pwdField.setEchoChar('*');
		pwdField.setFont(font);
		pwdField.setBounds(240, 140, 174, 40);
		pane.add(pwdField);

		loginBut = new JButton("登录");
		loginBut.setFont(font);
		loginBut.setBounds(185, 313, 100, 30);
		pane.add(loginBut);
		loginBut.addActionListener(this);

		exitBut = new JButton("退出");
		exitBut.setFont(font);
		exitBut.setBounds(343, 313, 100, 30);
		exitBut.addActionListener(this);
		pane.add(exitBut);
		
		typeLabel=new JLabel("用户类型");
		typeLabel.setFont(new Font("华文宋体", Font.PLAIN, 15));
		typeLabel.setBounds(80, 200,100, 40);
		pane.add(typeLabel);
		this.setVisible(true); 	
		
		tr=new JRadioButton("管理员");
		ta=new JRadioButton("读者");
		tr.setBounds(220, 200, 100, 40);
		ta.setBounds(350,200, 100, 40);
		pane.add(tr);
		pane.add(ta);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//String type=typeBox.getSelectedItem().toString();
		if(e.getSource()==exitBut) {
			this.dispose();
		}
		else if(e.getSource()==loginBut) {
			 {
				 if(ta.isSelected()==false){
					int id=Integer.parseInt(usnameField.getText());
					try {
						admin=bll.getAdminByID(id);
						if(admin!=null && admin.getAdminPwd().equals(pwdField.getText())) {
							JOptionPane.showMessageDialog(null, "登录成功", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
							this.setVisible(false);
							rd=null;
							Main m=new Main();
							m.setResizable(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "用户名或密码错误", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					break;
				}
				 if(tr.isSelected()==false) {
					int id=Integer.parseInt(usnameField.getText());
					rd=rdBll.getRdByID(id);
					if(rd!=null && rd.getRdPwd().equals(pwdField.getText())) {
						JOptionPane.showMessageDialog(null, "登录成功", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
						admin=null;
						this.setVisible(false);
						new Main();
					}
					else {
						JOptionPane.showMessageDialog(null, "用户名或密码错误", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
						
					}
//					break;
				}
			}
		}
	
	}

	
}
