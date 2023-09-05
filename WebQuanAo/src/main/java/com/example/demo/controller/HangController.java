package com.example.demo.controller;


import com.example.demo.model.Hang;
import com.example.demo.service.IHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/hang-san-xuat")
public class HangController {

    Hang hang =new Hang();

    @Autowired
    private IHangService service;

    @GetMapping
    public String viewAll(Model model){
        List<Hang> nsx =service.fillAll();
        model.addAttribute("listHangSanXuat",nsx);
        model.addAttribute("hangSanXuat", hang);
        return "hang-san-xuat/hsxht";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "hangSanXuat") Hang hang,
                      BindingResult result, @RequestParam(value = "trangThai", defaultValue = "3") int trangThai,Model model) {
        if (result.hasErrors()) {
            return "hang-san-xuat/hsxht";

        }
            List<Hang> nsx =service.fillAll();
            model.addAttribute("listHangSanXuat",nsx);
            model.addAttribute("hangSanXuat", hang);
            hang.setTrangThai(trangThai);
            service.add(hang);
            return "redirect:/hang-san-xuat";

    }



    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable(name = "id") Integer id ,Model model){
        Hang nx =service.findHangById(id);
        model.addAttribute("hangSanXuat",nx);
        return "hang-san-xuat/hsxedit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Integer id,
                         @RequestParam(name = "ma") String ma,@RequestParam(name = "ten")String ten){
        Hang hang =service.findHangById(id);
        hang.setMa(ma);
        hang.setTen(ten);
        service.update(hang);
        return "redirect:/hang-san-xuat";

    }


    @GetMapping ("/delete/{id}")
    public String updateTrangThai(@PathVariable("id") Integer id, @RequestParam("trangThai") Integer trangThai) {
        Hang n = service.findHangById(id);
        n.setTrangThai(trangThai);
        service.update(n);

        return "redirect:/hang-san-xuat";
    }

//    @GetMapping ("/delete1/{id}")
//    public String updateTrangThai1(@PathVariable("id") Integer id, @RequestParam("trangThai") Integer trangThai) {
//        NhaSanXuat n = service.findNhaSanXuatById(id);
//        n.setTrangThai(trangThai);
//        service.update(n);
//
//        return "redirect:/nha-san-xuat";
//    }







}
