package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;


public class TrangChu_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangChu_GUI frame = new TrangChu_GUI();
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
	public TrangChu_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1440, 912); 
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setForeground(SystemColor.window);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Menu
		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		menuBar.setBackground(new Color(219, 244, 247));
		menuBar.setBounds(0, 0, 1255, 70);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu(" Trang Chủ");
		mnNewMenu.setOpaque(true);
		mnNewMenu.setBackground(SystemColor.activeCaption);
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		ImageIcon icon = new ImageIcon(TrangChu_GUI.class.getResource("/gui/house-solid.png"));
		Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu.setIcon(new ImageIcon(scaledImage));
		menuBar.add(mnNewMenu);

		
		JMenu mnNewMenu_1 = new JMenu(" Quản Lý");
		mnNewMenu_1.setOpaque(true);
		mnNewMenu_1.setBackground(new Color(118, 209, 228));
		mnNewMenu_1.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		mnNewMenu_1.setForeground(Color.BLACK);
		ImageIcon icon1 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/list-check-solid.png"));
		Image scaledImage1 = icon1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu_1.setIcon(new ImageIcon(scaledImage1));
		menuBar.add(Box.createHorizontalStrut(30));
		menuBar.add(mnNewMenu_1);
				
		JMenuItem mntmNewMenuItem = new JMenuItem("Sản Phẩm");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNhnVin = new JMenuItem("Nhân Viên");
		mntmNhnVin.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_1.add(mntmNhnVin);
		
		JMenuItem mntmKhchHng = new JMenuItem("Khách Hàng");
		mntmKhchHng.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_1.add(mntmKhchHng);
		
		JMenu mnNewMenu_2_1 = new JMenu(" Bán Hàng");
		mnNewMenu_2_1.setBackground(new Color(118, 209, 228));
		mnNewMenu_2_1.setOpaque(true);
		mnNewMenu_2_1.setForeground(Color.BLACK);
		mnNewMenu_2_1.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		ImageIcon icon2_1 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/cart-shopping-solid.png"));
		Image scaledImage2_1 = icon2_1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu_2_1.setIcon(new ImageIcon(scaledImage2_1));
		menuBar.add(Box.createHorizontalStrut(30));
		menuBar.add(mnNewMenu_2_1);
		
		JMenu mnNewMenu_2 = new JMenu(" Thống Kê");
		mnNewMenu_2.setBackground(new Color(118, 209, 228));
		mnNewMenu_2.setOpaque(true);
		mnNewMenu_2.setForeground(Color.BLACK);
		mnNewMenu_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		ImageIcon icon2 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/clipboard-solid.png"));
		Image scaledImage2 = icon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu_2.setIcon(new ImageIcon(scaledImage2));
		menuBar.add(Box.createHorizontalStrut(30));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Doanh Số");
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_1_3 = new JMenuItem("Nhân Viên");
		mntmNewMenuItem_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2.add(mntmNewMenuItem_1_3);
		
		JMenuItem mntmNewMenuItem_1_2 = new JMenuItem("Khách Hàng");
		mntmNewMenuItem_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2.add(mntmNewMenuItem_1_2);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("Sản Phẩm");
		mntmNewMenuItem_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2.add(mntmNewMenuItem_1_1);
		
		JMenu mnNewMenu_2_2 = new JMenu(" Tra Cứu");
		mnNewMenu_2_2.setBackground(new Color(118, 209, 228));
		mnNewMenu_2_2.setOpaque(true);
		mnNewMenu_2_2.setForeground(Color.BLACK);
		mnNewMenu_2_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		ImageIcon icon2_2 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/circle-question-solid.png"));
		Image scaledImage2_2 = icon2_2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu_2_2.setIcon(new ImageIcon(scaledImage2_2));
		menuBar.add(Box.createHorizontalStrut(30));
		menuBar.add(mnNewMenu_2_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Sản Phẩm");
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_2_2 = new JMenuItem("Nhân Viên");
		mntmNewMenuItem_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2_2.add(mntmNewMenuItem_2_2);
		
		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("Khách Hàng");
		mntmNewMenuItem_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2_2.add(mntmNewMenuItem_2_1);
		
		JButton btnNewButton = new JButton("Đăng Xuất");
		btnNewButton.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		btnNewButton.setBackground(new Color(118, 209, 228));
		mnNewMenu_2_2.setOpaque(true);
		btnNewButton.setBounds(1213, 0, 211, 70);
		ImageIcon iconBt = new ImageIcon(TrangChu_GUI.class.getResource("/gui/arrow-from-bracket-solid.png"));
		Image scaledImageBt = iconBt.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		btnNewButton.setIcon(new ImageIcon(scaledImageBt));
		btnNewButton.setHorizontalTextPosition(SwingConstants.LEFT);
		contentPane.add(btnNewButton);
		
		//Trang Chủ
		JPanel panel = new JPanel();
		panel.setBackground(new Color(226, 250, 252));
		panel.setBounds(0, 69, 1083, 833);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HỆ THỐNG QUẢN LÝ HIỆU THUỐC ABC");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
		lblNewLabel.setBounds(88, 33, 880, 70);
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(88, 131, 843, 629);
		ImageIcon poster = new ImageIcon(TrangChu_GUI.class.getResource("/gui/poster.png"));
		Image scaledPoster = poster.getImage().getScaledInstance(843, 629, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
		imageLabel.setBounds(88, 131, 843, 629);
		panel_2.add(imageLabel);
		panel.add(panel_2);
		

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(219, 244, 247));
		panel_1.setBounds(1084, 69, 340, 833);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JTextPane txtpnChiNhnhTrn = new JTextPane();
		txtpnChiNhnhTrn.setBackground(new Color(219, 244, 247));
		txtpnChiNhnhTrn.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		txtpnChiNhnhTrn.setText("CHI NHÁNH TRÊN TOÀN QUỐC:");
		txtpnChiNhnhTrn.setBounds(29, 38, 279, 70);
		panel_1.add(txtpnChiNhnhTrn);
		
		JTextPane txtpnCnaDng = new JTextPane();
		txtpnCnaDng.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtpnCnaDng.setBackground(new Color(219, 244, 247));
		txtpnCnaDng.setText("CN1: 392A Dương Quảng Hàm, Phường 05, Quận Gò Vấp, Thành phố Hồ Chí Minh");
		txtpnCnaDng.setBounds(55, 133, 253, 72);
		panel_1.add(txtpnCnaDng);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBounds(29, 142, 24, 46);
		ImageIcon iconLocal = new ImageIcon(TrangChu_GUI.class.getResource("/gui/location-dot-solid.png"));
		Image scaledLocal = iconLocal.getImage().getScaledInstance(16, 26, Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(scaledLocal));
		panel_1.add(lblNewLabel_1);
		
		JTextPane txtpnCnNguyn = new JTextPane();
		txtpnCnNguyn.setText("CN2: 384 Nguyễn Oanh , Phường 6, Quận Gò Vấp, Thành phố Hồ Chí Minh");
		txtpnCnNguyn.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtpnCnNguyn.setBackground(new Color(219, 244, 247));
		txtpnCnNguyn.setBounds(55, 229, 253, 72);
		panel_1.add(txtpnCnNguyn);
		
		JTextPane txtpnCnaDng_1_1 = new JTextPane();
		txtpnCnaDng_1_1.setText("CN3: 39 Phan Văn Trị, Phường7, Quận Gò Vấp, Thành phố Hồ Chí Minh");
		txtpnCnaDng_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtpnCnaDng_1_1.setBackground(new Color(219, 244, 247));
		txtpnCnaDng_1_1.setBounds(55, 332, 253, 72);
		panel_1.add(txtpnCnaDng_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setBounds(29, 240, 24, 46);
		lblNewLabel_1_1.setIcon(new ImageIcon(scaledLocal));
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_2.setBounds(29, 344, 24, 46);
		lblNewLabel_1_2.setIcon(new ImageIcon(scaledLocal));
		panel_1.add(lblNewLabel_1_2);
		
		JTextPane txtpnLinH = new JTextPane();
		txtpnLinH.setText("LIÊN HỆ");
		txtpnLinH.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		txtpnLinH.setBackground(new Color(219, 244, 247));
		txtpnLinH.setBounds(29, 518, 279, 46);
		panel_1.add(txtpnLinH);
		
		JTextPane txtpnCnaDng_1_1_1 = new JTextPane();
		txtpnCnaDng_1_1_1.setText("19001080");
		txtpnCnaDng_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtpnCnaDng_1_1_1.setBackground(new Color(219, 244, 247));
		txtpnCnaDng_1_1_1.setBounds(55, 575, 253, 34);
		panel_1.add(txtpnCnaDng_1_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("");
		lblNewLabel_1_2_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_2_1.setBounds(29, 584, 24, 26);
		ImageIcon iconPhone = new ImageIcon(TrangChu_GUI.class.getResource("/gui/phone-solid.png"));
		Image scaledPhone = iconPhone.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		lblNewLabel_1_2_1.setIcon(new ImageIcon(scaledPhone));
		panel_1.add(lblNewLabel_1_2_1);
		
		JTextPane txtpnCnaDng_1_1_1_1 = new JTextPane();
		txtpnCnaDng_1_1_1_1.setText("ABC@gmail.com");
		txtpnCnaDng_1_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtpnCnaDng_1_1_1_1.setBackground(new Color(219, 244, 247));
		txtpnCnaDng_1_1_1_1.setBounds(55, 624, 253, 34);
		panel_1.add(txtpnCnaDng_1_1_1_1);
		
		JTextPane txtpnCnaDng_1_1_1_1_1 = new JTextPane();
		txtpnCnaDng_1_1_1_1_1.setText("NhathuocABC.com/");
		txtpnCnaDng_1_1_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtpnCnaDng_1_1_1_1_1.setBackground(new Color(219, 244, 247));
		txtpnCnaDng_1_1_1_1_1.setBounds(55, 669, 253, 34);
		panel_1.add(txtpnCnaDng_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_2_1_1.setBounds(29, 632, 24, 26);
		ImageIcon iconMail = new ImageIcon(TrangChu_GUI.class.getResource("/gui/envelope-solid.png"));
		Image scaledMail = iconMail.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		lblNewLabel_1_2_1_1.setIcon(new ImageIcon(scaledMail));
		panel_1.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("");
		lblNewLabel_1_2_1_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_2_1_1_1.setBounds(29, 677, 24, 26);
		ImageIcon iconWeb = new ImageIcon(TrangChu_GUI.class.getResource("/gui/globe-solid.png"));
		Image scaledWeb = iconWeb.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		lblNewLabel_1_2_1_1_1.setIcon(new ImageIcon(scaledWeb));
		panel_1.add(lblNewLabel_1_2_1_1_1);
		
		//Actions Menu
		mnNewMenu.addActionListener(e -> openTrangChu());
		mntmNewMenuItem.addActionListener(e -> openQuanLySanPham());
		mntmNhnVin.addActionListener(e -> openQuanLyNhanVien());
		mntmKhchHng.addActionListener(e -> openQuanLyKhachHang());
		mnNewMenu_2_1.addActionListener(e -> openBanHang());
		mntmNewMenuItem_1.addActionListener(e -> openThongKeDoanhSo());
		mntmNewMenuItem_1_3.addActionListener(e -> openThongKeNhanVien());
		mntmNewMenuItem_1_2.addActionListener(e -> openThongKeKhachHang());
		mntmNewMenuItem_1_1.addActionListener(e -> openThongKeSanPham());
		mntmNewMenuItem_2.addActionListener(e -> openTraCuuSanPham());
		mntmNewMenuItem_2_2.addActionListener(e -> openTraCuuNhanVien());
		mntmNewMenuItem_2_1.addActionListener(e -> openTraCuuKhachHang());
		btnNewButton.addActionListener(e -> openDangNhap());
		
		this.setVisible(true);
	}
	
	public void openTrangChu() {
        TrangChu_GUI trangChu = new TrangChu_GUI();
        trangChu.setVisible(true);
    }
	
	public void openQuanLySanPham() {
        QuanLySanPham_GUI quanLySanPham = new QuanLySanPham_GUI();
        quanLySanPham.setVisible(true);
        this.setVisible(false);
    }
	public void openQuanLyNhanVien() {
        QuanLyNhanVien_GUI quanLyNhanVien = new QuanLyNhanVien_GUI();
        quanLyNhanVien.setVisible(true);
        this.setVisible(false);
    }
	public void openQuanLyKhachHang() {
        QuanLyKhachHang_GUI quanLyKhachHang = new QuanLyKhachHang_GUI();
        quanLyKhachHang.setVisible(true);
        this.setVisible(false);
    }
	public void openBanHang() {
        BanHang_GUI banHang = new BanHang_GUI();
        banHang.setVisible(true);
        this.setVisible(false);
    }
	public void openThongKeDoanhSo() {
        ThongKeDoanhSo_GUI thongKeDoanhSo = new ThongKeDoanhSo_GUI();
        thongKeDoanhSo.setVisible(true);
        this.setVisible(false);
    }
	public void openThongKeNhanVien() {
        ThongKeNhanVien_GUI thongKeNhanVien = new ThongKeNhanVien_GUI();
        thongKeNhanVien.setVisible(true);
        this.setVisible(false);
    }
	public void openThongKeKhachHang() {
        ThongKeKhachHang_GUI e = new ThongKeKhachHang_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
	public void openThongKeSanPham() {
        ThongKeSanPham_GUI e = new ThongKeSanPham_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
	public void openTraCuuSanPham() {
        TraCuuSanPham_GUI e = new TraCuuSanPham_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
	public void openTraCuuNhanVien() {
        TraCuuNhanVien_GUI e = new TraCuuNhanVien_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
	public void openTraCuuKhachHang() {
        TraCuuKhachHang_GUI e = new TraCuuKhachHang_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
	public void openDangNhap() {
        DangNhap_GUI e = new DangNhap_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
	
}
