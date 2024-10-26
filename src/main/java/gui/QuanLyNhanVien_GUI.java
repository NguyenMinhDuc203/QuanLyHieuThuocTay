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



public class QuanLyNhanVien_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_5;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyNhanVien_GUI frame = new QuanLyNhanVien_GUI();
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
	public QuanLyNhanVien_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1423, 912); 
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
				ImageIcon icon = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/house-solid.png"));
				Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				mnNewMenu.setIcon(new ImageIcon(scaledImage));
				menuBar.add(mnNewMenu);

				
				JMenu mnNewMenu_1 = new JMenu(" Quản Lý");
				mnNewMenu_1.setOpaque(true);
				mnNewMenu_1.setBackground(new Color(118, 209, 228));
				mnNewMenu_1.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
				mnNewMenu_1.setForeground(Color.BLACK);
ImageIcon icon1 = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/list-check-solid.png"));
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
				ImageIcon icon2_1 = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/cart-shopping-solid.png"));
				Image scaledImage2_1 = icon2_1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				mnNewMenu_2_1.setIcon(new ImageIcon(scaledImage2_1));
				menuBar.add(Box.createHorizontalStrut(30));
				menuBar.add(mnNewMenu_2_1);
				
				JMenu mnNewMenu_2 = new JMenu(" Thống Kê");
				mnNewMenu_2.setBackground(new Color(118, 209, 228));
				mnNewMenu_2.setOpaque(true);
				mnNewMenu_2.setForeground(Color.BLACK);
				mnNewMenu_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
				ImageIcon icon2 = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/clipboard-solid.png"));
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
ImageIcon icon2_2 = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/circle-question-solid.png"));
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
				ImageIcon iconBt = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/arrow-from-bracket-solid.png"));
				Image scaledImageBt = iconBt.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(226, 250, 252));
				panel.setBounds(0, 69, 1596, 866);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("QUẢN LÝ NHÂN VIÊN ");
				lblNewLabel.setForeground(Color.BLUE);
				lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
				lblNewLabel.setBounds(97, 22, 512, 70);
				ImageIcon poster = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/poster.png"));
				Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
				imageLabel.setBounds(10, 11, 77, 81);
				panel.add(imageLabel);
				panel.add(lblNewLabel);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(220, 220, 220));
				panel_1.setBounds(9, 121, 1386, 283);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(505, 31, 352, 30);
				panel_1.add(textField_1);
				
				textField_2 = new JTextField();
				textField_2.setColumns(10);
				textField_2.setBounds(973, 31, 352, 30);
				panel_1.add(textField_2);
				
				textField_3 = new JTextField();
				textField_3.setColumns(10);
				textField_3.setBounds(23, 31, 352, 30);
				panel_1.add(textField_3);
				
				textField_4 = new JTextField();
				textField_4.setColumns(10);
				textField_4.setBounds(23, 98, 352, 30);
				panel_1.add(textField_4);
				
				textField_6 = new JTextField();
				textField_6.setColumns(10);
				textField_6.setBounds(505, 98, 352, 30);
				panel_1.add(textField_6);
textField_7 = new JTextField();
				textField_7.setColumns(10);
				textField_7.setBounds(505, 171, 352, 30);
				panel_1.add(textField_7);
				
				textField_8 = new JTextField();
				textField_8.setColumns(10);
				textField_8.setBounds(505, 235, 352, 30);
				panel_1.add(textField_8);
				
				textField_9 = new JTextField();
				textField_9.setColumns(10);
				textField_9.setBounds(973, 98, 352, 30);
				panel_1.add(textField_9);
				
				textField_10 = new JTextField();
				textField_10.setColumns(10);
				textField_10.setBounds(973, 171, 352, 30);
				panel_1.add(textField_10);
				
				textField_11 = new JTextField();
				textField_11.setColumns(10);
				textField_11.setBounds(973, 235, 352, 30);
				panel_1.add(textField_11);
				
				JLabel lblTenNV = new JLabel("Tên Nhân Viên");
				lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblTenNV.setBounds(23, 12, 126, 14);
				panel_1.add(lblTenNV);
				
				JLabel lblSDT = new JLabel("Số Điện Thoại ");
				lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblSDT.setBounds(23, 82, 126, 14);
				panel_1.add(lblSDT);
				
				JLabel lblSDT_1 = new JLabel("Giới Tính");
				lblSDT_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblSDT_1.setBounds(23, 152, 126, 14);
				panel_1.add(lblSDT_1);
				
				JRadioButton rdbtnNam = new JRadioButton("Nam");
				rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 14));
				rdbtnNam.setBackground(new Color(220, 220, 220));
				rdbtnNam.setBounds(23, 173, 53, 23);
				panel_1.add(rdbtnNam);
				
				JRadioButton rdbNư = new JRadioButton("Nữ");
				rdbNư.setFont(new Font("Tahoma", Font.PLAIN, 14));
				rdbNư.setBackground(new Color(220, 220, 220));
				rdbNư.setBounds(96, 173, 109, 23);
				panel_1.add(rdbNư);
				
				JLabel lblNgaysinh = new JLabel("Ngày Sinh");
				lblNgaysinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNgaysinh.setBounds(505, 11, 126, 16);
				panel_1.add(lblNgaysinh);
				
				JLabel lblNgayVaolam = new JLabel("Ngày Vào Làm");
				lblNgayVaolam.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNgayVaolam.setBounds(505, 77, 126, 24);
				panel_1.add(lblNgayVaolam);
				
				JLabel lblLngCnBnlblLngCnBn = new JLabel("Lương Căn Bản");
				lblLngCnBnlblLngCnBn.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblLngCnBnlblLngCnBn.setBounds(505, 144, 126, 30);
				panel_1.add(lblLngCnBnlblLngCnBn);
				
				JLabel lblChucVu = new JLabel("Chức Vụ");
				lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblChucVu.setBounds(505, 214, 126, 24);
				panel_1.add(lblChucVu);
				
				JLabel lblCmnd = new JLabel("CMND");
				lblCmnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblCmnd.setBounds(974, 13, 126, 16);
				panel_1.add(lblCmnd);
				
				JLabel lblTrinhDo = new JLabel("Trình Độ");
lblTrinhDo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblTrinhDo.setBounds(973, 81, 126, 16);
				panel_1.add(lblTrinhDo);
				
				JLabel lblDiaChi = new JLabel("Địa Chỉ");
				lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDiaChi.setBounds(973, 151, 126, 16);
				panel_1.add(lblDiaChi);
				
				JLabel lblEmail = new JLabel("Email");
				lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblEmail.setBounds(973, 218, 126, 16);
				panel_1.add(lblEmail);
				
				JMenu mnNewMenu_2_1_1 = new JMenu("Thêm\r\n");
				mnNewMenu_2_1_1.setOpaque(true);
				mnNewMenu_2_1_1.setForeground(Color.BLACK);
				
				mnNewMenu_2_1_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				mnNewMenu_2_1_1.setBackground(new Color(118, 209, 228));
				mnNewMenu_2_1_1.setBounds(9, 429, 120, 45);
				ImageIcon iconThem = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/4993253681582956831-128.png"));
				Image scaledImageThem = iconThem.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				mnNewMenu_2_1_1.setIcon(new ImageIcon(scaledImageThem));
				panel.add(mnNewMenu_2_1_1);
				
				JMenu mnNewMenu_2_1_2 = new JMenu("Xóa");
				mnNewMenu_2_1_2.setOpaque(true);
				mnNewMenu_2_1_2.setForeground(Color.BLACK);
				mnNewMenu_2_1_2.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				mnNewMenu_2_1_2.setBackground(new Color(118, 209, 228));
				mnNewMenu_2_1_2.setBounds(185, 429, 113, 45);
				ImageIcon iconXoa = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/320632131667326703-128.png"));
				Image scaledImageXoa = iconXoa.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				mnNewMenu_2_1_2.setIcon(new ImageIcon(scaledImageXoa));
				panel.add(mnNewMenu_2_1_2);
				
				JMenu mnNewMenu_2_1_3 = new JMenu("Sửa");
				mnNewMenu_2_1_3.setOpaque(true);
				mnNewMenu_2_1_3.setForeground(Color.BLACK);
				mnNewMenu_2_1_3.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				mnNewMenu_2_1_3.setBackground(new Color(118, 209, 228));
				mnNewMenu_2_1_3.setBounds(361, 429, 113, 45);
				ImageIcon iconSua = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/setting-icon.png"));
				Image scaledImageSua = iconSua.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				mnNewMenu_2_1_3.setIcon(new ImageIcon(scaledImageSua));
				panel.add(mnNewMenu_2_1_3);
				
				JMenu mnNewMenu_2_1_4 = new JMenu("Xóa trắng ");
				mnNewMenu_2_1_4.setOpaque(true);
				mnNewMenu_2_1_4.setForeground(Color.BLACK);
				mnNewMenu_2_1_4.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				mnNewMenu_2_1_4.setBackground(new Color(118, 209, 228));
				mnNewMenu_2_1_4.setBounds(536, 429, 152, 45);
				ImageIcon iconXT = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/calendar-remove-icon.png"));
				Image scaledImageXT = iconXT.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				mnNewMenu_2_1_4.setIcon(new ImageIcon(scaledImageXT));
				panel.add(mnNewMenu_2_1_4);
				
				JMenu mnNewMenu_2_1_5 = new JMenu("Lưu");
				mnNewMenu_2_1_5.setOpaque(true);
				mnNewMenu_2_1_5.setForeground(Color.BLACK);
				mnNewMenu_2_1_5.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				mnNewMenu_2_1_5.setBackground(new Color(118, 209, 228));
				mnNewMenu_2_1_5.setBounds(748, 429, 105, 45);
				ImageIcon iconLuu = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/save-icon.png"));
				Image scaledImageLuu = iconLuu.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				mnNewMenu_2_1_5.setIcon(new ImageIcon(scaledImageLuu));
				panel.add(mnNewMenu_2_1_5);
				
				textField_5 = new JTextField();
				textField_5.setColumns(10);
				textField_5.setBounds(1077, 442, 318, 32);
				panel.add(textField_5);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 495, 1386, 305);
				panel.add(scrollPane);
				
				table = new JTable();
				table.setFont(new Font("Tahoma", Font.PLAIN, 13));
				table.setBackground(new Color(220, 220, 220));
				table.setRowHeight(40);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
					},
					new String[] {
						"M\u00C3 NV", "T\u00EAn NV", "S\u0110T", "Gi\u1EDBi T\u00EDnh", "Tu\u1ED5i", "Ch\u1EE9c v\u1EE5", "L\u01B0\u01A1ng"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, Integer.class, Object.class, Integer.class, String.class, Double.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				scrollPane.setViewportView(table);
				
				JLabel lblNhpMNhn = new JLabel("Nhập mã nhân Viên ");
				lblNhpMNhn.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNhpMNhn.setBounds(1077, 415, 126, 26);
				panel.add(lblNhpMNhn);
				
				JMenu btThoat = new JMenu("Thoát");
				btThoat.setOpaque(true);
				btThoat.setForeground(Color.BLACK);
				btThoat.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btThoat.setBackground(new Color(118, 209, 228));
				btThoat.setBounds(921, 429, 120, 45);
				ImageIcon iconThoat = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/exit-icon.png"));
				Image scaledImageThoat = iconThoat.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				btThoat.setIcon(new ImageIcon(scaledImageThoat));
				panel.add(btThoat);
	}
}