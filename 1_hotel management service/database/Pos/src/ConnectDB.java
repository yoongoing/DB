import java.sql.*;

import javax.swing.JOptionPane;

public class ConnectDB {
	// Oracle user ID
	private String id;
	// Password
	private String pwd; 
	// Connection
	private static Connection db;
	
	// ������
	public ConnectDB(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
		connect();
	}
	
	// Connect
	private void connect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			db = DriverManager.getConnection("jdbc:oracle:thin:"+ "@localhost:1521:XE", id, pwd);
			System.out.println("�����ͺ��̽��� �����Ͽ����ϴ�.");
			new Viewer(db);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.out.println("SQLException :" + e);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			JOptionPane.showMessageDialog(null, "�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
		}
	}
	
	public Connection getConnect() {
		return db;
	}

}
