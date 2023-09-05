package com.example.demo.service.Impl;

import com.example.demo.model.AppUser;
import com.example.demo.repository.IAppUserRepository;
import com.example.demo.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {


    @Autowired
    private IAppUserRepository repository;

    @Override
    public AppUser findAppUserByEmail(String email) {
        return repository.findAppUserByEmail(email);
    }

    @Override
    public AppUser findByResetPassToken(String token) {
        return repository.findByResetPassToken(token);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) {
        AppUser chucVu = repository.findAppUserByEmail(email);
        if (chucVu != null) {
            chucVu.setResetPassToken(token);
            repository.save(chucVu);
        } else {
            throw new RuntimeException("Could not find any customer with the email " + email);
        }
    }

    @Override
    public void updatePassword(AppUser chucVu, String newPassword) {
        int maxLength = 50; // Độ dài tối đa của cột 'Ten'
        String trimmedValue = chucVu.getEncrytedPassword();
        if (trimmedValue.length() > maxLength) {
            trimmedValue = trimmedValue.substring(0, maxLength);
        }
        chucVu.setEncrytedPassword(trimmedValue);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        chucVu.setEncrytedPassword(encodedPassword);

        chucVu.setResetPassToken(null);
        repository.save(chucVu);
    }

    @Override
    public AppUser getAppUserByResetPassToken(String token) {
        return repository.getAppUserByResetPassToken(token);
    }

    @Override
    public AppUser get(String resetPassToken) {
        return repository.findByResetPassToken(resetPassToken);
    }
}
