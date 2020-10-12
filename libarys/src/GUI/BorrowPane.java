package GUI;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import BLL.BookAdmin;
import BLL.BorrowAdmin;
import Model.Book;
import Model.Borrow;
import Model.Reader;
import Model.ReaderType;
import java.awt.Color;

public class BorrowPane extends JPanel implements ActionListener{
	BorrowAdmin bll=new BorrowAdmin();
	BookAdmin bkBll=new BookAdmin();
	Reader rd;
	ReaderType rt;
	
	Font font = new Font("宋体", Font.PLAIN, 20);
	Font font1 = new Font("宋体", Font.PLAIN, 18);
	JPanel readerInfoPane=new JPanel();
	JScrollPane borrowedBookPane;
	JTable borrowedBookTable=new JTable();
	
	JPanel bookInfoPane=new JPanel();
	JScrollPane bookInfoTablePane;
	JTable bookInfoTable=new JTable();
	
	JLabel readerIDLabel=new JLabel("读者编号");
	JLabel readerNameLabel=new JLabel("读者姓名");
	JLabel readerDepartmentLabel=new JLabel("读者单位");
	JLabel readerTypeLabel=new JLabel("读者类型");
	JLabel borrowCountLabel=new JLabel("可借书数量");
	JLabel borrowDaysLabel=new JLabel("可借书天数");
	JLabel borrowedCountLabel=new JLabel("已借数量");
	
	JLabel bookIDLabel=new JLabel("索书号");
	JLabel bookNameLabel=new JLabel("图书名称");
	
	JTextField readerIDText=new JTextField();
	JButton readerIDBtn=new JButton();
	
	JTextField readerNameText=new JTextField();
	JTextField readerDepartText=new JTextField();
	JTextField readerTypeText=new JTextField();
	
	JTextField borrowCountText=new JTextField();
	JTextField borrowDaysText=new JTextField();
	JTextField borrowedCountText=new JTextField();
	
	JTextField bookCodeText=new JTextField();
	JButton bookCodeBtn=new JButton();
	JTextField bookNameText=new JTextField();
	JButton bookNameBtn=new JButton();
	
	
	JButton borrowBookBtn = new JButton("借阅");
	JButton renewBookBtn = new JButton("续借");
	JButton returnBookBtn = new JButton("还书");
	JButton returnBtn = new JButton("返回");

	//[start]设置读者信息Pane
	public void setReaderInfoPane() {
		readerInfoPane.setBorder(new TitledBorder(null, "读者信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		readerInfoPane.setBounds(4, 15, 986, 100);
		readerInfoPane.setLayout(null);
		add(readerInfoPane);
		
		readerIDLabel.setFont(font1);
		readerIDLabel.setBounds(657, 37, 80, 30);
		readerInfoPane.add(readerIDLabel);
		
		readerIDText.setFont(font1);
		readerIDText.setBounds(747, 37, 101, 30);
		readerInfoPane.add(readerIDText);
		readerIDText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if (keyChar<KeyEvent.VK_0 || keyChar>KeyEvent.VK_9) {
					e.consume();
				} 
			}
		@Override
		public void keyReleased(KeyEvent e) {
			String txt=readerIDText.getText();
			StringBuffer text=new StringBuffer("");
			for(int i=0;i<txt.length();i++) {
				if(txt.charAt(i)>='0'&&txt.charAt(i)<='9') {
					text.append(txt.charAt(i));
				}
			}
			readerIDText.setText(text.toString());
		}
		});
		readerIDBtn.setText("查询");
		
		readerIDBtn.setBounds(897, 38, 66, 30);
		readerInfoPane.add(readerIDBtn);
		readerIDBtn.addActionListener(this);
		
		readerNameLabel.setFont(font1);
		readerNameLabel.setBounds(10, 15, 90, 30);
		readerInfoPane.add(readerNameLabel);
		readerNameText.setEditable(false);
		
		readerNameText.setFont(font1);
		readerNameText.setBounds(100, 15, 90, 30);
		readerInfoPane.add(readerNameText);
		
		readerDepartmentLabel.setFont(font1);
		readerDepartmentLabel.setBounds(211, 15, 90, 30);
		readerInfoPane.add(readerDepartmentLabel);
		readerDepartText.setEditable(false);
		
		readerDepartText.setFont(font1);
		readerDepartText.setBounds(311, 15, 114, 30);
		readerInfoPane.add(readerDepartText);
		
		readerTypeLabel.setFont(font1);
		readerTypeLabel.setBounds(453, 15, 90, 30);
		readerInfoPane.add(readerTypeLabel);
		readerTypeText.setEditable(false);
		
		readerTypeText.setFont(font1);
		readerTypeText.setBounds(564, 15, 76, 30);
		readerInfoPane.add(readerTypeText);
		
		borrowCountLabel.setFont(font1);
		borrowCountLabel.setBounds(10, 55, 100, 30);
		readerInfoPane.add(borrowCountLabel);
		borrowCountText.setEditable(false);
		
		borrowCountText.setFont(font1);
		borrowCountText.setBounds(110, 55, 80, 30);
		readerInfoPane.add(borrowCountText);
		
		borrowDaysLabel.setFont(font1);
		borrowDaysLabel.setBounds(211, 55, 100, 30);
		readerInfoPane.add(borrowDaysLabel);
		borrowDaysText.setEditable(false);
		
		borrowDaysText.setFont(font1);
		borrowDaysText.setBounds(321, 55, 104, 30);
		readerInfoPane.add(borrowDaysText);
		
		borrowedCountLabel.setFont(font1);
		borrowedCountLabel.setBounds(453, 55, 90, 30);
		readerInfoPane.add(borrowedCountLabel);
		borrowedCountText.setEditable(false);
		
		borrowedCountText.setFont(font1);
		borrowedCountText.setBounds(564, 55, 66, 30);
		readerInfoPane.add(borrowedCountText);
	}
	//[end]
	
	
	//[start]设置借书面板属性
	public void setBorrowedPane() {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {
		},
		new String[] {
			"图书序号", "图书名称","图书作者", "续借次数", "借阅日期","应还日期","超期天数","超期金额"});
		
		borrowedBookTable.setModel(model);
		borrowedBookTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		borrowedBookTable.getTableHeader().setReorderingAllowed(false);
		borrowedBookTable.setFont(new Font("宋体", Font.PLAIN, 12));
		borrowedBookTable.setRowHeight(25);
		
		borrowedBookPane=new JScrollPane(borrowedBookTable);
		borrowedBookPane.setBorder(new TitledBorder(null, "已借图书", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		borrowedBookPane.setBounds(4, 115, 835, 230);
		add(borrowedBookPane);
		
	
	}
	//[end]
	
	//[start]设置图书信息Pane
	public void setBookInfoPane() {
		bookInfoPane=new JPanel();
		bookInfoPane.setBorder(new TitledBorder(null, "图书信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bookInfoPane.setBounds(4, 350, 835, 247);
		bookInfoPane.setLayout(null);
		add(bookInfoPane);
		
		DefaultTableModel model = new DefaultTableModel(new Object[][] {
		},
		new String[] {
				"序号", "索书号","书名", "作者", "出版社","出版日期","ISBN","分类号","价格","入馆时间","状态"});
		
		bookInfoTable.setModel(model);
		bookInfoTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		bookInfoTable.getTableHeader().setReorderingAllowed(false);
		bookInfoTable.setFont(new Font("宋体", Font.PLAIN, 12));
		bookInfoTable.setRowHeight(25);
		
		bookInfoTablePane=new JScrollPane(bookInfoTable);
		bookInfoTablePane.setBounds(4, 70, 829, 171);
		bookInfoPane.add(bookInfoTablePane);
		
		bookIDLabel.setFont(font1);
		bookIDLabel.setBounds(10, 25, 68, 30);
		bookInfoPane.add(bookIDLabel);
		
		bookCodeText.setFont(font1);
		bookCodeText.setBounds(88, 25, 135, 30);
		bookInfoPane.add(bookCodeText);
		bookCodeText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if (keyChar<KeyEvent.VK_0 || keyChar>KeyEvent.VK_9) {
					e.consume();
				} 
			}
		@Override
		public void keyReleased(KeyEvent e) {
			String txt=bookCodeText.getText();
			StringBuffer text=new StringBuffer("");
			for(int i=0;i<txt.length();i++) {
				if(txt.charAt(i)>='0'&&txt.charAt(i)<='9') {
					text.append(txt.charAt(i));
				}
			}
			bookCodeText.setText(text.toString());
		}
		});
		bookCodeBtn.setText("查询");
		
		bookCodeBtn.setBounds(233, 25, 75, 30);
		bookCodeBtn.addActionListener(this);
		bookInfoPane.add(bookCodeBtn);
		

		bookNameLabel.setFont(font1);
		bookNameLabel.setBounds(318, 24, 140, 30);
		bookInfoPane.add(bookNameLabel);
		
		bookNameText.setFont(font1);
		bookNameText.setBounds(416, 26, 140, 30);
		bookInfoPane.add(bookNameText);
		bookNameBtn.setText("查询");
		
		bookNameBtn.setBounds(583, 27, 68, 30);
		bookNameBtn.addActionListener(this);
		bookInfoPane.add(bookNameBtn);	
	}
	
	//[end]
	
	
	//[start]设置操作权限
	public void setPermission() {
		if(Login.rd!=null) {
			//borrowBookBtn.setEnabled(false);
			//renewBookBtn.setEnabled(false);
			//returnBookBtn.setEnabled(false);
			returnBtn.setEnabled(false);
			readerIDText.setEditable(false);
			readerIDText.setText(String.valueOf(Login.rd.getRdID()));
		}
	}
	//[end]
	
	//[start]构造函数
	public BorrowPane() {
		setBackground(Color.GRAY);
		setBorder(new TitledBorder(null, "借书", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		setBounds(4, 4, 1000, 662);
		setReaderInfoPane();
		setBorrowedPane();
		setBookInfoPane();
		
		borrowBookBtn.setFont(font1);
		borrowBookBtn.setBounds(71, 618, 100, 30);
		add(borrowBookBtn);
		borrowBookBtn.addActionListener(this);
		
		renewBookBtn.setFont(font1);
		renewBookBtn.setBounds(222, 618, 100, 30);
		add(renewBookBtn);
		renewBookBtn.addActionListener(this);
		
		returnBookBtn.setFont(font1);
		returnBookBtn.setBounds(384, 618, 100, 30);
		add(returnBookBtn);
		returnBookBtn.addActionListener(this);
		
		returnBtn.setFont(font1);
		returnBtn.setBounds(700, 690, 100, 30);
		add(returnBtn);
		setPermission();
	}
	//[end]
	
	//[start] 设置读者信息结果
	public void setRdResult(Reader rd,Borrow[] brs,ReaderType rt,Book[] bks) {
		readerIDText.setText(String.valueOf(rd.getRdID()).trim());
		readerNameText.setText(rd.getRdName().trim());
		readerDepartText.setText(rd.getRdDept().trim());
		readerTypeText.setText(String.valueOf(rt.getRdTypeName()).trim());
		borrowDaysText.setText(String.valueOf(rt.getCanLendDay()).trim());
		borrowCountText.setText(String.valueOf(rt.getCanLendQty()).trim());
		borrowedCountText.setText(String.valueOf(rd.getRdBorrowQty()).trim());
		
		ArrayList<String[]> objects = new ArrayList<String[]>();
		
		for(int i=0;i<brs.length;i++) {
			String[] str=new String[]{
					(String.valueOf(brs[i].getBkID())).trim(),
					(String.valueOf(bks[i].getBkName())).trim(),
					(String.valueOf(bks[i].getBkAythor())).trim(),
					(String.valueOf(brs[i].getLdContinueTimes())).trim(),
					(String.valueOf(brs[i].getLdDateOut())).trim(),
					(String.valueOf(brs[i].getLdDateRetPlan())).trim(),
					(String.valueOf(brs[i].getLdOverDay())).trim(),
					(String.valueOf(brs[i].getLdOverMoney())).trim(),
			};
			objects.add(str);
		}
		String[][] RS=new String[objects.size()][];
		objects.toArray(RS);
		DefaultTableModel model = new DefaultTableModel(
			RS
		,
		new String[] {
				"图书序号", "图书名称","图书作者", "续借次数", "借阅日期","应还日期","超期天数","超期金额"}) ;
		borrowedBookTable.setModel(model);
		
	}
	//[end]

	//[start]将查询信息显示到表格
	public void setBkResult(Book[] rs) {
		ArrayList<String[]> objects = new ArrayList<String[]>();
		
		for(int i=0;i<rs.length;i++) {
			String[] str=new String[]{
					(String.valueOf(rs[i].getBkID())).trim(),
					rs[i].getBkCode(),
					(rs[i].getBkName()).trim(),
					(rs[i].getBkAythor()).trim(),
					rs[i].getBkPress().trim(),
					(rs[i].getBkDatePress()).trim(),
					(rs[i].getBkISBN()).trim(),
					(rs[i].getBkCatalog()).trim(),
					(String.valueOf(rs[i].getBkPrice())),
					(String.valueOf(rs[i].getBkDateIn())),
					(rs[i].getBkStatus()).trim()
			};
			objects.add(str);
		}
		String[][] RS=new String[objects.size()][];
		objects.toArray(RS);
		DefaultTableModel model = new DefaultTableModel(
			RS
		,
		new String[] {
				"序号", "索书号","书名", "作者", "出版社","出版日期","ISBN","分类号","价格","入馆时间","状态"}) ;
		bookInfoTable.setModel(model);
		bookInfoTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		bookInfoTable.getTableHeader().setReorderingAllowed(false);
		bookInfoTable.setFont(new Font("宋体", Font.PLAIN, 12));
	}
	//[end]
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==readerIDBtn) {
			int RdID=Integer.parseInt(readerIDText.getText());
			
				rd=bll.getReaderByID(RdID);
				if(rd==null) {
					JOptionPane.showMessageDialog(null, "未查询到相关用户信息", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
					return;
				}
				rt=bll.getRdTypeByid(rd.getRdType());
				Borrow[] brs=bll.getBrsByRdID(RdID);
				Book[] bks=bll.getBksByBkID(brs);
				setRdResult(rd,brs,rt,bks);
		}
		
		else if(e.getSource()==bookCodeBtn) {
			String bkCode = bookCodeText.getText();
			try {
				Book[] rs=bkBll.getBkByStr(bkCode,"bkCode");
				setBkResult(rs);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		else if(e.getSource()==bookNameBtn) {
			String bkName = bookNameText.getText();
			try {
				Book[] rs=bkBll.getBkByStr(bkName,"bkName");
				setBkResult(rs);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(e.getSource()==borrowBookBtn) {
			int count=bookInfoTable.getSelectedRow();//获取你选中的行号（记录）
			if(count==-1) {
				JOptionPane.showMessageDialog(null,  "请选择需要借的书籍","错误",JOptionPane.YES_NO_CANCEL_OPTION);
				return ;
			}
			int rdID=rd.getRdID();
			int bkID=Integer.parseInt(bookInfoTable.getValueAt(count, 0).toString());
			int lendOperator = 0;
			if(Login.admin!=null) lendOperator=Login.admin.getAdminID();
			if(Login.rd!=null) lendOperator=Login.rd.getRdID();
			if(Integer.parseInt(borrowedCountText.getText())>=Integer.parseInt(borrowCountText.getText())) {
				JOptionPane.showMessageDialog(null,  "已达最大借书数量","错误",JOptionPane.YES_NO_CANCEL_OPTION);
				return;
			}
			if(!(rd.getRdStatus().equals("有效"))) {
				JOptionPane.showMessageDialog(null,  "借书证当前状态不可借书","错误",JOptionPane.YES_NO_CANCEL_OPTION);
				return;
			}
			if(!(bookInfoTable.getValueAt(count, 10).toString().trim().equals("在馆"))) {
				JOptionPane.showMessageDialog(null,  "图书当前状态不可借出","错误",JOptionPane.YES_NO_CANCEL_OPTION);
				return;
			}
			
			bll.insertBr(rdID,bkID,lendOperator);
			JOptionPane.showMessageDialog(null,  "借书成功","提示",JOptionPane.YES_NO_CANCEL_OPTION);
			
		}
		
		else if(e.getSource()==renewBookBtn) {
			int count=borrowedBookTable.getSelectedRow();//获取你选中的行号（记录）
			if(count==-1) {
				JOptionPane.showMessageDialog(null,  "请选择需要续借的书籍","错误",JOptionPane.YES_NO_CANCEL_OPTION);
				return ;
			}
			if(rt.getCanContinueTimes()<=Integer.parseInt(borrowedBookTable.getValueAt(count, 3).toString())) {
				JOptionPane.showMessageDialog(null,  "续借次数已达上限，不可续借","提示",JOptionPane.YES_NO_CANCEL_OPTION);
			}
			int bkID=Integer.parseInt(borrowedBookTable.getValueAt(count, 0).toString());
			int lendOperator = 0;
			if(Login.admin!=null) lendOperator=Login.admin.getAdminID();
			if(Login.rd!=null) lendOperator=Login.rd.getRdID();
			bll.reNewBook(bkID,lendOperator);
			JOptionPane.showMessageDialog(null,  "续借成功","提示",JOptionPane.YES_NO_CANCEL_OPTION);
		}
		
		else if(e.getSource()==returnBookBtn) {
			int count=borrowedBookTable.getSelectedRow();//获取你选中的行号（记录）
			if(count==-1) {
				JOptionPane.showMessageDialog(null,  "请选择需要还的书籍","错误",JOptionPane.YES_NO_CANCEL_OPTION);
				return ;
			}
			Borrow borrow=new Borrow();
			int bkID=Integer.parseInt(borrowedBookTable.getValueAt(count, 0).toString());
			int RetOperator = 0;
			borrow.setRdID(Integer.parseInt(readerIDText.getText()));
			borrow.setBkID(Integer.parseInt(borrowedBookTable.getValueAt(count, 0).toString()));
			if(Login.admin!=null) RetOperator=Login.admin.getAdminID();
			if(Login.rd!=null) RetOperator=Login.rd.getRdID();
			bll.returnBook(bkID,RetOperator);
			bll.deleteBr(borrow);
			JOptionPane.showMessageDialog(null,  "还书成功","提示",JOptionPane.YES_NO_CANCEL_OPTION);
		}
		
	}
}
