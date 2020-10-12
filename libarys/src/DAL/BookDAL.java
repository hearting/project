package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.AbstractModel;
import Model.Book;
import Model.ReaderType;

public class BookDAL extends AbstractDAL{

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Book> objects = new ArrayList<Book>();
		ResultSet rs =SQLHelper.getResultSet("select * from TB_Book");
		
		if(rs!=null) {
			while (rs.next()) {
				Book BK =initBook(rs);
				objects.add(BK);
			}
			rs.close();
		}
		Book[] bk=new Book[objects.size()];
		objects.toArray(bk);
		
		return bk;
	}

	private Book initBook(ResultSet rs) throws SQLException {
		Book bk=new Book();
		bk.setBkID(rs.getInt("bkID"));
		bk.setBkCode(rs.getString("bkCode"));
		bk.setBkName(rs.getString("bkName"));
		bk.setBkAythor(rs.getString("bkAuthor"));
		bk.setBkPress(rs.getString("bkPress"));
		bk.setBkDatePress(rs.getString("bkDatePress"));
		bk.setBkISBN(rs.getString("bkISBN"));
		bk.setBkPages(rs.getInt("bkPages"));
		bk.setBkCatalog(rs.getString("bkCatalog"));
		bk.setBkLanguage(rs.getInt("bkLanguage"));
		bk.setBkPrice(rs.getFloat("bkPrice"));
		bk.setBkDateIn(rs.getDate("bkDateIn"));
		bk.setBkBrief(rs.getString("bkBrief"));
		bk.setBkCover(rs.getBytes("bkCover"));
		bk.setBkStatus(rs.getString("bkStatus"));
		
		return bk;
		
	}
	
	
	
	@Override
	public int insert(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		Book bk = (Book)obj;
		
		String sql=" insert into TB_Book"
					+"(bkCode,bkName,bkAuthor,bkPress,bkDatePress,bkISBN,"
					+ "bkCatalog,bkLanguage,bkPages,bkPrice,bkDateIn,bkBrief,bkCover)"
					+"values(?,?,?,?,?,?,?,?,?,?,getdate(),?,?)";
		
		Object[] params =new Object[] {
				//bk.getBkID(),
				bk.getBkCode(),
				bk.getBkName(),
				bk.getBkAythor(),
				bk.getBkPress(),
				bk.getBkDatePress(),
				bk.getBkISBN(),
				bk.getBkCatalog(),
				bk.getBkLanguage(),
				bk.getBkPages(),
				bk.getBkPrice(),
				//datein
				bk.getBkBrief(),
				bk.getBkCover(),
				//bk.getBkStatus()		
		};
		
		
		return SQLHelper.ExecSql(sql,params);
	}

	@Override
	public int delete(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		Book bk = (Book)obj;
		String sql ="delete from TB_Book where bkID = ?";
		Object[] params =new Object[] {
				bk.getBkID()
		};
		return SQLHelper.ExecSql(sql,params);
	}

	@Override
	public int update(AbstractModel obj) throws Exception {
		// TODO Auto-generated method stub
		Book bk = (Book)obj;
		
		String sql=" update  TB_Book set "
					+"bkCode=?,bkName=?,bkAuthor=?,bkPress=?,bkDatePress=?,bkISBN=?,"
					+ "bkCatalog=?,bkLanguage=?,bkPages=?,bkPrice=?,bkBrief=?,bkCover=?,bkStatus=?"
					+" where bkID=?";
		
		Object[] params =new Object[] {
				
				bk.getBkCode(),
				bk.getBkName(),
				bk.getBkAythor(),
				bk.getBkPress(),
				bk.getBkDatePress(),
				bk.getBkISBN(),
				bk.getBkCatalog(),
				bk.getBkLanguage(),
				bk.getBkPages(),
				bk.getBkPrice(),
				//datein
				bk.getBkBrief(),
				bk.getBkCover(),
				bk.getBkStatus(),
				bk.getBkID()
		};
		return SQLHelper.ExecSql(sql,params);
	}

	@Override
	public AbstractModel getObjectByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		Book bk=null;
		String cmdText="select bkID,bkCode,bkName,bkAuthor,bkPress,bkDatePress,bkISBN,"
						+ "bkCatalog,bkLanguage,bkPages,bkPrice,bkDateIn,bkBrief,bkCover,bkStatus "
						+"from TB_Book where bkID="+id;
		ResultSet rs;
		try {
			rs = SQLHelper.getResultSet(cmdText);
		
		
		if(rs!=null) {
			if(rs.next()) {
				bk=initBook(rs);
			}
			rs.close();
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bk;
	}

	@Override
	public String[] getPrettyColumnNames() {
		// TODO Auto-generated method stub
		String[] ColumnNames={"ID", "索书号","书名", "作者", "出版社","出版日期","ISBN","分类号","语种","页数","价格","入馆时间","状态" };
		
		return ColumnNames;
	}

	@Override
	public String[] getMethodNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public Book[] getObjectByStr(String str,String selectType) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<Book> objects = new ArrayList<Book>();
		String cmdText="select bkID,bkCode,bkName,bkAuthor,bkPress,bkDatePress,bkISBN,"
						+ "bkCatalog,bkLanguage,bkPages,bkPrice,bkDateIn,bkBrief,bkCover,bkStatus"
						+" from TB_Book where "+selectType+" like "+" '"+"%"+str+"%"+"'";
		ResultSet rs =SQLHelper.getResultSet(cmdText);
		if(rs!=null) {
			while (rs.next()) {
				Book BK =initBook(rs);
				objects.add(BK);
			}
			rs.close();
		}
		Book[] bk=new Book[objects.size()];
		objects.toArray(bk);
		
		return bk;
	}

	public Book[] getObjects(String[] strs) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<Book> objects = new ArrayList<Book>();
		String cmdText="select bkID,bkCode,bkName,bkAuthor,bkPress,bkDatePress,bkISBN,"
						+ "bkCatalog,bkLanguage,bkPages,bkPrice,bkDateIn,bkBrief,bkCover,bkStatus"
						+" from TB_Book where "
						+"bkName like "+"'%"+strs[0]+"%'" +" and "
						+"bkAuthor like "+"'%"+strs[1]+"%'" +" and "
						+"bkBrief like "+"'%"+strs[2]+"%'" +" and "
						+"bkPress like "+"'%"+strs[3]+"%'" +" and "
						+"bkCatalog like "+"'%"+strs[4]+"%'" +" and "
						+"bkDatePress like "+"'%"+strs[5]+"%'";
		ResultSet rs =SQLHelper.getResultSet(cmdText);
		if(rs!=null) {
			while (rs.next()) {
				Book BK =initBook(rs);
				objects.add(BK);
			}
			rs.close();
		}
		Book[] bk=new Book[objects.size()];
		objects.toArray(bk);
		
		return bk;
	}

}
