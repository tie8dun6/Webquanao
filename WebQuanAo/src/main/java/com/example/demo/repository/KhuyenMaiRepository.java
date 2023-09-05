package com.example.demo.repository;

import com.example.demo.controller.KhuyenMaiController;
import com.example.demo.model.ChitietSanPham;
import com.example.demo.model.KhuyenMai;
import com.example.demo.model.TrangThai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KhuyenMaiRepository {
    @Autowired
    private IKhuyenMaiRepository iKhuyenMaiRepository;

    @Autowired
    private ITrangThaiRepository iTrangThaiRepository;

    @Autowired
    private IChiTietSanPhamRepository iChiTietSanPhamRepository;


    public void updateAll(KhuyenMai khuyenMai) {
        List<ChitietSanPham> chitietSanPhamList = iChiTietSanPhamRepository.findAll();
        List<KhuyenMai> khuyenMaiList = iKhuyenMaiRepository.findAll();
        TrangThai trangThai = iTrangThaiRepository.findTrangThaiById(1);/* dang hoat dong*/
        TrangThai trangThai2 = iTrangThaiRepository.findTrangThaiById(2);/* ngung hoat dong*/
        TrangThai trangThai3 = iTrangThaiRepository.findTrangThaiById(3);/* chua dien ra*/
        float ptKM = khuyenMai.getPhanTramGiam();
        for (ChitietSanPham chitietSanPham : chitietSanPhamList) {
            Float giaBanDau = chitietSanPham.getGiaBan();
            Float giaKM = giaBanDau - giaBanDau * ptKM / 100;
            chitietSanPham.setGiaKhuyenMai(giaKM);
            iChiTietSanPhamRepository.save(chitietSanPham);
        }
        for (KhuyenMai khuyenMai1 : khuyenMaiList) {
            if (khuyenMai1.getId() != khuyenMai.getId()) {
                if (khuyenMai1.getTrangThai() == trangThai) {
                    khuyenMai1.setTrangThai(trangThai2);
                    iKhuyenMaiRepository.save(khuyenMai1);
                }
            }
        }
    }
}
