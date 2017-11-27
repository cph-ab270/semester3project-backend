-- MySQL dump 10.13  Distrib 5.6.31, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: cba_sem3project
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`) VALUES (1,'User'),(2,'Admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

--
-- Dumping data for table `place`
--

/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` (`id`, `title`, `description`, `rating`, `image_url`, `latitude`, `longitude`) VALUES (1,'some place','nice',1,'url',50.506144,9.20091);
/*!40000 ALTER TABLE `place` ENABLE KEYS */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `salt`) VALUES (1,'Adam','498686f70928c6f3dfd4e5288bf38b7b0ca789a5e933f6893c5398fbca8452e4','320927329287754881270722666944861037179');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--
-- Dumping data for table `rental`
--

/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
INSERT INTO `rental` (`id`, `title`, `city`, `zip`, `address`, `description`, `rating`, `image_url`, `latitude`, `longitude`) VALUES (1,'title','city','13404','some addr','desc',3,'url',63.506144,9.20091),(2,'Title','CPH','2500','Kjeldsgaardsvej 27C','Great environment',0,'nulldownload.jpg',64.506144,9.20091);
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;

--
-- Dumping data for table `user_role`
--

/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-27 10:39:55
