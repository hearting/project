package BLL;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAL.BookDAL;
import Model.Book;

public class BookAdmin {
    private BookDAL dal=new BookDAL(); 
	
    
    public void insertBook(Book bk) throws Exception {
		// TODO Auto-generated method stub
		dal.insert(bk);
	}


	public Book[] getBkByStr(String str,String selectType) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return dal.getObjectByStr(str,selectType);
	}


	public Book[] getBks(String[] strs) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return dal.getObjects(strs);
	}


	public Book getBookByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		
		return (Book)(dal.getObjectByID(id));
	}


	public void updateBook(Book bk) throws Exception {
		// TODO Auto-generated method stub
		dal.update(bk);
	}
	
	public void deletebook(Book bk) {
		try {
			if(JOptionPane.showConfirmDialog(null,"确定将删除所选内容", "提示",
					JOptionPane.OK_CANCEL_OPTION)==0)
			{
			dal.delete(bk);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
