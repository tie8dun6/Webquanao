package com.example.demo.repository;

import com.example.demo.model.KichCo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IKichCoRepository extends JpaRepository<KichCo,Integer> {
    KichCo findKichCoById(int idHangValue);

    KichCo findKichCoByTen(String tenKichCo);
}
