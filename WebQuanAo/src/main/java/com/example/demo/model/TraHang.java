package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "trahang")
@Entity
public class TraHang {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "madonhang")
    private HoaDonChiTiet chiTietHoaDon;

//    @ManyToOne
//    @JoinColumn(name = "orderitemid")
//    private HoaDonChiTiet hoaDonId;

    @Column(name = "ngaytra")
    private LocalDateTime ngayTra;

    @Column(name = "soluong")
    private String soLuong;

    @Column(name = "lydotrahang")
    private String liDo;

}
