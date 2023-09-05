package com.example.demo.service;

import com.example.demo.model.AppUser;

public interface ILoginService {


    AppUser findAppUserByEmail(String email);

    AppUser findByResetPassToken(String token);


    void updateResetPasswordToken(String token, String email);




    void updatePassword(AppUser chucVu, String newPassword);

    AppUser getAppUserByResetPassToken(String token);
    AppUser get(String resetPassToken);

}
