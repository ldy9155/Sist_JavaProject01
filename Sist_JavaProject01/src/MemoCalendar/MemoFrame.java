package MemoCalendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemoFrame extends JFrame implements ActionListener {

	private static final MemoFrame MemoFrame = null;
	private JPanel jPanel1,jPanel2,jPanel3,jPanelTot1,jPanelTot2;
	private JButton jButton1;
	private JButton [][]dateButs = new JButton[6][7];
	private JButton []weekDaysName = new JButton[7];
	private Calendar today = Calendar.getInstance();
	
	private JPanel calOpPanel,pack = new JPanel(new BorderLayout());
	private JButton lYearBut,lMonBut,nMonBut,nYearBut;
	private JLabel curMMYYYYLab;
	ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons();
	
	private JLabel jLabel1, jLabel2; 
	private CalendarData calendarData = new CalendarData();
	final String WEEK_DAY_NAME[] = { "SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT" };
	
	public CalendarData getCalendarData() {
		return calendarData;
	}

	public void setCalendarData(CalendarData calendarData) {
		this.calendarData = calendarData;
	}
	
	public class ListenForCalOpButtons implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == lYearBut) calendarData.moveMonth(-12);
			else if(e.getSource() == lMonBut) calendarData.moveMonth(-1);
			else if(e.getSource() == nMonBut) calendarData.moveMonth(1);
			else if(e.getSource() == nYearBut) calendarData.moveMonth(12);
			curMMYYYYLab.setText("<html><table width=100><tr><th><font size=5>"+((calendarData.getCalMonth()+1)<10?"&nbsp;":"")+(calendarData.getCalMonth()+1)+" / "+calendarData.getCalYear()+"</th></tr></table></html>");
			showCal();
		}
	}
	private void focusToday(){
		if(today.get(Calendar.DAY_OF_WEEK) == 1)
			dateButs[today.get(Calendar.WEEK_OF_MONTH)][today.get(Calendar.DAY_OF_WEEK)-1].requestFocusInWindow();
		else
			dateButs[today.get(Calendar.WEEK_OF_MONTH)-1][today.get(Calendar.DAY_OF_WEEK)-1].requestFocusInWindow();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jButton1){
			setToday();
			focusToday();
		}
		int k=0,l=0;
		for(int i=0 ; i<calendarData.getCalHeigth() ; i++){
			for(int j=0 ; j<calendarData.getCalWidth() ; j++){
			if(e.getSource()==dateButs[i][j]){
					k=i;
					l=j;
				}
			}
		}
		if(!(k ==0 && l == 0)){
			calendarData.setCalDayOfMon(calendarData.getCalDate()[k][l]);
		}
		new MemoDialog(this,calendarData.getCalYear(),calendarData.getCalMonth()+1,calendarData.getCalDayOfMon());
	}

	public void ThreadClock(){ //현재 시간
		MemoThread mt = new MemoThread(jLabel2, MemoFrame);
		mt.start();
	}
	
	public void initControl(){
		calOpPanel = new JPanel();
		lYearBut = new JButton("<<");
		lYearBut.setToolTipText("Previous Year");
		lYearBut.addActionListener(lForCalOpButtons);
		lMonBut = new JButton("<");
		lMonBut.setToolTipText("Previous Month");
		lMonBut.addActionListener(lForCalOpButtons);
		curMMYYYYLab = new JLabel("<html><table width=100><tr><th><font size=5>"+((calendarData.getCalMonth()+1)<10?"&nbsp;":"")+(calendarData.getCalMonth()+1)+" / "+calendarData.getCalYear()+"</th></tr></table></html>");
		nMonBut = new JButton(">");
		nMonBut.setToolTipText("Next Month");
		nMonBut.addActionListener(lForCalOpButtons);
		nYearBut = new JButton(">>");
		nYearBut.setToolTipText("Next Year");
		nYearBut.addActionListener(lForCalOpButtons);
		calOpPanel.setLayout(new GridBagLayout());
		GridBagConstraints calOpGC = new GridBagConstraints();
		calOpGC.gridx = 1;
		calOpGC.gridy = 1;
		calOpGC.gridwidth = 2;
		calOpGC.gridheight = 1;
		calOpGC.weightx = 1;
		calOpGC.weighty = 1;
		calOpGC.insets = new Insets(5,5,0,0);
		calOpGC.anchor = GridBagConstraints.WEST;
		calOpGC.fill = GridBagConstraints.NONE;
		calOpGC.gridwidth = 3;
		calOpGC.gridx = 2;
		calOpGC.gridy = 1;
		calOpGC.anchor = GridBagConstraints.CENTER;
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 1;
		calOpGC.gridy = 2;
		calOpPanel.add(lYearBut,calOpGC);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 2;
		calOpGC.gridy = 2;
		calOpPanel.add(lMonBut,calOpGC);
		calOpGC.gridwidth = 2;
		calOpGC.gridx = 3;
		calOpGC.gridy = 2;
		calOpPanel.add(curMMYYYYLab,calOpGC);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 5;
		calOpGC.gridy = 2;
		calOpPanel.add(nMonBut,calOpGC);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 6;
		calOpGC.gridy = 2;
		calOpPanel.add(nYearBut,calOpGC);
		pack.add("North",calOpPanel);
	}
	
	
	public void initClock(){
		jPanelTot2 = new JPanel(new BorderLayout());
		jPanelTot1 = new JPanel(new GridLayout(1, 2));
		jPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			jButton1 = new JButton("Today");
			jButton1.addActionListener(this);
			jLabel1 = new JLabel(calendarData.getCalYear()+"/"+(calendarData.getCalMonth()+1)+"/"+calendarData.getCalDayOfMon());
			
			jPanel1.add(jButton1);
			jLabel2 = new JLabel();
			
			jPanel1.add(jLabel1);
			jPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			jPanel2.add(jLabel2);
			
			jPanelTot1.add(jPanel1);
			jPanelTot1.add(jPanel2);
			jPanelTot2.add("North",jPanelTot1);
	}
	
	public void initCal(){
		jPanel3 = new JPanel();
		weekDaysName = new JButton[7];
		for(int i=0 ; i<calendarData.getCalWidth(); i++){
			weekDaysName[i]=new JButton(WEEK_DAY_NAME[i]);
			weekDaysName[i].setBorderPainted(false);
			weekDaysName[i].setContentAreaFilled(false);
			weekDaysName[i].setForeground(Color.WHITE);
			if(i == 0) weekDaysName[i].setBackground(new Color(200, 50, 50));
			else if (i == 6) weekDaysName[i].setBackground(new Color(50, 100, 200));
			else weekDaysName[i].setBackground(new Color(150, 150, 150));
			weekDaysName[i].setOpaque(true);
			weekDaysName[i].setFocusPainted(false);
			jPanel3.add(weekDaysName[i]);
		}
		
		for(int i=0 ; i<calendarData.getCalHeigth() ; i++){
			for(int j=0 ; j<calendarData.getCalWidth() ; j++){
				dateButs[i][j]=new JButton();
				dateButs[i][j].setBorderPainted(false);
				dateButs[i][j].setContentAreaFilled(false);
				dateButs[i][j].setBackground(Color.WHITE);
				dateButs[i][j].setOpaque(true);
				dateButs[i][j].addActionListener(this);
				jPanel3.add(dateButs[i][j]);
				pack.add("South", jPanel3);
				jPanelTot2.add("South",pack);
			}
		}
		jPanel3.setLayout(new GridLayout(0,7,2,2));
		jPanel3.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		this.add(jPanelTot2);
		showCal();
	}
	
	public void setToday(){
		calendarData.setCalYear(today.get(Calendar.YEAR));  
		calendarData.setCalMonth(today.get(Calendar.MONTH)); 
		calendarData.setCalDayOfMon(today.get(Calendar.DAY_OF_MONTH)); 
		calendarData.makeCalData(today);
	}
	
	public void showCal(){
		for(int i=0;i<calendarData.getCalHeigth();i++){
			for(int j=0;j<calendarData.getCalWidth();j++){
				String fontColor="black";
				if(j==0) fontColor="red";
				else if(j==6) fontColor="blue";
				File f =new File("CalData/"+calendarData.getCalYear()+((calendarData.getCalMonth()+1)<10?"0":"")+(calendarData.getCalMonth()+1)+(calendarData.getCalDate()[i][j]<10?"0":"")+calendarData.getCalDate()[i][j]+".txt");
				if(f.exists()){
					dateButs[i][j].setText("<html><u><EM><font color="+fontColor+">"+calendarData.getCalDate()[i][j]+"*"+"</font></EM></u></html>");
				}
				else dateButs[i][j].setText("<html><font color="+fontColor+">"+calendarData.getCalDate()[i][j]+"</font></html>");
				
				JLabel todayMark = new JLabel("<html><font color=green>*</html>");
				dateButs[i][j].removeAll();
				if(calendarData.getCalMonth() == calendarData.today.get(Calendar.MONTH) &&
						calendarData.getCalYear() == calendarData.today.get(Calendar.YEAR) &&
								calendarData.getCalDate()[i][j] == calendarData.today.get(Calendar.DAY_OF_MONTH)){
					dateButs[i][j].add(todayMark);
					dateButs[i][j].setToolTipText("Today");
				}
				if(calendarData.getCalDate()[i][j] == 0) dateButs[i][j].setVisible(false);
				else dateButs[i][j].setVisible(true);
			}
		}
	}
	
	public void initMain(){
		initClock();
		initCal();
		initControl();
		ThreadClock();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		this.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		this.setLocation(xPos,yPos);
		this.setTitle("Memo Calendar");
		this.setVisible(true);
	}
	
	public MemoFrame(){
		initMain();
	}
}
