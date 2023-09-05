package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IGioHangRepository extends JpaRepository<GioHang,Integer> {
    @Override
    List<GioHang> findAll();

    GioHang findGioHangByUserId(AppUser userId);
}
