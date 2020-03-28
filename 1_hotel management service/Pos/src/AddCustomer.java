

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCustomer implements ActionListener {
	JFrame f = new JFrame();
	JPanel p = new JPanel();
	JLabel l_customer_name = new JLabel("������");
	JLabel l_born = new JLabel("����(4�ڸ�)");
	JLabel l_phone = new JLabel("����ó");
	JTextField f_add_customer_name = new JTextField();
	JTextField f_add_customer_born = new JTextField();
	JTextField f_add_customer_phone = new JTextField();
	JButton b_add_customer = new JButton("���Խ�û");
	JButton b_add_customer_cancle = new JButton("���");
	Connection db;
	String sql;
	PreparedStatement stmt;
	ResultSet rs;
	int customer_id = 1000;
	
	public AddCustomer(Connection db) {
		this.db = db;
		p.setLayout(null);
		l_customer_name.setBounds(20, 20, 100, 30);
		l_born.setBounds(20, 70, 100, 30);
		l_phone.setBounds(20, 120, 100, 30);
		f_add_customer_name.setBounds(130, 20, 100, 30);
		f_add_customer_born.setBounds(130, 70, 100, 30);
		f_add_customer_phone.setBounds(130, 120, 100, 30);
		b_add_customer.setBounds(20, 170, 100, 30);
		b_add_customer_cancle.setBounds(130, 170, 100, 30);
		b_add_customer.addActionListener(this);
		b_add_customer_cancle.addActionListener(this);
		p.add(l_customer_name);
		p.add(l_born);
		p.add(l_phone);
		p.add(f_add_customer_name);
		p.add(f_add_customer_born);
		p.add(f_add_customer_phone);
		p.add(b_add_customer);
		p.add(b_add_customer_cancle);
		f.add(p);
		f.setTitle("ȸ�����");
		f.setBounds(150, 150, 270, 250);
		f.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == b_add_customer) {
			String name = f_add_customer_name.getText();
			String born = f_add_customer_born.getText();
			String phone = f_add_customer_phone.getText();
			// ���� ����� �̸����� ��� ���ϱ�
			try {
				boolean already = this.already(name);
				System.out.println(already);
				if (already) {
					JOptionPane.showMessageDialog(null, "�̹� �����ϴ� �̸��Դϴ�.");
				}
				else if (born.length()!=4) {
					JOptionPane.showMessageDialog(null, "������ (MMDD)���·� �Է��Ͻñ� �ٶ��ϴ�.");
				}
				else {
					// customer�� id Ȯ���Ű��
					sql = "select customer_id from customer";
					stmt = db.prepareStatement(sql);
					rs = stmt.executeQuery();
					while (rs.next()) {
						customer_id = rs.getInt("customer_id");
						System.out.println(customer_id);
					}
					customer_id++;
					// ���ο� cusotmer �����ϱ�
					sql = "insert into customer values('"
							+ name + "'," // name
							+ customer_id + ",'" // customer_id
							+ born + "'," // born
							+ phone + "," // phone
							+ "'Normal'," // grade
							+ "0)"; // amount
					stmt = db.prepareStatement(sql);
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "���ԵǾ����ϴ�.");
					f.dispose();
				}
				stmt.close();
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == b_add_customer_cancle) {
			f.dispose();
		}
	}
	
	public boolean already(String name) throws SQLException {
		boolean checkName = false;
		sql = "select name from customer";
		stmt = db.prepareStatement(sql);
		rs = stmt.executeQuery();
		while (rs.next()) {
			if (name.equals(rs.getString("name"))) {
				checkName = true;
			}
		}
		return checkName;
	}	
}
