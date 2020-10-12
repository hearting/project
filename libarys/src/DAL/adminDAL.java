package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.AbstractModel;
import Model.Admin;
import Model.Book;
import Model.ReaderType;

public class adminDAL extends AbstractDAL{

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		ArrayList<Admin> objects = new ArrayList<Admin>();
		ResultSet rs =SQLHelper.getResultSet("select * from TB_Admin");
		
		if(rs!=null) {
			while (rs.next()) {
				Admin rt =initAdmin(rs);
				objects.add(rt);
			}
			rs.close();
		}
		Admin[] types=new Admin[objects.size()];
		objects.toArray(types);
		
		return types;
	}

	@Override
	public int insert(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		Admin admin=(Admin)obj;
		String sql="insert into TB_admin(adminName,adminEmail,dateReg,adminPwd,adminPhone,adminRole) "
					+" values (?,?,getdate(),?,?,0)";
		Object[] params =new Object[] {
				admin.getAdminName(),
				admin.getAdminEmail(),
				admin.getAdminPwd(),
				admin.getAdminPhone()
		};
		return SQLHelper.ExecSql(sql,params);
	}

	@Override
	public int delete(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		Admin admin=(Admin)obj;
		String sql="update TB_admin set adminName=?,adminEmail=?,adminPwd=?,adminPhone=? "
					+" where adminID=?";
		Object[] params =new Object[] {
				admin.getAdminName(),
				admin.getAdminEmail(),
				admin.getAdminPwd(),
				admin.getAdminPhone(),
				admin.getAdminID()
		};
		return SQLHelper.ExecSql(sql,params);
	}

	@Override
	public AbstractModel getObjectByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		Admin ad=null;
		String cmdText="select  adminID,adminName,adminPhone,adminEmail,dateReg,adminPwd,adminRole"
						+" from TB_admin where adminID="+id;
		ResultSet rs;
		try {
			rs = SQLHelper.getResultSet(cmdText);
		
		
		if(rs!=null) {
			if(rs.next()) {
				ad=initAdmin(rs);
			}
			rs.close();
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ad;
	}

	private Admin initAdmin(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Admin ad=new Admin();
		ad.setAdminID(rs.getInt("adminID"));
		ad.setAdminName(rs.getString("adminName"));
		ad.setAdminPhone(rs.getString("adminPhone"));
		ad.setAdminEmail(rs.getString("adminEmail"));
		ad.setDateReg(rs.getDate("dateReg"));
		ad.setAdminPwd(rs.getString("adminPwd"));
		ad.setAdminRole(rs.getInt("adminRole"));
		return ad;
	}
	

	@Override
	public String[] getPrettyColumnNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getMethodNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updatePwd(String newPwd, int id) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql="update TB_Admin set adminPwd=? where adminID="+id;
		Object[] params =new Object[] {
				newPwd
			};
			SQLHelper.ExecSql(sql,params);
	}

	public Admin getObjectByName(String text) throws SQLException {
		// TODO Auto-generated method stub
		Admin ad=null;
		String cmdText="select  adminID,adminName,adminPhone,adminEmail,dateReg,adminPwd,adminRole"
						+" from TB_admin where adminName="+"'"+text+"'";
		ResultSet rs;
		try {
			rs = SQLHelper.getResultSet(cmdText);
		
		
		if(rs!=null) {
			if(rs.next()) {
				ad=initAdmin(rs);
			}
			rs.close();
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ad;
	}

	public void deleteByID(int id) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql="delete from TB_Admin where adminID=?";
		Object[] params =new Object[] {
				id
			};
			SQLHelper.ExecSql(sql,params);
	}

}
