import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class User  extends JFrame{
	String id;
	Container contentPane;
	
	
	Customer customer = new Customer();
	
	Airplane airplane = new Airplane();
	public User(String id, String pw){
		this.id=id;
		JPanel contentPane = new JPanel();
		Client client = new Client(id, pw);
		CAirplane_flight CAirplane_flight = new CAirplane_flight(id);
		CReservation reservation  = new CReservation(id);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1070,545);
		contentPane = new JPanel();
	//	contentPaneC = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	//	contentPaneC.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(200,201,236));
		
		JLabel lblNewLabel = new JLabel("Hannam ¿©Çà»ç(°í°´)");
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setFont(new Font(null, Font.BOLD, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 10, 1040, 50);
		contentPane.add(lblNewLabel);
	
		JTabbedPane jtab1 = new JTabbedPane(JTabbedPane.TOP); //ÅÇÆÒ °´Ã¼ »ý¼º
		jtab1.setBounds(20, 60, 1019, 433);
		jtab1.setBackground(Color.white);
		
		jtab1.addTab("ÀÚ±â Á¤º¸", client);
	//	JTabbedPane jtab2 = new JTabbedPane(); //ÅÇÆÒ °´Ã¼ »ý¼º
		//jtab1.setBounds(50, 40, 1019, 453);
		jtab1.addTab("Ç×°øÆí Á¤º¸", CAirplane_flight);
		
		
		jtab1.addTab("¿¹¾à Á¤º¸", reservation);
		//JTabbedPane jtab3 = new JTabbedPane(); //ÅÇÆÒ °´Ã¼ »ý¼º
		//jtab3.setBounds(80, 40, 1019, 453);
		
		
		//JTabbedPane jtab4 = new JTabbedPane(); //ÅÇÆÒ °´Ã¼ »ý¼º
		//jtab4.setBounds(100, 40, 1019, 453);
		
		//jtab1.addTab("ºñÇà±â Á¤º¸", airplane);
		
	//	jtab.addTab("°í°´ °ü¸®", client);
		
//		JTabbedPane jtab2 = new JTabbedPane(); //ÅÇÆÒ °´Ã¼ »ý¼º
//		jtab2.setBounds(50, 51, 1029, 453);
//		jtab2.addTab("Ç×°ø»ç °ü¸®2", airline);
		setContentPane(contentPane);
		setResizable(false);
		contentPane.add(jtab1);
	
		setVisible(true);
	}
}

