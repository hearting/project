package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import BLL.adminBLL;
import Model.Admin;
public class PermissionMgtPane extends JPanel implements ActionListener {
	//[start]管理员信息控件
		private adminBLL Adbll=new adminBLL();
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
		private JTable resutlTable;
		private JTextField NameText= new JTextField();
		private JTextField IDText = new JTextField();
		private JButton enterNewReaderBtn=new JButton("确认办证");
		private JButton enterChangeBtn=new JButton("确认变更");
		private final JButton selectBtn = new JButton("查询");
		private final JButton selectBtn_1 = new JButton("查询");
		private JButton deleteBtn = new JButton("删除");
		
		private void setResutlPane() {
			DefaultTableModel model = new DefaultTableModel(new Object[][] {
			},
			new String[] {
				"ID", "姓名",  "电话","E-mail","密码","注册日期"});
		    
			resutlTable=new JTable();
			resutlTable.setModel(model);
			resutlTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			resutlTable.getTableHeader().setReorderingAllowed(false);
			resutlTable.setFont(new Font("宋体", Font.PLAIN, 12));
			resutlTable.setRowHeight(35);
			 JScrollPane resultPane = new JScrollPane(resutlTable);
			 resultPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "查询信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			 resultPane.setBounds(505, 126, 780, 446);
			add(resultPane);
			 resutlTable.addMouseListener(new MouseAdapter() {/////表格控件事件
				 	@Override
				 	public void mouseClicked(MouseEvent e) {
				 		int count=resutlTable.getSelectedRow();//获取你选中的行号（记录）
				 		AdIDText.setText(resutlTable.getValueAt(count, 0).toString().trim());
				 		AdnameText.setText(resutlTable.getValueAt(count, 1).toString().trim());
				 		AdPhoneText.setText(resutlTable.getValueAt(count, 2).toString().trim());
				 		AdEmailText.setText(resutlTable.getValueAt(count, 3).toString().trim());
				 		AdPwdText.setText(resutlTable.getValueAt(count, 4).toString().trim());
				 		AdDateRegText.setText(resutlTable.getValueAt(count, 5).toString().trim());
				 	}		
				 });
			
			
			
			NameText = new JTextField();
			NameText.setBounds(227, 40, 225, 48);
			add(NameText);
			
			
			
			IDText.setBounds(710, 40, 215, 48);
			add(IDText);
			IDText.setFont(font1);
			
			JLabel label = new JLabel("姓名");
			label.setBounds(97, 32, 120, 62);
			add(label);
			label.setFont(font1);
			
			JLabel lblId = new JLabel("ID");
			lblId.setBounds(604, 39, 75, 48);
			add(lblId);
			lblId.setFont(font1);
			
			enterNewReaderBtn.setBounds(374, 606, 150, 40);
			enterNewReaderBtn.addActionListener(this);
			add(enterNewReaderBtn);
			
			enterChangeBtn.setBounds(551, 606, 131, 40);
			enterChangeBtn.addActionListener(this);
			add(enterChangeBtn);
			selectBtn.setBounds(463, 46, 61, 34);
			selectBtn.addActionListener(this);
			add(selectBtn);
			selectBtn_1.setBounds(935, 45, 61, 36);
			
			add(selectBtn_1);
			selectBtn_1.addActionListener(this);
			
		
			deleteBtn.setBounds(705, 606, 150, 40);
			add(deleteBtn);
			deleteBtn.addActionListener(this);
		}
		
		
		//[start]初始化管理员信息panel
			private void setAdmin() {
				Adminpanel.setBorder(new TitledBorder(null, "读者信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				Adminpanel.setBounds(76, 126, 393, 446);
				add(Adminpanel);
				Adminpanel.setLayout(null);
				
				AdIDText.setFont(font1);
				AdIDText.setBounds(130,70,140,30);
				Adminpanel.add(AdIDText);
				AdIDText.setEditable(false);
				
				AdnameText.setFont(font1);
				AdnameText.setBounds(130,110,140,30);
				Adminpanel.add(AdnameText);
				

				AdPwdText.setFont(font1);
				AdPwdText.setBounds(130,150,140,30);
				Adminpanel.add(AdPwdText);
				
				AdEmailText.setFont(font1);
				AdEmailText.setBounds(130,190,140,30);
				Adminpanel.add(AdEmailText);
				
				
				AdPhoneText.setFont(font1);
				AdPhoneText.setBounds(130,230,140,30);
				Adminpanel.add(AdPhoneText);
				
				
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
	public PermissionMgtPane() {
		setLayout(null);
		setBounds(4, 4, 1376, 730);
		setBorder(new TitledBorder(null, "图书管理员管理", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 setAdmin();
		 setResutlPane() ;
	}

	//[start]将查询结果输出到table
		private void setResult(Admin[] rs) {
			ArrayList<String[]> objects = new ArrayList<String[]>();
		
			for(int i=0;i<rs.length;i++) {
				String[] str=new String[]{
						String.valueOf(rs[i].getAdminID()),
						rs[i].getAdminName(),
						rs[i].getAdminPhone(),
						rs[i].getAdminEmail(),
						rs[i].getAdminPwd(),
						String.valueOf(rs[i].getDateReg())
				};
				objects.add(str);
			}
			String[][] RS=new String[objects.size()][];
			objects.toArray(RS);
			DefaultTableModel model = new DefaultTableModel(
				RS
			,
			new String[] {
					"ID", "姓名",  "电话","E-mail","密码","注册日期"}) ;
			resutlTable.setModel(model);
			resutlTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		}
		//[end]
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==enterNewReaderBtn) {
			Admin ad=new Admin();
			ad.setAdminName(AdnameText.getText());
			ad.setAdminEmail(AdEmailText.getText());
			ad.setAdminPhone(AdPhoneText.getText());
			ad.setAdminPwd(AdPwdText.getText());
			Adbll.insertAd(ad);
			JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			Admin rd[];
			rd = Adbll.getAdByName(AdnameText.getText());
			if(rd==null) {
				JOptionPane.showMessageDialog(null, "未查询到相关信息", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			}
			setResult(rd);
		}
		else if(e.getSource()==enterChangeBtn) {
			Admin ad=new Admin();
			ad.setAdminID(Integer.parseInt(AdIDText.getText()));
			ad.setAdminName(AdnameText.getText());
			ad.setAdminEmail(AdEmailText.getText());
			ad.setAdminPhone(AdPhoneText.getText());
			ad.setAdminPwd(AdPwdText.getText());
			Adbll.updateAd(ad);
			JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
		}
		else if(e.getSource()==selectBtn) {
			Admin rd[];
			rd = Adbll.getAdByName(NameText.getText());
			if(rd==null) {
				JOptionPane.showMessageDialog(null, "未查询到相关信息", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			}
			setResult(rd);
		}
		else if(e.getSource()==selectBtn_1) {
			
			Admin ad;
			try {
				ad = Adbll.getAdminByID(Integer.parseInt(IDText.getText()));
				Admin rd[]= {ad};
				if(rd==null) {
					JOptionPane.showMessageDialog(null, "未查询到相关信息", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
				}
				setResult(rd);
			} catch (NumberFormatException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource()==deleteBtn) {
			int count=resutlTable.getSelectedRow();//获取你选中的行号（记录）
			int id=Integer.parseInt(resutlTable.getValueAt(count, 0).toString().trim());
			Adbll.deleteAd(id);
			JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
		}
		
		
		
		
	}
}
