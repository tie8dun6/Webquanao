package com.example.demo.controller;

import com.example.demo.repository.IHoaDonChiTietRepo;
import com.example.demo.repository.IHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/thong-ke")
public class ThongKeController {
    @Autowired
    private IHoaDonChiTietRepo iHoaDonChiTietRepo;

    @Autowired
    private IHoaDonRepository iHoaDonRepository;

    //    @GetMapping("/theo-thang")
//    private String viewTop10TheoThang(Model model,@RequestParam(value = "selectedMonth",required = false) String selectedMonth) {
//        int monthValue;
//        Month month = null;
//        if (selectedMonth == null) {
//            LocalDateTime now = LocalDateTime.now();
//            selectedMonth = String.valueOf(now.getMonth());
//            month = Month.valueOf(selectedMonth.toUpperCase());
//
//        }
//        monthValue = month.getValue();
//        List<Object[]> listTop10 = iHoaDonChiTietRepo.getTop10SanPhamTheoThang(monthValue);
//        model.addAttribute("listTop10", listTop10);
//        return "thong-ke/thang";
//
//    }
    @GetMapping("/theo-thang")
    private String viewTop10TheoThang(Model model, @RequestParam(value = "selectedMonth", required = false) String selectedMonthStr) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            if (selectedMonthStr == null || selectedMonthStr.isEmpty()) {
                // Nếu không có giá trị selectedMonth được truyền vào, gán giá trị mặc định là tháng và năm hiện tại.
                Date currentDate = new Date();
                selectedMonthStr = sdf.format(currentDate);
            }
            Date selectedMonth = sdf.parse(selectedMonthStr);
            List<Object[]> listTop10 = iHoaDonChiTietRepo.getTop10SanPhamTheoThang2(selectedMonth);
            model.addAttribute("listTop10", listTop10);
            model.addAttribute("now", selectedMonthStr);
            return "thong-ke/thang";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "thong-ke/thang";

        }
    }

    @GetMapping("/theo-ngay")
    private String viewTop10TheoNgay(Model model, @RequestParam(value = "selectedDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date selectedDate) {
        List<Object[]> listTop10 = iHoaDonChiTietRepo.getTop10SanPhamTheoNgay2(selectedDate);
        model.addAttribute("listTop10", listTop10);
        model.addAttribute("selectedDate", LocalDate.now());

        return "thong-ke/ngay";

    }

    @GetMapping("/theo-khoang-ngay")
    private String viewTop10TheoKhoang(Model model
            , @RequestParam(value = "selectedDateStart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime selectedDateStart
            , @RequestParam(value = "selectedDateEnd", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime selectedDateEnd) {

        List<Object[]> listTop10 = iHoaDonChiTietRepo.getTop10SanPhamTheoKhoangNgay(selectedDateStart, selectedDateEnd);
        model.addAttribute("listTop10", listTop10);
        return "thong-ke/khoang";

    }

    @GetMapping("/doanh-thu-theo-khoang")
    private String doanhThuTheoKhoangNgay(Model model
            , @RequestParam(value = "selectedDateStart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime selectedDateStart
            , @RequestParam(value = "selectedDateEnd", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime selectedDateEnd
            , RedirectAttributes redirectAttributes
    ) {

        BigDecimal doanhThuKhoang = iHoaDonRepository.tinhTongDoanhThuTrongKhoangNgay(selectedDateStart, selectedDateEnd);

        redirectAttributes.addFlashAttribute("doanhThuKhoang", doanhThuKhoang);
        return "redirect:/thong-ke/doanh-thu";

    }

    @GetMapping("/theo-nam")
    private String viewTop10TheoNam(Model model, @RequestParam(value = "selectedYear", required = false) String selectedYearStr) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            if (selectedYearStr == null || selectedYearStr.isEmpty()) {
                // Nếu không có giá trị selectedMonth được truyền vào, gán giá trị mặc định là tháng và năm hiện tại.
                Date currentDate = new Date();
                selectedYearStr = sdf.format(currentDate);
            }
            Date selectedYear = sdf.parse(selectedYearStr);
            List<Object[]> listTop10 = iHoaDonChiTietRepo.getTop10SanPhamTheoNam(selectedYear);
            model.addAttribute("listTop10", listTop10);
            model.addAttribute("now", selectedYearStr);
            return "thong-ke/nam";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "thong-ke/nam";

        }
    }

    @GetMapping("/doanh-thu")
    private String doanhThuNgay(Model model, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate ngayHienTai) {
        ngayHienTai = LocalDate.now();
        BigDecimal doanhThu = iHoaDonRepository.tinhTongDoanhThuTrongNgay(ngayHienTai);
        String selectedMonthStr = null;
        String selectedYearStr = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

            if (selectedMonthStr == null || selectedMonthStr.isEmpty()) {
                // Nếu không có giá trị selectedMonth được truyền vào, gán giá trị mặc định là tháng và năm hiện tại.
                Date currentDate = new Date();
                selectedMonthStr = sdf.format(currentDate);
            }
            if (selectedYearStr == null || selectedYearStr.isEmpty()) {
                // Nếu không có giá trị selectedMonth được truyền vào, gán giá trị mặc định là tháng và năm hiện tại.
                Date currentDate = new Date();
                selectedYearStr = sdf.format(currentDate);
            }
//            thang
            Date selectedMonth = sdf.parse(selectedMonthStr);
            BigDecimal doanhThuThang = iHoaDonRepository.tinhTongDoanhThuTrongThang(selectedMonth);
//            năm
            Date selectedYear = sdfYear.parse(selectedYearStr);
            BigDecimal doanhThuNam = iHoaDonRepository.tinhTongDoanhThuTrongNam(selectedYear);
//          biểu đồ
            int currentYear = YearMonth.now().getYear();
            List<BigDecimal> doanhThuTheoThang = new ArrayList<>();

            for (int month = 1; month <= 12; month++) {
                BigDecimal doanhThuThangChart = iHoaDonRepository.tinhTongDoanhThuTrongThangChar(currentYear, month);
                doanhThuTheoThang.add(doanhThuThangChart);
            }
            model.addAttribute("doanhThuTheoThang", doanhThuTheoThang);
            model.addAttribute("doanhThu", doanhThu);
            model.addAttribute("doanhThuThang", doanhThuThang);
            model.addAttribute("doanhThuNam", doanhThuNam);
            return "thong-ke/doanh-thu";
        } catch (ParseException e) {
            return "thong-ke/doanh-thu";

        }

    }
    @GetMapping("/bieu-do")
    private String bieuDo(Model model) {
        int currentYear = YearMonth.now().getYear();
        List<BigDecimal> doanhThuTheoThang = new ArrayList<>();

        for (int month = 1; month <= 12; month++) {
            BigDecimal doanhThuThang = iHoaDonRepository.tinhTongDoanhThuTrongThangChar(currentYear, month);
            doanhThuTheoThang.add(doanhThuThang);
        }
        model.addAttribute("doanhThuTheoThang", doanhThuTheoThang);
        return "thong-ke/bieu-do";
    }



//    Top 10 bán chậm theo năm
@GetMapping("/ban-cham-nhat-nam")
private String viewTop10BanChamTheoNam(Model model, @RequestParam(value = "selectedYear", required = false) String selectedYearStr) throws ParseException {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        if (selectedYearStr == null || selectedYearStr.isEmpty()) {
            // Nếu không có giá trị selectedMonth được truyền vào, gán giá trị mặc định là tháng và năm hiện tại.
            Date currentDate = new Date();
            selectedYearStr = sdf.format(currentDate);
        }
        Date selectedYear = sdf.parse(selectedYearStr);
        List<Object[]> listTop10 = iHoaDonChiTietRepo.getTop10SanPhamChamNhatTheoNam(selectedYear);
        model.addAttribute("listTop10ChamNhatNam", listTop10);
        model.addAttribute("now", selectedYearStr);
        return "thong-ke/ban-cham";
    } catch (Exception exception) {
        exception.printStackTrace();
        return "thong-ke/ban-cham";

    }
}



}
