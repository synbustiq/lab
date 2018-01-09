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

public class TestDepartment extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_testDepartmentSearch;
	private JTable table;
	private JTextField textField_id;
	private JTextField textField_departmentName;

	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnSearch;
	private JButton btnExit;

	DatabaseConnection dataBase = new DatabaseConnection();
	static TestDepartment frametestDepartment = new TestDepartment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					frametestDepartment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		table("departmentName", "testdepartment");
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

	private void close() {
		// TODO Auto-generated method stub
		frametestDepartment.setVisible(false);
		dispose();
	}

	/**
	 * Create the frame.
	 */
	public TestDepartment() {
		setTitle("Test Department");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 128, 272, 532);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTestDepartmentName = new JLabel("Test Department Name");
		lblTestDepartmentName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTestDepartmentName.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestDepartmentName.setBounds(10, 11, 252, 31);
		panel.add(lblTestDepartmentName);

		textField_testDepartmentSearch = new JTextField();
		textField_testDepartmentSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String name = textField_testDepartmentSearch.getText().trim();
				try {
					String sql = "select departmentName from testdepartment where departmentName like '" + name
							+ "%'  ";
					PreparedStatement statement = dataBase.getConnection().prepareStatement(sql);
					ResultSet set = statement.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(set));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		textField_testDepartmentSearch.setEditable(false);
		textField_testDepartmentSearch.setBounds(10, 53, 252, 31);
		panel.add(textField_testDepartmentSearch);
		textField_testDepartmentSearch.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 252, 426);
		panel.add(scrollPane);

		table = new JTable();
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
						String sql = "select * from testdepartment where departmentName = '" + name + "'";
						PreparedStatement statement = dataBase.getConnection().prepareStatement(sql);
						ResultSet set = statement.executeQuery();
						while (set.next()) {
							textField_id.setText(set.getString(1));
							textField_departmentName.setText(set.getString(2));
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		});
		table.setVisible(false);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(292, 128, 692, 532);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTestDepartment = new JLabel("Test Department");
		lblTestDepartment.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTestDepartment.setBackground(Color.WHITE);
		lblTestDepartment.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTestDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestDepartment.setBounds(0, 0, 692, 37);
		panel_1.add(lblTestDepartment);

		JLabel lblDepartmentCode = new JLabel("Department Code");
		lblDepartmentCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDepartmentCode.setBounds(38, 82, 219, 37);
		panel_1.add(lblDepartmentCode);

		JLabel lblDepartmentName = new JLabel("Department Name");
		lblDepartmentName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDepartmentName.setBounds(38, 159, 219, 37);
		panel_1.add(lblDepartmentName);

		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setBounds(260, 82, 156, 37);
		panel_1.add(textField_id);
		textField_id.setColumns(10);

		textField_departmentName = new JTextField();
		textField_departmentName.setEditable(false);
		textField_departmentName.setColumns(10);
		textField_departmentName.setBounds(260, 159, 312, 37);
		panel_1.add(textField_departmentName);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 702, 82);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_departmentName.setEditable(true);

				textField_testDepartmentSearch.setEditable(false);
				textField_testDepartmentSearch.setText(null);
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
		btnAdd.setIcon(new ImageIcon(TestDepartment.class.getResource("/image/Actions-list-add-icon.png")));
		btnAdd.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAdd.setVerticalAlignment(SwingConstants.TOP);
		btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setFocusPainted(false);
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(0, 0, 102, 82);
		panel_2.add(btnAdd);

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_departmentName.setEditable(true);

				textField_testDepartmentSearch.setEditable(false);
				textField_testDepartmentSearch.setText(null);
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
		btnEdit.setIcon(new ImageIcon(TestDepartment.class.getResource("/image/document-edit-icon.png")));
		btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEdit.setVerticalAlignment(SwingConstants.TOP);
		btnEdit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEdit.setFocusPainted(false);
		btnEdit.setBackground(Color.WHITE);
		btnEdit.setBounds(100, 0, 102, 82);
		panel_2.add(btnEdit);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField_id.getText();
				if (id.equals(null) || id.equals(" ")) {
					JOptionPane.showMessageDialog(null, "Please fill the Department Code");
				} else {
					try {
						String sql = "delete from testdepartment where id  ='" + id + "'";
						Statement statement = dataBase.getConnection().createStatement();
						statement.executeUpdate(sql);

						refreshTable();

						JOptionPane.showMessageDialog(null, "Deleted successfully");

						textField_departmentName.setEditable(false);

						textField_testDepartmentSearch.setEditable(false);
						textField_testDepartmentSearch.setText(null);
						table.setVisible(false);
						table.setEnabled(false);

						textField_id.setText(null);
						textField_departmentName.setText(null);

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
		btnDelete.setEnabled(false);
		btnDelete.setIcon(new ImageIcon(TestDepartment.class.getResource("/image/delete-icon.png")));
		btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDelete.setVerticalAlignment(SwingConstants.TOP);
		btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setFocusPainted(false);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(200, 0, 102, 82);
		panel_2.add(btnDelete);

		btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String departmentName = textField_departmentName.getText();

				String sql = "insert into testdepartment(departmentName) values('" + departmentName + "') ";
				executeQuery(sql, "Inserted");

				textField_departmentName.setEditable(false);

				textField_testDepartmentSearch.setEditable(false);
				textField_testDepartmentSearch.setText(null);
				table.setVisible(false);
				table.setEnabled(false);

				textField_departmentName.setText(null);

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
		btnSave.setIcon(new ImageIcon(TestDepartment.class.getResource("/image/Devices-media-floppy-icon.png")));
		btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSave.setVerticalAlignment(SwingConstants.TOP);
		btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setFocusPainted(false);
		btnSave.setBackground(Color.WHITE);
		btnSave.setBounds(300, 0, 102, 82);
		panel_2.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_departmentName.setEditable(false);

				textField_testDepartmentSearch.setEditable(false);
				textField_testDepartmentSearch.setText(null);
				table.setVisible(false);
				table.setEnabled(false);

				textField_id.setText(null);
				textField_departmentName.setText(null);

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
		btnCancel.setIcon(new ImageIcon(TestDepartment.class.getResource("/image/Actions-edit-undo-icon.png")));
		btnCancel.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancel.setVerticalAlignment(SwingConstants.TOP);
		btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCancel.setFocusPainted(false);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(400, 0, 102, 82);
		panel_2.add(btnCancel);

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

				textField_testDepartmentSearch.setEditable(true);
				table.setVisible(true);
				table.setEnabled(true);
			}
		});
		btnSearch.setIcon(new ImageIcon(TestDepartment.class.getResource("/image/Zoom-icon.png")));
		btnSearch.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSearch.setVerticalAlignment(SwingConstants.TOP);
		btnSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSearch.setFocusPainted(false);
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(500, 0, 102, 82);
		panel_2.add(btnSearch);

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnExit.setIcon(new ImageIcon(TestDepartment.class.getResource("/image/Actions-window-close-icon.png")));
		btnExit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExit.setVerticalAlignment(SwingConstants.TOP);
		btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setFocusPainted(false);
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(600, 0, 102, 82);
		panel_2.add(btnExit);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String departmentName = textField_departmentName.getText();
				String id = textField_id.getText();

				String sql = "update testdepartment set departmentName = '" + departmentName + "' where id ='" + id
						+ "'";
				executeQuery(sql, "Updated");

				textField_id.setEditable(false);
				textField_departmentName.setEditable(false);

				textField_testDepartmentSearch.setEditable(false);
				textField_testDepartmentSearch.setText(null);
				table.setVisible(false);
				table.setEnabled(false);

				textField_id.setText(null);
				textField_departmentName.setText(null);

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
		panel_2.add(btnUpdate);
		btnUpdate.setVisible(false);
		btnUpdate.setIcon(new ImageIcon(TestDepartment.class.getResource("/image/update-icon.png")));
		btnUpdate.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnUpdate.setVerticalAlignment(SwingConstants.TOP);
		btnUpdate.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBackground(Color.WHITE);

		table("departmentName", "testdepartment");
	}
}
