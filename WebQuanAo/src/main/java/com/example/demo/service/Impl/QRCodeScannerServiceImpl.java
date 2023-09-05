package com.example.demo.service.Impl;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.example.demo.model.Nhanvien;
import com.example.demo.repository.INhanVienRepository;
import com.example.demo.service.QRCodeScannerService;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class QRCodeScannerServiceImpl implements QRCodeScannerService {

    private Webcam webcam; // Đối tượng Webcam để quét mã QR
    private WebcamPanel webcamPanel; // Đối tượng WebcamPanel để hiển thị hình ảnh từ webcam

    private String latestQRCodeData;

    @Autowired
    private INhanVienRepository repository;

    @Override
    public void startScanner() {
        // Khởi tạo Webcam và WebcamPanel
        webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        // Khởi tạo JFrame để hiển thị WebcamPanel
        JFrame window = new JFrame("QR Code Scanner");
        webcamPanel = new WebcamPanel(webcam);
        window.add(webcamPanel);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        // Bắt đầu quét mã QR
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            while (true) {
                try {
                    String qrCodeData = readQRCode(webcam);
                    if (qrCodeData != null && !qrCodeData.isEmpty()) {
                        latestQRCodeData = qrCodeData; // Lưu dữ liệu mã QR mới nhất vào biến latestQRCodeData
                        processAndSaveQRCode(qrCodeData); // Quét và lưu dữ liệu từ mã QR vào cơ sở dữ liệu
                    }
                } catch (Exception e) {
                    handleQRCodeError(e);
                }
            }
        });
    }

    private String readQRCode(Webcam webcam) throws NotFoundException, FormatException, ChecksumException {
        BufferedImage image = webcam.getImage();
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));

        Reader reader = new MultiFormatReader();
        Result result = reader.decode(bitmap, hints);

        // Trả về nội dung mã QR nếu quét thành công
        return result != null ? result.getText() : null;
    }


    // Phương thức xử lý dữ liệu từ mã QR và lưu vào cơ sở dữ liệu
    @Override
    public Nhanvien processAndSaveQRCode(String qrCodeData) {
        // Tách các trường dữ liệu từ mã QR
        String[] qrCodeFields = qrCodeData.split("\\|");

        // Kiểm tra số lượng trường dữ liệu
        if (qrCodeFields.length != 7) {
            throw new IllegalArgumentException("Invalid QR code data");
        }

        // Tạo đối tượng QRCodeData từ các trường dữ liệu
        Nhanvien qrCodeDataObj = new Nhanvien();
        qrCodeDataObj.setCccd(qrCodeFields[0]);
        qrCodeDataObj.setFullname(qrCodeFields[1]);
        qrCodeDataObj.setBirthdate(qrCodeFields[2]);
        qrCodeDataObj.setGender(qrCodeFields[3]);
        qrCodeDataObj.setAddresses(qrCodeFields[4]);
        qrCodeDataObj.setQrcodedate(qrCodeFields[5]);

        // Lưu đối tượng QRCodeData vào cơ sở dữ liệu
        repository.save(qrCodeDataObj);
        return qrCodeDataObj;
    }





    public String getLatestQRCodeData() {
        return latestQRCodeData;
    }

    // Xử lý lỗi khi quét mã QR
    private void handleQRCodeError(Exception e) {
        // Xử lý lỗi, ví dụ: in log hoặc thông báo lỗi
        e.printStackTrace();
    }

//
//    @Override
//    public void stopScanner() {
//        webcamPanel.stop();
//        webcam.close();
//    }

}
