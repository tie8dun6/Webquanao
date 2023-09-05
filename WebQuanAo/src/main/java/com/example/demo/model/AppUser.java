package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

    @NotBlank(message = "Tên không được để trống ")
    @Column(name = "username")
    private String userName;

    @NotBlank(message = "Mật khẩu không được để trống ")
    @Column(name = "Encryted_Password")
    private String encrytedPassword;

    @Column(name = "Enabled")
    private boolean enabled;

    @NotBlank(message = "Email không được để trống ")
    @Column(name = "Email")
    private String email;


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
    //
    @ManyToOne
    @JoinColumn(name = "trangthai")
    private TrangThai trangThai;

    @Column(name = "reset_pass_token")
    private String resetPassToken;

}
