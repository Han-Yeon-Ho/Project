import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

//import jdk.nashorn.internal.objects.Global;

class CReservation extends JPanel{
	TableCellRenderer renderer = new MyTableCellRenderer();

	//table.setDefaultRenderer(Class.forName("java.lang.Object"), renderer);

	 
	Connection con = null;
	JComboBox strCombo;
	JComboBox strCombo2;
	private String colName1[] = {"예약번호","항공편 번호","비행기ID","고객ID","고객이름","출발지","도착지","출발시간","도착시간"};
	private DefaultTableModel model1 = new DefaultTableModel(colName1, 0) {
        // Jtable 내용 편집 안되게
        public boolean isCellEditable(int i, int c) {
            return false;
        }
    };

    private ListSelectionModel model2;
    
	
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
	String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	String jdbcUrl = "jdbc:mysql://localhost/airline?characterEncoding=UTF-8&serverTimezone=UTC";
	String useDB = "airline";
	String userId = "root";
	String userpw = "1234";
	String name=null;
	String ins =null;
	int instype=0;
	public CReservation(String id) {
		setBackground(Color.white);
		try {
			
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "1234");
			con = DriverManager.getConnection(jdbcUrl, userId, userpw);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("DB연결 완료");

	table_2 = new JTable(model1){

        @Override
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            // TODO Auto-generated method stub
            JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);
            if(row % 2== 0) {
            	component.setBackground(Color.white);	
            }
            else {
            component.setBackground(new Color(213,242,242));
            }
            return component;
        }

    };
	PreparedStatement ps = null;
	ResultSet rs2 = null;
	try {
		Statement st = null;
		//ResultSet rs = null;
		String sql = null;
		String authority=null;
		
//		sql = "select * from resv1 where cus_id = "+id;
//		st = con.createStatement();
//		rs2 = st.executeQuery(sql);
//		if (st.execute(sql)) {
//			rs2 = st.getResultSet();
//		}
		
		ps = (PreparedStatement) con.prepareStatement("select * from resv1 where cus_id like ?");
		ps.setString(1, id);
		rs2 = ps.executeQuery();

		
		
		File image =new File("C:\\Users\\Yeono\\Desktop\\qp1.png");
		Image img=null;
		try {
			 img=ImageIO.read(image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("이미지 파일이 없습니다.");
			
		}
		JLabel imageLabel= new JLabel(new ImageIcon(img));
		imageLabel.setBounds(807,200,200,200);
		add(imageLabel);

		
		 // JTable 내용제거
	    for(int i= model1.getRowCount()-1; i>=0; i--) {
	     model1.removeRow(i);
	    }
	    try {
			while(rs2.next()) {
			    // JTable 내용추가
				
			    String[] row = new String[9];
			    row[0] = rs2.getString("reservation_num");
			    row[1] = rs2.getString("filght_num");
			    row[2] = rs2.getString("airplane_id");
			    row[3] = rs2.getString("cus_id");
			    row[4] = rs2.getString("cus_name");
			    row[5] = rs2.getString("airport_start");
			    row[6] = rs2.getString("airport_des");
			    row[7] = rs2.getString("start_time");
			    row[8] = rs2.getString("des_time");
			    model1.addRow(row);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
			 
//		ps = (PreparedStatement) con.prepareStatement("SELECT * FROM customer");
//		rs = ps.executeQuery();
		
	} catch (SQLException e1) {
		e1.printStackTrace();
	}

setLayout(null);

	table_2.setBounds(12, 10, 847, 382);
	add(table_2);
	
	scrollPane_1 = new JScrollPane(table_2);
	scrollPane_1.setBackground(Color.white);
	scrollPane_1.setOpaque(false);
	//scrollPane_1.setBackground(new Color(248,236,251));
	scrollPane_1.setBounds(12, 10, 782, 347);
	 add(scrollPane_1);
	
	button_1 = new JButton("전체보기");
	button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			PreparedStatement ps = null;
			ResultSet rs2 = null;
			try {
				Statement st = null;
				//ResultSet rs = null;
				String sql = null;
				String authority=null;
				
//				sql = "select * from resv1 where cus_id = "+id;
//				st = con.createStatement();
//				rs2 = st.executeQuery(sql);
//				if (st.execute(sql)) {
//					rs2 = st.getResultSet();
//				}
//				
				
				ps = (PreparedStatement) con.prepareStatement("select * from resv1 where cus_id like ?");
				ps.setString(1, id);
				rs2 = ps.executeQuery();
		
		
				
				 // JTable 내용제거
			    for(int i= model1.getRowCount()-1; i>=0; i--) {
			     model1.removeRow(i);
			    }
			    try {
					while(rs2.next()) {
					    // JTable 내용추가
						
					    String[] row = new String[9];
					    row[0] = rs2.getString("reservation_num");
					    row[1] = rs2.getString("filght_num");
					    row[2] = rs2.getString("airplane_id");
					    row[3] = rs2.getString("cus_id");
					    row[4] = rs2.getString("cus_name");
					    row[5] = rs2.getString("airport_start");
					    row[6] = rs2.getString("airport_des");
					    row[7] = rs2.getString("start_time");
					    row[8] = rs2.getString("des_time");
					    model1.addRow(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
					 
//				ps = (PreparedStatement) con.prepareStatement("SELECT * FROM customer");
//				rs = ps.executeQuery();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

	
		}
	});
	
	button_1.setBounds(806, 10, 200, 33);
	 add(button_1);
	
	label_1 = new JLabel("메 뉴");
	label_1.setHorizontalAlignment(SwingConstants.CENTER);
	label_1.setBounds(807, 58, 205, 30);
	 add(label_1);
	String [] names = {"	조 회		", "	삭 제		"};
	
	strCombo = new JComboBox(names);
	strCombo.setBackground(new Color(213,242,242));
	strCombo.setBounds(807, 98, 120, 29);
	add(strCombo);



	//label_2 = new JLabel("검색어 :");
	//label_2.setBounds(807, 98, 57, 29);
	// add(label_2);
//	
//	textField_1 = new JTextField();
//	textField_1.setColumns(10);
//	textField_1.setBounds(876, 95, 57, 32);
//	 add(textField_1);
	
	button_2 = new JButton("확 인");
	button_3 = new JButton("수 정");
	model2 = table_2.getSelectionModel();
    model2.addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if(!model2.isSelectionEmpty()) {
				if(strCombo.getSelectedIndex()==1) {
				int selectc = model2.getMinSelectionIndex();
				
				int result = JOptionPane.showConfirmDialog(null, "삭제 하시겠습니까?",	"Confirm", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.CLOSED_OPTION) {
					// 사용자가 "예"나 "아니오"의 선택없이 다이얼로그 창을 닫은 경우
					
				}
				else if(result == JOptionPane.YES_OPTION) {
					// 사용자가 "예"를 선택한 경우
					String tp =(String) table_2.getValueAt(selectc, 0);
					System.out.println(tp);
					PreparedStatement ps = null;
					ResultSet rs = null;
					ResultSet rs2 = null;
					try {
						ps = (PreparedStatement) con.prepareStatement("delete from reservation where reservation_num= ?");
						ps.setString(1, tp);
					
						
						
						int rs1 = 0;
							rs1 = ps.executeUpdate();
							if(rs1==0) {
								JOptionPane.showMessageDialog(null, "삭제에 실패하였습니다.");
							}
							
							Statement st = null;
							//ResultSet rs = null;
							String sql = null;
							String authority=null;
							
							sql = "select * from resv1 where cus_id = "+id;
							st = con.createStatement();
							rs2 = st.executeQuery(sql);
							if (st.execute(sql)) {
								rs2 = st.getResultSet();
							}
							
					
							
							 // JTable 내용제거
						    for(int i= model1.getRowCount()-1; i>=0; i--) {
						     model1.removeRow(i);
						    }
						   
								while(rs2.next()) {
								    // JTable 내용추가
									
								    String[] row = new String[9];
								    row[0] = rs2.getString("reservation_num");
								    row[1] = rs2.getString("filght_num");
								    row[2] = rs2.getString("airplane_id");
								    row[3] = rs2.getString("cus_id");
								    row[4] = rs2.getString("cus_name");
								    row[5] = rs2.getString("airport_start");
								    row[6] = rs2.getString("airport_des");
								    row[7] = rs2.getString("start_time");
								    row[8] = rs2.getString("des_time");
								    model1.addRow(row);
								}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//ps.setString(1, id);
				
				
					
				}
				else {	// 사용자가 "아니오"를 선택한 경우
					
				}
				
			}
		}
		}
	});
	button_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				int type = strCombo.getSelectedIndex();
				if(type==0) {
					if(lblid_1!=null) {
						if(button_3!=null) {
						lblid_1.setVisible(false);
						strCombo2.setVisible(false);
						button_3.setVisible(false);
						}
						}
					repaint();
					System.out.println(type);
				String name = JOptionPane.showInputDialog("조회 할 예약번호를 입력하세요.");
				ps = (PreparedStatement) con.prepareStatement("SELECT * FROM resv1 WHERE reservation_num LIKE CONCAT('%', ?, '%')");
				ps.setString(1, name);
				rs = ps.executeQuery();
			    for(int i= model1.getRowCount()-1; i>=0; i--) {
				     model1.removeRow(i);
				    }
				    try {
						while(rs.next()) {
						    // JTable 내용추가
						    String[] row = new String[9];
						    row[0] = rs.getString("reservation_num");
						    row[1] = rs.getString("filght_num");
						    row[2] = rs.getString("airplane_id");
						    row[3] = rs.getString("cus_id");
						    row[4] = rs.getString("cus_name");
						    row[5] = rs.getString("airport_start");
						    row[6] = rs.getString("airport_des");
						    row[7] = rs.getString("start_time");
						    row[8] = rs.getString("des_time");
						    model1.addRow(row);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
				}
				
				else if(type ==1) {
					if(lblid_1!=null) {
						if(button_3!=null) {
						lblid_1.setVisible(false);
						strCombo2.setVisible(false);
						button_3.setVisible(false);
						}
						}
					
					JOptionPane.showMessageDialog(null, "삭제를 원하는 행을 클릭하셔도 됩니다.");
					repaint();
					System.out.println(type);
					name = JOptionPane.showInputDialog("삭제 할 예약번호를 입력하세요.");
					System.out.println(name);
					ps = (PreparedStatement) con.prepareStatement("DELETE FROM reservation WHERE reservation_num =?");
					ps.setString(1, name);
					
					int rs1 = 0;
					System.out.println(ps);
					rs1 = ps.executeUpdate();
					if(rs1==0) {
						JOptionPane.showMessageDialog(null, "삭제에 실패하였습니다.");
					}
					
					ps = (PreparedStatement) con.prepareStatement("select* from resv1 where cus_id ="+id);		
					rs = ps.executeQuery();
				    for(int i= model1.getRowCount()-1; i>=0; i--) {
					     model1.removeRow(i);
					    }
					    try {
							while(rs.next()) {
							    // JTable 내용추가
							    String[] row = new String[9];
							    row[0] = rs.getString("reservation_num");
							    row[1] = rs.getString("filght_num");
							    row[2] = rs.getString("airplane_id");
							    row[3] = rs.getString("cus_id");
							    row[4] = rs.getString("cus_name");
							    row[5] = rs.getString("airport_start");
							    row[6] = rs.getString("airport_des");
							    row[7] = rs.getString("start_time");
							    row[8] = rs.getString("des_time");
							    model1.addRow(row);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				}
						
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		 // JTable 내용제거
		
		}
	});
	//button_2.setBounds(807, 137, 206, 43);
	button_2.setBounds(938, 95, 70, 32);
	 add(button_2);
	
	//lblid_1 = new JLabel("항공사ID로 삭제");
//	lblid_1.setHorizontalAlignment(SwingConstants.CENTER);
//	lblid_1.setBounds(808, 200, 205, 30);
	// add(lblid_1);
	
//	label_3 = new JLabel("검색어");
//	label_3.setBounds(806, 235, 57, 29);
//	 add(label_3);
	
//	textField_2 = new JTextField();
//	textField_2.setColumns(10);
//	textField_2.setBounds(875, 232, 137, 32);
//	 add(textField_2);
	
	//button_3 = new JButton("삭   제");
	
				//ps = (PreparedStatement) con.prepareStatement("update customer set Name ='"+ins+"' WHERE ID = '"+name+"'");
				//ps1 = (PreparedStatement) con.prepareStatement("SELECT * FROM airline");
			
				
	
	
	button_1.setBackground(new Color(200,201,236));
	button_1.setForeground(Color.white);
	button_2.setBackground(new Color(200,201,236));
	button_2.setForeground(Color.white);
	button_3.setBackground(new Color(200,201,236));
	button_3.setForeground(Color.white);
	table_2.setBackground(new Color(213,242,242));
	
	scrollPane_1.getVerticalScrollBar().setBackground(new Color(200,201,236));
	table_2.getTableHeader().setBackground(new Color(200,201,236));
	table_2.getTableHeader().setForeground(Color.white);
	
	
	table_2.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
	table_2.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가

	//table_2.setForeground(Color.white);
		
	
//	button_3.setBounds(806, 274, 206, 43);
	// add(button_3);
	
	
//	button_4.setBounds(807, 363, 206, 49);
//	 add(button_4);
//	
//	textField_3 = new JTextField();
//	textField_3.setBounds(12, 387, 153, 25);
//	 add(textField_3);
//	textField_3.setColumns(10);
//	
//	textField_4 = new JTextField();
//	textField_4.setColumns(10);
//	textField_4.setBounds(169, 387, 153, 25);
//	 add(textField_4);
//	
//	textField_5 = new JTextField();
//	textField_5.setColumns(10);
//	textField_5.setBounds(326, 387, 153, 25);
//	 add(textField_5);
//	
//	textField_6 = new JTextField();
//	textField_6.setColumns(10);
//	textField_6.setBounds(481, 387, 153, 25);
//	 add(textField_6);
//	
//	textField_7 = new JTextField();
//	textField_7.setColumns(10);
//	textField_7.setBounds(641, 387, 153, 25);
//	 add(textField_7);
//	
//	lblid_2 = new JLabel("항공사ID");
//	lblid_2.setHorizontalAlignment(SwingConstants.CENTER);
//	lblid_2.setBounds(12, 367, 153, 15);
//	 add(lblid_2);
//	
//	label_4 = new JLabel("항공사이름");
//	label_4.setHorizontalAlignment(SwingConstants.CENTER);
//	label_4.setBounds(169, 367, 153, 15);
//	 add(label_4);
//	
//	label_5 = new JLabel("주소");
//	label_5.setHorizontalAlignment(SwingConstants.CENTER);
//	label_5.setBounds(326, 367, 153, 15);
//	 add(label_5);
//	
//	label_6 = new JLabel("전화번호");
//	label_6.setHorizontalAlignment(SwingConstants.CENTER);
//	label_6.setBounds(481, 367, 153, 15);
//	 add(label_6);
//	
//	label_7 = new JLabel("국적");
//	label_7.setHorizontalAlignment(SwingConstants.CENTER);
//	label_7.setBounds(641, 367, 153, 15);
//	 add(label_7);
	}
	public class MyTableCellRenderer extends DefaultTableCellRenderer {

		@Override

		 public Component getTableCellRendererComponent(JTable table, Object value, boolean 

		isSelected, boolean hasFocus, int row, int column) {

		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		if (!isSelected) {

		if (row % 2 == 0) {

		cell.setBackground(new Color(220,228,243));
		cell.setForeground(Color.white);
		} else {

		cell.setBackground(Color.white);

		}

		}

		return cell;

		}

		}

}
