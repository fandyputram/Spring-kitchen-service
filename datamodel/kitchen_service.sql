-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2019 at 02:24 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kitchen_service`
--

-- --------------------------------------------------------

--
-- Table structure for table `kitchen_order`
--

CREATE TABLE `kitchen_order` (
  `o_id` int(11) NOT NULL,
  `o_detail` text NOT NULL,
  `o_price` int(11) NOT NULL,
  `s_id` int(11) NOT NULL,
  `o_created_at` datetime NOT NULL,
  `o_updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kitchen_order`
--

INSERT INTO `kitchen_order` (`o_id`, `o_detail`, `o_price`, `s_id`, `o_created_at`, `o_updated_at`) VALUES
(1, 'Rendang', 20000, 0, '2019-05-14 19:06:05', '2019-05-14 19:06:05');

-- --------------------------------------------------------

--
-- Table structure for table `kitchen_status`
--

CREATE TABLE `kitchen_status` (
  `s_id` int(11) NOT NULL,
  `s_detail` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kitchen_status`
--

INSERT INTO `kitchen_status` (`s_id`, `s_detail`) VALUES
(0, 'Belum diproses'),
(1, 'Sedang diproses'),
(2, 'Selesai');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kitchen_order`
--
ALTER TABLE `kitchen_order`
  ADD PRIMARY KEY (`o_id`),
  ADD KEY `s_id` (`s_id`);

--
-- Indexes for table `kitchen_status`
--
ALTER TABLE `kitchen_status`
  ADD PRIMARY KEY (`s_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kitchen_order`
--
ALTER TABLE `kitchen_order`
  ADD CONSTRAINT `kitchen_order_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `kitchen_status` (`S_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
