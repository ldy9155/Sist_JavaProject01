package Management;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

public class View extends Frame implements ActionListener {
	final static String FILEPATH = "d:\\memberfile.txt";
	private JTable jTable;
	private Object[][] data;
	private JTableModel jTableModel;
	private JPanel jPanelAdd, jPanelAddCenter, jPanel1, jPanel2, jPanel3, jPanelBtn, jPanelLeft;
	private JLabel jLabelTitle, jLabelID, jLabelArea, jLabelName, jLabelPhone, jLabelBirth, jLabelEmail;
	private JTextField jTextID, jTextArea, jTextName, jTextPhone, jTextBirth, jTextEmail;
	private JButton jbtnAdd, jbtnDelete;
	private MemberBean bean = null;
	private Vector<MemberBean> vector = new Vector<MemberBean>();
	private int row = -1;

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtnAdd) {
			if (memberBool()) {
				if (row != -1) {
					vector.remove(row);
					row=-1;
				}
					addMember();
					clearField();
					saveMember();
				
			} else {
				JOptionPane.showMessageDialog(this, "null error");
			}
		}
		if (obj == jbtnDelete) {
			System.out.println("Delete");
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "no selection error");
			} else {
				vector.remove(row);
				saveMember();
			}
		}

	}

	public boolean memberBool() {
		return !(jTextID.getText().equals("") || jTextArea.getText().equals("") || jTextName.getText().equals("")
				|| jTextPhone.getText().equals("") || jTextBirth.getText().equals("")
				|| jTextEmail.getText().equals(""));
	}

	public void clearField() {
		jTextID.setText("");
		jTextArea.setText("");
		jTextName.setText("");
		jTextPhone.setText("");
		jTextBirth.setText("");
		jTextEmail.setText("");
		jTextID.requestFocus();
	}

	public void setObject() {
		data = new Object[vector.size()][6];
		for (int i = 0; i < vector.size(); i++) {
			for (int j = 0; j < data[i].length;) {
				MemberBean b = (MemberBean) vector.get(i);
				data[i][j++] = b.getjTextID();
				data[i][j++] = b.getjTextArea();
				data[i][j++] = b.getjTextName();
				data[i][j++] = b.getjTextPhone();
				data[i][j++] = b.getjTextBirth();
				data[i][j++] = b.getjTextEmail();
			}
		}
		jTable.setModel(new JTableModel(data));
	}

	public void openMember() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File(View.FILEPATH)));
			vector = (Vector<MemberBean>) ois.readObject();

		} catch (Exception e) {
			// TODO: handle exception
			vector = new Vector<MemberBean>();
		} finally {
			try {
				if (ois != null)
					ois.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	public void saveMember() {

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File(View.FILEPATH)));
			oos.writeObject(vector);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null)
					oos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		setObject();
		jTable.setModel(new JTableModel(data));
	}

	public void addMember() {
		MemberBean memberBean = new MemberBean();
		memberBean.setjTextID(jTextID.getText());
		memberBean.setjTextArea(jTextArea.getText());
		memberBean.setjTextName(jTextName.getText());
		memberBean.setjTextPhone(jTextPhone.getText());
		memberBean.setjTextBirth(jTextBirth.getText());
		memberBean.setjTextEmail(jTextEmail.getText());

		vector.add(memberBean);

		saveMember();
	}

	public View() {

		// right layout
		jPanelAdd = new JPanel(new BorderLayout());
		jPanelAddCenter = new JPanel(new GridLayout(3, 1));
		jLabelTitle = new JLabel("|     J O I N    M E M B E R S H I P     |", 0);

		// ID ,PW
		jPanel1 = new JPanel(new GridLayout(1, 4));
		jPanel1.add(jLabelID = new JLabel("ID : "));
		jPanel1.add(jTextID = new JTextField());
		jPanel1.add(jLabelArea = new JLabel("AREA : "));
		jPanel1.add(jTextArea = new JTextField());

		// NAME, PHONE
		jPanel2 = new JPanel(new GridLayout(1, 4));
		jPanel2.add(jLabelName = new JLabel("NAME : "));
		jPanel2.add(jTextName = new JTextField());
		jPanel2.add(jLabelPhone = new JLabel("PHONE : "));
		jPanel2.add(jTextPhone = new JTextField());

		// BIRTH, EMAIL
		jPanel3 = new JPanel(new GridLayout(1, 4));
		jPanel3.add(jLabelBirth = new JLabel("BIRTH : "));
		jPanel3.add(jTextBirth = new JTextField());
		jPanel3.add(jLabelEmail = new JLabel("EMAIL : "));
		jPanel3.add(jTextEmail = new JTextField());

		// Button
		jPanelBtn = new JPanel();
		jPanelBtn.add("East", jbtnAdd = new JButton("RESIST"));
		jPanelBtn.add("East", jbtnDelete = new JButton("DELETE"));

		jPanelAddCenter.add(jPanel1);
		jPanelAddCenter.add(jPanel2);
		jPanelAddCenter.add(jPanel3);

		jPanelAdd.add("North", jLabelTitle);
		jPanelAdd.add("Center", jPanelAddCenter);
		jPanelAdd.add("South", jPanelBtn);

		// left layout
		jPanelLeft = new JPanel();

		openMember();
		jTableModel = new JTableModel(data);
		jTable = new JTable(jTableModel);
		jPanelLeft.add("Center", new JScrollPane(jTable = new JTable(jTableModel),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		jbtnAdd.addActionListener(this);
		jbtnDelete.addActionListener(this);

		this.add(jPanelLeft);
		this.add(jPanelAdd);
		setObject();
		saveMember();

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		jTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					row = jTable.getSelectedRow();
					System.out.println(row);
					jTextID.setText(vector.get(row).jTextID);
					jTextArea.setText(vector.get(row).jTextArea);
					jTextName.setText(vector.get(row).jTextName);
					jTextPhone.setText(vector.get(row).jTextPhone);
					jTextBirth.setText(vector.get(row).jTextBirth);
					jTextEmail.setText(vector.get(row).jTextEmail);
				}
			}

		});

		this.setLayout(new GridLayout(1, 2));
		this.setVisible(true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(1000, 480);
		this.setLocation(new Point((d.width / 2) - (this.getWidth() / 2), (d.height / 2) - (this.getHeight() / 2)));
	}

	public static void main(String[] args) {
		new View();
	}
}
