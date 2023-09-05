package com.example.demo.controller;

import com.example.demo.model.ChitietSanPham;
import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.model.TraHang;
import com.example.demo.repository.IChiTietSanPhamRepository;
import com.example.demo.repository.IHoaDonChiTietRepo;
import com.example.demo.repository.ITraHangRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
//@RequestMapping("/chi-tiet-hoa-don")

public class TraHangController {

    @Autowired
    private IHoaDonChiTietRepo iHoaDonChiTietRepo;

    @Autowired
    private IChiTietSanPhamRepository repositoryChiTiet;


    @PostMapping("/trahang/{id}")
    public String traHang(Model model, @PathVariable(name = "id") Integer id, @RequestParam(name = "soLuong") Integer soLuongHoaDon) {
        // Tìm chi tiết hóa đơn cần trả hàng
        HoaDonChiTiet chiTietHoaDon = iHoaDonChiTietRepo.findHoaDonChiTietById(id);

        if (chiTietHoaDon != null) {
            // Tìm chi tiết sản phẩm tương ứng
            Optional<ChitietSanPham> optionalChiTietSanPham = repositoryChiTiet.findById(chiTietHoaDon.getId());

            if (optionalChiTietSanPham.isPresent()) {
                ChitietSanPham chiTietSanPham = optionalChiTietSanPham.get();

                // Lấy số lượng tồn từ chi tiết sản phẩm
                int soLuongTon = chiTietSanPham.getSoLuongTon();

                // Cộng số lượng từ chi tiết hóa đơn vào số lượng tồn của chi tiết sản phẩm
                int soLuongMoi = soLuongTon + soLuongHoaDon;
                chiTietSanPham.setSoLuongTon(soLuongMoi);

                // Lưu thay đổi vào cơ sở dữ liệu
                repositoryChiTiet.save(chiTietSanPham);
                iHoaDonChiTietRepo.save(chiTietHoaDon);
            }
        }
        model.addAttribute("chiTietHoaDon",chiTietHoaDon);
        return "redirect:/chi-tiet-hoa-don";
    }

}



