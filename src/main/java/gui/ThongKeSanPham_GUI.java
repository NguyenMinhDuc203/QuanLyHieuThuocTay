package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ThongKeSanPham_GUI extends JFrame {
	
	
	// vấn đề : 
	// khi chọn ngày ở JDateChooser, ngày không được chọn
	// khi chọn JRadioButton, nhóm không hoạt động
	

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
   

    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public ThongKeSanPham_GUI() {
    	
    	
//    	
//    	
//    	
//    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(0, 0, 1920, 1080); 
//        setSize(1920,1080);
//        contentPane = new JPanel();
//        contentPane.setBackground(new Color(26, 133, 94));
//        contentPane.setForeground(SystemColor.window);
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        setLocationRelativeTo(null);
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//        
    	
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
		
		JLabel lblNewLabel = new JLabel("THỐNG KÊ SẢN PHẨM");
		lblNewLabel.setForeground(new Color(10, 69, 23));
		lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
		lblNewLabel.setBounds(588, 22, 703, 70);
		ImageIcon poster = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/gui/poster.png"));
		Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
		imageLabel.setBounds(421, 2, 93, 92);
		panel.add(imageLabel);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(26, 133, 94, 94));
		panel_1.setBounds(26, 105, 1553, 263);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Loại sản phẩm");
		lblNewLabel_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblNewLabel_1.setBounds(177, 11, 152, 37);
		panel_1.add(lblNewLabel_1);

	    // Thiết lập font và màu cho các radio button (tuỳ chọn)
	    Font radioFont = new Font("Segoe UI", Font.PLAIN, 18);
	    Color radioColor = new Color(10, 69, 23);

	    // Tạo nhóm ButtonGroup và thêm các radio button vào nhóm
	    ButtonGroup radioGroup = new ButtonGroup();

	    panel_1.setLayout(null);
	    
	    JLabel lblNewLabel_1_2_1 = new JLabel("Số lượng bán ra");
	    lblNewLabel_1_2_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    lblNewLabel_1_2_1.setBounds(176, 105, 168, 37);
	    panel_1.add(lblNewLabel_1_2_1);
	    
	    
	    JMenu btnThongKe = new JMenu("Thống Kê");
	    btnThongKe.setMnemonic('.');
	    btnThongKe.setIcon(null);
	    btnThongKe.setOpaque(true);
	    btnThongKe.setForeground(Color.WHITE);
	    btnThongKe.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    btnThongKe.setBackground(new Color(26, 133, 94));
	    btnThongKe.setBounds(520, 149, 204, 47);
	    panel_1.add(btnThongKe);
	    
	    JComboBox comboBox = new JComboBox();
	    comboBox.setBackground(new Color(26, 133, 94));
	    comboBox.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
	    comboBox.setModel(new DefaultComboBoxModel(new String[] {"Thuốc", "Khẩu Trang", "Thực phẩm chức năng"}));
	    comboBox.setBounds(175, 53, 204, 37);
	    panel_1.add(comboBox);
	    
	    JRadioButton rdbtnNewRadioButton = new JRadioButton("0-20");
	    rdbtnNewRadioButton.setBackground(new Color(26, 133, 94));
	    rdbtnNewRadioButton.setFont(new Font("Leelawadee UI", Font.PLAIN, 15));
	    rdbtnNewRadioButton.setBounds(176, 149, 109, 37);
	    panel_1.add(rdbtnNewRadioButton);
	    
	    JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("21-50");
	    rdbtnNewRadioButton_1.setBackground(new Color(26, 133, 94));
	    rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    rdbtnNewRadioButton_1.setBounds(310, 149, 109, 34);
	    panel_1.add(rdbtnNewRadioButton_1);
	    
	    JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("51-100");
	    rdbtnNewRadioButton_2.setBackground(new Color(26, 133, 94));
	    rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    rdbtnNewRadioButton_2.setBounds(176, 206, 109, 37);
	    panel_1.add(rdbtnNewRadioButton_2);
	    
	    JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("> 100");
	    rdbtnNewRadioButton_3.setBackground(new Color(26, 133, 94));
	    rdbtnNewRadioButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    rdbtnNewRadioButton_3.setBounds(310, 206, 109, 37);
	    panel_1.add(rdbtnNewRadioButton_3);
	    
	    JLabel lblNewLabel_1_1 = new JLabel("Hạn sử dụng");
	    lblNewLabel_1_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    lblNewLabel_1_1.setBounds(522, 11, 152, 37);
	    panel_1.add(lblNewLabel_1_1);
	    
	    JComboBox comboBox_1 = new JComboBox();
	    comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Đã hết hạn", "Còn ít hơn 3 tháng", "Còn từ 3 đến 6 tháng", "Còn hơn 6 tháng"}));
	    comboBox_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
	    comboBox_1.setBackground(new Color(26, 133, 94));
	    comboBox_1.setBounds(520, 53, 204, 37);
	    panel_1.add(comboBox_1);
	    
	    JLabel lblNewLabel_1_1_1 = new JLabel("Tống số lượng sản phẩm:");
	    lblNewLabel_1_1_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    lblNewLabel_1_1_1.setBounds(1040, 53, 240, 37);
	    panel_1.add(lblNewLabel_1_1_1);
	    
	    textField = new JTextField();
	    textField.setEditable(false);
	    textField.setBounds(1276, 53, 152, 37);
	    panel_1.add(textField);
	    textField.setColumns(10);
	    
	    JLabel lblNewLabel_1_1_1_1 = new JLabel("Tống số đã bán ra:");
	    lblNewLabel_1_1_1_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    lblNewLabel_1_1_1_1.setBounds(1040, 105, 227, 37);
	    panel_1.add(lblNewLabel_1_1_1_1);
	    
	    JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Số sản phẩm còn hạn:");
	    lblNewLabel_1_1_1_1_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    lblNewLabel_1_1_1_1_1.setBounds(1040, 159, 212, 37);
	    panel_1.add(lblNewLabel_1_1_1_1_1);
	    
	    textField_1 = new JTextField();
	    textField_1.setEditable(false);
	    textField_1.setColumns(10);
	    textField_1.setBounds(1276, 101, 152, 37);
	    panel_1.add(textField_1);
	    
	    textField_2 = new JTextField();
	    textField_2.setEditable(false);
	    textField_2.setColumns(10);
	    textField_2.setBounds(1276, 159, 152, 37);
	    panel_1.add(textField_2);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(26, 385, 1553, 408);
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
	    		{null, null, null, null, null, null},
	    	},
	    	new String[] {
	    		"M\u00E3 S\u1EA3n Ph\u1EA9m", "T\u00EAn S\u1EA3n Ph\u1EA9m", "Lo\u1EA1i S\u1EA3n ph\u1EA9m", "H\u1EA1n S\u1EED D\u1EE5ng", "S\u1ED1 L\u01B0\u1EE3ng \u0110\u00E3 B\u00E1n", "T\u1ED3n Kho"
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


		scrollPane.setViewportView(table);
		
    }
}
