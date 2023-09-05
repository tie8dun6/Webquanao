package com.example.demo.service;

import com.example.demo.model.ChitietSanPham;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

public interface IChiTietSanPhamService {
    Page<ChitietSanPham> findAll(Pageable pageable);

    void add(ChitietSanPham chiTietSanPham);
    void update(ChitietSanPham chiTietSanPham);
    ChitietSanPham findChitietSanPhamById(Integer id);



    Page<ChitietSanPham> searchByTen(String ten, Pageable pageable);

    Page<ChitietSanPham> searchByTenAndGiaRange(String ten, BigDecimal min, BigDecimal max, Pageable pageable);


    void saveProductsFromExcel(MultipartFile file) throws IOException;

}
