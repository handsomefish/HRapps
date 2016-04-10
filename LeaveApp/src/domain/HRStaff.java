package domain;

import java.util.ArrayList;
import java.util.List;

public class HRStaff extends Staff{

	private List <AbstractStaff> staffList = new ArrayList<AbstractStaff>();
	HRStaff(String id, String password) {
		super(id, password,null);
		staffList.add(this);
	}

	public void createDirector(String DirectorID,String password){
		Director newStaff = new Director(DirectorID,password);
		staffList.add(newStaff);
	}

	public boolean createStaff(String newStaffID,String password,String supervisorID){
		for(int i=0;i<staffList.size();i++){
			if(staffList.get(i).getStaffID().equals(newStaffID))
				return false;
		}
		for(int i=0;i<staffList.size();i++){
			if(staffList.get(i).getStaffID().equals(supervisorID)){
				Staff newStaff = new Staff(newStaffID,password,staffList.get(i));
				staffList.add(newStaff);
				return true;
			}
		}
		return false;
	}

	public int getStafflistSize(){
		return staffList.size();
		
	}
	
	
	public boolean checkStaffExist(String ID){
		for(int i=0;i<staffList.size();i++){
			if(staffList.get(i).getStaffID().equals(ID))
				return true;
		}
		return false;
	}

	public Staff returnStaff(String id){
		for(int i=0;i<staffList.size();i++){
			if(staffList.get(i).getStaffID().equals(id))
				return (Staff)staffList.get(i);
		}
		return null;
	}
	
	public Director returnDirector(){
		for(int i=0;i<staffList.size();i++){
			if(getAccountType(staffList.get(i).id).equals("Director")){
				return (Director)staffList.get(i);
			}
				
		}
		return null;
		
	}
	
	public boolean checkLogin(String id, String password){
		for(int i=0;i<staffList.size();i++){
			if(staffList.get(i).getStaffID().equals(id) && staffList.get(i).getStaffPassword().equals(password)){
				return true;
			}
		}
		return false;
	}
	
	public String getAccountType(String loginID){
		for(int i=0;i<staffList.size();i++){
			if(staffList.get(i).getStaffID().equals(loginID))
				return staffList.get(i).getClass().getName().substring(7);
			
		}
		return "NULL";
	}
	
	public boolean deleteStaff(String loginID){
		if(staffList.size()>1&&checkStaffExist(loginID)&&getAccountType(loginID).equals("Staff")){
			Staff staff = returnStaff(loginID);
			for(int i=0; i<staff.getAppovalListSize();i++){
				staff.getSupervisor().addAppovalList(staff.getLeaveApplication(i));
			}
			for(int i=0;i<staffList.size();i++){
				if(getAccountType(staffList.get(i).getStaffID()).equals("Staff")){
					Staff staffUpdate=(Staff)staffList.get(i);
					if(staffUpdate.getSupervisor().getStaffID().equals(loginID)){
						staffUpdate.setSupervisor(staff.getSupervisor());
					}
				}
			}
			for(int i=0;i<staffList.size();i++){
				for(int j=0;j<staffList.get(i).needAppovalList.size();j++){
					if(staffList.get(i).needAppovalList.get(j).getStaffApply().id.equals(loginID)){
						staffList.get(i).needAppovalList.remove(j);
					}
				}
			}
			
			staff.setStaffExist(false);
			for(int i=0;i<staffList.size();i++){
				if(staffList.get(i).pageOpened!=null)
					staffList.get(i).pageOpened.refresh();
			}
			
			for(int i=0;i<staffList.size();i++){
				if(staffList.get(i).getStaffID().equals(loginID)){
					staffList.remove(i);
					
					return true;
				}
			}
			return false;
		}else
			return false;
		
		
	}
	
}
