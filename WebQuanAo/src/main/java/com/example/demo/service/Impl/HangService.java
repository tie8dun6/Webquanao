package com.example.demo.service.Impl;

import com.example.demo.model.Hang;
import com.example.demo.repository.IHangRepository;
import com.example.demo.service.IHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HangService implements IHangService {

    @Autowired
    private IHangRepository repository;

    @Override
    public void add(Hang hang) {
        repository.save(hang);
    }

    @Override
    public void update(Hang hang) {
        repository.save(hang);

    }

    @Override
    public Hang findHangById(Integer id) {
        return repository.findHangById(id);
    }

//    @Override
//    public void deleteNhaSanXuatById(Integer id) {
//        repository.deleteNhaSanXuatById(id);
//    }


    @Override
    public List<Hang> fillAll() {
        return repository.findAll();
    }
}
