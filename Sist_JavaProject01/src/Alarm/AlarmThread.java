package Alarm;

import java.util.Calendar;

import javax.swing.JLabel;

public class AlarmThread extends Thread{

	private JLabel jLabel;
	private int schour;
	private int scmin;
	private int scsec;
	
	private int hour;
	private int min;
	private int sec;
	
	public AlarmThread() {
		super();
	}
	public AlarmThread(JLabel jLabel,int schour, int scmin, int scsec) {
		super();
		this.jLabel = jLabel;
		this.schour = schour;
		this.scmin = scmin;
		this.scsec = scsec;
	}
	public void run() {

		while(true){
			Calendar calendar = Calendar.getInstance();
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			min = calendar.get(Calendar.MINUTE);
			sec = calendar.get(Calendar.SECOND);
			jLabel.setText("현재 : " + hour + "시" + min + "분 " + sec + "초");
		try {
			Thread.sleep(1000);
			if(hour==schour&&min==scmin&&sec==scsec){
				System.out.println("일어나 잠만보야");
				
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
