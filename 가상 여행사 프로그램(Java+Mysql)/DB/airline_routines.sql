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
-- Temporary view structure for view `resv`
--

DROP TABLE IF EXISTS `resv`;
/*!50001 DROP VIEW IF EXISTS `resv`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `resv` AS SELECT 
 1 AS `reservation_num`,
 1 AS `filght_num`,
 1 AS `airplane_id`,
 1 AS `cus_name`,
 1 AS `airport_start`,
 1 AS `airport_des`,
 1 AS `start_time`,
 1 AS `des_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `airps`
--

DROP TABLE IF EXISTS `airps`;
/*!50001 DROP VIEW IF EXISTS `airps`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `airps` AS SELECT 
 1 AS `airplane_id`,
 1 AS `airplane_model`,
 1 AS `seat`,
 1 AS `airplane_airline`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `resv1`
--

DROP TABLE IF EXISTS `resv1`;
/*!50001 DROP VIEW IF EXISTS `resv1`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `resv1` AS SELECT 
 1 AS `reservation_num`,
 1 AS `filght_num`,
 1 AS `airplane_id`,
 1 AS `cus_id`,
 1 AS `cus_name`,
 1 AS `airport_start`,
 1 AS `airport_des`,
 1 AS `start_time`,
 1 AS `des_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `left_seata`
--

DROP TABLE IF EXISTS `left_seata`;
/*!50001 DROP VIEW IF EXISTS `left_seata`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `left_seata` AS SELECT 
 1 AS `COUNT(*)`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `resv`
--

/*!50001 DROP VIEW IF EXISTS `resv`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `resv` AS select `r`.`reservation_num` AS `reservation_num`,`a`.`filght_num` AS `filght_num`,`ap`.`airplane_id` AS `airplane_id`,`c`.`cus_name` AS `cus_name`,`a`.`airport_start` AS `airport_start`,`a`.`airport_des` AS `airport_des`,`a`.`start_time` AS `start_time`,`a`.`des_time` AS `des_time` from (((`airline_filght` `a` join `reservation` `r`) join `customer` `c`) join `airplane` `ap`) where ((`a`.`filght_num` = `r`.`filght_num`) and (`r`.`cus_id` = `c`.`cus_id`) and (`a`.`airplane_id` = `ap`.`airplane_id`)) group by `r`.`reservation_num` asc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `airps`
--

/*!50001 DROP VIEW IF EXISTS `airps`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `airps` AS select `a`.`airplane_id` AS `airplane_id`,`b`.`airplane_model` AS `airplane_model`,`b`.`seat` AS `seat`,`a`.`airplane_airline` AS `airplane_airline` from (`airplane` `a` join `airplane_seat` `b`) where (`a`.`airplane_model` = `b`.`airplane_model`) group by `a`.`airplane_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `resv1`
--

/*!50001 DROP VIEW IF EXISTS `resv1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `resv1` AS select `r`.`reservation_num` AS `reservation_num`,`a`.`filght_num` AS `filght_num`,`ap`.`airplane_id` AS `airplane_id`,`c`.`cus_id` AS `cus_id`,`c`.`cus_name` AS `cus_name`,`a`.`airport_start` AS `airport_start`,`a`.`airport_des` AS `airport_des`,`a`.`start_time` AS `start_time`,`a`.`des_time` AS `des_time` from (((`airline_filght` `a` join `reservation` `r`) join `customer` `c`) join `airplane` `ap`) where ((`a`.`filght_num` = `r`.`filght_num`) and (`r`.`cus_id` = `c`.`cus_id`) and (`a`.`airplane_id` = `ap`.`airplane_id`)) group by `r`.`reservation_num` asc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `left_seata`
--

/*!50001 DROP VIEW IF EXISTS `left_seata`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `left_seata` AS select count(0) AS `COUNT(*)` from (`reservation` join `airline_filght`) where (`reservation`.`filght_num` = `airline_filght`.`filght_num`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-14 13:06:28
