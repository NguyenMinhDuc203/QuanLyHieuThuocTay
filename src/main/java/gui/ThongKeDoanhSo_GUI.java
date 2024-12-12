package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

import dao.HoaDonXuat_DAO;
import entity.SanPham;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ListSelectionModel;

public class ThongKeDoanhSo_GUI extends JFrame {
	
	
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTable table;
    private HoaDonXuat_DAO dao;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ThongKeDoanhSo_GUI frame = new ThongKeDoanhSo_GUI();
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
    public ThongKeDoanhSo_GUI() {
    	
 
    	dao = new HoaDonXuat_DAO();
    	
    	
    	
    	
    	
    	
    	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080); 
        setSize(1920,1080);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(26, 133, 94));
        contentPane.setForeground(SystemColor.window);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Menu
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setOpaque(true);
		menuBar.setBackground(new Color(26, 133, 94));
		menuBar.setBounds(0, 0, 1079, 70);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu(" Trang Chủ");
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.setOpaque(true);
		mnNewMenu.setBackground(new Color(26, 133, 94));
		mnNewMenu.setForeground(new Color(255, 255, 255));
		mnNewMenu.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		ImageIcon icon = new ImageIcon(TrangChu_GUI.class.getResource("/gui/house-solid.png"));
		Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu.setIcon(new ImageIcon(scaledImage));
		mnNewMenu.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
		menuBar.add(mnNewMenu);

		
		JMenu mnNewMenu_1 = new JMenu(" Quản Lý");
		mnNewMenu_1.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_1.setOpaque(true);
		mnNewMenu_1.setBackground(new Color(26, 133, 94));
		mnNewMenu_1.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		mnNewMenu_1.setForeground(new Color(255, 255, 255));
		ImageIcon icon1 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/list-check-solid.png"));
		Image scaledImage1 = icon1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu_1.setIcon(new ImageIcon(scaledImage1));
		mnNewMenu_1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
		menuBar.add(mnNewMenu_1);
				
		JMenuItem mntmNewMenuItem = new JMenuItem("Sản Phẩm");
		mntmNewMenuItem.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNhnVin = new JMenuItem("Nhân Viên");
		mntmNhnVin.setForeground(new Color(255, 255, 255));
		mntmNhnVin.setBackground(new Color(26, 133, 94));
		mntmNhnVin.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_1.add(mntmNhnVin);
		
		JMenuItem mntmKhchHng = new JMenuItem("Khách Hàng");
		mntmKhchHng.setForeground(new Color(255, 255, 255));
		mntmKhchHng.setBackground(new Color(26, 133, 94));
		mntmKhchHng.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_1.add(mntmKhchHng);
		
		JMenu mnNewMenu_2_1 = new JMenu(" Bán Hàng");
		mnNewMenu_2_1.setBackground(new Color(26, 133, 94));
		mnNewMenu_2_1.setOpaque(true);
		mnNewMenu_2_1.setForeground(new Color(255, 255, 255));
		mnNewMenu_2_1.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		ImageIcon icon2_1 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/cart-shopping-solid.png"));
		Image scaledImage2_1 = icon2_1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu_2_1.setIcon(new ImageIcon(scaledImage2_1));
		mnNewMenu_2_1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
		menuBar.add(mnNewMenu_2_1);
		
		JMenu mnNewMenu_2 = new JMenu(" Thống Kê");
		mnNewMenu_2.setBackground(new Color(26, 133, 94));
		mnNewMenu_2.setOpaque(true);
		mnNewMenu_2.setForeground(new Color(255, 255, 255));
		mnNewMenu_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		ImageIcon icon2 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/clipboard-solid.png"));
		Image scaledImage2 = icon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu_2.setIcon(new ImageIcon(scaledImage2));
		mnNewMenu_2.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Doanh Số");
		mntmNewMenuItem_1.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_1.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_1_3 = new JMenuItem("Nhân Viên");
		mntmNewMenuItem_1_3.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_1_3.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2.add(mntmNewMenuItem_1_3);
		
		JMenuItem mntmNewMenuItem_1_2 = new JMenuItem("Khách Hàng");
		mntmNewMenuItem_1_2.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_1_2.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2.add(mntmNewMenuItem_1_2);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("Sản Phẩm");
		mntmNewMenuItem_1_1.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_1_1.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2.add(mntmNewMenuItem_1_1);
		
		JMenu mnNewMenu_2_2 = new JMenu(" Tra Cứu   ");
		mnNewMenu_2_2.setBackground(new Color(10, 69, 23));
		mnNewMenu_2_2.setOpaque(true);
		mnNewMenu_2_2.setForeground(new Color(255, 255, 255));
		mnNewMenu_2_2.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		ImageIcon icon2_2 = new ImageIcon(TrangChu_GUI.class.getResource("/gui/circle-question-solid.png"));
		Image scaledImage2_2 = icon2_2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		mnNewMenu_2_2.setIcon(new ImageIcon(scaledImage2_2));
		mnNewMenu_2_2.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
		menuBar.add(mnNewMenu_2_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Sản Phẩm");
		mntmNewMenuItem_2.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_2.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_2_2 = new JMenuItem("Nhân Viên");
		mntmNewMenuItem_2_2.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_2_2.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2_2.add(mntmNewMenuItem_2_2);
		
		JMenuItem mntmNewMenuItem_2_1_1 = new JMenuItem("Hóa Đơn");
		mntmNewMenuItem_2_1_1.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_2_1_1.setForeground(Color.WHITE);
		mntmNewMenuItem_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2_2.add(mntmNewMenuItem_2_1_1);
		
		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("Khách Hàng");
		mntmNewMenuItem_2_1.setBackground(new Color(26, 133, 94));
		mntmNewMenuItem_2_1.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		mnNewMenu_2_2.add(mntmNewMenuItem_2_1);
        ImageIcon originalIcon = new ImageIcon(ThongKeKhachHang_GUI.class.getResource("/gui/poster.png"));
        Image img = originalIcon.getImage();
        Image resizedImage = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Thiết lập GroupLayout cho contentPane
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 1901, Short.MAX_VALUE)
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 1038, Short.MAX_VALUE)
        );
    	
		JPanel panel = new JPanel();
		panel.setBackground(new Color(244, 253, 253));
		panel.setBounds(0, 69, 1604, 833);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THỐNG KÊ DOANH SỐ");
		lblNewLabel.setForeground(new Color(10, 69, 23));
		lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
		lblNewLabel.setBounds(588, 22, 703, 70);
		ImageIcon poster = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/gui/poster.png"));
		Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
		imageLabel.setBounds(385, 10, 93, 92);
		panel.add(imageLabel);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(26, 133, 94, 94));
		panel_1.setBounds(0, 105, 1604, 263);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Chọn phương thức thống kê");
		lblNewLabel_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblNewLabel_1.setBounds(176, 10, 294, 37);
		panel_1.add(lblNewLabel_1);
		

	    // Tạo các JRadioButton
	    JRadioButton rdbtnOption1 = new JRadioButton("Theo ngày");
	    JRadioButton rdbtnOption2 = new JRadioButton("Theo tháng");

	    // Thiết lập font và màu cho các radio button (tuỳ chọn)
	    Font radioFont = new Font("Segoe UI", Font.PLAIN, 18);
	    Color radioColor = new Color(10, 69, 23);
	    rdbtnOption1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    rdbtnOption1.setForeground(radioColor);
	    rdbtnOption2.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    rdbtnOption2.setForeground(radioColor);

	    // Tạo nhóm ButtonGroup và thêm các radio button vào nhóm
	    ButtonGroup radioGroup = new ButtonGroup();
	    radioGroup.add(rdbtnOption1);
	    radioGroup.add(rdbtnOption2);
	    // Đặt vị trí và thêm các radio button vào panel_1
	    rdbtnOption1.setBounds(226, 72, 244, 30);  // Tùy chỉnh vị trí và kích thước
	    rdbtnOption2.setBounds(226, 139, 244, 30);
	    rdbtnOption1.setSelected(true);  // Đặt mặc định radio button 1 được chọn

	    panel_1.setLayout(null);  // Sử dụng layout null để dễ kiểm soát vị trí
	    panel_1.add(rdbtnOption1);
	    panel_1.add(rdbtnOption2);
	    
	    JLabel lblNewLabel_1_1 = new JLabel("Chọn thời gian");
	    lblNewLabel_1_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    lblNewLabel_1_1.setBounds(864, 10, 152, 37);
	    panel_1.add(lblNewLabel_1_1);
	    
	    JDateChooser dateChooser = new JDateChooser();
	    dateChooser.setDateFormatString("dd/MM/yyyy");
	    dateChooser.getCalendarButton().setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    dateChooser.setBounds(1026, 10, 307, 37);
	    panel_1.add(dateChooser);
	    
	    JLabel lblNewLabel_1_2 = new JLabel("Kết quả");
	    lblNewLabel_1_2.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    lblNewLabel_1_2.setBounds(864, 53, 76, 37);
	    panel_1.add(lblNewLabel_1_2);
	    
	    JLabel lblNewLabel_1_2_1 = new JLabel("Số lượng hóa đơn");
	    lblNewLabel_1_2_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    lblNewLabel_1_2_1.setBounds(890, 169, 168, 37);
	    panel_1.add(lblNewLabel_1_2_1);
	    
	    JLabel lblNewLabel_1_2_1_1 = new JLabel("Tổng số tiền");
	    lblNewLabel_1_2_1_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    lblNewLabel_1_2_1_1.setBounds(890, 105, 158, 37);
	    panel_1.add(lblNewLabel_1_2_1_1);
	    
	    textField = new JTextField();
	    textField.setEditable(false);
	    textField.setBounds(1089, 114, 244, 37);
	    panel_1.add(textField);
	    textField.setColumns(10);
	    
	    textField_1 = new JTextField();
	    textField_1.setEditable(false);
	    textField_1.setColumns(10);
	    textField_1.setBounds(1089, 174, 244, 37);
	    panel_1.add(textField_1);
	    
	    
//	    JMenu btnThongKe = new JMenu("Thống Kê");
//	    btnThongKe.setMnemonic('.');
//	    btnThongKe.setIcon(null);
//	    btnThongKe.setOpaque(true);
//	    btnThongKe.setForeground(Color.WHITE);
//	    btnThongKe.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
//	    btnThongKe.setBackground(new Color(46, 139, 87));
//	    btnThongKe.setBounds(615, 218, 152, 45);
//	    panel_1.add(btnThongKe);

	    JButton btnNewButton = new JButton("Thống Kê");
        btnNewButton.setBounds(600, 290, 158, 62);
        panel.add(btnNewButton);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton.setBackground(new Color(46, 139, 87));
        btnNewButton.setForeground(Color.WHITE);
	    
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(0, 372, 1604, 461);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowGrid(false);
		table.setBackground(new Color(26, 133, 94, 196));
		table.setBorder(null);
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"M\u00E3 NV", "T\u00EAn NV", "SDT", "Tr\u00ECnh \u0111\u1ED9", "CMDN", "Email"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
	    table.setModel(new DefaultTableModel(
	    	new Object[][] {
	    	},
	    	new String[] {
	    		"Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Ngày mua", "Tổng tiền"
	    	}
	    ));

		// Thiết lập font cho table và header
		Font headerFont = new Font("Leelawadee UI", Font.BOLD, 18); // Chữ to hơn cho header
		table.getTableHeader().setFont(headerFont); // Áp dụng cho header
		table.getTableHeader().setPreferredSize(new Dimension(0, 50));
		// Thiết lập màu cho header
        table.getTableHeader().setBackground(new Color(26, 133, 94, 196)); // Màu xanh cho header
		table.setFont(new Font("Leelawadee UI", Font.PLAIN, 16)); // Font cho các dòng

		// Thiết lập chiều cao của các dòng
		table.setRowHeight(30); // Đặt chiều cao dòng lớn hơn
		// Áp dụng màu xen kẽ cho các dòng
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
            	Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (row % 2 != 0) {
                    c.setBackground(new Color(26, 133, 94, 94)); // Màu cho dòng chẵn
                } else {
                    c.setBackground(Color.WHITE); // Màu cho dòng lẻ
                }
                return c;
            }
        });
     // Ngăn di chuyển cột
        table.getTableHeader().setReorderingAllowed(false);

        // Ngăn nhấn vào header
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });
        
        mnNewMenu.addActionListener(e -> openTrangChu());
		mntmNewMenuItem.addActionListener(e -> openQuanLySanPham());
		mntmNhnVin.addActionListener(e -> openQuanLyNhanVien());
		mntmKhchHng.addActionListener(e -> openQuanLyKhachHang());
		mnNewMenu_2_1.addActionListener(e -> openBanHang());
		//mntmNewMenuItem_1.addActionListener(e -> openThongKeDoanhSo());
		mntmNewMenuItem_1_3.addActionListener(e -> openThongKeNhanVien());
		mntmNewMenuItem_1_2.addActionListener(e -> openThongKeKhachHang());
		mntmNewMenuItem_1_1.addActionListener(e -> openThongKeSanPham());
		mntmNewMenuItem_2.addActionListener(e -> openTraCuuSanPham());
		mntmNewMenuItem_2_2.addActionListener(e -> openTraCuuNhanVien());
		mntmNewMenuItem_2_1.addActionListener(e -> openTraCuuKhachHang());
		mntmNewMenuItem_2_1_1.addActionListener(e -> openTraCuuHoaDon());
		
        // Thiết lập chế độ chọn hàng
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Thêm MouseListener để chọn toàn bộ hàng khi nhấp vào ô
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                	table.clearSelection(); // Dọn sạch lựa chọn hiện tại
                    table.setRowSelectionInterval(row, row); // Chọn hàng đã nhấp
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        table.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column );
                                if (row == table.getSelectedRow()) {
                                    c.setBackground(new Color(10, 69, 23)); // Màu cho hàng được chọn
                                    c.setForeground(Color.WHITE);
                                } else {
                                	if (row % 2 != 0) {
                                        c.setBackground(new Color(26, 133, 94, 94)); // Màu cho dòng chẵn
                                        c.setForeground(Color.BLACK);
                                    } else {
                                        c.setBackground(Color.WHITE); // Màu cho dòng lẻ
                                        c.setForeground(Color.BLACK);
                                    }
                                }
                                return c;
                            }
                        });
                    } // Màu cho dòng lẻ
                }
            }
        });
        


        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate selectedDate = LocalDate.ofInstant(dateChooser.getDate().toInstant(), ZoneId.systemDefault());

                List<Object[]> result = null;
                if (rdbtnOption1.isSelected()) {
                    // Fetch data for the specific day
                    result = dao.thongKeDoanhSoTheoNgay(selectedDate);
                } else if (rdbtnOption2.isSelected()) {
                    // Fetch data for the specific month
                    result = dao.thongKeDoanhSoTheoThang(selectedDate);
                }

                // Update the table with the retrieved data
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Clear the existing table data

                // Variables to store total quantity and total amount
                int totalQuantity = 0;
                double totalAmount = 0.0;

                // Loop through the result and add it to the table
                if (result != null) {
                    for (Object[] row : result) {
                        model.addRow(row);
                        
                        // Calculate the total quantity and total amount from the result
                        double amount = (Double) row[4];  // Assuming the 5th column ("Tổng tiền") is at index 4
                        totalAmount += amount;
                        totalQuantity++;
                    }

                    // Update the JLabel values
                    textField.setText(""+totalQuantity);
                    textField_1.setText("" + totalAmount);
                } else {
                    System.out.println("Không có dữ liệu");
                }
            }
        });
		scrollPane.setViewportView(table);
		
    }
    public void openTrangChu() {
        TrangChu_GUI trangChu = new TrangChu_GUI();
        trangChu.setVisible(true);
    }
	
	public void openQuanLySanPham() {
        QuanLySanPham_GUI quanLySanPham = new QuanLySanPham_GUI();
        quanLySanPham.setVisible(true);
        this.setVisible(false);
    }
	public void openQuanLyNhanVien() {
        QuanLyNhanVien_GUI quanLyNhanVien = new QuanLyNhanVien_GUI();
        quanLyNhanVien.setVisible(true);
        this.setVisible(false);
    }
	public void openQuanLyKhachHang() {
        QuanLyKhachHang_GUI quanLyKhachHang = new QuanLyKhachHang_GUI();
        quanLyKhachHang.setVisible(true);
        this.setVisible(false);
    }
	public void openBanHang() {
        BanHang_GUI banHang = new BanHang_GUI();
        banHang.setVisible(true);
        this.setVisible(false);
    }
	public void openThongKeDoanhSo() {
        ThongKeDoanhSo_GUI thongKeDoanhSo = new ThongKeDoanhSo_GUI();
        thongKeDoanhSo.setVisible(true);
        this.setVisible(false);
    }
	public void openThongKeNhanVien() {
        ThongKeNhanVien_GUI thongKeNhanVien = new ThongKeNhanVien_GUI();
        thongKeNhanVien.setVisible(true);
        this.setVisible(false);
    }
	public void openThongKeKhachHang() {
        ThongKeKhachHang_GUI e = new ThongKeKhachHang_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
	public void openThongKeSanPham() {
        ThongKeSanPham_GUI e = new ThongKeSanPham_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
	public void openTraCuuSanPham() {
        TraCuuSanPham_GUI e = new TraCuuSanPham_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
	public void openTraCuuNhanVien() {
        TraCuuNhanVien_GUI e = new TraCuuNhanVien_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
	public void openTraCuuKhachHang() {
        TraCuuKhachHang_GUI e = new TraCuuKhachHang_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
	public void openTraCuuHoaDon() {
		TraCuuHoaDon_GUI e = new TraCuuHoaDon_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
	public void openDangNhap() {
        DangNhap_GUI e = new DangNhap_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
}
