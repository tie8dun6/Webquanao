package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "giohangchitiet")
@Entity
public class GioHangChiTiet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "idgiohang")
    private GioHang idGioHang;
    @ManyToOne
    @JoinColumn(name = "idchitietsanpham    ")
    private ChitietSanPham idChiTietSanPham;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "dongia")
    private BigDecimal donGia;
}
