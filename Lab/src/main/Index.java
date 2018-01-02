package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.Database;
import database.DatabaseConnection;
import database.ExecuteQuery;

public class Index {

	private JFrame frame;
	private JTextField textField_Username;
	private JLabel label;
	private JPasswordField passwordField;
	DatabaseConnection connection = new DatabaseConnection();
	ExecuteQuery executeQuery = new ExecuteQuery();
	Database database = new Database();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Index() {
		initialize();
		database.database();
		createTable();
	}

	public void createTable() {
		String doctorTable = "create table doctor(id int(11)AUTO_INCREMENT PRIMARY KEY,doctorName varchar(255),"
				+ "department varchar(255),doorNo varchar(255),streetInfo varchar(255),areaName varchar(255),"
				+ "cityName varchar(255),pincode varchar(255),state varchar(255),phone varchar(255),"
				+ "mobile varchar(255),email varchar(255),remarks varchar(255)";

		String labTable = "create table lab(id int(11)AUTO_INCREMENT PRIMARY KEY,labName varchar(255),"
				+ "department varchar(255),doorNo varchar(255),streetInfo varchar(255),areaName varchar(255),"
				+ "cityName varchar(255),pincode varchar(255),state varchar(255),phone varchar(255),"
				+ "mobile varchar(255),email varchar(255),remarks varchar(255)";

		String invoiceTable = "create table invoice(id int(11)AUTO_INCREMENT PRIMARY KEY,patientName varchar(255),"
				+ "age varchar(255),sex varchar(255),address varchar(255),city varchar(255),"
				+ "state varchar(255),mobile varchar(255),refferedBy varchar(255),labName varchar(255),"
				+ "totalAmount int(11),concession int(11),otherCharges int(11),netAmount int(11),"
				+ "receivedAmount int(11),balance int(11),date varchar(255)";

		String testTable = "create table test(id int(11)AUTO_INCREMENT PRIMARY KEY,testName varchar(255),"
				+ "rate1 int(11),rate2 int(11),departmentNo varchar(255),sampleNo varchar(255),"
				+ "inputType varchar(255),methodType varchar(255),isSubTest varchar(255),unit varchar(255),"
				+ "digit varchar(255),decimal varchar(255),defaultValue varchar(255),minValue varchar(255),"
				+ "maxValue varchar(255),isPicklist varchar(255),formula varchar(255),normalValue text(),"
				+ "isSpecial varchar(255),isCulture varchar(255)";

		String reportTable = "create table report(id int(11)AUTO_INCREMENT PRIMARY KEY,rate1 int(11),"
				+ "rate1 int(11),departmentNo varchar(255),departmentNo varchar(255),normalValue text(),"
				+ "departmentNo varchar(255)";

		try {
			DatabaseMetaData dmd = connection.getConnection().getMetaData();
			ResultSet set = dmd.getTables(null, null, "doctor", null);
			if (set.next()) {
				System.out.println("doctor table already created");
			} else {
				executeQuery.executeQuery(doctorTable, "doctorTable");

			}
			ResultSet set1 = dmd.getTables(null, null, "lab", null);
			if (set1.next()) {
				System.out.println("lab table already created");
			} else {
				executeQuery.executeQuery(labTable, "labTable");
			}
			ResultSet set2 = dmd.getTables(null, null, "test", null);
			if (set2.next()) {
				System.out.println("test table already created");
			} else {
				executeQuery.executeQuery(testTable, "testTable");
			}
			ResultSet set3 = dmd.getTables(null, null, "invoice", null);
			if (set3.next()) {
				System.out.println("invoice table already created");
			} else {
				executeQuery.executeQuery(invoiceTable, "invoiceTable");
			}
			ResultSet set4 = dmd.getTables(null, null, "report", null);
			if (set4.next()) {
				System.out.println("report table already created");
			} else {
				executeQuery.executeQuery(reportTable, "reportTable");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField_Username = new JTextField();
		textField_Username.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_Username.setBounds(684, 282, 272, 43);
		frame.getContentPane().add(textField_Username);
		textField_Username.setColumns(10);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(684, 349, 272, 38);
		frame.getContentPane().add(lblPassword);

		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(684, 233, 272, 38);
		frame.getContentPane().add(lblUsername);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBackground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(684, 447, 272, 38);
		frame.getContentPane().add(label);

		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String username = textField_Username.getText();
					@SuppressWarnings("deprecation")
					String password = passwordField.getText();
					if (username.equals("admin") && password.equals("admin")) {
						new Admin().setVisible(true);
					} else if (username.equals("local") && password.equals("local")) {
						new Local().setVisible(true);
						frame.setVisible(false);
					} else {
						label.setText("USERNAME OR PASSWORD IS INCORRECT");
					}
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String username = textField_Username.getText();
				@SuppressWarnings("deprecation")
				String password = passwordField.getText();
				if (username.equals("admin") && password.equals("admin")) {
					new Admin().setVisible(true);
				} else if (username.equals("local") && password.equals("local")) {
					new Local().setVisible(true);
					frame.setVisible(false);
				} else {
					label.setText("USERNAME OR PASSWORD IS INCORRECT");
				}
			}
		});
		btnLogin.setBounds(847, 483, 109, 43);
		frame.getContentPane().add(btnLogin);
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String username = textField_Username.getText();
					@SuppressWarnings("deprecation")
					String password = passwordField.getText();
					if (username.equals("admin") && password.equals("admin")) {
						new Admin().setVisible(true);
					} else if (username.equals("local") && password.equals("local")) {
						new Local().setVisible(true);
						frame.setVisible(false);
					} else {
						label.setText("USERNAME OR PASSWORD IS INCORRECT");
					}
				}
			}
		});
		passwordField.setBounds(684, 399, 272, 43);
		frame.getContentPane().add(passwordField);
	}

}
