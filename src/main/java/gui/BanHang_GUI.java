package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BanHang_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTimThuoc;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTable table_2;
    private JTable table_1;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_10;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BanHang_GUI frame = new BanHang_GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public BanHang_GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1440, 912); 
        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setForeground(SystemColor.window);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Tạo Menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(0, 191, 255));
        menuBar.setBounds(0, 0, 1426, 70);
        contentPane.add(menuBar);

        // Thêm các mục vào Menu (Trang Chủ, Quản Lý, Bán Hàng, Thống Kê, Tra Cứu)
        addMenuItems(menuBar);

        // Các label và text field cho thông tin hóa đơn
        addInvoiceInfoSection();

        // Danh sách thuốc
        addMedicineListSection();

        // Chi tiết hóa đơn
        addInvoiceDetailsSection();

        // Các nút chức năng
        addFunctionButtons();
    }

    private void addMenuItems(JMenuBar menuBar) {
        JMenu mnNewMenu = new JMenu(" Trang Chủ");
        mnNewMenu.setOpaque(true);
        mnNewMenu.setBackground(new Color(135, 206, 235));
        mnNewMenu.setForeground(new Color(0, 0, 0));
        mnNewMenu.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        ImageIcon icon = new ImageIcon(TrangChu_GUI.class.getResource("/gui/house-solid.png"));
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        mnNewMenu.setIcon(new ImageIcon(scaledImage));
        menuBar.add(mnNewMenu);

        JMenu mnNewMenu_1 = new JMenu(" Quản Lý");
        mnNewMenu_1.setOpaque(true);
        mnNewMenu_1.setBackground(new Color(135, 206, 235));
        mnNewMenu_1.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        mnNewMenu_1.setForeground(Color.BLACK);
        ImageIcon icon1 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/list-check-solid.png"));
        Image scaledImage1 = icon1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        mnNewMenu_1.setIcon(new ImageIcon(scaledImage1));
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.add(mnNewMenu_1);

        JMenu mnNewMenu_2_1 = new JMenu(" Bán Hàng");
        mnNewMenu_2_1.setBackground(new Color(135, 206, 235));
        mnNewMenu_2_1.setOpaque(true);
        mnNewMenu_2_1.setForeground(Color.BLACK);
        mnNewMenu_2_1.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        ImageIcon icon2_1 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/cart-shopping-solid.png"));
        Image scaledImage2_1 = icon2_1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        mnNewMenu_2_1.setIcon(new ImageIcon(scaledImage2_1));
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.add(mnNewMenu_2_1);
        
        JMenu mnNewMenu_2 = new JMenu(" Thống Kê");
        mnNewMenu_2.setBackground(new Color(135, 206, 235));
        mnNewMenu_2.setOpaque(true);
        mnNewMenu_2.setForeground(Color.BLACK);
        mnNewMenu_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        ImageIcon icon2 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/clipboard-solid.png"));
        Image scaledImage2 = icon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        mnNewMenu_2.setIcon(new ImageIcon(scaledImage2));
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.add(mnNewMenu_2);
        
        JMenu mnNewMenu_2_2 = new JMenu(" Tra Cứu");
        mnNewMenu_2_2.setBackground(new Color(135, 206, 235));
        mnNewMenu_2_2.setOpaque(true);
        mnNewMenu_2_2.setForeground(Color.BLACK);
        mnNewMenu_2_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
        ImageIcon icon2_2 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/circle-question-solid.png"));
        Image scaledImage2_2 = icon2_2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        mnNewMenu_2_2.setIcon(new ImageIcon(scaledImage2_2));
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.add(mnNewMenu_2_2);
    }

    private void addInvoiceInfoSection() {
    }

    private void addMedicineListSection() {
    }

    private void addInvoiceDetailsSection() {
    }

    private void addFunctionButtons() {
        JButton btnThanhToan = new JButton("In Hóa Đơn");
        btnThanhToan.setBounds(868, 204, 150, 30);
        contentPane.add(btnThanhToan);

        JButton btnThemThuoc = new JButton("Tạo Hóa Đơn Mới");
        btnThemThuoc.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnThemThuoc.setBounds(868, 260, 150, 30);
        contentPane.add(btnThemThuoc);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(210, 166, 545, 208);
        contentPane.add(panel);
        
        JLabel lblNhanVien = new JLabel("Mã Nhân Viên Lập HD:");
        lblNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblNhanVien.setBounds(20, 29, 248, 30);
        
        textField_2 = new JTextField();
        textField_2.setBounds(244, 35, 277, 30);
        
        JLabel lblKhachHang = new JLabel("Tên Khách Hàng:");
        lblKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblKhachHang.setBounds(20, 84, 150, 30);
        
        textField_3 = new JTextField();
        textField_3.setBounds(244, 90, 277, 30);
        
        JLabel lblDienThoai = new JLabel("Điện Thoại:");
        lblDienThoai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblDienThoai.setBounds(23, 149, 150, 30);
        
        textField_4 = new JTextField();
        textField_4.setBounds(244, 150, 277, 30);
        panel.setLayout(null);
        panel.add(lblNhanVien);
        panel.add(textField_2);
        panel.add(textField_3);
        panel.add(lblKhachHang);
        panel.add(textField_4);
        panel.add(lblDienThoai);
        
        JLabel lblNewLabel_1 = new JLabel("Bán Hàng");
        lblNewLabel_1.setForeground(new Color(0, 0, 255));
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 35));
        lblNewLabel_1.setBounds(87, 80, 240, 50);
        contentPane.add(lblNewLabel_1);
        
        table_2 = new JTable();
        table_2.setBounds(55, 459, 1, 1);
        contentPane.add(table_2);
        
        table_1 = new JTable();
        table_1.setBounds(237, 459, 1, 1);
        contentPane.add(table_1);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(148, 463, 1143, 208);
        contentPane.add(scrollPane);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"STT", "T\u00EAn S\u1EA3n Ph\u1EA9m", "\u0110\u01A1n V\u1ECB T\u00EDn", "S\u1ED1 L\u01B0\u1EE3ng", "\u0110\u01A1n Gi\u00E1", "Thu\u1EBF GTGT", "Th\u00E0nh Ti\u1EC1n"
        	}
        ));
        scrollPane.setViewportView(table);
        
        JButton btnThot = new JButton("Thoát");
        btnThot.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnThot.setBounds(868, 324, 150, 30);
        contentPane.add(btnThot);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(0, 712, 1426, 133);
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblTongTienThuoc = new JLabel("Tổng Tiền Thuốc:");
        lblTongTienThuoc.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblTongTienThuoc.setBounds(737, 37, 193, 30);
        panel_1.add(lblTongTienThuoc);
        
        textField_7 = new JTextField();
        textField_7.setBounds(952, 43, 150, 30);
        panel_1.add(textField_7);
        
        textField_8 = new JTextField();
        textField_8.setBounds(461, 38, 150, 30);
        panel_1.add(textField_8);
        
        JLabel lblTienNhan = new JLabel("Tiền Nhận:");
        lblTienNhan.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblTienNhan.setBounds(298, 37, 150, 30);
        panel_1.add(lblTienNhan);
        
        textField_10 = new JTextField();
        textField_10.setBounds(461, 97, 150, 30);
        panel_1.add(textField_10);
        
        JLabel lblTienTraLai = new JLabel("Tiền Trả Lại:");
        lblTienTraLai.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblTienTraLai.setBounds(298, 96, 150, 30);
        panel_1.add(lblTienTraLai);
        
                JButton btnXoa = new JButton("Lưu Hóa Đơn\r\n");
                btnXoa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                btnXoa.setBounds(737, 93, 150, 30);
                panel_1.add(btnXoa);
                
                JLabel lblNewLabel = new JLabel("Mã Sản Phẩm:");
                lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                lblNewLabel.setBounds(187, 422, 135, 26);
                contentPane.add(lblNewLabel);
                
                textField = new JTextField();
                textField.setBounds(333, 422, 155, 28);
                contentPane.add(textField);
                textField.setColumns(10);
                
                JLabel lblNewLabel_2 = new JLabel("Số Lượng");
                lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                lblNewLabel_2.setBounds(509, 418, 96, 34);
                contentPane.add(lblNewLabel_2);
                
                textField_1 = new JTextField();
                textField_1.setBounds(627, 422, 96, 28);
                contentPane.add(textField_1);
                textField_1.setColumns(10);
                
                JButton btnNewButton = new JButton("Thêm Sản Phẩm\r\n");
                btnNewButton.setBounds(868, 418, 150, 35);
                contentPane.add(btnNewButton);
                btnXoa.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                	}
                });
        
     
    }
}
