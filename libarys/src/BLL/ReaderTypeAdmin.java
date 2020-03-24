package BLL;

import javax.swing.JOptionPane;

import DAL.ReaderTypeDAL;
import Model.ReaderType;

public class ReaderTypeAdmin {
	ReaderTypeDAL dal =new ReaderTypeDAL();
	
	
	
	
	public ReaderType[] getReaderTypes() {
		try {
			return (ReaderType[])dal.getAllObjects();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertRdType(ReaderType rt) {
		try {
			dal.insert(rt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteRdType(ReaderType rt) {
		try {
			if(JOptionPane.showConfirmDialog(null,"确定将删除所选内容", "提示",
					JOptionPane.OK_CANCEL_OPTION)==0)
			{
			dal.delete(rt);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateRdType(ReaderType rt) {
		try {
			dal.update(rt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
