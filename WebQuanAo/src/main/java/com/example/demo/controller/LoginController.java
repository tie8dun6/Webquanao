package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.IChiTietSanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
@Controller

public class LoginController {
    @Autowired
    private IAppUserRepository appUserRepository;

    @Autowired
    private IUserRoleRepository iUserRoleRepository;
    @Autowired
    private IHangRepository repositoryHang;

    @Autowired
    private ILoaiRepository repositoryLoai;

    @Autowired
    private IMauSacRepository repositoryMauSac;

    @Autowired
    private IChiTietSanPhamRepository repositoryChiTiet;

    @Autowired
    private IChatLieuRepository repositoryChatLieu;

    @Autowired
    private IKichCoRepository repositoryKichCo;

    @Autowired
    private IChiTietSanPhamService serviceChiTiet;
    @Autowired
    private IGioHangRepository gioHangRepository;

    @Autowired
    private IGioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private IGioHangChiTietSessionRepo iGioHangChiTietSessionRepo;
    @Autowired
    private IHoaDonRepository iHoaDonRepository;
    @Autowired
    private ITrangThaiOrderRepo iTrangThaiOrderRepo;
    @Autowired
    private IHoaDonChiTietRepo iHoaDonChiTietRepo;

    @RequestMapping("/login")
    public String showLogin() {
        return "login/index";
    }




    @GetMapping("/checkLogin")
    private String viewAll(Model model, @RequestParam(defaultValue = "1") int page,
                           @RequestParam(required = false, name = "email") String username,
                           @RequestParam(required = false, name = "tenSanPham") String keyword,
                           @RequestParam(name = "min", defaultValue = "0") BigDecimal min,
                           @RequestParam(name = "max", defaultValue = "100000000") BigDecimal max,
                           @RequestParam(name = "idLoai", required = false) Integer loaiId) {
        if (page < 1) {
            page = 1;
        }

        AppUser appUser = appUserRepository.findAppUserByEmail(username);

        UserRole userRole = iUserRoleRepository.findUserRoleByAppUser(appUser);



            Pageable pageable = PageRequest.of(page - 1, 6);
            Page<ChitietSanPham> listCTSP;

            // Xử lý đăng nhập cho khách hàng
            // Thực hiện các thao tác đăng nhập của khách hàng
            if (loaiId != null) {
                listCTSP = repositoryChiTiet.findByIdLoai(loaiId, pageable);
            } else if (keyword == null || keyword.isBlank() && min == null && max == null) {
                listCTSP = repositoryChiTiet.findAll(pageable);
            } else if (keyword == null || keyword.isBlank()) {
                listCTSP = repositoryChiTiet.findAll(pageable);
            } else {
                listCTSP = repositoryChiTiet.searchByTenAndGiaKhuyenMai(keyword, min, max, pageable);
            }
            model.addAttribute("appUser", appUser);
            model.addAttribute("listChiTietSanPham", listCTSP);

        return "ban-hang/shop";

    }

    @GetMapping("/home")
    private String viewHome(Model model, @RequestParam(defaultValue = "1") int page,
                           @RequestParam(required = false, name = "email") String username,
                           @RequestParam(required = false, name = "tenSanPham") String keyword,
                           @RequestParam(name = "min", defaultValue = "0") BigDecimal min,
                           @RequestParam(name = "max", defaultValue = "100000000") BigDecimal max,
                           @RequestParam(name = "idLoai", required = false) Integer loaiId) {
        if (page < 1) {
            page = 1;
        }

        AppUser appUser = appUserRepository.findAppUserByEmail(username);

//        UserRole userRole = iUserRoleRepository.findUserRoleByAppUser(appUser);


        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<ChitietSanPham> listCTSP;

        listCTSP = repositoryChiTiet.findTop6ByOrderByNgayNhapDesc(pageable);

        model.addAttribute("appUser", appUser);
        model.addAttribute("listChiTietSanPhamTop5", listCTSP);

        return "ban-hang/home";

    }

        @PostMapping("/checkLogin")
    public String checkLogin(ModelMap modelMap,
                             @RequestParam(required = false, name = "email") String username,
                             @RequestParam(name = "matKhau") String password, Model model,
                             @RequestParam(defaultValue = "1") int page,
                             @RequestParam(required = false, name = "tenSanPham") String keyword,
                             @RequestParam(name = "min", defaultValue = "0") BigDecimal min,
                             @RequestParam(name = "max", defaultValue = "100000000") BigDecimal max,
                             @RequestParam(name = "idLoai", required = false) Integer loaiId
    ) {

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            // Xử lý khi tên đăng nhập hoặc mật khẩu trống
            return "login/index";
        }

        AppUser appUser = appUserRepository.findAppUserByEmail(username);

        UserRole userRole = iUserRoleRepository.findUserRoleByAppUser(appUser);


//        if (appUser != null) {
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            if (passwordEncoder.matches(password, appUser.getEncrytedPassword())) {
//                if (userRole.getAppRole().getRoleName().equals("ROLE_ADMIN")) {
//                    // Xử lý đăng nhập cho admin
//                    // Thực hiện các thao tác đăng nhập của admin
//                    if (page < 1) {
//                        page = 1;
//                    }
//
//                    Pageable pageable = PageRequest.of(page - 1, 6);
//                    Page<ChitietSanPham> listCTSP;
//
//                    if (loaiId != null) {
//                        listCTSP = repositoryChiTiet.findByIdLoai(loaiId, pageable);
//                    } else if (keyword == null || keyword.isBlank() && min == null && max == null) {
//                        listCTSP = repositoryChiTiet.findAll(pageable);
//                    } else if (keyword == null || keyword.isBlank()) {
//                        listCTSP = repositoryChiTiet.findAll(pageable);
//
//                    } else {
//                        listCTSP = serviceChiTiet.searchByTenAndGiaRange(keyword, min, max, pageable);
//                    }
//                    model.addAttribute("listChiTietSanPham", listCTSP);
//
//                    return "chi-tiet-san-pham/tables";
//
//                } else
        if (userRole.getAppRole().getRoleName().equals("ROLE_USER")) {
            Pageable pageable = PageRequest.of(page - 1, 6);
            Page<ChitietSanPham> listCTSP;

            // Xử lý đăng nhập cho khách hàng
            // Thực hiện các thao tác đăng nhập của khách hàng
            if (loaiId != null) {
                listCTSP = repositoryChiTiet.findByIdLoai(loaiId, pageable);
            } else if (keyword == null || keyword.isBlank() && min == null && max == null) {
                listCTSP = repositoryChiTiet.findAll(pageable);
            } else if (keyword == null || keyword.isBlank()) {
                listCTSP = repositoryChiTiet.findAll(pageable);
            } else {
                listCTSP = serviceChiTiet.searchByTenAndGiaRange(keyword, min, max, pageable);
            }
            model.addAttribute("appUser", appUser);
            model.addAttribute("listChiTietSanPham", listCTSP);
            return "ban-hang/shop";

        } else {

            Pageable pageable = PageRequest.of(page - 1, 6);
            Page<ChitietSanPham> listCTSP;

            // Xử lý đăng nhập cho khách hàng
            // Thực hiện các thao tác đăng nhập của khách hàng
            if (loaiId != null) {
                listCTSP = repositoryChiTiet.findByIdLoai(loaiId, pageable);
            } else if (keyword == null || keyword.isBlank() && min == null && max == null) {
                listCTSP = repositoryChiTiet.findAll(pageable);
            } else if (keyword == null || keyword.isBlank()) {
                listCTSP = repositoryChiTiet.findAll(pageable);

            } else {
                listCTSP = serviceChiTiet.searchByTenAndGiaRange(keyword, min, max, pageable);
            }
            model.addAttribute("appUser", appUser);
            model.addAttribute("listChiTietSanPham", listCTSP);
            return "ban-hang/shop";


//            }
//        }

            // Xử lý khi tên đăng nhập hoặc mật khẩu không đúng
//            model.addAttribute("ERROR", "Username or password not exist");
//            return "login/index";

        }
    }
    @GetMapping("/cart/{userId}")
    private String cartUser(Model model,
                            @PathVariable(name = "userId") Long userId) {
        // tìm khach hang co id
        AppUser appUser = appUserRepository.findById(userId).orElse(null);
        // lấy ra gio hàng có id là khách hàng ở trêm
        GioHang gioHang = gioHangRepository.findGioHangByUserId(appUser);
        // lấy ghct
        List<GioHangChiTiet> gioHangChiTiets = gioHangChiTietRepository.findByIdGioHang(gioHang);
        model.addAttribute("gioHangChiTiets", gioHangChiTiets);
        model.addAttribute("appUser", appUser);
        return "ban-hang/cart";

    }



    @GetMapping("/my-order/{userId}")
    public String viewOrder(Model model, @PathVariable(name = "userId") Long userId) {
        // Lấy danh sách hóa đơn dựa trên userId từ repository
        List<HoaDon> hoaDons = iHoaDonRepository.findByUserId(userId);

        model.addAttribute("hoaDon", hoaDons);

        return "hoa-don/hoa-don"; // Trả về trang JSP hoặc thymeleaf tương ứng
    }


    HoaDonChiTiet hoaDon =new HoaDonChiTiet();

    @GetMapping("/chi-tiet-hoa-don/{userId}")
    public String viewHoaDonChiTiet(Model model,  @PathVariable(name = "userId") Long userId, @RequestParam(name = "hoaDonId", required = false) Integer hoaDonId) {

            List<HoaDonChiTiet> chiTietHoaDon ;

            if(hoaDonId != null){
                chiTietHoaDon = iHoaDonChiTietRepo.timKiemHoaDonCT(hoaDonId);
            }else {
               chiTietHoaDon =iHoaDonChiTietRepo.findAll();
            }

        List<HoaDon> hoaDons = iHoaDonRepository.findByUserId(userId);
        model.addAttribute("hoaDon", hoaDons);
        // Lấy danh sách tất cả các hóa đơn
//        List<HoaDon> listCTHD = iHoaDonRepository.findAll();
//        model.addAttribute("listCTHD", listCTHD);


        model.addAttribute("chiTietHoaDon",chiTietHoaDon);
//        model.addAttribute("traHang",hoaDon);

//        model.addAttribute("chiTietHoaDon",hoaDon);

        return "hoa-don/chitiethoadon";
    }

//    @GetMapping("/chi-tiet-hoa-don/view-all/{id}")
//    public String viewHoaDon(Model model, @RequestParam(name = "id") Integer id) {
//
//      HoaDon chiTietHoaDon =iHoaDonRepository.findHoaDonById(id);
//
//
//        model.addAttribute("hoaDon", chiTietHoaDon);
//
//
//        return "hoa-don/hoaDon";
//    }


//    @GetMapping("/chi-tiet-hoa-don/view-update/{id}")
//    public String viewHienThiTraHang(Model model, @PathVariable(name = "id") Integer id){
//        HoaDonChiTiet chiTietHoaDon1 =iHoaDonChiTietRepo.findHoaDonChiTietById(id);
//
//
//        // Lấy danh sách tất cả các hóa đơn
//
//        model.addAttribute("traHang",chiTietHoaDon1);
//
//        return "tra-hang/donhangtra";
//    }




    @PostMapping("/add-to-cart/{id}")
    private String addToCart(Model model, RedirectAttributes redirectAttributes, @PathVariable(name = "id") Integer id,
                             @RequestParam(name = "userId") Long userId) {
        AppUser appUser = appUserRepository.findById(userId).orElse(null);
        ChitietSanPham chitietSanPham = serviceChiTiet.findChitietSanPhamById(id);

        if (chitietSanPham.getSoLuongTon() > 2 ){
            chitietSanPham.setSoLuongTon(chitietSanPham.getSoLuongTon() - 1);
            serviceChiTiet.add(chitietSanPham);
            iGioHangChiTietSessionRepo.addToCart(chitietSanPham, userId);

        }else if(chitietSanPham.getSoLuongTon() >= 1) {
            redirectAttributes.addFlashAttribute("successMessage", "Sản phẩm đã hết hàng!");

        }

        model.addAttribute("appUser", appUser);
        return "redirect:/cart/" + userId;
    }

    @PostMapping("/add-to-cart-in-detail/{id}")
    private String addToCartInDetail(Model model, @PathVariable(name = "id") Integer id
            , @RequestParam(name = "userId") Long userId
            , @RequestParam(name = "soLuong") String soLuong
    ) {
        AppUser appUser = appUserRepository.findById(userId).orElse(null);
        ChitietSanPham chitietSanPham = serviceChiTiet.findChitietSanPhamById(id);
        iGioHangChiTietSessionRepo.addToCartinDetail(chitietSanPham, userId,Integer.valueOf(soLuong));
        model.addAttribute("appUser", appUser);
        return "redirect:/cart/" + userId;

    }

    @PostMapping("/reduce-cart/{id}")
    private String giamSanPham(Model model, @PathVariable(name = "id") Integer id
            , @RequestParam(name = "userId") Long userId
    ) {
        AppUser appUser = appUserRepository.findById(userId).orElse(null);
        ChitietSanPham chitietSanPham = serviceChiTiet.findChitietSanPhamById(id);

        chitietSanPham.setSoLuongTon(chitietSanPham.getSoLuongTon()+1);
        serviceChiTiet.add(chitietSanPham);

        iGioHangChiTietSessionRepo.truSanPham(userId, chitietSanPham);
        model.addAttribute("appUser", appUser);
        return "redirect:/cart/" + userId;


    }

    @PostMapping("/remove-cart/{id}")
    private String xoaSanPham(Model model, @PathVariable(name = "id") Integer id
            , @RequestParam(name = "userId") Long userId
    ) {
        AppUser appUser = appUserRepository.findById(userId).orElse(null);
        ChitietSanPham chitietSanPham = serviceChiTiet.findChitietSanPhamById(id);
        iGioHangChiTietSessionRepo.xoaSanPham(userId, chitietSanPham);
        model.addAttribute("appUser", appUser);
        return "redirect:/cart/" + userId;


    }


    @PostMapping("/tao-hoa-don")
    private String taoHoaDon(Model model,  @RequestParam(name = "userId") Long userId, @RequestParam(value = "totalPrice", required = false) BigDecimal totalPrice) {



            Integer tt = 1;
            TrangThaiOrder trangThaiOrder = iTrangThaiOrderRepo.findById(tt).orElse(null);
            AppUser appUser = appUserRepository.findById(userId).orElse(null);
            GioHang gioHang = gioHangRepository.findGioHangByUserId(appUser);
            // lấy ghct
            List<GioHangChiTiet> gioHangChiTiets = gioHangChiTietRepository.findByIdGioHang(gioHang);
            HoaDon hoaDon = new HoaDon();
            hoaDon.setUserId(appUser);
            hoaDon.setIdTT(trangThaiOrder);
            hoaDon.setTongTien(totalPrice);
            LocalDateTime currentDateTime = LocalDateTime.now();
            hoaDon.setNgayDat(currentDateTime);
            iHoaDonRepository.save(hoaDon);

            Integer idHoaDonVuaTao = hoaDon.getId();
            HoaDon hoaDonIdVuaTao = iHoaDonRepository.findById(idHoaDonVuaTao).orElse(null);
            for (GioHangChiTiet gioHangChiTiet : gioHangChiTiets) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setIdHoaDon(hoaDonIdVuaTao);
                hoaDonChiTiet.setIdChiTietSanPham(gioHangChiTiet.getIdChiTietSanPham());
                hoaDonChiTiet.setSoLuong(gioHangChiTiet.getSoLuong());
                hoaDonChiTiet.setDonGia(gioHangChiTiet.getDonGia());
                iHoaDonChiTietRepo.save(hoaDonChiTiet);
            }
            model.addAttribute("hoaDon", hoaDonIdVuaTao);
//        model.addAttribute("hoaDon", hoaDon2);
            return "thanh-toan/checkout";
        }




    @PostMapping("/thanh-toan/{idHoaDon}")
    private String datHang( Model model
            , @PathVariable(name = "idHoaDon") Integer idHoaDon
            , @RequestParam(name = "sdt") String sdt
            , @RequestParam(name = "diaChi") String diaChi
            , @RequestParam(name = "tenNguoiNhan") String tenNguoiNhan
            , @RequestParam(name = "sdtNguoiNhan") String sdtNguoiNhan

    ) {



        HoaDon hoaDon = iHoaDonRepository.findById(idHoaDon).orElse(null);
            hoaDon.setSdt(sdt);
            hoaDon.setDiaChi(diaChi);
            hoaDon.setTenNguoiNhan(tenNguoiNhan);
            hoaDon.setSdtNguoiNhan(sdtNguoiNhan);
            iHoaDonRepository.save(hoaDon);

            AppUser appUser = appUserRepository.findById(hoaDon.getUserId().getUserId()).orElse(null);
            GioHang gioHang = gioHangRepository.findGioHangByUserId(appUser);
            List<GioHangChiTiet> gioHangChiTiet = gioHangChiTietRepository.findByIdGioHang(gioHang);
            gioHangChiTietRepository.deleteAll(gioHangChiTiet);
            model.addAttribute("successMessage", "Bạn đã đặt hàng thành công đơn hàng sẽ đến trong vài ngày nữa.");
            model.addAttribute("appUser", appUser);

            return "thanh-toan/thanh-cong";

        }



    @GetMapping("/detail/{id}/{idUser}")
    private String detail(Model model, @PathVariable(name = "id") Integer id
//    ,  @RequestParam(name = "userId") Long userId
            , @PathVariable(name = "idUser") Long idUser
    ) {
        ChitietSanPham chiTietSanPham =serviceChiTiet.findChitietSanPhamById(id);
        AppUser appUser = appUserRepository.findById(idUser).orElse(null);

        model.addAttribute("appUser", appUser);

        model.addAttribute("chiTietSP", chiTietSanPham);

        return "ban-hang/detail";

    }




}
