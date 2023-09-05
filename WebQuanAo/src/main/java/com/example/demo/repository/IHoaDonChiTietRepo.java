package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.HoaDon;
import com.example.demo.model.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface IHoaDonChiTietRepo extends JpaRepository<HoaDonChiTiet, Integer> {

  HoaDonChiTiet findHoaDonChiTietById(Integer id);

  @Query("SELECT c FROM HoaDonChiTiet c  WHERE c.idHoaDon.id = :hoaDonId")
  List<HoaDonChiTiet> timKiemHoaDonCT(@Param("hoaDonId") Integer hoaDonId);


//  @Query("SELECT c FROM HoaDonChiTiet c JOIN c.idHoaDon h WHERE h.UserId.userId = :userId")
//  List<HoaDonChiTiet> timKiemHoaDonCTTheoUserId(@Param("userId") Long userId);


//  List<HoaDonChiTiet> findByAppUser(AppUser appUser);




  @Query("SELECT c.id AS idCTSP, c.ten, CONVERT(VARCHAR(100), c.image) AS image, SUM(a.soLuong) AS tongSoLuong " +
            "FROM HoaDonChiTiet a " +
            "JOIN a.idHoaDon b " +
            "JOIN a.idChiTietSanPham c " +
            "WHERE FUNCTION('MONTH', b.ngayDat) = :selectedMonth " +
            "AND FUNCTION('YEAR', b.ngayDat) = FUNCTION('YEAR', CURRENT_DATE) " +
            "GROUP BY c.id, CONVERT(VARCHAR(100), c.image), c.ten " +
            "ORDER BY SUM(a.soLuong) DESC")
    List<Object[]> getTop10SanPhamTheoThang(@Param("selectedMonth") int selectedMonth);


    @Query("SELECT c.id AS idCTSP,c.ten, CONVERT(VARCHAR(100), c.image) AS image, SUM(a.soLuong) AS tongSoLuong " +
            "FROM HoaDonChiTiet a " +
            "JOIN a.idHoaDon b " +
            "JOIN a.idChiTietSanPham c " +
            "WHERE FUNCTION('DAY', b.ngayDat) = FUNCTION('DAY', CURRENT_DATE) " +   // Lấy theo ngày hôm nay
            "AND FUNCTION('MONTH', b.ngayDat) = FUNCTION('MONTH', CURRENT_DATE) " + // Lấy theo tháng hiện tại
            "AND FUNCTION('YEAR', b.ngayDat) = FUNCTION('YEAR', CURRENT_DATE) " +   // Lấy theo năm hiện tại
            "GROUP BY c.id, CONVERT(VARCHAR(100), c.image),c.ten " +
            "ORDER BY SUM(a.soLuong) DESC")
    List<Object[]> getTop10SanPhamTheoNgay();

    @Query("SELECT c.id AS idCTSP, c.ten, CONVERT(VARCHAR(100), c.image) AS image, SUM(a.soLuong) AS tongSoLuong " +
            "FROM HoaDonChiTiet a " +
            "JOIN a.idHoaDon b " +
            "JOIN a.idChiTietSanPham c " +
            "WHERE FUNCTION('DAY', b.ngayDat) = FUNCTION('DAY', :selectedDate) " +   // Lấy theo ngày được chọn từ input
            "AND FUNCTION('MONTH', b.ngayDat) = FUNCTION('MONTH', :selectedDate) " + // Lấy theo tháng được chọn từ input
            "AND FUNCTION('YEAR', b.ngayDat) = FUNCTION('YEAR', :selectedDate) " +   // Lấy theo năm được chọn từ input
            "GROUP BY c.id, CONVERT(VARCHAR(100), c.image), c.ten " +
            "ORDER BY SUM(a.soLuong) DESC")
    List<Object[]> getTop10SanPhamTheoNgay2(@Param("selectedDate") Date selectedDate);
    @Query("SELECT c.id AS idCTSP, c.ten, CONVERT(VARCHAR(100), c.image) AS image, SUM(a.soLuong) AS tongSoLuong " +
            "FROM HoaDonChiTiet a " +
            "JOIN a.idHoaDon b " +
            "JOIN a.idChiTietSanPham c " +
            "WHERE b.ngayDat >= :selectedDateStart and b.ngayDat <= :selectedDateEnd" +   // Lấy theo ngày được chọn từ input
            " group by c.id, CONVERT(VARCHAR(100), c.image), c.ten " +
            "ORDER BY SUM(a.soLuong) DESC")
    List<Object[]> getTop10SanPhamTheoKhoangNgay(@Param("selectedDateStart") LocalDateTime selectedDateStart,
                                                 @Param("selectedDateEnd") LocalDateTime selectedDateEnd

    );

    @Query("SELECT c.id AS idCTSP, c.ten, CONVERT(VARCHAR(100), c.image) AS image, SUM(a.soLuong) AS tongSoLuong " +
            "FROM HoaDonChiTiet a " +
            "JOIN a.idHoaDon b " +
            "JOIN a.idChiTietSanPham c " +
            "WHERE FUNCTION('YEAR', b.ngayDat) = FUNCTION('YEAR', :selectedDate) " +
            "AND FUNCTION('MONTH', b.ngayDat) = FUNCTION('MONTH', :selectedDate) " +
            "GROUP BY c.id, c.ten, CONVERT(VARCHAR(100), c.image) " +
            "ORDER BY SUM(a.soLuong) DESC")
    List<Object[]> getTop10SanPhamTheoThang2(@Param("selectedDate") Date selectedDate);
    @Query("SELECT c.id AS idCTSP, c.ten, CONVERT(VARCHAR(100), c.image) AS image, SUM(a.soLuong) AS tongSoLuong " +
            "FROM HoaDonChiTiet a " +
            "JOIN a.idHoaDon b " +
            "JOIN a.idChiTietSanPham c " +
            "WHERE FUNCTION('YEAR', b.ngayDat) = FUNCTION('YEAR', :selectedDate) " +
            "GROUP BY c.id, c.ten, CONVERT(VARCHAR(100), c.image) " +
            "ORDER BY SUM(a.soLuong) DESC")
    List<Object[]> getTop10SanPhamTheoNam(@Param("selectedDate") Date selectedDate);


//    bán chậm nhất theo năm
@Query("SELECT c.id AS idCTSP, c.ten, CONVERT(VARCHAR(100), c.image) AS image, SUM(a.soLuong) AS tongSoLuong " +
        "FROM HoaDonChiTiet a " +
        "JOIN a.idHoaDon b " +
        "JOIN a.idChiTietSanPham c " +
        "WHERE FUNCTION('YEAR', b.ngayDat) = FUNCTION('YEAR', :selectedDate) " +
        "GROUP BY c.id, c.ten, CONVERT(VARCHAR(100), c.image) " +
        "ORDER BY SUM(a.soLuong) ASC") // Sắp xếp theo tổng số lượng tăng dần (chậm nhất trước)
List<Object[]> getTop10SanPhamChamNhatTheoNam(@Param("selectedDate") Date selectedDate);



}
