package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.ChitietSanPham;
import com.example.demo.model.GioHang;
import com.example.demo.model.GioHangChiTiet;
import com.example.demo.service.IChiTietSanPhamService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Repository
public class GioHangChiTietSessionRepo implements IGioHangChiTietSessionRepo {
    @PersistenceContext
    private EntityManager entityManager;
    private PlatformTransactionManager transactionManager;
    @Autowired
    private IChiTietSanPhamService serviceChiTiet;
    @Autowired
    private IGioHangRepository gioHangRepository;
    @Autowired
    private IAppUserRepository appUserRepository;
    @Autowired
    private IGioHangChiTietRepository gioHangChiTietRepository;
    @Autowired
    private IChiTietSanPhamRepository repositoryChiTiet;

    @Override
    public void addToCart(ChitietSanPham chiTietSanPham, Long userId) {
        AppUser appUser = appUserRepository.findById(userId).orElse(null);
        GioHang gioHang = gioHangRepository.findGioHangByUserId(appUser);
        List<GioHangChiTiet> gioHangChiTiet = gioHangChiTietRepository.findByIdGioHang(gioHang);
        boolean productExists = false;
        for (GioHangChiTiet gioHangChiTietItem : gioHangChiTiet) {
            if (gioHangChiTietItem.getIdChiTietSanPham().getId() == chiTietSanPham.getId()) {
                // Sản phẩm đã tồn tại, tăng số lượng lên 1
                gioHangChiTietItem.setSoLuong(gioHangChiTietItem.getSoLuong() + 1);
                productExists = true;
                break;
            }
        }

        // Nếu sản phẩm chưa tồn tại, tạo mới giỏ hàng chi tiết và thêm vào danh sách
        if (!productExists) {
            GioHangChiTiet newGioHangChiTiet = new GioHangChiTiet();
            newGioHangChiTiet.setIdChiTietSanPham(chiTietSanPham);
            newGioHangChiTiet.setIdGioHang(gioHang);
            newGioHangChiTiet.setSoLuong(1);
            newGioHangChiTiet.setDonGia(BigDecimal.valueOf(chiTietSanPham.getGiaBan()));
            gioHangChiTiet.add(newGioHangChiTiet);
        }

        // Lưu thông tin vào cơ sở dữ liệu
        gioHangChiTietRepository.saveAll(gioHangChiTiet);
    }
    @Override
    public void addToCartinDetail(ChitietSanPham chiTietSanPham, Long userId,Integer soLuong) {
        AppUser appUser = appUserRepository.findById(userId).orElse(null);
        GioHang gioHang = gioHangRepository.findGioHangByUserId(appUser);
        List<GioHangChiTiet> gioHangChiTiet = gioHangChiTietRepository.findByIdGioHang(gioHang);
        boolean productExists = false;
        for (GioHangChiTiet gioHangChiTietItem : gioHangChiTiet) {
            if (gioHangChiTietItem.getIdChiTietSanPham().getId() == chiTietSanPham.getId()) {
                // Sản phẩm đã tồn tại, tăng số lượng lên 1
                gioHangChiTietItem.setSoLuong(gioHangChiTietItem.getSoLuong() + soLuong);
                productExists = true;
                break;
            }
        }

        // Nếu sản phẩm chưa tồn tại, tạo mới giỏ hàng chi tiết và thêm vào danh sách
        if (!productExists) {
            GioHangChiTiet newGioHangChiTiet = new GioHangChiTiet();
            newGioHangChiTiet.setIdChiTietSanPham(chiTietSanPham);
            newGioHangChiTiet.setIdGioHang(gioHang);
            newGioHangChiTiet.setSoLuong(soLuong);
            newGioHangChiTiet.setDonGia(BigDecimal.valueOf(chiTietSanPham.getGiaBan()));
            gioHangChiTiet.add(newGioHangChiTiet);
        }

        // Lưu thông tin vào cơ sở dữ liệu
        gioHangChiTietRepository.saveAll(gioHangChiTiet);
    }

    @Override
    public void truSanPham(Long userId, ChitietSanPham chiTietSanPham) {
        AppUser appUser = appUserRepository.findById(userId).orElse(null);
        GioHang gioHang = gioHangRepository.findGioHangByUserId(appUser);
        List<GioHangChiTiet> gioHangChiTiet = gioHangChiTietRepository.findByIdGioHang(gioHang);
        boolean productExists = false;
        for (GioHangChiTiet gioHangChiTietItem : gioHangChiTiet) {
            int newSoLuong = gioHangChiTietItem.getSoLuong() - 1;
            if (newSoLuong >= 1) {
                gioHangChiTietItem.setSoLuong(newSoLuong);
            } else {
                // Nếu số lượng sau khi giảm đi 1 là nhỏ hơn 0, xóa sản phẩm khỏi giỏ hàng
                // gioHangChiTiet.removeIf(item -> item.getIdChiTietSanPham().getId() == chiTietSanPham.getId());
                gioHangChiTietRepository.delete(gioHangChiTietItem);
            }
            productExists = true;
            break;
        }

    }

    @Override
    public void xoaSanPham(Long userId, ChitietSanPham chiTietSanPham) {
        AppUser appUser = appUserRepository.findById(userId).orElse(null);
        GioHang gioHang = gioHangRepository.findGioHangByUserId(appUser);
        List<GioHangChiTiet> gioHangChiTiet = gioHangChiTietRepository.findByIdGioHang(gioHang);
        for (GioHangChiTiet gioHangChiTietItem : gioHangChiTiet) {
            if (gioHangChiTietItem.getIdChiTietSanPham().getId() == chiTietSanPham.getId()) {
                gioHangChiTietRepository.delete(gioHangChiTietItem);

            }
        }
    }
}

