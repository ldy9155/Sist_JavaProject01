package TwoRukSer;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.acl.Group;

import javax.accessibility.Accessible;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Erukser extends Frame implements ActionListener{

	private JLabel jLabel1, jLabel2,jLabel3,jLabel4,jLabel5,jLabel6,jLabel7,jLabel8,jLabel9,jLabel10,jLabel11;
	private JTextField jTextField1, jTextField2, jTextField3, jTextField4, jTextField5,
	        jTextField6, jTextField7, jTextField8;
	private JButton jButton1, jButton2, jButton3;
	private JPanel jPanel,jPanel1, jPanel2, jPanel3, jPanel4, jPanel5,jPanel6,jPanel7,jPanel8, jPanelTOT;
	private JRadioButton jRadioButton, jRadioButton1, jRadioButton2, jRadioButton3, jRadioButton4,jRadioButton5;
	private JTextArea jtextArea;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (jButton1==e.getSource()) {
		}
		if (jButton3==e.getSource()) {
		}
		if (jButton2==e.getSource()) {
			dispose();
		}
	}

	public void init(){
		
		jPanel1 = new JPanel(new GridLayout(1,1));
		jPanel2 = new JPanel(new GridLayout(2,1));
		jPanel3 = new JPanel(new GridLayout(4,2));
		//----- ���� row
		
		jPanel4 = new JPanel(new GridLayout());
		jPanel5 = new JPanel(new GridLayout());
		jPanel6 = new JPanel(new FlowLayout());
		jPanel7 = new JPanel(new GridLayout(4,2));
		jPanel8 = new JPanel(new BorderLayout());
		
		jPanelTOT = new JPanel(new BorderLayout());
		
		jPanel2.add(jLabel2 = new JLabel("         ��      ��     ��  "));
		jPanel2.add(jPanel3);
		
		jPanel3.add(jLabel4 = new JLabel("��  ��"));
		jPanel3.add(jTextField1 = new JTextField());
		
		
		jPanel1.add("West",jLabel3= new JLabel("���� �Է� Ŭ��"));
		jPanel1.add("East", jPanel2);
		
		
		jPanel3.add(jLabel5= new JLabel("�ֹ� ��ȣ"));
		jPanel3.add(jTextField2 = new JTextField());
		
		jRadioButton = new JRadioButton("���б� ����");
		jRadioButton1 = new JRadioButton("����б� ����");
		jRadioButton2 = new JRadioButton("���б� ����");
		jRadioButton3 = new JRadioButton("�ڻ� ����");
		jRadioButton4 = new JRadioButton("��Ÿ");
		
		jButton1 = new JButton("Ȯ��");
		jButton2 = new JButton("���");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(jRadioButton);
		bg.add(jRadioButton1);
		bg.add(jRadioButton2);
		bg.add(jRadioButton3);
		bg.add(jRadioButton4);
		
		jPanel4.add(jLabel6= new JLabel("�з�"));
		jPanel4.add(jRadioButton);
		jPanel4.add(jRadioButton1);
		jPanel4.add(jRadioButton2);
		jPanel4.add(jRadioButton3);
		jPanel4.add(jRadioButton4);
		
		jPanel1.setBorder(new LineBorder(Color.black));
		jPanel2.setBorder(new LineBorder(Color.black));
		jPanel4.setBorder(new LineBorder(Color.black));
		jPanel5.setBorder(new LineBorder(Color.black));
		
		jLabel8 = new JLabel("�ּ�");
		jTextField2 = new JTextField();
		jLabel9 = new JLabel("��ȭ ��ȣ");
		jTextField3 = new JTextField();
		jLabel10 = new JLabel("���");
		jTextField5 = new JTextField();
		jLabel11 = new JLabel("����");
		jTextField4 = new JTextField();
		
		jPanel7.add(jLabel8);
		jPanel7.add(jLabel9);
		jPanel7.add(jTextField2);
		jPanel7.add(jTextField3);

		jPanel7.add(jLabel10);
		jPanel7.add(jLabel11);
		jPanel7.add(jTextField5);
		jPanel7.add(jTextField4);
		
		jPanel5.add(jLabel7= new JLabel("�ڱ� �Ұ���"));
		jPanel5.add(jtextArea = new JTextArea(5,25));
		
		jPanel8.add("North",jPanel4);
		jPanel8.add("Center",jPanel7);
		jPanel8.add("South",jPanel5);
		
		jPanel6.add(jButton1); // ��ư
		jPanel6.add(jButton2);

		jButton1.addActionListener(this);
		jButton2.addActionListener(this);
		
		
		jPanelTOT.add("North",jPanel1);
		jPanelTOT.add("Center",jPanel8);
		jPanelTOT.add("South",jPanel6);
		this.add(jPanelTOT);
	}
	
   public Erukser() {
	   super("�� �� ��");
	   init();
	   
      this.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            // TODO Auto-generated method stub
            dispose();
         }
      });
      
      this.setVisible(true); //�������� �����־��
      Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//ȭ�� �ػ󵵸� ���´�.
      this.setSize(600, 600); // ������â ũ�� ����

      int height = (int) (d.getHeight() / 2 - this.getHeight() / 2);
      int width = (int) (d.getWidth() / 2 - this.getHeight() / 2);

      this.setLocation(new Point(width, height));//������â �⺻ ���� ��ġ ���� ���� 500,500
      //this.pack(); // ���� �� , ������ �ּ�â
   }
   public static void main(String[] args) {
      new Erukser();
   }
}