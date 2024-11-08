package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import gui.TrangChu_GUI;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import dao.HoaDonXuat_DAO;
import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import entity.HoaDonXuat;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

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
    private JTextField textField_10;
    private JTable table_3;
    private JTable table_4;
    private JTextField txtLoiKhchHng;
    private JTextField textField_11;
    private JTextField textField_12;
    private JTextField textField_13;
    private JTextField textField_14;
    private JTable table_5;

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
	  trangChu = new TrangChu_GUI();
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
      menuBar.setBounds(0, 0, 1395, 70);
      contentPane.add(menuBar);

      contentPane.setLayout(null);
      // Tạo menu dọc bằng JToolBar
      JToolBar menuToolBar = new JToolBar();
      menuToolBar.setBounds(0, 75, 222, 795);
      menuToolBar.setOrientation(JToolBar.VERTICAL);  // Chuyển menu thành dạng dọc
      menuToolBar.setBackground(new Color(240, 240, 240));
      contentPane.add(menuToolBar);

      // Thêm các mục menu vào JToolBar
      JButton btnBanHang = new JButton("Bán Hàng");
      btnBanHang.setPreferredSize(new Dimension(222, 60)); // Set chiều cao cố định và chiều rộng
      btnBanHang.setMaximumSize(new Dimension(222, 60));  
      btnBanHang.setAlignmentX(Component.LEFT_ALIGNMENT);
      menuToolBar.add(btnBanHang);

      JButton btnDonHang = new JButton("Đơn Hàng");
      btnDonHang.setPreferredSize(new Dimension(222, 60)); // Set chiều cao cố định và chiều rộng
      btnDonHang.setMaximumSize(new Dimension(222, 60));  
      btnDonHang.setAlignmentX(Component.LEFT_ALIGNMENT);
      menuToolBar.add(btnDonHang);

      // Tạo menu con cho "Đơn Hàng"
      JPanel panelDonHang = new JPanel();
      panelDonHang.setLayout(new BoxLayout(panelDonHang, BoxLayout.Y_AXIS));
      panelDonHang.setBackground(new Color(220, 220, 220));
      panelDonHang.setVisible(false); // Ẩn menu con khi mới bắt đầu
      menuToolBar.add(panelDonHang);

      JButton btnDonTam = new JButton("Đơn Tạm");
      btnDonTam.setPreferredSize(new Dimension(200, 45)); // Set chiều cao cố định và chiều rộng
      btnDonTam.setMaximumSize(new Dimension(200, 45));  
      btnDonTam.setAlignmentX(Component.LEFT_ALIGNMENT);
      panelDonHang.add(btnDonTam);

      JButton btnDonHoanThanh = new JButton("Đơn Hoàn Thành");
      btnDonHoanThanh.setPreferredSize(new Dimension(200, 45)); // Set chiều cao cố định và chiều rộng
      btnDonHoanThanh.setMaximumSize(new Dimension(200, 45));  
      btnDonHoanThanh.setAlignmentX(Component.LEFT_ALIGNMENT);
      panelDonHang.add(btnDonHoanThanh);

      JButton btnDonTra = new JButton("Đơn Trả");
      btnDonTra.setPreferredSize(new Dimension(200, 45)); // Set chiều cao cố định và chiều rộng
      btnDonTra.setMaximumSize(new Dimension(200, 45));  
      btnDonTra.setAlignmentX(Component.LEFT_ALIGNMENT);
      panelDonHang.add(btnDonTra);
      
      JButton btnKhuyenMai = new JButton("Khuyến Mãi");
      btnKhuyenMai.setPreferredSize(new Dimension(200, 60)); // Set chiều cao cố định và chiều rộng
      btnKhuyenMai.setMaximumSize(new Dimension(222, 60));  
      btnKhuyenMai.setAlignmentX(Component.LEFT_ALIGNMENT);
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
      panelContent.add(BanHangPane, "BanHangPane"); // Tên để chuyển đổi
      BanHangPane.setLayout(null);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBounds(945, 11, 402, 773);
      BanHangPane.add(panel_1);
      panel_1.setLayout(null);
      
      JPanel panel_2 = new JPanel();
      panel_2.setBorder(new TitledBorder(null, "Th\u00F4ng Tin Kh\u00E1ch H\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      panel_2.setBounds(10, 24, 382, 263);
      panel_1.add(panel_2);
      panel_2.setLayout(null);
      
      JComboBox comboBox = new JComboBox();
      comboBox.setModel(new DefaultComboBoxModel(new String[] {"Khách Vãng Lai", "Thành Viên", "Khách Mới"}));
      comboBox.setBounds(126, 26, 246, 39);
      panel_2.add(comboBox);
      
      JLabel lblNewLabel = new JLabel("Số điện thoại:");
      lblNewLabel.setBounds(24, 87, 98, 39);
      panel_2.add(lblNewLabel);
      
      textField = new JTextField();
      textField.setBounds(126, 87, 246, 39);
      panel_2.add(textField);
      textField.setColumns(10);
      
      JLabel lblHTn = new JLabel("Họ tên");
      lblHTn.setBounds(24, 150, 98, 39);
      panel_2.add(lblHTn);
      
      textField_1 = new JTextField();
      textField_1.setColumns(10);
      textField_1.setBounds(126, 150, 246, 39);
      panel_2.add(textField_1);
      
      JCheckBox chckbxNewCheckBox = new JCheckBox("Sử dụng mã giảm giá");
      chckbxNewCheckBox.setBounds(125, 205, 251, 39);
      panel_2.add(chckbxNewCheckBox);
      
      JPanel panel = new JPanel();
      panel.setBorder(new TitledBorder(null, "Th\u00F4ng Tin H\u00F3a \u0110\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      panel.setBounds(10, 316, 382, 446);
      panel_1.add(panel);
      panel.setLayout(null);
      
      JLabel lblNewLabel_1 = new JLabel("Mã hóa đơn:");
      lblNewLabel_1.setBounds(10, 25, 105, 35);
      panel.add(lblNewLabel_1);
      
      textField_2 = new JTextField();
      textField_2.setEditable(false);
      textField_2.setBounds(135, 25, 237, 35);
      panel.add(textField_2);
      textField_2.setColumns(10);
      
      JLabel lblNewLabel_1_1 = new JLabel("Ngày tạo:");
      lblNewLabel_1_1.setBounds(10, 80, 105, 35);
      panel.add(lblNewLabel_1_1);
      
      JLabel lblNewLabel_1_2 = new JLabel("Giảm giá");
      lblNewLabel_1_2.setBounds(10, 137, 105, 35);
      panel.add(lblNewLabel_1_2);
      
      JLabel lblNewLabel_1_3 = new JLabel("Tiền khách trả:");
      lblNewLabel_1_3.setBounds(10, 190, 105, 35);
      panel.add(lblNewLabel_1_3);
      
      textField_3 = new JTextField();
      textField_3.setEditable(false);
      textField_3.setColumns(10);
      textField_3.setBounds(135, 80, 237, 35);
      panel.add(textField_3);
      
      textField_4 = new JTextField();
      textField_4.setEditable(false);
      textField_4.setColumns(10);
      textField_4.setBounds(135, 137, 237, 35);
      panel.add(textField_4);
      
      textField_5 = new JTextField();
      textField_5.setEditable(false);
      textField_5.setColumns(10);
      textField_5.setBounds(135, 190, 237, 35);
      panel.add(textField_5);
      
      JSpinner spinner = new JSpinner();
      spinner.setModel(new SpinnerListModel(new String[] {"Ti\u1EC1n m\u1EB7t", "ATM"}));
      spinner.setBounds(135, 247, 237, 35);
      panel.add(spinner);
      
      JLabel lblNewLabel_1_3_1 = new JLabel("Phương thức:");
      lblNewLabel_1_3_1.setBounds(10, 247, 105, 35);
      panel.add(lblNewLabel_1_3_1);
      
      JLabel lblNewLabel_2 = new JLabel("Tiền khách đưa");
      lblNewLabel_2.setBounds(10, 306, 79, 27);
      panel.add(lblNewLabel_2);
      
      textField_6 = new JTextField();
      textField_6.setColumns(10);
      textField_6.setBounds(135, 302, 154, 35);
      panel.add(textField_6);
      
      textField_7 = new JTextField();
      textField_7.setText("000");
      textField_7.setEditable(false);
      textField_7.setColumns(10);
      textField_7.setBounds(293, 302, 79, 35);
      panel.add(textField_7);
      
      JLabel lblNewLabel_2_1 = new JLabel("Tiền thối");
      lblNewLabel_2_1.setBounds(10, 364, 79, 27);
      panel.add(lblNewLabel_2_1);
      
      textField_8 = new JTextField();
      textField_8.setText("000");
      textField_8.setEditable(false);
      textField_8.setColumns(10);
      textField_8.setBounds(134, 360, 238, 35);
      panel.add(textField_8);
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(10, 62, 925, 449);
      BanHangPane.add(scrollPane);
      
      table = new JTable();
      table.setModel(new DefaultTableModel(
      	new Object[][] {
      	},
      	new String[] {
      		"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn S\u1EA3n Ph\u1EA9m", "S\u1ED1 L\u01B0\u1EE3ng", "Gi\u00E1 B\u00E1n", "Thu\u1EBF GTGT", "Gi\u1EA3m Gi\u00E1", "Th\u00E0nh Ti\u1EC1n"
      	}
      ) {
      	boolean[] columnEditables = new boolean[] {
      		false, false, true, true, true, true, true
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
      scrollPane.setViewportView(table);
      
      txtNhpMSn = new JTextField();
      txtNhpMSn.setText("Nhập mã sản phẩm");
      txtNhpMSn.setBounds(10, 11, 565, 40);
      BanHangPane.add(txtNhpMSn);
      txtNhpMSn.setColumns(10);
      
      JButton btnNewButton = new JButton("Thêm");
      btnNewButton.setBounds(617, 11, 140, 40);
      BanHangPane.add(btnNewButton);
      
      JButton btnXa = new JButton("Xóa");
      btnXa.setBounds(795, 11, 140, 40);
      BanHangPane.add(btnXa);
      
      JButton btnNewButton_1 = new JButton("Xử lý đơn tạm");
      btnNewButton_1.setBounds(10, 606, 195, 54);
      BanHangPane.add(btnNewButton_1);
   // Thêm sự kiện cho các nút chuyển panel
      btnNewButton_1.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "DonTamPane"); // Chuyển sang trang Bán Hàng
      });
      
      JButton btnNewButton_1_1 = new JButton("Lưu đơn tạm");
      btnNewButton_1_1.setBounds(253, 606, 195, 54);
      BanHangPane.add(btnNewButton_1_1);
      
      JButton btnNewButton_1_2 = new JButton("Khuyến mãi");
      btnNewButton_1_2.setBounds(495, 606, 195, 54);
      BanHangPane.add(btnNewButton_1_2);
   // Thêm sự kiện cho các nút chuyển panel
      btnNewButton_1_2.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "KhuyenMaiPane"); // Chuyển sang trang Bán Hàng
      });
      
      JButton btnNewButton_1_3 = new JButton("Hủy");
      btnNewButton_1_3.setBounds(740, 606, 195, 54);
      BanHangPane.add(btnNewButton_1_3);
      
      JButton btnNewButton_2 = new JButton("New button");
      btnNewButton_2.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      btnNewButton_2.setBounds(339, 724, 261, 54);
      BanHangPane.add(btnNewButton_2);
      
      JLabel lblNewLabel_3 = new JLabel("Tổng tiền:");
      lblNewLabel_3.setBounds(604, 522, 153, 40);
      BanHangPane.add(lblNewLabel_3);
      
      textField_9 = new JTextField();
      textField_9.setEditable(false);
      textField_9.setBounds(767, 522, 168, 40);
      BanHangPane.add(textField_9);
      textField_9.setColumns(10);

      // Trang Đơn Hàng
      JPanel DonTamPane = new JPanel();
      panelContent.add(DonTamPane, "DonTamPane"); // Tên để chuyển đổi
      DonTamPane.setLayout(null);
      
      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(10, 65, 558, 528);
      DonTamPane.add(scrollPane_1);
      
      table_1 = new JTable();
      table_1.setModel(new DefaultTableModel(
      	new Object[][] {
      		{null, null, null},
      	},
      	new String[] {
      		"M\u00E3 H\u00F3a \u0110\u01A1n", "T\u00EAn Kh\u00E1ch H\u00E0ng", "Ng\u00E0y T\u1EA1o H\u00F3a \u0110\u01A1n"
      	}
      ));
      scrollPane_1.setViewportView(table_1);
      
      JScrollPane scrollPane_1_1 = new JScrollPane();
      scrollPane_1_1.setBounds(607, 11, 740, 671);
      DonTamPane.add(scrollPane_1_1);
      
      table_2 = new JTable();
      table_2.setModel(new DefaultTableModel(
      	new Object[][] {
      		{null, null, null, null},
      	},
      	new String[] {
      		"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1"
      	}
      ));
      scrollPane_1_1.setViewportView(table_2);
      
      txtMHan = new JTextField();
      txtMHan.setForeground(new Color(192, 192, 192));
      txtMHan.setText("Mã hóa đơn");
      txtMHan.setBounds(147, 11, 276, 43);
      DonTamPane.add(txtMHan);
      txtMHan.setColumns(10);
      
      JButton btnNewButton_3 = new JButton("TÌm kiếm");
      btnNewButton_3.setBounds(445, 11, 123, 43);
      DonTamPane.add(btnNewButton_3);
      
      JButton btnNewButton_4 = new JButton("Xóa tát cả đơn");
      btnNewButton_4.setBounds(10, 633, 156, 49);
      DonTamPane.add(btnNewButton_4);
      
      JButton btnNewButton_4_1 = new JButton("Xóa đơn tạm");
      btnNewButton_4_1.setBounds(212, 633, 156, 49);
      DonTamPane.add(btnNewButton_4_1);
      
      JButton btnNewButton_4_2 = new JButton("Xử lí đơn");
      btnNewButton_4_2.setBounds(412, 633, 156, 49);
      DonTamPane.add(btnNewButton_4_2);
      
      JSpinner spinner_1 = new JSpinner();
      spinner_1.setModel(new SpinnerListModel(new String[] {"M\u00E3 h\u00F3a \u0111\u01A1n", "T\u00EAn kh\u00E1ch h\u00E0ng", "Ng\u00E0y t\u1EA1o h\u00F3a \u0111\u01A1n"}));
      spinner_1.setBounds(10, 11, 127, 43);
      DonTamPane.add(spinner_1);
      
      
      
      JPanel DonHoanThanhPane = new JPanel();
      panelContent.add(DonHoanThanhPane, "DonHoanThanhPane"); // Tên để chuyển đổi
      DonHoanThanhPane.setLayout(null);
      
      JScrollPane scrollPane_2 = new JScrollPane();
      scrollPane_2.setBounds(30, 73, 659, 584);
      DonHoanThanhPane.add(scrollPane_2);
      
      table_3 = new JTable();
      table_3.setModel(new DefaultTableModel(
      	new Object[][] {
      		{null, null, null, null, null},
      	},
      	new String[] {
      		"M\u00E3 h\u00F3a \u0111\u01A1n", "M\u00E3 nh\u00E2n vi\u00EAn", "M\u00E3 kh\u00E1ch h\u00E0ng", "Ng\u00E0y mua", "Th\u00E0nh ti\u1EC1n"
      	}
      ));
      scrollPane_2.setViewportView(table_3);
      
      JScrollPane scrollPane_2_1 = new JScrollPane();
      scrollPane_2_1.setBounds(699, 11, 648, 459);
      DonHoanThanhPane.add(scrollPane_2_1);
      
      table_4 = new JTable();
      table_4.setModel(new DefaultTableModel(
      	new Object[][] {
      		{null, null, "", null, null, null},
      	},
      	new String[] {
      		"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1", "Thu\u1EBF GTGT", "Th\u00E0nh Ti\u1EC1n"
      	}
      ));
      scrollPane_2_1.setViewportView(table_4);
      
      JPanel panel_3 = new JPanel();
      panel_3.setBorder(new TitledBorder(null, "Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      panel_3.setBounds(699, 497, 648, 273);
      DonHoanThanhPane.add(panel_3);
      panel_3.setLayout(null);
      
      txtLoiKhchHng = new JTextField();
      txtLoiKhchHng.setText("Loại khách hàng");
      txtLoiKhchHng.setBounds(119, 22, 508, 41);
      panel_3.add(txtLoiKhchHng);
      txtLoiKhchHng.setColumns(10);
      
      JLabel lblNewLabel_4 = new JLabel("Mã khách hàng");
      lblNewLabel_4.setBounds(20, 79, 151, 41);
      panel_3.add(lblNewLabel_4);
      
      JLabel lblNewLabel_4_1 = new JLabel("Tên khách hàng");
      lblNewLabel_4_1.setBounds(20, 147, 151, 41);
      panel_3.add(lblNewLabel_4_1);
      
      JLabel lblNewLabel_4_2 = new JLabel("Tiền khách đưa");
      lblNewLabel_4_2.setBounds(20, 210, 89, 41);
      panel_3.add(lblNewLabel_4_2);
      
      textField_11 = new JTextField();
      textField_11.setText("Loại khách hàng");
      textField_11.setColumns(10);
      textField_11.setBounds(119, 74, 508, 41);
      panel_3.add(textField_11);
      
      textField_12 = new JTextField();
      textField_12.setColumns(10);
      textField_12.setBounds(119, 147, 508, 41);
      panel_3.add(textField_12);
      
      textField_13 = new JTextField();
      textField_13.setColumns(10);
      textField_13.setBounds(119, 210, 161, 41);
      panel_3.add(textField_13);
      
      JLabel lblNewLabel_4_2_1 = new JLabel("Tiền trả lại khách");
      lblNewLabel_4_2_1.setBounds(348, 210, 89, 41);
      panel_3.add(lblNewLabel_4_2_1);
      
      textField_14 = new JTextField();
      textField_14.setText("Loại khách hàng");
      textField_14.setColumns(10);
      textField_14.setBounds(466, 210, 161, 41);
      panel_3.add(textField_14);
      
      JSpinner spinner_2 = new JSpinner();
      spinner_2.setModel(new SpinnerListModel(new String[] {"M\u00E3 h\u00F3a \u0111\u01A1n", "M\u00E3 nh\u00E2n vi\u00EAn", "M\u00E3 Kh\u00E1ch h\u00E0ng", "Ng\u00E0y mua", "Th\u00E0nh ti\u1EC1n"}));
      spinner_2.setBounds(30, 11, 157, 51);
      DonHoanThanhPane.add(spinner_2);
      
      textField_10 = new JTextField();
      textField_10.setBounds(195, 11, 345, 51);
      DonHoanThanhPane.add(textField_10);
      textField_10.setColumns(10);
      
      JButton btnNewButton_5 = new JButton("Tìm kiếm");
      btnNewButton_5.setBounds(550, 11, 124, 51);
      DonHoanThanhPane.add(btnNewButton_5);
      
      JButton btnNewButton_6 = new JButton("Xem bản in");
      btnNewButton_6.setBounds(30, 678, 145, 43);
      DonHoanThanhPane.add(btnNewButton_6);
      
      JButton btnNewButton_6_1 = new JButton("Xuất hóa đơn");
      btnNewButton_6_1.setBounds(205, 678, 145, 43);
      DonHoanThanhPane.add(btnNewButton_6_1);
      
      JButton btnNewButton_6_2 = new JButton("Trả hàng");
      btnNewButton_6_2.setBounds(544, 678, 145, 43);
      DonHoanThanhPane.add(btnNewButton_6_2);
      
      JButton btnNewButton_6_2_1 = new JButton("Đổi hàng");
      btnNewButton_6_2_1.setBounds(375, 678, 145, 43);
      DonHoanThanhPane.add(btnNewButton_6_2_1);
      
      JPanel DonTraPane = new JPanel();
      panelContent.add(DonTraPane, "DonTraPane"); // Tên để chuyển đổi
      DonTraPane.setLayout(null);
      
      JScrollPane scrollPane_3 = new JScrollPane();
      scrollPane_3.setBounds(23, 240, 629, 479);
      DonTraPane.add(scrollPane_3);
      
      table_5 = new JTable();
      table_5.setModel(new DefaultTableModel(
      	new Object[][] {
      		{null, null, null, null, null, null, null},
      	},
      	new String[] {
      		"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1", "Thu\u1EBF", "Gi\u1EA3m gi\u00E1", "Th\u00E0nh ti\u1EC1n"
      	}
      ));
      scrollPane_3.setViewportView(table_5);
      
      JLabel lblNewLabel_5 = new JLabel("ĐỔI HÀNG");
      lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 22));
      lblNewLabel_5.setBounds(10, 11, 311, 57);
      DonTraPane.add(lblNewLabel_5);
      
      JPanel panel_4 = new JPanel();
      panel_4.setBorder(new TitledBorder(null, "Th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      panel_4.setBounds(20, 65, 406, 141);
      DonTraPane.add(panel_4);
      
      JPanel panel_4_1 = new JPanel();
      panel_4_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
      panel_4_1.setBounds(465, 65, 406, 141);
      DonTraPane.add(panel_4_1);
      
      JPanel panel_4_1_1 = new JPanel();
      panel_4_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin \u0111\u01A1n mua", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
      panel_4_1_1.setBounds(914, 65, 406, 141);
      DonTraPane.add(panel_4_1_1);
      
      JPanel panel_5 = new JPanel();
      panel_5.setBorder(new TitledBorder(null, "Th\u00F4ng tin \u0111\u01A1n \u0111\u1ED5i tr\u1EA3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      panel_5.setBounds(682, 240, 635, 479);
      DonTraPane.add(panel_5);
      
      JButton btnNewButton_7 = new JButton("Tạo hóa đơn đổi trả");
      btnNewButton_7.setBounds(782, 730, 206, 43);
      DonTraPane.add(btnNewButton_7);
      
      JButton btnNewButton_7_1 = new JButton("Hủy đổi trả");
      btnNewButton_7_1.setBounds(1017, 730, 206, 43);
      DonTraPane.add(btnNewButton_7_1);

      // Trang Khuyến Mãi
      JPanel KhuyenMaiPane = new JPanel();
      panelContent.add(KhuyenMaiPane, "KhuyenMaiPane"); // Tên để chuyển đổi
      KhuyenMaiPane.setLayout(null);

      // Thêm sự kiện cho các nút chuyển panel
      btnBanHang.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "BanHangPane"); // Chuyển sang trang Bán Hàng
      });

      btnDonTam.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "DonTamPane"); // Chuyển sang trang Đơn Hàng
      });
      
      btnDonHoanThanh.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "DonHoanThanhPane"); // Chuyển sang trang Đơn Hàng
      });
      
      btnDonTra.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "DonTraPane"); // Chuyển sang trang Đơn Hàng
      });

      btnKhuyenMai.addActionListener(e -> {
          CardLayout cardLayout = (CardLayout) panelContent.getLayout();
          cardLayout.show(panelContent, "KhuyenMaiPane"); // Chuyển sang trang Khuyến Mãi
      });

      // Hiển thị cửa sổ
      setVisible(true);
      
      
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
                  double giaBan = sanPham.getGiaBan();
                  double thueGTGT = giaBan * sanPham.getThueGTGT() / 100;
                  double giamGia = giaBan * 0 / 100; // Giả sử không có giảm giá
                  double thanhTien = giaBan + thueGTGT - giamGia;

                  DefaultTableModel model = (DefaultTableModel) table.getModel();
                  model.addRow(new Object[]{
                      sanPham.getMaSanPham(),
                      sanPham.getTenSanPham(),
                      1,  // Số lượng mặc định là 1
                      giaBan,
                      thueGTGT,
                      giamGia,
                      thanhTien
                  });

                  // Tính và cập nhật tổng thành tiền
                  double tongThanhTien = 0.0;
                  for (int i = 0; i < model.getRowCount(); i++) {
                      double thanhTienRow = (double) model.getValueAt(i, 6); // Cột "Thành Tiền"
                      tongThanhTien += thanhTienRow;
                  }

                  textField_9.setText(String.format("%.2f", tongThanhTien));
                  double tongTien = Double.parseDouble(textField_9.getText());
          	    textField_5.setText(0+tongTien+"");
                  JOptionPane.showMessageDialog(null, "Đã thêm thành công sản phẩm vào đơn hàng ");
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

                  textField_9.setText(String.format("%.2f", tongThanhTien));
              } else {
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
              }
          }
      });
      btnNewButton_2.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              // Lấy thông tin từ các trường nhập liệu
              String soDienThoai = textField.getText();
              String hoTen = textField_1.getText();
//              String loaiKhach = (String) comboBox.getSelectedItem();
//              boolean suDungMaGiamGia = chckbxNewCheckBox.isSelected();

              // Kiểm tra nếu có thông tin trống
              if (soDienThoai.isEmpty() || hoTen.isEmpty()) {
                  JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
                  return;
              }

              // Tạo đối tượng khách hàng mới
              KhachHang khachHang = new KhachHang();
              khachHang.setSDT(soDienThoai);
              khachHang.setTenKhachHang(hoTen);
              khachHang.setMaKhachHang("KH"+soDienThoai);
              // Lưu khách hàng vào cơ sở dữ liệu (DAO)
              KhachHang_DAO khachHangDAO = new KhachHang_DAO();
              boolean isSaved = khachHangDAO.save(khachHang);

              // Kiểm tra kết quả lưu
              if (isSaved) {
                  JOptionPane.showMessageDialog(null, "Thông tin khách hàng đã được lưu!");
                  // Cập nhật giao diện nếu cần
              } else {
                  JOptionPane.showMessageDialog(null, "Lỗi khi lưu thông tin khách hàng.");
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
              double tongTien = Double.parseDouble(textField_9.getText());
              double tienThoi = Double.parseDouble(textField_8.getText());
              String maKhachHang = "KH"+textField.getText();
              NhanVien nhanVien = new NhanVien();
              nhanVien.setMaNhanVien("NVBH103202074405");
              KhachHang khachHang = new KhachHang();
              khachHang.setMaKhachHang(maKhachHang);
              // Bước 2: Tạo đối tượng HoaDonXuat
              HoaDonXuat hoaDon = new HoaDonXuat();
              hoaDon.setMaHoaDonXuat(maHoaDon);
              hoaDon.setNgayTao(ngayTao);
              hoaDon.setTienKhachDua(tongTien);
              hoaDon.setTienThoi(tienThoi);
              hoaDon.setNhanVien(nhanVien);
              hoaDon.setKhachHang(khachHang);
              // Bước 3: Gọi DAO để lưu đối tượng vào database
              HoaDonXuat_DAO hoaDonDAO = new HoaDonXuat_DAO();
              hoaDonDAO.luuHoaDonXuat(hoaDon);  // Phương thức lưu vào database trong DAO

              JOptionPane.showMessageDialog(null, "Lưu hóa đơn thành công!");
          } catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "Lỗi khi lưu hóa đơn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
          }
      });

      textField_6.addFocusListener(new FocusAdapter() {
    	    @Override
    	    public void focusLost(FocusEvent e) {
    	        try {
    	            // Lấy giá trị từ textField_9 và textField_6
    	            double tongTien = Double.parseDouble(textField_9.getText());
    	            double tienKhachDua = Double.parseDouble(textField_6.getText());
    	            
    	            // Thực hiện phép tính
    	            double tienThoiLai = tienKhachDua - tongTien;
    	            
    	            // Gán kết quả vào textField_8
    	            textField_8.setText(String.valueOf(tienThoiLai));
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
    	    textField_1.setText("");

    	    // Xóa tiền khách đưa (ví dụ: textField_6 là tiền khách đưa)
    	    textField_6.setText("");
    	});

        
      initComponents();
      initializeInvoiceFields();
    }
  protected void initComponents() {
	// TODO Auto-generated method stub
	
}

private void initializeInvoiceFields() {
	    HoaDonXuat_DAO hoaDonXuatDAO = new HoaDonXuat_DAO();
	    
	    // Set mã hóa đơn tự động
	    String maHoaDonMoi = hoaDonXuatDAO.generateNewInvoiceCode();
	    textField_2.setText(maHoaDonMoi);
	    
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

 

}