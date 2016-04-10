package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

import ui.AbstractPage;

public abstract class AbstractStaff {
	protected String id;
	protected String password;
	protected AbstractStaff supervisor;
	protected List <LeaveApplication> needAppovalList = new ArrayList<LeaveApplication>();
	protected AbstractPage pageOpened;
	AbstractStaff(String id,String password,AbstractStaff supervisor){
		this.supervisor=supervisor;
		this.id=id;
		this.password=password;
	}

	
	void notifySupervisor(LeaveApplication app){
		if(supervisor != null){
			supervisor.handleApplication(app);
		}
	}
	

	
	void setIDPassword(String id, String password){
		this.id=id;
		this.password=password;
	}
	
	public String getStaffID(){
		return this.id;
	}
	
	public int getAppovalListSize(){
		return needAppovalList.size();
	}
	
	public String getStaffPassword(){
		return this.password;
	}
	public Date getAppovalListDateFrom(int i){
		return needAppovalList.get(i).getLeaveDateFrom();
	}
	public Date getAppovalListDateTo(int i){
		return needAppovalList.get(i).getLeaveDateTo();
	}
	
	public String getAppovalListStaffID(int i){
		return needAppovalList.get(i).getStaffApply().id;
	}
	
	public LeaveApplication getLeaveApplication(int i){
		return needAppovalList.get(i);
	}

	public void removeLeaveApplication(int i){
		needAppovalList.remove(i);
	}
	
	public void addAppovalList(LeaveApplication app){
		needAppovalList.add(app);
	}
	

	public void setPage(AbstractPage page){
		this.pageOpened=page;
	}
	
	public JFrame getPage(){
		return this.pageOpened;
	}
	abstract void handleApplication(LeaveApplication app);
	abstract public void decideAppoval(boolean decide);
}
