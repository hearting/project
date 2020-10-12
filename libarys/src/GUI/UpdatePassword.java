package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import BLL.ReaderAdmin;
import BLL.adminBLL;
import Model.Admin;
import Model.Reader;

public class UpdatePassword extends JPanel implements ActionListener {
	//[start]普通读者信息控件
	String path="img\\huancun";
	String fileType=".jpg";
	static int a=0;
	private ReaderAdmin bll=new ReaderAdmin();
	private adminBLL Adbll=new adminBLL();
	static File rdPhoto;
	private JLabel imageLabel = new JLabel();
	private Font font = new Font("宋体", Font.PLAIN, 20);
	private Font font1 = new Font("宋体", Font.PLAIN, 18);
	private JLabel labels[]= {
			new JLabel("借书证号"),new JLabel("姓名"),new JLabel("密码"),
			new JLabel("性别"),new JLabel("已借书"),new JLabel("证件状态"),
			new JLabel("读者角色"),new JLabel("读者类别"),new JLabel("单位"),
			new JLabel("电话"),new JLabel("E-mail"),new JLabel("办证日期")
	};

	private JTextField IDText=new JTextField();
	private JTextField nameText1=new JTextField();
	private JTextField pwdText=new JTextField();
	private JTextField borrowedBookText=new JTextField();
	
	private JTextField statusText=new JTextField();
	private JTextField readercharText=new JTextField();
	
	private JTextField telNumText=new JTextField();
	private JTextField EmailText=new JTextField();
	private JTextField dateRegText=new JTextField();
	
	private JComboBox sexBox = new JComboBox();
	private JComboBox readerTypeBox1 = new JComboBox();
	private JComboBox deptCmbx=new JComboBox();
	private JPanel readerpanel= new JPanel();
	//[end]
	
	//[start]管理员信息控件
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
	
	JButton updateBtn ;
	//[start]初始化读者信息panel
	private void setReaderPane() {
		readerpanel.setBounds(32, 34, 499, 527);
		add(readerpanel);
		readerpanel.setBorder(new TitledBorder(null, "读者信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		readerpanel.setLayout(null);
		
		for(int i=0;i<12;i++) {
			labels[i].setFont(font);
			labels[i].setBounds(10,40*(i)+30,80,30);
			readerpanel.add(labels[i]);
		}
		imageLabel.setBounds(300,30,140,250);
		readerpanel.add(imageLabel);
		readerTypeBox1.setEnabled(false);
		
		readerTypeBox1.setFont(font1);
		
		readerTypeBox1.setModel(new DefaultComboBoxModel(bll.getAllTypeName()));
		readerTypeBox1.setBounds(130, 310, 140, 30);
		readerpanel.add(readerTypeBox1);
		sexBox.setEnabled(false);
		
		sexBox.setFont(font1);
		sexBox.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		sexBox.setBounds(130, 150, 140, 30);
		readerpanel.add(sexBox);
		IDText.setEditable(false);
		
		IDText.setFont(font1);
		IDText.setBounds(130,30,140,30);
		readerpanel.add(IDText);
		nameText1.setEditable(false);
		
		
		nameText1.setFont(font1);
		nameText1.setBounds(130,70,140,30);
		readerpanel.add(nameText1);
		
		pwdText.setFont(font1);
		pwdText.setBounds(130,110,140,30);
		readerpanel.add(pwdText);
		borrowedBookText.setEditable(false);
		
		borrowedBookText.setFont(font1);
		borrowedBookText.setBounds(130,190,140,30);
		readerpanel.add(borrowedBookText);
		statusText.setEditable(false);
		
		statusText.setFont(font1);
		statusText.setBounds(130,230,140,30);
		readerpanel.add(statusText);
		readercharText.setEditable(false);
		
		
		
		readercharText.setFont(font1);
		readercharText.setBounds(130,270,140,30);
		readerpanel.add(readercharText);
		deptCmbx.setEnabled(false);
		
		deptCmbx.setModel(new DefaultComboBoxModel(new String[] {"计算机科学学院", "文学院", "教育与体育学院", "城市建设学院","电子信息学院","外国语学院","化学与环境工程学院","艺术学院","农学院"}));
		deptCmbx.setFont(font1);
		deptCmbx.setBounds(130,350,180,30);
		readerpanel.add(deptCmbx);
		telNumText.setEditable(false);
		telNumText.setEnabled(false);
		
		telNumText.setFont(font1);
		telNumText.setBounds(130,390,140,30);
		readerpanel.add(telNumText);
		EmailText.setEnabled(false);
		EmailText.setEditable(false);
		
		EmailText.setFont(font1);
		EmailText.setBounds(130,430,140,30);
		readerpanel.add(EmailText);
		dateRegText.setEditable(false);
		
		dateRegText.setFont(font1);
		dateRegText.setBounds(130,470,140,30);
		readerpanel.add(dateRegText);
		updateBtn = new JButton("确定修改");
		updateBtn.setBounds(176, 594, 147, 43);
		add(updateBtn);
		updateBtn.addActionListener(this);
		
	}
	//[end]
	
	//[start]初始化管理员信息panel
	private void setAdmin() {
		Adminpanel.setBorder(new TitledBorder(null, "读者信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Adminpanel.setBounds(32, 34, 499, 527);
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
	
	private void setPermission() {
		if(Login.rd!=null) {
			Adminpanel.setVisible(false);
			readerpanel.setVisible(true);
		}
		else {
			readerpanel.setVisible(false);
			//Adminpanel.setVisible(true);
		}
	}
	//[end]
	
	public UpdatePassword() {
		setLayout(null);
		setBounds(4, 4, 649, 648);
		setBorder(new TitledBorder(null, "用户信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setPermission();
		setReaderPane();
		 setAdmin();
		if(Login.rd!=null) {
		Reader rd= bll.getRdByID(Login.rd.getRdID());
		IDText.setText(String.valueOf(rd.getRdID()));
		nameText1.setText(rd.getRdName());
		pwdText.setText(rd.getRdPwd());
		borrowedBookText.setText(String.valueOf(rd.getRdBorrowQty()));
		statusText.setText(rd.getRdStatus());
		telNumText.setText(rd.getRdPhone());
		EmailText.setText(rd.getRdEMail());
		dateRegText.setText(String.valueOf(rd.getRdDatereg()));
		sexBox.setSelectedItem(rd.getRdSex());
		deptCmbx.setSelectedItem(rd.getRdDept());
		readerTypeBox1.setSelectedItem(rd.getRdType());
		try {
			rdPhoto=ByteToFile(rd.getRdPhoto());
			imageLabel.setBounds(300,30,140,250);
			readerpanel.add(imageLabel);
			if(rdPhoto!=null) {
				ImageIcon icon=new ImageIcon(rdPhoto.getPath());  
				imageLabel.setIcon(icon); 
		    }  
			else {
				imageLabel.setIcon(null); 
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		}
		
		if(Login.admin!=null) {
			Admin ad=Login.admin;
			AdnameText.setText(ad.getAdminName());
			AdIDText.setText(String.valueOf(ad.getAdminID()));
			AdPwdText.setText(ad.getAdminPwd());
			AdEmailText.setText(ad.getAdminEmail());
			AdPhoneText.setText(ad.getAdminPhone());
			AdDateRegText.setText(String.valueOf(ad.getDateReg()));
		}
	}
	
	
	public void setIcon() {
		readerpanel.add(imageLabel);
		ImageIcon icon=new ImageIcon(path+a+fileType);  
	    imageLabel.setIcon(icon);   
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==updateBtn) {
			if(Login.rd!=null) {
			String newPwd=pwdText.getText();
			int id=Integer.parseInt(IDText.getText());
			bll.updateRdPwd(newPwd,id);
			JOptionPane.showMessageDialog(null, "密码修改成功", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			}
			else if(Login.admin!=null) {
				String newPwd=AdPwdText.getText();
				int id=Integer.parseInt(AdIDText.getText());
				Adbll.updateRdPwd(newPwd,id);
				JOptionPane.showMessageDialog(null, "密码修改成功", "提示", JOptionPane.YES_NO_CANCEL_OPTION);	
			}
		} 
	}	
	
	public File ByteToFil(byte[] bytes)throws Exception{ 
		File f=new File(path+a+fileType);
		f.delete();
		a++;
		File file=new File(path+a+fileType);
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);   
        BufferedImage bi1 =ImageIO.read(bais); 
        try {  
        	System.err.println(bytes.length);
            ImageIO.write(bi1, "jpg", file);//不管输出什么格式图片，此处不需改动   
            
        } catch (IOException e) {   
            e.printStackTrace();   
        }finally{
        	
        	bais.close();
        	return file;
        }
		
    }  

	public File ByteToFile(byte[] bytes)throws Exception{ 
		if(bytes!=null) {
		delAllFile("img");
		a++;
		File file=new File(path+a+fileType);
		InputStream in = new ByteArrayInputStream(bytes); 
        FileOutputStream fos = null;
        try {  
            fos = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[9102];
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            in.close();
            fos.flush();
            fos.close();
        } catch (IOException e) {   
            e.printStackTrace();   
        }
       	return file; }
		return null;
        
    }  
	
	public  boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				flag = true;
			}
		}
		return flag;
	}
}
