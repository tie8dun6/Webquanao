package com.example.demo.controller;

import com.example.demo.model.Nhanvien;
import com.example.demo.repository.INhanVienRepository;
import com.example.demo.service.QRCodeScannerService;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Qrcontroller {

    @Autowired
    private INhanVienRepository repository;

@GetMapping("/")
public String index() {
    return "nhan-vien/index"; // Trả về trang index (giao diện đọc mã QR từ webcam)
}

    @PostMapping("/decode")
    @ResponseBody
    public Map<String, String> decodeQrCode(@RequestParam("file") MultipartFile file) {
        Map<String, String> result = new HashMap<>();
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            MultiFormatReader reader = new MultiFormatReader();
            Result decodeResult = reader.decode(bitmap);
            result.put("text", decodeResult.getText());
        } catch (NotFoundException | IOException e) {
            result.put("error", "Không tìm thấy mã QR hoặc lỗi đọc mã.");
        }
        return result;
    }

    @PostMapping("/addToSql")
    @ResponseBody
    public Map<String, String> addToSql(@RequestBody Nhanvien qrCodeDataObj) {
        Map<String, String> response = new HashMap<>();

        try {
            // Lưu đối tượng Nhanvien vào cơ sở dữ liệu
            repository.save(qrCodeDataObj);

            response.put("success", "true");
            response.put("message", "Đã thêm vào cơ sở dữ liệu.");
        } catch (Exception e) {
            response.put("success", "false");
            response.put("message", "Đã có lỗi xảy ra khi thêm vào cơ sở dữ liệu.");
        }

        return response;
    }


}
