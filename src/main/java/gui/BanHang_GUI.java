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

import dao.SanPham_DAO;
import entity.SanPham;

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
    private DefaultTableModel tableModel;
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
        
	      //Menu
	        trangChu = new TrangChu_GUI();
      		JMenuBar menuBar = trangChu.createMenuBar();
      		menuBar.setBorderPainted(false);
      		menuBar.setOpaque(true);
      		menuBar.setBackground(new Color(26, 133, 94));
      		menuBar.setBounds(0, 0, 1395, 70);
      		contentPane.add(menuBar);
      		
        contentPane.setLayout(null);
        
        // Menu Panel
        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(5, 75, 222, 795);
        menuPanel.setPreferredSize(new Dimension(250, getHeight()));  // Đặt kích thước cho menuPanel

        // Tạo các nút cho menu
        JButton banHangButton = new JButton("Bán hàng");
        banHangButton.setBounds(2, 27, 220, 47);
        JButton donHangButton = new JButton("Đơn hàng");
        donHangButton.setBounds(0, 92, 222, 47);
        JButton khuyenMaiButton = new JButton("Khuyến mãi");
        khuyenMaiButton.setBounds(2, 159, 220, 47);
        menuPanel.setLayout(null);

        // Thêm các nút vào menuPanel
        menuPanel.add(banHangButton);
        menuPanel.add(donHangButton);
        menuPanel.add(khuyenMaiButton);

        // Thiết lập màu nền cho menuPanel (tùy chọn)
        menuPanel.setBackground(new Color(240, 240, 240));

        // Thêm menuPanel vào bên trái của cửa sổ (BorderLayout.WEST)
        contentPane.add(menuPanel);
        
        JPanel panelContent = new JPanel();
        panelContent.setBounds(237, 75, 1357, 795);
        contentPane.add(panelContent);
        panelContent.setLayout(null);
        
        //Trang Bán Hàng
        JPanel BanHangPane = new JPanel();
        BanHangPane.setBounds(0, 0, 1357, 795);
        panelContent.add(BanHangPane);
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
        		"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá Bán", "Thuế GTGT", "Giảm giá", "Thành Tiền"
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
        
        contentPane.add(panelContent);
        
        //TRang Đơn Hàng
        JPanel DonHangPane = new JPanel();
        DonHangPane.setBounds(237, 75, 1357, 795);
        DonHangPane.setLayout(null);
        
      //TRang Khuyến Mãi
        JPanel KhuyenMaiPane = new JPanel();
        KhuyenMaiPane.setBounds(237, 75, 1357, 795);
        KhuyenMaiPane.setLayout(null);

     /// Xử lý sự kiện khi nhấn vào nút menu
        banHangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa các panel cũ trước khi thêm panel mới
                panelContent.removeAll();  // Xóa tất cả component hiện tại trong panelContent
                panelContent.add(BanHangPane);  // Thêm panel Bán hàng vào
                panelContent.revalidate();  // Cập nhật lại layout của panelContent
                panelContent.repaint();  // Vẽ lại panelContent
            }
        });

        donHangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa các panel cũ trước khi thêm panel mới
                panelContent.removeAll();  // Xóa tất cả component hiện tại trong panelContent
                panelContent.add(DonHangPane);  // Thêm panel Đơn hàng vào
                panelContent.revalidate();  // Cập nhật lại layout của panelContent
                panelContent.repaint();  // Vẽ lại panelContent
            }
        });

        khuyenMaiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa các panel cũ trước khi thêm panel mới
                panelContent.removeAll();  // Xóa tất cả component hiện tại trong panelContent
                panelContent.add(KhuyenMaiPane);  // Thêm panel Khuyến mãi vào
                panelContent.revalidate();  // Cập nhật lại layout của panelContent
                panelContent.repaint();  // Vẽ lại panelContent
            }
        });


        // Hiển thị cửa sổ
        setVisible(true);
        
        
        
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maSanPham = txtNhpMSn.getText().trim();
                
                if (maSanPham.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                SanPham_DAO sanPhamDAO = new SanPham_DAO();
                SanPham sanPham = sanPhamDAO.getSanPhamByMaSanPham(maSanPham);
                
                if (sanPham != null) {
                    // Sản phẩm đã tồn tại, lấy thông tin và hiển thị
                    // Ví dụ: Hiển thị thông tin sản phẩm trong bảng hoặc một nơi nào đó
                    JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại: " + sanPham.toString());
                    
                    // Cập nhật bảng hoặc các trường thông tin nếu cần
                    // Ví dụ, thêm sản phẩm vào bảng
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.addRow(new Object[]{
                        sanPham.getMaSanPham(),
                        sanPham.getTenSanPham(),
                        1,  // Số lượng mặc định
                        sanPham.getGiaBan(),
                        sanPham.getThueGTGT(),
                        0,  // Giảm giá mặc định
                        sanPham.getGiaBan()  // Thành tiền mặc định
                    });
                } else {
                    // Sản phẩm không tồn tại
                    JOptionPane.showMessageDialog(null, "Sản phẩm không tồn tại trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        
     // Đặt bảng vào chế độ chọn hàng (mỗi lần chỉ có thể chọn 1 dòng)
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Thêm ListSelectionListener để theo dõi lựa chọn hàng trong bảng
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Kiểm tra nếu có hàng nào được chọn
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Hành động khi hàng được chọn (có thể sử dụng thông tin của dòng này nếu cần)
                        // Ví dụ: Lấy giá trị của cột đầu tiên (Mã Sản Phẩm)
                        String productCode = table.getValueAt(selectedRow, 0).toString();
                        System.out.println("Sản phẩm được chọn: " + productCode);
                    }
                }
            }
        });

        // Thêm sự kiện cho nút Xóa
        btnXa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy chỉ số dòng được chọn
                int selectedRow = table.getSelectedRow();

                // Kiểm tra xem có dòng nào được chọn không
                if (selectedRow != -1) {
                    // Lấy model của bảng
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    
                    // Xóa dòng được chọn
                    model.removeRow(selectedRow);
                } else {
                    // Hiển thị thông báo nếu không có dòng nào được chọn
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


    }
  
  
//  tính thuế gtgt = giá bán * thuế /100
//  tính giảm giá = giá bán * giảm giá /100
  //  tính thành tiền = giá bán + thuế gtgt - giảm giá
  //  tính tổng tiền = thành tiền 1 = thánh tiền 2 + . . . 
 
  
  
  
  
  
  
  
  

//    private JPanel createProductTablePanel() {
//        JPanel productPanel = new JPanel();
//        productPanel.setLayout(null);
//
//        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá bán", "VAT", "Tổng tiền", "Tiền giảm", "Thành tiền"};
//        productTableModel = new DefaultTableModel(columnNames, 0);
//        productTable = new JTable(productTableModel);
//
//        JScrollPane scrollPane = new JScrollPane(productTable);
//        scrollPane.setBounds(0, 0, 1065, 821);
//        productPanel.add(scrollPane);
//
//        return productPanel;
//    }
//
//    private JPanel createInfoPanel() {
//        JPanel infoPanel = new JPanel();
//        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
//
//        // Thông tin khách hàng
//        JPanel customerInfoPanel = new JPanel(new GridLayout(6, 2, 5, 5));
//        customerInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
//
//     // Replace the JCheckBox with a JComboBox for customer type selection
//        String[] customerTypes = {"Vãng lai", "Thành viên", "Mới"};
//        JComboBox<String> customerTypeComboBox = new JComboBox<>(customerTypes);
//
//        phoneField = new JTextField();
//        nameField = new JTextField();
//        nameField.setEditable(false);
//        membershipField = new JTextField();
//        membershipField.setEditable(false);
//
//     // Add a label and the JComboBox for customer type selection to the panel
//        customerInfoPanel.add(new JLabel("Loại khách hàng:"));
//        customerInfoPanel.add(customerTypeComboBox);
//
//        customerInfoPanel.add(new JLabel("Số điện thoại:"));
//        customerInfoPanel.add(phoneField);
//        customerInfoPanel.add(new JLabel("Họ và tên:"));
//        customerInfoPanel.add(nameField);
//        customerInfoPanel.add(new JLabel("Tích điểm"));
//        customerInfoPanel.add(membershipField);
//        infoPanel.add(customerInfoPanel);
//
//        // Thông tin hóa đơn
//     // Thông tin hóa đơn
//        JPanel invoiceInfoPanel = new JPanel(new GridLayout(14, 2, 5, 5));
//        invoiceInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));
//
//        JTextField invoiceIdField = new JTextField("HD111220230004");
//        JTextField dateField = new JTextField("11/12/2023");
//        discountField = new JTextField();
//        totalAmountLabel = new JTextField("0 đ"); // Đã đổi thành JTextField
//        totalAmountLabel.setEditable(false); // Đặt là không thể chỉnh sửa
//        JComboBox<String> paymentMethodCombo = new JComboBox<>(new String[]{"Tiền mặt", "Thẻ tín dụng", "Chuyển khoản"});
//        amountGivenField = new JTextField("0");
//
//        invoiceInfoPanel.add(new JLabel("Mã hóa đơn:"));
//        invoiceInfoPanel.add(invoiceIdField);
//        invoiceInfoPanel.add(new JLabel("Ngày tạo:"));
//        invoiceInfoPanel.add(dateField);
//        invoiceInfoPanel.add(new JLabel("Chiết khấu:"));
//        invoiceInfoPanel.add(discountField);
//        invoiceInfoPanel.add(new JLabel("Khách phải trả:"));
//        invoiceInfoPanel.add(totalAmountLabel); // Đã thêm dưới dạng JTextField
//        invoiceInfoPanel.add(new JLabel("Phương thức:"));
//        invoiceInfoPanel.add(paymentMethodCombo);
//        invoiceInfoPanel.add(new JLabel("Tiền khách đưa:"));
//        invoiceInfoPanel.add(amountGivenField);
//
//        addMoneyButtons(invoiceInfoPanel);
//
//        JScrollPane scrollPane = new JScrollPane(invoiceInfoPanel);
//        scrollPane.setPreferredSize(new Dimension(400, 200));
//        infoPanel.add(scrollPane);
//
//        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
//        JButton saveButton = new JButton("LƯU TẠM");
//        JButton processButton = new JButton("XỬ LÍ ĐƠN TẠM");
//        JButton cancelButton = new JButton("HỦY");
//        JButton discountButton = new JButton("KHUYẾN MÃI");
//        JButton checkoutButton = new JButton("THANH TOÁN");
//
//        buttonPanel.add(saveButton);
//        buttonPanel.add(processButton);
//        buttonPanel.add(cancelButton);
//        buttonPanel.add(discountButton);
//        buttonPanel.add(checkoutButton);
//
//        
//        
//
//        saveButton.addActionListener(e -> saveTemporaryInvoice());
//        processButton.addActionListener(e -> openTemporaryInvoicesDialog());
//
//        cancelButton.addActionListener(e -> {
//            productTableModel.setRowCount(0);
//            phoneField.setText("");
//            nameField.setText("");
//            membershipField.setText("");
//            discountField.setText("");
//            totalAmountLabel.setText("0 đ");
//            amountGivenField.setText("0");
//
//            JOptionPane.showMessageDialog(this, "Hóa đơn đã được hủy.");
//        });
//
//   
//        checkoutButton.addActionListener(e -> {
//            if (productTableModel.getRowCount() == 0) {
//                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào trong hóa đơn. Vui lòng thêm sản phẩm trước khi thanh toán.");
//                return;
//            }
//
//            try {
//                double totalAmount = Double.parseDouble(totalAmountLabel.getText().replace(" đ", ""));
//                double amountGiven = Double.parseDouble(amountGivenField.getText());
//
//                if (amountGiven >= totalAmount) {
//                    double change = amountGiven - totalAmount;
//                    JOptionPane.showMessageDialog(this, "Thanh toán thành công. Tiền thừa: " + change + " đ");
//                    cancelButton.doClick(); // Xóa thông tin hóa đơn sau khi thanh toán thành công
//                } else {
//                    JOptionPane.showMessageDialog(this, "Số tiền khách đưa không đủ. Vui lòng kiểm tra lại.");
//                }
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền hợp lệ.");
//            }
//        });
//
//        infoPanel.add(buttonPanel);
//        return infoPanel;
//    }
//
//    private void addMoneyButtons(JPanel panel) {
//        String[] moneyValues = {"1k", "2k", "5k", "10k", "20k", "50k", "100k", "200k", "500k"};
//        int[] moneyAmounts = {1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000};
//
//        for (int i = 0; i < moneyValues.length; i++) {
//            JButton moneyButton = new JButton(moneyValues[i]);
//            int amount = moneyAmounts[i];
//
//            moneyButton.setPreferredSize(new Dimension(30, 20));
//
//            moneyButton.addActionListener(e -> {
//                try {
//                    double currentAmount = amountGivenField.getText().isEmpty() ? 0 : Double.parseDouble(amountGivenField.getText());
//                    double newAmount = currentAmount + amount;
//                    amountGivenField.setText(String.format("%.0f", newAmount));
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi nhập số tiền.");
//                }
//            });
//
//            panel.add(moneyButton);
//        }
//    }
//
//    private double calculateTotalAmount() {
//        double total = 0.0;
//        for (int i = 0; i < productTableModel.getRowCount(); i++) {
//            total += Double.parseDouble(productTableModel.getValueAt(i, 5).toString());
//        }
//        return total;
//    }
//
//    private void saveTemporaryInvoice() {
//        int confirmation = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn lưu tạm hóa đơn không?", "Xác nhận lưu tạm", JOptionPane.YES_NO_OPTION);
//        if (confirmation == JOptionPane.YES_OPTION) {
//            JOptionPane.showMessageDialog(this, "Lưu tạm thành công vào bảng hóa đơn.");
//        }
//    }
//
//    private void openTemporaryInvoicesDialog() {
//        TemporaryInvoicesDialog dialog = new TemporaryInvoicesDialog(this);
//        dialog.setVisible(true);
//    }
}