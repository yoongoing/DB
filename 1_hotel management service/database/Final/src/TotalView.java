import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class TotalView extends JPanel {
	JTabbedPane tp = new JTabbedPane();
	
	JPanel tabCustomer = new JPanel();
	JLabel labelCustomerName = new JLabel("������");
	JTextField tfiledCustomerName = new JTextField();
	JButton ButtonAddCustomer = new JButton("����");
	JButton ButtonFindCustomer = new JButton("��ȸ");
	JTextArea tAreaCustomer = new JTextArea();
	
	JPanel tabSales = new JPanel();
	JLabel labelDate = new JLabel("�Ⱓ");
	JComboBox<String> comboDate = new JComboBox();
	JTextArea tAreaSales = new JTextArea();
	
	JPanel tabWorker = new JPanel();
	JLabel LabelWorkerName = new JLabel("������");
	JTextField tfiledWorkerName = new JTextField();
	JButton ButtonAddWorker = new JButton("�������");
	JButton ButtonFindWorker = new JButton("��ȸ");
	JTextArea tAreaCWorker = new JTextArea();
	
	JPanel tabMenu = new JPanel();
	JLabel labelMenuName = new JLabel("�޴���");
	JTextField tfiledMenuName = new JTextField();
	JButton ButtonAddMenu = new JButton("�޴����");
	JButton ButtonFindMenu = new JButton("��ȸ");
	JTextArea tAreaMenu = new JTextArea();
	
	private Connection db;
	private TableView tableView = null;
	private MenuView menuView = null;
	private Addmenu addmenu = null;
	
	private String loginSet[] = null;


	public ArrayList<String> menuList = new ArrayList<>();
	
	TotalView(Connection db) {
		this.db = db;
		this.setBorder(new TitledBorder("���/��ȸ"));
		this.setLayout(new BorderLayout());
		//customer tab
		tabCustomer.setLayout(null);
		labelCustomerName.setBounds(15, 15, 100, 30);
		tfiledCustomerName.setBounds(15, 50, 100, 30);
		ButtonAddCustomer.setBounds(180, 50, 60, 30);
		ButtonFindCustomer.setBounds(250, 50, 60, 30);
		tAreaCustomer.setBounds(15, 90, 300, 200);
		tAreaCustomer.setBorder(new LineBorder(Color.gray, 2));
		ButtonAddCustomer.setBackground(Color.WHITE);
		ButtonAddCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddCustomer addCustomer = new AddCustomer(db);
			}
		});
		ButtonFindCustomer.setBackground(Color.WHITE);
		ButtonFindCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				findCustomer();
			}
		});
		tabCustomer.add(labelCustomerName);
		tabCustomer.add(tfiledCustomerName);
		tabCustomer.add(ButtonAddCustomer);
		tabCustomer.add(ButtonFindCustomer);
		tabCustomer.add(tAreaCustomer);

		//sales tab
		tabSales.setLayout(null);
		labelDate.setBounds(15, 15, 100, 30);
		comboDate.setBounds(150, 15, 100, 30);
		comboDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateSales();
			
			}
		});
		tAreaSales.setBounds(15, 50, 300, 240);
		tAreaSales.setBorder(new LineBorder(Color.gray, 2));
		tabSales.add(labelDate);
		tabSales.add(comboDate);
		tabSales.add(tAreaSales);
		tAreaSales.setText("");
		updateDateList();
		
		
		tabWorker.setLayout(null);
		
		LabelWorkerName.setBounds(15, 15, 100, 30);
		tfiledWorkerName.setBounds(15, 50, 100, 30);
		ButtonAddWorker.setBounds(150, 50, 90, 30);
		ButtonFindWorker.setBounds(250, 50, 60, 30);
		tAreaCWorker.setBounds(15, 90, 300, 200);
		tAreaCWorker.setBorder(new LineBorder(Color.gray, 2));
		ButtonAddWorker.setBackground(Color.WHITE);
		ButtonAddWorker.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddWorker addworker = new AddWorker(db);
			}
		});
		ButtonFindWorker.setBackground(Color.WHITE);
		ButtonFindWorker.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				findWorker();
			}
		});
		tabWorker.add(LabelWorkerName);
		tabWorker.add(tfiledWorkerName);
		tabWorker.add(ButtonAddWorker);
		tabWorker.add(ButtonFindWorker);
		tabWorker.add(tAreaCWorker);

		
		//menu tab
		tabMenu.setLayout(null);
		labelMenuName.setBounds(15, 15, 100, 30);
		tfiledMenuName.setBounds(15, 50, 120, 30);
		ButtonAddMenu.setBounds(150, 50, 90, 30);
		ButtonFindMenu.setBounds(250, 50, 60, 30);
		tAreaMenu.setBounds(15, 90, 300, 200);
		tAreaMenu.setBorder(new LineBorder(Color.gray, 2));
		ButtonAddMenu.setBackground(Color.WHITE);
		ButtonAddMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addmenu = new Addmenu(db);
				addmenu.setMenuView(menuView);
			}
		});
		ButtonFindMenu.setBackground(Color.WHITE);
		ButtonFindMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				findMenu();
			}
		});
		
		ButtonFindCustomer.setEnabled(false);
        tfiledCustomerName.setEnabled(false);
        ButtonAddCustomer.setEnabled(false);
        
        comboDate.setEnabled(false);
        
        ButtonFindMenu.setEnabled(false);
        tfiledMenuName.setEnabled(false);
        ButtonAddMenu.setEnabled(false);
        
        ButtonFindWorker.setEnabled(false);
        tfiledMenuName.setEnabled(false);
        ButtonAddWorker.setEnabled(false);      
		
		tabMenu.add(labelMenuName);
		tabMenu.add(tfiledMenuName);
		tabMenu.add(ButtonAddMenu);
		tabMenu.add(ButtonFindMenu);
		tabMenu.add(tAreaMenu);
		

		tp.addTab("����", tabCustomer);
		tp.addTab("����", tabSales);
		tp.addTab("����", tabWorker);
		tp.addTab("�޴�", tabMenu);
		this.add(tp, BorderLayout.CENTER);
		

		
	}
	
	//sales tab
	public void updateDateList(){
		comboDate.removeAllItems();
		try{
			String sql = "SELECT DISTINCT orderdate from total";
			PreparedStatement stmt = db.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				comboDate.addItem(rs.getDate("orderdate").toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int getDateSum(String date){
		int sum = 0;
		try{
			String sql = "SELECT sum(menuprice) FROM total WHERE orderdate = '"+ date +"'";
			PreparedStatement stmt = db.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			sum = rs.getInt("sum(menuprice)");
		}catch(Exception e){
			e.printStackTrace();
		}
		return sum;
	}
	public int getAllSum(String date){
		int sum = 0;
		try{
			String sql = "SELECT sum(menuprice) FROM total WHERE orderdate <= '"+date+"'";
			PreparedStatement stmt = db.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			sum = rs.getInt("sum(menuprice)");
		}catch(Exception e){
			e.printStackTrace();
		}
		return sum;
	}
	
	public ArrayList<String> maxMenuList(){
		menuList = new ArrayList<String>();
		
		try{
			String sql = "SELECT menuname FROM total GROUP BY menuname HAVING COUNT(menuname) =  ("
					+ "SELECT MAX(COUNT(menuname)) FROM total GROUP BY menuname)";
			PreparedStatement stmt = db.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				menuList.add(rs.getString("menuname"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return menuList;
		
	}
	public ArrayList<String> minMenuList(){
		menuList = new ArrayList<String>();
		
		try{
			String sql = "SELECT menuname FROM total GROUP BY menuname HAVING COUNT(menuname) = ("
					+ "SELECT MIN(COUNT(menuname)) FROM total GROUP BY menuname)";
			PreparedStatement stmt = db.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				menuList.add(rs.getString("menuname"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return menuList;
		
	}
	
	
	
	public void updateSales(){
		updateDateList();
		String result = "";
		if(loginSet != null && loginSet[1].toLowerCase().equals("supervisor")){
			
			String date = comboDate.getSelectedItem().toString();
			
			result += "�� ���� : " + getDateSum(date) + "\n";
			result += "--------------------------------\n";
			ArrayList<String> maxmenulist = maxMenuList();
			result += "���� ���� �ȸ� �޴�\n :";
			
			for (String menu : maxmenulist) {
	            result += menu + "\n";
	        }

	        result += "\n";

	        ArrayList<String> minmenulist = minMenuList();
	        result += "���� ���� �ȸ� �޴�\n :";

	        for (String menu : minmenulist) {
	            result+= menu + "\n";
	        }

	        result += "----------------------------------------\n";
			result += "���� ���� : " +getAllSum(date) + "\n";
		}
		
		tAreaSales.setText(result);
	}
	
	public void findWorker() {
		System.out.println("est");
		try {
			String sql = "SELECT * FROM worker WHERE name = '" + tfiledWorkerName.getText() + "'";
			PreparedStatement stmt = db.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			String resultStr = "������: " + rs.getString("name") + "\n";
			resultStr += "��  ��: " + rs.getString("grade") + "\n";
			resultStr += "�ѽ���: " + rs.getString("sales") + "\n";
			
			tAreaCWorker.setText(resultStr);
			stmt.close();
		}
		catch (Exception e) {
			tAreaCWorker.setText("����� ã�� �� ����");
		}
	}
	
	public void findMenu() {
		System.out.println("est");
		try {
			String sql = "SELECT * FROM menu WHERE name = '" + tfiledMenuName.getText() + "'";
			PreparedStatement stmt = db.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			String resultStr = "�޴���: " + rs.getString("name") + "\n";
			resultStr += "��  ��: " + rs.getString("price") + "\n";
			
			tAreaMenu.setText(resultStr);
			stmt.close();
		}
		catch (Exception e) {
			tAreaMenu.setText("����� ã�� �� ����");
		}
	}
	
	
	public void findCustomer() {
		System.out.println("est");
		try {
			String sql = "SELECT * FROM customer WHERE name = '" + tfiledCustomerName.getText() + "'";
			PreparedStatement stmt = db.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			String resultStr = "������: " + rs.getString("name") + "\n";
			resultStr += "����ID: " + rs.getString("id") + "\n";
			resultStr += "��    ��: " + rs.getString("birth") + "\n";
			resultStr += "��ȭ��ȣ: " + rs.getString("PONE") + "\n";
			resultStr += "�������: " + rs.getString("grade") + "\n";
			resultStr += "�� ���űݾ�: " + rs.getString("sales") + "\n";
			
			tAreaCustomer.setText(resultStr);
			stmt.close();
		}
		catch (Exception e) {
			tAreaMenu.setText("����� ã�� �� ����");
		}
	}
	
	public void setLoginTotal(String loginSet[]) {
        this.loginSet = loginSet;
        if(loginSet != null) {
            ButtonFindCustomer.setEnabled(true);
            tfiledCustomerName.setEnabled(true);
            ButtonAddCustomer.setEnabled(loginSet[1].toLowerCase().equals("supervisor"));
            
            comboDate.setEnabled(loginSet[1].toLowerCase().equals("supervisor"));
            
            
            ButtonFindMenu.setEnabled(true);
            tfiledMenuName.setEnabled(true);
            ButtonAddMenu.setEnabled(loginSet[1].toLowerCase().equals("supervisor"));
            
            ButtonFindWorker.setEnabled(true);
            tfiledWorkerName.setEnabled(true);
            ButtonAddWorker.setEnabled(loginSet[1].toLowerCase().equals("supervisor"));

        }
        else {
        	ButtonFindCustomer.setEnabled(false);
            tfiledCustomerName.setEnabled(false);
            ButtonAddCustomer.setEnabled(false);
            
            comboDate.setEnabled(false);
            
            ButtonFindMenu.setEnabled(false);
            tfiledMenuName.setEnabled(false);
            ButtonAddMenu.setEnabled(false);
            
            ButtonFindWorker.setEnabled(false);
            tfiledMenuName.setEnabled(false);
            ButtonAddWorker.setEnabled(false);            
            
        }
    }
	
	
	public void setTableView(TableView tableView){
		this.tableView = tableView;
	}
	public void setMenuView(MenuView menuView){
		this.menuView = menuView;
		
	}
	public void setAddmenu(Addmenu addmenu){
		this.addmenu = addmenu;
	}
	public void setLogin(String loginSet[]){
		this.loginSet = loginSet;
	}
}