package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Administator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Administator frameAdministator = new Administator();
	private JButton btnDoctor;
	private JButton btnTestMethod;
	private JButton btnTestSample;
	private JButton btnPatientMaster;
	private JButton btnOtherLab;
	private JButton btnProfileMaster;
	private JButton btnTestMaster;
	private JButton btnTestDepartment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameAdministator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void close() {
		frameAdministator.setVisible(false);
		dispose();
	}

	/**
	 * Create the frame.
	 */
	public Administator() {
		setTitle("Administator");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 684, 21);
		contentPane.add(menuBar);

		JMenu mnAdministator = new JMenu("Administator");
		menuBar.add(mnAdministator);

		JMenu mnMaster = new JMenu("Master");
		mnAdministator.add(mnMaster);

		JMenuItem mntmDoctorSetup = new JMenuItem("Doctor Setup");
		mntmDoctorSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Doctor().setVisible(true);
				close();
			}
		});
		mntmDoctorSetup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mnMaster.add(mntmDoctorSetup);

		JMenuItem mntmTestDepartment = new JMenuItem("Test Department");
		mntmTestDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TestDepartment().setVisible(true);
				close();
			}
		});
		mnMaster.add(mntmTestDepartment);

		JMenuItem mntmTestSample = new JMenuItem("Test Sample");
		mntmTestSample.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TestSample().setVisible(true);
				close();
			}
		});
		mnMaster.add(mntmTestSample);

		JMenuItem mntmTestMaster = new JMenuItem("Test Master");
		mntmTestMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TestMaster().setVisible(true);
				close();
			}
		});
		mntmTestMaster.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		mnMaster.add(mntmTestMaster);

		JMenuItem mntmTestMethod = new JMenuItem("Test Method");
		mntmTestMethod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TestMethod().setVisible(true);
				close();
			}
		});
		mnMaster.add(mntmTestMethod);

		JMenuItem mntmProfileMaster = new JMenuItem("Profile Master");
		mntmProfileMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProfileMaster().setVisible(true);
				close();
			}
		});
		mnMaster.add(mntmProfileMaster);

		JMenuItem mntmPatientMaster = new JMenuItem("Patient Master");
		mntmPatientMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PatientMaster().setVisible(true);
				close();
			}
		});
		mnMaster.add(mntmPatientMaster);

		JMenuItem mntmOtherLab = new JMenuItem("Other Lab");
		mntmOtherLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OtherLab().setVisible(true);
				close();
			}
		});
		mnMaster.add(mntmOtherLab);

		btnDoctor = new JButton("Doctor");
		btnDoctor.setForeground(new Color(255, 215, 0));
		btnDoctor.setFocusPainted(false);
		btnDoctor.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		btnDoctor.setBackground(new Color(102, 205, 170));
		btnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Doctor().setVisible(true);
				close();
			}
		});
		btnDoctor.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDoctor.setBounds(59, 71, 200, 48);
		contentPane.add(btnDoctor);

		btnTestMethod = new JButton("Test Method");
		btnTestMethod.setForeground(new Color(255, 215, 0));
		btnTestMethod.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		btnTestMethod.setBackground(new Color(102, 205, 170));
		btnTestMethod.setFocusPainted(false);
		btnTestMethod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TestMethod().setVisible(true);
				close();
			}
		});
		btnTestMethod.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTestMethod.setBounds(59, 149, 200, 48);
		contentPane.add(btnTestMethod);

		btnPatientMaster = new JButton("Patient Master");
		btnPatientMaster.setForeground(new Color(255, 215, 0));
		btnPatientMaster.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		btnPatientMaster.setBackground(new Color(102, 205, 170));
		btnPatientMaster.setFocusPainted(false);
		btnPatientMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PatientMaster().setVisible(true);
				close();
			}
		});
		btnPatientMaster.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnPatientMaster.setBounds(59, 305, 200, 48);
		contentPane.add(btnPatientMaster);

		btnTestDepartment = new JButton("Test Department");
		btnTestDepartment.setForeground(new Color(255, 215, 0));
		btnTestDepartment.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		btnTestDepartment.setBackground(new Color(102, 205, 170));
		btnTestDepartment.setFocusPainted(false);
		btnTestDepartment.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTestDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TestDepartment().setVisible(true);
				close();
			}
		});
		btnTestDepartment.setBounds(385, 71, 200, 48);
		contentPane.add(btnTestDepartment);

		btnProfileMaster = new JButton("Profile Master");
		btnProfileMaster.setForeground(new Color(255, 215, 0));
		btnProfileMaster.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		btnProfileMaster.setBackground(new Color(102, 205, 170));
		btnProfileMaster.setFocusPainted(false);
		btnProfileMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProfileMaster().setVisible(true);
				close();
			}
		});
		btnProfileMaster.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnProfileMaster.setBounds(385, 225, 200, 48);
		contentPane.add(btnProfileMaster);

		btnTestMaster = new JButton("Test Master");
		btnTestMaster.setForeground(new Color(255, 215, 0));
		btnTestMaster.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		btnTestMaster.setBackground(new Color(102, 205, 170));
		btnTestMaster.setFocusPainted(false);
		btnTestMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TestMaster().setVisible(true);
				close();
			}
		});
		btnTestMaster.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTestMaster.setBounds(385, 149, 200, 48);
		contentPane.add(btnTestMaster);

		btnTestSample = new JButton("Test Sample");
		btnTestSample.setForeground(new Color(255, 215, 0));
		btnTestSample.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		btnTestSample.setBackground(new Color(102, 205, 170));
		btnTestSample.setFocusPainted(false);
		btnTestSample.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TestSample().setVisible(true);
				close();
			}
		});
		btnTestSample.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTestSample.setBounds(59, 225, 200, 48);
		contentPane.add(btnTestSample);

		btnOtherLab = new JButton("Other Lab");
		btnOtherLab.setForeground(new Color(255, 215, 0));
		btnOtherLab.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		btnOtherLab.setBackground(new Color(102, 205, 170));
		btnOtherLab.setFocusPainted(false);
		btnOtherLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OtherLab().setVisible(true);
				close();
			}
		});
		btnOtherLab.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnOtherLab.setBounds(385, 305, 200, 48);
		contentPane.add(btnOtherLab);

		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setIcon(new ImageIcon(Administator.class.getResource("/image/pexels-photo-248152.jpeg")));
		label.setBounds(0, 0, 684, 461);
		contentPane.add(label);
	}
}
