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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "appuser")
@Entity

public class Nhanvien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

//
    @Column(name = "username")
    private String userName;

    @Column(name = "Encryted_Password")
    private String encrytedPassword;


    @Column(name = "Email")
    private String email;

//
    @Column(name = "cccd")
    private String cccd;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "birthdate")
    private String birthdate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "addresses")
    private String addresses;

    @Column(name = "qrcodedate")
    private String qrcodedate;

    @ManyToOne
    @JoinColumn(name = "trangthai")
    private TrangThai trangThai;


}
