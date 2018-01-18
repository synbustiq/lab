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
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import database.DatabaseConnection;
import net.proteanit.sql.DbUtils;

public class Doctor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_id;
	private JTextField textField_doctorName;
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
	private JTextField textField_doctorSearch;

	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnSearch;
	private JButton btnExit;
	private JButton btnUpdate;

	DatabaseConnection dataBase = new DatabaseConnection();
	static Doctor frameDoctor = new Doctor();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameDoctor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

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
		table("doctorName", "doctor");
	}

	public void executeQuery(String query, String message) {
		Connection connection = dataBase.getConnection();
		try {
			Statement statement = connection.createStatement();
			int set = statement.executeUpdate(query);
			if (set == 1) {
				JOptionPane.showMessageDialog(null, "Data " + message + "Successfully");
				refreshTable();
			} else {
				JOptionPane.showMessageDialog(null, "Data Not " + message + "Successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void close() {
		frameDoctor.setVisible(false);
		dispose();
	}

	public Doctor() {
		setTitle("Doctor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 104, 296, 546);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSearchByDoctor = new JLabel("Search by Doctor Name");
		lblSearchByDoctor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchByDoctor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearchByDoctor.setBounds(10, 11, 276, 38);
		panel.add(lblSearchByDoctor);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 94, 276, 441);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 256, 419);
		panel_2.add(scrollPane);

		table = new JTable();
		table.setVisible(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Get a selected row value from table
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int rowIndex = table.getSelectedRow();
				String name = (String) model.getValueAt(rowIndex, 0);
				if (name.equals(" ") || name.equals(null)) {
					// display if it doesn't contain any value
				} else {
					try {
						String sql = "select * from doctor where doctorName = '" + name + "'";
						PreparedStatement statement = dataBase.getConnection().prepareStatement(sql);
						ResultSet set = statement.executeQuery();
						while (set.next()) {
							textField_id.setText(set.getString(1));
							textField_doctorName.setText(set.getString(2));
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
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DoctorName" }));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textField_doctorSearch = new JTextField();
		textField_doctorSearch.setEditable(false);
		textField_doctorSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String name = textField_doctorSearch.getText().trim();
				try {
					String sql = "select doctorName from doctor where doctorName like '" + name + "%'  ";
					PreparedStatement statement = dataBase.getConnection().prepareStatement(sql);
					ResultSet set = statement.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(set));
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		textField_doctorSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_doctorSearch.setBounds(10, 51, 276, 32);
		panel.add(textField_doctorSearch);
		textField_doctorSearch.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(316, 104, 658, 546);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblDoctorCode = new JLabel("Doctor Code");
		lblDoctorCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDoctorCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctorCode.setBounds(10, 11, 145, 33);
		panel_1.add(lblDoctorCode);

		JLabel lblDoctorName_1 = new JLabel("Doctor Name");
		lblDoctorName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctorName_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDoctorName_1.setBounds(10, 55, 145, 33);
		panel_1.add(lblDoctorName_1);

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

		textField_doctorName = new JTextField();
		textField_doctorName.setEditable(false);
		textField_doctorName.setColumns(10);
		textField_doctorName.setBounds(165, 57, 480, 33);
		panel_1.add(textField_doctorName);

		textField_department = new JTextField();
		textField_department.setEditable(false);
		textField_department.setColumns(10);
		textField_department.setBounds(165, 101, 480, 33);
		panel_1.add(textField_department);

		textField_doorNo = new JTextField();
		textField_doorNo.setEditable(false);
		textField_doorNo.setColumns(10);
		textField_doorNo.setBounds(165, 145, 480, 33);
		panel_1.add(textField_doorNo);

		textField_streetInfo = new JTextField();
		textField_streetInfo.setEditable(false);
		textField_streetInfo.setColumns(10);
		textField_streetInfo.setBounds(165, 189, 480, 33);
		panel_1.add(textField_streetInfo);

		textField_areaName = new JTextField();
		textField_areaName.setEditable(false);
		textField_areaName.setColumns(10);
		textField_areaName.setBounds(165, 233, 480, 33);
		panel_1.add(textField_areaName);

		textField_cityName = new JTextField();
		textField_cityName.setEditable(false);
		textField_cityName.setColumns(10);
		textField_cityName.setBounds(165, 277, 480, 33);
		panel_1.add(textField_cityName);

		textField_phoneNo = new JTextField();
		textField_phoneNo.setEditable(false);
		textField_phoneNo.setColumns(10);
		textField_phoneNo.setBounds(165, 365, 480, 33);
		panel_1.add(textField_phoneNo);

		textField_mobileNo = new JTextField();
		textField_mobileNo.setEditable(false);
		textField_mobileNo.setColumns(10);
		textField_mobileNo.setBounds(165, 409, 480, 33);
		panel_1.add(textField_mobileNo);

		textField_eMail = new JTextField();
		textField_eMail.setEditable(false);
		textField_eMail.setColumns(10);
		textField_eMail.setBounds(165, 453, 480, 33);
		panel_1.add(textField_eMail);

		textField_remarks = new JTextField();
		textField_remarks.setEditable(false);
		textField_remarks.setColumns(10);
		textField_remarks.setBounds(165, 497, 480, 33);
		panel_1.add(textField_remarks);

		textField_pincode = new JTextField();
		textField_pincode.setEditable(false);
		textField_pincode.setColumns(10);
		textField_pincode.setBounds(165, 321, 178, 33);
		panel_1.add(textField_pincode);

		textField_state = new JTextField();
		textField_state.setEditable(false);
		textField_state.setColumns(10);
		textField_state.setBounds(467, 321, 178, 33);
		panel_1.add(textField_state);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 11, 702, 82);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		btnAdd = new JButton("Add");
		btnAdd.setFocusPainted(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_doctorName.setEditable(true);
				textField_department.setEditable(true);
				textField_doorNo.setEditable(true);
				textField_streetInfo.setEditable(true);
				textField_areaName.setEditable(true);
				textField_cityName.setEditable(true);
				textField_pincode.setEditable(true);
				textField_state.setEditable(true);
				textField_phoneNo.setEditable(true);
				textField_mobileNo.setEditable(true);
				textField_eMail.setEditable(true);
				textField_remarks.setEditable(true);

				textField_doctorSearch.setEditable(false);
				textField_doctorSearch.setText(null);
				table.setVisible(false);
				table.setEnabled(false);

				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setVisible(true);
				btnSave.setEnabled(true);
				btnUpdate.setVisible(false);
				btnSearch.setEnabled(false);
				btnExit.setEnabled(false);
			}
		});
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAdd.setVerticalAlignment(SwingConstants.TOP);
		btnAdd.setIcon(new ImageIcon(Doctor.class.getResource("/image/Actions-list-add-icon.png")));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBounds(0, 0, 102, 82);
		panel_3.add(btnAdd);

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textField_doctorName.setEditable(true);
				textField_department.setEditable(true);
				textField_doorNo.setEditable(true);
				textField_streetInfo.setEditable(true);
				textField_areaName.setEditable(true);
				textField_cityName.setEditable(true);
				textField_pincode.setEditable(true);
				textField_state.setEditable(true);
				textField_phoneNo.setEditable(true);
				textField_mobileNo.setEditable(true);
				textField_eMail.setEditable(true);
				textField_remarks.setEditable(true);

				textField_doctorSearch.setEditable(false);
				textField_doctorSearch.setText(null);
				table.setVisible(false);
				table.setEnabled(false);

				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setVisible(false);
				btnSave.setEnabled(false);
				btnUpdate.setVisible(true);
				btnSearch.setEnabled(false);
				btnExit.setEnabled(false);
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setFocusPainted(false);
		btnEdit.setIcon(new ImageIcon(Doctor.class.getResource("/image/document-edit-icon.png")));
		btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEdit.setVerticalAlignment(SwingConstants.TOP);
		btnEdit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEdit.setBackground(Color.WHITE);
		btnEdit.setBounds(100, 0, 102, 82);
		panel_3.add(btnEdit);

		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField_id.getText();
				if (id == null) {
					JOptionPane.showMessageDialog(null, "Please fill the Doctor Code");
				} else {
					try {
						String sql = "delete from doctor where id  ='" + id + "'";
						Statement statement = dataBase.getConnection().createStatement();
						statement.executeUpdate(sql);

						refreshTable();

						JOptionPane.showMessageDialog(null, "Deleted successfully");

						textField_doctorName.setEditable(false);
						textField_department.setEditable(false);
						textField_doorNo.setEditable(false);
						textField_streetInfo.setEditable(false);
						textField_areaName.setEditable(false);
						textField_cityName.setEditable(false);
						textField_pincode.setEditable(false);
						textField_state.setEditable(false);
						textField_phoneNo.setEditable(false);
						textField_mobileNo.setEditable(false);
						textField_eMail.setEditable(false);
						textField_remarks.setEditable(false);

						textField_doctorSearch.setEditable(false);
						textField_doctorSearch.setText(null);
						table.setVisible(false);
						table.setEnabled(false);

						textField_id.setText(null);
						textField_doctorName.setText(null);
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

						btnAdd.setEnabled(true);
						btnEdit.setEnabled(false);
						btnDelete.setEnabled(false);
						btnSave.setVisible(true);
						btnSave.setEnabled(false);
						btnUpdate.setVisible(false);
						btnSearch.setEnabled(true);
						btnExit.setEnabled(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btnDelete.setFocusPainted(false);
		btnDelete.setIcon(new ImageIcon(Doctor.class.getResource("/image/delete-icon.png")));
		btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDelete.setVerticalAlignment(SwingConstants.TOP);
		btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(200, 0, 102, 82);
		panel_3.add(btnDelete);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textField_doctorName.setEditable(false);
				textField_department.setEditable(false);
				textField_doorNo.setEditable(false);
				textField_streetInfo.setEditable(false);
				textField_areaName.setEditable(false);
				textField_cityName.setEditable(false);
				textField_pincode.setEditable(false);
				textField_state.setEditable(false);
				textField_phoneNo.setEditable(false);
				textField_mobileNo.setEditable(false);
				textField_eMail.setEditable(false);
				textField_remarks.setEditable(false);

				textField_doctorSearch.setEditable(false);
				textField_doctorSearch.setText(null);
				table.setVisible(false);
				table.setEnabled(false);

				textField_id.setText(null);
				textField_doctorName.setText(null);
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

				btnAdd.setEnabled(true);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setVisible(true);
				btnSave.setEnabled(false);
				btnSearch.setEnabled(true);
				btnUpdate.setVisible(false);
				btnExit.setEnabled(true);
			}
		});
		btnCancel.setFocusPainted(false);
		btnCancel.setIcon(new ImageIcon(Doctor.class.getResource("/image/Actions-edit-undo-icon.png")));
		btnCancel.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancel.setVerticalAlignment(SwingConstants.TOP);
		btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(400, 0, 102, 82);
		panel_3.add(btnCancel);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnAdd.setEnabled(false);
				btnEdit.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setVisible(true);
				btnSave.setEnabled(false);
				btnSearch.setEnabled(false);
				btnUpdate.setVisible(false);
				btnExit.setEnabled(true);

				textField_doctorSearch.setEditable(true);
				table.setVisible(true);
				table.setEnabled(true);
			}
		});
		btnSearch.setFocusPainted(false);
		btnSearch.setIcon(new ImageIcon(Doctor.class.getResource("/image/Zoom-icon.png")));
		btnSearch.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSearch.setVerticalAlignment(SwingConstants.TOP);
		btnSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(500, 0, 102, 82);
		panel_3.add(btnSearch);

		btnExit = new JButton("Exit");
		btnExit.setFocusPainted(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnExit.setIcon(new ImageIcon(Doctor.class.getResource("/image/Actions-window-close-icon.png")));
		btnExit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExit.setVerticalAlignment(SwingConstants.TOP);
		btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(600, 0, 102, 82);
		panel_3.add(btnExit);

		btnSave = new JButton("Save");
		btnSave.setBounds(300, 0, 102, 82);
		panel_3.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String doctorName = textField_doctorName.getText();
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

				String sql = "insert into doctor(doctorName,department,doorNo,streetInfo,areaName,cityName,pincode,state,phone,mobile,email,remarks)values ('"
						+ doctorName + "','" + department + "','" + doorNo + "','" + streetInfo + "','" + areaName
						+ "','" + cityName + "','" + pincode + "','" + state + "','" + phone + "','" + mobile + "','"
						+ email + "','" + remarks + "')";
				executeQuery(sql, "Inserted");

				textField_doctorName.setEditable(false);
				textField_department.setEditable(false);
				textField_doorNo.setEditable(false);
				textField_streetInfo.setEditable(false);
				textField_areaName.setEditable(false);
				textField_cityName.setEditable(false);
				textField_pincode.setEditable(false);
				textField_state.setEditable(false);
				textField_phoneNo.setEditable(false);
				textField_mobileNo.setEditable(false);
				textField_eMail.setEditable(false);
				textField_remarks.setEditable(false);

				textField_doctorSearch.setEditable(false);
				textField_doctorSearch.setText(null);
				table.setVisible(false);
				table.setEnabled(false);

				textField_doctorName.setText(null);
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

				btnAdd.setEnabled(true);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setVisible(true);
				btnSave.setEnabled(false);
				btnUpdate.setVisible(false);
				btnSearch.setEnabled(true);
				btnExit.setEnabled(true);
			}
		});
		btnSave.setEnabled(false);
		btnSave.setFocusPainted(false);
		btnSave.setIcon(new ImageIcon(Doctor.class.getResource("/image/Devices-media-floppy-icon.png")));
		btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSave.setVerticalAlignment(SwingConstants.TOP);
		btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setBackground(Color.WHITE);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = textField_id.getText();
				String doctorName = textField_doctorName.getText();
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

				String sql = "update doctor set doctorName='" + doctorName + "', department='" + department
						+ "', doorNo='" + doorNo + "', streetInfo = '" + streetInfo + "',areaName='" + areaName
						+ "',cityName='" + cityName + "',pincode='" + pincode + "',state='" + state + "',phone='"
						+ phone + "',mobile='" + mobile + "',email='" + email + "',remarks='" + remarks + "' where id='"
						+ id + "'";
				executeQuery(sql, "Updated");

				textField_id.setEditable(false);
				textField_doctorName.setEditable(false);
				textField_department.setEditable(false);
				textField_doorNo.setEditable(false);
				textField_streetInfo.setEditable(false);
				textField_areaName.setEditable(false);
				textField_cityName.setEditable(false);
				textField_pincode.setEditable(false);
				textField_state.setEditable(false);
				textField_phoneNo.setEditable(false);
				textField_mobileNo.setEditable(false);
				textField_eMail.setEditable(false);
				textField_remarks.setEditable(false);

				textField_doctorSearch.setEditable(false);
				textField_doctorSearch.setText(null);
				table.setVisible(false);
				table.setEnabled(false);

				textField_id.setText(null);
				textField_doctorName.setText(null);
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

				btnAdd.setEnabled(true);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setVisible(true);
				btnSave.setEnabled(false);
				btnUpdate.setVisible(false);
				btnSearch.setEnabled(true);
				btnExit.setEnabled(true);
			}
		});
		btnUpdate.setBounds(300, 0, 102, 82);
		panel_3.add(btnUpdate);
		btnUpdate.setVisible(false);
		btnUpdate.setIcon(new ImageIcon(Doctor.class.getResource("/image/update-icon.png")));
		btnUpdate.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnUpdate.setVerticalAlignment(SwingConstants.TOP);
		btnUpdate.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBackground(Color.WHITE);

		table("doctorName", "doctor");
	}
}
