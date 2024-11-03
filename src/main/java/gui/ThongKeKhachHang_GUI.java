package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;

public class ThongKeKhachHang_GUI extends JFrame {
	
	
	// vấn đề : 
	// khi chọn ngày ở JDateChooser, ngày không được chọn
	// khi chọn JRadioButton, nhóm không hoạt động
	

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_1;
    private JTable table;
   

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1440, 912);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);


        
        JLabel lblNewLabel = new JLabel("THỐNG KÊ KHÁCH HÀNG");
        lblNewLabel.setForeground(new Color(0, 0, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        
        JLabel lblNewLabel_1 = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(ThongKeKhachHang_GUI.class.getResource("/gui/poster.png"));
        Image img = originalIcon.getImage();
        Image resizedImage = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        lblNewLabel_1.setIcon(resizedIcon);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblNewLabel_1.setLabelFor(this);
        
        JLabel lblNewLabel_2 = new JLabel("Chọn thời gian");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Thêm JDateChooser để chọn ngày
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("");
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton("Khách hàng mới (0-3)");
        rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnNewRadioButton.setSelected(true);
        
        JLabel lblNewLabel_4 = new JLabel("Tổng hóa đơn");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        textField_1 = new JTextField();
        textField_1.setText("2");
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField_1.setEditable(false);
        textField_1.setColumns(10);

        // Khởi tạo JTable
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setFillsViewportHeight(true);
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {"KH0001", "Nguyễn Minh Đức", "1",  "255000"},
                {"KH0002", "Trần Minh Đức", "2",  "350000"},
            },
            new String[] {
                "Mã khách hàng", "Tên khách hàng", "Số lần mua", "Tổng tiền"
            }
        ));

        

        // Tạo JScrollPane và thêm JTable vào nó
        JScrollPane scrollPane = new JScrollPane(table);
        
        JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("Khách hàng quen (4-10)");
        rdbtnNewRadioButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        JRadioButton rdbtnNewRadioButton_1_1_1 = new JRadioButton("Khách hàng quen (4-10)");
        rdbtnNewRadioButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Thiết lập GroupLayout cho contentPane
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(90)
        					.addComponent(lblNewLabel_1)
        					.addGap(42)
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(771)
        					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(122)
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addComponent(rdbtnNewRadioButton)
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        								.addComponent(rdbtnNewRadioButton_1_1_1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
        								.addComponent(rdbtnNewRadioButton_1_1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
        							.addGap(320)
        							.addComponent(lblNewLabel_4)
        							.addGap(4)
        							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE))))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1450, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(40)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addGap(27)
        							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
        					.addGap(45)
        					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
        				.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(rdbtnNewRadioButton)
        			.addGap(2)
        			.addComponent(rdbtnNewRadioButton_1_1_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(rdbtnNewRadioButton_1_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel_4)
        				.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
        			.addGap(34)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }
}
