package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TraCuuSanPham_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField searchTextField;
    private JTable table;
    private JComboBox<String> comboBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TraCuuSanPham_GUI frame = new TraCuuSanPham_GUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TraCuuSanPham_GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Tạo Menu
        createMenuBar();

        // Tạo phần nội dung chính
        createMainContent();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(26, 133, 94));
        menuBar.setBounds(0, 0, 1553, 70);
        contentPane.add(menuBar);

        JMenu mnNewMenu = new JMenu(" Trang Chủ");
        setupMenu(mnNewMenu, "/gui/house-solid.png");
        menuBar.add(mnNewMenu);

        JMenu mnNewMenu_1 = new JMenu(" Quản Lý");
        setupMenu(mnNewMenu_1, "/gui/list-check-solid.png");
        menuBar.add(mnNewMenu_1);

        addMenuItem(mnNewMenu_1, "Sản Phẩm");
        addMenuItem(mnNewMenu_1, "Nhân Viên");
        addMenuItem(mnNewMenu_1, "Khách Hàng");

        JMenu mnNewMenu_2_1 = new JMenu(" Bán Hàng");
        setupMenu(mnNewMenu_2_1, "/gui/cart-shopping-solid.png");
        menuBar.add(mnNewMenu_2_1);

        JMenu mnNewMenu_2 = new JMenu(" Thống Kê");
        setupMenu(mnNewMenu_2, "/gui/clipboard-solid.png");
        menuBar.add(mnNewMenu_2);

        addMenuItem(mnNewMenu_2, "Doanh Số");
        addMenuItem(mnNewMenu_2, "Nhân Viên");
        addMenuItem(mnNewMenu_2, "Khách Hàng");
        addMenuItem(mnNewMenu_2, "Sản Phẩm");

        JMenu mnNewMenu_2_2 = new JMenu(" Tra Cứu   ");
        setupMenu(mnNewMenu_2_2, "/gui/circle-question-solid.png");
        menuBar.add(mnNewMenu_2_2);

        addMenuItem(mnNewMenu_2_2, "Sản Phẩm");
        addMenuItem(mnNewMenu_2_2, "Nhân Viên");
        addMenuItem(mnNewMenu_2_2, "Hóa Đơn");
        addMenuItem(mnNewMenu_2_2, "Khách Hàng");
    }

    private void setupMenu(JMenu menu, String iconPath) {
        menu.setOpaque(true);
        menu.setBackground(new Color(26, 133, 94));
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        menu.setIcon(loadIcon(iconPath));
        menu.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
    }

    private void addMenuItem(JMenu menu, String title) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        menuItem.setBackground(new Color(26, 133, 94));
        menuItem.setForeground(Color.WHITE);
        menu.add(menuItem);
    }

    private ImageIcon loadIcon(String path) {
        ImageIcon icon = new ImageIcon(TraCuuSanPham_GUI.class.getResource(path));
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private void createMainContent() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(226, 250, 252));
        panel.setBounds(0, 69, 1553, 833);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("TRA CỨU SẢN PHẨM");
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
        lblTitle.setBounds(97, 22, 703, 70);
        panel.add(lblTitle);

        // Phần bảng hiển thị kết quả tìm kiếm
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(53, 275, 1469, 379);
        panel.add(scrollPane);

        table = new JTable();
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        table.setBackground(Color.WHITE);
        table.setRowHeight(40);
        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {
                    "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Bán", "Công Dụng", "Hạn Sử Dụng", "Bảo Quản",
                    "Chống Chỉ Định", "Ngày Sản Xuất", "Thành Phần", "Số Lượng Tồn Kho", "Ghi Chú",
                    "Nhà Sản Xuất", "Đơn Vị Tính", "Thuế GTGT", "Giá Nhập"
                }
        ));
        scrollPane.setViewportView(table);

        comboBox = new JComboBox<>(new String[] {"Mã Sản Phẩm", "Tên Sản Phẩm"});
        comboBox.setBounds(38, 187, 152, 32);
        panel.add(comboBox);

        searchTextField = new JTextField();
        searchTextField.setBounds(200, 188, 972, 32);
        panel.add(searchTextField);

        JButton btnSearch = new JButton("Tìm Kiếm");
        btnSearch.setBounds(1221, 187, 152, 32);
        btnSearch.addActionListener(e -> searchProduct());
        panel.add(btnSearch);
    }

    private void searchProduct() {
        // Dữ liệu mẫu giả lập
        Object[][] sampleData = {
            {"1", "SP001", "Paracetamol", "10000", "Giảm đau, hạ sốt", "2025-12-31", "Nơi khô ráo", "Không dùng cho trẻ em dưới 6 tuổi", "2023-01-01", "Paracetamol 500mg", "50", "Không", "ABC Pharma", "Viên", "10%", "8000"},
            {"2", "SP002", "Aspirin", "15000", "Giảm đau", "2024-11-30", "Bảo quản nhiệt độ phòng", "Không dùng cho phụ nữ có thai", "2023-02-10", "Aspirin 100mg", "30", "Không", "XYZ Pharma", "Viên", "5%", "12000"},
            {"3", "SP003", "Vitamin C", "5000", "Tăng cường sức đề kháng", "2026-08-15", "Tránh ánh nắng trực tiếp", "Không dùng cho người bị sỏi thận", "2023-03-15", "Vitamin C 100mg", "100", "Không", "Health Inc.", "Viên", "8%", "4000"},
            // Thêm các sản phẩm khác nếu cần
        };

        // Lấy giá trị tìm kiếm và lựa chọn loại tìm kiếm
        String searchValue = searchTextField.getText().trim();
        String searchType = comboBox.getSelectedItem().toString();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);  // Xóa bảng trước khi thêm kết quả mới

        // Duyệt qua dữ liệu mẫu để tìm kết quả phù hợp
        int index = 1;
        boolean found = false;
        for (Object[] product : sampleData) {
            String productCode = (String) product[1];
            String productName = (String) product[2];
            if ((searchType.equals("Mã Sản Phẩm") && productCode.equalsIgnoreCase(searchValue)) ||
                (searchType.equals("Tên Sản Phẩm") && productName.equalsIgnoreCase(searchValue))) {
                model.addRow(new Object[] {index++, product[1], product[2], product[3], product[4], product[5], product[6], product[7], product[8], product[9], product[10], product[11], product[12], product[13], product[14], product[15]});
                found = true;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm!", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
