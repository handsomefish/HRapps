package domain;

public class Director extends AbstractStaff {

	Director(String id, String password) {
		super(id,password,null);
	}

	public void decideAppoval(boolean decide){
		if(decide){
			getLeaveApplication(0).setFinalResult(true);
		}else{
			getLeaveApplication(0).setFinalResult(false);
		}
		getLeaveApplication(0).getStaffApply().addResultList(getLeaveApplication(0));
		removeLeaveApplication(0);	
	}

	@Override
	void handleApplication(LeaveApplication app) {
		needAppovalList.add(app);
		if(this.pageOpened!=null)
			this.pageOpened.refresh();
	}
	
	
}
