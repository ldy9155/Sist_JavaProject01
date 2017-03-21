package Calculator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Calculator extends JFrame implements ActionListener {
	private JLabel jLabel;
	private JTextArea jTextArea;
	private JMenuBar jMenuBar;
	private JMenu view, edit, help;
	private JMenuItem general, engineering, record;
	private JMenuItem copy, paste;
	private JMenuItem helpInfo, Info;
	private JButton jButton[];
	private double result, route, temp, cnt = 0.0;
	private String operator;
	Vector<Double> vector = new Vector<Double>();
	GridBagLayout gbl;
	GridBagConstraints gbc;
	Font font = new Font("����ü", 0, 50);

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		// ���ڹ�ư
		for (int i = 1; i < 10; i++) {
			if (obj == jButton[i])
				if (cnt == 0.0) {
					jTextArea.append(String.valueOf(i));
				} else {
					jTextArea.setText(String.valueOf(i));
				}
		}
		if (obj == jButton[0]) { // 0 
				if (jTextArea.getText().length() > 0 || jTextArea.getText().contains(".")) {
					jTextArea.append("0");
				} else {
					jTextArea.setText("0");
				}
		}		

		// �����ư
		if (obj == jButton[16]) {	 // /
			try {
				if (vector.size() == 0) {
					vector.add(Double.valueOf(jTextArea.getText()));
					jTextArea.setText("");
				} else {
					vector.add(Double.valueOf(jTextArea.getText()));
					temp = vector.get(0) / vector.get(1);
					vector.clear();
					vector.add(temp);
				}
				jTextArea.setText("");
				operator = "/";
			} catch (Exception e2) {
			}
		}
		if (obj == jButton[18]) {	 // *
			try {
				if (vector.size() == 0) {
					vector.add(Double.valueOf(jTextArea.getText()));
				} else {
					vector.add(Double.valueOf(jTextArea.getText()));
					temp = vector.get(0) * vector.get(1);
					vector.clear();
					vector.add(temp);
				}
				jTextArea.setText("");
				operator = "*";
			} catch (Exception e2) {
			}
		}
		if (obj == jButton[20]) {	 // -
			try {
				if (vector.size() == 0) {
					vector.add(Double.valueOf(jTextArea.getText()));
				} else {
					vector.add(Double.valueOf(jTextArea.getText()));
					temp = vector.get(0) - vector.get(1);
					vector.clear();
					vector.add(temp);
				}
				jTextArea.setText("");
				operator = "-";
			} catch (Exception e2) {
			}
		}
		if (obj == jButton[21]) { 	// +
			try {
				if (vector.size() == 0) {
					vector.add(Double.valueOf(jTextArea.getText()));
				} else {
					vector.add(Double.valueOf(jTextArea.getText()));
					temp = vector.get(0) + vector.get(1);
					vector.clear();
					vector.add(temp);
				}
				jTextArea.setText("");
				operator = "+";
			} catch (Exception e2) {
			}
		}
		if (obj == jButton[22]) { 	// =
			try {
				vector.add(Double.valueOf(jTextArea.getText()));
				CalculatorFinal();
				jTextArea.setText(String.valueOf(result));
				vector.clear();
				cnt++;
			} catch (Exception e2) {
			}
		}

		// ������ ��ư
		if (obj == jButton[10]) { // .
			if(!jTextArea.getText().contains(".")){
			jTextArea.append(".");
			}else{
				
			}
		}
		if (obj == jButton[11]) { // ��
			if (jTextArea.getText().length() != 0) {
				jTextArea.setText(jTextArea.getText().substring(0, jTextArea.getText().length() - 1));
			}
		}
		if (obj == jButton[12]) { // ce
			jTextArea.setText("");
		}
		if (obj == jButton[13]) { // c
			jTextArea.setText("");
			vector.clear();
			cnt=0;
		}
		if (obj == jButton[14]) { // +-
			if (jTextArea.getText().length() != 0) {
				if (Double.valueOf(jTextArea.getText()) > 0) {
					jTextArea.setText("-" + jTextArea.getText());
				} else {
					jTextArea.setText(jTextArea.getText().substring(1, jTextArea.getText().length()));
				}
			}
		}
		if (obj == jButton[15]) { // ^
			if (jTextArea.getText().length() != 0) {
				route = Double.valueOf(jTextArea.getText());
				jTextArea.setText(String.valueOf(Math.pow(route,2)));
			}
		}
		if (obj == jButton[19]) { // 1/x
			if (jTextArea.getText().length() != 0) {
				jTextArea.setText(String.valueOf(1 / Double.valueOf(jTextArea.getText())));
			}
		}
		if (obj == jButton[17]) { // %
			if(jTextArea.getText().length() != 0 && vector.size()!=0){
				jTextArea.setText(String.valueOf(vector.get(0)*Double.parseDouble(jTextArea.getText())/100));
			}else{
				jTextArea.setText("0");
			}
		}
	}

	public double CalculatorFinal() {
		switch (operator) {
		case "/":
			result = vector.get(0) / vector.get(1);
			vector.clear();
			vector.add(result);
			return vector.get(0);
		case "*":
			result = vector.get(0) * vector.get(1);
			vector.clear();
			vector.add(result);
			return vector.get(0);
		case "-":
			result = vector.get(0) - vector.get(1);
			vector.clear();
			vector.add(result);
			return vector.get(0);
		case "+":
			result = vector.get(0) + vector.get(1);
			vector.clear();
			vector.add(result);
			return vector.get(0);
		}
		return -1;
	}

	public void initMenu() {
		jMenuBar = new JMenuBar();

		view = new JMenu("����(V)");
		view.add(general = new JMenuItem("�Ϲݿ�(T)"));
		view.add(engineering = new JMenuItem("���п�(S)"));
		view.addSeparator();
		view.add(record = new JMenuItem("���(Y)"));
		jMenuBar.add(view);

		edit = new JMenu("����(E)");
		jMenuBar.add(edit);
		edit.add(copy = new JMenuItem("����(C)"));
		edit.add(paste = new JMenuItem("�ٿ��ֱ�(P)"));

		help = new JMenu("����(H)");
		help.add(helpInfo = new JMenuItem("���� ����(V)"));
		help.addSeparator();
		help.add(Info = new JMenuItem("���� ����(A)"));
		jMenuBar.add(help);

		this.setJMenuBar(jMenuBar);

	}

	public void add(Component c, int x, int y, int w, int h) { // GridBagLayout�޼���
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(c, gbc);

		add(c);
	}

	public Calculator() {
		// �޴��ٸ޼���
		initMenu();

		// Layout, Button ����
		jTextArea = new JTextArea();
		jTextArea.setFont(font);
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();

		setLayout(gbl);

		gbc.fill = GridBagConstraints.BOTH;

		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		jButton = new JButton[23];
		for (int i = 0; i < 10; i++) {
			jButton[i] = new JButton(String.valueOf(i));

			jButton[i].setBackground(Color.gray);
		}
		jButton[10] = new JButton(".");
		jButton[10].setBackground(Color.gray);
		jButton[11] = new JButton("��");
		jButton[12] = new JButton("CE");
		jButton[13] = new JButton("C");
		jButton[14] = new JButton("+-");
		jButton[15] = new JButton("^");
		jButton[16] = new JButton("/");
		jButton[17] = new JButton("%");
		jButton[18] = new JButton("*");
		jButton[19] = new JButton("1/x");
		jButton[20] = new JButton("-");
		jButton[21] = new JButton("+");
		jButton[22] = new JButton("=");

		for (int i = 11; i < jButton.length; i++) {
			jButton[i].setBackground(Color.pink);
		}

		// Layout�� button���̱�
		add(jTextArea, 0, 0, 0, 1);
		add(jButton[11], 0, 1, 1, 1);
		add(jButton[12], 1, 1, 1, 1);
		add(jButton[13], 2, 1, 1, 1);
		add(jButton[14], 3, 1, 1, 1);
		add(jButton[15], 4, 1, 1, 1);
		add(jButton[16], 3, 2, 1, 1);
		add(jButton[17], 4, 2, 1, 1);
		add(jButton[18], 3, 3, 1, 1);
		add(jButton[19], 4, 3, 1, 1);
		add(jButton[20], 3, 4, 1, 1);
		add(jButton[21], 3, 5, 1, 1);
		add(jButton[22], 4, 4, 1, 2);
		add(jButton[10], 2, 5, 1, 1);
		add(jButton[0], 0, 5, 2, 1);
		add(jButton[1], 0, 4, 1, 1);
		add(jButton[2], 1, 4, 1, 1);
		add(jButton[3], 2, 4, 1, 1);
		add(jButton[4], 0, 3, 1, 1);
		add(jButton[5], 1, 3, 1, 1);
		add(jButton[6], 2, 3, 1, 1);
		add(jButton[7], 0, 2, 1, 1);
		add(jButton[8], 1, 2, 1, 1);
		add(jButton[9], 2, 2, 1, 1);

		jTextArea.setEditable(false);

		for (int i = 0; i < jButton.length; i++) {
			jButton[i].addActionListener(this);
		}

		this.setTitle("����");

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		this.setVisible(true);
		this.setBounds(100, 100, 350, 500);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	public static void main(String[] args) { 
		new Calculator();
	}
}
