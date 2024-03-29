import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class AddWorker extends JFrame {
	
	JLabel labelWorkerName = new JLabel("직원명");
	JLabel labelWorkerGrade = new JLabel("직급");

	JButton buttonSign = new JButton("등록");
	JButton buttonCancel = new JButton("취소");

	JTextField tFieldName = new JTextField();
	JComboBox<String> comboGrade = new JComboBox<>();

	private Connection db;
	AddWorker(Connection db) {
		this.setLayout(null);
		this.db = db;

		comboGrade.addItem("Supervisor");
		comboGrade.addItem("Staff");

		buttonSign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				signStaff();
				dispose();
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		labelWorkerName.setBounds(20, 20, 100, 30);
		labelWorkerGrade.setBounds(20, 70, 100, 30);
		tFieldName.setBounds(130, 20, 100, 30);
		comboGrade.setBounds(130, 70, 100, 30);
		buttonSign.setBounds(20, 120, 100, 30);
		buttonCancel.setBounds(130, 120, 100, 30);

		this.add(labelWorkerName);
		this.add(labelWorkerGrade);
		this.add(tFieldName);
		this.add(comboGrade);
		this.add(buttonSign);
		this.add(buttonCancel);

		this.setTitle("직원등록");
		this.setBounds(150, 150, 270, 200);
		this.setVisible(true);
	}

	private int getStaffCount() {
		String sqlStr = "Select Count(id) from staff";
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
			String sqlStr = "select Count(name) from worker where name = '" + name + "'";
			PreparedStatement stmt = db.prepareStatement(sqlStr);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			already = Integer.parseInt(rs.getString("Count(name)")) > 0;
		} catch (Exception e) {
			System.out.println(e);
		}
		return already;
	}

	private void signStaff() {
		String name = tFieldName.getText();
		String grade = (String) comboGrade.getSelectedItem();

		try {
			if (!isAlready(name)) {
				int id = 1000 + getStaffCount();

				String sqlStr = "insert into worker values (" + "'" + name + "'," + "" + id + ", " + "'"
						+ grade + "', " + "0)";

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
