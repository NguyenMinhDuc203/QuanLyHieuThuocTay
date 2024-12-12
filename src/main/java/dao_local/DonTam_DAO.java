
package dao_local;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import local.ChiTietDonTam;
import local.DonTam;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonTam_DAO {

	private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();
    private static final String FILE_PATH = "./donTam.json"; // Đường dẫn đến file JSON

    // Phương thức lưu danh sách DonTam vào file JSON
    public void saveToFile(List<DonTam> donTamList) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(donTamList, writer);
            System.out.println("Lưu dữ liệu thành công vào file JSON.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Đã xảy ra lỗi khi lưu dữ liệu vào file JSON.");
        }
    }
    public void clearAllDonTam() {
        List<DonTam> emptyList = new ArrayList<>(); // Tạo danh sách rỗng
        saveToFile(emptyList); // Ghi danh sách rỗng vào file JSON
        System.out.println("Đã xóa toàn bộ dữ liệu trong file JSON.");
    }
    // Phương thức đọc danh sách DonTam từ file JSON
    public List<DonTam> readFromFile() {
        List<DonTam> donTamList = new ArrayList<>();

        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<DonTam>>() {}.getType();
            donTamList = gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            System.out.println("File không tồn tại. Trả về danh sách rỗng.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Đã xảy ra lỗi khi đọc file JSON.");
        }

        return donTamList;
    }

    // Phương thức thêm mới DonTam vào danh sách và lưu lại
    public void addDonTam(DonTam donTam) {
        List<DonTam> donTamList = readFromFile();
        donTamList.add(donTam);
        saveToFile(donTamList);
    }

    // Phương thức lấy tất cả các DonTam
    public List<DonTam> getAllDonTam() {
        return readFromFile();
    }

    // Phương thức tìm DonTam theo mã hóa đơn
    public DonTam getDonTamByMaHoaDon(String maHoaDon) {
        List<DonTam> donTamList = readFromFile();

        for (DonTam donTam : donTamList) {
            if (donTam.getMaHoaDon().equals(maHoaDon)) {
                return donTam;
            }
        }

        System.out.println("Không tìm thấy đơn tạm với mã hóa đơn: " + maHoaDon);
        return null;
    }

    // Phương thức xóa DonTam theo mã hóa đơn
    public boolean xoaDonTam(String maHoaDon) {
        List<DonTam> donTamList = readFromFile();
        boolean found = donTamList.removeIf(donTam -> donTam.getMaHoaDon().equals(maHoaDon));

        if (found) {
            saveToFile(donTamList);
            System.out.println("Đã xóa đơn tạm với mã hóa đơn: " + maHoaDon);
            return true;
        } else {
            System.out.println("Không tìm thấy đơn tạm với mã hóa đơn: " + maHoaDon);
            return false;
        }
    }
    
    

    
}
