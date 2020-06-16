-- MariaDB dump 10.17  Distrib 10.5.3-MariaDB, for Win64 (AMD64)
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COMMENT='Albums collection table.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (2,'Love & the death of damnation','The white buffalo',2015,'One of the greatest albums of all time.','https://i.imgur.com/bo3KIgS.jpg'),(3,'Darkest darks, lightest lights','The white buffalo',2024,'One of the greatest albums of all time.','https://i.imgur.com/bo3KIgS.jpg'),(4,'Every picture tells a story','Rod Stewart',1971,'One of the greatest albums of all time.','https://i.imgur.com/bo3KIgS.jpg'),(5,'Every picture tells a story5','Rod Stewart',1971,'One of the greatest albums of all time.','https://i.imgur.com/bo3KIgS.jpg'),(6,'Now\'s the time','Charlie Parker',1945,'One of the greatest albums of all time.','https://i.imgur.com/bo3KIgS.jpg'),(7,'La leyenda del tiempo','Camarón de la isla',1979,'One of the greatest albums of all time.','https://i.imgur.com/bo3KIgS.jpg'),(8,'OK computer','Radiohead',1997,'One of the greatest albums of all time.','https://i.imgur.com/bo3KIgS.jpg'),(9,'On the shoulder of giants','Shawn James',2016,'One of the greatest albums of all time.','https://i.imgur.com/bo3KIgS.jpg'),(10,'No promises','Golden earring',1979,'One of the greatest albums of all time.','https://i.imgur.com/bo3KIgS.jpg');
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_type` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `roles_type` (`role_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='Users role table.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Admin'),(3,'Listener'),(2,'Mod');
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
  `role` int(11) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email` (`email`),
  UNIQUE KEY `users_name` (`name`),
  KEY `fk_roles` (`role`),
  CONSTRAINT `fk_roles` FOREIGN KEY (`role`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='Users table.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Admin','root@admin.com',1,'123456'),(2,'Mod','mod@mod.com',2,'123456'),(3,'Alpha','alpha@mail.com',3,'123456'),(4,'Beta','beta@mail.com',3,'123456');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'audio'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-16 18:17:58
