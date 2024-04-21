-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: apartment_db
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `apartment_admin`
--

DROP TABLE IF EXISTS `apartment_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_admin` (
  `user_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `id` FOREIGN KEY (`user_id`) REFERENCES `apartment_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_admin`
--

LOCK TABLES `apartment_admin` WRITE;
/*!40000 ALTER TABLE `apartment_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_detail_receipt`
--

DROP TABLE IF EXISTS `apartment_detail_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_detail_receipt` (
  `id` int NOT NULL AUTO_INCREMENT,
  `receipt_id` int NOT NULL,
  `content` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `receiptdetail_idx` (`receipt_id`),
  CONSTRAINT `receiptdetail` FOREIGN KEY (`receipt_id`) REFERENCES `apartment_receipt` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_detail_receipt`
--

LOCK TABLES `apartment_detail_receipt` WRITE;
/*!40000 ALTER TABLE `apartment_detail_receipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_detail_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_detail_report`
--

DROP TABLE IF EXISTS `apartment_detail_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_detail_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `report_id` int DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `detailreport_idx` (`report_id`),
  CONSTRAINT `detailreport` FOREIGN KEY (`report_id`) REFERENCES `apartment_report` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_detail_report`
--

LOCK TABLES `apartment_detail_report` WRITE;
/*!40000 ALTER TABLE `apartment_detail_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_detail_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_detail_request`
--

DROP TABLE IF EXISTS `apartment_detail_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_detail_request` (
  `id` int NOT NULL AUTO_INCREMENT,
  `request_id` int NOT NULL,
  `question` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `score_band` enum('BAND_5','BAND_10') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'BAND_5',
  PRIMARY KEY (`id`),
  KEY `request_id_idx` (`request_id`),
  CONSTRAINT `request_id` FOREIGN KEY (`request_id`) REFERENCES `apartment_survey_request` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_detail_request`
--

LOCK TABLES `apartment_detail_request` WRITE;
/*!40000 ALTER TABLE `apartment_detail_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_detail_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_detail_response`
--

DROP TABLE IF EXISTS `apartment_detail_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_detail_response` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_id` int NOT NULL,
  `answer` int NOT NULL,
  `response_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `question_idx` (`question_id`),
  KEY `response_idx` (`response_id`),
  CONSTRAINT `question` FOREIGN KEY (`question_id`) REFERENCES `apartment_detail_request` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `response` FOREIGN KEY (`response_id`) REFERENCES `apartment_survey_response` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_detail_response`
--

LOCK TABLES `apartment_detail_response` WRITE;
/*!40000 ALTER TABLE `apartment_detail_response` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_detail_response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_floor`
--

DROP TABLE IF EXISTS `apartment_floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_floor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_floor`
--

LOCK TABLES `apartment_floor` WRITE;
/*!40000 ALTER TABLE `apartment_floor` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_other_member`
--

DROP TABLE IF EXISTS `apartment_other_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_other_member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apartment_id` int NOT NULL,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `relationship` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `apartmentmembers_idx` (`apartment_id`),
  CONSTRAINT `apartmentmembers` FOREIGN KEY (`apartment_id`) REFERENCES `apartment_rental_constract` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_other_member`
--

LOCK TABLES `apartment_other_member` WRITE;
/*!40000 ALTER TABLE `apartment_other_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_other_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_receipt`
--

DROP TABLE IF EXISTS `apartment_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_receipt` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apartment_id` int NOT NULL,
  `month` int DEFAULT NULL,
  `created_date` date NOT NULL,
  `total` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `apartment_receipt_idx` (`apartment_id`),
  CONSTRAINT `apartment_receipt` FOREIGN KEY (`apartment_id`) REFERENCES `apartment_rental_constract` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_receipt`
--

LOCK TABLES `apartment_receipt` WRITE;
/*!40000 ALTER TABLE `apartment_receipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_relative_registry`
--

DROP TABLE IF EXISTS `apartment_relative_registry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_relative_registry` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apartment_id` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `active` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `apartment_id_idx` (`apartment_id`),
  CONSTRAINT `apartment_id` FOREIGN KEY (`apartment_id`) REFERENCES `apartment_rental_constract` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_relative_registry`
--

LOCK TABLES `apartment_relative_registry` WRITE;
/*!40000 ALTER TABLE `apartment_relative_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_relative_registry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_rental_constract`
--

DROP TABLE IF EXISTS `apartment_rental_constract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_rental_constract` (
  `id` int NOT NULL AUTO_INCREMENT,
  `resident_id` int NOT NULL,
  `room_id` int DEFAULT NULL,
  `status` enum('OWNED','RENTED') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'RENTED',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` tinyint NOT NULL DEFAULT '1',
  `final_price` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `room_id_idx` (`room_id`),
  KEY `resident_id_idx` (`resident_id`),
  CONSTRAINT `resident_id` FOREIGN KEY (`resident_id`) REFERENCES `apartment_resident` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `room_id` FOREIGN KEY (`room_id`) REFERENCES `apartment_room` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_rental_constract`
--

LOCK TABLES `apartment_rental_constract` WRITE;
/*!40000 ALTER TABLE `apartment_rental_constract` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_rental_constract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_report`
--

DROP TABLE IF EXISTS `apartment_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apartment_id` int DEFAULT NULL,
  `title` text COLLATE utf8mb4_unicode_ci,
  `created_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `apartment_idx` (`apartment_id`),
  CONSTRAINT `apartment_report` FOREIGN KEY (`apartment_id`) REFERENCES `apartment_rental_constract` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_report`
--

LOCK TABLES `apartment_report` WRITE;
/*!40000 ALTER TABLE `apartment_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_report_picture`
--

DROP TABLE IF EXISTS `apartment_report_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_report_picture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `report_id` int NOT NULL,
  `picture` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `reportpicture_idx` (`report_id`),
  CONSTRAINT `reportpicture` FOREIGN KEY (`report_id`) REFERENCES `apartment_detail_report` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_report_picture`
--

LOCK TABLES `apartment_report_picture` WRITE;
/*!40000 ALTER TABLE `apartment_report_picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_report_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_resident`
--

DROP TABLE IF EXISTS `apartment_resident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_resident` (
  `user_id` int NOT NULL,
  `joined_date` date NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `apartment_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_resident`
--

LOCK TABLES `apartment_resident` WRITE;
/*!40000 ALTER TABLE `apartment_resident` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_resident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_room`
--

DROP TABLE IF EXISTS `apartment_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `room_number` varchar(3) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_blank` tinyint NOT NULL DEFAULT '1',
  `price` int NOT NULL,
  `floor` int NOT NULL,
  `is_active` tinyint DEFAULT '1',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `floor_idx` (`floor`),
  CONSTRAINT `floor` FOREIGN KEY (`floor`) REFERENCES `apartment_floor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_room`
--

LOCK TABLES `apartment_room` WRITE;
/*!40000 ALTER TABLE `apartment_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_service`
--

DROP TABLE IF EXISTS `apartment_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` int NOT NULL,
  `is_active` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_service`
--

LOCK TABLES `apartment_service` WRITE;
/*!40000 ALTER TABLE `apartment_service` DISABLE KEYS */;
INSERT INTO `apartment_service` VALUES (1,'TIEN_DIEN',3500,1),(2,'TIEN_NUOC',80000,1),(3,'WIFI',80000,1),(4,'GIU_XE',100000,1),(5,'AN_NINH',70000,1),(6,'TU_DO_THONG_MINH',50000,1);
/*!40000 ALTER TABLE `apartment_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_service_constract`
--

DROP TABLE IF EXISTS `apartment_service_constract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_service_constract` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apartment_id` int NOT NULL,
  `service_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `refapa_idx` (`apartment_id`),
  KEY `refsv_idx` (`service_id`),
  CONSTRAINT `refapa` FOREIGN KEY (`apartment_id`) REFERENCES `apartment_rental_constract` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `refsv` FOREIGN KEY (`service_id`) REFERENCES `apartment_service` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_service_constract`
--

LOCK TABLES `apartment_service_constract` WRITE;
/*!40000 ALTER TABLE `apartment_service_constract` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_service_constract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_smart_cabinet`
--

DROP TABLE IF EXISTS `apartment_smart_cabinet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_smart_cabinet` (
  `id` int NOT NULL,
  `apartment_id` int NOT NULL,
  `decription` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('PENDING','RECIEVCED') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'PENDING',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `prod_idx` (`apartment_id`),
  CONSTRAINT `prod` FOREIGN KEY (`apartment_id`) REFERENCES `apartment_rental_constract` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_smart_cabinet`
--

LOCK TABLES `apartment_smart_cabinet` WRITE;
/*!40000 ALTER TABLE `apartment_smart_cabinet` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_smart_cabinet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_survey_request`
--

DROP TABLE IF EXISTS `apartment_survey_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_survey_request` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admin_id` int DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `is_active` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `admin_id_idx` (`admin_id`),
  CONSTRAINT `admin_id` FOREIGN KEY (`admin_id`) REFERENCES `apartment_admin` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_survey_request`
--

LOCK TABLES `apartment_survey_request` WRITE;
/*!40000 ALTER TABLE `apartment_survey_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_survey_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_survey_response`
--

DROP TABLE IF EXISTS `apartment_survey_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_survey_response` (
  `id` int NOT NULL AUTO_INCREMENT,
  `survey_id` int NOT NULL,
  `apartment_id` int DEFAULT NULL,
  `submit_date` date NOT NULL,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `survey_id_idx` (`survey_id`),
  KEY `apartment_id_idx` (`apartment_id`),
  CONSTRAINT `apartment` FOREIGN KEY (`apartment_id`) REFERENCES `apartment_rental_constract` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `survey_id` FOREIGN KEY (`survey_id`) REFERENCES `apartment_survey_request` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_survey_response`
--

LOCK TABLES `apartment_survey_response` WRITE;
/*!40000 ALTER TABLE `apartment_survey_response` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_survey_response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_user`
--

DROP TABLE IF EXISTS `apartment_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `last_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthdate` date NOT NULL,
  `gender` tinyint NOT NULL DEFAULT '1',
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT 'mhphat.c17nvt@gmail.com',
  `phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_active` tinyint NOT NULL DEFAULT '1',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `role` enum('ADMIN','RESIDENT') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'RESIDENT',
  `username` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_user`
--

LOCK TABLES `apartment_user` WRITE;
/*!40000 ALTER TABLE `apartment_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-21 15:01:06
