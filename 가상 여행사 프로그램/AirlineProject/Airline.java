
	
	import java.awt.event.*;
	import java.sql.*;

	import javax.swing.*;
	import javax.swing.table.*;

	class Airline extends JPanel{
		
		Connection con = null;
		JComboBox strCombo;
		JComboBox strCombo2;
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
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://localhost/airline?characterEncoding=UTF-8&serverTimezone=UTC";
		String useDB = "airline";
		String userId = "root";
		String userpw = "1234";
		String name=null;
		String ins =null;
		int instype=0;
		public Airline() {
			
			try {
				
				//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "1234");
				con = DriverManager.getConnection(jdbcUrl, userId, userpw);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("DB연결 완료");
			PreparedStatement ps = null;
			ResultSet rs2 = null;
			try {
				Statement st = null;
				//ResultSet rs = null;
				String sql = null;
				String authority=null;
				
				sql = "select * from resv";
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
						
					    String[] row = new String[8];
					    row[0] = rs2.getString("reservation_num");
					    row[1] = rs2.getString("filght_num");
					    row[2] = rs2.getString("airplane_id");
					    row[3] = rs2.getString("cus_name");
					    row[4] = rs2.getString("airport_start");
					    row[5] = rs2.getString("airport_des");
					    row[6] = rs2.getString("start_time");
					    row[7] = rs2.getString("des_time");
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
				ResultSet rs2 = null;
				try {
					Statement st = null;
					//ResultSet rs = null;
					String sql = null;
					String authority=null;
					
					sql = "select * from resv";
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
							
						    String[] row = new String[8];
						    row[0] = rs2.getString("reservation_num");
						    row[1] = rs2.getString("filght_num");
						    row[2] = rs2.getString("airplane_id");
						    row[3] = rs2.getString("cus_name");
						    row[4] = rs2.getString("airport_start");
						    row[5] = rs2.getString("airport_des");
						    row[6] = rs2.getString("start_time");
						    row[7] = rs2.getString("des_time");
						    model1.addRow(row);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
						 
//					ps = (PreparedStatement) con.prepareStatement("SELECT * FROM customer");
//					rs = ps.executeQuery();
					
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
		String [] names = {"	조 회		", "	수 정		", "	삭 제		","		삽 입		"};
		
		strCombo = new JComboBox(names);
		
		strCombo.setBounds(807, 98, 120, 29);
		add(strCombo);



		//label_2 = new JLabel("검색어 :");
		//label_2.setBounds(807, 98, 57, 29);
		// add(label_2);
	//	
//		textField_1 = new JTextField();
//		textField_1.setColumns(10);
//		textField_1.setBounds(876, 95, 57, 32);
//		 add(textField_1);
		
		button_2 = new JButton("확 인");
		button_3 = new JButton("수 정");
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
					ps = (PreparedStatement) con.prepareStatement("SELECT * FROM resv WHERE reservation_num LIKE CONCAT('%', ?, '%')");
					ps.setString(1, name);
					rs = ps.executeQuery();
				    for(int i= model1.getRowCount()-1; i>=0; i--) {
					     model1.removeRow(i);
					    }
					    try {
							while(rs.next()) {
							    // JTable 내용추가
							    String[] row = new String[8];
							    row[0] = rs.getString("reservation_num");
							    row[1] = rs.getString("filght_num");
							    row[2] = rs.getString("airplane_id");
							    row[3] = rs.getString("cus_name");
							    row[4] = rs.getString("airport_start");
							    row[5] = rs.getString("airport_des");
							    row[6] = rs.getString("start_time");
							    row[7] = rs.getString("des_time");
							    model1.addRow(row);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					
					}
					else if(type ==1) {
//						if(lblid_1==null) {
//							
//							lblid_1.setVisible(true);
//							strCombo2.setVisible(true);
//							button_3.setVisible(true);
//							}
//						if(button_3==null) {
//							lblid_1.setVisible(true);
//							strCombo2.setVisible(true);
//							button_3.setVisible(true);
//							}
						repaint();
						System.out.println(type);
						name = JOptionPane.showInputDialog("수정 할 예약번호를 입력하세요.");
						button_3.setVisible(true);
						button_3.setBounds(938, 160, 70, 32);	
						 add(button_3);
						ps = (PreparedStatement) con.prepareStatement("SELECT * FROM resv WHERE reservation_num LIKE CONCAT('%', ?, '%')");
						ps.setString(1, name);
						rs = ps.executeQuery();
						 String[] row=null;
					    for(int i= model1.getRowCount()-1; i>=0; i--) {
						     model1.removeRow(i);
						    }
						    try {
								while(rs.next()) {
								    // JTable 내용추가
									row = new String[8];
								    row[0] = rs.getString("reservation_num");
								    row[1] = rs.getString("filght_num");
								    row[2] = rs.getString("airplane_id");
								    row[3] = rs.getString("cus_name");
								    row[4] = rs.getString("airport_start");
								    row[5] = rs.getString("airport_des");
								    row[6] = rs.getString("start_time");
								    row[7] = rs.getString("des_time");
								    
								    model1.addRow(row);
								}
								//MyModalDialog dialog = new MyModalDialog(thi,row[0]+row[1]+row[2]+row[3]+row[4]);
								
								lblid_1 = new JLabel("수정할 항목 선택");
								lblid_1.setHorizontalAlignment(SwingConstants.CENTER);
								lblid_1.setBounds(807, 130, 205, 30);
								add(lblid_1);
								//button_3 = new JButton("수 정");
								
								String [] names = {"항목 선택","항공편번호", "비행기ID","고객 이름","출발지","도착지","출발시간","도착시간"};
								strCombo2 = new JComboBox(names);
								strCombo2.setBounds(807, 160, 120, 29);
								add(strCombo2);
								//button_3 = new JButton("수 정");
								
				
								
								repaint();
								
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						    
					}
					else if(type ==2) {
						if(lblid_1!=null) {
							if(button_3!=null) {
							lblid_1.setVisible(false);
							strCombo2.setVisible(false);
							button_3.setVisible(false);
							}
							}
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
						
						ps = (PreparedStatement) con.prepareStatement("select* from resv");		
						rs = ps.executeQuery();
					    for(int i= model1.getRowCount()-1; i>=0; i--) {
						     model1.removeRow(i);
						    }
						    try {
								while(rs.next()) {
								    // JTable 내용추가
								    String[]  row = new String[8];
								    row[0] = rs.getString("reservation_num");
								    row[1] = rs.getString("filght_num");
								    row[2] = rs.getString("airplane_id");
								    row[3] = rs.getString("cus_name");
								    row[4] = rs.getString("airport_start");
								    row[5] = rs.getString("airport_des");
								    row[6] = rs.getString("start_time");
								    row[7] = rs.getString("des_time");
								    model1.addRow(row);
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
					}
					else if(type==3){
						
						if(lblid_1!=null) {
						lblid_1.setVisible(false);
						strCombo2.setVisible(false);
						button_3.setVisible(false);
						}	
						repaint();
						System.out.println(type);
						String ID1=JOptionPane.showInputDialog("추가 할 예약번호를 입력하세요.");
						ps = (PreparedStatement) con.prepareStatement("select* from resv where reservation_num = ?");
						ps.setString(1, ID1);
						
						rs = ps.executeQuery();
						if(rs.next()) {
							 ID1=JOptionPane.showInputDialog("중복된 예약번호입니다. \n 다른 예약번호를 입력하세요.");
							
						}
						
						String apNO=JOptionPane.showInputDialog("추가 할 예약의 항공편번호를 입력하세요.");
						ps = (PreparedStatement) con.prepareStatement("select* from airline_filght where filght_num = ?");
						ps.setString(1, apNO);
						int rs1 = 0;
						rs = ps.executeQuery();
						if(!rs.next()) {
							 ID1=JOptionPane.showInputDialog("존재하지 않는 항공편 번호입니다. \n 다른 항공편 번호를 입력하세요.");
							
						}
						
						String cID=JOptionPane.showInputDialog("추가 할 예약의 회원ID를 입력하세요.");
						ps = (PreparedStatement) con.prepareStatement("select* from customer where cus_id = ?");
						ps.setString(1, cID);
						 rs1 = 0;
							rs = ps.executeQuery();
							if(!rs.next()) {
							 ID1=JOptionPane.showInputDialog("존재하지 않는 회원 ID입니다. \n 다른 회원 ID를 입력하세요.");
							
						}
						
						
						
						//String alNO=JOptionPane.showInputDialog("추가 할 예약의 비행기ID를 입력하세요.");
						//String cName=JOptionPane.showInputDialog("추가 할 예약의 고객이름을 입력하세요.");
						//String Std=JOptionPane.showInputDialog("추가 할 예약의 출발지를 입력하세요.");
						//String End=JOptionPane.showInputDialog("추가 할 예약의 도착지를 입력하세요.");
						//String Stim=JOptionPane.showInputDialog("추가 할 예약의 출발시간를 입력하세요.");
						//String Etim=JOptionPane.showInputDialog("추가 할 예약의 도착시간를 입력하세요.");
						
						
						ps = (PreparedStatement) con.prepareStatement("insert into reservation values(?,?,?)");
						ps.setString(1, ID1);
						ps.setString(2, apNO);
						ps.setString(3, cID);
						
						
						//ps.setString(4, cName);
						//ps.setString(5, Std);
						//ps.setString(6, End);
						//ps.setString(7, Stim);
						//ps.setString(8, Etim);
						
						 rs1 = 0;
						rs1 = ps.executeUpdate();
						if(rs1==0) {
							JOptionPane.showMessageDialog(null, "삽입에 실패하였습니다.");
						}
						
						ps = (PreparedStatement) con.prepareStatement("select* from resv");		
						rs = ps.executeQuery();
					    for(int i= model1.getRowCount()-1; i>=0; i--) {
						     model1.removeRow(i);
						    }
						    try {
								while(rs.next()) {
								    // JTable 내용추가
								    String[]  row = new String[8];
								    row[0] = rs.getString("reservation_num");
								    row[1] = rs.getString("filght_num");
								    row[2] = rs.getString("airplane_id");
								    row[3] = rs.getString("cus_name");
								    row[4] = rs.getString("airport_start");
								    row[5] = rs.getString("airport_des");
								    row[6] = rs.getString("start_time");
								    row[7] = rs.getString("des_time");
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
//		lblid_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblid_1.setBounds(808, 200, 205, 30);
		// add(lblid_1);
		
//		label_3 = new JLabel("검색어");
//		label_3.setBounds(806, 235, 57, 29);
//		 add(label_3);
		
//		textField_2 = new JTextField();
//		textField_2.setColumns(10);
//		textField_2.setBounds(875, 232, 137, 32);
//		 add(textField_2);
		
		//button_3 = new JButton("삭   제");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instype = strCombo2.getSelectedIndex();
				if(instype==1) {
				 ins = JOptionPane.showInputDialog("수정 할 항공편번호를 입력하세요.");
				}
				else if(instype==2) {
				 ins = JOptionPane.showInputDialog("수정 할 비행기ID를 입력하세요.");
				}
				else if(instype==3) {
					 ins = JOptionPane.showInputDialog("수정 할 고객이름을 입력하세요.\n");
					}
				else if(instype==4) {
					 ins = JOptionPane.showInputDialog("수정 할 출발지를 입력하세요.");
					}
				else if(instype==5) {
					 ins = JOptionPane.showInputDialog("수정 할 도착지를 입력하세요.");
					}
				else if(instype==6) {
					
					 ins = JOptionPane.showInputDialog("수정 할 출발시간을 입력하세요.");
		
					  
					
					}
				else if(instype==7) {
					 ins = JOptionPane.showInputDialog("수정 할 도착시간을 입력하세요.");
					}
				
				PreparedStatement ps = null;
				PreparedStatement ps1 = null;
				ResultSet rs1 = null;
				try {
					System.out.println(instype);
					if(instype==1) {
						ps = (PreparedStatement) con.prepareStatement("Update airline_filght set filght_num =? WHERE filght_num = (select filght_num from reservation where reservation_num = ?)");
						ps.setString(1, ins);
						System.out.println(ins);
						ps.setString(2, name);
						int rs = 0;
						System.out.println(ps);
						rs = ps.executeUpdate();
						if(rs==0) {
							JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
						}
						ps = (PreparedStatement) con.prepareStatement("Update reservation set filght_num =? WHERE  reservation_num = ?");
						ps.setString(1, ins);
						System.out.println(ins);
						ps.setString(2, name);
						 rs = 0;
						 
						System.out.println(ps);
						rs = ps.executeUpdate();
						if(rs==0) {
							JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
						}
						ps = (PreparedStatement)  con.prepareStatement("select* from resv WHERE reservation_num = ?");
						ps.setString(1, name);
						rs1 = ps.executeQuery();
						
						for(int i= model1.getRowCount()-1; i>=0; i--) {
						     model1.removeRow(i);
						    }
						    
								while(rs1.next()) {
								    // JTable 내용추가
								    String[]  row = new String[8];
								    row[0] = rs1.getString("reservation_num");
								    row[1] = rs1.getString("filght_num");
								    row[2] = rs1.getString("airplane_id");
								    row[3] = rs1.getString("cus_name");
								    row[4] = rs1.getString("airport_start");
								    row[5] = rs1.getString("airport_des");
								    row[6] = rs1.getString("start_time");
								    row[7] = rs1.getString("des_time");
								    model1.addRow(row);
								}
					}
					else if (instype==2) {
						
						ps = (PreparedStatement) con.prepareStatement(" update airplane set airplane_id = ? WHERE airplane_id =(select airplane_id from airline_filght where filght_num=(select filght_num from reservation where reservation_num = ?))");
						ps.setString(1, ins);
						ps.setString(2, name);
						int rs = 0;
						System.out.println(ps);
						rs = ps.executeUpdate();
						if(rs==0) {
							JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
						}
						ps = (PreparedStatement) con.prepareStatement(" update airline_filght set airplane_id = ? WHERE filght_num =(select filght_num from reservation where reservation_num = ?)");
						ps.setString(1, ins);
						ps.setString(2, name);
						 rs = 0;
						System.out.println(ps);
						rs = ps.executeUpdate();
						if(rs==0) {
							JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
						}
						
						ps = (PreparedStatement) con.prepareStatement("select* from resv WHERE reservation_num = ?");
						ps.setString(1, name);
						rs1 = ps.executeQuery();
						for(int i= model1.getRowCount()-1; i>=0; i--) {
						     model1.removeRow(i);
						    }
						    
								while(rs1.next()) {
								    // JTable 내용추가
								    String[]  row = new String[8];
								    row[0] = rs1.getString("reservation_num");
								    row[1] = rs1.getString("filght_num");
								    row[2] = rs1.getString("airplane_id");
								    row[3] = rs1.getString("cus_name");
								    row[4] = rs1.getString("airport_start");
								    row[5] = rs1.getString("airport_des");
								    row[6] = rs1.getString("start_time");
								    row[7] = rs1.getString("des_time");
								    model1.addRow(row);
								}
					}
					else if (instype==3) {
						ps = (PreparedStatement) con.prepareStatement("update customer set cus_name =? WHERE cus_id=(select cus_id from reservation where reservation_num = ?)");
						ps.setString(1, ins);
						ps.setString(2, name);
						int rs = 0;
						
						rs = ps.executeUpdate();
						if(rs==0) {
							JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
						}
						ps = (PreparedStatement) con.prepareStatement("select* from resv WHERE reservation_num = ?");
						ps.setString(1, name);
						rs1 = ps.executeQuery();
						for(int i= model1.getRowCount()-1; i>=0; i--) {
						     model1.removeRow(i);
						    }
						    
								while(rs1.next()) {
								    // JTable 내용추가
								    String[]  row = new String[8];
								    row[0] = rs1.getString("reservation_num");
								    row[1] = rs1.getString("filght_num");
								    row[2] = rs1.getString("airplane_id");
								    row[3] = rs1.getString("cus_name");
								    row[4] = rs1.getString("airport_start");
								    row[5] = rs1.getString("airport_des");
								    row[6] = rs1.getString("start_time");
								    row[7] = rs1.getString("des_time");
								    model1.addRow(row);
								}
					}
					else if (instype==4) {
						ps = (PreparedStatement) con.prepareStatement("update airline_filght set airport_start =? WHERE filght_num =(select filght_num from reservation where reservation_num= ?)");
						ps.setString(1, ins);
						ps.setString(2, name);
						int rs = 0;
						
						rs = ps.executeUpdate();
						if(rs==0) {
							JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
						}
						ps = (PreparedStatement) con.prepareStatement("select* from resv WHERE reservation_num = ?");
						ps.setString(1, name);
						rs1 = ps.executeQuery();
						for(int i= model1.getRowCount()-1; i>=0; i--) {
						     model1.removeRow(i);
						    }
						    
								while(rs1.next()) {
								    // JTable 내용추가
								    String[]  row = new String[8];
								    row[0] = rs1.getString("reservation_num");
								    row[1] = rs1.getString("filght_num");
								    row[2] = rs1.getString("airplane_id");
								    row[3] = rs1.getString("cus_name");
								    row[4] = rs1.getString("airport_start");
								    row[5] = rs1.getString("airport_des");
								    row[6] = rs1.getString("start_time");
								    row[7] = rs1.getString("des_time");
								    model1.addRow(row);
								}
					}
					else if (instype==5) {
						ps = (PreparedStatement) con.prepareStatement("update airline_filght set airport_des =? WHERE filght_num =(select filght_num from reservation where reservation_num= ?)");
						ps.setString(1, ins);
						ps.setString(2, name);
						int rs = 0;
						
						rs = ps.executeUpdate();
						if(rs==0) {
							JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
						}
						ps = (PreparedStatement) con.prepareStatement("select* from resv WHERE reservation_num = ?");
						ps.setString(1, name);
						rs1 = ps.executeQuery();
						for(int i= model1.getRowCount()-1; i>=0; i--) {
						     model1.removeRow(i);
						    }
						    
								while(rs1.next()) {
								    // JTable 내용추가
									 String[]  row = new String[8];
									    row[0] = rs1.getString("reservation_num");
									    row[1] = rs1.getString("filght_num");
									    row[2] = rs1.getString("airplane_id");
									    row[3] = rs1.getString("cus_name");
									    row[4] = rs1.getString("airport_start");
									    row[5] = rs1.getString("airport_des");
									    row[6] = rs1.getString("start_time");
									    row[7] = rs1.getString("des_time");
								    model1.addRow(row);
								}
					}
					else if (instype==6) {
						ps = (PreparedStatement) con.prepareStatement("update airline_filght set start_time =? WHERE filght_num =(select filght_num from reservation where reservation_num= ?)");
						ps.setString(1, ins);
						ps.setString(2, name);
						int rs = 0;
						
						rs = ps.executeUpdate();
						if(rs==0) {
							JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
						}
						ps = (PreparedStatement) con.prepareStatement("select* from resv WHERE reservation_num = ?");
						ps.setString(1, name);
						rs1 = ps.executeQuery();
						for(int i= model1.getRowCount()-1; i>=0; i--) {
						     model1.removeRow(i);
						    }
						    
								while(rs1.next()) {
								    // JTable 내용추가
									 String[]  row = new String[8];
									    row[0] = rs1.getString("reservation_num");
									    row[1] = rs1.getString("filght_num");
									    row[2] = rs1.getString("airplane_id");
									    row[3] = rs1.getString("cus_name");
									    row[4] = rs1.getString("airport_start");
									    row[5] = rs1.getString("airport_des");
									    row[6] = rs1.getString("start_time");
									    row[7] = rs1.getString("des_time");
								    model1.addRow(row);
								}
					}
					else if (instype==7) {
						ps = (PreparedStatement) con.prepareStatement("update airline_filght set des_time =? WHERE filght_num =(select filght_num from reservation where reservation_num= ?)");
						ps.setString(1, ins);
						ps.setString(2, name);
						int rs = 0;
						
						rs = ps.executeUpdate();
						if(rs==0) {
							JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
						}
						ps = (PreparedStatement) con.prepareStatement("select* from resv WHERE reservation_num = ?");
						ps.setString(1, name);
						rs1 = ps.executeQuery();
						for(int i= model1.getRowCount()-1; i>=0; i--) {
						     model1.removeRow(i);
						    }
						    
								while(rs1.next()) {
								    // JTable 내용추가
									 String[]  row = new String[8];
									    row[0] = rs1.getString("reservation_num");
									    row[1] = rs1.getString("filght_num");
									    row[2] = rs1.getString("airplane_id");
									    row[3] = rs1.getString("cus_name");
									    row[4] = rs1.getString("airport_start");
									    row[5] = rs1.getString("airport_des");
									    row[6] = rs1.getString("start_time");
									    row[7] = rs1.getString("des_time");
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
//		button_3.setBounds(806, 274, 206, 43);
		// add(button_3);
		
		
//		button_4.setBounds(807, 363, 206, 49);
//		 add(button_4);
	//	
//		textField_3 = new JTextField();
//		textField_3.setBounds(12, 387, 153, 25);
//		 add(textField_3);
//		textField_3.setColumns(10);
	//	
//		textField_4 = new JTextField();
//		textField_4.setColumns(10);
//		textField_4.setBounds(169, 387, 153, 25);
//		 add(textField_4);
	//	
//		textField_5 = new JTextField();
//		textField_5.setColumns(10);
//		textField_5.setBounds(326, 387, 153, 25);
//		 add(textField_5);
	//	
//		textField_6 = new JTextField();
//		textField_6.setColumns(10);
//		textField_6.setBounds(481, 387, 153, 25);
//		 add(textField_6);
	//	
//		textField_7 = new JTextField();
//		textField_7.setColumns(10);
//		textField_7.setBounds(641, 387, 153, 25);
//		 add(textField_7);
	//	
//		lblid_2 = new JLabel("항공사ID");
//		lblid_2.setHorizontalAlignment(SwingConstants.CENTER);
//		lblid_2.setBounds(12, 367, 153, 15);
//		 add(lblid_2);
	//	
//		label_4 = new JLabel("항공사이름");
//		label_4.setHorizontalAlignment(SwingConstants.CENTER);
//		label_4.setBounds(169, 367, 153, 15);
//		 add(label_4);
	//	
//		label_5 = new JLabel("주소");
//		label_5.setHorizontalAlignment(SwingConstants.CENTER);
//		label_5.setBounds(326, 367, 153, 15);
//		 add(label_5);
	//	
//		label_6 = new JLabel("전화번호");
//		label_6.setHorizontalAlignment(SwingConstants.CENTER);
//		label_6.setBounds(481, 367, 153, 15);
//		 add(label_6);
	//	
//		label_7 = new JLabel("국적");
//		label_7.setHorizontalAlignment(SwingConstants.CENTER);
//		label_7.setBounds(641, 367, 153, 15);
//		 add(label_7);
		}
		

	}
