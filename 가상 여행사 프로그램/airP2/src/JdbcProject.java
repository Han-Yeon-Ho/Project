import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//import com.sun.prism.paint.Color;

//import iniProgram.MyDialog;



public class JdbcProject extends JFrame{
	Connection con = null;
	//public Airline airline = null;
	public Client client = null;
	MyDialog dialog = new MyDialog(this, "Please Insert ID & Password");	
	joinDialog jd = new joinDialog(this,"Join - Membership");
	int typeInt=0;
	JPanel contentPane;
	String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	String jdbcUrl = "jdbc:mysql://localhost/airline?characterEncoding=UTF-8&serverTimezone=UTC";
	String useDB = "airline";
	String userId = "root";
	String userpw = "1234";
	public JdbcProject(){
		setResizable(false);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
		
				dialog.setBackground(Color.white);	
		dialog.setVisible(true);
		
		//airline = new Airline();//항공사관리
		//client = new Client();//고객관리
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
								dialog.setVisible(true);
						
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
							new User(dialog.insID.getText(),dialog.insPW.getText());
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
dialog.okButton2.addActionListener(new ActionListener() {
		
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jd.setVisible(true);
				
			}
		});
jd.okButton.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = null;
		String authority=null;
		PreparedStatement ps = null;
		String tempid=null;
		int rs1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "1234");
			con = DriverManager.getConnection(jdbcUrl, userId, userpw);
			System.out.println("DB 연결 완료");
			tempid=jd.insID.getText();
			ps = (PreparedStatement) con.prepareStatement("select cus_id from customer where cus_id=?");
			ps.setString(1, jd.insID.getText());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				tempid=JOptionPane.showInputDialog("존재하는 회원 ID입니다. \n 다른 회원 ID를 입력하세요.");
				//ps.setString(1, tempid);
		}
			else {
			tempid=jd.insID.getText();
			}
				ps = (PreparedStatement) con.prepareStatement("insert into customer values(?,?,?,?,?,?)");
			
			
			
			ps.setString(1, tempid);
			ps.setString(2,  jd.insPW.getText());
			ps.setString(3,  "USER");
			ps.setString(4,  jd.insnm.getText());
			ps.setString(5,  jd.instel.getText());
			ps.setString(6,  jd.insadr.getText());
			
			 rs1 = 0;
				rs1 = ps.executeUpdate();
				if(rs1==0) {
					JOptionPane.showMessageDialog(null, "삽입에 실패하였습니다.");
				}
				else {
					new User(tempid,jd.insPW.getText());
				}
			
			System.out.println(sql);
			//sql = "select * from customer where cus_id like '"+dialog.insID.getText()+"'";
			
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		JLabel ti = new JLabel(" Hnu 여행사 ");
		TextField insID = new TextField("",15);
		TextField insPW = new TextField("",15);
		
		 Font font = new Font("arian", Font.BOLD, 15);
		    // TextField의 font를 설정합니다.
	
		JButton okButton = new JButton("LOG-IN");

		JButton okButton2 = new JButton("JOIN");


		
		public MyDialog(JFrame frame, String title) {
			super(frame,title);
			
			 Font font = new Font("arian", Font.BOLD, 20);
			 Font font1 = new Font("arian", Font.BOLD, 35);
			setLayout(null);
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			 ti.setBounds(40,5,240,50); 
			Lid.setBounds(20,60,90,30); 
			Lpw.setBounds(20,110,90,30);
			insID.setBounds(110, 60, 150, 30);
			insPW.setBounds(110, 110, 150, 30);
			okButton.setBounds(30, 155, 100, 30);
			okButton.setBackground(new Color(200,201,236));
			okButton.setForeground(Color.white);
			okButton2.setBounds(160, 155, 100, 30);
			okButton2.setBackground(new Color(200,201,236));
			okButton2.setForeground(Color.white);
			setResizable(false);
			
			
			 Dimension frameSize = this.getSize();
			    // 모니터 크기
			    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
			
			setBackground(Color.WHITE);
			insPW.setEchoChar('●');
			ti.setFont(font1);
			insID.setFont(font);
			insPW.setFont(font);
			add(ti);
			add(Lid);
			add(Lpw);
			add(insID);
			add(insPW);
			add(okButton);
			add(okButton2);
			
			//setBackground(Color.WHITE);
			setSize(300, 240);
			
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
				
			});
			okButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					joinDialog jd = new joinDialog(frame,"Join - Membership");
				}
				
			});
		}
		
	}
	class joinDialog extends JDialog {
		JLabel Lid = new JLabel(" I		D : ");
		JLabel Lpw = new JLabel(" PASSWORD :");
		JLabel name = new JLabel(" 이	름 : ");
		JLabel tel = new JLabel(" 전화  번호 : ");
		JLabel adr = new JLabel(" 주		소 : ");
		TextField insID = new TextField("",15);
		TextField insPW = new TextField("",15);
		TextField insnm = new TextField("",15);
		TextField instel = new TextField("",15);
		TextField insadr = new TextField("",15);
		 Font font = new Font("arian", Font.BOLD, 15);
		    // TextField의 font를 설정합니다.
	
		//JButton okButton = new JButton("LOG-IN");

		JButton okButton = new JButton("JOIN");


		
		public joinDialog(JFrame frame, String title) {
			super(frame,title);
			
			 Font font = new Font("arian", Font.BOLD, 20);
			 Font font1 = new Font("arian", Font.BOLD, 35);
			setLayout(null);
			 //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			 
			Lid.setBounds(20,10,90,30); 
			Lpw.setBounds(20,60,90,30);
			name.setBounds(20,110,90,30); 
			tel.setBounds(20,160,90,30);
			adr.setBounds(20,210,90,30);
			insID.setBounds(110, 10, 150, 30);
			insPW.setBounds(110, 60, 150, 30);
			insnm.setBounds(110, 110, 150, 30);
			instel.setBounds(110, 160, 150, 30);
			insadr.setBounds(110, 210, 150, 30);
			
			okButton.setBounds(80, 260, 150, 30);
			okButton.setBackground(new Color(200,201,236));
			okButton.setForeground(Color.white);
			setResizable(false);
			//setLocation(900,400);
			setBackground(Color.WHITE);
			//insPW.setEchoChar('●');
			 Dimension frameSize = this.getSize();
			    // 모니터 크기
			    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
			
			insID.setFont(font);
			insPW.setFont(font);
			insID.setFont(font);
			insPW.setFont(font);
			insnm.setFont(font);
			instel.setFont(font);
			insadr.setFont(font);
			add(Lid);
			add(Lpw);
			add(name);
			add(tel);
			add(adr);
			
			add(insID);
			add(insPW);
			add(insnm);
			add(instel);
			add(insadr);
			add(okButton);
		//	add(okButton2);
			
			//setBackground(Color.WHITE);
			setSize(300, 350);
			
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


