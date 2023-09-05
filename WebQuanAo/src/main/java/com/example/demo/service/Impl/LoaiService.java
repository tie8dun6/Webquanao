package com.example.demo.service.Impl;

import com.example.demo.model.Loai;
import com.example.demo.repository.ILoaiRepository;
import com.example.demo.service.ILoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiService implements ILoaiService {

    @Autowired
    private ILoaiRepository repository;

    @Override
    public void add(Loai loai) {
        repository.save(loai);
    }

    @Override
    public void update(Loai loai) {
        repository.save(loai);
    }

//    @Override
//    public void delete(String id) {
//        repository.deleteById(id);
//    }

    @Override
    public List<Loai> fillAll() {
        return repository.findAll();
    }

    @Override
    public Loai findLoaiById(Integer id) {
        return repository.findLoaiById(id);
    }



}
