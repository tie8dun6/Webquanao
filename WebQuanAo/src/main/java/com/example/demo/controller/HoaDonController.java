package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.HoaDon;
import com.example.demo.model.Loai;
import com.example.demo.model.TrangThaiOrder;
import com.example.demo.repository.IHoaDonRepository;
import com.example.demo.repository.ITrangThaiOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/hoa-don")
public class HoaDonController {
    @Autowired
    private IHoaDonRepository iHoaDonRepository;

    @Autowired
    private ITrangThaiOrderRepo iTrangThaiOrderRepo;

    @GetMapping()
    private String viewOrder(Model model){
        List<HoaDon> hoaDons = iHoaDonRepository.findAll();
        model.addAttribute("hoaDon", hoaDons);
        return "duyet-hoa-don/hoa-don";

    }
    @GetMapping("/duyet/{id}")
    private String duyetHoaDon(@PathVariable(name = "id")Integer id,Model model){
        HoaDon hoaDon=iHoaDonRepository.findById(id).orElse(null);
        TrangThaiOrder trangThaiOrder=iTrangThaiOrderRepo.findById(2).orElse(null);
        hoaDon.setIdTT(trangThaiOrder);
        iHoaDonRepository.save(hoaDon);
        return "redirect:/hoa-don";
    }
//    @GetMapping("/da-nhan/{id}")
//    private String daNhan(@PathVariable(name = "id")Integer id,Model model){
//        HoaDon hoaDon=iHoaDonRepository.findById(id).orElse(null);
//        TrangThaiOrder trangThaiOrder=iTrangThaiOrderRepo.findById(3).orElse(null);
//        hoaDon.setIdTT(trangThaiOrder);
//        iHoaDonRepository.save(hoaDon);
//        return "redirect:/hoa-don";
//    }
@GetMapping("/da-nhan/{id}")
private String daNhan(@PathVariable(name = "id") Integer id, Model model) {
    HoaDon hoaDon = iHoaDonRepository.findById(id).orElse(null);

    if (hoaDon != null) {
        TrangThaiOrder trangThaiOrder = iTrangThaiOrderRepo.findById(3).orElse(null);
        hoaDon.setIdTT(trangThaiOrder);

        // Lấy thời gian hiện tại
        LocalDateTime ngayNhan = LocalDateTime.now();

        // Gán thời gian hiện tại vào trường "Nhận"
        hoaDon.setNgayKhachNhan(ngayNhan);

        iHoaDonRepository.save(hoaDon);
    }

    return "redirect:/hoa-don";
}




}
