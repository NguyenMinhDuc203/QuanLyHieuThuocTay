package gui;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
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

import com.toedter.calendar.JDateChooser;

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
import java.time.ZoneId;
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
		private JRadioButton rdbNu;
		private DateTimeFormatter formatter;
		private AbstractButton txtCMND;
		private JDateChooser dateChooser;
		private JDateChooser dateChooser_1;
		
		
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
				
				
				JButton btnNewButton = new JButton("ÄÄƒng Xuáº¥t");
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
				
				
				JLabel lblNewLabel = new JLabel("QUáº¢N LÃ NHÃ‚N VIÃŠN ");
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
						"M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn", "SDT", "Giá»›i TÃ­nh ", "NgÃ y Sinh ", "NgÃ y VÃ o lÃ m  ", "LÆ°Æ¡ng cÄƒn báº£n", "chá»©c vá»¥ ", "CMND", "TrÃ¬nh Äá»™", "Äá»‹a Chá»‰", "Email ", "Tráº¡ng ThÃ¡i  ","Máº­t kháº©u"
					}
				) {
					Class[] columnTypes = new Class[] {
						Object.class, Object.class, String.class, Object.class, Object.class, Object.class, Object.class, String.class, Object.class, String.class, String.class, Object.class, String.class, Object.class
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

				// Táº¡o renderer cho táº¥t cáº£ cÃ¡c cá»™t
				DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
				    @Override
				    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				        
				        // Äáº·t mÃ u ná»n xen káº½
				        if (!isSelected) {
				            if (row % 2 == 0) {
				                c.setBackground(Color.WHITE);
				            } else {
				                c.setBackground(new Color(200, 230, 220)); // MÃ u xanh nháº¡t
				            }
				        } else {
				            c.setBackground(table.getSelectionBackground());
				        }
				        setHorizontalAlignment(SwingConstants.CENTER); 
				        return c;
				    }
				};

				// Ãp dá»¥ng renderer cho táº¥t cáº£ cÃ¡c cá»™t
				for (int i = 0; i < table.getColumnCount(); i++) {
				    table.getColumnModel().getColumn(i).setCellRenderer(renderer);
				}
				
				// Láº¥y JTableHeader tá»« báº£ng
				JTableHeader header = table.getTableHeader();
				header.setFont(new Font("Tahoma", Font.BOLD, 14));
				header.setBackground(new Color(46,139,87)); 
				header.setForeground(Color.BLACK); 
				header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
				table.setShowGrid(false);
				table.setIntercellSpacing(new Dimension(0, 0)); // XÃ³a khoáº£ng cÃ¡ch giá»¯a cÃ¡c Ã´

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
				panel_2.setBounds(1165, 102, 372, 145);
				TitledBorder titledBorder1 = BorderFactory.createTitledBorder("TÃ¬m Kiáº¿m nhÃ¢n ViÃªn");
	 	        //titledBorder.setTitleColor(Color.RED);  // Äáº·t mÃ u chá»¯ cho tiÃªu Ä‘á»
	 	        titledBorder1.setBorder(BorderFactory.createLineBorder(Color.black));  // Äáº·t mÃ u cho viá»n
	 			panel_2.setBorder(titledBorder1);
				panel_2.setBackground(new Color(226, 250, 252));
				panel.add(panel_2);
												panel_2.setLayout(null);
								
												
												txtNhap = new JTextField();
												txtNhap.setBounds(71, 49, 240, 30);
												panel_2.add(txtNhap);
												txtNhap.setColumns(10);
				
								
								JLabel lblNhpMNhn = new JLabel("Nháº­p mÃ£ nhÃ¢n ViÃªn ");
								lblNhpMNhn.setBounds(124, 22, 130, 17);
								panel_2.add(lblNhpMNhn);
								lblNhpMNhn.setFont(new Font("Tahoma", Font.PLAIN, 14));
								
								// NÃºt "TÃ¬m"
								 btnTim = new JButton("TÃ¬m Kiáº¿m ");
								 btnTim.setBounds(26, 89, 159, 35);
								 panel_2.add(btnTim);
								 btnTim.setOpaque(true);
								 btnTim.setForeground(new Color(255, 255, 255)); // Äá»•i mÃ u chá»¯ thÃ nh tráº¯ng
								 btnTim.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 btnTim.setBackground(new Color(46, 139, 87));
								 
								  btThoat = new JButton("ThoÃ¡t");
								  btThoat.setBounds(199, 90, 152, 34);
								  panel_2.add(btThoat);
								  btThoat.setOpaque(true);
								  btThoat.setForeground(new Color(255, 255, 255)); // Äá»•i mÃ u chá»¯ thÃ nh tráº¯ng
								  btThoat.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								  btThoat.setBackground(new Color(46, 139, 87));
								  btThoat.setIcon(new ImageIcon(scaledImageThoat));
								  btThoat.addActionListener(this);
								 			
								 			JPanel panel_2_1 = new JPanel();
								 			panel_2_1.setLayout(null);
								 			TitledBorder titledBorder = BorderFactory.createTitledBorder("Thao tÃ¡c ");
								 	        //titledBorder.setTitleColor(Color.RED);  // Äáº·t mÃ u chá»¯ cho tiÃªu Ä‘á»
								 	        titledBorder.setBorder(BorderFactory.createLineBorder(Color.black));  // Äáº·t mÃ u cho viá»n
								 			panel_2_1.setBorder(titledBorder);
								 			panel_2_1.setBackground(new Color(226, 250, 252));
								 			
								 			panel_2_1.setBounds(1165, 278, 372, 158);
								 			panel.add(panel_2_1);
								 			 				 
								 			 				 				// NÃºt "Sá»­a"
								 			 				 				 btnSua = new JButton("Sá»­a");
								 			 				 				 btnSua.setBounds(205, 31, 157, 37);
								 			 				 				 panel_2_1.add(btnSua);
								 			 				 				 btnSua.setOpaque(true);
								 			 				 				 btnSua.setForeground(new Color(255, 255, 255)); // Äá»•i mÃ u chá»¯ thÃ nh tráº¯ng
								 			 				 				 btnSua.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 btnSua.setBackground(new Color(46, 139, 87));
								 			 				 				 btnSua.setIcon(new ImageIcon(scaledImageSua));
								 			 				 				 
								 			 				 				 
								 			 				 				 				// NÃºt "XÃ³a"
								 			 				 				 				 btnXoa = new JButton("XÃ³a");
								 			 				 				 				 btnXoa.setBounds(205, 93, 157, 39);
								 			 				 				 				 panel_2_1.add(btnXoa);
								 			 				 				 				 btnXoa.setOpaque(true);
								 			 				 				 				 btnXoa.setForeground(new Color(255, 255, 255)); // Äá»•i mÃ u chá»¯ thÃ nh tráº¯ng
								 			 				 				 				 btnXoa.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 				 btnXoa.setBackground(new Color(46, 139, 87));
								 			 				 				 				 btnXoa.setIcon(new ImageIcon(scaledImageXoa));
								 			 				 				 				 
								 			 				 				 				 
								 			 				 				 				 // NÃºt "ThÃªm"
								 			 				 				 				  btnThem = new JButton("ThÃªm");
								 			 				 				 				  btnThem.setBounds(10, 30, 167, 39);
								 			 				 				 				  panel_2_1.add(btnThem);
								 			 				 				 				  btnThem.setOpaque(true);
								 			 				 				 				  btnThem.setForeground(new Color(255, 255, 255));
								 			 				 				 				  btnThem.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 				  btnThem.setBackground(new Color(46, 139, 87));
								 			 				 				 				  btnThem.setIcon(new ImageIcon(scaledImageThem));
								 			 				 				 				  
								 			 				 				 				  
								 			 				 				 				  				// NÃºt "XÃ³a Tráº¯ng"
								 			 				 				 				  			btnXoaTrang = new JButton("XÃ³a Tráº¯ng");
								 			 				 				 				  			btnXoaTrang.setBounds(10, 95, 167, 35);
								 			 				 				 				  			panel_2_1.add(btnXoaTrang);
								 			 				 				 				  			btnXoaTrang.setOpaque(true);
								 			 				 				 				  			btnXoaTrang.setForeground(new Color(255, 255, 255)); // Äá»•i mÃ u chá»¯ thÃ nh tráº¯ng
								 			 				 				 				  			btnXoaTrang.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								 			 				 				 				  			btnXoaTrang.setBackground(new Color(46, 139, 87));
								 			 				 				 				  			btnXoaTrang.setIcon(new ImageIcon(scaledImageXT));
								 			 				 				 				  			btnXoaTrang.addActionListener(this);
								 			 				 				 				  
								 			 				 				 				  JPanel panel_1 = new JPanel();
								 			 				 				 				  panel_1.setLayout(null);
								 			 				 				 				  panel_1.setBackground(new Color(154, 202, 189));
								 			 				 				 				  panel_1.setBounds(10, 102, 1120, 334);
								 			 				 				 				  panel.add(panel_1);
								 			 				 				 				  
								 			 				 				 				 dateChooser = new JDateChooser();

								 			 				 				 			//	dateChooser.setColumns(10);
								 			 				 				 				 dateChooser.setBounds(448, 31, 280, 30);
								 			 				 				 				  panel_1.add(dateChooser);
								 			 				 				 				  
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
								 			 				 				 				  
									 			 				 				 				 dateChooser_1 = new JDateChooser();
								 			 				 				 			//	  txtNVLNV.setColumns(10);
									 			 				 				 				dateChooser_1.setBounds(448, 98, 280, 30);
								 			 				 				 				  panel_1.add(dateChooser_1);
								 			 				 				 				  
								 			 				 				 				  txtLuong = new JTextField();
								 			 				 				 				  txtLuong.setColumns(10);
								 			 				 				 				  txtLuong.setBounds(448, 171, 280, 30);
								 			 				 				 				  panel_1.add(txtLuong);
								 			 				 				 				 cboChucVuNV = new JComboBox<>();
								 			 				 				 			cboChucVuNV.setBounds(448, 235, 280, 30);
								 			 				 				 			panel_1.add(cboChucVuNV);

								 			 				 				 			// ThÃªm cÃ¡c tÃ¹y chá»n chá»©c vá»¥ vÃ o JComboBox
								 			 				 				 			cboChucVuNV.addItem("NhÃ¢n ViÃªn");
								 			 				 				 			
								 			 				 				 			cboChucVuNV.addItem("Quáº£n LÃ½");
								 			 				 				 			
								 			 				 				 			// Báº¡n cÃ³ thá»ƒ thÃªm cÃ¡c chá»©c vá»¥ khÃ¡c náº¿u cáº§n
								 			 				 				 				  
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
								 			 				 				 				  
								 			 				 				 				  JLabel lblTenNV = new JLabel("TÃªn NhÃ¢n ViÃªn");
								 			 				 				 				  lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblTenNV.setBounds(23, 12, 126, 14);
								 			 				 				 				  panel_1.add(lblTenNV);
								 			 				 				 				  
								 			 				 				 				  JLabel lblSDT = new JLabel("Sá»‘ Äiá»‡n Thoáº¡i ");
								 			 				 				 				  lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblSDT.setBounds(23, 82, 126, 14);
								 			 				 				 				  panel_1.add(lblSDT);
								 			 				 				 				  
								 			 				 				 				  JLabel lblNgaysinh = new JLabel("NgÃ y Sinh");
								 			 				 				 				  lblNgaysinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblNgaysinh.setBounds(448, 11, 126, 16);
								 			 				 				 				  panel_1.add(lblNgaysinh);
								 			 				 				 				  
								 			 				 				 				  JLabel lblNgayVaolam = new JLabel("NgÃ y VÃ o LÃ m");
								 			 				 				 				  lblNgayVaolam.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblNgayVaolam.setBounds(448, 71, 126, 24);
								 			 				 				 				  panel_1.add(lblNgayVaolam);
								 			 				 				 				  
								 			 				 				 				  JLabel lblLngCnBnlblLngCnBn = new JLabel("LÆ°Æ¡ng CÄƒn Báº£n");
								 			 				 				 				  lblLngCnBnlblLngCnBn.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblLngCnBnlblLngCnBn.setBounds(448, 138, 126, 30);
								 			 				 				 				  panel_1.add(lblLngCnBnlblLngCnBn);
								 			 				 				 				  
								 			 				 				 				  JLabel lblChucVu = new JLabel("Chá»©c Vá»¥");
								 			 				 				 				  lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblChucVu.setBounds(448, 211, 126, 24);
								 			 				 				 				  panel_1.add(lblChucVu);
								 			 				 				 				  
								 			 				 				 				  JLabel lblCmnd = new JLabel("CMND");
								 			 				 				 				  lblCmnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblCmnd.setBounds(853, 11, 126, 16);
								 			 				 				 				  panel_1.add(lblCmnd);
								 			 				 				 				  
								 			 				 				 				  JLabel lblTrinhDo = new JLabel("TrÃ¬nh Äá»™");
								 			 				 				 				  lblTrinhDo.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblTrinhDo.setBounds(853, 81, 126, 16);
								 			 				 				 				  panel_1.add(lblTrinhDo);
								 			 				 				 				  
								 			 				 				 				  JLabel lblDiaChi = new JLabel("Äá»‹a Chá»‰");
								 			 				 				 				  lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblDiaChi.setBounds(853, 154, 126, 16);
								 			 				 				 				  panel_1.add(lblDiaChi);
								 			 				 				 				  
								 			 				 				 				  JLabel lblEmail = new JLabel("Email");
								 			 				 				 				  lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblEmail.setBounds(853, 215, 126, 16);
								 			 				 				 				  panel_1.add(lblEmail);
								 			 				 				 				  
								 			 				 				 				  JLabel lblMtKhu = new JLabel("Máº­t kháº©u");
								 			 				 				 				  lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblMtKhu.setBounds(23, 155, 126, 14);
								 			 				 				 				  panel_1.add(lblMtKhu);
								 			 				 				 				  
								 			 				 				 				  txtMK = new JTextField();
								 			 				 				 				  txtMK.setColumns(10);
								 			 				 				 				  txtMK.setBounds(23, 171, 309, 30);
								 			 				 				 				  panel_1.add(txtMK);
								 			 				 				 				  
								 			 				 				 				  JLabel lblSDT_1 = new JLabel("Giá»›i TÃ­nh");
								 			 				 				 				  lblSDT_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  lblSDT_1.setBounds(34, 285, 126, 14);
								 			 				 				 				  panel_1.add(lblSDT_1);
								 			 				 				 				  
								 			 				 				 				   rdbtnNam = new JRadioButton("Nam");
								 			 				 				 				  rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				  rdbtnNam.setBackground(new Color(154, 202, 189));
								 			 				 				 				  rdbtnNam.setBounds(32, 305, 77, 23);
								 			 				 				 				  panel_1.add(rdbtnNam);
								 			 				 				 				  
								 			 				 				 				   rdbNu = new JRadioButton("Ná»¯");
								 			 				 				 				  rdbNu.setFont(new Font("Tahoma", Font.PLAIN, 14));
								 			 				 				 				rdbNu.setBackground(new Color(154, 202, 189));
								 			 				 				 			rdbNu.setBounds(122, 305, 109, 23);
								 			 				 				 				  panel_1.add(rdbNu);
								 			 				 				 				ButtonGroup group = new ButtonGroup();
								 			 				 				 			group.add(rdbtnNam);
								 			 				 				 			group.add(rdbNu);
								 			 				 				 				  
								 			 				 				 				  JLabel lblMtKhu_1 = new JLabel("Tráº¡ng ThÃ¡i ");
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

	 // Menu Trang Chá»§
	    JMenu homeMenu = createMenu("Trang Chá»§", "/gui/house-solid.png");
	    menuBar.add(homeMenu);
	    
	    // Menu Quáº£n LÃ½
	    JMenuItem manageMenuItem1 = createMenuItem("Sáº£n Pháº©m");
	    JMenuItem manageMenuItem2 = createMenuItem("NhÃ¢n ViÃªn");
	    JMenuItem manageMenuItem3 = createMenuItem("KhÃ¡ch HÃ ng");

	    JMenu manageMenu = createMenu("Quáº£n LÃ½", "/gui/list-check-solid.png");
	    manageMenu.add(manageMenuItem1);
	    manageMenu.add(manageMenuItem2);
	    manageMenu.add(manageMenuItem3);
	    menuBar.add(manageMenu);

	    // Menu BÃ¡n HÃ ng
	    JMenu salesMenu = createMenu("BÃ¡n HÃ ng", "/gui/cart-shopping-solid.png");
	    menuBar.add(salesMenu);

	    // Menu Thá»‘ng KÃª
	    JMenuItem statsMenuItem1 = createMenuItem("Doanh Sá»‘");
	    JMenuItem statsMenuItem2 = createMenuItem("NhÃ¢n ViÃªn");
	    JMenuItem statsMenuItem3 = createMenuItem("KhÃ¡ch HÃ ng");
	    JMenuItem statsMenuItem4 = createMenuItem("Sáº£n Pháº©m");
	    
	    JMenu statsMenu = createMenu("Thá»‘ng KÃª", "/gui/clipboard-solid.png");
	    statsMenu.add(statsMenuItem1);
	    statsMenu.add(statsMenuItem2);
	    statsMenu.add(statsMenuItem3);
	    statsMenu.add(statsMenuItem4);
	    menuBar.add(statsMenu);
	    
	    // Menu Tra Cá»©u
	    JMenuItem searchMenuItem1 = createMenuItem("Sáº£n Pháº©m");
	    JMenuItem searchMenuItem2 = createMenuItem("NhÃ¢n ViÃªn");
	    JMenuItem searchMenuItem3 = createMenuItem("KhÃ¡ch HÃ ng");
	    JMenuItem searchMenuItem4 = createMenuItem("HÃ³a ÄÆ¡n");
	    
	    JMenu searchMenu = createMenu("Tra Cá»©u", "/gui/circle-question-solid.png");
	    searchMenu.add(searchMenuItem1);
	    searchMenu.add(searchMenuItem2);
	    searchMenu.add(searchMenuItem3);
	    searchMenu.add(searchMenuItem4);
	    menuBar.add(searchMenu);
	    // **Sá»± kiá»‡n cho cÃ¡c nÃºt trong menu (gá»™p chung trong má»™t hÃ m xá»­ lÃ½)**

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
				if (o.equals(btnXoaTrang)) {
				    // XÃ³a tráº¯ng cÃ¡c trÆ°á»ng nháº­p liá»‡u
				    txtTenNV.setText("");
				    txtSDT.setText("");
				    txtLuong.setText("");
				    txtCMNDNV.setText("");
				    txtTDNV.setText("");
				    txtDiaChiNV.setText("");
				    txtEmailNV.setText("");
				    txtMK.setText("");
				    txtTrangThaiNV.setText("");

				    // Äáº·t giÃ¡ trá»‹ máº·c Ä‘á»‹nh cho cÃ¡c thÃ nh pháº§n khÃ¡c
				    dateChooser.setDate(null);        // Náº¿u sá»­ dá»¥ng JDateChooser
				    dateChooser_1.setDate(null);     // Náº¿u sá»­ dá»¥ng JDateChooser
				    cboChucVuNV.setSelectedIndex(0); // Äáº·t láº¡i JComboBox vá» má»¥c Ä‘áº§u tiÃªn
				    rdbtnNam.setSelected(true);      // Äáº·t radio button máº·c Ä‘á»‹nh lÃ  "Nam"
				}

				if (o.equals(btnThem)) {
		        // Láº¥y thÃ´ng tin tá»« cÃ¡c trÆ°á»ng nháº­p liá»‡u
		        String tenNV = txtTenNV.getText();
		        String sdt = txtSDT.getText();

		        Date ngaySinh = dateChooser.getDate();
		        Date ngayVaoLam = dateChooser_1.getDate();

		        if (ngaySinh == null || ngayVaoLam == null) {
		            JOptionPane.showMessageDialog(null, "Vui lÃ²ng chá»n Ä‘áº§y Ä‘á»§ ngÃ y sinh vÃ  ngÃ y vÃ o lÃ m!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Chuyá»ƒn Ä‘á»•i sang LocalDate
		        LocalDate ngaySinh1 = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        LocalDate ngayVaoLam1 = ngayVaoLam.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		        Double luongCB;
		        try {
		            luongCB = Double.parseDouble(txtLuong.getText());
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "LÆ°Æ¡ng cÆ¡ báº£n pháº£i lÃ  sá»‘!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        String chucVu = cboChucVuNV.getSelectedItem().toString();
		        ChucVu chucVu1 = chucVu.equals("NhÃ¢n ViÃªn") ? ChucVu.NhanVien : ChucVu.QuanLy;

		        String cmnd = txtCMNDNV.getText();
		        String trinhDo = txtTDNV.getText();
		        String diaChi = txtDiaChiNV.getText();
		        String email = txtEmailNV.getText();
		        String matKhau = txtMK.getText();
		        boolean gioiTinh = rdbtnNam.isSelected();
		        String gioiTinh1 = gioiTinh ? "Nam" : "Ná»¯";

		        boolean trangThai;

		     // Láº¥y giÃ¡ trá»‹ tá»« text field vÃ  loáº¡i bá» khoáº£ng tráº¯ng
		     String trangThaiText = txtTrangThaiNV.getText().trim();

		     // Xá»­ lÃ½ giÃ¡ trá»‹
		     trangThai = trangThaiText.equalsIgnoreCase("Äang lÃ m viá»‡c");

		        // Kiá»ƒm tra dá»¯ liá»‡u
		        if (tenNV.isEmpty() || sdt.isEmpty() || chucVu.isEmpty() || cmnd.isEmpty() || trinhDo.isEmpty() || diaChi.isEmpty() || email.isEmpty() || matKhau.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lÃ²ng nháº­p Ä‘áº§y Ä‘á»§ thÃ´ng tin!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        if (checkData()) {
		            // Táº¡o mÃ£ nhÃ¢n viÃªn tá»± sinh
		            String maNV = dao_nv.maTuSinhNhanVien(chucVu);

		            // Táº¡o Ä‘á»‘i tÆ°á»£ng nhÃ¢n viÃªn
		            NhanVien nv = new NhanVien();
		            nv.setMaNhanVien(maNV);
		            nv.setChucVu(chucVu1);
		            nv.setTenNhanVien(tenNV);
		            nv.setCMND(cmnd);
		            nv.setDiaChi(diaChi);
		            nv.setEmail(email);
		            nv.setGioiTinh(gioiTinh);
		            nv.setLuongCanBan(luongCB);
		            nv.setMatKhau(matKhau);
		            nv.setNgaySinh(ngaySinh1);
		            nv.setNgayVaoLam(ngayVaoLam1);
		            nv.setSDT(sdt);
		            nv.setTrinhDo(trinhDo);

		            // LÆ°u nhÃ¢n viÃªn vÃ o database
		            boolean t = dao_nv.createNhanVien(nv);
		            if (t) {
		                DefaultTableModel model = (DefaultTableModel) table.getModel();
		                model.addRow(new Object[] { maNV, tenNV, sdt, gioiTinh1, ngaySinh1, ngayVaoLam1, luongCB, chucVu, cmnd, trinhDo, diaChi, email, trangThaiText, matKhau });
		                JOptionPane.showMessageDialog(null, "ThÃªm nhÃ¢n viÃªn vÃ o báº£ng thÃ nh cÃ´ng!");
		            } else {
		                JOptionPane.showMessageDialog(null, "Lá»—i khi lÆ°u nhÃ¢n viÃªn vÃ o database");
		            }

		            // XÃ³a dá»¯ liá»‡u trÃªn form
		            txtTenNV.setText("");
		            txtSDT.setText("");
		            dateChooser.setDate(null);
		            dateChooser_1.setDate(null);
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

				if (o.equals(btnXoa)) {
				    int row = table.getSelectedRow();
				    
				    if (row == -1) {
				        JOptionPane.showMessageDialog(null, "Vui lÃ²ng chá»n nhÃ¢n viÃªn cáº§n xÃ³a!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
				        return;
				    }

				    String maNhanVien = table.getValueAt(row, 0).toString();

				    int confirmation = JOptionPane.showConfirmDialog(
				        null,
				        "Báº¡n cÃ³ cháº¯c cháº¯n muá»‘n xÃ³a thÃ´ng tin nhÃ¢n viÃªn nÃ y khÃ´ng?",
				        "XÃ¡c nháº­n",
				        JOptionPane.YES_NO_OPTION
				    );

				    if (confirmation == JOptionPane.YES_OPTION) {
				        boolean t = dao_nv.deleteNhanVien(maNhanVien);

				        if (t) {
				            DefaultTableModel model = (DefaultTableModel) table.getModel();
				            model.removeRow(row);

				            JOptionPane.showMessageDialog(null, "XÃ³a nhÃ¢n viÃªn khá»i báº£ng thÃ nh cÃ´ng!");
				        } else {
				            JOptionPane.showMessageDialog(null, "XÃ³a nhÃ¢n viÃªn trong cÆ¡ sá»Ÿ dá»¯ liá»‡u bá»‹ lá»—i!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
				        }
				    }

				    txtTenNV.setText("");
				    txtSDT.setText("");
				    if (dateChooser != null) {
				        dateChooser.setDate(null);
				    }
				    if (dateChooser_1 != null) {
				        dateChooser_1.setDate(null);
				    }
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

				if (o.equals(btnSua)) {
				    // Chá»©c nÄƒng sá»­a thÃ´ng tin
				    int row = table.getSelectedRow();

				    if (row == -1) {
				        JOptionPane.showMessageDialog(null, "Vui lÃ²ng chá»n nhÃ¢n viÃªn cáº§n sá»­a thÃ´ng tin!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
				        return;  
				    }

				    // Láº¥y thÃ´ng tin tá»« cÃ¡c trÆ°á»ng nháº­p liá»‡u
				    String tenNV = txtTenNV.getText();
				    String sdt = txtSDT.getText();
				    Date ngaySinh = dateChooser.getDate();
				    Date ngayVaoLam = dateChooser_1.getDate();

				    if (ngaySinh == null || ngayVaoLam == null) {
				        JOptionPane.showMessageDialog(null, "Vui lÃ²ng chá»n Ä‘áº§y Ä‘á»§ ngÃ y sinh vÃ  ngÃ y vÃ o lÃ m!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
				        return;
				    }

				    // Chuyá»ƒn Ä‘á»•i sang LocalDate
				    LocalDate ngaySinh1 = ngaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				    LocalDate ngayVaoLam1 = ngayVaoLam.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				    String luongCB = txtLuong.getText();
				    String chucVu = cboChucVuNV.getSelectedItem().toString();
				    String cmnd = txtCMNDNV.getText();
				    String trinhDo = txtTDNV.getText();
				    String diaChi = txtDiaChiNV.getText();
				    String email = txtEmailNV.getText();
				    String matKhau = txtMK.getText();
				    String gioiTinh = rdbtnNam.isSelected() ? "Nam" : "Ná»¯";
				    String trangThai = txtTrangThaiNV.getText();

				    // Kiá»ƒm tra dá»¯ liá»‡u
				    if (tenNV.isEmpty() || sdt.isEmpty() || luongCB.isEmpty() || chucVu.isEmpty() || cmnd.isEmpty() || trinhDo.isEmpty() || diaChi.isEmpty() || email.isEmpty() || matKhau.isEmpty()) {
				        JOptionPane.showMessageDialog(null, "Vui lÃ²ng nháº­p Ä‘áº§y Ä‘á»§ thÃ´ng tin!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
				        return; 
				    }

				    // Cáº­p nháº­t thÃ´ng tin trong báº£ng JTable
				    DefaultTableModel model = (DefaultTableModel) table.getModel();
				    model.setValueAt(tenNV, row, 1); 
				    model.setValueAt(sdt, row, 2);
				    model.setValueAt(ngaySinh1, row, 4); 
				    model.setValueAt(ngayVaoLam1, row, 5); 
				    model.setValueAt(luongCB, row, 6); 
				    model.setValueAt(chucVu, row, 7);
				    model.setValueAt(cmnd, row, 8); 
				    model.setValueAt(trinhDo, row, 9);
				    model.setValueAt(diaChi, row, 10);
				    model.setValueAt(gioiTinh, row, 3); 
				    model.setValueAt(email, row, 11); 
				    model.setValueAt(trangThai, row, 12); 
				    model.setValueAt(matKhau, row, 13);


				    // Cáº­p nháº­t thÃ´ng tin nhÃ¢n viÃªn trong cÆ¡ sá»Ÿ dá»¯ liá»‡u
				    NhanVien nv = new NhanVien();
				    nv.setMaNhanVien(model.getValueAt(row, 0).toString()); // Láº¥y mÃ£ nhÃ¢n viÃªn tá»« cá»™t 0
				    nv.setChucVu(chucVu.equals("NhÃ¢n ViÃªn") ? ChucVu.NhanVien : ChucVu.QuanLy);
				    nv.setTenNhanVien(tenNV);
				    nv.setCMND(cmnd);
				    nv.setDiaChi(diaChi);
				    nv.setEmail(email);
				    nv.setGioiTinh(gioiTinh.equals("Nam"));
				    nv.setLuongCanBan(Double.parseDouble(luongCB));
				    nv.setMatKhau(matKhau);
				    nv.setNgaySinh(ngaySinh1);
				    nv.setNgayVaoLam(ngayVaoLam1);
				    nv.setSDT(sdt);
				    nv.setTrinhDo(trinhDo);
				    nv.setTrangThai(trangThai.equalsIgnoreCase("Äang lÃ m viá»‡c"));

				    boolean updateSuccess = dao_nv.updateNhanVien(nv); 
				    if (updateSuccess) {
				        JOptionPane.showMessageDialog(null, "Sá»­a thÃ´ng tin nhÃ¢n viÃªn thÃ nh cÃ´ng!");
				    } else {
				        JOptionPane.showMessageDialog(null, "Lá»—i khi cáº­p nháº­t thÃ´ng tin nhÃ¢n viÃªn", "Lá»—i", JOptionPane.ERROR_MESSAGE);
				    }

				    // XÃ³a dá»¯ liá»‡u trÃªn form
				    txtTenNV.setText("");
				    txtSDT.setText("");
				    dateChooser.setDate(null);
				    dateChooser_1.setDate(null);
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
				
				if (o.equals(btnTim)) {
				    String maNV = txtNhap.getText().trim(); // Láº¥y mÃ£ nhÃ¢n viÃªn tá»« Ã´ nháº­p liá»‡u tÃ¬m kiáº¿m

				    if (maNV.isEmpty()) {
				        JOptionPane.showMessageDialog(null, "Vui lÃ²ng nháº­p mÃ£ nhÃ¢n viÃªn cáº§n tÃ¬m!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
				        return;
				    }

				    // Thá»±c hiá»‡n tÃ¬m kiáº¿m nhÃ¢n viÃªn vá»›i mÃ£ nhÃ¢n viÃªn báº¯t Ä‘áº§u báº±ng maNV
				    List<NhanVien> nhanViens = dao_nv.findNhanVienByPartialId(maNV); // TÃ¬m nhÃ¢n viÃªn theo pháº§n mÃ£

				    if (!nhanViens.isEmpty()) {
				        DefaultTableModel model = (DefaultTableModel) table.getModel();
				        model.setRowCount(0); // XÃ³a háº¿t cÃ¡c dÃ²ng cÅ© trong báº£ng

				        try {
				            for (NhanVien nhanVien : nhanViens) {
				                Field[] fields = NhanVien.class.getDeclaredFields(); // Láº¥y táº¥t cáº£ cÃ¡c trÆ°á»ng trong lá»›p NhanVien
				                Object[] rowData = new Object[fields.length]; // Máº£ng chá»©a dá»¯ liá»‡u cho má»™t dÃ²ng trong báº£ng

				                // Láº¥y giÃ¡ trá»‹ cá»§a má»—i trÆ°á»ng trong Ä‘á»‘i tÆ°á»£ng nhanVien vÃ  thÃªm vÃ o máº£ng rowData
				                for (int i = 0; i < fields.length; i++) {
				                    fields[i].setAccessible(true); // Cho phÃ©p truy cáº­p trÆ°á»ng private
				                    rowData[i] = fields[i].get(nhanVien); // Láº¥y giÃ¡ trá»‹ cá»§a trÆ°á»ng tá»« Ä‘á»‘i tÆ°á»£ng
				                }

				                // ThÃªm dá»¯ liá»‡u vÃ o báº£ng
				                model.addRow(rowData);
				            }

				            JOptionPane.showMessageDialog(null, "TÃ¬m tháº¥y nhÃ¢n viÃªn vá»›i mÃ£ báº¯t Ä‘áº§u báº±ng: " + maNV);
				        } catch (IllegalAccessException e1) {
				            e1.printStackTrace();
				        }
				    } else {
				        JOptionPane.showMessageDialog(null, "KhÃ´ng tÃ¬m tháº¥y nhÃ¢n viÃªn vá»›i mÃ£ báº¯t Ä‘áº§u báº±ng: " + maNV, "KhÃ´ng tÃ¬m tháº¥y", JOptionPane.INFORMATION_MESSAGE);
				    }
				}

				 if(o.equals(btThoat)) {
					 displayNhanViensInTable();
				 }
			    }
		public class DateUtils {

		    // Chuyá»ƒn Ä‘á»•i tá»« LocalDate sang Date
		    public static Date convertLocalDateToDate(LocalDate localDate) {
		        if (localDate != null) {
		            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		        }
		        return null;  // Tráº£ vá» null náº¿u localDate lÃ  null
		    }

		    // PhÆ°Æ¡ng thá»©c chuyá»ƒn Ä‘á»•i ngÃ y tá»« "d/MM/yyyy" sang "yyyy-MM-dd"
		    public static String convertDateToStandardFormat(String dateStr) {
		        try {
		            // Äá»‹nh dáº¡ng ngÃ y gá»‘c "d/MM/yyyy"
		            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		            LocalDate date = LocalDate.parse(dateStr, inputFormatter);

		            // Äá»‹nh dáº¡ng ngÃ y má»›i "yyyy-MM-dd"
		            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		            return date.format(outputFormatter);

		        } catch (DateTimeParseException e) {
		            // Xá»­ lÃ½ lá»—i náº¿u chuá»—i ngÃ y khÃ´ng há»£p lá»‡
		            System.out.println("NgÃ y khÃ´ng há»£p lá»‡: " + dateStr);
		            return null; // Tráº£ vá» null náº¿u chuá»—i ngÃ y khÃ´ng há»£p lá»‡
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
		       
		        
		        // Láº¥y ngÃ y sinh vÃ  ngÃ y vÃ o lÃ m tá»« báº£ng (kiá»ƒu LocalDate)
		        LocalDate ngaySinhLocalDate = (LocalDate) table.getValueAt(selectedRow, 4);
		        LocalDate ngayVaoLamLocalDate = (LocalDate) table.getValueAt(selectedRow, 5);

		        // Chuyá»ƒn Ä‘á»•i LocalDate sang Date
		        Date ngaySinh = Date.from(ngaySinhLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		        Date ngayVaoLam = Date.from(ngayVaoLamLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		        
		        String luongCanBan = table.getValueAt(selectedRow, 6).toString();
		        String chucVu = table.getValueAt(selectedRow, 7).toString();
		        String cMND = table.getValueAt(selectedRow, 8).toString();
		        String trinhDo = table.getValueAt(selectedRow, 9).toString();
		        String diaChi = table.getValueAt(selectedRow, 10).toString();
		        String gioiTinh = table.getValueAt(selectedRow, 3).toString();
		        String email = table.getValueAt(selectedRow, 11).toString();
		        String trangThai = table.getValueAt(selectedRow, 12).toString();
		        String matKhau = table.getValueAt(selectedRow, 13).toString();

		        // Hiá»ƒn thá»‹ thÃ´ng tin lÃªn cÃ¡c trÆ°á»ng trong form
		        txtTenNV.setText(tenNhanVien);
		        txtSDT.setText(sDT);
		        
		        // Äáº·t ngÃ y vÃ o cÃ¡c JDateChooser
		        dateChooser.setDate(ngaySinh); // ngÃ y sinh
		        dateChooser_1.setDate(ngayVaoLam); // ngÃ y vÃ o lÃ m
		        
		        txtLuong.setText(luongCanBan);
		        
		        // Set giÃ¡ trá»‹ vÃ o ComboBox "Chá»©c vá»¥"
		        cboChucVuNV.setSelectedItem(chucVu);  // comboBoxChucVu lÃ  JComboBox cho chá»©c vá»¥
		        
		        txtCMNDNV.setText(cMND);
		        txtTDNV.setText(trinhDo);
		        txtDiaChiNV.setText(diaChi);
		        txtEmailNV.setText(email);
		        txtTrangThaiNV.setText(trangThai);
		        txtMK.setText(matKhau);
		        if (gioiTinh.equals("Nam")) {
		            rdbtnNam.setSelected(true);
		            rdbNu.setSelected(false);
		        } else {
		            rdbtnNam.setSelected(false);
		            rdbNu.setSelected(true);
		        }
		        
		        if (chucVu.equals("NhanVien")) {
		            cboChucVuNV.setSelectedItem("NhÃ¢n ViÃªn ");
		        } else {
		        	cboChucVuNV.setSelectedItem("Quáº£n LÃ½");
		        }


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
		    Date ngaySinh = dateChooser.getDate();  // Láº¥y ngÃ y tá»« JDateChooser (Date)
		    Date ngayVaoLam = dateChooser_1.getDate();
		    String luong = txtLuong.getText().trim();
		    String cmnd = txtCMNDNV.getText().trim();
		    String trinhDo = txtTDNV.getText().trim();
		    String diaChi = txtDiaChiNV.getText().trim();
		    String email = txtEmailNV.getText().trim();
		    String matKhau = txtMK.getText().trim();
		    String trangThai = txtTrangThaiNV.getText().trim();

		    if (tenNV.isEmpty() || sdt.isEmpty() || ngaySinh==null || ngayVaoLam==null ||
		        luong.isEmpty() || cmnd.isEmpty() || trinhDo.isEmpty() || diaChi.isEmpty() ||
		        email.isEmpty() || matKhau.isEmpty() || trangThai.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Vui lÃ²ng nháº­p Ä‘áº§y Ä‘á»§ thÃ´ng tin!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    // Kiá»ƒm tra SDT cÃ³ 10 hoáº·c 11 sá»‘
		    if (sdt.length() != 10 && sdt.length() != 11) {
		        JOptionPane.showMessageDialog(null, "Sá»‘ Ä‘iá»‡n thoáº¡i pháº£i cÃ³ 10 hoáº·c 11 sá»‘!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    // Kiá»ƒm tra email
		    if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
		        JOptionPane.showMessageDialog(null, "Äá»‹a chá»‰ email khÃ´ng há»£p lá»‡!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    // Kiá»ƒm tra ngÃ y vÃ o lÃ m vÃ  ngÃ y sinh
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    dateFormat.setLenient(false);
		 // Láº¥y ngÃ y hiá»‡n táº¡i
		    Date currentDate = new Date();

		    // Kiá»ƒm tra xem ngÃ y vÃ o lÃ m cÃ³ pháº£i sau ngÃ y hiá»‡n táº¡i khÃ´ng
		    if (ngayVaoLam.after(currentDate)) {
		        JOptionPane.showMessageDialog(null, "NgÃ y vÃ o lÃ m khÃ´ng thá»ƒ sau ngÃ y hiá»‡n táº¡i!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    // Kiá»ƒm tra xem ngÃ y sinh cÃ³ Ä‘á»§ 18 tuá»•i khÃ´ng
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(currentDate);
		    calendar.add(Calendar.YEAR, -18); // Giáº£m Ä‘i 18 nÄƒm
		    Date eighteenYearsAgo = calendar.getTime();

		    if (ngaySinh.after(eighteenYearsAgo)) {
		        JOptionPane.showMessageDialog(null, "NhÃ¢n viÃªn pháº£i Ä‘á»§ 18 tuá»•i!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }


		    // Kiá»ƒm tra lÆ°Æ¡ng
		    try {
		        double luongValue = Double.parseDouble(luong);
		        if (luongValue <= 0) {
		            JOptionPane.showMessageDialog(null, "LÆ°Æ¡ng pháº£i lÃ  má»™t sá»‘ lá»›n hÆ¡n 0!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
		            return false;
		        }
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(null, "LÆ°Æ¡ng pháº£i lÃ  má»™t sá»‘ há»£p lá»‡!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    // Kiá»ƒm tra CMND
		    if (cmnd.length() != 12) {
		        JOptionPane.showMessageDialog(null, "CCCD pháº£i cÃ³ 12 chá»¯ sá»‘!", "Lá»—i", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }

		    return true;
		}


		// HÃ m chung xá»­ lÃ½ sá»± kiá»‡n click chuá»™t cho JMenu
		public MouseAdapter createMenuMouseAdapter(JFrame frame, Class<?> guiClass) {
		    return new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            System.out.println("ÄÃ£ vÃ o mouseClicked"); // Kiá»ƒm tra sá»± kiá»‡n mouseClicked

		            try {
		                System.out.println("Äang khá»Ÿi táº¡o giao diá»‡n: " + guiClass.getName());

		                // Táº¡o Ä‘á»‘i tÆ°á»£ng GUI má»›i tá»« class truyá»n vÃ o
		                Object guiInstance = guiClass.getDeclaredConstructor().newInstance();
		                System.out.println("Khá»Ÿi táº¡o Ä‘á»‘i tÆ°á»£ng thÃ nh cÃ´ng");

		                // Kiá»ƒm tra náº¿u guiInstance lÃ  má»™t JFrame, thÃ¬ hiá»ƒn thá»‹ nÃ³
		                if (guiInstance instanceof JFrame) {
		                    JFrame newFrame = (JFrame) guiInstance;
		                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ÄÃ³ng cá»­a sá»• hiá»‡n táº¡i
		                    newFrame.setSize(1920, 1080);  // KÃ­ch thÆ°á»›c cá»­a sá»• má»›i
		                    newFrame.setLocationRelativeTo(null); // CÄƒn giá»¯a cá»­a sá»•
		                    newFrame.setVisible(true);  // Hiá»ƒn thá»‹ cá»­a sá»• má»›i

		                    // ÄÃ³ng cá»­a sá»• hiá»‡n táº¡i
		                    frame.dispose();  // Äáº£m báº£o cá»­a sá»• cÅ© Ä‘Æ°á»£c Ä‘Ã³ng láº¡i khi chuyá»ƒn sang cá»­a sá»• má»›i
		                    System.out.println("ÄÃ£ chuyá»ƒn sang cá»­a sá»• má»›i: " + guiClass.getName());
		                } else {
		                    System.out.println("Gui khÃ´ng pháº£i lÃ  má»™t JFrame, xá»­ lÃ½ khÃ¡c: " + guiClass.getName());
		                }
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                System.out.println("Lá»—i khi khá»Ÿi táº¡o giao diá»‡n: " + guiClass.getName());
		            }
		        }
		    };
		}



	// HÃ m chung xá»­ lÃ½ sá»± kiá»‡n
		public ActionListener createMenuActionListener(JFrame frame, Class<?> guiClass) {
		    return new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            System.out.println("ÄÃ£ vÃ o actionPerformed"); // Kiá»ƒm tra xem cÃ³ vÃ o Ä‘Ã¢y khÃ´ng
		            try {
		                System.out.println("Äang khá»Ÿi táº¡o giao diá»‡n: " + guiClass.getName());

		                Object guiInstance = guiClass.getDeclaredConstructor().newInstance();
		                System.out.println("Khá»Ÿi táº¡o Ä‘á»‘i tÆ°á»£ng thÃ nh cÃ´ng");

		                if (guiInstance instanceof JFrame) {
		                    JFrame newFrame = (JFrame) guiInstance;
		                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		                    newFrame.setSize(1920, 1080);
		                    newFrame.setLocationRelativeTo(null);
		                    newFrame.setVisible(true);

		                    // ÄÃ³ng cá»­a sá»• hiá»‡n táº¡i
		                    frame.dispose();  // Äáº£m báº£o cá»­a sá»• cÅ© Ä‘Æ°á»£c Ä‘Ã³ng láº¡i khi chuyá»ƒn sang cá»­a sá»• má»›i
		                    System.out.println("ÄÃ£ chuyá»ƒn sang cá»­a sá»• má»›i: " + guiClass.getName());
		                } else {
		                    System.out.println("Gui khÃ´ng pháº£i lÃ  má»™t JFrame, xá»­ lÃ½ khÃ¡c: " + guiClass.getName());
		                }
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                System.out.println("Lá»—i khi khá»Ÿi táº¡o giao diá»‡n: " + guiClass.getName());
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
		    // Láº¥y táº¥t cáº£ nhÃ¢n viÃªn tá»« cÆ¡ sá»Ÿ dá»¯ liá»‡u
		    List<NhanVien> nhanViens = dao_nv.getAllNhanViens();

		    // Kiá»ƒm tra xem danh sÃ¡ch nhÃ¢n viÃªn cÃ³ rá»—ng khÃ´ng
		    if (nhanViens == null || nhanViens.isEmpty()) {
		        System.out.println("KhÃ´ng cÃ³ nhÃ¢n viÃªn Ä‘á»ƒ hiá»ƒn thá»‹.");
		        return; // ThoÃ¡t khá»i phÆ°Æ¡ng thá»©c náº¿u khÃ´ng cÃ³ nhÃ¢n viÃªn nÃ o
		    }

		    // Láº¥y mÃ´ hÃ¬nh báº£ng
		    DefaultTableModel model = (DefaultTableModel) table.getModel();

		    // XÃ³a cÃ¡c dÃ²ng hiá»‡n cÃ³ trong báº£ng trÆ°á»›c khi thÃªm dá»¯ liá»‡u má»›i
		    model.setRowCount(0);

		    // Duyá»‡t qua danh sÃ¡ch nhÃ¢n viÃªn vÃ  thÃªm vÃ o báº£ng
		    for (NhanVien nv : nhanViens) {
		        try {
		            // Truy xuáº¥t cÃ¡c thuá»™c tÃ­nh riÃªng tÆ° báº±ng reflection
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
		            Object gioiTinhValue = (boolean) gioiTinhField.get(nv) ? "Nam" : "Ná»¯";  // Äá»‹nh dáº¡ng giá»›i tÃ­nh

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
		            Object trangThaiValue = (boolean) trangThaiField.get(nv) ? "Äang lÃ m viá»‡c" : "Nghá»‰ viá»‡c";

		            // ThÃªm dá»¯ liá»‡u nhÃ¢n viÃªn vÃ o má»™t dÃ²ng cá»§a báº£ng
		            model.addRow(new Object[]{
		                maNhanVienValue, tenNhanVienValue, sdtValue, gioiTinhValue, ngaySinhValue, ngayVaoLamValue,
		                luongCanBanValue, chucVuValue, cmndValue, trinhDoValue, diaChiValue, emailValue, trangThaiValue, matKhauValue
		            });
		        } catch (NoSuchFieldException | IllegalAccessException e) {
		            // Xá»­ lÃ½ ngoáº¡i lá»‡ liÃªn quan Ä‘áº¿n reflection
		            e.printStackTrace();
		        }
		    }
		}
		public List<NhanVien> getDanhSachNhanVienFromTable(JTable table) {
		    List<NhanVien> danhSachNhanVien = new ArrayList<>();

		    // Láº¥y model cá»§a JTable
		    DefaultTableModel model = (DefaultTableModel) table.getModel();

		    // Duyá»‡t qua táº¥t cáº£ cÃ¡c dÃ²ng trong báº£ng (báº¯t Ä‘áº§u tá»« dÃ²ng 0 Ä‘áº¿n model.getRowCount() - 1)
		    for (int i = 0; i < model.getRowCount(); i++) {
		        // Táº¡o má»™t Ä‘á»‘i tÆ°á»£ng NhanVien má»›i
		        NhanVien nv = new NhanVien();

		        try {
		            // Duyá»‡t qua táº¥t cáº£ cÃ¡c cá»™t trong báº£ng vÃ  gÃ¡n giÃ¡ trá»‹ vÃ o Ä‘á»‘i tÆ°á»£ng NhanVien
		            String[] fieldNames = {
		                "maNhanVien", "tenNhanVien", "sDT", "gioiTinh", "ngaySinh", "ngayVaoLam", 
		                "luongCanBan", "chucVu", "cMND", "trinhDo", "diaChi", "email", "matKhau", "trangThai"
		            };

		            for (int j = 0; j < fieldNames.length; j++) {
		                // Láº¥y Field cá»§a lá»›p NhanVien
		                Field field = NhanVien.class.getDeclaredField(fieldNames[j]);
		                field.setAccessible(true);  // Cho phÃ©p truy cáº­p trÆ°á»ng private

		                // Láº¥y giÃ¡ trá»‹ tá»« báº£ng vÃ  gÃ¡n vÃ o trÆ°á»ng tÆ°Æ¡ng á»©ng
		                Object value = model.getValueAt(i, j);  // Láº¥y giÃ¡ trá»‹ táº¡i dÃ²ng i, cá»™t j

		                // Xá»­ lÃ½ cÃ¡c kiá»ƒu dá»¯ liá»‡u Ä‘áº·c biá»‡t trÆ°á»›c khi gÃ¡n giÃ¡ trá»‹ cho trÆ°á»ng
		                if (value != null) {
		                    value = convertFieldValue(value, field.getType());
		                    field.set(nv, value);  // GÃ¡n giÃ¡ trá»‹ vÃ o trÆ°á»ng tÆ°Æ¡ng á»©ng cá»§a Ä‘á»‘i tÆ°á»£ng NhanVien
		                }
		            }
		        } catch (NoSuchFieldException | IllegalAccessException e) {
		            e.printStackTrace(); // Xá»­ lÃ½ ngoáº¡i lá»‡ khi khÃ´ng thá»ƒ truy cáº­p trÆ°á»ng
		        }

		        // ThÃªm Ä‘á»‘i tÆ°á»£ng NhanVien vÃ o danh sÃ¡ch
		        danhSachNhanVien.add(nv);
		    }

		    return danhSachNhanVien; // Tráº£ vá» danh sÃ¡ch nhÃ¢n viÃªn tá»« báº£ng
		}

		// PhÆ°Æ¡ng thá»©c chuyá»ƒn Ä‘á»•i giÃ¡ trá»‹ theo kiá»ƒu dá»¯ liá»‡u cá»§a trÆ°á»ng
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


}
