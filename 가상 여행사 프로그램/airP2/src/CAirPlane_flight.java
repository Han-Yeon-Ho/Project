import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

class CAirplane_flight extends JPanel{
	
	Connection con = null;
	JComboBox strCombo;
	JComboBox strCombo2;
	private String colName1[] = {"항공편 번호","비행기 ID","출발지(공항)","도착지(공항)","출발시간","도착시간","비용"};
	private DefaultTableModel model1 = new DefaultTableModel(colName1, 0){
        // Jtable 내용 편집 안되게
        public boolean isCellEditable(int i, int c) {
            return false;
        }
    };
    private JTable table_2; //항공사관리의 테이블
    private ListSelectionModel model2;
    
	
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
	JComboBox strCombo3;
	String item;
	public CAirplane_flight(String id) {
		setBackground(Color.white);
		try {
			
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "1234");
			con = DriverManager.getConnection(jdbcUrl, userId, userpw);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		System.out.println("DB연결 완료");
		PreparedStatement ps = null;
		ResultSet rs2 = null;
		try {
			Statement st = null;
			//ResultSet rs = null;
			String sql = null;
			String authority=null;
			
			sql = "select * from airline_filght";
			st = con.createStatement();
			rs2 = st.executeQuery(sql);
			if (st.execute(sql)) {
				rs2 = st.getResultSet();
			}
			
	
			
			 // JTable 내용제거
		    for(int i= model1.getRowCount()-1; i>=0; i--) {
		     model1.removeRow(i);
		    }
		    try {
				while(rs2.next()) {
				    // JTable 내용추가
					
				    String[] row = new String[7];
				    row[0] = rs2.getString("filght_num");
				    row[1] = rs2.getString("airplane_id");
				    row[2] = rs2.getString("airport_start");
				    row[3] = rs2.getString("airport_des");
				    row[4] = rs2.getString("start_time");
				    row[5] = rs2.getString("des_time");
				    row[6] = rs2.getString("cost");
				   
				    model1.addRow(row);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
				 
//			ps = (PreparedStatement) con.prepareStatement("SELECT * FROM customer");
//			rs = ps.executeQuery();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
	setLayout(null);
	
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
    
    model2 = table_2.getSelectionModel();
    model2.addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if(!model2.isSelectionEmpty()) {
				int selectc = model2.getMinSelectionIndex();
				
				int result = JOptionPane.showConfirmDialog(null, "예매 하시겠습니까?",	"Confirm", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.CLOSED_OPTION) {
					// 사용자가 "예"나 "아니오"의 선택없이 다이얼로그 창을 닫은 경우
					
				}
				else if(result == JOptionPane.YES_OPTION) {
					// 사용자가 "예"를 선택한 경우
					String tp =(String) table_2.getValueAt(selectc, 0);
					System.out.println(tp);
					PreparedStatement ps = null;
					ResultSet rs = null;
					try {
						ps = (PreparedStatement) con.prepareStatement("insert into reservation(filght_num,cus_id) values(?,?)");
						ps.setString(1, tp);
						ps.setString(2, id);
						
						
						int rs1 = 0;
							rs1 = ps.executeUpdate();
							if(rs1==0) {
								JOptionPane.showMessageDialog(null, "예약에 실패하였습니다.");
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
	});
	table_2.setBounds(12, 10, 847, 382);
	//table_2.setRowSelectionAllowed(true);
	table_2.setColumnSelectionAllowed(true);
	table_2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	
	add(table_2);
	
	scrollPane_1 = new JScrollPane(table_2);
	scrollPane_1.setBackground(Color.white);
	scrollPane_1.getVerticalScrollBar().setBackground(new Color(200,201,236));
	table_2.getTableHeader().setBackground(new Color(200,201,236));
	table_2.getTableHeader().setForeground(Color.white);
	table_2.setRowSelectionAllowed(true);
	scrollPane_1.setBounds(12, 10, 782, 347);
	 add(scrollPane_1);
	    String []apID=null;
	    String []apSD=null;
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
				
				sql = "select * from airline_filght";
				st = con.createStatement();
				rs2 = st.executeQuery(sql);
				if (st.execute(sql)) {
					rs2 = st.getResultSet();
				}
				
		
				
				 // JTable 내용제거
			    for(int i= model1.getRowCount()-1; i>=0; i--) {
			     model1.removeRow(i);
			    }
			    try {
					while(rs2.next()) {
					    // JTable 내용추가
						
					    String[] row = new String[7];
					    row[0] = rs2.getString("filght_num");
					    row[1] = rs2.getString("airplane_id");
					    row[2] = rs2.getString("airport_start");
					    row[3] = rs2.getString("airport_des");
					    row[4] = rs2.getString("start_time");
					    row[5] = rs2.getString("des_time");
					    row[6] = rs2.getString("cost");
					    model1.addRow(row);
					    
					
//					    for(int i=0; i<model1.getRowCount(); i++) {
//							apID[i] =rs2.getString("airplane_id");
//							}
//							
//							for(int i=0; i<model1.getRowCount(); i++) {
//							apID[i] =rs2.getString("airport_start");
//							}  
					    
					}
					//strCombo3=new JComboBox(apID);
					item =(String)strCombo3.getSelectedItem();
					strCombo3.setBackground(new Color(213,242,242));
					strCombo3.setBounds(807, 160, 120, 29);
					add(strCombo3);
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
	
	label_1 = new JLabel("조 회");
	label_1.setHorizontalAlignment(SwingConstants.CENTER);
	label_1.setBounds(807, 58, 205, 30);
	 add(label_1);
	String [] names = {"	비행기 ID		", "	출발지 공항		","도착지 공항","	출발 시간		","도착 시간","	비 용		"};
	
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
	
	TextField tp = new TextField(15);
	
	button_2 = new JButton("확 인");
	button_3 = new JButton("검 색");
	button_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			 Font font = new Font("arian", Font.BOLD, 15);
			tp.setFont(font);
			tp.setBounds(807, 156, 120, 29);
			button_3.setBounds(938, 160, 70, 32);
			
			add(button_3);
			add(tp);
			
//			try {
//				int type = strCombo.getSelectedIndex();
//				
//				if(type==0) {
//					
//					repaint();
//					System.out.println(type);
//				//String name = JOptionPane.showInputDialog("조회 할 항공편 번호를 입력하세요.");
//					
//					
//					
//					item=tp.getText();
//				ps = (PreparedStatement) con.prepareStatement("SELECT * FROM airline_filght WHERE airplane_id LIKE CONCAT('%', ?, '%')");
//				ps.setString(1, item);
//				rs = ps.executeQuery();
//			    for(int i= model1.getRowCount()-1; i>=0; i--) {
//				     model1.removeRow(i);
//				    }
//				    try {
//						while(rs.next()) {
//						    // JTable 내용추가
//						    String[] row = new String[7];
//						    row[0] = rs.getString("filght_num");
//						    row[1] = rs.getString("airplane_id");
//						    row[2] = rs.getString("airport_start");
//						    row[3] = rs.getString("airport_des");
//						    row[4] = rs.getString("start_time");
//						    row[5] = rs.getString("des_time");
//						    row[6] = rs.getString("cost");
//						    model1.addRow(row);
//						}
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
//				
//				}
//				else if(type ==1) {
////					if(lblid_1==null) {
////						
////						lblid_1.setVisible(true);
////						strCombo2.setVisible(true);
////						button_3.setVisible(true);
////						}
////					if(button_3==null) {
////						lblid_1.setVisible(true);
////						strCombo2.setVisible(true);
////						button_3.setVisible(true);
////						}
//					repaint();
//					System.out.println(type);
//					//name = JOptionPane.showInputDialog("수정 할 항공편 번호를 입력하세요.");
//					button_3.setVisible(true);
//					button_3.setBounds(938, 160, 70, 32);	
//					 add(button_3);
//					ps = (PreparedStatement) con.prepareStatement("SELECT * FROM airline_filght WHERE airport_start LIKE CONCAT('%', ?, '%')");
//					ps.setString(1, item);
//					rs = ps.executeQuery();
//					 String[] row=null;
//				    for(int i= model1.getRowCount()-1; i>=0; i--) {
//					     model1.removeRow(i);
//					    }
//					    try {
//							while(rs.next()) {
//							    // JTable 내용추가
//							    row = new String[7];
//							    row[0] = rs.getString("filght_num");
//							    row[1] = rs.getString("airplane_id");
//							    row[2] = rs.getString("airport_start");
//							    row[3] = rs.getString("airport_des");
//							    row[4] = rs.getString("start_time");
//							    row[5] = rs.getString("des_time");
//							    row[6] = rs.getString("cost");
//							    
//							    model1.addRow(row);
//							}
//							
//							
//							lblid_1 = new JLabel("수정할 항목 선택");
//							lblid_1.setHorizontalAlignment(SwingConstants.CENTER);
//							lblid_1.setBounds(807, 130, 205, 30);
//							add(lblid_1);
//							//button_3 = new JButton("수 정");
//							
//							String [] names = {"항목 선택","항공편번호", "비행기ID","출발지","도착지","출발시간","도착시간","비용"};
//							strCombo2 = new JComboBox(names);
//							strCombo2.setBackground(new Color(213,242,242));
//							strCombo2.setBounds(807, 160, 120, 29);
//							add(strCombo2);
//							//button_3 = new JButton("수 정");
//							
//			
//							
//							repaint();
//							
//						} catch (SQLException e1) {
//							e1.printStackTrace();
//						}
//					    
//				}
//				else if(type ==2) {
//					if(lblid_1!=null) {
//						if(button_3!=null) {
//						lblid_1.setVisible(false);
//						strCombo2.setVisible(false);
//						button_3.setVisible(false);
//						}
//						}
//					repaint();
//					System.out.println(type);
//					name = JOptionPane.showInputDialog("삭제 할 예약번호를 입력하세요.");
//					System.out.println(name);
//					ps = (PreparedStatement) con.prepareStatement("DELETE FROM airline_filght WHERE filght_num =?");
//					ps.setString(1, name);
//					
//					int rs1 = 0;
//					System.out.println(ps);
//					rs1 = ps.executeUpdate();
//					if(rs1==0) {
//						JOptionPane.showMessageDialog(null, "삭제에 실패하였습니다.");
//					}
//					
//					ps = (PreparedStatement) con.prepareStatement("select* from airline_filght");		
//					rs = ps.executeQuery();
//				    for(int i= model1.getRowCount()-1; i>=0; i--) {
//					     model1.removeRow(i);
//					    }
//					    try {
//							while(rs.next()) {
//							    // JTable 내용추가
//								 String[]    row = new String[7];
//							    row[0] = rs.getString("filght_num");
//							    row[1] = rs.getString("airplane_id");
//							    row[2] = rs.getString("airport_start");
//							    row[3] = rs.getString("airport_des");
//							    row[4] = rs.getString("start_time");
//							    row[5] = rs.getString("des_time");
//							    row[6] = rs.getString("cost");
//							    model1.addRow(row);
//							}
//						} catch (SQLException e1) {
//							e1.printStackTrace();
//						}
//				}
//				else if(type==3){
//					
//					if(lblid_1!=null) {
//					lblid_1.setVisible(false);
//					strCombo2.setVisible(false);
//					button_3.setVisible(false);
//					}	
//					repaint();
//					System.out.println(type);
//					String ID1=JOptionPane.showInputDialog("추가 할 항공편 번호를 입력하세요.");
//					ps = (PreparedStatement) con.prepareStatement("select* from airline_filght where filght_num = ?");
//					ps.setString(1, ID1);
//					
//					rs = ps.executeQuery();
//					if(rs.next()) {
//						 ID1=JOptionPane.showInputDialog("중복된 예약번호입니다. \n 다른 예약번호를 입력하세요.");
//						
//					}
//					
//					String apNO=JOptionPane.showInputDialog("추가 할 항공편의 비행기ID를 입력하세요.");
//					ps = (PreparedStatement) con.prepareStatement("select* from airplane where airplane_id = ?");
//					ps.setString(1, apNO);
//					int rs1 = 0;
//					rs = ps.executeQuery();
//					if(!rs.next()) {
//						apNO=JOptionPane.showInputDialog("존재하지 않는 항공편 번호입니다. \n 다른 항공편 번호를 입력하세요.");
//						
//					}
//					String sD=JOptionPane.showInputDialog("추가 할 항공편의 출발지(공항)을 입력하세요.");
//					String eD=JOptionPane.showInputDialog("추가 할 항공편의 도착지(공항)을 입력하세요.");
//
//					
//					String sT=JOptionPane.showInputDialog("추가 할 항공편의 출발시간을 입력하세요.");
//					String eT=JOptionPane.showInputDialog("추가 할 항공편의 도착시간을 입력하세요.");
//					String cst=JOptionPane.showInputDialog("추가 할 항공편의 비용을 입력하세요.");
////					ps = (PreparedStatement) con.prepareStatement("select* from customer where cus_id = ?");
////					ps.setString(1, cID);
////					 rs1 = 0;
////						rs = ps.executeQuery();
////						if(!rs.next()) {
////						 ID1=JOptionPane.showInputDialog("존재하지 않는 회원 ID입니다. \n 다른 회원 ID를 입력하세요.");
////						
////					}
//					
//					
//					
//					//String alNO=JOptionPane.showInputDialog("추가 할 예약의 비행기ID를 입력하세요.");
//					//String cName=JOptionPane.showInputDialog("추가 할 예약의 고객이름을 입력하세요.");
//					//String Std=JOptionPane.showInputDialog("추가 할 예약의 출발지를 입력하세요.");
//					//String End=JOptionPane.showInputDialog("추가 할 예약의 도착지를 입력하세요.");
//					//String Stim=JOptionPane.showInputDialog("추가 할 예약의 출발시간를 입력하세요.");
//					//String Etim=JOptionPane.showInputDialog("추가 할 예약의 도착시간를 입력하세요.");
//					
//					
//					ps = (PreparedStatement) con.prepareStatement("insert into airline_filght(filght_num, airplane_id, airport_start, airport_des, start_time, des_time, cost) values(?,?,?,?,?,?,?)");
//					ps.setString(1, ID1);
//					ps.setString(2, apNO);
//					ps.setString(3, sD);
//					ps.setString(4, eD);
//					ps.setString(5, sT);
//					ps.setString(6, eT);
//					ps.setString(7, cst);
//					
//					//ps.setString(4, cName);
//					//ps.setString(5, Std);
//					//ps.setString(6, End);
//					//ps.setString(7, Stim);
//					//ps.setString(8, Etim);
//					
//					 rs1 = 0;
//					rs1 = ps.executeUpdate();
//					if(rs1==0) {
//						JOptionPane.showMessageDialog(null, "삽입에 실패하였습니다.");
//					}
//					
//					ps = (PreparedStatement) con.prepareStatement("select* from airline_filght");		
//					rs = ps.executeQuery();
//				    for(int i= model1.getRowCount()-1; i>=0; i--) {
//					     model1.removeRow(i);
//					    }
//					    try {
//							while(rs.next()) {
//							    // JTable 내용추가
//								 String[]    row = new String[7];
//								    row[0] = rs.getString("filght_num");
//								    row[1] = rs.getString("airplane_id");
//								    row[2] = rs.getString("airport_start");
//								    row[3] = rs.getString("airport_des");
//								    row[4] = rs.getString("start_time");
//								    row[5] = rs.getString("des_time");
//								    row[6] = rs.getString("cost");
//							    model1.addRow(row);
//							}
//						} catch (SQLException e1) {
//							e1.printStackTrace();
//						}
//					
//				}
//				
//				
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			
//		 // JTable 내용제거
//		
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
		button_1.setBackground(new Color(200,201,236));
		button_1.setForeground(Color.white);
		button_2.setBackground(new Color(200,201,236));
		button_2.setForeground(Color.white);
		button_3.setBackground(new Color(200,201,236));
		button_3.setForeground(Color.white);
		//table_2.setBackground(new Color(248,236,251));
		table_2.setBackground(new Color(213,242,242));
		table_2.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		table_2.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가


	//button_3 = new JButton("삭   제");
	button_3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			PreparedStatement ps = null;
			PreparedStatement ps1 = null;
			ResultSet rs1 = null;
			item=tp.getText();
			instype = strCombo.getSelectedIndex();
			try {
			if(instype==0) {
				ps = (PreparedStatement)  con.prepareStatement("select* from airline_filght WHERE airplane_id Like CONCAT('%', ?, '%')");
				ps.setString(1, item);
				rs1 = ps.executeQuery();
				
				for(int i= model1.getRowCount()-1; i>=0; i--) {
				     model1.removeRow(i);
				    }
				    
						while(rs1.next()) {
						    // JTable 내용추가
							 String[]    row = new String[7];
							    row[0] = rs1.getString("filght_num");
							    row[1] = rs1.getString("airplane_id");
							    row[2] = rs1.getString("airport_start");
							    row[3] = rs1.getString("airport_des");
							    row[4] = rs1.getString("start_time");
							    row[5] = rs1.getString("des_time");
							    row[6] = rs1.getString("cost");
						    model1.addRow(row);
						}
			
			}
			else if(instype==1) {
				ps = (PreparedStatement)  con.prepareStatement("select* from airline_filght WHERE airport_start Like CONCAT('%', ?, '%')");
				ps.setString(1, item);
				rs1 = ps.executeQuery();
				
				for(int i= model1.getRowCount()-1; i>=0; i--) {
				     model1.removeRow(i);
				    }
				    
						while(rs1.next()) {
						    // JTable 내용추가
							 String[]    row = new String[7];
							    row[0] = rs1.getString("filght_num");
							    row[1] = rs1.getString("airplane_id");
							    row[2] = rs1.getString("airport_start");
							    row[3] = rs1.getString("airport_des");
							    row[4] = rs1.getString("start_time");
							    row[5] = rs1.getString("des_time");
							    row[6] = rs1.getString("cost");
						    model1.addRow(row);
						}
			}
			else if(instype==2) {
				ps = (PreparedStatement)  con.prepareStatement("select* from airline_filght WHERE airport_des Like CONCAT('%', ?, '%')");
				ps.setString(1, item);
				rs1 = ps.executeQuery();
				
				for(int i= model1.getRowCount()-1; i>=0; i--) {
				     model1.removeRow(i);
				    }
				    
						while(rs1.next()) {
						    // JTable 내용추가
							 String[]    row = new String[7];
							    row[0] = rs1.getString("filght_num");
							    row[1] = rs1.getString("airplane_id");
							    row[2] = rs1.getString("airport_start");
							    row[3] = rs1.getString("airport_des");
							    row[4] = rs1.getString("start_time");
							    row[5] = rs1.getString("des_time");
							    row[6] = rs1.getString("cost");
						    model1.addRow(row);
						}
				}
			else if(instype==3) {
				ps = (PreparedStatement)  con.prepareStatement("select* from airline_filght WHERE start_time Like CONCAT('%', ?, '%')");
				ps.setString(1, item);
				rs1 = ps.executeQuery();
				
				for(int i= model1.getRowCount()-1; i>=0; i--) {
				     model1.removeRow(i);
				    }
				    
						while(rs1.next()) {
						    // JTable 내용추가
							 String[]    row = new String[7];
							    row[0] = rs1.getString("filght_num");
							    row[1] = rs1.getString("airplane_id");
							    row[2] = rs1.getString("airport_start");
							    row[3] = rs1.getString("airport_des");
							    row[4] = rs1.getString("start_time");
							    row[5] = rs1.getString("des_time");
							    row[6] = rs1.getString("cost");
						    model1.addRow(row);
						}
				}
			else if(instype==4) {
				ps = (PreparedStatement)  con.prepareStatement("select* from airline_filght WHERE des_time Like CONCAT('%', ?, '%')");
				ps.setString(1, item);
				rs1 = ps.executeQuery();
				
				for(int i= model1.getRowCount()-1; i>=0; i--) {
				     model1.removeRow(i);
				    }
				    
						while(rs1.next()) {
						    // JTable 내용추가
							 String[]    row = new String[7];
							    row[0] = rs1.getString("filght_num");
							    row[1] = rs1.getString("airplane_id");
							    row[2] = rs1.getString("airport_start");
							    row[3] = rs1.getString("airport_des");
							    row[4] = rs1.getString("start_time");
							    row[5] = rs1.getString("des_time");
							    row[6] = rs1.getString("cost");
						    model1.addRow(row);
						}
				}
			else if(instype==5) {
				
				ps = (PreparedStatement)  con.prepareStatement("select* from airline_filght WHERE cost Like CONCAT('%', ?, '%')");
				ps.setString(1, item);
				rs1 = ps.executeQuery();
				
				for(int i= model1.getRowCount()-1; i>=0; i--) {
				     model1.removeRow(i);
				    }
				    
						while(rs1.next()) {
						    // JTable 내용추가
							 String[]    row = new String[7];
							    row[0] = rs1.getString("filght_num");
							    row[1] = rs1.getString("airplane_id");
							    row[2] = rs1.getString("airport_start");
							    row[3] = rs1.getString("airport_des");
							    row[4] = rs1.getString("start_time");
							    row[5] = rs1.getString("des_time");
							    row[6] = rs1.getString("cost");
						    model1.addRow(row);
						}
	
				}
			
			
		
			
				
				//ps = (PreparedStatement) con.prepareStatement("update customer set Name ='"+ins+"' WHERE ID = '"+name+"'");
				//ps1 = (PreparedStatement) con.prepareStatement("SELECT * FROM airline");
			
				
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

		 // JTable 내용제거

		}
	});
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
	

}


