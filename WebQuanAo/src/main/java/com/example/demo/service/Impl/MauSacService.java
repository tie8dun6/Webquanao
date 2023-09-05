package com.example.demo.service.Impl;

import com.example.demo.model.MauSac;
import com.example.demo.repository.IMauSacRepository;
import com.example.demo.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauSacService implements IMauSacService {

    @Autowired
    private IMauSacRepository repository;

    @Override
    public List<MauSac> findAll() {
        return repository.findAll();
    }

    @Override
    public void add(MauSac mauSac) {
        repository.save(mauSac);
    }

    @Override
    public void update(MauSac mauSac) {
        repository.save(mauSac);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public MauSac findMauSacById(Integer id) {
        return repository.findMauSacById(id);
    }


}
