
package gui;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.mariadb.jdbc.client.column.SignedTinyIntColumn;

import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Dimension;

import dao.KhachHang_DAO;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.Field;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;


public class QuanLyKhachHang_GUI extends JFrame implements MouseListener,ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNgaySinh;
	private JTextField txtCMND;
	private JTextField txtTenNV;
	private JTextField txtSDT;
	private JTextField txtNVL;
	private JTextField txtNhap;
	private JTable table;
	private JButton btnThem;
	private JButton btnXoaTrang;


	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnTim;
	private JButton btThoat;
	
	private JTextField txtDTL;
	private TrangChu_GUI trangChuGUI;
	private KhachHang_DAO dao_kh = new KhachHang_DAO();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");

	    private DefaultTableModel tbm = new DefaultTableModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyKhachHang_GUI frame = new QuanLyKhachHang_GUI();
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
	public QuanLyKhachHang_GUI() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");

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
		//Menu
				trangChuGUI = new TrangChu_GUI();
				JMenuBar menuBar = trangChuGUI.createMenuBar();
				menuBar.setBorderPainted(false);
				menuBar.setOpaque(true);
				menuBar.setBackground(new Color(26, 133, 94));
				menuBar.setBounds(0, 0, 1395, 70);
				contentPane.add(menuBar);
				
				
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(226, 250, 252));
				panel.setBounds(0, 69, 1550, 866);
				contentPane.add(panel);
				panel.setLayout(null);
				
				////
				
				
				JLabel lblNewLabel = new JLabel("QUẢN LÝ KHÁCH HÀNG");
				lblNewLabel.setForeground(new Color(46, 139, 87));
				lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 40));
				lblNewLabel.setBounds(97, 11, 512, 70);
				ImageIcon poster = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/poster.png"));
				Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
				imageLabel.setBounds(10, 11, 77, 81);
				panel.add(imageLabel);
				panel.add(lblNewLabel);
				
				JPanel panel_1 = new JPanel();
				
				panel_1.setBounds(10, 106, 1073, 212);
				panel.add(panel_1);
				panel_1.setBackground(new Color(154, 202, 189));
				panel_1.setLayout(null);
				
				
				txtTenNV = new JTextField();
				txtTenNV.setColumns(10);
				txtTenNV.setBounds(23, 31, 427, 30);
				panel_1.add(txtTenNV);
				
				txtSDT = new JTextField();
				txtSDT.setColumns(10);
				txtSDT.setBounds(23, 128, 427, 30);
				panel_1.add(txtSDT);
				
				
				
				JLabel lblTenNV = new JLabel("Tên Khách Hàng");
				lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblTenNV.setBounds(23, 12, 126, 14);
				panel_1.add(lblTenNV);
				
				JLabel lblSDT = new JLabel("Số Điện Thoại ");
				lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblSDT.setBounds(23, 104, 126, 14);
				panel_1.add(lblSDT);
				
				JLabel lblDTL = new JLabel("Điểm tích lũy");
				lblDTL.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDTL.setBounds(574, 12, 126, 14);
				panel_1.add(lblDTL);
				
				txtDTL = new JTextField();
				txtDTL.setColumns(10);
				txtDTL.setBounds(574, 31, 427, 30);
				panel_1.add(txtDTL);

				ImageIcon iconThem = new ImageIcon(QuanLyKhachHang_GUI.class.getResource("/gui/4993253681582956831-128.png"));
				Image imgThem = iconThem.getImage();
				BufferedImage bImageThem = new BufferedImage(imgThem.getWidth(null), imgThem.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dThem = bImageThem.createGraphics();
				g2dThem.drawImage(imgThem, 0, 0, null);
				g2dThem.setComposite(AlphaComposite.SrcIn);
				g2dThem.setColor(Color.WHITE);
				g2dThem.fillRect(0, 0, bImageThem.getWidth(), bImageThem.getHeight());
				g2dThem.dispose();
				Image scaledImageThem = bImageThem.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

				ImageIcon iconXoa = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/320632131667326703-128.png"));
				Image imgXoa = iconXoa.getImage();
				BufferedImage bImageXoa = new BufferedImage(imgXoa.getWidth(null), imgXoa.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dXoa = bImageXoa.createGraphics();
				g2dXoa.drawImage(imgXoa, 0, 0, null);
				g2dXoa.setComposite(AlphaComposite.SrcIn);
				g2dXoa.setColor(Color.WHITE);
				g2dXoa.fillRect(0, 0, bImageXoa.getWidth(), bImageXoa.getHeight());
				g2dXoa.dispose();
				Image scaledImageXoa = bImageXoa.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

				ImageIcon iconSua = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/setting-icon.png"));
				Image imgSua = iconSua.getImage();
				BufferedImage bImageSua = new BufferedImage(imgSua.getWidth(null), imgSua.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dSua = bImageSua.createGraphics();
				g2dSua.drawImage(imgSua, 0, 0, null);
				g2dSua.setComposite(AlphaComposite.SrcIn);
				g2dSua.setColor(Color.WHITE);
				g2dSua.fillRect(0, 0, bImageSua.getWidth(), bImageSua.getHeight());
				g2dSua.dispose();
				Image scaledImageSua = bImageSua.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

				ImageIcon iconXT = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/calendar-remove-icon.png"));
				Image imgXT = iconXT.getImage();
				BufferedImage bImageXT = new BufferedImage(imgXT.getWidth(null), imgXT.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dXT = bImageXT.createGraphics();
				g2dXT.drawImage(imgXT, 0, 0, null);
				g2dXT.setComposite(AlphaComposite.SrcIn);
				g2dXT.setColor(Color.WHITE);
				g2dXT.fillRect(0, 0, bImageXT.getWidth(), bImageXT.getHeight());
				g2dXT.dispose();
				Image scaledImageXT = bImageXT.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

				ImageIcon iconLuu = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/save-icon.png"));
				Image imgLuu = iconLuu.getImage();
				BufferedImage bImageLuu = new BufferedImage(imgLuu.getWidth(null), imgLuu.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dLuu = bImageLuu.createGraphics();
				g2dLuu.drawImage(imgLuu, 0, 0, null);
				g2dLuu.setComposite(AlphaComposite.SrcIn);
				g2dLuu.setColor(Color.WHITE);
				g2dLuu.fillRect(0, 0, bImageLuu.getWidth(), bImageLuu.getHeight());
				g2dLuu.dispose();
				Image scaledImageLuu = bImageLuu.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 355, 1073, 426);
				panel.add(scrollPane);

				table = new JTable();
				table.setFont(new Font("Tahoma", Font.BOLD, 13));
				table.setBackground(new Color(220, 220, 220));
				table.setRowHeight(40);
				table.setModel(new DefaultTableModel(
				    new Object[][] {

				    },
				    new String[] {
				        "Mã Khách Hàng", "Tên Khách Hàng", "Số điện thoại", "Điểm Tích Lũy"
				    }
				) {
				    Class[] columnTypes = new Class[] {
				        Object.class, String.class, String.class, String.class
				    };

				    public Class getColumnClass(int columnIndex) {
				        return columnTypes[columnIndex];
				    }

				    // Ghi đè phương thức isCellEditable để tất cả các ô không thể chỉnh sửa
				    @Override
				    public boolean isCellEditable(int row, int column) {
				        return false;
				    }
				});

				table.getColumnModel().getColumn(2).setPreferredWidth(99);
				table.getColumnModel().getColumn(3).setPreferredWidth(91);

				scrollPane.setViewportView(table);


				// Tạo renderer cho tất cả các cột
				DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
				    @Override
				    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				        
				        // Đặt màu nền xen kẽ
				        if (!isSelected) {
				            if (row % 2 == 0) {
				                c.setBackground(Color.WHITE);
				            } else {
				                c.setBackground(new Color(200, 230, 220)); // Màu xanh nhạt
				            }
				        } else {
				            c.setBackground(table.getSelectionBackground());
				        }
				        setHorizontalAlignment(SwingConstants.CENTER); 
				        return c;
				    }
				};

				// Áp dụng renderer cho tất cả các cột
				for (int i = 0; i < table.getColumnCount(); i++) {
				    table.getColumnModel().getColumn(i).setCellRenderer(renderer);
				}
				
				// Lấy JTableHeader từ bảng
				JTableHeader header = table.getTableHeader();
				header.setFont(new Font("Tahoma", Font.BOLD, 14));
				header.setBackground(new Color(46,139,87)); 
				header.setForeground(Color.BLACK); 
				header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
				table.setShowGrid(false);
				table.setIntercellSpacing(new Dimension(0, 0)); // Xóa khoảng cách giữa các ô

				scrollPane.setViewportView(table);

				ImageIcon iconThoat = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/exit-icon.png"));
				Image imgThoat = iconThoat.getImage();
				BufferedImage bImageThoat = new BufferedImage(imgThoat.getWidth(null), imgThoat.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dThoat = bImageThoat.createGraphics();
				g2dThoat.drawImage(imgThoat, 0, 0, null);
				g2dThoat.setComposite(AlphaComposite.SrcIn);
				g2dThoat.setColor(Color.WHITE);
				g2dThoat.fillRect(0, 0, bImageThoat.getWidth(), bImageThoat.getHeight());
				g2dThoat.dispose();
				Image scaledImageThoat = bImageThoat.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				
				JPanel panel_2 = new JPanel();
				panel_2.setBounds(1105, 106, 407, 212);
				TitledBorder titledBorder1 = BorderFactory.createTitledBorder("Tìm Kiếm nhân Viên");
	 	        //titledBorder.setTitleColor(Color.RED);  // Đặt màu chữ cho tiêu đề
	 	        titledBorder1.setBorder(BorderFactory.createLineBorder(Color.black));  // Đặt màu cho viền
	 			panel_2.setBorder(titledBorder1);
				panel_2.setBackground(new Color(226, 250, 252));
				panel.add(panel_2);
												panel_2.setLayout(null);
								
												
												txtNhap = new JTextField();
												txtNhap.setBounds(51, 58, 309, 38);
												panel_2.add(txtNhap);
												txtNhap.setColumns(10);
				
								
								JLabel lblNhpMNhn = new JLabel("Nhập mã nhân Viên ");
								lblNhpMNhn.setBounds(136, 31, 130, 17);
								panel_2.add(lblNhpMNhn);
								lblNhpMNhn.setFont(new Font("Tahoma", Font.PLAIN, 14));
								
								// Nút "Tìm"
								 btnTim = new JButton("Tìm Kiếm ");
								 btnTim.setBounds(24, 140, 161, 47);
								 panel_2.add(btnTim);
								 btnTim.setOpaque(true);
								 btnTim.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 btnTim.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 btnTim.setBackground(new Color(46, 139, 87));
								 			
								 			 btThoat = new JButton("Thoát");
								 			 btThoat.setBounds(216, 141, 161, 45);
								 			 panel_2.add(btThoat);
								 			 btThoat.setOpaque(true);
								 			 btThoat.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 			 btThoat.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 btThoat.setBackground(new Color(46, 139, 87));
								 			 btThoat.setIcon(new ImageIcon(scaledImageThoat));
								 			 btThoat.addActionListener(this);
								 			
								 			JPanel panel_2_1 = new JPanel();
								 			panel_2_1.setLayout(null);
								 			TitledBorder titledBorder = BorderFactory.createTitledBorder("Thao tác ");
								 	        //titledBorder.setTitleColor(Color.RED);  // Đặt màu chữ cho tiêu đề
								 	        titledBorder.setBorder(BorderFactory.createLineBorder(Color.black));  // Đặt màu cho viền
								 			panel_2_1.setBorder(titledBorder);
								 			panel_2_1.setBackground(new Color(226, 250, 252));
								 			
								 			panel_2_1.setBounds(1105, 413, 407, 212);
								 			panel.add(panel_2_1);
								 			 				 
								 			 				 				// Nút "Sửa"
								 			 				 				 btnSua = new JButton("Sửa");
								 			 				 				 btnSua.setBounds(10, 126, 185, 45);
								 			 				 				 panel_2_1.add(btnSua);
								 			 				 				 btnSua.setOpaque(true);
								 			 				 				 btnSua.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 			 				 				 btnSua.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 btnSua.setBackground(new Color(46, 139, 87));
								 			 				 				 btnSua.setIcon(new ImageIcon(scaledImageSua));
								 			 				 				 
								 			 				 				 
								 			 				 				 				// Nút "Xóa"
								 			 				 				 				 btnXoa = new JButton("Xóa");
								 			 				 				 				 btnXoa.setBounds(222, 126, 175, 45);
								 			 				 				 				 panel_2_1.add(btnXoa);
								 			 				 				 				 btnXoa.setOpaque(true);
								 			 				 				 				 btnXoa.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 			 				 				 				 btnXoa.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 				 btnXoa.setBackground(new Color(46, 139, 87));
								 			 				 				 				 btnXoa.setIcon(new ImageIcon(scaledImageXoa));
								 			 				 				 				 
								 			 				 				 				 
								 			 				 				 				 // Nút "Thêm"
								 			 				 				 				  btnThem = new JButton("Thêm");
								 			 				 				 				  btnThem.setBounds(10, 37, 186, 45);
								 			 				 				 				  panel_2_1.add(btnThem);
								 			 				 				 				  btnThem.setOpaque(true);
								 			 				 				 				  btnThem.setForeground(new Color(255, 255, 255));
								 			 				 				 				  btnThem.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 				  btnThem.setBackground(new Color(46, 139, 87));
								 			 				 				 				  btnThem.setIcon(new ImageIcon(scaledImageThem));
								 			 				 				 				  
								 			 				 				 				  
								 			 				 				 				  				// Nút "Xóa Trắng"
								 			 				 				 				  			btnXoaTrang = new JButton("Xóa Trắng");
								 			 				 				 				  			btnXoaTrang.setBounds(212, 36, 185, 47);
								 			 				 				 				  			panel_2_1.add(btnXoaTrang);
								 			 				 				 				  			btnXoaTrang.setOpaque(true);
								 			 				 				 				  			btnXoaTrang.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 			 				 				 				  			btnXoaTrang.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 				  			btnXoaTrang.setBackground(new Color(46, 139, 87));
								 			 				 				 				  			btnXoaTrang.setIcon(new ImageIcon(scaledImageXT));
								 			 				 				 				  			btnXoaTrang.addActionListener(this);
								 			 				 				 				  btnThem.addActionListener(this);
								 			 				 				 				 btnXoa.addActionListener(this);
								 			 				 				 btnSua.addActionListener(this);
								 btnTim.addActionListener(this);
				
				//Actions Menu
				
			table.addMouseListener(this);

			displayKhachHangsInTable();
			this.setVisible(true);
	}
		//
	
	//
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
	    
	//    salesMenu.addMouseListener(createMenuMouseAdapter(this, BanHang_GUI.class));
	    homeMenu.addMouseListener(createMenuMouseAdapter(this, TrangChu_GUI.class));
	    
	    manageMenuItem1.addActionListener(createMenuActionListener(this, QuanLySanPham_GUI.class));
	    manageMenuItem2.addActionListener(createMenuActionListener(this, QuanLyNhanVien_GUI.class));
	    manageMenuItem3.addActionListener(createMenuActionListener(this, QuanLyKhachHang_GUI.class));

	    statsMenuItem1.addActionListener(createMenuActionListener(this, ThongKeDoanhSo_GUI.class));
	    statsMenuItem2.addActionListener(createMenuActionListener(this, ThongKeNhanVien_GUI.class));
	    statsMenuItem3.addActionListener(createMenuActionListener(this, ThongKeKhachHang_GUI.class));
	    statsMenuItem4.addActionListener(createMenuActionListener(this, ThongKeSanPham_GUI.class));

	    return menuBar;}

	public void displayKhachHangsInTable() {
	    // Get all customer records from the database
	    List<KhachHang> khachHangs = dao_kh.getAllKhachHangs();  

	    // Check if the list is empty or null
	    if (khachHangs == null || khachHangs.isEmpty()) {
	        System.out.println("Không có khách hàng để hiển thị.");
	        return; // Exit the method if no customers are found
	    }

	    // Get the table model
	    DefaultTableModel model = (DefaultTableModel) table.getModel();

	    // Clear the existing rows in the table before adding new data
	    model.setRowCount(0);

	    // Loop through the list of customers and add them to the table
	    for (KhachHang kh : khachHangs) {
	        try {
	            // Use reflection to access private fields in the KhachHang class
	            Field maKhachHangField = KhachHang.class.getDeclaredField("maKhachHang");
	            maKhachHangField.setAccessible(true);  // Make private fields accessible
	            Object maKhachHangValue = maKhachHangField.get(kh);  // Get value of maKhachHang

	            Field tenKhachHangField = KhachHang.class.getDeclaredField("tenKhachHang");
	            tenKhachHangField.setAccessible(true);
	            Object tenKhachHangValue = tenKhachHangField.get(kh);  // Get value of tenKhachHang

	            Field sdtField = KhachHang.class.getDeclaredField("sDT");
	            sdtField.setAccessible(true);
	            Object sdtValue = sdtField.get(kh);  // Get value of sDT

	            Field dtiemTichLuyField = KhachHang.class.getDeclaredField("diemTichLuy");
	            dtiemTichLuyField.setAccessible(true);
	            Object diemTichLuyValue = dtiemTichLuyField.get(kh);  // Get value of diemTichLuy

	            // Add the customer data to the table row
	            model.addRow(new Object[]{
	                maKhachHangValue, tenKhachHangValue, sdtValue, diemTichLuyValue
	            });
	        } catch (NoSuchFieldException | IllegalAccessException e) {
	            // Handle exceptions related to reflection
	            e.printStackTrace();
	        }
	    }
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
	  //      BanHang_GUI banHang = new BanHang_GUI();
	     //   banHang.setVisible(true);
	   //     this.setVisible(false);
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
		

		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub\
			
				Object o = e.getSource();
				if(o.equals(btnXoaTrang)) {
					txtTenNV.setText("");
					txtSDT.setText("");
					txtDTL.setText("");
					
				}

				if (o.equals(btnThem)) {
				    
				    String tenKH = txtTenNV.getText();
				    String sdt = txtSDT.getText();
				    int diemTichLuy=0;
				    try {
				        diemTichLuy = Integer.parseInt(txtDTL.getText().trim());
				    } catch (NumberFormatException e1) {
				        JOptionPane.showMessageDialog(null, "Điểm tích lũy phải là một số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        return; // Dừng phương thức nếu có lỗi
				    }

				    
				    if (tenKH.isEmpty() || sdt.isEmpty() ) {
				        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        return; 
				    }

				 
				    DefaultTableModel model = (DefaultTableModel) table.getModel();
				    
				    // Kiểm tra xem tên khách hàng và số điện thoại đã tồn tại chưa
				    boolean isDuplicate = false;
				    for (int i = 0; i < model.getRowCount(); i++) {
				        String existingTenKH = model.getValueAt(i, 1).toString();
				        String existingSDT = model.getValueAt(i, 2).toString();
				        Object value = model.getValueAt(i, 3);
				       
				        if (existingSDT.equals(sdt)) {
				            isDuplicate = true;
				            break;
				        }
				    }

				    if (isDuplicate) {
				        JOptionPane.showMessageDialog(null, "Khách hàng này đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        return;
				    }
				    
				    String maKH =dao_kh.maTuSinhKhachHang(sdt);
				    KhachHang kh= new KhachHang();
				    kh.setMaKhachHang(maKH);
				    kh.setTenKhachHang(tenKH);
				    kh.setSDT(sdt);
				    kh.setDiemTichLuy(diemTichLuy);
				    dao_kh.create(kh);
				    int rowCount = model.getRowCount(); // Số dòng hiện có
				    
				   

				   
				    model.addRow(new Object[] { maKH, tenKH, sdt, diemTichLuy });
				    model.fireTableDataChanged();
				    table.repaint();
				   
				        JOptionPane.showMessageDialog(null, "Thêm khách hàng vào bảng thành công!");
				    
				       

				   
				    txtTenNV.setText("");
				    txtSDT.setText("");
				    txtDTL.setText("");
				}




			    if(o.equals(btnXoa)) {
			       
			        int row = table.getSelectedRow();
			        String maKhachHang = table.getValueAt(row, 0).toString();
			       
			        if (row == -1) {
			            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			            return;  
			        }
			        int confirmation = JOptionPane.showConfirmDialog(
			        	    null,
			        	    "Bạn có chắc chắn muốn xóa thông tin khách hàng  này không?", 
			        	    "Xác nhận",  
			        	    JOptionPane.YES_NO_OPTION
			        	);
			        if (confirmation == JOptionPane.YES_OPTION) {
			        	
			        boolean t=	dao_kh.delete(maKhachHang);
			        if(t)
			        {  DefaultTableModel model = (DefaultTableModel) table.getModel();
			        model.removeRow(row);

			        
			        JOptionPane.showMessageDialog(null, "Xóa khách hàng khỏi bảng thành công!");}
			        else {
				        JOptionPane.showMessageDialog(null, "xóa ở csdl bị lỗi");

			        }
			        }
			        txtTenNV.setText("");
			        txtSDT.setText("");
			        txtDTL.setText("");  
			    }

			    if(o.equals(btnSua)) {
			        int row = table.getSelectedRow();
			        if (row == -1) {
			            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        String maNhanVien = table.getValueAt(row, 0).toString();
			        String tenNV = txtTenNV.getText();
			        String sdt = txtSDT.getText();
			        int diemTichLuy = Integer.parseInt(txtDTL.getText().trim());

			        if (tenNV.isEmpty() || sdt.isEmpty() ) {
			            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
			        dao_kh.updateKhachHang(maNhanVien,tenNV,sdt,diemTichLuy);
			  
			        DefaultTableModel model = (DefaultTableModel) table.getModel();
			        model.setValueAt(tenNV, row, 1);  // Cập nhật tên nhân viên tại cột 1
			        model.setValueAt(sdt, row, 2);    // Cập nhật số điện thoại tại cột 2
			        model.setValueAt(diemTichLuy, row, 3);  // Cập nhật điểm tích lũy tại cột 3

			       
			        JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thành công!");

			        txtTenNV.setText("");
			        txtSDT.setText("");
			        txtDTL.setText("");
			    }
			  

			    if (o.equals(btnTim)) {
			        String maKH = txtNhap.getText().trim();

			        if (maKH.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã khách hàng cần tìm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        List<KhachHang> khachHangs = dao_kh.findKhachHangByPartialId(maKH);

			        if (khachHangs != null && !khachHangs.isEmpty()) {
			            DefaultTableModel model = (DefaultTableModel) table.getModel();
			            model.setRowCount(0);

			            try {
			                for (KhachHang khachHang : khachHangs) {
			                    Field[] fields = KhachHang.class.getDeclaredFields();
			                    Object[] rowData = new Object[fields.length];

			                    for (int i = 0; i < fields.length; i++) {
			                        fields[i].setAccessible(true);
			                        rowData[i] = fields[i].get(khachHang);
			                    }

			                    model.addRow(rowData);
			                }

			                JOptionPane.showMessageDialog(null, "Tìm thấy " + khachHangs.size() + " khách hàng với mã bắt đầu bằng: " + maKH);
			            } catch (IllegalAccessException e1) {
			                e1.printStackTrace();
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với mã bắt đầu bằng: " + maKH, "Không tìm thấy", JOptionPane.INFORMATION_MESSAGE);
			        }
			    }

			    if (o.equals(btThoat)) {
			        displayKhachHangsInTable();
			    }

			    }


			

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			  int selectedRow = table.getSelectedRow();
			    
			    if (selectedRow != -1) {
			        String tenKH = table.getValueAt(selectedRow, 1).toString();
			        String sdtKH = table.getValueAt(selectedRow, 2).toString();
			        String dtlKH = table.getValueAt(selectedRow, 3).toString();
			       
			        // Hiển thị thông tin lên các trường trong form
			      
			        txtTenNV.setText(tenKH);
			        txtSDT.setText(sdtKH);
			        txtDTL.setText(dtlKH);
			       
			       
			    }
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
