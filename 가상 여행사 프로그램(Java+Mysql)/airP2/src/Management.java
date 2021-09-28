import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Management  extends JFrame{
	Container contentPane;
	Airplane_flight Airplane_flight = new Airplane_flight();
	//Client client = new Client();
	Customer customer = new Customer();
	Reservation reservation  = new Reservation();
	Airplane airplane = new Airplane();
	public Management(){
		JPanel contentPane = new JPanel();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1070,545);
		contentPane = new JPanel();
	//	contentPaneC = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	//	contentPaneC.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(200,201,236));
		
		JLabel lblNewLabel = new JLabel("Hannam �����(������)");
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setFont(new Font(null, Font.BOLD, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 10, 1040, 50);
		contentPane.add(lblNewLabel);
	
		JTabbedPane jtab1 = new JTabbedPane(JTabbedPane.TOP); //���� ��ü ����
		jtab1.setBounds(20, 60, 1019, 433);
		jtab1.setBackground(Color.white);
		
		jtab1.addTab("�� ����", customer);
	//	JTabbedPane jtab2 = new JTabbedPane(); //���� ��ü ����
		//jtab1.setBounds(50, 40, 1019, 453);
		jtab1.addTab("���� ����", reservation);
		//JTabbedPane jtab3 = new JTabbedPane(); //���� ��ü ����
		//jtab3.setBounds(80, 40, 1019, 453);
		jtab1.addTab("�װ��� ����", Airplane_flight);
		
		//JTabbedPane jtab4 = new JTabbedPane(); //���� ��ü ����
		//jtab4.setBounds(100, 40, 1019, 453);
		
		jtab1.addTab("����� ����", airplane);
		
	//	jtab.addTab("�� ����", client);
		
//		JTabbedPane jtab2 = new JTabbedPane(); //���� ��ü ����
//		jtab2.setBounds(50, 51, 1029, 453);
//		jtab2.addTab("�װ��� ����2", airline);
		setContentPane(contentPane);
		setResizable(false);
		contentPane.add(jtab1);
	
		setVisible(true);
	}
}

