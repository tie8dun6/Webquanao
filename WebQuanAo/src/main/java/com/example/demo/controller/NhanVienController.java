package com.example.demo.controller;

import com.example.demo.model.Nhanvien;
import com.example.demo.model.TrangThai;
import com.example.demo.repository.INhanVienRepository;
//import com.example.demo.service.INhanVienService;
//import com.example.demo.service.QRCodeScannerService;
import com.github.sarxos.webcam.Webcam;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {


    @Autowired
    private INhanVienRepository repository;


    Nhanvien nhanVien = new Nhanvien(); // Tạo đối tượng 'nhanVien'

    @GetMapping
    public String viewAll(Model model) {
        List<Nhanvien> listNV = repository.findAll();
        model.addAttribute("listNhanVien", listNV);
        return "nhan-vien/nhanvien";
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
    public String addToSql(@RequestBody Nhanvien qrCodeDataObj, RedirectAttributes redirectAttributes) {
        Map<String, String> response = new HashMap<>();

        try {
            repository.save(qrCodeDataObj);
            response.put("success", "true");
            response.put("message", "Đã thêm vào cơ sở dữ liệu.");
            redirectAttributes.addFlashAttribute("successMessage", "Thêm nhân viên thành công");
            return "redirect:/nhan-vien";
        } catch (Exception e) {
            response.put("success", "false");
            response.put("message", "Đã có lỗi xảy ra khi thêm vào cơ sở dữ liệu.");
        }

        return "error";
    }

    @GetMapping("/view-update/{userId}")
    public String viewUpdate(Model model, @PathVariable(name = "userId") Long userId) {
        Nhanvien nhanvien1 = repository.findNhanvienByUserId(userId);

        if (nhanvien1 != null) {
            model.addAttribute("nhanVien", nhanvien1);
            return "nhan-vien/themnhanvien"; // Thay "nhan-vien/themnhanvien" bằng tên trang JSP/Thymeleaf của bạn
        } else {
            // Xử lý trường hợp nhanvien1 không tồn tại
            return "redirect:/error-page"; // Thay "/error-page" bằng URL của trang lỗi hoặc xử lý khác
        }
    }

    private BCryptPasswordEncoder passwordEncoder;

// ...

    @PostMapping("/update/{userId}")
    public String updateNhanVien(@PathVariable(name = "userId") Long userId, @ModelAttribute("nhanVien") Nhanvien nhanVien) {
        Nhanvien existingNhanVien = repository.findNhanvienByUserId(userId);

        existingNhanVien.setUserName(nhanVien.getUserName());
        existingNhanVien.setEmail(nhanVien.getEmail());

        // Tạo một thể hiện của BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Kiểm tra xem mật khẩu mới từ form có tồn tại không
        if (nhanVien.getEncrytedPassword() == null || nhanVien.getEncrytedPassword().isEmpty()) {
            // Nếu không có mật khẩu mới từ form, đặt mật khẩu mặc định là "12345" và mã hóa
            String defaultPassword = "12345";
            String encodedPassword = passwordEncoder.encode(defaultPassword);
            existingNhanVien.setEncrytedPassword(encodedPassword);
        } else {
            // Nếu có mật khẩu mới từ form, mã hóa nó
            String encodedPassword = passwordEncoder.encode(nhanVien.getEncrytedPassword());
            existingNhanVien.setEncrytedPassword(encodedPassword);
        }

        repository.save(existingNhanVien);
        return "redirect:/nhan-vien"; // Thay "/nhan-vien" bằng URL của trang danh sách của bạn
    }



}
