package domain;


import java.util.Date;

import ui.*;


public class LeaveApplication {
	private Staff staffApply;
	private Date dateFrom;
	private Date dateTo;
	private boolean finalResult;
	public LeaveApplication(Staff staff, Date dateFrom,Date dateTo){
		this.staffApply=staff;
		this.dateFrom=dateFrom;
		this.dateTo=dateTo;
	}
	
	public Staff getStaffApply(){
		return staffApply;
	}
	public Date getLeaveDateFrom(){
		return dateFrom;
	}
	public Date getLeaveDateTo(){
		return dateTo;
	}
	public boolean getFinalResult(){
		return finalResult;
	}
	public void setFinalResult(boolean finalResult){
		this.finalResult=finalResult;
	}
	
	public static void main(String[] args){
		HRStaff hr = new HRStaff("hr","hr");
		new LoginPage(hr);
	}
	
}
