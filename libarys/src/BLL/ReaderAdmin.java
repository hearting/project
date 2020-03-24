package BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAL.ReaderDAL;
import DAL.ReaderTypeDAL;
import DAL.SQLHelper;
import Model.Reader;
import Model.ReaderType;

public class ReaderAdmin {
	private ReaderDAL dal=new ReaderDAL();
	private ReaderTypeDAL rtDal=new ReaderTypeDAL();
	
	
	public void insertReader(Reader rd) {
		if(rd.getRdPwd().equals("")) rd.setRdPwd("123");
		if(rd.getRdName().equals("")) {
			JOptionPane.showMessageDialog(null, "信息填写不全", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			return;
		}
		try {
			dal.insert(rd);
			JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ReplaceReader(String NowStatus,Reader rd) {
		if(rd.getRdName().equals("")) {
			JOptionPane.showMessageDialog(null, "信息填写不全", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			return;
		}
		if(NowStatus.equals("挂失")) {
			changeStatus(rd.getRdID(), "注销");
			insertReader(rd);
			JOptionPane.showMessageDialog(null, "补办成功", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			}
			
			else {
				JOptionPane.showMessageDialog(null, "当前状态不可补办", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			}	
	}
	
	
	public String[] getAllTypeName() {
	
		ReaderTypeAdmin typeDal =new ReaderTypeAdmin();
	
		ReaderType[] rt=typeDal.getReaderTypes();
		ArrayList<String> objects = new ArrayList<String>();
		for(int i=0;i<rt.length;i++) {
			String str=rt[i].getRdTypeName();
			objects.add(str);
		}
		String[] RS=new String[objects.size()];
		objects.toArray(RS);
		return RS;
	}
	
	public String getTypeNameByID(int id) throws SQLException {
		ReaderType rt=(ReaderType) rtDal.getObjectByID(id);
		return rt.getRdTypeName();
	}
	
	public int getTypeFromCmbx(String name) throws SQLException {
		
		String sql ="select * from TB_ReaderType where rdTypeName=? ";
		Object[] params =new Object[] {
			name	
		};
		try {
			ResultSet rs=SQLHelper.getResultSet(sql,params);
			rs.next();
			return rs.getInt("rdType");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
	public Reader[] getReaderByName(String name) throws SQLException, ClassNotFoundException {
		return dal.getObjectByName(name);
	}
	

	public Reader getRdByID(int id) {
		// TODO Auto-generated method stub
		try {
			return (Reader)dal.getObjectByID(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public void changeStatus(int id,String status) {
		dal.changeStatus(id, status);
	}


	public void updateReader(Reader rd) {
		if(JOptionPane.showConfirmDialog(null,"确定将保存，且不可恢复", "提示",JOptionPane.OK_CANCEL_OPTION)==0){
			try {
				dal.update(rd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public Reader[] getReaderByTypeName(String rdType) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return	dal.getObjectByTypeName(rdType);
		 
	}


	public Reader[] getReaderByDept(String rdDept) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return	dal.getObjectByDept(rdDept);
	}





	public Reader[] getReaderByDeptAndTypeName(String rdDept, String rdType) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return	dal.getObjectByDeptAndTypeName(rdDept,rdType);
	}

	public void LostReader(String NowStatus,int id) {
		if(NowStatus.equals("有效")){
			changeStatus(id, "挂失");
			JOptionPane.showMessageDialog(null, "挂失操作成功", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			}
			else {
				JOptionPane.showMessageDialog(null, "当前状态不可进行挂失操作", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			}
	}
	
	public void RelLostReader(String NowStatus,int id) {
		if(NowStatus.equals("挂失")){
			changeStatus(id, "有效");
			JOptionPane.showMessageDialog(null, "解除挂失操作成功", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			}
			else {
				JOptionPane.showMessageDialog(null, "当前状态不可进行解除挂失操作", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			}
	}


	public void CancelReader(int borrowedQty, int iD) {
		// TODO Auto-generated method stub
		if(borrowedQty>0) {
			JOptionPane.showMessageDialog(null, "有未归还书籍，不可注销", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
		}
		else {
			if(JOptionPane.showConfirmDialog(null,"确定将注销，且不可恢复", "提示",JOptionPane.OK_CANCEL_OPTION)==0){
				changeStatus(iD, "注销");
			}
		}
	}

	public void updateRdPwd(String newPwd,int id) {
		// TODO Auto-generated method stub
		try {
			dal.updatePwd(newPwd,id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
