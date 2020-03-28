
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class AddCustomer extends JFrame {
	JLabel labelCustomerName = new JLabel("고객명");
	JLabel labelBirthday = new JLabel("생일(4자리)");
	JLabel labelContact = new JLabel("연락처");

	JTextField inputCustomerName = new JTextField();
	JTextField inputBirthday = new JTextField();
	JTextField inputContact = new JTextField();

	JButton btnSign = new JButton("가입신청");
	JButton btnCancel = new JButton("취소");
	private Connection db;
	
	AddCustomer(Connection db) {
		this.setLayout(null);
		this.db = db;
		labelCustomerName.setBounds(20, 20, 100, 30);
		labelBirthday.setBounds(20, 70, 100, 30);
		labelContact.setBounds(20, 120, 100, 30);
		inputCustomerName.setBounds(130, 20, 100, 30);
		inputBirthday.setBounds(130, 70, 100, 30);
		inputContact.setBounds(130, 120, 100, 30);
		btnSign.setBounds(20, 170, 100, 30);
		btnSign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				signCustomer();
				dispose();
			}
		});
		btnCancel.setBounds(130, 170, 100, 30);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		this.add(labelCustomerName);
		this.add(labelBirthday);
		this.add(labelContact);
		this.add(inputCustomerName);
		this.add(inputBirthday);
		this.add(inputContact);
		this.add(btnSign);
		this.add(btnCancel);

		this.setTitle("회원등록");
		this.setBounds(150, 150, 270, 250);
		this.setVisible(true);
	}

	private int getCustomerCount() {
		String sqlStr = "Select Count(id) from customer";
		int n = 0;
		try {
			PreparedStatement stmt = db.prepareStatement(sqlStr);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			n = Integer.parseInt(rs.getString("count(id)"));
		} catch (Exception e) {
			System.out.println(e);
		}
		return n;
	}

	private Boolean isAlready(String name) {
		Boolean already = false;

		try {
			String sqlStr = "select Count(name) from customer where name = '" + name + "'";
			PreparedStatement stmt = db.prepareStatement(sqlStr);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			already = Integer.parseInt(rs.getString("Count(name)")) > 0;
		} catch (Exception e) {
			System.out.println(e);
		}
		return already;
	}

	private void signCustomer() {
		String name = inputCustomerName.getText();
		String birthday = inputBirthday.getText();
		String phone = inputContact.getText();

		try {
			if (!isAlready(name)) {
				int id = 1000 + getCustomerCount();

				String sqlStr = "insert into customer values (" + "'" + name + "', "
						+ "" + id + ", " + "'" + birthday + "', " + "" + phone + ", " + "'Normal', 0" + ")";
				PreparedStatement stmt = db.prepareStatement(sqlStr);
				stmt.executeUpdate();
				stmt.close();
				JOptionPane.showMessageDialog(null, "등록되었습니다.");
			} else {
				JOptionPane.showMessageDialog(null, "동명이인은 추가할 수 없습니다.");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
