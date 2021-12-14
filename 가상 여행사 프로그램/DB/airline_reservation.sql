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
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reservation` (
  `reservation_num` int(11) NOT NULL AUTO_INCREMENT,
  `filght_num` varchar(20) DEFAULT NULL,
  `cus_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`reservation_num`),
  KEY `filght_num` (`filght_num`),
  KEY `cus_id` (`cus_id`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`filght_num`) REFERENCES `airline_filght` (`filght_num`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`cus_id`) REFERENCES `customer` (`cus_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=604 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (500,'135','abc123'),(501,'136','abc124'),(502,'137','abc125'),(503,'138','abc126'),(504,'139','abc127'),(505,'140','abc128'),(506,'141','abc129'),(507,'142','abc130'),(508,'143','abc131'),(509,'144','abc132'),(510,'145','abc133'),(511,'146','abc134'),(512,'147','abc135'),(513,'148','abc136'),(514,'149','abc137'),(515,'150','abc138'),(516,'151','abc139'),(517,'152','abc140'),(518,'153','abc141'),(519,'154','abc142'),(520,'155','abc143'),(521,'156','abc144'),(522,'157','abc145'),(523,'158','abc146'),(524,'159','abc147'),(525,'135','abc148'),(526,'136','abc149'),(527,'137','abc150'),(528,'138','abc151'),(529,'139','abc152'),(530,'140','abc153'),(531,'141','abc154'),(532,'142','abc155'),(533,'143','abc156'),(534,'144','abc157'),(535,'145','abc158'),(536,'146','abc159'),(537,'147','abc160'),(538,'148','abc161'),(539,'149','abc162'),(540,'150','abc163'),(541,'151','abc164'),(542,'152','abc165'),(543,'153','abc166'),(544,'154','abc167'),(545,'155','abc168'),(546,'156','abc169'),(547,'157','abc170'),(548,'158','abc171'),(549,'159','abc172'),(550,'135','abc173'),(551,'136','abc174'),(552,'137','abc175'),(553,'138','abc176'),(554,'139','abc177'),(555,'140','abc178'),(556,'141','abc179'),(557,'142','abc180'),(558,'143','abc181'),(559,'144','abc182'),(560,'145','abc183'),(561,'146','abc184'),(562,'147','abc185'),(563,'148','abc186'),(564,'149','abc187'),(565,'150','abc188'),(566,'151','abc189'),(567,'152','abc190'),(568,'153','abc191'),(569,'154','abc192'),(570,'155','abc193'),(571,'156','abc194'),(572,'157','abc195'),(573,'158','abc196'),(574,'159','abc197'),(575,'135','abc198'),(576,'136','abc199'),(577,'137','abc200'),(578,'138','abc201'),(579,'139','abc202'),(580,'140','abc203'),(581,'141','abc204'),(582,'142','abc205'),(583,'143','abc206'),(584,'144','abc207'),(585,'145','abc208'),(586,'146','abc209'),(587,'147','abc210'),(588,'148','abc211'),(589,'149','abc212'),(590,'150','abc213'),(591,'151','abc214'),(592,'152','abc215'),(593,'153','abc216'),(594,'154','abc217'),(595,'155','abc218'),(596,'156','abc219'),(597,'157','abc220'),(598,'158','abc221'),(599,'159','abc222'),(600,'135','yun'),(601,'145','yun'),(603,'154','yun');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
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
