package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemporaryInvoicesDialog extends JDialog {
    private JTable productTable;
    private JTable invoiceTable;
    private DefaultTableModel productTableModel;
    private DefaultTableModel invoiceTableModel;

    public TemporaryInvoicesDialog(JFrame parent) {
        super(parent, "Xử lí đơn lưu tạm", true);
        setLayout(new BorderLayout(10, 10));
        setSize(800, 550);
        setLocationRelativeTo(parent);

        // Tạo tiêu đề
        JLabel titleLabel = new JLabel("DANH SÁCH HÓA ĐƠN ĐÃ LƯU TẠM", JLabel.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Panel chính chứa các bảng
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        add(mainPanel, BorderLayout.CENTER);

        // Bảng hóa đơn
        invoiceTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {"Mã hóa đơn", "Tên khách hàng", "Ngày tạo"}
        );
        invoiceTable = new JTable(invoiceTableModel);
        JScrollPane invoiceScrollPane = new JScrollPane(invoiceTable);
        invoiceScrollPane.setBorder(BorderFactory.createTitledBorder("Hóa đơn đã lưu"));
        mainPanel.add(invoiceScrollPane);

        // Bảng chi tiết sản phẩm
        productTableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {"Tên Sản Phẩm", "Số Lượng"}
        );
        productTable = new JTable(productTableModel);
        productTable.getColumnModel().getColumn(0).setPreferredWidth(138);
        productTable.getColumnModel().getColumn(1).setPreferredWidth(130);
        JScrollPane productScrollPane = new JScrollPane(productTable);
        productScrollPane.setBorder(BorderFactory.createTitledBorder("Chi tiết sản phẩm"));
        mainPanel.add(productScrollPane);

        // Panel chứa các nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton processButton = new JButton("Xử lí đơn");
        JButton deleteButton = new JButton("Xóa đơn");
        JButton deleteAllButton = new JButton("Xóa toàn bộ đơn lưu tạm");

        buttonPanel.add(processButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(deleteAllButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Thêm hành động cho nút "Xử lí đơn"
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSelectedInvoice();
            }
        });

        // Thêm hành động cho nút "Xóa đơn"
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedInvoice();
            }
        });

        // Thêm hành động cho nút "Xóa toàn bộ đơn lưu tạm"
        deleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAllInvoices();
            }
        });
    }

    // Phương thức thêm dòng dữ liệu vào bảng hóa đơn
    public void addInvoiceRow(Object[] row) {
        invoiceTableModel.addRow(row);
    }

    // Phương thức thêm dòng dữ liệu vào bảng sản phẩm
    public void addProductRow(Object[] row) {
        productTableModel.addRow(row);
    }

    // Phương thức xử lý hóa đơn được chọn
    private void processSelectedInvoice() {
        int selectedRow = invoiceTable.getSelectedRow(); // Lấy dòng được chọn
        if (selectedRow != -1) { // Kiểm tra nếu có dòng được chọn
            // Lấy dữ liệu từ dòng đã chọn trong bảng hóa đơn
            Object[] rowData = new Object[invoiceTableModel.getColumnCount()];
            for (int i = 0; i < invoiceTableModel.getColumnCount(); i++) {
                rowData[i] = invoiceTableModel.getValueAt(selectedRow, i);
            }

          
            addProductRow(new Object[] {rowData[1], "1"}); 

        
            invoiceTableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để xử lý.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Phương thức xóa đơn được chọn
    private void deleteSelectedInvoice() {
        int selectedRow = invoiceTable.getSelectedRow();
        if (selectedRow != -1) { // Kiểm tra nếu có dòng được chọn
            int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn xóa đơn đã chọn không?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION
            );
            if (confirmation == JOptionPane.YES_OPTION) {
                invoiceTableModel.removeRow(selectedRow); // Xóa dòng đã chọn
                JOptionPane.showMessageDialog(this, "Đơn đã được xóa.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một đơn để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Phương thức xóa toàn bộ hóa đơn
    private void deleteAllInvoices() {
        int confirmation = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa toàn bộ hóa đơn lưu tạm không?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmation == JOptionPane.YES_OPTION) {
            invoiceTableModel.setRowCount(0); // Xóa toàn bộ dòng khỏi bảng hóa đơn
            JOptionPane.showMessageDialog(this, "Toàn bộ hóa đơn lưu tạm đã được xóa.");
        }
    }
}
