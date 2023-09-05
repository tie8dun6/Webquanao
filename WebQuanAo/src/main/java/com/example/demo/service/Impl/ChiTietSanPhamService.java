package com.example.demo.service.Impl;

import com.example.demo.model.ChatLieu;
import com.example.demo.model.ChitietSanPham;
import com.example.demo.model.Hang;
import com.example.demo.model.KichCo;
import com.example.demo.model.Loai;
import com.example.demo.model.MauSac;
import com.example.demo.model.TrangThai;
import com.example.demo.repository.IChatLieuRepository;
import com.example.demo.repository.IChiTietSanPhamRepository;
import com.example.demo.repository.IHangRepository;
import com.example.demo.repository.IKichCoRepository;
import com.example.demo.repository.ILoaiRepository;
import com.example.demo.repository.IMauSacRepository;
import com.example.demo.repository.ITrangThaiRepository;
import com.example.demo.service.IChiTietSanPhamService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ChiTietSanPhamService implements IChiTietSanPhamService {


    @Autowired
    private IHangRepository repositoryHang;

    @Autowired
    private ILoaiRepository repositoryLoai;

    @Autowired
    private IMauSacRepository repositoryMauSac;

    @Autowired
    private IChiTietSanPhamRepository repositoryChiTiet;

    @Autowired
    private IChatLieuRepository repositoryChatLieu;

    @Autowired
    private IKichCoRepository repositoryKichCo;
    @Autowired
    private ITrangThaiRepository trangThaiRepository;

    @Autowired
    private IChiTietSanPhamRepository repository;


    @Override
    public Page<ChitietSanPham> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void add(ChitietSanPham chiTietSanPham) {
        repository.save(chiTietSanPham);
    }

    @Override
    public void update(ChitietSanPham chiTietSanPham) {
        repository.save(chiTietSanPham);
    }

    @Override
    public ChitietSanPham findChitietSanPhamById(Integer id) {
        return repository.findChitietSanPhamById(id);
    }

    @Override
    public Page<ChitietSanPham> searchByTen(String ten, Pageable pageable) {
        return repository.searchByTen(ten,pageable);
    }

    @Override
    public Page<ChitietSanPham> searchByTenAndGiaRange(String ten, BigDecimal min, BigDecimal max, Pageable pageable) {
        return repository.searchByTenAndGiaRange(ten, min, max, pageable);
    }

    @Override
    public void saveProductsFromExcel(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            for (Row row : sheet) {
                // Bỏ qua dòng tiêu đề (nếu cần)
                if (row.getRowNum() == 0) {
                    continue;
                }

                ChitietSanPham product = new ChitietSanPham();

                Cell tenCell = row.getCell(0);
                if (tenCell != null && tenCell.getCellType() == CellType.STRING) {
                    product.setTen(tenCell.getStringCellValue());
                }


                Cell mauSacCell = row.getCell(1);
                if (mauSacCell != null && mauSacCell.getCellType() == CellType.STRING) {
                    String tenMauSac= mauSacCell.getStringCellValue();
                    MauSac mauSac = repositoryMauSac.findMauSacByTen(tenMauSac);
                    product.setIdMauSac(mauSac);
                }
                Cell hangCell = row.getCell(2);
                if (hangCell != null && hangCell.getCellType() == CellType.STRING) {
                    String tenHang =  hangCell.getStringCellValue();
                    Hang hang = repositoryHang.findHangByTen(tenHang);
                    product.setIdHang(hang);
                }
                Cell kichCoCell = row.getCell(3);
                if (kichCoCell != null && kichCoCell.getCellType() == CellType.STRING) {
                    String tenKichCo =  kichCoCell.getStringCellValue();
                    KichCo kichCo = repositoryKichCo.findKichCoByTen(tenKichCo);
                    product.setIdKichCo(kichCo);
                }
                Cell chatLieuCell = row.getCell(4);
                if (chatLieuCell != null && chatLieuCell.getCellType() == CellType.STRING) {
                    String tenChatLieu = chatLieuCell.getStringCellValue();
                    ChatLieu chatLieu = repositoryChatLieu.findChatLieuByTen(tenChatLieu);
                    product.setIdChatLieu(chatLieu);
                }
                Cell loaiCell = row.getCell(5);
                if (loaiCell != null && loaiCell.getCellType() == CellType.STRING) {
                    String tenLoai =  loaiCell.getStringCellValue();
                    Loai loai = repositoryLoai.findLoaiByTen(tenLoai);
                    product.setIdLoai(loai);
                }
                Cell soLuongTonCell = row.getCell(6);
                if (soLuongTonCell != null && soLuongTonCell.getCellType() == CellType.NUMERIC) {
                    product.setSoLuongTon((int) soLuongTonCell.getNumericCellValue());
                }

                Cell giaBanCell = row.getCell(7);
                if (giaBanCell != null && giaBanCell.getCellType() == CellType.NUMERIC) {
                    product.setGiaBan(Float.valueOf((float) giaBanCell.getNumericCellValue()));
                }
                Cell trangThaiCell = row.getCell(8);
                if (trangThaiCell != null && trangThaiCell.getCellType() == CellType.NUMERIC) {
                    int idHangValue = (int) trangThaiCell.getNumericCellValue();
                      TrangThai trangThai=trangThaiRepository.findTrangThaiById(idHangValue);
                      product.setIdTrangThai(trangThai);
                }

                Cell ngayNhapCell = row.getCell(9);
                if (ngayNhapCell != null) {
                    if (ngayNhapCell.getCellType() == CellType.STRING) {
                        String ngayNhapValue = ngayNhapCell.getStringCellValue();
                        if (!ngayNhapValue.isEmpty()) {
                            product.setNgayNhap(LocalDate.parse(ngayNhapValue));
                        }
                    } else if (ngayNhapCell.getCellType() == CellType.NUMERIC) {
                        product.setNgayNhap(ngayNhapCell.getLocalDateTimeCellValue().toLocalDate());
                    }
                }

                Cell ngayChinhSuaCell = row.getCell(10);
                if (ngayChinhSuaCell != null) {
                    if (ngayChinhSuaCell.getCellType() == CellType.STRING) {
                        String ngayChinhSuaValue = ngayChinhSuaCell.getStringCellValue();
                        if (!ngayChinhSuaValue.isEmpty()) {
                            product.setNgayChinhSua(LocalDate.parse(ngayChinhSuaValue));
                        }
                    } else if (ngayChinhSuaCell.getCellType() == CellType.NUMERIC) {
                        product.setNgayChinhSua(ngayChinhSuaCell.getLocalDateTimeCellValue().toLocalDate());
                    }
                }

                Cell imageCell = row.getCell(11);
                if (imageCell != null && imageCell.getCellType() == CellType.STRING) {
                    product.setImage(imageCell.getStringCellValue());
                }

                // Lưu đối tượng Product vào cơ sở dữ liệu
                repository.save(product);

            }
        }
    }
}





