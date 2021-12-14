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
	private String colName1[] = {"�װ��� ��ȣ","����� ID","�����(����)","������(����)","��߽ð�","�����ð�","���"};
	private DefaultTableModel model1 = new DefaultTableModel(colName1, 0){
        // Jtable ���� ���� �ȵǰ�
        public boolean isCellEditable(int i, int c) {
            return false;
        }
    };
    private JTable table_2; //�װ�������� ���̺�
    private ListSelectionModel model2;
    
	
	private JScrollPane scrollPane_1; //�װ�������� ��ũ����
	private JButton button_1; //��ü����
	private JLabel label_1; //�װ����̸����� �˻�
	private JLabel label_2; //�˻���. �װ����̸�
	private JTextField textField_1; //�˻���. �װ����̸�
	private JButton button_2; //�� ��.
	private JLabel lblid_1; //�װ���ID�� ����
	private JLabel label_3; //�˻���. �װ���ID
	private JTextField textField_2; //�˻���. �װ���ID
	private JButton button_3; //�� ��.
	private JButton button_4; //�� ��.
	
	private JTextField textField_3; //�װ���ID
	private JTextField textField_4; //�װ��� �̸�
	private JTextField textField_5; //�ּ�
	private JTextField textField_6; //��ȭ��ȣ
	private JTextField textField_7; //����
	
	private JLabel lblid_2;//�װ��� ID
	private JLabel label_4; //�װ��� �̸�
	private JLabel label_5; //�ּ�
	private JLabel label_6; //��ȭ��ȣ
	private JLabel label_7; //����
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
			System.out.println("�̹��� ������ �����ϴ�.");
			
		}
		JLabel imageLabel= new JLabel(new ImageIcon(img));
		imageLabel.setBounds(807,200,200,200);
		add(imageLabel);
		System.out.println("DB���� �Ϸ�");
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
			
	
			
			 // JTable ��������
		    for(int i= model1.getRowCount()-1; i>=0; i--) {
		     model1.removeRow(i);
		    }
		    try {
				while(rs2.next()) {
				    // JTable �����߰�
					
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
				
				int result = JOptionPane.showConfirmDialog(null, "���� �Ͻðڽ��ϱ�?",	"Confirm", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.CLOSED_OPTION) {
					// ����ڰ� "��"�� "�ƴϿ�"�� ���þ��� ���̾�α� â�� ���� ���
					
				}
				else if(result == JOptionPane.YES_OPTION) {
					// ����ڰ� "��"�� ������ ���
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
								JOptionPane.showMessageDialog(null, "���࿡ �����Ͽ����ϴ�.");
							}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//ps.setString(1, id);
				
				
					
				}
				else {	// ����ڰ� "�ƴϿ�"�� ������ ���
					
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
	button_1 = new JButton("��ü����");
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
				
		
				
				 // JTable ��������
			    for(int i= model1.getRowCount()-1; i>=0; i--) {
			     model1.removeRow(i);
			    }
			    try {
					while(rs2.next()) {
					    // JTable �����߰�
						
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
	
	label_1 = new JLabel("�� ȸ");
	label_1.setHorizontalAlignment(SwingConstants.CENTER);
	label_1.setBounds(807, 58, 205, 30);
	 add(label_1);
	String [] names = {"	����� ID		", "	����� ����		","������ ����","	��� �ð�		","���� �ð�","	�� ��		"};
	
	strCombo = new JComboBox(names);
	strCombo.setBackground(new Color(213,242,242));
	strCombo.setBounds(807, 98, 120, 29);
	add(strCombo);



	//label_2 = new JLabel("�˻��� :");
	//label_2.setBounds(807, 98, 57, 29);
	// add(label_2);
//	
//	textField_1 = new JTextField();
//	textField_1.setColumns(10);
//	textField_1.setBounds(876, 95, 57, 32);
//	 add(textField_1);
	
	TextField tp = new TextField(15);
	
	button_2 = new JButton("Ȯ ��");
	button_3 = new JButton("�� ��");
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
//				//String name = JOptionPane.showInputDialog("��ȸ �� �װ��� ��ȣ�� �Է��ϼ���.");
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
//						    // JTable �����߰�
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
//					//name = JOptionPane.showInputDialog("���� �� �װ��� ��ȣ�� �Է��ϼ���.");
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
//							    // JTable �����߰�
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
//							lblid_1 = new JLabel("������ �׸� ����");
//							lblid_1.setHorizontalAlignment(SwingConstants.CENTER);
//							lblid_1.setBounds(807, 130, 205, 30);
//							add(lblid_1);
//							//button_3 = new JButton("�� ��");
//							
//							String [] names = {"�׸� ����","�װ����ȣ", "�����ID","�����","������","��߽ð�","�����ð�","���"};
//							strCombo2 = new JComboBox(names);
//							strCombo2.setBackground(new Color(213,242,242));
//							strCombo2.setBounds(807, 160, 120, 29);
//							add(strCombo2);
//							//button_3 = new JButton("�� ��");
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
//					name = JOptionPane.showInputDialog("���� �� �����ȣ�� �Է��ϼ���.");
//					System.out.println(name);
//					ps = (PreparedStatement) con.prepareStatement("DELETE FROM airline_filght WHERE filght_num =?");
//					ps.setString(1, name);
//					
//					int rs1 = 0;
//					System.out.println(ps);
//					rs1 = ps.executeUpdate();
//					if(rs1==0) {
//						JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�.");
//					}
//					
//					ps = (PreparedStatement) con.prepareStatement("select* from airline_filght");		
//					rs = ps.executeQuery();
//				    for(int i= model1.getRowCount()-1; i>=0; i--) {
//					     model1.removeRow(i);
//					    }
//					    try {
//							while(rs.next()) {
//							    // JTable �����߰�
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
//					String ID1=JOptionPane.showInputDialog("�߰� �� �װ��� ��ȣ�� �Է��ϼ���.");
//					ps = (PreparedStatement) con.prepareStatement("select* from airline_filght where filght_num = ?");
//					ps.setString(1, ID1);
//					
//					rs = ps.executeQuery();
//					if(rs.next()) {
//						 ID1=JOptionPane.showInputDialog("�ߺ��� �����ȣ�Դϴ�. \n �ٸ� �����ȣ�� �Է��ϼ���.");
//						
//					}
//					
//					String apNO=JOptionPane.showInputDialog("�߰� �� �װ����� �����ID�� �Է��ϼ���.");
//					ps = (PreparedStatement) con.prepareStatement("select* from airplane where airplane_id = ?");
//					ps.setString(1, apNO);
//					int rs1 = 0;
//					rs = ps.executeQuery();
//					if(!rs.next()) {
//						apNO=JOptionPane.showInputDialog("�������� �ʴ� �װ��� ��ȣ�Դϴ�. \n �ٸ� �װ��� ��ȣ�� �Է��ϼ���.");
//						
//					}
//					String sD=JOptionPane.showInputDialog("�߰� �� �װ����� �����(����)�� �Է��ϼ���.");
//					String eD=JOptionPane.showInputDialog("�߰� �� �װ����� ������(����)�� �Է��ϼ���.");
//
//					
//					String sT=JOptionPane.showInputDialog("�߰� �� �װ����� ��߽ð��� �Է��ϼ���.");
//					String eT=JOptionPane.showInputDialog("�߰� �� �װ����� �����ð��� �Է��ϼ���.");
//					String cst=JOptionPane.showInputDialog("�߰� �� �װ����� ����� �Է��ϼ���.");
////					ps = (PreparedStatement) con.prepareStatement("select* from customer where cus_id = ?");
////					ps.setString(1, cID);
////					 rs1 = 0;
////						rs = ps.executeQuery();
////						if(!rs.next()) {
////						 ID1=JOptionPane.showInputDialog("�������� �ʴ� ȸ�� ID�Դϴ�. \n �ٸ� ȸ�� ID�� �Է��ϼ���.");
////						
////					}
//					
//					
//					
//					//String alNO=JOptionPane.showInputDialog("�߰� �� ������ �����ID�� �Է��ϼ���.");
//					//String cName=JOptionPane.showInputDialog("�߰� �� ������ ���̸��� �Է��ϼ���.");
//					//String Std=JOptionPane.showInputDialog("�߰� �� ������ ������� �Է��ϼ���.");
//					//String End=JOptionPane.showInputDialog("�߰� �� ������ �������� �Է��ϼ���.");
//					//String Stim=JOptionPane.showInputDialog("�߰� �� ������ ��߽ð��� �Է��ϼ���.");
//					//String Etim=JOptionPane.showInputDialog("�߰� �� ������ �����ð��� �Է��ϼ���.");
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
//						JOptionPane.showMessageDialog(null, "���Կ� �����Ͽ����ϴ�.");
//					}
//					
//					ps = (PreparedStatement) con.prepareStatement("select* from airline_filght");		
//					rs = ps.executeQuery();
//				    for(int i= model1.getRowCount()-1; i>=0; i--) {
//					     model1.removeRow(i);
//					    }
//					    try {
//							while(rs.next()) {
//							    // JTable �����߰�
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
//		 // JTable ��������
//		
		}
	});
	//button_2.setBounds(807, 137, 206, 43);
	button_2.setBounds(938, 95, 70, 32);
	 add(button_2);
	
	//lblid_1 = new JLabel("�װ���ID�� ����");
//	lblid_1.setHorizontalAlignment(SwingConstants.CENTER);
//	lblid_1.setBounds(808, 200, 205, 30);
	// add(lblid_1);
	
//	label_3 = new JLabel("�˻���");
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
		table_2.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
		table_2.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�


	//button_3 = new JButton("��   ��");
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
						    // JTable �����߰�
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
						    // JTable �����߰�
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
						    // JTable �����߰�
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
						    // JTable �����߰�
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
						    // JTable �����߰�
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
						    // JTable �����߰�
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

		 // JTable ��������

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
//	lblid_2 = new JLabel("�װ���ID");
//	lblid_2.setHorizontalAlignment(SwingConstants.CENTER);
//	lblid_2.setBounds(12, 367, 153, 15);
//	 add(lblid_2);
//	
//	label_4 = new JLabel("�װ����̸�");
//	label_4.setHorizontalAlignment(SwingConstants.CENTER);
//	label_4.setBounds(169, 367, 153, 15);
//	 add(label_4);
//	
//	label_5 = new JLabel("�ּ�");
//	label_5.setHorizontalAlignment(SwingConstants.CENTER);
//	label_5.setBounds(326, 367, 153, 15);
//	 add(label_5);
//	
//	label_6 = new JLabel("��ȭ��ȣ");
//	label_6.setHorizontalAlignment(SwingConstants.CENTER);
//	label_6.setBounds(481, 367, 153, 15);
//	 add(label_6);
//	
//	label_7 = new JLabel("����");
//	label_7.setHorizontalAlignment(SwingConstants.CENTER);
//	label_7.setBounds(641, 367, 153, 15);
//	 add(label_7);
	}
	

}


