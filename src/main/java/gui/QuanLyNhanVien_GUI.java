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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Dimension;

import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.ChucVu;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
public class QuanLyNhanVien_GUI extends JFrame implements MouseListener,ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNgaySinh;
	private JTextField txtCMND;
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
	private JButton btnLuu;
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
						"M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn", "SDT", "ng\u00E0y sinh", "ng\u00E0y v\u00E0o l\u00E0m ", "l\u01B0\u01A1ng c\u0103n b\u1EA3n ", "Ch\u1EE9c v\u1EE5", "CMND", "Tr\u00ECnh \u0110\u1ED9 ", "\u0110\u1ECBa Ch\u1EC9", "Gi\u1EDBi T\u00EDnh", "Email", "Tr\u1EA1ng Th\u00E1i ", "M\u1EADt Kh\u1EA9u "
					}
				) {
					Class[] columnTypes = new Class[] {
						Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, String.class, String.class, String.class, String.class, Integer.class, String.class, Double.class
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
								 
								 
								 				// Nút "Xóa Trắng"
								 			btnXoaTrang = new JButton("Xóa Trắng");
								 			btnXoaTrang.setBounds(195, 89, 167, 35);
								 			panel_2.add(btnXoaTrang);
								 			btnXoaTrang.setOpaque(true);
								 			btnXoaTrang.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 			btnXoaTrang.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			btnXoaTrang.setBackground(new Color(46, 139, 87));
								 			btnXoaTrang.setIcon(new ImageIcon(scaledImageXT));
								 			
								 			JPanel panel_2_1 = new JPanel();
								 			panel_2_1.setLayout(null);
								 			TitledBorder titledBorder = BorderFactory.createTitledBorder("Thao tác ");
								 	        //titledBorder.setTitleColor(Color.RED);  // Đặt màu chữ cho tiêu đề
								 	        titledBorder.setBorder(BorderFactory.createLineBorder(Color.black));  // Đặt màu cho viền
								 			panel_2_1.setBorder(titledBorder);
								 			panel_2_1.setBackground(new Color(226, 250, 252));
								 			
								 			panel_2_1.setBounds(1165, 246, 372, 206);
								 			panel.add(panel_2_1);
								 			
								 			 btThoat = new JButton("Thoát");
								 			 btThoat.setBounds(91, 150, 206, 39);
								 			 panel_2_1.add(btThoat);
								 			 btThoat.setOpaque(true);
								 			 btThoat.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 			 btThoat.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 btThoat.setBackground(new Color(46, 139, 87));
								 			 btThoat.setIcon(new ImageIcon(scaledImageThoat));
								 			 
								 			 				// Nút "Lưu"
								 			 				 btnLuu = new JButton("Lưu");
								 			 				 btnLuu.setBounds(32, 91, 133, 39);
								 			 				 panel_2_1.add(btnLuu);
								 			 				 btnLuu.setOpaque(true);
								 			 				 btnLuu.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								 			 				 btnLuu.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 btnLuu.setBackground(new Color(46, 139, 87));
								 			 				 btnLuu.setIcon(new ImageIcon(scaledImageLuu));
								 			 				 
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
								 			 				 				 			cboChucVuNV.addItem("Nhân Viên");
								 			 				 				 			
								 			 				 				 			cboChucVuNV.addItem("Quản Lý");
								 			 				 				 			
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
								 			 				 btnLuu.addActionListener(this);
								 			 btThoat.addActionListener(this);
								 			btnXoaTrang.addActionListener(this);
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
	    
	    salesMenu.addMouseListener(createMenuMouseAdapter(this, BanHang_GUI.class));
	    homeMenu.addMouseListener(createMenuMouseAdapter(this, TrangChu_GUI.class));
	    
	    manageMenuItem1.addActionListener(createMenuActionListener(this, QuanLySanPham_GUI.class));
	    manageMenuItem2.addActionListener(createMenuActionListener(this, QuanLyNhanVien_GUI.class));
	    manageMenuItem3.addActionListener(createMenuActionListener(this, QuanLyNhanVien_GUI.class));

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
			QuanLyNhanVien_GUI quanLyKhachHang = new QuanLyNhanVien_GUI();
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
				    String tenNV = txtTenNV.getText();
				    String sdt = txtSDT.getText();
				    String ngaySinh = txtNSNV.getText();
				    String ngayVaoLam = txtNVLNV.getText();
				    String luongCB = txtLuong.getText();
				    String chucVu = cboChucVuNV.getSelectedItem().toString();
				    String cmnd = txtCMNDNV.getText();
				    String trinhDo = txtTDNV.getText();
				    String diaChi = txtDiaChiNV.getText();
				    String email = txtEmailNV.getText();
				    String matKhau = txtMK.getText();
				    String gioiTinh = rdbtnNam.isSelected() ? "Nam" : "Nữ";
				    String trangThai = txtTrangThaiNV.getText();

				    if (tenNV.isEmpty() || sdt.isEmpty() || ngaySinh.isEmpty() || ngayVaoLam.isEmpty() || luongCB.isEmpty() ||
				        chucVu.isEmpty() || cmnd.isEmpty() || trinhDo.isEmpty() || diaChi.isEmpty() || email.isEmpty() || matKhau.isEmpty()) {
				        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				        return; 
				    }
                    if(checkData()) {
				    DefaultTableModel model = (DefaultTableModel) table.getModel();
				    int rowCount = model.getRowCount();
				    String maNV = String.format("NV%03d", rowCount + 1);

				    model.addRow(new Object[] { maNV, tenNV, sdt, ngaySinh, ngayVaoLam, luongCB, chucVu, cmnd, trinhDo, diaChi, gioiTinh, email, trangThai, matKhau });

				    JOptionPane.showMessageDialog(null, "Thêm nhân viên vào bảng thành công!");
                    
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

				       
				        if (row == -1) {
				            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				            return;  
				        }

				       
				        DefaultTableModel model = (DefaultTableModel) table.getModel();
				        model.removeRow(row);

				        
				        JOptionPane.showMessageDialog(null, "Xóa nhân viên khỏi bảng thành công!");

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

				        String tenNV = txtTenNV.getText();
				        String sdt = txtSDT.getText();
				        String ngaySinh = txtNSNV.getText();
				        String ngayVaoLam = txtNVLNV.getText();
				        String luongCB = txtLuong.getText();
				        String chucVu = cboChucVuNV.getSelectedItem().toString();
				        String cmnd = txtCMNDNV.getText();
				        String trinhDo = txtTDNV.getText();
				        String diaChi = txtDiaChiNV.getText();
				        String email = txtEmailNV.getText();
				        String matKhau = txtMK.getText();
				        String gioiTinh = rdbtnNam.isSelected() ? "Nam" : "Nữ";
				        String trangThai = txtTrangThaiNV.getText();

				        if (tenNV.isEmpty() || sdt.isEmpty() || ngaySinh.isEmpty() || ngayVaoLam.isEmpty() || luongCB.isEmpty() ||
				            chucVu.isEmpty() || cmnd.isEmpty() || trinhDo.isEmpty() || diaChi.isEmpty() || email.isEmpty() || matKhau.isEmpty()) {
				            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				            return; 
				        }

				        DefaultTableModel model = (DefaultTableModel) table.getModel();

				       
				        model.setValueAt(tenNV, row, 1); 
				        model.setValueAt(sdt, row, 2);
				        model.setValueAt(ngaySinh, row, 3); 
				        model.setValueAt(ngayVaoLam, row, 4); 
				        model.setValueAt(luongCB, row, 5); 
				        model.setValueAt(chucVu, row, 6);
				        model.setValueAt(cmnd, row, 7); 
				        model.setValueAt(trinhDo, row, 8);
				        model.setValueAt(diaChi, row, 9); // Cập nhật Địa Chỉ
				        model.setValueAt(gioiTinh, row, 10); // Cập nhật Giới Tính
				        model.setValueAt(email, row, 11); // Cập nhật Email
				        model.setValueAt(trangThai, row, 12); // Cập nhật Trạng Thái
				        model.setValueAt(matKhau, row, 13); // Cập nhật Mật Khẩu

				        JOptionPane.showMessageDialog(null, "Sửa thông tin nhân viên thành công!");

				      
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
			//	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Điều chỉnh theo định dạng của bạn

				// Thêm phương thức chuyển đổi ngày vào trong mã xử lý sự kiện
				
				 // Trong phần xử lý sự kiện của btnLuu
				 if (o.equals(btnLuu)) {
				     formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				     DefaultTableModel model = (DefaultTableModel) table.getModel();
				     int rowCount = model.getRowCount();

				     if (rowCount == 0) {
				         JOptionPane.showMessageDialog(null, "Không có dữ liệu để lưu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				         return;
				     }

				     boolean isCleared = dao_nv.clearAllNhanVien();
				     if (!isCleared) {
				         JOptionPane.showMessageDialog(null, "Lỗi khi xóa dữ liệu cũ trong cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				         return;
				     }

				     boolean isError = false;
				     for (int i = 0; i < rowCount; i++) {
				         String maNV = model.getValueAt(i, 0) != null ? model.getValueAt(i, 0).toString() : "";
				         String tenNV = model.getValueAt(i, 1) != null ? model.getValueAt(i, 1).toString() : "";
				         String sdt = model.getValueAt(i, 2) != null ? model.getValueAt(i, 2).toString() : "";
				         String ngaySinh = model.getValueAt(i, 3) != null ? model.getValueAt(i, 3).toString() : "";
				         String ngayVaoLam = model.getValueAt(i, 4) != null ? model.getValueAt(i, 4).toString() : "";
				         String luongCB = model.getValueAt(i, 5) != null ? model.getValueAt(i, 5).toString() : "";
				         String chucVu = model.getValueAt(i, 6) != null ? model.getValueAt(i, 6).toString() : "";
				         String cmnd = model.getValueAt(i, 7) != null ? model.getValueAt(i, 7).toString() : "";
				         String trinhDo = model.getValueAt(i, 8) != null ? model.getValueAt(i, 8).toString() : "";
				         String diaChi = model.getValueAt(i, 9) != null ? model.getValueAt(i, 9).toString() : "";
				         String gioiTinh = model.getValueAt(i, 10) != null ? model.getValueAt(i, 10).toString() : "";
				         String email = model.getValueAt(i, 11) != null ? model.getValueAt(i, 11).toString() : "";
				         String trangThai = model.getValueAt(i, 12) != null ? model.getValueAt(i, 12).toString() : "";
				         String matKhau = model.getValueAt(i, 13) != null ? model.getValueAt(i, 13).toString() : "";

				         // Chuyển đổi chuỗi ngày từ d/MM/yyyy sang LocalDate
				         LocalDate ngaySinhDate = convertStringToDate(ngaySinh);
				         LocalDate ngayVaoLamDate = convertStringToDate(ngayVaoLam);

				         // Kiểm tra ngày chuyển đổi có hợp lệ không (ngày null nghĩa là lỗi chuyển đổi)
				         if (ngaySinhDate == null || ngayVaoLamDate == null) {
				             isError = true;
				             break;
				         }

				         boolean gioiTinhBoolean = gioiTinh.equalsIgnoreCase("Nam");

				         if (maNV.isEmpty() || tenNV.isEmpty() || sdt.isEmpty() || luongCB.isEmpty() || cmnd.isEmpty() ||
				                 trinhDo.isEmpty() || diaChi.isEmpty() || gioiTinh.isEmpty() || email.isEmpty() || 
				                 trangThai.isEmpty() || matKhau.isEmpty()) {
				             JOptionPane.showMessageDialog(null, "Dữ liệu không đầy đủ tại dòng " + (i + 1), "Lỗi", JOptionPane.ERROR_MESSAGE);
				             isError = true;
				             break;
				         }

				         try {
				             double luongCanBan = Double.parseDouble(luongCB);

				             boolean isSaved = dao_nv.saveNhanVien(
				                 maNV, tenNV, sdt, ngaySinhDate, ngayVaoLamDate, luongCanBan, chucVu, cmnd, trinhDo, diaChi,
				                 gioiTinhBoolean, email, matKhau, trangThai
				             );

				             if (!isSaved) {
				                 JOptionPane.showMessageDialog(null, "Lỗi khi lưu nhân viên: " + maNV, "Lỗi", JOptionPane.ERROR_MESSAGE);
				                 isError = true;
				                 break;
				             }
				         } catch (NumberFormatException e1) {
				             JOptionPane.showMessageDialog(null, "Lương cơ bản không hợp lệ tại dòng " + (i + 1), "Lỗi", JOptionPane.ERROR_MESSAGE);
				             isError = true;
				             break;
				         }
				     }

				     if (!isError) {
				         JOptionPane.showMessageDialog(null, "Lưu tất cả nhân viên thành công!");
				     }
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


		 private LocalDate convertStringToDate(String dateStr) {
		     DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		     try {
		         return LocalDate.parse(dateStr, inputFormatter);
		     } catch (DateTimeParseException e) {
		         JOptionPane.showMessageDialog(null, "Ngày không hợp lệ: " + dateStr, "Lỗi", JOptionPane.ERROR_MESSAGE);
		         return null; // Trả về null nếu ngày không hợp lệ
		     }
		 }


		@Override
		public void mouseClicked(MouseEvent e) {
		    int selectedRow = table.getSelectedRow();
		    
		    if (selectedRow != -1) {
		        String maNhanVien = table.getValueAt(selectedRow, 0).toString();
		        String tenNhanVien = table.getValueAt(selectedRow, 1).toString();
		        String sDT = table.getValueAt(selectedRow, 2).toString();
		        String ngaySinh = table.getValueAt(selectedRow, 3).toString();
		        String ngayVaoLam = table.getValueAt(selectedRow, 4).toString();
		        String luongCanBan = table.getValueAt(selectedRow, 5).toString();
		        String chucVu = table.getValueAt(selectedRow, 6).toString();
		        String cMND = table.getValueAt(selectedRow, 7).toString();
		        String trinhDo = table.getValueAt(selectedRow, 8).toString();
		        String diaChi = table.getValueAt(selectedRow, 9).toString();
		        String gioiTinh = table.getValueAt(selectedRow, 10).toString();
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
		        cboChucVuNV.setSelectedItem(chucVu);  // comboBoxChucVu là JComboBox cho chức vụ
		        
		        txtCMND.setText(cMND);
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
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
		        JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ. Vui lòng nhập lại theo định dạng dd/MM/yyyy.", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
		    if (cmnd.length() != 9) {
		        JOptionPane.showMessageDialog(null, "CMND phải có 9 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
}
