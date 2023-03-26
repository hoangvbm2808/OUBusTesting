CREATE DATABASE  IF NOT EXISTS `oubus` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `oubus`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: oubus
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `taiKhoan` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `matKhau` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_vi_0900_as_cs DEFAULT NULL,
  `maQuyen` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin','123',1),(2,'staff','123',2),(3,'staff1','123',2),(4,'staff2','123',2);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chuyendi`
--

DROP TABLE IF EXISTS `chuyendi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuyendi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `giaVe` int DEFAULT NULL,
  `ngayKhoiHanh` date DEFAULT NULL,
  `gioKhoiHanh` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `diemKhoiHanh` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `diemKetThuc` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `soGheTrong` int DEFAULT NULL,
  `soGheDat` int DEFAULT NULL,
  `trangThai` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `maXe` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chuyendi_xekhach_idx` (`maXe`),
  CONSTRAINT `fk_chuyendi_xekhach` FOREIGN KEY (`maXe`) REFERENCES `xekhach` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuyendi`
--

LOCK TABLES `chuyendi` WRITE;
/*!40000 ALTER TABLE `chuyendi` DISABLE KEYS */;
INSERT INTO `chuyendi` VALUES (1,120000,'2020-02-02','12','TPHCM','Gia Lai',45,0,'1',1);
/*!40000 ALTER TABLE `chuyendi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doanhthuchuyendi`
--

DROP TABLE IF EXISTS `doanhthuchuyendi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doanhthuchuyendi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doanhthu` int DEFAULT NULL,
  `soVeDat` int DEFAULT NULL,
  `ngay` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doanhthuchuyendi`
--

LOCK TABLES `doanhthuchuyendi` WRITE;
/*!40000 ALTER TABLE `doanhthuchuyendi` DISABLE KEYS */;
INSERT INTO `doanhthuchuyendi` VALUES (1,12000000,10,'2020-02-02');
/*!40000 ALTER TABLE `doanhthuchuyendi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenNhanVien` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `maLoaiNhanVien` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ngaySinh` date DEFAULT NULL,
  `soDienThoai` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `cMND` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `queQuan` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `maAccount` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `maAccount_UNIQUE` (`maAccount`),
  CONSTRAINT `fk_account_nhanvien` FOREIGN KEY (`maAccount`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Lê Văn Lâm','staff','2000-01-01','0335555555','123456789123','TPHCM',2),(2,'Thái Tấn Phát','staff','1999-02-02','0720397434','123456789123','An Giang',3);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vexe`
--

DROP TABLE IF EXISTS `vexe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vexe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenKhachHang` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngayDat` date DEFAULT NULL,
  `sdt` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `viTriGhe` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `trangThai` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `maChuyenDi` int DEFAULT NULL,
  `maNhanVien` int DEFAULT NULL,
  `maDoanhThu` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vexe_nhanvien_idx` (`maNhanVien`),
  KEY `fk_vexe_chuyendi_idx` (`maChuyenDi`),
  KEY `fk_doanhthu_vexe_idx` (`maDoanhThu`),
  CONSTRAINT `fk_doanhthu_vexe` FOREIGN KEY (`maDoanhThu`) REFERENCES `doanhthuchuyendi` (`id`),
  CONSTRAINT `fk_vexe_chuyendi` FOREIGN KEY (`maChuyenDi`) REFERENCES `chuyendi` (`id`),
  CONSTRAINT `fk_vexe_nhanvien` FOREIGN KEY (`maNhanVien`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vexe`
--

LOCK TABLES `vexe` WRITE;
/*!40000 ALTER TABLE `vexe` DISABLE KEYS */;
INSERT INTO `vexe` VALUES (1,'Phan Thi Yen Vi','2020-02-02','0123456789','23','1',1,2,1);
/*!40000 ALTER TABLE `vexe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xekhach`
--

DROP TABLE IF EXISTS `xekhach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xekhach` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bienSoXe` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `soGhe` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xekhach`
--

LOCK TABLES `xekhach` WRITE;
/*!40000 ALTER TABLE `xekhach` DISABLE KEYS */;
INSERT INTO `xekhach` VALUES (1,'55-A23',45),(2,'59-B12',45);
/*!40000 ALTER TABLE `xekhach` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-26 13:13:34
