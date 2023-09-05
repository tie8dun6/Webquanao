package com.example.demo.repository;


import com.example.demo.model.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMauSacRepository extends JpaRepository<MauSac,Integer> {

    @Override
    List<MauSac> findAll();
    MauSac findMauSacById(Integer id);


    MauSac findMauSacByTen(String tenMauSac);
}
