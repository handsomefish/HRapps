package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.*;

@SuppressWarnings("serial")
public class DirectorPage extends AbstractPage {
	private Director dir;

	DirectorPage(Director dir){
		super();
		this.setTitle("Director manage Leave Application Page");
		DirPageInit(dir);
		this.setVisible(true);
		dir.setPage(this);
	}
	
	DirectorPage(Director dir,int X,int Y){
		super();
		this.setTitle("Director manage Leave Application Page");
		DirPageInit(dir);
		this.setLocation(X, Y);
		this.setVisible(true);
		dir.setPage(this);
	}
	
	public void DirPageInit(Director dir){
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy",Locale.ENGLISH);
		JButton accept = new JButton("Accept");
		JButton decline = new JButton("Decline");
		JButton refresh = new JButton("Refresh");
		
		this.dir=dir;
		this.setSize(500,300);
		this.setLocation(300, 300);
		
		this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                dir.setPage(null);
                e.getWindow().dispose();
            }
        });
		
		
		JPanel panel = new JPanel(new GridLayout(4,4));
		if(dir.getAppovalListSize()>0){
			panel.add(new JLabel("Staff Leave Application"));
			panel.add(new JLabel(""));
			panel.add(new JLabel("Staff ID:"));
			panel.add(new JLabel(dir.getAppovalListStaffID(0)));
	
			panel.add(new JLabel("From: "+formatter.format(dir.getAppovalListDateFrom(0))));
			panel.add(new JLabel("To: "+formatter.format(dir.getAppovalListDateTo(0))));
			panel.add(accept);
			panel.add(decline);
	
		}else{
			panel.add(new JLabel("NO Leave application"));
			panel.add(refresh);
		}
		
		accept.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
	        {	
				JOptionPane.showMessageDialog(null, "Accepted, result passed to the staff", "Director Message", JOptionPane.INFORMATION_MESSAGE);
				dir.decideAppoval(true);
				refresh();
	        }
		});
		
		decline.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
	        {
				JOptionPane.showMessageDialog(null, "Decline, result passed to the staff", "Director Message", JOptionPane.INFORMATION_MESSAGE);
				dir.decideAppoval(false);
				refresh();
	        }
		});
		
		refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
	        {
				refresh();
	        }
		});
		
		this.add(panel);
		
	}

	public void refresh(){
		new DirectorPage(this.dir,this.getX(),this.getY());
		this.dispose();
	}
	

}
