
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main implements ActionListener {
	private JFrame frame = new JFrame();
	private JPanel panel1 = new JPanel();
	private JLabel id = new JLabel("���̵�");
	private JLabel pwd = new JLabel("��й�ȣ");
	private JTextField putid = new JTextField();
	private JPasswordField putpwd = new JPasswordField();
	private JButton login = new JButton("�α���");
	public String username;
	public String password;
	public static ConnectDB connect;

	
	public Main() {
		// �г� ���̾ƿ� ����
		panel1.setLayout(null);
		// ������Ʈ ��ġ ����
		id.setBounds(20, 10, 60, 30);
		pwd.setBounds(20, 50, 60, 30);
		putid.setBounds(100, 10, 80, 30);
		putid.setText("System");
		putpwd.setBounds(100, 50, 80, 30);
		putpwd.setText("sdk9z7a15u");
		login.setBounds(200, 25, 80, 35);
		// ��ư�� �׼Ǹ����� �߰�
		login.addActionListener(this);
		// �гο� ������Ʈ �߰�
		panel1.add(id);
		panel1.add(pwd);
		panel1.add(putid);
		panel1.add(putpwd);
		panel1.add(login);
		// �����ӿ� �г� �߰�
		frame.add(panel1);
		// ������ ����
		frame.setTitle("�����ͺ��̽� �α���");
		frame.setBounds(150, 150, 320, 130);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.setVisible(true);		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == login) {
			username = putid.getText();
			password = new String(putpwd.getPassword());
			
			new ConnectDB(username, password);
			frame.dispose();
		}
	}
}