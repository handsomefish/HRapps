package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import domain.*;

@SuppressWarnings("serial")
public class LoginPage extends JFrame{
	
	
	public LoginPage(HRStaff hr){
		super();
		this.setTitle("Leave Application");
		
		JTextField loginID = new JTextField(20);
		JTextField loginPassword = new JTextField(20);
		JButton btnLogin = new JButton("login");
		this.setSize(500,300);
		this.setLocation(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LayoutManager layout1 = new GridLayout(4,4);
		JPanel panel = new JPanel(layout1);
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel("Login ID"));panel.add(loginID);
		panel.add(new JLabel(""));panel.add(new JLabel(""));
		panel.add(new JLabel("Login Password"));panel.add(loginPassword);
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
            {
				
				if(hr.checkLogin(loginID.getText(),loginPassword.getText())){
					switch(hr.getAccountType(loginID.getText())){
					case "Director":
						Director dir = hr.returnDirector();
						if(dir.getPage()==null)
							new DirectorPage(dir);
						else
							JOptionPane.showMessageDialog(null, "account logged in", "Login Page", JOptionPane.INFORMATION_MESSAGE);
						break;
					
					case "HRStaff":
						new HRPage(hr);
						break;
						
					case "Staff":
						Staff st = (Staff)hr.returnStaff(loginID.getText());
						if(st.getPage()==null)		
							new StaffPage(st);
						else
							JOptionPane.showMessageDialog(null, "account logged in", "Login Page", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "wrong ID or password", "Login Page", JOptionPane.INFORMATION_MESSAGE);
					
				}
				loginID.setText("");
				loginPassword.setText("");
			

               
            }
		});
		
		this.add(panel);
		this.setVisible(true);
	}
	
}
