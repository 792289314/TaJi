-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: TaJi
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `classify`
--

DROP TABLE IF EXISTS `classify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classify` (
  `uid` int DEFAULT NULL,
  `cid` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(30) NOT NULL,
  `cflag` tinyint(1) NOT NULL,
  `ccolor` varchar(10) NOT NULL,
  PRIMARY KEY (`cid`),
  KEY `classify_user_uid_fk` (`uid`),
  CONSTRAINT `classify_user_uid_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classify`
--

LOCK TABLES `classify` WRITE;
/*!40000 ALTER TABLE `classify` DISABLE KEYS */;
INSERT INTO `classify` (`uid`, `cid`, `cname`, `cflag`, `ccolor`) VALUES (9,1,'未分类',0,'#239469'),(9,2,'学习',0,'#89A5F9'),(9,11,'富婆计划',0,'#F1F51B'),(9,12,'张雨剑老公！',1,'#729BF4'),(9,13,'毕业后应该干什么呢？',0,'#FF6A00'),(9,15,'工程完结倒计时',0,'#7B75F1'),(12,28,'未分类',0,'#239469'),(13,29,'未分类',0,'#239469');
/*!40000 ALTER TABLE `classify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diary`
--

DROP TABLE IF EXISTS `diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diary` (
  `uid` int DEFAULT NULL,
  `cid` int DEFAULT NULL,
  `did` int NOT NULL AUTO_INCREMENT,
  `dflag` int DEFAULT NULL,
  `dtext` text,
  `dtime` datetime DEFAULT NULL,
  `dweather` int DEFAULT NULL,
  PRIMARY KEY (`did`),
  KEY `diary_classify_cid_fk` (`cid`),
  KEY `diary_user_uid_fk` (`uid`),
  CONSTRAINT `diary_classify_cid_fk` FOREIGN KEY (`cid`) REFERENCES `classify` (`cid`) ON DELETE CASCADE,
  CONSTRAINT `diary_user_uid_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diary`
--

LOCK TABLES `diary` WRITE;
/*!40000 ALTER TABLE `diary` DISABLE KEYS */;
INSERT INTO `diary` (`uid`, `cid`, `did`, `dflag`, `dtext`, `dtime`, `dweather`) VALUES (9,1,1,0,'这是一个未分类的日记','2020-12-12 20:45:45',1),(9,1,2,0,'这也是一个未分类的日记','2020-12-12 20:46:06',2),(9,1,3,0,'这同样也是一个未分类的日记','2020-12-12 20:46:27',0),(9,2,4,0,'今天我有好好学习吗','2020-12-16 09:57:10',1),(9,2,5,0,'没有','2020-12-16 09:57:30',2),(9,1,6,0,'2020年12月16日 今天的我很开心','2020-12-16 10:02:24',1),(9,1,7,1,'clion天下无敌！！！','2020-12-16 10:02:57',0),(9,1,12,1,'中文测试','2020-12-24 01:58:25',0),(9,1,13,1,'中文测试？','2020-12-24 02:07:48',0),(9,1,14,1,'去掉了axios里的中文转码，现在来试试看功能','2020-12-24 02:09:53',0),(9,2,15,1,'为什么总有bug的存在？难过','2020-12-24 02:12:06',1),(9,1,17,1,'测试添加日记的刷新功能','2020-12-24 03:02:10',0),(9,1,19,1,'我又来测试啦！','2020-12-24 05:39:03',0),(9,1,20,1,'晚上7点 我还在努力！','2020-12-24 05:46:12',0),(9,1,21,1,'而我的咖啡不用太多，我的钱包不够','2020-12-24 05:59:28',0),(9,1,22,0,'退役啦！修改一下','2020-12-27 07:08:00',0),(9,1,23,0,'你看得到这篇日记吗','2020-12-28 02:04:08',0),(9,2,24,0,'接到我的弟弟啦','2020-12-31 03:43:53',1),(9,11,25,0,'更新了时间线','2020-12-31 07:06:02',0),(9,15,26,0,'修改了部分bug\n','2020-12-31 08:34:24',0),(9,13,27,0,'找工作','2020-12-31 08:40:30',0),(9,1,28,0,'今天 几号？','2021-01-01 09:31:57',0),(9,1,29,1,'哈哈哈','2021-01-01 10:05:42',2),(9,1,33,1,'lalalalal\n','2021-01-02 03:20:13',0),(9,11,34,1,'','2021-01-02 03:24:31',0),(9,11,35,0,'添加文件上传功能！','2021-01-05 07:08:30',0),(12,28,37,0,'欢迎来到她记大本营！','2021-01-05 08:43:02',0),(9,11,40,0,'恭喜完结！','2021-01-05 08:54:59',0),(9,11,41,0,'恭喜完结！','2021-01-05 08:55:12',0),(9,13,42,0,'再试试一张图','2021-01-05 09:18:14',0),(9,13,43,0,'再试试一张图','2021-01-05 09:18:44',0),(9,15,44,0,'撒花！！！！','2021-01-05 09:21:18',0),(9,13,45,0,'woc 又有bug','2021-01-05 09:34:20',1),(9,1,46,1,'给了xml后的测试','2021-01-05 09:48:00',0),(9,1,47,1,'','2021-01-05 09:49:20',0),(9,12,48,1,'气死我了(;´༎ຶД༎ຶ`)','2021-01-05 09:51:56',0),(9,2,49,1,'test1','2021-01-05 10:55:32',0),(9,1,50,1,'test2','2021-01-05 10:59:29',0),(9,15,51,1,'QAQtest3','2021-01-05 11:05:57',1),(9,2,52,0,'多组图片测试','2021-01-05 11:19:39',0),(9,11,53,0,'困死我了','2021-01-05 12:06:24',2),(9,15,54,0,'.zZ','2021-01-05 12:09:40',1),(9,1,55,0,'修改','2021-01-05 18:22:57',1);
/*!40000 ALTER TABLE `diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `idir` varchar(100) DEFAULT NULL,
  `did` int DEFAULT NULL,
  KEY `image_diary_did_fk` (`did`),
  CONSTRAINT `image_diary_did_fk` FOREIGN KEY (`did`) REFERENCES `diary` (`did`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` (`idir`, `did`) VALUES ('/Users/mac/Desktop/TaJi/web/Img/WedJan06005928CST2021_0.jpg',50),('/Users/mac/Desktop/TaJi/web/Img/WedJan06010556CST2021_0.jpg',51),('/Users/mac/Desktop/TaJi/web/Img/WedJan06011938CST2021_0.jpg',52),('/Users/mac/Desktop/TaJi/web/Img/WedJan06011938CST2021_1.jpg',52),('/Users/mac/Desktop/TaJi/web/Img/WedJan06020623CST2021_0.jpg',53),('/Users/mac/Desktop/TaJi/web/Img/WedJan06020623CST2021_1.jpg',53),('/Users/mac/Desktop/TaJi/web/Img/WedJan06020939CST2021_0.jpg',54),('/Users/mac/Desktop/TaJi/web/Img/WedJan06020939CST2021_1.jpg',54),('/Users/mac/Desktop/TaJi/web/Img/WedJan06082257CST2021_0.jpg',55),('/Users/mac/Desktop/TaJi/web/Img/WedJan06082257CST2021_1.jpg',55);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(50) NOT NULL,
  `upassword` varchar(20) NOT NULL,
  `uemail` varchar(30) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `user_uid_uindex` (`uid`),
  UNIQUE KEY `user_uemail_uindex` (`uemail`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`uid`, `uname`, `upassword`, `uemail`) VALUES (6,'123','123','123@123.com'),(7,'123456','123456','123456@123.com'),(8,'111','111','111@111.com'),(9,'zaller12','zaller12','zaller12@123.com'),(10,'zaller13','aller13','zaller13@123.com'),(11,'zaller14','zaller14','zaller14@123.com'),(12,'zaller15','zaller15','zaller15@123.com'),(13,'zaller16','zaller116','zaller16@123.com');
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

-- Dump completed on 2021-01-06 11:07:46
