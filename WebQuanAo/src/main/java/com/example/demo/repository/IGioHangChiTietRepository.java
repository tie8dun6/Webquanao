package com.example.demo.repository;

import com.example.demo.model.GioHang;
import com.example.demo.model.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IGioHangChiTietRepository extends JpaRepository<GioHangChiTiet,Integer> {
    @Override
    List<GioHangChiTiet> findAll();


    List<GioHangChiTiet> findByIdGioHang(GioHang idGioHang);
}
