package DAL;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.AbstractModel;
import Model.Book;
import Model.Reader;


public class ReaderDAL extends AbstractDAL{

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		Reader rd =(Reader) obj;
		String sql ="insert into TB_Reader "
				+"( rdName , rdSex,rdType , rdDept , rdPhone , rdEmail , rdDateReg , rdPhoto,rdPwd  )"
				+"values (?,?,?,?,?,?,getdate(),?,?)";
		Object[] params =new Object[] {
				//rd.getRdID(),	
				rd.getRdName(),
				rd.getRdSex(),
				rd.getRdType(),
				rd.getRdDept(),
				rd.getRdPhone(),
				rd.getRdEMail(),
				//rd注册日期
				rd.getRdPhoto(),
				//rd.getRdStatus(),
				//rd.getRdBorrowQty(),
				rd.getRdPwd(),
				//rd.getRdAdminRoles()
		};
		return SQLHelper.ExecSql(sql,params);
	}

	@Override
	public int delete(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		Reader rd = (Reader)obj;
		String sql ="delete fromTB_Reader where rdID = ?";
		Object[] params =new Object[] {
				rd.getRdID()
		};
		return SQLHelper.ExecSql(sql,params);
	}

	@Override
	public int update(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		Reader rd = (Reader)obj;
		String sql="update TB_Reader set "
					+"rdName =?, rdSex=?,rdType=? , rdDept=? , rdPhone=? , rdEmail=? ,  rdPhoto=? where rdID=?";
		Object[] params =new Object[] {
				
				rd.getRdName(),
				rd.getRdSex(),
				rd.getRdType(),
				rd.getRdDept(),
				rd.getRdPhone(),
				rd.getRdEMail(),
				//rd注册日期
				rd.getRdPhoto(),
				//rd.getRdStatus(),
				//rd.getRdBorrowQty(),
				//rd.getRdPwd(),
				//rd.getRdAdminRoles(),
				rd.getRdID()
		};
		int a=SQLHelper.ExecSql(sql, params);
		System.out.println(a);
		return a;
	}

	
	private Reader initReader(ResultSet rs) throws SQLException {
		Reader rd = new Reader();
		rd.setRdID(rs.getInt("rdID"));
		rd.setRdName(rs.getString("rdName"));
		rd.setRdSex(rs.getString("rdSex"));
		rd.setRdType(rs.getInt("rdType"));
		rd.setRdDept(rs.getString("rdDept"));
		rd.setRdPhone(rs.getString("rdPhone"));
		rd.setRdEMail(rs.getString("rdEMail"));
		rd.setRdDatereg(rs.getDate("rdDateReg"));
		rd.setRdPhoto(rs.getBytes("rdPhoto"));
		rd.setRdStatus(rs.getString("rdStatus"));
		rd.setRdBorrowQty(rs.getInt("rdBorrowQty"));
		rd.setRdPwd(rs.getString("rdPwd"));		
		
		
		return rd;
		
	}
	
	
	
	@Override
	public AbstractModel getObjectByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		Reader rd=null;
		String cmdText="select * from TB_Reader where rdID="+id;
		//[start]更新借书表
		String updateBrTB="exec usp_calcPunish "+id;
		try {
			SQLHelper.ExecSql(updateBrTB);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//[end]
		ResultSet rs;
		try {
			rs = SQLHelper.getResultSet(cmdText);
		
		
		if(rs!=null) {
			if(rs.next()) {
				rd=initReader(rs);
			}
			rs.close();
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rd;
	}

	public Reader[] getObjectByName(String name) throws SQLException, ClassNotFoundException {
		
		//String cmdText="select * from TB_Reader where rdName="+"'"+name+"'";
		//ResultSet rs;
		ArrayList<Reader> objects = new ArrayList<Reader>();
		ResultSet rs =SQLHelper.getResultSet("select * from TB_Reader where rdName="+"'"+name+"'");
		
		if(rs!=null) {
			while (rs.next()) {
				Reader Rd =initReader(rs);
				objects.add(Rd);
			}
			rs.close();
		}
		Reader[] rd=new Reader[objects.size()];
		objects.toArray(rd);
		
		
		return rd;
	}
	
	
	
	
	public int changeStatus(int id,String status) {
		
		String sql ="update TB_Reader set rdStatus=? where rdID=? ";
		Object[] params =new Object[] {
				status,id
		};
		try {
			return SQLHelper.ExecSql(sql,params);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	@Override
	public String[] getPrettyColumnNames() {
		// TODO Auto-generated method stub
		String[] ColumnNames=new String[]{
			"ID", "姓名","性别", "类型", "院系","电话","E-mail","状态","已借书","注册日期"};
		return ColumnNames;
	}

	@Override
	public String[] getMethodNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public Reader[] getObjectByTypeName(String rdType) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<Reader> objects = new ArrayList<Reader>();
		ResultSet rs =SQLHelper.getResultSet("select * from TB_Reader where rdType in (select rdType from TB_ReaderType where rdTypeName="+"'"+rdType+"'"+")");
		
		if(rs!=null) {
			while (rs.next()) {
				Reader Rd =initReader(rs);
				objects.add(Rd);
			}
			rs.close();
		}
		Reader[] rd=new Reader[objects.size()];
		objects.toArray(rd);
		
		
		return rd;
	}

	public Reader[] getObjectByDept(String rdDept) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<Reader> objects = new ArrayList<Reader>();
		ResultSet rs =SQLHelper.getResultSet("select * from TB_Reader where rdDept="+"'"+rdDept+"'" );
		
		if(rs!=null) {
			while (rs.next()) {
				Reader Rd =initReader(rs);
				objects.add(Rd);
			}
			rs.close();
		}
		Reader[] rd=new Reader[objects.size()];
		objects.toArray(rd);
		
		
		return rd;
	}

	public Reader[] getObjectByDeptAndTypeName(String rdDept, String rdType) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<Reader> objects = new ArrayList<Reader>();
		ResultSet rs =SQLHelper.getResultSet("select * from TB_Reader where "
				+ "rdType in (select rdType from TB_ReaderType where rdTypeName="+"'"+rdType+"'"+")"
				+"and rdDept="+"'"+rdDept+"'");
		
		if(rs!=null) {
			while (rs.next()) {
				Reader Rd =initReader(rs);
				objects.add(Rd);
			}
			rs.close();
		}
		Reader[] rd=new Reader[objects.size()];
		objects.toArray(rd);
		
		
		return rd;
	}

	public void updatePwd(String newPwd, int id) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql="update TB_Reader set rdPwd=? where rdID="+id;
		Object[] params =new Object[] {
			newPwd
		};
		SQLHelper.ExecSql(sql,params);
	}

}
