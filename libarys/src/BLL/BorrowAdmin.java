package BLL;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAL.BookDAL;
import DAL.BorrowDAL;
import DAL.ReaderDAL;
import DAL.ReaderTypeDAL;
import Model.Book;
import Model.Borrow;
import Model.Reader;
import Model.ReaderType;

public class BorrowAdmin {
	ReaderDAL rdDAL=new ReaderDAL();
	ReaderTypeDAL rtDAL=new ReaderTypeDAL();
	BorrowDAL brDAL=new BorrowDAL();
	BookDAL bkDAL=new BookDAL();
	
	public Reader getReaderByID(int id) {
		// TODO Auto-generated method stub
		try {
			return (Reader) rdDAL.getObjectByID(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public ReaderType getRdTypeByid(int rdType) {
		// TODO Auto-generated method stub
		try {
			return (ReaderType) rtDAL.getObjectByID(rdType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public Borrow[] getBrsByRdID(int rdID) {
		// TODO Auto-generated method stub
		
		try {
				return brDAL.getObjectsByID(rdID);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return null;
	}


	public Book[] getBksByBkID(Borrow[] brs)  {
		// TODO Auto-generated method stub
		ArrayList<Book> objects = new ArrayList<Book>();
		for(int i=0;i<brs.length;i++) {
			try {
				Book bk=(Book) bkDAL.getObjectByID(brs[i].getBkID());
				objects.add(bk);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Book[] BK=new Book[objects.size()];
		objects.toArray(BK);
		
		return BK;
	}


	public void insertBr(int rdID, int bkID, int lendOperator) {
		// TODO Auto-generated method stub
		Borrow br=new Borrow();
		br.setBkID(bkID);
		br.setRdID(rdID);
		br.setOperatorLend(lendOperator);
		try {
			brDAL.insert(br);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//br.
	}

	public void deleteBr(Borrow b) {
		try {
			brDAL.deleteb(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void returnBook(int bkID, int retOperator) {
		// TODO Auto-generated method stub
		brDAL.returnBkByBKID(bkID,retOperator);
	}


	public void reNewBook(int bkID, int retOperator) {
		// TODO Auto-generated method stub
		brDAL.reRnewBk(bkID,retOperator);
	}

	public void deleteBorrow(Borrow b) {
		try {
			if(JOptionPane.showConfirmDialog(null,"确定将删除所选内容", "提示",
					JOptionPane.OK_CANCEL_OPTION)==0)
			{
				brDAL.delete(b);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
