package com.example.demo.repository;

import com.example.demo.model.ChitietSanPham;
import com.example.demo.model.GioHang;

public interface IGioHangChiTietSessionRepo {


    void addToCart(ChitietSanPham chiTietSanPham, Long userId);
    void addToCartinDetail(ChitietSanPham chiTietSanPham, Long userId,Integer soLuong);

    void truSanPham(Long userId, ChitietSanPham chiTietSanPham);



    void xoaSanPham(Long userId, ChitietSanPham chiTietSanPham);
}
