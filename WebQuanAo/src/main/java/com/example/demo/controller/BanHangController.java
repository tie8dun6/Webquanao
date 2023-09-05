package com.example.demo.controller;

import com.example.demo.model.ChitietSanPham;
import com.example.demo.model.Loai;
import com.example.demo.model.MauSac;
import com.example.demo.repository.*;
import com.example.demo.service.IChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/ban-hang")

public class BanHangController {
    @Autowired
    private IHangRepository repositoryHang;

    @Autowired
    private ILoaiRepository repositoryLoai;

    @Autowired
    private IMauSacRepository repositoryMauSac;

    @Autowired
    private IChiTietSanPhamRepository repositoryChiTiet;

    @Autowired
    private IChatLieuRepository repositoryChatLieu;

    @Autowired
    private IKichCoRepository repositoryKichCo;

    @Autowired
    private IChiTietSanPhamService serviceChiTiet;
    @Autowired
    private IAppUserRepository appUserRepository;

    @Autowired
    private IUserRoleRepository iUserRoleRepository;

    @GetMapping
    private String viewAll(Model model, @RequestParam(defaultValue = "1") int page,
                           @RequestParam(required = false, name = "tenSanPham") String keyword,
                           @RequestParam(name = "min", defaultValue = "0") BigDecimal min,
                           @RequestParam(name = "max", defaultValue = "100000000") BigDecimal max,
                           @RequestParam(name = "idLoai", required = false) Integer loaiId) {
        if (page < 1) {
            page = 1;
        }

        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<ChitietSanPham> listCTSP;

        if (loaiId != null) {
            listCTSP = repositoryChiTiet.findByIdLoai(loaiId, pageable);
        } else if (keyword == null || keyword.isBlank() && min == null && max == null) {
            listCTSP = repositoryChiTiet.findAll(pageable);
        } else if (keyword == null || keyword.isBlank()) {
            listCTSP = repositoryChiTiet.findAll(pageable);

        } else {
            listCTSP = serviceChiTiet.searchByTenAndGiaRange(keyword, min, max, pageable);
        }

        List<Loai> listLoai = repositoryLoai.findAll();
        model.addAttribute("listLoai", listLoai);

        model.addAttribute("listChiTietSanPham", listCTSP);

        return "ban-hang/shop";

    }


}
