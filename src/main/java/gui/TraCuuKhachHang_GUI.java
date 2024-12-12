
package gui;

import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import gui.TrangChu_GUI;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class TraCuuKhachHang_GUI extends JFrame {

    private JTextField txtMaKhachHang, txtTenKhachHang, txtSDT;
    private JTable bangKhachHang, bangSanPhamDaMua;
    private JButton btnTimKiem, btnLamMoi;
    private KhachHang_DAO khachHangDAO;
    private SanPham_DAO sanPhamDAO;
    private TrangChu_GUI trangChuGUI;
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

      //Menu
      		trangChuGUI = new TrangChu_GUI();
      		JMenuBar menuBar = trangChuGUI.createMenuBar();
      		menuBar.setBorderPainted(false);
      		menuBar.setOpaque(true);
      		menuBar.setBackground(new Color(26, 133, 94));
      		menuBar.setBounds(0, 0, 1395, 70);
      		add(menuBar);

        // Panel Tìm kiếm Khách Hàng
        JPanel timKiemPanel = new JPanel();
        timKiemPanel.setLayout(null);
        timKiemPanel.setBackground(new Color(244, 253, 253));

        JLabel lblMaKhachHang = new JLabel("Mã khách hàng");
        lblMaKhachHang.setForeground(new Color(0, 128, 0));
        lblMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblMaKhachHang.setBounds(123, 99, 190, 33);
        timKiemPanel.add(lblMaKhachHang);

        txtMaKhachHang = new JTextField(20);
        txtMaKhachHang.setBounds(319, 106, 166, 30);
        timKiemPanel.add(txtMaKhachHang);

        JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
        lblTenKhachHang.setForeground(new Color(0, 128, 0));
        lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblTenKhachHang.setBounds(544, 99, 237, 33);
        timKiemPanel.add(lblTenKhachHang);

        txtTenKhachHang = new JTextField(25);
        txtTenKhachHang.setBounds(755, 106, 206, 30);
        timKiemPanel.add(txtTenKhachHang);

        JLabel lblSDT = new JLabel("Số điện thoại");
        lblSDT.setForeground(new Color(0, 128, 0));
        lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblSDT.setBounds(1010, 99, 185, 33);
        timKiemPanel.add(lblSDT);

        txtSDT = new JTextField(15);
        txtSDT.setBounds(1205, 104, 175, 30);
        timKiemPanel.add(txtSDT);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnTimKiem.setBounds(1471, 98, 150, 35);
        btnTimKiem.setBackground(new Color(76, 175, 80));
        btnTimKiem.setForeground(Color.WHITE);
        timKiemPanel.add(btnTimKiem);

        btnLamMoi = new JButton("Làm Mới");
        btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnLamMoi.setBounds(1652, 98, 150, 35);
        btnLamMoi.setForeground(Color.WHITE);
        btnLamMoi.setBackground(new Color(76, 175, 80));
        timKiemPanel.add(btnLamMoi);

        // Bảng Khách Hàng
        String[] khachHangColumns = {"Mã KH", "Tên KH", "Số ĐT"};
        bangKhachHang = new JTable(new DefaultTableModel(new Object[][]{}, khachHangColumns));
        bangKhachHang.setRowHeight(30);
        customizeTable(bangKhachHang);

        JScrollPane bangKhachHangScrollPane = new JScrollPane(bangKhachHang);
        bangKhachHangScrollPane.setBounds(23, 155, 1870, 359);
        timKiemPanel.add(bangKhachHangScrollPane);
        bangKhachHangScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(183, 28, 28)), "Danh Sách Khách Hàng",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", Font.BOLD, 20), new Color(110, 0, 28))); 

        // Bảng Sản Phẩm Đã Mua
        String[] sanPhamColumns = {"Mã SP", "Tên SP", "Số lượng", "Ngày mua"};
        bangSanPhamDaMua = new JTable(new DefaultTableModel(new Object[][]{}, sanPhamColumns));
        bangSanPhamDaMua.setRowHeight(30);
        customizeTable(bangSanPhamDaMua);

        JScrollPane bangSanPhamScrollPane = new JScrollPane(bangSanPhamDaMua);
        bangSanPhamScrollPane.setBounds(23, 550, 1870, 300);
        timKiemPanel.add(bangSanPhamScrollPane);
        bangSanPhamScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(183, 28, 28)), "Sản Phẩm Đã Mua",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", Font.BOLD, 20), new Color(110, 0, 28))); 

        // Label tiêu đề
        JLabel lblNewLabel = new JLabel("Tra Cứu Khách Hàng");
        lblNewLabel.setForeground(new Color(0, 128, 64));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
        lblNewLabel.setBounds(761, 24, 427, 58);
        timKiemPanel.add(lblNewLabel);

        getContentPane().add(timKiemPanel, BorderLayout.CENTER);
        
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

        // ActionListener cho nút Làm Mới
        btnLamMoi.addActionListener(e -> lamMoi());

        // Lắng nghe sự kiện chọn khách hàng trong bảng
        bangKhachHang.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = bangKhachHang.getSelectedRow();
            if (selectedRow >= 0) {
                String maKhachHang = bangKhachHang.getValueAt(selectedRow, 0).toString();
                List<String[]> danhSachSanPham = sanPhamDAO.laySanPhamDaMua(maKhachHang);
                hienThiSanPham(danhSachSanPham);
            }
        });
        bangKhachHang.getSelectionModel().addListSelectionListener(e -> {
            // Kiểm tra xem có dòng nào được chọn hay không
            if (!e.getValueIsAdjusting() && bangKhachHang.getSelectedRow() != -1) {
                // Lấy thông tin từ dòng đã chọn
                int selectedRow = bangKhachHang.getSelectedRow();
                String maKhachHang = (String) bangKhachHang.getValueAt(selectedRow, 0);
                String tenKhachHang = (String) bangKhachHang.getValueAt(selectedRow, 1);
                String sdt = (String) bangKhachHang.getValueAt(selectedRow, 2);

                // Cập nhật các text field với thông tin từ dòng đã chọn
                txtMaKhachHang.setText(maKhachHang);
                txtTenKhachHang.setText(tenKhachHang);
                txtSDT.setText(sdt);
            }
        });
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

    private void customizeTable(JTable table) {
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);

       
        table.getTableHeader().setBackground(new Color(240,240,240));  
        table.getTableHeader().setForeground(Color.BLACK); 
        table.setRowHeight(35); 

      
        Font font = new Font("Tahoma", Font.PLAIN, 18);  
        table.setFont(font); 
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));  
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);          
                if (row % 2 == 0) {
                    component.setBackground(Color.WHITE); 
                } else {
                    component.setBackground(new Color(240, 248, 255));  
                }            
                component.setForeground(Color.BLACK);
                                if (isSelected) {
                    component.setBackground(new Color(100, 200, 100)); 
                    component.setForeground(Color.WHITE); 
                }
                return component;
            }
        });
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
