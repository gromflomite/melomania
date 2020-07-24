-- MariaDB dump 10.17  Distrib 10.5.4-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: audio
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `albums`
--

DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `albums` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(250) NOT NULL,
  `artist` varchar(100) NOT NULL,
  `year` int(4) NOT NULL,
  `comments` varchar(250) DEFAULT NULL,
  `cover` varchar(250) DEFAULT NULL,
  `id_genre` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `creation_date` datetime NOT NULL DEFAULT current_timestamp(),
  `approved_date` datetime DEFAULT NULL COMMENT 'If null, album not publicy visible, must be first approved by an admin',
  PRIMARY KEY (`id`),
  KEY `fk_genres` (`id_genre`),
  KEY `fk_users` (`id_user`),
  CONSTRAINT `fk_genres` FOREIGN KEY (`id_genre`) REFERENCES `genres` (`id`),
  CONSTRAINT `fk_users` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8 COMMENT='Albums collection table.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (2,'Love & the death of damnation','The white buffalo',2015,'Meet The White Buffalo, project of earthy, Oregon-born and Southern California-raised singer/songwriter Jake Smith, whose music is finally getting the attention it deserves.','https://i.imgur.com/iIqIXsJ.jpg',1,8,'2020-07-13 12:27:01','2020-07-13 12:27:01'),(3,'Darkest darks, lightest lights','The white buffalo',2017,'Meet The White Buffalo, project of earthy, Oregon-born and Southern California-raised singer/songwriter Jake Smith, whose music is finally getting the attention it deserves.','https://i.imgur.com/wVo5QbZ.jpg',1,8,'2020-07-13 12:27:01','2020-07-13 12:27:01'),(4,'Every picture tells a story','Rod Stewart',1971,'Every Picture Tells a Story, released May 1971, is the third album by Rod Stewart. It incorporates hard rock, folk, and blues styles.[','https://i.imgur.com/KhoCqxS.jpg',1,7,'2020-07-13 12:27:01','2020-07-13 12:27:01'),(6,'Now\'s the time','Charlie Parker',1945,'\"Now\'s the Time\" is a composition by Charlie Parker. He led the first recording of it, on November 26, 1945. ','https://i.imgur.com/ujHCBKh.jpg',7,6,'2020-07-13 12:27:01','2020-07-13 12:27:01'),(7,'La leyenda del tiempo','Camaron de la isla',1979,'La leyenda del tiempo is the tenth album by Spanish flamenco singer Camarón de la Isla, and the first one not to feature his long-time collaborator, guitarist Paco de Lucía. ','https://i.imgur.com/p7BbF90.jpg',5,8,'2020-07-13 12:27:01','2020-07-13 12:27:01'),(9,'On the shoulder of giants','Shawn James',2016,'One of the greatest albums of all time.','https://i.imgur.com/lOVK72p.jpg',1,8,'2020-07-13 12:27:01','2020-07-13 12:27:01'),(10,'No promises... no debts','Golden earring',1979,'No Promises...No Debts is an album by Dutch hard rock band Golden Earring, released in 1979. In the U.S. the album was released with a different cover photo showing the group standing around. ','https://i.imgur.com/T6yIe1T.jpg',1,7,'2020-07-13 12:27:01','2020-07-13 12:27:01'),(77,'Beggars banquet','The rolling stones',1968,'Beggars Banquet is a studio album by English rock band the Rolling Stones. It was released in December 1968.','https://i.imgur.com/ufdoqiz.jpg',1,8,'2020-07-13 12:27:01','2020-07-13 12:27:01'),(78,'Melody A.M.','Röyksopp',2001,'Melody A.M. is the debut studio album by Norwegian electronic music duo Röyksopp. It was released on 3 September 2001 by Wall of Sound.','https://i.imgur.com/uskA6vv.jpg',8,6,'2020-07-13 12:27:01','2020-07-13 12:27:01'),(79,'Un buen momento','M Clan',1995,'Un buen momento es el primer álbum del grupo español de rock M-Clan. Salió al mercado en el año 1995 y supuso su debut. Fue grabado en Memphis (Estados Unidos), de donde tomaron sus raíces de música sureña. ','https://i.imgur.com/NOUklce.jpg',1,7,'2020-07-13 12:27:01','2020-07-13 12:27:01'),(118,'OK computer','Radiohead',1997,'OK Computer is the third studio album by English rock band Radiohead, released on 21 May 1997 on EMI subsidiaries Parlophone and Capitol Records.','https://i.imgur.com/jD9YA7w.jpg',1,8,'2020-07-24 13:42:15','2020-07-13 12:27:01'),(122,'Defender','Rory Gallagher',1987,'Defender is the thirteenth album and tenth studio album by Irish musician Rory Gallagher. Coming after a five-year hiatus from the recording studio','https://i.imgur.com/vcgaObl.jpg',2,7,'2020-07-24 19:21:56','2020-07-13 12:27:01'),(123,'Songs from the Road','Joanne Shaw Taylor',2013,'Songs from the Road is a live album by British blues rock artist Joanne Shaw Taylor, covering her first three studio albums. It was released on 12 May 2013 on Ruf Records.','https://i.imgur.com/FhWnEJA.jpg',2,7,'2020-07-24 19:26:15','2020-07-13 12:27:01'),(124,'Wagner and Strauss','Lise Davidsen',2019,'In her first recording, featuring songs and arias by Wagner and Richard Strauss, you can hear why one conductor called hers \"a one-in-a-million voice.\"','https://i.imgur.com/p5rLdsW.jpg',6,7,'2020-07-24 19:30:18','2020-07-13 12:27:01'),(125,'Stop the clocks','Oasis',2006,'Stop the Clocks is a compilation album by the English rock band Oasis, released on 20 November 2006. The \"retrospective collection\" is an 18-track double album with the featured songs chosen by Noel Gallagher.','https://i.imgur.com/GpOKTdk.jpg',3,7,'2020-07-24 19:34:08','2020-07-13 12:27:01'),(126,'Ribeira sacra','Luar na lubre',2018,'Disco dedicado a la regiÃ³n de la Ribeira Sacra ourensana, que recoge canciones tradicionales y sonidos propios de la zona, asÃ­ como composiciones propias inspiradas en este territorio.','https://i.imgur.com/uK9rp9A.jpg',4,7,'2020-07-24 19:40:32','2020-07-13 12:27:01');
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genres` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='Music genres.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,'Rock'),(2,'Blues'),(3,'Pop'),(4,'Folk'),(5,'Flamenco'),(6,'Classical'),(7,'Jazz'),(8,'Electronic');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `roles_names` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='Users role table.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (4,'Administrator'),(6,'Listener'),(5,'Moderator');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email` (`email`),
  UNIQUE KEY `users_name` (`name`),
  KEY `fk_roles` (`id_role`),
  CONSTRAINT `fk_roles` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='Users table.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (5,'Admin','admin@root.com','5eac6548943cda81ae05b025615bf192d7df767a7eda3cae958a4216f1116b05',4),(6,'Mod','mod@mod.com','5eac6548943cda81ae05b025615bf192d7df767a7eda3cae958a4216f1116b05',6),(7,'Alpha','alpha@gmail.com','5eac6548943cda81ae05b025615bf192d7df767a7eda3cae958a4216f1116b05',6),(8,'Beta','beta@gmail.com','5eac6548943cda81ae05b025615bf192d7df767a7eda3cae958a4216f1116b05',6),(9,'Gamma','gamma@gmail.com','5eac6548943cda81ae05b025615bf192d7df767a7eda3cae958a4216f1116b05',6),(26,'demo','demo@playzone.cyou','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',6);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `view_user_albums`
--

DROP TABLE IF EXISTS `view_user_albums`;
/*!50001 DROP VIEW IF EXISTS `view_user_albums`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `view_user_albums` (
  `id_user` tinyint NOT NULL,
  `approved_albums` tinyint NOT NULL,
  `pending_albums` tinyint NOT NULL,
  `total` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'audio'
--

--
-- Final view structure for view `view_user_albums`
--

/*!50001 DROP TABLE IF EXISTS `view_user_albums`*/;
/*!50001 DROP VIEW IF EXISTS `view_user_albums`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_user_albums` AS select `a`.`id_user` AS `id_user`,count(`a`.`approved_date`) AS `approved_albums`,sum(`a`.`approved_date` is null) AS `pending_albums`,count(0) AS `total` from `albums` `a` group by `a`.`id_user` */;
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

-- Dump completed on 2020-07-24 20:24:23
