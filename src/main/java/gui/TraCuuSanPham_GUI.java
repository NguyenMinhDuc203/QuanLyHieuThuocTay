
package gui;
import dao.SanPham_DAO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
    private JTextField txtmasp;
    private JTextField txtsanpham;
    private JTable bangSanPham;
    private JButton btnTimKiem, btnLamMoi;
    private SanPham_DAO sanPhamDAO;

    public TraCuuSanPham_GUI() {
        getContentPane().setBackground(new Color(244, 253, 253)); // White background
        // Connect to database and initialize DAO
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

        // Menu Bar setup
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(26, 133, 94)); // Dark green menu bar
        menuBar.setPreferredSize(new Dimension(getWidth(), 70));
        setJMenuBar(menuBar);

        // Create menus
        JMenu mnHome = createMenu("Trang Chủ", "/gui/house-solid.png");
        JMenu mnManage = createMenu("Quản Lý", "/gui/list-check-solid.png");
        JMenu mnSales = createMenu("Bán Hàng", "/gui/cart-shopping-solid.png");
        JMenu mnStats = createMenu("Thống Kê", "/gui/clipboard-solid.png");
        JMenu mnLookup = createMenu("Tra Cứu", "/gui/circle-question-solid.png");

        menuBar.add(mnHome);
        menuBar.add(mnManage);
        menuBar.add(mnSales);
        menuBar.add(mnStats);
        menuBar.add(mnLookup);

        // Panel tìm kiếm (Search Panel)
        JPanel timKiemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
        timKiemPanel.setBackground(new Color(244, 253, 253)); // White background
        timKiemPanel.setBounds(42, 75, 1822, 63);
        txtmasp = new JTextField(20);
        txtmasp.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtsanpham = new JTextField(25);
        txtsanpham.setFont(new Font("Tahoma", Font.PLAIN, 20));

        // Labels
        JLabel label = new JLabel("Mã sản phẩm");
        label.setForeground(new Color(0, 128, 64)); // Red text color
        label.setFont(new Font("Tahoma", Font.PLAIN, 34));
        timKiemPanel.add(label);
        timKiemPanel.add(txtmasp);

        JLabel label_1 = new JLabel("Tên sản phẩm");
        label_1.setForeground(new Color(0, 128, 64)); // Red text color
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
        timKiemPanel.add(label_1);
        timKiemPanel.add(txtsanpham);

        // Buttons
        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnTimKiem.setBackground(new Color(76, 175, 80)); // Green button
        btnTimKiem.setForeground(Color.WHITE);
        timKiemPanel.add(btnTimKiem);

        btnLamMoi = new JButton("Làm Mới");
        btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnLamMoi.setBackground(new Color(76, 175, 80)); // Green button
        btnLamMoi.setForeground(Color.WHITE);
        timKiemPanel.add(btnLamMoi);


     // Table setup
        String[] columns = {"Mã SP", "Tên SP", "Giá nhập", "Giá bán", "SL tồn"};
        bangSanPham = new JTable(new DefaultTableModel(new Object[][]{}, columns));
        bangSanPham.setFont(new Font("SansSerif", Font.PLAIN, 18));  // Set font size for cells

        
        bangSanPham.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
               
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
               
                if (row % 2 == 0) {
                    cell.setBackground(new Color(240, 248, 255)); 
                } else {
                    cell.setBackground(Color.WHITE); // White for odd rows
                }

                // Set text color
                cell.setForeground(Color.BLACK); // Text color for both selected and unselected cells
                
                return cell;
            }
        });

        // Customize the header
        JTableHeader header = bangSanPham.getTableHeader();
        header.setForeground(Color.BLACK); // Title text color
        header.setBackground(new Color(255, 255, 255)); // White background
        header.setFont(new Font("SansSerif", Font.BOLD, 18)); // Larger and bold font for header

        // Scroll pane setup for the table
        JScrollPane bangScrollPane = new JScrollPane(bangSanPham);
        bangScrollPane.setBounds(42, 148, 1299, 754);
        bangSanPham.setRowHeight(40); // Increase row height for better readability

        // Apply customizations to your JFrame content
        getContentPane().add(bangScrollPane);

        // Panel thông tin sản phẩm (Product info panel)
        JPanel thongTinSPPanel = new JPanel();
        thongTinSPPanel.setBounds(1372, 148, 538, 754);
        thongTinSPPanel.setLayout(new BoxLayout(thongTinSPPanel, BoxLayout.Y_AXIS));
        thongTinSPPanel.setBackground(new Color(220, 220, 220)); // Light grey background
        TitledBorder mainBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "Thông tin sản phẩm");
        thongTinSPPanel.setBorder(mainBorder);

        // Basic info panel
        JPanel basicInfoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        basicInfoPanel.setBackground(new Color(26, 133, 94,94)); // Info background color
        txtGiaNhap = new JTextField(20);
        txtGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtGiaBan = new JTextField(20);
        txtGiaBan.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtMaSanPham = new JTextField(20);
        txtMaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtTenSanPham = new JTextField(20);
        txtTenSanPham.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JLabel label_2 = new JLabel("Mã sản phẩm:");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        basicInfoPanel.add(label_2);
        basicInfoPanel.add(txtMaSanPham);
        JLabel label_3 = new JLabel("Tên sản phẩm:");
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        basicInfoPanel.add(label_3);
        basicInfoPanel.add(txtTenSanPham);
        JLabel label_4 = new JLabel("Giá nhập:");
        label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        basicInfoPanel.add(label_4);
        basicInfoPanel.add(txtGiaNhap);
        JLabel label_5 = new JLabel("Giá bán:");
        label_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        basicInfoPanel.add(label_5);
        basicInfoPanel.add(txtGiaBan);

        // Specifications panel
        JPanel specPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        specPanel.setBackground(new Color(26, 133, 94,94));
        txtCongDung = new JTextField(20);
        txtCongDung.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtThanhPhan = new JTextField(20);
        txtThanhPhan.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtBaoQuan = new JTextField(20);
        txtBaoQuan.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtChongChiDinh = new JTextField(20);
        txtChongChiDinh.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JLabel label_6 = new JLabel("Công dụng:");
        label_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
        specPanel.add(label_6);
        specPanel.add(txtCongDung);
        JLabel label_7 = new JLabel("Thành phần:");
        label_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
        specPanel.add(label_7);
        specPanel.add(txtThanhPhan);
        JLabel label_8 = new JLabel("Bảo quản:");
        label_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
        specPanel.add(label_8);
        specPanel.add(txtBaoQuan);
        JLabel label_9 = new JLabel("Chống chỉ định:");
        label_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
        specPanel.add(label_9);
        specPanel.add(txtChongChiDinh);

        thongTinSPPanel.add(basicInfoPanel);
        thongTinSPPanel.add(specPanel);
        getContentPane().setLayout(null);

        // Add components to content pane
        getContentPane().add(timKiemPanel);
        getContentPane().add(bangScrollPane);
        getContentPane().add(thongTinSPPanel);

        // Title label
        JLabel lblNewLabel = new JLabel("Tra Cứu Sản Phẩm");
        lblNewLabel.setForeground(new Color(0, 128, 64)); // Dark green text
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
        lblNewLabel.setBounds(828, 11, 474, 53);
        getContentPane().add(lblNewLabel);

        // Search button action
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSP = txtMaSanPham.getText().trim();
                String tenSP = txtTenSanPham.getText().trim();

                List<String[]> ketQua = sanPhamDAO.timKiemSanPham(maSP, tenSP, null);
                
                if (ketQua.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm nào theo tiêu chí tìm kiếm!");
                    hienThiSanPham(new ArrayList<>());
                } else {
                    hienThiSanPham(ketQua);
                }
            }
        });

        // Refresh button action
        btnLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtmasp.setText("");
                txtsanpham.setText("");
                txtMaSanPham.setText("");
                txtTenSanPham.setText("");
                txtGiaNhap.setText("");
                txtGiaBan.setText("");
                txtCongDung.setText("");
                txtThanhPhan.setText("");
                txtBaoQuan.setText("");
                txtChongChiDinh.setText("");
                hienThiSanPham(new ArrayList<>());
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
        menu.setBackground(new Color(26, 133, 94)); // Dark green
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font("Leelawadee UI", Font.BOLD, 28));
        menu.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        menu.setIcon(new ImageIcon(scaledImage));
        return menu;
    }

    public static void main(String[] args) {
        new TraCuuSanPham_GUI();
    }
}
