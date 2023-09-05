package com.example.demo.model;

import com.example.demo.repository.IKhuyenMaiRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "khuyenmai")
@Entity
public class KhuyenMai {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    @NotBlank(message = "Mã không được để trống")
    @Column(name = "ma")
    private String ma;

    //    @NotBlank(message = "Mã không được để trống")
    @Column(name = "ten")
    private String ten;

    //    @NotBlank(message = "Tên không được để trống")
    @Column(name = "phantramgiam")
    private Float phanTramGiam;

    @Column(name = "ngaybatdau")
    private LocalDate ngayBatDau;

    @Column(name = "ngayketthuc")
    private LocalDate ngayKetThuc;

    @ManyToOne
    @JoinColumn(name = "trangthai")
    private TrangThai trangThai;

}
