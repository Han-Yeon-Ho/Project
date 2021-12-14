import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.prism.paint.Color;

//import iniProgram.MyDialog;



public class JdbcProject extends JFrame{
	Connection con = null;
	//public Airline airline = null;
	public Client client = null;
	MyDialog dialog = new MyDialog(this, "Please Insert ID & Password");	
	int typeInt=0;
	JPanel contentPane;
	String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	String jdbcUrl = "jdbc:mysql://localhost/airline?characterEncoding=UTF-8&serverTimezone=UTC";
	String useDB = "airline";
	String userId = "root";
	String userpw = "1234";
	public JdbcProject(){
		setResizable(false);
//		String ID=null;
//		String authority=null;
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		String sql = null;
//		try {
//			Class.forName(jdbcDriver);
//			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "1234");
//			con = DriverManager.getConnection(jdbcUrl, userId, userpw);
//			System.out.println("DB 연결 완료");
//			sql = "select * from customer";
//			st = con.createStatement();
//			rs = st.executeQuery(sql);
//
//			if (st.execute(sql)) {
//				rs = st.getResultSet();
//			}
//
//			while (rs.next()) {
//			   
//				    ID=rs.getString("ID"); 
//				   authority=rs.getString("authority"); 
//				  // java.sql.Date wdate = rs.getDate("last_update"); 
//				   System.out.println("\t"+ID+" "
//							+authority+"\t");
//				   
//			}
//			
//			
//
//			
//		} catch (ClassNotFoundException e) {
//			System.out.println("JDBC 드라이버 로드 에러 : " + e.getMessage());
//			e.printStackTrace();
//		} catch (SQLException e) {
//			System.out.println("SQL 실행 에러: " + e.getMessage());
//			System.out.println("SQLState: " + e.getSQLState());
//			e.printStackTrace();
//		}
		
		 Dimension frameSize = this.getSize();
		    // 모니터 크기
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	
		
					
		dialog.setVisible(true);
		
		//airline = new Airline();//항공사관리
		client = new Client();//고객관리
		String type=null;

		 
		
		
	
//		type=dialog.insID.getText();
		dialog.okButton.addActionListener(new ActionListener() {
		
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					con = null;
					Statement st = null;
					ResultSet rs = null;
					String sql = null;
					String authority=null;
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "1234");
					con = DriverManager.getConnection(jdbcUrl, userId, userpw);
					System.out.println("DB 연결 완료");
					
					sql=  "select * from customer where cus_id like '"+dialog.insID.getText()+"' and cus_pswd like '"+dialog.insPW.getText()+"'";
					System.out.println(sql);
					//sql = "select * from customer where cus_id like '"+dialog.insID.getText()+"'";
					st = con.createStatement();
					rs = st.executeQuery(sql);
					if (st.execute(sql)) {
						rs = st.getResultSet();
					}
					
			
					if (st.execute(sql)) {
						rs = st.getResultSet();
					}
					if(!(rs.next())) {
						JOptionPane.showMessageDialog(null, "일치하지 않는 ID 및 PASSWORD 입니다.", "Message", 
								JOptionPane.ERROR_MESSAGE); 
						dialog.show();
					}
					else {
						rs = st.executeQuery(sql);
					//rs = st.getResultSet();
					while (rs.next()) {
						 authority=rs.getString("cus_lv"); 
						 System.out.println(authority);
						 if(authority.equals("MASTER")) {
							typeInt=1;
							System.out.println(typeInt);
							//con.close();
							new Management();
						 }
						 else
						 {
							typeInt=2;
							System.out.println(typeInt);
						//	rs.close();
						 }
					}
					}
				} catch (ClassNotFoundException e1) {
					System.out.println("JDBC 드라이버 로드 에러 : " + e1.getMessage());
					e1.printStackTrace();
				} catch (SQLException e2) {
					System.out.println("SQL 실행 에러: " + e2.getMessage());
					System.out.println("SQLState: " + e2.getSQLState());
					e2.printStackTrace();
				}
				
					
				
			}
		});
//	if(typeInt==1) {
//		// setVisible(true);
//			//contentPaneC.setVisible(true);
//	
//		// new Management().setVisible(true);
//		//	contentPane.add(jtab1);
//		//	contentPane.add(jtab2);
//			contentPane.setVisible(true);
//		
//			//setSize(1070,545);
//			//setVisible(true);
//	}
		/*
		 * else if(typeInt==2) {
		 * 
		 * 
		 * // contentPane.setVisible(true); // contentPaneC.add(jtabC1); //
		 * contentPaneC.setVisible(true); // setContentPane(contentPaneC);
		 * setSize(1070,545); // }
		 */
	//setSize(1070,545);
//	 setSize(1070,545);
	
	//	setVisible(true);
		setResizable(false);
	}
	class MyDialog extends JDialog {
		JLabel Lid = new JLabel(" I      D ");
		JLabel Lpw = new JLabel(" PASSWORD ");
		TextField insID = new TextField("",15);
		TextField insPW = new TextField("",15);
		
		 Font font = new Font("arian", Font.BOLD, 15);
		    // TextField의 font를 설정합니다.
	
		JButton okButton = new JButton("LOG-IN");


		
		public MyDialog(JFrame frame, String title) {
			super(frame,title);
			
			 Font font = new Font("arian", Font.BOLD, 20);
			setLayout(null);
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Lid.setBounds(20,15,90,30); 
			Lpw.setBounds(20,65,90,30);
			insID.setBounds(110, 15, 150, 30);
			insPW.setBounds(110, 65, 150, 30);
			okButton.setBounds(70, 105, 150, 30);
			
			insPW.setEchoChar('●');
			insID.setFont(font);
			insPW.setFont(font);
			add(Lid);
			add(Lpw);
			add(insID);
			add(insPW);
			add(okButton);
			
			//setBackground(Color.WHITE);
			setSize(300, 200);
			
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JdbcProject pro = new JdbcProject();

		
	}

}

