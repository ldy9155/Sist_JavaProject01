package Alarm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Alarm extends Frame implements ActionListener{

	private JPanel jPanel,jPanel2,jPanel3,jPanel4,jPanel5;
	private JButton jbutton;
	private JLabel jLabel,jLabel2,jLabel3,jLabel4,jLabel5;
	private JTextField jTextField,jTextField2,jTextField3;
	
	private int hour;
	private int min;
	private int sec;
	
	private int schour;
	private int scmin;
	private int scsec;
	
	public void initput(){
		this.setTitle("알람시계");
		jPanel=new JPanel(new BorderLayout(1, 2));
		jPanel2=new JPanel(new GridLayout(2, 1));
		jPanel3=new JPanel(new FlowLayout());
		jPanel4=new JPanel(new BorderLayout());
		
		jbutton=new JButton("시작");
		jbutton.addActionListener(this);
		
		jLabel=new JLabel("알람시간: ");
		jLabel.setFont(new Font("궁서", 0, 30));

		jTextField=new JTextField(3);
		jLabel3=new JLabel("시");
		jTextField.setFont(new Font("궁서", 0, 40));
		jTextField.setHorizontalAlignment(jTextField.RIGHT);
		
		jTextField2=new JTextField(4);
		jLabel4=new JLabel("분");
		jTextField2.setFont(new Font("궁서", 0, 40));
		jTextField2.setHorizontalAlignment(jTextField2.RIGHT);
		
		jTextField3=new JTextField(4);
		jLabel5=new JLabel("초");
		jTextField3.setFont(new Font("궁서", 0, 40));
		jTextField3.setHorizontalAlignment(jTextField3.RIGHT);
		
		jPanel3.add(jLabel);
		jPanel3.add(jTextField);
		jPanel3.add(jLabel3);
		jPanel3.add(jTextField2);
		jPanel3.add(jLabel4);
		jPanel3.add(jTextField3);
		jPanel3.add(jLabel5);
		
		
		javax.swing.Timer timer;
		timer = new Timer(1000, this);
		timer.setInitialDelay(0);
		timer.start();
		
		jLabel2=new JLabel("현재 : " + hour + "시" + min + "분 " + sec + "초",Label.RIGHT);
		jLabel2.setFont(new Font("궁서", 0, 50));
		
		jPanel4.add(jLabel2);
		jPanel2.add(jPanel3);
		jPanel2.add(jPanel4);
		jPanel.add(jPanel2,"West");
		jPanel.add(jbutton,"East");
		
		this.add(jPanel);
		
	}
	
	public Alarm() throws HeadlessException {
		initput();
		this.setTitle("알람시계");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			dispose();
			}    	 
		 });
		this.setVisible(true);
		 this.pack();
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==jbutton){
			schour=Integer.parseInt(jTextField.getText());
			scmin=Integer.parseInt(jTextField2.getText());
			scsec=Integer.parseInt(jTextField3.getText());
			
			AlarmThread at2=new AlarmThread(jLabel2,schour,scmin,scsec);
			at2.start();
		}
	}
	
	public static void main(String[] args) {
		new Alarm();
	}
}
