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




///


USE [master]
GO
/****** Object:  Database [DRINK_DROP]    Script Date: 31/7/2024 3:17:03 pm ******/
CREATE DATABASE [DRINK_DROP]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DRINK_DROP', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\DRINK_DROP.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'DRINK_DROP_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\DRINK_DROP_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [DRINK_DROP] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DRINK_DROP].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DRINK_DROP] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DRINK_DROP] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DRINK_DROP] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DRINK_DROP] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DRINK_DROP] SET ARITHABORT OFF 
GO
ALTER DATABASE [DRINK_DROP] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DRINK_DROP] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DRINK_DROP] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DRINK_DROP] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DRINK_DROP] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DRINK_DROP] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DRINK_DROP] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DRINK_DROP] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DRINK_DROP] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DRINK_DROP] SET  DISABLE_BROKER 
GO
ALTER DATABASE [DRINK_DROP] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DRINK_DROP] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DRINK_DROP] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DRINK_DROP] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DRINK_DROP] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DRINK_DROP] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DRINK_DROP] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DRINK_DROP] SET RECOVERY FULL 
GO
ALTER DATABASE [DRINK_DROP] SET  MULTI_USER 
GO
ALTER DATABASE [DRINK_DROP] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DRINK_DROP] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DRINK_DROP] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DRINK_DROP] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [DRINK_DROP] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [DRINK_DROP] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'DRINK_DROP', N'ON'
GO
ALTER DATABASE [DRINK_DROP] SET QUERY_STORE = ON
GO
ALTER DATABASE [DRINK_DROP] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [DRINK_DROP]
GO
/****** Object:  Table [dbo].[Banhang]    Script Date: 31/7/2024 3:17:03 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Banhang](
	[MaGiaoHang] [int] NOT NULL,
	[MaSanPham] [varchar](50) NULL,
	[TenSanPham] [varchar](255) NULL,
	[SoLuong] [int] NULL,
	[DonGia] [float] NULL,
	[TongTien] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaGiaoHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 31/7/2024 3:17:03 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHoaDon] [varchar](50) NOT NULL,
	[KhachHang] [varchar](100) NULL,
	[DiaChiKhach] [varchar](255) NULL,
	[SDTKhach] [varchar](20) NULL,
	[TongTien] [decimal](10, 2) NULL,
	[ThoiGian] [datetime] NULL,
	[MaNhanVien] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonChiTiet]    Script Date: 31/7/2024 3:17:03 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonChiTiet](
	[MaHDCT] [varchar](50) NOT NULL,
	[MaHoaDon] [varchar](50) NULL,
	[MaSanPham] [varchar](50) NULL,
	[SoLuong] [int] NULL,
	[DonGia] [decimal](10, 2) NULL,
	[ThanhTien] [decimal](10, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHDCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 31/7/2024 3:17:03 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MaKhachHang] [varchar](50) NOT NULL,
	[TenKhachHang] [varchar](100) NOT NULL,
	[DiaChi] [varchar](255) NULL,
	[SDT] [varchar](20) NULL,
	[LoaiKhachHang] [varchar](50) NULL,
	[MaNhanVien] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 31/7/2024 3:17:03 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[MaNhaCungCap] [varchar](50) NOT NULL,
	[TenNhaCungCap] [varchar](100) NOT NULL,
	[DiaChi] [varchar](255) NULL,
	[SDT] [varchar](20) NULL,
	[Email] [varchar](100) NULL,
	[GhiChu] [nvarchar](255) NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNhaCungCap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 31/7/2024 3:17:03 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNhanVien] [varchar](50) NOT NULL,
	[TenNhanVien] [varchar](100) NOT NULL,
	[MatKhau] [nvarchar](50) NOT NULL,
	[DiaChi] [varchar](255) NULL,
	[GioiTinh] [bit] NULL,
	[SDT] [varchar](20) NULL,
	[VaiTro] [bit] NOT NULL,
	[TenDN] [varchar](50) NOT NULL,
	[Anh] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhapKho]    Script Date: 31/7/2024 3:17:03 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhapKho](
	[MaPhieuNhap] [varchar](50) NOT NULL,
	[MaNguoiNhap] [varchar](50) NOT NULL,
	[MaNhaCungCap] [varchar](50) NOT NULL,
	[NgayNhap] [date] NOT NULL,
	[TenSanPham] [nvarchar](50) NOT NULL,
	[SoLuong] [int] NOT NULL,
	[TienNhap] [float] NOT NULL,
	[LoaiSanPham] [nvarchar](50) NOT NULL,
	[MaSanPham] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaPhieuNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 31/7/2024 3:17:03 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[MaSanPham] [varchar](50) NOT NULL,
	[TenSanPham] [varchar](100) NOT NULL,
	[SoLuong] [int] NULL,
	[GiaBan] [decimal](10, 2) NULL,
	[LoaiSanPham] [varchar](50) NULL,
	[MoTa] [text] NULL,
	[Anh] [varchar](255) NULL,
	[GiaNhap] [float] NULL,
	[TongSanPham] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 31/7/2024 3:17:03 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[TenDN] [varchar](50) NOT NULL,
	[MatKhau] [varchar](50) NOT NULL,
	[VaiTro] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[TenDN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Banhang] ([MaGiaoHang], [MaSanPham], [TenSanPham], [SoLuong], [DonGia], [TongTien]) VALUES (1, N'SP001', N'7 Up', 5, 150000, 750000)
GO
INSERT [dbo].[Banhang] ([MaGiaoHang], [MaSanPham], [TenSanPham], [SoLuong], [DonGia], [TongTien]) VALUES (2, N'SP002', N'Numberone', 3, 200000, 600000)
GO
INSERT [dbo].[Banhang] ([MaGiaoHang], [MaSanPham], [TenSanPham], [SoLuong], [DonGia], [TongTien]) VALUES (3, N'SP003', N'Sting', 2, 250000, 500000)
GO
INSERT [dbo].[HoaDon] ([MaHoaDon], [KhachHang], [DiaChiKhach], [SDTKhach], [TongTien], [ThoiGian], [MaNhanVien]) VALUES (N'HD001', N'Le Van C', N'789 Duong 3, Quan 3, TP HCM', N'0912345678', CAST(400000.00 AS Decimal(10, 2)), CAST(N'2024-06-27T10:00:00.000' AS DateTime), N'NV001')
GO
INSERT [dbo].[HoaDon] ([MaHoaDon], [KhachHang], [DiaChiKhach], [SDTKhach], [TongTien], [ThoiGian], [MaNhanVien]) VALUES (N'HD002', N'Pham Thi D', N'101 Duong 4, Quan 4, TP HCM', N'0934567890', CAST(600000.00 AS Decimal(10, 2)), CAST(N'2024-06-27T11:00:00.000' AS DateTime), N'NV002')
GO
INSERT [dbo].[HoaDon] ([MaHoaDon], [KhachHang], [DiaChiKhach], [SDTKhach], [TongTien], [ThoiGian], [MaNhanVien]) VALUES (N'HD003', N'Nguyen Van F', N'789 Duong 5, Quan 5, TP HCM', N'0987654321', CAST(450000.00 AS Decimal(10, 2)), CAST(N'2024-06-28T10:00:00.000' AS DateTime), N'NV003')
GO
INSERT [dbo].[HoaDon] ([MaHoaDon], [KhachHang], [DiaChiKhach], [SDTKhach], [TongTien], [ThoiGian], [MaNhanVien]) VALUES (N'HD004', N'Tran Thi G', N'101 Duong 6, Quan 6, TP HCM', N'0912345678', CAST(550000.00 AS Decimal(10, 2)), CAST(N'2024-06-28T11:00:00.000' AS DateTime), N'NV004')
GO
INSERT [dbo].[HoaDon] ([MaHoaDon], [KhachHang], [DiaChiKhach], [SDTKhach], [TongTien], [ThoiGian], [MaNhanVien]) VALUES (N'HD005', N'Le Van H', N'789 Duong 7, Quan 7, TP HCM', N'0934567890', CAST(700000.00 AS Decimal(10, 2)), CAST(N'2024-06-29T10:00:00.000' AS DateTime), N'NV005')
GO
INSERT [dbo].[HoaDonChiTiet] ([MaHDCT], [MaHoaDon], [MaSanPham], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'HDCT001', N'HD001', N'SP001', 10, CAST(20000.00 AS Decimal(10, 2)), CAST(200000.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[HoaDonChiTiet] ([MaHDCT], [MaHoaDon], [MaSanPham], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'HDCT002', N'HD001', N'SP002', 10, CAST(30000.00 AS Decimal(10, 2)), CAST(300000.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[HoaDonChiTiet] ([MaHDCT], [MaHoaDon], [MaSanPham], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'HDCT003', N'HD002', N'SP001', 15, CAST(20000.00 AS Decimal(10, 2)), CAST(300000.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[HoaDonChiTiet] ([MaHDCT], [MaHoaDon], [MaSanPham], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'HDCT004', N'HD002', N'SP002', 10, CAST(30000.00 AS Decimal(10, 2)), CAST(300000.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[HoaDonChiTiet] ([MaHDCT], [MaHoaDon], [MaSanPham], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'HDCT005', N'HD003', N'SP003', 12, CAST(25000.00 AS Decimal(10, 2)), CAST(300000.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [DiaChi], [SDT], [LoaiKhachHang], [MaNhanVien]) VALUES (N'KH001', N'Le Van C', N'789 Duong 3, Quan 3, TP HCM', N'0912345678', N'VIP', N'NV001')
GO
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [DiaChi], [SDT], [LoaiKhachHang], [MaNhanVien]) VALUES (N'KH002', N'Pham Thi D', N'101 Duong 4, Quan 4, TP HCM', N'0934567890', N'Thuong', N'NV002')
GO
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [DiaChi], [SDT], [LoaiKhachHang], [MaNhanVien]) VALUES (N'KH003', N'Nguyen Van F', N'789 Duong 5, Quan 5, TP HCM', N'0987654321', N'Thuong', N'NV003')
GO
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [DiaChi], [SDT], [LoaiKhachHang], [MaNhanVien]) VALUES (N'KH004', N'Tran Thi G', N'101 Duong 6, Quan 6, TP HCM', N'0912345678', N'VIP', N'NV004')
GO
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [DiaChi], [SDT], [LoaiKhachHang], [MaNhanVien]) VALUES (N'KH005', N'Le Van H', N'789 Duong 7, Quan 7, TP HCM', N'0934567890', N'Thuong', N'NV005')
GO
INSERT [dbo].[NhaCungCap] ([MaNhaCungCap], [TenNhaCungCap], [DiaChi], [SDT], [Email], [GhiChu], [TrangThai]) VALUES (N'NCC001', N'NCC 1', N'123 Duong NCC 1, TP HCM', N'0901123456', N'ncc1@example.com', N'Ghi chu 1', 1)
GO
INSERT [dbo].[NhaCungCap] ([MaNhaCungCap], [TenNhaCungCap], [DiaChi], [SDT], [Email], [GhiChu], [TrangThai]) VALUES (N'NCC002', N'NCC 2', N'456 Duong NCC 2, TP HCM', N'0902234567', N'ncc2@example.com', N'Ghi chu 2', 0)
GO
INSERT [dbo].[NhaCungCap] ([MaNhaCungCap], [TenNhaCungCap], [DiaChi], [SDT], [Email], [GhiChu], [TrangThai]) VALUES (N'NCC003', N'NCC 3', N'789 Duong NCC 3, TP HCM', N'0903345678', N'ncc3@example.com', N'Ghi chu 3', 1)
GO
INSERT [dbo].[NhaCungCap] ([MaNhaCungCap], [TenNhaCungCap], [DiaChi], [SDT], [Email], [GhiChu], [TrangThai]) VALUES (N'NCC004', N'NCC 4', N'101 Duong NCC 4, TP HCM', N'0904456789', N'ncc4@example.com', N'Ghi chu 4', 0)
GO
INSERT [dbo].[NhaCungCap] ([MaNhaCungCap], [TenNhaCungCap], [DiaChi], [SDT], [Email], [GhiChu], [TrangThai]) VALUES (N'NCC005', N'NCC 5', N'123 Duong NCC 5, TP HCM', N'0905567890', N'ncc5@example.com', N'Ghi chu 5', 1)
GO
INSERT [dbo].[NhanVien] ([MaNhanVien], [TenNhanVien], [MatKhau], [DiaChi], [GioiTinh], [SDT], [VaiTro], [TenDN], [Anh]) VALUES (N'NV001', N'Nguyen Van A', N'password1', N'123 ABC Street', 1, N'0123456789', 1, N'user1', N'anh1.jpg')
GO
INSERT [dbo].[NhanVien] ([MaNhanVien], [TenNhanVien], [MatKhau], [DiaChi], [GioiTinh], [SDT], [VaiTro], [TenDN], [Anh]) VALUES (N'NV002', N'Tran Thi B', N'password2', N'456 DEF Street', 0, N'0987654321', 0, N'user2', N'anh2.jpg')
GO
INSERT [dbo].[NhanVien] ([MaNhanVien], [TenNhanVien], [MatKhau], [DiaChi], [GioiTinh], [SDT], [VaiTro], [TenDN], [Anh]) VALUES (N'NV003', N'Tran Thi C', N'password3', N'789 GHI Street', 0, N'0987654321', 0, N'user3', N'anh3.jpg')
GO
INSERT [dbo].[NhanVien] ([MaNhanVien], [TenNhanVien], [MatKhau], [DiaChi], [GioiTinh], [SDT], [VaiTro], [TenDN], [Anh]) VALUES (N'NV004', N'Le Van D', N'password4', N'101 JKL Street', 1, N'0123456789', 0, N'user4', N'anh4.jpg')
GO
INSERT [dbo].[NhanVien] ([MaNhanVien], [TenNhanVien], [MatKhau], [DiaChi], [GioiTinh], [SDT], [VaiTro], [TenDN], [Anh]) VALUES (N'NV005', N'Pham Thi E', N'password5', N'456 MNO Street', 0, N'0987654321', 0, N'user5', N'anh5.jpg')
GO
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham], [SoLuong], [GiaBan], [LoaiSanPham], [MoTa], [Anh], [GiaNhap], [TongSanPham]) VALUES (N'SP001', N'NUMBERONE', 150, CAST(20000.00 AS Decimal(10, 2)), N'Lo?i 1', N'Mô t? s?n ph?m 1', N'anh1.jpg', 15000, 0)
GO
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham], [SoLuong], [GiaBan], [LoaiSanPham], [MoTa], [Anh], [GiaNhap], [TongSanPham]) VALUES (N'SP002', N'7 Up', 200, CAST(30000.00 AS Decimal(10, 2)), N'Lo?i 2', N'Mô t? s?n ph?m 2', N'anh2.jpg', 25000, 0)
GO
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham], [SoLuong], [GiaBan], [LoaiSanPham], [MoTa], [Anh], [GiaNhap], [TongSanPham]) VALUES (N'SP003', N'ReaBull', 150, CAST(25000.00 AS Decimal(10, 2)), N'Lo?i 1', N'Mô t? s?n ph?m 3', N'anh3.jpg', 18000, 0)
GO
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham], [SoLuong], [GiaBan], [LoaiSanPham], [MoTa], [Anh], [GiaNhap], [TongSanPham]) VALUES (N'SP004', N'STING', 300, CAST(35000.00 AS Decimal(10, 2)), N'Lo?i 2', N'Mô t? s?n ph?m 4', N'anh4.jpg', 28000, 0)
GO
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham], [SoLuong], [GiaBan], [LoaiSanPham], [MoTa], [Anh], [GiaNhap], [TongSanPham]) VALUES (N'SP005', N'Tra Dao', 120, CAST(18000.00 AS Decimal(10, 2)), N'Lo?i 4', N'Mô t? s?n ph?m 5', N'anh5.jpg', 16000, 0)
GO
INSERT [dbo].[Users] ([TenDN], [MatKhau], [VaiTro]) VALUES (N'user1', N'password1', N'Admin')
GO
INSERT [dbo].[Users] ([TenDN], [MatKhau], [VaiTro]) VALUES (N'user2', N'password2', N'Employee')
GO
INSERT [dbo].[Users] ([TenDN], [MatKhau], [VaiTro]) VALUES (N'user3', N'password3', N'Manager')
GO
INSERT [dbo].[Users] ([TenDN], [MatKhau], [VaiTro]) VALUES (N'user4', N'password4', N'Employee')
GO
INSERT [dbo].[Users] ([TenDN], [MatKhau], [VaiTro]) VALUES (N'user5', N'password5', N'Employee')
GO
ALTER TABLE [dbo].[SanPham] ADD  DEFAULT ((0)) FOR [TongSanPham]
GO
ALTER TABLE [dbo].[Banhang]  WITH CHECK ADD FOREIGN KEY([MaSanPham])
REFERENCES [dbo].[SanPham] ([MaSanPham])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_NhanVien] FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[NhanVien] ([MaNhanVien])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_NhanVien]
GO
ALTER TABLE [dbo].[HoaDonChiTiet]  WITH CHECK ADD FOREIGN KEY([MaHoaDon])
REFERENCES [dbo].[HoaDon] ([MaHoaDon])
GO
ALTER TABLE [dbo].[HoaDonChiTiet]  WITH CHECK ADD FOREIGN KEY([MaSanPham])
REFERENCES [dbo].[SanPham] ([MaSanPham])
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[NhanVien] ([MaNhanVien])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([TenDN])
REFERENCES [dbo].[Users] ([TenDN])
GO
ALTER TABLE [dbo].[NhapKho]  WITH CHECK ADD FOREIGN KEY([MaNguoiNhap])
REFERENCES [dbo].[NhanVien] ([MaNhanVien])
GO
ALTER TABLE [dbo].[NhapKho]  WITH CHECK ADD FOREIGN KEY([MaNhaCungCap])
REFERENCES [dbo].[NhaCungCap] ([MaNhaCungCap])
GO
ALTER TABLE [dbo].[NhapKho]  WITH CHECK ADD FOREIGN KEY([MaSanPham])
REFERENCES [dbo].[SanPham] ([MaSanPham])
GO
/****** Object:  StoredProcedure [dbo].[sp_GetThongKe]    Script Date: 31/7/2024 3:17:04 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_GetThongKe]
   @dateFrom DATE,
   @dateTo DATE
AS
BEGIN
    SELECT 
        hd.ThoiGian AS ThoiGianLap,
        COUNT(hd.MaHoaDon) AS TongSoSanPham,
        SUM(ct.ThanhTien) AS TongTien,
        nv.MaNhanVien,
        nv.TenNhanVien AS NhanVienLapHoadon
    FROM 
        HoaDon hd
        JOIN HoaDonChiTiet ct ON hd.MaHoaDon = ct.MaHoaDon
        JOIN NhanVien nv ON hd.MaNhanVien = nv.MaNhanVien
    WHERE 
        hd.ThoiGian >= @dateFrom AND hd.ThoiGian <= @dateTo
    GROUP BY 
        hd.ThoiGian,
        nv.MaNhanVien,
        nv.TenNhanVien
    ORDER BY 
        hd.ThoiGian;


--   @dateFrom DATE
--AS
--BEGIN
--    SELECT 
--        hd.ThoiGian AS Thoigianlap,
--        COUNT(hd.MaHoaDon) AS TongSoSanPham,
--        SUM(ct.ThanhTien) AS Tongtien,
--        nv.MaNhanVien,
--        nv.TenNhanVien AS NhanVienLapHoadon
--    FROM 
--        HoaDon hd
--        JOIN HoaDonChiTiet ct ON hd.MaHoaDon = ct.MaHoaDon
--        JOIN NhanVien nv ON hd.MaNhanVien = nv.MaNhanVien
--    WHERE 
--        hd.ThoiGian >= @dateFrom
--    GROUP BY 
--        hd.ThoiGian,
--        nv.MaNhanVien,
--        nv.TenNhanVien
--    ORDER BY 
--        hd.ThoiGian;
END;

--Exec [dbo].[sp_GetThongKe]
GO
/****** Object:  StoredProcedure [dbo].[sp_GetThongKeByNhanVien]    Script Date: 31/7/2024 3:17:04 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_GetThongKeByNhanVien]
@MaNhanVien VARCHAR(50)
AS
BEGIN
    SELECT 
        hd.ThoiGian AS ThoiGian, 
        hd.MaHoaDon AS SoHoaDon,
        COUNT(hd.MaHoaDon) AS TongHD,
        SUM(ct.ThanhTien) AS TongTienBanRa,
        SUM(ct.SoLuong * sp.[GiaNhap]) AS TongTienThuLai,
        nv.MaNhanVien AS MaNhanVien, 
        nv.TenNhanVien AS NhanVienLapHoaDon,
        sp.TenSanPham AS SanPham,
        sp.[GiaNhap] AS GiaNhap,
        sp.GiaBan AS GiaBan,
        ct.SoLuong AS SoLuong
    FROM HoaDon hd
    JOIN HoaDonChiTiet ct ON hd.MaHoaDon = ct.MaHoaDon
    JOIN NhanVien nv ON hd.MaNhanVien = nv.MaNhanVien
    JOIN SanPham sp ON ct.MaSanPham = sp.MaSanPham
    WHERE nv.MaNhanVien = @MaNhanVien
    GROUP BY 
        hd.ThoiGian, 
        hd.MaHoaDon, 
        nv.MaNhanVien, 
        nv.TenNhanVien,
        sp.TenSanPham,
        sp.[GiaNhap],
        sp.GiaBan,
        ct.SoLuong
--    @TenNhanVien NVARCHAR(50)
--AS
--BEGIN
--    SELECT 
--        hd.ThoiGian AS ThoiGian, 
--        hd.MaHoaDon AS SoHoaDon,
--        COUNT(hd.MaHoaDon) AS TongHD,
--        SUM(ct.ThanhTien) AS TongTienBanRa,
--        SUM(ct.SoLuong * sp.[GiaNhap]) AS TongTienThuLai,
--        nv.MaNhanVien AS MaNhanVien, 
--        nv.TenNhanVien AS NhanVienLapHoaDon,
--        sp.TenSanPham AS SanPham,
--        sp.[GiaNhap] AS GiaNhap,
--        sp.GiaBan AS GiaBan,
--        ct.SoLuong AS SoLuong
--    FROM HoaDon hd
--    JOIN HoaDonChiTiet ct ON hd.MaHoaDon = ct.MaHoaDon
--    JOIN NhanVien nv ON hd.MaNhanVien = nv.MaNhanVien
--    JOIN SanPham sp ON ct.MaSanPham = sp.MaSanPham
--    WHERE nv.TenNhanVien = @TenNhanVien
--    GROUP BY 
--        hd.ThoiGian, 
--        hd.MaHoaDon, 
--        nv.MaNhanVien, 
--        nv.TenNhanVien,
--        sp.TenSanPham,
--        sp.[GiaNhap],
--        sp.GiaBan,
--        ct.SoLuong
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_GetThongKeByThoiGian]    Script Date: 31/7/2024 3:17:04 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_GetThongKeByThoiGian]
    @TenSanPham VARCHAR(100)
AS
BEGIN
     SELECT 
        sp.MaSanPham As MaSanPham,
        sp.TenSanPham as TenSanPham,
        sp.LoaiSanPham As LoaiSanPham,
        sp.SoLuong AS TongSanPham,
        SUM(ct.SoLuong) AS SoLuongBan,
        sp.SoLuong - SUM(ct.SoLuong) AS SoLuongCon
    FROM 
        SanPham sp
        LEFT JOIN HoaDonChiTiet ct ON sp.MaSanPham = ct.MaSanPham
    WHERE 
        sp.TenSanPham = @TenSanPham
    GROUP BY 
        sp.MaSanPham,
        sp.TenSanPham,
        sp.LoaiSanPham,
        sp.SoLuong
    ORDER BY 
        sp.TenSanPham;
END;
GO
USE [master]
GO
ALTER DATABASE [DRINK_DROP] SET  READ_WRITE 
GO
