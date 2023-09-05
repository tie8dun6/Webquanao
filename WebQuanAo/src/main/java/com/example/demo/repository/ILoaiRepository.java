package com.example.demo.repository;


import com.example.demo.model.Loai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILoaiRepository extends JpaRepository<Loai,Integer> {
    @Override
    List<Loai> findAll();

    Loai findLoaiById(Integer id);

    Loai findLoaiByTen(String tenLoai);
}
