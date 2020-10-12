package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Main extends JFrame implements ActionListener{
	private JMenuBar menu = new JMenuBar();
	private JMenu readerManage=new JMenu("读者管理");
	private JMenu bookManage=new JMenu("图书管理");
	private JMenu borrowManage=new JMenu("借阅管理");
	private JMenu userManage=new JMenu("用户管理");
	
	public static newBookPane getNbPane() {
		return nbPane;
	}

	public static ChangeBookInfoPane getCbPane() {
		return cbPane;
	}

	public static BorrowPane getBrPane() {
		return brPane;
	}

	public static PermissionMgtPane getPmPane() {
		return pmPane;
	}

	public static readerManagePane getRmPane() {
		return rmPane;
	}

	public static readerTypeManagePane getRtmPane() {
		return rtmPane;
	}

	public static UpdatePassword getUpPane() {
		return upPane;
	}

	public static selectBookPane getSbPane() {
		return sbPane;
	}

	//菜单栏按钮
		//读者管理
	private JMenuItem MI_ReaderMgt=new JMenuItem("借书证管理");
	
	private JMenuItem MI_ReaderTypeMgt=new JMenuItem("读者类型管理");
		//图书管理
	private JMenuItem MI_NewBook=new JMenuItem("新书入库");
	private JMenuItem MI_SelectBook=new JMenuItem("图书查询");

		//借阅管理
	private JMenuItem MI_Borrow=new JMenuItem("借书");
	
		//用户管理
	private JMenuItem MI_PermissionMgt=new JMenuItem("图书管理员管理");
	private JMenuItem MI_UpdatePassword=new JMenuItem("密码修改"); 
	
	
	private final static newBookPane nbPane=new newBookPane();
	private final static ChangeBookInfoPane cbPane=new ChangeBookInfoPane();
	private final static BorrowPane brPane=new BorrowPane();
	private final static PermissionMgtPane pmPane=new PermissionMgtPane();
	private final static readerManagePane rmPane=new readerManagePane();
	private final static readerTypeManagePane rtmPane=new readerTypeManagePane();
	private final static UpdatePassword upPane=new UpdatePassword();
	private final static selectBookPane sbPane=new selectBookPane();
	
	
	
	//[start] 设置panel  
	private void setPane() {
		getContentPane().setLayout(null);
		
		getContentPane().add(nbPane);
		nbPane.setVisible(false);
		
		
		getContentPane().add(cbPane);
		cbPane.setVisible(false);
		
		getContentPane().add(brPane);
		brPane.setVisible(false);
		
		getContentPane().add(pmPane);
		pmPane.setVisible(false);
		
		getContentPane().add(rmPane);
		rmPane.setVisible(false);
		
		
		
		getContentPane().add(rtmPane);
		rtmPane.setVisible(false);
		
		
		
		getContentPane().add(upPane);
		upPane.setVisible(false);
		
		getContentPane().add(sbPane);
		sbPane.setVisible(false);
	}
	//[end]	  
		
	//[start] 菜单按钮设置
	private void setMenuItem() {
		MI_NewBook.addActionListener(this);
		MI_SelectBook.addActionListener(this);
		
		MI_ReaderMgt.addActionListener(this);
		
		MI_ReaderTypeMgt.addActionListener(this);
		
		MI_Borrow.addActionListener(this);
		MI_PermissionMgt.addActionListener(this);
		MI_UpdatePassword.addActionListener(this);
		
		readerManage.add(MI_ReaderMgt);
		readerManage.add(MI_ReaderTypeMgt);
		
		bookManage.add(MI_NewBook);
		bookManage.add(MI_SelectBook);
		
		borrowManage.add(MI_Borrow);
		
		userManage.add(MI_PermissionMgt);
		userManage.add(MI_UpdatePassword);
		
		if(Login.rd!=null) {
			MI_ReaderMgt.setVisible(false);;
			bookManage.setVisible(false);
			MI_PermissionMgt.setVisible(false);
		}
		else if(Login.admin!=null) {
			if(Login.admin.getAdminRole()==0) {
				MI_PermissionMgt.setVisible(false);
			}
			else {
				
			}
		}
	}
	//[end]
	public Main() {
		setTitle("长江大学图书馆管理信息系统");
		setPane();
		Font font = new Font("宋体", Font.BOLD, 20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(60, 15, 1300, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setMenuItem();
		menu=new JMenuBar();
		menu.add(readerManage);
		menu.add(bookManage);
		menu.add(borrowManage);
		menu.add(userManage);
		this.setJMenuBar(menu);
		this.setVisible(true); 	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==MI_NewBook) {
			
			  nbPane.setVisible(true);
			  cbPane.setVisible(false);
			  brPane.setVisible(false);
			  pmPane.setVisible(false);
			  rmPane.setVisible(false); 
			  rtmPane.setVisible(false);
			  upPane.setVisible(false); 
			  sbPane.setVisible(false);
		}
		
		else if(e.getSource()==MI_ReaderMgt) {
			
			  nbPane.setVisible(false); 
			  cbPane.setVisible(false); 
			  brPane.setVisible(false);
			  pmPane.setVisible(false); 
			  rmPane.setVisible(true); 
			  rtmPane.setVisible(false); 
			  upPane.setVisible(false); 
			  sbPane.setVisible(false);
		}
		
		else if(e.getSource()==MI_ReaderTypeMgt) {
			nbPane.setVisible(false);
			cbPane.setVisible(false);						
			brPane.setVisible(false);						
			pmPane.setVisible(false);						
			rmPane.setVisible(false);			
			rtmPane.setVisible(true);			
			upPane.setVisible(false);
			sbPane.setVisible(false);
		}
		
		else if(e.getSource()==MI_Borrow) {
			nbPane.setVisible(false);
			cbPane.setVisible(false);						
			brPane.setVisible(true);						
			pmPane.setVisible(false);						
			rmPane.setVisible(false);			
			rtmPane.setVisible(false);			
			upPane.setVisible(false);
			sbPane.setVisible(false);
		}
		
		else if(e.getSource()==MI_PermissionMgt) {
			nbPane.setVisible(false);
			cbPane.setVisible(false);						
			brPane.setVisible(false);						
			pmPane.setVisible(true);						
			rmPane.setVisible(false);					
			rtmPane.setVisible(false);			
			upPane.setVisible(false);
			sbPane.setVisible(false);
		}
		
		else if(e.getSource()==MI_UpdatePassword) {
			nbPane.setVisible(false);
			cbPane.setVisible(false);						
			brPane.setVisible(false);						
			pmPane.setVisible(false);						
			rmPane.setVisible(false);				
			rtmPane.setVisible(false);				
			upPane.setVisible(true);
			sbPane.setVisible(false);
		}
		
		else if(e.getSource()==MI_SelectBook) {
			nbPane.setVisible(false);
			cbPane.setVisible(false);						
			brPane.setVisible(false);						
			pmPane.setVisible(false);						
			rmPane.setVisible(false);						
			rtmPane.setVisible(false);				
			upPane.setVisible(false);
			sbPane.setVisible(true);
		}

	}
}


