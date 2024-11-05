package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.TrangChu_GUI;
public class BanHang_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable productTable;
    private JTextField txtMSnPhm;
    private JTextField phoneField, nameField, membershipField, discountField, amountGivenField;
    private JTextField totalAmountLabel;
    private DefaultTableModel productTableModel;
    private TrangChu_GUI trangChu;

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
        setBounds(0, 0, 1920, 1080);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Menu setup
        trangChu = new TrangChu_GUI();
        JMenuBar menuBar = trangChu.createMenuBar();
        menuBar.setBounds(0, 0, 1906, 70);
        contentPane.add(menuBar);

        
    }

    

    private JPanel createProductTablePanel() {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(null);

        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá bán", "VAT", "Tổng tiền", "Tiền giảm", "Thành tiền"};
        productTableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(productTableModel);

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(0, 0, 1065, 821);
        productPanel.add(scrollPane);

        return productPanel;
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        // Thông tin khách hàng
        JPanel customerInfoPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        customerInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));

     // Replace the JCheckBox with a JComboBox for customer type selection
        String[] customerTypes = {"Vãng lai", "Thành viên", "Mới"};
        JComboBox<String> customerTypeComboBox = new JComboBox<>(customerTypes);

        phoneField = new JTextField();
        nameField = new JTextField();
        nameField.setEditable(false);
        membershipField = new JTextField();
        membershipField.setEditable(false);

     // Add a label and the JComboBox for customer type selection to the panel
        customerInfoPanel.add(new JLabel("Loại khách hàng:"));
        customerInfoPanel.add(customerTypeComboBox);

        customerInfoPanel.add(new JLabel("Số điện thoại:"));
        customerInfoPanel.add(phoneField);
        customerInfoPanel.add(new JLabel("Họ và tên:"));
        customerInfoPanel.add(nameField);
        customerInfoPanel.add(new JLabel("Tích điểm"));
        customerInfoPanel.add(membershipField);
        infoPanel.add(customerInfoPanel);

        // Thông tin hóa đơn
     // Thông tin hóa đơn
        JPanel invoiceInfoPanel = new JPanel(new GridLayout(14, 2, 5, 5));
        invoiceInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        JTextField invoiceIdField = new JTextField("HD111220230004");
        JTextField dateField = new JTextField("11/12/2023");
        discountField = new JTextField();
        totalAmountLabel = new JTextField("0 đ"); // Đã đổi thành JTextField
        totalAmountLabel.setEditable(false); // Đặt là không thể chỉnh sửa
        JComboBox<String> paymentMethodCombo = new JComboBox<>(new String[]{"Tiền mặt", "Thẻ tín dụng", "Chuyển khoản"});
        amountGivenField = new JTextField("0");

        invoiceInfoPanel.add(new JLabel("Mã hóa đơn:"));
        invoiceInfoPanel.add(invoiceIdField);
        invoiceInfoPanel.add(new JLabel("Ngày tạo:"));
        invoiceInfoPanel.add(dateField);
        invoiceInfoPanel.add(new JLabel("Chiết khấu:"));
        invoiceInfoPanel.add(discountField);
        invoiceInfoPanel.add(new JLabel("Khách phải trả:"));
        invoiceInfoPanel.add(totalAmountLabel); // Đã thêm dưới dạng JTextField
        invoiceInfoPanel.add(new JLabel("Phương thức:"));
        invoiceInfoPanel.add(paymentMethodCombo);
        invoiceInfoPanel.add(new JLabel("Tiền khách đưa:"));
        invoiceInfoPanel.add(amountGivenField);

        addMoneyButtons(invoiceInfoPanel);

        JScrollPane scrollPane = new JScrollPane(invoiceInfoPanel);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        infoPanel.add(scrollPane);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        JButton saveButton = new JButton("LƯU TẠM");
        JButton processButton = new JButton("XỬ LÍ ĐƠN TẠM");
        JButton cancelButton = new JButton("HỦY");
        JButton discountButton = new JButton("KHUYẾN MÃI");
        JButton checkoutButton = new JButton("THANH TOÁN");

        buttonPanel.add(saveButton);
        buttonPanel.add(processButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(discountButton);
        buttonPanel.add(checkoutButton);

        
        

        saveButton.addActionListener(e -> saveTemporaryInvoice());
        processButton.addActionListener(e -> openTemporaryInvoicesDialog());

        cancelButton.addActionListener(e -> {
            productTableModel.setRowCount(0);
            phoneField.setText("");
            nameField.setText("");
            membershipField.setText("");
            discountField.setText("");
            totalAmountLabel.setText("0 đ");
            amountGivenField.setText("0");

            JOptionPane.showMessageDialog(this, "Hóa đơn đã được hủy.");
        });

   
        checkoutButton.addActionListener(e -> {
            if (productTableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào trong hóa đơn. Vui lòng thêm sản phẩm trước khi thanh toán.");
                return;
            }

            try {
                double totalAmount = Double.parseDouble(totalAmountLabel.getText().replace(" đ", ""));
                double amountGiven = Double.parseDouble(amountGivenField.getText());

                if (amountGiven >= totalAmount) {
                    double change = amountGiven - totalAmount;
                    JOptionPane.showMessageDialog(this, "Thanh toán thành công. Tiền thừa: " + change + " đ");
                    cancelButton.doClick(); // Xóa thông tin hóa đơn sau khi thanh toán thành công
                } else {
                    JOptionPane.showMessageDialog(this, "Số tiền khách đưa không đủ. Vui lòng kiểm tra lại.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền hợp lệ.");
            }
        });

        infoPanel.add(buttonPanel);
        return infoPanel;
    }

    private void addMoneyButtons(JPanel panel) {
        String[] moneyValues = {"1k", "2k", "5k", "10k", "20k", "50k", "100k", "200k", "500k"};
        int[] moneyAmounts = {1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000};

        for (int i = 0; i < moneyValues.length; i++) {
            JButton moneyButton = new JButton(moneyValues[i]);
            int amount = moneyAmounts[i];

            moneyButton.setPreferredSize(new Dimension(30, 20));

            moneyButton.addActionListener(e -> {
                try {
                    double currentAmount = amountGivenField.getText().isEmpty() ? 0 : Double.parseDouble(amountGivenField.getText());
                    double newAmount = currentAmount + amount;
                    amountGivenField.setText(String.format("%.0f", newAmount));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi nhập số tiền.");
                }
            });

            panel.add(moneyButton);
        }
    }

    private double calculateTotalAmount() {
        double total = 0.0;
        for (int i = 0; i < productTableModel.getRowCount(); i++) {
            total += Double.parseDouble(productTableModel.getValueAt(i, 5).toString());
        }
        return total;
    }

    private void saveTemporaryInvoice() {
        int confirmation = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn lưu tạm hóa đơn không?", "Xác nhận lưu tạm", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Lưu tạm thành công vào bảng hóa đơn.");
        }
    }

    private void openTemporaryInvoicesDialog() {
        TemporaryInvoicesDialog dialog = new TemporaryInvoicesDialog(this);
        dialog.setVisible(true);
    }
}