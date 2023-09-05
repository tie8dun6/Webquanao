package com.example.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "loai")
@Entity
public class Loai {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Mã không được để trống")
    @Column(name = "ma")
    private String ma;

    @NotBlank(message = "Tên không được để trống")
    @Column(name = "ten")
    private String ten;


    @Column(name = "trangthai")
    private int trangThai;
}
