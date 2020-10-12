package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import BLL.BookAdmin;
import Model.Book;
import Model.ReaderType;

public class selectBookPane extends JPanel implements ActionListener {
	static int Bkid;
	BookAdmin bll=new BookAdmin();
	Font font1 = new Font("宋体", Font.PLAIN, 18);
	JLabel selectTypeLabel=new JLabel("检索字段");
	
	JLabel nameLabel=new JLabel("图书名称");
	JLabel authorLabel=new JLabel("图书作者");
	JLabel bookDescribeLabel=new JLabel("图书描述");
	JLabel publishLabel=new JLabel("出版社名");
	JLabel TypeNumLabel=new JLabel("分类号");
	JLabel publishYearLabel=new JLabel("出版年");
	
	JComboBox selectTypeBox=new JComboBox();
	JTextField selectTypeText=new JTextField();

	JTextField nameText=new JTextField();
	JTextField authorText=new JTextField();
	JTextField bookDescribeText=new JTextField();
	JTextField publishText=new JTextField();
	JTextField TypeNumText=new JTextField();
	JTextField publishYearText=new JTextField();
	JButton LselectBtn=new JButton("查询");
	JButton HselectBtn=new JButton("查询");
	JButton updateBtn=new JButton("修改");
	JButton deleteBtn=new JButton("删除");
	//JButton excelBtn=new JButton("excel");
	//JButton returnBtn=new JButton("返回");
	private JTable resutlTable;
	JPanel  HselectPane= new JPanel();
	JPanel LselectPane = new JPanel();
	
	//[start]设值结果面板的属性，为里面添加控件
		private void setResutlPane() {
			DefaultTableModel model = new DefaultTableModel(new Object[][] {
				new String[]{"", "", "", "","","","","","",""},	
			},
			new String[] {
				"ID", "索书号","书名", "作者", "出版社","出版日期","ISBN","分类号","语种","页数","价格","入馆时间","状态"});
		    
			resutlTable=new JTable();
			resutlTable.setModel(model);
			resutlTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			resutlTable.getTableHeader().setReorderingAllowed(false);
			resutlTable.setFont(new Font("宋体", Font.PLAIN, 12));
			resutlTable.setRowHeight(35);
			resutlTable.getColumnModel().getColumn(0).setPreferredWidth(40);
			resutlTable.getColumnModel().getColumn(1).setPreferredWidth(90);
			resutlTable.getColumnModel().getColumn(2).setPreferredWidth(140);
			resutlTable.getColumnModel().getColumn(3).setPreferredWidth(80);
			resutlTable.getColumnModel().getColumn(4).setPreferredWidth(100);
			resutlTable.getColumnModel().getColumn(5).setPreferredWidth(100);
			resutlTable.getColumnModel().getColumn(6).setPreferredWidth(100);
			resutlTable.getColumnModel().getColumn(7).setPreferredWidth(80);
			resutlTable.getColumnModel().getColumn(8).setPreferredWidth(20);
			resutlTable.getColumnModel().getColumn(9).setPreferredWidth(15);
			resutlTable.getColumnModel().getColumn(10).setPreferredWidth(30);
			resutlTable.getColumnModel().getColumn(11).setPreferredWidth(100);
			resutlTable.getColumnModel().getColumn(12).setPreferredWidth(80);
			JScrollPane resultPane = new JScrollPane(resutlTable);
			resultPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "查询信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			resultPane.setBounds(10, 217, 1250, 430);
			add(resultPane);
		}
		//[end]

	
	//[start]构造函数
	public void setTabPane() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(80, 20, 1100, 180);
		add(tabbedPane);
		
		tabbedPane.addTab("简单查询", null, LselectPane, null);
		LselectPane.setLayout(null);
		
		tabbedPane.addTab("高级查询", null, HselectPane, null);
		HselectPane.setLayout(null);
		
		//[start]设置标签
		selectTypeLabel.setFont(font1);
		selectTypeLabel.setBounds(20, 30, 100, 30);
		LselectPane.add(selectTypeLabel);
		
		nameLabel.setFont(font1);
		nameLabel.setBounds(20, 15, 100, 30);
		HselectPane.add(nameLabel);
		
		authorLabel.setFont(font1);
		authorLabel.setBounds(320, 15, 100, 30);
		HselectPane.add(authorLabel);
		
		bookDescribeLabel.setFont(font1);
		bookDescribeLabel.setBounds(620, 15, 100, 30);
		HselectPane.add(bookDescribeLabel);
		
		publishLabel.setFont(font1);
		publishLabel.setBounds(20, 65, 100, 30);
		HselectPane.add(publishLabel);
		
		TypeNumLabel.setFont(font1);
		TypeNumLabel.setBounds(320, 65, 100, 30);
		HselectPane.add(TypeNumLabel);
		
		publishYearLabel.setFont(font1);
		publishYearLabel.setBounds(620, 65, 100, 30);
		HselectPane.add(publishYearLabel);
		//[end]
		
		
		//[start]设置信息输入控件
		selectTypeBox.setFont(font1);
		selectTypeBox.setModel(new DefaultComboBoxModel(new String[] {"书名", "作者", "分类号", "出版社","索书号"}));
		selectTypeBox.setBounds(200, 30, 140, 30);
		LselectPane.add(selectTypeBox);
		
		selectTypeText.setFont(font1);
		selectTypeText.setBounds(420, 30, 240, 30);
		LselectPane.add(selectTypeText);
		selectTypeText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(selectTypeBox.getSelectedItem().equals("索书号")) {
				int keyChar=e.getKeyChar();
				if (keyChar<KeyEvent.VK_0 || keyChar>KeyEvent.VK_9) {
					e.consume();
				} }
			}
		@Override
		public void keyReleased(KeyEvent e) {
			if(selectTypeBox.getSelectedItem().equals("索书号")) {
			String txt=selectTypeText.getText();
			StringBuffer text=new StringBuffer("");
			for(int i=0;i<txt.length();i++) {
				if(txt.charAt(i)>='0'&&txt.charAt(i)<='9') {
					text.append(txt.charAt(i));
				}
			}
			selectTypeText.setText(text.toString());
		}}
		});
		
		nameText.setFont(font1);
		nameText.setBounds(100, 15, 130, 30);
		HselectPane.add(nameText);
		
		nameText.setFont(font1);
		nameText.setBounds(100, 15, 130, 30);
		HselectPane.add(nameText);
		
		authorText.setFont(font1);
		authorText.setBounds(400, 15, 130, 30);
		HselectPane.add(authorText);
		
		bookDescribeText.setFont(font1);
		bookDescribeText.setBounds(700, 15, 130, 30);
		HselectPane.add(bookDescribeText);
		
		publishText.setFont(font1);
		publishText.setBounds(100, 65, 130, 30);
		HselectPane.add(publishText);
		
		TypeNumText.setFont(font1);
		TypeNumText.setBounds(400, 65, 130, 30);
		HselectPane.add(TypeNumText);
		
		publishYearText.setFont(font1);
		publishYearText.setBounds(700, 65, 130, 30);
		HselectPane.add(publishYearText);
		//[end]

		//[start]设置按钮属性
		LselectBtn.setFont(font1);
		LselectBtn.setBounds(900, 80, 140, 30);
		LselectPane.add(LselectBtn);
		LselectBtn.addActionListener(this);
		
		HselectBtn.setFont(font1);
		HselectBtn.setBounds(907, 15, 140, 30);
		HselectPane.add(HselectBtn);
		HselectBtn.addActionListener(this);
		//[end]
	
	}
	//[end]
	
	
	//[start]//构造函数
	public selectBookPane() {
		setForeground(Color.GRAY);
		setLayout(null);
		setBounds(4, 4, 1376, 730);
		setBorder(new TitledBorder(null, "图书查询", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		setResutlPane();
		setTabPane();
		Font f = new Font("宋体", Font.PLAIN, 15);
		updateBtn.setFont(f);
		updateBtn.setBounds(380, 650, 140, 30);
		add(updateBtn);
		updateBtn.addActionListener(this);
		
		deleteBtn.setFont(f);
		deleteBtn.setBounds(580, 650, 140, 30);
		add(deleteBtn);
		deleteBtn.addActionListener(this);
		
//		excelBtn.setFont(font1);
//		excelBtn.setBounds(780, 680, 140, 30);
//		add(excelBtn);
		
//		returnBtn.setFont(font1);
//		returnBtn.setBounds(980, 680, 140, 30);
//		add(returnBtn);
		
	}
	//[end]
	
	//[start]将查询信息显示到表格
	public void setResult(Book[] rs) {
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
					String.valueOf(rs[i].getBkLanguage()),
					(String.valueOf(rs[i].getBkPages())),
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
				"ID", "索书号","书名", "作者", "出版社","出版日期","ISBN","分类号","语种","页数","价格","入馆时间","状态"}) ;
		resutlTable.setModel(model);
		resutlTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		resutlTable.getTableHeader().setReorderingAllowed(false);
		resutlTable.setFont(new Font("宋体", Font.PLAIN, 12));
		resutlTable.setRowHeight(35);
		resutlTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		resutlTable.getColumnModel().getColumn(1).setPreferredWidth(90);
		resutlTable.getColumnModel().getColumn(2).setPreferredWidth(140);
		resutlTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		resutlTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		resutlTable.getColumnModel().getColumn(5).setPreferredWidth(60);
		resutlTable.getColumnModel().getColumn(6).setPreferredWidth(100);
		resutlTable.getColumnModel().getColumn(7).setPreferredWidth(80);
		resutlTable.getColumnModel().getColumn(8).setPreferredWidth(20);
		resutlTable.getColumnModel().getColumn(9).setPreferredWidth(15);
		resutlTable.getColumnModel().getColumn(10).setPreferredWidth(30);
		resutlTable.getColumnModel().getColumn(11).setPreferredWidth(100);
		resutlTable.getColumnModel().getColumn(12).setPreferredWidth(40);
	}
	//[end]
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==LselectBtn) {
			Book[] bk;
			String selectType=selectTypeBox.getSelectedItem().toString();
			String selectStr=selectTypeText.getText();
			switch(selectType) {
				case "书名":{
					selectType="bkName";
					break;
				}
				case "作者":{
					selectType="bkAuthor";
					break;
				}
				case "分类号":{
					selectType="bkCatalog";
					break;
				}
				case "出版社":{
					selectType="bkPress";
					break;
				}
				case "索书号":{
					selectType="bkCode";
					break;
				}
			}
				try {
					bk=bll.getBkByStr(selectStr,selectType);
					setResult(bk);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
		
		else if(e.getSource()==HselectBtn) {
			Book[] bk;
			String bkName=nameText.getText();
			String bkAuthor=authorText.getText();
			String bkBrief=bookDescribeText.getText();
			String bkPress=publishText.getText();
			String bkCatalog=TypeNumText.getText();
			String publishYear=publishYearText.getText();
			String[] strs= {bkName,bkAuthor,bkBrief,bkPress,bkCatalog,publishYear};
			for(int i=0;i<strs.length;i++) {
				if(strs[i].equals("")) {
					strs[i]="%";
				}
			}
				try {
					bk=bll.getBks(strs);
					setResult(bk);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	
		else if(e.getSource()==updateBtn) {
			int count=resutlTable.getSelectedRow();//获取你选中的行号（记录）
			if(count==-1) {
				JOptionPane.showMessageDialog(null,  "请选择需要修改的书籍","错误",JOptionPane.YES_NO_CANCEL_OPTION);
				return ;
			}
			else Bkid =	Integer.parseInt(resutlTable.getValueAt(count, 0).toString());
			Main.getNbPane().setVisible(false);
			Main.getCbPane().setVisible(true);						
			Main.getBrPane().setVisible(false);						
			Main.getPmPane().setVisible(false);						
			Main.getPmPane().setVisible(false);					
			Main.getRtmPane().setVisible(false);				
			Main.getRmPane().setVisible(false);
			Main.getSbPane().setVisible(false);
			try {
				ChangeBookInfoPane.setResult(Bkid);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
		else if (e.getSource() == deleteBtn)
		{
			Book book=new Book();
			int count=resutlTable.getSelectedRow();
			
			if(count==-1) {
				JOptionPane.showMessageDialog(null,  "请选择需要删除的类型","错误",JOptionPane.YES_NO_CANCEL_OPTION);
			}
			else {
				
					book.setBkID(Integer.parseInt(resutlTable.getValueAt(count, 0).toString()));
					bll.deletebook(book);
				
			}
		}
	
	
	
	}
}
