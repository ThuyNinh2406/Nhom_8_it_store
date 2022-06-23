-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 22, 2022 lúc 04:24 PM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `dataonline`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(30) NOT NULL,
  `tensanpham` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hinhanh` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `hinhanh`) VALUES
(1, 'Laptop', 'https://ngochieu.name.vn/img/laptop.png'),
(2, 'Help', 'https://ngochieu.name.vn/img/contact.png'),
(5, 'Information', 'https://ngochieu.name.vn/img/info.png'),
(7, 'Giỏ hàng', 'https://png.pngtree.com/png-vector/20190831/ourlarge/pngtree-icon-cart-blue-gradient-light-png-image_1717175.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanphammoi`
--

CREATE TABLE `sanphammoi` (
  `id` int(11) NOT NULL,
  `tensp` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hinhanh` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `mota` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `loai` int(2) NOT NULL,
  `giasp` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanphammoi`
--

INSERT INTO `sanphammoi` (`id`, `tensp`, `hinhanh`, `mota`, `loai`, `giasp`) VALUES
(1, 'Laptop Dell Vostro V3568', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/dell-V3568-XF6C61-01.jpg', 'Chip: Intel Core i5-7200U\r\nRAM: DDR4 4GB (2 khe cắm)\r\nỔ cứng: HDD 1TB\r\nChipset đồ họa: Intel HD Graphics 620\r\nMàn hình: 15.6 Inches\r\nHệ điều hành: Free Dos\r\nPin: 4 Cell Lithium-ion', 2, '25,399000 vnd'),
(2, 'Apple Macbook Pro 2017 ', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/mac-pro-2017-01.jpg', 'Bộ xử lý: Intel Core i5 dual-core 3.1GHz, Turbo Boost up to 3.5GHz, with 64MB of eDRAM\r\nRAM: 8GB 2133MHz LPDDR3 memory\r\nỔ cứng SSD: 512GB\r\nCard đồ hoạ: Intel Iris Plus Graphics 650', 1, '30,469000 vnd'),
(3, 'Laptop HP Envy 13-ad074TU', 'http://mauweb.monamedia.net/hanoicomputer/wp-content/uploads/2017/12/hp-envy-13-01.jpg', 'CPU Intel Core i7-7500U 2.7GHz up to 3.5GHz 4MB\r\nRAM 8GB LPDDR3 Onboard\r\nĐĩa cứng 256 GB PCIe® NVMe™ M.2 SSD\r\nCard đồ họa Intel® HD Graphics 620\r\nMàn hình 13.3 inch FHD (1920 x 1080) diagonal IPS BrightView micro-edge WLED-backlit', 2, '17,899000 vnd'),
(4, 'Laptop Asus X515EA-EJ1046W', 'https://hanoicomputercdn.com/media/product/62697_laptop_asus_x515ea_24.jpg', 'CPU: Intel core i5 1135G7. RAM: 8GB. Ổ cứng: 512GB SSD. VGA: Onboard. Màn hình: 15.6 inch FHD. HĐH: Win 11. Màu: Bạc', 1, '16,439000 vnd'),
(9, 'Lenovo IdeaPad Gaming 3 15ACH6', 'https://anphat.com.vn/media/product/38023_ideapad_gaming_3_15ach6_ct1_07.png', 'Laptop Lenovo IdeaPad Gaming 3 15ACH6 82K2008WVN (Ryzen 5-5600H | 8GB | 512GB | RTX 3050 4GB | 15.6 inch FHD | Win 10 | Đen)\r\nMã SP: NBLN0579  26 đánh giá Lượt xem: 20.059  So sánh\r\nCPU: AMD Ryzen™ 5-5600H (3.30GHz upto 4.20GHz, 16MB)\r\nRAM: 8GB(1x 8GB) SO-DIMM DDR4-3200Mhz (2 khe, tối đa 16GB)\r\nỔ cứng: 512GB SSD M.2 2242 PCIe 3.0x4 NVMe\r\nVGA: NVIDIA GeForce RTX 3050 4GB GDDR6', 1, '17,790000 vnd'),
(10, 'Laptop Dell XPS 13 9305 (2021)', 'https://laptopaz.vn/media/lib/2031_xps2.jpg', 'CPU	Intel® Core™ i5-1135G7\r\nRAM	8GB 4267MHz LPDDR4x Memory Onboard\r\nỔ cứng	256GB M.2 PCIe NVMe \r\nCard VGA	13.3″ FHD (1920 x 1080) InfinityEdge Non-Touch display\r\nMàn hình	13.3 Inch FHD IPS\r\nWebcam	2,25mm 720p ở HD 30 khung hình / giây, micrô mảng kỹ thuật số\r\nKết nối	\r\n1x USB 3.2 Gen 2 Type-C ™ với Power Delivery / DisplayPort,\r\n\r\n1x Tai nghe (kết hợp tai nghe và micrô),\r\n\r\n2x Thunderbolt ™ 4 với Power Delivery / DisplayPort ,\r\n\r\nTrọng lượng	1.16 kg\r\nPin	3 Cells - 52WHr\r\nHệ điều hành	Windows 10', 1, '23,490,000 vnd');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanphammoi`
--
ALTER TABLE `sanphammoi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `sanphammoi`
--
ALTER TABLE `sanphammoi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
