package com.example.demo.service;

import com.example.demo.model.Hang;

import java.util.List;

public interface IHangService {
    void add(Hang hang);
    void update(Hang hang);

//    void deleteNhaSanXuatById(@Param("id") Integer id);

    Hang findHangById(Integer id);
    List<Hang> fillAll();
}
