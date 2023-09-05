package com.example.demo.main2;

import com.example.demo.model.ChitietSanPham;
import com.example.demo.model.KhuyenMai;
import com.example.demo.model.TrangThai;
import com.example.demo.repository.IChiTietSanPhamRepository;
import com.example.demo.repository.IKhuyenMaiRepository;
import com.example.demo.repository.ITrangThaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@EnableScheduling
@Service
public class AutoKhuyenMaiMain {
    @Autowired
    private IKhuyenMaiRepository iKhuyenMaiRepository;

    @Autowired
    private ITrangThaiRepository iTrangThaiRepository;

    @Autowired
    private IChiTietSanPhamRepository iChiTietSanPhamRepository;

    @Async
    public void processInBackground() {
        // Thực hiện tác vụ nền
        // ...
    }

    @Scheduled(fixedRate = 600000000) // Chạy mỗi 1 phút
    public void scheduleBackgroundTask() {
        LocalDate currentDate = LocalDate.now();

        TrangThai trangThai = iTrangThaiRepository.findTrangThaiById(1);/* dang hoat dong*/
        TrangThai trangThai2 = iTrangThaiRepository.findTrangThaiById(2);/* ngung hoat dong*/
        TrangThai trangThai3 = iTrangThaiRepository.findTrangThaiById(3);/* chua dien ra*/
        List<ChitietSanPham> chitietSanPhamList = iChiTietSanPhamRepository.findAll();

        List<KhuyenMai> khuyenMaiList = iKhuyenMaiRepository.findAll();
        for (int i = 0; i < khuyenMaiList.size(); i++) {
            if (!khuyenMaiList.get(i).getTrangThai().getId().equals(trangThai2.getId())) {
                if (khuyenMaiList.get(i).getNgayBatDau().isBefore(currentDate) || khuyenMaiList.get(i).getNgayBatDau().isEqual(currentDate)
                        && khuyenMaiList.get(i).getNgayKetThuc().isAfter(currentDate) || khuyenMaiList.get(i).getNgayKetThuc().isEqual(currentDate) ) {
                    khuyenMaiList.get(i).setTrangThai(trangThai);
                    iKhuyenMaiRepository.save(khuyenMaiList.get(i));
                    Float ptKm = khuyenMaiList.get(i).getPhanTramGiam();
                    for (ChitietSanPham chitietSanPham : chitietSanPhamList) {
                        Float giaBanDau = chitietSanPham.getGiaBan();
                        Float giaKM = giaBanDau - giaBanDau * ptKm / 100;
                        chitietSanPham.setGiaKhuyenMai(giaKM);
                        iChiTietSanPhamRepository.save(chitietSanPham);
                    }


                } else if (khuyenMaiList.get(i).getNgayBatDau().isBefore(currentDate) && khuyenMaiList.get(i).getNgayKetThuc().isBefore(currentDate)) {
                    khuyenMaiList.get(i).setTrangThai(trangThai2);
                    iKhuyenMaiRepository.save(khuyenMaiList.get(i));
                    for (ChitietSanPham chitietSanPham : chitietSanPhamList) {
                        chitietSanPham.setGiaKhuyenMai(0F);
                        iChiTietSanPhamRepository.save(chitietSanPham);
                    }
                } else {
                    khuyenMaiList.get(i).setTrangThai(trangThai3);
                    iKhuyenMaiRepository.save(khuyenMaiList.get(i));
                    Float ptKm = khuyenMaiList.get(i).getPhanTramGiam();
//                    for (ChitietSanPham chitietSanPham : chitietSanPhamList) {
//                        Float giaBanDau = chitietSanPham.getGiaBan();
//                        Float giaKM = giaBanDau - giaBanDau * ptKm / 100;
//                        chitietSanPham.setGiaKhuyenMai(giaKM);
//                        iChiTietSanPhamRepository.save(chitietSanPham);
//                    }

                }
            }

        }

    }
}

