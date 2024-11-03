package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class BanHang_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_10;
    private JTable table;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1440, 912);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Tạo Menu
        createMenuBar();

        // Tạo phần thông tin hóa đơn
        createInvoiceInfoSection();

        // Tạo các nút chức năng
        createFunctionButtons();

        // Tạo bảng hiển thị sản phẩm
        createProductTableSection();

        // Tạo phần tổng kết
        createSummarySection();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(26, 133, 94));
        menuBar.setBounds(0, 0, 1426, 70);
        contentPane.add(menuBar);

        // Trang Chủ
        JMenu mnNewMenu = new JMenu(" Trang Chủ");
        mnNewMenu.setOpaque(true);
        mnNewMenu.setBackground(new Color(26, 133, 94));
        mnNewMenu.setForeground(Color.WHITE);
        mnNewMenu.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        mnNewMenu.setIcon(loadIcon("/gui/house-solid.png"));
        mnNewMenu.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        menuBar.add(mnNewMenu);

        // Quản Lý
        JMenu mnNewMenu_1 = new JMenu(" Quản Lý");
        mnNewMenu_1.setOpaque(true);
        mnNewMenu_1.setBackground(new Color(26, 133, 94));
        mnNewMenu_1.setForeground(Color.WHITE);
        mnNewMenu_1.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        mnNewMenu_1.setIcon(loadIcon("/gui/list-check-solid.png"));
        mnNewMenu_1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        menuBar.add(mnNewMenu_1);

        // Các mục con của Quản Lý
        addMenuItem(mnNewMenu_1, "Sản Phẩm");
        addMenuItem(mnNewMenu_1, "Nhân Viên");
        addMenuItem(mnNewMenu_1, "Khách Hàng");

        // Bán Hàng
        JMenu mnNewMenu_2_1 = new JMenu(" Bán Hàng");
        mnNewMenu_2_1.setBackground(new Color(26, 133, 94));
        mnNewMenu_2_1.setOpaque(true);
        mnNewMenu_2_1.setForeground(Color.WHITE);
        mnNewMenu_2_1.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        mnNewMenu_2_1.setIcon(loadIcon("/gui/cart-shopping-solid.png"));
        mnNewMenu_2_1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        menuBar.add(mnNewMenu_2_1);

        // Thống Kê
        JMenu mnNewMenu_2 = new JMenu(" Thống Kê");
        mnNewMenu_2.setBackground(new Color(26, 133, 94));
        mnNewMenu_2.setOpaque(true);
        mnNewMenu_2.setForeground(Color.WHITE);
        mnNewMenu_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        mnNewMenu_2.setIcon(loadIcon("/gui/clipboard-solid.png"));
        mnNewMenu_2.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        menuBar.add(mnNewMenu_2);

        // Các mục con của Thống Kê
        addMenuItem(mnNewMenu_2, "Doanh Số");
        addMenuItem(mnNewMenu_2, "Nhân Viên");
        addMenuItem(mnNewMenu_2, "Khách Hàng");
        addMenuItem(mnNewMenu_2, "Sản Phẩm");

        // Tra Cứu
        JMenu mnNewMenu_2_2 = new JMenu(" Tra Cứu   ");
        mnNewMenu_2_2.setBackground(new Color(26, 133, 94));
        mnNewMenu_2_2.setOpaque(true);
        mnNewMenu_2_2.setForeground(Color.WHITE);
        mnNewMenu_2_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        mnNewMenu_2_2.setIcon(loadIcon("/gui/circle-question-solid.png"));
        mnNewMenu_2_2.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        menuBar.add(mnNewMenu_2_2);

        // Các mục con của Tra Cứu
        addMenuItem(mnNewMenu_2_2, "Sản Phẩm");
        addMenuItem(mnNewMenu_2_2, "Nhân Viên");
        addMenuItem(mnNewMenu_2_2, "Hóa Đơn");
        addMenuItem(mnNewMenu_2_2, "Khách Hàng");
    }

    private void addMenuItem(JMenu menu, String title) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setForeground(Color.WHITE);
        menuItem.setBackground(new Color(26, 133, 94));
        menuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        menu.add(menuItem);
    }

    private ImageIcon loadIcon(String path) {
        ImageIcon icon = new ImageIcon(BanHang_GUI.class.getResource(path));
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private void createInvoiceInfoSection() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(210, 166, 545, 208);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNhanVien = new JLabel("Mã Nhân Viên Lập HD:");
        lblNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblNhanVien.setBounds(20, 29, 248, 30);
        panel.add(lblNhanVien);

        textField_2 = new JTextField();
        textField_2.setBounds(244, 35, 277, 30);
        panel.add(textField_2);

        JLabel lblKhachHang = new JLabel("Tên Khách Hàng:");
        lblKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblKhachHang.setBounds(20, 84, 150, 30);
        panel.add(lblKhachHang);

        textField_3 = new JTextField();
        textField_3.setBounds(244, 90, 277, 30);
        panel.add(textField_3);

        JLabel lblDienThoai = new JLabel("Điện Thoại:");
        lblDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblDienThoai.setBounds(23, 149, 150, 30);
        panel.add(lblDienThoai);

        textField_4 = new JTextField();
        textField_4.setBounds(244, 150, 277, 30);
        panel.add(textField_4);

        JLabel lblBanHang = new JLabel("Bán Hàng");
        lblBanHang.setForeground(Color.BLUE);
        lblBanHang.setFont(new Font("Segoe UI", Font.PLAIN, 35));
        lblBanHang.setBounds(138, 83, 240, 50);
        contentPane.add(lblBanHang);
    }

    private void createFunctionButtons() {
        JButton btnInHoaDon = new JButton("In Hóa Đơn");
        btnInHoaDon.setBounds(868, 204, 150, 30);
        btnInHoaDon.setBackground(new Color(173, 216, 230));
        contentPane.add(btnInHoaDon);

        JButton btnTaoHoaDonMoi = new JButton("Tạo Hóa Đơn Mới");
        btnTaoHoaDonMoi.setBounds(868, 260, 150, 30);
        btnTaoHoaDonMoi.setBackground(new Color(173, 216, 230));
        contentPane.add(btnTaoHoaDonMoi);

        JButton btnThoat = new JButton("Thoát");
        btnThoat.setBounds(868, 324, 150, 30);
        btnThoat.setBackground(new Color(173, 216, 230));
        contentPane.add(btnThoat);
    }

    private void createProductTableSection() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(148, 463, 1143, 208);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {1, "Panadol", "Hộp", 2, 50000, 10, 110000},
                {2, "Aspirin", "Viên", 5, 2000, 5, 10500},
                {3, "Vitamin C", "Viên", 10, 1000, 0, 10000},
                {4, "Tylenol", "Hộp", 1, 55000, 10, 60500},
            },
            new String[] {
                "STT", "Tên Sản Phẩm", "Đơn Vị Tính", "Số Lượng", "Đơn Giá", "Thuế GTGT (%)", "Thành Tiền"
            }
        ));
        scrollPane.setViewportView(table);
    }

    private void createSummarySection() {
        JPanel panelFooter = new JPanel();
        panelFooter.setBackground(Color.WHITE);
        panelFooter.setBounds(0, 712, 1426, 133);
        contentPane.add(panelFooter);
        panelFooter.setLayout(null);

        JLabel lblTongTien = new JLabel("Tổng Tiền Thuốc:");
        lblTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblTongTien.setBounds(737, 37, 193, 30);
        panelFooter.add(lblTongTien);

        textField_7 = new JTextField();
        textField_7.setBounds(952, 43, 150, 30);
        panelFooter.add(textField_7);

        JLabel lblTienNhan = new JLabel("Tiền Nhận:");
        lblTienNhan.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblTienNhan.setBounds(298, 37, 150, 30);
        panelFooter.add(lblTienNhan);

        textField_8 = new JTextField();
        textField_8.setBounds(461, 38, 150, 30);
        panelFooter.add(textField_8);

        JLabel lblTienTraLai = new JLabel("Tiền Trả Lại:");
        lblTienTraLai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblTienTraLai.setBounds(298, 96, 150, 30);
        panelFooter.add(lblTienTraLai);

        textField_10 = new JTextField();
        textField_10.setBounds(461, 97, 150, 30);
        panelFooter.add(textField_10);

        JButton btnLuuHoaDon = new JButton("Lưu Hóa Đơn");
        btnLuuHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        btnLuuHoaDon.setBounds(737, 93, 150, 30);
        panelFooter.add(btnLuuHoaDon);
    }
}
