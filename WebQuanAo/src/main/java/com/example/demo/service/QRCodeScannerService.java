package com.example.demo.service;

import com.example.demo.model.Nhanvien;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;

import java.awt.image.BufferedImage;

public interface QRCodeScannerService {
    void startScanner(); // Bắt đầu quét mã QR bằng webcam

//    void stopScanner(); // Dừng quét mã QR

    Nhanvien processAndSaveQRCode(String qrCodeData);

    String getLatestQRCodeData();

//    void processQRCode(String qrCodeData); // Xử lý dữ liệu mã QR
//
//    void handleQRCodeError(Exception e); // Xử lý lỗi khi quét mã QR
}
