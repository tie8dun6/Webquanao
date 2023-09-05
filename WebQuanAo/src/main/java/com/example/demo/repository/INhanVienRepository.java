package com.example.demo.repository;

import com.example.demo.model.Hang;
import com.example.demo.model.Nhanvien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INhanVienRepository extends JpaRepository<Nhanvien,Long> {

    @Override
    List<Nhanvien> findAll();
    Nhanvien findNhanvienByUserId(Long userId);
}
