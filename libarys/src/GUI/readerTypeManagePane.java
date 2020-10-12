package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import BLL.ReaderTypeAdmin;
import Model.ReaderType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class readerTypeManagePane extends JPanel implements ActionListener{
	//[start]控件的定义
	Font font = new Font("宋体", Font.PLAIN, 20);
	Font font1 = new Font("宋体", Font.PLAIN, 18);
	private ReaderTypeAdmin rdTypeBll=new ReaderTypeAdmin();
	private JTable resutlTable;
	private JTextField readerTypeBox = new JTextField();
	private JTextField BorrowCountText=new JTextField();
	private JTextField BorrowDaysText=new JTextField();
	private JTextField RenewText=new JTextField();
	private JTextField punishRateText=new JTextField();
	private JTextField dateValid=new JTextField();
	private JButton insertBtn=new JButton("添加");
	private JButton updateBtn=new JButton("修改");
	private JButton deleteBtn=new JButton("删除");
	//[end]
	
	//[start]设置结果显示panel和table
	public void setResultPane() {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {
			new String[]{"", "", "", "","","","","","",""},	
		},
		new String[] {
			"ID", "读者类型","可借数量", "可接天数", "课续借次数","罚金率","证件有效期"}) ;
 
		resutlTable=new JTable();
		resutlTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		resutlTable.getTableHeader().setReorderingAllowed(false);
		resutlTable.setModel(model);
		resutlTable.getTableHeader().setReorderingAllowed(false); 
		
		resutlTable.setFont(new Font("宋体", Font.PLAIN, 12));
		resutlTable.setRowHeight(35);
		resutlTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
		 JScrollPane resultPane = new JScrollPane(resutlTable);
		 resutlTable.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		int count=resutlTable.getSelectedRow();//获取你选中的行号（记录）
		 		readerTypeBox.setText(resutlTable.getValueAt(count, 1).toString());
		 		BorrowCountText.setText(resutlTable.getValueAt(count, 2).toString());
		 		BorrowDaysText.setText(resutlTable.getValueAt(count, 3).toString());
		 		RenewText.setText(resutlTable.getValueAt(count, 4).toString());
		 		punishRateText.setText(resutlTable.getValueAt(count, 5).toString());
		 		dateValid.setText(resutlTable.getValueAt(count, 6).toString());		
		 	}
		 });
		 resultPane.setEnabled(false);
		 resultPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "查询信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		 resultPane.setBounds(10, 81, 1151, 421);
		add(resultPane);
	}
	//[end]
	
	//[start]设置提示标签
	private void setLabel() {
		 JLabel typeName=new JLabel("类型名称");
		 typeName.setFont(new Font("华文细黑", Font.PLAIN, 15));
		 typeName.setBounds(10,30,80,30);
		 add(typeName);
		 JLabel BorrowCount=new JLabel("可借数量");
		 BorrowCount.setFont(new Font("华文细黑", Font.PLAIN, 15));
		 BorrowCount.setBounds(197,30,80,30);
		 add(BorrowCount);
		 JLabel BorrowDays=new JLabel("可借天数");
		 BorrowDays.setFont(new Font("华文细黑", Font.PLAIN, 15));
		 BorrowDays.setBounds(373,30,80,30);
		 add(BorrowDays);
		 JLabel RenewCount=new JLabel("可续借次数");
		 RenewCount.setFont(new Font("华文细黑", Font.PLAIN, 15));
		 RenewCount.setBounds(799,30,132,30);
		 add(RenewCount);
		 JLabel fineRate=new JLabel("罚金率");
		 fineRate.setFont(new Font("华文细黑", Font.PLAIN, 15));
		 fineRate.setBounds(1045,30,80,30);
		 add(fineRate);
		 JLabel youxiaoqi=new JLabel("证件有效期");
		 youxiaoqi.setFont(new Font("华文细黑", Font.PLAIN, 15));
		 youxiaoqi.setBounds(570,30,120,30);
		 add(youxiaoqi);
	}
	//[end]
	
	//[start]设置文本框
	private void setTextField() {
		readerTypeBox.setFont(font1);
		readerTypeBox.setBounds(86, 31, 106, 30);
		add(readerTypeBox);
		
		BorrowCountText.setFont(font1);
		BorrowCountText.setBounds(274, 31, 89, 30);
		add(BorrowCountText);
		BorrowCountText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if (keyChar<KeyEvent.VK_0 || keyChar>KeyEvent.VK_9) {
					e.consume();
				} 
			}
		@Override
		public void keyReleased(KeyEvent e) {
			String txt=BorrowCountText.getText();
			StringBuffer text=new StringBuffer("");
			for(int i=0;i<txt.length();i++) {
				if(txt.charAt(i)>='0'&&txt.charAt(i)<='9') {
					text.append(txt.charAt(i));
				}
			}
			BorrowCountText.setText(text.toString());
		}
		});
		
		BorrowDaysText.setFont(font1);
		BorrowDaysText.setBounds(448, 31, 112, 30);
		add(BorrowDaysText);
		BorrowDaysText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if (keyChar<KeyEvent.VK_0 || keyChar>KeyEvent.VK_9) {
					e.consume();
				} 
			}
		@Override
		public void keyReleased(KeyEvent e) {
			String txt=BorrowDaysText.getText();
			StringBuffer text=new StringBuffer("");
			for(int i=0;i<txt.length();i++) {
				if(txt.charAt(i)>='0'&&txt.charAt(i)<='9') {
					text.append(txt.charAt(i));
				}
			}
			BorrowDaysText.setText(text.toString());
		}
		});
		
		RenewText.setFont(font1);
		RenewText.setBounds(929, 31, 106, 30);
		add(RenewText);
		RenewText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if (keyChar<KeyEvent.VK_0 || keyChar>KeyEvent.VK_9) {
					e.consume();
				} 
			}
		@Override
		public void keyReleased(KeyEvent e) {
			String txt=RenewText.getText();
			StringBuffer text=new StringBuffer("");
			for(int i=0;i<txt.length();i++) {
				if(txt.charAt(i)>='0'&&txt.charAt(i)<='9') {
					text.append(txt.charAt(i));
				}
			}
			RenewText.setText(text.toString());
		}
		});
		
		punishRateText.setFont(font1);
		punishRateText.setBounds(1122, 31, 68, 30);
		add(punishRateText);
		
		
		dateValid.setFont(font1);
		dateValid.setBounds(684, 31, 112, 30);
		add(dateValid);
		dateValid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if (keyChar<KeyEvent.VK_0 || keyChar>KeyEvent.VK_9) {
					e.consume();
				} 
			}
		@Override
		public void keyReleased(KeyEvent e) {
			String txt=dateValid.getText();
			StringBuffer text=new StringBuffer("");
			for(int i=0;i<txt.length();i++) {
				if(txt.charAt(i)>='0'&&txt.charAt(i)<='9') {
					text.append(txt.charAt(i));
				}
			}
			dateValid.setText(text.toString());
		}
		});
	}
	//[end]
	
	//[start]构造函数
	public readerTypeManagePane() {
		//[start]按钮控件和panel的初始化
		 setLayout(null);
		 setBounds(4, 4, 1200, 660);
		 setBorder(new TitledBorder(null, "读者类型管理", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 setResultPane();
		 setLabel();
		 setTextField();
		  
		 insertBtn.setFont(new Font("宋体", Font.PLAIN, 20));
		 insertBtn.setBounds(157, 512, 120, 43);
		 add(insertBtn);
		 insertBtn.addActionListener(this);
		 
		 updateBtn.setFont(new Font("宋体", Font.PLAIN, 20));
		 updateBtn.setBounds(398, 512, 122, 43);
		 add(updateBtn);
		 updateBtn.addActionListener(this);
		 
		 deleteBtn.setFont(new Font("宋体", Font.PLAIN, 20));
		 deleteBtn.setBounds(633, 512, 120, 43);
		 add(deleteBtn);
		 deleteBtn.addActionListener(this);
		 //[end]
		 
		 setResult(rdTypeBll.getReaderTypes());//初始状态显示所有的读者类型信息
		 setPermission() ;
	}
	//[end]
	
	//[start]将查询结果输出到table
	private void setResult(ReaderType[] rs) {
		ArrayList<String[]> objects = new ArrayList<String[]>();
	
		for(int i=0;i<rs.length;i++) {
			String[] str=new String[]{
					String.valueOf(rs[i].getRdType()),
					rs[i].getRdTypeName(),
					String.valueOf(rs[i].getCanLendQty()),
					String.valueOf(rs[i].getCanLendDay()),
					String.valueOf(rs[i].getCanContinueTimes()),
					String.valueOf(rs[i].getPunishRate()),
					String.valueOf(rs[i].getDateValid()),
			};
			objects.add(str);
		}
		String[][] RS=new String[objects.size()][];
		objects.toArray(RS);
		DefaultTableModel model = new DefaultTableModel(
			RS
		,
		new String[] {
			"ID", "读者类型","可借数量", "可接天数", "课续借次数","罚金率","证件有效期"}) ;
		resutlTable.setModel(model);
		resutlTable.getColumnModel().getColumn(0).setPreferredWidth(40);
	}
	//[end]
	
	private void setPermission() {
		if(Login.rd!=null) {
			deleteBtn.setEnabled(false);
			updateBtn.setEnabled(false);
			insertBtn.setEnabled(false);
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==insertBtn) {
			ReaderType rt=new ReaderType();
			rt.setRdTypeName(readerTypeBox.getText());
			rt.setCanLendQty(Integer.parseInt(BorrowCountText.getText()));
			rt.setCanLendDay(Integer.parseInt(BorrowDaysText.getText()));
			rt.setCanContinueTimes(Integer.parseInt(RenewText.getText()));
			rt.setPunishRate(Float.parseFloat(punishRateText.getText()));
			rt.setDateValid(Integer.parseInt(dateValid.getText()));
			rdTypeBll.insertRdType(rt);
		}
		else if(e.getSource()==deleteBtn) {
			
			ReaderType rt=new ReaderType();
			int count=resutlTable.getSelectedRow();
			
			if(count==-1) {
				JOptionPane.showMessageDialog(null,  "请选择需要删除的类型","错误",JOptionPane.YES_NO_CANCEL_OPTION);
			}
			else {
				
					rt.setRdType(Integer.parseInt(resutlTable.getValueAt(count, 0).toString()));
					rdTypeBll.deleteRdType(rt);
				
			}
		}
		else if(e.getSource()==updateBtn) {
			ReaderType rt=new ReaderType();
			int count=resutlTable.getSelectedRow();
			if(count==-1) {
				JOptionPane.showMessageDialog(null, "请选择需要操作的对象", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			}
			else {
			rt.setRdType(Integer.parseInt(resutlTable.getValueAt(count, 0).toString()));
			rt.setRdTypeName(readerTypeBox.getText());
			rt.setCanLendQty(Integer.parseInt(BorrowCountText.getText()));
			rt.setCanLendDay(Integer.parseInt(BorrowDaysText.getText()));
			rt.setCanContinueTimes(Integer.parseInt(RenewText.getText()));
			rt.setPunishRate(Float.parseFloat(punishRateText.getText()));
			rt.setDateValid(Integer.parseInt(dateValid.getText()));
			rdTypeBll.updateRdType(rt);
			}
		}
		setResult(rdTypeBll.getReaderTypes());
	}
	

}
