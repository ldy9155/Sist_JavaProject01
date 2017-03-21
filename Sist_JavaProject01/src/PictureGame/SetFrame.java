package PictureGame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SetFrame extends JFrame implements ActionListener {

	JMenuBar jMenuBar;
	JMenu Game, Score, Help;
	JMenuItem Start, Restart, Pause, Exit;
	JMenuItem ShowScore;
	JMenuItem Menual, AboutUs;
	CardLayout cardLayout;
	JPanel defaultPanel;
	JPanel gamePanel, timeScorePanel;
	JPanel jPanelTotal;
	JLabel timeLabel, getTimeLabel, scoreLabel, getScoreLabel;

	JButton[] jButtons = new JButton[16];

	// Object[] jbtn = {jbtn1, jbtn2, jbtn3, jbtn4,
	// jbtn5, jbtn6, jbtn7, jbtn8,
	// jbtn9, jbtn10, jbtn11, jbtn12,
	// jbtn13, jbtn14, jbtn15, jbtn16};

	Font font;

	static Object arr = null;
	static boolean isplaying = false;
	static int Count = 0;
	private ActionListener actionListener;

	public void initFrame() {
		/*------- Menu setting ------*/
		jMenuBar = new JMenuBar();

		/*------- Game Menu ------*/
		jMenuBar.add(Game = new JMenu("Game"));
		Game.add(Start = new JMenuItem("Start"));
		Game.add(Restart = new JMenuItem("Restart"));
		Game.add(Pause = new JMenuItem("Pause"));
		Game.addSeparator();
		Game.add(Exit = new JMenuItem("Exit"));

		/*------- Score Menu ------*/
		jMenuBar.add(Score = new JMenu("Score"));
		Score.add(ShowScore = new JMenuItem("ShowScore"));

		/*------- Help Menu ------*/
		jMenuBar.add(Help = new JMenu("Help"));
		Help.add(Menual = new JMenuItem("Menual"));
		Help.addSeparator();
		Help.add(AboutUs = new JMenuItem("About us.."));

		this.setJMenuBar(jMenuBar);

		/*------  SetView & Time ------*/
		jPanelTotal = new JPanel(new BorderLayout());
		jPanelTotal.add("Center", gamePanel = new JPanel(new GridLayout(4, 4)));
		jPanelTotal.add("South", timeScorePanel = new JPanel(new GridLayout(1, 4)));

		/*------  setLabel ------*/
		timeLabel = new JLabel("Time : ");
		getTimeLabel = new JLabel();
		getTimeLabel.setFont(font = new Font("Default", 1, 12));
		scoreLabel = new JLabel("Score : ");
		getScoreLabel = new JLabel("2point");

		timeScorePanel.add(timeLabel);
		timeScorePanel.add(getTimeLabel);
		timeScorePanel.add(scoreLabel);
		timeScorePanel.add(getScoreLabel);

		this.add(jPanelTotal);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		this.setTitle("Picture Game");
		this.setVisible(true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();// 
		this.setSize(500, 500);
		this.setLocation(new Point((d.width / 2) - (this.getWidth() / 2), (d.height / 2) - (this.getHeight() / 2)));
	}

	public void setImage() {
		for (int i = 1; i < 17; i++) {
			gamePanel.add((Component) (jButtons[i - 1] = new JButton(i + "", new ImageIcon("cat/" + i + ".gif"))));
			jButtons[i - 1].addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		while (true) {

			if (e.getActionCommand().equals("1") || e.getActionCommand().equals("7")) {
				jButtons[0].setBackground(Color.BLACK);
				jButtons[6].setBackground(Color.BLACK);
			}

			if (e.getActionCommand().equals("2") || e.getActionCommand().equals("8")) {
				jButtons[1].setBackground(Color.BLACK);
				jButtons[7].setBackground(Color.BLACK);
			}

			if (e.getActionCommand().equals("3") || e.getActionCommand().equals("9")) {
				jButtons[2].setBackground(Color.BLACK);
				jButtons[8].setBackground(Color.BLACK);
			}

			if (e.getActionCommand().equals("4") || e.getActionCommand().equals("10")) {
				jButtons[3].setBackground(Color.BLACK);
				jButtons[9].setBackground(Color.BLACK);
			}

			if (e.getActionCommand().equals("5") || e.getActionCommand().equals("11")) {
				jButtons[4].setBackground(Color.BLACK);
				jButtons[10].setBackground(Color.BLACK);
			}

			if (e.getActionCommand().equals("6") || e.getActionCommand().equals("12")) {
				jButtons[5].setBackground(Color.BLACK);
				jButtons[11].setBackground(Color.BLACK);
			}

			if (e.getActionCommand().equals("14") || e.getActionCommand().equals("13")) {
				jButtons[12].setBackground(Color.BLACK);
				jButtons[13].setBackground(Color.BLACK);
			}

			if (e.getActionCommand().equals("16") || e.getActionCommand().equals("15")) {
				jButtons[14].setBackground(Color.BLACK);
				jButtons[15].setBackground(Color.BLACK);
			}
			break;
		}

	}

	public SetFrame() {

		initFrame();

		SetClock cl = new SetClock();
		final Thread thread = new Thread(cl);

		class Listener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == Start) {
					if (Count == 0) {
						isplaying = true;
						thread.start();
						setImage();// setButtonImage into gamePanel
						Count++;
						System.out.println("if" + Count);
						this.notifyAll();
					} else {
						try {
							System.out.println("else" + Count);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Count = 0;
					}
				}
				if (e.getSource() == Restart) {
					jPanelTotal.setVisible(false);
					isplaying = false;
				}
				if (e.getSource() == Pause) {
					if (Count == 0) {
						isplaying = false;
						Count = 1;
						System.out.println("in" + Count);
					}

					if (Count == 1) {
						synchronized (thread) {
							thread.notify();
							System.out.println("out" + Count);
							Count = 0;
						}
					}
				}
				if (e.getSource() == Exit) {
					dispose();
				}

			}
		}

		actionListener = new Listener();
		Start.addActionListener(actionListener);
		Restart.addActionListener(actionListener);
		Pause.addActionListener(actionListener);
		Exit.addActionListener(actionListener);
	}

	/* dispaly clock with use thread */
	public class SetClock implements Runnable, ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

		public void run() {
			Calendar startCalendar = Calendar.getInstance();
			long startTime = startCalendar.getTime().getTime(); // get start
																// time
			long endTime = startTime + 60 * 1000; // from start time + 2mins
			long nowTime, leftTime;
			Calendar now;
			System.out.println("run()");

			while (isplaying) {
				System.out.println("run().while");
				now = Calendar.getInstance();
				nowTime = now.getTime().getTime(); // get current time
				leftTime = (endTime - nowTime) / 1000; // from start time +
														// 2mins

				getTimeLabel.setText(leftTime + "sec."); // For dispaly clock

				System.out.println(leftTime + "sec.");

				if (leftTime == 0) {
					JOptionPane.showMessageDialog(jPanelTotal, "NO TIME,GAME OVER", "", JOptionPane.OK_OPTION);
					jPanelTotal.setVisible(false);
					isplaying = false;
					// break;
				}

				try {
					Thread.sleep(1000); // 1 second
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
