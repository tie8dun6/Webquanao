package com.example.demo.controller;


import com.example.demo.model.MauSac;
import com.example.demo.service.IMauSacService;
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
@RequestMapping("mau-sac")
public class MauSacController {
    MauSac ms = new MauSac();

    @Autowired
    private IMauSacService service ;

    @GetMapping
    public String viewAll(Model model) {
        List<MauSac> listMS = service.findAll();
        model.addAttribute("mauSac", ms);
        model.addAttribute("listMS", listMS);
        return "mau-sac/mausacht";
    }

//    @GetMapping("/delete/{id}")
//    public String deleteById(@PathVariable(name = "id")Integer id){
//        service.delete(id);
//        return "redirect:/mau-sac";
//    }

    @GetMapping("/view-update/{id}")
    public String detail(@PathVariable(name = "id")Integer id,Model model){
        MauSac mauSac = service.findMauSacById(id);
        model.addAttribute("mauSac", mauSac);
        return "mau-sac/mausachedit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Integer id,
                         @RequestParam(name = "ma")String ma,
                         @RequestParam(name = "ten")String ten){

        MauSac loaiSP = service.findMauSacById(id);
        loaiSP.setMa(ma);
        loaiSP.setTen(ten);
        service.update(loaiSP);
        return "redirect:/mau-sac";
    }

//    @PostMapping("/add")
//    public String add(@ModelAttribute(name = "mauSac") MauSac mauSac) {
//
//        service.add(mauSac); // Assuming the service or repository has a suitable method to create a new record
//
//        return "redirect:/mau-sac";
//    }



    @GetMapping ("/delete/{id}")
    public String updateTrangThai(@PathVariable("id") Integer id, @RequestParam("trangThai") Integer trangThai) {
        MauSac n = service.findMauSacById(id);
        n.setTrangThai(trangThai);
        service.update(n);

        return "redirect:/mau-sac";
    }

    @PostMapping("/add")
    public String addMauSac(@Valid @ModelAttribute("mauSac") MauSac mauSac, BindingResult result, @RequestParam(value = "trangThai", defaultValue = "3") int trangThai) {
        // Kiểm tra tính hợp lệ của dữ liệu
        if (result.hasErrors()) {
            // Nếu có lỗi, trả về view "mau-sac/hien-thi" để hiển thị lại form với thông báo lỗi
            return "mau-sac/mausacht";
        }

        mauSac.setTrangThai(trangThai);
        service.add(mauSac);

        return "redirect:/mau-sac";
    }
}