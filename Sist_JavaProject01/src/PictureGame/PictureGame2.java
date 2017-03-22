package PictureGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

public class PictureGame2 extends JFrame implements ActionListener{

	JToggleButton [] button =new JToggleButton[16];
	
	HashMap<JToggleButton ,Integer>map;
	int check;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		++check;
		System.out.println(e.getSource());
		if(check==2){
			if(true){
				
			}
		}
	}

	public PictureGame2(){
		
		this.setLayout(new GridLayout(4, 4));
		
		map =new HashMap<JToggleButton,Integer>();
		
		for (int i = 0; i < button.length; i++) {
			map.put(button [i] = new JToggleButton(new ImageIcon("cat/"+(i+1)+".gif")), i);
			button[i].addActionListener(this);
		}
		int cnt=0;
		while(true) {
			this.add(button[(int)(Math.random()*16)]);
			cnt++;
			if(cnt==400){
				break;
			}
		}
		this.setVisible(true);
		this.pack();
	}
	
	
	
	public static void main(String[] args) {
		new PictureGame2();
	}
	
}
