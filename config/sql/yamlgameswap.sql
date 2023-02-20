-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: yamlgameswap
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book_nft`
--

DROP TABLE IF EXISTS `book_nft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_nft` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userToken` varchar(128) DEFAULT NULL,
  `bookToken` varchar(128) DEFAULT NULL,
  `originRent` smallint(6) NOT NULL,
  `NFTToken` varchar(64) NOT NULL COMMENT 'NFT 合约地址',
  `NFTName` varchar(50) NOT NULL,
  `NFTURL` varchar(200) NOT NULL,
  `NFTPrice` float NOT NULL,
  `NFTOriginNumber` int(11) NOT NULL,
  `isChain` tinyint(1) DEFAULT NULL,
  `chainCode` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='游戏本 nft';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_nft`
--

LOCK TABLES `book_nft` WRITE;
/*!40000 ALTER TABLE `book_nft` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_nft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collect`
--

DROP TABLE IF EXISTS `collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userToken` varchar(128) DEFAULT NULL,
  `bookToken` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收藏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collect`
--

LOCK TABLES `collect` WRITE;
/*!40000 ALTER TABLE `collect` DISABLE KEYS */;
/*!40000 ALTER TABLE `collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userToken` varchar(128) NOT NULL,
  `bookToken` varchar(128) NOT NULL,
  `comment` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danmu`
--

DROP TABLE IF EXISTS `danmu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danmu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userToken` varchar(128) NOT NULL,
  `bookToken` varchar(128) NOT NULL,
  `sectionToken` varchar(128) NOT NULL,
  `comment` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='弹幕';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danmu`
--

LOCK TABLES `danmu` WRITE;
/*!40000 ALTER TABLE `danmu` DISABLE KEYS */;
/*!40000 ALTER TABLE `danmu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_book`
--

DROP TABLE IF EXISTS `game_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookTitle` varchar(50) NOT NULL,
  `bookSummary` varchar(500) NOT NULL,
  `userToken` varchar(128) NOT NULL,
  `bookToken` varchar(128) NOT NULL,
  `bookType` smallint(6) NOT NULL,
  `bookUrl` varchar(500) NOT NULL,
  `engineCode` smallint(6) NOT NULL,
  `bookStatus` smallint(6) NOT NULL,
  `StatusMsg` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='游戏本';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_book`
--

LOCK TABLES `game_book` WRITE;
/*!40000 ALTER TABLE `game_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `game_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_section`
--

DROP TABLE IF EXISTS `game_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookToken` varchar(128) NOT NULL,
  `sectionToken` varchar(128) NOT NULL,
  `order` int(11) NOT NULL,
  `sectionContent` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `game_section` (`bookToken`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='游戏本章节';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_section`
--

LOCK TABLES `game_section` WRITE;
/*!40000 ALTER TABLE `game_section` DISABLE KEYS */;
/*!40000 ALTER TABLE `game_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userToken` varchar(128) DEFAULT NULL,
  `bookToken` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='点赞';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--

LOCK TABLES `like` WRITE;
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
/*!40000 ALTER TABLE `like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper_nft`
--

DROP TABLE IF EXISTS `paper_nft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paper_nft` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userToken` varchar(128) NOT NULL,
  `offlineNumber` int(11) DEFAULT NULL,
  `actionCode` int(11) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='纸张NFT';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper_nft`
--

LOCK TABLES `paper_nft` WRITE;
/*!40000 ALTER TABLE `paper_nft` DISABLE KEYS */;
/*!40000 ALTER TABLE `paper_nft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(20) DEFAULT NULL,
  `userAddress` varchar(42) DEFAULT NULL,
  `userToken` varchar(128) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_token` (`userToken`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-20 15:50:43
