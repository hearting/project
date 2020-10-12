package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.AbstractModel;
import Model.ReaderType;

public class ReaderTypeDAL extends AbstractDAL{

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<ReaderType> objects = new ArrayList<ReaderType>();
		ResultSet rs =SQLHelper.getResultSet("select * from TB_ReaderType");
		
		if(rs!=null) {
			while (rs.next()) {
				ReaderType rt =initReaderType(rs);
				objects.add(rt);
			}
			rs.close();
		}
		ReaderType[] types=new ReaderType[objects.size()];
		objects.toArray(types);
		
		return types;
	}

	@Override
	public int insert(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		ReaderType rt = (ReaderType)obj;
		
		String sql=" insert into TB_ReaderType"
					+"(rdTypeName,CanLendQty,CanLendDay,CanContinueTimes,PunishRate,DateValid)"
					+"values(?,?,?,?,?,?)";
		
		Object[] params =new Object[] {
				//rt.getRdType(),	
				rt.getRdTypeName(),
				rt.getCanLendQty(),
				rt.getCanLendDay(),
				rt.getCanContinueTimes(),
				rt.getPunishRate(),
				rt.getDateValid()		
		};
		
		
		return SQLHelper.ExecSql(sql,params);
	}

	@Override
	public int delete(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		ReaderType rt = (ReaderType)obj;
		String sql ="delete from   TB_ReaderType where rdType = ?";
		Object[] params =new Object[] {
				rt.getRdType()
		};
		return SQLHelper.ExecSql(sql,params);
	}

	@Override
	public int update(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		
		ReaderType rt = (ReaderType)obj;
		String sql="update TB_ReaderType set "  
				+"rdTypeName=? ,CanLendQty=?,CanLendDay=?,CanContinueTimes=?,PunishRate=?,DateValid=? where rdType =?";
		
		Object[] params =new Object[] {
				rt.getRdTypeName(),
				rt.getCanLendQty(),
				rt.getCanLendDay(),
				rt.getCanContinueTimes(),
				rt.getPunishRate(),
				rt.getDateValid(),
				rt.getRdType()
		};
		
		
		return SQLHelper.ExecSql(sql, params);
	}

	@Override
	public AbstractModel getObjectByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		ReaderType rt=null;
		String cmdText="select * from TB_ReaderType where rdType="+id;
		ResultSet rs;
		try {
			rs = SQLHelper.getResultSet(cmdText);
		
		
		if(rs!=null) {
			if(rs.next()) {
				rt=initReaderType(rs);
			}
			rs.close();
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;
	}

	private ReaderType initReaderType(ResultSet rs) throws SQLException{
		ReaderType rt =new ReaderType();
		rt.setRdType(rs.getInt("rdType"));
		rt.setRdTypeName(rs.getString("rdTypeName"));
		rt.setCanLendQty(rs.getInt("CanLendQty"));
		rt.setCanLendDay(rs.getInt("CanLendDay"));
		rt.setCanContinueTimes(rs.getInt("CanContinueTimes"));
		rt.setPunishRate(rs.getFloat("PunishRate"));
		rt.setDateValid(rs.getInt("DateValid"));
		return rt;
		
	}
	
	@Override
	public String[] getPrettyColumnNames() {
		// TODO Auto-generated method stub
		String[] ColumnNames=new String[] {
				"ID", "读者类型","可借数量", "可接天数", "课续借次数","罚金率"	
		};
		return ColumnNames;
	}

	@Override
	public String[] getMethodNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
