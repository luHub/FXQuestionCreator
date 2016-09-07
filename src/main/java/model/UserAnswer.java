package model;

public class UserAnswer {

	private STATUS status;
	private boolean aCheck;
	private boolean bCheck;
	private boolean cCheck;
	private boolean dCheck;
	
	public STATUS getStatus() {
		return status;
	}
	public void setStatus(STATUS status) {
		this.status = status;
	}
	public boolean isaCheck() {
		return aCheck;
	}
	public void setaCheck(boolean aCheck) {
		this.aCheck = aCheck;
	}
	public boolean isbCheck() {
		return bCheck;
	}
	public void setbCheck(boolean bCheck) {
		this.bCheck = bCheck;
	}
	public boolean iscCheck() {
		return cCheck;
	}
	public void setcCheck(boolean cCheck) {
		this.cCheck = cCheck;
	}
	public boolean isdCheck() {
		return dCheck;
	}
	public void setdCheck(boolean dCheck) {
		this.dCheck = dCheck;
	}
}
