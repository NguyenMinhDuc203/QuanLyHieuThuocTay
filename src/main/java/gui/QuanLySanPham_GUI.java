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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import org.hibernate.Hibernate;

import com.toedter.calendar.JDateChooser;

import java.awt.Component;
import java.awt.Dimension;

import dao.HoaDonNhap_DAO;
import dao.KhachHang_DAO;
import dao.LoaiSanPham_DAO;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import entity.ChucVu;
import entity.DonViTinh;
import entity.HoaDonNhap;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhanVien;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.sql.*;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class QuanLySanPham_GUI extends JFrame implements MouseListener,ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNgaySinh;
	
	private JTextField txtNVL;
	private JTextField txtNhap;
	private JTable table;
	private JButton btnThem;
	private JButton btnXoaTrang;

	private SanPham_DAO dao_sp = new SanPham_DAO();
	
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnTim;
	private JButton btnThoat;
	private KhachHang_DAO dao_kh = new KhachHang_DAO();
	private HoaDonNhap_DAO dao_hdn = new HoaDonNhap_DAO();
	private LoaiSanPham_DAO dao_lsp = new LoaiSanPham_DAO();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");

	    private DefaultTableModel tbm = new DefaultTableModel();
	    private JTextField txtGCSP;
	    private JTextField txtNhaSXSP;
	    private JTextField txtTenSP;
	    private JTextField txtBQSP;
	    private JTextField txtGBSP;
	    private JTextField txtGNSP;
	    
	    private JTextField txtSLTKSP;
	    private JTextField txtTPSP;
	    private JTextField txtTGTGTSP;
	    private JTextField txtCCDSP;
		private DateTimeFormatter formatter;
		private AbstractButton txtCMND;
		private JTextField txtCDSP;
		private JTextField txtNSXSP;
		private JTextField txtMHDSP;

		private JTextField txtMLSP;
		private EntityManager entityManager;
		private JComboBox<Object> cboDVTSP;
		private TrangChu_GUI trangChuGUI;
		private JDateChooser dateChooser;
		private JDateChooser dateChooser1;
		private Date dateChooserHSDSP;
		
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLySanPham_GUI frame = new QuanLySanPham_GUI();
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
	public QuanLySanPham_GUI() {
        emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
        if (entityManager == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");
            entityManager = emf.createEntityManager();
        }
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
				
				
				JButton btnNewButton = new JButton("Đăng Xuất");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnNewButton.setForeground(new Color(255, 255, 255));
				btnNewButton.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
				btnNewButton.setBackground(new Color(26, 133, 94));
				btnNewButton.setOpaque(true);
				btnNewButton.setBounds(1244, 0, 348, 70);
				ImageIcon iconBt = new ImageIcon(TrangChu_GUI.class.getResource("/gui/arrow-right-from-bracket-solid.png"));
				Image scaledImageBt = iconBt.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				btnNewButton.setIcon(new ImageIcon(scaledImageBt));
				btnNewButton.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
				btnNewButton.setHorizontalTextPosition(SwingConstants.LEFT);
				contentPane.add(btnNewButton);
				JPanel panel = new JPanel();
				panel.setBackground(new Color(226, 250, 252));
				panel.setBounds(0, 69, 1550, 866);
				contentPane.add(panel);
				panel.setLayout(null);
				
				////
				
				
				JLabel lblNewLabel = new JLabel("QUẢN LÝ SẢN PHẨM");
				lblNewLabel.setForeground(new Color(46, 139, 87));
				lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 40));
				lblNewLabel.setBounds(97, 11, 512, 70);
				ImageIcon poster = new ImageIcon(QuanLySanPham_GUI.class.getResource("/gui/poster.png"));
				Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
				imageLabel.setBounds(10, 11, 77, 81);
				panel.add(imageLabel);
				panel.add(lblNewLabel);

				ImageIcon iconThem = new ImageIcon(QuanLySanPham_GUI.class.getResource("/gui/4993253681582956831-128.png"));
				Image imgThem = iconThem.getImage();
				BufferedImage bImageThem = new BufferedImage(imgThem.getWidth(null), imgThem.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dThem = bImageThem.createGraphics();
				g2dThem.drawImage(imgThem, 0, 0, null);
				g2dThem.setComposite(AlphaComposite.SrcIn);
				g2dThem.setColor(Color.WHITE);
				g2dThem.fillRect(0, 0, bImageThem.getWidth(), bImageThem.getHeight());
				g2dThem.dispose();
				Image scaledImageThem = bImageThem.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

				ImageIcon iconXoa = new ImageIcon(QuanLySanPham_GUI.class.getResource("/gui/320632131667326703-128.png"));
				Image imgXoa = iconXoa.getImage();
				BufferedImage bImageXoa = new BufferedImage(imgXoa.getWidth(null), imgXoa.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dXoa = bImageXoa.createGraphics();
				g2dXoa.drawImage(imgXoa, 0, 0, null);
				g2dXoa.setComposite(AlphaComposite.SrcIn);
				g2dXoa.setColor(Color.WHITE);
				g2dXoa.fillRect(0, 0, bImageXoa.getWidth(), bImageXoa.getHeight());
				g2dXoa.dispose();
				Image scaledImageXoa = bImageXoa.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

				ImageIcon iconSua = new ImageIcon(QuanLySanPham_GUI.class.getResource("/gui/setting-icon.png"));
				Image imgSua = iconSua.getImage();
				BufferedImage bImageSua = new BufferedImage(imgSua.getWidth(null), imgSua.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dSua = bImageSua.createGraphics();
				g2dSua.drawImage(imgSua, 0, 0, null);
				g2dSua.setComposite(AlphaComposite.SrcIn);
				g2dSua.setColor(Color.WHITE);
				g2dSua.fillRect(0, 0, bImageSua.getWidth(), bImageSua.getHeight());
				g2dSua.dispose();
				Image scaledImageSua = bImageSua.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

				ImageIcon iconXT = new ImageIcon(QuanLySanPham_GUI.class.getResource("/gui/calendar-remove-icon.png"));
				Image imgXT = iconXT.getImage();
				BufferedImage bImageXT = new BufferedImage(imgXT.getWidth(null), imgXT.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dXT = bImageXT.createGraphics();
				g2dXT.drawImage(imgXT, 0, 0, null);
				g2dXT.setComposite(AlphaComposite.SrcIn);
				g2dXT.setColor(Color.WHITE);
				g2dXT.fillRect(0, 0, bImageXT.getWidth(), bImageXT.getHeight());
				g2dXT.dispose();
				Image scaledImageXT = bImageXT.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

				ImageIcon iconLuu = new ImageIcon(QuanLySanPham_GUI.class.getResource("/GUI/save-icon.png"));
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
				scrollPane.setBounds(10, 520, 1527, 261);
				panel.add(scrollPane);
				table = new JTable();
				table.setFont(new Font("Tahoma", Font.BOLD, 13));
				table.setBackground(new Color(220, 220, 220));
				table.setRowHeight(40);
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"M\u00E3 s\u1EA3n ph\u1EA9m ", "T\u00EAn S\u1EA3n ph\u1EA9m ", "b\u1EA3o qu\u1EA3n s\u1EA3n ph\u1EA9m ", "ch\u1ED1ng ch\u1EC9 \u0111\u1ECBnh  ", "C\u00F4ng d\u1EE5ng ", "\u0110\u01A1n v\u1ECB t\u00EDnh", "Ghi ch\u00FA ", "Gi\u00E1 b\u00E1n ", "Gi\u00E1 nh\u1EADp", "H\u1EA1n s\u1EED d\u1EE5ng ", "Ng\u00E0y s\u1EA3n xu\u1EA5t ", "Nha s\u1EA3n xu\u1EA5t  ", "S\u1ED1 l\u01B0\u1EE3ng t\u1ED3n kho", "th\u00E0nh ph\u1EA7n ", "thu\u1EBF GTGT", "M\u00E3 h\u00F3a \u0111\u01A1n ", "M\u00E3 loại SP"
					}
				) {
					Class[] columnTypes = new Class[] {
						Object.class, Object.class, String.class, Object.class, Object.class, Object.class, Object.class, String.class, Object.class, String.class, String.class, Object.class, String.class, Object.class, Object.class, Object.class, Object.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.getColumnModel().getColumn(1).setPreferredWidth(92);
				table.getColumnModel().getColumn(5).setPreferredWidth(91);
				table.getColumnModel().getColumn(9).setPreferredWidth(99);
				table.getColumnModel().getColumn(10).setPreferredWidth(91);
				table.getColumnModel().getColumn(13).setPreferredWidth(88);

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
				header.setFont(new Font("Tahoma", Font.PLAIN, 12)); // Điều chỉnh kích thước font ở đây
				header.setBackground(new Color(46, 139, 87)); 
				header.setForeground(Color.BLACK); 
				header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
				table.setShowGrid(false);
				table.setIntercellSpacing(new Dimension(0, 0)); // Xóa khoảng cách giữa các ô


				scrollPane.setViewportView(table);

				ImageIcon iconThoat = new ImageIcon(QuanLySanPham_GUI.class.getResource("/gui/exit-icon.png"));
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
				panel_2.setBounds(1165, 113, 372, 145);
				TitledBorder titledBorder1 = BorderFactory.createTitledBorder("Tìm Kiếm nhân Viên");
	 	        //titledBorder.setTitleColor(Color.RED);  // Đặt màu chữ cho tiêu đề
	 	        titledBorder1.setBorder(BorderFactory.createLineBorder(Color.black));  // Đặt màu cho viền
	 			panel_2.setBorder(titledBorder1);
				panel_2.setBackground(new Color(226, 250, 252));
				panel.add(panel_2);
												panel_2.setLayout(null);
								
												
												txtNhap = new JTextField();
												txtNhap.setBounds(71, 49, 240, 30);
												panel_2.add(txtNhap);
												txtNhap.setColumns(10);
				
								
								JLabel lblNhpMNhn = new JLabel("Nhập mã Sản Phẩm ");
								lblNhpMNhn.setBounds(124, 22, 130, 17);
								panel_2.add(lblNhpMNhn);
								lblNhpMNhn.setFont(new Font("Tahoma", Font.PLAIN, 14));
								
								// Nút "Tìm"
								 btnTim = new JButton("Tìm Kiếm ");
								 btnTim.setBounds(26, 89, 159, 35);
								 panel_2.add(btnTim);
								 btnTim.setOpaque(true);
								 btnTim.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 btnTim.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 btnTim.setBackground(new Color(46, 139, 87));
								 
								  btnThoat = new JButton("Thoát");
								  btnThoat.setBounds(195, 90, 148, 37);
								  panel_2.add(btnThoat);
								  btnThoat.setOpaque(true);
								  btnThoat.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								  btnThoat.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								  btnThoat.setBackground(new Color(46, 139, 87));
								  btnThoat.setIcon(new ImageIcon(scaledImageThoat));
								  btnThoat.addActionListener(this);
								 			
								 			JPanel panel_2_1 = new JPanel();
								 			panel_2_1.setLayout(null);
								 			TitledBorder titledBorder = BorderFactory.createTitledBorder("Thao tác ");
								 	        //titledBorder.setTitleColor(Color.RED);  // Đặt màu chữ cho tiêu đề
								 	        titledBorder.setBorder(BorderFactory.createLineBorder(Color.black));  // Đặt màu cho viền
								 			panel_2_1.setBorder(titledBorder);
								 			panel_2_1.setBackground(new Color(226, 250, 252));
								 			
								 			panel_2_1.setBounds(1165, 315, 372, 161);
								 			panel.add(panel_2_1);
								 			 				 
								 			 				 				// Nút "Sửa"
								 			 				 				 btnSua = new JButton("Sửa");
								 			 				 				 btnSua.setBounds(203, 91, 148, 37);
								 			 				 				 panel_2_1.add(btnSua);
								 			 				 				 btnSua.setOpaque(true);
								 			 				 				 btnSua.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 			 				 				 btnSua.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 btnSua.setBackground(new Color(46, 139, 87));
								 			 				 				 btnSua.setIcon(new ImageIcon(scaledImageSua));
								 			 				 				 
								 			 				 				 
								 			 				 				 				// Nút "Xóa"
								 			 				 				 				 btnXoa = new JButton("Xóa");
								 			 				 				 				 btnXoa.setBounds(203, 30, 148, 39);
								 			 				 				 				 panel_2_1.add(btnXoa);
								 			 				 				 				 btnXoa.setOpaque(true);
								 			 				 				 				 btnXoa.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 			 				 				 				 btnXoa.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 				 btnXoa.setBackground(new Color(46, 139, 87));
								 			 				 				 				 btnXoa.setIcon(new ImageIcon(scaledImageXoa));
								 			 				 				 				 
								 			 				 				 				 
								 			 				 				 				 // Nút "Thêm"
								 			 				 				 				  btnThem = new JButton("Thêm");
								 			 				 				 				  btnThem.setBounds(20, 30, 151, 39);
								 			 				 				 				  panel_2_1.add(btnThem);
								 			 				 				 				  btnThem.setOpaque(true);
								 			 				 				 				  btnThem.setForeground(new Color(255, 255, 255));
								 			 				 				 				  btnThem.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 				  btnThem.setBackground(new Color(46, 139, 87));
								 			 				 				 				  btnThem.setIcon(new ImageIcon(scaledImageThem));
								 			 				 				 				  
								 			 				 				 				  
								 			 				 				 				  				// Nút "Xóa Trắng"
								 			 				 				 				  			btnXoaTrang = new JButton("Xóa Trắng");
								 			 				 				 				  			btnXoaTrang.setBounds(20, 92, 151, 35);
								 			 				 				 				  			panel_2_1.add(btnXoaTrang);
								 			 				 				 				  			btnXoaTrang.setOpaque(true);
								 			 				 				 				  			btnXoaTrang.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 			 				 				 				  			btnXoaTrang.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 				  			btnXoaTrang.setBackground(new Color(46, 139, 87));
								 			 				 				 				  			btnXoaTrang.setIcon(new ImageIcon(scaledImageXT));
								 			 				 				 				  			btnXoaTrang.addActionListener(this);
								 			 				 				 				  
								 			 				 				 				  JPanel panel_1 = new JPanel();
								 			 				 				 				  panel_1.setLayout(null);
								 			 				 				 				  panel_1.setBackground(new Color(154, 202, 189));
								 			 				 				 				  panel_1.setBounds(10, 102, 1120, 408);
								 			 				 				 				  panel.add(panel_1);
								 			 				 				 				  
								 			 				 				 				  txtGCSP = new JTextField();
								 			 				 				 				  txtGCSP.setColumns(10);
								 			 				 				 				  txtGCSP.setBounds(448, 31, 280, 30);
								 			 				 				 				  panel_1.add(txtGCSP);
								 			 				 				 				  
								 			 				 				 				  txtNhaSXSP = new JTextField();
								 			 				 				 				  txtNhaSXSP.setColumns(10);
								 			 				 				 				  txtNhaSXSP.setBounds(853, 31, 257, 30);
								 			 				 				 				  panel_1.add(txtNhaSXSP);
								 			 				 				 				  
								 			 				 				 				  txtTenSP = new JTextField();
								 			 				 				 				  txtTenSP.setColumns(10);
								 			 				 				 				  txtTenSP.setBounds(23, 31, 280, 30);
								 			 				 				 				  panel_1.add(txtTenSP);
								 			 				 				 				  
								 			 				 				 				  txtBQSP = new JTextField();
								 			 				 				 				  txtBQSP.setColumns(10);
								 			 				 				 				  txtBQSP.setBounds(23, 98, 280, 30);
								 			 				 				 				  panel_1.add(txtBQSP);
								 			 				 				 				  
								 			 				 				 				  txtGBSP = new JTextField();
								 			 				 				 				  txtGBSP.setColumns(10);
								 			 				 				 				  txtGBSP.setBounds(448, 98, 280, 30);
								 			 				 				 				  panel_1.add(txtGBSP);
								 			 				 				 				  
								 			 				 				 				  txtGNSP = new JTextField();
								 			 				 				 				  txtGNSP.setColumns(10);
								 			 				 				 				  txtGNSP.setBounds(448, 171, 280, 30);
								 			 				 				 				  panel_1.add(txtGNSP);
								 			 				 				 			
								 			 				 				 			// Bạn có thể thêm các chức vụ khác nếu cần
								 			 				 				 				  
								 			 				 				 				  txtSLTKSP = new JTextField();
								 			 				 				 				  txtSLTKSP.setColumns(10);
								 			 				 				 				  txtSLTKSP.setBounds(853, 98, 257, 30);
								 			 				 				 				  panel_1.add(txtSLTKSP);
								 			 				 				 				  
								 			 				 				 				  txtTPSP = new JTextField();
								 			 				 				 				  txtTPSP.setColumns(10);
								 			 				 				 				  txtTPSP.setBounds(853, 171, 257, 30);
								 			 				 				 				  panel_1.add(txtTPSP);
								 			 				 				 				  
								 			 				 				 				  txtTGTGTSP = new JTextField();
								 			 				 				 				  txtTGTGTSP.setColumns(10);
								 			 				 				 				  txtTGTGTSP.setBounds(853, 235, 257, 30);
								 			 				 				 				  panel_1.add(txtTGTGTSP);
								 			 				 				 				  
								 			 				 				 				  JLabel lblTenNV = new JLabel("Tên Sản Phẩm");
								 			 				 				 				  lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblTenNV.setBounds(23, 12, 126, 14);
								 			 				 				 				  panel_1.add(lblTenNV);
								 			 				 				 				  
								 			 				 				 				  JLabel lblSDT = new JLabel("Bảo Quản ");
								 			 				 				 				  lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblSDT.setBounds(23, 82, 126, 14);
								 			 				 				 				  panel_1.add(lblSDT);
								 			 				 				 				  
								 			 				 				 				  JLabel lblNgaysinh = new JLabel("Ghi Chú ");
								 			 				 				 				  lblNgaysinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblNgaysinh.setBounds(448, 11, 126, 16);
								 			 				 				 				  panel_1.add(lblNgaysinh);
								 			 				 				 				  
								 			 				 				 				  JLabel lblNgayVaolam = new JLabel("Giá Bán ");
								 			 				 				 				  lblNgayVaolam.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblNgayVaolam.setBounds(448, 71, 126, 24);
								 			 				 				 				  panel_1.add(lblNgayVaolam);
								 			 				 				 				  
								 			 				 				 				  JLabel lblLngCnBnlblLngCnBn = new JLabel("Giá nhập");
								 			 				 				 				  lblLngCnBnlblLngCnBn.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblLngCnBnlblLngCnBn.setBounds(448, 138, 126, 30);
								 			 				 				 				  panel_1.add(lblLngCnBnlblLngCnBn);
								 			 				 				 				  
								 			 				 				 				  JLabel lblChucVu = new JLabel("Hạn Sử Dụng");
								 			 				 				 				  lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblChucVu.setBounds(448, 211, 126, 24);
								 			 				 				 				  panel_1.add(lblChucVu);
								 			 				 				 				  
								 			 				 				 				  JLabel lblCmnd = new JLabel("Nhà Sản Xuất ");
								 			 				 				 				  lblCmnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblCmnd.setBounds(853, 11, 126, 16);
								 			 				 				 				  panel_1.add(lblCmnd);
								 			 				 				 				  
								 			 				 				 				  JLabel lblTrinhDo = new JLabel("Số Lượng Tồn Kho");
								 			 				 				 				  lblTrinhDo.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblTrinhDo.setBounds(853, 81, 126, 16);
								 			 				 				 				  panel_1.add(lblTrinhDo);
								 			 				 				 				  
								 			 				 				 				  JLabel lblDiaChi = new JLabel("Thành Phần ");
								 			 				 				 				  lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblDiaChi.setBounds(853, 154, 126, 16);
								 			 				 				 				  panel_1.add(lblDiaChi);
								 			 				 				 				  
								 			 				 				 				  JLabel lblEmail = new JLabel("Thuế GTGT");
								 			 				 				 				  lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblEmail.setBounds(853, 215, 126, 16);
								 			 				 				 				  panel_1.add(lblEmail);
								 			 				 				 				  
								 			 				 				 				  JLabel lblMtKhu = new JLabel("Chống Chỉ Định");
								 			 				 				 				  lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblMtKhu.setBounds(23, 155, 126, 14);
								 			 				 				 				  panel_1.add(lblMtKhu);
								 			 				 				 				  
								 			 				 				 				  txtCCDSP = new JTextField();
								 			 				 				 				  txtCCDSP.setColumns(10);
								 			 				 				 				  txtCCDSP.setBounds(23, 171, 280, 30);
								 			 				 				 				  panel_1.add(txtCCDSP);
								 			 				 				 				  
								 			 				 				 				  JLabel lblMtKhu_1 = new JLabel("Công Dụng");
								 			 				 				 				  lblMtKhu_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblMtKhu_1.setBounds(23, 214, 126, 14);
								 			 				 				 				  panel_1.add(lblMtKhu_1);
								 			 				 				 				  
								 			 				 				 				  JLabel lblnVTnh = new JLabel("Đơn Vị Tính");
								 			 				 				 				  lblnVTnh.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblnVTnh.setBounds(23, 275, 126, 14);
								 			 				 				 				  panel_1.add(lblnVTnh);
								 			 				 				 				  
								 			 				 				 				  
								 			 				 				 				  
								 			 				 				 				  txtCDSP = new JTextField();
								 			 				 				 				  txtCDSP.setColumns(10);
								 			 				 				 				  txtCDSP.setBounds(23, 236, 280, 30);
								 			 				 				 				  panel_1.add(txtCDSP);
								 			 				 				 				  
								 			 				 				 				  JLabel lblTenNV_2 = new JLabel("Ngày Sản Xuất ");
								 			 				 				 				  lblTenNV_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblTenNV_2.setBounds(448, 275, 280, 14);
								 			 				 				 				  panel_1.add(lblTenNV_2);
								 			 				 				 				  
							 			 				 				 				  		dateChooser1 = new JDateChooser();
							 			 				 				 				//  	dateChooser1.setColumns(10);
							 			 				 				 				dateChooser1.setBounds(448, 294, 280, 30);
								 			 				 				 				  panel_1.add(dateChooser1);
								 			 				 				 				  
								 			 				 				 				  txtMHDSP = new JTextField();
								 			 				 				 				  txtMHDSP.setColumns(10);
								 			 				 				 				  txtMHDSP.setBounds(853, 294, 257, 30);
								 			 				 				 				  panel_1.add(txtMHDSP);
								 			 				 				 				  
								 			 				 				 				  JLabel lblTenNV_2_1 = new JLabel("Mã Hóa Đơn");
								 			 				 				 				  lblTenNV_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblTenNV_2_1.setBounds(853, 275, 280, 14);
								 			 				 				 				  panel_1.add(lblTenNV_2_1);
								 			 				 				 				  
								 			 				 				 				  cboDVTSP = new JComboBox<Object>();
								 			 				 				 				  cboDVTSP.setBounds(23, 293, 280, 30);
								 			 				 				 				  panel_1.add(cboDVTSP);
								 			 				 				 				  
								 			 				 				 				cboDVTSP.addItem("VIEN");
									 			 				 				 			
									 			 				 				 			cboDVTSP.addItem("MG");
									 			 				 				 			cboDVTSP.addItem("ML");
									 			 				 				 			cboDVTSP.addItem("Gram");
								 			 				 				 			
									 			 				 				 			cboDVTSP.addItem("LỌ");
									 			 				 				 			cboDVTSP.addItem("HỘP");
										 			 				 				 		cboDVTSP.addItem("VỈ");
									 			 				 				 			cboDVTSP.addItem("GÓI");
								 			 				 				 			
									 			 				 				 			cboDVTSP.addItem("CÁI");
									 			 				 				 			cboDVTSP.addItem("MIẾNG");
								 			 				 				 			
								 			 				 				 				  
						 			 				 				 				  		dateChooser = new JDateChooser();
						 			 				 				 				  //		dateChooser.setColumns(10);
						 			 				 				 				  		dateChooser.setBounds(448, 235, 280, 30);
								 			 				 				 				 panel_1.add(dateChooser);
								 			 				 				 				  		
								 			 				 				 				  
								 			 				 				 				  JLabel lblTenNV_2_1_1 = new JLabel("Mã Loại Sản Phẩm ");
								 			 				 				 				  lblTenNV_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblTenNV_2_1_1.setBounds(853, 348, 280, 14);
								 			 				 				 				  panel_1.add(lblTenNV_2_1_1);
								 			 				 				 				  
								 			 				 				 				  txtMLSP = new JTextField();
								 			 				 				 				  txtMLSP.setColumns(10);
								 			 				 				 				  txtMLSP.setBounds(853, 368, 257, 30);
								 			 				 				 				  panel_1.add(txtMLSP);
								 			 				 				 				  btnThem.addActionListener(this);
								 			 				 				 				 btnXoa.addActionListener(this);
								 			 				 				 btnSua.addActionListener(this);
								 btnTim.addActionListener(this);
				
				//Actions Menu
				
			table.addMouseListener(this);
					//    countComponents(); // Khởi tạo các thành phần giao diện người dùng
			displaySanPhamsInTable();
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
	    
	    salesMenu.addMouseListener(createMenuMouseAdapter(this, BanHang_GUI.class));
	    homeMenu.addMouseListener(createMenuMouseAdapter(this, TrangChu_GUI.class));
	    
	    manageMenuItem1.addActionListener(createMenuActionListener(this, QuanLySanPham_GUI.class));
	    manageMenuItem2.addActionListener(createMenuActionListener(this, QuanLySanPham_GUI.class));
	    manageMenuItem3.addActionListener(createMenuActionListener(this, QuanLySanPham_GUI.class));

	    statsMenuItem1.addActionListener(createMenuActionListener(this, ThongKeDoanhSo_GUI.class));
	    statsMenuItem2.addActionListener(createMenuActionListener(this, ThongKeNhanVien_GUI.class));
	    statsMenuItem3.addActionListener(createMenuActionListener(this, ThongKeKhachHang_GUI.class));
	    statsMenuItem4.addActionListener(createMenuActionListener(this, ThongKeSanPham_GUI.class));

	    return menuBar;}

	


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
	        QuanLySanPham_GUI quanLyNhanVien = new QuanLySanPham_GUI();
	        quanLyNhanVien.setVisible(true);
	        this.setVisible(false);
	    }
		public void openQuanLyKhachHang() {
			QuanLySanPham_GUI quanLyKhachHang = new QuanLySanPham_GUI();
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
		

	
		public class DateUtils {
		    
		    // Phương thức chuyển đổi ngày từ "d/MM/yyyy" sang "yyyy-MM-dd"
		    public static String convertDateToStandardFormat(String dateStr) {
		        try {
		            // Định dạng ngày gốc "d/MM/yyyy"
		            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		            LocalDate date = LocalDate.parse(dateStr, inputFormatter);

		            // Định dạng ngày mới "yyyy-MM-dd"
		            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		            return date.format(outputFormatter);

		        } catch (DateTimeParseException e) {
		            // Xử lý lỗi nếu chuỗi ngày không hợp lệ
		            System.out.println("Ngày không hợp lệ: " + dateStr);
		            return null; // Trả về null nếu chuỗi ngày không hợp lệ
		        }
		    }
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
		
		public List<NhanVien> getDanhSachNhanVienFromTable(JTable table) {
		    List<NhanVien> danhSachNhanVien = new ArrayList<>();

		    // Lấy model của JTable
		    DefaultTableModel model = (DefaultTableModel) table.getModel();

		    // Duyệt qua tất cả các dòng trong bảng (bắt đầu từ dòng 0 đến model.getRowCount() - 1)
		    for (int i = 0; i < model.getRowCount(); i++) {
		        // Tạo một đối tượng NhanVien mới
		        NhanVien nv = new NhanVien();

		        try {
		            // Duyệt qua tất cả các cột trong bảng và gán giá trị vào đối tượng NhanVien
		            String[] fieldNames = {
		                "maNhanVien", "tenNhanVien", "sDT", "gioiTinh", "ngaySinh", "ngayVaoLam", 
		                "luongCanBan", "chucVu", "cMND", "trinhDo", "diaChi", "email", "matKhau", "trangThai"
		            };

		            for (int j = 0; j < fieldNames.length; j++) {
		                // Lấy Field của lớp NhanVien
		                Field field = NhanVien.class.getDeclaredField(fieldNames[j]);
		                field.setAccessible(true);  // Cho phép truy cập trường private

		                // Lấy giá trị từ bảng và gán vào trường tương ứng
		                Object value = model.getValueAt(i, j);  // Lấy giá trị tại dòng i, cột j

		                // Xử lý các kiểu dữ liệu đặc biệt trước khi gán giá trị cho trường
		                if (value != null) {
		                    value = convertFieldValue(value, field.getType());
		                    field.set(nv, value);  // Gán giá trị vào trường tương ứng của đối tượng NhanVien
		                }
		            }
		        } catch (NoSuchFieldException | IllegalAccessException e) {
		            e.printStackTrace(); // Xử lý ngoại lệ khi không thể truy cập trường
		        }

		        // Thêm đối tượng NhanVien vào danh sách
		        danhSachNhanVien.add(nv);
		    }

		    return danhSachNhanVien; // Trả về danh sách nhân viên từ bảng
		}

		// Phương thức chuyển đổi giá trị theo kiểu dữ liệu của trường
		private Object convertFieldValue(Object value, Class<?> fieldType) {
		    if (fieldType == boolean.class || fieldType == Boolean.class) {
		        return value instanceof Boolean ? value : Boolean.parseBoolean(value.toString());
		    } else if (fieldType == LocalDate.class) {
		        return value instanceof String ? LocalDate.parse((String) value) : value;
		    } else if (fieldType == double.class || fieldType == Double.class) {
		        return value instanceof Double ? value : Double.parseDouble(value.toString());
		    } else if (fieldType == ChucVu.class) {
		        return value instanceof String ? ChucVu.valueOf((String) value) : value;
		    }
		    return value;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o= e.getSource();
			if(o.equals(btnXoaTrang)) {
				  txtGCSP.setText("");
			        txtNhaSXSP.setText("");
			        txtTenSP.setText("");
			        txtBQSP.setText("");
			        txtGBSP.setText("");
			        txtGNSP.setText("");
			        txtSLTKSP.setText("");
			        txtTPSP.setText("");
			        txtTGTGTSP.setText("");
			        txtCCDSP.setText("");
			        txtCDSP.setText("");
			      //  txtNSXSP.setText("");
			        txtMHDSP.setText("");
			      
			        txtMLSP.setText("");
			        dateChooser.setDate(null);
		            dateChooser1.setDate(null);
			        cboDVTSP.setSelectedIndex(0);
			}
			// Tạo hành động khi nhấn nút "Thêm Sản Phẩm"
			if (o.equals(btnThem)) {
			    // Lấy thông tin từ các trường nhập liệu
			    String tenSP = txtTenSP.getText();
			    String baoq = txtBQSP.getText();
			    String congDung = txtCDSP.getText();
			    String chongChiDinh = txtCCDSP.getText();
			    String thanhPhan = txtTPSP.getText();
			    String ghiChu = txtGCSP.getText();
			    String nhaSanXuat = txtNhaSXSP.getText();

			    LocalDate hanSuDung, ngaySanXuat;
			    try {
			        // Lấy ngày từ dateChooser (hạn sử dụng) và chuyển đổi sang LocalDate
			        Date selectedDateHSD = dateChooser.getDate();
			        if (selectedDateHSD == null) {
			            throw new DateTimeParseException("Ngày hạn sử dụng không hợp lệ!", "", 0);
			        }
			        hanSuDung = selectedDateHSD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			        // Lấy ngày từ dateChooser1 (ngày sản xuất) và chuyển đổi sang LocalDate
			        Date selectedDateNSX = dateChooser1.getDate();
			        if (selectedDateNSX == null) {
			            throw new DateTimeParseException("Ngày sản xuất không hợp lệ!", "", 0);
			        }
			        ngaySanXuat = selectedDateNSX.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			        
			    } catch (DateTimeParseException ex) {
			        JOptionPane.showMessageDialog(null, "Ngày không đúng định dạng hoặc không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    double giaBan, giaNhap, thueGTGT;
			    int soLuongTonKho;
			    try {
			        giaBan = Double.parseDouble(txtGBSP.getText());
			        giaNhap = Double.parseDouble(txtGNSP.getText());
			        thueGTGT = Double.parseDouble(txtTGTGTSP.getText());
			        soLuongTonKho = Integer.parseInt(txtSLTKSP.getText());
			    } catch (NumberFormatException ex) {
			        JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số cho giá hoặc số lượng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    String donViTinhStr = cboDVTSP.getSelectedItem().toString();
			    DonViTinh donViTinh = DonViTinh.valueOf(donViTinhStr);

			    // Kiểm tra dữ liệu nhập vào
			    if (tenSP.isEmpty() || baoq.isEmpty() || congDung.isEmpty() || chongChiDinh.isEmpty() ||
			        thanhPhan.isEmpty() || ghiChu.isEmpty() || nhaSanXuat.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    // Lấy thông tin từ combobox loại sản phẩm
			    LoaiSanPham loaiSanPham = dao_lsp.findLoaiSanPhamById(txtMLSP.getText());
			    String loaiSP= loaiSanPham.getMaLoai();
			    if (loaiSanPham == null) {
			        JOptionPane.showMessageDialog(null, "Loại sản phẩm không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    HoaDonNhap hoaDonNhap = dao_hdn.findHoaDonNhapByMa(txtMHDSP.getText());
			    if (hoaDonNhap == null) {
			        JOptionPane.showMessageDialog(null, "Hóa đơn nhập  không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        return;
			    }
			    // Tạo mã sản phẩm tự sinh
			    String maSP = dao_sp.maTuSinhSanPham(loaiSP);

			    // Tạo đối tượng sản phẩm
			    SanPham sp = new SanPham();
			    sp.setMaSanPham(maSP);
			    sp.setTenSanPham(tenSP);
			    sp.setGiaBan(giaBan);
			    sp.setCongDung(congDung);
			    sp.setHanSuDung(hanSuDung);
			    sp.setBaoQuan(baoq);
			    sp.setChongChiDinh(chongChiDinh);
			    sp.setNgaySanXuat(ngaySanXuat);
			    sp.setThanhPhan(thanhPhan);
			    sp.setSoLuongTonkho(soLuongTonKho);
			    sp.setGhiChu(ghiChu);
			    sp.setNhaSanXuat(nhaSanXuat);
			    sp.setDonViTinh(donViTinh);
			    sp.setThueGTGT(thueGTGT);
			    sp.setGiaNhap(giaNhap);
			    sp.setHoaDonNhap(hoaDonNhap);
			    sp.setLoaiSanPham(loaiSanPham);

			    // Lưu sản phẩm vào database
			    boolean t = dao_sp.createSanPham(sp);
			    if (t) {
			        DefaultTableModel model = (DefaultTableModel) table.getModel();
			        model.addRow(new Object[] {
			            maSP, tenSP, baoq, chongChiDinh, congDung, donViTinh, ghiChu, giaBan, giaNhap,
			            hanSuDung, ngaySanXuat, nhaSanXuat, soLuongTonKho, thanhPhan, thueGTGT,hoaDonNhap.getMaHoaDonNhap(), loaiSanPham.getMaLoai()
			        });
			        JOptionPane.showMessageDialog(null, "Thêm sản phẩm vào bảng thành công!");
			    } else {
			        JOptionPane.showMessageDialog(null, "Lỗi khi lưu sản phẩm vào database", "Lỗi", JOptionPane.ERROR_MESSAGE);
			    }

			    // Làm sạch các trường nhập liệu sau khi thêm sản phẩm
			    txtTenSP.setText("");
			    txtBQSP.setText("");
			    txtGBSP.setText("");
			    txtGCSP.setText("");
			    txtGNSP.setText("");
			    txtSLTKSP.setText("");
			    txtTPSP.setText("");
			    txtTGTGTSP.setText("");
			    txtNhaSXSP.setText("");
			//    txtHSDSP.setText("");
			  //  txtNSXSP.setText("");
			    txtMLSP.setText("");
			    txtCDSP.setText("");
			    txtCCDSP.setText("");
			  //  txtHSDSP.setText("");
			    cboDVTSP.setSelectedIndex(0);
			    dateChooser.setDate(null);
	            dateChooser1.setDate(null);
			}

			

			if (o.equals(btnXoa)) {
				if (o.equals(btnXoa)) {
				    int row = table.getSelectedRow();

				    // Kiểm tra xem người dùng đã chọn hàng nào chưa
				    if (row == -1) {
				        JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        return;
				    }

				    // Lấy mã sản phẩm từ hàng được chọn
				    String maSanPham = table.getValueAt(row, 0).toString();

				    // Xác nhận người dùng có muốn xóa hay không
				    int confirmation = JOptionPane.showConfirmDialog(
				        null,
				        "Bạn có chắc chắn muốn xóa thông tin sản phẩm này không?",
				        "Xác nhận",
				        JOptionPane.YES_NO_OPTION
				    );

				    if (confirmation == JOptionPane.YES_OPTION) {
				        // Gọi DAO để xóa sản phẩm
				        boolean isDeleted = dao_sp.deleteSanPham(maSanPham);

				        if (isDeleted) {
				            // Xóa sản phẩm khỏi bảng hiển thị
				            DefaultTableModel model = (DefaultTableModel) table.getModel();
				            model.removeRow(row);

				            JOptionPane.showMessageDialog(null, "Xóa sản phẩm khỏi bảng thành công!");
				        } else {
				            JOptionPane.showMessageDialog(null, "Xóa sản phẩm trong cơ sở dữ liệu bị lỗi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        }
				    }

				    // Xóa các trường nhập liệu
				    txtTenSP.setText("");
				    txtBQSP.setText("");
				    txtGBSP.setText("");
				    txtGCSP.setText("");
				    txtGNSP.setText("");
				    txtSLTKSP.setText("");
				    txtTPSP.setText("");
				    txtTGTGTSP.setText("");
				    txtNhaSXSP.setText("");
				//    txtHSDSP.setText("");
				  //  txtNSXSP.setText("");
				    txtMLSP.setText("");
				    txtCDSP.setText("");
				    txtCCDSP.setText("");
				  //  txtHSDSP.setText("");
				    cboDVTSP.setSelectedIndex(0);
				    dateChooser.setDate(null);
		            dateChooser1.setDate(null);
				}}
			if (o.equals(btnSua)) {
			    // Kiểm tra xem có dòng nào được chọn trong bảng chưa
			    int row = table.getSelectedRow();
			    if (row == -1) {
			        JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    // Lấy mã sản phẩm từ dòng đã chọn
			    String maSP = table.getValueAt(row, 0).toString();

			    // Lấy thông tin từ các trường nhập liệu
			    String tenSP = txtTenSP.getText();
			    String baoq = txtBQSP.getText();
			    String congDung = txtCDSP.getText();
			    String chongChiDinh = txtCCDSP.getText();
			    String thanhPhan = txtTPSP.getText();
			    String ghiChu = txtGCSP.getText();
			    String nhaSanXuat = txtNhaSXSP.getText();

			    LocalDate hanSuDung, ngaySanXuat;
			    try {
			        // Lấy ngày từ dateChooser và chuyển đổi sang LocalDate
			        Date selectedDateHSD = dateChooser.getDate();
			        Date selectedDateNSX = dateChooser1.getDate();
			        if (selectedDateHSD == null || selectedDateNSX == null) {
			            throw new DateTimeParseException("Ngày không hợp lệ!", "", 0);
			        }
			        hanSuDung = selectedDateHSD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			        ngaySanXuat = selectedDateNSX.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    } catch (DateTimeParseException ex) {
			        JOptionPane.showMessageDialog(null, "Ngày không đúng định dạng hoặc không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    double giaBan, giaNhap, thueGTGT;
			    int soLuongTonKho;
			    try {
			        giaBan = Double.parseDouble(txtGBSP.getText());
			        giaNhap = Double.parseDouble(txtGNSP.getText());
			        thueGTGT = Double.parseDouble(txtTGTGTSP.getText());
			        soLuongTonKho = Integer.parseInt(txtSLTKSP.getText());
			    } catch (NumberFormatException ex) {
			        JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số cho giá hoặc số lượng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    String donViTinhStr = cboDVTSP.getSelectedItem().toString();
			    DonViTinh donViTinh = DonViTinh.valueOf(donViTinhStr);

			    // Kiểm tra dữ liệu nhập vào
			    if (tenSP.isEmpty() || baoq.isEmpty() || congDung.isEmpty() || chongChiDinh.isEmpty() ||
			        thanhPhan.isEmpty() || ghiChu.isEmpty() || nhaSanXuat.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    // Lấy thông tin từ combobox loại sản phẩm
			    LoaiSanPham loaiSanPham = dao_lsp.findLoaiSanPhamById(txtMLSP.getText());
			    if (loaiSanPham == null) {
			        JOptionPane.showMessageDialog(null, "Loại sản phẩm không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    HoaDonNhap hoaDonNhap = dao_hdn.findHoaDonNhapByMa(txtMHDSP.getText());
			    if (hoaDonNhap == null) {
			        JOptionPane.showMessageDialog(null, "Hóa đơn nhập không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    // Tạo đối tượng sản phẩm
			    SanPham sp = new SanPham();
			    sp.setMaSanPham(maSP);
			    sp.setTenSanPham(tenSP);
			    sp.setGiaBan(giaBan);
			    sp.setCongDung(congDung);
			    sp.setHanSuDung(hanSuDung);
			    sp.setBaoQuan(baoq);
			    sp.setChongChiDinh(chongChiDinh);
			    sp.setNgaySanXuat(ngaySanXuat);
			    sp.setThanhPhan(thanhPhan);
			    sp.setSoLuongTonkho(soLuongTonKho);
			    sp.setGhiChu(ghiChu);
			    sp.setNhaSanXuat(nhaSanXuat);
			    sp.setDonViTinh(donViTinh);
			    sp.setThueGTGT(thueGTGT);
			    sp.setGiaNhap(giaNhap);
			    sp.setHoaDonNhap(hoaDonNhap);
			    sp.setLoaiSanPham(loaiSanPham);

			    // Cập nhật sản phẩm trong database
			    boolean updateSuccess = dao_sp.updateSanPham(sp);
			    
			    if (updateSuccess) {
			        JOptionPane.showMessageDialog(null, "Cập nhật thông tin sản phẩm thành công!");

			        // Làm mới bảng bằng cách lấy lại dữ liệu từ CSDL
			        displaySanPhamsInTable(); 
			    } else {
			        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật sản phẩm trong database", "Lỗi", JOptionPane.ERROR_MESSAGE);
			    }


			    // Làm sạch các trường nhập liệu
			    txtTenSP.setText("");
			    txtBQSP.setText("");
			    txtGBSP.setText("");
			    txtGCSP.setText("");
			    txtGNSP.setText("");
			    txtSLTKSP.setText("");
			    txtTPSP.setText("");
			    txtTGTGTSP.setText("");
			    txtNhaSXSP.setText("");
			    cboDVTSP.setSelectedIndex(0);
			    dateChooser.setDate(null);
			    dateChooser1.setDate(null);
			    txtMLSP.setText("");
			    txtCDSP.setText("");
			    txtCCDSP.setText("");
			}
			if (o.equals(btnTim)) {
			    String maSP = txtNhap.getText().trim();

			    if (maSP.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sản phẩm cần tìm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    searchInTable(maSP);  // Gọi phương thức tìm trong bảng
			    JOptionPane.showMessageDialog(null, "Đã tìm kiếm sản phẩm với mã: " + maSP);
			}
				
			 if(o.equals(btnThoat)) {
				 displaySanPhamsInTable();
			 }
			
		}
			

		 @Override
		 public void mouseClicked(MouseEvent e) {
			    int selectedRow = table.getSelectedRow();
			    
			    if (selectedRow != -1) {
			        String maSanPham = table.getValueAt(selectedRow, 0).toString();
			        String tenSanPham = table.getValueAt(selectedRow, 1).toString();
			        String baoQuan = table.getValueAt(selectedRow, 2).toString();
			        String congDung = table.getValueAt(selectedRow, 4).toString();
			        String chongChiDinh = table.getValueAt(selectedRow, 3).toString();
			        
			        // Lấy ngày sử dụng và ngày sản xuất từ bảng (kiểu LocalDate)
			        LocalDate hanSuDungLocalDate = (LocalDate) table.getValueAt(selectedRow, 9);
			        LocalDate ngaySanXuatLocalDate = (LocalDate) table.getValueAt(selectedRow, 10);

			        // Chuyển đổi LocalDate sang Date
			        Date hanSuDung = Date.from(hanSuDungLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			        Date ngaySanXuat = Date.from(ngaySanXuatLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			        
			        String giaBan = table.getValueAt(selectedRow, 7).toString();
			        String giaNhap = table.getValueAt(selectedRow, 8).toString();
			        String thanhPhan = table.getValueAt(selectedRow, 13).toString();
			        String soLuongTonKho = table.getValueAt(selectedRow, 12).toString();
			        String thueGTGT = table.getValueAt(selectedRow, 14).toString();
			        String nhaSanXuat = table.getValueAt(selectedRow, 11).toString();
			        String maHoaDon = table.getValueAt(selectedRow, 15).toString();
			        String maLoaiSanPham = table.getValueAt(selectedRow, 16).toString();
			        String ghiChu= table.getValueAt(selectedRow, 6).toString();
			        
			        // Hiển thị thông tin lên các trường trong form
			        txtTenSP.setText(tenSanPham);
			        txtBQSP.setText(baoQuan);
			        txtCDSP.setText(congDung);
			        txtGBSP.setText(giaBan);
			        txtGNSP.setText(giaNhap);
			        txtTPSP.setText(thanhPhan);
			        txtSLTKSP.setText(soLuongTonKho);
			        txtTGTGTSP.setText(thueGTGT);
			        txtNhaSXSP.setText(nhaSanXuat);
			        txtMHDSP.setText(maHoaDon);
			        txtMLSP.setText(maLoaiSanPham);
			        txtCCDSP.setText(chongChiDinh);
			        txtGCSP.setText(ghiChu);
			        // Đặt ngày vào các JDateChooser
			        dateChooser.setDate(hanSuDung); // Hạn sử dụng
			        dateChooser1.setDate(ngaySanXuat); // Ngày sản xuất
			        
			        // Nếu cần, bạn có thể chọn giá trị trong JComboBox cho đơn vị tính
			        String donViTinhValue = table.getValueAt(selectedRow, 5).toString(); // Lấy giá trị của cột "Đơn vị tính"

			        switch (donViTinhValue) {
			        case "ML":
			            cboDVTSP.setSelectedItem("ML");
			            break;
			        case "MG":
			            cboDVTSP.setSelectedItem("MG");
			            break;
			        case "VIEN":
			            cboDVTSP.setSelectedItem("VIEN");
			            break;
			        case "CÁI":
			            cboDVTSP.setSelectedItem("CÁI");
			            break;
			        case "HỘP":
			            cboDVTSP.setSelectedItem("HỘP");
			            break;
			        case "GRAM":
			            cboDVTSP.setSelectedItem("GRAM");
			            break;
			        case "VỈ":
			            cboDVTSP.setSelectedItem("VỈ");
			            break;
			        case "MIẾNG":
			            cboDVTSP.setSelectedItem("MIẾNG");
			            break;
			        case "LỌ":
			            cboDVTSP.setSelectedItem("LỌ");
			            break;
			        case "GÓI":
			            cboDVTSP.setSelectedItem("GÓI");
			            break;
			        default:
			            // Nếu không có giá trị phù hợp, có thể chọn giá trị mặc định hoặc để trống
			            cboDVTSP.setSelectedItem(null);
			            break;
			        }}
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
		public void displaySanPhamsInTable() {
		    List<SanPham> sanPhams = entityManager.createQuery(
		        "SELECT sp FROM SanPham sp LEFT JOIN FETCH sp.loaiSanPham LEFT JOIN FETCH sp.hoaDonNhap", SanPham.class)
		        .getResultList();

		    if (sanPhams == null || sanPhams.isEmpty()) {
		        System.out.println("Không có sản phẩm để hiển thị.");
		        return;
		    }

		    DefaultTableModel model = (DefaultTableModel) table.getModel();
		    model.setRowCount(0);

		    for (SanPham sp : sanPhams) {
		        String maSanPham = sp.getMaSanPham();
		        String tenSanPham = sp.getTenSanPham();
		        double giaBan = sp.getGiaBan();
		        String congDung = sp.getCongDung();
		        LocalDate hanSuDung = sp.getHanSuDung();
		        String baoQuan = sp.getBaoQuan();
		        String chongChiDinh = sp.getChongChiDinh();
		        LocalDate ngaySanXuat = sp.getNgaySanXuat();
		        String thanhPhan = sp.getThanhPhan();
		        int soLuongTonkho = sp.getSoLuongTonkho();
		        String ghiChu = (sp.getGhiChu() != null) ? sp.getGhiChu() : "Không có ghi chú"; // Kiểm tra ghi chú
		        String nhaSanXuat = sp.getNhaSanXuat();
		        DonViTinh donViTinh = sp.getDonViTinh();
		        double thueGTGT = sp.getThueGTGT();
		        double giaNhap = sp.getGiaNhap();
		        
		        // Lấy mã loại sản phẩm và mã hóa đơn
		        String loaiSanPhamStr = (sp.getLoaiSanPham() != null) ? sp.getLoaiSanPham().getMaLoai() : "Không xác định";
		        String hoaDonNhapStr = (sp.getHoaDonNhap() != null) ? sp.getHoaDonNhap().getMaHoaDonNhap() : "Không xác định";

		        // Thêm dòng vào bảng
		        model.addRow(new Object[]{
		            maSanPham, tenSanPham, baoQuan, chongChiDinh, congDung, donViTinh, ghiChu, giaBan, giaNhap, hanSuDung,
		            ngaySanXuat, nhaSanXuat, soLuongTonkho, thanhPhan, thueGTGT, hoaDonNhapStr, loaiSanPhamStr
		        });
		    }
		}

		//
		public List<SanPham> getDanhSachSanPhamFromTable(JTable table) {
		    List<SanPham> danhSachSanPham = new ArrayList<>();

		    // Lấy model của JTable
		    DefaultTableModel model = (DefaultTableModel) table.getModel();

		    // Duyệt qua tất cả các dòng trong bảng (bắt đầu từ dòng 0 đến model.getRowCount() - 1)
		    for (int i = 0; i < model.getRowCount(); i++) {
		        // Tạo một đối tượng SanPham mới
		        SanPham sp = new SanPham();

		        try {
		            // Duyệt qua tất cả các cột trong bảng và gán giá trị vào đối tượng SanPham
		            String[] fieldNames = {
		                "maSanPham", "tenSanPham","baoQuan", "chongChiDinh","congDung",  "donViTinh","ghiChu","giaBan", "giaNhap", "hanSuDung",  
		                 "ngaySanXuat","nhaSanXuat", "soLuongTonkho",  "thanhPhan", 
		                 "thueGTGT",  "hoaDonNhap", "loaiSanPham"
		            };
		            

		            for (int j = 0; j < fieldNames.length; j++) {
		                // Lấy Field của lớp SanPham
		                Field field = SanPham.class.getDeclaredField(fieldNames[j]);
		                field.setAccessible(true);  // Cho phép truy cập trường private

		                // Lấy giá trị từ bảng và gán vào trường tương ứng
		                Object value = model.getValueAt(i, j);  // Lấy giá trị tại dòng i, cột j

		                // Xử lý các kiểu dữ liệu đặc biệt trước khi gán giá trị cho trường
		                if (value != null) {
		                    value = convertFieldValue(value, field.getType());
		                    field.set(sp, value);  // Gán giá trị vào trường tương ứng của đối tượng SanPham
		                }
		            }
		        } catch (NoSuchFieldException | IllegalAccessException e) {
		            e.printStackTrace(); // Xử lý ngoại lệ khi không thể truy cập trường
		        }

		        // Thêm đối tượng SanPham vào danh sách
		        danhSachSanPham.add(sp);
		    }

		    return danhSachSanPham; // Trả về danh sách sản phẩm từ bảng
		}

		// Phương thức chuyển đổi giá trị theo kiểu dữ liệu của trường
		private Object convertFieldValue1(Object value, Class<?> fieldType) {
		    if (fieldType == boolean.class || fieldType == Boolean.class) {
		        return value instanceof Boolean ? value : Boolean.parseBoolean(value.toString());
		    } else if (fieldType == LocalDate.class) {
		        return value instanceof String ? LocalDate.parse((String) value) : value;
		    } else if (fieldType == double.class || fieldType == Double.class) {
		        return value instanceof Double ? value : Double.parseDouble(value.toString());
		    } else if (fieldType == ChucVu.class) {
		        return value instanceof String ? ChucVu.valueOf((String) value) : value;
		    }
		    return value;
		}
		private void searchInTable(String maSP) {
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
		    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		    table.setRowSorter(sorter);

		    // Tạo bộ lọc tìm kiếm cho cột mã sản phẩm (giả sử cột mã sản phẩm là cột đầu tiên)
		    RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter("(?i)" + maSP, 0);
		    sorter.setRowFilter(filter);
		}


}