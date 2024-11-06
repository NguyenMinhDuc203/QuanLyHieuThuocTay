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
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Dimension;

import dao.KhachHang_DAO;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class qlNV extends JFrame implements MouseListener,ActionListener{

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
	private KhachHang_DAO dao_kh = new KhachHang_DAO();
	private DefaultTableModel tbm;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnTim;
	private JButton btThoat;
	private JButton btnLuu;
	private EntityManagerFactory emf;
	private JTextField txtDTL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					qlNV frame = new qlNV();
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
	public qlNV() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1423, 912); 
        contentPane = new JPanel();
        contentPane.setBackground(Color.decode("#FF5733"));
        contentPane.setForeground(SystemColor.window);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(46, 139, 87));
        menuBar.setBounds(0, 0, 1407, 70);
        contentPane.add(menuBar);

        // Menu "Trang Chủ"
        JMenu mnTrangChu = new JMenu(" Trang Chủ");
        mnTrangChu.setOpaque(true);
        mnTrangChu.setBackground(new Color(46, 139, 87));
        mnTrangChu.setForeground(Color.WHITE);
        mnTrangChu.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        
        // Tạo icon trắng cho "Trang Chủ"
        ImageIcon icon = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/house-solid.png"));
        Image img = icon.getImage();
        BufferedImage bImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bImage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.setComposite(AlphaComposite.SrcIn);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, bImage.getWidth(), bImage.getHeight());
        g2d.dispose();
        Image scaledImage = bImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        mnTrangChu.setIcon(new ImageIcon(scaledImage));
        menuBar.add(mnTrangChu);

      
     // Menu "Quản Lý"
        JMenu mnQuanLy = new JMenu(" Quản Lý");
        mnQuanLy.setOpaque(true);
        mnQuanLy.setBackground(new Color(46, 139, 87));
        mnQuanLy.setForeground(new Color(255, 255, 255));
        mnQuanLy.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        ImageIcon icon1 = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/list-check-solid.png"));
        Image img1 = icon1.getImage();
        BufferedImage bImage1 = new BufferedImage(img1.getWidth(null), img1.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d1 = bImage1.createGraphics();
        g2d1.drawImage(img1, 0, 0, null);
        g2d1.setComposite(AlphaComposite.SrcIn);
        g2d1.setColor(Color.WHITE);
        g2d1.fillRect(0, 0, bImage1.getWidth(), bImage1.getHeight());
        g2d1.dispose();
        Image scaledImage1 = bImage1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        mnQuanLy.setIcon(new ImageIcon(scaledImage1));
        mnQuanLy.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // Thay đổi giá trị 20 để điều chỉnh khoảng cách
        menuBar.add(mnQuanLy);

        // Thêm các menu item cho "Quản Lý"
        JMenuItem mntmSP = new JMenuItem("Sản Phẩm");
        mntmSP.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        mnQuanLy.add(mntmSP);

        JMenuItem mntmNV = new JMenuItem("Nhân Viên");
        mntmNV.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        mnQuanLy.add(mntmNV);

        JMenuItem mntmKH = new JMenuItem("Khách Hàng");
        mntmKH.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        mnQuanLy.add(mntmKH);

        // Menu "Bán Hàng"
        JMenu mnBanHang = new JMenu(" Bán Hàng");
        mnBanHang.setOpaque(true);
        mnBanHang.setBackground(new Color(46, 139, 87));
        mnBanHang.setForeground(new Color(255, 255, 255));
        mnBanHang.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        ImageIcon icon2 = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/cart-shopping-solid.png"));
        Image img2 = icon2.getImage();
        BufferedImage bImage2 = new BufferedImage(img2.getWidth(null), img2.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d2 = bImage2.createGraphics();
        g2d2.drawImage(img2, 0, 0, null); // Sử dụng img2 thay vì img1
        g2d2.setComposite(AlphaComposite.SrcIn);
        g2d2.setColor(Color.WHITE);
        g2d2.fillRect(0, 0, bImage2.getWidth(), bImage2.getHeight());
        g2d2.dispose();
        Image scaledImage2 = bImage2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        mnBanHang.setIcon(new ImageIcon(scaledImage2));
        mnBanHang.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // Thay đổi giá trị 20 để điều chỉnh khoảng cách
        menuBar.add(mnBanHang);

     // Menu "Thống Kê"
        JMenu mnThongKe = new JMenu(" Thống Kê");
        mnThongKe.setBackground(new Color(46, 139, 87));
        mnThongKe.setOpaque(true);
        mnThongKe.setForeground(new Color(255, 255, 255));
        mnThongKe.setFont(new Font("Leelawadee UI", Font.BOLD, 24));

        ImageIcon icon3 = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/clipboard-solid.png"));
        Image img3 = icon3.getImage();
        BufferedImage bImage3 = new BufferedImage(img3.getWidth(null), img3.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d3 = bImage3.createGraphics();
        g2d3.drawImage(img3, 0, 0, null);
        g2d3.setComposite(AlphaComposite.SrcIn);
        g2d3.setColor(Color.WHITE);
        g2d3.fillRect(0, 0, bImage3.getWidth(), bImage3.getHeight());
        g2d3.dispose();
        Image scaledImage3 = bImage3.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        mnThongKe.setIcon(new ImageIcon(scaledImage3)); // Sử dụng icon đã được chuyển thành màu trắng

        menuBar.add(Box.createHorizontalStrut(30)); // Thêm khoảng cách
        menuBar.add(mnThongKe);

        // Thêm các menu item cho "Thống Kê"
        JMenuItem mntmDoanhSo = new JMenuItem("Doanh Số");
        mntmDoanhSo.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        mnThongKe.add(mntmDoanhSo);

        JMenuItem mntmNhanVien = new JMenuItem("Nhân Viên");
        mntmNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        mnThongKe.add(mntmNhanVien);

        JMenuItem mntmKhachHang = new JMenuItem("Khách Hàng");
        mntmKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        mnThongKe.add(mntmKhachHang);

        JMenuItem mntmSanPham = new JMenuItem("Sản Phẩm");
        mntmSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        mnThongKe.add(mntmSanPham);

        // Menu "Tra Cứu"
        JMenu mnNewMenu_2_2 = new JMenu(" Tra Cứu");
        mnNewMenu_2_2.setBackground(new Color(46, 139, 87));
        mnNewMenu_2_2.setOpaque(true);
        mnNewMenu_2_2.setForeground(new Color(255, 255, 255));
        mnNewMenu_2_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));

        ImageIcon icon2_2 = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/circle-question-solid.png"));
        Image img2_2 = icon2_2.getImage();
        BufferedImage bImage2_2 = new BufferedImage(img2_2.getWidth(null), img2_2.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d2_2 = bImage2_2.createGraphics();
        g2d2_2.drawImage(img2_2, 0, 0, null);
        g2d2_2.setComposite(AlphaComposite.SrcIn);
        g2d2_2.setColor(Color.WHITE);
        g2d2_2.fillRect(0, 0, bImage2_2.getWidth(), bImage2_2.getHeight());
        g2d2_2.dispose();
        Image scaledImage2_2 = bImage2_2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        mnNewMenu_2_2.setIcon(new ImageIcon(scaledImage2_2)); // Sử dụng icon đã được chuyển thành màu trắng

        Component horizontalStrut2 = Box.createHorizontalStrut(30); // Thêm khoảng cách
        horizontalStrut2.setBackground(new Color(46, 139, 87));
        menuBar.add(horizontalStrut2);
        menuBar.add(mnNewMenu_2_2);

        // Thêm các menu item cho "Tra Cứu"
        JMenuItem mntmTCSP = new JMenuItem("Sản Phẩm");
        mntmTCSP.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        mnNewMenu_2_2.add(mntmTCSP);

        JMenuItem mntmTCNV = new JMenuItem("Nhân Viên");
        mntmTCNV.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        mnNewMenu_2_2.add(mntmTCNV);

				
				JMenuItem mntmTCKH = new JMenuItem("Khách Hàng");
				mntmTCKH.setFont(new Font("Segoe UI", Font.PLAIN, 24));
				mnNewMenu_2_2.add(mntmTCKH);
				mnNewMenu_2_2.setOpaque(true);
				ImageIcon iconBt = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/arrow-from-bracket-solid.png"));
				Image scaledImageBt = iconBt.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(226, 250, 252));
				panel.setBounds(0, 69, 1407, 866);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("QUẢN LÝ KHÁCH HÀNG");
				lblNewLabel.setForeground(new Color(46, 139, 87));
				lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 40));
				lblNewLabel.setBounds(96, 11, 512, 70);
				ImageIcon poster = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/poster.png"));
				Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
				imageLabel.setBounds(9, 11, 77, 81);
				panel.add(imageLabel);
				panel.add(lblNewLabel);
				
				JPanel panel_1 = new JPanel();
				
				panel_1.setBounds(9, 99, 1386, 164);
				panel.add(panel_1);
				panel_1.setBackground(new Color(154, 202, 189));
				panel_1.setLayout(null);
				
				
				txtTenNV = new JTextField();
				txtTenNV.setColumns(10);
				txtTenNV.setBounds(23, 31, 352, 30);
				panel_1.add(txtTenNV);
				
				txtSDT = new JTextField();
				txtSDT.setColumns(10);
				txtSDT.setBounds(23, 98, 352, 30);
				panel_1.add(txtSDT);
				
				
				
				JLabel lblTenNV = new JLabel("Tên Khách Hàng");
				lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblTenNV.setBounds(23, 12, 126, 14);
				panel_1.add(lblTenNV);
				
				JLabel lblSDT = new JLabel("Số Điện Thoại ");
				lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblSDT.setBounds(23, 82, 126, 14);
				panel_1.add(lblSDT);
				
				JLabel lblDTL = new JLabel("Điểm tích lũy");
				lblDTL.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDTL.setBounds(574, 12, 126, 14);
				panel_1.add(lblDTL);
				
				txtDTL = new JTextField();
				txtDTL.setColumns(10);
				txtDTL.setBounds(574, 31, 352, 30);
				panel_1.add(txtDTL);
				
				
				// Nút "Thêm"
				 btnThem = new JButton("Thêm");
				btnThem.setOpaque(true);
				btnThem.setForeground(new Color(255, 255, 255));
				btnThem.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnThem.setBackground(new Color(46, 139, 87));
				btnThem.setBounds(10, 291, 128, 45);

				ImageIcon iconThem = new ImageIcon(qlNV.class.getResource("/gui/4993253681582956831-128.png"));
				Image imgThem = iconThem.getImage();
				BufferedImage bImageThem = new BufferedImage(imgThem.getWidth(null), imgThem.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dThem = bImageThem.createGraphics();
				g2dThem.drawImage(imgThem, 0, 0, null);
				g2dThem.setComposite(AlphaComposite.SrcIn);
				g2dThem.setColor(Color.WHITE);
				g2dThem.fillRect(0, 0, bImageThem.getWidth(), bImageThem.getHeight());
				g2dThem.dispose();
				Image scaledImageThem = bImageThem.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				btnThem.setIcon(new ImageIcon(scaledImageThem));
				panel.add(btnThem);


				// Nút "Xóa"
				 btnXoa = new JButton("Xóa");
				btnXoa.setOpaque(true);
				btnXoa.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btnXoa.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnXoa.setBackground(new Color(46, 139, 87));
				btnXoa.setBounds(148, 291, 113, 45);

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
				btnXoa.setIcon(new ImageIcon(scaledImageXoa));
				panel.add(btnXoa);

				// Nút "Sửa"
				 btnSua = new JButton("Sửa");
				btnSua.setOpaque(true);
				btnSua.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btnSua.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnSua.setBackground(new Color(46, 139, 87));
				btnSua.setBounds(280, 291, 113, 45);

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
				btnSua.setIcon(new ImageIcon(scaledImageSua));
				panel.add(btnSua);


				// Nút "Xóa Trắng"
			btnXoaTrang = new JButton("Xóa Trắng");
				btnXoaTrang.setOpaque(true);
				btnXoaTrang.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btnXoaTrang.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnXoaTrang.setBackground(new Color(46, 139, 87));
				btnXoaTrang.setBounds(414, 291, 152, 45);

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
				btnXoaTrang.setIcon(new ImageIcon(scaledImageXT));
				panel.add(btnXoaTrang);

				// Nút "Lưu"
				 btnLuu = new JButton("Lưu");
				btnLuu.setOpaque(true);
				btnLuu.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btnLuu.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnLuu.setBackground(new Color(46, 139, 87));
				btnLuu.setBounds(589, 291, 119, 45);

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
				btnLuu.setIcon(new ImageIcon(scaledImageLuu));
				panel.add(btnLuu);

				
				txtNhap = new JTextField();
				txtNhap.setColumns(10);
				txtNhap.setBounds(1070, 304, 318, 32);
				panel.add(txtNhap);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 347, 1386, 453);
				panel.add(scrollPane);
				table = new JTable();
				table.setFont(new Font("Tahoma", Font.BOLD, 13));
				table.setBackground(new Color(220, 220, 220));
				table.setRowHeight(40);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, "", "", ""},
						{null, "", "", ""},
						{null, null, null, null},
						{null, null, null, null},
					},
					new String[] {
						"M\u00E3 Kh\u00E1ch H\u00E0ng", "T\u00EAn Kh\u00E1ch H\u00E0ng", "\u00F3 \u0111i\u1EC7n tho\u1EA1i", "i\u1EC3m t\u00EDch l\u0169y"
					}
				) {
					Class[] columnTypes = new Class[] {
						Object.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
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

				
				JLabel lblNhpMNhn = new JLabel("Nhập mã nhân Viên ");
				lblNhpMNhn.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNhpMNhn.setBounds(1070, 282, 126, 26);
				panel.add(lblNhpMNhn);
				
				 btThoat = new JButton("Thoát");
				btThoat.setOpaque(true);
				btThoat.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btThoat.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btThoat.setBackground(new Color(46, 139, 87));
				btThoat.setBounds(718, 291, 120, 45);

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
				btThoat.setIcon(new ImageIcon(scaledImageThoat));
				panel.add(btThoat);
				
				// Nút "Tìm"
				 btnTim = new JButton("Tìm");
				btnTim.setOpaque(true);
				btnTim.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btnTim.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnTim.setBackground(new Color(46, 139, 87));
				btnTim.setBounds(955, 291, 105, 45);

				
				panel.add(btnTim);
				//Actions Menu
				mnTrangChu.addActionListener(e -> openTrangChu());
				mntmSP.addActionListener(e -> openQuanLySanPham());
			mntmNhanVien.addActionListener(e -> openQuanLyNhanVien());
			mntmKH.addActionListener(e -> openQuanLyKhachHang());
			mnBanHang.addActionListener(e -> openBanHang());
			mntmDoanhSo.addActionListener(e -> openThongKeDoanhSo());
			mntmTCNV.addActionListener(e -> openThongKeNhanVien());
			mntmTCKH.addActionListener(e -> openThongKeKhachHang());
			mntmTCSP.addActionListener(e -> openThongKeSanPham());
			mntmTCSP.addActionListener(e -> openTraCuuSanPham());
			mntmTCNV.addActionListener(e -> openTraCuuNhanVien());
			mntmTCKH.addActionListener(e -> openTraCuuKhachHang());
			table.addMouseListener(this);
			btnThem.addActionListener(this);
			btnXoa.addActionListener(this);
			btnLuu.addActionListener(this);
			btnSua.addActionListener(this);
			btnTim.addActionListener(this);
			btnXoaTrang.addActionListener(this);
			btThoat.addActionListener(this);

			 duyetListVaoTable();
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
	        qlNV quanLyKhachHang = new qlNV();
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

		public void duyetListVaoTable() {
		    EntityManager em = emf.createEntityManager();
		    List<Object[]> list = null;

		    try {
		        String jpql = """
		            SELECT kh.maKhachHang, kh.tenKhachHang, kh.sDT
		            FROM KhachHang kh
		        """;

		        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		        list = query.getResultList();
		        
		        tbm.setRowCount(0);  // Xóa hết dữ liệu trong bảng

		        // Duyệt qua danh sách và thêm từng dòng vào bảng
		        for (Object[] obj : list) {
		            tbm.addRow(obj);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        em.close();
		    }
		}
		public boolean checkdata() {
		    String tenkh = txtTenNV.getText().trim();
		    String sdt = txtSDT.getText().trim();

		    if (tenkh.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Tên khách hàng không được rỗng");
		        txtTenNV.requestFocus();
		        return false;
		    } else if (!sdt.matches("^0(\\d{9}|\\d{10})$")) {
		        JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu với số 0 và có 10 đến 11 chữ số");
		        txtSDT.requestFocus();
		        return false;
		    }

		    return true;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub\
			
				Object o = e.getSource();
				if(o.equals(btnXoaTrang)) {
					txtTenNV.setText("");
					txtSDT.setText("");
					
				}
				
				



			} 

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
}