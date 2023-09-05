package com.example.demo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "chitietsanpham")
@Entity
public class ChitietSanPham {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Tên không được để trống")
    @Column(name = "ten")
    private String ten;

    @ManyToOne
    @JoinColumn(name = "idmausac")
    private MauSac idMauSac;

    @ManyToOne
    @JoinColumn(name = "idhang")
    private Hang idHang;

    @ManyToOne
    @JoinColumn(name = "idkichco")
    private KichCo idKichCo;

    @ManyToOne
    @JoinColumn(name = "idchatlieu")
    private ChatLieu idChatLieu;

    @ManyToOne
    @JoinColumn(name = "idloai")
    private Loai idLoai;
    @Column(name = "soluongton")
    @Min(value = 1, message = "Số lượng không được để trống và phải lớn hơn hoặc bằng 0")
    private int soLuongTon;
    @NotNull(message = "Giá không được để trống")
    @Column(name = "gia")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá không được để trống và phải lớn hơn 0")
    private Float giaBan;

    @Column(name = "giakhuyenmai")
    private Float  giaKhuyenMai;

    @ManyToOne
    @JoinColumn(name = "trangthai")
    private TrangThai idTrangThai;

    @NotNull(message = "Ngày nhập không được để trống")
    @Column(name = "ngaynhap")
    private LocalDate ngayNhap;

    @NotNull(message = "Ngày chỉnh sửa không được để trống")
    @Column(name = "ngaychinhsua")
    private LocalDate  ngayChinhSua;

    @NotBlank(message = "Ảnh không được để trống")
    @Column(name = "image")
    private String image;


}
