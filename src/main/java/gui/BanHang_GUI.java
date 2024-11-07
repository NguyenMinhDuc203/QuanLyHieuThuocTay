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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
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
      		{null, null, null, null, null, null, null},
      	},
      	new String[] {
      		"M\u00E3 S\u1EA3n Ph\u1EA9m", "T\u00EAn S\u1EA3n Ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng", "Gi\u00E1 B\u00E1n", "Thu\u1EBF GTGT", "Gi\u1EA3m Gi\u00E1", "Th\u00E0nh Ti\u1EC1n"
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
      
      JButton btnNewButton_1_1 = new JButton("Lưu đơn tạm");
      btnNewButton_1_1.setBounds(253, 606, 195, 54);
      BanHangPane.add(btnNewButton_1_1);
      
      JButton btnNewButton_1_2 = new JButton("Khuyến mãi");
      btnNewButton_1_2.setBounds(495, 606, 195, 54);
      BanHangPane.add(btnNewButton_1_2);
      
      JButton btnNewButton_1_3 = new JButton("Hủy");
      btnNewButton_1_3.setBounds(740, 606, 195, 54);
      BanHangPane.add(btnNewButton_1_3);
      
      JButton btnNewButton_2 = new JButton("New button");
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
      
      JPanel DonHoanThanhPane = new JPanel();
      panelContent.add(DonHoanThanhPane, "DonHoanThanhPane"); // Tên để chuyển đổi
      DonHoanThanhPane.setLayout(null);
      
      JPanel DonTraPane = new JPanel();
      panelContent.add(DonTraPane, "DonTraPane"); // Tên để chuyển đổi
      DonTraPane.setLayout(null);

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
        
    }
}