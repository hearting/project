package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.AbstractModel;
import Model.Borrow;
import Model.ReaderType;

public class BorrowDAL extends AbstractDAL{

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Borrow> objects = new ArrayList<Borrow>();
		ResultSet rs =SQLHelper.getResultSet("select * from TB_Borrow");
		
		if(rs!=null) {
			while (rs.next()) {
				Borrow br =initBorrow(rs);
				objects.add(br);
			}
			rs.close();
		}
		Borrow[] BR=new Borrow[objects.size()];
		objects.toArray(BR);
		
		return BR;
	}

	private Borrow initBorrow(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Borrow br=new Borrow();
		
		br.setBorrowID(rs.getLong("BorrowID"));
		br.setRdID(rs.getInt("rdID"));
		br.setBkID(rs.getInt("bkID"));
		br.setLdContinueTimes(rs.getInt("ldContinueTimes"));
		br.setLdDateOut(rs.getDate("ldDateOut"));
		br.setLdDateRetPlan(rs.getDate("ldDateRetPlan"));
		br.setLdDateRetAct(rs.getDate("ldDateRetAct"));
		br.setLdOverDay(rs.getInt("ldOverDay"));
		br.setLdOverMoney(rs.getFloat("ldOverMoney"));
		br.setLdPunishMoney(rs.getFloat("ldPunishMoney"));
		br.setIsHasReturn(rs.getBoolean("IsHasReturn"));
		br.setOperatorLend(rs.getInt("OperatorIDLend"));
		br.setOperatorRet(rs.getInt("OperatorRet"));
		
		
		
		return br;
	}

	@Override
	public int insert(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		Borrow br=(Borrow) obj;
		String sql="insert into TB_Borrow (rdID,bkID,ldDateOut,OperatorIDLend)"
				+ "values(?,?,getdate(),?)";
		Object[] params =new Object[] {
				//br.getBorrowID(),
				br.getRdID(),
				br.getBkID(),
				//br.getLdContinueTimes(),
				//br.getLdDateOut(),
				
				//br.getLdDateRetPlan(),
				//br.getLdDateRetPlan(),
				//br.getLdOverDay(),
				//br.getLdOverMoney(),
				//br.getLdPunishMoney(),
				//br.isIsHasReturn(),
				br.getOperatorLend(),
				//br.getOperatorRet()
		};
		
		return SQLHelper.ExecSql(sql,params);
	}

	public int delete(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		Borrow br =(Borrow)obj;
		String sql="delete from TB_Borrow where BorrowID=?";
		Object[] params =new Object[] {
				br.getBorrowID()		};
		return SQLHelper.ExecSql(sql,params);
	}
	
	public int deleteb(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		Borrow br =(Borrow)obj;
		String sql="delete from TB_Borrow where bkID=? and rdID=?";
		Object[] params =new Object[] {
				br.getBkID(),
				br.getRdID()};
		return SQLHelper.ExecSql(sql,params);
	}
	

	@Override
	public int update(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		
		Borrow br =(Borrow)obj;
		String sql="update TB_Borrow set ldContinueTimes=?,ldDateRetPlan=?,ldDateRetAct=?, where BorrowID=?";
		Object[] params =new Object[] {
				br.getBorrowID()		};
		return SQLHelper.ExecSql(sql,params);
	}

	public Borrow[] getObjectsByID(int RdID) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<Borrow> objects = new ArrayList<Borrow>();
		ResultSet rs =SQLHelper.getResultSet("select * from TB_Borrow where rdID="+RdID);
		
		if(rs!=null) {
			while (rs.next()) {
				Borrow br =initBorrow(rs);
				objects.add(br);
			}
			rs.close();
		}
		Borrow[] BR=new Borrow[objects.size()];
		objects.toArray(BR);
		
		return BR;
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

	@Override
	public AbstractModel getObjectByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void returnBkByBKID(int bkID,int retOperator) {
		// TODO Auto-generated method stub
		String returnbk="exec usp_returnBook "+bkID+" ,"+retOperator;
		try {
			SQLHelper.ExecSql(returnbk);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void reRnewBk(int bkID, int retOperator) {
		// TODO Auto-generated method stub
		String renewBk="exec usp_reNewBook "+bkID+","+retOperator;
		try {
			SQLHelper.ExecSql(renewBk);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
