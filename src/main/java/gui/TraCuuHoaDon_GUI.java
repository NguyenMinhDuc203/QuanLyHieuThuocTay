package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import entity.ChucVu;
import entity.NhanVien;
import dao.NhanVien_DAO;
public class TraCuuHoaDon_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_3;
	private JTextField search;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTable table;
	private NhanVien_DAO nhanVienDAO;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TraCuuHoaDon_GUI frame = new TraCuuHoaDon_GUI();
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
	public TraCuuHoaDon_GUI() {
		
		//Khai báo DAO
		nhanVienDAO = new NhanVien_DAO();
		
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
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setOpaque(true);
		menuBar.setBackground(new Color(26, 133, 94));
		menuBar.setBounds(0, 0, 1395, 70);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu(" Trang Chủ");
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.setOpaque(true);
		mnNewMenu.setBackground(new Color(26, 133, 94));
		mnNewMenu.setForeground(new Color(255, 255, 255));
		mnNewMenu.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		ImageIcon icon = new ImageIcon(TrangChu_GUI.class.getResource("/gui/house-solid.png"));
		Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu.setIcon(new ImageIcon(scaledImage));
		mnNewMenu.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
		menuBar.add(mnNewMenu);

		
		JMenu mnNewMenu_1 = new JMenu(" Quản Lý");
		mnNewMenu_1.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_1.setOpaque(true);
		mnNewMenu_1.setBackground(new Color(26, 133, 94));
		mnNewMenu_1.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		mnNewMenu_1.setForeground(new Color(255, 255, 255));
		ImageIcon icon1 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/list-check-solid.png"));
		Image scaledImage1 = icon1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu_1.setIcon(new ImageIcon(scaledImage1));
		mnNewMenu_1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
		menuBar.add(mnNewMenu_1);
				
		JMenuItem mntmNewMenuItem = new JMenuItem("Sản Phẩm");
		mntmNewMenuItem.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNhnVin = new JMenuItem("Nhân Viên");
		mntmNhnVin.setForeground(new Color(255, 255, 255));
		mntmNhnVin.setBackground(new Color(26, 133, 94));
		mntmNhnVin.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_1.add(mntmNhnVin);
		
		JMenuItem mntmKhchHng = new JMenuItem("Khách Hàng");
		mntmKhchHng.setForeground(new Color(255, 255, 255));
		mntmKhchHng.setBackground(new Color(26, 133, 94));
		mntmKhchHng.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_1.add(mntmKhchHng);
		
		JMenu mnNewMenu_2_1 = new JMenu(" Bán Hàng");
		mnNewMenu_2_1.setBackground(new Color(26, 133, 94));
		mnNewMenu_2_1.setOpaque(true);
		mnNewMenu_2_1.setForeground(new Color(255, 255, 255));
		mnNewMenu_2_1.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		ImageIcon icon2_1 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/cart-shopping-solid.png"));
		Image scaledImage2_1 = icon2_1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu_2_1.setIcon(new ImageIcon(scaledImage2_1));
		mnNewMenu_2_1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
		menuBar.add(mnNewMenu_2_1);
		
		JMenu mnNewMenu_2 = new JMenu(" Thống Kê");
		mnNewMenu_2.setBackground(new Color(26, 133, 94));
		mnNewMenu_2.setOpaque(true);
		mnNewMenu_2.setForeground(new Color(255, 255, 255));
		mnNewMenu_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		ImageIcon icon2 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/clipboard-solid.png"));
		Image scaledImage2 = icon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu_2.setIcon(new ImageIcon(scaledImage2));
		mnNewMenu_2.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Doanh Số");
		mntmNewMenuItem_1.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_1.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_1_3 = new JMenuItem("Nhân Viên");
		mntmNewMenuItem_1_3.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_1_3.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2.add(mntmNewMenuItem_1_3);
		
		JMenuItem mntmNewMenuItem_1_2 = new JMenuItem("Khách Hàng");
		mntmNewMenuItem_1_2.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_1_2.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2.add(mntmNewMenuItem_1_2);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("Sản Phẩm");
		mntmNewMenuItem_1_1.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_1_1.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2.add(mntmNewMenuItem_1_1);
		
		JMenu mnNewMenu_2_2 = new JMenu(" Tra Cứu   ");
		mnNewMenu_2_2.setBackground(new Color(10, 69, 23));
		mnNewMenu_2_2.setOpaque(true);
		mnNewMenu_2_2.setForeground(new Color(255, 255, 255));
		mnNewMenu_2_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		ImageIcon icon2_2 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/circle-question-solid.png"));
		Image scaledImage2_2 = icon2_2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu_2_2.setIcon(new ImageIcon(scaledImage2_2));
		mnNewMenu_2_2.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
		menuBar.add(mnNewMenu_2_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Sản Phẩm");
		mntmNewMenuItem_2.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_2.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_2_2 = new JMenuItem("Nhân Viên");
		mntmNewMenuItem_2_2.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_2_2.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2_2.add(mntmNewMenuItem_2_2);
		
		JMenuItem mntmNewMenuItem_2_1_1 = new JMenuItem("Hóa Đơn");
		mntmNewMenuItem_2_1_1.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_2_1_1.setForeground(Color.WHITE);
		mntmNewMenuItem_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2_2.add(mntmNewMenuItem_2_1_1);
		
		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("Khách Hàng");
		mntmNewMenuItem_2_1.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_2_1.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2_2.add(mntmNewMenuItem_2_1);
		
				
		JPanel panel = new JPanel();
		panel.setBackground(new Color(244, 253, 253));
		panel.setBounds(0, 69, 1604, 833);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TRA CỨU HÓA ĐƠN");
		lblNewLabel.setForeground(new Color(10, 69, 23));
		lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
		lblNewLabel.setBounds(588, 22, 703, 70);
		ImageIcon poster = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/gui/poster.png"));
		Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
		imageLabel.setBounds(477, 11, 86, 81);
		panel.add(imageLabel);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(26, 133, 94, 94));
		panel_1.setBounds(36, 103, 1533, 360);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setToolTipText("");
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(100, 49, 370, 32);
		panel_1.add(textField_3);
		
		JLabel lblTenNV = new JLabel("Ngày sinh");
		lblTenNV.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblTenNV.setBounds(589, 11, 159, 41);
		panel_1.add(lblTenNV);
		
		JLabel lblMKhchHng = new JLabel("Tên nhân viên\r\n");
		lblMKhchHng.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblMKhchHng.setBounds(100, 92, 159, 41);
		panel_1.add(lblMKhchHng);
		
		JLabel lblCmnd = new JLabel("CMND");
		lblCmnd.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblCmnd.setBounds(1056, 11, 159, 41);
		panel_1.add(lblCmnd);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(100, 129, 370, 32);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(100, 219, 370, 32);
		panel_1.add(textField_2);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(589, 49, 370, 32);
		panel_1.add(textField);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(589, 129, 370, 32);
		panel_1.add(textField_4);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(589, 219, 370, 32);
		panel_1.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(589, 301, 370, 32);
		panel_1.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBounds(1056, 49, 370, 32);
		panel_1.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBounds(1056, 129, 370, 32);
		panel_1.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(1056, 219, 370, 32);
		panel_1.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		textField_12.setBounds(1056, 301, 370, 32);
		panel_1.add(textField_12);
		
		JLabel lblMKhchHng_1 = new JLabel("Mã nhân viên\n");
		lblMKhchHng_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblMKhchHng_1.setBounds(100, 11, 159, 41);
		panel_1.add(lblMKhchHng_1);
		
		JLabel lblMKhchHng_1_1 = new JLabel("Số điện thoại");
		lblMKhchHng_1_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblMKhchHng_1_1.setBounds(100, 180, 159, 41);
		panel_1.add(lblMKhchHng_1_1);
		
		JLabel lblMKhchHng_1_2 = new JLabel("Giới tính");
		lblMKhchHng_1_2.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblMKhchHng_1_2.setBounds(100, 261, 159, 41);
		panel_1.add(lblMKhchHng_1_2);
		
		JLabel lblTenNV_1 = new JLabel("Ngày vào làm");
		lblTenNV_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblTenNV_1.setBounds(589, 92, 159, 41);
		panel_1.add(lblTenNV_1);
		
		JLabel lblTenNV_1_1 = new JLabel("Lương căn bản");
		lblTenNV_1_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblTenNV_1_1.setBounds(589, 180, 159, 41);
		panel_1.add(lblTenNV_1_1);
		
		JLabel lblTenNV_1_2 = new JLabel("Chức vụ");
		lblTenNV_1_2.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblTenNV_1_2.setBounds(589, 262, 159, 41);
		panel_1.add(lblTenNV_1_2);
		
		JLabel lblTrnh = new JLabel("Trình độ");
		lblTrnh.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblTrnh.setBounds(1056, 92, 159, 41);
		panel_1.add(lblTrnh);
		
		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblaCh.setBounds(1056, 180, 159, 41);
		panel_1.add(lblaCh);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblEmail.setBounds(1056, 261, 159, 41);
		panel_1.add(lblEmail);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Nam");
		rdbtnNewRadioButton.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		rdbtnNewRadioButton.setBounds(100, 306, 137, 27);
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnN = new JRadioButton("Nữ");
		rdbtnN.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		rdbtnN.setBounds(278, 304, 137, 27);
		panel_1.add(rdbtnN);
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
		
		search = new JTextField();
		search.setColumns(10);
		search.setBounds(320, 492, 960, 61);
		panel.add(search);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(36, 585, 1522, 220);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowGrid(false);
		table.setBackground(new Color(26, 133, 94, 196));
		table.setBorder(null);
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"M\u00E3 NV", "T\u00EAn NV", "SDT", "Tr\u00ECnh \u0111\u1ED9", "CMDN", "Email"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
	    table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(99);
		table.getColumnModel().getColumn(2).setPreferredWidth(65);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);

		// Thiết lập font cho table và header
		Font headerFont = new Font("Leelawadee UI", Font.BOLD, 18); // Chữ to hơn cho header
		table.getTableHeader().setFont(headerFont); // Áp dụng cho header
		table.getTableHeader().setPreferredSize(new Dimension(0, 50));
		// Thiết lập màu cho header
        table.getTableHeader().setBackground(new Color(26, 133, 94, 196)); // Màu xanh cho header
		table.setFont(new Font("Leelawadee UI", Font.PLAIN, 16)); // Font cho các dòng

		// Thiết lập chiều cao của các dòng
		table.setRowHeight(30); // Đặt chiều cao dòng lớn hơn
		// Áp dụng màu xen kẽ cho các dòng
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
            	Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (row % 2 != 0) {
                    c.setBackground(new Color(26, 133, 94, 94)); // Màu cho dòng chẵn
                } else {
                    c.setBackground(Color.WHITE); // Màu cho dòng lẻ
                }
                return c;
            }
        });
     // Ngăn di chuyển cột
        table.getTableHeader().setReorderingAllowed(false);

        // Ngăn nhấn vào header
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });

        // Thiết lập chế độ chọn hàng
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Thêm MouseListener để chọn toàn bộ hàng khi nhấp vào ô
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                	table.clearSelection(); // Dọn sạch lựa chọn hiện tại
                    table.setRowSelectionInterval(row, row); // Chọn hàng đã nhấp
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        table.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column );
                                if (row == table.getSelectedRow()) {
                                    c.setBackground(new Color(10, 69, 23)); // Màu cho hàng được chọn
                                    c.setForeground(Color.WHITE);
                                } else {
                                	if (row % 2 != 0) {
                                        c.setBackground(new Color(26, 133, 94, 94)); // Màu cho dòng chẵn
                                        c.setForeground(Color.BLACK);
                                    } else {
                                        c.setBackground(Color.WHITE); // Màu cho dòng lẻ
                                        c.setForeground(Color.BLACK);
                                    }
                                }
                                return c;
                            }
                        });
                    } // Màu cho dòng lẻ
                }
            }
        });


		scrollPane.setViewportView(table);

		
		JButton searchBtn = new JButton("Tìm Kiếm\r\n");
		searchBtn.setForeground(new Color(255, 255, 255));
		searchBtn.setBackground(new Color(26, 133, 94));
		searchBtn.setFont(new Font("Leelawadee UI", Font.BOLD, 22));
		searchBtn.setBounds(1335, 492, 230, 60);
		
		panel.add(searchBtn);
		
		JComboBox typeSearch = new JComboBox();
		typeSearch.setForeground(new Color(255, 255, 255));
		typeSearch.setBackground(new Color(26, 133, 94));
		typeSearch.setFont(new Font("Leelawadee UI", Font.BOLD, 22));
		typeSearch.setModel(new DefaultComboBoxModel(new String[] {"Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Email", "Chức vụ", "CMND"}));
		typeSearch.setBounds(36, 492, 230, 60);
		panel.add(typeSearch);
		
		searchBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String maNhanVien = search.getText(); // Get the input from search field
		        ArrayList<Object[]> results = nhanVienDAO.danhSachNhanVienTheoMa(maNhanVien);

		        // Clear the existing table data
		        tableModel.setRowCount(0);

		        // Check if results are empty
		        if (results.isEmpty()) {
		            // Show message when no employee is found
		            JOptionPane.showMessageDialog(panel, "Không tìm thấy", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            // Populate the table with the search results
		            for (Object[] row : results) {
		                tableModel.addRow(row);
		            }
		        }
		    }
		});

		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table.rowAtPoint(e.getPoint());
		        if (row >= 0) {
		            table.clearSelection(); // Xóa lựa chọn hiện tại
		            table.setRowSelectionInterval(row, row); // Chọn hàng đã nhấp

		            // Lấy mã nhân viên từ cột đầu tiên của hàng đã chọn
		            String maNhanVien = (String) table.getValueAt(row, 0); // Giả sử mã nhân viên ở cột đầu tiên
		            
		            // Fetch thông tin nhân viên bằng DAO
		            NhanVien nhanVien = nhanVienDAO.layThongTinNhanVienTheoMa(maNhanVien);
		            
		            if (nhanVien != null) {
		                // Cập nhật thông tin vào các JTextField
		                textField_3.setText(nhanVien.getTenNhanVien()); // Tên nhân viên
		             // Chuyển đổi LocalDate sang String
		                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Định dạng ngày
		                textField.setText(nhanVien.getNgaySinh().format(formatter)); // Ngày sinh
		                textField_4.setText(nhanVien.getNgayVaoLam().format(formatter)); // Ngày vào làm
		                textField_1.setText(nhanVien.getMaNhanVien()); // Mã nhân viên
		                textField_2.setText(nhanVien.getSDT()); // Số điện thoại
		                textField_7.setText(nhanVien.getTrinhDo()); // Trình độ
		                textField_9.setText(nhanVien.getCMND()); // CMND
		                textField_10.setText(nhanVien.getEmail()); // Email
		             // Chuyển đổi ChucVu thành chuỗi
		                ChucVu chucVu = nhanVien.getChucVu(); // Lấy đối tượng ChucVu
		                textField_11.setText(chucVu != null ? chucVu.toString() : ""); // Đảm bảo không có NullPointerException
		                textField_12.setText(nhanVien.getDiaChi()); // Địa chỉ
		                textField_8.setText(String.valueOf(nhanVien.getLuongCanBan())); // Lương căn bản

		                // Cập nhật giới tính từ boolean
		                if (nhanVien.isGioiTinh()) { // true là Nam
		                    rdbtnNewRadioButton.setSelected(true);
		                    rdbtnN.setSelected(false);
		                } else { // false là Nữ
		                    rdbtnNewRadioButton.setSelected(false);
		                    rdbtnN.setSelected(true);
		                }
		            } else {
		                // Xử lý trường hợp không tìm thấy nhân viên
		                JOptionPane.showMessageDialog(panel, "Không tìm thấy thông tin nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            }
		        }
		    }
		});

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
				
		
		this.setVisible(true);
		
	}
	public void openTrangChu() {
        TrangChu_GUI trangChu = new TrangChu_GUI();
        trangChu.setVisible(true);
        this.setVisible(false);
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
//        TraCuuNhanVien_GUI e = new TraCuuNhanVien_GUI();
//        e.setVisible(true);
//        this.setVisible(false);
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
