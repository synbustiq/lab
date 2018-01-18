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
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import database.DatabaseConnection;
import net.proteanit.sql.DbUtils;

public class TestMaster extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_testName;
	private JTextField textField_rate1;
	private JTextField textField_units;
	private JTextField textField_digits;
	private JTextField textField_decimal;
	private JTextField textField_defaultValue;
	private JTextField textField_rangeLower;
	private JTextField textField_rangeUpper;
	private JTextField textField_formula;
	private JTextField textField_validation;
	private JTextField textField_tblCaption;
	private JTextField textField_propertyName;
	private JTextField textField_propertyType;
	private JTable table;
	private JCheckBox chckbxIsSubHead;
	private JCheckBox chckbxIsPicklist;
	private JCheckBox chckbxIsCulture;
	private JCheckBox chckbxIsSpecial;
	private JEditorPane editorPane_normalValue;
	private JTextField textField_rate2;
	private JTable table_1;
	private JTextField textField_testNameSearch;

	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnSearch;
	private JButton btnExit;
	private JButton btnUpdate;

	private JTextField textField_Department;
	private JTextField textField_Sample;
	private JTextField textField_TypeofTest;
	private JTextField textField_Method;
	private JButton btnDepartment;
	private JButton btnSample;
	private JButton btnTypeofTest;
	private JButton btnMethod;

	DatabaseConnection dataBase = new DatabaseConnection();
	static TestMaster frameTestMaster = new TestMaster();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameTestMaster.setVisible(true);
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
		table("testName", "test");
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
		frameTestMaster.setVisible(false);
		dispose();
	}

	/**
	 * Create the frame.
	 */
	public TestMaster() {
		setTitle("Test Master");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 104, 286, 646);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSearchByTest = new JLabel("Search by Test Name");
		lblSearchByTest.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearchByTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchByTest.setBounds(10, 11, 266, 28);
		panel.add(lblSearchByTest);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 266, 546);
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
						String sql = "select * from test where testName = '" + name + "'";
						PreparedStatement statement = dataBase.getConnection().prepareStatement(sql);
						ResultSet set = statement.executeQuery();
						while (set.next()) {
							textField_id.setText(set.getString(1));
							textField_testName.setText(set.getString(2));
							textField_rate1.setText(set.getString(3));
							textField_rate2.setText(set.getString(4));
							textField_Department.setText(set.getString(5));
							textField_Sample.setText(set.getString(6));
							textField_TypeofTest.setText(set.getString(7));
							textField_Method.setText(set.getString(8));
							chckbxIsSubHead.setSelected(set.getBoolean(9));
							textField_units.setText(set.getString(10));
							textField_digits.setText(set.getString(11));
							textField_decimal.setText(set.getString(12));
							textField_defaultValue.setText(set.getString(13));
							textField_rangeLower.setText(set.getString(14));
							textField_rangeUpper.setText(set.getString(15));
							chckbxIsPicklist.setSelected(set.getBoolean(16));
							textField_formula.setText(set.getString(17));
							editorPane_normalValue.setText(set.getString(18));
							chckbxIsSpecial.setSelected(set.getBoolean(19));
							chckbxIsCulture.setSelected(set.getBoolean(20));
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		});
		table.setVisible(false);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Test Name" }));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(table);

		textField_testNameSearch = new JTextField();
		textField_testNameSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String name = textField_testNameSearch.getText().trim();
				try {
					String sql = "select testName from test where testName like '" + name + "%'  ";
					PreparedStatement statement = dataBase.getConnection().prepareStatement(sql);
					ResultSet set = statement.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(set));

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		textField_testNameSearch.setEditable(false);
		textField_testNameSearch.setBounds(10, 50, 266, 28);
		panel.add(textField_testNameSearch);
		textField_testNameSearch.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(306, 104, 868, 646);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 145, 436, 315);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblUnits = new JLabel("Units");
		lblUnits.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnits.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUnits.setBounds(0, 11, 45, 32);
		panel_2.add(lblUnits);

		textField_units = new JTextField();
		textField_units.setEditable(false);
		textField_units.setColumns(10);
		textField_units.setBounds(55, 13, 68, 32);
		panel_2.add(textField_units);

		JLabel lblDigits = new JLabel("Digits");
		lblDigits.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDigits.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDigits.setBounds(133, 11, 34, 32);
		panel_2.add(lblDigits);

		textField_digits = new JTextField();
		textField_digits.setEditable(false);
		textField_digits.setColumns(10);
		textField_digits.setBounds(177, 13, 68, 32);
		panel_2.add(textField_digits);

		JLabel lblDecimals = new JLabel("Decimals");
		lblDecimals.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDecimals.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDecimals.setBounds(259, 11, 62, 32);
		panel_2.add(lblDecimals);

		textField_decimal = new JTextField();
		textField_decimal.setEditable(false);
		textField_decimal.setColumns(10);
		textField_decimal.setBounds(331, 13, 77, 32);
		panel_2.add(textField_decimal);

		JLabel lblDefaultValue = new JLabel("Default Value");
		lblDefaultValue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDefaultValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDefaultValue.setBounds(10, 54, 94, 32);
		panel_2.add(lblDefaultValue);

		textField_defaultValue = new JTextField();
		textField_defaultValue.setEditable(false);
		textField_defaultValue.setColumns(10);
		textField_defaultValue.setBounds(123, 56, 303, 32);
		panel_2.add(textField_defaultValue);

		JLabel lblRangenormal = new JLabel("Range(Normal)");
		lblRangenormal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRangenormal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRangenormal.setBounds(0, 97, 107, 32);
		panel_2.add(lblRangenormal);

		JLabel lblLower = new JLabel("Lower");
		lblLower.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLower.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLower.setBounds(117, 97, 45, 32);
		panel_2.add(lblLower);

		JLabel lblUpper = new JLabel("Upper");
		lblUpper.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUpper.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUpper.setBounds(276, 97, 45, 32);
		panel_2.add(lblUpper);

		textField_rangeLower = new JTextField();
		textField_rangeLower.setEditable(false);
		textField_rangeLower.setColumns(10);
		textField_rangeLower.setBounds(177, 99, 84, 32);
		panel_2.add(textField_rangeLower);

		textField_rangeUpper = new JTextField();
		textField_rangeUpper.setEditable(false);
		textField_rangeUpper.setColumns(10);
		textField_rangeUpper.setBounds(341, 99, 85, 32);
		panel_2.add(textField_rangeUpper);

		JLabel lblFormula = new JLabel("Formula");
		lblFormula.setHorizontalAlignment(SwingConstants.LEFT);
		lblFormula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFormula.setBounds(10, 140, 62, 32);
		panel_2.add(lblFormula);

		JLabel lblValidation = new JLabel("Validation");
		lblValidation.setHorizontalAlignment(SwingConstants.LEFT);
		lblValidation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblValidation.setBounds(10, 183, 62, 32);
		panel_2.add(lblValidation);

		JLabel lblTblCaption = new JLabel("Tbl Caption");
		lblTblCaption.setHorizontalAlignment(SwingConstants.LEFT);
		lblTblCaption.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTblCaption.setBounds(10, 225, 101, 32);
		panel_2.add(lblTblCaption);

		textField_formula = new JTextField();
		textField_formula.setEditable(false);
		textField_formula.setColumns(10);
		textField_formula.setBounds(117, 142, 309, 32);
		panel_2.add(textField_formula);

		textField_validation = new JTextField();
		textField_validation.setEditable(false);
		textField_validation.setColumns(10);
		textField_validation.setBounds(117, 185, 309, 32);
		panel_2.add(textField_validation);

		textField_tblCaption = new JTextField();
		textField_tblCaption.setEditable(false);
		textField_tblCaption.setColumns(10);
		textField_tblCaption.setBounds(117, 228, 309, 30);
		panel_2.add(textField_tblCaption);

		chckbxIsPicklist = new JCheckBox("Is PickList");
		chckbxIsPicklist.setEnabled(false);
		chckbxIsPicklist.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxIsPicklist.setBounds(22, 276, 101, 32);
		panel_2.add(chckbxIsPicklist);

		chckbxIsCulture = new JCheckBox("Is Culture");
		chckbxIsCulture.setEnabled(false);
		chckbxIsCulture.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxIsCulture.setBounds(133, 276, 101, 32);
		panel_2.add(chckbxIsCulture);

		chckbxIsSpecial = new JCheckBox("Is Special");
		chckbxIsSpecial.setEnabled(false);
		chckbxIsSpecial.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxIsSpecial.setBounds(239, 276, 94, 32);
		panel_2.add(chckbxIsSpecial);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, Color.BLACK, null));
		panel_3.setBounds(456, 236, 401, 224);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		editorPane_normalValue = new JEditorPane();
		editorPane_normalValue.setEditable(false);
		editorPane_normalValue.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		editorPane_normalValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		editorPane_normalValue.setBounds(10, 30, 381, 183);
		panel_3.add(editorPane_normalValue);

		JLabel lblNormalValue = new JLabel("NORMAL VALUE");
		lblNormalValue.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNormalValue.setBounds(10, 0, 162, 25);
		panel_3.add(lblNormalValue);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(456, 103, 401, 124);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblPropertyName = new JLabel("Property Name");
		lblPropertyName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPropertyName.setBounds(10, 40, 130, 30);
		panel_4.add(lblPropertyName);

		JLabel lblPropertyType = new JLabel("Property Type");
		lblPropertyType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPropertyType.setBounds(10, 81, 130, 30);
		panel_4.add(lblPropertyType);

		textField_propertyName = new JTextField();
		textField_propertyName.setEditable(false);
		textField_propertyName.setBounds(161, 42, 229, 30);
		panel_4.add(textField_propertyName);
		textField_propertyName.setColumns(10);

		textField_propertyType = new JTextField();
		textField_propertyType.setEditable(false);
		textField_propertyType.setColumns(10);
		textField_propertyType.setBounds(161, 83, 229, 30);
		panel_4.add(textField_propertyType);

		chckbxIsSubHead = new JCheckBox("Is Sub Head");
		chckbxIsSubHead.setEnabled(false);
		chckbxIsSubHead.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxIsSubHead.setBounds(7, 7, 160, 32);
		panel_4.add(chckbxIsSubHead);

		JLabel lblTestCode = new JLabel("Test Code");
		lblTestCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTestCode.setBounds(10, 11, 76, 32);
		panel_1.add(lblTestCode);

		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setBounds(96, 11, 103, 32);
		panel_1.add(textField_id);
		textField_id.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(209, 11, 44, 32);
		panel_1.add(lblName);

		textField_testName = new JTextField();
		textField_testName.setEditable(false);
		textField_testName.setColumns(10);
		textField_testName.setBounds(263, 13, 275, 32);
		panel_1.add(textField_testName);

		JLabel lblTypeOfTest = new JLabel("Type of Test");
		lblTypeOfTest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTypeOfTest.setBounds(555, 59, 87, 32);
		panel_1.add(lblTypeOfTest);

		JLabel lblRate_1 = new JLabel("Rate 1");
		lblRate_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRate_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRate_1.setBounds(558, 11, 44, 32);
		panel_1.add(lblRate_1);

		textField_rate1 = new JTextField();
		textField_rate1.setEditable(false);
		textField_rate1.setColumns(10);
		textField_rate1.setBounds(612, 13, 87, 32);
		panel_1.add(textField_rate1);

		JLabel lblDepart = new JLabel("Depart");
		lblDepart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDepart.setBounds(10, 59, 64, 32);
		panel_1.add(lblDepart);

		JLabel lblMethod = new JLabel("Method");
		lblMethod.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMethod.setBounds(10, 102, 76, 32);
		panel_1.add(lblMethod);

		JLabel lblSample = new JLabel("Sample");
		lblSample.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSample.setBounds(290, 59, 59, 32);
		panel_1.add(lblSample);

		JLabel lblRate_2 = new JLabel("Rate 2");
		lblRate_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRate_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRate_2.setBounds(716, 11, 44, 32);
		panel_1.add(lblRate_2);

		textField_rate2 = new JTextField();
		textField_rate2.setEditable(false);
		textField_rate2.setColumns(10);
		textField_rate2.setBounds(770, 13, 87, 32);
		panel_1.add(textField_rate2);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 468, 847, 167);
		panel_1.add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		btnDepartment = new JButton("Click");
		btnDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDepartment.setEnabled(false);
		btnDepartment.setFocusPainted(false);
		btnDepartment.setBounds(219, 61, 62, 32);
		panel_1.add(btnDepartment);

		btnSample = new JButton("Click");
		btnSample.setEnabled(false);
		btnSample.setFocusPainted(false);
		btnSample.setBounds(483, 60, 62, 32);
		panel_1.add(btnSample);

		btnTypeofTest = new JButton("Click");
		btnTypeofTest.setEnabled(false);
		btnTypeofTest.setFocusPainted(false);
		btnTypeofTest.setBounds(795, 59, 62, 32);
		panel_1.add(btnTypeofTest);

		btnMethod = new JButton("Click");
		btnMethod.setEnabled(false);
		btnMethod.setFocusPainted(false);
		btnMethod.setBounds(229, 103, 62, 32);
		panel_1.add(btnMethod);

		textField_Department = new JTextField();
		textField_Department.setEditable(false);
		textField_Department.setBounds(67, 61, 213, 32);
		panel_1.add(textField_Department);
		textField_Department.setColumns(10);

		textField_Sample = new JTextField();
		textField_Sample.setEditable(false);
		textField_Sample.setColumns(10);
		textField_Sample.setBounds(352, 60, 193, 32);
		panel_1.add(textField_Sample);

		textField_TypeofTest = new JTextField();
		textField_TypeofTest.setEditable(false);
		textField_TypeofTest.setColumns(10);
		textField_TypeofTest.setBounds(652, 59, 205, 32);
		panel_1.add(textField_TypeofTest);

		textField_Method = new JTextField();
		textField_Method.setEditable(false);
		textField_Method.setColumns(10);
		textField_Method.setBounds(77, 103, 213, 32);
		panel_1.add(textField_Method);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 11, 702, 82);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_testName.setEditable(true);
				textField_rate1.setEditable(true);
				textField_rate2.setEditable(true);
				textField_Department.setEditable(true);
				textField_Sample.setEditable(true);
				textField_TypeofTest.setEditable(true);
				textField_Method.setEditable(true);
				btnDepartment.setEnabled(true);
				btnSample.setEnabled(true);
				btnTypeofTest.setEnabled(true);
				btnMethod.setEnabled(true);
				textField_units.setEditable(true);
				textField_digits.setEditable(true);
				textField_decimal.setEditable(true);
				textField_defaultValue.setEditable(true);
				textField_rangeLower.setEditable(true);
				textField_rangeUpper.setEditable(true);
				textField_formula.setEditable(true);
				textField_validation.setEditable(true);
				textField_tblCaption.setEditable(true);
				chckbxIsPicklist.setEnabled(true);
				chckbxIsCulture.setEnabled(true);
				chckbxIsSpecial.setEnabled(true);
				chckbxIsSubHead.setEnabled(true);
				textField_propertyName.setEditable(true);
				textField_propertyType.setEditable(true);
				editorPane_normalValue.setEditable(true);

				textField_testNameSearch.setEditable(false);
				textField_testNameSearch.setText(null);
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
		btnAdd.setIcon(new ImageIcon(TestMaster.class.getResource("/image/Actions-list-add-icon.png")));
		btnAdd.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAdd.setVerticalAlignment(SwingConstants.TOP);
		btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setFocusPainted(false);
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(0, 0, 102, 82);
		panel_5.add(btnAdd);

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_testName.setEditable(true);
				textField_rate1.setEditable(true);
				textField_rate2.setEditable(true);
				textField_Department.setEditable(true);
				textField_Sample.setEditable(true);
				textField_TypeofTest.setEditable(true);
				textField_Method.setEditable(true);
				btnDepartment.setEnabled(true);
				btnSample.setEnabled(true);
				btnTypeofTest.setEnabled(true);
				btnMethod.setEnabled(true);
				textField_units.setEditable(true);
				textField_digits.setEditable(true);
				textField_decimal.setEditable(true);
				textField_defaultValue.setEditable(true);
				textField_rangeLower.setEditable(true);
				textField_rangeUpper.setEditable(true);
				textField_formula.setEditable(true);
				textField_validation.setEditable(true);
				textField_tblCaption.setEditable(true);
				chckbxIsPicklist.setEnabled(true);
				chckbxIsCulture.setEnabled(true);
				chckbxIsSpecial.setEnabled(true);
				chckbxIsSubHead.setEnabled(true);
				textField_propertyName.setEditable(true);
				textField_propertyType.setEditable(true);
				editorPane_normalValue.setEditable(true);

				textField_testNameSearch.setEditable(false);
				textField_testNameSearch.setText(null);
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
		btnEdit.setIcon(new ImageIcon(TestMaster.class.getResource("/image/document-edit-icon.png")));
		btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEdit.setVerticalAlignment(SwingConstants.TOP);
		btnEdit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEdit.setFocusPainted(false);
		btnEdit.setBackground(Color.WHITE);
		btnEdit.setBounds(100, 0, 102, 82);
		panel_5.add(btnEdit);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField_id.getText();
				if (id == null) {
					JOptionPane.showMessageDialog(null, "Please fill the Doctor Code");
				} else {
					try {
						String sql = "delete from test where id  ='" + id + "'";
						Statement statement = dataBase.getConnection().createStatement();
						statement.executeUpdate(sql);

						refreshTable();

						JOptionPane.showMessageDialog(null, "Deleted successfully");

						textField_testName.setEditable(false);
						textField_rate1.setEditable(false);
						textField_rate2.setEditable(false);
						textField_Department.setEditable(false);
						textField_Sample.setEditable(false);
						textField_TypeofTest.setEditable(false);
						textField_Method.setEditable(false);
						btnDepartment.setEnabled(false);
						btnSample.setEnabled(false);
						btnTypeofTest.setEnabled(false);
						btnMethod.setEnabled(false);
						textField_units.setEditable(false);
						textField_digits.setEditable(false);
						textField_decimal.setEditable(false);
						textField_defaultValue.setEditable(false);
						textField_rangeLower.setEditable(false);
						textField_rangeUpper.setEditable(false);
						textField_formula.setEditable(false);
						textField_validation.setEditable(false);
						textField_tblCaption.setEditable(false);
						chckbxIsPicklist.setEnabled(false);
						chckbxIsCulture.setEnabled(false);
						chckbxIsSpecial.setEnabled(false);
						chckbxIsSubHead.setEnabled(false);
						textField_propertyName.setEditable(false);
						textField_propertyType.setEditable(false);
						editorPane_normalValue.setEditable(false);

						textField_testNameSearch.setEditable(false);
						textField_testNameSearch.setText(null);
						table.setVisible(false);
						table.setEnabled(false);

						textField_id.setText(null);
						textField_testName.setText(null);
						textField_rate1.setText(null);
						textField_rate2.setText(null);
						textField_Department.setText(null);
						textField_Sample.setText(null);
						textField_TypeofTest.setText(null);
						textField_Method.setText(null);
						textField_units.setText(null);
						textField_digits.setText(null);
						textField_decimal.setText(null);
						textField_defaultValue.setText(null);
						textField_rangeLower.setText(null);
						textField_rangeUpper.setText(null);
						textField_formula.setText(null);
						textField_validation.setText(null);
						textField_tblCaption.setText(null);
						chckbxIsPicklist.setSelected(false);
						chckbxIsCulture.setSelected(false);
						chckbxIsSpecial.setSelected(false);
						chckbxIsSubHead.setSelected(false);
						textField_propertyName.setText(null);
						textField_propertyType.setText(null);
						editorPane_normalValue.setText(null);

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
		btnDelete.setIcon(new ImageIcon(TestMaster.class.getResource("/image/delete-icon.png")));
		btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDelete.setVerticalAlignment(SwingConstants.TOP);
		btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setFocusPainted(false);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(200, 0, 102, 82);
		panel_5.add(btnDelete);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String testName = textField_testName.getText();
				// String rate1 = textField_rate1.getText();
				// String rate2 = textField_rate2.getText();
				// String department = comboBox_Department.getName();
				// String sample = comboBox_Sample.getName();
				// String typeoftest = comboBox_TypeOfTest.getName();
				// String method = comboBox_Method.getName();
				// String unit = textField_units.getText();
				// String digit = textField_digits.getText();
				// String decimal = textField_decimal.getText();
				// String defaultValue = textField_defaultValue.getText();
				// String rangeLower = textField_rangeLower.getText();
				// String rangeUpper = textField_rangeUpper.getText();
				// String formula = textField_formula.getText();
				// String validation = textField_validation.getText();
				// String tblCaption = textField_tblCaption.getText();
				// String isPicklist = chckbxIsPicklist.getActionCommand();
				// String isCulture = chckbxIsCulture.getActionCommand();
				// String isSpecial = chckbxIsSpecial.getActionCommand();
				// String isSubHead = chckbxIsSubHead.getActionCommand();
				// String propertyName = textField_propertyName.getText();
				// String propertyType = textField_propertyType.getText();
				// String normalValue = editorPane_normalValue.getText();

				String sql = "";
				executeQuery(sql, "Inserted");

				textField_testName.setEditable(false);
				textField_rate1.setEditable(false);
				textField_rate2.setEditable(false);
				textField_Department.setEditable(false);
				textField_Sample.setEditable(false);
				textField_TypeofTest.setEditable(false);
				textField_Method.setEditable(false);
				btnDepartment.setEnabled(false);
				btnSample.setEnabled(false);
				btnTypeofTest.setEnabled(false);
				btnMethod.setEnabled(false);
				textField_units.setEditable(false);
				textField_digits.setEditable(false);
				textField_decimal.setEditable(false);
				textField_defaultValue.setEditable(false);
				textField_rangeLower.setEditable(false);
				textField_rangeUpper.setEditable(false);
				textField_formula.setEditable(false);
				textField_validation.setEditable(false);
				textField_tblCaption.setEditable(false);
				chckbxIsPicklist.setEnabled(false);
				chckbxIsCulture.setEnabled(false);
				chckbxIsSpecial.setEnabled(false);
				chckbxIsSubHead.setEnabled(false);
				textField_propertyName.setEditable(false);
				textField_propertyType.setEditable(false);
				editorPane_normalValue.setEditable(false);

				textField_testNameSearch.setEditable(false);
				textField_testNameSearch.setText(null);
				table.setVisible(false);
				table.setEnabled(false);

				textField_testName.setText(null);
				textField_rate1.setText(null);
				textField_rate2.setText(null);
				textField_Department.setText(null);
				textField_Sample.setText(null);
				textField_TypeofTest.setText(null);
				textField_Method.setText(null);
				textField_units.setText(null);
				textField_digits.setText(null);
				textField_decimal.setText(null);
				textField_defaultValue.setText(null);
				textField_rangeLower.setText(null);
				textField_rangeUpper.setText(null);
				textField_formula.setText(null);
				textField_validation.setText(null);
				textField_tblCaption.setText(null);
				chckbxIsPicklist.setSelected(false);
				chckbxIsCulture.setSelected(false);
				chckbxIsSpecial.setSelected(false);
				chckbxIsSubHead.setSelected(false);
				textField_propertyName.setText(null);
				textField_propertyType.setText(null);
				editorPane_normalValue.setText(null);

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
		btnSave.setIcon(new ImageIcon(TestMaster.class.getResource("/image/Devices-media-floppy-icon.png")));
		btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSave.setVerticalAlignment(SwingConstants.TOP);
		btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setFocusPainted(false);
		btnSave.setBackground(Color.WHITE);
		btnSave.setBounds(300, 0, 102, 82);
		panel_5.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_testName.setEditable(false);
				textField_rate1.setEditable(false);
				textField_rate2.setEditable(false);
				textField_Department.setEditable(false);
				textField_Sample.setEditable(false);
				textField_TypeofTest.setEditable(false);
				textField_Method.setEditable(false);
				btnDepartment.setEnabled(false);
				btnSample.setEnabled(false);
				btnTypeofTest.setEnabled(false);
				btnMethod.setEnabled(false);
				textField_units.setEditable(false);
				textField_digits.setEditable(false);
				textField_decimal.setEditable(false);
				textField_defaultValue.setEditable(false);
				textField_rangeLower.setEditable(false);
				textField_rangeUpper.setEditable(false);
				textField_formula.setEditable(false);
				textField_validation.setEditable(false);
				textField_tblCaption.setEditable(false);
				chckbxIsPicklist.setEnabled(false);
				chckbxIsCulture.setEnabled(false);
				chckbxIsSpecial.setEnabled(false);
				chckbxIsSubHead.setEnabled(false);
				textField_propertyName.setEditable(false);
				textField_propertyType.setEditable(false);
				editorPane_normalValue.setEditable(false);

				textField_testNameSearch.setEditable(false);
				textField_testNameSearch.setText(null);
				table.setVisible(false);
				table.setEnabled(false);

				textField_id.setText(null);
				textField_testName.setText(null);
				textField_rate1.setText(null);
				textField_rate2.setText(null);
				textField_Department.setText(null);
				textField_Sample.setText(null);
				textField_TypeofTest.setText(null);
				textField_Method.setText(null);
				textField_units.setText(null);
				textField_digits.setText(null);
				textField_decimal.setText(null);
				textField_defaultValue.setText(null);
				textField_rangeLower.setText(null);
				textField_rangeUpper.setText(null);
				textField_formula.setText(null);
				textField_validation.setText(null);
				textField_tblCaption.setText(null);
				chckbxIsPicklist.setSelected(false);
				chckbxIsCulture.setSelected(false);
				chckbxIsSpecial.setSelected(false);
				chckbxIsSubHead.setSelected(false);
				textField_propertyName.setText(null);
				textField_propertyType.setText(null);
				editorPane_normalValue.setText(null);

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
		btnCancel.setIcon(new ImageIcon(TestMaster.class.getResource("/image/Actions-edit-undo-icon.png")));
		btnCancel.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancel.setVerticalAlignment(SwingConstants.TOP);
		btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCancel.setFocusPainted(false);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(400, 0, 102, 82);
		panel_5.add(btnCancel);

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

				textField_testNameSearch.setEditable(true);
				table.setVisible(true);
				table.setEnabled(true);
			}
		});
		btnSearch.setIcon(new ImageIcon(TestMaster.class.getResource("/image/Zoom-icon.png")));
		btnSearch.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSearch.setVerticalAlignment(SwingConstants.TOP);
		btnSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSearch.setFocusPainted(false);
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(500, 0, 102, 82);
		panel_5.add(btnSearch);

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnExit.setIcon(new ImageIcon(TestMaster.class.getResource("/image/Actions-window-close-icon.png")));
		btnExit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExit.setVerticalAlignment(SwingConstants.TOP);
		btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setFocusPainted(false);
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(600, 0, 102, 82);
		panel_5.add(btnExit);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(300, 0, 102, 82);
		panel_5.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField_id.getText();
				String testName = textField_testName.getText();
				String rate1 = textField_rate1.getText();
				String rate2 = textField_rate2.getText();
				String department = textField_Department.getText();
				String sample = textField_Sample.getText();
				String typeoftest = textField_TypeofTest.getText();
				String method = textField_Method.getText();
				String unit = textField_units.getText();
				String digit = textField_digits.getText();
				String decimal = textField_decimal.getText();
				String defaultValue = textField_defaultValue.getText();
				String rangeLower = textField_rangeLower.getText();
				String rangeUpper = textField_rangeUpper.getText();
				String formula = textField_formula.getText();
				String validation = textField_validation.getText();
				String tblCaption = textField_tblCaption.getText();
				String isPicklist = chckbxIsPicklist.getActionCommand();
				String isCulture = chckbxIsCulture.getActionCommand();
				String isSpecial = chckbxIsSpecial.getActionCommand();
				String isSubHead = chckbxIsSubHead.getActionCommand();
				String propertyName = textField_propertyName.getText();
				String propertyType = textField_propertyType.getText();
				String normalValue = editorPane_normalValue.getText();

				String sql = "update test set testName ='" + testName + "' rate1 = '" + rate1 + "' rate2 ='" + rate2
						+ "' depatement ='" + department + "' sample ='" + sample + "' inputType ='" + typeoftest
						+ "' methodType ='" + method + "' isSubTest ='" + isSubHead + "' unit ='" + unit + "' digit ='"
						+ digit + "' decimal_1 ='" + decimal + "' defaultValue_1 ='" + defaultValue + "' minValue_1 ='"
						+ rangeLower + "' maxValue_1 ='" + rangeUpper + "' isPicklist ='" + isPicklist + "' formula ='"
						+ formula + "' normalValue ='" + normalValue + "' isSpecial ='" + isSpecial + "' isCulture ='"
						+ isCulture + "' where id ='" + id + "'";
				executeQuery(sql, "Updated");

				textField_testName.setEditable(false);
				textField_rate1.setEditable(false);
				textField_rate2.setEditable(false);
				textField_Department.setEditable(false);
				textField_Sample.setEditable(false);
				textField_TypeofTest.setEditable(false);
				textField_Method.setEditable(false);
				btnDepartment.setEnabled(false);
				btnSample.setEnabled(false);
				btnTypeofTest.setEnabled(false);
				btnMethod.setEnabled(false);
				textField_units.setEditable(false);
				textField_digits.setEditable(false);
				textField_decimal.setEditable(false);
				textField_defaultValue.setEditable(false);
				textField_rangeLower.setEditable(false);
				textField_rangeUpper.setEditable(false);
				textField_formula.setEditable(false);
				textField_validation.setEditable(false);
				textField_tblCaption.setEditable(false);
				chckbxIsPicklist.setEnabled(false);
				chckbxIsCulture.setEnabled(false);
				chckbxIsSpecial.setEnabled(false);
				chckbxIsSubHead.setEnabled(false);
				textField_propertyName.setEditable(false);
				textField_propertyType.setEditable(false);
				editorPane_normalValue.setEditable(false);

				textField_testNameSearch.setEditable(false);
				textField_testNameSearch.setText(null);
				table.setVisible(false);
				table.setEnabled(false);

				textField_id.setText(null);
				textField_testName.setText(null);
				textField_rate1.setText(null);
				textField_rate2.setText(null);
				textField_Department.setText(null);
				textField_Sample.setText(null);
				textField_TypeofTest.setText(null);
				textField_Method.setText(null);
				textField_units.setText(null);
				textField_digits.setText(null);
				textField_decimal.setText(null);
				textField_defaultValue.setText(null);
				textField_rangeLower.setText(null);
				textField_rangeUpper.setText(null);
				textField_formula.setText(null);
				textField_validation.setText(null);
				textField_tblCaption.setText(null);
				chckbxIsPicklist.setSelected(false);
				chckbxIsCulture.setSelected(false);
				chckbxIsSpecial.setSelected(false);
				chckbxIsSubHead.setSelected(false);
				textField_propertyName.setText(null);
				textField_propertyType.setText(null);
				editorPane_normalValue.setText(null);

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
		btnUpdate.setVisible(false);
		btnUpdate.setIcon(new ImageIcon(TestMaster.class.getResource("/image/update-icon.png")));
		btnUpdate.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnUpdate.setVerticalAlignment(SwingConstants.TOP);
		btnUpdate.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBackground(Color.WHITE);

		table("testName", "test");

	}
}
