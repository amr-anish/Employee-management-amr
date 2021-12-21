CREATE DATABASE  IF NOT EXISTS `employeedb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `employeedb`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: employeedb
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `attendance_id` int NOT NULL AUTO_INCREMENT,
  `emp_id` int DEFAULT NULL,
  `login` time DEFAULT NULL,
  `logout` time DEFAULT NULL,
  `day` date NOT NULL,
  PRIMARY KEY (`attendance_id`),
  KEY `emp_idx` (`emp_id`),
  CONSTRAINT `emp` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (16,1,'13:10:13','13:10:28','2021-11-23'),(17,1,'13:36:19','13:36:25','2021-11-25');
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `desigination`
--

DROP TABLE IF EXISTS `desigination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desigination` (
  `desigination_id` int NOT NULL,
  `post` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`desigination_id`,`post`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desigination`
--

LOCK TABLES `desigination` WRITE;
/*!40000 ALTER TABLE `desigination` DISABLE KEYS */;
INSERT INTO `desigination` VALUES (1,'Group Manager'),(2,'Project Manager'),(3,'Associate Manager');
/*!40000 ALTER TABLE `desigination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_leave`
--

DROP TABLE IF EXISTS `emp_leave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp_leave` (
  `emp_leave_id` int NOT NULL AUTO_INCREMENT,
  `emp_id` int NOT NULL,
  `sick` int NOT NULL DEFAULT '2',
  `casual` int NOT NULL DEFAULT '2',
  PRIMARY KEY (`emp_leave_id`),
  KEY `EMP_ID_idx` (`emp_id`),
  CONSTRAINT `EMP_ID` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_leave`
--

LOCK TABLES `emp_leave` WRITE;
/*!40000 ALTER TABLE `emp_leave` DISABLE KEYS */;
INSERT INTO `emp_leave` VALUES (1,1,4,2),(2,2,2,1),(3,3,0,0),(4,17,2,2);
/*!40000 ALTER TABLE `emp_leave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `emp_id` int NOT NULL AUTO_INCREMENT,
  `desigination_id` int NOT NULL DEFAULT '3',
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone_number` bigint NOT NULL,
  `manager_id` int DEFAULT NULL,
  `attendance_id` int DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `post_idx` (`desigination_id`),
  CONSTRAINT `post` FOREIGN KEY (`desigination_id`) REFERENCES `desigination` (`desigination_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,3,'Anish','1997-02-16','anish@gmail.com',9874563210,15,17,'qwerty'),(2,2,'John','1985-02-02','jhon@gmail.com',7896541230,3,NULL,'qwerty'),(3,1,'Sam','1982-08-09','sam@gmail.com',8975462130,NULL,NULL,'qwerty'),(4,2,'Jack','1982-08-09','jack@gmail.com',7896541213,3,NULL,'qwerty'),(14,3,'Samuel','1982-08-09','samuel@gmail.com',8795856452,2,NULL,'qwerty'),(17,3,'Jenish','1999-06-25','jenish@gmail.com',7896542130,2,NULL,'test');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_approval`
--

DROP TABLE IF EXISTS `leave_approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leave_approval` (
  `leave_id` int NOT NULL AUTO_INCREMENT,
  `emp_id` int NOT NULL,
  `leave_type` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `no_of_days` int NOT NULL,
  `reason` varchar(45) CHARACTER SET utf8 NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  `manager_id` int NOT NULL,
  `approval` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`leave_id`),
  KEY `empID_idx` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_approval`
--

LOCK TABLES `leave_approval` WRITE;
/*!40000 ALTER TABLE `leave_approval` DISABLE KEYS */;
INSERT INTO `leave_approval` VALUES (1,2,'casual',1,'Personal Leave','2021-11-23','2021-11-24',3,1),(2,1,'sick',1,'fever','2021-11-13','2021-11-14',15,2),(3,3,'sick',1,'cold','2021-11-13','2021-11-14',0,1),(4,3,'casual',2,'fever','2021-11-10','2021-11-12',0,1),(5,3,'sick',1,'cold','2021-11-26','2021-11-27',0,1);
/*!40000 ALTER TABLE `leave_approval` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-25 14:25:52
