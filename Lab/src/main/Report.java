package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import database.DatabaseConnection;
import database.ExecuteQuery;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Report extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField_billNo;
	private JTextField textField_patientName;
	private JTextField textField_age;
	private JTextField textField_sex;
	private JTextField textField_mobile;
	private JTextField textField_refferedBy;
	private JTextField textField_date;
	private JTable testTable;
	private JTextPane textPane_testValue;
	DatabaseConnection dataBase = new DatabaseConnection();
	ExecuteQuery executeQuery = new ExecuteQuery();
	private JPanel panel_3;
	private JTextField textField_testName;
	private JTextField textField_testNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report frame = new Report();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Report() {
		setTitle("Report");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 27, 242, 65);
		panel.setLayout(null);
		panel.setBorder(null);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 103, 964, 547);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(10, 11, 57, 31);
		panel_1.add(lblName);

		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAge.setBounds(298, 11, 38, 31);
		panel_1.add(lblAge);

		JLabel lblSex = new JLabel("Sex");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSex.setBounds(475, 11, 38, 31);
		panel_1.add(lblSex);

		JLabel lblMobileNo = new JLabel("Mobile No");
		lblMobileNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMobileNo.setBounds(655, 11, 80, 31);
		panel_1.add(lblMobileNo);

		textField_patientName = new JTextField();
		textField_patientName.setEditable(false);
		textField_patientName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_patientName.setBounds(68, 13, 201, 31);
		panel_1.add(textField_patientName);
		textField_patientName.setColumns(10);

		textField_age = new JTextField();
		textField_age.setEditable(false);
		textField_age.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_age.setColumns(10);
		textField_age.setBounds(336, 13, 110, 31);
		panel_1.add(textField_age);

		textField_sex = new JTextField();
		textField_sex.setEditable(false);
		textField_sex.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_sex.setColumns(10);
		textField_sex.setBounds(514, 13, 110, 31);
		panel_1.add(textField_sex);

		textField_mobile = new JTextField();
		textField_mobile.setEditable(false);
		textField_mobile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_mobile.setColumns(10);
		textField_mobile.setBounds(735, 11, 201, 31);
		panel_1.add(textField_mobile);

		JLabel lblRefferedby = new JLabel("RefferedBy");
		lblRefferedby.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRefferedby.setBounds(10, 68, 80, 31);
		panel_1.add(lblRefferedby);

		textField_refferedBy = new JTextField();
		textField_refferedBy.setEditable(false);
		textField_refferedBy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_refferedBy.setColumns(10);
		textField_refferedBy.setBounds(91, 68, 201, 31);
		panel_1.add(textField_refferedBy);

		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(319, 68, 57, 31);
		panel_1.add(lblDate);

		textField_date = new JTextField();
		textField_date.setEditable(false);
		textField_date.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_date.setColumns(10);
		textField_date.setBounds(377, 68, 146, 31);
		panel_1.add(textField_date);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBounds(10, 110, 214, 426);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setVisible(false);
		panel_3.setBounds(234, 110, 720, 426);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		textPane_testValue = new JTextPane();
		textPane_testValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPane_testValue.setBounds(40, 83, 651, 247);
		panel_3.add(textPane_testValue);

		JButton btnSave = new JButton("SAVE");
		btnSave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String testValue = textPane_testValue.getText();
					String billNo = textField_billNo.getText();
					String testName = textField_testName.getText();
					String testNo = textField_testNo.getText();
					String sql = "UPDATE report SET testValue = '" + testValue + "' where billNo = '" + billNo
							+ "' and testName = '" + testName + "' and testNo = '" + testNo + "' ";
					executeQuery.executeQuery(sql, "Inserted");
				}
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String testValue = textPane_testValue.getText();
				String billNo = textField_billNo.getText();
				String testName = textField_testName.getText();
				String testNo = textField_testNo.getText();
				String sql = "UPDATE report SET testValue = '" + testValue + "' where billNo = '" + billNo
						+ "' and testName = '" + testName + "' and testNo = '" + testNo + "' ";
				executeQuery.executeQuery(sql, "Inserted");

			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setBounds(490, 352, 178, 45);
		panel_3.add(btnSave);

		JLabel lblTestname = new JLabel("Test Name");
		lblTestname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTestname.setBounds(40, 11, 84, 31);
		panel_3.add(lblTestname);

		JLabel lblTestNo = new JLabel("Test No");
		lblTestNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTestNo.setBounds(417, 11, 73, 31);
		panel_3.add(lblTestNo);

		textField_testName = new JTextField();
		textField_testName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_testName.setEditable(false);
		textField_testName.setColumns(10);
		textField_testName.setBounds(125, 11, 201, 31);
		panel_3.add(textField_testName);

		textField_testNo = new JTextField();
		textField_testNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_testNo.setEditable(false);
		textField_testNo.setColumns(10);
		textField_testNo.setBounds(490, 11, 201, 31);
		panel_3.add(textField_testNo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 192, 404);
		panel_2.add(scrollPane);

		testTable = new JTable();
		testTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Get a selected row value from table
				DefaultTableModel model = (DefaultTableModel) testTable.getModel();
				int rowIndex = testTable.getSelectedRow();
				textField_testName.setText((String) model.getValueAt(rowIndex, 0));

				panel_3.setVisible(true);
				String billNo = textField_billNo.getText();
				String testName = textField_testName.getText();
				Connection connection = dataBase.getConnection();
				PreparedStatement statement;
				ResultSet set;
				try {
					String sql = "select * from report where billNo = '" + billNo + "' and testName='" + testName + "'";
					statement = connection.prepareStatement(sql);
					set = statement.executeQuery();
					if (set.next()) {
						textField_testNo.setText(set.getString("testNo"));
						textPane_testValue.setText(set.getString("testValue"));
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		testTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		testTable.setColumnSelectionAllowed(true);
		testTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "TestName" }));

		scrollPane.setViewportView(testTable);

		textField_billNo = new JTextField();
		textField_billNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String billNo = textField_billNo.getText();
					Connection connection = dataBase.getConnection();
					PreparedStatement statement;
					ResultSet set;
					try {
						String sql = "select testName from report where billNo = '" + billNo + "'";
						statement = connection.prepareStatement(sql);
						set = statement.executeQuery();
						testTable.setModel(DbUtils.resultSetToTableModel(set));
						String sql1 = "select * from invoice where id ='" + billNo + "' ";
						statement = connection.prepareStatement(sql1);
						set = statement.executeQuery();
						if (set.next()) {
							textField_patientName.setText(set.getString("patientName"));
							textField_age.setText(set.getString("age"));
							textField_sex.setText(set.getString("sex"));
							textField_refferedBy.setText(set.getString("refferedBy"));
							textField_date.setText(set.getString("date"));
							textField_mobile.setText(set.getString("mobile"));
							textField_billNo.setEditable(false);
						} else {
							JOptionPane.showMessageDialog(null, "No data found");
						}

					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
					}
				}
			}
		});
		textField_billNo.setEditable(false);
		textField_billNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_billNo.setBounds(811, 54, 163, 38);
		contentPane.add(textField_billNo);
		textField_billNo.setColumns(10);

		JLabel lblBillNo = new JLabel("BILL NO :");
		lblBillNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBillNo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBillNo.setBounds(711, 54, 93, 38);
		contentPane.add(lblBillNo);

		JButton button_new = new JButton("NEW");
		button_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_billNo.setEditable(true);
				textField_patientName.setText(null);
				textField_age.setText(null);
				textField_sex.setText(null);
				textField_mobile.setText(null);
				textField_refferedBy.setText(null);
				textField_date.setText(null);
				testTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "TestName" }));
				panel_3.setVisible(false);
			}
		});
		button_new.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_new.setBounds(0, 0, 116, 65);
		panel.add(button_new);

		JButton button_print = new JButton("PRINT");
		button_print.addActionListener(new ActionListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void actionPerformed(ActionEvent arg0) {
				String id = textField_billNo.getText();
				// String patientName = textField_patientName.getText();
				// String age = textField_age.getText();
				// String sex = textField_sex.getText();
				// String date = textField_date.getText();
				// String doctorName = textField_refferedBy.getText();
				// String mobile = textField_mobile.getText();
				try {
					// print table without using database
					Connection connection = dataBase.getConnection();
					String reportPath = "src/jasper/report.jrxml";
					JasperReport jr = JasperCompileManager.compileReport(reportPath);
					HashMap report = new HashMap<>();
					report.put("id", id);
					// report.put("patientName", patientName);
					// report.put("age", age);
					// report.put("sex", sex);
					// report.put("date", date);
					// report.put("doctorName", doctorName);
					// report.put("mobile", mobile);
					JasperPrint jp = JasperFillManager.fillReport(jr, report, connection);
					JasperViewer.viewReport(jp, false);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		button_print.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_print.setBounds(126, 0, 116, 65);
		panel.add(button_print);
	}
}
