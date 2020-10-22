-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 23, 2019 at 11:27 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sclass`
--

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

CREATE TABLE `classes` (
  `classid` int(11) NOT NULL,
  `tid` int(10) NOT NULL,
  `classname` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`classid`, `tid`, `classname`) VALUES
(1, 1722231646, 'First class'),
(8, 1722231646, 'Chuto class');

-- --------------------------------------------------------

--
-- Table structure for table `class_files`
--

CREATE TABLE `class_files` (
  `classId` int(11) DEFAULT NULL,
  `filename` varchar(40) DEFAULT NULL,
  `fileId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_files`
--

INSERT INTO `class_files` (`classId`, `filename`, `fileId`) VALUES
(1, 'Software engineering 1', 1),
(1, 'Design', 2);

-- --------------------------------------------------------

--
-- Table structure for table `class_list`
--

CREATE TABLE `class_list` (
  `sid` int(11) NOT NULL,
  `classid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_list`
--

INSERT INTO `class_list` (`sid`, `classid`) VALUES
(1722231642, 1),
(1722231642, 8),
(1722231643, 1),
(1722231643, 8),
(1722231644, 1),
(1722231645, 1),
(1722231647, 1),
(1722231648, 8);

-- --------------------------------------------------------

--
-- Table structure for table `enroll`
--

CREATE TABLE `enroll` (
  `studentid` int(10) NOT NULL,
  `classid` int(11) NOT NULL,
  `record` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `pass` varchar(35) NOT NULL,
  `speed` int(11) NOT NULL,
  `qstn1` varchar(20) NOT NULL,
  `qstn2` varchar(20) NOT NULL,
  `preState` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `fname`, `lname`, `pass`, `speed`, `qstn1`, `qstn2`, `preState`) VALUES
(1722231642, 'MD. Ariful', 'Haque', '12345', 15, 'Sreemongal', '', NULL),
(1722231643, 'Mst. Syeda Tasfia', 'Hossain', '12345', 16, 'Sreemongal', '', NULL),
(1722231644, 'MD. Ashraful', 'Haque', '12345', 16, 'Sreemongal', '', NULL),
(1722231645, 'MD. Raquibul', 'Hasan', '12345', 17, 'Sreemongal', '', NULL),
(1722231646, 'MD. Rabiul', 'Hossain', '12345', 20, 'Sreemongal', '', 'teacher'),
(1722231647, 'Jubaer', 'Khan', '123456', 30, 'Spicy stick', 'XYZ', NULL),
(1722231648, 'Hasibul', 'Seyam', '12345', 18, 'huh', 'humm', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`classid`),
  ADD KEY `tid` (`tid`);

--
-- Indexes for table `class_files`
--
ALTER TABLE `class_files`
  ADD PRIMARY KEY (`fileId`),
  ADD KEY `classId` (`classId`);

--
-- Indexes for table `class_list`
--
ALTER TABLE `class_list`
  ADD PRIMARY KEY (`sid`,`classid`),
  ADD KEY `classid` (`classid`);

--
-- Indexes for table `enroll`
--
ALTER TABLE `enroll`
  ADD PRIMARY KEY (`studentid`,`classid`),
  ADD KEY `classid` (`classid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
  MODIFY `classid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `class_files`
--
ALTER TABLE `class_files`
  MODIFY `fileId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `classes`
--
ALTER TABLE `classes`
  ADD CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `user` (`id`);

--
-- Constraints for table `class_files`
--
ALTER TABLE `class_files`
  ADD CONSTRAINT `class_files_ibfk_1` FOREIGN KEY (`classId`) REFERENCES `classes` (`classid`);

--
-- Constraints for table `class_list`
--
ALTER TABLE `class_list`
  ADD CONSTRAINT `class_list_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `class_list_ibfk_2` FOREIGN KEY (`classid`) REFERENCES `classes` (`classid`);

--
-- Constraints for table `enroll`
--
ALTER TABLE `enroll`
  ADD CONSTRAINT `enroll_ibfk_1` FOREIGN KEY (`studentid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `enroll_ibfk_2` FOREIGN KEY (`classid`) REFERENCES `classes` (`classid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
