package com.example.demo.repository;

import com.example.demo.model.TrangThai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrangThaiRepository extends JpaRepository<TrangThai,Integer> {
    TrangThai findTrangThaiById(int idHangValue);
}
