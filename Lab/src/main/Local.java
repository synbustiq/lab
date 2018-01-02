package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Local extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnInvoice = new JButton("INVOICE");
		btnInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Invoice().setVisible(true);
			}
		});
		btnInvoice.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnInvoice.setBounds(43, 277, 205, 55);
		contentPane.add(btnInvoice);

		JButton btnReport = new JButton("REPORT");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Report().setVisible(true);
			}
		});
		btnReport.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReport.setBounds(43, 436, 205, 55);
		contentPane.add(btnReport);
	}

}
