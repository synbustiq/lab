package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import database.DatabaseConnection;
import database.ExecuteQuery;
import other.Table;

public class TestMaster extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_testCode;
	private JTextField textField_testName;
	private JTextField textField_rate1;
	private JTextField textField_rate2;
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
	private JComboBox<String> comboBox_test;
	private JComboBox<String> comboBox_Depart;
	private JComboBox<String> comboBox_Sample;
	private JComboBox<String> comboBox_TypeOfTest;
	private JComboBox<String> comboBox_Method;
	private JCheckBox chckbxIsSubHead;
	private JCheckBox chckbxIsPicklist;
	private JCheckBox chckbxIsCulture;
	private JCheckBox chckbxIsSpecial;
	private JEditorPane editorPane_normalValue;
	private JButton btnUpdate;
	private JButton btnSave;
	private JButton btnEdit;
	DatabaseConnection dataBase = new DatabaseConnection();
	ExecuteQuery executeQuery = new ExecuteQuery();
	Table test_table = new Table();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestMaster frame = new TestMaster();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void refreshTable() {
		// Refresh the table data
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		// table function is called again to diaplay refresh table
		test_table.table("testName", "test");
	}

	public void comboBox(String name, int index) {
		Connection connection = dataBase.getConnection();
		PreparedStatement statement;
		ResultSet set;
		try {
			String sql = "select * from " + name + " ";
			statement = connection.prepareStatement(sql);
			set = statement.executeQuery();
			while (set.next()) {
				comboBox_test.addItem(set.getString(index));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void refreshCombobox() {
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox_test.getModel();
		model.getIndexOf(0);
		// fillComboBox function is called again to diaplay refresh comboBox
		comboBox("test", 2);
	}

	/**
	 * Create the frame.
	 */

	public TestMaster() {
		setTitle("Test Master");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1500, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 39, 286, 711);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSearchByTest = new JLabel("Search by Test Name");
		lblSearchByTest.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearchByTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchByTest.setBounds(10, 11, 266, 28);
		panel.add(lblSearchByTest);

		comboBox_test = new JComboBox<String>();
		comboBox_test.setEnabled(false);
		comboBox_test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = comboBox_test.getSelectedItem().toString();
				if (name.equals("") || name.equals(null)) {
					// Does not do nothing
				} else {
					try {
						String sql = "select * from test where testName = '" + name + "'";
						PreparedStatement statement = dataBase.getConnection().prepareStatement(sql);
						ResultSet set = statement.executeQuery();
						while (set.next()) {
							textField_testCode.setText(set.getString(1));
							textField_testName.setText(set.getString(2));
							textField_rate1.setText(set.getString(3));
							textField_rate2.setText(set.getString(4));
							comboBox_Depart.setSelectedIndex(set.getInt(5));
							comboBox_Sample.setSelectedIndex(set.getInt(6));
							comboBox_TypeOfTest.setSelectedItem(set.getObject(7));
							comboBox_Method.setSelectedIndex(set.getInt(8));
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
		comboBox_test.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
		comboBox_test.setEditable(true);
		comboBox_test.setBounds(10, 50, 266, 28);
		panel.add(comboBox_test);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 266, 611);
		panel.add(scrollPane);

		table = new JTable();
		table.setVisible(false);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Test Name" }));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		table.setModel(test_table.table("testName", "test"));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(306, 39, 1168, 579);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 157, 621, 411);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblUnits = new JLabel("Units");
		lblUnits.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnits.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUnits.setBounds(10, 11, 45, 32);
		panel_2.add(lblUnits);

		textField_units = new JTextField();
		textField_units.setColumns(10);
		textField_units.setBounds(65, 13, 130, 32);
		panel_2.add(textField_units);

		JLabel lblDigits = new JLabel("Digits");
		lblDigits.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDigits.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDigits.setBounds(193, 13, 62, 32);
		panel_2.add(lblDigits);

		textField_digits = new JTextField();
		textField_digits.setColumns(10);
		textField_digits.setBounds(265, 13, 130, 32);
		panel_2.add(textField_digits);

		JLabel lblDecimals = new JLabel("Decimals");
		lblDecimals.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDecimals.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDecimals.setBounds(405, 11, 62, 32);
		panel_2.add(lblDecimals);

		textField_decimal = new JTextField();
		textField_decimal.setColumns(10);
		textField_decimal.setBounds(477, 13, 130, 32);
		panel_2.add(textField_decimal);

		JLabel lblDefaultValue = new JLabel("Default Value");
		lblDefaultValue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDefaultValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDefaultValue.setBounds(10, 72, 94, 32);
		panel_2.add(lblDefaultValue);

		textField_defaultValue = new JTextField();
		textField_defaultValue.setColumns(10);
		textField_defaultValue.setBounds(114, 72, 493, 32);
		panel_2.add(textField_defaultValue);

		JLabel lblRangenormal = new JLabel("Range(Normal)");
		lblRangenormal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRangenormal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRangenormal.setBounds(10, 136, 107, 32);
		panel_2.add(lblRangenormal);

		JLabel lblLower = new JLabel("Lower");
		lblLower.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLower.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLower.setBounds(124, 136, 75, 32);
		panel_2.add(lblLower);

		JLabel lblUpper = new JLabel("Upper");
		lblUpper.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUpper.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUpper.setBounds(374, 136, 62, 32);
		panel_2.add(lblUpper);

		textField_rangeLower = new JTextField();
		textField_rangeLower.setColumns(10);
		textField_rangeLower.setBounds(209, 138, 155, 32);
		panel_2.add(textField_rangeLower);

		textField_rangeUpper = new JTextField();
		textField_rangeUpper.setColumns(10);
		textField_rangeUpper.setBounds(446, 138, 160, 32);
		panel_2.add(textField_rangeUpper);

		JLabel lblFormula = new JLabel("Formula");
		lblFormula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFormula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFormula.setBounds(10, 191, 62, 32);
		panel_2.add(lblFormula);

		JLabel lblValidation = new JLabel("Validation");
		lblValidation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValidation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblValidation.setBounds(20, 244, 62, 32);
		panel_2.add(lblValidation);

		JLabel lblTblCaption = new JLabel("Tbl Caption");
		lblTblCaption.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTblCaption.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTblCaption.setBounds(10, 301, 84, 32);
		panel_2.add(lblTblCaption);

		textField_formula = new JTextField();
		textField_formula.setColumns(10);
		textField_formula.setBounds(114, 199, 493, 32);
		panel_2.add(textField_formula);

		textField_validation = new JTextField();
		textField_validation.setColumns(10);
		textField_validation.setBounds(114, 252, 493, 32);
		panel_2.add(textField_validation);

		textField_tblCaption = new JTextField();
		textField_tblCaption.setColumns(10);
		textField_tblCaption.setBounds(114, 301, 493, 32);
		panel_2.add(textField_tblCaption);

		chckbxIsPicklist = new JCheckBox("Is PickList");
		chckbxIsPicklist.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxIsPicklist.setBounds(10, 360, 160, 32);
		panel_2.add(chckbxIsPicklist);

		chckbxIsCulture = new JCheckBox("Is Culture");
		chckbxIsCulture.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxIsCulture.setBounds(193, 360, 160, 32);
		panel_2.add(chckbxIsCulture);

		chckbxIsSpecial = new JCheckBox("Is Special");
		chckbxIsSpecial.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxIsSpecial.setBounds(405, 360, 160, 32);
		panel_2.add(chckbxIsSpecial);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, Color.BLACK, null));
		panel_3.setBounds(641, 260, 517, 308);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		editorPane_normalValue = new JEditorPane();
		editorPane_normalValue.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		editorPane_normalValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		editorPane_normalValue.setBounds(10, 30, 497, 267);
		panel_3.add(editorPane_normalValue);

		JLabel lblNormalValue = new JLabel("NORMAL VALUE");
		lblNormalValue.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNormalValue.setBounds(10, 0, 162, 25);
		panel_3.add(lblNormalValue);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(641, 103, 517, 146);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblPropertyName = new JLabel("Property Name");
		lblPropertyName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPropertyName.setBounds(10, 40, 157, 30);
		panel_4.add(lblPropertyName);

		JLabel lblPropertyType = new JLabel("Property Type");
		lblPropertyType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPropertyType.setBounds(10, 93, 157, 30);
		panel_4.add(lblPropertyType);

		textField_propertyName = new JTextField();
		textField_propertyName.setBounds(161, 42, 332, 30);
		panel_4.add(textField_propertyName);
		textField_propertyName.setColumns(10);

		textField_propertyType = new JTextField();
		textField_propertyType.setColumns(10);
		textField_propertyType.setBounds(161, 100, 332, 30);
		panel_4.add(textField_propertyType);

		chckbxIsSubHead = new JCheckBox("Is Sub Head");
		chckbxIsSubHead.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxIsSubHead.setBounds(7, 7, 160, 32);
		panel_4.add(chckbxIsSubHead);

		JLabel lblTestCode = new JLabel("Test Code");
		lblTestCode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTestCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTestCode.setBounds(10, 11, 76, 32);
		panel_1.add(lblTestCode);

		textField_testCode = new JTextField();
		textField_testCode.setEnabled(false);
		textField_testCode.setBounds(96, 11, 130, 32);
		panel_1.add(textField_testCode);
		textField_testCode.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(236, 11, 76, 32);
		panel_1.add(lblName);

		textField_testName = new JTextField();
		textField_testName.setColumns(10);
		textField_testName.setBounds(322, 11, 362, 32);
		panel_1.add(textField_testName);

		comboBox_TypeOfTest = new JComboBox<String>();
		comboBox_TypeOfTest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_TypeOfTest.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Only Numbers", "Only Texts", "Paragraph", "Table", "Date", "Time", "MultiProperty" }));
		comboBox_TypeOfTest.setEditable(true);
		comboBox_TypeOfTest.setBounds(880, 60, 264, 32);
		panel_1.add(comboBox_TypeOfTest);

		JLabel lblTypeOfTest = new JLabel("Type of Test");
		lblTypeOfTest.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTypeOfTest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTypeOfTest.setBounds(755, 60, 115, 32);
		panel_1.add(lblTypeOfTest);

		JLabel lblRate = new JLabel("Rate 1");
		lblRate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRate.setBounds(694, 11, 76, 32);
		panel_1.add(lblRate);

		textField_rate1 = new JTextField();
		textField_rate1.setColumns(10);
		textField_rate1.setBounds(786, 11, 130, 32);
		panel_1.add(textField_rate1);

		JLabel lblRate_1 = new JLabel("Rate 2");
		lblRate_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRate_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRate_1.setBounds(926, 11, 76, 32);
		panel_1.add(lblRate_1);

		textField_rate2 = new JTextField();
		textField_rate2.setColumns(10);
		textField_rate2.setBounds(1014, 11, 130, 32);
		panel_1.add(textField_rate2);

		JLabel lblDepart = new JLabel("Depart");
		lblDepart.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDepart.setBounds(10, 58, 76, 32);
		panel_1.add(lblDepart);

		JLabel lblMethod = new JLabel("Method");
		lblMethod.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMethod.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMethod.setBounds(10, 114, 76, 32);
		panel_1.add(lblMethod);

		JLabel lblSample = new JLabel("Sample");
		lblSample.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSample.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSample.setBounds(370, 58, 76, 32);
		panel_1.add(lblSample);

		comboBox_Depart = new JComboBox<String>();
		comboBox_Depart.setModel(new DefaultComboBoxModel<String>(
				new String[] { "HAEMATOLOGY", "BIOCHEMISTRY", "SEROLOGY", "ENDOCRINOLOGY", "IMMUNO ASSAY",
						"TUMOUR MARKERS", "TORCH PANEL", "DRUG ASSAY", "FLOW CYTOMETRY", "SPOT URINE ANALYSIS",
						"24 HOURS URINE ANALYSIS", "CLINICAL PATHOLOGY", "HISTOPATHOLOGY", "MICROBIOLOGY", "RADIOLOGY-",
						"SEMEN ANALYSES", "X-RAY", "RADIOLOGY", "ECG", "PARASITOLOGY", "BIO-CHEMISTRY", "OTHERS" }));
		comboBox_Depart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_Depart.setEditable(true);
		comboBox_Depart.setBounds(96, 60, 264, 32);
		panel_1.add(comboBox_Depart);

		comboBox_Method = new JComboBox<String>();
		comboBox_Method.setModel(new DefaultComboBoxModel<String>(new String[] { "Slide Method", "Tube Method",
				"QBC Method", "CLIA", "IFCC Method", "BROMOCRESOL GREEN" }));
		comboBox_Method.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_Method.setEditable(true);
		comboBox_Method.setBounds(96, 114, 264, 32);
		panel_1.add(comboBox_Method);

		comboBox_Sample = new JComboBox<String>();
		comboBox_Sample.setModel(new DefaultComboBoxModel<String>(new String[] { "BLOOD", "URINE", "STOOL", "SEMEN",
				"PUS", "SPUTUM", "STONE", "CSF ANALYSIS", "FLUID", "SERUM", "SWAB", "SCAN", "X-RAY", "ECG" }));
		comboBox_Sample.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_Sample.setEditable(true);
		comboBox_Sample.setBounds(456, 60, 275, 32);
		panel_1.add(comboBox_Sample);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(1353, 713, 121, 37);
		contentPane.add(btnExit);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Variables Declaration
				String testName = textField_testName.getText();
				String rate1 = textField_rate1.getText();
				String rate2 = textField_rate2.getText();
				int departmentNo = comboBox_Depart.getSelectedIndex();
				int sampleNo = comboBox_Sample.getSelectedIndex();
				String inputType = (String) comboBox_TypeOfTest.getSelectedItem();
				int methodType = comboBox_Method.getSelectedIndex();
				Boolean isSubTest = chckbxIsSubHead.isSelected();
				String unit = textField_units.getText();
				String digit = textField_digits.getText();
				String decimal = textField_decimal.getText();
				String defaultValue = textField_defaultValue.getText();
				String minValue = textField_rangeLower.getText();
				String maxValue = textField_rangeUpper.getText();
				Boolean isPicklist = chckbxIsPicklist.isSelected();
				String formula = textField_formula.getText();
				String normalValue = editorPane_normalValue.getText();
				Boolean isSpecial = chckbxIsSpecial.isSelected();
				Boolean isCulture = chckbxIsCulture.isSelected();

				String sql = "insert into test(testName,rate1,rate2,departmentNo,sampleNo,inputType,methodtype,isSubTest,unit,digit,decimal_1,defaultValue_1,minValue_1,maxValue_1,isPicklist,formula,normalValue,isSpecial,isCulture) values('"
						+ testName + "','" + rate1 + "','" + rate2 + "','" + departmentNo + "','" + sampleNo + "','"
						+ inputType + "','" + methodType + "','" + isSubTest + "','" + unit + "','" + digit + "','"
						+ decimal + "','" + defaultValue + "','" + minValue + "','" + maxValue + "','" + isPicklist
						+ "','" + formula + "','" + normalValue + "','" + isSpecial + "','" + isCulture + "')";
				executeQuery.executeQuery(sql, "INSERTED");
				textField_testCode.setText(null);
				textField_testName.setText(null);
				textField_rate1.setText(null);
				textField_rate2.setText(null);
				comboBox_Depart.setSelectedIndex(0);
				comboBox_Sample.setSelectedIndex(0);
				comboBox_TypeOfTest.setSelectedIndex(0);
				comboBox_Method.setSelectedIndex(0);
				chckbxIsSubHead.setSelected(false);
				textField_units.setText(null);
				textField_digits.setText(null);
				textField_decimal.setText(null);
				textField_defaultValue.setText(null);
				textField_rangeLower.setText(null);
				textField_rangeUpper.setText(null);
				chckbxIsPicklist.setSelected(false);
				textField_formula.setText(null);
				editorPane_normalValue.setText(null);
				chckbxIsSpecial.setSelected(false);
				chckbxIsCulture.setSelected(false);
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(1212, 713, 121, 37);
		contentPane.add(btnSave);

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSave.setVisible(false);
				btnUpdate.setVisible(true);
				comboBox_test.setEnabled(true);
				table.setVisible(true);
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit.setBounds(1063, 713, 121, 37);
		contentPane.add(btnEdit);

		btnUpdate = new JButton("Save");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Variables Declaration
				String id = textField_testCode.getText();
				String testName = textField_testName.getText();
				String rate1 = textField_rate1.getText();
				String rate2 = textField_rate2.getText();
				int departmentNo = comboBox_Depart.getSelectedIndex();
				int sampleNo = comboBox_Sample.getSelectedIndex();
				String inputType = (String) comboBox_TypeOfTest.getSelectedItem();
				int methodType = comboBox_Method.getSelectedIndex();
				Boolean isSubTest = chckbxIsSubHead.isSelected();
				String unit = textField_units.getText();
				String digit = textField_digits.getText();
				String decimal = textField_decimal.getText();
				String defaultValue = textField_defaultValue.getText();
				String minValue = textField_rangeLower.getText();
				String maxValue = textField_rangeUpper.getText();
				Boolean isPicklist = chckbxIsPicklist.isSelected();
				String formula = textField_formula.getText();
				String normalValue = editorPane_normalValue.getText();
				Boolean isSpecial = chckbxIsSpecial.isSelected();
				Boolean isCulture = chckbxIsCulture.isSelected();

				String sql = "update test set testName = '" + testName + "', rate1 ='" + rate1 + "', rate2 = '" + rate2
						+ "',departmentNo ='" + departmentNo + "',sampleNo ='" + sampleNo + "',inputType ='" + inputType
						+ "',methodType ='" + methodType + "',isSubTest ='" + isSubTest + "',unit ='" + unit
						+ "',digit ='" + digit + "',decimal_1 ='" + decimal + "',defaultValue_1 ='" + defaultValue
						+ "',minValue_1 ='" + minValue + "',maxValue_1 ='" + maxValue + "',isPicklist ='" + isPicklist
						+ "',formula ='" + formula + "',normalValue ='" + normalValue + "',isSpecial ='" + isSpecial
						+ "',isCulture ='" + isCulture + "' where id ='" + id + "'";
				executeQuery.executeQuery(sql, "Updated");
				btnSave.setVisible(true);
				btnUpdate.setVisible(false);
				comboBox_test.setEnabled(false);
				table.setVisible(false);
				textField_testCode.setText(null);
				textField_testName.setText(null);
				textField_rate1.setText(null);
				textField_rate2.setText(null);
				comboBox_Depart.setSelectedIndex(0);
				comboBox_Sample.setSelectedIndex(0);
				comboBox_TypeOfTest.setSelectedIndex(0);
				comboBox_Method.setSelectedIndex(0);
				chckbxIsSubHead.setSelected(false);
				textField_units.setText(null);
				textField_digits.setText(null);
				textField_decimal.setText(null);
				textField_defaultValue.setText(null);
				textField_rangeLower.setText(null);
				textField_rangeUpper.setText(null);
				chckbxIsPicklist.setSelected(false);
				textField_formula.setText(null);
				editorPane_normalValue.setText(null);
				chckbxIsSpecial.setSelected(false);
				chckbxIsCulture.setSelected(false);
			}
		});
		btnUpdate.setVisible(false);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.setBounds(1212, 713, 121, 37);
		contentPane.add(btnUpdate);

		comboBox("test", 2);
		AutoCompleteDecorator.decorate(comboBox_test);
	}

}
