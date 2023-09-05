package com.example.demo.controller;

import com.example.demo.model.Loai;
import com.example.demo.service.ILoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("loai-san-pham")
public class LoaiController {

    Loai dong=new Loai();

    @Autowired
    private ILoaiService service;

    @GetMapping
    private String viewAll(Model model){
        List<Loai> loai =service.fillAll();
        model.addAttribute("listLoai", loai);
        model.addAttribute("dongDoChoi",dong);
        return "loai/loaispht";
    }
    @PostMapping("/add")
    private String add( @RequestParam(name = "ma") String ma,
                       @RequestParam(name = "ten") String ten){

        Loai loai =new Loai();
        loai.setMa(ma);
        loai.setTen(ten);
        service.add(loai);
        return "redirect:/loai-san-pham";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Integer id,
                         @RequestParam(name = "ma") String ma,
                         @RequestParam(name = "ten") String ten){
        Loai dsp=service.findLoaiById(id);
        dsp.setMa(ma);
        dsp.setTen(ten);
        service.update(dsp);
        return "redirect:/loai-san-pham";

    }


    @GetMapping("/view-update/{id}")
    private String viewUpdate(@PathVariable(name = "id")Integer id,Model model){
        Loai loai =service.findLoaiById(id);
        model.addAttribute("dongDoChoi", loai);
        return "loai/loaispedit";
    }



//    @GetMapping("/delete/{id}")
//    private String delete(@PathVariable(name = "id") String id,Model model){
//        Loai loai =service.findDongSanPhamById(id);
//        service.delete(id);
//        return "redirect:/dong-san-pham";
//    }

}
