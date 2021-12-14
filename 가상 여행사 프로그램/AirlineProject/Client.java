import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

class Client extends JPanel{
	
	Connection con = null;
	
	private String colName1[] = {"항공사ID","항공사이름","주소","전화번호","국적"};
	private DefaultTableModel model1 = new DefaultTableModel(colName1, 0);
	
	private JTable table_2; //항공사관리의 테이블
	private JScrollPane scrollPane_1; //항공사관리의 스크롤펜
	private JButton button_1; //전체보기
	private JLabel label_1; //항공사이름으로 검색
	private JLabel label_2; //검색어. 항공사이름
	private JTextField textField_1; //검색어. 항공사이름
	private JButton button_2; //검 색.
	private JLabel lblid_1; //항공사ID로 삭제
	private JLabel label_3; //검색어. 항공사ID
	private JTextField textField_2; //검색어. 항공사ID
	private JButton button_3; //삭 제.
	private JButton button_4; //삽 입.
	
	private JTextField textField_3; //항공사ID
	private JTextField textField_4; //항공사 이름
	private JTextField textField_5; //주소
	private JTextField textField_6; //전화번호
	private JTextField textField_7; //국적
	
	private JLabel lblid_2;//항공사 ID
	private JLabel label_4; //항공사 이름
	private JLabel label_5; //주소
	private JLabel label_6; //전화번호
	private JLabel label_7; //국적
	
	public Client() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");				
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "1234");
			System.out.println("DB연결 완료");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러 : " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
		
	
	setLayout(null);
	
	table_2 = new JTable(model1);
	table_2.setBounds(12, 10, 847, 382);
	add(table_2);
	
	scrollPane_1 = new JScrollPane(table_2);
	scrollPane_1.setBounds(12, 10, 782, 347);
	 add(scrollPane_1);
	
	button_1 = new JButton("전체보기");
	button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = (PreparedStatement) con.prepareStatement("SELECT * FROM customer");
				rs = ps.executeQuery();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		 // JTable 내용제거
		    for(int i= model1.getRowCount()-1; i>=0; i--) {
		     model1.removeRow(i);
		    }
		    try {
				while(rs.next()) {
				    // JTable 내용추가
				    String[] row = new String[5];
				    row[0] = rs.getString("항공사ID");
				    row[1] = rs.getString("항공사이름");
				    row[2] = rs.getString("주소");
				    row[3] = rs.getString("전화번호");
				    row[4] = rs.getString("국적");
				    model1.addRow(row);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	});
	button_1.setBounds(806, 10, 206, 43);
	 add(button_1);
	
	label_1 = new JLabel("항공사이름으로 검색");
	label_1.setHorizontalAlignment(SwingConstants.CENTER);
	label_1.setBounds(807, 58, 205, 30);
	 add(label_1);
	
	label_2 = new JLabel("검색어 :");
	label_2.setBounds(807, 98, 57, 29);
	 add(label_2);
	
	textField_1 = new JTextField();
	textField_1.setColumns(10);
	textField_1.setBounds(876, 95, 137, 32);
	 add(textField_1);
	
	button_2 = new JButton("검   색");
	button_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = (PreparedStatement) con.prepareStatement("SELECT * FROM airline WHERE 항공사이름 LIKE CONCAT('%', ?, '%')");
				ps.setString(1, textField_1.getText());
				
				rs = ps.executeQuery();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		 // JTable 내용제거
		    for(int i= model1.getRowCount()-1; i>=0; i--) {
		     model1.removeRow(i);
		    }
		    try {
				while(rs.next()) {
				    // JTable 내용추가
					String[] row = new String[5];
				    row[0] = rs.getString("항공사ID");
				    row[1] = rs.getString("항공사이름");
				    row[2] = rs.getString("주소");
				    row[3] = rs.getString("전화번호");
				    row[4] = rs.getString("국적");
				    model1.addRow(row);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	});
	button_2.setBounds(807, 137, 206, 43);
	 add(button_2);
	
	lblid_1 = new JLabel("항공사ID로 삭제");
	lblid_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblid_1.setBounds(808, 200, 205, 30);
	 add(lblid_1);
	
	label_3 = new JLabel("검색어");
	label_3.setBounds(806, 235, 57, 29);
	 add(label_3);
	
	textField_2 = new JTextField();
	textField_2.setColumns(10);
	textField_2.setBounds(875, 232, 137, 32);
	 add(textField_2);
	
	button_3 = new JButton("삭   제");
	button_3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			PreparedStatement ps = null;
			PreparedStatement ps1 = null;
			ResultSet rs1 = null;
			try {
				ps = (PreparedStatement) con.prepareStatement("DELETE FROM airline WHERE 항공사ID = '"+textField_2.getText()+"'");
				ps1 = (PreparedStatement) con.prepareStatement("SELECT * FROM airline");
				
				int rs = 0;
			
				rs = ps.executeUpdate();
				if(rs==0) {
					JOptionPane.showMessageDialog(null, "삭제에 실패하였습니다.");
				}
				rs1 = ps1.executeQuery();
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}

		 // JTable 내용제거
		    for(int i= model1.getRowCount()-1; i>=0; i--) {
		     model1.removeRow(i);
		    }
		    try {
				while(rs1.next()) {
				    // JTable 내용추가
					String[] row = new String[5];
				    row[0] = rs1.getString("항공사ID");
				    row[1] = rs1.getString("항공사이름");
				    row[2] = rs1.getString("주소");
				    row[3] = rs1.getString("전화번호");
				    row[4] = rs1.getString("국적");
				    model1.addRow(row);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	});
	button_3.setBounds(806, 274, 206, 43);
	 add(button_3);
	
	button_4 = new JButton("삽   입");
	button_4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			PreparedStatement ps = null;
			PreparedStatement ps1 = null;
			
			ResultSet rs1 = null;
			try {
				ps = (PreparedStatement) con.prepareStatement("insert into airline values('"+textField_3.getText()+"','"+textField_4.getText()+"','"+textField_5.getText()+"','"+textField_6.getText()+"','"+textField_7.getText()+"')");
				ps1 = (PreparedStatement) con.prepareStatement("SELECT * FROM airline");
				
				int rs = 0;
				
				rs = ps.executeUpdate();
				rs1 = ps1.executeQuery();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		 // JTable 내용제거
		    for(int i= model1.getRowCount()-1; i>=0; i--) {
		     model1.removeRow(i);
		    }
		    try {
				while(rs1.next()) {
				    // JTable 내용추가
					String[] row = new String[5];
				    row[0] = rs1.getString("항공사ID");
				    row[1] = rs1.getString("항공사이름");
				    row[2] = rs1.getString("주소");
				    row[3] = rs1.getString("전화번호");
				    row[4] = rs1.getString("국적");
				    model1.addRow(row);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	});
	button_4.setBounds(807, 363, 206, 49);
	 add(button_4);
	
	textField_3 = new JTextField();
	textField_3.setBounds(12, 387, 153, 25);
	 add(textField_3);
	textField_3.setColumns(10);
	
	textField_4 = new JTextField();
	textField_4.setColumns(10);
	textField_4.setBounds(169, 387, 153, 25);
	 add(textField_4);
	
	textField_5 = new JTextField();
	textField_5.setColumns(10);
	textField_5.setBounds(326, 387, 153, 25);
	 add(textField_5);
	
	textField_6 = new JTextField();
	textField_6.setColumns(10);
	textField_6.setBounds(481, 387, 153, 25);
	 add(textField_6);
	
	textField_7 = new JTextField();
	textField_7.setColumns(10);
	textField_7.setBounds(641, 387, 153, 25);
	 add(textField_7);
	
	lblid_2 = new JLabel("항공사ID");
	lblid_2.setHorizontalAlignment(SwingConstants.CENTER);
	lblid_2.setBounds(12, 367, 153, 15);
	 add(lblid_2);
	
	label_4 = new JLabel("항공사이름");
	label_4.setHorizontalAlignment(SwingConstants.CENTER);
	label_4.setBounds(169, 367, 153, 15);
	 add(label_4);
	
	label_5 = new JLabel("주소");
	label_5.setHorizontalAlignment(SwingConstants.CENTER);
	label_5.setBounds(326, 367, 153, 15);
	 add(label_5);
	
	label_6 = new JLabel("전화번호");
	label_6.setHorizontalAlignment(SwingConstants.CENTER);
	label_6.setBounds(481, 367, 153, 15);
	 add(label_6);
	
	label_7 = new JLabel("국적");
	label_7.setHorizontalAlignment(SwingConstants.CENTER);
	label_7.setBounds(641, 367, 153, 15);
	 add(label_7);
	}

}
