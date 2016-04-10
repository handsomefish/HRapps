package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import domain.HRStaff;

@SuppressWarnings("serial")
public class HRPage extends JFrame {

	
	public HRPage(HRStaff hr){
		super();
		this.setTitle("HR Staff Administration page");	
		JTextField loginID = new JTextField(20);
		JTextField loginPassword = new JTextField(20);
		JTextField supervisorID = new JTextField(20);
		JButton btnCreate = new JButton("Create Account");
		JButton btnDelete = new JButton("Delete Account");
		this.setSize(700,300);
		this.setVisible(true);

		
		JPanel panel = new JPanel(new GridLayout(4,4));
		panel.add(new JLabel("Account Management"));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		
		if(hr.getStafflistSize()==1)
			panel.add(new JLabel("Director Account:"));
		else
			panel.add(new JLabel("Staff Account:"));
		panel.add(new JLabel("ID:"));
		
		panel.add(loginID);
		panel.add(btnCreate);
		panel.add(new JLabel(""));
		panel.add(new JLabel("password:"));
		panel.add(loginPassword);
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		JLabel labsup = new JLabel("Supervised By:");
		panel.add(labsup);
		panel.add(supervisorID);
		panel.add(btnDelete);
		if(hr.getStafflistSize()==1){
			labsup.setVisible(false);
			supervisorID.setVisible(false);
			btnDelete.setVisible(false);
		}
		
		this.add(panel);
		
		btnCreate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
            {
				if(loginID.getText().length()>0&&loginPassword.getText().length()>0){
					if(hr.getStafflistSize()==1){
						hr.createDirector(loginID.getText(),loginPassword.getText());
						JOptionPane.showMessageDialog(null, "Director account Created", "HR Message", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						if(supervisorID.getText().length()>0){
							if(!hr.createStaff(loginID.getText(), loginPassword.getText(), supervisorID.getText()))
								JOptionPane.showMessageDialog(null, "wrong input");
							else
								JOptionPane.showMessageDialog(null, "Staff account Created", "HR Message", JOptionPane.INFORMATION_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(null, "wrong input", "HR Message", JOptionPane.INFORMATION_MESSAGE);
					}
					labsup.setVisible(true);
					supervisorID.setVisible(true);
					btnDelete.setVisible(true);
					
					panel.remove(4);
					if(hr.getStafflistSize()==1)
						panel.add(new JLabel("Create Director Account:"),4);
					else
						panel.add(new JLabel("Staff Account:"),4);
	               loginID.setText("");
	               loginPassword.setText("");
	               supervisorID.setText("");
				}else
					JOptionPane.showMessageDialog(null, "please input", "HR Message", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
            {
				if(hr.deleteStaff(loginID.getText()))
					JOptionPane.showMessageDialog(null, "Staff deleted", "HR Message", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Cannot delete this account", "HR Message", JOptionPane.INFORMATION_MESSAGE);
				loginID.setText("");
				loginPassword.setText("");
				supervisorID.setText("");

            }
		});

		
	}



}