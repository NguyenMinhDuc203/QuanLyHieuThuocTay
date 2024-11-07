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
import org.eclipse.wb.swing.FocusTraversalOnArray;

import dao.SanPham_DAO;
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
    private JTable table_8;

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
      		"Mã sản phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá Bán", "Thuế GTGT", "Giảm Giá", "Thành Tiền"
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
      
      JButton btnNewButton_2 = new JButton("Hoàn thành đơn");
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
      scrollPane_3.setBounds(10, 240, 629, 479);
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
      
      JPanel panel_4 = new JPanel();
      panel_4.setBorder(new TitledBorder(null, "Th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      panel_4.setBounds(20, 65, 406, 141);
      DonTraPane.add(panel_4);
      panel_4.setLayout(null);
      
      JLabel lblNewLabel_6 = new JLabel("Mã nhân viên:");
      lblNewLabel_6.setBounds(28, 29, 82, 26);
      panel_4.add(lblNewLabel_6);
      
      textField_15 = new JTextField();
      textField_15.setBounds(122, 29, 257, 26);
      panel_4.add(textField_15);
      textField_15.setColumns(10);
      
      JLabel lblNewLabel_6_1 = new JLabel("Tên nhân viên:");
      lblNewLabel_6_1.setBounds(28, 71, 82, 26);
      panel_4.add(lblNewLabel_6_1);
      
      textField_16 = new JTextField();
      textField_16.setColumns(10);
      textField_16.setBounds(122, 71, 257, 26);
      panel_4.add(textField_16);
      
      JPanel panel_4_1 = new JPanel();
      panel_4_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
      panel_4_1.setBounds(465, 65, 406, 141);
      DonTraPane.add(panel_4_1);
      panel_4_1.setLayout(null);
      
      textField_17 = new JTextField();
      textField_17.setBounds(115, 25, 281, 27);
      panel_4_1.add(textField_17);
      textField_17.setColumns(10);
      
      textField_18 = new JTextField();
      textField_18.setColumns(10);
      textField_18.setBounds(115, 63, 281, 27);
      panel_4_1.add(textField_18);
      
      textField_19 = new JTextField();
      textField_19.setColumns(10);
      textField_19.setBounds(115, 101, 281, 27);
      panel_4_1.add(textField_19);
      
      JLabel lblNewLabel_6_1_1 = new JLabel("Mã khách hàng:");
      lblNewLabel_6_1_1.setBounds(10, 63, 82, 26);
      panel_4_1.add(lblNewLabel_6_1_1);
      
      JLabel lblNewLabel_6_1_2 = new JLabel("Tên khách hàng:");
      lblNewLabel_6_1_2.setBounds(10, 101, 82, 26);
      panel_4_1.add(lblNewLabel_6_1_2);
      
      JPanel panel_4_1_1 = new JPanel();
      panel_4_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin \u0111\u01A1n mua", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
      panel_4_1_1.setBounds(914, 65, 406, 141);
      DonTraPane.add(panel_4_1_1);
      panel_4_1_1.setLayout(null);
      
      JLabel lblNewLabel_6_1_1_1 = new JLabel("Mã đơn hàng:");
      lblNewLabel_6_1_1_1.setBounds(10, 29, 76, 14);
      panel_4_1_1.add(lblNewLabel_6_1_1_1);
      
      textField_20 = new JTextField();
      textField_20.setColumns(10);
      textField_20.setBounds(114, 23, 281, 27);
      panel_4_1_1.add(textField_20);
      
      JLabel lblNewLabel_6_1_1_1_1 = new JLabel("Ngày tạo:");
      lblNewLabel_6_1_1_1_1.setBounds(10, 66, 76, 14);
      panel_4_1_1.add(lblNewLabel_6_1_1_1_1);
      
      textField_21 = new JTextField();
      textField_21.setColumns(10);
      textField_21.setBounds(114, 63, 281, 27);
      panel_4_1_1.add(textField_21);
      
      JLabel lblNewLabel_6_1_1_1_1_1 = new JLabel("Thành tiền:");
      lblNewLabel_6_1_1_1_1_1.setBounds(10, 104, 76, 14);
      panel_4_1_1.add(lblNewLabel_6_1_1_1_1_1);
      
      textField_22 = new JTextField();
      textField_22.setColumns(10);
      textField_22.setBounds(114, 101, 281, 27);
      panel_4_1_1.add(textField_22);
      
      JPanel panel_5 = new JPanel();
      panel_5.setBorder(new TitledBorder(null, "Th\u00F4ng tin \u0111\u01A1n \u0111\u1ED5i tr\u1EA3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      panel_5.setBounds(682, 240, 635, 479);
      DonTraPane.add(panel_5);
      panel_5.setLayout(null);
      
      JLabel lblNewLabel_5 = new JLabel("Mã hóa đơn đổi trả:");
      lblNewLabel_5.setBounds(25, 31, 128, 30);
      panel_5.add(lblNewLabel_5);
      
      textField_23 = new JTextField();
      textField_23.setBounds(214, 31, 396, 30);
      panel_5.add(textField_23);
      textField_23.setColumns(10);
      
      JLabel lblNewLabel_5_1 = new JLabel("Mã nhân viên phụ trách:");
      lblNewLabel_5_1.setBounds(25, 72, 186, 30);
      panel_5.add(lblNewLabel_5_1);
      
      textField_24 = new JTextField();
      textField_24.setColumns(10);
      textField_24.setBounds(214, 72, 396, 30);
      panel_5.add(textField_24);
      
      JLabel lblNewLabel_5_1_1 = new JLabel("Ngày tạo hóa đơn:");
      lblNewLabel_5_1_1.setBounds(25, 113, 186, 30);
      panel_5.add(lblNewLabel_5_1_1);
      
      textField_25 = new JTextField();
      textField_25.setColumns(10);
      textField_25.setBounds(214, 113, 396, 30);
      panel_5.add(textField_25);
      
      JLabel lblNewLabel_5_1_1_1 = new JLabel("Loại:");
      lblNewLabel_5_1_1_1.setBounds(25, 154, 186, 30);
      panel_5.add(lblNewLabel_5_1_1_1);
      
      JRadioButton rdbtnNewRadioButton = new JRadioButton("Đổi hàng");
      rdbtnNewRadioButton.setBounds(217, 158, 109, 23);
      panel_5.add(rdbtnNewRadioButton);
      
      JRadioButton rdbtnTrHng = new JRadioButton("Trả hàng");
      rdbtnTrHng.setBounds(364, 158, 109, 23);
      panel_5.add(rdbtnTrHng);
      
      JLabel lblNewLabel_5_1_1_1_1 = new JLabel("Danh sách sản phẩm:");
      lblNewLabel_5_1_1_1_1.setBounds(25, 195, 186, 30);
      panel_5.add(lblNewLabel_5_1_1_1_1);
      
      JScrollPane scrollPane_4 = new JScrollPane();
      scrollPane_4.setBounds(25, 224, 585, 152);
      panel_5.add(scrollPane_4);
      
      table_6 = new JTable();
      table_6.setModel(new DefaultTableModel(
      	new Object[][] {
      		{null, null, null, null},
      	},
      	new String[] {
      		"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng", "Th\u00E0nh ti\u1EC1n"
      	}
      ));
      scrollPane_4.setViewportView(table_6);
      
      JLabel lblNewLabel_5_1_1_1_1_1 = new JLabel("Lý do:");
      lblNewLabel_5_1_1_1_1_1.setBounds(25, 388, 86, 30);
      panel_5.add(lblNewLabel_5_1_1_1_1_1);
      
      textField_26 = new JTextField();
      textField_26.setColumns(10);
      textField_26.setBounds(111, 387, 499, 30);
      panel_5.add(textField_26);
      
      JLabel lblNewLabel_5_1_1_1_1_1_1 = new JLabel("Tiền hoàn");
      lblNewLabel_5_1_1_1_1_1_1.setBounds(25, 429, 86, 30);
      panel_5.add(lblNewLabel_5_1_1_1_1_1_1);
      
      textField_27 = new JTextField();
      textField_27.setColumns(10);
      textField_27.setBounds(111, 428, 499, 30);
      panel_5.add(textField_27);
      
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
      
      textField_28 = new JTextField();
      textField_28.setBounds(10, 11, 520, 44);
      KhuyenMaiPane.add(textField_28);
      textField_28.setColumns(10);
      
      JButton btnNewButton_8 = new JButton("Tìm kiếm");
      btnNewButton_8.setBounds(540, 11, 170, 44);
      KhuyenMaiPane.add(btnNewButton_8);
      
      JSpinner spinner_3 = new JSpinner();
      spinner_3.setModel(new SpinnerListModel(new String[] {"Lo\u1EA1i", "Ph\u1EA7n tr\u0103m", "Ti\u1EC1n"}));
      spinner_3.setBounds(747, 11, 158, 44);
      KhuyenMaiPane.add(spinner_3);
      
      JSpinner spinner_3_1 = new JSpinner();
      spinner_3_1.setModel(new SpinnerListModel(new String[] {"Tr\u1EA1ng th\u00E1i", "H\u1EBFt h\u1EA1n", "C\u00F2n h\u1EA1n"}));
      spinner_3_1.setBounds(939, 11, 158, 44);
      KhuyenMaiPane.add(spinner_3_1);
      
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
      scrollPane_5.setViewportView(table_7);
      
      JSpinner spinner_3_1_1 = new JSpinner();
      spinner_3_1_1.setModel(new SpinnerListModel(new String[] {"\u0110i\u1EC3m quy \u0111\u1ED5i", "1000", "2000", "3000", "0"}));
      spinner_3_1_1.setBounds(1132, 11, 158, 44);
      KhuyenMaiPane.add(spinner_3_1_1);
      
      JPanel panel_6 = new JPanel();
      panel_6.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin \u00E1p d\u1EE5ng khuy\u1EBFn m\u00E3i", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
      panel_6.setBounds(747, 81, 600, 703);
      KhuyenMaiPane.add(panel_6);
      panel_6.setLayout(null);
      
      JLabel lblNewLabel_7 = new JLabel("Mã khuyến mãi:");
      lblNewLabel_7.setBounds(27, 34, 127, 36);
      panel_6.add(lblNewLabel_7);
      
      textField_29 = new JTextField();
      textField_29.setBounds(167, 34, 400, 36);
      panel_6.add(textField_29);
      textField_29.setColumns(10);
      
      JLabel lblNewLabel_7_1 = new JLabel("Mã hóa đơn:");
      lblNewLabel_7_1.setBounds(27, 84, 127, 36);
      panel_6.add(lblNewLabel_7_1);
      
      textField_30 = new JTextField();
      textField_30.setColumns(10);
      textField_30.setBounds(167, 81, 400, 36);
      panel_6.add(textField_30);
      
      JLabel lblNewLabel_7_1_1 = new JLabel("Mã khách hàng:");
      lblNewLabel_7_1_1.setBounds(27, 131, 127, 36);
      panel_6.add(lblNewLabel_7_1_1);
      
      textField_31 = new JTextField();
      textField_31.setColumns(10);
      textField_31.setBounds(167, 128, 400, 36);
      panel_6.add(textField_31);
      
      textField_32 = new JTextField();
      textField_32.setColumns(10);
      textField_32.setBounds(167, 176, 400, 36);
      panel_6.add(textField_32);
      
      JLabel lblNewLabel_7_1_1_1 = new JLabel("Điểm tích lũy của KH:");
      lblNewLabel_7_1_1_1.setBounds(27, 176, 127, 36);
      panel_6.add(lblNewLabel_7_1_1_1);
      
      JLabel lblNewLabel_7_1_1_1_1 = new JLabel("Điểm cần để đổi mã:");
      lblNewLabel_7_1_1_1_1.setBounds(27, 223, 127, 36);
      panel_6.add(lblNewLabel_7_1_1_1_1);
      
      textField_33 = new JTextField();
      textField_33.setColumns(10);
      textField_33.setBounds(167, 223, 400, 36);
      panel_6.add(textField_33);
      
      JLabel lblNewLabel_7_1_1_1_1_1 = new JLabel("Loại khuyến mãi:");
      lblNewLabel_7_1_1_1_1_1.setBounds(27, 271, 127, 36);
      panel_6.add(lblNewLabel_7_1_1_1_1_1);
      
      JLabel lblNewLabel_7_1_1_1_1_1_1 = new JLabel("Trị giá");
      lblNewLabel_7_1_1_1_1_1_1.setBounds(27, 320, 127, 36);
      panel_6.add(lblNewLabel_7_1_1_1_1_1_1);
      
      JLabel lblNewLabel_7_1_1_1_1_1_1_1 = new JLabel("Giảm được:");
      lblNewLabel_7_1_1_1_1_1_1_1.setBounds(27, 370, 127, 36);
      panel_6.add(lblNewLabel_7_1_1_1_1_1_1_1);
      
      JLabel lblNewLabel_7_1_1_1_1_1_1_2 = new JLabel("Thành tiền sau giảm:");
      lblNewLabel_7_1_1_1_1_1_1_2.setBounds(27, 417, 127, 36);
      panel_6.add(lblNewLabel_7_1_1_1_1_1_1_2);
      
      textField_34 = new JTextField();
      textField_34.setColumns(10);
      textField_34.setBounds(167, 320, 400, 36);
      panel_6.add(textField_34);
      
      textField_35 = new JTextField();
      textField_35.setColumns(10);
      textField_35.setBounds(167, 370, 400, 36);
      panel_6.add(textField_35);
      
      textField_36 = new JTextField();
      textField_36.setColumns(10);
      textField_36.setBounds(167, 417, 400, 36);
      panel_6.add(textField_36);
      
      JButton btnNewButton_9 = new JButton("Áp dụng");
      btnNewButton_9.setBounds(401, 648, 166, 44);
      panel_6.add(btnNewButton_9);
      
      JButton btnNewButton_9_1 = new JButton("Hủy");
      btnNewButton_9_1.setBounds(204, 648, 166, 44);
      panel_6.add(btnNewButton_9_1);
      
      JSpinner spinner_4 = new JSpinner();
      spinner_4.setModel(new SpinnerListModel(new String[] {"Ph\u1EA7n tr\u0103m", "Ti\u1EC1n"}));
      spinner_4.setBounds(167, 270, 132, 37);
      panel_6.add(spinner_4);
      
      JLabel lblNewLabel_7_1_1_1_1_1_2 = new JLabel("Giảm giá trên:");
      lblNewLabel_7_1_1_1_1_1_2.setBounds(327, 270, 98, 36);
      panel_6.add(lblNewLabel_7_1_1_1_1_1_2);
      
      JSpinner spinner_4_1 = new JSpinner();
      spinner_4_1.setModel(new SpinnerListModel(new String[] {"H\u00F3a \u0111\u01A1n", "S\u1EA3n ph\u1EA9m"}));
      spinner_4_1.setBounds(435, 270, 132, 37);
      panel_6.add(spinner_4_1);
      
      JLabel lblNewLabel_7_1_1_1_1_1_1_2_1 = new JLabel("Danh sách sản phẩm có thể áp dụng:");
      lblNewLabel_7_1_1_1_1_1_1_2_1.setBounds(27, 464, 209, 36);
      panel_6.add(lblNewLabel_7_1_1_1_1_1_1_2_1);
      
      JScrollPane scrollPane_6 = new JScrollPane();
      scrollPane_6.setBounds(27, 498, 541, 139);
      panel_6.add(scrollPane_6);
      
      table_8 = new JTable();
      table_8.setModel(new DefaultTableModel(
      	new Object[][] {
      		{null, null},
      	},
      	new String[] {
      		"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m"
      	}
      ));
      scrollPane_6.setViewportView(table_8);

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

                  JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại: " + sanPham.toString());
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
        
    }
}