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



public class TraCuuKhachHang_GUI extends JFrame {

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
					TraCuuKhachHang_GUI frame = new TraCuuKhachHang_GUI();
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
	public TraCuuKhachHang_GUI() {
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
				ImageIcon icon = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/gui/house-solid.png"));
				Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				mnNewMenu.setIcon(new ImageIcon(scaledImage));
				menuBar.add(mnNewMenu);

				
				JMenu mnNewMenu_1 = new JMenu(" Quản Lý");
				mnNewMenu_1.setOpaque(true);
				mnNewMenu_1.setBackground(new Color(118, 209, 228));
				mnNewMenu_1.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
				mnNewMenu_1.setForeground(Color.BLACK);
ImageIcon icon1 = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/gui/list-check-solid.png"));
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
				ImageIcon icon2_1 = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/gui/cart-shopping-solid.png"));
				Image scaledImage2_1 = icon2_1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				mnNewMenu_2_1.setIcon(new ImageIcon(scaledImage2_1));
				menuBar.add(Box.createHorizontalStrut(30));
				menuBar.add(mnNewMenu_2_1);
				
				JMenu mnNewMenu_2 = new JMenu(" Thống Kê");
				mnNewMenu_2.setBackground(new Color(118, 209, 228));
				mnNewMenu_2.setOpaque(true);
				mnNewMenu_2.setForeground(Color.BLACK);
				mnNewMenu_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
				ImageIcon icon2 = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/gui/clipboard-solid.png"));
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
ImageIcon icon2_2 = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/gui/circle-question-solid.png"));
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
				ImageIcon iconBt = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/gui/arrow-from-bracket-solid.png"));
				Image scaledImageBt = iconBt.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(226, 250, 252));
				panel.setBounds(0, 69, 1445, 833);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("TRA CỨU KHÁCH HÀNG");
				lblNewLabel.setForeground(Color.BLUE);
				lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
				lblNewLabel.setBounds(97, 22, 703, 70);
				ImageIcon poster = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/gui/poster.png"));
				Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
				imageLabel.setBounds(10, 11, 77, 81);
				panel.add(imageLabel);
				panel.add(lblNewLabel);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(255, 255, 255));
				panel_1.setBounds(231, 102, 903, 372);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				textField_3 = new JTextField();
				textField_3.setColumns(10);
				textField_3.setBounds(406, 80, 382, 41);
				panel_1.add(textField_3);
				
				textField_4 = new JTextField();
				textField_4.setColumns(10);
				textField_4.setBounds(406, 193, 382, 41);
				panel_1.add(textField_4);
				
				JLabel lblTenNV = new JLabel("Tên Khách Hàng\r\n");
				lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 23));
				lblTenNV.setBounds(127, 71, 264, 49);
				panel_1.add(lblTenNV);
				
				JLabel lblSDT = new JLabel("Số Điện Thoại ");
				lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 23));
				lblSDT.setBounds(132, 193, 264, 41);
				panel_1.add(lblSDT);
				
				JLabel lblSDT_1 = new JLabel("Giới Tính\r\n");
				lblSDT_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
				lblSDT_1.setBounds(132, 287, 126, 36);
				panel_1.add(lblSDT_1);
				
				JRadioButton rdbtnNam = new JRadioButton("Nam");
				rdbtnNam.setForeground(Color.BLACK);
				rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 23));
				rdbtnNam.setBackground(Color.WHITE);
				rdbtnNam.setBounds(406, 297, 109, 23);
				panel_1.add(rdbtnNam);
				
				JRadioButton rdbNư = new JRadioButton("Nữ");
				rdbNư.setForeground(Color.BLACK);
				rdbNư.setFont(new Font("Tahoma", Font.PLAIN, 23));
				rdbNư.setBackground(Color.WHITE);
				rdbNư.setBounds(570, 297, 109, 23);
				panel_1.add(rdbNư);
				ImageIcon iconThem = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/GUI/4993253681582956831-128.png"));
				Image scaledImageThem = iconThem.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				ImageIcon iconXoa = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/GUI/320632131667326703-128.png"));
				Image scaledImageXoa = iconXoa.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				ImageIcon iconSua = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/GUI/setting-icon.png"));
				Image scaledImageSua = iconSua.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				ImageIcon iconXT = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/GUI/calendar-remove-icon.png"));
				Image scaledImageXT = iconXT.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				ImageIcon iconLuu = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/GUI/calendar-remove-icon.png"));
				Image scaledImageLuu = iconLuu.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				
				textField_5 = new JTextField();
				textField_5.setColumns(10);
				textField_5.setBounds(214, 533, 972, 32);
				panel.add(textField_5);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(79, 585, 1294, 179);
				panel.add(scrollPane);
				
				table = new JTable();
				table.setForeground(Color.WHITE);
				table.setFont(new Font("Tahoma", Font.PLAIN, 13));
				table.setBackground(Color.LIGHT_GRAY);
				table.setRowHeight(40);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
					},
					new String[] {
						"STT", "T\u00EAn Kh\u00E1ch H\u00E0ng", "S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i", "Gi\u1EDBi T\u00EDnh", "S\u1EA3n Ph\u1EA9m \u0110\u00E3 Mua", "S\u1ED1 L\u01B0\u1EE3ng", "T\u1ED5ng Ti\u1EC1n"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, Integer.class, Object.class, Integer.class, String.class, Double.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.getColumnModel().getColumn(1).setPreferredWidth(100);
				table.getColumnModel().getColumn(4).setPreferredWidth(114);
				scrollPane.setViewportView(table);
				
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(36, 532, 152, 32);
				panel.add(comboBox);
				
				JButton btnNewButton = new JButton("Tìm Kiếm\r\n");
				btnNewButton.setBounds(1228, 532, 152, 32);
				panel.add(btnNewButton);
	}
}
