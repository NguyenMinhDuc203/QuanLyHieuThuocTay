package gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

import dao.NhanVien_DAO;
import entity.NhanVien;
import gui.BanHang_GUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class DangNhap_GUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField textTaiKhoan;
    private JButton btnLogIn_1;
    private JButton btnLogIn_1_1;
    private NhanVien_DAO dao_nv = new NhanVien_DAO();
    private JTextField txtMaNhanVien;
    public NhanVien nhanVienHienTai;
    private JPasswordField txtMK;

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

        // Sử dụng BackgroundPanel cho contentPane
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);  // Đảm bảo không có layout tự động
        setContentPane(contentPane);
        
                // Thêm JLabel để làm ảnh nền phía sau ô đăng nhập
                JLabel backgroundLabel = new JLabel(new ImageIcon("/gui/background.jpg"));
                backgroundLabel.setBounds(0, 0, 964, 596);  // Đảm bảo ảnh phủ toàn bộ màn hình
                contentPane.add(backgroundLabel);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245, 200));  // Màu nền có độ trong suốt để nhìn thấy ảnh nền
        panel.setBounds(300, 148, 349, 277);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(26, 133, 94));
        panel_1.setBounds(0, 0, 349, 56);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Đăng Nhập");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel.setBounds(122, 11, 112, 31);
        panel_1.add(lblNewLabel);

        textTaiKhoan = new JTextField("");
        textTaiKhoan.setToolTipText("Mã nhân viên");
        textTaiKhoan.setForeground(new Color(0, 0, 0));
        textTaiKhoan.setBounds(37, 80, 286, 35);
        panel.add(textTaiKhoan);
        textTaiKhoan.setColumns(10);

        btnLogIn_1 = new JButton("Đăng Nhập");
        btnLogIn_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnLogIn_1.setForeground(new Color(248, 248, 255));
        btnLogIn_1.setBackground(new Color(26, 133, 94));
        btnLogIn_1.setBounds(103, 201, 157, 37);
        panel.add(btnLogIn_1);

        btnLogIn_1_1 = new JButton("Quên Mật Khẩu? ");
        btnLogIn_1_1.setForeground(new Color(0, 0, 0));
        btnLogIn_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnLogIn_1_1.setBackground(new Color(245,245,245));
        btnLogIn_1_1.setBounds(217, 165, 122, 25);
        btnLogIn_1_1.setBorder(null);
        panel.add(btnLogIn_1_1);

        txtMK = new JPasswordField();
        txtMK.setToolTipText("Mật khẩu");
        txtMK.setForeground(new Color(0, 0, 0));
        txtMK.setBounds(37, 130, 286, 35);
        panel.add(txtMK);

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
                // Thiết lập mã nhân viên đăng nhập thông qua dao_nv
                BanHang_GUI.maNVDangNhap = tenTK;
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
        dialog.getContentPane().setLayout(new FlowLayout());

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
        dialog.getContentPane().add(lblMaNhanVien);
        dialog.getContentPane().add(txtMaNhanVien);
        dialog.getContentPane().add(btnSubmit);

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
