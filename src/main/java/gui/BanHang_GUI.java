package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.TrangChu_GUI;
import local.ChiTietDonTam;
import local.DonTam;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Desktop;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.element.Cell;

import com.itextpdf.kernel.font.PdfFontFactory;

import java.io.IOException;

import com.itextpdf.kernel.font.PdfFont;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.stream.Collectors;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.itextpdf.layout.element.List;

import dao.HoaDonDoiTra_DAO;
import dao.HoaDonXuat_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import dao_local.DonTam_DAO;
import entity.HoaDonXuat;
import entity.KhachHang;
import entity.LoaiHoaDon;
import entity.NhanVien;
import entity.SanPham;
import entity.ChiTietHoaDon;
import entity.HoaDonDoiTra;
import gui.DangNhap_GUI;
import entity.MaGiamGia;
import dao.MaGiamGia_DAO;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.EtchedBorder;
public class BanHang_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable productTable;
    private JTextField txtMSnPhm;
    private JTextField phoneField, nameField, membershipField, discountField, amountGivenField;
    private JTextField totalAmountLabel;
    private DefaultTableModel productTableModel;
    private TrangChu_GUI trangChu;
    private JTextField txtNhpMSn;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTable table_1;
    private JTextField txtMHan;
    private JTable table_2;
    private JTextField search;
    private JTable table_3;
    private JTable table_4;
    private JTextField txtLoiKhchHng;
    private JTextField textField_11;
    private JTextField textField_12;
    private JTextField textField_13;
    private JTextField textField_14;
    private JTable table_5;
    private JTextField textField_15;
    private JTextField textField_16;
    private JTextField textField_17;
    private JTextField textField_18;
    private JTextField textField_19;
    private JTextField textField_20;
    private JTextField textField_21;
    private JTextField textField_22;
    private JTextField textField_23;
    private JTextField textField_24;
    private JTextField textField_25;
    private JTable table_6;
    private JTextField textField_26;
    private JTextField textField_27;
    private JTextField textField_28;
    private JTable table_7;
    private JTextField textField_29;
    private JTextField textField_30;
    private JTextField textField_31;
    private JTextField textField_32;
    private JTextField textField_33;
    private JTextField textField_34;
    private JTextField textField_35;
    private JTextField textField_36;
    private JTextField textField_37;
    private DangNhap_GUI dangNhap;
    public static String maNVDangNhap;
    private JTextField txtNhapSL;
	private JLabel lblMSp;
	private JLabel lblSl;
	private KhachHang khachHang;
	private ChiTietHoaDon chiTietHoaDon;
	private HoaDonXuat hoaDonXuat;
	private static KhachHang_DAO khachHangDAO;
	private HoaDonXuat_DAO hoaDonXuatDAO;
	private SanPham sanPham;
	private NhanVien_DAO nhanVienDAO;

    private DonTam_DAO donTamDAO;  // DAO để lấy và lưu dữ liệu
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BanHang_GUI frame = new BanHang_GUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

  public BanHang_GUI() {
	  //Khai báo DAO
	  trangChu = new TrangChu_GUI();
	  dangNhap = new DangNhap_GUI();
	  khachHang = new KhachHang();
	  chiTietHoaDon = new ChiTietHoaDon();
	  hoaDonXuat = new HoaDonXuat();
	  khachHangDAO = new KhachHang_DAO();
	  hoaDonXuatDAO = new HoaDonXuat_DAO();
	  nhanVienDAO = new NhanVien_DAO();

	  
	  donTamDAO = new DonTam_DAO();

	  NumberFormat formatter2 = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));  // Định dạng theo tiền tệ Việt Nam
      // Cài đặt cửa sổ chính
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(0, 0, 1920, 1080);  // Kích thước cửa sổ lớn
      contentPane = new JPanel();
      contentPane.setBackground(new Color(255, 255, 255));  // Màu nền trắng
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);

      // Menu
      trangChu = new TrangChu_GUI();
      JMenuBar menuBar = trangChu.createMenuBar();
      menuBar.setBorderPainted(false);
      menuBar.setOpaque(true);
      menuBar.setBackground(new Color(26, 133, 94));
      menuBar.setBounds(0, 0, 1920, 70);
      contentPane.add(menuBar);

      contentPane.setLayout(null);
      // Tạo menu dọc bằng JToolBar
      JToolBar menuToolBar = new JToolBar();
      menuToolBar.setBounds(0, 75, 222, 795);
      menuToolBar.setOrientation(JToolBar.VERTICAL);  // Chuyển menu thành dạng dọc
      menuToolBar.setBackground(new Color(26,133, 94));
      contentPane.add(menuToolBar);

      // Thêm các mục menu vào JToolBar
      JButton btnBanHang = new JButton("Bán Hàng");
      btnBanHang.setForeground(new Color(255, 255, 255));
      btnBanHang.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
      btnBanHang.setBackground(new Color(153, 211, 158));
      btnBanHang.setPreferredSize(new Dimension(222, 60)); // Set chiều cao cố định và chiều rộng
      btnBanHang.setMaximumSize(new Dimension(222, 60));  
      btnBanHang.setAlignmentX(Component.LEFT_ALIGNMENT);
      btnBanHang.setBorder(BorderFactory.createEmptyBorder());
      menuToolBar.add(btnBanHang);

      JButton btnDonHang = new JButton("Đơn Hàng");
      btnDonHang.setForeground(new Color(255, 255, 255));
      btnDonHang.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
      btnDonHang.setBackground(new Color(26, 133, 94));
      btnDonHang.setPreferredSize(new Dimension(222, 60)); // Set chiều cao cố định và chiều rộng
      btnDonHang.setMaximumSize(new Dimension(222, 60));  
      btnDonHang.setBorder(BorderFactory.createEmptyBorder());
      btnDonHang.setAlignmentX(Component.LEFT_ALIGNMENT);
      menuToolBar.add(btnDonHang);

      // Tạo menu con cho "Đơn Hàng"
      JPanel panelDonHang = new JPanel();
      panelDonHang.setLayout(new BoxLayout(panelDonHang, BoxLayout.Y_AXIS));
      panelDonHang.setBackground(new Color(26, 133, 94));
      panelDonHang.setVisible(false); // Ẩn menu con khi mới bắt đầu
      menuToolBar.add(panelDonHang);

      JButton btnDonTam = new JButton("Đơn Tạm");
      btnDonTam.setForeground(new Color(255, 255, 255));
      btnDonTam.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnDonTam.setBackground(new Color(32, 210, 139));
      btnDonTam.setPreferredSize(new Dimension(222, 45)); // Set chiều cao cố định và chiều rộng
      btnDonTam.setMaximumSize(new Dimension(222, 45));  
      btnDonTam.setAlignmentX(Component.LEFT_ALIGNMENT);
      btnDonTam.setBorder(BorderFactory.createEmptyBorder());
      panelDonHang.add(btnDonTam);

      JButton btnDonHoanThanh = new JButton("Đơn Hoàn Thành");
      btnDonHoanThanh.setForeground(new Color(255, 255, 255));
      btnDonHoanThanh.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnDonHoanThanh.setBackground(new Color(32, 210, 139));
      btnDonHoanThanh.setPreferredSize(new Dimension(222, 45)); // Set chiều cao cố định và chiều rộng
      btnDonHoanThanh.setMaximumSize(new Dimension(222, 45));
      btnDonHoanThanh.setBorder(BorderFactory.createEmptyBorder());
      panelDonHang.add(btnDonHoanThanh);

      JButton btnDonTra = new JButton("Đơn Trả");
      btnDonTra.setForeground(new Color(255, 255, 255));
      btnDonTra.setBackground(new Color(32, 210, 139));
      btnDonTra.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnDonTra.setPreferredSize(new Dimension(222, 45)); // Set chiều cao cố định và chiều rộng
      btnDonTra.setMaximumSize(new Dimension(222, 45));  
      btnDonTra.setAlignmentX(Component.LEFT_ALIGNMENT);
      btnDonTra.setBorder(BorderFactory.createEmptyBorder());
      panelDonHang.add(btnDonTra);
      
      JButton btnKhuyenMai = new JButton("Khuyến Mãi");
      btnKhuyenMai.setForeground(new Color(255, 255, 255));
      btnKhuyenMai.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
      btnKhuyenMai.setBackground(new Color(26,133, 94));
      btnKhuyenMai.setPreferredSize(new Dimension(222, 60)); // Set chiều cao cố định và chiều rộng
      btnKhuyenMai.setMaximumSize(new Dimension(222, 60));  
      btnKhuyenMai.setAlignmentX(Component.LEFT_ALIGNMENT);
      btnKhuyenMai.setBorder(BorderFactory.createEmptyBorder());
      menuToolBar.add(btnKhuyenMai);

      // Sự kiện khi nhấn vào "Đơn Hàng" để hiển thị menu con
      btnDonHang.addActionListener(new ActionListener() {
    	  @Override
          public void actionPerformed(ActionEvent e) {
              // Tạo hiệu ứng hiện/ẩn menu con
              panelDonHang.setVisible(!panelDonHang.isVisible());
          }
          
          
      });
      btnKhuyenMai.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              // Tạo hiệu ứng ẩn menu con
              panelDonHang.setVisible(false);
          }
          
      });

      // Panel Content
      JPanel panelContent = new JPanel();
      panelContent.setBounds(237, 75, 1357, 795);
      contentPane.add(panelContent);
      panelContent.setLayout(new CardLayout(0, 0));

      // Trang Bán Hàng
      JPanel BanHangPane = new JPanel();
      BanHangPane.setBackground(new Color(255, 255, 255));
      panelContent.add(BanHangPane, "BanHangPane"); // Tên để chuyển đổi
      BanHangPane.setLayout(null);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(new Color(255, 255, 255));
      panel_1.setForeground(new Color(217, 245, 251));
      panel_1.setBounds(945, 11, 402, 773);
      BanHangPane.add(panel_1);
      panel_1.setLayout(null);
      
      JPanel panel_2 = new JPanel();
      panel_2.setBackground(new Color(255, 255, 255));
      panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng Tin Kh\u00E1ch H\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(33, 171, 119)));
      panel_2.setBounds(10, 0, 382, 214);
      panel_1.add(panel_2);
      panel_2.setLayout(null);
      
      JComboBox comboBox = new JComboBox();
      comboBox.setBackground(new Color(255, 255, 255));
      comboBox.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      comboBox.setModel(new DefaultComboBoxModel(new String[] {"Khách Vãng Lai", "Thành Viên", "Khách Mới"}));
      comboBox.setBounds(126, 26, 246, 39);
      panel_2.add(comboBox);
      
      JLabel lblNewLabel = new JLabel("Số điện thoại:");
      lblNewLabel.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel.setBounds(24, 87, 98, 39);
      panel_2.add(lblNewLabel);
      
      textField = new JTextField();
      textField.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField.setEditable(false);
      textField.setBackground(new Color(239, 239, 239));
      textField.setBounds(126, 87, 246, 39);
      panel_2.add(textField);
      textField.setColumns(10);
      
      JLabel lblHTn = new JLabel("Họ tên");
      lblHTn.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblHTn.setBounds(24, 150, 98, 39);
      panel_2.add(lblHTn);
      
      textField_1 = new JTextField();
      textField_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_1.setBackground(new Color(239, 239, 239));
      textField_1.setColumns(10);
      textField_1.setEditable(false);
      textField_1.setBounds(126, 150, 246, 39);
      panel_2.add(textField_1);

      JPanel panel = new JPanel();
      panel.setBackground(new Color(255, 255, 255));
      panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng Tin H\u00F3a \u0110\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(33, 171, 119)));
      panel.setBounds(10, 237, 382, 507);
      panel_1.add(panel);
      panel.setLayout(null);
      
      JLabel lblNewLabel_1 = new JLabel("Mã hóa đơn:");
      lblNewLabel_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_1.setBounds(10, 25, 129, 35);
      panel.add(lblNewLabel_1);
      
      textField_2 = new JTextField();
      textField_2.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_2.setBackground(new Color(255, 255, 255));
      textField_2.setEditable(false);
      textField_2.setBounds(135, 25, 237, 35);
      panel.add(textField_2);
      textField_2.setColumns(10);
 
      JLabel lblNewLabel_1_1 = new JLabel("Ngày tạo:");
      lblNewLabel_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_1_1.setBounds(10, 137, 105, 35);
      panel.add(lblNewLabel_1_1);
      
      JLabel lblNewLabel_1_2 = new JLabel("Giảm giá");
      lblNewLabel_1_2.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_1_2.setBounds(10, 190, 105, 35);
      panel.add(lblNewLabel_1_2);
      
      JLabel lblNewLabel_1_3 = new JLabel("Tiền khách trả:");
      lblNewLabel_1_3.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_1_3.setBounds(10, 246, 129, 35);
      panel.add(lblNewLabel_1_3);
      
      textField_3 = new JTextField();
      textField_3.setBackground(new Color(255, 255, 255));
      textField_3.setEditable(false);
      textField_3.setColumns(10);
      textField_3.setBounds(135, 140, 237, 35);
      panel.add(textField_3);
      
      textField_4 = new JTextField();
      textField_4.setBackground(new Color(255, 255, 255));
      textField_4.setEditable(false);
      textField_4.setColumns(10);
      textField_4.setBounds(135, 193, 237, 35);
      panel.add(textField_4);
      
      textField_5 = new JTextField();
      textField_5.setHorizontalAlignment(SwingConstants.RIGHT);
      textField_5.setBackground(new Color(255, 255, 255));
      textField_5.setEditable(false);
      textField_5.setColumns(10);
      textField_5.setBounds(135, 249, 237, 35);
      panel.add(textField_5);
      
      JLabel lblNewLabel_1_3_1 = new JLabel("Phương thức:");
      lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
      lblNewLabel_1_3_1.setBounds(10, 303, 129, 35);
      panel.add(lblNewLabel_1_3_1);
      
      JLabel lblNewLabel_2 = new JLabel("Tiền khách đưa");
      lblNewLabel_2.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_2.setBounds(10, 363, 129, 34);
      panel.add(lblNewLabel_2);
      
      textField_6 = new JTextField();
      textField_6.setHorizontalAlignment(SwingConstants.RIGHT);
      textField_6.setColumns(10);
      textField_6.setBounds(135, 363, 154, 35);
      panel.add(textField_6);
      
      textField_7 = new JTextField();
      textField_7.setBackground(new Color(255, 255, 255));
      textField_7.setText("000");
      textField_7.setEditable(false);
      textField_7.setColumns(10);
      textField_7.setBounds(293, 363, 79, 35);
      panel.add(textField_7);
      
      JLabel lblNewLabel_2_1 = new JLabel("Tiền thối");
      lblNewLabel_2_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_2_1.setBounds(10, 415, 114, 34);
      panel.add(lblNewLabel_2_1);
      
      textField_8 = new JTextField();
      textField_8.setHorizontalAlignment(SwingConstants.RIGHT);
      textField_8.setBackground(new Color(255, 255, 255));
      textField_8.setText("000");
      textField_8.setEditable(false);
      textField_8.setColumns(10);
      textField_8.setBounds(134, 414, 238, 35);
      panel.add(textField_8);
      
      textField_37 = new JTextField();
      textField_37.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_37.setEditable(false);
      textField_37.setColumns(10);
      textField_37.setBackground(Color.WHITE);
      textField_37.setBounds(135, 80, 237, 35);
      // lấy maNVDangNhap ở đây
      textField_37.setText(maNVDangNhap);
      panel.add(textField_37);
      
      JLabel lblNewLabel_1_4 = new JLabel("Mã nhân viên:");
      lblNewLabel_1_4.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_1_4.setBounds(10, 80, 129, 35);
      panel.add(lblNewLabel_1_4);
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(10, 62, 925, 449);
      BanHangPane.add(scrollPane);
      
      table = new JTable();
      table.setRowSelectionAllowed(false);
      table.setBackground(new Color(255, 255, 255));
      table.setModel(new DefaultTableModel(
      	new Object[][] {

      	},
      	new String[] {
      		"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá Bán", "Thuế GTGT", "Giảm Giá", "Thành tiền"
      	}
      ) {
      	boolean[] columnEditables = new boolean[] {
      		false, false, false, false, false, false, false
      	};
      	public boolean isCellEditable(int row, int column) {
      		return columnEditables[column];
      	}
      });
      table.getColumnModel().getColumn(0).setResizable(false);
      table.getColumnModel().getColumn(2).setResizable(false);
      table.getColumnModel().getColumn(3).setResizable(false);
      table.getColumnModel().getColumn(4).setResizable(false);
      table.getColumnModel().getColumn(5).setResizable(false);
      table.getColumnModel().getColumn(6).setResizable(false);
      giaoDienTable(table);
      scrollPane.setViewportView(table);
      
      
      lblMSp = new JLabel("Mã SP:");
	    lblMSp.setForeground(new Color(0, 0, 0));
	    lblMSp.setBackground(new Color(255, 255, 255));
	    lblMSp.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
	    lblMSp.setBounds(10, 11, 58, 39);
	    BanHangPane.add(lblMSp);

      txtNhpMSn = new JTextField();
      txtNhpMSn.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
//      txtNhpMSn.setText("Nhập mã sản phẩm");
      txtNhpMSn.setBounds(70, 11, 437, 40);
      BanHangPane.add(txtNhpMSn);
      txtNhpMSn.setColumns(10);
      txtNhapSL = new JTextField();
	    txtNhapSL.setBackground(new Color(255, 255, 255));
	    txtNhapSL.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
	    txtNhapSL.setColumns(10);
	    txtNhapSL.setBounds(577, 11, 58, 40);
	    BanHangPane.add(txtNhapSL);
	    
	    lblSl = new JLabel("SL:");
	    lblSl.setBackground(new Color(0, 0, 0));
	    lblSl.setForeground(new Color(0, 0, 0));
	    lblSl.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
	    lblSl.setBounds(532, 12, 35, 39);
	    BanHangPane.add(lblSl);
      JButton btnNewButton = new JButton("Thêm");
      btnNewButton.setForeground(new Color(255, 255, 255));
      btnNewButton.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnNewButton.setBackground(new Color(26, 133, 94));
      btnNewButton.setBounds(645, 11, 140, 40);
      BanHangPane.add(btnNewButton);
      
      JButton btnXa = new JButton("Xóa");
      btnXa.setForeground(new Color(255, 255, 255));
      btnXa.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnXa.setBackground(new Color(26, 133, 94));
      btnXa.setBounds(795, 11, 140, 40);
      BanHangPane.add(btnXa);
      
      JButton btnNewButton_1 = new JButton("Xử lý đơn tạm");
      btnNewButton_1.setForeground(new Color(255, 255, 255));
      btnNewButton_1.setBackground(new Color(26, 133, 94));
      btnNewButton_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnNewButton_1.setBounds(10, 606, 195, 54);
      BanHangPane.add(btnNewButton_1);
   // Thêm sự kiện cho các nút chuyển panel
      btnNewButton_1.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "DonTamPane"); // Chuyển sang trang Đơn tạm
          btnBanHang.setBackground(new Color(26, 133, 94));
          btnDonTam.setBackground(new Color(153, 211, 158));
      });

//      JButton btnNewButton_2 = new JButton("New button");
//      btnNewButton_2.addActionListener(new ActionListener() {
//      	public void actionPerformed(ActionEvent e) {
//      	}
//      });
      JLabel lblNewLabel_3 = new JLabel("Tổng tiền:");
      lblNewLabel_3.setForeground(new Color(26, 133, 94));
      lblNewLabel_3.setFont(new Font("Leelawadee UI", Font.BOLD, 22));
      lblNewLabel_3.setBounds(604, 522, 153, 40);
      BanHangPane.add(lblNewLabel_3);
      
      textField_9 = new JTextField();
      textField_9.setBackground(new Color(255, 255, 255));
      textField_9.setEditable(false);
      textField_9.setBounds(724, 522, 211, 40);
      BanHangPane.add(textField_9);
      textField_9.setColumns(10);
      
      JComboBox comboBox_1 = new JComboBox();
      comboBox_1.setBackground(new Color(255, 255, 255));
	    comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Tiền mặt", "ATM"}));
	    comboBox_1.setBounds(135, 304, 237, 42);
	    panel.add(comboBox_1);
	    
      JButton btnNewButton_1_1 = new JButton("Lưu đơn tạm");
      btnNewButton_1_1.setForeground(Color.WHITE);
      btnNewButton_1_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnNewButton_1_1.setBackground(new Color(26, 133, 94));
      btnNewButton_1_1.setBounds(252, 606, 195, 54);
      BanHangPane.add(btnNewButton_1_1);
      
      JButton btnNewButton_1_2 = new JButton("Khuyến mãi");
      btnNewButton_1_2.setForeground(Color.WHITE);
      btnNewButton_1_2.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnNewButton_1_2.setBackground(new Color(26, 133, 94));
      btnNewButton_1_2.setBounds(496, 606, 195, 54);
      BanHangPane.add(btnNewButton_1_2);
      
      JButton btnNewButton_1_3 = new JButton("Hủy");
      btnNewButton_1_3.setForeground(Color.WHITE);
      btnNewButton_1_3.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnNewButton_1_3.setBackground(new Color(26, 133, 94));
      btnNewButton_1_3.setBounds(740, 606, 195, 54);
      BanHangPane.add(btnNewButton_1_3);
      
      JButton btnNewButton_2 = new JButton("Hoàn thành đơn");
      btnNewButton_2.setForeground(Color.WHITE);
      btnNewButton_2.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnNewButton_2.setBackground(new Color(26, 133, 94));
      btnNewButton_2.setBounds(342, 704, 270, 54);
      BanHangPane.add(btnNewButton_2);

      // Trang Đơn Hàng
      JPanel DonTamPane = new JPanel();
      DonTamPane.setBackground(new Color(255, 255, 255));
      panelContent.add(DonTamPane, "DonTamPane"); // Tên để chuyển đổi
      DonTamPane.setLayout(null);
      
      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(10, 65, 558, 528);
      DonTamPane.add(scrollPane_1);
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      table_1 = new JTable();
      table_1.setModel(new DefaultTableModel(
      	new Object[][] {
      	},
      	new String[] {
      		"M\u00E3 H\u00F3a \u0110\u01A1n", "SDT Kh\u00E1ch H\u00E0ng", "T\u00EAn Kh\u00E1ch H\u00E0ng", "Ng\u00E0y T\u1EA1o"
      	}
      ));
      giaoDienTable(table_1);
      scrollPane_1.setViewportView(table_1);

      JScrollPane scrollPane_1_1 = new JScrollPane();
      scrollPane_1_1.setBounds(607, 11, 740, 671);
      DonTamPane.add(scrollPane_1_1);
      
      table_2 = new JTable();
      table_2.setModel(new DefaultTableModel(
      	new Object[][] {
      		
      	},
      	new String[] {
      		"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá"
      	}
      ));
      giaoDienTable(table_2);
      scrollPane_1_1.setViewportView(table_2);
      
      txtMHan = new JTextField();
      txtMHan.setFont(new Font("Tahoma", Font.PLAIN, 16));
      txtMHan.setForeground(new Color(192, 192, 192));
      txtMHan.setText("Mã hóa đơn");
      txtMHan.setBounds(147, 11, 276, 43);
      DonTamPane.add(txtMHan);
      txtMHan.setColumns(10);
      
      JButton btnNewButton_3 = new JButton("TÌm kiếm");
      btnNewButton_3.setBackground(new Color(26, 133, 94));
      btnNewButton_3.setForeground(new Color(255, 255, 255));
      btnNewButton_3.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
      btnNewButton_3.setBounds(445, 11, 123, 43);
      DonTamPane.add(btnNewButton_3);
      
      JButton btnNewButton_4 = new JButton("Xóa tát cả đơn");
      btnNewButton_4.setForeground(new Color(255, 255, 255));
      btnNewButton_4.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
      btnNewButton_4.setBackground(new Color(26, 133, 94));
      btnNewButton_4.setBounds(10, 633, 156, 49);
      DonTamPane.add(btnNewButton_4);
      
      JButton btnNewButton_4_1 = new JButton("Xóa đơn tạm");
      btnNewButton_4_1.setForeground(new Color(255, 255, 255));
      btnNewButton_4_1.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
      btnNewButton_4_1.setBackground(new Color(26, 133, 94));
      btnNewButton_4_1.setBounds(212, 633, 156, 49);
      DonTamPane.add(btnNewButton_4_1);
      
      JButton btnNewButton_4_2 = new JButton("Xử lí đơn");
      btnNewButton_4_2.setForeground(new Color(255, 255, 255));
      btnNewButton_4_2.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
      btnNewButton_4_2.setBackground(new Color(26, 133, 94));
      btnNewButton_4_2.setBounds(412, 633, 156, 49);
      DonTamPane.add(btnNewButton_4_2);
      
      JSpinner spinner_1 = new JSpinner();
      spinner_1.setBackground(new Color(26, 133, 94));
      spinner_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      spinner_1.setModel(new SpinnerListModel(new String[] {"M\u00E3 h\u00F3a \u0111\u01A1n", "T\u00EAn kh\u00E1ch h\u00E0ng", "Ng\u00E0y t\u1EA1o h\u00F3a \u0111\u01A1n"}));
      spinner_1.setBounds(10, 11, 127, 43);
      DonTamPane.add(spinner_1);
      
      
      
      JPanel DonHoanThanhPane = new JPanel();
      DonHoanThanhPane.setBackground(new Color(255, 255, 255));
      panelContent.add(DonHoanThanhPane, "DonHoanThanhPane"); // Tên để chuyển đổi
      DonHoanThanhPane.setLayout(null);
      
      JScrollPane scrollPane_2 = new JScrollPane();
      scrollPane_2.setBounds(30, 73, 659, 584);
      DonHoanThanhPane.add(scrollPane_2);
//      Hoàn thành table 1
      table_3 = new JTable();
      table_3.setModel(new DefaultTableModel(
      	new Object[][] {
      		
      	},
      	new String[] {
      		"M\u00E3 h\u00F3a \u0111\u01A1n", "M\u00E3 nh\u00E2n vi\u00EAn", "M\u00E3 kh\u00E1ch h\u00E0ng", "Ng\u00E0y mua", "Giảm giá", "Th\u00E0nh ti\u1EC1n"
      	}
      ));
   // Lấy danh sách hóa đơn trong vòng 3 ngày
      ArrayList<Object[]> hoaDons = hoaDonXuatDAO.layDanhSachHoaDonTrong3Ngay("", "Mã nhân viên"); // Lọc tất cả hóa đơn, có thể tùy chỉnh thêm điều kiện lọc nếu cần

      // Lấy mô hình bảng hiện tại
      DefaultTableModel model_1 = (DefaultTableModel) table_3.getModel();

      // Xóa tất cả các dòng hiện có trong bảng trước khi thêm mới
     model_1.setRowCount(0);

      // Thêm từng hóa đơn vào bảng
     for (Object[] hoaDon : hoaDons) {
    	    String maHoaDon = hoaDon[0] != null ? hoaDon[0].toString() : "";
    	    String maNhanVien = hoaDon[1] != null ? hoaDon[1].toString() : "";
    	    String maKhachHang = hoaDon[2] != null ? hoaDon[2].toString() : "";
    	    String ngayMua = hoaDon[3] != null ? hoaDon[3].toString() : "";
    	    String giamGia = hoaDon[4] != null ? hoaDon[4].toString() : "0";  // Trị giá giảm giá
    	    String thanhTien = hoaDon[5] != null ? hoaDon[5].toString() : "0";

    	    // Thêm vào bảng
    	    model_1.addRow(new Object[]{
    	        maHoaDon, 
    	        maNhanVien, 
    	        maKhachHang, 
    	        ngayMua, 
    	        giamGia,  // Giảm giá đã định dạng
    	        thanhTien // Thành tiền đã định dạng
    	    });
    	}
      giaoDienTable(table_3);
      scrollPane_2.setViewportView(table_3);
      
      JScrollPane scrollPane_2_1 = new JScrollPane();
      scrollPane_2_1.setBounds(699, 11, 648, 459);
      DonHoanThanhPane.add(scrollPane_2_1);
//      Hoàn thành table 2
      table_4 = new JTable();
      table_4.setModel(new DefaultTableModel(
      	new Object[][] {
      		{null, null, "", null, null, null},
      	},
      	new String[] {
      		"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn giá", "Thuế GTGT", "Thành Tiền"
      	}
      ));
      giaoDienTable(table_4);
      scrollPane_2_1.setViewportView(table_4);
      
      JPanel panel_3 = new JPanel();
      panel_3.setBackground(new Color(255, 255, 255));
      panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(26, 133, 94)));
      panel_3.setBounds(699, 497, 648, 273);
      DonHoanThanhPane.add(panel_3);
      panel_3.setLayout(null);
      
      txtLoiKhchHng = new JTextField();
      txtLoiKhchHng.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      txtLoiKhchHng.setEditable(false);
      txtLoiKhchHng.setBounds(155, 22, 472, 41);
      panel_3.add(txtLoiKhchHng);
      txtLoiKhchHng.setColumns(10);
      
      JLabel lblNewLabel_4 = new JLabel("Mã khách hàng");
      lblNewLabel_4.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_4.setBounds(20, 74, 151, 41);
      panel_3.add(lblNewLabel_4);
      
      JLabel lblNewLabel_4_1 = new JLabel("Tên khách hàng");
      lblNewLabel_4_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_4_1.setBounds(20, 147, 135, 41);
      panel_3.add(lblNewLabel_4_1);
      
      JLabel lblNewLabel_4_2 = new JLabel("Tiền khách đưa");
      lblNewLabel_4_2.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_4_2.setBounds(20, 210, 125, 41);
      panel_3.add(lblNewLabel_4_2);
      
      textField_11 = new JTextField();
      textField_11.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_11.setEditable(false);
      textField_11.setColumns(10);
      textField_11.setBounds(155, 74, 472, 41);
      panel_3.add(textField_11);
      
      textField_12 = new JTextField();
      textField_12.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_12.setEditable(false);
      textField_12.setColumns(10);
      textField_12.setBounds(155, 147, 466, 41);
      panel_3.add(textField_12);
      
      textField_13 = new JTextField();
      textField_13.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_13.setEditable(false);
      textField_13.setColumns(10);
      textField_13.setBounds(155, 210, 135, 41);
      panel_3.add(textField_13);
      
      JLabel lblNewLabel_4_2_1 = new JLabel("Tiền trả lại khách");
      lblNewLabel_4_2_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_4_2_1.setBounds(333, 210, 141, 41);
      panel_3.add(lblNewLabel_4_2_1);
      
      textField_14 = new JTextField();
      textField_14.setEditable(false);
      textField_14.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_14.setColumns(10);
      textField_14.setBounds(484, 210, 143, 41);
      panel_3.add(textField_14);
      
      search = new JTextField();
      search.setBounds(195, 11, 345, 51);
      DonHoanThanhPane.add(search);
      search.setColumns(10);
      
      JButton btnSearch = new JButton("Tìm kiếm");
      btnSearch.setForeground(new Color(255, 255, 255));
      btnSearch.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnSearch.setBackground(new Color(26, 133, 94));
      btnSearch.setBounds(550, 11, 124, 51);
      DonHoanThanhPane.add(btnSearch);
      
      JButton btnNewButton_6_1 = new JButton("Xuất hóa đơn");
      btnNewButton_6_1.setForeground(new Color(255, 255, 255));
      btnNewButton_6_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnNewButton_6_1.setBackground(new Color(26, 133, 94));
      btnNewButton_6_1.setBounds(129, 693, 183, 59);
      DonHoanThanhPane.add(btnNewButton_6_1);
      
      JButton btnNewButton_6_2_1 = new JButton("Đổi/Trả hàng");
      btnNewButton_6_2_1.setForeground(new Color(255, 255, 255));
      btnNewButton_6_2_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnNewButton_6_2_1.setBackground(new Color(26, 133, 94));
      btnNewButton_6_2_1.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      btnNewButton_6_2_1.setBounds(412, 693, 183, 59);
      DonHoanThanhPane.add(btnNewButton_6_2_1);
      
      JComboBox typeSearch = new JComboBox();
      typeSearch.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      typeSearch.setForeground(new Color(255, 255, 255));
      typeSearch.setBackground(new Color(26, 133, 94));
      typeSearch.setModel(new DefaultComboBoxModel(new String[] {"Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Ngày mua"}));
      typeSearch.setBounds(30, 11, 155, 51);
      DonHoanThanhPane.add(typeSearch);
      
      JPanel DonTraPane = new JPanel();
      DonTraPane.setBackground(new Color(255, 255, 255));
      panelContent.add(DonTraPane, "DonTraPane"); // Tên để chuyển đổi
      DonTraPane.setLayout(null);
      
      JScrollPane scrollPane_3 = new JScrollPane();
      scrollPane_3.setBounds(10, 208, 629, 495);
      DonTraPane.add(scrollPane_3);
//      Đổi trả table 1
      table_5 = new JTable();
      table_5.setModel(new DefaultTableModel(
      	new Object[][] {
      		
      	},
      	new String[] {
      		"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1", "Thu\u1EBF", "Gi\u1EA3m gi\u00E1", "Th\u00E0nh ti\u1EC1n"
      	}
      ));
      giaoDienTable(table_5);
      scrollPane_3.setViewportView(table_5);
      
      JPanel panel_4 = new JPanel();
      panel_4.setBackground(new Color(255, 255, 255));
      panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(26, 133, 94)));
      panel_4.setBounds(20, 38, 406, 141);
      DonTraPane.add(panel_4);
      panel_4.setLayout(null);
      
      JLabel lblNewLabel_6 = new JLabel("Mã nhân viên:");
      lblNewLabel_6.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      lblNewLabel_6.setBounds(10, 27, 112, 26);
      panel_4.add(lblNewLabel_6);
      
      textField_15 = new JTextField();
      textField_15.setEditable(false);
      textField_15.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      textField_15.setBounds(122, 29, 257, 26);
      panel_4.add(textField_15);
      textField_15.setColumns(10);
      
      JLabel lblNewLabel_6_1 = new JLabel("Tên nhân viên:");
      lblNewLabel_6_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      lblNewLabel_6_1.setBounds(10, 71, 112, 26);
      panel_4.add(lblNewLabel_6_1);
      
      textField_16 = new JTextField();
      textField_16.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      textField_16.setEditable(false);
      textField_16.setColumns(10);
      textField_16.setBounds(122, 71, 257, 26);
      panel_4.add(textField_16);
      
      JPanel panel_4_1 = new JPanel();
      panel_4_1.setBackground(new Color(255, 255, 255));
      panel_4_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(26, 133, 94)));
      panel_4_1.setBounds(467, 38, 406, 141);
      DonTraPane.add(panel_4_1);
      panel_4_1.setLayout(null);
      
      textField_17 = new JTextField();
      textField_17.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      textField_17.setEditable(false);
      textField_17.setBounds(115, 25, 281, 27);
      panel_4_1.add(textField_17);
      textField_17.setColumns(10);
      
      textField_18 = new JTextField();
      textField_18.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      textField_18.setEditable(false);
      textField_18.setColumns(10);
      textField_18.setBounds(115, 63, 281, 27);
      panel_4_1.add(textField_18);
      
      textField_19 = new JTextField();
      textField_19.setEditable(false);
      textField_19.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      textField_19.setColumns(10);
      textField_19.setBounds(115, 101, 281, 27);
      panel_4_1.add(textField_19);
      
      JLabel lblNewLabel_6_1_1 = new JLabel("Mã khách hàng:");
      lblNewLabel_6_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      lblNewLabel_6_1_1.setBounds(10, 63, 107, 26);
      panel_4_1.add(lblNewLabel_6_1_1);
      
      JLabel lblNewLabel_6_1_2 = new JLabel("Tên khách hàng:");
      lblNewLabel_6_1_2.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      lblNewLabel_6_1_2.setBounds(10, 101, 107, 26);
      panel_4_1.add(lblNewLabel_6_1_2);
      
      JPanel panel_4_1_1 = new JPanel();
      panel_4_1_1.setBackground(new Color(255, 255, 255));
      panel_4_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin \u0111\u01A1n mua", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(26, 133, 94)));
      panel_4_1_1.setBounds(910, 38, 406, 141);
      DonTraPane.add(panel_4_1_1);
      panel_4_1_1.setLayout(null);
      
      JLabel lblNewLabel_6_1_1_1 = new JLabel("Mã đơn hàng:");
      lblNewLabel_6_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      lblNewLabel_6_1_1_1.setBounds(10, 23, 94, 27);
      panel_4_1_1.add(lblNewLabel_6_1_1_1);
      
      textField_20 = new JTextField();
      textField_20.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      textField_20.setEditable(false);
      textField_20.setColumns(10);
      textField_20.setBounds(114, 23, 281, 27);
      panel_4_1_1.add(textField_20);
      
      JLabel lblNewLabel_6_1_1_1_1 = new JLabel("Ngày tạo:");
      lblNewLabel_6_1_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      lblNewLabel_6_1_1_1_1.setBounds(10, 64, 94, 24);
      panel_4_1_1.add(lblNewLabel_6_1_1_1_1);
      
      textField_21 = new JTextField();
      textField_21.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      textField_21.setEditable(false);
      textField_21.setColumns(10);
      textField_21.setBounds(114, 63, 281, 27);
      panel_4_1_1.add(textField_21);
      
      JLabel lblNewLabel_6_1_1_1_1_1 = new JLabel("Thành tiền:");
      lblNewLabel_6_1_1_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      lblNewLabel_6_1_1_1_1_1.setBounds(10, 104, 76, 14);
      panel_4_1_1.add(lblNewLabel_6_1_1_1_1_1);
      
      textField_22 = new JTextField();
      textField_22.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
      textField_22.setEditable(false);
      textField_22.setColumns(10);
      textField_22.setBounds(114, 101, 281, 27);
      panel_4_1_1.add(textField_22);
      
      JPanel panel_5 = new JPanel();
      panel_5.setBackground(new Color(255, 255, 255));
      panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin \u0111\u01A1n \u0111\u1ED5i tr\u1EA3", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(26, 133, 94)));
      panel_5.setBounds(680, 208, 635, 495);
      DonTraPane.add(panel_5);
      panel_5.setLayout(null);
      
      JLabel lblNewLabel_5 = new JLabel("Mã hóa đơn đổi trả:");
      lblNewLabel_5.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_5.setBounds(25, 31, 158, 30);
      panel_5.add(lblNewLabel_5);
      
      textField_23 = new JTextField();
      textField_23.setEditable(false);
      textField_23.setBounds(214, 31, 396, 30);
      panel_5.add(textField_23);
      textField_23.setColumns(10);
      
      JLabel lblNewLabel_5_1 = new JLabel("Mã nhân viên phụ trách:");
      lblNewLabel_5_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_5_1.setBounds(25, 72, 186, 30);
      panel_5.add(lblNewLabel_5_1);
      
      textField_24 = new JTextField();
      textField_24.setEditable(false);
      textField_24.setColumns(10);
      textField_24.setBounds(214, 72, 396, 30);
      panel_5.add(textField_24);
      
      JLabel lblNewLabel_5_1_1 = new JLabel("Ngày tạo hóa đơn:");
      lblNewLabel_5_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_5_1_1.setBounds(25, 113, 186, 30);
      panel_5.add(lblNewLabel_5_1_1);
      
      textField_25 = new JTextField();
      textField_25.setEditable(false);
      textField_25.setColumns(10);
      textField_25.setBounds(214, 113, 396, 30);
      panel_5.add(textField_25);
      
      JLabel lblNewLabel_5_1_1_1 = new JLabel("Loại:");
      lblNewLabel_5_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_5_1_1_1.setBounds(25, 154, 186, 30);
      panel_5.add(lblNewLabel_5_1_1_1);
   // Tạo các radio button
      JRadioButton rdbtnNewRadioButton = new JRadioButton("Đổi hàng");
      rdbtnNewRadioButton.setBackground(new Color(255, 255, 255));
      rdbtnNewRadioButton.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      rdbtnNewRadioButton.setBounds(217, 158, 109, 23);
      panel_5.add(rdbtnNewRadioButton);

      JRadioButton rdbtnTrHng = new JRadioButton("Trả hàng");
      rdbtnTrHng.setBackground(new Color(255, 255, 255));
      rdbtnTrHng.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      rdbtnTrHng.setBounds(364, 158, 109, 23);
      panel_5.add(rdbtnTrHng);

      // Tạo ButtonGroup và thêm các radio button vào nhóm
      ButtonGroup group = new ButtonGroup();
      group.add(rdbtnNewRadioButton);
      group.add(rdbtnTrHng);

      rdbtnTrHng.setSelected(true);
      
      JLabel lblNewLabel_5_1_1_1_1 = new JLabel("Danh sách sản phẩm:");
      lblNewLabel_5_1_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_5_1_1_1_1.setBounds(25, 195, 186, 30);
      panel_5.add(lblNewLabel_5_1_1_1_1);
      
      JScrollPane scrollPane_4 = new JScrollPane();
      scrollPane_4.setBounds(25, 224, 585, 152);
      panel_5.add(scrollPane_4);
//      Đổi trả table 2
      table_6 = new JTable();
      table_6.setModel(new DefaultTableModel(
      	new Object[][] {
      		{null, null, null, null},
      	},
      	new String[] {
      		"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng", "Th\u00E0nh ti\u1EC1n"
      	}
      ));
      giaoDienTable(table_6);
      scrollPane_4.setViewportView(table_6);
      
      JLabel lblNewLabel_5_1_1_1_1_1 = new JLabel("Lý do:");
      lblNewLabel_5_1_1_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_5_1_1_1_1_1.setBounds(25, 388, 86, 30);
      panel_5.add(lblNewLabel_5_1_1_1_1_1);
      
      textField_26 = new JTextField();
      textField_26.setFont(new Font("Tahoma", Font.PLAIN, 14));
      textField_26.setColumns(10);
      textField_26.setBounds(111, 387, 499, 30);
      panel_5.add(textField_26);
      
      JLabel lblNewLabel_5_1_1_1_1_1_1 = new JLabel("Tiền hoàn");
      lblNewLabel_5_1_1_1_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_5_1_1_1_1_1_1.setBounds(25, 429, 86, 30);
      panel_5.add(lblNewLabel_5_1_1_1_1_1_1);
      
      textField_27 = new JTextField();
      textField_27.setEditable(false);
      textField_27.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_27.setColumns(10);
      textField_27.setBounds(111, 428, 499, 30);
      panel_5.add(textField_27);
      
      JButton btnNewButton_7 = new JButton("Tạo hóa đơn đổi trả");
      btnNewButton_7.setForeground(new Color(255, 255, 255));
      btnNewButton_7.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnNewButton_7.setBackground(new Color(26, 133, 94));
      btnNewButton_7.setBounds(751, 730, 237, 43);
      DonTraPane.add(btnNewButton_7);
      
      JButton btnNewButton_7_1 = new JButton("Hủy đổi trả");
      btnNewButton_7_1.setForeground(new Color(255, 255, 255));
      btnNewButton_7_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnNewButton_7_1.setBackground(new Color(26, 133, 94));
      btnNewButton_7_1.setBounds(1017, 730, 237, 43);
      DonTraPane.add(btnNewButton_7_1);

      // Trang Khuyến Mãi
      JPanel KhuyenMaiPane = new JPanel();
      KhuyenMaiPane.setBackground(new Color(255, 255, 255));
      panelContent.add(KhuyenMaiPane, "KhuyenMaiPane"); // Tên để chuyển đổi
      KhuyenMaiPane.setLayout(null);
      
      textField_28 = new JTextField();
      textField_28.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_28.setBounds(10, 11, 520, 44);
      KhuyenMaiPane.add(textField_28);
      textField_28.setColumns(10);
      
      JButton btnNewButton_8 = new JButton("Tìm kiếm");
      btnNewButton_8.setForeground(new Color(255, 255, 255));
      btnNewButton_8.setBackground(new Color(26, 133, 94));
      btnNewButton_8.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
      btnNewButton_8.setBounds(540, 11, 170, 44);
      KhuyenMaiPane.add(btnNewButton_8);
      
      JScrollPane scrollPane_5 = new JScrollPane();
      scrollPane_5.setBounds(10, 66, 700, 718);
      KhuyenMaiPane.add(scrollPane_5);
      
      table_7 = new JTable();
      table_7.setModel(new DefaultTableModel(
      	new Object[][] {
      		{null, null, null, null, null},
      	},
      	new String[] {
      		"M\u00E3 gi\u1EA3m gi\u00E1", "Lo\u1EA1i gi\u1EA3m gi\u00E1", "Tr\u1ECB gi\u00E1", "\u0110i\u1EC3m quy \u0111\u1ED5i", "H\u1EA1n s\u1EED d\u1EE5ng"
      	}
      ));
      giaoDienTable(table_7);
      scrollPane_5.setViewportView(table_7);
      
      JPanel panel_6 = new JPanel();
      panel_6.setBackground(new Color(255, 255, 255));
      panel_6.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin \u00E1p d\u1EE5ng khuy\u1EBFn m\u00E3i", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(26, 133, 94)));
      panel_6.setBounds(732, 11, 600, 544);
      KhuyenMaiPane.add(panel_6);
      panel_6.setLayout(null);
      
      JLabel lblNewLabel_7 = new JLabel("Mã khuyến mãi:");
      lblNewLabel_7.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_7.setBounds(27, 34, 127, 36);
      panel_6.add(lblNewLabel_7);
      
      textField_29 = new JTextField();
      textField_29.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_29.setEditable(false);
      textField_29.setBounds(167, 34, 400, 36);
      panel_6.add(textField_29);
      textField_29.setColumns(10);
      
      JLabel lblNewLabel_7_1 = new JLabel("Mã hóa đơn:");
      lblNewLabel_7_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_7_1.setBounds(27, 84, 127, 36);
      panel_6.add(lblNewLabel_7_1);
      
      textField_30 = new JTextField();
      textField_30.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_30.setEditable(false);
      textField_30.setColumns(10);
      textField_30.setBounds(167, 81, 400, 36);
      panel_6.add(textField_30);
      
      JLabel lblNewLabel_7_1_1 = new JLabel("Mã khách hàng:");
      lblNewLabel_7_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_7_1_1.setBounds(27, 131, 127, 36);
      panel_6.add(lblNewLabel_7_1_1);
      
      textField_31 = new JTextField();
      textField_31.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_31.setEditable(false);
      textField_31.setColumns(10);
      textField_31.setBounds(167, 128, 400, 36);
      panel_6.add(textField_31);
      
      textField_32 = new JTextField();
      textField_32.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_32.setEditable(false);
      textField_32.setColumns(10);
      textField_32.setBounds(167, 176, 400, 36);
      panel_6.add(textField_32);
      
      JLabel lblNewLabel_7_1_1_1 = new JLabel("Điểm tích lũy");
      lblNewLabel_7_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
      lblNewLabel_7_1_1_1.setBounds(27, 176, 155, 36);
      panel_6.add(lblNewLabel_7_1_1_1);
      
      JLabel lblNewLabel_7_1_1_1_1 = new JLabel("Điểm cần để đổi:");
      lblNewLabel_7_1_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_7_1_1_1_1.setBounds(27, 223, 127, 36);
      panel_6.add(lblNewLabel_7_1_1_1_1);
      
      textField_33 = new JTextField();
      textField_33.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_33.setEditable(false);
      textField_33.setColumns(10);
      textField_33.setBounds(167, 223, 400, 36);
      panel_6.add(textField_33);
      
      JLabel lblNewLabel_7_1_1_1_1_1_1 = new JLabel("Trị giá");
      lblNewLabel_7_1_1_1_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_7_1_1_1_1_1_1.setBounds(27, 270, 127, 36);
      panel_6.add(lblNewLabel_7_1_1_1_1_1_1);
      
      JLabel lblNewLabel_7_1_1_1_1_1_1_1 = new JLabel("Giảm được:");
      lblNewLabel_7_1_1_1_1_1_1_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_7_1_1_1_1_1_1_1.setBounds(27, 317, 127, 36);
      panel_6.add(lblNewLabel_7_1_1_1_1_1_1_1);
      
      JLabel lblNewLabel_7_1_1_1_1_1_1_2 = new JLabel("Thành tiền:");
      lblNewLabel_7_1_1_1_1_1_1_2.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      lblNewLabel_7_1_1_1_1_1_1_2.setBounds(27, 370, 127, 36);
      panel_6.add(lblNewLabel_7_1_1_1_1_1_1_2);
      
      textField_34 = new JTextField();
      textField_34.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_34.setEditable(false);
      textField_34.setColumns(10);
      textField_34.setBounds(167, 270, 400, 36);
      panel_6.add(textField_34);
      
      textField_35 = new JTextField();
      textField_35.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_35.setEditable(false);
      textField_35.setColumns(10);
      textField_35.setBounds(167, 317, 400, 36);
      panel_6.add(textField_35);
      
      textField_36 = new JTextField();
      textField_36.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      textField_36.setEditable(false);
      textField_36.setColumns(10);
      textField_36.setBounds(167, 370, 400, 36);
      panel_6.add(textField_36);
      
      JButton btnNewButton_9 = new JButton("Áp dụng");
      btnNewButton_9.setForeground(new Color(255, 255, 255));
      btnNewButton_9.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
      btnNewButton_9.setBackground(new Color(26, 133, 94));
      btnNewButton_9.setBounds(401, 461, 166, 57);
      panel_6.add(btnNewButton_9);
      
      JButton btnNewButton_9_1 = new JButton("Hủy");
      btnNewButton_9_1.setForeground(new Color(255, 255, 255));
      btnNewButton_9_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
      btnNewButton_9_1.setBackground(new Color(26, 133, 94));
      btnNewButton_9_1.setBounds(167, 461, 166, 57);
      panel_6.add(btnNewButton_9_1);
      MaGiamGia_DAO maGGDAO = new MaGiamGia_DAO();
      //Sự kiện cho giảm giá
      ArrayList<MaGiamGia> maGiamGiaList = maGGDAO.getDanhSachMaGiamGiaConHieuLuc();
   // Thêm dữ liệu vào bảng
      DefaultTableModel model_7 = (DefaultTableModel) table_7.getModel();
      for (MaGiamGia maGiamGia : maGiamGiaList) {
    	  model_7.addRow(new Object[]{
              maGiamGia.getMaGiamGia(),    // Mã giảm giá
              maGGDAO.getLoaiGiamGiaByMa(maGiamGia.getMaGiamGia()),  // Loại giảm giá (ví dụ: phần trăm, số tiền)
              maGiamGia.getTriGia(),       // Trị giá giảm giá
              maGiamGia.getDiemDoi(),      // Điểm quy đổi
              maGiamGia.getNgayHetHan()    // Hạn sử dụng
          });
      }
   // Lắng nghe sự kiện chọn dòng trên table_7
      table_7.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
          @Override
          public void valueChanged(ListSelectionEvent e) {
              if (!e.getValueIsAdjusting()) { // Đảm bảo chỉ xử lý khi người dùng thực sự thay đổi dòng
                  int selectedRow = table_7.getSelectedRow();
                  if (selectedRow != -1) {
                      String maGiam = table_7.getValueAt(selectedRow, 0).toString(); // Lấy mã giảm giá từ dòng được chọn
                      
                      // Gọi lại phương thức getMaGiamGiaTheoMa để lấy thông tin mã giảm giá
                      MaGiamGia maGG = maGGDAO.getMaGiamGiaTheoMa(maGiam);
                      
                      if (maGG != null) {
                          // Cập nhật thông tin mã giảm giá vào các TextField
                          textField_29.setText(maGG.getMaGiamGia());
                          textField_33.setText(String.valueOf(maGG.getDiemDoi()));
                          textField_34.setText(String.valueOf(maGG.getTriGia()));

                          // Tính toán giảm giá
                          Double tongTien = 0.0;
                          try {
                              tongTien = Double.parseDouble(textField_9.getText()); // Lấy tổng tiền từ textField_9
                          } catch (NumberFormatException ex) {
                              tongTien = 0.0; // Nếu không phải là số hợp lệ, gán giá trị 0
                          }

                          if (maGGDAO.getLoaiGiamGiaByMa(maGiam).equals("Tiền")) {
                              textField_35.setText(String.valueOf(maGG.getTriGia())); // Nếu là loại "Tiền", lấy giá trị giảm trực tiếp
                          } else {
                              textField_35.setText(String.valueOf(maGG.getTriGia() * tongTien / 100)); // Nếu là "Phần trăm", tính theo tỷ lệ
                          }

                          // Tính toán số tiền còn lại sau khi giảm
                          Double giam = 0.0;
                          try {
                              giam = Double.parseDouble(textField_35.getText()); // Lấy giá trị giảm từ textField_35
                          } catch (NumberFormatException ex) {
                              giam = 0.0; // Nếu không phải là số hợp lệ, gán giá trị 0
                          }
                       // Tính toán giá trị còn lại sau khi giảm giá
                          Double remainingAmount = tongTien - giam;

                          // Nếu giá trị nhỏ hơn 0, gán bằng 0
                          if (remainingAmount < 0) {
                              remainingAmount = 0.0;
                          }
                          textField_36.setText(String.valueOf(remainingAmount));
                          // Cập nhật số tiền còn lại sau khi giảm
                       
                      } else {
                          // Nếu không tìm thấy mã giảm giá, xóa thông tin
                          textField_29.setText("");
                          textField_33.setText("");
                          textField_34.setText("");
                          textField_35.setText("");
                          textField_36.setText("");
                      }
                  }
              }
          }
      });

   // Thêm sự kiện cho nút Tìm kiếm
      btnNewButton_8 .addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String searchText = textField_28.getText().trim();  // Lấy giá trị người dùng nhập vào

              // Tìm kiếm theo mã giảm giá và cập nhật bảng
              ArrayList<MaGiamGia> resultList = maGGDAO.getDanhSachMaGiamGiaTheoMa(searchText);
              // Xóa hết các hàng hiện tại trong bảng
              model_7.setRowCount(0);

              // Thêm dữ liệu vào bảng
              for (MaGiamGia mgg : resultList) {
            	  model_7.addRow(new Object[]{
                      mgg.getMaGiamGia(),
                      maGGDAO.getLoaiGiamGiaByMa(mgg.getMaGiamGia()),
                      mgg.getTriGia(),
                      mgg.getDiemDoi(),
                      mgg.getNgayHetHan()
                  });
              } 
          }
      });
      btnNewButton_9.addActionListener(e -> {
    	  CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "BanHangPane"); // Chuyển sang trang Bán Hàng
          textField_4.setText(textField_35.getText());
          textField_5.setText(textField_36.getText()); 
      });
      
      

      
      
      
      
      
      
      
      
      

      
      
      // Thêm sự kiện cho các nút chuyển panel
      btnBanHang.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "BanHangPane"); // Chuyển sang trang Bán Hàng
          btnBanHang.setBackground(new Color(153, 211, 158));
          btnDonHang.setBackground(new Color(26, 133, 94));
          btnDonTam.setBackground(new Color(26, 133, 94));
          btnDonHoanThanh.setBackground(new Color(26, 133, 94));
          btnDonTra.setBackground(new Color(26, 133, 94));
          btnKhuyenMai.setBackground(new Color(26, 133, 94));
          
      });
      btnDonHang.addActionListener(e -> {
          btnBanHang.setBackground(new Color(26, 133, 94));
          btnDonHang.setBackground(new Color(153, 211, 158));
          btnDonTam.setBackground(new Color(26, 133, 94));
          btnDonHoanThanh.setBackground(new Color(26, 133, 94));
          btnDonTra.setBackground(new Color(26, 133, 94));
          btnKhuyenMai.setBackground(new Color(26, 133, 94));
      });
      btnDonTam.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "DonTamPane"); // Chuyển sang trang Đơn Hàng
          btnBanHang.setBackground(new Color(26, 133, 94));
          btnDonHang.setBackground(new Color(26, 133, 94));
          btnDonTam.setBackground(new Color(153, 211, 158));
          btnDonHoanThanh.setBackground(new Color(26, 133, 94));
          btnDonTra.setBackground(new Color(26, 133, 94));
          btnKhuyenMai.setBackground(new Color(26, 133, 94));
      });
      
      btnDonHoanThanh.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "DonHoanThanhPane"); // Chuyển sang trang Đơn Hàng
          btnBanHang.setBackground(new Color(26, 133, 94));
          btnDonHang.setBackground(new Color(26, 133, 94));
          btnDonTam.setBackground(new Color(26, 133, 94));
          btnDonHoanThanh.setBackground(new Color(153, 211, 158));
          btnDonTra.setBackground(new Color(26, 133, 94));
          btnKhuyenMai.setBackground(new Color(26, 133, 94));
      });
      
      btnDonTra.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "DonTraPane"); // Chuyển sang trang Đơn Hàng
          btnBanHang.setBackground(new Color(26, 133, 94));
          btnDonHang.setBackground(new Color(26, 133, 94));
          btnDonTam.setBackground(new Color(26, 133, 94));
          btnDonHoanThanh.setBackground(new Color(26, 133, 94));
          btnDonTra.setBackground(new Color(153, 211, 158));
          btnKhuyenMai.setBackground(new Color(26, 133, 94));
      });

      btnKhuyenMai.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "KhuyenMaiPane"); // Chuyển sang trang Khuyến Mãi
          btnBanHang.setBackground(new Color(26, 133, 94));
          btnDonHang.setBackground(new Color(26, 133, 94));
          btnDonTam.setBackground(new Color(26, 133, 94));
          btnDonHoanThanh.setBackground(new Color(26, 133, 94));
          btnDonTra.setBackground(new Color(26, 133, 94));
          btnKhuyenMai.setBackground(new Color(153, 211, 158));
      });
      btnNewButton_1_2.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "KhuyenMaiPane"); // Chuyển sang trang Khuyến Mãi
          btnBanHang.setBackground(new Color(26, 133, 94));
          btnKhuyenMai.setBackground(new Color(153, 211, 158));
          textField_30.setText(textField_2.getText());
          String maKH = "KH" +textField.getText();
          textField_31.setText(maKH);
          int diemTL = khachHangDAO.layDiemTichLuyTheoSDT(textField.getText());
          textField_32.setText(diemTL+"");
      });
   // Lắng nghe sự kiện thay đổi giá trị của JComboBox
      comboBox_1.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String selectedValue = (String) comboBox_1.getSelectedItem();  // Lấy giá trị hiện tại từ JComboBox

              if ("ATM".equalsIgnoreCase(selectedValue)) {
                  textField_6.setEditable(false);  // Không thể chỉnh sửa textField nếu là ATM
              } else {
                  textField_6.setEditable(true);   // Có thể chỉnh sửa textField nếu là Tiền mặt
              }
          }
      });
      // Hiển thị cửa sổ
      setVisible(true);
      khachHang.setMaKhachHang("KH0000000000");
      khachHang.setSDT("0000000000");
      khachHang.setTenKhachHang("Khách Vãng Lai");
      khachHang.setDiemTichLuy(0);
      setMaHD();
   // Thêm ActionListener cho JComboBox để xử lý lựa chọn khi người dùng thay đổi
      comboBox.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              // Lấy lựa chọn hiện tại từ JComboBox
              String selectedOption = (String) comboBox.getSelectedItem();
              textField.setText("");  // Reset textField khi thay đổi lựa chọn
              textField_1.setText("");  // Reset textField_1 khi thay đổi lựa chọn
              khachHang = new KhachHang();
           // Xóa KeyListener và ActionListener nếu có trước khi thay đổi
              for (KeyListener kl : textField.getKeyListeners()) {
                  textField.removeKeyListener(kl);
              }
              for (KeyListener kl : textField_1.getKeyListeners()) {
                  textField_1.removeKeyListener(kl);
              }
              for (ActionListener al : textField.getActionListeners()) {
                  textField.removeActionListener(al);
              }
              for (ActionListener al : textField_1.getActionListeners()) {
                  textField_1.removeActionListener(al);
              }
              if(selectedOption.equalsIgnoreCase("Khách Vãng Lai")) {
            	  textField.setEditable(false);
                  textField.setBackground(new Color(239, 239, 239));  // Màu xám nhạt
                  textField_1.setEditable(false);
                  textField_1.setBackground(new Color(239, 239, 239));  // Màu xám nhạt
                  // Cập nhật mã khách hàng là "KH0000000000" cho khách vãng lai
                  khachHang.setMaKhachHang("KH0000000000");
                  khachHang.setSDT("0000000000");
                  khachHang.setTenKhachHang("Khách Vãng Lai");
                  khachHang.setDiemTichLuy(0);
                  setMaHD();
              } else if (selectedOption.equalsIgnoreCase("Khách Mới")) {
            	  textField.setEditable(true);
                  textField.setBackground(new Color(255, 255, 255));  // Màu trắng
                  textField_1.setEditable(true);
                  textField_1.setBackground(new Color(255, 255, 255));  // Màu trắng
               // Đăng ký KeyListener cho textField (số điện thoại)
                  textField_1.addKeyListener(new KeyAdapter() {
                      @Override
                      public void keyPressed(KeyEvent e) {
                          if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                              // Người dùng nhấn Enter, lấy số điện thoại người dùng nhập
                              String SDT = textField.getText().trim();
                              String tenKh = textField_1.getText().trim();
                              if (SDT.matches("\\d{10,11}") && tenKh.matches("^[A-Za-zÀ-ÿ]+( [A-Za-zÀ-ÿ]+)*$")) {
                            	    // Thực hiện hành động khi SDT hợp lệ
                            	  if (!SDT.isEmpty() && !tenKh.isEmpty()) {
                                      // Tạo mã khách hàng theo quy tắc "KH" + số điện thoại
                                      String maKH = "KH" + SDT;
                                      // Kiểm tra khách hàng có tồn tại trong hệ thống không
                                      if (!(khachHangDAO.kiemTraKHTonTai(SDT))) {
                                          // Nếu khách hàng không tồn tại, cập nhật mã khách hàng vào textField_1
                                          khachHang.setMaKhachHang(maKH);  // Cập nhật thông tin khách hàng
                                          khachHang.setTenKhachHang(tenKh);
                                          khachHang.setSDT(SDT);
                                          khachHang.setDiemTichLuy(0);
                                          setMaHD();
                                      } else {
                                          // Nếu khách hàng tồn tại, thông báo lỗi
                                          JOptionPane.showMessageDialog(null, "Khách hàng với số điện thoại " + SDT + " đã tồn tại trong hệ thống.");
                                      }
                                  }
                            	} else {
                            	    // Thực hiện hành động khi SDT không hợp lệ
                            		JOptionPane.showMessageDialog(null, "Thông tin đã nhập không hợp lệ");
                            	}
                              
                          }
                      }
                  });
            	    
              } else {
            	  textField.setEditable(true);
                  textField.setBackground(new Color(255, 255, 255));  // Màu trắng
                  textField_1.setEditable(false);
                  textField_1.setBackground(new Color(239, 239, 239));  // Màu xám nhạt
                  for (ActionListener al : textField_1.getActionListeners()) {
              	    textField_1.removeActionListener(al);
              	}
                for (ActionListener al : textField.getActionListeners()) {
              	    textField.removeActionListener(al);
              	}
               // Đăng ký KeyListener cho textField (số điện thoại)
                  textField.addKeyListener(new KeyAdapter() {
                      @Override
                      public void keyPressed(KeyEvent e) {
                          if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                              // Lấy số điện thoại người dùng nhập
                              String SDT = textField.getText().trim();
                              if (SDT.matches("\\d{10,11}")) {
                            	  if (!SDT.isEmpty()) {
                                      // Tạo mã khách hàng theo quy tắc "KH" + số điện thoại
                                      String maKH = "KH" + SDT;
                                      // Kiểm tra khách hàng có tồn tại trong hệ thống không
                                      if (khachHangDAO.kiemTraKHTonTai(SDT)) {
                                          // Cập nhật thông tin khách hàng
                                          khachHang = khachHangDAO.layKhachHangTheoMa(maKH);
                                          textField_1.setText(khachHang.getTenKhachHang());
                                          setMaHD();
                                      } else {
                                          // Nếu khách hàng không tồn tại, thông báo lỗi
                                          JOptionPane.showMessageDialog(null, "Khách hàng với số điện thoại " + SDT + " chưa tồn tại trong hệ thống.");
                                      }
                                  }
                              } else {
                            	  JOptionPane.showMessageDialog(null, "Thông tin đã nhập không hợp lệ");
                              }
                          }
                      }
                  });
              }
          }
      });

// Lưu đơn tạm 

      
      btnNewButton_1_1.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent evt) {
    	        // Lấy thông tin đơn tạm từ các trường nhập liệu
    	        String maHoaDon = textField_2.getText();  // Mã hóa đơn
    	        String sdtKhachHang = textField.getText();  // Lấy số điện thoại khách hàng từ textField
    	        String tenKhachHang = textField_1.getText();  // Tên khách hàng
    	        LocalDate ngayTaoHoaDon = LocalDate.now();  // Ngày tạo hóa đơn là ngày hiện tại

    	        // Lấy thông tin chi tiết đơn tạm từ bảng
    	        java.util.List<ChiTietDonTam> chiTietDonTamList = new ArrayList<>();
    	        DefaultTableModel model = (DefaultTableModel) table.getModel();  // Lấy model của bảng

    	        // Duyệt qua các dòng trong bảng và lấy dữ liệu chi tiết hóa đơn
    	        for (int i = 0; i < model.getRowCount(); i++) {
    	            String maSanPham = (String) model.getValueAt(i, 0);  // Mã sản phẩm
    	            String tenSanPham = (String) model.getValueAt(i, 1);  // Tên sản phẩm

    	            // Kiểm tra và ép kiểu số lượng (int)
    	            int soLuong = 0;
    	            try {
    	                soLuong = Integer.parseInt(model.getValueAt(i, 2).toString());  // Ép kiểu số lượng
    	            } catch (NumberFormatException ex) {
    	                System.out.println("Lỗi chuyển đổi số lượng: " + ex.getMessage());
    	                continue;  // Nếu có lỗi chuyển đổi, bỏ qua dòng này
    	            }

    	            // Kiểm tra và ép kiểu đơn giá (double)
    	            double donGia = 0.0;
    	            try {
    	                donGia = Double.parseDouble(textField_9.getText());  // Ép kiểu đơn giá
    	            } catch (NumberFormatException ex) {
    	                System.out.println("Lỗi chuyển đổi đơn giá: " + ex.getMessage());
    	                continue;  // Nếu có lỗi chuyển đổi, bỏ qua dòng này
    	            }

    	            // Tạo đối tượng ChiTietDonTam và thêm vào danh sách
    	            ChiTietDonTam chiTiet = new ChiTietDonTam(maSanPham, tenSanPham, soLuong, donGia);
    	            chiTietDonTamList.add(chiTiet);
    	        }

    	        // Kiểm tra xem danh sách chi tiết có dữ liệu không
    	        if (chiTietDonTamList.isEmpty()) {
    	            System.out.println("Danh sách chi tiết đơn tạm không có dữ liệu.");
    	            return;  // Nếu không có chi tiết, dừng lại
    	        }

    	        // Tạo đối tượng DonTam và lưu vào danh sách
    	        DonTam donTam = new DonTam(maHoaDon, sdtKhachHang, tenKhachHang, ngayTaoHoaDon, chiTietDonTamList);

    	        // Gọi phương thức lưu đơn tạm vào file JSON (hoặc cơ sở dữ liệu)
    	        DonTam_DAO donTamDao = new DonTam_DAO();
    	        donTamDao.addDonTam(donTam);  // Lưu đối tượng DonTam
    	        System.out.println("Đơn tạm đã được lưu thành công.");

    	        // Làm mới dữ liệu hiển thị trên bảng
    	        updateDonTamTable();
    	     // Xóa trắng bảng
    	        model.setRowCount(0);  // Xóa toàn bộ dữ liệu trong bảng

    	        // Xóa dữ liệu trong các trường nhập liệu
    	       
//    	        textField_2.setText("");
//    	        textField.setText("");
//    	        textField_1.setText("");
//    	        textField_9.setText("");
//    	        textField_5.setText("");
//    	        textField_6.setText("");
//    	        textField_8.setText("");
    	        // Chuyển sang pane Đơn Tạm
    	        CardLayout cardLayout = (CardLayout) panelContent.getLayout();
    	        cardLayout.show(panelContent, "DonTamPane");
    	    }
    	});
      
      
      
      
//  Xử lý đơn tạm 
      btnNewButton_4_2.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        // Lấy dòng đang được chọn trong table_1
    	        int selectedRow = table_1.getSelectedRow();
    	        if (selectedRow == -1) {
    	            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	            return;
    	        }

    	        // Gán giá trị từ table_1 vào các text field
    	        DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
    	        String maHoaDon = (String) model1.getValueAt(selectedRow, 0);
    	        String sdtKhachHang = (String) model1.getValueAt(selectedRow, 1);
    	        String tenKhachHang = (String) model1.getValueAt(selectedRow, 2);

    	        textField_2.setText(maHoaDon);
    	        textField_1.setText(tenKhachHang);
    	        textField.setText(sdtKhachHang);

    	        // Lấy dữ liệu từ table_2 và thêm vào table
    	        DefaultTableModel model2 = (DefaultTableModel) table_2.getModel();
    	        DefaultTableModel modelTable = (DefaultTableModel) table.getModel();

    	        SanPham_DAO sanPhamDAO = new SanPham_DAO();

    	        // Duyệt qua từng dòng của table_2
    	        for (int i = 0; i < model2.getRowCount(); i++) {
    	            String maSanPham = (String) model2.getValueAt(i, 0); // Mã sản phẩm
    	            int soLuong = Integer.parseInt(model2.getValueAt(i, 2).toString()); // Số lượng

    	            // Gọi phương thức để lấy thông tin sản phẩm
    	            SanPham sanPham = sanPhamDAO.getSanPhamByMaSanPham(maSanPham);
    	            if (sanPham != null) {
    	                // Lấy thông tin cần thiết
    	                double giaBan = sanPham.getGiaBan();
    	                double thueGTGT = giaBan * 10 / 100; // Giả sử thuế GTGT là 10%
    	                double giamGia = giaBan * 0 / 100;  // Giả sử giảm giá là 0%
    	                double thanhTien = (giaBan + thueGTGT - giamGia) * soLuong;

    	                // Thêm dòng mới vào table
    	                modelTable.addRow(new Object[]{
    	                    maSanPham,
    	                    sanPham.getTenSanPham(),
    	                    soLuong,
    	                    giaBan,
    	                    thueGTGT,
    	                    giamGia,
    	                    thanhTien
    	                });
    	            } else {
    	                JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm: " + maSanPham, "Lỗi", JOptionPane.ERROR_MESSAGE);
    	            }
    	        }

    	        // Cập nhật tổng tiền
    	        double tongThanhTien = 0.0;
    	        for (int i = 0; i < modelTable.getRowCount(); i++) {
    	            double thanhTien = (double) modelTable.getValueAt(i, 6); // Cột "Thành Tiền"
    	            tongThanhTien += thanhTien;
    	        }
    	        textField_9.setText(String.format("%.2f", tongThanhTien));
    	        textField_5.setText(textField_9.getText());
    	        // Xóa đơn tạm
    	        DonTam_DAO donTamDAO = new DonTam_DAO();
    	        boolean isDeleted = donTamDAO.xoaDonTam(maHoaDon);
    	        if (isDeleted) {
    	            JOptionPane.showMessageDialog(null, "Đơn tạm đã được xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

    	            // Xóa dòng đã chọn khỏi bảng table_1
    	            ((DefaultTableModel) table_1.getModel()).removeRow(selectedRow);
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Không thể xóa đơn tạm. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	        }
    	     // Chuyển sang pane Bán hàng
    	        CardLayout cardLayout = (CardLayout) panelContent.getLayout();
    	        cardLayout.show(panelContent, "BanHangPane");
    	    }
    	});


    	

    
   // Xử lý sự kiện thêm sản phẩm
      btnNewButton.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        String maSanPham = txtNhpMSn.getText().trim(); // Giả sử bạn có một text field nhập mã sản phẩm

    	        if (maSanPham.isEmpty()) {
    	            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	            return;
    	        }

    	        SanPham_DAO sanPhamDAO = new SanPham_DAO();
    	        SanPham sanPham = sanPhamDAO.getSanPhamByMaSanPham(maSanPham);
    	        
    	        if (sanPham != null) {
    	            // Lấy giá bán và các thông tin cần thiết từ sản phẩm
    	            double giaBan = sanPham.getGiaBan();
    	            double thueGTGT = giaBan * sanPham.getThueGTGT() / 100;
    	            double giamGia = 0; // Giả sử không có giảm giá
    	            double thanhTien = giaBan + thueGTGT - giamGia;

    	            // Kiểm tra xem sản phẩm đã tồn tại trong bảng chưa
    	            DefaultTableModel model = (DefaultTableModel) table.getModel();
    	            boolean found = false;  // Biến kiểm tra xem sản phẩm đã có trong bảng

    	            for (int i = 0; i < model.getRowCount(); i++) {
    	                String maSPInTable = (String) model.getValueAt(i, 0); // Cột mã sản phẩm

    	                if (maSPInTable.equals(maSanPham)) {
    	                    // Nếu sản phẩm đã tồn tại trong bảng, cập nhật số lượng và tính lại thành tiền
    	                    int currentQuantity = 0;

    	                    try {
    	                        // Kiểm tra và lấy số lượng hiện tại từ bảng (chắc chắn là Integer)
    	                        currentQuantity = Integer.parseInt(model.getValueAt(i, 2).toString());
    	                    } catch (NumberFormatException ex) {
    	                        // Nếu số lượng không phải là số nguyên hợp lệ, thông báo lỗi
    	                        JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	                        return;
    	                    }

    	                    int newQuantity = currentQuantity + Integer.parseInt(txtNhapSL.getText()); // Cập nhật số lượng mới

    	                    model.setValueAt(newQuantity, i, 2); // Cập nhật số lượng trong bảng

    	                    // Tính lại thành tiền cho sản phẩm
    	                    double newThanhTien = (giaBan + thueGTGT - giamGia) * newQuantity;
    	                    model.setValueAt(newThanhTien, i, 6); // Cập nhật lại thành tiền

    	                    found = true; // Đánh dấu là đã tìm thấy và cập nhật
    	                    break; // Thoát vòng lặp
    	                }
    	            }
    	            
    	            if (!found) {
    	                // Nếu sản phẩm chưa có trong bảng, thêm mới sản phẩm vào bảng
    	                model.addRow(new Object[]{
    	                    sanPham.getMaSanPham(),
    	                    sanPham.getTenSanPham(),
    	                    txtNhapSL.getText(),  // Số lượng mặc định là giá trị trong text field
    	                    giaBan,  // Không định dạng ở đây
    	                    thueGTGT,
    	                    giamGia,
    	                    thanhTien  // Không định dạng ở đây
    	                });
    	            }

    	            // Tính và cập nhật tổng thành tiền
    	            double tongThanhTien = 0.0;
    	            for (int i = 0; i < model.getRowCount(); i++) {
    	                double thanhTienRow = (double) model.getValueAt(i, 6); // Cột "Thành Tiền"
    	                tongThanhTien += thanhTienRow;
    	            }

    	            // Định dạng lại tổng tiền trước khi hiển thị
    	            textField_9.setText(tongThanhTien+""); // Cập nhật tổng thành tiền (định dạng tiền tệ)
    	            int tienKhachTra = (int) ((int) tongThanhTien - tongThanhTien%1000);
    	            textField_5.setText(tienKhachTra+""); // Cập nhật giá trị tổng tiền
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Sản phẩm không tồn tại trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	        }
    	    }
    	});


      
      // Xử lý sự kiện xóa sản phẩm
      btnXa.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              int selectedRow = table.getSelectedRow();

              if (selectedRow != -1) {
                  DefaultTableModel model = (DefaultTableModel) table.getModel();
                  model.removeRow(selectedRow);

                  // Tính và cập nhật tổng thành tiền sau khi xóa sản phẩm
                  double tongThanhTien = 0.0;
                  for (int i = 0; i < model.getRowCount(); i++) {
                      double thanhTienRow = (double) model.getValueAt(i, 6); // Cột "Thành Tiền"
                      tongThanhTien += thanhTienRow;
                  }

                  textField_9.setText(formatter2.format(tongThanhTien));
                  int tienKhachTra = (int) tongThanhTien;
  	              textField_5.setText(formatter2.format(tienKhachTra));
              } else {
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
              }
          }
      });

   // Thêm ActionListener cho nút NewButton_2
      btnNewButton_2.addActionListener(e -> {
          try {
              // Bước 1: Thu thập dữ liệu từ giao diện
              String maHoaDon = textField_2.getText();
              String ngayTaoStr = textField_3.getText();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  // Định dạng ngày tháng bạn nhập vào
              LocalDate ngayTao = LocalDate.parse(ngayTaoStr, formatter);              
              Double tongTien = Double.parseDouble(textField_9.getText());
              String selectedValue = (String) comboBox_1.getSelectedItem();
              Double tienThoi = 0.0;
              if (selectedValue.equals("ATM")) {
            	  tienThoi = 0.0; 
              } else {
            	  tienThoi = Double.parseDouble(textField_8.getText());
              }
     
              int diemTichLuy = 0;
              int diemCong = (int) (tongTien*0.001);
              if (comboBox.getSelectedItem().equals("Thành Viên")) {
            	  khachHang = khachHangDAO.layKhachHangTheoSDT(textField.getText());
            	  diemTichLuy = khachHangDAO.layDiemTichLuyTheoSDT(textField.getText());
            	  khachHang.setDiemTichLuy(diemTichLuy+diemCong);
            	  khachHangDAO.updateKhachHang(khachHang.getMaKhachHang(), khachHang.getTenKhachHang(), khachHang.getSDT(), khachHang.getDiemTichLuy());
              }
              if (comboBox.getSelectedItem().equals("Khách Mới")) {
            	  khachHangDAO.saveKhachHang(khachHang.getMaKhachHang(),khachHang.getTenKhachHang(), khachHang.getSDT(), diemCong);
              }
              // Bước 2: Tạo đối tượng HoaDonXuat
              HoaDonXuat hoaDon = new HoaDonXuat();
              hoaDon.setMaHoaDonXuat(maHoaDon);
              hoaDon.setNgayTao(ngayTao);
              hoaDon.setTienKhachDua(tongTien);
              hoaDon.setTienThoi(tienThoi);
              NhanVien_DAO nhanVienDAO = new NhanVien_DAO();
              hoaDon.setNhanVien(nhanVienDAO .layThongTinNhanVienTheoMa(maNVDangNhap));
              hoaDon.setKhachHang(khachHang);
              // Bước 3: Gọi DAO để lưu đối tượng vào database
              HoaDonXuat_DAO hoaDonDAO = new HoaDonXuat_DAO();
              DefaultTableModel model = (DefaultTableModel) table.getModel();
              hoaDonDAO.luuHoaDonXuat(hoaDon, model);  // Phương thức lưu vào database trong DAO
              
              //Chuyển trang
              DefaultTableModel model_8 = (DefaultTableModel) table_3.getModel();
              String maKH = khachHang.getMaKhachHang();
              model_8.addRow(new Object[] {hoaDon.getMaHoaDonXuat(), maNVDangNhap, maKH,LocalDate.now(),0, tongTien });
              table_3.setModel(model_8);
              CardLayout cardLayout = (CardLayout) panelContent.getLayout();
              cardLayout.show(panelContent, "DonHoanThanhPane"); // Chuyển sang trang Khuyến Mãi
          } catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "Lỗi khi lưu hóa đơn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
          }
      });
      btnSearch.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String searchTerm = search.getText(); // Get the input from the search field
		        String selectedType = (String) typeSearch.getSelectedItem();
		        DefaultTableModel model_1 = (DefaultTableModel) table_3.getModel();
		        table_3.setModel(model_1);
		        // Clear the existing table data
		        model_1.setRowCount(0);
		        ArrayList<Object[]> results;
		            results = hoaDonXuatDAO.layDanhSachHoaDonTheoNgay(searchTerm, selectedType, LocalDate.now());
		       
		        // Check if results are empty
		        if (results.isEmpty()) {
		            // Show message when no results are found
		            JOptionPane.showMessageDialog(panel, "Không tìm thấy", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		        	for (Object[] row : results) {
		        		model_1.addRow(row);
		        	}
		    }}
		});
      textField_6.addFocusListener(new FocusAdapter() {
    	    @Override
    	    public void focusLost(FocusEvent e) {
    	        try {
    	            // Lấy giá trị từ textField_9 và textField_6
    	            double tienPhaiTra = Double.parseDouble(textField_5.getText());
    	            double tienKhachDua = Double.parseDouble(textField_6.getText());
    	            String selectedValue = (String) comboBox.getSelectedItem();  // Lấy giá trị hiện tại từ JComboBox
    	            double tienThoiLai = 0;
                    if ("ATM".equals(selectedValue)) {
                    	tienThoiLai = 0;  // Không thể chỉnh sửa textField nếu là ATM
                    } else {
                    	tienThoiLai = tienKhachDua*1000 - tienPhaiTra;   // Có thể chỉnh sửa textField nếu là Tiền mặt
                    }
    	            // Thực hiện phép tính
    	            
    	            if (tienThoiLai<0) {
    	            	JOptionPane.showMessageDialog(null, "Số tiền khách đưa chưa hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	            } else {
    	            	// Gán kết quả vào textField_8
        	            textField_8.setText(String.valueOf(tienThoiLai));
    	            }
    	        } catch (NumberFormatException ex) {
    	            // Xử lý ngoại lệ nếu giá trị nhập không phải là số
    	            JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	            textField_6.setText("");
    	            textField_8.setText("");
    	        }
    	    }
    	});
      
      btnNewButton_1_3.addActionListener(e -> {
    	    // Xóa trắng thanh nhập mã sản phẩm (ví dụ: textField_5 là mã sản phẩm)
    	    textField.setText("");
    	    txtNhpMSn.setText("");
    	    // Xóa các sản phẩm khỏi bảng (table)
    	    DefaultTableModel model = (DefaultTableModel) table.getModel();
    	    model.setRowCount(0); // Xóa tất cả các dòng trong bảng

    	    // Xóa thông tin khách hàng (ví dụ: textField_7 là tên khách hàng)
    	    textField.setText("");
    	    textField_1.setText("");

    	    // Xóa tiền khách đưa (ví dụ: textField_6 là tiền khách đưa)
    	    textField_2.setText("");
    	    textField_4.setText("");
    	    textField_5.setText("");
    	    textField_6.setText("");
    	    textField_8.setText("");
    	    
    	   
    	   
    	}
      );
      

      loadDonTamData();

  
      
      
      
      
   // Lắng nghe sự kiện khi người dùng chọn một dòng trong table_1
      table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
          @Override
          public void valueChanged(ListSelectionEvent e) {
              if (!e.getValueIsAdjusting()) {
                  int selectedRow = table_1.getSelectedRow();  // Lấy dòng được chọn
                  if (selectedRow != -1) {  // Kiểm tra nếu có dòng được chọn
                      // Trước tiên, xóa tất cả các dòng hiện có trong table_2
                      DefaultTableModel model2 = (DefaultTableModel) table_2.getModel();
                      model2.setRowCount(0);  // Xóa toàn bộ các dòng trong table_2

                      // Lấy mã hóa đơn từ dòng được chọn
                      String maHoaDon = (String) table_1.getValueAt(selectedRow, 0);

                      // Gọi phương thức để lấy chi tiết hóa đơn từ DAO hoặc dữ liệu có sẵn
                      DonTam_DAO donTamDao = new DonTam_DAO();
                      DonTam donTam = donTamDao.getDonTamByMaHoaDon(maHoaDon);

                      if (donTam != null) {
                          // Cập nhật bảng table_2 với chi tiết của hóa đơn
                          java.util.List<ChiTietDonTam> chiTietList = donTam.getDonHang();

                          // Thêm dữ liệu chi tiết vào table_2
                          for (ChiTietDonTam chiTiet : chiTietList) {
                              model2.addRow(new Object[] {
                                  chiTiet.getMaSanPham(),
                                  chiTiet.getTenSanPham(),
                                  chiTiet.getSoLuong(),
                                  chiTiet.getDonGia()
                              });
                          }
                      }
                  }
              }
          }
      });


      table_3.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseClicked(MouseEvent e) {
    	        // Lấy chỉ số dòng mà người dùng nhấp vào
    	        int row = table_3.rowAtPoint(e.getPoint());
    	        table_3.clearSelection(); // Xóa lựa chọn hiện tại
    	        table_3.setRowSelectionInterval(row, row); // Chọn hàng đã nhấp vào
    	        
    	        DefaultTableModel model_1 = (DefaultTableModel) table_3.getModel();
    	        
    	        // Lấy mã hóa đơn xuất từ cột đầu tiên của hàng đã chọn
    	        String maHoaDon = (String) table_3.getValueAt(row, 0); // Giả sử mã hóa đơn xuất ở cột đầu tiên
    	        
    	        // Lấy bảng chi tiết sản phẩm (table_4) để cập nhật
    	        DefaultTableModel model_2 = (DefaultTableModel) table_4.getModel();
    	        model_2.setRowCount(0); // Xóa hết các hàng cũ trong bảng chi tiết
    	        
    	        // Lấy danh sách chi tiết sản phẩm cho mã hóa đơn
    	        ArrayList<Object[]> chiTietSanPham = hoaDonXuatDAO.layDanhSachChiTietSanPhamTheoMaHoaDonXuat2(maHoaDon);
    	        
    	        // Thêm dữ liệu vào bảng chi tiết sản phẩm (table_4)
    	        for (Object[] rowData : chiTietSanPham) {
    	            model_2.addRow(rowData);
    	        }

    	        // Cập nhật thông tin khách hàng và hóa đơn vào các trường văn bản
    	        String maKhachHang = (String) table_3.getValueAt(row, 2);
    	        KhachHang kh = khachHangDAO.layKhachHangTheoMa(maKhachHang);
    	        HoaDonXuat HD = hoaDonXuatDAO.findHoaDonById(maHoaDon);

    	        txtLoiKhchHng.setText("Thành viên");
    	        if (maKhachHang.equals("KH0000000000")) {
    	            txtLoiKhchHng.setText("Khách Vãng Lai");
    	        }
    	        
    	        // Điền thông tin khách hàng vào các trường văn bản
    	        textField_11.setText(kh.getMaKhachHang());
    	        textField_12.setText(kh.getTenKhachHang());
    	        textField_13.setText(HD.getTienKhachDua() + "");
    	        textField_14.setText(HD.getTienThoi() + "");

    	        // Thêm sự kiện ActionListener cho nút xuất hóa đơn (btnNewButton_6_1)
    	        btnNewButton_6_1.addActionListener(new ActionListener() {
    	            @Override
    	            public void actionPerformed(ActionEvent e) {
    	                // Gọi hàm GeneratePdf để xuất hóa đơn cho mã hóa đơn đã chọn
    	                try {
    	                    // Gọi hàm GeneratePdf và truyền mã hóa đơn cần xuất
    	                    GeneratePdf(maHoaDon, maKhachHang);  // Gọi phương thức main của class GeneratePdf

    	                    // Mở file PDF vừa tạo
    	                    File invoiceFile = new File(maHoaDon+".pdf");
    	                    if (invoiceFile.exists()) {
    	                        // Sử dụng Desktop API để mở file PDF
    	                        if (Desktop.isDesktopSupported()) {
    	                            Desktop desktop = Desktop.getDesktop();
    	                            desktop.open(invoiceFile);  // Mở file PDF với ứng dụng mặc định
    	                        } else {
    	                            JOptionPane.showMessageDialog(null, "Ứng dụng mở PDF không được hỗ trợ.");
    	                        }
    	                    } else {
    	                        JOptionPane.showMessageDialog(null, "Không thể tìm thấy file hóa đơn.");
    	                    }
    	                } catch (Exception ex) {
    	                    JOptionPane.showMessageDialog(null, "Lỗi khi xuất hóa đơn: " + ex.getMessage());
    	                }
    	            }
    	        });
    	        table_3.setModel(model_1);
		        table_4.setModel(model_2);
    	    }
    	});

     
   // Lắng nghe sự kiện khi người dùng nhấn nút tìm kiếm
      btnNewButton_3.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              // Lấy giá trị mã hóa đơn từ ô nhập liệu
              String maHoaDon = txtMHan.getText().trim();
              
              // Kiểm tra xem mã hóa đơn có trống không
              if (maHoaDon.isEmpty()) {
                  JOptionPane.showMessageDialog(null, "Vui lòng nhập mã hóa đơn!");
                  return;
              }

              // Tìm kiếm mã hóa đơn trong table_1
              boolean found = false;
              for (int row = 0; row < table_1.getRowCount(); row++) {
                  String maHoaDonTrongTable = table_1.getValueAt(row, 0).toString(); // Giả sử cột mã hóa đơn là cột đầu tiên (index 0)

                  if (maHoaDonTrongTable.equals(maHoaDon)) {
                      // Nếu tìm thấy mã hóa đơn, chọn dòng đó trong table_1
                      table_1.setRowSelectionInterval(row, row);
                      found = true;
                      break;
                  }
              }

              if (found) {
                  // Hiển thị thông báo nếu tìm thấy mã hóa đơn và dòng đã được chọn
                  JOptionPane.showMessageDialog(null, "Đã chọn mã hóa đơn: " + maHoaDon);
              } else {
                  // Thông báo nếu không tìm thấy mã hóa đơn trong bảng
                  JOptionPane.showMessageDialog(null, "Không tìm thấy mã hóa đơn: " + maHoaDon);
              }
          }
      });
      
      btnNewButton_4.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent evt) {
    	        int confirm = JOptionPane.showConfirmDialog(null, 
    	            "Bạn có chắc chắn muốn xóa toàn bộ dữ liệu không?", 
    	            "Xác nhận xóa", 
    	            JOptionPane.YES_NO_OPTION);

    	        if (confirm == JOptionPane.YES_OPTION) {
    	            // Xóa toàn bộ dữ liệu
    	            DonTam_DAO donTamDao = new DonTam_DAO();
    	            donTamDao.clearAllDonTam(); // Gọi phương thức xóa

    	            // Làm mới bảng hiển thị
    	            updateDonTamTable();
    	            JOptionPane.showMessageDialog(null, "Đã xóa toàn bộ dữ liệu.");
    	        }
    	    }
    	});

      btnNewButton_4_1.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        // Lấy mã hóa đơn từ cột đầu tiên trong bảng
    	        int selectedRow = table_1.getSelectedRow(); // Lấy dòng được chọn trong bảng
    	        if (selectedRow != -1) { // Nếu có dòng được chọn
    	            String maHoaDon = table_1.getValueAt(selectedRow, 0).toString(); // Lấy mã hóa đơn

    	            // Tạo đối tượng DonTamDAO để gọi phương thức xóa
    	            DonTam_DAO donTamDAO = new DonTam_DAO();
    	            boolean success = donTamDAO.xoaDonTam(maHoaDon); // Gọi phương thức xóa

    	            // Thông báo kết quả xóa
    	            if (success) {
    	                JOptionPane.showMessageDialog(null, "Đã xóa đơn tạm với mã hóa đơn: " + maHoaDon);

    	                // Cập nhật lại bảng (xóa dòng trong bảng)
    	                DefaultTableModel model = (DefaultTableModel) table_1.getModel();
    	                model.removeRow(selectedRow); // Xóa dòng đã chọn trong bảng
    	            } else {
    	                JOptionPane.showMessageDialog(null, "Không tìm thấy mã hóa đơn " + maHoaDon + " trong file.");
    	            }
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Vui lòng chọn một đơn hàng trong bảng.");
    	        }
    	    }
    	});

      //initComponents();
      initializeInvoiceFields();
      
   // Thêm ActionListener vào nút btnNewButton_6_2_1
      btnNewButton_6_2_1.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        // Lấy mô hình của table_4 và table_5
    	        DefaultTableModel modelTable4 = (DefaultTableModel) table_4.getModel();
    	        DefaultTableModel modelTable5 = (DefaultTableModel) table_5.getModel();
    	        DefaultTableModel modelTable6 = (DefaultTableModel) table_6.getModel();
    	        // Xóa các hàng hiện tại của table_5
    	        modelTable5.setRowCount(0);

    	        // Lặp qua các hàng của table_4 và thêm vào table_5
    	        for (int i = 0; i < modelTable4.getRowCount(); i++) {
    	            Object maSanPham = modelTable4.getValueAt(i, 0);
    	            Object tenSanPham = modelTable4.getValueAt(i, 1);
    	            Object soLuong = modelTable4.getValueAt(i, 2);
    	            Object donGia = modelTable4.getValueAt(i, 3);
    	            Object thueGTGT = modelTable4.getValueAt(i, 4);
    	            Object thanhTien = modelTable4.getValueAt(i, 5);

    	            // Thêm hàng vào table_5
    	            modelTable5.addRow(new Object[]{maSanPham, tenSanPham, soLuong, donGia, thueGTGT, null, thanhTien});
    	            modelTable6.addRow(new Object[]{maSanPham, tenSanPham, soLuong, thanhTien});
    	        }

    	        // Lấy mô hình của table_3
    	        DefaultTableModel modelTable3 = (DefaultTableModel) table_3.getModel();

    	        // Lấy chỉ số dòng đang được chọn
    	        int selectedRow = table_3.getSelectedRow();

    	        // Kiểm tra nếu có dòng nào được chọn
    	        if (selectedRow != -1) {
    	            // Lấy các giá trị trong dòng đó
    	        	
//    	        	lấy của LLy
//    	        	String maHoaDon = textField_2.getText();
//    	              String ngayTaoStr = textField_3.getText();
//    	              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  // Định dạng ngày tháng bạn nhập vào
//    	              LocalDate ngayTao = LocalDate.parse(ngayTaoStr, formatter);              
//    	              Double tongTien = Double.parseDouble(textField_9.getText());
//    	              Double tienThoi = Double.parseDouble(textField_8.getText());
    	        	
    	        	
//    	            String maHoaDon = (String) modelTable3.getValueAt(selectedRow, 0);
    	        	String maHoaDon = textField_2.getText();
    	            String maNhanVien = (String) modelTable3.getValueAt(selectedRow, 1);
//    	            String maKhachHang = (String) modelTable3.getValueAt(selectedRow, 2);
    	            String maKhachHang = "KH"+textField.getText();
//    	            java.sql.Date ngayMuaSQL = (java.sql.Date) modelTable3.getValueAt(selectedRow, 3);
//    	            String ngayMua = ngayMuaSQL.toString();
    	            String ngayTaoStr = textField_3.getText();
  	              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  // Định dạng ngày tháng bạn nhập vào
  	              LocalDate ngayTao = LocalDate.parse(ngayTaoStr, formatter);
    	            String ngayMua = ngayTao.toString();
//    	            String maGiamGia = (String) modelTable3.getValueAt(selectedRow, 4);
    	            int maGG = (Integer) modelTable3.getValueAt(selectedRow, 4);
    	            String maGiamGia = String.valueOf(maGG);
    	            Double value = (Double) modelTable3.getValueAt(selectedRow, 5);  // Giả sử bạn đang lấy giá trị kiểu Double từ bảng
    	            String thanhTien = String.valueOf(value);
    	            // Gọi hàm truy vấn tên khách hàng và tên nhân viên từ DAO
    	            String tenKhachHang = khachHangDAO.layTenKhachHangByMa(maKhachHang);
    	            String tenNhanVien = nhanVienDAO.layTenNhanVienByMa(maNhanVien);

    	            String loaiKhachHang;
					if (maKhachHang.equals("KH0000000000")) {
						loaiKhachHang = "Khách vãng lai";
					}
					else {
						loaiKhachHang= "Thành viên";
					}
    	            // Đặt giá trị cho các JTextField tương ứng
    	            textField_15.setText(maNhanVien);
    	            textField_16.setText(tenNhanVien != null ? tenNhanVien : "Không tìm thấy tên NV");
    	            textField_17.setText(loaiKhachHang);
    	            textField_18.setText(maKhachHang);
    	            textField_19.setText(tenKhachHang != null ? tenKhachHang : "Không tìm thấy tên KH");
    	            textField_20.setText(maHoaDon);
    	            textField_21.setText(ngayMua);
    	            textField_22.setText(thanhTien);
    	            
    	            
    	            
    	            
    	            String maHoaDonDoiTra = maHoaDon.replace("BH", "DT");
    	            textField_23.setText(maHoaDonDoiTra);
    	            textField_24.setText(maNVDangNhap);
    	            textField_25.setText(ngayMua);
//    	            textField_26 : Lý do
    	            textField_27.setText(thanhTien);
//Ngày tạo, Tiền hoàn, Lý do, maHoaDonDoiTra, maHoaDonXuat, maKhachHang, maNhanVien, loaiHoaDon ( Trả hàng / Đổi hàng )

    	            // Chuyển từ panel DonHoanThanhPane sang DonTraPane
    	            DonHoanThanhPane.setVisible(false);
    	            DonTraPane.setVisible(true);
    	        } else {
    	            // Hiển thị thông báo nếu chưa chọn dòng nào
    	            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trong bảng.");
    	        }
    	        CardLayout cardLayout = (CardLayout) panelContent.getLayout();
    	        cardLayout.show(panelContent, "DonTraPane");
    	    }
    	});
      
      

      
      
      btnNewButton_7.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        // Lấy thông tin từ các TextField
    	        String maHoaDonDoiTra = textField_23.getText();
    	        String maHoaDonXuat = textField_20.getText();
    	        String maKhachHang = textField_18.getText();
    	        String maNhanVien = textField_15.getText(); // textField_24 mới đúng
    	        LoaiHoaDon loaiHoaDon = rdbtnTrHng.isSelected() ? LoaiHoaDon.TRA_HANG : LoaiHoaDon.DOI_HANG;
    	        String ngayTao = textField_25.getText();  // Giả sử bạn có ngày tạo
    	        String tienHoan = textField_27.getText();
    	        String lyDo = textField_26.getText();

    	        // Chuyển các giá trị chuỗi thành kiểu dữ liệu thích hợp
    	        LocalDate ngayTaoDate = LocalDate.parse(ngayTao); // Giả sử ngày có định dạng hợp lệ
    	        double tienHoanValue = Double.parseDouble(tienHoan); // Chuyển chuỗi thành double

    	        // Tạo đối tượng HoaDonDoiTra
    	        HoaDonDoiTra hoaDonDoiTra = new HoaDonDoiTra();
    	        hoaDonDoiTra.setMaHoaDonDoiTra(maHoaDonDoiTra);
    	        hoaDonDoiTra.setHoaDonXuat(new HoaDonXuat(maHoaDonXuat));
    	        hoaDonDoiTra.setKhachHang(new KhachHang(maKhachHang));
    	        hoaDonDoiTra.setNhanVien(new NhanVien(maNhanVien));
    	        hoaDonDoiTra.setLoaiHoaDon(loaiHoaDon);
    	        hoaDonDoiTra.setNgayTao(ngayTaoDate);
    	        hoaDonDoiTra.setTienHoan(tienHoanValue);
    	        hoaDonDoiTra.setLyDo(lyDo);

    	        // Gọi phương thức lưu vào DAO
    	        HoaDonDoiTra_DAO hoaDonDoiTraDAO = new HoaDonDoiTra_DAO();
    	        boolean result = hoaDonDoiTraDAO.luuHoaDonDoiTra(hoaDonDoiTra);
    	        if (result) {
    	            JOptionPane.showMessageDialog(null, "Lưu hóa đơn đổi trả thành công!");
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Lỗi khi lưu hóa đơn đổi trả!");
    	        }
    	    }
    	});

      
      btnNewButton_7_1.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        // 1. Xóa tất cả các dòng trong bảng table_5 và table_6
    	        DefaultTableModel model5 = (DefaultTableModel) table_5.getModel();
    	        DefaultTableModel model6 = (DefaultTableModel) table_6.getModel();
    	        model5.setRowCount(0);  // Xóa tất cả các dòng trong table_5
    	        model6.setRowCount(0);  // Xóa tất cả các dòng trong table_6
    	        
    	        // 2. Đặt tất cả các textField từ textField_15 đến textField_27 thành rỗng
    	        textField_15.setText("");
    	        textField_16.setText("");
    	        textField_17.setText("");
    	        textField_18.setText("");
    	        textField_19.setText("");
    	        textField_20.setText("");
    	        textField_21.setText("");
    	        textField_22.setText("");
    	        textField_23.setText("");
    	        textField_24.setText("");
    	        textField_25.setText("");
    	        textField_26.setText("");
    	        textField_27.setText("");
    	        
    	        // 3. Chọn lại rdbtnTrHng
    	        rdbtnTrHng.setSelected(true);  // Đặt radio button rdbtnTrHng thành đã chọn
    	        
    	        DonHoanThanhPane.setVisible(true);
    	        DonTraPane.setVisible(false);
    	    }
    	    
    	});

      
      
      
      
      

    }
  
  	public void giaoDienTable (JTable table) {
  	// Thiết lập font cho table và header
   		Font headerFont = new Font("Leelawadee UI", Font.BOLD, 18); // Chữ to hơn cho header
   		table.getTableHeader().setFont(headerFont); // Áp dụng cho header
   		table.getTableHeader().setPreferredSize(new Dimension(0, 50));
   		table.getTableHeader().setForeground(new Color(255, 255, 255));
   		// Thiết lập màu cho header
           table.getTableHeader().setBackground(new Color(26, 133, 94)); // Màu xanh cho header
   			table.setFont(new Font("Leelawadee UI", Font.PLAIN, 16)); // Font cho các dòng

   		// Thiết lập chiều cao của các dòng
   		table.setRowHeight(30); // Đặt chiều cao dòng lớn hơn
   		// Áp dụng màu xen kẽ cho các dòng
           table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
               public Component getTableCellRendererComponent(JTable table, Object value,
                       boolean isSelected, boolean hasFocus, int row, int column) {
               	Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                   if (row % 2 != 0) {
                       c.setBackground(new Color(26, 133, 94)); // Màu cho dòng chẵn
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
                                           c.setBackground(new Color(32, 210, 139)); // Màu cho dòng chẵn
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
   
           donTamDAO = new DonTam_DAO();
  	}
  	
  private void setMaHD() {
	//Tạo mã hóa đơn
      LocalDate currentDate = LocalDate.now();
      String maHD = hoaDonXuatDAO.taoMaHD(currentDate, maNVDangNhap, khachHang.getMaKhachHang(), "BH");
      hoaDonXuat.setMaHoaDonXuat(maHD);
      textField_2.setText(hoaDonXuat.getMaHoaDonXuat());
  }
private void initializeInvoiceFields() {
	    HoaDonXuat_DAO hoaDonXuatDAO = new HoaDonXuat_DAO();
	    
	    // Set mã hóa đơn tự động
//	    String maHoaDonMoi = hoaDonXuatDAO.generateNewInvoiceCode();
//	    textField_2.setText(maHoaDonMoi);
	    
	    // Set ngày tạo là ngày hiện tại
	 // Định dạng ngày
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    textField_3.setText(LocalDate.now().format(formatter));

	    // Set các trường khác mặc định
	    textField_4.setText("0");
//	    textField_6.setText(100+"");
//	    double tongTien = Double.parseDouble(textField_9.getText());
//	    double tienKhachDua = Double.parseDouble(textField_6.getText());
//	    textField_8.setText(tienKhachDua-tongTien+"");
	}

//Phương thức để tải DonTam vào table_1
private void loadDonTamData() {
    java.util.List<DonTam> donTamList = donTamDAO.getAllDonTam();  // Lấy toàn bộ DonTam từ DAO

    DefaultTableModel model = (DefaultTableModel) table_1.getModel();
    for (DonTam donTam : donTamList) {
        model.addRow(new Object[] {
            donTam.getMaHoaDon(),
            donTam.getTenKhachHang(),
            donTam.getNgayTaoHoaDon().toString()
        });
    }
}
//// Phương thức để thêm chi tiết đơn hàng vào table_2
//private void loadChiTietDonTam(java.util.List<ChiTietDonTam> donHang) {
//    DefaultTableModel model = (DefaultTableModel) table_2.getModel();
//    
//    // Kiểm tra danh sách chi tiết và thêm vào table_2
//    if (donHang != null && !donHang.isEmpty()) {
//        for (ChiTietDonTam chiTiet : donHang) {
//            model.addRow(new Object[] {
//                chiTiet.getMaSanPham(),
//                chiTiet.getTenSanPham(),
//                chiTiet.getSoLuong(),
//                chiTiet.getDonGia()
//            });
//            System.out.println("Đã thêm vào table_2: " + chiTiet.getMaSanPham());
//        }
//    } else {
//        // Trường hợp không có chi tiết đơn hàng
//        System.out.println("Danh sách chi tiết đơn hàng là null hoặc rỗng!");
//    }
//}
    public static void GeneratePdf (String maHD, String maKh) throws IOException {
        String path = maHD+".pdf";

        // Tạo PdfWriter và PdfDocument
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);
        
     // Đường dẫn tới font Tahoma trên hệ thống của bạn
        String fontPath = "./gui/tahoma.ttf";
        
        // Đặt kích thước trang mặc định
        pdfDoc.setDefaultPageSize(PageSize.A4);
        
        // Sử dụng font mặc định (Times Roman)
        PdfFont font = PdfFontFactory.createFont(fontPath);  // Font mặc định sẽ được sử dụng
        
        // Tạo đối tượng Document và thêm nội dung
        Document document = new Document(pdfDoc);
        
     // Tiêu đề hóa đơn
        document.add(new Paragraph("NHÀ THUỐC ABC")
            .setTextAlignment(TextAlignment.CENTER)
            .setBold().setFontSize(20).setFont(font));
        document.add(new Paragraph("CN1: 392A Dương Quảng Hàm, Phường 05, Quận Gò Vấp, Thành phố Hồ Chí Minh")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(13).setFont(font));
        document.add(new Paragraph("CN2: 384 Nguyễn Oanh , Phường 6, Quận Gò Vấp, Thành phố Hồ Chí Minh")
                .setTextAlignment(TextAlignment.CENTER)
               .setFontSize(13).setFont(font));
        document.add(new Paragraph("CN3: 39 Phan Văn Trị, Phường7, Quận Gò Vấp, Thành phố Hồ Chí Minh")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(13).setFont(font));
        document.add(new Paragraph("Liên hệ hotline: 19001080")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(15).setFont(font));
        document.add(new Paragraph("Website: https://ABC.com/")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(15).setFont(font));
        // Tiêu đề hóa đơn
        document.add(new Paragraph("HÓA ĐƠN MUA HÀNG")
            .setTextAlignment(TextAlignment.CENTER)
            .setBold().setFontSize(20).setFont(font));  // Sử dụng font mặc định
        LocalDate currentDate = LocalDate.now();
        document.add(new Paragraph("Ngày: " + currentDate.toString())
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(13).setFont(font));
        HoaDonXuat_DAO hoaDonXuatDAO = new HoaDonXuat_DAO();
        HoaDonXuat HD = hoaDonXuatDAO.findHoaDonById(maHD);
        document.add(new Paragraph("Mã đơn hàng: " + HD.getMaHoaDonXuat())
                .setTextAlignment(TextAlignment.LEFT)
               .setFontSize(13).setFont(font));
        NhanVien_DAO nvdao = new NhanVien_DAO();
        String manv = HD.getNhanVien().getMaNhanVien();
        NhanVien nv = nvdao.layThongTinNhanVienTheoMa(manv);
        KhachHang_DAO khachHangDAO = new KhachHang_DAO();
        KhachHang kh = khachHangDAO.layKhachHangTheoMa(maKh);
        // Thêm thông tin khách hàng
        document.add(new Paragraph("Nhân viên: " + nv.getTenNhanVien()).setFont(font));  // setFont() trên Paragraph
        document.add(new Paragraph("Khách hàng: "+ kh.getTenKhachHang()).setFont(font));  // setFont() trên Paragraph
//        
        // Tạo bảng chi tiết hóa đơn
        float[] pointColumnWidths = {150f, 100f, 100f, 100f};  // Định nghĩa độ rộng cột
        Table table = new Table(pointColumnWidths);
        Double tongtien = 0.0;
        // Thêm tiêu đề bảng
        table.addCell(new Cell().add(new Paragraph("Sản phẩm")).setBold().setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("Số lượng")).setBold().setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("Đơn giá")).setBold().setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("Thành tiền")).setBold().setFont(font));  // setFont() trên Cell
        ArrayList<Object[]> cthd = hoaDonXuatDAO.layDanhSachChiTietSanPhamTheoMaHoaDonXuat2(maHD);
        for (Object[] ct : cthd) {
            // Giả sử ct có cấu trúc: [0] -> tên sản phẩm, [1] -> số lượng, [2] -> đơn giá, [3] -> thành tiền
            String tenSanPham = ct[1].toString();   // Lấy tên sản phẩm
            int soLuong = Integer.parseInt(ct[2].toString());  // Lấy số lượng, giả sử đây là kiểu số nguyên
            double donGia = Double.parseDouble(ct[3].toString());  // Lấy đơn giá, giả sử đây là kiểu số thực
            double thanhTien = Double.parseDouble(ct[5].toString());  // Lấy thành tiền, giả sử đây là kiểu số thực
            tongtien += thanhTien;
            // Thêm các thông tin vào bảng
            table.addCell(new Cell().add(new Paragraph(tenSanPham)).setFont(font));  // Tên sản phẩm
            table.addCell(new Cell().add(new Paragraph(String.valueOf(soLuong))).setFont(font));  // Số lượng
            table.addCell(new Cell().add(new Paragraph(String.format("%.0f VND", donGia))).setFont(font));  // Đơn giá
            table.addCell(new Cell().add(new Paragraph(String.format("%.0f VND", thanhTien))).setFont(font));  // Thành tiền
        }
        
        // Thêm bảng vào tài liệu
        document.add(table);
        // Giảm giá
        String maGG;
        Double trgia = 0.0;
//        maGG = HD.getMaGiamGia().getMaGiamGia();
//        if (maGG!=null) trgia = HD.getMaGiamGia().getTriGia();
//        else trgia = 0.0;
     // Giảm giá
        String giadisc = String.format("%.0f", trgia);  // Định dạng giá trị trgia thành số nguyên không có phần thập phân
        document.add(new Paragraph("Giảm giá: " + giadisc)
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setBold().setFontSize(13).setFont(font));  // setFont() trên Paragraph	

        // Tổng cộng
        String tongcong = String.format("%.0f", (tongtien-trgia));  // Định dạng tổng cộng thành số nguyên không có phần thập phân
        document.add(new Paragraph("Tổng cộng: " + tongcong)
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setBold().setFontSize(16).setFont(font));  // setFont() trên Paragraph

        // Chỉ xuất hóa đơn trong ngày
        document.add(new Paragraph("Chỉ xuất hóa đơn trong ngày")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(13).setFont(font));

        // Đổi trả trong vòng 3 ngày
        document.add(new Paragraph("Đổi trả trong vòng 3 ngày")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(13).setFont(font));


        // Đóng tài liệu
        document.close();
        
        System.out.println("PDF đã được tạo thành công tại: " + path);
    }
 
 // Phương thức làm mới bảng hiển thị đơn tạm
    private void updateDonTamTable() {
        DonTam_DAO donTamDao = new DonTam_DAO();
        java.util.List<DonTam> donTamList = donTamDao.readFromFile();  // Đọc lại dữ liệu từ file JSON

        // Xóa dữ liệu cũ trên bảng
        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
        model.setRowCount(0);

        // Thêm dữ liệu mới vào bảng
        for (DonTam donTam : donTamList) {
            model.addRow(new Object[]{
                donTam.getMaHoaDon(),
                donTam.getSdtKhachHang(),
                donTam.getTenKhachHang(),
                donTam.getNgayTaoHoaDon().toString()
            });
        }
    }
    
    
}