package TotalPack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Calculator.*;
import Management.*;
import MemoCalendar.*;
import PictureGame.*;
import Alarm.*;
import TwoRukSer.*;

public class Main extends Frame implements ActionListener,Serializable{
	
	private JButton[]button = new JButton[6];
	private JPanel []jPanel = new JPanel[5];
	private JLabel []jLabel = new JLabel[6];
	private JLabel jimage;
	private ImageIcon image = new ImageIcon("bear/bear3.jpg");
	private ObjectInputStream ois=null;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private String [] projectName = {"Calculator","Management","MemoCalendar","PictureGame","Alarm","TwoRukSer"};
	private FileReader textFileReader;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o =e.getSource();
		if(o==button[0]){
			new Calculator();
		}else if(o==button[1]){
			new View();
		}else if(o==button[2]){
			new MemoMain();
		}else if(o==button[3]){
			new SetFrame();
		}else if(o==button[4]){
			new Alarm();
		}else if(o==button[5]){
			new Erukser();
		}
	}

	public void init(){
		jPanel[0] = new JPanel(new BorderLayout());
		jPanel[1] = new JPanel();
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton(projectName[i]);
			button[i].addActionListener(this);
		}
		for (int i = 0; i < button.length; i++) {
			jPanel[1].add(button[i]);
		}
		jPanel[2] = new JPanel();
			Image originImg = image.getImage();
			Image changedImg= originImg.getScaledInstance(420, 300, Image.SCALE_SMOOTH );
			ImageIcon Icon = new ImageIcon(changedImg);
			jimage = new JLabel(Icon);
			jPanel[2].add(jimage);
			
		jPanel[3] = new JPanel(new GridLayout(6,1,5,35));
			try {
			     BufferedReader in = new BufferedReader(new FileReader("./Log.txt"));
			     String []s = new String[10];
			     int cnt=0;
			      while ((s[cnt++] = in.readLine()) != null);
			      for (int i = 0; i < jLabel.length; i++) {
					jLabel[i] = new JLabel(s[i]);
					jPanel[3].add(jLabel[i]);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(ois!=null){
					try {
						ois.close();	
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
			
		jPanel[4] = new JPanel();
		jPanel[4].add(jPanel[3]);
		jPanel[4].add(jPanel[2]);
		
		jPanel[0].add("North",jPanel[1]);
		jPanel[0].add("South",jPanel[4]);
		this.add(jPanel[0]);
	}
	
	public Main() {
		init();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		this.pack();
		int xPos=dim.width/2-this.getWidth()/2;
		int yPos=dim.height/2-this.getWidth()/2;
		this.setLocation(xPos,yPos);
		this.setTitle("OGG ETC");
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}
}
