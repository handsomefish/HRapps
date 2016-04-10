package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Staff extends AbstractStaff{
	
	private boolean staffExist = true;
	
	private List <LeaveApplication> resultList = new ArrayList<LeaveApplication>();
	
	Staff(String id,String password,AbstractStaff supervisor) {
		super(id,password,supervisor);
	}

	@Override
	void handleApplication(LeaveApplication app) {
		needAppovalList.add(app);
		if(this.pageOpened!=null)
			this.pageOpened.refresh();
	}

	public boolean createLeaveApplication(Date leaveFromDate, Date leaveToDate){
		
		if(leaveToDate.after(leaveFromDate) || leaveToDate.equals(leaveFromDate)){
			notifySupervisor(new LeaveApplication(this,leaveFromDate,leaveToDate));
			return true;
		}
		else
			return false;
	}
	
	public LeaveApplication returnLeaveApplication(){
		return needAppovalList.get(0);
	}
	
	public void decideAppoval(boolean decide){
		if(decide){
			notifySupervisor(needAppovalList.get(0));
			needAppovalList.remove(0);
		}else{
			needAppovalList.get(0).setFinalResult(false);
			needAppovalList.get(0).getStaffApply().resultList.add(needAppovalList.get(0));
			needAppovalList.remove(0);
		}
	
	}
	
	
	
	public int getResultListSize(){
		return resultList.size();
	}
	
	public boolean getResultListResult(int i){
		return resultList.get(i).getFinalResult();
	}
	public Date getResultListDateFrom(int i){
		return resultList.get(i).getLeaveDateFrom();
	}
	public Date getResultListDateTo(int i){
		return resultList.get(i).getLeaveDateTo();
	}
	public void removeAllResultList(){
		while(resultList.size()>0){
			resultList.remove(0);
		}
	}
	public void addResultList(LeaveApplication app){
		resultList.add(app);
		if(this.pageOpened!=null)
			this.pageOpened.refresh();
	}
	
	public AbstractStaff getSupervisor(){
		return this.supervisor;
	}
	
	public void setSupervisor(AbstractStaff a){
		this.supervisor=a;
	}
	
	
	public boolean checkStaffExist(){
		return this.staffExist;
	}
	
	public void setStaffExist(boolean e){
		this.staffExist=e;
	}

}
