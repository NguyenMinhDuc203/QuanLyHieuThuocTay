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
import java.util.Random;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Component;
import java.awt.Dimension;

import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.ChucVu;
import entity.KhachHang;
import entity.NhanVien;
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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.sql.*;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class QuanLyNhanVien_GUI extends JFrame implements MouseListener,ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNgaySinh;
	
	private JTextField txtNVL;
	private JTextField txtNhap;
	private JTable table;
	private JButton btnThem;
	private JButton btnXoaTrang;

	private NhanVien_DAO dao_nv = new NhanVien_DAO();
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnTim;
	private JButton btThoat;
	private KhachHang_DAO dao_kh = new KhachHang_DAO();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");

	    private DefaultTableModel tbm = new DefaultTableModel();
	    private JTextField txtNSNV;
	    private JTextField txtCMNDNV;
	    private JTextField txtTenNV;
	    private JTextField txtSDT;
	    private JTextField txtNVLNV;
	    private JTextField txtLuong;
	    
	    private JTextField txtTDNV;
	    private JTextField txtDiaChiNV;
	    private JTextField txtEmailNV;
	    private JTextField txtMK;
	    private JTextField txtTrangThaiNV;
	    private JTable table_1;
		private JComboBox cboChucVuNV;
		private JRadioButton rdbtnNam;
		private JRadioButton rdbNư;
		private DateTimeFormatter formatter;
		private AbstractButton txtCMND;
		
		
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
				JMenuBar menuBar = createMenuBar();
				menuBar.setBorderPainted(false);
				menuBar.setOpaque(true);
				menuBar.setBackground(new Color(26, 133, 94));
				menuBar.setBounds(0, 0, 1338, 70);
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
				
				
				JLabel lblNewLabel = new JLabel("QUẢN LÝ NHÂN VIÊN ");
				lblNewLabel.setForeground(new Color(46, 139, 87));
				lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 40));
				lblNewLabel.setBounds(97, 11, 512, 70);
				ImageIcon poster = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/poster.png"));
				Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
				imageLabel.setBounds(10, 11, 77, 81);
				panel.add(imageLabel);
				panel.add(lblNewLabel);

				ImageIcon iconThem = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/4993253681582956831-128.png"));
				Image imgThem = iconThem.getImage();
				BufferedImage bImageThem = new BufferedImage(imgThem.getWidth(null), imgThem.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dThem = bImageThem.createGraphics();
				g2dThem.drawImage(imgThem, 0, 0, null);
				g2dThem.setComposite(AlphaComposite.SrcIn);
				g2dThem.setColor(Color.WHITE);
				g2dThem.fillRect(0, 0, bImageThem.getWidth(), bImageThem.getHeight());
				g2dThem.dispose();
				Image scaledImageThem = bImageThem.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

				ImageIcon iconXoa = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/320632131667326703-128.png"));
				Image imgXoa = iconXoa.getImage();
				BufferedImage bImageXoa = new BufferedImage(imgXoa.getWidth(null), imgXoa.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dXoa = bImageXoa.createGraphics();
				g2dXoa.drawImage(imgXoa, 0, 0, null);
				g2dXoa.setComposite(AlphaComposite.SrcIn);
				g2dXoa.setColor(Color.WHITE);
				g2dXoa.fillRect(0, 0, bImageXoa.getWidth(), bImageXoa.getHeight());
				g2dXoa.dispose();
				Image scaledImageXoa = bImageXoa.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

				ImageIcon iconSua = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/setting-icon.png"));
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
				scrollPane.setBounds(10, 464, 1515, 317);
				panel.add(scrollPane);
				table = new JTable();
				table.setFont(new Font("Tahoma", Font.BOLD, 13));
				table.setBackground(new Color(220, 220, 220));
				table.setRowHeight(40);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						
					},
					new String[] {
						"M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn", "SDT", "Giới Tính ", "Ngày Sinh ", "Ngày Vào làm  ", "Lương căn bản", "chức vụ ", "CMND", "Trình Độ", "Địa Chỉ", "Email ", "Trạng Thái  ","Mật khẩu"
					}
				) {
					Class[] columnTypes = new Class[] {
						Object.class, Object.class, String.class, Object.class, Object.class, Object.class, Object.class, String.class, Object.class, String.class, String.class, Object.class, String.class, Object.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					// Ghi đè phương thức isCellEditable để tất cả các ô không thể chỉnh sửa
				    @Override
				    public boolean isCellEditable(int row, int column) {
				        return false; // Không cho phép chỉnh sửa ô
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
				header.setFont(new Font("Tahoma", Font.BOLD, 14));
				header.setBackground(new Color(46,139,87)); 
				header.setForeground(Color.BLACK); 
				header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
				table.setShowGrid(false);
				table.setIntercellSpacing(new Dimension(0, 0)); // Xóa khoảng cách giữa các ô

				scrollPane.setViewportView(table);
				
				table_1 = new JTable();
				scrollPane.setColumnHeaderView(table_1);

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
				panel_2.setBounds(1165, 66, 372, 145);
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
				
								
								JLabel lblNhpMNhn = new JLabel("Nhập mã nhân Viên ");
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
								 
								  btThoat = new JButton("Thoát");
								  btThoat.setBounds(195, 89, 159, 35);
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
								 			
								 			panel_2_1.setBounds(1165, 246, 372, 171);
								 			panel.add(panel_2_1);
								 			 				 
								 			 				 				// Nút "Sửa"
								 			 				 				 btnSua = new JButton("Sửa");
								 			 				 				 btnSua.setBounds(206, 31, 133, 37);
								 			 				 				 panel_2_1.add(btnSua);
								 			 				 				 btnSua.setOpaque(true);
								 			 				 				 btnSua.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 			 				 				 btnSua.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 btnSua.setBackground(new Color(46, 139, 87));
								 			 				 				 btnSua.setIcon(new ImageIcon(scaledImageSua));
								 			 				 				 
								 			 				 				 
								 			 				 				 				// Nút "Xóa"
								 			 				 				 				 btnXoa = new JButton("Xóa");
								 			 				 				 				 btnXoa.setBounds(206, 91, 133, 39);
								 			 				 				 				 panel_2_1.add(btnXoa);
								 			 				 				 				 btnXoa.setOpaque(true);
								 			 				 				 				 btnXoa.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 			 				 				 				 btnXoa.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 				 btnXoa.setBackground(new Color(46, 139, 87));
								 			 				 				 				 btnXoa.setIcon(new ImageIcon(scaledImageXoa));
								 			 				 				 				 
								 			 				 				 				 
								 			 				 				 				 // Nút "Thêm"
								 			 				 				 				  btnThem = new JButton("Thêm");
								 			 				 				 				  btnThem.setBounds(32, 30, 133, 39);
								 			 				 				 				  panel_2_1.add(btnThem);
								 			 				 				 				  btnThem.setOpaque(true);
								 			 				 				 				  btnThem.setForeground(new Color(255, 255, 255));
								 			 				 				 				  btnThem.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 				  btnThem.setBackground(new Color(46, 139, 87));
								 			 				 				 				  btnThem.setIcon(new ImageIcon(scaledImageThem));
								 			 				 				 				  
								 			 				 				 				  
								 			 				 				 				  				// Nút "Xóa Trắng"
								 			 				 				 				  			btnXoaTrang = new JButton("Xóa Trắng");
								 			 				 				 				  			btnXoaTrang.setBounds(32, 91, 133, 39);
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
								 			 				 				 				  panel_1.setBounds(10, 102, 1120, 334);
								 			 				 				 				  panel.add(panel_1);
								 			 				 				 				  
								 			 				 				 				  txtNSNV = new JTextField();
								 			 				 				 				  txtNSNV.setColumns(10);
								 			 				 				 				  txtNSNV.setBounds(448, 31, 280, 30);
								 			 				 				 				  panel_1.add(txtNSNV);
								 			 				 				 				  
								 			 				 				 				  txtCMNDNV = new JTextField();
								 			 				 				 				  txtCMNDNV.setColumns(10);
								 			 				 				 				  txtCMNDNV.setBounds(853, 31, 257, 30);
								 			 				 				 				  panel_1.add(txtCMNDNV);
								 			 				 				 				  
								 			 				 				 				  txtTenNV = new JTextField();
								 			 				 				 				  txtTenNV.setColumns(10);
								 			 				 				 				  txtTenNV.setBounds(23, 31, 309, 30);
								 			 				 				 				  panel_1.add(txtTenNV);
								 			 				 				 				  
								 			 				 				 				  txtSDT = new JTextField();
								 			 				 				 				  txtSDT.setColumns(10);
								 			 				 				 				  txtSDT.setBounds(23, 98, 309, 30);
								 			 				 				 				  panel_1.add(txtSDT);
								 			 				 				 				  
								 			 				 				 				  txtNVLNV = new JTextField();
								 			 				 				 				  txtNVLNV.setColumns(10);
								 			 				 				 				  txtNVLNV.setBounds(448, 98, 280, 30);
								 			 				 				 				  panel_1.add(txtNVLNV);
								 			 				 				 				  
								 			 				 				 				  txtLuong = new JTextField();
								 			 				 				 				  txtLuong.setColumns(10);
								 			 				 				 				  txtLuong.setBounds(448, 171, 280, 30);
								 			 				 				 				  panel_1.add(txtLuong);
								 			 				 				 				 cboChucVuNV = new JComboBox<>();
								 			 				 				 			cboChucVuNV.setBounds(448, 235, 280, 30);
								 			 				 				 			panel_1.add(cboChucVuNV);

								 			 				 				 			// Thêm các tùy chọn chức vụ vào JComboBox
								 			 				 				 			cboChucVuNV.addItem("NhanVien");
								 			 				 				 			
								 			 				 				 			cboChucVuNV.addItem("QuanLy");
								 			 				 				 			
								 			 				 				 			// Bạn có thể thêm các chức vụ khác nếu cần
								 			 				 				 				  
								 			 				 				 				  txtTDNV = new JTextField();
								 			 				 				 				  txtTDNV.setColumns(10);
								 			 				 				 				  txtTDNV.setBounds(853, 98, 257, 30);
								 			 				 				 				  panel_1.add(txtTDNV);
								 			 				 				 				  
								 			 				 				 				  txtDiaChiNV = new JTextField();
								 			 				 				 				  txtDiaChiNV.setColumns(10);
								 			 				 				 				  txtDiaChiNV.setBounds(853, 171, 257, 30);
								 			 				 				 				  panel_1.add(txtDiaChiNV);
								 			 				 				 				  
								 			 				 				 				  txtEmailNV = new JTextField();
								 			 				 				 				  txtEmailNV.setColumns(10);
								 			 				 				 				  txtEmailNV.setBounds(853, 235, 257, 30);
								 			 				 				 				  panel_1.add(txtEmailNV);
								 			 				 				 				  
								 			 				 				 				  JLabel lblTenNV = new JLabel("Tên Nhân Viên");
								 			 				 				 				  lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblTenNV.setBounds(23, 12, 126, 14);
								 			 				 				 				  panel_1.add(lblTenNV);
								 			 				 				 				  
								 			 				 				 				  JLabel lblSDT = new JLabel("Số Điện Thoại ");
								 			 				 				 				  lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblSDT.setBounds(23, 82, 126, 14);
								 			 				 				 				  panel_1.add(lblSDT);
								 			 				 				 				  
								 			 				 				 				  JLabel lblNgaysinh = new JLabel("Ngày Sinh");
								 			 				 				 				  lblNgaysinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblNgaysinh.setBounds(448, 11, 126, 16);
								 			 				 				 				  panel_1.add(lblNgaysinh);
								 			 				 				 				  
								 			 				 				 				  JLabel lblNgayVaolam = new JLabel("Ngày Vào Làm");
								 			 				 				 				  lblNgayVaolam.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblNgayVaolam.setBounds(448, 71, 126, 24);
								 			 				 				 				  panel_1.add(lblNgayVaolam);
								 			 				 				 				  
								 			 				 				 				  JLabel lblLngCnBnlblLngCnBn = new JLabel("Lương Căn Bản");
								 			 				 				 				  lblLngCnBnlblLngCnBn.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblLngCnBnlblLngCnBn.setBounds(448, 138, 126, 30);
								 			 				 				 				  panel_1.add(lblLngCnBnlblLngCnBn);
								 			 				 				 				  
								 			 				 				 				  JLabel lblChucVu = new JLabel("Chức Vụ");
								 			 				 				 				  lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblChucVu.setBounds(448, 211, 126, 24);
								 			 				 				 				  panel_1.add(lblChucVu);
								 			 				 				 				  
								 			 				 				 				  JLabel lblCmnd = new JLabel("CMND");
								 			 				 				 				  lblCmnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblCmnd.setBounds(853, 11, 126, 16);
								 			 				 				 				  panel_1.add(lblCmnd);
								 			 				 				 				  
								 			 				 				 				  JLabel lblTrinhDo = new JLabel("Trình Độ");
								 			 				 				 				  lblTrinhDo.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblTrinhDo.setBounds(853, 81, 126, 16);
								 			 				 				 				  panel_1.add(lblTrinhDo);
								 			 				 				 				  
								 			 				 				 				  JLabel lblDiaChi = new JLabel("Địa Chỉ");
								 			 				 				 				  lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblDiaChi.setBounds(853, 154, 126, 16);
								 			 				 				 				  panel_1.add(lblDiaChi);
								 			 				 				 				  
								 			 				 				 				  JLabel lblEmail = new JLabel("Email");
								 			 				 				 				  lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblEmail.setBounds(853, 215, 126, 16);
								 			 				 				 				  panel_1.add(lblEmail);
								 			 				 				 				  
								 			 				 				 				  JLabel lblMtKhu = new JLabel("Mật khẩu");
								 			 				 				 				  lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblMtKhu.setBounds(23, 155, 126, 14);
								 			 				 				 				  panel_1.add(lblMtKhu);
								 			 				 				 				  
								 			 				 				 				  txtMK = new JTextField();
								 			 				 				 				  txtMK.setColumns(10);
								 			 				 				 				  txtMK.setBounds(23, 171, 309, 30);
								 			 				 				 				  panel_1.add(txtMK);
								 			 				 				 				  
								 			 				 				 				  JLabel lblSDT_1 = new JLabel("Giới Tính");
								 			 				 				 				  lblSDT_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblSDT_1.setBounds(34, 285, 126, 14);
								 			 				 				 				  panel_1.add(lblSDT_1);
								 			 				 				 				  
								 			 				 				 				   rdbtnNam = new JRadioButton("Nam");
								 			 				 				 				  rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  rdbtnNam.setBackground(new Color(154, 202, 189));
								 			 				 				 				  rdbtnNam.setBounds(32, 305, 77, 23);
								 			 				 				 				  panel_1.add(rdbtnNam);
								 			 				 				 				  
								 			 				 				 				   rdbNư = new JRadioButton("Nữ");
								 			 				 				 				  rdbNư.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  rdbNư.setBackground(new Color(154, 202, 189));
								 			 				 				 				  rdbNư.setBounds(122, 305, 109, 23);
								 			 				 				 				  panel_1.add(rdbNư);
								 			 				 				 				  
								 			 				 				 				  JLabel lblMtKhu_1 = new JLabel("Trạng Thái ");
								 			 				 				 				  lblMtKhu_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblMtKhu_1.setBounds(23, 214, 126, 14);
								 			 				 				 				  panel_1.add(lblMtKhu_1);
								 			 				 				 				  
								 			 				 				 				  txtTrangThaiNV = new JTextField();
								 			 				 				 				  txtTrangThaiNV.setColumns(10);
								 			 				 				 				  txtTrangThaiNV.setBounds(23, 230, 309, 30);
								 			 				 				 				  panel_1.add(txtTrangThaiNV);
								 			 				 				 				  btnThem.addActionListener(this);
								 			 				 				 				 btnXoa.addActionListener(this);
								 			 				 				 btnSua.addActionListener(this);
								 btnTim.addActionListener(this);
				
				//Actions Menu
				
			table.addMouseListener(this);

			displayNhanViensInTable();
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
	    
	 //   salesMenu.addMouseListener(createMenuMouseAdapter(this, BanHang_GUI.class));
	    homeMenu.addMouseListener(createMenuMouseAdapter(this, TrangChu_GUI.class));
	    
	    manageMenuItem1.addActionListener(createMenuActionListener(this, QuanLySanPham_GUI.class));
	    manageMenuItem2.addActionListener(createMenuActionListener(this, QuanLyNhanVien_GUI.class));
	    manageMenuItem3.addActionListener(createMenuActionListener(this, QuanLyNhanVien_GUI.class));

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
	        QuanLyNhanVien_GUI quanLyNhanVien = new QuanLyNhanVien_GUI();
	        quanLyNhanVien.setVisible(true);
	        this.setVisible(false);
	    }
		public void openQuanLyKhachHang() {
			QuanLyNhanVien_GUI quanLyKhachHang = new QuanLyNhanVien_GUI();
	        quanLyKhachHang.setVisible(true);
	        this.setVisible(false);
	    }
		public void openBanHang() {
	  //      BanHang_GUI banHang = new BanHang_GUI();
	    //    banHang.setVisible(true);
	      //  this.setVisible(false);
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
					    txtNSNV.setText("");
					    txtNVLNV.setText("");
					    txtLuong.setText("");
					    txtCMNDNV.setText("");
					    txtTDNV.setText("");
					    txtDiaChiNV.setText("");
					    txtEmailNV.setText("");
					    txtMK.setText("");
					    txtTrangThaiNV.setText("");
					    cboChucVuNV.setSelectedIndex(0);
					    rdbtnNam.setSelected(true);
					
				}
				if (o.equals(btnThem)) {
					
					String tenNV = txtTenNV.getText().trim();
					String sdt = txtSDT.getText().trim();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

					LocalDate ngaySinh = null;
					LocalDate ngayVaoLam = null;
					try {
					    ngaySinh = LocalDate.parse(txtNSNV.getText().trim(), formatter);
					    ngayVaoLam = LocalDate.parse(txtNVLNV.getText().trim(), formatter);
					} catch (DateTimeParseException e1) {
					    JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.", "Lỗi", JOptionPane.ERROR_MESSAGE);
					    return;
					}

					Double luongCB = null;
					try {
					    luongCB = Double.parseDouble(txtLuong.getText().trim());
					} catch (NumberFormatException e1) {
					    JOptionPane.showMessageDialog(null, "Lương cơ bản phải là một số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					    return;
					}

					String cmnd = txtCMNDNV.getText().trim();
					String trinhDo = txtTDNV.getText().trim();
					String diaChi = txtDiaChiNV.getText().trim();
					String email = txtEmailNV.getText().trim();
					String matKhau = txtMK.getText().trim();
					boolean gioiTinh = rdbtnNam.isSelected();
					String trangThai = txtTrangThaiNV.getText().trim();
					boolean isActive = trangThai.equalsIgnoreCase("Đang hoạt động");

					String selectedChucVu = cboChucVuNV.getSelectedItem().toString();
					ChucVu chucVu1 = null;
					try {
					    chucVu1 = ChucVu.valueOf(selectedChucVu);
					} catch (IllegalArgumentException e1) {
					    JOptionPane.showMessageDialog(null, "Chức vụ không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					    return;
					}

					// Kiểm tra xem có trường nào bị trống không
					if (tenNV.isEmpty() || sdt.isEmpty() || ngaySinh == null || ngayVaoLam == null || luongCB == null ||
					    chucVu1 == null || cmnd.isEmpty() || trinhDo.isEmpty() || diaChi.isEmpty() || email.isEmpty() || matKhau.isEmpty()) {
					    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					    return;
					}
				
					
					// Tiếp tục xử lý nếu tất cả các trường đều hợp lệ
					// Ví dụ: lưu thông tin khách hàng vào cơ sở dữ liệu

                    if(checkData()) {
                    	
				    DefaultTableModel model = (DefaultTableModel) table.getModel();
				    if (chucVu1 == ChucVu.NhanVien) {
				        
				        boolean isExist = kiemTraNhanVienTonTai( sdt, cmnd);
				        
				        if (isExist) {
				            JOptionPane.showMessageDialog(null, "Nhân viên đã tồn tại");
				        } else {
				        	String maNV = taoMaNhanVien("NVBH");
					        NhanVien nv= new NhanVien();
					    	nv.setMaNhanVien(maNV);
					    	nv.setTenNhanVien(tenNV);
					    	nv.setSDT(sdt);
					    	nv.setGioiTinh(gioiTinh);
					    	nv.setNgaySinh(ngaySinh);
					    	nv.setNgayVaoLam(ngayVaoLam);
					    	nv.setChucVu(chucVu1);
					    	nv.setCMND(cmnd);
					    	nv.setTrinhDo(trinhDo);
					    	nv.setDiaChi(diaChi);
					    	nv.setEmail(email);
					    	nv.setMatKhau(matKhau);
					    	nv.setTrangThai(isActive);
				        	dao_nv.create(nv);
				        	model.addRow(new Object[] {
				        		    maNV,
				        		    tenNV,
				        		    sdt,
				        		    gioiTinh ? "Nam" : "Nữ", // Chuyển đổi boolean giới tính sang chuỗi
				        		    ngaySinh,
				        		    ngayVaoLam,
				        		    luongCB,
				        		    chucVu1, // Chức vụ là kiểu enum ChucVu
				        		    cmnd,
				        		    trinhDo,
				        		    diaChi,
				        		    email,
				        		    isActive ? "Đang hoạt động" : "Ngừng hoạt động", // Chuyển đổi trạng thái boolean sang chuỗi
				        		    matKhau
				        		});

				            model.fireTableDataChanged();
				            JOptionPane.showMessageDialog(null, "Thêm nhân viên vào bảng thành công!");
				        }
				    } else {
				        String maNV = taoMaNhanVien("NVQL");
				        boolean isExist = kiemTraNhanVienTonTai( sdt, cmnd);

				        if (isExist) {
				            JOptionPane.showMessageDialog(null, "Nhân viên đã tồn tại");
				        } else {
				        	String maNV1 = taoMaNhanVien("NVBH");
					        NhanVien nv= new NhanVien();
					    	nv.setMaNhanVien(maNV1);
					    	nv.setTenNhanVien(tenNV);
					    	nv.setSDT(sdt);
					    	nv.setGioiTinh(gioiTinh);
					    	nv.setNgaySinh(ngaySinh);
					    	nv.setNgayVaoLam(ngayVaoLam);
					    	nv.setChucVu(chucVu1);
					    	nv.setCMND(cmnd);
					    	nv.setTrinhDo(trinhDo);
					    	nv.setDiaChi(diaChi);
					    	nv.setEmail(email);
					    	nv.setMatKhau(matKhau);
					    	nv.setTrangThai(isActive);
				        	dao_nv.create(nv);

				        	model.addRow(new Object[] {
				        		    maNV1,
				        		    tenNV,
				        		    sdt,
				        		    gioiTinh ? "Nam" : "Nữ", // Chuyển đổi boolean giới tính sang chuỗi
				        		    ngaySinh,
				        		    ngayVaoLam,
				        		    luongCB,
				        		    chucVu1, // Chức vụ là kiểu enum ChucVu
				        		    cmnd,
				        		    trinhDo,
				        		    diaChi,
				        		    email,
				        		    isActive ? "Đang hoạt động" : "Ngừng hoạt động", // Chuyển đổi trạng thái boolean sang chuỗi
				        		    matKhau
				        		});
				            model.fireTableDataChanged();
				            JOptionPane.showMessageDialog(null, "Thêm nhân viên vào bảng thành công!");
				        }
				    }

                    
				    txtTenNV.setText("");
				    txtSDT.setText("");
				    txtNSNV.setText("");
				    txtNVLNV.setText("");
				    txtLuong.setText("");
				    txtCMNDNV.setText("");
				    txtTDNV.setText("");
				    txtDiaChiNV.setText("");
				    txtEmailNV.setText("");
				    txtMK.setText("");
				    txtTrangThaiNV.setText("");
				    cboChucVuNV.setSelectedIndex(0);
				    rdbtnNam.setSelected(true);
				}
				}
				
				 if(o.equals(btnXoa)) {
				       
				        int row = table.getSelectedRow();
				        String maNhanVien = table.getValueAt(row, 0).toString();

				       
				        if (row == -1) {
				            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				            return;  
				        }

				        int confirmation = JOptionPane.showConfirmDialog(
				        	    null,
				        	    "Bạn có chắc chắn muốn lưu thông tin nhân viên này không?", 
				        	    "Xác nhận",  
				        	    JOptionPane.YES_NO_OPTION
				        	);
				        if (confirmation == JOptionPane.YES_OPTION) {
				        dao_nv.delete(maNhanVien);
				        DefaultTableModel model = (DefaultTableModel) table.getModel();
				        model.removeRow(row);

				        
				        JOptionPane.showMessageDialog(null, "Xóa nhân viên khỏi bảng thành công!");}

				        txtTenNV.setText("");
					    txtSDT.setText("");
					    txtNSNV.setText("");
					    txtNVLNV.setText("");
					    txtLuong.setText("");
					    txtCMNDNV.setText("");
					    txtTDNV.setText("");
					    txtDiaChiNV.setText("");
					    txtEmailNV.setText("");
					    txtMK.setText("");
					    txtTrangThaiNV.setText("");
					    cboChucVuNV.setSelectedIndex(0);
					    rdbtnNam.setSelected(true);  
				    }
				 if(o.equals(btnSua)) {
					    // Chức năng sửa thông tin
					    int row = table.getSelectedRow();

					    if (row == -1) {
					        JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					        return;  
					    }
					    String maNhanVien = table.getValueAt(row, 0).toString();
					    String tenNV = txtTenNV.getText().trim();
					    String sdt = txtSDT.getText().trim();
					    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

					    LocalDate ngaySinh = null;
					    LocalDate ngayVaoLam = null;
					    try {
					        ngaySinh = LocalDate.parse(txtNSNV.getText().trim(), formatter);
					        ngayVaoLam = LocalDate.parse(txtNVLNV.getText().trim(), formatter);
					    } catch (DateTimeParseException e1) {
					        JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.", "Lỗi", JOptionPane.ERROR_MESSAGE);
					        return;
					    }

					    Double luongCB = null;
					    try {
					        luongCB = Double.parseDouble(txtLuong.getText().trim());
					    } catch (NumberFormatException e1) {
					        JOptionPane.showMessageDialog(null, "Lương cơ bản phải là một số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					        return;
					    }

					    String cmnd = txtCMNDNV.getText().trim();
					    String trinhDo = txtTDNV.getText().trim();
					    String diaChi = txtDiaChiNV.getText().trim();
					    String email = txtEmailNV.getText().trim();
					    String matKhau = txtMK.getText().trim();
					    
					    // Xử lý giới tính (nam hay nữ)
					    boolean gioiTinh = rdbtnNam.isSelected(); // nếu chọn nam thì là true, ngược lại là false
					    
					    String trangThai = txtTrangThaiNV.getText().trim();
					    boolean isActive = trangThai.equalsIgnoreCase("Đang hoạt động");

					 // Lấy giá trị chức vụ từ JComboBox
					    String selectedChucVu = cboChucVuNV.getSelectedItem().toString();

					    // Kiểm tra nếu giá trị chức vụ không hợp lệ
					    if (selectedChucVu == null || selectedChucVu.trim().isEmpty()) {
					        JOptionPane.showMessageDialog(null, "Vui lòng chọn chức vụ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					        return;
					    }

					    // Biến lưu trữ chức vụ
					    ChucVu chucVu1 = null;

					    // Kiểm tra và chuyển đổi giá trị từ chuỗi thành enum ChucVu
					    try {
					        chucVu1 = ChucVu.valueOf(selectedChucVu.trim());
					    } catch (IllegalArgumentException e1) {
					        // Nếu không tìm thấy giá trị hợp lệ trong enum ChucVu, thông báo lỗi cho người dùng
					        JOptionPane.showMessageDialog(null, "Chức vụ không hợp lệ! Vui lòng chọn lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
					        return;
					    }

					    // Tiến hành cập nhật thông tin nhân viên
					    NhanVien nv = new NhanVien();
					    nv.setMaNhanVien(maNhanVien);
					    nv.setTenNhanVien(tenNV);
					    nv.setSDT(sdt);
					    nv.setGioiTinh(gioiTinh);
					    nv.setNgaySinh(ngaySinh);
					    nv.setNgayVaoLam(ngayVaoLam);
					    nv.setChucVu(chucVu1);  // Cập nhật chức vụ đã chọn vào nhân viên
					    nv.setCMND(cmnd);
					    nv.setTrinhDo(trinhDo);
					    nv.setDiaChi(diaChi);
					    nv.setEmail(email);
					    nv.setMatKhau(matKhau);
					    nv.setTrangThai(isActive);

					    // Gọi phương thức cập nhật nhân viên trong DAO
					    dao_nv.updatenhanVien(nv);

					    // Cập nhật bảng hiển thị
					    DefaultTableModel model = (DefaultTableModel) table.getModel();
					    model.setValueAt(tenNV, row, 1);
					    model.setValueAt(sdt, row, 2);
					    model.setValueAt(ngaySinh, row, 4);
					    model.setValueAt(ngayVaoLam, row, 5);
					    model.setValueAt(luongCB, row, 6);
					    model.setValueAt(chucVu1, row, 7);  // Cập nhật chức vụ trong bảng
					    model.setValueAt(cmnd, row, 8);
					    model.setValueAt(trinhDo, row, 9);
					    model.setValueAt(diaChi, row, 10);
					    model.setValueAt(gioiTinh ? "Nam" : "Nữ", row, 3);
					    model.setValueAt(email, row, 11);
					    model.setValueAt(trangThai, row, 12);
					    model.setValueAt(matKhau, row, 13);

					    // Thông báo thành công
					    JOptionPane.showMessageDialog(null, "Sửa thông tin nhân viên thành công!");

					    // Xóa các trường nhập liệu
					    txtTenNV.setText("");
					    txtSDT.setText("");
					    txtNSNV.setText("");
					    txtNVLNV.setText("");
					    txtLuong.setText("");
					    txtCMNDNV.setText("");
					    txtTDNV.setText("");
					    txtDiaChiNV.setText("");
					    txtEmailNV.setText("");
					    txtMK.setText("");
					    txtTrangThaiNV.setText("");
					    cboChucVuNV.setSelectedIndex(0);
					    rdbtnNam.setSelected(true);  // Đặt lại giới tính mặc định là Nam
				 }
				
				 
				 if (o.equals(btnTim)) {
					    String maNV = txtNhap.getText().trim(); // Lấy mã nhân viên từ ô nhập liệu tìm kiếm

					    if (maNV.isEmpty()) {
					        JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên cần tìm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					        return;
					    }

					    NhanVien nhanVien = dao_nv.findNhanVienById(maNV); // Tìm nhân viên theo mã

					    if (nhanVien != null) {
					        DefaultTableModel model = (DefaultTableModel) table.getModel();
					        model.setRowCount(0); // Xóa hết các dòng cũ trong bảng

					        try {
					            Field[] fields = NhanVien.class.getDeclaredFields(); // Lấy tất cả các trường trong lớp NhanVien
					            Object[] rowData = new Object[fields.length]; // Mảng chứa dữ liệu cho một dòng trong bảng

					            // Lấy giá trị của mỗi trường trong đối tượng nhanVien và thêm vào mảng rowData
					            for (int i = 0; i < fields.length; i++) {
					                fields[i].setAccessible(true); // Cho phép truy cập trường private
					                rowData[i] = fields[i].get(nhanVien); // Lấy giá trị của trường từ đối tượng
					            }

					            // Thêm dữ liệu vào bảng
					            model.addRow(rowData);

					            JOptionPane.showMessageDialog(null, "Tìm thấy nhân viên: " + maNV);
					        } catch (IllegalAccessException e1) {
					            e1.printStackTrace();
					        }
					    } else {
					        JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên với mã: " + maNV, "Không tìm thấy", JOptionPane.INFORMATION_MESSAGE);
					    }
					}
				 if(o.equals(btThoat)) {
					 displayNhanViensInTable();
				 }
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


	


		@Override
		public void mouseClicked(MouseEvent e) {
		    int selectedRow = table.getSelectedRow();
		    
		    if (selectedRow != -1) {
		        String maNhanVien = table.getValueAt(selectedRow, 0).toString();
		        String tenNhanVien = table.getValueAt(selectedRow, 1).toString();
		        String sDT = table.getValueAt(selectedRow, 2).toString();
		        String ngaySinh = table.getValueAt(selectedRow, 4).toString();
		        String ngayVaoLam = table.getValueAt(selectedRow, 5).toString();
		        String luongCanBan = table.getValueAt(selectedRow, 6).toString();
		        //String chucVu = table.getValueAt(selectedRow, ).toString();
		        String cMND = table.getValueAt(selectedRow, 8).toString();
		        String trinhDo = table.getValueAt(selectedRow, 9).toString();
		        String diaChi = table.getValueAt(selectedRow, 10).toString();
		        String gioiTinh = table.getValueAt(selectedRow, 3).toString();
		        String email = table.getValueAt(selectedRow, 11).toString();
		        String trangThai = table.getValueAt(selectedRow, 12).toString();
		        String matKhau = table.getValueAt(selectedRow, 13).toString();

		        // Hiển thị thông tin lên các trường trong form
		      
		        txtTenNV.setText(tenNhanVien);
		        txtSDT.setText(sDT);
		        txtNSNV.setText(ngaySinh);
		        txtNVLNV.setText(ngayVaoLam);
		        txtLuong.setText(luongCanBan);
		        
		        // Set giá trị vào ComboBox "Chức vụ"
		     //   cboChucVuNV.setSelectedItem(chucVu);  // comboBoxChucVu là JComboBox cho chức vụ
		        
		        txtCMNDNV.setText(cMND);
		        txtTDNV.setText(trinhDo);
		        txtDiaChiNV.setText(diaChi);
		       
		        txtEmailNV.setText(email);
		        txtTrangThaiNV.setText(trangThai);
		        txtMK.setText(matKhau);
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
		//
		public boolean checkData() {
		    String tenNV = txtTenNV.getText().trim();
		    String sdt = txtSDT.getText().trim();
		    String ngaySinh = txtNSNV.getText().trim();
		    String ngayVaoLam = txtNVLNV.getText().trim();
		    String luong = txtLuong.getText().trim();
		    String cmnd = txtCMNDNV.getText().trim();
		    String trinhDo = txtTDNV.getText().trim();
		    String diaChi = txtDiaChiNV.getText().trim();
		    String email = txtEmailNV.getText().trim();
		    String matKhau = txtMK.getText().trim();
		    String trangThai = txtTrangThaiNV.getText().trim();

		    if (tenNV.isEmpty() || sdt.isEmpty() || ngaySinh.isEmpty() || ngayVaoLam.isEmpty() ||
		        luong.isEmpty() || cmnd.isEmpty() || trinhDo.isEmpty() || diaChi.isEmpty() ||
		        email.isEmpty() || matKhau.isEmpty() || trangThai.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    // Kiểm tra SDT có 10 hoặc 11 số
		    if (sdt.length() != 10 && sdt.length() != 11) {
		        JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 hoặc 11 số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    // Kiểm tra email
		    if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
		        JOptionPane.showMessageDialog(null, "Địa chỉ email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    // Kiểm tra ngày vào làm và ngày sinh
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		    dateFormat.setLenient(false);
		    try {
		        Date ngaySinhDate = dateFormat.parse(ngaySinh);
		        Date ngayVaoLamDate = dateFormat.parse(ngayVaoLam);
		        Date currentDate = new Date();
		        
		        if (ngayVaoLamDate.after(currentDate)) {
		            JOptionPane.showMessageDialog(null, "Ngày vào làm không thể sau ngày hiện tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return false;
		        }

		        Calendar calendar = Calendar.getInstance();
		        calendar.setTime(currentDate);
		        calendar.add(Calendar.YEAR, -18);
		        Date eighteenYearsAgo = calendar.getTime();

		        if (ngaySinhDate.after(eighteenYearsAgo)) {
		            JOptionPane.showMessageDialog(null, "Nhân viên phải đủ 18 tuổi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return false;
		        }

		    } catch (ParseException e) {
		        JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ. Vui lòng nhập lại theo định dạng yyyy-mm-dd.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    // Kiểm tra lương
		    try {
		        Double.parseDouble(luong);
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(null, "Lương phải là một số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    // Kiểm tra CMND
		    if (cmnd.length() != 12) {
		        JOptionPane.showMessageDialog(null, "CCCD phải có 12 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    return true;
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
		public void displayNhanViensInTable() {
		    // Lấy tất cả nhân viên từ cơ sở dữ liệu
		    List<NhanVien> nhanViens = dao_nv.getAllNhanViens();

		    // Kiểm tra xem danh sách nhân viên có rỗng không
		    if (nhanViens == null || nhanViens.isEmpty()) {
		        System.out.println("Không có nhân viên để hiển thị.");
		        return; // Thoát khỏi phương thức nếu không có nhân viên nào
		    }

		    // Lấy mô hình bảng
		    DefaultTableModel model = (DefaultTableModel) table.getModel();

		    // Xóa các dòng hiện có trong bảng trước khi thêm dữ liệu mới
		    model.setRowCount(0);

		    // Duyệt qua danh sách nhân viên và thêm vào bảng
		    for (NhanVien nv : nhanViens) {
		        try {
		            // Truy xuất các thuộc tính riêng tư bằng reflection
		            Field maNhanVienField = NhanVien.class.getDeclaredField("maNhanVien");
		            maNhanVienField.setAccessible(true);
		            Object maNhanVienValue = maNhanVienField.get(nv);

		            Field tenNhanVienField = NhanVien.class.getDeclaredField("tenNhanVien");
		            tenNhanVienField.setAccessible(true);
		            Object tenNhanVienValue = tenNhanVienField.get(nv);

		            Field sdtField = NhanVien.class.getDeclaredField("sDT");
		            sdtField.setAccessible(true);
		            Object sdtValue = sdtField.get(nv);

		            Field gioiTinhField = NhanVien.class.getDeclaredField("gioiTinh");
		            gioiTinhField.setAccessible(true);
		            Object gioiTinhValue = (boolean) gioiTinhField.get(nv) ? "Nam" : "Nữ";  // Định dạng giới tính

		            Field ngaySinhField = NhanVien.class.getDeclaredField("ngaySinh");
		            ngaySinhField.setAccessible(true);
		            Object ngaySinhValue = ngaySinhField.get(nv);

		            Field ngayVaoLamField = NhanVien.class.getDeclaredField("ngayVaoLam");
		            ngayVaoLamField.setAccessible(true);
		            Object ngayVaoLamValue = ngayVaoLamField.get(nv);

		            Field luongCanBanField = NhanVien.class.getDeclaredField("luongCanBan");
		            luongCanBanField.setAccessible(true);
		            Object luongCanBanValue = luongCanBanField.get(nv);

		            Field chucVuField = NhanVien.class.getDeclaredField("chucVu");
		            chucVuField.setAccessible(true);
		            Object chucVuValue = chucVuField.get(nv);

		            Field cmndField = NhanVien.class.getDeclaredField("cMND");
		            cmndField.setAccessible(true);
		            Object cmndValue = cmndField.get(nv);

		            Field trinhDoField = NhanVien.class.getDeclaredField("trinhDo");
		            trinhDoField.setAccessible(true);
		            Object trinhDoValue = trinhDoField.get(nv);

		            Field diaChiField = NhanVien.class.getDeclaredField("diaChi");
		            diaChiField.setAccessible(true);
		            Object diaChiValue = diaChiField.get(nv);

		            Field emailField = NhanVien.class.getDeclaredField("email");
		            emailField.setAccessible(true);
		            Object emailValue = emailField.get(nv);

		            Field matKhauField = NhanVien.class.getDeclaredField("matKhau");
		            matKhauField.setAccessible(true);
		            Object matKhauValue = matKhauField.get(nv);

		            Field trangThaiField = NhanVien.class.getDeclaredField("trangThai");
		            trangThaiField.setAccessible(true);
		            Object trangThaiValue = (boolean) trangThaiField.get(nv) ? "Đang làm việc" : "Nghỉ việc";

		            // Thêm dữ liệu nhân viên vào một dòng của bảng
		            model.addRow(new Object[]{
		                maNhanVienValue, tenNhanVienValue, sdtValue, gioiTinhValue, ngaySinhValue, ngayVaoLamValue,
		                luongCanBanValue, chucVuValue, cmndValue, trinhDoValue, diaChiValue, emailValue, trangThaiValue, matKhauValue
		            });
		        } catch (NoSuchFieldException | IllegalAccessException e) {
		            // Xử lý ngoại lệ liên quan đến reflection
		            e.printStackTrace();
		        }
		    }
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
		                "luongCanBan", "chucVu", "cMND", "trinhDo", "diaChi", "email", "trangThai", "matKhau"
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

//
		

		// Thêm phương thức kiểm tra sự tồn tại của nhân viên trong bảng
		private boolean kiemTraNhanVienTonTai( String sdt, String cmnd) {
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
		    for (int i = 0; i < model.getRowCount(); i++) {
		        String tenTrongBang = model.getValueAt(i, 1).toString();
		        String sdtTrongBang = model.getValueAt(i, 2).toString();
		        String cmndTrongBang = model.getValueAt(i, 8).toString();
		        
		        if ((sdt.equals(sdtTrongBang) )) {
		            return true; // Nhân viên đã tồn tại
		        }
		        
		        if(cmnd.equals(cmndTrongBang)) {
		        	 return true;
		        }
		    }
		    return false; // Nhân viên chưa tồn tại
		}
		private String taoMaNhanVien(String tienTo) {
		    Random random = new Random();
		    String maNV;
		    boolean isUnique;

		    do {
		        // Sinh mã ngẫu nhiên với tiền tố và 9 chữ số
		        maNV = String.format("%s%09d", tienTo, random.nextInt(1000000000));
		        
		        // Kiểm tra tính duy nhất của mã trong bảng
		        isUnique = true;
		        for (int i = 0; i < table.getRowCount(); i++) {
		            if (maNV.equals(table.getValueAt(i, 0))) {
		                isUnique = false;
		                break;
		            }
		        }
		    } while (!isUnique);

		    return maNV;
		}


}
