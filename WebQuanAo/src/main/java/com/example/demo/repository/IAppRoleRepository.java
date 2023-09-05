package com.example.demo.repository;

import com.example.demo.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAppRoleRepository extends JpaRepository<AppRole, Long> {
    List<AppRole> findAll();

    AppRole findByRoleId(Long roleId);
}
