package DAL;

import java.sql.SQLException;

import Model.AbstractModel;

public abstract class AbstractDAL {

	public abstract AbstractModel[] getAllObjects() throws Exception;
	
	public abstract int insert(AbstractModel obj) throws Exception;
	
	public abstract int delete(AbstractModel obj) throws Exception;
	
	public abstract int update(AbstractModel obj) throws Exception;
	
	public abstract AbstractModel getObjectByID(int id) throws SQLException;
	
	public abstract String[] getPrettyColumnNames();
	
	public abstract String[] getMethodNames();
	
}
