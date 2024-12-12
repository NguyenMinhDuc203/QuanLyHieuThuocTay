package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

import dao.KhachHang_DAO;

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

public class ThongKeKhachHang_GUI extends JFrame {
	
	
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_1;
    private JTable table;
<<<<<<< HEAD
   private TrangChu_GUI trangChuGUI;
=======
    private KhachHang_DAO dao;
>>>>>>> 1bd206c4b2918ffccdcbc2388bd48674f8f06b1d

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ThongKeKhachHang_GUI frame = new ThongKeKhachHang_GUI();
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
    public ThongKeKhachHang_GUI() {
    	
    	dao = new KhachHang_DAO();
    	
    	
    	
    	
    	

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
				trangChuGUI = new TrangChu_GUI();
				JMenuBar menuBar = trangChuGUI.createMenuBar();
				menuBar.setBorderPainted(false);
				menuBar.setOpaque(true);
				menuBar.setBackground(new Color(26, 133, 94));
				menuBar.setBounds(0, 0, 1395, 70);
				contentPane.add(menuBar);

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
		
		JLabel lblNewLabel = new JLabel("THỐNG KÊ KHÁCH HÀNG");
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
		lblNewLabel_1.setBounds(189, 10, 294, 37);
		panel_1.add(lblNewLabel_1);
		

	    // Tạo các JRadioButton
	    JRadioButton rdbtnOption1 = new JRadioButton("Khách hàng mới");
	    JRadioButton rdbtnOption2 = new JRadioButton("Khách hàng quen");
	    JRadioButton rdbtnOption3 = new JRadioButton("Khách hàng thân thiết");

	    // Thiết lập font và màu cho các radio button (tuỳ chọn)
	    Font radioFont = new Font("Segoe UI", Font.PLAIN, 18);
	    Color radioColor = new Color(10, 69, 23);
	    rdbtnOption1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    rdbtnOption1.setForeground(radioColor);
	    rdbtnOption2.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    rdbtnOption2.setForeground(radioColor);
	    rdbtnOption3.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    rdbtnOption3.setForeground(radioColor);

	    // Tạo nhóm ButtonGroup và thêm các radio button vào nhóm
	    ButtonGroup radioGroup = new ButtonGroup();
	    radioGroup.add(rdbtnOption1);
	    radioGroup.add(rdbtnOption2);
	    radioGroup.add(rdbtnOption3);

	    // Đặt vị trí và thêm các radio button vào panel_1
	    rdbtnOption1.setBounds(226, 53, 244, 30);  // Tùy chỉnh vị trí và kích thước
	    rdbtnOption2.setBounds(226, 112, 244, 30);
	    rdbtnOption3.setBounds(226, 172, 244, 30);

	    panel_1.setLayout(null);  // Sử dụng layout null để dễ kiểm soát vị trí
	    panel_1.add(rdbtnOption1);
	    panel_1.add(rdbtnOption2);
	    panel_1.add(rdbtnOption3);
	    
	 
	    
	    JLabel lblNewLabel_1_2_1 = new JLabel("Số lượng khách hàng");
	    lblNewLabel_1_2_1.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
	    lblNewLabel_1_2_1.setBounds(992, 105, 215, 37);
	    panel_1.add(lblNewLabel_1_2_1);
	    
	    textField_1 = new JTextField();
	    textField_1.setEditable(false);
	    textField_1.setColumns(10);
	    textField_1.setBounds(1003, 165, 244, 37);
	    panel_1.add(textField_1);

	    
	    JButton btnNewButton = new JButton("Thống Kê");
        btnNewButton.setBounds(700, 290, 158, 62);
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
	    		"Mã khách hàng", "Tên khách hàng", "Số lần mua", "Tổng tiền"
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
		btnNewButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Lấy ngày được chọn từ JDateChooser
		        
		        List<Object[]> result = null;

		        // Kiểm tra lựa chọn radio button
		        if (rdbtnOption1.isSelected()) {
		            // Lấy dữ liệu thống kê cho khách hàng mới
		            result = dao.thongKeSoLanMuaBang1();
		        } else if (rdbtnOption2.isSelected()) {
		            // Lấy dữ liệu thống kê cho khách hàng quen
		            result = dao.thongKeSoLanMuaLonHon2NhoHon5();
		        } else if (rdbtnOption3.isSelected()) {
		            // Lấy dữ liệu thống kê cho khách hàng thân thiết
		            result = dao.thongKeSoLanMuaLonHon5();
		        }

		        // Cập nhật bảng với dữ liệu lấy được
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

		        // Biến để tính tổng số lượng và tổng tiền
		        int totalCustomers = 0;

		        // Lặp qua kết quả và thêm vào bảng
		        if (result != null) {
		            for (Object[] row : result) {
		                model.addRow(row);  // Thêm một hàng vào bảng

		                // Tính tổng số tiền từ kết quả (giả sử cột "Tổng tiền" ở vị trí index 3)
		                totalCustomers++;
		            }

		            // Cập nhật các JLabel hiển thị tổng số khách hàng và tổng tiền
		            textField_1.setText("" + totalCustomers); // Hiển thị tổng tiền
		        } else {
		            System.out.println("Không có dữ liệu");
		        }
		    }
		});

    }
    
}
