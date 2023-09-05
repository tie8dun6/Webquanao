package com.example.demo.repository;

import com.example.demo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IAppUserRepository extends JpaRepository<AppUser,Long> {
    List<AppUser> findAll();
    AppUser findAppUserByEmail(String email);


    AppUser getAppUserByResetPassToken(String token);

    AppUser findByResetPassToken(String token);
}
