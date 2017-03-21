package MemoCalendar;

import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MemoThread extends Thread {

	private Calendar today;
	private JLabel infoClock;
	private MemoFrame mf;
	
	public MemoThread(JLabel infoClock, MemoFrame mf) {
		super();
		this.infoClock = infoClock;
		this.mf = mf;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			try {
				today = Calendar.getInstance();
				String amPm = (today.get(Calendar.AM_PM)==0?"AM":"PM");
				String hour;
					if(today.get(Calendar.HOUR)==0){
						hour = "12";
					}else if(today.get(Calendar.HOUR)==12){
						hour = " 0";
					}else{
						hour = (today.get(Calendar.HOUR)<10?" ":"")+today.get(Calendar.HOUR);}
				String min = (today.get(Calendar.MINUTE)<10?"0":"")+today.get(Calendar.MINUTE);
				String sec = (today.get(Calendar.SECOND)<10?"0":"")+today.get(Calendar.SECOND);
				infoClock.setText(amPm+" "+hour+":"+min+":"+sec);
				sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
