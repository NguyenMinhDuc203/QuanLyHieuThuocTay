package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JEditorPane;



public class TraCuuSanPham_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TraCuuSanPham_GUI frame = new TraCuuSanPham_GUI();
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
	public TraCuuSanPham_GUI() {
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
				menuBar.setBounds(0, 0, 1445, 70);
				contentPane.add(menuBar);
				
				JMenu mnNewMenu = new JMenu(" Trang Chủ");
				mnNewMenu.setOpaque(true);
				mnNewMenu.setBackground(SystemColor.activeCaption);
				mnNewMenu.setForeground(Color.BLACK);
				mnNewMenu.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
				ImageIcon icon = new ImageIcon(TraCuuSanPham_GUI.class.getResource("/gui/house-solid.png"));
				Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				mnNewMenu.setIcon(new ImageIcon(scaledImage));
				menuBar.add(mnNewMenu);

				
				JMenu mnNewMenu_1 = new JMenu(" Quản Lý");
				mnNewMenu_1.setOpaque(true);
				mnNewMenu_1.setBackground(new Color(118, 209, 228));
				mnNewMenu_1.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
				mnNewMenu_1.setForeground(Color.BLACK);
ImageIcon icon1 = new ImageIcon(TraCuuSanPham_GUI.class.getResource("/gui/list-check-solid.png"));
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
				ImageIcon icon2_1 = new ImageIcon(TraCuuSanPham_GUI.class.getResource("/gui/cart-shopping-solid.png"));
				Image scaledImage2_1 = icon2_1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				mnNewMenu_2_1.setIcon(new ImageIcon(scaledImage2_1));
				menuBar.add(Box.createHorizontalStrut(30));
				menuBar.add(mnNewMenu_2_1);
				
				JMenu mnNewMenu_2 = new JMenu(" Thống Kê");
				mnNewMenu_2.setBackground(new Color(118, 209, 228));
				mnNewMenu_2.setOpaque(true);
				mnNewMenu_2.setForeground(Color.BLACK);
				mnNewMenu_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
				ImageIcon icon2 = new ImageIcon(TraCuuSanPham_GUI.class.getResource("/gui/clipboard-solid.png"));
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
ImageIcon icon2_2 = new ImageIcon(TraCuuSanPham_GUI.class.getResource("/gui/circle-question-solid.png"));
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
				mnNewMenu_2_2.setOpaque(true);
				ImageIcon iconBt = new ImageIcon(TraCuuSanPham_GUI.class.getResource("/gui/arrow-from-bracket-solid.png"));
				Image scaledImageBt = iconBt.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(226, 250, 252));
				panel.setBounds(0, 69, 1445, 833);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("TRA CỨU SẢN PHẨM\r\n");
				lblNewLabel.setForeground(Color.BLUE);
				lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
				lblNewLabel.setBounds(97, 22, 703, 70);
				ImageIcon poster = new ImageIcon(TraCuuSanPham_GUI.class.getResource("/gui/poster.png"));
				Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
				imageLabel.setBounds(10, 11, 77, 81);
				panel.add(imageLabel);
				panel.add(lblNewLabel);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(169, 204, 193)); // Thiết lập màu nền để khớp với ảnh
				panel_1.setBounds(79, 102, 1265, 420); // Tăng chiều cao panel để có khoảng cách rộng hơn giữa các hàng
				panel.add(panel_1);
				panel_1.setLayout(null);

				// Thiết lập cỡ chữ lớn hơn
				Font font = new Font("Tahoma", Font.PLAIN, 18);

				// mãSảnPhẩm
				JLabel lblMaSanPham = new JLabel("Mã Sản Phẩm");
				lblMaSanPham.setFont(font);
				lblMaSanPham.setBounds(65, 34, 150, 30);
				panel_1.add(lblMaSanPham);

				JTextField textField_MaSanPham = new JTextField();
				textField_MaSanPham.setBounds(220, 34, 200, 30);
				panel_1.add(textField_MaSanPham);

				// tênSảnPhẩm
				JLabel lblTenSanPham = new JLabel("Tên Sản Phẩm");
				lblTenSanPham.setFont(font);
				lblTenSanPham.setBounds(65, 84, 150, 30);
				panel_1.add(lblTenSanPham);

				JTextField textField_TenSanPham = new JTextField();
				textField_TenSanPham.setBounds(220, 84, 200, 30);
				panel_1.add(textField_TenSanPham);

				// giáBán
				JLabel lblGiaBan = new JLabel("Giá Bán");
				lblGiaBan.setFont(font);
				lblGiaBan.setBounds(65, 134, 150, 30);
				panel_1.add(lblGiaBan);

				JTextField textField_GiaBan = new JTextField();
				textField_GiaBan.setBounds(220, 134, 200, 30);
				panel_1.add(textField_GiaBan);

				// côngDụng
				JLabel lblCongDung = new JLabel("Công Dụng");
				lblCongDung.setFont(font);
				lblCongDung.setBounds(65, 184, 150, 30);
				panel_1.add(lblCongDung);

				JTextField textField_CongDung = new JTextField();
				textField_CongDung.setBounds(220, 184, 200, 30);
				panel_1.add(textField_CongDung);

				// hạnSửDụng
				JLabel lblHanSuDung = new JLabel("Hạn Sử Dụng");
				lblHanSuDung.setFont(font);
				lblHanSuDung.setBounds(696, 34, 150, 30);
				panel_1.add(lblHanSuDung);

				JTextField textField_HanSuDung = new JTextField();
				textField_HanSuDung.setBounds(901, 38, 200, 30);
				panel_1.add(textField_HanSuDung);

				// bảoQuản
				JLabel lblBaoQuan = new JLabel("Bảo Quản");
				lblBaoQuan.setFont(font);
				lblBaoQuan.setBounds(696, 84, 150, 30);
				panel_1.add(lblBaoQuan);

				JTextField textField_BaoQuan = new JTextField();
				textField_BaoQuan.setBounds(901, 88, 200, 30);
				panel_1.add(textField_BaoQuan);

				// chốngChỉĐịnh
				JLabel lblChongChiDinh = new JLabel("Chống Chỉ Định");
				lblChongChiDinh.setFont(font);
				lblChongChiDinh.setBounds(696, 134, 150, 30);
				panel_1.add(lblChongChiDinh);

				JTextField textField_ChongChiDinh = new JTextField();
				textField_ChongChiDinh.setBounds(901, 138, 200, 30);
				panel_1.add(textField_ChongChiDinh);

				// ngàySảnXuất
				JLabel lblNgaySanXuat = new JLabel("Ngày Sản Xuất");
				lblNgaySanXuat.setFont(font);
				lblNgaySanXuat.setBounds(696, 188, 150, 30);
				panel_1.add(lblNgaySanXuat);

				JTextField textField_NgaySanXuat = new JTextField();
				textField_NgaySanXuat.setBounds(901, 188, 200, 30);
				panel_1.add(textField_NgaySanXuat);

				// thànhPhần
				JLabel lblThanhPhan = new JLabel("Thành Phần");
				lblThanhPhan.setFont(font);
				lblThanhPhan.setBounds(65, 234, 150, 30);
				panel_1.add(lblThanhPhan);

				JTextField textField_ThanhPhan = new JTextField();
				textField_ThanhPhan.setBounds(220, 234, 200, 30);
				panel_1.add(textField_ThanhPhan);

				// sốLượngTồnKho
				JLabel lblSoLuongTonKho = new JLabel("Số Lượng Tồn Kho");
				lblSoLuongTonKho.setFont(font);
				lblSoLuongTonKho.setBounds(696, 234, 150, 30);
				panel_1.add(lblSoLuongTonKho);

				JTextField textField_SoLuongTonKho = new JTextField();
				textField_SoLuongTonKho.setBounds(901, 238, 200, 30);
				panel_1.add(textField_SoLuongTonKho);

				// ghiChú
				JLabel lblGhiChu = new JLabel("Ghi Chú");
				lblGhiChu.setFont(font);
				lblGhiChu.setBounds(65, 284, 150, 30);
				panel_1.add(lblGhiChu);

				JTextField textField_GhiChu = new JTextField();
				textField_GhiChu.setBounds(220, 274, 438, 44);
				panel_1.add(textField_GhiChu);

				// nhàSảnXuất
				JLabel lblNhaSanXuat = new JLabel("Nhà Sản Xuất");
				lblNhaSanXuat.setFont(font);
				lblNhaSanXuat.setBounds(65, 334, 150, 30);
				panel_1.add(lblNhaSanXuat);

				JTextField textField_NhaSanXuat = new JTextField();
				textField_NhaSanXuat.setBounds(220, 334, 200, 30);
				panel_1.add(textField_NhaSanXuat);

				// đơnVịTính
				JLabel lblDonViTinh = new JLabel("Đơn Vị Tính");
				lblDonViTinh.setFont(font);
				lblDonViTinh.setBounds(696, 284, 150, 30);
				panel_1.add(lblDonViTinh);

				JTextField textField_DonViTinh = new JTextField();
				textField_DonViTinh.setBounds(901, 288, 200, 30);
				panel_1.add(textField_DonViTinh);

				// thuếGTGT
				JLabel lblThueGTGT = new JLabel("Thuế GTGT");
				lblThueGTGT.setFont(font);
				lblThueGTGT.setBounds(65, 384, 150, 30);
				panel_1.add(lblThueGTGT);

				JTextField textField_ThueGTGT = new JTextField();
				textField_ThueGTGT.setBounds(220, 384, 200, 30);
				panel_1.add(textField_ThueGTGT);

				// giáNhập
				JLabel lblGiaNhap = new JLabel("Giá Nhập");
				lblGiaNhap.setFont(font);
				lblGiaNhap.setBounds(696, 334, 150, 30);
				panel_1.add(lblGiaNhap);

				JTextField textField_GiaNhap = new JTextField();
				textField_GiaNhap.setBounds(901, 338, 200, 30);
				panel_1.add(textField_GiaNhap);

				ImageIcon iconThem = new ImageIcon(TraCuuSanPham_GUI.class.getResource("/GUI/4993253681582956831-128.png"));
				Image scaledImageThem = iconThem.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				ImageIcon iconXoa = new ImageIcon(TraCuuSanPham_GUI.class.getResource("/GUI/320632131667326703-128.png"));
				Image scaledImageXoa = iconXoa.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				ImageIcon iconSua = new ImageIcon(TraCuuSanPham_GUI.class.getResource("/GUI/setting-icon.png"));
				Image scaledImageSua = iconSua.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				ImageIcon iconXT = new ImageIcon(TraCuuSanPham_GUI.class.getResource("/GUI/calendar-remove-icon.png"));
				Image scaledImageXT = iconXT.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				ImageIcon iconLuu = new ImageIcon(TraCuuSanPham_GUI.class.getResource("/GUI/calendar-remove-icon.png"));
				Image scaledImageLuu = iconLuu.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				
				textField_5 = new JTextField();
				textField_5.setColumns(10);
				textField_5.setBounds(214, 533, 972, 32);
				panel.add(textField_5);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(36, 585, 1386, 179);
				panel.add(scrollPane);
				
				JTable table = new JTable();
				table.setForeground(Color.WHITE);
				table.setFont(new Font("Tahoma", Font.PLAIN, 13));
				table.setBackground(Color.LIGHT_GRAY);
				table.setRowHeight(40);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
					},
					new String[] {
						"STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Bán", "Công Dụng", "Hạn Sử Dụng", "Bảo Quản",
						"Chống Chỉ Định", "Ngày Sản Xuất", "Thành Phần", "Số Lượng Tồn Kho", "Ghi Chú",
						"Nhà Sản Xuất", "Đơn Vị Tính", "Thuế GTGT", "Giá Nhập"
					}
				) {
					Class[] columnTypes = new Class[] {
						Integer.class, String.class, String.class, Double.class, String.class, String.class,
						String.class, String.class, String.class, String.class, Integer.class,
						String.class, String.class, String.class, Double.class, Double.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.getColumnModel().getColumn(1).setPreferredWidth(100); // Cột "Mã Sản Phẩm"
				table.getColumnModel().getColumn(2).setPreferredWidth(100); // Cột "Tên Sản Phẩm"
				table.getColumnModel().getColumn(3).setPreferredWidth(100); // Cột "Giá Bán"
				scrollPane.setViewportView(table);

				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(36, 532, 152, 32);
				panel.add(comboBox);
				
				JButton btnNewButton = new JButton("Tìm Kiếm\r\n");
				btnNewButton.setBounds(1228, 532, 152, 32);
				panel.add(btnNewButton);
	}
}
