package MemoCalendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarData {
	
	private final static int CAL_WIDTH = 7;
	private final static int CAL_HEIGTH = 6;
	private final static int []calLastDateOfMonth={31,28,31,30,31,30,31,31,30,31,30,31};
	
	private int [][]calDate = new int[CAL_HEIGTH][CAL_WIDTH];
	private int calYear;
	private int calMonth;
	private int calDayOfMon;
	private int calLastDate;
	Calendar today = Calendar.getInstance();
	Calendar cal;
	
	public int[][] getCalDate() {
		return calDate;
	}

	public void setCalDate(int[][] calDate) {
		this.calDate = calDate;
	}

	public int getCalLastDate() {
		return calLastDate;
	}

	public void setCalLastDate(int calLastDate) {
		this.calLastDate = calLastDate;
	}

	public Calendar getToday() {
		return today;
	}

	public void setToday(Calendar today) {
		this.today = today;
	}

	public Calendar getCal() {
		return cal;
	}

	public void setCal(Calendar cal) {
		this.cal = cal;
	}

	public static int getCalWidth() {
		return CAL_WIDTH;
	}

	public static int getCalHeigth() {
		return CAL_HEIGTH;
	}

	public static int[] getCallastdateofmonth() {
		return calLastDateOfMonth;
	}

	public int getCalYear() {
		return calYear;
	}

	public void setCalYear(int calYear) {
		this.calYear = calYear;
	}

	public int getCalMonth() {
		return calMonth;
	}

	public void setCalMonth(int calMonth) {
		this.calMonth = calMonth;
	}

	public int getCalDayOfMon() {
		return calDayOfMon;
	}

	public void setCalDayOfMon(int calDayOfMon) {
		this.calDayOfMon = calDayOfMon;
	}

	public CalendarData(){
		setToday();
	}
	
	public void setToday(){
		calYear = today.get(Calendar.YEAR); 
		calMonth = today.get(Calendar.MONTH);
		calDayOfMon = today.get(Calendar.DAY_OF_MONTH);
		makeCalData(today);
	}
	
	public void makeCalData(Calendar cal){
		// 1일의 위치와 마지막 날짜를 구함 
		int calStartingPos = (cal.get(Calendar.DAY_OF_WEEK)+7-(cal.get(Calendar.DAY_OF_MONTH))%7)%7;
		if(calMonth == 1) calLastDate = calLastDateOfMonth[calMonth] + leapCheck(calYear);
		else calLastDate = calLastDateOfMonth[calMonth];
		// 달력 배열 초기화
		for(int i = 0 ; i<CAL_HEIGTH ; i++){
			for(int j = 0 ; j<CAL_WIDTH ; j++){
				calDate[i][j] = 0;
			}
		}
		// 달력 배열에 값 채워넣기
		for(int i = 0, num = 1, k = 0 ; i<CAL_HEIGTH ; i++){
			if(i == 0) k = calStartingPos;
			else k = 0;
			for(int j = k ; j<CAL_WIDTH ; j++){
				if(num <= calLastDate) calDate[i][j]=num++;
			}
		}
	}
	
	public int leapCheck(int year){ // 윤년인지 확인하는 함수
		if(year%4 == 0 && year%100 != 0 || year%400 == 0) return 1;
		else return 0;
	}
	
	public void moveMonth(int mon){ // 현재달로 부터 n달 전후를 받아 달력 배열을 만드는 함수(1년은 +12, -12달로 이동 가능)
		calMonth += mon;
		if(calMonth>11) while(calMonth>11){
			calYear++;
			calMonth -= 12;
		} else if (calMonth<0) while(calMonth<0){
			calYear--;
			calMonth += 12;
		}
		cal = new GregorianCalendar(calYear,calMonth,calDayOfMon);
		makeCalData(cal);
	}
	
	public static void main(String[] args) {
		new CalendarData();
	}
}
