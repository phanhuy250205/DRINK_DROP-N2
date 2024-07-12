-- Thêm dữ liệu vào bảng Users
INSERT INTO Users (TenDN, MatKhau, VaiTro) VALUES 
('user1', 'password1', 'Admin'),
('user2', 'password2', 'Employee'),
('user3', 'password3', 'Manager'),
('user4', 'password4', 'Employee'),
('user5', 'password5', 'Employee');

-- Thêm dữ liệu vào bảng NHÂN VIÊN
INSERT INTO NhanVien (MaNhanVien, TenNhanVien, MatKhau, DiaChi, GioiTinh, SDT, VaiTro, TenDN, Anh) 
VALUES 
('NV001', 'Nguyen Van A', 'password1', '123 ABC Street', 1, '0123456789', 1, 'user1', 'anh1.jpg'),
('NV002', 'Tran Thi B', 'password2', '456 DEF Street', 0, '0987654321', 0, 'user2', 'anh2.jpg'), 
('NV003', 'Tran Thi C', 'password3', '789 GHI Street', 0, '0987654321', 0, 'user3', 'anh3.jpg'),
('NV004', 'Le Van D', 'password4', '101 JKL Street', 1, '0123456789', 0, 'user4', 'anh4.jpg'),
('NV005', 'Pham Thi E', 'password5', '456 MNO Street', 0, '0987654321', 0, 'user5', 'anh5.jpg');

-- Thêm dữ liệu vào bảng KHÁCH HÀNG
INSERT INTO KhachHang (MaKhachHang, TenKhachHang, DiaChi, SDT, LoaiKhachHang, MaNhanVien) VALUES
('KH001', 'Le Van C', '789 Duong 3, Quan 3, TP HCM', '0912345678', 'VIP', 'NV001'),
('KH002', 'Pham Thi D', '101 Duong 4, Quan 4, TP HCM', '0934567890', 'Thuong', 'NV002'),
('KH003', 'Nguyen Van F', '789 Duong 5, Quan 5, TP HCM', '0987654321', 'Thuong', 'NV003'),
('KH004', 'Tran Thi G', '101 Duong 6, Quan 6, TP HCM', '0912345678', 'VIP', 'NV004'),
('KH005', 'Le Van H', '789 Duong 7, Quan 7, TP HCM', '0934567890', 'Thuong', 'NV005');

-- Thêm dữ liệu vào bảng SẢN PHẨM
INSERT INTO SanPham (MaSanPham, TenSanPham, SoLuong, GiaBan, LoaiSanPham, MoTa, Anh, GiaNhap)
VALUES 
('SP001', 'NUMBERONE', 150, 20000, 'Loại 1', 'Mô tả sản phẩm 1', 'anh1.jpg', 15000),
('SP002', '7 Up', 200, 30000, 'Loại 2', 'Mô tả sản phẩm 2', 'anh2.jpg', 25000),
('SP003', 'ReaBull', 150, 25000, 'Loại 1', 'Mô tả sản phẩm 3', 'anh3.jpg', 18000),
('SP004', 'STING', 300, 35000, 'Loại 2', 'Mô tả sản phẩm 4', 'anh4.jpg', 28000),
('SP005', 'Tra Dao', 120, 18000, 'Loại 4', 'Mô tả sản phẩm 5', 'anh5.jpg', 16000);

-- Thêm dữ liệu vào bảng KHO HÀNG
INSERT INTO KhoHang (MaSanPham, TenSanPham, Ncc, SoLuong, GiaNhap, GiaBan, SLTonKho, NgayNhap, Anh) VALUES
('SP001', 'San pham 1', 'NCC 1', 100, 15000, 20000, 50, '2024-06-27', 'sanpham1.jpg'),
('SP002', 'San pham 2', 'NCC 2', 200, 25000, 30000, 100, '2024-06-27', 'sanpham2.jpg'),
('SP003', 'San pham 3', 'NCC 1', 150, 18000, 25000, 80, '2024-06-27', 'sanpham3.jpg'),
('SP004', 'San pham 4', 'NCC 2', 120, 30000, 35000, 60, '2024-06-27', 'sanpham4.jpg'),
('SP005', 'San pham 5', 'NCC 1', 180, 20000, 28000, 90, '2024-06-27', 'sanpham5.jpg');

-- Thêm dữ liệu vào bảng HÓA ĐƠN
INSERT INTO HoaDon (MaHoaDon, KhachHang, DiaChiKhach, SDTKhach, TongTien, ThoiGian, MaNhanVien) VALUES
('HD001', 'Le Van C', '789 Duong 3, Quan 3, TP HCM', '0912345678', 400000, '2024-06-27 10:00:00', 'NV001'),
('HD002', 'Pham Thi D', '101 Duong 4, Quan 4, TP HCM', '0934567890', 600000, '2024-06-27 11:00:00', 'NV002'),
('HD003', 'Nguyen Van F', '789 Duong 5, Quan 5, TP HCM', '0987654321', 450000, '2024-06-28 10:00:00', 'NV003'),
('HD004', 'Tran Thi G', '101 Duong 6, Quan 6, TP HCM', '0912345678', 550000, '2024-06-28 11:00:00', 'NV004'),
('HD005', 'Le Van H', '789 Duong 7, Quan 7, TP HCM', '0934567890', 700000, '2024-06-29 10:00:00', 'NV005');

-- Thêm dữ liệu vào bảng HÓA ĐƠN CHI TIẾT
INSERT INTO HoaDonChiTiet (MaHDCT, MaHoaDon, MaSanPham, SoLuong, DonGia, ThanhTien) VALUES
('HDCT001', 'HD001', 'SP001', 10, 20000, 200000),
('HDCT002', 'HD001', 'SP002', 10, 30000, 300000),
('HDCT003', 'HD002', 'SP001', 15, 20000, 300000),
('HDCT004', 'HD002', 'SP002', 10, 30000, 300000),
('HDCT005', 'HD003', 'SP003', 12, 25000, 300000);

-- Thêm dữ liệu vào bảng NHÀ CUNG CẤP
INSERT INTO NhaCungCap (MaNhaCungCap, TenNhaCungCap, DiaChi, SDT, Email, GhiChu, TrangThai) VALUES
('NCC001', 'NCC 1', '123 Duong NCC 1, TP HCM', '0901123456', 'ncc1@example.com', 'Ghi chu 1', 1),
('NCC002', 'NCC 2', '456 Duong NCC 2, TP HCM', '0902234567', 'ncc2@example.com', 'Ghi chu 2', 0),
('NCC003', 'NCC 3', '789 Duong NCC 3, TP HCM', '0903345678', 'ncc3@example.com', 'Ghi chu 3', 1),
('NCC004', 'NCC 4', '101 Duong NCC 4, TP HCM', '0904456789', 'ncc4@example.com', 'Ghi chu 4', 0),
('NCC005', 'NCC 5', '123 Duong NCC 5, TP HCM', '0905567890', 'ncc5@example.com', 'Ghi chu 5', 1);

-- Thêm dữ liệu vào bảng GIAO HÀNG
INSERT INTO GIAOHANG (MaGiaoHang, LoaiNuoc, MaSP, DiaChiGiao, TinhTrangGiaoHang, TenNguoiGiao, SDTNguoiGiao, TenNguoiNhan, SDTNguoiNhan) VALUES
(1, 'Nuoc loc', 'SP001', '789 Duong 3, Quan 3, TP HCM', 'Da giao', 'Nguyen Van E', '0912123456', 'Le Van F', '0913123456'),
(2, 'Nuoc khoang', 'SP002', '101 Duong 4, Quan 4, TP HCM', 'Chua giao', 'Tran Thi G', '0914234567', 'Pham Van H', '0915234567'),
(3, 'Nuoc ngot', 'SP003', '789 Duong 5, Quan 5, TP HCM', 'Dang giao', 'Nguyen Van I', '0915345678', 'Le Thi K', '0916345678'),
(4, 'Nuoc chanh', 'SP004', '101 Duong 6, Quan 6, TP HCM', 'Da giao', 'Tran Van L', '0916456789', 'Pham Thi M', '0917456789'),
(5, 'Nuoc cam', 'SP005', '789 Duong 7, Quan 7, TP HCM', 'Chua giao', 'Nguyen Van N', '0917567890', 'Le Thi O', '0918567890');

-- Thêm cột MaNhanVien vào bảng HÓA ĐƠN và thiết lập ràng buộc khóa ngoại
ALTER TABLE HoaDon
ADD MaNhanVien VARCHAR(50);

UPDATE HoaDon SET MaNhanVien = 'NV001' WHERE MaHoaDon = 'HD001';
UPDATE HoaDon SET MaNhanVien = 'NV002' WHERE MaHoaDon = 'HD002';
UPDATE HoaDon SET MaNhanVien = 'NV003' WHERE MaHoaDon = 'HD003';
UPDATE HoaDon SET MaNhanVien = 'NV004' WHERE MaHoaDon = 'HD004';
UPDATE HoaDon SET MaNhanVien = 'NV005' WHERE MaHoaDon = 'HD005';

ALTER TABLE HoaDon
ADD CONSTRAINT FK_HoaDon_NhanVien FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien);


DELETE FROM Users;
DELETE FROM NhanVien;
DELETE FROM KhoHang;
DELETE FROM HoaDonChiTiet;
DELETE FROM HoaDon;
DELETE FROM KhachHang;
DELETE FROM GIAOHANG;
DELETE FROM SanPham;
DELETE FROM NhaCungCap;

SELECT  * from Users
SELECT * from NhanVien
SELECT * from HoaDonChiTiet
select * from HoaDon
select * from KhachHang
select * from GIAOHANG
select * from SanPham
SELECT * from KhoHang