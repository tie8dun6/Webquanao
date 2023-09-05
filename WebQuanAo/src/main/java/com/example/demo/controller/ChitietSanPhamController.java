package com.example.demo.controller;


import com.example.demo.model.*;
import com.example.demo.repository.IChatLieuRepository;
import com.example.demo.repository.IChiTietSanPhamRepository;
import com.example.demo.repository.IHangRepository;
import com.example.demo.repository.IKichCoRepository;
import com.example.demo.repository.ILoaiRepository;
import com.example.demo.repository.IMauSacRepository;
import com.example.demo.service.IChiTietSanPhamService;
import jakarta.validation.Valid;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


@Controller
@RequestMapping("/chi-tiet-san-pham")
public class ChitietSanPhamController {


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

    @PostMapping("/upload")
    public String uploadProducts(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {

            serviceChiTiet.saveProductsFromExcel(file);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm excel thành công");
            return "redirect:/chi-tiet-san-pham";
        } catch (IOException e) {
            return "redirect:/chi-tiet-san-pham";
        }
    }


    @GetMapping
    private String viewAll(Model model, @RequestParam(defaultValue = "1") int page,
                           @RequestParam(required = false, name = "tenSanPham") String keyword,
                           @RequestParam(name = "min", defaultValue = "0") BigDecimal min,
                           @RequestParam(name = "max", defaultValue = "100000000") BigDecimal max,
                           @RequestParam(name = "idLoai", required = false) Integer loaiId) {
        if (page < 1) {
            page = 1;
        }

        Pageable pageable = PageRequest.of(page - 1, 5);
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

        return "chi-tiet-san-pham/tables";

    }

    @GetMapping("/formAdd")
    public String goFormAdd(Model model) {
        ChitietSanPham chiTietSanPhamDTO = new ChitietSanPham();
        List<Hang> listHang = repositoryHang.findAll();
        List<MauSac> listMS = repositoryMauSac.findAll();
        List<KichCo> listKichCo = repositoryKichCo.findAll();
        List<Loai> listLoai = repositoryLoai.findAll();
        List<ChatLieu> listChatLieu = repositoryChatLieu.findAll();

        model.addAttribute("chiTietSPDTO", chiTietSanPhamDTO);
        model.addAttribute("listHangSanXuat", listHang);
        model.addAttribute("listLoai", listLoai);
        model.addAttribute("listMS", listMS);
        model.addAttribute("listKichCo", listKichCo);
        model.addAttribute("listChatLieu", listChatLieu);
        return "chi-tiet-san-pham/addsp";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("chiTietSPDTO") ChitietSanPham chiTietSanPham,
                       BindingResult bindingResult, RedirectAttributes redirectAttributes,
                       @RequestParam(value = "image") MultipartFile imageFile,
                       Model model
    ) {

        String image = "anh1.jpg";
        Path path = Paths.get("D:\\shop_quan_ao3\\WebQuanAo\\src\\main\\webapp\\img");
        try {
            InputStream inputStream = imageFile.getInputStream();
            Files.copy(inputStream, path.resolve(imageFile.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            image = imageFile.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (imageFile.isEmpty() || chiTietSanPham.getNgayNhap() == null || chiTietSanPham.getTen() == null || chiTietSanPham.getNgayChinhSua() == null) {
            List<Hang> listHang = repositoryHang.findAll();
            List<MauSac> listMS = repositoryMauSac.findAll();
            List<KichCo> listKichCo = repositoryKichCo.findAll();
            List<Loai> listLoai = repositoryLoai.findAll();
            List<ChatLieu> listChatLieu = repositoryChatLieu.findAll();


            model.addAttribute("listHangSanXuat", listHang);
            model.addAttribute("listLoai", listLoai);
            model.addAttribute("listMS", listMS);
            model.addAttribute("listKichCo", listKichCo);
            model.addAttribute("listChatLieu", listChatLieu);
            return "chi-tiet-san-pham/addsp";
        }
        int trangThaiId = 1;
        TrangThai trangThai = new TrangThai();
        trangThai.setId(trangThaiId);
        chiTietSanPham.setIdTrangThai(trangThai);

        chiTietSanPham.setImage(image); // Gán tên file ảnh vào đối tượng chiTietSanPham
        redirectAttributes.addFlashAttribute("successMessage", "Thêm thành công");

        serviceChiTiet.add(chiTietSanPham);
        return "redirect:/chi-tiet-san-pham";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable(name = "id") Integer id) {

        List<Hang> listHang = repositoryHang.findAll();
        List<MauSac> listMS = repositoryMauSac.findAll();
        List<KichCo> listKichCo = repositoryKichCo.findAll();
        List<Loai> listLoai = repositoryLoai.findAll();
        List<ChatLieu> listChatLieu = repositoryChatLieu.findAll();


        ChitietSanPham chitietSanPham = serviceChiTiet.findChitietSanPhamById(id);
        model.addAttribute("chiTietSPDTO", chitietSanPham);
        model.addAttribute("ngayNhap", chitietSanPham.getNgayNhap());
        model.addAttribute("ngayChinhSua", chitietSanPham.getNgayNhap());
        model.addAttribute("listHangSanXuat", listHang);
        model.addAttribute("listLoai", listLoai);
        model.addAttribute("listMS", listMS);
        model.addAttribute("listKichCo", listKichCo);
        model.addAttribute("listChatLieu", listChatLieu);
        ChitietSanPham chitietSanPham1 = serviceChiTiet.findChitietSanPhamById(id);

        model.addAttribute("image", chitietSanPham1.getImage());
        return "chi-tiet-san-pham/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Integer id,
                         @Valid @ModelAttribute("chiTietSPDTO") ChitietSanPham chiTietSanPham,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes,
                         @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        ChitietSanPham chiTietSanPham1 = serviceChiTiet.findChitietSanPhamById(id);

        // Lưu giữ tên file ảnh hiện tại
        String currentImage = chiTietSanPham1.getImage();

        if (imageFile != null && !imageFile.isEmpty()) {
            String uploadDir = "D:\\shop_quan_ao3\\WebQuanAo\\src\\main\\webapp\\img";

            try {
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String originalFilename = imageFile.getOriginalFilename();

                if (originalFilename != null && !originalFilename.isEmpty()) {
                    String fileExtension = FilenameUtils.getExtension(originalFilename);
                    String timestamp = String.valueOf(System.currentTimeMillis());
                    String randomString = RandomStringUtils.randomAlphanumeric(8);
                    String fileName = timestamp + "_" + randomString + "." + fileExtension;
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    chiTietSanPham.setImage(fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Nếu không tải lên ảnh mới, gán lại tên file ảnh hiện tại
            chiTietSanPham.setImage(currentImage);
        }

        int trangThaiId = 1;
        TrangThai trangThai = new TrangThai();
        trangThai.setId(trangThaiId);
        chiTietSanPham.setIdTrangThai(trangThai);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thành công");

        serviceChiTiet.update(chiTietSanPham);

        return "redirect:/chi-tiet-san-pham";
    }


    @GetMapping("/remove/{id}")
    public String updateTrangThai(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ChitietSanPham chiTietSanPham = serviceChiTiet.findChitietSanPhamById(id);
        int trangThaiId = 2;
        TrangThai trangThai = new TrangThai();
        trangThai.setId(trangThaiId);
        chiTietSanPham.setIdTrangThai(trangThai);
        redirectAttributes.addFlashAttribute("successMessage", "Ngưng hoạt động thành công");

        serviceChiTiet.update(chiTietSanPham); // Sử dụng phương thức "update" thay vì "add"

        return "redirect:/chi-tiet-san-pham";
    }

    @GetMapping("/hoatdong/{id}")
    public String updateTrangThaiBat(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ChitietSanPham chiTietSanPham = serviceChiTiet.findChitietSanPhamById(id);
        int trangThaiId = 1;
        TrangThai trangThai = new TrangThai();
        trangThai.setId(trangThaiId);
        chiTietSanPham.setIdTrangThai(trangThai);
        redirectAttributes.addFlashAttribute("successMessage", "Bật hoạt động thành công");

        serviceChiTiet.update(chiTietSanPham); // Sử dụng phương thức "update" thay vì "add"

        return "redirect:/chi-tiet-san-pham";
    }

}

