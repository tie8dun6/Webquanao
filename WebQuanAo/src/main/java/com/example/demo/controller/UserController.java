package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IAppUserRepository appUserRepository;
    @GetMapping
    public String viewAll(Model model) {
        List<AppUser> listUser = appUserRepository.findAll();
        model.addAttribute("listUser", listUser);
        return "user/user";
    }
}
