package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class DangNhap_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTaiKhoan;
	private JTextField txtMK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap_GUI frame = new DangNhap_GUI();
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
	public DangNhap_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 964, 596);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogIn = new JButton("Đăng Nhập");
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogIn.setForeground(new Color(248, 248, 255));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogIn.setBackground(new Color(100, 149, 237));
		btnLogIn.setBounds(409, 83, 120, 37);
		contentPane.add(btnLogIn);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(300, 148, 349, 260);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(100, 149, 237));
		panel_1.setBounds(0, 0, 349, 56);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng Nhập");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(122, 11, 112, 31);
		panel_1.add(lblNewLabel);
		
		textTaiKhoan = new JTextField("Tên Tài khoản");
		textTaiKhoan.setForeground(new Color(211, 211, 211));
		textTaiKhoan.setBounds(37, 80, 286, 35);
		panel.add(textTaiKhoan);
		textTaiKhoan.setColumns(10);
		
		txtMK = new JTextField("Mật khẩu");
		txtMK.setForeground(new Color(211, 211, 211));
		txtMK.setColumns(10);
		txtMK.setBounds(37, 136, 286, 35);
		panel.add(txtMK);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(48, 90, 46, 14);
		panel.add(lblNewLabel_1);
		
		;
		
		JButton btnLogIn_1 = new JButton("Đăng Nhập");
		btnLogIn_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogIn_1.setForeground(new Color(248, 248, 255));
		btnLogIn_1.setBackground(new Color(100, 149, 237));
		btnLogIn_1.setBounds(37, 194, 286, 37);
		panel.add(btnLogIn_1);
		
		
	}}
