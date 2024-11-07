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
public class Ly extends JFrame implements MouseListener,ActionListener{

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
	private JButton btnLuu;
	
	private JTextField txtDTL;
	private Ly dao_kh = new Ly();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Nhom1_QuanLyHieuThuocTay");

	    private DefaultTableModel tbm = new DefaultTableModel();
		private JTextField txtLCB;
		private JTextField txtChucVu;
		private JTextField txtTrinhDo;
		private JTextField txtDiaChi;
		private JTextField txtEmail;
		private JTextField txtMK;
		private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ly frame = new Ly();
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
	public Ly() {
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
				panel.setBounds(0, 65, 1550, 866);
				contentPane.add(panel);
				panel.setLayout(null);
				
				////
				
				
				JLabel lblNewLabel = new JLabel("QUẢN LÝ NHÂN VIÊN");
				lblNewLabel.setForeground(new Color(46, 139, 87));
				lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 40));
				lblNewLabel.setBounds(97, 11, 512, 70);
				ImageIcon poster = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/poster.png"));
				Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
				imageLabel.setBounds(10, 11, 77, 81);
				panel.add(imageLabel);
				panel.add(lblNewLabel);
				
				

				ImageIcon iconThem = new ImageIcon(Ly.class.getResource("/gui/4993253681582956831-128.png"));
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
				
JPanel panel_1 = new JPanel();
				
				panel_1.setBounds(9, 99, 1349, 334);
				panel.add(panel_1);
				panel_1.setBackground(new Color(154, 202, 189));
				panel_1.setLayout(null);
				
				txtNgaySinh = new JTextField();
				txtNgaySinh.setColumns(10);
				txtNgaySinh.setBounds(505, 31, 352, 30);
				panel_1.add(txtNgaySinh);
				
				txtCMND = new JTextField();
				txtCMND.setColumns(10);
				txtCMND.setBounds(973, 31, 352, 30);
				panel_1.add(txtCMND);
				
				txtTenNV = new JTextField();
				txtTenNV.setColumns(10);
				txtTenNV.setBounds(23, 31, 352, 30);
				panel_1.add(txtTenNV);
				
				txtSDT = new JTextField();
				txtSDT.setColumns(10);
				txtSDT.setBounds(23, 98, 352, 30);
				panel_1.add(txtSDT);
				
				txtNVL = new JTextField();
				txtNVL.setColumns(10);
				txtNVL.setBounds(505, 98, 352, 30);
				panel_1.add(txtNVL);
txtLCB = new JTextField();
				txtLCB.setColumns(10);
				txtLCB.setBounds(505, 171, 352, 30);
				panel_1.add(txtLCB);
				
				txtChucVu = new JTextField();
				txtChucVu.setColumns(10);
				txtChucVu.setBounds(505, 235, 352, 30);
				panel_1.add(txtChucVu);
				
				txtTrinhDo = new JTextField();
				txtTrinhDo.setColumns(10);
				txtTrinhDo.setBounds(973, 98, 352, 30);
				panel_1.add(txtTrinhDo);
				
				txtDiaChi = new JTextField();
				txtDiaChi.setColumns(10);
				txtDiaChi.setBounds(973, 171, 352, 30);
				panel_1.add(txtDiaChi);
				
				txtEmail = new JTextField();
				txtEmail.setColumns(10);
				txtEmail.setBounds(973, 235, 352, 30);
				panel_1.add(txtEmail);
				
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
				
				JLabel lblMtKhu = new JLabel("Mật khẩu");
				lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblMtKhu.setBounds(23, 155, 126, 14);
				panel_1.add(lblMtKhu);
				
				txtMK = new JTextField();
				txtMK.setColumns(10);
				txtMK.setBounds(23, 171, 352, 30);
				panel_1.add(txtMK);
				
				JLabel lblSDT_1 = new JLabel("Giới Tính");
				lblSDT_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblSDT_1.setBounds(34, 285, 126, 14);
				panel_1.add(lblSDT_1);
				
				JRadioButton rdbtnNam = new JRadioButton("Nam");
				rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 14));
				rdbtnNam.setBackground(new Color(154, 202, 189));
				rdbtnNam.setBounds(32, 305, 77, 23);
				panel_1.add(rdbtnNam);
				
				JRadioButton rdbNư = new JRadioButton("Nữ");
				rdbNư.setFont(new Font("Tahoma", Font.PLAIN, 14));
				rdbNư.setBackground(new Color(154, 202, 189));
				rdbNư.setBounds(122, 305, 109, 23);
				panel_1.add(rdbNư);
				
				JLabel lblMtKhu_1 = new JLabel("Mật khẩu");
				lblMtKhu_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblMtKhu_1.setBounds(23, 214, 126, 14);
				panel_1.add(lblMtKhu_1);
				
				textField = new JTextField();
				textField.setColumns(10);
				textField.setBounds(23, 230, 352, 30);
				panel_1.add(textField);
				
				// Nút "Thêm"
				JMenu btnThem = new JMenu("Thêm");
				btnThem.setOpaque(true);
				btnThem.setForeground(new Color(255, 255, 255));
				btnThem.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnThem.setBackground(new Color(46, 139, 87));
				btnThem.setBounds(10, 461, 120, 45);

				ImageIcon iconThem1 = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/4993253681582956831-128.png"));
				Image imgThem1 = iconThem1.getImage();
				BufferedImage bImageThem1 = new BufferedImage(imgThem1.getWidth(null), imgThem1.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dThem1 = bImageThem1.createGraphics();
				g2dThem1.drawImage(imgThem1, 0, 0, null);
				g2dThem1.setComposite(AlphaComposite.SrcIn);
				g2dThem1.setColor(Color.WHITE);
				g2dThem1.fillRect(0, 0, bImageThem1.getWidth(), bImageThem1.getHeight());
				g2dThem1.dispose();
				Image scaledImageThem1 = bImageThem1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				btnThem.setIcon(new ImageIcon(scaledImageThem1));
				panel.add(btnThem);

				ImageIcon iconXoa1 = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/320632131667326703-128.png"));
				Image imgXoa1 = iconXoa1.getImage();
				BufferedImage bImageXoa1 = new BufferedImage(imgXoa1.getWidth(null), imgXoa1.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dXoa1 = bImageXoa1.createGraphics();
				g2dXoa1.drawImage(imgXoa1, 0, 0, null);
				g2dXoa1.setComposite(AlphaComposite.SrcIn);
				g2dXoa1.setColor(Color.WHITE);
				g2dXoa1.fillRect(0, 0, bImageXoa1.getWidth(), bImageXoa1.getHeight());
				g2dXoa1.dispose();
				Image scaledImageXoa1 = bImageXoa1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

				// Nút "Sửa"
				JMenu btnSua = new JMenu("Sửa");
				btnSua.setOpaque(true);
				btnSua.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btnSua.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnSua.setBackground(new Color(46, 139, 87));
				btnSua.setBounds(289, 461, 113, 45);

				ImageIcon iconSua1 = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/setting-icon.png"));
				Image imgSua1 = iconSua1.getImage();
				BufferedImage bImageSua1 = new BufferedImage(imgSua1.getWidth(null), imgSua1.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dSua1 = bImageSua1.createGraphics();
				g2dSua1.drawImage(imgSua1, 0, 0, null);
				g2dSua1.setComposite(AlphaComposite.SrcIn);
				g2dSua1.setColor(Color.WHITE);
				g2dSua1.fillRect(0, 0, bImageSua1.getWidth(), bImageSua1.getHeight());
				g2dSua1.dispose();
				Image scaledImageSua1 = bImageSua1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				btnSua.setIcon(new ImageIcon(scaledImageSua1));
				panel.add(btnSua);

				// Nút "Xóa Trắng"
				JMenu btnXoaTrang = new JMenu("Xóa Trắng");
				btnXoaTrang.setOpaque(true);
				btnXoaTrang.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btnXoaTrang.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnXoaTrang.setBackground(new Color(46, 139, 87));
				btnXoaTrang.setBounds(430, 461, 165, 45);

				ImageIcon iconXT1 = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/calendar-remove-icon.png"));
				Image imgXT1 = iconXT1.getImage();
				BufferedImage bImageXT1 = new BufferedImage(imgXT1.getWidth(null), imgXT1.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dXT1 = bImageXT1.createGraphics();
				g2dXT1.drawImage(imgXT1, 0, 0, null);
				g2dXT1.setComposite(AlphaComposite.SrcIn);
				g2dXT1.setColor(Color.WHITE);
				g2dXT1.fillRect(0, 0, bImageXT1.getWidth(), bImageXT1.getHeight());
				g2dXT1.dispose();
				Image scaledImageXT1 = bImageXT1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				btnXoaTrang.setIcon(new ImageIcon(scaledImageXT1));
				panel.add(btnXoaTrang);

				// Nút "Lưu"
				JMenu btnLuu = new JMenu("Lưu");
				btnLuu.setOpaque(true);
				btnLuu.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btnLuu.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnLuu.setBackground(new Color(46, 139, 87));
				btnLuu.setBounds(623, 461, 105, 45);

				ImageIcon iconLuu1 = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/save-icon.png"));
				Image imgLuu1 = iconLuu1.getImage();
				BufferedImage bImageLuu1 = new BufferedImage(imgLuu1.getWidth(null), imgLuu1.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2dLuu1 = bImageLuu1.createGraphics();
				g2dLuu1.drawImage(imgLuu1, 0, 0, null);
				g2dLuu1.setComposite(AlphaComposite.SrcIn);
				g2dLuu1.setColor(Color.WHITE);
				g2dLuu1.fillRect(0, 0, bImageLuu1.getWidth(), bImageLuu1.getHeight());
				g2dLuu1.dispose();
				Image scaledImageLuu1 = bImageLuu1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				btnLuu.setIcon(new ImageIcon(scaledImageLuu1));
				panel.add(btnLuu);

				
				txtNhap = new JTextField();
				txtNhap.setColumns(10);
				txtNhap.setBounds(1077, 474, 318, 32);
				panel.add(txtNhap);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 529, 1386, 271);
				panel.add(scrollPane);
				table = new JTable();
				table.setFont(new Font("Tahoma", Font.BOLD, 13));
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
				        "Mã NV", "Tên NV", "SĐT", "Giới Tính", "Tuổi", "Chức vụ", "Lương"
				    }
				) {
				    Class[] columnTypes = new Class[] {
				        String.class, String.class, String.class, String.class, Integer.class, String.class, Double.class
				    };
				    public Class getColumnClass(int columnIndex) {
				        return columnTypes[columnIndex];
				    }
				});

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
				                c.setBackground(new Color(154,202,189)); // Màu xanh nhạt
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
				lblNhpMNhn.setBounds(1079, 443, 152, 26);
				panel.add(lblNhpMNhn);
				
				 btThoat = new JButton("Thoát");
				btThoat.setOpaque(true);
				btThoat.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btThoat.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btThoat.setBackground(new Color(46, 139, 87));
				btThoat.setBounds(751, 461, 137, 45);

				ImageIcon iconThoat = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/exit-icon.png"));
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
				btnTim.setBounds(962, 461, 105, 45);

//				ImageIcon iconTim = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/search.png"));
//				Image imgTim = iconTim.getImage();
//				BufferedImage bImageTim = new BufferedImage(imgTim.getWidth(null), imgTim.getHeight(null), BufferedImage.TYPE_INT_ARGB);
//				Graphics2D g2dTim = bImageTim.createGraphics();
//				g2dTim.drawImage(imgTim, 0, 0, null);
//				g2dTim.setComposite(AlphaComposite.SrcIn);
//				g2dTim.setColor(Color.WHITE);
//				g2dTim.fillRect(0, 0, bImageTim.getWidth(), bImageTim.getHeight());
//				g2dTim.dispose();
//				Image scaledImageTim = bImageTim.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//				btnTim.setIcon(new ImageIcon(scaledImageTim));
				panel.add(btnTim);
				
								// Nút "Xóa"
								JMenu btnXoa_1 = new JMenu("Xóa");
								btnXoa_1.setBounds(164, 461, 113, 45);
								panel.add(btnXoa_1);
								btnXoa_1.setOpaque(true);
								btnXoa_1.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
								btnXoa_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
								btnXoa_1.setBackground(new Color(46, 139, 87));
								btnXoa_1.setIcon(new ImageIcon(scaledImageXoa1));
	
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
	    manageMenuItem3.addActionListener(createMenuActionListener(this, Ly.class));

	    statsMenuItem1.addActionListener(createMenuActionListener(this, ThongKeDoanhSo_GUI.class));
	    statsMenuItem2.addActionListener(createMenuActionListener(this, ThongKeNhanVien_GUI.class));
	    statsMenuItem3.addActionListener(createMenuActionListener(this, ThongKeKhachHang_GUI.class));
	    statsMenuItem4.addActionListener(createMenuActionListener(this, ThongKeSanPham_GUI.class));

	    return menuBar;}

	
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

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
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
