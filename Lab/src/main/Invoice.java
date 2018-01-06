package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import database.DatabaseConnection;
import database.ExecuteQuery;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import other.ShowDate;

public class Invoice extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_date;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_age;
	private JTextField txtCity;
	private JTextField textField_mobile;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_sex;
	private JTextField textField_totalAmount;
	private JTextField textField_concession;
	private JTextField textField_otherCharges;
	private JTextField textField_netAmount;
	private JTextField textField_receivedAmount;
	private JTextField textField_balance;
	private JComboBox<String> comboBox_doctor;
	private JComboBox<String> comboBox_test;
	private JTable table;
	private JComboBox<String> comboBox_state;
	private JScrollPane scrollPane;
	private JComboBox<String> comboBox_Lab;
	private JButton btnPrint;
	private JButton btnEdit;
	private JButton btnNew;
	private JComboBox<String> comboBox_name;

	DatabaseConnection dataBase = new DatabaseConnection();
	ExecuteQuery executeQuery = new ExecuteQuery();
	ShowDate date = new ShowDate();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Invoice frame = new Invoice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void comboBox(String name, int index) {
		Connection connection = dataBase.getConnection();
		PreparedStatement statement;
		ResultSet set;
		try {
			String sql = "select * from " + name + " ";
			statement = connection.prepareStatement(sql);
			set = statement.executeQuery();
			if (name == "test") {
				while (set.next()) {
					comboBox_test.addItem(set.getString(index));
				}
			} else if (name == "doctor") {
				while (set.next()) {
					comboBox_doctor.addItem(set.getString(index));
				}
			} else if (name == "lab") {
				while (set.next()) {
					comboBox_Lab.addItem(set.getString(index));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Invoice() {
		setTitle("Invoice");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField_date = new JTextField();
		textField_date.setEditable(false);
		textField_date.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_date.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_date.setBounds(1065, 59, 119, 33);
		contentPane.add(textField_date);
		textField_date.setColumns(10);
		textField_date.setText(date.showDate());

		JLabel lblBillDate = new JLabel("BILL DATE");
		lblBillDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblBillDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBillDate.setBounds(926, 58, 129, 33);
		contentPane.add(lblBillDate);

		JLabel lblBillNo = new JLabel("BILL NO");
		lblBillNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBillNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBillNo.setBounds(658, 58, 129, 33);
		contentPane.add(lblBillNo);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 102, 1174, 287);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblPatientName = new JLabel("Patient Name");
		lblPatientName.setHorizontalAlignment(SwingConstants.LEFT);
		lblPatientName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPatientName.setBounds(10, 11, 112, 31);
		panel.add(lblPatientName);

		JLabel lblAge = new JLabel("Age");
		lblAge.setHorizontalAlignment(SwingConstants.LEFT);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAge.setBounds(20, 62, 102, 31);
		panel.add(lblAge);

		JLabel lblSex = new JLabel("Sex");
		lblSex.setHorizontalAlignment(SwingConstants.LEFT);
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSex.setBounds(348, 59, 60, 36);
		panel.add(lblSex);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAddress.setBounds(10, 104, 112, 31);
		panel.add(lblAddress);

		JLabel lblCity = new JLabel("City");
		lblCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCity.setBounds(10, 200, 112, 31);
		panel.add(lblCity);

		JLabel lblMobileNo = new JLabel("Mobile No");
		lblMobileNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMobileNo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMobileNo.setBounds(10, 248, 112, 31);
		panel.add(lblMobileNo);

		JLabel lblState = new JLabel("State");
		lblState.setHorizontalAlignment(SwingConstants.LEFT);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblState.setBounds(348, 197, 60, 37);
		panel.add(lblState);

		comboBox_name = new JComboBox();
		comboBox_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_name.setModel(new DefaultComboBoxModel(new String[] { "Mr.", "Mrs.", "Ms.", "Baby.", "Master.", "Dr.",
				"Selvi.", "Honable.", "Sister.", "Father." }));
		comboBox_name.setBounds(132, 9, 83, 37);
		panel.add(comboBox_name);

		textField_name = new JTextField();
		textField_name.setEditable(false);
		textField_name.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_name.setBounds(225, 11, 386, 37);
		panel.add(textField_name);
		textField_name.setColumns(10);

		textField_age = new JTextField();
		textField_age.setEditable(false);
		textField_age.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_age.setColumns(10);
		textField_age.setBounds(132, 59, 206, 37);
		panel.add(textField_age);

		txtCity = new JTextField();
		txtCity.setEditable(false);
		txtCity.setText("Chennai");
		txtCity.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtCity.setColumns(10);
		txtCity.setBounds(132, 197, 206, 37);
		panel.add(txtCity);

		textField_mobile = new JTextField();
		textField_mobile.setEditable(false);
		textField_mobile.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_mobile.setColumns(10);
		textField_mobile.setBounds(132, 245, 206, 37);
		panel.add(textField_mobile);

		JTextPane textPane_address = new JTextPane();
		textPane_address.setEditable(false);
		textPane_address.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPane_address.setBounds(132, 107, 479, 79);
		panel.add(textPane_address);

		comboBox_sex = new JComboBox();
		comboBox_sex.setEnabled(false);
		comboBox_sex.setEditable(true);
		comboBox_sex.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_sex.setModel(new DefaultComboBoxModel(new String[] { "Male", "Female", "Other" }));
		comboBox_sex.setBounds(407, 61, 204, 36);
		panel.add(comboBox_sex);

		comboBox_doctor = new JComboBox();
		comboBox_doctor.setEnabled(false);
		comboBox_doctor.setEditable(true);
		comboBox_doctor.setModel(new DefaultComboBoxModel(new String[] { "Self" }));
		comboBox_doctor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_doctor.setBounds(645, 60, 519, 37);
		panel.add(comboBox_doctor);

		JLabel lblRefferedBy = new JLabel("Reffered By");
		lblRefferedBy.setHorizontalAlignment(SwingConstants.LEFT);
		lblRefferedBy.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRefferedBy.setBounds(645, 8, 176, 37);
		panel.add(lblRefferedBy);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 389, 852, 271);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		comboBox_test = new JComboBox();
		comboBox_test.setEnabled(false);
		comboBox_test.setEditable(true);
		comboBox_test.setModel(new DefaultComboBoxModel(new String[] { "" }));
		comboBox_test.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_test.setBounds(8, 39, 719, 43);
		panel_1.add(comboBox_test);

		textField_id = new JTextField();
		textField_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {

						String id = textField_id.getText();

						Connection connection = dataBase.getConnection();
						PreparedStatement statement;
						ResultSet set;
						String sql1 = "select * from report where billNo = '" + id + "'";
						statement = connection.prepareStatement(sql1);
						ResultSet set1 = statement.executeQuery();
						while (set1.next()) {
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							Object[] row = new Object[3];
							row[0] = set1.getString("testNo");
							row[1] = set1.getString("testName");
							row[2] = set1.getString("testRate");
							model.addRow(row);

						}
						String sql = "select * from invoice where id = '" + id + "'";
						statement = connection.prepareStatement(sql);
						set = statement.executeQuery();
						if (set.next()) {
							textField_name.setText(set.getString("patientName"));
							textField_age.setText(set.getString("age"));
							txtCity.setText(set.getString("city"));
							textField_mobile.setText(set.getString("mobile"));
							textPane_address.setText(set.getString("address"));
							comboBox_sex.setActionCommand("sex");
							comboBox_doctor.setActionCommand("doctor");
							comboBox_Lab.setActionCommand("lab");
							comboBox_state.setActionCommand("state");
							textField_balance.setText(set.getString("balance"));
							textField_concession.setText(set.getString("concession"));
							textField_netAmount.setText(set.getString("netAmount"));
							textField_otherCharges.setText(set.getString("otherCharges"));
							textField_receivedAmount.setText(set.getString("receivedAmount"));
							textField_totalAmount.setText(set.getString("totalAmount"));

							textField_name.setEditable(true);
							textField_age.setEditable(true);
							txtCity.setEditable(true);
							textField_mobile.setEditable(true);
							textPane_address.setEditable(true);
							comboBox_sex.setEnabled(true);
							comboBox_doctor.setEnabled(true);
							comboBox_Lab.setEnabled(true);
							comboBox_state.setEnabled(true);
							comboBox_test.setEnabled(true);
							textField_balance.setEditable(true);
							textField_concession.setEditable(true);
							textField_netAmount.setEditable(true);
							textField_otherCharges.setEditable(true);
							textField_receivedAmount.setEditable(true);
							textField_totalAmount.setEditable(true);
						} else {
							JOptionPane.showMessageDialog(null, "NO value found in database");
						}
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
					}
				}
			}
		});
		textField_id.setEditable(false);
		textField_id.setEnabled(true);
		textField_id.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_id.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_id.setColumns(10);
		textField_id.setBounds(797, 59, 119, 33);
		contentPane.add(textField_id);

		JButton btnAdd = new JButton("Add");
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String name = comboBox_test.getSelectedItem().toString();
					try {
						String sql = "select * from test where testName = '" + name + "'";
						PreparedStatement statement = dataBase.getConnection().prepareStatement(sql);
						ResultSet set = statement.executeQuery();
						while (set.next()) {
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							Object[] row = new Object[3];
							row[0] = set.getString("id");
							row[1] = set.getString("testName");
							row[2] = set.getString("rate1");
							model.addRow(row);

							// Insert value into report table
							String testNo = set.getString("id");
							String billNo = textField_id.getText();
							String testRate = set.getString("rate1");
							String normalValue = set.getString("normalValue");
							String sql1 = "insert into report(billNo,testNo,testName,normalValue) values('" + billNo
									+ "','" + testNo + "','" + name + "','" + testRate + "','" + normalValue + "') ";
							Statement statement1 = dataBase.getConnection().createStatement();
							statement1.executeUpdate(sql1);

							double total1 = 0;
							for (int i = 0; i < table.getRowCount(); i++) {
								double result = Double.parseDouble((String) table.getValueAt(i, 2));
								total1 += result;
							}
							textField_totalAmount.setText(String.valueOf(total1));
						}
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
					}
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String name = comboBox_test.getSelectedItem().toString();
				try {
					String sql = "select * from test where testName = '" + name + "'";
					PreparedStatement statement = dataBase.getConnection().prepareStatement(sql);
					ResultSet set = statement.executeQuery();
					while (set.next()) {
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						Object[] row = new Object[3];
						row[0] = set.getString("id");
						row[1] = set.getString("testName");
						row[2] = set.getString("rate1");
						model.addRow(row);

						// Insert value into report table
						String testNo = set.getString("id");
						String billNo = textField_id.getText();
						String testRate = set.getString("rate1");
						String normalValue = set.getString("normalValue");
						String sql1 = "insert into report(billNo,testNo,testName,testRate,normalValue) values('"
								+ billNo + "','" + testNo + "','" + name + "','" + testRate + "','" + normalValue
								+ "') ";
						Statement statement1 = dataBase.getConnection().createStatement();
						statement1.executeUpdate(sql1);

						// calculation of Table value
						double total1 = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							double result = Double.parseDouble((String) table.getValueAt(i, 2));
							total1 += result;
						}
						textField_totalAmount.setText(String.valueOf(total1));
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(737, 41, 105, 43);
		panel_1.add(btnAdd);

		JLabel lblTestName = new JLabel("Test Name");
		lblTestName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTestName.setBounds(8, 11, 214, 28);
		panel_1.add(lblTestName);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(862, 389, 322, 271);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotalAmount.setBounds(10, 11, 161, 31);
		panel_2.add(lblTotalAmount);

		JLabel lblConcession = new JLabel("Concession");
		lblConcession.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblConcession.setBounds(10, 53, 161, 31);
		panel_2.add(lblConcession);

		JLabel lblOtherCharges = new JLabel("Other Charges");
		lblOtherCharges.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblOtherCharges.setBounds(10, 95, 161, 31);
		panel_2.add(lblOtherCharges);

		JLabel lblNetAmount = new JLabel("Net Amount");
		lblNetAmount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNetAmount.setBounds(10, 137, 161, 31);
		panel_2.add(lblNetAmount);

		JLabel lblReceivedAmount = new JLabel("Received Amount");
		lblReceivedAmount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblReceivedAmount.setBounds(10, 179, 161, 31);
		panel_2.add(lblReceivedAmount);

		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBalance.setBounds(10, 221, 161, 31);
		panel_2.add(lblBalance);

		textField_totalAmount = new JTextField();
		textField_totalAmount.setEditable(false);
		textField_totalAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_totalAmount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_totalAmount.setText("00.00");
		textField_totalAmount.setBounds(181, 10, 126, 34);
		panel_2.add(textField_totalAmount);
		textField_totalAmount.setColumns(10);

		textField_concession = new JTextField();
		textField_concession.setEditable(false);
		textField_concession.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_concession.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_concession.setText("00.00");
		textField_concession.setColumns(10);
		textField_concession.setBounds(181, 52, 126, 34);
		panel_2.add(textField_concession);

		textField_otherCharges = new JTextField();
		textField_otherCharges.setEditable(false);
		textField_otherCharges.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_otherCharges.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_otherCharges.setText("00.00");
		textField_otherCharges.setColumns(10);
		textField_otherCharges.setBounds(181, 94, 126, 34);
		panel_2.add(textField_otherCharges);

		textField_netAmount = new JTextField();
		textField_netAmount.setEditable(false);
		textField_netAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// calculation of value

				double totalAmount = Double.parseDouble(textField_totalAmount.getText());
				double concession = Double.parseDouble(textField_concession.getText());
				double otherCharge = Double.parseDouble(textField_otherCharges.getText());
				double amount = 0;
				double total = 0;
				amount = totalAmount - concession;
				total = amount + otherCharge;
				textField_netAmount.setText(String.valueOf(total));
			}
		});
		textField_netAmount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_netAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_netAmount.setText("00.00");
		textField_netAmount.setColumns(10);
		textField_netAmount.setBounds(181, 136, 126, 34);
		panel_2.add(textField_netAmount);

		textField_receivedAmount = new JTextField();
		textField_receivedAmount.setEditable(false);
		textField_receivedAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// calculation of value
				double amount = 0;
				double receivedAmount = Double.parseDouble(textField_receivedAmount.getText());
				double netAmount = Double.parseDouble(textField_netAmount.getText());
				amount = netAmount - receivedAmount;
				textField_balance.setText(String.valueOf(amount));
			}
		});
		textField_receivedAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_receivedAmount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_receivedAmount.setText("00.00");
		textField_receivedAmount.setColumns(10);
		textField_receivedAmount.setBounds(181, 178, 126, 34);
		panel_2.add(textField_receivedAmount);

		textField_balance = new JTextField();
		textField_balance.setEditable(false);
		textField_balance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// calculation of value
				double amount = 0;
				double receivedAmount = Double.parseDouble(textField_receivedAmount.getText());
				double netAmount = Double.parseDouble(textField_netAmount.getText());
				amount = netAmount - receivedAmount;
				textField_balance.setText(String.valueOf(amount));
			}
		});
		textField_balance.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_balance.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_balance.setText("00.00");
		textField_balance.setColumns(10);
		textField_balance.setBounds(181, 220, 126, 34);
		panel_2.add(textField_balance);

		comboBox_state = new JComboBox();
		comboBox_state.setEnabled(false);
		comboBox_state.setEditable(true);
		comboBox_state.setModel(new DefaultComboBoxModel(new String[] { "Tamil Nadu ", "Andhra Pradesh ",
				"Arunachal Pradesh", "Assam ", "Bihar ", "Goa ", "Gujarat ", "Haryana ", "Himachal Pradesh",
				"Jammu & Kashmir ", "Karnataka", "Kerala ", "Madhya Pradesh ", "Maharashtra ", "Manipur ", "Meghalaya ",
				"Mizoram ", "Nagaland ", "Orissa ", "Punjab ", "Rajasthan ", "Sikkim ", "Tripura ", "Uttar Pradesh ",
				"West Bengal ", "Chhattisgarh ", "Uttarakhand ", "Jharkhand ", "Telangana ", "Delhi ",
				"Andaman & Nicobar Islands", "Chandigarh ", "Dadra & Nagar Haveli ", "Daman & Diu ", "Lakshadweep ",
				"Puducherry " }));
		comboBox_state.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_state.setBounds(418, 199, 193, 36);
		panel.add(comboBox_state);

		JButton btnNewButton = new JButton("ADD NEW DOCTOR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Doctor doctor = new Doctor();
				doctor.setVisible(true);

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(988, 112, 176, 36);
		panel.add(btnNewButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 93, 719, 159);
		panel_1.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "TestNo", "TestName", "Rate" }));
		scrollPane.setViewportView(table);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Deleting a value from table
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int i = table.getSelectedRow();
				if (i >= 0) { // Condition is check whether the row is selected
								// or not
					double total = Double.parseDouble(textField_totalAmount.getText());
					double result = Double.parseDouble((String) table.getValueAt(i, 2));
					total -= result;
					// Used to delete value from database
					String testNo = (String) table.getValueAt(i, 0);
					String billNo = textField_id.getText();
					try {
						String sql = "delete from report where testNo ='" + testNo + "' and billNo='" + billNo + "'";
						Statement statement = dataBase.getConnection().createStatement();
						statement.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					model.removeRow(i);
					textField_totalAmount.setText(String.valueOf(total));
				} else {
					JOptionPane.showMessageDialog(null, "No row has been selected");
				}
			}
		});
		btnDelete.setBounds(737, 95, 105, 43);
		panel_1.add(btnDelete);

		JLabel lblLab = new JLabel("LAB");
		lblLab.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLab.setBounds(645, 142, 176, 31);
		panel.add(lblLab);

		comboBox_Lab = new JComboBox();
		comboBox_Lab.setEnabled(false);
		comboBox_Lab.setEditable(true);
		comboBox_Lab.setModel(new DefaultComboBoxModel(new String[] { "Lancent" }));
		comboBox_Lab.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_Lab.setBounds(645, 184, 519, 36);
		panel.add(comboBox_Lab);

		JButton btnAddNewLab = new JButton("ADD NEW LAB");
		btnAddNewLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OtherLab lab = new OtherLab();
				lab.setVisible(true);
			}
		});
		btnAddNewLab.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddNewLab.setBounds(988, 231, 176, 36);
		panel.add(btnAddNewLab);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBounds(10, 27, 494, 65);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		btnNew = new JButton("NEW");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// make all the value to reset
				textField_name.setText(null);
				textField_age.setText(null);
				txtCity.setText("chennai");
				textField_mobile.setText(null);
				textPane_address.setText(null);
				comboBox_sex.setEnabled(true);
				comboBox_doctor.setEnabled(true);
				comboBox_Lab.setEnabled(true);
				comboBox_state.setEnabled(true);
				comboBox_test.setEnabled(true);
				textField_totalAmount.setText("00.00");
				textField_concession.setText("00.00");
				textField_otherCharges.setText("00.00");
				textField_netAmount.setText("00.00");
				textField_receivedAmount.setText(null);
				textField_balance.setText(null);
				table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "TestNo", "TestName", "Rate" }));

				// make all the fields editable
				textField_name.setEditable(true);
				textField_age.setEditable(true);
				txtCity.setEditable(true);
				textField_mobile.setEditable(true);
				textPane_address.setEditable(true);
				comboBox_sex.setEnabled(true);
				comboBox_doctor.setEnabled(true);
				comboBox_Lab.setEnabled(true);
				comboBox_state.setEnabled(true);
				comboBox_test.setEnabled(true);
				textField_balance.setEditable(true);
				textField_concession.setEditable(true);
				textField_netAmount.setEditable(true);
				textField_otherCharges.setEditable(true);
				textField_receivedAmount.setEditable(true);
				textField_totalAmount.setEditable(true);
				try {
					Connection connection = dataBase.getConnection();
					Statement statement;
					String sql = "insert into invoice() values()";
					statement = connection.createStatement();
					@SuppressWarnings("unused")
					int set = statement.executeUpdate(sql);
					String sql1 = "select max(id) from invoice";
					ResultSet set1 = statement.executeQuery(sql1);
					while (set1.next()) {
						// JOptionPane.showMessageDialog(null,
						// set1.getString(1));
						textField_id.setText(set1.getString(1));
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				textField_id.setEnabled(false);
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNew.setBounds(0, 0, 116, 65);
		panel_3.add(btnNew);

		btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_id.setEditable(true);
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEdit.setBounds(126, 0, 116, 65);
		panel_3.add(btnEdit);

		btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = textField_id.getText();
				try {
					// print table without using database
					Connection connection = dataBase.getConnection();
					String reportPath = "src/jasper/invoice.jrxml";
					JasperReport jr = JasperCompileManager.compileReport(reportPath);
					HashMap invoice = new HashMap<>();
					invoice.put("id", id);
					JasperPrint jp = JasperFillManager.fillReport(jr, invoice, connection);
					JasperViewer.viewReport(jp, false);

					// // print table value from database
					// Connection connection = dataBase.getConnection();
					// String reportPath = "src/jasper/report.jrxml";
					// JasperDesign jd = JRXmlLoader.load(reportPath);
					// String sql = "select * from invoice where id ='" + id + "'";
					// JRDesignQuery report = new JRDesignQuery();
					// report.setText(sql);
					// jd.setQuery(report);
					// JasperReport jr = JasperCompileManager.compileReport(jd);
					// JasperPrint jp = JasperFillManager.fillReport(jr, null, connection);
					// JasperViewer.viewReport(jp);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPrint.setBounds(252, 0, 116, 65);
		panel_3.add(btnPrint);

		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField_id.getText();
				String patient = textField_name.getText();
				String a = (String) comboBox_name.getSelectedItem();
				String patientName = a.concat(patient);
				String age = textField_age.getText();
				String sex = (String) comboBox_sex.getSelectedItem();
				String address = textPane_address.getText();
				String city = txtCity.getText();
				String state = (String) comboBox_state.getSelectedItem();
				String mobile = textField_mobile.getText();
				String refferedBy = (String) comboBox_doctor.getSelectedItem();
				String labName = (String) comboBox_Lab.getSelectedItem();
				String totalAmount = textField_totalAmount.getText();
				String concession = textField_concession.getText();
				String otherCharges = textField_otherCharges.getText();
				String netAmount = textField_netAmount.getText();
				String receivedAmount = textField_receivedAmount.getText();
				String balance = textField_balance.getText();
				String date = textField_date.getText();

				String sql = "UPDATE invoice SET patientName ='" + patientName + "',age ='" + age + "',sex ='" + sex
						+ "',address ='" + address + "',city ='" + city + "',state ='" + state + "',mobile ='" + mobile
						+ "',refferedBy ='" + refferedBy + "',labName ='" + labName + "',totalAmount ='" + totalAmount
						+ "',concession ='" + concession + "',otherCharges ='" + otherCharges + "',netAmount ='"
						+ netAmount + "',receivedAmount ='" + receivedAmount + "',balance ='" + balance + "',date='"
						+ date + "' WHERE id ='" + id + "'";
				executeQuery.executeQuery(sql, "Inserted");
				textField_name.setEditable(false);
				textField_age.setEditable(false);
				txtCity.setEditable(false);
				textField_mobile.setEditable(false);
				textPane_address.setEditable(false);
				comboBox_sex.setEnabled(false);
				comboBox_doctor.setEnabled(false);
				comboBox_Lab.setEnabled(false);
				comboBox_state.setEnabled(false);
				comboBox_test.setEnabled(false);
				textField_balance.setEditable(false);
				textField_concession.setEditable(false);
				textField_netAmount.setEditable(false);
				textField_otherCharges.setEditable(false);
				textField_receivedAmount.setEditable(false);
				textField_totalAmount.setEditable(false);
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSave.setBounds(378, 0, 116, 65);
		panel_3.add(btnSave);

		comboBox("test", 2);
		comboBox("doctor", 2);
		comboBox("lab", 2);
		AutoCompleteDecorator.decorate(comboBox_test);
		AutoCompleteDecorator.decorate(comboBox_doctor);
	}

}
