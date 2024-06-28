CREATE TABLE Users(
	TenDN	VARCHAR(50) PRIMARY KEY NOT NULL,
	MatKhau		VARCHAR(50) NOT NULL,
	VaiTro		NVARCHAR(100)
);

-- Tạo bảng NHÂN VIÊN
CREATE TABLE NhanVien (
    MaNhanVien VARCHAR(50) PRIMARY KEY,
    TenNhanVien VARCHAR(100) NOT NULL,
	MatKhau NVARCHAR(50) NOT NULL,
    DiaChi VARCHAR(255),
    GioiTinh BIT,
    SDT VARCHAR(20),
    VaiTro BIT NOT NULL,
	TenDN VARCHAR(50) NOT NULL,
    Anh VARCHAR(255) NULL
	FOREIGN KEY (TenDN) REFERENCES Users(TenDN)
);

DROP TABLE NhanVien
-- Tạo bảng KHÁCH HÀNG
CREATE TABLE KhachHang (
    MaKhachHang VARCHAR(50) PRIMARY KEY,
    TenKhachHang VARCHAR(100) NOT NULL,
    DiaChi VARCHAR(255),
    SDT VARCHAR(20),
    LoaiKhachHang VARCHAR(50),
    MaNhanVien VARCHAR(50),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);

-- Tạo bảng SẢN PHẨM
CREATE TABLE SanPham (
    MaSanPham VARCHAR(50) PRIMARY KEY,
    TenSanPham VARCHAR(100) NOT NULL,
    SoLuong INT,
    GiaBan DECIMAL(10, 2),
    LoaiSanPham VARCHAR(50),
    MoTa TEXT,
    Anh VARCHAR(255) NULL
);

-- Tạo bảng KHO HÀNG
CREATE TABLE KhoHang (
    MaSanPham VARCHAR(50),
    TenSanPham VARCHAR(100) NOT NULL,
    Ncc VARCHAR(100),
    SoLuong INT,
    GiaNhap DECIMAL(10, 2),
    GiaBan DECIMAL(10, 2),
    SLTonKho INT,
    NgayNhap DATE,
    Anh VARCHAR(255) NULL,
    PRIMARY KEY (MaSanPham)
);

-- Tạo bảng HÓA ĐƠN
CREATE TABLE HoaDon (
    MaHoaDon VARCHAR(50) PRIMARY KEY ,
    KhachHang VARCHAR(100),
    DiaChiKhach VARCHAR(255),
    SDTKhach VARCHAR(20),
    TongTien DECIMAL(10, 2),
    ThoiGian DATETIME
);

-- Tạo bảng HÓA ĐƠN CHI TIẾT
CREATE TABLE HoaDonChiTiet (
    MaHDCT VARCHAR(50) PRIMARY KEY,
    MaHoaDon VARCHAR(50),
    MaSanPham VARCHAR(50),
    SoLuong INT,
    DonGia DECIMAL(10, 2),
    ThanhTien DECIMAL(10, 2),
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)
);

-- Tạo bảng NHÀ CUNG CẤP
CREATE TABLE NhaCungCap (
    MaNhaCungCap VARCHAR(50) PRIMARY KEY,
    TenNhaCungCap VARCHAR(100) NOT NULL,
    DiaChi VARCHAR(255),
    SDT VARCHAR(20),
    Email VARCHAR(100),
    GhiChu NVARCHAR(255),
    TrangThai BIT
);

-- Tạo bảng GIAO HÀNG
CREATE TABLE GIAOHANG (
    MaGiaoHang INT PRIMARY KEY,
    LoaiNuoc VARCHAR(50),
    MaSP VARCHAR(50),
    DiaChiGiao VARCHAR(100),
    TinhTrangGiaoHang VARCHAR(50),
    TenNguoiGiao VARCHAR(50),
    SDTNguoiGiao VARCHAR(15),
    TenNguoiNhan VARCHAR(50),
    SDTNguoiNhan VARCHAR(15),
    FOREIGN KEY (MaSP) REFERENCES SANPHAM(MaSanPham)
);


INSERT INTO Users (TenDN, MatKhau, VaiTro) VALUES 
('user1', 'password1', 'Admin'),
('user2', 'password2', 'Employee'),
('user3', 'password3', 'Manager');





INSERT INTO NhanVien (MaNhanVien, TenNhanVien, MatKhau, DiaChi, GioiTinh, SDT, VaiTro, TenDN, Anh) 
VALUES 
('NV001', 'Nguyen Van A', 'password1', '123 ABC Street', 1, '0123456789', 1, 'user1', 'anh1.jpg'),
('NV002', 'Tran Thi B', 'password2', '456 DEF Street', 0, '0987654321', 0, 'user2', 'anh2.jpg'), 
('NV003', 'Tran Thi B', 'password3', '456 DEF Street', 0, '0987654321', 0, 'user3', 'anh2.jpg');





INSERT INTO KhachHang (MaKhachHang, TenKhachHang, DiaChi, SDT, LoaiKhachHang, MaNhanVien) VALUES
('KH001', 'Le Van C', '789 Duong 3, Quan 3, TP HCM', '0912345678', 'VIP', 'NV001'),
('KH002', 'Pham Thi D', '101 Duong 4, Quan 4, TP HCM', '0934567890', 'Thuong', 'NV002');


INSERT INTO SanPham (MaSanPham, TenSanPham, SoLuong, GiaBan, LoaiSanPham, MoTa, Anh) VALUES
('SP001', 'San pham 1', 100, 20000, 'Loai 1', 'Mo ta san pham 1', 'sanpham1.jpg'),
('SP002', 'San pham 2', 200, 30000, 'Loai 2', 'Mo ta san pham 2', 'sanpham2.jpg');


INSERT INTO KhoHang (MaSanPham, TenSanPham, Ncc, SoLuong, GiaNhap, GiaBan, SLTonKho, NgayNhap, Anh) VALUES
('SP001', 'San pham 1', 'NCC 1', 100, 15000, 20000, 50, '2024-06-27', 'sanpham1.jpg'),
('SP002', 'San pham 2', 'NCC 2', 200, 25000, 30000, 100, '2024-06-27', 'sanpham2.jpg');


INSERT INTO HoaDon (MaHoaDon, KhachHang, DiaChiKhach, SDTKhach, TongTien, ThoiGian) VALUES
('HD001', 'Le Van C', '789 Duong 3, Quan 3, TP HCM', '0912345678', 400000, '2024-06-27 10:00:00'),
('HD002', 'Pham Thi D', '101 Duong 4, Quan 4, TP HCM', '0934567890', 600000, '2024-06-27 11:00:00');


INSERT INTO HoaDonChiTiet (MaHDCT, MaHoaDon, MaSanPham, SoLuong, DonGia, ThanhTien) VALUES
('HDCT001', 'HD001', 'SP001', 10, 20000, 200000),
('HDCT002', 'HD001', 'SP002', 10, 20000, 200000),
('HDCT003', 'HD002', 'SP001', 15, 20000, 300000),
('HDCT004', 'HD002', 'SP002', 10, 30000, 300000);


INSERT INTO NhaCungCap (MaNhaCungCap, TenNhaCungCap, DiaChi, SDT, Email, GhiChu, TrangThai) VALUES
('NCC001', 'NCC 1', '123 Duong NCC 1, TP HCM', '0901123456', 'ncc1@example.com', 'Ghi chu 1', 1),
('NCC002', 'NCC 2', '456 Duong NCC 2, TP HCM', '0902234567', 'ncc2@example.com', 'Ghi chu 2', 0);


INSERT INTO GiaoHang (MaGiaoHang, LoaiNuoc, MaSP,DiaChiGiao, TinhTrangGiaoHang, TenNguoiGiao, SDTNguoiGiao, TenNguoiNhan, SDTNguoiNhan) VALUES
(1, 'Nuoc loc', 'SP001','789 Duong 3, Quan 3, TP HCM', 'Da giao', 'Nguyen Van E', '0912123456', 'Le Van F', '0913123456'),
(2, 'Nuoc khoang','SP002' ,'101 Duong 4, Quan 4, TP HCM', 'Chua giao', 'Tran Thi G', '0914234567', 'Pham Van H', '0915234567');



SELECT * FROM NhanVien;
SELECT * FROM KhachHang;
SELECT * FROM SanPham;
SELECT * FROM KhoHang;
SELECT * FROM HoaDon;
SELECT * FROM HoaDonChiTiet;
SELECT * FROM NhaCungCap;
SELECT * FROM GiaoHang;
SELECT * FROM Users;