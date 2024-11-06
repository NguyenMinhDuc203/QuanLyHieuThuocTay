package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;

public class TraCuuSanPham_GUI extends JFrame {

    private JTextField txtMaSanPham, txtTenSanPham, txtGiaBan, txtCongDung, txtHanSuDung, txtBaoQuan,
                       txtChongChiDinh, txtNgaySanXuat, txtThanhPhan, txtSoLuongTonkho, txtGhiChu,
                       txtNhaSanXuat, txtThueGTGT, txtGiaNhap;
    private JComboBox<String> cbDonViTinh, cbLoaiSanPham, cbHoaDonNhap;
    private JTable bangSanPham;
    private JButton btnTimKiem, btnLoc;

    public TraCuuSanPham_GUI() {
        setTitle("Tra cứu sản phẩm");

        setBounds(0, 0, 1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(26, 133, 94));
        menuBar.setPreferredSize(new Dimension(getWidth(), 70));
        setJMenuBar(menuBar);

        // Create Menus
        JMenu mnHome = createMenu(" Trang Chủ", "/gui/house-solid.png");
        JMenu mnManage = createMenu(" Quản Lý", "/gui/list-check-solid.png");
        JMenu mnSales = createMenu(" Bán Hàng", "/gui/cart-shopping-solid.png");
        JMenu mnStats = createMenu(" Thống Kê", "/gui/clipboard-solid.png");
        JMenu mnLookup = createMenu(" Tra Cứu", "/gui/circle-question-solid.png");

        // Add Menus to Menu Bar
        menuBar.add(mnHome);
        menuBar.add(mnManage);
        menuBar.add(mnSales);
        menuBar.add(mnStats);
        menuBar.add(mnLookup);

        // Add items to Quản Lý menu
        addItemToMenu(mnManage, "Sản Phẩm");
        addItemToMenu(mnManage, "Nhân Viên");
        addItemToMenu(mnManage, "Khách Hàng");

        // Add items to Thống Kê menu
        addItemToMenu(mnStats, "Doanh Số");
        addItemToMenu(mnStats, "Nhân Viên");
        addItemToMenu(mnStats, "Khách Hàng");
        addItemToMenu(mnStats, "Sản Phẩm");

        // Add items to Tra Cứu menu
        addItemToMenu(mnLookup, "Sản Phẩm");
        addItemToMenu(mnLookup, "Nhân Viên");
        addItemToMenu(mnLookup, "Hóa Đơn");
        addItemToMenu(mnLookup, "Khách Hàng");

        JPanel timKiemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));


        txtMaSanPham = new JTextField(20);
        txtTenSanPham = new JTextField(25);

        
        cbLoaiSanPham = new JComboBox<>(new String[]{"Tất Cả", "Thực Phẩm Chức Năng", "Thực Phẩm Bổ Sung","Thuốc","Sản Phẩm Y Tế","Dụng Cụ Y Tế"});
        cbLoaiSanPham.setBackground(new Color(76, 175, 80));
        cbLoaiSanPham.setForeground(Color.WHITE);

      
        btnLoc = new JButton("Lọc");
        btnLoc.setBackground(new Color(76, 175, 80));   
        btnLoc.setForeground(Color.WHITE);             

      
        JLabel label = new JLabel("Mã sản phẩm");
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        timKiemPanel.add(label);
        timKiemPanel.add(txtMaSanPham);
        JLabel label_1 = new JLabel("Tên sản phẩm");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        timKiemPanel.add(label_1);
        timKiemPanel.add(txtTenSanPham);

      
        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(new Color(76, 175, 80));  
        btnTimKiem.setForeground(Color.WHITE);             

       
        timKiemPanel.add(btnTimKiem);
        timKiemPanel.add(cbLoaiSanPham);
        timKiemPanel.add(btnLoc);

        // Bảng Sản phẩm
     // Định nghĩa bảng với các cột và dữ liệu
        String[] columns = {"Mã SP", "Tên SP", "Giá nhập", "Giá bán", "SL tồn"};
        Object[][] data = {};
        bangSanPham = new JTable(new DefaultTableModel(data, columns));

        // Thiết lập nền trắng cho bảng và các dòng xen kẽ
        bangSanPham.setBackground(Color.WHITE);       
        bangSanPham.setOpaque(true);                  
        bangSanPham.setRowHeight(30);               
        bangSanPham.setGridColor(new Color(220, 220, 220)); 
        bangSanPham.setSelectionBackground(new Color(245, 245, 245)); 

        // Tùy chỉnh tiêu đề bảng
        JTableHeader header = bangSanPham.getTableHeader();
        header.setBackground(Color.WHITE);               
        header.setForeground(new Color(26, 133, 94));   
        header.setFont(new Font("Segoe UI", Font.BOLD, 16)); 

        // Đặt bảng vào JScrollPane và thiết lập nền trắng
        JScrollPane bangScrollPane = new JScrollPane(bangSanPham);
        bangScrollPane.getViewport().setBackground(Color.WHITE); 
        bangScrollPane.setBorder(BorderFactory.createEmptyBorder()); 


        // Panel Thông tin sản phẩm (Main Panel)

  
        JPanel thongTinSPPanel = new JPanel();
   
        Color panelBackgroundColor = new Color(220, 220, 220);
        Color titleTextColor = new Color(0, 77, 77);         
        Font titleFont = new Font("Segoe UI", Font.BOLD, 18);  

       
        thongTinSPPanel.setLayout(new BoxLayout(thongTinSPPanel, BoxLayout.Y_AXIS));
        thongTinSPPanel.setBackground(panelBackgroundColor);

       
        TitledBorder mainBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(titleTextColor), "Thông tin sản phẩm");
        mainBorder.setTitleColor(titleTextColor);         mainBorder.setTitleFont(titleFont);       
        thongTinSPPanel.setBorder(mainBorder);


      
        Color sectionBackground = new Color(255, 235, 238); 
        Color titleBorderColor = new Color(183, 28, 28);   

        
        JPanel basicInfoPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        basicInfoPanel.setBackground(SystemColor.window);
        TitledBorder basicInfoBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(titleBorderColor), "Thông tin cơ bản");
        basicInfoBorder.setTitleColor(new Color(183, 28, 28)); 
        basicInfoPanel.setBorder(basicInfoBorder);

        txtMaSanPham = new JTextField(20);
        txtTenSanPham = new JTextField(20);
        txtGiaNhap = new JTextField(20);
        txtGiaBan = new JTextField(20);

        basicInfoPanel.add(new JLabel("Mã sản phẩm:"));
        basicInfoPanel.add(txtMaSanPham);
        basicInfoPanel.add(new JLabel("Tên sản phẩm:"));
        basicInfoPanel.add(txtTenSanPham);
        basicInfoPanel.add(new JLabel("Giá nhập:"));
        basicInfoPanel.add(txtGiaNhap);
        basicInfoPanel.add(new JLabel("Giá bán:"));
        basicInfoPanel.add(txtGiaBan);

        // Section 2: Product Specifications
        JPanel specPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        specPanel.setBackground(SystemColor.window);
        TitledBorder specBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(titleBorderColor), "Đặc tính sản phẩm");
        specBorder.setTitleColor(new Color(183, 28, 28)); 
        specPanel.setBorder(specBorder);

        txtCongDung = new JTextField(20);
        txtThanhPhan = new JTextField(20);
        txtBaoQuan = new JTextField(20);
        txtChongChiDinh = new JTextField(20);

        specPanel.add(new JLabel("Công dụng:"));
        specPanel.add(txtCongDung);
        specPanel.add(new JLabel("Thành phần:"));
        specPanel.add(txtThanhPhan);
        specPanel.add(new JLabel("Bảo quản:"));
        specPanel.add(txtBaoQuan);
        specPanel.add(new JLabel("Chống chỉ định:"));
        specPanel.add(txtChongChiDinh);

        // Section 3: Production & Expiration Info
        JPanel productionPanel = new JPanel(new GridLayout(2, 2, 10, 10));
    
        productionPanel.setBackground(SystemColor.window);
        TitledBorder productionBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(titleBorderColor), "Thông tin sản xuất");
        productionBorder.setTitleColor(new Color(183, 28, 28)); 
        productionPanel.setBorder(productionBorder);


        txtNgaySanXuat = new JTextField(20);
        txtHanSuDung = new JTextField(20);

        productionPanel.add(new JLabel("Ngày sản xuất:"));
        productionPanel.add(txtNgaySanXuat);
        productionPanel.add(new JLabel("Hạn sử dụng:"));
        productionPanel.add(txtHanSuDung);

        // Section 4: Stock & Tax Information
        JPanel stockTaxPanel = new JPanel(new GridLayout(3, 2, 10, 10));
     
        stockTaxPanel.setBackground(SystemColor.window);
        TitledBorder stockTaxBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(titleBorderColor), "Kho & Thuế");
        stockTaxBorder.setTitleColor(new Color(183, 28, 28)); 
        stockTaxPanel.setBorder(stockTaxBorder);


        txtSoLuongTonkho = new JTextField(20);
        txtThueGTGT = new JTextField(20);
        cbDonViTinh = new JComboBox<>(new String[]{"ML", "MG", "VIEN"});

        stockTaxPanel.add(new JLabel("Số lượng tồn kho:"));
        stockTaxPanel.add(txtSoLuongTonkho);
        stockTaxPanel.add(new JLabel("Thuế GTGT:"));
        stockTaxPanel.add(txtThueGTGT);
        stockTaxPanel.add(new JLabel("Đơn vị tính:"));
        stockTaxPanel.add(cbDonViTinh);

        // Section 5: Additional Info
        JPanel additionalPanel = new JPanel(new GridLayout(2, 2, 10, 10));
    
        additionalPanel.setBackground(SystemColor.window);
        TitledBorder additionalBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(titleBorderColor), "Thông tin thêm");
        additionalBorder.setTitleColor(new Color(183, 28, 28)); 
        additionalPanel.setBorder(additionalBorder);


        txtGhiChu = new JTextField(20);
        txtNhaSanXuat = new JTextField(20);

        additionalPanel.add(new JLabel("Ghi chú:"));
        additionalPanel.add(txtGhiChu);
        additionalPanel.add(new JLabel("Nhà sản xuất:"));
        additionalPanel.add(txtNhaSanXuat);

        // Adding all sections to the main panel
        thongTinSPPanel.add(basicInfoPanel);
        thongTinSPPanel.add(specPanel);
        thongTinSPPanel.add(productionPanel);
        thongTinSPPanel.add(stockTaxPanel);
        thongTinSPPanel.add(additionalPanel);

        // Panel Nút chức năng
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Bố trí chính
        getContentPane().add(timKiemPanel, BorderLayout.NORTH);
        getContentPane().add(bangScrollPane, BorderLayout.CENTER);
        getContentPane().add(thongTinSPPanel, BorderLayout.EAST);
        getContentPane().add(actionPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JMenu createMenu(String title, String iconPath) {
        JMenu menu = new JMenu(title);
        menu.setHorizontalAlignment(SwingConstants.CENTER);
        menu.setOpaque(true);
        menu.setBackground(new Color(26, 133, 94));
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font("Leelawadee UI", Font.BOLD, 24));

        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        menu.setIcon(new ImageIcon(scaledImage));
        menu.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        return menu;
    }

    private void addItemToMenu(JMenu menu, String title) {
        JMenuItem item = new JMenuItem(title);
        item.setForeground(Color.WHITE);
        item.setBackground(new Color(26, 133, 94));
        item.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        menu.add(item);
    }

    public static void main(String[] args) {
        new TraCuuSanPham_GUI();
    }
}
