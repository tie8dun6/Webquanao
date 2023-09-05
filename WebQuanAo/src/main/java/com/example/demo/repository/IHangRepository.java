package com.example.demo.repository;


import com.example.demo.model.Hang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHangRepository extends JpaRepository<Hang,Integer> {

    @Override
    List<Hang> findAll();

    Hang findHangById(Integer id);

    Hang findHangByTen(String tenHang);


//    @Query("UPDATE NhaSanXuat n SET n.trangThai = 4 WHERE n.id = :id")
//    void deleteNhaSanXuatById(@Param("id") Integer id);

}
