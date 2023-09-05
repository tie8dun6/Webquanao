package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole,Long> {
    List<UserRole> findAll();
    UserRole findUserRoleByAppUser(AppUser appUser);

}
