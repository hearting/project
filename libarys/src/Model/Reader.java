package Model;

import java.sql.Date;

public class Reader extends AbstractModel{
	private int rdID;
	private String rdName;
	private String rdSex;
	private int rdType;
	private String rdDept;
	private String rdPhone;
	private String rdEMail;
	private Date rdDatereg;
	private byte[] rdPhoto;
	private String rdStatus;
	private int rdBorrowQty;
	private String rdPwd;
	//private int rdAdminRoles;
	public int getRdID() {
		return rdID;
	}
	public void setRdID(int rdID) {
		this.rdID = rdID;
	}
	public String getRdName() {
		return rdName;
	}
	public void setRdName(String rdName) {
		this.rdName = rdName;
	}
	public String getRdSex() {
		return rdSex;
	}
	public void setRdSex(String rdSex) {
		this.rdSex = rdSex;
	}
	public int getRdType() {
		return rdType;
	}
	public void setRdType(int rdType) {
		this.rdType = rdType;
	}
	public String getRdDept() {
		return rdDept;
	}
	public void setRdDept(String rdDept) {
		this.rdDept = rdDept;
	}
	public String getRdPhone() {
		return rdPhone;
	}
	public void setRdPhone(String rdPhone) {
		this.rdPhone = rdPhone;
	}
	public String getRdEMail() {
		return rdEMail;
	}
	public void setRdEMail(String rdEMail) {
		this.rdEMail = rdEMail;
	}
	public Date getRdDatereg() {
		return rdDatereg;
	}
	public void setRdDatereg(Date rdDatereg) {
		this.rdDatereg = rdDatereg;
	}
	public byte[] getRdPhoto() {
		return rdPhoto;
	}
	public void setRdPhoto(byte[] rdPhoto) {
		this.rdPhoto = rdPhoto;
	}
	public String getRdStatus() {
		return rdStatus;
	}
	public void setRdStatus(String rdStatus) {
		this.rdStatus = rdStatus;
	}
	public int getRdBorrowQty() {
		return rdBorrowQty;
	}
	public void setRdBorrowQty(int rdBorrowQty) {
		this.rdBorrowQty = rdBorrowQty;
	}
	public String getRdPwd() {
		return rdPwd;
	}
	public void setRdPwd(String rdPwd) {
		this.rdPwd = rdPwd;
	}
	
	
}
