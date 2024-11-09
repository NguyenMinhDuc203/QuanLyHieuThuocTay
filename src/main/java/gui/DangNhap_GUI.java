package gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.NhanVien;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class DangNhap_GUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTaiKhoan;
	private JTextField txtMK;
	private JButton btnLogIn_1;
	private JButton btnLogIn_1_1;
	private NhanVien_DAO dao_nv = new NhanVien_DAO();
	private JTextField txtMaNhanVien;
	   private NhanVien nhanVienHienTai;
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(300, 148, 349, 277);
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
		
		textTaiKhoan = new JTextField("");
		textTaiKhoan.setForeground(Color.LIGHT_GRAY);
		textTaiKhoan.setBounds(37, 80, 286, 35);
		panel.add(textTaiKhoan);
		textTaiKhoan.setColumns(10);
		
		txtMK = new JTextField("");
		txtMK.setForeground(Color.LIGHT_GRAY);
		txtMK.setColumns(10);
		txtMK.setBounds(37, 136, 286, 35);
		panel.add(txtMK);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(48, 90, 46, 14);
		panel.add(lblNewLabel_1);
		
		;
		
		 btnLogIn_1 = new JButton("Đăng Nhập");
		btnLogIn_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogIn_1.setForeground(new Color(248, 248, 255));
		btnLogIn_1.setBackground(new Color(100, 149, 237));
		btnLogIn_1.setBounds(37, 181, 286, 37);
		panel.add(btnLogIn_1);
		
		 btnLogIn_1_1 = new JButton("Quên Mật Khẩu ");
		btnLogIn_1_1.setForeground(new Color(0, 0, 0));
		btnLogIn_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogIn_1_1.setBackground(new Color(245,245,245));
		btnLogIn_1_1.setBounds(37, 228, 286, 37);
		btnLogIn_1_1.setBorder(null);
		panel.add(btnLogIn_1_1);
		 
		btnLogIn_1.addActionListener(this);
		btnLogIn_1_1.addActionListener(this);
	}

	 @Override
	    public void actionPerformed(ActionEvent e) {
	        Object o = e.getSource();
	        if (o.equals(btnLogIn_1)) {
	            String tenTK = textTaiKhoan.getText().trim();
	            String sdt = txtMK.getText().trim();

	            String result = dao_nv.kiemTraDangNhap(tenTK, sdt);

	            if (result.equals("Đăng nhập thành công.")) {
	                // Lưu thông tin nhân viên vào đối tượng nhanVienHienTai
	                nhanVienHienTai = dao_nv.findNhanVienById(tenTK);
	                openTrangChu();
	            } else {
	                JOptionPane.showMessageDialog(this, result);
	            }
	        }
	        if (o.equals(btnLogIn_1_1)) {
	            showMaNhanVienDialog(); // Gọi hàm hiển thị dialog
	        }
	    }

	    
	

	private void showMaNhanVienDialog() {
	    // Tạo dialog
	    JDialog dialog = new JDialog(this, "Nhập mã nhân viên", true);
	    dialog.setLayout(new FlowLayout());
	    
	    // Tạo label và text field
	    JLabel lblMaNhanVien = new JLabel("Nhập mã nhân viên:");
	    JTextField txtMaNhanVien = new JTextField(15); // Kích thước 15 ký tự
	    
	    // Tạo nút xác nhận
	    JButton btnSubmit = new JButton("Xác nhận");
	    btnSubmit.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	            String maNhanVien = txtMaNhanVien.getText().trim();
	            if (!maNhanVien.isEmpty()) {
	                String result = dao_nv.guiMaVeSDT(maNhanVien);
	                JOptionPane.showMessageDialog(dialog, result);
	                dialog.dispose(); // Đóng dialog
	            } else {
	                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập mã nhân viên.");
	            }
	        }
	    });
	    
	    // Thêm các thành phần vào dialog
	    dialog.add(lblMaNhanVien);
	    dialog.add(txtMaNhanVien);
	    dialog.add(btnSubmit);
	    
	    // Cài đặt kích thước và hiển thị dialog
	    dialog.setSize(300, 150);
	    dialog.setLocationRelativeTo(this); // Đặt vị trí dialog ở giữa màn hình
	    dialog.setVisible(true); // Hiển thị dialog
	
	}

	public void openTrangChu() {
        TrangChu_GUI trangChu = new TrangChu_GUI();
        trangChu.setVisible(true);
	}
	
}
