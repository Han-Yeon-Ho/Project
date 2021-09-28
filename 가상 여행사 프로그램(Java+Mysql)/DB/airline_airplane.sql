-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: airline
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `airplane`
--

DROP TABLE IF EXISTS `airplane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `airplane` (
  `airplane_id` varchar(20) NOT NULL,
  `airplane_model` varchar(20) DEFAULT NULL,
  `airplane_airline` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`airplane_id`),
  KEY `airplane_model` (`airplane_model`),
  CONSTRAINT `airplane_ibfk_1` FOREIGN KEY (`airplane_model`) REFERENCES `airplane_seat` (`airplane_model`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airplane`
--

LOCK TABLES `airplane` WRITE;
/*!40000 ALTER TABLE `airplane` DISABLE KEYS */;
INSERT INTO `airplane` VALUES ('GHI456','보잉777-300ER','대한항공'),('GHI457','보잉777-300ER','아시아나항공'),('GHI458','보잉777-300ER','진에어'),('GHI459','보잉777-300ER','에어서울'),('GHI460','보잉777-300ER','이스타항공'),('GHI461','보잉737','대한항공'),('GHI462','보잉737','아시아나항공'),('GHI463','보잉737','진에어'),('GHI464','보잉737','에어서울'),('GHI465','보잉737','이스타항공'),('GHI466','에어버스A330','대한항공'),('GHI467','에어버스A330','아시아나항공'),('GHI468','에어버스A330','진에어'),('GHI469','에어버스A330','에어서울'),('GHI470','에어버스A330','이스타항공'),('GHI471','에어버스A321','대한항공'),('GHI472','에어버스A321','아시아나항공'),('GHI473','에어버스A321','진에어'),('GHI474','에어버스A321','에어서울'),('GHI475','에어버스A321','이스타항공'),('GHI476','에어버스A320','대한항공'),('GHI477','에어버스A320','아시아나항공'),('GHI478','에어버스A320','진에어'),('GHI479','에어버스A320','에어서울'),('GHI480','에어버스A320','이스타항공');
/*!40000 ALTER TABLE `airplane` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-14 13:06:27
