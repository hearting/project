package BLL;

import java.sql.SQLException;

import DAL.adminDAL;
import Model.Admin;
import Model.Reader;

public class adminBLL {
	private adminDAL dal=new adminDAL();
	
	
	
	public Admin getAdminByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		return (Admin) dal.getObjectByID(id);
	}



	public Reader getReaderByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	public void updateRdPwd(String newPwd, int id) {
		// TODO Auto-generated method stub
		try {
			dal.updatePwd(newPwd,id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void insertAd(Admin ad) {
		// TODO Auto-generated method stub
		try {
			dal.insert(ad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void updateAd(Admin ad) {
		// TODO Auto-generated method stub
		try {
			dal.update(ad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public Admin[] getAdByName(String text) {
		// TODO Auto-generated method stub

		try {
			Admin[] ad= {dal.getObjectByName(text)};
			return ad;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



	public void deleteAd(int id) {
		// TODO Auto-generated method stub
		try {
			dal.deleteByID(id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
