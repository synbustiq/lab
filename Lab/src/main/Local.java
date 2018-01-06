package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Local extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Local frame = new Local();
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
	public Local() {
		setTitle("Local");

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAdministator = new JMenu("Administator");
		menuBar.add(mnAdministator);

		JMenu mnMaster = new JMenu("Master");
		mnAdministator.add(mnMaster);

		JMenuItem mntmDoctorSetup = new JMenuItem("Doctor Setup");
		mntmDoctorSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Doctor().setVisible(true);
			}
		});
		mntmDoctorSetup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mnMaster.add(mntmDoctorSetup);

		JMenuItem mntmTestDepartmentSetup = new JMenuItem("Test Department Setup");
		mntmTestDepartmentSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TestDepartment().setVisible(true);
			}
		});
		mnMaster.add(mntmTestDepartmentSetup);

		JMenuItem mntmTestSampleSetup = new JMenuItem("Test  Sample Setup");
		mntmTestSampleSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TestSample().setVisible(true);
			}
		});
		mnMaster.add(mntmTestSampleSetup);

		JMenuItem mntmTestMaster = new JMenuItem("Test Master");
		mntmTestMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TestMaster().setVisible(true);
			}
		});
		mntmTestMaster.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		mnMaster.add(mntmTestMaster);

		JMenuItem mntmProfileMaster = new JMenuItem("Profile Master");
		mntmProfileMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProfileMaster().setVisible(true);
			}
		});
		mnMaster.add(mntmProfileMaster);

		JMenuItem mntmPatientMaster = new JMenuItem("Patient Master");
		mntmPatientMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PatientMaster().setVisible(true);
			}
		});
		mnMaster.add(mntmPatientMaster);

		JMenuItem mntmTestMethod = new JMenuItem("Test Method");
		mntmTestMethod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TestMethod().setVisible(true);
			}
		});
		mnMaster.add(mntmTestMethod);

		JMenuItem mntmOtherLab = new JMenuItem("Other Lab");
		mntmOtherLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OtherLab().setVisible(true);
			}
		});
		mnMaster.add(mntmOtherLab);

		JMenu mnTransaction = new JMenu("Transaction");
		menuBar.add(mnTransaction);

		JMenuItem mntmInvoice = new JMenuItem("Invoice");
		mntmInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Invoice().setVisible(true);
			}
		});
		mntmInvoice.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnTransaction.add(mntmInvoice);

		JMenuItem mntmResultEntry = new JMenuItem("Result Entry");
		mntmResultEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Report().setVisible(true);
			}
		});
		mntmResultEntry.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnTransaction.add(mntmResultEntry);

		JMenuItem mntmXrayScan = new JMenuItem("X-Ray / Scan Entry");
		mnTransaction.add(mntmXrayScan);

		JMenu mnReport = new JMenu("Report");
		menuBar.add(mnReport);

		JMenuItem mntmTransactionReport = new JMenuItem("Transaction Report");
		mnReport.add(mntmTransactionReport);
		getContentPane().setLayout(null);

		JLabel lblCtrliInvoice = new JLabel("CTRL+I INVOICE");
		lblCtrliInvoice.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrliInvoice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCtrliInvoice.setForeground(new Color(0, 0, 128));
		lblCtrliInvoice.setBounds(10, 451, 242, 34);
		getContentPane().add(lblCtrliInvoice);

		JLabel lblKeyboardShortcut = new JLabel("KEYBOARD SHORTCUT");
		lblKeyboardShortcut.setForeground(new Color(255, 255, 0));
		lblKeyboardShortcut.setHorizontalAlignment(SwingConstants.CENTER);
		lblKeyboardShortcut.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKeyboardShortcut.setBounds(10, 384, 242, 57);
		getContentPane().add(lblKeyboardShortcut);

		JLabel lblCtrlrReport = new JLabel("CTRL+R REPORT");
		lblCtrlrReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrlrReport.setForeground(new Color(0, 0, 128));
		lblCtrlrReport.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCtrlrReport.setBounds(10, 496, 242, 34);
		getContentPane().add(lblCtrlrReport);

		JLabel lblCtrltAddTest = new JLabel("CTRL+T ADD TEST");
		lblCtrltAddTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrltAddTest.setForeground(new Color(0, 0, 128));
		lblCtrltAddTest.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCtrltAddTest.setBounds(10, 541, 242, 34);
		getContentPane().add(lblCtrltAddTest);

		JLabel lblCtrldAddDoctor = new JLabel("CTRL+D ADD DOCTOR");
		lblCtrldAddDoctor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtrldAddDoctor.setForeground(new Color(0, 0, 128));
		lblCtrldAddDoctor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCtrldAddDoctor.setBounds(10, 586, 242, 34);
		getContentPane().add(lblCtrldAddDoctor);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(139, 0, 139));
		panel.setBorder(new LineBorder(new Color(138, 43, 226), 4, true));
		panel.setBounds(85, 94, 816, 83);
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnTransaction = new JButton("TRANSACTION");
		btnTransaction.setForeground(new Color(255, 0, 0));
		btnTransaction.setBackground(new Color(255, 255, 0));
		btnTransaction.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTransaction.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 69, 0), new Color(255, 69, 0),
				new Color(255, 69, 0), new Color(255, 69, 0)));
		btnTransaction.setBounds(144, 11, 124, 61);
		panel.add(btnTransaction);

		JButton btnReport = new JButton("REPORT");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Invoice().setVisible(true);
			}
		});
		btnReport.setBackground(new Color(255, 255, 0));
		btnReport.setForeground(new Color(255, 0, 0));
		btnReport.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReport.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 69, 0), new Color(255, 69, 0),
				new Color(255, 69, 0), new Color(255, 69, 0)));
		btnReport.setBounds(278, 11, 124, 61);
		panel.add(btnReport);

		JButton btnTestBooking = new JButton("TEST BOOKING");
		btnTestBooking.setForeground(new Color(255, 0, 0));
		btnTestBooking.setBackground(new Color(255, 255, 0));
		btnTestBooking.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTestBooking.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 69, 0), new Color(255, 69, 0),
				new Color(255, 69, 0), new Color(255, 69, 0)));
		btnTestBooking.setBounds(412, 11, 124, 61);
		panel.add(btnTestBooking);

		JButton btnEnterResult = new JButton("ENTER RESULT");
		btnEnterResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Report().setVisible(true);
			}
		});
		btnEnterResult.setBackground(new Color(255, 255, 0));
		btnEnterResult.setForeground(new Color(255, 0, 0));
		btnEnterResult.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEnterResult.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 69, 0), new Color(255, 69, 0),
				new Color(255, 69, 0), new Color(255, 69, 0)));
		btnEnterResult.setBounds(546, 11, 124, 61);
		panel.add(btnEnterResult);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setForeground(new Color(255, 0, 0));
		btnExit.setBackground(new Color(255, 255, 0));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 69, 0), new Color(255, 69, 0),
				new Color(255, 69, 0), new Color(255, 69, 0)));
		btnExit.setBounds(680, 11, 124, 61);
		panel.add(btnExit);

		JButton btnAdministator = new JButton("ADMINISTATOR");
		btnAdministator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Administator().setVisible(true);
			}
		});
		btnAdministator.setBounds(10, 11, 124, 61);
		panel.add(btnAdministator);
		btnAdministator.setBackground(new Color(255, 255, 0));
		btnAdministator.setForeground(new Color(255, 0, 0));
		btnAdministator.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdministator.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 69, 0), new Color(255, 69, 0),
				new Color(255, 69, 0), new Color(255, 69, 0)));

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Local.class.getResource("/image/lab.jpg")));
		label.setLabelFor(label);
		label.setBounds(0, 0, 994, 650);
		getContentPane().add(label);
	}

	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
