package gui;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.element.Cell;

import com.itextpdf.kernel.font.PdfFontFactory;

import java.io.IOException;

import com.itextpdf.kernel.font.PdfFont;

public class GeneratePdf {
    public static void main (String[] args) throws IOException {
        String path = "invoice.pdf";

        // Tạo PdfWriter và PdfDocument
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);
        
     // Đường dẫn tới font Tahoma trên hệ thống của bạn
        String fontPath = "./gui/tahoma.ttf";
        
        // Đặt kích thước trang mặc định
        pdfDoc.setDefaultPageSize(PageSize.A4);
        
        // Sử dụng font mặc định (Times Roman)
        PdfFont font = PdfFontFactory.createFont(fontPath);  // Font mặc định sẽ được sử dụng
        
        // Tạo đối tượng Document và thêm nội dung
        Document document = new Document(pdfDoc);
        
        // Tiêu đề hóa đơn
        document.add(new Paragraph("HÓA ĐƠN MUA HÀNG")
            .setTextAlignment(TextAlignment.CENTER)
            .setBold().setFontSize(20).setFont(font));  // Sử dụng font mặc định

        // Thêm thông tin khách hàng
        document.add(new Paragraph("Khách hàng: Nguyễn Văn A").setFont(font));  // setFont() trên Paragraph
        document.add(new Paragraph("Ngày: 12/11/2024").setFont(font));  // setFont() trên Paragraph
        
        // Tạo bảng chi tiết hóa đơn
        float[] pointColumnWidths = {150f, 100f, 100f, 100f};  // Định nghĩa độ rộng cột
        Table table = new Table(pointColumnWidths);
        
        // Thêm tiêu đề bảng
        table.addCell(new Cell().add(new Paragraph("Sản phẩm")).setBold().setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("Số lượng")).setBold().setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("Đơn giá")).setBold().setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("Thành tiền")).setBold().setFont(font));  // setFont() trên Cell
        
        // Dữ liệu chi tiết hóa đơn
        table.addCell(new Cell().add(new Paragraph("Sản phẩm A")).setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("2")).setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("100,000 VND")).setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("200,000 VND")).setFont(font));  // setFont() trên Cell
        
        table.addCell(new Cell().add(new Paragraph("Sản phẩm B")).setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("3")).setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("50,000 VND")).setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("150,000 VND")).setFont(font));  // setFont() trên Cell

        table.addCell(new Cell().add(new Paragraph("Sản phẩm C")).setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("1")).setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("200,000 VND")).setFont(font));  // setFont() trên Cell
        table.addCell(new Cell().add(new Paragraph("200,000 VND")).setFont(font));  // setFont() trên Cell
        
        // Thêm bảng vào tài liệu
        document.add(table);
        
        // Tổng cộng
        document.add(new Paragraph("\nTổng cộng: 550,000 VND")
            .setTextAlignment(TextAlignment.RIGHT)
            .setBold().setFontSize(14).setFont(font));  // setFont() trên Paragraph

        // Đóng tài liệu
        document.close();
        
        System.out.println("PDF đã được tạo thành công tại: " + path);
    }
}
