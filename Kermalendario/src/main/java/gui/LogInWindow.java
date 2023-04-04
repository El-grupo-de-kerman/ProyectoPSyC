package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import main.Main;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;

public class LogInWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LogInWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public LogInWindow(Main exampleClient) {
		setTitle("LogIn Kermalendar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 316);
		setMinimumSize(new Dimension(400, 300));
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inicie sesión o cree una cuenta nueva");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 484, 17);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(0, 0, 484, 17);
		contentPane.add(panel);
		
		JPanel panelRegister = new JPanel();
		panelRegister.setBackground(new Color(135, 206, 250));
		panelRegister.setBounds(247, 25, 227, 241);
		contentPane.add(panelRegister);
		panelRegister.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("¿No tiene cuenta? Regístrese");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 5, 227, 14);
		panelRegister.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre de usuario");
		lblNewLabel_4.setBounds(10, 30, 207, 14);
		panelRegister.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(20, 55, 151, 20);
		panelRegister.add(textField_2);
		
		JLabel lblNewLabel_4_1 = new JLabel("Correo electrónico");
		lblNewLabel_4_1.setBounds(10, 86, 207, 14);
		panelRegister.add(lblNewLabel_4_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(20, 111, 151, 20);
		panelRegister.add(textField_3);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Contraseña");
		lblNewLabel_4_1_1.setBounds(10, 142, 207, 14);
		panelRegister.add(lblNewLabel_4_1_1);
		
		textField_4 = new JPasswordField();
		textField_4.setColumns(10);
		textField_4.setBounds(20, 165, 151, 20);
		panelRegister.add(textField_4);
		
		JButton btnDoneReg = new JButton("Done");
		btnDoneReg.setBounds(67, 207, 104, 23);
		panelRegister.add(btnDoneReg);
		btnDoneReg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exampleClient.registerUser(textField_2.getText(), 
						textField_3.getText(), textField_4.getText());
			}
		});
		
		JPanel panelLogIn = new JPanel();
		panelLogIn.setBackground(new Color(135, 206, 250));
		panelLogIn.setBounds(10, 25, 227, 241);
		contentPane.add(panelLogIn);
		panelLogIn.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Inicie sesión con su cuenta");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 5, 238, 14);
		panelLogIn.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre de usuario");
		lblNewLabel_3.setBounds(10, 57, 207, 14);
		panelLogIn.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(36, 82, 151, 20);
		panelLogIn.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setColumns(10);
		textField_1.setBounds(36, 149, 151, 20);
		panelLogIn.add(textField_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Contraseña");
		lblNewLabel_3_1.setBounds(10, 124, 104, 14);
		panelLogIn.add(lblNewLabel_3_1);
		
		JButton btnDoneLog = new JButton("Done");
		btnDoneLog.setBounds(62, 207, 104, 23);
		panelLogIn.add(btnDoneLog);
		btnDoneLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exampleClient.logUser(textField.getText(), textField_1.getText());
			}
		});
		repaint();
	}
}
