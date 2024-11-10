package gui;

import dao.SanPham_DAO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class TraCuuSanPham_GUI extends JFrame {
    private JTextField txtMaSanPham, txtTenSanPham, txtGiaNhap, txtGiaBan, txtCongDung, 
                       txtThanhPhan, txtBaoQuan, txtChongChiDinh, txtNgaySanXuat, 
                       txtHanSuDung, txtNhaSanXuat, txtThueGTGT, txtGhiChu;
    private JTable bangSanPham;
    private JButton btnTimKiem, btnLamMoi;
    private SanPham_DAO sanPhamDAO;

    public TraCuuSanPham_GUI() {
        // Kết nối database và tạo DAO
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/thuoc", "root", "root");
            sanPhamDAO = new SanPham_DAO(connection);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể kết nối database");
            return;
        }

        setTitle("Tra cứu sản phẩm");
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

        // Panel tìm kiếm
        JPanel timKiemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
        txtMaSanPham = new JTextField(20);
        txtTenSanPham = new JTextField(25);

        JLabel label = new JLabel("Mã sản phẩm");
        timKiemPanel.add(label);
        timKiemPanel.add(txtMaSanPham);
        JLabel label_1 = new JLabel("Tên sản phẩm");
        timKiemPanel.add(label_1);
        timKiemPanel.add(txtTenSanPham);

        btnTimKiem = new JButton("Tìm kiếm");
        timKiemPanel.add(btnTimKiem);

        btnLamMoi = new JButton("Làm Mới");
        timKiemPanel.add(btnLamMoi);

        String[] columns = {"Mã SP", "Tên SP", "Giá nhập", "Giá bán", "SL tồn"};
        bangSanPham = new JTable(new DefaultTableModel(new Object[][]{}, columns));
        JScrollPane bangScrollPane = new JScrollPane(bangSanPham);

        // Panel thông tin sản phẩm
        JPanel thongTinSPPanel = new JPanel();
        thongTinSPPanel.setLayout(new BoxLayout(thongTinSPPanel, BoxLayout.Y_AXIS));
        thongTinSPPanel.setBackground(new Color(220, 220, 220));
        TitledBorder mainBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 77, 77)), "Thông tin sản phẩm");
        thongTinSPPanel.setBorder(mainBorder);

        JPanel basicInfoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        txtGiaNhap = new JTextField(20);
        txtGiaBan = new JTextField(20);

        txtMaSanPham = new JTextField(20);
        txtTenSanPham = new JTextField(20);

        basicInfoPanel.add(new JLabel("Mã sản phẩm:"));
        basicInfoPanel.add(txtMaSanPham);
        basicInfoPanel.add(new JLabel("Tên sản phẩm:"));
        basicInfoPanel.add(txtTenSanPham);
        basicInfoPanel.add(new JLabel("Giá nhập:"));
        basicInfoPanel.add(txtGiaNhap);
        basicInfoPanel.add(new JLabel("Giá bán:"));
        basicInfoPanel.add(txtGiaBan);

        JPanel specPanel = new JPanel(new GridLayout(4, 2, 10, 10));
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

        thongTinSPPanel.add(basicInfoPanel);
        thongTinSPPanel.add(specPanel);

        getContentPane().add(timKiemPanel, BorderLayout.NORTH);
        getContentPane().add(bangScrollPane, BorderLayout.CENTER);
        getContentPane().add(thongTinSPPanel, BorderLayout.EAST);

        // Xử lý sự kiện tìm kiếm
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSP = txtMaSanPham.getText().trim();
                String tenSP = txtTenSanPham.getText().trim();

                // Thực hiện tìm kiếm
                List<String[]> ketQua = sanPhamDAO.timKiemSanPham(maSP, tenSP, null);
                
                if (ketQua.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm nào theo tiêu chí tìm kiếm!");
                    hienThiSanPham(new ArrayList<>()); // Xóa bảng kết quả nếu không có dữ liệu
                } else {
                    hienThiSanPham(ketQua); // Hiển thị sản phẩm nếu có dữ liệu
                }
            }
        });

        // Xử lý sự kiện làm mới
        btnLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaSanPham.setText("");
                txtTenSanPham.setText("");
                txtBaoQuan.setText("");
                txtChongChiDinh.setText("");
                txtThanhPhan.setText("");
                txtGiaBan.setText("");
                txtGiaNhap.setText("");
                txtCongDung.setText("");
                hienThiSanPham(new ArrayList<>()); // Xóa bảng kết quả
            }
        });

        bangSanPham.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = bangSanPham.getSelectedRow();
            if (selectedRow >= 0) {
                String maSanPham = (String) bangSanPham.getValueAt(selectedRow, 0);
                String[] chiTietSanPham = sanPhamDAO.layChiTietSanPham(maSanPham);
                if (chiTietSanPham != null) {
                    hienThiChiTietSanPham(chiTietSanPham);
                }
            }
        });

        setVisible(true);
    }

    private void hienThiSanPham(List<String[]> danhSachSanPham) {
        DefaultTableModel model = (DefaultTableModel) bangSanPham.getModel();
        model.setRowCount(0);
        for (String[] sp : danhSachSanPham) {
            model.addRow(sp);
        }
    }

    private void hienThiChiTietSanPham(String[] chiTietSanPham) {
        txtMaSanPham.setText(chiTietSanPham[0]);
        txtTenSanPham.setText(chiTietSanPham[1]);
        txtGiaNhap.setText(chiTietSanPham[2]);
        txtGiaBan.setText(chiTietSanPham[3]);
        txtCongDung.setText(chiTietSanPham[5]);
        txtThanhPhan.setText(chiTietSanPham[6]);
        txtBaoQuan.setText(chiTietSanPham[7]);
        txtChongChiDinh.setText(chiTietSanPham[8]);
    }

    private JMenu createMenu(String title, String iconPath) {
        JMenu menu = new JMenu(title);
        menu.setHorizontalAlignment(SwingConstants.CENTER);
        menu.setBackground(new Color(26, 133, 94));
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font("Leelawadee UI", Font.BOLD, 28));
        menu.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        menu.setIcon(new ImageIcon(scaledImage));
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
