package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TraCuuKhachHang_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTable table;
    private JComboBox<String> comboBox;
    private JRadioButton rdbtnMale;
    private JRadioButton rdbtnFemale;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TraCuuKhachHang_GUI frame = new TraCuuKhachHang_GUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TraCuuKhachHang_GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1440, 912);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Create Menu
        createMenuBar();

        // Main Content Section
        createMainContent();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(26, 133, 94));
        menuBar.setBounds(0, 0, 1445, 70);
        contentPane.add(menuBar);

        JMenu menuHome = new JMenu(" Trang Chủ");
        setupMenu(menuHome, "/gui/house-solid.png", new Color(26, 133, 94));
        menuBar.add(menuHome);

        JMenu menuManage = new JMenu(" Quản Lý");
        setupMenu(menuManage, "/gui/list-check-solid.png", new Color(26, 133, 94));
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.add(menuManage);

        addMenuItem(menuManage, "Sản Phẩm");
        addMenuItem(menuManage, "Nhân Viên");
        addMenuItem(menuManage, "Khách Hàng");
    }

    private void setupMenu(JMenu menu, String iconPath, Color bgColor) {
        menu.setOpaque(true);
        menu.setBackground(bgColor);
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        ImageIcon icon = new ImageIcon(TraCuuKhachHang_GUI.class.getResource(iconPath));
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        menu.setIcon(new ImageIcon(scaledImage));
        menu.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
    }

    private void addMenuItem(JMenu menu, String title) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        menuItem.setBackground(new Color(26, 133, 94));
        menuItem.setForeground(Color.WHITE);
        menu.add(menuItem);
    }

    private void createMainContent() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(226, 250, 252));
        panel.setBounds(0, 69, 1445, 833);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("TRA CỨU KHÁCH HÀNG");
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
        lblTitle.setBounds(97, 22, 703, 70);
        ImageIcon poster = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/gui/poster.png"));
        Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
        imageLabel.setBounds(10, 11, 77, 81);
        panel.add(imageLabel);
        panel.add(lblTitle);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(231, 102, 903, 372);
        panel.add(panel_1);
        panel_1.setLayout(null);

        createCustomerInfoSection(panel_1);

        textField_5 = new JTextField();
        textField_5.setBounds(214, 533, 972, 32);
        panel.add(textField_5);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(79, 585, 1294, 179);
        panel.add(scrollPane);

        table = new JTable();
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        table.setBackground(Color.LIGHT_GRAY);
        table.setRowHeight(40);
        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {
                    "STT", "Tên Khách Hàng", "Số Điện Thoại", "Giới Tính", "Sản Phẩm Đã Mua", "Số Lượng", "Tổng Tiền"
                }
        ));
        scrollPane.setViewportView(table);

        comboBox = new JComboBox<>(new String[] {"Tên Khách Hàng", "Số Điện Thoại"});
        comboBox.setBounds(36, 532, 152, 32);
        panel.add(comboBox);

        JButton btnSearch = new JButton("Tìm Kiếm");
        btnSearch.setBounds(1228, 532, 152, 32);
        btnSearch.addActionListener(e -> searchCustomer());
        panel.add(btnSearch);
    }

    private void createCustomerInfoSection(JPanel panel) {
        textField_3 = new JTextField();
        textField_3.setBounds(406, 80, 382, 41);
        panel.add(textField_3);

        textField_4 = new JTextField();
        textField_4.setBounds(406, 193, 382, 41);
        panel.add(textField_4);

        JLabel lblCustomerName = new JLabel("Tên Khách Hàng");
        lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 23));
        lblCustomerName.setBounds(127, 71, 264, 49);
        panel.add(lblCustomerName);

        JLabel lblPhone = new JLabel("Số Điện Thoại ");
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 23));
        lblPhone.setBounds(132, 193, 264, 41);
        panel.add(lblPhone);

        JLabel lblGender = new JLabel("Giới Tính");
        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 23));
        lblGender.setBounds(132, 287, 126, 36);
        panel.add(lblGender);

        rdbtnMale = new JRadioButton("Nam");
        rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 23));
        rdbtnMale.setBackground(Color.WHITE);
        rdbtnMale.setBounds(406, 297, 109, 23);
        panel.add(rdbtnMale);

        rdbtnFemale = new JRadioButton("Nữ");
        rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 23));
        rdbtnFemale.setBackground(Color.WHITE);
        rdbtnFemale.setBounds(570, 297, 109, 23);
        panel.add(rdbtnFemale);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rdbtnMale);
        genderGroup.add(rdbtnFemale);
    }

    private void searchCustomer() {
        String searchField = (String) comboBox.getSelectedItem();
        String searchText = textField_5.getText();

        
        Object[][] customers = {
        	    {"1", "Nguyen Van A", "0123456789", "Nam", "Panadol", "2", "100000"},
        	    {"2", "Tran Thi B", "0987654321", "Nữ", "Aspirin", "5", "50000"},
        	    {"3", "Tran Doan Khoe", "0981234567", "Nam", "Vitamin C", "10", "200000"},
        	    {"4", "Huynh Thi Luu Ly", "0938765432", "Nữ", "Tylenol", "1", "55000"},
        	    {"5", "Nguyen Anh Duc", "0912345678", "Nam", "Decolgen", "3", "75000"}
        	};

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 

        for (Object[] customer : customers) {
            String customerName = (String) customer[1];
            String phone = (String) customer[2];

            if (searchField.equals("Tên Khách Hàng") && customerName.contains(searchText) ||
                searchField.equals("Số Điện Thoại") && phone.contains(searchText)) {
                model.addRow(customer);
            }
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng.", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
