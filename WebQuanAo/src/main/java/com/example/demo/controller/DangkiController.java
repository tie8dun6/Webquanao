package com.example.demo.controller;

import com.example.demo.model.AppRole;
import com.example.demo.model.AppUser;
import com.example.demo.model.UserRole;
import com.example.demo.model.Utility;
import com.example.demo.repository.IAppRoleRepository;
import com.example.demo.repository.IAppUserRepository;
import com.example.demo.repository.IUserRoleRepository;
import com.example.demo.service.ILoginService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class DangkiController {

    @Autowired
    private IAppUserRepository repository;

    @Autowired
    private ILoginService service;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
            private IUserRoleRepository repositoryUserRole;
    @Autowired
            private IAppRoleRepository repositoryAppRole;

    AppUser appUser1 =new AppUser();

    @GetMapping("/dang-ki")
    public String dangKi(Model model){

        model.addAttribute("appUser", appUser1);
        return "login/index1";
    }

    @PostMapping("/dang-ki/add")
    public String dangKiTaiKhoan(Model model,@Valid @ModelAttribute(name = "appUser") AppUser appUser, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // Kiểm tra lỗi validation
        if (bindingResult.hasErrors()) {
            // Xử lý lỗi validation ở đây (nếu cần)
            return "login/index1";
        }

        // Tạo một đối tượng BCryptPasswordEncoder để mã hóa mật khẩu
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Lấy mật khẩu từ appUser
        String rawPassword = appUser.getEncrytedPassword();

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Đặt mật khẩu đã mã hóa lại vào appUser
        appUser.setEncrytedPassword(encodedPassword);

        // Lưu appUser (có mật khẩu đã mã hóa) vào cơ sở dữ liệu
         repository.save(appUser);

         Long idAppUser = appUser.getUserId();
        AppUser savedAppUser = repository.findById(idAppUser).orElse(null);
        AppRole appRole=repositoryAppRole.findById(2L).orElse(null);


        UserRole userRole =new UserRole();

        userRole.setAppUser(savedAppUser);
        userRole.setAppRole(appRole);
        repositoryUserRole.save(userRole);

        // Thêm thông báo thành công vào redirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Đăng ký tài khoản thành công");
        model.addAttribute("ddd",idAppUser);
        model.addAttribute("ddd3",savedAppUser);
        // Redirect đến trang "/dang-ki"
        return "redirect:/dang-ki";
//        return "login/Error";

    }



    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "login/forgot";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(10);

        // Cập nhật mã thông báo đặt lại mật khẩu trong cơ sở dữ liệu
        service.updateResetPasswordToken(token, email);

        try {
            // Tạo URL đặt lại mật khẩu
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;

            // Gửi email chứa liên kết đặt lại mật khẩu
            sendEmail(email, resetPasswordLink);

            // Trả về giao diện forgot thành công
            return "login/forgot";
        } catch (UnsupportedEncodingException | MessagingException e) {
            // Xử lý lỗi gửi email
            model.addAttribute("error", "Đã xảy ra lỗi trong quá trình gửi email.");
            return "login/Error";
        }
    }

    public void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("codejava.net@gmail.com", "Quen Mat Khau");

        helper.setTo(email);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

//    @GetMapping("/reset_password")
//    private String resetPass()

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        AppUser customer = service.get(token);

        model.addAttribute("token", token);
        return "login/resetpass";
    }
    //
    @PostMapping("/reset_password")
    public String processResetPassword(@RequestParam("token") String token, @RequestParam("ten") String password, Model model) {
        AppUser chucVu = service.getAppUserByResetPassToken(token);
        if (chucVu == null) {
            model.addAttribute("message", "Invalid Token");
        } else {
            service.updatePassword(chucVu,password);
            model.addAttribute("message", "You have successfully changed your password.");
        }
        model.addAttribute("title", "Reset your password");
        return "login/index";
    }





}
