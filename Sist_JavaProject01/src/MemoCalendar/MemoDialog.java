package MemoCalendar;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MemoDialog extends Dialog implements ActionListener{
	
	private MemoFrame mf;
	private JButton []jButtons = new JButton[3];
	private JTextArea jTextArea; 
	private JLabel jLabel;
	private JPanel []jPanel = new JPanel[4];
	private String []buttonName = {"Save","Delete","Clear"};
	private String []location = {"North","Center","South"};
	private int year,month,day;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jButtons[0]){
			save();
			mf.showCal();
		}
		if(e.getSource()==jButtons[1]){
			delete();
			mf.showCal();
		}
		if(e.getSource()==jButtons[2]){
			clear();
		}
	}
	
	public void clear(){
		jTextArea.setText("");
	}
	
	public void delete(){
		jTextArea.setText("");
		File f = new File("CalData/"+year+((month)<10?"0":"")+(month)+(day<10?"0":"")+day+".txt");
		f.delete();
	}
	
	public void save(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("CalData/"+year+((month)<10?"0":"")+(month)+(day<10?"0":"")+day+".txt")));
			oos.writeObject(jTextArea.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		} finally {
			if(oos!=null){
				try {
					oos.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}
	
	public void initFrame(){
		jPanel[0] = new JPanel();
		
		jLabel = new JLabel(year+" / "+month+" / "+day);
		jPanel[0].add(jLabel);
		
		jPanel[1] = new JPanel();
		jTextArea = new JTextArea(20,40);
		jPanel[1].add(jTextArea);
		
		jPanel[2] = new JPanel(new GridLayout(1, 3));
		for (int i = 0; i < jButtons.length; i++) {
			jButtons[i] = new JButton(buttonName[i]);
			jButtons[i].addActionListener(this);
			jButtons[i].setBorderPainted(true);
			jButtons[i].setContentAreaFilled(false);
			jPanel[2].add(jButtons[i]);
		}
		
		jPanel[3] = new JPanel(new BorderLayout());
		for (int i = 0; i < jPanel.length-1; i++) {
			jPanel[3].add(location[i],jPanel[i]);
		}
		this.add(jPanel[3]);
	}
	public void load(){
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("CalData/"+year+((month)<10?"0":"")+(month)+(day<10?"0":"")+day+".txt")));	
			jTextArea.append((String)ois.readObject());
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			try {
				if(ois!=null){
				ois.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public MemoDialog(MemoFrame mf,int year,int month,int day){
		super(mf);
		this.mf = mf;
		this.year = year;
		this.month = month;
		this.day = day;
		
		initFrame();
		load();
		this.setVisible(true);
		this.pack();
		this.setTitle("Memo");
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int xPos = (dim.width/2 - this.getWidth()/2+500); 
		int yPos = (dim.height/2 - this.getHeight()/2);
		this.setLocation(xPos, yPos);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}
}
