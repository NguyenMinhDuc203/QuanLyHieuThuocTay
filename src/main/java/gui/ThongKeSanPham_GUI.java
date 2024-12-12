package gui;

import dao.SanPham_DAO;
import entity.SanPham;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import javax.swing.border.EmptyBorder;

public class ThongKeSanPham_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JComboBox<String> comboBox;
    private JRadioButton rdbtnTatCa;
    private JRadioButton rdbtnDuoi20;
    private JRadioButton rdbtn20_100;
    private JRadioButton rdbtnTren100;
    private JComboBox<String> comboBoxHanSuDung;
    private SanPham_DAO sanPhamDAO;
    private JLabel lblSLngBn;
    private JLabel lblTngLngSn;
    private JLabel lblTngS;
    private JLabel lblSSnPhm;
    private TrangChu_GUI trangChuGUI;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ThongKeSanPham_GUI frame = new ThongKeSanPham_GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ThongKeSanPham_GUI() {
        sanPhamDAO = new SanPham_DAO();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080);
        setSize(1920, 1080);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 255, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //Menu
  		trangChuGUI = new TrangChu_GUI();
  		JMenuBar menuBar = trangChuGUI.createMenuBar();
  		menuBar.setBorderPainted(false);
  		menuBar.setOpaque(true);
  		menuBar.setBackground(new Color(26, 133, 94));
  		menuBar.setBounds(0, 0, 1395, 70);
  		contentPane.add(menuBar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(null);
        scrollPane.setBounds(61, 568, 1612, 408);
        contentPane.add(scrollPane);
        JLabel lblNewLabel = new JLabel("THỐNG KÊ SẢN PHẨM");
        lblNewLabel.setForeground(new Color(10, 69, 23));
        lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
        lblNewLabel.setBounds(601, 108, 703, 70);
        ImageIcon poster = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/gui/poster.png"));
        Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
        imageLabel.setBounds(452, 86, 93, 92);
        contentPane.add(imageLabel);
        contentPane.add(lblNewLabel);

        table = new JTable();
        table.setBackground(SystemColor.window);
        table.setBorder(null);
        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm", "Hạn Sử Dụng", "Số Lượng Đã Bán", "Tồn Kho"
                }));
        // Thiết lập màu nền và font chữ cho tiêu đề cột
        JTableHeader header = table.getTableHeader();
        header.setForeground(Color.WHITE); // Màu chữ tiêu đề
        header.setBackground(new Color(26, 133, 94)); // Màu nền tiêu đề
        header.setFont(new Font("SansSerif", Font.BOLD, 14)); // Font chữ tiêu đề

        // Thiết lập màu nền cho các hàng trong bảng
        table.setBackground(Color.WHITE);
        table.setGridColor(Color.LIGHT_GRAY); // Màu cho đường kẻ ô
        table.setFont(new Font("SansSerif", Font.PLAIN, 12)); // Font chữ trong bảng
        table.setRowHeight(30); // Chiều cao của mỗi hàng

        // Thiết lập màu nền cho hàng được chọn
        table.setSelectionBackground(Color.CYAN); // Màu nền khi chọn hàng
        table.setSelectionForeground(Color.BLACK); // Màu chữ khi chọn hàng

        table.setRowHeight(30);
        scrollPane.setViewportView(table);

        ButtonGroup buttonGroup = new ButtonGroup();

        JPanel panel = new JPanel();
        panel.setBackground(new Color(26, 133, 94));
        panel.setBounds(61, 205, 1612, 330);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton btnNewButton = new JButton("Thống Kê");
        btnNewButton.setBounds(687, 258, 158, 62);
        panel.add(btnNewButton);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.setBackground(SystemColor.inactiveCaption);

        lblSSnPhm = new JLabel("Số Sản Phẩm Còn Hạng\r\n");
        lblSSnPhm.setBounds(996, 207, 221, 37);
        panel.add(lblSSnPhm);
        lblSSnPhm.setFont(new Font("Leelawadee UI", Font.BOLD, 20));

        textField_2 = new JTextField();
        textField_2.setBounds(1256, 207, 256, 37);
        panel.add(textField_2);
        textField_2.setEditable(false);

        textField_1 = new JTextField();
        textField_1.setBounds(1256, 135, 256, 37);
        panel.add(textField_1);
        textField_1.setEditable(false);

        lblTngS = new JLabel("Tổng Số Đã Bán Ra\r\n");
        lblTngS.setBounds(996, 135, 221, 37);
        panel.add(lblTngS);
        lblTngS.setFont(new Font("Leelawadee UI", Font.BOLD, 20));

        lblTngLngSn = new JLabel("Tổng Lượng Sản Phẩm");
        lblTngLngSn.setBounds(996, 79, 221, 37);
        panel.add(lblTngLngSn);
        lblTngLngSn.setFont(new Font("Leelawadee UI", Font.BOLD, 20));

        textField = new JTextField();
        textField.setBounds(1256, 67, 256, 37);
        panel.add(textField);
        textField.setEditable(false);

        comboBoxHanSuDung = new JComboBox<>();
        comboBoxHanSuDung.setBounds(488, 68, 255, 48);
        panel.add(comboBoxHanSuDung);
        comboBoxHanSuDung.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        comboBoxHanSuDung.setModel(new DefaultComboBoxModel<>(
                new String[] { "Tất Cả", "Đã hết hạn", "<3 tháng", "3 - 6 tháng", "> 6 tháng" }));

        JLabel lblHanSuDung = new JLabel("Hạn Sử Dụng");
        lblHanSuDung.setBounds(476, 21, 152, 37);
        panel.add(lblHanSuDung);
        lblHanSuDung.setFont(new Font("Leelawadee UI", Font.BOLD, 20));

        rdbtn20_100 = new JRadioButton("20-100");
        rdbtn20_100.setBounds(73, 241, 109, 62);
        panel.add(rdbtn20_100);
        rdbtn20_100.setBackground(SystemColor.window);
        rdbtn20_100.setFont(new Font("Tahoma", Font.PLAIN, 14));
        buttonGroup.add(rdbtn20_100);

        rdbtnTren100 = new JRadioButton("> 100");
        rdbtnTren100.setBounds(233, 241, 109, 62);
        panel.add(rdbtnTren100);
        rdbtnTren100.setBackground(SystemColor.window);
        rdbtnTren100.setFont(new Font("Tahoma", Font.PLAIN, 14));
        buttonGroup.add(rdbtnTren100);

        rdbtnDuoi20 = new JRadioButton("<20");
        rdbtnDuoi20.setBounds(233, 150, 109, 60);
        panel.add(rdbtnDuoi20);
        rdbtnDuoi20.setBackground(SystemColor.window);
        rdbtnDuoi20.setFont(new Font("Tahoma", Font.PLAIN, 14));
        buttonGroup.add(rdbtnDuoi20);

        rdbtnTatCa = new JRadioButton("Tất Cả");
        rdbtnTatCa.setBounds(73, 148, 109, 62);
        panel.add(rdbtnTatCa);
        rdbtnTatCa.setBackground(SystemColor.window);
        rdbtnTatCa.setFont(new Font("Leelawadee UI", Font.PLAIN, 15));
        buttonGroup.add(rdbtnTatCa);

        lblSLngBn = new JLabel("Số Lượng Bán Ra\r\n");
        lblSLngBn.setBounds(70, 107, 194, 37);
        panel.add(lblSLngBn);
        lblSLngBn.setFont(new Font("Leelawadee UI", Font.BOLD, 20));

        comboBox = new JComboBox<>();
        comboBox.setBounds(73, 57, 330, 48);
        panel.add(comboBox);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        comboBox.setModel(new DefaultComboBoxModel<>(
                new String[] { "Tất Cả", "LDCYT: Dụng cụ y tế", "LSPYT: Lãnh vực sản phẩm y tế",
                        "LTPCN: Sản phẩm phòng chống", "LTKS: Sản phẩm kháng sinh", "LTGD: Sản phẩm giảm đau",
                        "LVTM: Sản phẩm vitamin và khoáng chất", "LTTH: Sản phẩm tiêu hóa", "LK: Sản phẩm thuốc" }));

        JLabel lblLoaiSanPham = new JLabel("Loại Sản Phẩm");
        lblLoaiSanPham.setBounds(76, 10, 152, 37);
        panel.add(lblLoaiSanPham);
        lblLoaiSanPham.setFont(new Font("Leelawadee UI", Font.BOLD, 20));

        // Add action listener to the button
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thongKeSanPham();
            }
        });
    }

    private void thongKeSanPham() {
        try {
            String selectedLoaiSanPham = comboBox.getSelectedItem().toString();
            String selectedHanSuDung = comboBoxHanSuDung.getSelectedItem().toString();
            String soLuongBanRa = "Tất Cả";

            if (rdbtnDuoi20.isSelected()) {
                soLuongBanRa = "<20";
            } else if (rdbtn20_100.isSelected()) {
                soLuongBanRa = "20-100";
            } else if (rdbtnTren100.isSelected()) {
                soLuongBanRa = ">100";
            }

            // Use existing DAO methods to filter products
            List<SanPham> sanPhamList = sanPhamDAO.getAllSanPhams();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows

            int totalInventory = 0;
            int totalSold = 0;
            int inStockWithinExpiry = 0;

            LocalDate currentDate = LocalDate.now();
            for (SanPham sp : sanPhamList) {
                // Tự động tính số lượng đã bán từ bảng ChiTietHoaDon
                int soLuongDaBan = sanPhamDAO.tinhSoLuongDaBan(sp.getMaSanPham());
                sp.setSoLuongTonkho(soLuongDaBan);

                boolean matchesLoaiSanPham = selectedLoaiSanPham.equals("Tất Cả")
                        || sp.getLoaiSanPham().getMaLoai().equals(selectedLoaiSanPham);
                boolean matchesHanSuDung = selectedHanSuDung.equals("Tất Cả") ||
                        (selectedHanSuDung.equals("Đã hết hạn") && sp.getHanSuDung().isBefore(currentDate)) ||
                        (selectedHanSuDung.equals("<3 tháng") && sp.getHanSuDung().isAfter(currentDate)
                                && sp.getHanSuDung().isBefore(currentDate.plusMonths(3)))
                        ||
                        (selectedHanSuDung.equals("3 - 6 tháng") && sp.getHanSuDung().isAfter(currentDate.plusMonths(3))
                                && sp.getHanSuDung().isBefore(currentDate.plusMonths(6)))
                        ||
                        (selectedHanSuDung.equals("> 6 tháng") && sp.getHanSuDung().isAfter(currentDate.plusMonths(6)));
                boolean matchesSoLuongBanRa = soLuongBanRa.equals("Tất Cả") ||
                        (soLuongBanRa.equals("<20") && soLuongDaBan < 20) ||
                        (soLuongBanRa.equals("20-100") && soLuongDaBan >= 20 && soLuongDaBan <= 100) ||
                        (soLuongBanRa.equals(">100") && soLuongDaBan > 100);

                if (matchesLoaiSanPham && matchesHanSuDung && matchesSoLuongBanRa) {
                    model.addRow(new Object[] {
                            sp.getMaSanPham(),
                            sp.getTenSanPham(),
                            sp.getLoaiSanPham().getMaLoai(),
                            sp.getHanSuDung(),
                            soLuongDaBan,
                            sp.getSoLuongTonkho()
                    });
                }

                totalInventory += sp.getSoLuongTonkho();
                totalSold += soLuongDaBan;
                if (sp.getHanSuDung().isAfter(currentDate)) {
                    inStockWithinExpiry += sp.getSoLuongTonkho();
                }
            }

            // Update the text fields with the statistics
            textField.setText(String.valueOf(totalInventory));
            textField_1.setText(String.valueOf(totalSold));
            textField_2.setText(String.valueOf(inStockWithinExpiry));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thống kê sản phẩm!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
