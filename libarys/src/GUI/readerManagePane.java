package GUI;



import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import BLL.ReaderAdmin;
import Model.Reader;


public class readerManagePane extends JPanel  implements ActionListener{
	String path="img\\huancun";
	String fileType=".jpg";
	static int a=0;
	private ReaderAdmin bll=new ReaderAdmin();
	static File rdPhoto;
	private JLabel imageLabel = new JLabel();
	private Font font = new Font("宋体", Font.PLAIN, 20);
	private Font font1 = new Font("宋体", Font.PLAIN, 18);
	JButton selectBtn = new JButton("查找");
	
	private JLabel labels[]= {
			new JLabel("借书证号"),new JLabel("姓名"),new JLabel("密码"),
			new JLabel("性别"),new JLabel("已借书"),new JLabel("证件状态"),
			new JLabel("读者角色"),new JLabel("读者类别"),new JLabel("单位"),
			new JLabel("电话"),new JLabel("E-mail"),new JLabel("办证日期")
	};
	private JLabel ReaderTypeLabel = new JLabel("读者类别");
	private JLabel departmentLabel = new JLabel("单位");
	private JLabel nameLabel = new JLabel("姓名");
	
	private JComboBox readerTypeBox = new JComboBox();
	private JComboBox departmentTypeBox = new JComboBox();
	private JTextField nameText=new JTextField();
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
	
	
	private JTable resutlTable;
	private JPanel readerpanel;
	
	
	private JButton imageLoad=new JButton("图片文件");
	private JButton newReaderBtn=new JButton("办理借书证");
	private JButton changeReaderInfoBtn=new JButton("变更信息");
	private JButton LostBtn=new JButton("挂失");
	private JButton RelLostBtn=new JButton("解除挂失");
	private JButton CancelBtn=new JButton("注销");
	private JButton exitBtn=new JButton("退出");
	private JButton enterNewReaderBtn=new JButton("确认办证");
	private JButton enterChangeBtn=new JButton("确认变更");
	private JButton replaceBtn=new JButton("补办证件");
	//[start]设置读者信息panel
	private void setReaderPane() {
		readerpanel = new JPanel();
		readerpanel.setBorder(new TitledBorder(null, "读者信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		readerpanel.setBounds(810, 80, 483, 544);
		add(readerpanel);
		readerpanel.setLayout(null);
		
		for(int i=0;i<12;i++) {
			labels[i].setFont(font);
			labels[i].setBounds(10,40*(i)+30,80,30);
			readerpanel.add(labels[i]);
		}
		imageLabel.setBounds(280,30,193,250);
		readerpanel.add(imageLabel);
		
		
		imageLoad.setFont(new Font("华文楷体", Font.PLAIN, 15));
		imageLoad.setBounds(300, 290, 140, 30);
		readerpanel.add(imageLoad);
		imageLoad.addActionListener(this);
		
		readerTypeBox1.setFont(new Font("华文细黑", Font.PLAIN, 18));
		
		readerTypeBox1.setModel(new DefaultComboBoxModel(bll.getAllTypeName()));
		readerTypeBox1.setBounds(130, 310, 140, 30);
		readerpanel.add(readerTypeBox1);
		
		sexBox.setFont(new Font("华文细黑", Font.PLAIN, 18));
		sexBox.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		sexBox.setBounds(130, 150, 140, 30);
		readerpanel.add(sexBox);
		IDText.setEditable(false);
		
		IDText.setFont(font1);
		IDText.setBounds(130,30,140,30);
		readerpanel.add(IDText);
		
		
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
		
		deptCmbx.setModel(new DefaultComboBoxModel(new String[] {"计算机科学学院", "文学院", "教育与体育学院", "城市建设学院","电子信息学院","外国语学院","化学与环境工程学院","艺术学院","农学院"}));
		deptCmbx.setFont(new Font("华文细黑", Font.PLAIN, 18));
		deptCmbx.setBounds(130,350,180,30);
		readerpanel.add(deptCmbx);
		
		telNumText.setFont(font1);
		telNumText.setBounds(130,390,140,30);
		readerpanel.add(telNumText);
		
		EmailText.setFont(font1);
		EmailText.setBounds(130,430,140,30);
		readerpanel.add(EmailText);
		dateRegText.setEditable(false);
		
		dateRegText.setFont(font1);
		dateRegText.setBounds(130,470,140,30);
		readerpanel.add(dateRegText);
		
	}
	//[end]
	
	
	//[start]设置查询结果panel和tabel
	private void setResutlPane() {
		
		
		DefaultTableModel model = new DefaultTableModel(new Object[][] {
		},
		new String[] {
			"ID", "姓名","性别", "类型", "院系","电话","E-mail","状态","已借书","注册日期"});
	    
		resutlTable=new JTable();
		resutlTable.setModel(model);
		resutlTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		resutlTable.getTableHeader().setReorderingAllowed(false);
		resutlTable.setFont(new Font("宋体", Font.PLAIN, 12));
		resutlTable.setRowHeight(35);
		resutlTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		resutlTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		resutlTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		resutlTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		resutlTable.getColumnModel().getColumn(4).setPreferredWidth(130);
		resutlTable.getColumnModel().getColumn(5).setPreferredWidth(100);
		resutlTable.getColumnModel().getColumn(6).setPreferredWidth(100);
		resutlTable.getColumnModel().getColumn(7).setPreferredWidth(40);
		resutlTable.getColumnModel().getColumn(8).setPreferredWidth(50);
		resutlTable.getColumnModel().getColumn(9).setPreferredWidth(90);
		 resutlTable.addMouseListener(new MouseAdapter() {/////表格控件事件
			 	@Override
			 	public void mouseClicked(MouseEvent e) {
			 		int count=resutlTable.getSelectedRow();//获取你选中的行号（记录）
			 		int id =	Integer.parseInt(resutlTable.getValueAt(count, 0).toString());
			 		
						Reader rd= bll.getRdByID(id);
						IDText.setText(String.valueOf(rd.getRdID()));
						nameText1.setText(rd.getRdName());
						pwdText.setText(rd.getRdPwd());
						borrowedBookText.setText(String.valueOf(rd.getRdBorrowQty()));
						statusText.setText(rd.getRdStatus());
						telNumText.setText(rd.getRdPhone());
						EmailText.setText(rd.getRdEMail());
						dateRegText.setText(String.valueOf(rd.getRdDatereg()));
						sexBox.setSelectedItem(rd.getRdSex());
						deptCmbx.setSelectedItem((resutlTable.getValueAt(count, 4).toString()).trim());
						readerTypeBox1.setSelectedItem((resutlTable.getValueAt(count, 3).toString()).trim());
						
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
			 });
		
		
		 JScrollPane resultPane = new JScrollPane(resutlTable);
		 resultPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "查询信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		 resultPane.setBounds(10, 80, 780, 550);
		add(resultPane);
	}
	//[end]
	
	
	
	public readerManagePane() {
		setResutlPane();
		setReaderPane();	
		//[start] 设置查询部分控件
		setLayout(null);
		setBounds(4, 4, 1376, 730);
		setBorder(new TitledBorder(null, "借书证管理", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		readerTypeBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String rdType=readerTypeBox.getSelectedItem().toString();
				String rdDept=departmentTypeBox.getSelectedItem().toString();
				Reader[] rds;
				try {
					if(rdDept.equals("全部")) {
						rds=bll.getReaderByTypeName(rdType);
					}
					else if(rdType.equals("全部")) {
						rds = bll.getReaderByDept(rdDept);
					}
					else {
					rds = bll.getReaderByDeptAndTypeName(rdDept,rdType);
					}
					setReult(rds);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		readerTypeBox.setFont(font);
		String[] type =bll.getAllTypeName();
		String[] NewType=new String[type.length+1];
		for(int i=0;i<type.length;i++) {
			NewType[i+1]=type[i];
		}
		NewType[0]="全部";
		readerTypeBox.setModel(new DefaultComboBoxModel(NewType));
		readerTypeBox.setBounds(130, 25, 140, 40);
		add(readerTypeBox);
		departmentTypeBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String rdDept=departmentTypeBox.getSelectedItem().toString();
				String rdType=readerTypeBox.getSelectedItem().toString();
				try {
					Reader[] rds;
					try {
						if(rdType.equals("全部")) {
							rds = bll.getReaderByDept(rdDept);
						}
						else if(rdDept.equals("全部")) {
							rds = bll.getReaderByTypeName(rdType);
						}
						else {
						rds = bll.getReaderByDeptAndTypeName(rdDept,rdType);
						}
						setReult(rds);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		departmentTypeBox.setFont(new Font("华文楷体", Font.PLAIN, 18));
		departmentTypeBox.setModel(new DefaultComboBoxModel(new String[] {"全部","计算机科学学院", "文学院", "教育与体育学院", "城市建设学院","电子信息学院","外国语学院","化学与环境工程学院","艺术学院","农学院"}));
		departmentTypeBox.setBounds(350, 25, 180, 40);
		add(departmentTypeBox);
		
		nameText.setFont(font);
		nameText.setBounds(660, 25, 130, 40);
		add(nameText);
		//[end]
		
		//[start]设置提示标签
		ReaderTypeLabel.setFont(new Font("华文楷体", Font.PLAIN, 18));
		ReaderTypeLabel.setBounds(40, 25, 90, 40);
		add(ReaderTypeLabel);
		
		departmentLabel.setFont(new Font("华文楷体", Font.PLAIN, 18));
		departmentLabel.setBounds(300, 25, 90, 40);
		add(departmentLabel);
		
		nameLabel.setFont(new Font("华文楷体", Font.PLAIN, 20));
		nameLabel.setBounds(600, 25, 90, 40);
		add(nameLabel);
		
		selectBtn.setFont(new Font("华文楷体", Font.PLAIN, 20));
		selectBtn.setBounds(840, 25, 100, 40);
		add(selectBtn);
		selectBtn.addActionListener(this);
		//[end]
		
		//[start]按钮设置
		newReaderBtn.setFont(new Font("宋体", Font.PLAIN, 15));
		newReaderBtn.setBounds(20, 640,130, 30);
		add(newReaderBtn);
		newReaderBtn.addActionListener(this);
		
		changeReaderInfoBtn.setFont(new Font("宋体", Font.PLAIN, 15));
		changeReaderInfoBtn.setBounds(159, 640,130, 30);
		add(changeReaderInfoBtn);
		
		
		LostBtn.setFont(new Font("宋体", Font.PLAIN, 15));
		LostBtn.setBounds(299, 640,81, 30);
		add(LostBtn);
		LostBtn.addActionListener(this);
		
		RelLostBtn.setFont(new Font("宋体", Font.PLAIN, 15));
		RelLostBtn.setBounds(390, 640,140, 30);
		add(RelLostBtn);
		RelLostBtn.addActionListener(this);
		
		CancelBtn.setFont(new Font("宋体", Font.PLAIN, 15));
		CancelBtn.setBounds(536, 640,74, 30);
		add(CancelBtn);
		
		exitBtn.setFont(new Font("宋体", Font.PLAIN, 15));
		exitBtn.setBounds(620, 640,70, 30);
		exitBtn.addActionListener(this);
		add(exitBtn);
		
		enterNewReaderBtn.setFont(new Font("宋体", Font.PLAIN, 15));
		enterNewReaderBtn.setBounds(876, 640,100, 30);
		add(enterNewReaderBtn);
		enterNewReaderBtn.addActionListener(this);
		
		enterChangeBtn.setFont(new Font("宋体", Font.PLAIN, 15));
		enterChangeBtn.setBounds(1017, 640,105, 30);
		add(enterChangeBtn);
		enterChangeBtn.addActionListener(this);
		
		replaceBtn.setFont(new Font("宋体", Font.PLAIN, 15));
		replaceBtn.setBounds(1170, 640,100, 30);
		add(replaceBtn);
		replaceBtn.addActionListener(this);
		//[end]

	}

	//[start] 显示图片信息
	public void setIcon() {
		readerpanel.add(imageLabel);
		ImageIcon icon=new ImageIcon(path+a+fileType);  
	    imageLabel.setIcon(icon);   
	}
	//[end]
	
	//[start]显示查询结果
	private void setReult(Reader[] rs) throws SQLException {
		ArrayList<String[]> objects = new ArrayList<String[]>();
		
		for(int i=0;i<rs.length;i++) {
			String[] str=new String[]{
					(String.valueOf(rs[i].getRdID())).trim(),
					(rs[i].getRdName()).trim(),
					(rs[i].getRdSex()).trim(),
					(bll.getTypeNameByID(rs[i].getRdType())).trim(),
					(rs[i].getRdDept()).trim(),
					(rs[i].getRdPhone()).trim(),
					(rs[i].getRdEMail()).trim(),
					(rs[i].getRdStatus()).trim(),
					(String.valueOf(rs[i].getRdBorrowQty())).trim(),
					(String.valueOf(rs[i].getRdDatereg())).trim()
			};
			objects.add(str);
		}
		String[][] RS=new String[objects.size()][];
		objects.toArray(RS);
		DefaultTableModel model = new DefaultTableModel(
			RS
		,
		new String[] {
				"ID", "姓名","性别", "类型", "院系","电话","E-mail","状态","已借书","注册日期"}) ;
		resutlTable.setModel(model);
		resutlTable.setRowHeight(35);
		resutlTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		resutlTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		resutlTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		resutlTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		resutlTable.getColumnModel().getColumn(4).setPreferredWidth(130);
		resutlTable.getColumnModel().getColumn(5).setPreferredWidth(100);
		resutlTable.getColumnModel().getColumn(6).setPreferredWidth(100);
		resutlTable.getColumnModel().getColumn(7).setPreferredWidth(40);
		resutlTable.getColumnModel().getColumn(8).setPreferredWidth(50);
		resutlTable.getColumnModel().getColumn(9).setPreferredWidth(90);
	}
	//[end]
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==exitBtn) {
			
		}
		else if(e.getSource()==imageLoad) {
			JFileChooser chooser = new JFileChooser("imgy");
	        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        chooser.showDialog(new JLabel(), "选择");
	        rdPhoto = chooser.getSelectedFile();
	        if(rdPhoto!=null) {
		        ImageIcon icon=new ImageIcon(rdPhoto.getPath()); 
				imageLabel.setBounds(300,30,140,250);
				readerpanel.add(imageLabel);
		        imageLabel.setIcon(icon); 
	        }
		}
		else if(e.getSource()==enterNewReaderBtn) {
			String name=nameText1.getText();
			String pwd=pwdText.getText();
			Reader rd=new Reader();
			rd.setRdName(name);
			rd.setRdPwd(pwd);
			rd.setRdSex((String)sexBox.getSelectedItem());
			try {
				rd.setRdType(bll.getTypeFromCmbx((String)readerTypeBox1.getSelectedItem()));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rd.setRdDept((String)deptCmbx.getSelectedItem());
			rd.setRdPhone(telNumText.getText());
			rd.setRdEMail(EmailText.getText());
			try {
				rd.setRdPhoto(fileToByte(rdPhoto));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			bll.insertReader(rd);//调用业务层方法插入数据到表
			
			Reader Rd[];
			try {
				try {
					Rd = bll.getReaderByName(nameText1.getText());
					setReult(Rd);	
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				} catch (SQLException e1) {
				e1.printStackTrace();
				}
		}
		else if(e.getSource()==selectBtn) {
			Reader rd[];
			try {
				try {
					rd = bll.getReaderByName(nameText.getText());
					setReult(rd);	
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				} catch (SQLException e1) {
				e1.printStackTrace();
				}
			}
		else if(e.getSource()==LostBtn) { 
			String NowStatus=statusText.getText().trim();
			int	ID=Integer.parseInt(IDText.getText());
			bll.LostReader(NowStatus, ID);	//调用业务层方法进行挂失操作
		}
		else if(e.getSource()==RelLostBtn) {
			String NowStatus =statusText.getText().trim();
			int ID=Integer.parseInt(IDText.getText());
			bll.RelLostReader(NowStatus, ID);	//调用业务层方法进行解除挂失操作
		}
		else if(e.getSource()==CancelBtn) {	
			
			int borrowedQty=Integer.parseInt(borrowedBookText.getText());
			int ID=Integer.parseInt(IDText.getText());
			bll.CancelReader(borrowedQty,ID);//调用业务层方法进行注销操作，此操作不可挂失
		}
		else if(e.getSource()==enterChangeBtn) {	
			Reader rd=new Reader();
			rd.setRdID(Integer.parseInt(IDText.getText()));
			rd.setRdName(nameText1.getText());
			rd.setRdPwd(pwdText.getText());
			rd.setRdSex((String)sexBox.getSelectedItem());
			try {
				rd.setRdType(bll.getTypeFromCmbx((String)readerTypeBox1.getSelectedItem()));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rd.setRdDept((String)deptCmbx.getSelectedItem());
			rd.setRdPhone(telNumText.getText());
			rd.setRdEMail(EmailText.getText());
			try {
				File f=rdPhoto;
				rd.setRdPhoto(fileToByte(f));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			bll.updateReader(rd);	
		}

		else if(e.getSource()==replaceBtn) {
			String NowStatus=statusText.getText().trim();
			Reader rd=new Reader();
			
			
			String name=nameText1.getText();
			String pwd=pwdText.getText();
			
			rd.setRdName(name);
			rd.setRdPwd(pwd);
			rd.setRdSex((String)sexBox.getSelectedItem());
			try {
				rd.setRdType(bll.getTypeFromCmbx((String)readerTypeBox1.getSelectedItem()));
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			rd.setRdDept((String)deptCmbx.getSelectedItem());
			rd.setRdPhone(telNumText.getText());
			rd.setRdEMail(EmailText.getText());
			try {
				rd.setRdPhoto(fileToByte(rdPhoto));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			bll.ReplaceReader(NowStatus,rd);//调用业务层方法
		}
	
		else if(e.getSource()==newReaderBtn) {
			IDText.setText("");
			nameText1.setText("");
			pwdText.setText("");
			borrowedBookText.setText("");
			statusText.setText("");
			telNumText.setText("");
			EmailText.setText("");
			dateRegText.setText("");
			sexBox.setSelectedItem("");
			deptCmbx.setSelectedItem("教师");
			readerTypeBox1.setSelectedItem("计算机科学学院");
			rdPhoto=null;
			imageLabel.setIcon(null);
			JOptionPane.showMessageDialog(null, "请填写借书证信息", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
		}
	}
		

		

	public  byte[] fileToByte(File img) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] bytes;
		try {
			BufferedImage bi;
			bi = ImageIO.read(img);
			ImageIO.write(bi, "jpg", baos);
			bytes = baos.toByteArray();
			System.err.println(bytes.length);
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			baos.close();
		}
		return null;
	}
	
	@SuppressWarnings("finally")
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






