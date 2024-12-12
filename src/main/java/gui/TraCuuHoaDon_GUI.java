package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import entity.ChucVu;
import entity.NhanVien;
import dao.NhanVien_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDonXuat;
import entity.HoaDonNhap;
import dao.HoaDonNhap_DAO;
import dao.HoaDonXuat_DAO;
public class TraCuuHoaDon_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField search;
	private JTable table;
	private HoaDonXuat_DAO hoaDonXuatDAO;
	private HoaDonNhap_DAO hoaDonNhapDAO;
	private JTable table_1;
	private TrangChu_GUI trangChuGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TraCuuHoaDon_GUI frame = new TraCuuHoaDon_GUI();
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
	public TraCuuHoaDon_GUI() {
		
		//Khai báo DAO
		hoaDonXuatDAO = new HoaDonXuat_DAO();
		hoaDonNhapDAO = new HoaDonNhap_DAO();
		
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
		
				
		JPanel panel = new JPanel();
		panel.setBackground(new Color(244, 253, 253));
		panel.setBounds(0, 69, 1604, 833);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TRA CỨU HÓA ĐƠN");
		lblNewLabel.setForeground(new Color(10, 69, 23));
		lblNewLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
		lblNewLabel.setBounds(588, 22, 703, 70);
		ImageIcon poster = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/gui/poster.png"));
		Image scaledPoster = poster.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(scaledPoster));
		imageLabel.setBounds(477, 11, 86, 81);
		panel.add(imageLabel);
		panel.add(lblNewLabel);
		ImageIcon iconThem = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/GUI/4993253681582956831-128.png"));
		Image scaledImageThem = iconThem.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconXoa = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/GUI/320632131667326703-128.png"));
		Image scaledImageXoa = iconXoa.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconSua = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/GUI/setting-icon.png"));
		Image scaledImageSua = iconSua.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconXT = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/GUI/calendar-remove-icon.png"));
		Image scaledImageXT = iconXT.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconLuu = new ImageIcon(TraCuuKhachHang_GUI.class.getResource("/GUI/calendar-remove-icon.png"));
		Image scaledImageLuu = iconLuu.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		
		search = new JTextField();
		search.setColumns(10);
		search.setBounds(556, 335, 742, 61);
		panel.add(search);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(36, 431, 1522, 302);
		panel.add(scrollPane);
		
		
		
		JButton searchBtn = new JButton("Tìm Kiếm\r\n");
		searchBtn.setForeground(new Color(255, 255, 255));
		searchBtn.setBackground(new Color(26, 133, 94));
		searchBtn.setFont(new Font("Leelawadee UI", Font.BOLD, 22));
		searchBtn.setBounds(1328, 336, 230, 60);
		
		panel.add(searchBtn);
		
		JComboBox typeSearch = new JComboBox();
		typeSearch.setForeground(new Color(255, 255, 255));
		typeSearch.setBackground(new Color(26, 133, 94));
		typeSearch.setFont(new Font("Leelawadee UI", Font.BOLD, 22));
		typeSearch.setModel(new DefaultComboBoxModel(new String[] {"Hóa đơn nhập", "Hóa đơn xuất"}));
		typeSearch.setBounds(36, 336, 230, 60);
		panel.add(typeSearch);
		
		JComboBox typeSearch_1 = new JComboBox();
		typeSearch_1.setModel(new DefaultComboBoxModel(new String[] {"Mã hóa đơn", "Mã nhà cung cấp"}));
		typeSearch_1.setForeground(Color.WHITE);
		typeSearch_1.setFont(new Font("Leelawadee UI", Font.BOLD, 22));
		typeSearch_1.setBackground(new Color(26, 133, 94));
		typeSearch_1.setBounds(296, 336, 230, 60);
		panel.add(typeSearch_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		scrollPane_1.setBounds(36, 111, 1522, 193);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setSurrendersFocusOnKeystroke(true);
		table_1.setShowGrid(false);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setRowHeight(30);
		table_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		table_1.setBorder(null);
		DefaultTableModel tableModel_1 = new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"Mã HD", "Mã NV", "Mã KH", "Ngày Tạo", "Ma Giam Gia", "Tổng Tiền"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, Double.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
			DefaultTableModel tableModel_1_2 = new DefaultTableModel(
					new Object[][] {
						
					},
					new String[] {
						"Mã HD", "Ngày Tạo", "Ma NPP", "Tổng Tiền"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, Double.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				};
	    table_1.setModel(tableModel_1_2);
		table_1.setBackground(new Color(255, 255, 255));
		// Thiết lập font cho table và header
				Font headerFont = new Font("Leelawadee UI", Font.BOLD, 18); // Chữ to hơn cho header
				table_1.getTableHeader().setFont(headerFont); // Áp dụng cho header
				table_1.getTableHeader().setPreferredSize(new Dimension(0, 50));
				// Thiết lập màu cho header
				table_1.getTableHeader().setBackground(new Color(26, 133, 94, 196)); // Màu xanh cho header
				table_1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16)); // Font cho các dòng

				// Thiết lập chiều cao của các dòng
				table_1.setRowHeight(30); // Đặt chiều cao dòng lớn hơn
				// Áp dụng màu xen kẽ cho các dòng
				table_1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		            public Component getTableCellRendererComponent(JTable table, Object value,
		                    boolean isSelected, boolean hasFocus, int row, int column) {
		            	Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		            	if (value instanceof Double) {
		                    // Nếu giá trị là Double, không thay đổi màu nền
		                    c.setBackground(Color.WHITE);
		                } else {
		                    // Áp dụng màu nền cho các dòng
		                    if (row % 2 != 0) {
		                        c.setBackground(new Color(26, 133, 94, 94)); // Màu cho dòng chẵn
		                    } else {
		                        c.setBackground(Color.WHITE); // Màu cho dòng lẻ
		                    }
		                }
		                return c;
		            }
		        });
		     // Ngăn di chuyển cột
				table_1.getTableHeader().setReorderingAllowed(false);

		        // Ngăn nhấn vào header
				table_1.getTableHeader().addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		                
		            }
		        });
				// Thêm MouseListener để chọn toàn bộ hàng khi nhấp vào ô
				table_1.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		                int row = table_1.rowAtPoint(e.getPoint());
		                if (row >= 0) {
		                	table_1.clearSelection(); // Dọn sạch lựa chọn hiện tại
		                	table_1.setRowSelectionInterval(row, row); // Chọn hàng đã nhấp
		                    for (int i = 0; i < table_1.getColumnCount(); i++) {
		                    	table_1.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
		                            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		                                Component c = super.getTableCellRendererComponent(table_1, value, isSelected, hasFocus, row, column );
		                                if (row == table_1.getSelectedRow()) {
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

		scrollPane_1.setViewportView(table_1);
		
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowGrid(false);
		table.setBackground(new Color(255, 255, 255));
		table.setBorder(null);
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"M\u00E3 Sản Phẩm", "T\u00EAn Sản Phẩm","Loại Sản Phẩm", "Số Lượng", "Giá Bán", "Thành Tiền"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, Integer.class, Double.class, Double.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
			DefaultTableModel tableModel2 = new DefaultTableModel(
					new Object[][] {
						
					},
					new String[] {
						"M\u00E3 Sản Phẩm", "T\u00EAn Sản Phẩm","Loại Sản Phẩm", "Số Lượng", "Giá Nhập", "Thành Tiền"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, Double.class, Double.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				};
		table.setModel(tableModel2);	
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(99);
		table.getColumnModel().getColumn(2).setPreferredWidth(65);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);

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
        typeSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) typeSearch.getSelectedItem();
                if ("Hóa đơn xuất".equals(selectedType)) {
                    table.setModel(tableModel); 
                    table_1.setModel(tableModel_1);// Sử dụng tableModel cho hóa đơn xuất
                    typeSearch_1.setModel(new DefaultComboBoxModel(new String[] {"Mã hóa đơn", "Mã khách hàng", "Mã nhân viên"}));
                } else {
                    table.setModel(tableModel2);
                    table_1.setModel(tableModel_1_2);// Sử dụng tableModel2 cho hóa đơn nhập
                    typeSearch_1.setModel(new DefaultComboBoxModel(new String[] {"Mã hóa đơn", "Mã nhà phân phối"}));
                }
            }
        });

		scrollPane.setViewportView(table);
		
		
		searchBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String searchTerm = search.getText(); // Get the input from the search field
		       
		        String selectedType = (String) typeSearch_1.getSelectedItem(); // Get the selected type
		        String selectedType2 = (String) typeSearch.getSelectedItem();
		        // Clear the existing table data
		        tableModel_1.setRowCount(0);
		        tableModel_1_2.setRowCount(0);
		        ArrayList<Object[]> results;
		        
		        if ("Hóa đơn xuất".equals(selectedType2)) {
		            results = hoaDonXuatDAO.layDanhSachHoaDon(searchTerm, selectedType);
		            
		        } else {
		            results = hoaDonNhapDAO.layDanhSachHoaDon(searchTerm, selectedType);
		            
		        }
		        
		        // Check if results are empty
		        if (results.isEmpty()) {
		            // Show message when no results are found
		            JOptionPane.showMessageDialog(panel, "Không tìm thấy", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		        	if ("Hóa đơn xuất".equals(selectedType2)) {
		        		for (Object[] row : results) {
			                tableModel_1.addRow(row);
			            }
			            
			        } else {
			        	for (Object[] row : results) {
			                tableModel_1_2.addRow(row);
			            }
			            
			        }
		            // Populate the table with the search results
		            
		        }
		    }
		});

//
		table_1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table_1.rowAtPoint(e.getPoint());
		        table_1.clearSelection(); // Xóa lựa chọn hiện tại
		        table_1.setRowSelectionInterval(row, row); // Chọn hàng đã nhấp
		        
		        String selectedType = (String) typeSearch.getSelectedItem();
		        // Lấy mã hóa đơn xuất từ cột đầu tiên của hàng đã chọn
		        String maHoaDon = (String) table_1.getValueAt(row, 0); // Giả sử mã hóa đơn xuất ở cột đầu tiên
		        tableModel.setRowCount(0);
		        tableModel2.setRowCount(0);
		        
		        ArrayList<Object[]> chiTietSanPham;
		        
		        // Xác định loại hóa đơn và gọi phương thức tương ứng
		        if ("Hóa đơn xuất".equals(selectedType)) {
		            chiTietSanPham = hoaDonXuatDAO.layDanhSachChiTietSanPhamTheoMaHoaDonXuat(maHoaDon);
		            // Thêm dữ liệu vào tableModel cho hóa đơn xuất
		            for (Object[] rowData : chiTietSanPham) {
		                tableModel.addRow(rowData);
		            }
		        } else {
		            chiTietSanPham = hoaDonNhapDAO.layDanhSachChiTietSanPhamTheoMaHoaDonNhap(maHoaDon);
		            // Thêm dữ liệu vào tableModel2 cho hóa đơn nhập
		            for (Object[] rowData : chiTietSanPham) {
		                tableModel2.addRow(rowData);
		            }
		        }
		        
		        // Kiểm tra và in ra thông báo nếu không có sản phẩm nào
		        if (chiTietSanPham == null || chiTietSanPham.isEmpty()) {
		            System.out.println("Không tìm thấy sản phẩm cho mã hóa đơn: " + maHoaDon);
		        }
		    }
		});


				
		
		
	}
	public void openTrangChu() {
        TrangChu_GUI trangChu = new TrangChu_GUI();
        trangChu.setVisible(true);
        this.setVisible(false);
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
	public void openDangNhap() {
        DangNhap_GUI e = new DangNhap_GUI();
        e.setVisible(true);
        this.setVisible(false);
    }
}
