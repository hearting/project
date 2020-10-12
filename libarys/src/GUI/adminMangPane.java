package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class adminMangPane extends JPanel {
	//[start]管理员信息控件
	private Font font = new Font("宋体", Font.PLAIN, 20);
	private Font font1 = new Font("宋体", Font.PLAIN, 18);
	private JLabel Adlabels[]= {
			new JLabel("ID"),new JLabel("姓名"),new JLabel("密码"),
			new JLabel("E-mail"),new JLabel("电话"),new JLabel("办证日期")
	};
	private JPanel Adminpanel= new JPanel();
	private JTextField AdnameText=new JTextField();
	private JTextField AdIDText=new JTextField();
	private JTextField AdEmailText=new JTextField();
	private JTextField AdPhoneText=new JTextField();
	private JTextField AdPwdText=new JTextField();
	private JTextField AdDateRegText=new JTextField();
	//[end]
	
	//[start]初始化管理员信息panel
		private void setAdmin() {
			Adminpanel.setBorder(new TitledBorder(null, "读者信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			Adminpanel.setBounds(80, 80, 499, 550);
			add(Adminpanel);
			Adminpanel.setLayout(null);
			
			AdIDText.setFont(font1);
			AdIDText.setBounds(130,70,140,30);
			Adminpanel.add(AdIDText);
			AdIDText.setEditable(false);
			
			AdnameText.setFont(font1);
			AdnameText.setBounds(130,110,140,30);
			Adminpanel.add(AdnameText);
			AdnameText.setEditable(false);

			AdPwdText.setFont(font1);
			AdPwdText.setBounds(130,150,140,30);
			Adminpanel.add(AdPwdText);
			
			AdEmailText.setFont(font1);
			AdEmailText.setBounds(130,190,140,30);
			Adminpanel.add(AdEmailText);
			AdEmailText.setEditable(false);
			
			AdPhoneText.setFont(font1);
			AdPhoneText.setBounds(130,230,140,30);
			Adminpanel.add(AdPhoneText);
			AdPhoneText.setEditable(false);
			
			AdDateRegText.setFont(font1);
			AdDateRegText.setBounds(130,270,140,30);
			Adminpanel.add(AdDateRegText);
			AdDateRegText.setEditable(false);
			
			for(int i=0;i<6;i++) {
				Adlabels[i].setFont(font);
				Adlabels[i].setBounds(10,40*(i)+70,80,30);
				Adminpanel.add(Adlabels[i]);
			}
		}
	public adminMangPane() {
		setLayout(null);
		setBounds(4, 4, 1376, 730);
		setBorder(new TitledBorder(null, "图书管理员管理", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setAdmin();
	
	
	
	}

}
