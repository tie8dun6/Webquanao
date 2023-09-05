package com.example.demo.service;

import com.example.demo.model.Loai;

import java.util.List;

public interface ILoaiService {
    void add(Loai loai);
    void update(Loai loai);
//    void delete(String id);

    List<Loai> fillAll();
    Loai findLoaiById(Integer id);


}
