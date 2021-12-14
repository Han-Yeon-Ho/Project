import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyModalDialog extends JDialog {
	JTextField id = new JTextField(10);
	JTextField name = new JTextField(10);
	JTextField address = new JTextField(30);
	JTextField tel = new JTextField(20);
	JTextField country = new JTextField(10);
	JButton okButton = new JButton("OK");
	
	public MyModalDialog(JFrame frame, String title) {
		super(frame,title, true); // 모달 다이얼로그로 설정
		setLayout(new FlowLayout());
		add(id);
		add(name);
		add(address);
		add(tel);
		add(country);
		
		add(okButton);
		setSize(200, 100);

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

	String getInput1() {
		if(id.getText().length() == 0) return null;
		else return id.getText();
	}
	String getInput2() {
		if(name.getText().length() == 0) return null;
		else return name.getText();
	}
	String getInput3() {
		if(address.getText().length() == 0) return null;
		else return address.getText();
	}
	String getInput4() {
		if(tel.getText().length() == 0) return null;
		else return tel.getText();
	}
	String getInput5() {
		if(country.getText().length() == 0) return null;
		else return country.getText();
	}
}
