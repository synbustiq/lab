package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import database.Database;
import database.DatabaseConnection;
import database.ExecuteQuery;

public class Index {

	private JFrame frmIndex;
	private JTextField textField_Username;
	private JLabel label;
	private JPasswordField passwordField;
	DatabaseConnection connection = new DatabaseConnection();
	ExecuteQuery executeQuery = new ExecuteQuery();
	Database database = new Database();
	private JLabel label_1;
	private JLabel lblSynbustiq;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window.frmIndex.setVisible(true);
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

		String doctorTable = "create table doctor(id int(11)AUTO_INCREMENT PRIMARY KEY,"
				+ "doctorName varchar(255),department varchar(255),doorNo varchar(255),"
				+ "streetInfo varchar(255),areaName varchar(255),cityName varchar(255),"
				+ "pincode varchar(255),state varchar(255),phone varchar(255),mobile varchar(255),"
				+ "email varchar(255),remarks varchar(255))";

		String labTable = "create table lab(id int(11)AUTO_INCREMENT PRIMARY KEY,"
				+ "labName varchar(255),department varchar(255),doorNo varchar(255),"
				+ "streetInfo varchar(255),areaName varchar(255),cityName varchar(255),"
				+ "pincode varchar(255),state varchar(255),phone varchar(255),mobile varchar(255),"
				+ "email varchar(255),remarks varchar(255))";

		String patientTable = "create table patient(id int(11)AUTO_INCREMENT PRIMARY KEY,"
				+ "patientName varchar(255),department varchar(255),doorNo varchar(255),"
				+ "streetInfo varchar(255),areaName varchar(255),cityName varchar(255),"
				+ "pincode varchar(255),state varchar(255),phone varchar(255),mobile varchar(255),"
				+ "email varchar(255),remarks varchar(255))";

		String invoiceTable = "create table invoice(id int(11)AUTO_INCREMENT PRIMARY KEY,"
				+ "patientName varchar(255),age varchar(255),sex varchar(255),address varchar(255),"
				+ "city varchar(255),state varchar(255),mobile varchar(255),refferedBy varchar(255),"
				+ "labName varchar(255),totalAmount int(11),concession int(11),otherCharges int(11),"
				+ "netAmount int(11),receivedAmount int(11),balance int(11),date varchar(255))";

		String testTable = "create table test(id int(11)AUTO_INCREMENT PRIMARY KEY,"
				+ "testName varchar(255),rate1 int(11),rate2 int(11),departmentNo varchar(255),"
				+ "sampleNo varchar(255),inputType varchar(255),methodType varchar(255),"
				+ "isSubTest varchar(255),unit varchar(255),digit varchar(255),decimal_1 varchar(255),"
				+ "defaultValue_1 varchar(255),minValue_1 varchar(255),maxValue_1 varchar(255),"
				+ "isPicklist varchar(255),formula varchar(255),normalValue text,"
				+ "isSpecial varchar(255),isCulture varchar(255))";

		String reportTable = "create table report(id int(11)AUTO_INCREMENT PRIMARY KEY,"
				+ "billNo int(11),testNo int(11),testName varchar(255),testRate varchar(255),"
				+ "normalValue text,testValue varchar(255))";

		String testDepartment = "create table testDepartment (id int(11)AUTO_INCREMENT PRIMARY KEY,"
				+ "departmentName varchar(255))";

		String testMethod = "create table testMethod (id int(11)AUTO_INCREMENT PRIMARY KEY,"
				+ "methodName varchar(255))";

		String testSample = "create table testSample (id int(11)AUTO_INCREMENT PRIMARY KEY,"
				+ "sampleName varchar(255))";
		try {
			DatabaseMetaData dmd = connection.getConnection().getMetaData();
			ResultSet set = dmd.getTables(null, null, "doctor", null);
			if (set.next()) {
				System.out.println("doctor table already created");
			} else {
				Statement statement = connection.getConnection().createStatement();
				statement.executeUpdate(doctorTable);
				System.out.println("doctor table created");
			}
			ResultSet set1 = dmd.getTables(null, null, "lab", null);
			if (set1.next()) {
				System.out.println("lab table already created");
			} else {
				Statement statement = connection.getConnection().createStatement();
				statement.executeUpdate(labTable);
				System.out.println("lab table created");
			}
			ResultSet set2 = dmd.getTables(null, null, "test", null);
			if (set2.next()) {
				System.out.println("test table already created");
			} else {
				Statement statement = connection.getConnection().createStatement();
				statement.executeUpdate(testTable);
				System.out.println("test table created");
			}
			ResultSet set3 = dmd.getTables(null, null, "invoice", null);
			if (set3.next()) {
				System.out.println("invoice table already created");
			} else {
				Statement statement = connection.getConnection().createStatement();
				statement.executeUpdate(invoiceTable);
				System.out.println("invoice table created");
			}
			ResultSet set4 = dmd.getTables(null, null, "report", null);
			if (set4.next()) {
				System.out.println("report table already created");
			} else {
				Statement statement = connection.getConnection().createStatement();
				statement.executeUpdate(reportTable);
				System.out.println("report table created");
			}
			ResultSet set5 = dmd.getTables(null, null, "patient", null);
			if (set5.next()) {
				System.out.println("patient table already created");
			} else {
				Statement statement = connection.getConnection().createStatement();
				statement.executeUpdate(patientTable);
				System.out.println("patient table created");
			}
			ResultSet set6 = dmd.getTables(null, null, "testDepartment", null);
			if (set6.next()) {
				System.out.println("testDepartment table already created");
			} else {
				Statement statement = connection.getConnection().createStatement();
				statement.executeUpdate(testDepartment);
				System.out.println("testDepartment table created");
			}
			ResultSet set7 = dmd.getTables(null, null, "testMethod", null);
			if (set7.next()) {
				System.out.println("testMethod table already created");
			} else {
				Statement statement = connection.getConnection().createStatement();
				statement.executeUpdate(testMethod);
				System.out.println("testMethod table created");
			}
			ResultSet set8 = dmd.getTables(null, null, "testSample", null);
			if (set8.next()) {
				System.out.println("testSample table already created");
			} else {
				Statement statement = connection.getConnection().createStatement();
				statement.executeUpdate(testSample);
				System.out.println("testSample table created");
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
		frmIndex = new JFrame();
		frmIndex.getContentPane().setBackground(new Color(255, 255, 0));
		frmIndex.setResizable(false);
		frmIndex.setTitle("Lancent");
		frmIndex.setBounds(100, 100, 500, 400);
		frmIndex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIndex.getContentPane().setLayout(null);

		textField_Username = new JTextField();
		textField_Username.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

				}
			}
		});
		textField_Username.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_Username.setBounds(132, 144, 352, 67);
		frmIndex.getContentPane().add(textField_Username);
		textField_Username.setColumns(10);

		JLabel lblUsername = new JLabel("");
		lblUsername.setIcon(new ImageIcon(Index.class.getResource("/image/Admin-icon.png")));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(10, 144, 97, 67);
		frmIndex.getContentPane().add(lblUsername);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBackground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(684, 447, 272, 38);
		frmIndex.getContentPane().add(label);
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 16));
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String username = textField_Username.getText();
					@SuppressWarnings("deprecation")
					String password = passwordField.getText();
					if (username.equals("admin") && password.equals("admin")) {
						new Admin().setVisible(true);
					} else if (username.equals("lab") && password.equals("lab")) {
						new Local().setVisible(true);
						frmIndex.dispose();
					} else {
						label.setText("USERNAME OR PASSWORD IS INCORRECT");
					}
				}
			}
		});
		passwordField.setBounds(132, 271, 352, 67);
		frmIndex.getContentPane().add(passwordField);

		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Index.class.getResource("/image/App-password-icon.png")));
		label_1.setBounds(10, 271, 84, 81);
		frmIndex.getContentPane().add(label_1);

		lblSynbustiq = new JLabel("SYNBUSTIQ");
		lblSynbustiq.setHorizontalAlignment(SwingConstants.CENTER);
		lblSynbustiq.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblSynbustiq.setBounds(10, 39, 474, 47);
		frmIndex.getContentPane().add(lblSynbustiq);
	}

}
