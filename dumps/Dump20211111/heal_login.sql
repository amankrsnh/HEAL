-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: heal
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `usertype` varchar(30) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('aashk23@gmail.com','aashk123','admin'),('aastha63@gmail.com','aastha263','doctor'),('abhi12@gmail.com','abhishek123','doctor'),('abhi63@gmail.com','abhi9947','patient'),('akhshay77@gmail.com','akshay283','doctor'),('aman99@gmail.com','aman123','admin'),('anjali81@gmail.com','anjali123','admin'),('avinash527@gmail.com','avinash239','doctor'),('deep748@gmail.com','deep039','doctor'),('lava162@gmail.com','lavanya373','doctor'),('mayank40@gmail.com','mayank123','admin'),('mitali27@gmail.com','mitali4959','doctor'),('naveenmedi19@gmail.com','naveen123','doctor'),('neelu256@gmail.com','neelam8192','doctor'),('neraj886@gmail.com','neeraj39','patient'),('nikhi76@gmail.com','nikhil849','doctor'),('prachi47@gmail.com','prachi9585','patient'),('pranav56@gmail.com','pranav9997','patient'),('raghav77@gmail.com','raghav273','doctor'),('shekhar253@gmail.com','shekhar384','doctor'),('shivani77@gmail.com','shivani993','patient'),('sidroy25@gmail.com','sidroy273','doctor'),('vaibhav22@gmail.com','vaibhav123','doctor'),('varun776@gmail.com','varun567','doctor');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-11  4:26:13
