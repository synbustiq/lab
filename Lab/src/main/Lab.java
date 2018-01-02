package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import database.DatabaseConnection;
import net.proteanit.sql.DbUtils;

public class Lab extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_id;
	private JTextField textField_labName;
	private JTextField textField_department;
	private JTextField textField_doorNo;
	private JTextField textField_streetInfo;
	private JTextField textField_areaName;
	private JTextField textField_cityName;
	private JTextField textField_phoneNo;
	private JTextField textField_mobileNo;
	private JTextField textField_eMail;
	private JTextField textField_remarks;
	private JTextField textField_pincode;
	private JTextField textField_state;
	private JComboBox<String> comboBox_lab;

	DatabaseConnection dataBase = new DatabaseConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab frame = new Lab();
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
	public void executeQuery(String query, String message) {
		Connection connection = dataBase.getConnection();
		try {
			Statement statement = connection.createStatement();
			int set = statement.executeUpdate(query);
			if (set == 1) {
				refreshCombobox();
				refreshTable();
				JOptionPane.showMessageDialog(null, "Data " + message + "Successfully");
			} else {
				JOptionPane.showMessageDialog(null, "Data Not " + message + "Successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void table(String name, String tableName) {
		Connection connection = dataBase.getConnection();
		PreparedStatement statement;
		ResultSet set;
		try {
			String sql = "select " + name + " from " + tableName + "";
			statement = connection.prepareStatement(sql);
			set = statement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(set));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void refreshTable() {
		// Refresh the table data
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		// table function is called again to display refresh table
		table("labName", "lab");
	}

	public void comboBox() {

		Connection connection = dataBase.getConnection();
		PreparedStatement statement;
		ResultSet set;
		try {
			String sql = "select * from lab ";
			statement = connection.prepareStatement(sql);
			set = statement.executeQuery();
			while (set.next()) {
				comboBox_lab.addItem(set.getString(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void refreshCombobox() {
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox_lab.getModel();
		model.getIndexOf(0);

		// fillComboBox function is called again to display refresh comboBox
		// comboBox();
	}

	public Lab() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 11, 296, 639);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSearchByLab = new JLabel("Search by Lab Name");
		lblSearchByLab.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchByLab.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearchByLab.setBounds(10, 11, 276, 38);
		panel.add(lblSearchByLab);

		comboBox_lab = new JComboBox<String>();
		comboBox_lab.setModel(new DefaultComboBoxModel<String>(new String[] { " " }));
		comboBox_lab.setEditable(true);
		comboBox_lab.setEnabled(false);
		comboBox_lab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = comboBox_lab.getSelectedItem().toString();
				if (name.equals("") || name.equals(null)) {
					// display if it doesn't contain any value
				} else {
					try {
						String sql = "select * from lab where labName = '" + name + "'";
						PreparedStatement statement = dataBase.getConnection().prepareStatement(sql);
						ResultSet set = statement.executeQuery();
						while (set.next()) {
							textField_id.setText(set.getString(1));
							textField_labName.setText(set.getString(2));
							textField_department.setText(set.getString(3));
							textField_doorNo.setText(set.getString(4));
							textField_streetInfo.setText(set.getString(5));
							textField_areaName.setText(set.getString(6));
							textField_cityName.setText(set.getString(7));
							textField_pincode.setText(set.getString(8));
							textField_state.setText(set.getString(9));
							textField_phoneNo.setText(set.getString(10));
							textField_mobileNo.setText(set.getString(11));
							textField_eMail.setText(set.getString(12));
							textField_remarks.setText(set.getString(13));
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		});
		comboBox_lab.setBounds(10, 60, 276, 43);
		panel.add(comboBox_lab);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 114, 276, 514);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 256, 492);
		panel_2.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DoctorName" }));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(316, 11, 658, 639);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblLabCode = new JLabel("Lab Code");
		lblLabCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLabCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabCode.setBounds(10, 11, 145, 33);
		panel_1.add(lblLabCode);

		JLabel lblLabName_1 = new JLabel("Lab Name");
		lblLabName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabName_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLabName_1.setBounds(10, 55, 145, 33);
		panel_1.add(lblLabName_1);

		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDepartment.setBounds(10, 99, 145, 33);
		panel_1.add(lblDepartment);

		JLabel lblDoorNo = new JLabel("Door No");
		lblDoorNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoorNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDoorNo.setBounds(10, 143, 145, 33);
		panel_1.add(lblDoorNo);

		JLabel lblStreetInfo = new JLabel("Street info");
		lblStreetInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblStreetInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStreetInfo.setBounds(10, 187, 145, 33);
		panel_1.add(lblStreetInfo);

		JLabel lblAreaName = new JLabel("Area Name");
		lblAreaName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAreaName.setBounds(10, 231, 145, 33);
		panel_1.add(lblAreaName);

		JLabel lblCityName = new JLabel("City Name");
		lblCityName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCityName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCityName.setBounds(10, 275, 145, 33);
		panel_1.add(lblCityName);

		JLabel lblPincode = new JLabel("Pincode");
		lblPincode.setHorizontalAlignment(SwingConstants.CENTER);
		lblPincode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPincode.setBounds(10, 319, 145, 33);
		panel_1.add(lblPincode);

		JLabel lblPhoneNo = new JLabel("Phone No");
		lblPhoneNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhoneNo.setBounds(10, 363, 145, 33);
		panel_1.add(lblPhoneNo);

		JLabel lblMobileNo = new JLabel("Mobile No");
		lblMobileNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMobileNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMobileNo.setBounds(10, 407, 145, 33);
		panel_1.add(lblMobileNo);

		JLabel lblEmail = new JLabel(" E-Mail");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 451, 145, 33);
		panel_1.add(lblEmail);

		JLabel lblRemarks = new JLabel("Remarks");
		lblRemarks.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemarks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRemarks.setBounds(10, 495, 145, 33);
		panel_1.add(lblRemarks);

		JLabel lblState = new JLabel("State");
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblState.setBounds(353, 319, 104, 33);
		panel_1.add(lblState);

		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setBounds(165, 13, 480, 33);
		panel_1.add(textField_id);
		textField_id.setColumns(10);

		textField_labName = new JTextField();
		textField_labName.setColumns(10);
		textField_labName.setBounds(165, 57, 480, 33);
		panel_1.add(textField_labName);

		textField_department = new JTextField();
		textField_department.setColumns(10);
		textField_department.setBounds(165, 101, 480, 33);
		panel_1.add(textField_department);

		textField_doorNo = new JTextField();
		textField_doorNo.setColumns(10);
		textField_doorNo.setBounds(165, 145, 480, 33);
		panel_1.add(textField_doorNo);

		textField_streetInfo = new JTextField();
		textField_streetInfo.setColumns(10);
		textField_streetInfo.setBounds(165, 189, 480, 33);
		panel_1.add(textField_streetInfo);

		textField_areaName = new JTextField();
		textField_areaName.setColumns(10);
		textField_areaName.setBounds(165, 233, 480, 33);
		panel_1.add(textField_areaName);

		textField_cityName = new JTextField();
		textField_cityName.setColumns(10);
		textField_cityName.setBounds(165, 277, 480, 33);
		panel_1.add(textField_cityName);

		textField_phoneNo = new JTextField();
		textField_phoneNo.setColumns(10);
		textField_phoneNo.setBounds(165, 365, 480, 33);
		panel_1.add(textField_phoneNo);

		textField_mobileNo = new JTextField();
		textField_mobileNo.setColumns(10);
		textField_mobileNo.setBounds(165, 409, 480, 33);
		panel_1.add(textField_mobileNo);

		textField_eMail = new JTextField();
		textField_eMail.setColumns(10);
		textField_eMail.setBounds(165, 453, 480, 33);
		panel_1.add(textField_eMail);

		textField_remarks = new JTextField();
		textField_remarks.setColumns(10);
		textField_remarks.setBounds(165, 497, 480, 33);
		panel_1.add(textField_remarks);

		textField_pincode = new JTextField();
		textField_pincode.setColumns(10);
		textField_pincode.setBounds(165, 321, 178, 33);
		panel_1.add(textField_pincode);

		textField_state = new JTextField();
		textField_state.setColumns(10);
		textField_state.setBounds(467, 321, 178, 33);
		panel_1.add(textField_state);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String labName = textField_labName.getText();
				String department = textField_department.getText();
				String doorNo = textField_doorNo.getText();
				String streetInfo = textField_streetInfo.getText();
				String areaName = textField_areaName.getText();
				String cityName = textField_cityName.getText();
				String pincode = textField_pincode.getText();
				String state = textField_state.getText();
				String phone = textField_phoneNo.getText();
				String mobile = textField_mobileNo.getText();
				String email = textField_eMail.getText();
				String remarks = textField_remarks.getText();
				String sql = "insert into lab(labName,department,doorNo,streetInfo,areaName,cityName,pincode,state,phone,mobile,email,remarks)values ('"
						+ labName + "','" + department + "','" + doorNo + "','" + streetInfo + "','" + areaName + "','"
						+ cityName + "','" + pincode + "','" + state + "','" + phone + "','" + mobile + "','" + email
						+ "','" + remarks + "')";
				executeQuery(sql, "Inserted");
				textField_id.setText(null);
				textField_labName.setText(null);
				textField_department.setText(null);
				textField_doorNo.setText(null);
				textField_streetInfo.setText(null);
				textField_areaName.setText(null);
				textField_cityName.setText(null);
				textField_pincode.setText(null);
				textField_state.setText(null);
				textField_phoneNo.setText(null);
				textField_mobileNo.setText(null);
				textField_eMail.setText(null);
				textField_remarks.setText(null);
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSave.setBounds(522, 560, 123, 38);
		panel_1.add(btnSave);

		JButton btnSave_1 = new JButton("Save");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField_id.getText();
				String labName = textField_labName.getText();
				String department = textField_department.getText();
				String doorNo = textField_doorNo.getText();
				String streetInfo = textField_streetInfo.getText();
				String areaName = textField_areaName.getText();
				String cityName = textField_cityName.getText();
				String pincode = textField_pincode.getText();
				String state = textField_state.getText();
				String phone = textField_phoneNo.getText();
				String mobile = textField_mobileNo.getText();
				String email = textField_eMail.getText();
				String remarks = textField_remarks.getText();
				String sql = "update lab set labName='" + labName + "', department='" + department + "', doorNo='"
						+ doorNo + "', streetInfo = '" + streetInfo + "',areaName='" + areaName + "',cityName='"
						+ cityName + "',pincode='" + pincode + "',state='" + state + "',phone='" + phone + "',mobile='"
						+ mobile + "',email='" + email + "',remarks='" + remarks + "' where id='" + id + "'";
				executeQuery(sql, "Updated");
				comboBox_lab.setEnabled(false);
				btnSave.setVisible(true);
				btnSave_1.setVisible(false);
				textField_id.setText(null);
				textField_labName.setText(null);
				textField_department.setText(null);
				textField_doorNo.setText(null);
				textField_streetInfo.setText(null);
				textField_areaName.setText(null);
				textField_cityName.setText(null);
				textField_pincode.setText(null);
				textField_state.setText(null);
				textField_phoneNo.setText(null);
				textField_mobileNo.setText(null);
				textField_eMail.setText(null);
				textField_remarks.setText(null);
			}

		});
		btnSave_1.setVisible(false);
		btnSave_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSave_1.setBounds(522, 560, 123, 38);
		panel_1.add(btnSave_1);

		JButton btnUpdate = new JButton("Edit");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_lab.setEnabled(true);
				btnSave.setVisible(false);
				btnSave_1.setVisible(true);
				comboBox();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(389, 560, 123, 38);
		panel_1.add(btnUpdate);

		// comboBox();
		AutoCompleteDecorator.decorate(comboBox_lab);
		table("labName", "lab");
	}

}
