package gui;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.util.Calendar;

import javax.swing.BorderFactory;
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
import javax.swing.SpinnerDateModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import gui.TrangChu_GUI;

public class QuanLySanPham_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtThanhPhan;
	private JTextField txtGiaNhap;
	private JTextField txtTenSP;
	private JTextField txtGiaBan;
	private JTextField txtSLTK;
	private JTextField textField_7;
	private JTextField txtNhaSanXuat;
	private JTextField textField_9;
	private JTextField txtGhiChu;
	private JTextField textField_11;
	private JTextField txtNhap;
	private JTable table;
	private JTextField txtThue;
	private JTextField textField_12;
	private JTextField txtCongDung;
	private JTextField txtBaoQuan;
	private JTextField txtChongChiDinh;
	private TrangChu_GUI trangChuGUI;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1423, 912); 
        contentPane = new JPanel();
        contentPane.setBackground(Color.decode("#FF5733"));
        contentPane.setForeground(SystemColor.window);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 // Menu Bar
		trangChuGUI = new TrangChu_GUI();
		JMenuBar menuBar = trangChuGUI.createMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setOpaque(true);
		menuBar.setBackground(new Color(26, 133, 94));
		menuBar.setBounds(0, 0, 1395, 70);
		contentPane.add(menuBar);

        
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(226, 250, 252));
				panel.setBounds(0, 69, 1407, 866);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("QUẢN LÝ SẢN PHẨM ");
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
				
				panel_1.setBounds(9, 99, 1386, 340);
				panel.add(panel_1);
				panel_1.setBackground(new Color(154, 202, 189));
				panel_1.setLayout(null);
				
				txtThanhPhan = new JTextField();
				txtThanhPhan.setColumns(10);
				txtThanhPhan.setBounds(505, 31, 352, 30);
				panel_1.add(txtThanhPhan);
				
				txtGiaNhap = new JTextField();
				txtGiaNhap.setColumns(10);
				txtGiaNhap.setBounds(973, 31, 352, 30);
				panel_1.add(txtGiaNhap);
				
				txtTenSP = new JTextField();
				txtTenSP.setColumns(10);
				txtTenSP.setBounds(23, 31, 352, 30);
				panel_1.add(txtTenSP);
				
				txtGiaBan = new JTextField();
				txtGiaBan.setColumns(10);
				txtGiaBan.setBounds(23, 98, 352, 30);
				panel_1.add(txtGiaBan);
				
				txtSLTK = new JTextField();
				txtSLTK.setColumns(10);
				txtSLTK.setBounds(505, 98, 352, 30);
				panel_1.add(txtSLTK);
				
				Calendar calendar = Calendar.getInstance();
		        JSpinner dateSpinnerNSX = new JSpinner(new SpinnerDateModel(calendar.getTime(), null, null, Calendar.DAY_OF_MONTH));
		        JSpinner.DateEditor de_dateSpinnerNSX = new JSpinner.DateEditor(dateSpinnerNSX, "dd/MM/yyyy");
		        dateSpinnerNSX.setEditor(de_dateSpinnerNSX);
		        dateSpinnerNSX.setBounds(973, 171, 352, 30); // Đặt vị trí và kích thước cho JSpinner
		        panel_1.add(dateSpinnerNSX);
			
				
				txtNhaSanXuat = new JTextField();
				txtNhaSanXuat.setColumns(10);
				txtNhaSanXuat.setBounds(505, 235, 352, 30);
				panel_1.add(txtNhaSanXuat);
				
				 Calendar calendar1 = Calendar.getInstance();
			        JSpinner dateSpinnerHSD = new JSpinner(new SpinnerDateModel(calendar1.getTime(), null, null, Calendar.DAY_OF_MONTH));
			        JSpinner.DateEditor de_dateSpinnerHSD = new JSpinner.DateEditor(dateSpinnerHSD, "dd/MM/yyyy");
			        dateSpinnerHSD.setEditor(de_dateSpinnerHSD);
			        dateSpinnerHSD.setBounds(973, 108, 352, 30); // Đặt vị trí và kích thước cho JSpinner
			        panel_1.add(dateSpinnerHSD);
				
				txtGhiChu = new JTextField();
				txtGhiChu.setColumns(10);
				txtGhiChu.setBounds(505, 171, 352, 30);
				panel_1.add(txtGhiChu);
				
				String[] items = {"Thuốc kê đơn", "Thuốc không kê đơn", "Thực phẩm chức năng ", "Chăm sóc cá nhân ", "Thiết bị y tế ","Sản phẩm trẻ em "};
				JComboBox<String> comboBox = new JComboBox<>(items);
				 comboBox.setBounds(973, 297, 352, 30);
				panel_1.add(comboBox);
				
				JLabel lblTenNV = new JLabel("Tên Sản Phẩm");
				lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblTenNV.setBounds(23, 12, 126, 14);
				panel_1.add(lblTenNV);
				
				JLabel lblSDT = new JLabel("Giá Bán ");
				lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblSDT.setBounds(23, 82, 126, 14);
				panel_1.add(lblSDT);
				
				JLabel lblNgaysinh = new JLabel("Thành phần ");
				lblNgaysinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNgaysinh.setBounds(505, 11, 126, 16);
				panel_1.add(lblNgaysinh);
				
				JLabel lblNgayVaolam = new JLabel("Số Lượng Tồn Kho");
				lblNgayVaolam.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNgayVaolam.setBounds(505, 77, 126, 24);
				panel_1.add(lblNgayVaolam);
				
				JLabel lblLngCnBnlblLngCnBn = new JLabel("Ghi Chú");
				lblLngCnBnlblLngCnBn.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblLngCnBnlblLngCnBn.setBounds(505, 144, 126, 30);
				panel_1.add(lblLngCnBnlblLngCnBn);
				
				JLabel lblChucVu = new JLabel("Nhà Sản Xuất");
				lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblChucVu.setBounds(505, 214, 126, 24);
				panel_1.add(lblChucVu);
				
				JLabel lblCmnd = new JLabel("Giá Nhập");
				lblCmnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblCmnd.setBounds(974, 13, 126, 16);
				panel_1.add(lblCmnd);
				
				JLabel lblTrinhDo = new JLabel("Hạn Sử Dụng ");
lblTrinhDo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblTrinhDo.setBounds(973, 81, 126, 16);
				panel_1.add(lblTrinhDo);
				
				JLabel lblDiaChi = new JLabel("Ngày Sản Xuất ");
				lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblDiaChi.setBounds(973, 151, 126, 16);
				panel_1.add(lblDiaChi);
				
				JLabel lblEmail = new JLabel("Đơn Vị Tính");
				lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblEmail.setBounds(973, 218, 126, 16);
				panel_1.add(lblEmail);
				
				JLabel lblThuegtgt = new JLabel("Thuế GTGT");
				lblThuegtgt.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblThuegtgt.setBounds(505, 276, 126, 24);
				panel_1.add(lblThuegtgt);
				
				txtThue = new JTextField();
				txtThue.setColumns(10);
				txtThue.setBounds(505, 297, 352, 30);
				panel_1.add(txtThue);
				
				JLabel lblLoiSnPhm = new JLabel("Loại Sản Phẩm ");
				lblLoiSnPhm.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblLoiSnPhm.setBounds(973, 280, 126, 16);
				panel_1.add(lblLoiSnPhm);
				
				String[] items1 = {"Viên", "Ml", "Cái", "Gam", "Bộ"};
				JComboBox<String> comboBox1 = new JComboBox<>(items1);
				comboBox1.setBackground(new Color(255, 255, 255));
				 comboBox1.setBounds(973, 235, 352, 30);
				panel_1.add(comboBox1);
				
				txtCongDung = new JTextField();
				txtCongDung.setColumns(10);
				txtCongDung.setBounds(23, 171, 352, 30);
				panel_1.add(txtCongDung);
				
				JLabel lblCngDng = new JLabel("Công Dụng");
				lblCngDng.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblCngDng.setBounds(23, 155, 126, 14);
				panel_1.add(lblCngDng);
				
				txtBaoQuan = new JTextField();
				txtBaoQuan.setColumns(10);
				txtBaoQuan.setBounds(23, 235, 352, 30);
				panel_1.add(txtBaoQuan);
				
				JLabel lblBoQun = new JLabel("Bảo Quản ");
				lblBoQun.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblBoQun.setBounds(23, 219, 126, 14);
				panel_1.add(lblBoQun);
				
				txtChongChiDinh = new JTextField();
				txtChongChiDinh.setColumns(10);
				txtChongChiDinh.setBounds(23, 297, 352, 30);
				panel_1.add(txtChongChiDinh);
				
				JLabel lblChngChnh = new JLabel("Chống Chỉ Định");
				lblChngChnh.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblChngChnh.setBounds(23, 281, 126, 14);
				panel_1.add(lblChngChnh);
				
				// Nút "Thêm"
				JMenu btnThem = new JMenu("Thêm");
				btnThem.setOpaque(true);
				btnThem.setForeground(new Color(255, 255, 255));
				btnThem.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnThem.setBackground(new Color(46, 139, 87));
				btnThem.setBounds(9, 450, 120, 45);

				ImageIcon iconThem = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/4993253681582956831-128.png"));
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
				JMenu btnXoa = new JMenu("Xóa");
				btnXoa.setOpaque(true);
				btnXoa.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btnXoa.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnXoa.setBackground(new Color(46, 139, 87));
				btnXoa.setBounds(152, 450, 113, 45);

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
				JMenu btnSua = new JMenu("Sửa");
				btnSua.setOpaque(true);
				btnSua.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btnSua.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnSua.setBackground(new Color(46, 139, 87));
				btnSua.setBounds(290, 450, 113, 45);

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
				JMenu btnXoaTrang = new JMenu("Xóa Trắng");
				btnXoaTrang.setOpaque(true);
				btnXoaTrang.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btnXoaTrang.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnXoaTrang.setBackground(new Color(46, 139, 87));
				btnXoaTrang.setBounds(437, 450, 152, 45);

				ImageIcon iconXT = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/GUI/calendar-remove-icon.png"));
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
				JMenu btnLuu = new JMenu("Lưu");
				btnLuu.setOpaque(true);
				btnLuu.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btnLuu.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnLuu.setBackground(new Color(46, 139, 87));
				btnLuu.setBounds(600, 450, 105, 45);

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
				txtNhap.setBounds(1075, 463, 318, 32);
				panel.add(txtNhap);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(9, 518, 1386, 284);
				panel.add(scrollPane);
				table = new JTable();
				table.setFont(new Font("Tahoma", Font.BOLD, 13));
				table.setBackground(new Color(220, 220, 220));
				table.setRowHeight(40);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null},
					},
					new String[] {
						"M\u00E3 SP", "T\u00EAn SP", "Gi\u00E1 B\u00E1n", "C\u00F4ng D\u1EE5ng", "\u0110\u01A1n V\u1ECB T\u00EDnh", "Th\u00E0nh Ph\u1EA7n", "S\u1ED1 L\u01B0\u1EE3ng T\u1ED3n Kho", "Lo\u1EA1i S\u1EA3n Ph\u1EA9m", "H\u1EA1n S\u1EED D\u1EE5ng"
					}
				) {
					Class[] columnTypes = new Class[] {
						Object.class, Object.class, String.class, String.class, String.class, String.class, Integer.class, String.class, Double.class
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
				

				
				JLabel lblNhpMNhn = new JLabel("Nhập mã  sản phẩm ");
				lblNhpMNhn.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNhpMNhn.setBounds(1075, 441, 159, 26);
				panel.add(lblNhpMNhn);
				
				JMenu btThoat = new JMenu("Thoát");
				btThoat.setOpaque(true);
				btThoat.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btThoat.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btThoat.setBackground(new Color(46, 139, 87));
				btThoat.setBounds(727, 450, 120, 45);

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
				JMenu btnTim = new JMenu("Tìm");
				btnTim.setOpaque(true);
				btnTim.setForeground(new Color(255, 255, 255)); // Đổi màu chữ thành trắng
				btnTim.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
				btnTim.setBackground(new Color(46, 139, 87));
				btnTim.setBounds(962, 450, 105, 45);

				//ImageIcon iconTim = new ImageIcon(QuanLyNhanVien_GUI.class.getResource("/gui/search.png"));
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
	}
}
