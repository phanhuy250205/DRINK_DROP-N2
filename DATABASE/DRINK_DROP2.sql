USE [master]
GO
/****** Object:  Database [DRINK_DROP]    Script Date: 12/7/2024 1:11:47 pm ******/
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
/****** Object:  Table [dbo].[GIAOHANG]    Script Date: 12/7/2024 1:11:47 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GIAOHANG](
	[MaGiaoHang] [int] NOT NULL,
	[LoaiNuoc] [varchar](50) NULL,
	[MaSP] [varchar](50) NULL,
	[DiaChiGiao] [varchar](100) NULL,
	[TenNguoiGiao] [varchar](50) NULL,
	[SDTNguoiGiao] [varchar](15) NULL,
	[TenNguoiNhan] [varchar](50) NULL,
	[SDTNguoiNhan] [varchar](15) NULL,
	[TinhTrangGiaoHang] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaGiaoHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 12/7/2024 1:11:47 pm ******/
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
/****** Object:  Table [dbo].[HoaDonChiTiet]    Script Date: 12/7/2024 1:11:47 pm ******/
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
/****** Object:  Table [dbo].[KhachHang]    Script Date: 12/7/2024 1:11:47 pm ******/
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
/****** Object:  Table [dbo].[KhoHang]    Script Date: 12/7/2024 1:11:47 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhoHang](
	[MaSanPham] [varchar](50) NOT NULL,
	[TenSanPham] [varchar](100) NOT NULL,
	[Ncc] [varchar](100) NULL,
	[SoLuong] [int] NULL,
	[GiaNhap] [decimal](10, 2) NULL,
	[GiaBan] [decimal](10, 2) NULL,
	[SLTonKho] [int] NULL,
	[NgayNhap] [date] NULL,
	[Anh] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 12/7/2024 1:11:47 pm ******/
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
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/7/2024 1:11:47 pm ******/
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
/****** Object:  Table [dbo].[SanPham]    Script Date: 12/7/2024 1:11:47 pm ******/
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
/****** Object:  Table [dbo].[Users]    Script Date: 12/7/2024 1:11:47 pm ******/
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
INSERT [dbo].[GIAOHANG] ([MaGiaoHang], [LoaiNuoc], [MaSP], [DiaChiGiao], [TenNguoiGiao], [SDTNguoiGiao], [TenNguoiNhan], [SDTNguoiNhan], [TinhTrangGiaoHang]) VALUES (1, N'Nuoc loc', N'SP001', N'789 Duong 3, Quan 3, TP HCM', N'Nguyen Van E', N'0912123456', N'Le Van F', N'0913123456', N'Nguoi Nhan tu choi')
GO
INSERT [dbo].[GIAOHANG] ([MaGiaoHang], [LoaiNuoc], [MaSP], [DiaChiGiao], [TenNguoiGiao], [SDTNguoiGiao], [TenNguoiNhan], [SDTNguoiNhan], [TinhTrangGiaoHang]) VALUES (2, N'Nuoc khoang', N'SP002', N'101 Duong 4, Quan 4, TP HCM', N'Tran Thi G', N'0914234567', N'Pham Van H', N'0915234567', N'Thanh Cong')
GO
INSERT [dbo].[HoaDon] ([MaHoaDon], [KhachHang], [DiaChiKhach], [SDTKhach], [TongTien], [ThoiGian], [MaNhanVien]) VALUES (N'HD001', N'Le Van C', N'789 Duong 3, Quan 3, TP HCM', N'0912345678', CAST(400000.00 AS Decimal(10, 2)), CAST(N'2024-06-27T10:00:00.000' AS DateTime), N'NV001')
GO
INSERT [dbo].[HoaDon] ([MaHoaDon], [KhachHang], [DiaChiKhach], [SDTKhach], [TongTien], [ThoiGian], [MaNhanVien]) VALUES (N'HD002', N'Pham Thi D', N'101 Duong 4, Quan 4, TP HCM', N'0934567890', CAST(600000.00 AS Decimal(10, 2)), CAST(N'2024-06-27T11:00:00.000' AS DateTime), N'NV002')
GO
INSERT [dbo].[HoaDon] ([MaHoaDon], [KhachHang], [DiaChiKhach], [SDTKhach], [TongTien], [ThoiGian], [MaNhanVien]) VALUES (N'HD003', N'Nguyen Van I', N'789 Duong 5, Quan 5, TP HCM', N'0945678912', CAST(800000.00 AS Decimal(10, 2)), CAST(N'2024-06-27T12:00:00.000' AS DateTime), N'NV003')
GO
INSERT [dbo].[HoaDon] ([MaHoaDon], [KhachHang], [DiaChiKhach], [SDTKhach], [TongTien], [ThoiGian], [MaNhanVien]) VALUES (N'HD004', N'Le Thi J', N'101 Duong 6, Quan 6, TP HCM', N'0956789123', CAST(900000.00 AS Decimal(10, 2)), CAST(N'2024-06-27T13:00:00.000' AS DateTime), NULL)
GO
INSERT [dbo].[HoaDon] ([MaHoaDon], [KhachHang], [DiaChiKhach], [SDTKhach], [TongTien], [ThoiGian], [MaNhanVien]) VALUES (N'HD005', N'Pham Van K', N'789 Duong 7, Quan 7, TP HCM', N'0967891234', CAST(1000000.00 AS Decimal(10, 2)), CAST(N'2024-06-27T14:00:00.000' AS DateTime), NULL)
GO
INSERT [dbo].[HoaDonChiTiet] ([MaHDCT], [MaHoaDon], [MaSanPham], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'HDCT001', N'HD001', N'SP001', 10, CAST(20000.00 AS Decimal(10, 2)), CAST(200000.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[HoaDonChiTiet] ([MaHDCT], [MaHoaDon], [MaSanPham], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'HDCT002', N'HD001', N'SP002', 10, CAST(20000.00 AS Decimal(10, 2)), CAST(200000.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[HoaDonChiTiet] ([MaHDCT], [MaHoaDon], [MaSanPham], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'HDCT003', N'HD002', N'SP001', 15, CAST(20000.00 AS Decimal(10, 2)), CAST(300000.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[HoaDonChiTiet] ([MaHDCT], [MaHoaDon], [MaSanPham], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'HDCT004', N'HD002', N'SP002', 10, CAST(30000.00 AS Decimal(10, 2)), CAST(300000.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[HoaDonChiTiet] ([MaHDCT], [MaHoaDon], [MaSanPham], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'HDCT005', N'HD003', N'SP003', 20, CAST(25000.00 AS Decimal(10, 2)), CAST(500000.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[HoaDonChiTiet] ([MaHDCT], [MaHoaDon], [MaSanPham], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'HDCT006', N'HD004', N'SP004', 25, CAST(35000.00 AS Decimal(10, 2)), CAST(875000.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[HoaDonChiTiet] ([MaHDCT], [MaHoaDon], [MaSanPham], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'HDCT007', N'HD005', N'SP005', 30, CAST(40000.00 AS Decimal(10, 2)), CAST(1200000.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [DiaChi], [SDT], [LoaiKhachHang], [MaNhanVien]) VALUES (N'', N'', N'', N'', N'khách hàng m?i', NULL)
GO
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [DiaChi], [SDT], [LoaiKhachHang], [MaNhanVien]) VALUES (N'KH001', N'Le Van C', N'789 Duong 3, Quan 3, TP HCM', N'0912345678', N'VIP', N'NV001')
GO
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [DiaChi], [SDT], [LoaiKhachHang], [MaNhanVien]) VALUES (N'KH002', N'Pham Thi D', N'101 Duong 4, Quan 4, TP HCM', N'0934567890', N'Thuong', N'NV002')
GO
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [DiaChi], [SDT], [LoaiKhachHang], [MaNhanVien]) VALUES (N'KH003', N'Nguyen Thi P', N'789 Duong 11, Quan 11, TP HCM', N'0913456789', N'VIP', N'NV004')
GO
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [DiaChi], [SDT], [LoaiKhachHang], [MaNhanVien]) VALUES (N'KH004', N'Tran Van Q', N'101 Duong 12, Quan 12, TP HCM', N'0924567890', N'Thuong', N'NV005')
GO
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [DiaChi], [SDT], [LoaiKhachHang], [MaNhanVien]) VALUES (N'KH005', N'Le Thi R', N'123 Duong 13, Quan 13, TP HCM', N'0935678901', N'VIP', N'NV006')
GO
INSERT [dbo].[KhoHang] ([MaSanPham], [TenSanPham], [Ncc], [SoLuong], [GiaNhap], [GiaBan], [SLTonKho], [NgayNhap], [Anh]) VALUES (N'SP001', N'San pham 1', N'NCC 1', 100, CAST(15000.00 AS Decimal(10, 2)), CAST(20000.00 AS Decimal(10, 2)), 50, CAST(N'2024-06-27' AS Date), N'sanpham1.jpg')
GO
INSERT [dbo].[KhoHang] ([MaSanPham], [TenSanPham], [Ncc], [SoLuong], [GiaNhap], [GiaBan], [SLTonKho], [NgayNhap], [Anh]) VALUES (N'SP002', N'San pham 2', N'NCC 2', 200, CAST(25000.00 AS Decimal(10, 2)), CAST(30000.00 AS Decimal(10, 2)), 100, CAST(N'2024-06-27' AS Date), N'sanpham2.jpg')
GO
INSERT [dbo].[KhoHang] ([MaSanPham], [TenSanPham], [Ncc], [SoLuong], [GiaNhap], [GiaBan], [SLTonKho], [NgayNhap], [Anh]) VALUES (N'SP003', N'San pham 3', N'NCC 3', 150, CAST(20000.00 AS Decimal(10, 2)), CAST(25000.00 AS Decimal(10, 2)), 75, CAST(N'2024-06-27' AS Date), N'sanpham3.jpg')
GO
INSERT [dbo].[KhoHang] ([MaSanPham], [TenSanPham], [Ncc], [SoLuong], [GiaNhap], [GiaBan], [SLTonKho], [NgayNhap], [Anh]) VALUES (N'SP004', N'San pham 4', N'NCC 4', 250, CAST(30000.00 AS Decimal(10, 2)), CAST(35000.00 AS Decimal(10, 2)), 125, CAST(N'2024-06-27' AS Date), N'sanpham4.jpg')
GO
INSERT [dbo].[KhoHang] ([MaSanPham], [TenSanPham], [Ncc], [SoLuong], [GiaNhap], [GiaBan], [SLTonKho], [NgayNhap], [Anh]) VALUES (N'SP005', N'San pham 5', N'NCC 5', 300, CAST(35000.00 AS Decimal(10, 2)), CAST(40000.00 AS Decimal(10, 2)), 150, CAST(N'2024-06-27' AS Date), N'sanpham5.jpg')
GO
INSERT [dbo].[NhaCungCap] ([MaNhaCungCap], [TenNhaCungCap], [DiaChi], [SDT], [Email], [GhiChu], [TrangThai]) VALUES (N'NCC001', N'NCC 1', N'123 Duong NCC 1, TP HCM', N'0901123456', N'ncc1@example.com', N'Ghi chu 1', 1)
GO
INSERT [dbo].[NhaCungCap] ([MaNhaCungCap], [TenNhaCungCap], [DiaChi], [SDT], [Email], [GhiChu], [TrangThai]) VALUES (N'NCC002', N'NCC 2', N'456 Duong NCC 2, TP HCM', N'0902234567', N'ncc2@example.com', N'Ghi chu 2', 0)
GO
INSERT [dbo].[NhanVien] ([MaNhanVien], [TenNhanVien], [MatKhau], [DiaChi], [GioiTinh], [SDT], [VaiTro], [TenDN], [Anh]) VALUES (N'NV001', N'Nguyen Van A', N'password1', N'123 ABC Street', 1, N'0123456789', 1, N'user1', N'anh1.jpg')
GO
INSERT [dbo].[NhanVien] ([MaNhanVien], [TenNhanVien], [MatKhau], [DiaChi], [GioiTinh], [SDT], [VaiTro], [TenDN], [Anh]) VALUES (N'NV002', N'Tran Thi B', N'password2', N'456 DEF Street', 0, N'0987654321', 0, N'user2', N'anh2.jpg')
GO
INSERT [dbo].[NhanVien] ([MaNhanVien], [TenNhanVien], [MatKhau], [DiaChi], [GioiTinh], [SDT], [VaiTro], [TenDN], [Anh]) VALUES (N'NV003', N'Tran Thi B', N'password3', N'456 DEF Street', 0, N'0987654321', 0, N'user3', N'anh2.jpg')
GO
INSERT [dbo].[NhanVien] ([MaNhanVien], [TenNhanVien], [MatKhau], [DiaChi], [GioiTinh], [SDT], [VaiTro], [TenDN], [Anh]) VALUES (N'NV004', N'Le Van D', N'password4', N'789 Duong 8, Quan 8, TP HCM', 1, N'0978912345', 1, N'user4', N'anh4.jpg')
GO
INSERT [dbo].[NhanVien] ([MaNhanVien], [TenNhanVien], [MatKhau], [DiaChi], [GioiTinh], [SDT], [VaiTro], [TenDN], [Anh]) VALUES (N'NV005', N'Nguyen Van E', N'password5', N'101 Duong 9, Quan 9, TP HCM', 1, N'0989123456', 1, N'user5', N'anh5.jpg')
GO
INSERT [dbo].[NhanVien] ([MaNhanVien], [TenNhanVien], [MatKhau], [DiaChi], [GioiTinh], [SDT], [VaiTro], [TenDN], [Anh]) VALUES (N'NV006', N'Pham Van F', N'password6', N'123 Duong 10, Quan 10, TP HCM', 1, N'0991234567', 1, N'user6', N'anh6.jpg')
GO
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham], [SoLuong], [GiaBan], [LoaiSanPham], [MoTa], [Anh], [GiaNhap], [TongSanPham]) VALUES (N'SP001', N'San pham 1', 100, CAST(20000.00 AS Decimal(10, 2)), N'Loai 1', N'Mo ta san pham 1', N'sanpham1.jpg', 2000, 0)
GO
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham], [SoLuong], [GiaBan], [LoaiSanPham], [MoTa], [Anh], [GiaNhap], [TongSanPham]) VALUES (N'SP002', N'San pham 2', 200, CAST(30000.00 AS Decimal(10, 2)), N'Loai 2', N'Mo ta san pham 2', N'sanpham2.jpg', 30000, 0)
GO
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham], [SoLuong], [GiaBan], [LoaiSanPham], [MoTa], [Anh], [GiaNhap], [TongSanPham]) VALUES (N'SP003', N'San pham 3', 150, CAST(25000.00 AS Decimal(10, 2)), N'Loai 1', N'Mo ta san pham 3', N'sanpham3.jpg', 30000, 0)
GO
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham], [SoLuong], [GiaBan], [LoaiSanPham], [MoTa], [Anh], [GiaNhap], [TongSanPham]) VALUES (N'SP004', N'San pham 4', 250, CAST(35000.00 AS Decimal(10, 2)), N'Loai 2', N'Mo ta san pham 4', N'sanpham4.jpg', 30000, 0)
GO
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham], [SoLuong], [GiaBan], [LoaiSanPham], [MoTa], [Anh], [GiaNhap], [TongSanPham]) VALUES (N'SP005', N'San pham 5', 300, CAST(40000.00 AS Decimal(10, 2)), N'Loai 3', N'Mo ta san pham 5', N'sanpham5.jpg', 35000, 0)
GO
INSERT [dbo].[Users] ([TenDN], [MatKhau], [VaiTro]) VALUES (N'user1', N'password1', N'Admin')
GO
INSERT [dbo].[Users] ([TenDN], [MatKhau], [VaiTro]) VALUES (N'user2', N'password2', N'Employee')
GO
INSERT [dbo].[Users] ([TenDN], [MatKhau], [VaiTro]) VALUES (N'user3', N'password3', N'Manager')
GO
INSERT [dbo].[Users] ([TenDN], [MatKhau], [VaiTro]) VALUES (N'user4', N'password4', N'Admin')
GO
INSERT [dbo].[Users] ([TenDN], [MatKhau], [VaiTro]) VALUES (N'user5', N'password5', N'Employee')
GO
INSERT [dbo].[Users] ([TenDN], [MatKhau], [VaiTro]) VALUES (N'user6', N'password6', N'Manager')
GO
ALTER TABLE [dbo].[SanPham] ADD  DEFAULT ((0)) FOR [TongSanPham]
GO
ALTER TABLE [dbo].[GIAOHANG]  WITH CHECK ADD FOREIGN KEY([MaSP])
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
/****** Object:  StoredProcedure [dbo].[sp_GetThongKe]    Script Date: 12/7/2024 1:11:47 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_GetThongKe]
 @DateFrom DATE,
    @DateTo DATE
AS
BEGIN
    SELECT 
        hd.ThoiGian AS Thoigianlap,
        COUNT(hd.MaHoaDon) AS TongSoSanPham,
        SUM(ct.ThanhTien) AS Tongtien,
        nv.MaNhanVien,
        nv.TenNhanVien AS NhanVienLapHoadon
    FROM 
        HoaDon hd
        JOIN HoaDonChiTiet ct ON hd.MaHoaDon = ct.MaHoaDon
        JOIN NhanVien nv ON hd.MaNhanVien = nv.MaNhanVien
    WHERE 
        hd.ThoiGian BETWEEN @DateFrom AND @DateTo
    GROUP BY 
        hd.ThoiGian,
        nv.MaNhanVien,
        nv.TenNhanVien
    ORDER BY 
        hd.ThoiGian;
END;

--Exec [dbo].[sp_GetThongKe]
GO
/****** Object:  StoredProcedure [dbo].[sp_GetThongKeByNhanVien]    Script Date: 12/7/2024 1:11:47 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_GetThongKeByNhanVien]
    @TenNhanVien NVARCHAR(50)
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
    WHERE nv.TenNhanVien = @TenNhanVien
    GROUP BY 
        hd.ThoiGian, 
        hd.MaHoaDon, 
        nv.MaNhanVien, 
        nv.TenNhanVien,
        sp.TenSanPham,
        sp.[GiaNhap],
        sp.GiaBan,
        ct.SoLuong
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_GetThongKeByThoiGian]    Script Date: 12/7/2024 1:11:47 pm ******/
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
