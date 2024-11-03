package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TraCuuSanPham_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTable table;

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
        setBounds(100, 100, 1440, 912);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Create Menu
        createMenuBar();

        // Create the main content
        createMainContent();
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


    private void createMainContent() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(226, 250, 252));
        panel.setBounds(0, 69, 1445, 833);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("TRA CỨU SẢN PHẨM");
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
        lblTitle.setBounds(97, 22, 703, 70);
        ImageIcon poster = new ImageIcon(TraCuuSanPham_GUI.class.getResource("/gui/poster.png"));
        Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
        imageLabel.setBounds(10, 11, 77, 81);
        panel.add(imageLabel);
        panel.add(lblTitle);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(169, 204, 193));
        panel_1.setBounds(79, 102, 1265, 420);
        panel.add(panel_1);
        panel_1.setLayout(null);

        // Create product information section
        createProductInfoSection(panel_1);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(214, 533, 972, 32);
        panel.add(textField_5);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(36, 585, 1386, 179);
        panel.add(scrollPane);

        table = new JTable();
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        table.setBackground(Color.LIGHT_GRAY);
        table.setRowHeight(40);
        table.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                },
                new String[] {
                    "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Bán", "Công Dụng", "Hạn Sử Dụng", "Bảo Quản",
                    "Chống Chỉ Định", "Ngày Sản Xuất", "Thành Phần", "Số Lượng Tồn Kho", "Ghi Chú",
                    "Nhà Sản Xuất", "Đơn Vị Tính", "Thuế GTGT", "Giá Nhập"
                }
        ));
        scrollPane.setViewportView(table);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(36, 532, 152, 32);
        panel.add(comboBox);

        JButton btnSearch = new JButton("Tìm Kiếm");
        btnSearch.setBounds(1228, 532, 152, 32);
        panel.add(btnSearch);
    }

    private void createProductInfoSection(JPanel panel) {
        Font font = new Font("Tahoma", Font.PLAIN, 18);

        // Product information fields
        createLabeledTextField(panel, "Mã Sản Phẩm", font, 65, 34, 220, 34);
        createLabeledTextField(panel, "Tên Sản Phẩm", font, 65, 84, 220, 84);
        createLabeledTextField(panel, "Giá Bán", font, 65, 134, 220, 134);
        createLabeledTextField(panel, "Công Dụng", font, 65, 184, 220, 184);
        createLabeledTextField(panel, "Hạn Sử Dụng", font, 696, 34, 901, 38);
        createLabeledTextField(panel, "Bảo Quản", font, 696, 84, 901, 88);
        createLabeledTextField(panel, "Chống Chỉ Định", font, 696, 134, 901, 138);
        createLabeledTextField(panel, "Ngày Sản Xuất", font, 696, 188, 901, 188);
        createLabeledTextField(panel, "Thành Phần", font, 65, 234, 220, 234);
        createLabeledTextField(panel, "Số Lượng Tồn Kho", font, 696, 234, 901, 238);
        createLabeledTextField(panel, "Ghi Chú", font, 65, 284, 220, 274);
        createLabeledTextField(panel, "Nhà Sản Xuất", font, 65, 334, 220, 334);
        createLabeledTextField(panel, "Đơn Vị Tính", font, 696, 284, 901, 288);
        createLabeledTextField(panel, "Thuế GTGT", font, 65, 384, 220, 384);
        createLabeledTextField(panel, "Giá Nhập", font, 696, 334, 901, 338);
    }

    private void createLabeledTextField(JPanel panel, String labelText, Font font, int labelX, int labelY, int fieldX, int fieldY) {
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        label.setBounds(labelX, labelY, 150, 30);
        panel.add(label);

        JTextField textField = new JTextField();
        textField.setBounds(fieldX, fieldY, 200, 30);
        panel.add(textField);
    }
}
