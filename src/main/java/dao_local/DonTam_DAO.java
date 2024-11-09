package dao_local;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import local.ChiTietDonTam;
import local.DonTam;

public class DonTam_DAO {
    private static final String FILE_PATH = "./dontam.txt";  // Đường dẫn đến file txt

    // Phương thức để lưu thông tin DonTam vào file
    public void saveToFile(DonTam donTam) {
        BufferedWriter writer = null;
        try {
            // Đường dẫn đến file
            String filePath = "./donTam.txt";
            
            // Tạo đối tượng File và FileWriter
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile(); // Nếu file không tồn tại, tạo mới
            }

            // Khởi tạo BufferedWriter
            writer = new BufferedWriter(new FileWriter(file, true)); // true để thêm vào cuối file

            // Ghi thông tin hóa đơn
            writer.write(donTam.getMaHoaDon() + "," + donTam.getTenKhachHang() + "," + donTam.getNgayTaoHoaDon().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            writer.newLine();
            
            // Ghi thông tin các chi tiết đơn hàng
            List<ChiTietDonTam> chiTietDonTamList = donTam.getDonHang();
            writer.newLine();
            
            for (ChiTietDonTam chiTiet : chiTietDonTamList) {
                writer.write(chiTiet.getMaSanPham() + "," + chiTiet.getTenSanPham() + "," + chiTiet.getSoLuong() + "," + chiTiet.getDonGia());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close(); // Đảm bảo đóng BufferedWriter sau khi sử dụng
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức để đọc thông tin từ file và trả về danh sách DonTam
    public List<DonTam> readFromFile() {
        BufferedReader reader = null;
        List<DonTam> donTamList = new ArrayList<>();
        
        try {
            // Đường dẫn đến file
            String filePath = "./donTam.txt";
            
            // Tạo đối tượng File và BufferedReader
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File không tồn tại.");
                return donTamList;
            }

            reader = new BufferedReader(new FileReader(file));
            String line;
            DonTam donTam = null;
            List<ChiTietDonTam> chiTietDonTamList = null;
            
            while ((line = reader.readLine()) != null) {
                // Nếu dòng bắt đầu bằng mã hóa đơn, tạo mới đối tượng DonTam
                if (line.contains(",")) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) { // Đây là dòng chứa thông tin hóa đơn
                        if (donTam != null) {
                            donTam.setDonHang(chiTietDonTamList);
                            donTamList.add(donTam); // Thêm DonTam đã đọc vào danh sách
                        }

                        // Tạo mới DonTam
                        donTam = new DonTam(parts[0], parts[1], LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")), new ArrayList<>());
                        chiTietDonTamList = new ArrayList<>();
                    } else if (parts.length == 4) { // Đây là dòng chứa chi tiết đơn hàng
                        ChiTietDonTam chiTiet = new ChiTietDonTam(parts[0], parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]));
                        chiTietDonTamList.add(chiTiet);
                    }
                }
            }
            // Thêm DonTam cuối cùng vào danh sách
            if (donTam != null) {
                donTam.setDonHang(chiTietDonTamList);
                donTamList.add(donTam);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close(); // Đảm bảo đóng BufferedReader sau khi sử dụng
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return donTamList;
    }
    

    // Phương thức lấy tất cả các đơn tạm từ file
    public List<DonTam> getAllDonTam() {
        List<DonTam> donTamList = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            // Bỏ qua dòng đầu tiên (tiêu đề cột)
            reader.readLine();
            
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length == 3) {
                    String maHoaDon = columns[0].trim();
                    String tenKhachHang = columns[1].trim();
                    String ngayTaoHoaDon = columns[2].trim();
                    
                    DonTam donTam = new DonTam(maHoaDon, tenKhachHang, LocalDate.parse(ngayTaoHoaDon, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    donTamList.add(donTam);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return donTamList;
    }
    public DonTam getDonTamByMaHoaDon(String maHoaDon) {
        System.out.println("Tìm DonTam với mã hóa đơn: " + maHoaDon);

        DonTam donTam = null;
        List<ChiTietDonTam> donHang = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean isCorrectInvoice = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                // Kiểm tra dòng chứa mã hóa đơn
                if (parts.length == 3 && parts[0].equals(maHoaDon)) {
                    isCorrectInvoice = true;
                    donTam = new DonTam(maHoaDon);

                } 
                // Nếu đã xác định đúng mã hóa đơn, đọc chi tiết đơn hàng
                else if (isCorrectInvoice && parts.length == 4) {
                    ChiTietDonTam chiTiet = new ChiTietDonTam(
                        parts[0].trim(),               // Mã sản phẩm
                        parts[1].trim(),               // Tên sản phẩm
                        Integer.parseInt(parts[2].trim()),  // Số lượng
                        Double.parseDouble(parts[3].trim()) // Đơn giá
                    );
                    donHang.add(chiTiet);
                    System.out.println("Đã thêm chi tiết: " + chiTiet.getMaSanPham());
                } 
                // Kết thúc đọc chi tiết nếu gặp mã hóa đơn mới
                else if (parts.length == 3 && !parts[0].equals(maHoaDon)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Kiểm tra danh sách chi tiết
        if (donHang.isEmpty()) {
            System.out.println("Danh sách chi tiết đơn hàng rỗng cho mã hóa đơn: " + maHoaDon);
        } else {
            System.out.println("Số lượng chi tiết đơn hàng tìm thấy: " + donHang.size());
        }

        // Gán danh sách chi tiết cho DonTam và trả về
        if (donTam != null) {
            donTam.setDonHang(donHang);
        }
        return donTam;
    }

 // Phương thức xóa đơn tạm theo mã hóa đơn
    public boolean xoaDonTam(String maHoaDon) {
        File file = new File(FILE_PATH);
        
        // Kiểm tra xem file có tồn tại không
        if (!file.exists()) {
            System.out.println("File không tồn tại.");
            return false;
        }
        
        try {
            // Đọc tất cả dữ liệu từ file vào danh sách
            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            boolean found = false;  // Biến kiểm tra xem có tìm thấy mã hóa đơn không
            List<String> newLines = new ArrayList<>(); // Danh sách chứa các dòng còn lại
            boolean deleting = false; // Cờ kiểm tra xem có đang xóa không
            
            for (String line : lines) {
                if (line.contains(maHoaDon)) {
                    // Nếu tìm thấy mã hóa đơn cần xóa, bắt đầu xóa tất cả các chi tiết liên quan
                    found = true;
                    deleting = true; // Bắt đầu xóa các chi tiết của đơn tạm này
                }

                if (deleting) {
                    // Loại bỏ các dòng liên quan đến đơn tạm nếu đang trong chế độ xóa
                    continue;
                }

                // Nếu gặp mã hóa đơn khác thì dừng việc xóa và giữ lại các dòng còn lại
                if (found && !line.contains("HD") && !line.contains(maHoaDon)) {
                    deleting = false; // Kết thúc việc xóa khi gặp mã hóa đơn tiếp theo
                }

                // Giữ lại các dòng không phải chi tiết đơn tạm cần xóa
                newLines.add(line);
            }

            if (found) {
                // Nếu tìm thấy mã hóa đơn cần xóa, ghi lại dữ liệu còn lại vào file
                Files.write(file.toPath(), newLines, StandardCharsets.UTF_8);
                System.out.println("Đã xóa đơn tạm với mã hóa đơn: " + maHoaDon);
                return true; // Trả về true nếu xóa thành công
            } else {
                // Nếu không tìm thấy mã hóa đơn
                System.out.println("Không tìm thấy mã hóa đơn " + maHoaDon + " trong file.");
                return false; // Trả về false nếu không tìm thấy
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Đã có lỗi khi xử lý file.");
            return false;
        }
    }
    public static void main(String[] args) {
		        DonTam_DAO dao = new DonTam_DAO();
        
	}
    

}
