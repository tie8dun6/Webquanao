package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "hoadon")
@Entity
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "userid")
    private AppUser UserId;

//    @NotBlank(message = "Số điện thoại không được để trống  ")
//    @Pattern(regexp = "^[0-9]{10}$", message = "Số điện thoại phải chứa đúng 10 chữ số")
    @Column(name = "sdt")
    private String sdt;

//    @NotBlank(message = "Địa chỉ không được để trống  ")
    @Column(name = "diachi")
    private String diaChi;

    @ManyToOne
    @JoinColumn(name = "idtt")
    private TrangThaiOrder idTT;

    @Column(name = "ngaydat")
    private LocalDateTime ngayDat;

    @Column(name = "ngayship")
    private LocalDateTime ngayShip;

    @Column(name = "ngaykhachnhan")
    private LocalDateTime ngayKhachNhan;

    @Column(name = "tongtien")
    private BigDecimal tongTien;

//    @NotBlank(message = "Tên người nhận không được để trống  ")
    @Column(name = "tennguoinhan")
    private String tenNguoiNhan;

//    @NotBlank(message = "Số điện thoại không được để trống  ")
//    @Pattern(regexp = "^[0-9]{10}$", message = "Số điện thoại phải chứa đúng 10 chữ số")
    @Column(name = "sdtnguoinhan")
    private String sdtNguoiNhan;



}
