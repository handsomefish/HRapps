package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.*;
import domain.Staff;

@SuppressWarnings("serial")
public class StaffPage extends AbstractPage{
	

	private Staff staff;
	
	public StaffPage(Staff staff){
		super();
		this.setTitle("Staff id:"+ staff.getStaffID() + " Leave Application");
		this.setLocation(300, 300);
		StaffPageInit(staff);
		this.setVisible(true);
		staff.setPage(this);
	}
	
	public StaffPage(Staff staff,int X, int Y){
		super();
		this.setTitle("Staff id:"+ staff.getStaffID() + " Leave Application");
		this.setLocation(X, Y);
		StaffPageInit(staff);
		this.setVisible(true);
		staff.setPage(this);
	}
	
	
	public void StaffPageInit(Staff staff){
		
		JTextField leaveFrom = new JTextField(20);
		JTextField leaveTo = new JTextField(20);
		JButton submit = new JButton("submit");
		JButton accept = new JButton("Accept");
		JButton decline = new JButton("Decline");
		JPanel panel = new JPanel(new GridLayout(4,4));
		JButton refresh = new JButton("Refresh");
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy",Locale.ENGLISH);
		
		this.setSize(700,300);

		this.staff=staff;
		
		this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                staff.setPage(null);
                e.getWindow().dispose();
            }
        });
		
		
		panel.add(new JLabel("Leave from:"));
		panel.add(leaveFrom);
		panel.add(new JLabel("Leave to:"));
		panel.add(leaveTo);
		this.add(panel);
		
		panel.add(refresh);
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(submit);
		
		if(staff.getAppovalListSize()>0){
			panel.add(new JLabel("Appoval for Leave"));
			panel.add(new JLabel(""));
			panel.add(new JLabel("Staff ID"));
			panel.add(new JLabel(staff.getAppovalListStaffID(0)));
	
			panel.add(new JLabel("From: "+formatter.format(staff.getAppovalListDateFrom(0))));
			panel.add(new JLabel("To: "+formatter.format(staff.getAppovalListDateTo(0))));
			panel.add(accept);
			panel.add(decline);

		}
		if(this.staff.getResultListSize()>0){
			for(int i=0;i<staff.getResultListSize();i++){
				if(staff.getResultListResult(i))
					JOptionPane.showMessageDialog(null,"Appcation from "+
							formatter.format(staff.getResultListDateFrom(i))+" to " +
							formatter.format(staff.getResultListDateTo(i)) + " ACCEPTED", "Staff "+ this.staff.getStaffID()+" Message", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null,"Appcation from "+
							formatter.format(staff.getResultListDateFrom(i))+" to " +
							formatter.format(staff.getResultListDateTo(i)) + " DECLINED", "Staff "+ this.staff.getStaffID()+" Message", JOptionPane.INFORMATION_MESSAGE);
			}
			staff.removeAllResultList();
		}
			
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
            {
				if(staff.checkStaffExist()){
					try{
						Date leaveFromDate = formatter.parse(leaveFrom.getText());
						Date leaveToDate = formatter.parse(leaveTo.getText());
						if(staff.createLeaveApplication(leaveFromDate,leaveToDate))
							JOptionPane.showMessageDialog(null, "submitted", "Staff "+ staff.getStaffID()+" Message", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "wrong input", "Staff "+ staff.getStaffID()+" Message", JOptionPane.INFORMATION_MESSAGE);	
					}catch (ParseException e1){
						JOptionPane.showMessageDialog(null, "wrong input", "Staff "+ staff.getStaffID()+" Message", JOptionPane.INFORMATION_MESSAGE);
					}
					leaveFrom.setText("");
					leaveTo.setText("");
				}
				else
					staffDeleted();	
            }
		});
		
		accept.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
            {
				if(staff.checkStaffExist()){
					
					JOptionPane.showMessageDialog(null, "Accepted, application passed to supervisor", "Staff "+ staff.getStaffID()+" Message", JOptionPane.INFORMATION_MESSAGE);
					staff.decideAppoval(true);
					refresh();
				}
				else
					staffDeleted();	
            }
		});
		
		decline.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
            {
				if(staff.checkStaffExist()){
					JOptionPane.showMessageDialog(null, "Decline, result passed to the staff", "Staff "+ staff.getStaffID()+" Message", JOptionPane.INFORMATION_MESSAGE);
					staff.decideAppoval(false);
					refresh();
				}else
					staffDeleted();	
            }
		});
		
		refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
            {
				if(staff.checkStaffExist()){
					refresh();
				}else
					staffDeleted();
            }
		});

		
	}
	
	public void refresh(){
		if(staff.checkStaffExist()){
			new StaffPage(this.staff,this.getX(),this.getY());
			this.dispose();
		}else
			staffDeleted();
	}
	
	public void staffDeleted(){
		JOptionPane.showMessageDialog(null, "This account has deleted", "Staff "+ this.staff.getStaffID()+" Message", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
	}
}
