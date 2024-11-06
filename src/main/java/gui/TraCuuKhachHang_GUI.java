package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TraCuuKhachHang_GUI extends JFrame {

    private JTextField txtMaKhachHang, txtTenKhachHang, txtSDT;
    private JTable bangKhachHang, bangSanPhamDaMua;
    private JButton btnTimKiem;

    public TraCuuKhachHang_GUI() {
        setTitle("Tra Cứu Mua Hàng Khách Hàng");
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

        JMenu mnHome = createMenu(" Trang Chủ", "/gui/house-solid.png");
        JMenu mnManage = createMenu(" Quản Lý", "/gui/list-check-solid.png");
        JMenu mnSales = createMenu(" Bán Hàng", "/gui/cart-shopping-solid.png");
        JMenu mnStats = createMenu(" Thống Kê", "/gui/clipboard-solid.png");
        JMenu mnLookup = createMenu(" Tra Cứu", "/gui/circle-question-solid.png");

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

        // Panel Tìm kiếm Khách Hàng
        JPanel timKiemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
        timKiemPanel.setBackground(new Color(245, 245, 245));

        JLabel lblMaKhachHang = new JLabel("Mã khách hàng");
        lblMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        timKiemPanel.add(lblMaKhachHang);
        txtMaKhachHang = new JTextField(20);
        timKiemPanel.add(txtMaKhachHang);

        JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
        lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        timKiemPanel.add(lblTenKhachHang);
        txtTenKhachHang = new JTextField(25);
        timKiemPanel.add(txtTenKhachHang);

        JLabel lblSDT = new JLabel("Số điện thoại");
        lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
        timKiemPanel.add(lblSDT);
        txtSDT = new JTextField(15);
        timKiemPanel.add(txtSDT);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(new Color(76, 175, 80));
        btnTimKiem.setForeground(Color.WHITE);
        timKiemPanel.add(btnTimKiem);

        // Bảng Khách Hàng
        String[] khachHangColumns = {"Mã KH", "Tên KH", "Số ĐT"};
        Object[][] khachHangData = {};
        bangKhachHang = new JTable(new DefaultTableModel(khachHangData, khachHangColumns));
        bangKhachHang.setRowHeight(30);
        bangKhachHang.setGridColor(new Color(220, 220, 220));
        bangKhachHang.setSelectionBackground(new Color(245, 245, 245));
        
        JTableHeader headerKH = bangKhachHang.getTableHeader();
        headerKH.setBackground(Color.WHITE);
        headerKH.setForeground(new Color(26, 133, 94));
        headerKH.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JScrollPane bangKhachHangScrollPane = new JScrollPane(bangKhachHang);
        bangKhachHangScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(183, 28, 28)), "Danh Sách Khách Hàng"));
        
        // Bảng Sản Phẩm Đã Mua
        String[] sanPhamColumns = {"Mã SP", "Tên SP", "Số lượng", "Ngày mua"};
        Object[][] sanPhamData = {};
        bangSanPhamDaMua = new JTable(new DefaultTableModel(sanPhamData, sanPhamColumns));
        bangSanPhamDaMua.setRowHeight(30);
        bangSanPhamDaMua.setGridColor(new Color(220, 220, 220));
        bangSanPhamDaMua.setSelectionBackground(new Color(245, 245, 245));

        JTableHeader headerSP = bangSanPhamDaMua.getTableHeader();
        headerSP.setBackground(Color.WHITE);
        headerSP.setForeground(new Color(26, 133, 94));
        headerSP.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JScrollPane bangSanPhamScrollPane = new JScrollPane(bangSanPhamDaMua);
        bangSanPhamScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(183, 28, 28)), "Sản Phẩm Đã Mua"));

        // Panel Nút chức năng
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Main Layout
        getContentPane().add(timKiemPanel, BorderLayout.NORTH);
        getContentPane().add(bangKhachHangScrollPane, BorderLayout.WEST);
        getContentPane().add(bangSanPhamScrollPane, BorderLayout.CENTER);
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
        new TraCuuKhachHang_GUI();
    }
}
