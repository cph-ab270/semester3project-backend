-- MySQL dump 10.13  Distrib 5.6.31, for osx10.6 (i386)
--
-- Host: 207.154.217.117    Database: cba_sem3project
-- ------------------------------------------------------
-- Server version	5.6.26

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
-- Dumping data for table `rating`
--

/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` (`id`, `user_id`, `rental_id`, `rating`) VALUES (5,3,4,4),(6,3,5,5);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;

--
-- Dumping data for table `rental`
--

/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
INSERT INTO `rental` (`id`, `title`, `city`, `zip`, `address`, `description`, `image_url`, `latitude`, `longitude`) VALUES (4,'Mountain View','Mountain City','8221','mt avenue','The Calabash Mountain Villa is a St. Lucia Villa built in a lush mountain setting. We want to become your vacation “home away from home.”  Join us on an adventure of the unfamiliar and appreciate the magnitude and beauty that surrounds us.','http://hyggeadam.cz:5001/img/08f98f1a-45d9-4f79-b223-4ab912660119.c10.jpg',40,50),(5,'Sea View Villa','Algarve','1212','Rua do Sol','Grande Estraded onde podes ir e ver a grandes raparigas a anders nus na rua. ','http://hyggeadam.cz:5001/img/pic.jpg',63,-9),(6,'City View','Beverly Hills','7777','Sunset Boulevard','Beverly Hills is a city in California\'s Los Angeles County. Home to many Hollywood stars, it features the upscale shopping street of Rodeo Drive. The expansive Beverly Gardens Park has fountains and rose gardens, plus an illuminated Beverly Hills sign. The 1920s Greystone Mansion is the backdrop for many films. Known as a celebrity haunt, The Beverly Hills Hotel is set in tropical gardens and has a poolside cafe.','http://hyggeadam.cz:5001/img/la-fi-hotprop-jackie-collins-beverly-hills-hom-001.jpg',63,9),(7,'Cali View','San Diego','3333','San Antonio street 12','Welcome to the Redondo Beach Luxury villa, a gated home boasting 7,800 square feet with coastline views. Sitting high on the hillside and completely remodeled, this is one of our must-see California vacation rentals. Located just 20 miles from downtown Los Angeles, Redondo Beach’s magnificent views and sandy beaches have made it a favorite resort destination for well over a century.','http://hyggeadam.cz:5001/img/download.jpg',23,11),(8,'London View','London','7211','Oxford Street','LONG LET. Situated on the ground floor of a picturesque period conversion, this spacious one bedroom apartment benefits from a bright and spacious interior and a private garden.  Hither Green Lane is an ideal location within a close proximity to excellent transport links into central London, including Hither Green Station and the South Circular road.','http://hyggeadam.cz:5001/img/londong.jpg',88,12);
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;

--
-- Dumping data for table `role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`) VALUES (1,'User'),(2,'Admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `salt`) VALUES (1,'User','498686f70928c6f3dfd4e5288bf38b7b0ca789a5e933f6893c5398fbca8452e4','320927329287754881270722666944861037179'),(2,'Admin','498686f70928c6f3dfd4e5288bf38b7b0ca789a5e933f6893c5398fbca8452e4','320927329287754881270722666944861037179'),(3,'pedro','f843c47ccf3610a6e96408adefefb235e896641ce08449e3bc63d97f370f5dc3','jHkuCHSFXYiavDGATY154WTisG5QOeiW6i8BWxhlsCAzNeJW0fS1S8n6vkfssqzo+1ZhsVgvGHqAXG5AbPPnYaDf1naxTj2kmm9WzAV402Ca+C2uR1BehE5LkM2S4q+wQ91F/MBRnjvOGNy4iV0w14SUUtgj3gFYoSsywu4JQR4=');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--
-- Dumping data for table `location`
--

/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` (`id`, `title`, `description`, `image_url`, `latitude`, `longitude`) VALUES (4,'Mountain View','Great view you can see when passing by','http://hyggeadam.cz:5001/img/7l0yxMF.jpg',40,50),(5,'Sea View','Great Beach','http://hyggeadam.cz:5001/img/lA4X8zc.jpg',63,9),(6,'Praia da Rocha','Praia da Rocha is one of the Algarve\'s most popular resort towns and offers a diverse selection of entertainment, sights and activities suitable for all ages. The town is set above one of the finest beaches of the Algarve, a vast expanse of soft golden sands which is lapped by the crystal clear (but shockingly cold) sea waters.','http://hyggeadam.cz:5001/img/praia-da-rocha-beach-5.jpg',63,-9),(7,'Mountain View','Great View','http://hyggeadam.cz:5001/img/7l0yxMF.jpg',12,1),(8,'Mountain View','Greate View','http://hyggeadam.cz:5001/img/7l0yxMF.jpg',12,1);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;

--
-- Dumping data for table `user_role`
--

/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1,1),(2,2),(3,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-29 16:13:42
