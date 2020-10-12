package Model;

import java.util.Date;

public class Borrow extends AbstractModel{
	private long BorrowID;
	private int rdID;
	private int bkID;
	private int ldContinueTimes;
	private Date ldDateOut;
	private Date ldDateRetPlan;
	private Date ldDateRetAct;
	private int ldOverDay;
	private float ldOverMoney;
	private float ldPunishMoney;
	private boolean IsHasReturn;
	private int OperatorIDLend;
	private int OperatorIDRet;
	public long getBorrowID() {
		return BorrowID;
	}
	public void setBorrowID(long borrowID) {
		BorrowID = borrowID;
	}
	public int getRdID() {
		return rdID;
	}
	public void setRdID(int rdID) {
		this.rdID = rdID;
	}
	public int getBkID() {
		return bkID;
	}
	public void setBkID(int bkID) {
		this.bkID = bkID;
	}
	public int getLdContinueTimes() {
		return ldContinueTimes;
	}
	public void setLdContinueTimes(int ldContinueTimes) {
		this.ldContinueTimes = ldContinueTimes;
	}
	public Date getLdDateOut() {
		return ldDateOut;
	}
	public void setLdDateOut(Date ldDateOut) {
		this.ldDateOut = ldDateOut;
	}
	public Date getLdDateRetPlan() {
		return ldDateRetPlan;
	}
	public void setLdDateRetPlan(Date ldDateRetPlan) {
		this.ldDateRetPlan = ldDateRetPlan;
	}
	public Date getLdDateRetAct() {
		return ldDateRetAct;
	}
	public void setLdDateRetAct(Date ldDateRetAct) {
		this.ldDateRetAct = ldDateRetAct;
	}
	public int getLdOverDay() {
		return ldOverDay;
	}
	public void setLdOverDay(int ldOverDay) {
		this.ldOverDay = ldOverDay;
	}
	public float getLdOverMoney() {
		return ldOverMoney;
	}
	public void setLdOverMoney(float ldOverMoney) {
		this.ldOverMoney = ldOverMoney;
	}
	public float getLdPunishMoney() {
		return ldPunishMoney;
	}
	public void setLdPunishMoney(float ldPunishMoney) {
		this.ldPunishMoney = ldPunishMoney;
	}
	public boolean isIsHasReturn() {
		return IsHasReturn;
	}
	public void setIsHasReturn(boolean isHasReturn) {
		IsHasReturn = isHasReturn;
	}
	public int getOperatorLend() {
		return OperatorIDLend;
	}
	public void setOperatorLend(int operatorLend) {
		OperatorIDLend = operatorLend;
	}
	public int getOperatorRet() {
		return OperatorIDRet;
	}
	public void setOperatorRet(int operatorRet) {
		OperatorIDRet = operatorRet;
	}
}
