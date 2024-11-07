package gui;

import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class TraCuuKhachHang_GUI extends JFrame {

    private JTextField txtMaKhachHang, txtTenKhachHang, txtSDT;
    private JTable bangKhachHang, bangSanPhamDaMua;
    private JButton btnTimKiem, btnLamMoi;
    private KhachHang_DAO khachHangDAO;
    private SanPham_DAO sanPhamDAO;

    public TraCuuKhachHang_GUI() {
        // Kết nối database
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/thuoc", "root", "root");
            khachHangDAO = new KhachHang_DAO(connection);
            sanPhamDAO = new SanPham_DAO(connection);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể kết nối database");
            return;
        }

        setTitle("Tra Cứu Mua Hàng Khách Hàng");
        setBounds(0, 0, 1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Menu Bar setup
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

        btnLamMoi = new JButton("Làm Mới");
        btnLamMoi.setForeground(Color.WHITE);
        btnLamMoi.setBackground(new Color(76, 175, 80));
        timKiemPanel.add(btnLamMoi);

        // Bảng Khách Hàng
        String[] khachHangColumns = {"Mã KH", "Tên KH", "Số ĐT"};
        bangKhachHang = new JTable(new DefaultTableModel(new Object[][]{}, khachHangColumns));
        bangKhachHang.setRowHeight(30);
        JScrollPane bangKhachHangScrollPane = new JScrollPane(bangKhachHang);
        bangKhachHangScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(183, 28, 28)), "Danh Sách Khách Hàng"));

        // Bảng Sản Phẩm Đã Mua
        String[] sanPhamColumns = {"Mã SP", "Tên SP", "Số lượng", "Ngày mua"};
        bangSanPhamDaMua = new JTable(new DefaultTableModel(new Object[][]{}, sanPhamColumns));
        bangSanPhamDaMua.setRowHeight(30);
        JScrollPane bangSanPhamScrollPane = new JScrollPane(bangSanPhamDaMua);
        bangSanPhamScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(183, 28, 28)), "Sản Phẩm Đã Mua"));

        // Thiết lập header cho bảng Khách Hàng
        bangKhachHang.getTableHeader().setBackground(new Color(76, 175, 80));
        bangKhachHang.getTableHeader().setForeground(Color.WHITE);
        bangKhachHang.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Thiết lập header cho bảng Sản Phẩm Đã Mua
        bangSanPhamDaMua.getTableHeader().setBackground(new Color(76, 175, 80));
        bangSanPhamDaMua.getTableHeader().setForeground(Color.WHITE);
        bangSanPhamDaMua.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Căn giữa nội dung các cột trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < bangKhachHang.getColumnCount(); i++) {
            bangKhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        for (int i = 0; i < bangSanPhamDaMua.getColumnCount(); i++) {
            bangSanPhamDaMua.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Main Layout
        getContentPane().add(timKiemPanel, BorderLayout.NORTH);
        getContentPane().add(bangKhachHangScrollPane, BorderLayout.WEST);
        getContentPane().add(bangSanPhamScrollPane, BorderLayout.CENTER);

        // ActionListener cho nút tìm kiếm
        btnTimKiem.addActionListener(e -> {
            String maKhachHang = txtMaKhachHang.getText().trim();
            String tenKhachHang = txtTenKhachHang.getText().trim();
            String sdt = txtSDT.getText().trim();

            if (!maKhachHang.isEmpty() || !tenKhachHang.isEmpty() || !sdt.isEmpty()) {
                List<String[]> danhSachKhachHang = khachHangDAO.timKiemKhachHang(maKhachHang, tenKhachHang, sdt);
                if (danhSachKhachHang.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng nào!");
                    lamMoi();
                } else {
                    hienThiKhachHang(danhSachKhachHang);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập ít nhất một tiêu chí tìm kiếm!");
            }
        });

        // Lắng nghe sự kiện chọn khách hàng trong bảng
        bangKhachHang.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = bangKhachHang.getSelectedRow();
            if (selectedRow >= 0) {
                String maKhachHang = bangKhachHang.getValueAt(selectedRow, 0).toString();
                List<String[]> danhSachSanPham = sanPhamDAO.laySanPhamDaMua(maKhachHang);
                hienThiSanPham(danhSachSanPham);
            }
        });

        // ActionListener cho nút Làm Mới
        btnLamMoi.addActionListener(e -> lamMoi());

        setVisible(true);
    }

    private void hienThiKhachHang(List<String[]> danhSachKhachHang) {
        DefaultTableModel model = (DefaultTableModel) bangKhachHang.getModel();
        model.setRowCount(0);
        for (String[] kh : danhSachKhachHang) {
            model.addRow(kh);
        }
        bangSanPhamDaMua.clearSelection();
    }

    private void hienThiSanPham(List<String[]> danhSachSanPham) {
        DefaultTableModel model = (DefaultTableModel) bangSanPhamDaMua.getModel();
        model.setRowCount(0);
        for (String[] sp : danhSachSanPham) {
            model.addRow(sp);
        }
    }

    private void lamMoi() {
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtSDT.setText("");
        ((DefaultTableModel) bangKhachHang.getModel()).setRowCount(0);
        ((DefaultTableModel) bangSanPhamDaMua.getModel()).setRowCount(0);
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

    public static void main(String[] args) {
        new TraCuuKhachHang_GUI();
    }
}

