package com.example.demo.repository;

import com.example.demo.model.TrangThaiOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrangThaiOrderRepo extends JpaRepository<TrangThaiOrder,Integer> {
}
