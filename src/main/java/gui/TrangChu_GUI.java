package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import entity.NhanVien;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.RootPaneContainer;


public class TrangChu_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
    private NhanVien nhanVienHienTai;


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
	 public TrangChu_GUI(NhanVien nhanVienHienTai) {
	        this.nhanVienHienTai = nhanVienHienTai;
	        // Các thành phần khác của giao diện và logic
	    }


	/**
	 * Create the frame.
	 */
	public TrangChu_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080); 
        setSize(1920,1080);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(26, 133, 94));
        contentPane.setForeground(SystemColor.window);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Menu
		JMenuBar menuBar = createMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setOpaque(true);
		menuBar.setBackground(new Color(26, 133, 94));
		menuBar.setBounds(0, 0, 1396, 70);
		contentPane.add(menuBar);
		ImageIcon iconBt = new ImageIcon(TrangChu_GUI.class.getResource("/gui/arrow-right-from-bracket-solid.png"));
		Image scaledImageBt = iconBt.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		
		//Trang Chủ
		JPanel panel = new JPanel();
		panel.setBackground(new Color(226, 250, 252));
		panel.setBounds(0, 69, 1201, 833);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HỆ THỐNG QUẢN LÝ HIỆU THUỐC ABC");
		lblNewLabel.setForeground(new Color(10, 69, 23));
		lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
		lblNewLabel.setBounds(161, 33, 880, 70);
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(161, 114, 843, 629);
		ImageIcon poster = new ImageIcon(TrangChu_GUI.class.getResource("/gui/poster.png"));
		Image scaledPoster = poster.getImage().getScaledInstance(843, 629, Image.SCALE_SMOOTH);
		panel.add(panel_2);
		JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
		panel_2.add(imageLabel);
		

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(26, 133, 94));
		panel_1.setBounds(1201, 69, 403, 833);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JTextPane txtpnCnaDng = new JTextPane();
		txtpnCnaDng.setEditable(false);
		txtpnCnaDng.setForeground(new Color(255, 255, 255));
		txtpnCnaDng.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtpnCnaDng.setBackground(new Color(26, 133, 94));
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
		txtpnCnNguyn.setEditable(false);
		txtpnCnNguyn.setForeground(new Color(255, 255, 255));
		txtpnCnNguyn.setText("CN2: 384 Nguyễn Oanh , Phường 6, Quận Gò Vấp, Thành phố Hồ Chí Minh");
		txtpnCnNguyn.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtpnCnNguyn.setBackground(new Color(26, 133, 94));
		txtpnCnNguyn.setBounds(55, 229, 253, 72);
		panel_1.add(txtpnCnNguyn);
		
		JTextPane txtpnCnaDng_1_1 = new JTextPane();
		txtpnCnaDng_1_1.setEditable(false);
		txtpnCnaDng_1_1.setForeground(new Color(255, 255, 255));
		txtpnCnaDng_1_1.setText("CN3: 39 Phan Văn Trị, Phường7, Quận Gò Vấp, Thành phố Hồ Chí Minh");
		txtpnCnaDng_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtpnCnaDng_1_1.setBackground(new Color(26, 133, 94));
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
		txtpnLinH.setForeground(new Color(255, 255, 255));
		txtpnLinH.setText("LIÊN HỆ");
		txtpnLinH.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		txtpnLinH.setBackground(new Color(26, 133, 94));
		txtpnLinH.setBounds(29, 518, 279, 46);
		panel_1.add(txtpnLinH);
		
		JTextPane txtpnCnaDng_1_1_1 = new JTextPane();
		txtpnCnaDng_1_1_1.setEditable(false);
		txtpnCnaDng_1_1_1.setForeground(new Color(255, 255, 255));
		txtpnCnaDng_1_1_1.setText("19001080");
		txtpnCnaDng_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtpnCnaDng_1_1_1.setBackground(new Color(26, 133, 94));
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
		txtpnCnaDng_1_1_1_1.setEditable(false);
		txtpnCnaDng_1_1_1_1.setForeground(new Color(255, 255, 255));
		txtpnCnaDng_1_1_1_1.setText("ABC@gmail.com");
		txtpnCnaDng_1_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtpnCnaDng_1_1_1_1.setBackground(new Color(26, 133, 94));
		txtpnCnaDng_1_1_1_1.setBounds(55, 624, 253, 34);
		panel_1.add(txtpnCnaDng_1_1_1_1);
		
		JTextPane txtpnCnaDng_1_1_1_1_1 = new JTextPane();
		txtpnCnaDng_1_1_1_1_1.setEditable(false);
		txtpnCnaDng_1_1_1_1_1.setForeground(new Color(255, 255, 255));
		txtpnCnaDng_1_1_1_1_1.setText("NhathuocABC.com/");
		txtpnCnaDng_1_1_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtpnCnaDng_1_1_1_1_1.setBackground(new Color(26, 133, 94));
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
		
		JTextPane txtpnChiNhnhTrn = new JTextPane();
		txtpnChiNhnhTrn.setEditable(false);
		txtpnChiNhnhTrn.setForeground(new Color(255, 255, 255));
		txtpnChiNhnhTrn.setBounds(29, 52, 279, 70);
		panel_1.add(txtpnChiNhnhTrn);
		txtpnChiNhnhTrn.setBackground(new Color(26, 133, 94));
		txtpnChiNhnhTrn.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		txtpnChiNhnhTrn.setText("CHI NHÁNH TRÊN TOÀN QUỐC:");
		
		
		JButton btnNewButton = new JButton("Đăng Xuất");
		btnNewButton.setBounds(1393, 0, 211, 70);
		contentPane.add(btnNewButton);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		btnNewButton.setBackground(new Color(26, 133, 94));
		btnNewButton.setOpaque(true);
		btnNewButton.setIcon(new ImageIcon(scaledImageBt));
		btnNewButton.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		btnNewButton.setHorizontalTextPosition(SwingConstants.LEFT);
		
	}
	public JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    menuBar.setBorderPainted(false);
    menuBar.setOpaque(true);
    menuBar.setBackground(new Color(26, 133, 94));

 // Menu Trang Chủ
    JMenu homeMenu = createMenu("Trang Chủ", "/gui/house-solid.png");
    menuBar.add(homeMenu);
    
    // Menu Quản Lý
    JMenuItem manageMenuItem1 = createMenuItem("Sản Phẩm");
    JMenuItem manageMenuItem2 = createMenuItem("Nhân Viên");
    JMenuItem manageMenuItem3 = createMenuItem("Khách Hàng");

    JMenu manageMenu = createMenu("Quản Lý", "/gui/list-check-solid.png");
    manageMenu.add(manageMenuItem1);
    manageMenu.add(manageMenuItem2);
    manageMenu.add(manageMenuItem3);
    menuBar.add(manageMenu);

    // Menu Bán Hàng
    JMenu salesMenu = createMenu("Bán Hàng", "/gui/cart-shopping-solid.png");
    menuBar.add(salesMenu);

    // Menu Thống Kê
    JMenuItem statsMenuItem1 = createMenuItem("Doanh Số");
    JMenuItem statsMenuItem2 = createMenuItem("Nhân Viên");
    JMenuItem statsMenuItem3 = createMenuItem("Khách Hàng");
    JMenuItem statsMenuItem4 = createMenuItem("Sản Phẩm");
    
    JMenu statsMenu = createMenu("Thống Kê", "/gui/clipboard-solid.png");
    statsMenu.add(statsMenuItem1);
    statsMenu.add(statsMenuItem2);
    statsMenu.add(statsMenuItem3);
    statsMenu.add(statsMenuItem4);
    menuBar.add(statsMenu);
    
    // Menu Tra Cứu
    JMenuItem searchMenuItem1 = createMenuItem("Sản Phẩm");
    JMenuItem searchMenuItem2 = createMenuItem("Nhân Viên");
    JMenuItem searchMenuItem3 = createMenuItem("Khách Hàng");
    JMenuItem searchMenuItem4 = createMenuItem("Hóa Đơn");
    
    JMenu searchMenu = createMenu("Tra Cứu", "/gui/circle-question-solid.png");
    searchMenu.add(searchMenuItem1);
    searchMenu.add(searchMenuItem2);
    searchMenu.add(searchMenuItem3);
    searchMenu.add(searchMenuItem4);
    menuBar.add(searchMenu);

    // **Sự kiện cho các nút trong menu (gộp chung trong một hàm xử lý)**

    searchMenuItem1.addActionListener(createMenuActionListener(this, TraCuuSanPham_GUI.class));
    searchMenuItem2.addActionListener(createMenuActionListener(this, TraCuuNhanVien_GUI.class));
    searchMenuItem3.addActionListener(createMenuActionListener(this, TraCuuKhachHang_GUI.class));
    searchMenuItem4.addActionListener(createMenuActionListener(this, TraCuuHoaDon_GUI.class));
    
  //  salesMenu.addMouseListener(createMenuMouseAdapter(this, BanHang_GUI.class));
    homeMenu.addMouseListener(createMenuMouseAdapter(this, TrangChu_GUI.class));
    
    manageMenuItem1.addActionListener(createMenuActionListener(this, QuanLySanPham_GUI.class));
    manageMenuItem2.addActionListener(createMenuActionListener(this, QuanLyNhanVien_GUI.class));
    manageMenuItem3.addActionListener(createMenuActionListener(this, QuanLyKhachHang_GUI.class));

    statsMenuItem1.addActionListener(createMenuActionListener(this, ThongKeDoanhSo_GUI.class));
    statsMenuItem2.addActionListener(createMenuActionListener(this, ThongKeNhanVien_GUI.class));
    statsMenuItem3.addActionListener(createMenuActionListener(this, ThongKeKhachHang_GUI.class));
    statsMenuItem4.addActionListener(createMenuActionListener(this, ThongKeSanPham_GUI.class));

    return menuBar;
}
	// Hàm chung xử lý sự kiện click chuột cho JMenu
	public MouseAdapter createMenuMouseAdapter(JFrame frame, Class<?> guiClass) {
	    return new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            System.out.println("Đã vào mouseClicked"); // Kiểm tra sự kiện mouseClicked

	            try {
	                System.out.println("Đang khởi tạo giao diện: " + guiClass.getName());

	                // Tạo đối tượng GUI mới từ class truyền vào
	                Object guiInstance = guiClass.getDeclaredConstructor().newInstance();
	                System.out.println("Khởi tạo đối tượng thành công");

	                // Kiểm tra nếu guiInstance là một JFrame, thì hiển thị nó
	                if (guiInstance instanceof JFrame) {
	                    JFrame newFrame = (JFrame) guiInstance;
	                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng cửa sổ hiện tại
	                    newFrame.setSize(1920, 1080);  // Kích thước cửa sổ mới
	                    newFrame.setLocationRelativeTo(null); // Căn giữa cửa sổ
	                    newFrame.setVisible(true);  // Hiển thị cửa sổ mới

	                    // Đóng cửa sổ hiện tại
	                    frame.dispose();  // Đảm bảo cửa sổ cũ được đóng lại khi chuyển sang cửa sổ mới
	                    System.out.println("Đã chuyển sang cửa sổ mới: " + guiClass.getName());
	                } else {
	                    System.out.println("Gui không phải là một JFrame, xử lý khác: " + guiClass.getName());
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                System.out.println("Lỗi khi khởi tạo giao diện: " + guiClass.getName());
	            }
	        }
	    };
	}



// Hàm chung xử lý sự kiện
	public ActionListener createMenuActionListener(JFrame frame, Class<?> guiClass) {
	    return new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("Đã vào actionPerformed"); // Kiểm tra xem có vào đây không
	            try {
	                System.out.println("Đang khởi tạo giao diện: " + guiClass.getName());

	                Object guiInstance = guiClass.getDeclaredConstructor().newInstance();
	                System.out.println("Khởi tạo đối tượng thành công");

	                if (guiInstance instanceof JFrame) {
	                    JFrame newFrame = (JFrame) guiInstance;
	                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                    newFrame.setSize(1920, 1080);
	                    newFrame.setLocationRelativeTo(null);
	                    newFrame.setVisible(true);

	                    // Đóng cửa sổ hiện tại
	                    frame.dispose();  // Đảm bảo cửa sổ cũ được đóng lại khi chuyển sang cửa sổ mới
	                    System.out.println("Đã chuyển sang cửa sổ mới: " + guiClass.getName());
	                } else {
	                    System.out.println("Gui không phải là một JFrame, xử lý khác: " + guiClass.getName());
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                System.out.println("Lỗi khi khởi tạo giao diện: " + guiClass.getName());
	            }
	        }
	    };
	}



	public JMenu createMenu(String title, String iconPath) {
        JMenu menu = new JMenu(" " + title + " ");
        menu.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        menu.setForeground(Color.WHITE);
        menu.setOpaque(true);
        menu.setBackground(new Color(26, 133, 94));

        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        menu.setIcon(new ImageIcon(scaledImage));
        menu.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        return menu;
    }
	public JMenuItem createMenuItem(String title) {
        JMenuItem menuItem = new JMenuItem(" " + title + " ");
        menuItem.setBackground(new Color(26, 133, 94));
        menuItem.setForeground(Color.WHITE);
        menuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        return menuItem;
    }
	public void openDangNhap() {
        DangNhap_GUI e = new DangNhap_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
}
