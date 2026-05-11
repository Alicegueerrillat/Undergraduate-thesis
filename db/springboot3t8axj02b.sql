-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: springboot3t8axj02b
-- ------------------------------------------------------
-- Server version	5.7.31

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
-- Current Database: `springboot3t8axj02b`
--

/*!40000 DROP DATABASE IF EXISTS `springboot3t8axj02b`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `springboot3t8axj02b` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `springboot3t8axj02b`;

--
-- Table structure for table `banji`
--

DROP TABLE IF EXISTS `banji`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banji` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `banji` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`id`),
  UNIQUE KEY `banji` (`banji`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='班级';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banji`
--

LOCK TABLES `banji` WRITE;
/*!40000 ALTER TABLE `banji` DISABLE KEYS */;
INSERT INTO `banji` VALUES (1,'2025-12-26 08:20:38','一班'),(2,'2025-12-26 08:20:38','四班'),(3,'2025-12-26 08:20:38','九班'),(4,'2025-12-26 08:20:38','七班'),(5,'2025-12-26 08:20:38','六班'),(6,'2025-12-26 08:20:38','二班'),(7,'2025-12-26 08:20:38','十班'),(8,'2025-12-26 08:20:38','八班');
/*!40000 ALTER TABLE `banji` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `adminid` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `ask` longtext COLLATE utf8mb4_unicode_ci COMMENT '提问',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复',
  `isreply` int(11) DEFAULT NULL COMMENT '是否回复',
  `isread` int(11) DEFAULT '0' COMMENT '已读/未读(1:已读,0:未读)',
  `uname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `uimage` longtext COLLATE utf8mb4_unicode_ci COMMENT '用户头像',
  `type` int(11) DEFAULT '1' COMMENT '内容类型(1:文本,2:图片,3:视频,4:文件,5:表情)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI助手';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,'2025-12-26 08:20:38',1,1,'提问1','回复1',1,1,'用户名1','upload/chat_uimage1.jpg,upload/chat_uimage2.jpg,upload/chat_uimage3.jpg',1),(2,'2025-12-26 08:20:38',2,2,'提问2','回复2',2,2,'用户名2','upload/chat_uimage2.jpg,upload/chat_uimage3.jpg,upload/chat_uimage4.jpg',2),(3,'2025-12-26 08:20:38',3,3,'提问3','回复3',3,3,'用户名3','upload/chat_uimage3.jpg,upload/chat_uimage4.jpg,upload/chat_uimage5.jpg',3),(4,'2025-12-26 08:20:38',4,4,'提问4','回复4',4,4,'用户名4','upload/chat_uimage4.jpg,upload/chat_uimage5.jpg,upload/chat_uimage6.jpg',4),(5,'2025-12-26 08:20:38',5,5,'提问5','回复5',5,5,'用户名5','upload/chat_uimage5.jpg,upload/chat_uimage6.jpg,upload/chat_uimage7.jpg',5),(6,'2025-12-26 08:20:38',6,6,'提问6','回复6',6,6,'用户名6','upload/chat_uimage6.jpg,upload/chat_uimage7.jpg,upload/chat_uimage8.jpg',6),(7,'2025-12-26 08:20:38',7,7,'提问7','回复7',7,7,'用户名7','upload/chat_uimage7.jpg,upload/chat_uimage8.jpg,upload/chat_uimage1.jpg',7),(8,'2025-12-26 08:20:38',8,8,'提问8','回复8',8,8,'用户名8','upload/chat_uimage8.jpg,upload/chat_uimage1.jpg,upload/chat_uimage2.jpg',8);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chengjixinxi`
--

DROP TABLE IF EXISTS `chengjixinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chengjixinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengleixing` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程类型',
  `kechengfengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '课程封面',
  `jiaoshizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师账号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  `dengjiriqi` date DEFAULT NULL COMMENT '登记日期',
  `xueshengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生账号',
  `xueshengxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生姓名',
  `chengji` double DEFAULT NULL COMMENT '成绩',
  `ketangbiaoxian` longtext COLLATE utf8mb4_unicode_ci COMMENT '课堂表现',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='成绩信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chengjixinxi`
--

LOCK TABLES `chengjixinxi` WRITE;
/*!40000 ALTER TABLE `chengjixinxi` DISABLE KEYS */;
INSERT INTO `chengjixinxi` VALUES (1,'2025-12-26 08:20:38','美术','选修','upload/chengjixinxi_美术1.jpg,upload/chengjixinxi_美术2.jpg,upload/chengjixinxi_美术3.jpg','3008','罗琳','一班','2025-12-26','102','王静',1,'您看到的编辑框内默认提示可作为创作起点，支持对现有内容进行全面修改，包括删除冗余文字、替换表述方式等；插入图片时，既可以从本地上传，也能粘贴网络图片地址，图片插入后会直接在文本中展示，完成后点击空白处即可保存设置。​'),(2,'2025-12-26 08:20:38','历史','文科必修','upload/chengjixinxi_历史1.jpg,upload/chengjixinxi_历史2.jpg,upload/chengjixinxi_历史3.jpg','3006','周杰','四班','2025-12-26','108','赵敏',2,'此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​'),(3,'2025-12-26 08:20:38','生物（选修1）','理科选修','upload/chengjixinxi_生物（选修1）1.jpg,upload/chengjixinxi_生物（选修1）2.jpg,upload/chengjixinxi_生物（选修1）3.jpg','3003','朱琳','九班','2025-12-26','104','陈明',3,'此处文本编辑框是内容详细的操作提示，您可以对现有文字进行修改，包括删除多余文字、替换不合适的表述等；插入图片时，既可以从本地选择图片上传，图片插入后会直接展示在编辑框中，完成操作后点击空白区域即可保存。'),(4,'2025-12-26 08:20:38','语文','文科必修','upload/chengjixinxi_语文1.jpg,upload/chengjixinxi_语文2.jpg,upload/chengjixinxi_语文3.jpg','3005','胡伟','七班','2025-12-26','101','刘强',4,'此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​'),(5,'2025-12-26 08:20:38','化学（选修4）','理科选修','upload/chengjixinxi_化学（选修4）1.jpg,upload/chengjixinxi_化学（选修4）2.jpg,upload/chengjixinxi_化学（选修4）3.jpg','3002','刘强','六班','2025-12-26','105','赵芳',5,'此编辑框内已详情的内容，您可以直接对现有文字进行修改，无论是调整文字内容、更正表述还是增减文字数量都能实现；若需插入图片，只需点击上方工具栏的图片图标，选择本地文件或输入图片链接即可插入展示，所有操作完成后提交后系统会保存您的修改。​'),(6,'2025-12-26 08:20:38','体育与健康','必修','upload/chengjixinxi_体育与健康1.jpg,upload/chengjixinxi_体育与健康2.jpg,upload/chengjixinxi_体育与健康3.jpg','3004','黄军','二班','2025-12-26','106','王明',6,'编辑框中展示的默认内容仅为示例，您可任意修改其中的文字表述，比如更换词语、调整语句顺序等；当需要插入图片时，点击编辑框上方的 “插入图片” 按钮，上传图片后即可直接在编辑框中展示。'),(7,'2025-12-26 08:20:38','物理（选修3-1）','理科选修','upload/chengjixinxi_物理（选修3-1）1.jpg,upload/chengjixinxi_物理（选修3-1）2.jpg,upload/chengjixinxi_物理（选修3-1）3.jpg','3007','徐敏','十班','2025-12-26','103','刘敏',7,'此编辑框内的默认内容为可编辑状态，您可对文字进行修改，比如调整语句顺序、更换文字内容等；插入图片时，支持上传图片，上传后图片会直接在编辑框中展示，也能一键删除不需要的图片。'),(8,'2025-12-26 08:20:38','思想政治','必修','upload/chengjixinxi_思想政治1.jpg,upload/chengjixinxi_思想政治2.jpg,upload/chengjixinxi_思想政治3.jpg','3001','宋琳','八班','2025-12-26','107','李娜与',8,'此处文本编辑框是内容详细的操作提示，您可对文字进行各类修改，比如调整语句先后顺序、更换文字内容等；插入图片时，支持同时上传多张图片，上传完成后图片会直接在编辑框中展示，不需要的图片可以一键删除。');
/*!40000 ALTER TABLE `chengjixinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(200) DEFAULT NULL COMMENT '配置参数值',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  `type` int(11) DEFAULT NULL COMMENT '参数类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='配置文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'picture1','upload/picture1.jpg',NULL,1),(2,'picture2','upload/picture2.jpg',NULL,1),(3,'picture3','upload/picture3.jpg',NULL,1),(7,'APIKey','ihML0NfEM0jokUAuBD9tbYtW',NULL,2),(8,'SecretKey','WK2bRUFf2uNe3oEoQXULpi8Y37aCvFdD',NULL,2),(10,'gaode','{\"key\":\"7a7b5d185ffd9ca30f582ff9349716ea\",\"code\":\"8848c1c64023c00cf192ce109b97243c\"}',NULL,2),(11,'baidu','{\"appId\":\"49214550\",\"apiKey\":\"7Otjpv2kn0ljQk45qXOXh5MO\",\"secretKey\":\"BMfbXRbTIVaB4C3SbRTtGqDv1wHDvyXS\"}',NULL,2),(12,'qianfan','{\"accessKey\":\"9dd9b2ea56b042c6a468bfeada1209a3\",\"accessSecretKey\":\"45a0aa98b86b4c54b9dc8ede3cc67274\"}',NULL,2),(21,'bLoginBackgroundImg','',NULL,3),(22,'bRegisterBackgroundImg','',NULL,3),(23,'bIndexBackgroundImg','',NULL,3),(24,'bTopLogo','',NULL,3),(25,'bHomeLogo','',NULL,3),(26,'fLoginBackgroundImg','',NULL,3),(27,'fRegisterBackgroudImg','',NULL,3),(28,'fTopLogo','',NULL,3);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiaoshi`
--

DROP TABLE IF EXISTS `jiaoshi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiaoshi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaoshizhanghao` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '教师账号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `jiaoshixingming` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '教师姓名',
  `xingbie` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `lianxifangshi` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系方式',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`id`),
  UNIQUE KEY `jiaoshizhanghao` (`jiaoshizhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='教师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiaoshi`
--

LOCK TABLES `jiaoshi` WRITE;
/*!40000 ALTER TABLE `jiaoshi` DISABLE KEYS */;
INSERT INTO `jiaoshi` VALUES (21,'2025-12-26 08:20:38','3008','e10adc3949ba59abbe56e057f20f883e','罗琳','男','13187654321','upload/jiaoshi_touxiang1.jpg','一班'),(22,'2025-12-26 08:20:38','3006','e10adc3949ba59abbe56e057f20f883e','周杰','女','13678901234','upload/jiaoshi_touxiang2.jpg','四班'),(23,'2025-12-26 08:20:38','3003','e10adc3949ba59abbe56e057f20f883e','朱琳','男','13654321098','upload/jiaoshi_touxiang3.jpg','九班'),(24,'2025-12-26 08:20:38','3005','e10adc3949ba59abbe56e057f20f883e','胡伟','女','13987654321','upload/jiaoshi_touxiang4.jpg','七班'),(25,'2025-12-26 08:20:38','3002','e10adc3949ba59abbe56e057f20f883e','刘强','女','13890123456','upload/jiaoshi_touxiang5.jpg','六班'),(26,'2025-12-26 08:20:38','3004','e10adc3949ba59abbe56e057f20f883e','黄军','男','15212345678','upload/jiaoshi_touxiang6.jpg','二班'),(27,'2025-12-26 08:20:38','3007','e10adc3949ba59abbe56e057f20f883e','徐敏','男','15187654321','upload/jiaoshi_touxiang7.jpg','十班'),(28,'2025-12-26 08:20:38','3001','e10adc3949ba59abbe56e057f20f883e','宋琳','男','13012345678','upload/jiaoshi_touxiang8.jpg','八班');
/*!40000 ALTER TABLE `jiaoshi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiaoxuetongzhi`
--

DROP TABLE IF EXISTS `jiaoxuetongzhi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiaoxuetongzhi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `biaoti` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `fengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '封面',
  `tongzhishijian` date DEFAULT NULL COMMENT '通知时间',
  `tongzhineirong` longtext COLLATE utf8mb4_unicode_ci COMMENT '通知内容',
  `jiaoshizhanghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师账号',
  `jiaoshixingming` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='教学通知';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiaoxuetongzhi`
--

LOCK TABLES `jiaoxuetongzhi` WRITE;
/*!40000 ALTER TABLE `jiaoxuetongzhi` DISABLE KEYS */;
INSERT INTO `jiaoxuetongzhi` VALUES (1,'2025-12-26 08:20:38','关于调整XX课程上课时间的通知','upload/jiaoxuetongzhi_美术1.jpg,upload/jiaoxuetongzhi_美术2.jpg,upload/jiaoxuetongzhi_美术3.jpg','2025-12-26','各专业学生：本学期《计算机基础》课程实践环节安排在第12-14周，实践地点为实训楼201机房，请同学们携带学生证按时参加，不得无故缺席','3008','罗琳','一班'),(2,'2025-12-26 08:20:38','关于辅修课程报名截止的通知','upload/jiaoxuetongzhi_历史1.jpg,upload/jiaoxuetongzhi_历史2.jpg,upload/jiaoxuetongzhi_历史3.jpg','2025-12-26','各班级：为优化教学安排，本学期《大学英语》课程上课时间调整为每周三下午第3-4节，上课地点不变，请同学们相互转告','3006','周杰','四班'),(3,'2025-12-26 08:20:38','关于本学期期末课程考核安排的通知','upload/jiaoxuetongzhi_生物（选修1）1.jpg,upload/jiaoxuetongzhi_生物（选修1）2.jpg,upload/jiaoxuetongzhi_生物（选修1）3.jpg','2025-12-26','教务处通知：课程学分认定工作将于11月启动，学生需提交相关课程成绩单及证明材料至学院教务办，经审核通过后方可认定相应学分','3003','朱琳','九班'),(4,'2025-12-26 08:20:38','关于课程调整的通知','upload/jiaoxuetongzhi_语文1.jpg,upload/jiaoxuetongzhi_语文2.jpg,upload/jiaoxuetongzhi_语文3.jpg','2025-12-26','跨校区选课学生：本学期《中国近代史纲要》课程上课地点为东校区文史楼101教室，学校将安排通勤班车往返两校区，具体发车时间详见后勤处通知','3005','胡伟','七班'),(5,'2025-12-26 08:20:38','关于课程答疑安排的通知','upload/jiaoxuetongzhi_化学（选修4）1.jpg,upload/jiaoxuetongzhi_化学（选修4）2.jpg,upload/jiaoxuetongzhi_化学（选修4）3.jpg','2025-12-26','各教学团队：为落实新版人才培养方案，现启用新版课程教学大纲，要求各教师严格按照新大纲组织教学，并于10月20日前完成教学计划的修订工作','3002','刘强','六班'),(6,'2025-12-26 08:20:38','关于课程论文提交要求的通知','upload/jiaoxuetongzhi_体育与健康1.jpg,upload/jiaoxuetongzhi_体育与健康2.jpg,upload/jiaoxuetongzhi_体育与健康3.jpg','2025-12-26','教务处通知：课程补修报名工作即将启动，补修报名时间为11月5日至11月10日，符合补修条件的学生需登录教务管理系统完成报名，逾期不予受理','3004','黄军','二班'),(7,'2025-12-26 08:20:38','关于跨校区选课课程上课地点的通知','upload/jiaoxuetongzhi_物理（选修3-1）1.jpg,upload/jiaoxuetongzhi_物理（选修3-1）2.jpg,upload/jiaoxuetongzhi_物理（选修3-1）3.jpg','2025-12-26','各年级同学：辅修课程报名工作已进入尾声，报名截止时间为10月15日，有意向修读辅修专业的同学请尽快登录教务系统完成报名，切勿错过时间','3007','徐敏','十班'),(8,'2025-12-26 08:20:38','关于组织课程教学质量评价的通知','upload/jiaoxuetongzhi_思想政治1.jpg,upload/jiaoxuetongzhi_思想政治2.jpg,upload/jiaoxuetongzhi_思想政治3.jpg','2025-12-26','各班级：本学期《高等数学》课程恢复线下教学，上课时间及地点不变，返校上课的同学需遵守课堂纪律，配合做好考勤工作','3001','宋琳','八班');
/*!40000 ALTER TABLE `jiaoxuetongzhi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaoqinshensu`
--

DROP TABLE IF EXISTS `kaoqinshensu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaoqinshensu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengleixing` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程类型',
  `kechengfengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '课程封面',
  `shensushijian` date DEFAULT NULL COMMENT '申诉时间',
  `jiaoshizhanghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师账号',
  `jiaoshixingming` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  `xueshengzhanghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生账号',
  `xueshengxingming` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生姓名',
  `shensuliyou` longtext COLLATE utf8mb4_unicode_ci COMMENT '申诉理由',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='考勤申诉';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaoqinshensu`
--

LOCK TABLES `kaoqinshensu` WRITE;
/*!40000 ALTER TABLE `kaoqinshensu` DISABLE KEYS */;
INSERT INTO `kaoqinshensu` VALUES (1,'2025-12-26 08:20:38','美术','选修','upload/kaoqinshensu_美术1.jpg,upload/kaoqinshensu_美术2.jpg,upload/kaoqinshensu_美术3.jpg','2025-12-26','3008','罗琳','一班','102','王静','您看到的编辑框内默认提示可作为创作起点，支持对现有内容进行全面修改，包括删除冗余文字、替换表述方式等；插入图片时，既可以从本地上传，也能粘贴网络图片地址，图片插入后会直接在文本中展示，完成后点击空白处即可保存设置。​','是',''),(2,'2025-12-26 08:20:38','历史','文科必修','upload/kaoqinshensu_历史1.jpg,upload/kaoqinshensu_历史2.jpg,upload/kaoqinshensu_历史3.jpg','2025-12-26','3006','周杰','四班','108','赵敏','此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​','是',''),(3,'2025-12-26 08:20:38','生物（选修1）','理科选修','upload/kaoqinshensu_生物（选修1）1.jpg,upload/kaoqinshensu_生物（选修1）2.jpg,upload/kaoqinshensu_生物（选修1）3.jpg','2025-12-26','3003','朱琳','九班','104','陈明','此处文本编辑框是内容详细的操作提示，您可以对现有文字进行修改，包括删除多余文字、替换不合适的表述等；插入图片时，既可以从本地选择图片上传，图片插入后会直接展示在编辑框中，完成操作后点击空白区域即可保存。','是',''),(4,'2025-12-26 08:20:38','语文','文科必修','upload/kaoqinshensu_语文1.jpg,upload/kaoqinshensu_语文2.jpg,upload/kaoqinshensu_语文3.jpg','2025-12-26','3005','胡伟','七班','101','刘强','此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​','是',''),(5,'2025-12-26 08:20:38','化学（选修4）','理科选修','upload/kaoqinshensu_化学（选修4）1.jpg,upload/kaoqinshensu_化学（选修4）2.jpg,upload/kaoqinshensu_化学（选修4）3.jpg','2025-12-26','3002','刘强','六班','105','赵芳','此编辑框内已详情的内容，您可以直接对现有文字进行修改，无论是调整文字内容、更正表述还是增减文字数量都能实现；若需插入图片，只需点击上方工具栏的图片图标，选择本地文件或输入图片链接即可插入展示，所有操作完成后提交后系统会保存您的修改。​','是',''),(6,'2025-12-26 08:20:38','体育与健康','必修','upload/kaoqinshensu_体育与健康1.jpg,upload/kaoqinshensu_体育与健康2.jpg,upload/kaoqinshensu_体育与健康3.jpg','2025-12-26','3004','黄军','二班','106','王明','编辑框中展示的默认内容仅为示例，您可任意修改其中的文字表述，比如更换词语、调整语句顺序等；当需要插入图片时，点击编辑框上方的 “插入图片” 按钮，上传图片后即可直接在编辑框中展示。','是',''),(7,'2025-12-26 08:20:38','物理（选修3-1）','理科选修','upload/kaoqinshensu_物理（选修3-1）1.jpg,upload/kaoqinshensu_物理（选修3-1）2.jpg,upload/kaoqinshensu_物理（选修3-1）3.jpg','2025-12-26','3007','徐敏','十班','103','刘敏','此编辑框内的默认内容为可编辑状态，您可对文字进行修改，比如调整语句顺序、更换文字内容等；插入图片时，支持上传图片，上传后图片会直接在编辑框中展示，也能一键删除不需要的图片。','是',''),(8,'2025-12-26 08:20:38','思想政治','必修','upload/kaoqinshensu_思想政治1.jpg,upload/kaoqinshensu_思想政治2.jpg,upload/kaoqinshensu_思想政治3.jpg','2025-12-26','3001','宋琳','八班','107','李娜与','此处文本编辑框是内容详细的操作提示，您可对文字进行各类修改，比如调整语句先后顺序、更换文字内容等；插入图片时，支持同时上传多张图片，上传完成后图片会直接在编辑框中展示，不需要的图片可以一键删除。','是','');
/*!40000 ALTER TABLE `kaoqinshensu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaoqinyujing`
--

DROP TABLE IF EXISTS `kaoqinyujing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaoqinyujing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengleixing` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程类型',
  `kechengfengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '课程封面',
  `yujingshijian` date DEFAULT NULL COMMENT '预警时间',
  `jiaoshizhanghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师账号',
  `jiaoshixingming` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  `xueshengzhanghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生账号',
  `xueshengxingming` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生姓名',
  `kaoqinyujing` longtext COLLATE utf8mb4_unicode_ci COMMENT '考勤预警',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='考勤预警';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaoqinyujing`
--

LOCK TABLES `kaoqinyujing` WRITE;
/*!40000 ALTER TABLE `kaoqinyujing` DISABLE KEYS */;
INSERT INTO `kaoqinyujing` VALUES (1,'2025-12-26 08:20:38','美术','选修','upload/kaoqinyujing_美术1.jpg,upload/kaoqinyujing_美术2.jpg,upload/kaoqinyujing_美术3.jpg','2025-12-26','3008','罗琳','一班','102','王静','您看到的编辑框内默认提示可作为创作起点，支持对现有内容进行全面修改，包括删除冗余文字、替换表述方式等；插入图片时，既可以从本地上传，也能粘贴网络图片地址，图片插入后会直接在文本中展示，完成后点击空白处即可保存设置。​'),(2,'2025-12-26 08:20:38','历史','文科必修','upload/kaoqinyujing_历史1.jpg,upload/kaoqinyujing_历史2.jpg,upload/kaoqinyujing_历史3.jpg','2025-12-26','3006','周杰','四班','108','赵敏','此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​'),(3,'2025-12-26 08:20:38','生物（选修1）','理科选修','upload/kaoqinyujing_生物（选修1）1.jpg,upload/kaoqinyujing_生物（选修1）2.jpg,upload/kaoqinyujing_生物（选修1）3.jpg','2025-12-26','3003','朱琳','九班','104','陈明','此处文本编辑框是内容详细的操作提示，您可以对现有文字进行修改，包括删除多余文字、替换不合适的表述等；插入图片时，既可以从本地选择图片上传，图片插入后会直接展示在编辑框中，完成操作后点击空白区域即可保存。'),(4,'2025-12-26 08:20:38','语文','文科必修','upload/kaoqinyujing_语文1.jpg,upload/kaoqinyujing_语文2.jpg,upload/kaoqinyujing_语文3.jpg','2025-12-26','3005','胡伟','七班','101','刘强','此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​'),(5,'2025-12-26 08:20:38','化学（选修4）','理科选修','upload/kaoqinyujing_化学（选修4）1.jpg,upload/kaoqinyujing_化学（选修4）2.jpg,upload/kaoqinyujing_化学（选修4）3.jpg','2025-12-26','3002','刘强','六班','105','赵芳','此编辑框内已详情的内容，您可以直接对现有文字进行修改，无论是调整文字内容、更正表述还是增减文字数量都能实现；若需插入图片，只需点击上方工具栏的图片图标，选择本地文件或输入图片链接即可插入展示，所有操作完成后提交后系统会保存您的修改。​'),(6,'2025-12-26 08:20:38','体育与健康','必修','upload/kaoqinyujing_体育与健康1.jpg,upload/kaoqinyujing_体育与健康2.jpg,upload/kaoqinyujing_体育与健康3.jpg','2025-12-26','3004','黄军','二班','106','王明','编辑框中展示的默认内容仅为示例，您可任意修改其中的文字表述，比如更换词语、调整语句顺序等；当需要插入图片时，点击编辑框上方的 “插入图片” 按钮，上传图片后即可直接在编辑框中展示。'),(7,'2025-12-26 08:20:38','物理（选修3-1）','理科选修','upload/kaoqinyujing_物理（选修3-1）1.jpg,upload/kaoqinyujing_物理（选修3-1）2.jpg,upload/kaoqinyujing_物理（选修3-1）3.jpg','2025-12-26','3007','徐敏','十班','103','刘敏','此编辑框内的默认内容为可编辑状态，您可对文字进行修改，比如调整语句顺序、更换文字内容等；插入图片时，支持上传图片，上传后图片会直接在编辑框中展示，也能一键删除不需要的图片。'),(8,'2025-12-26 08:20:38','思想政治','必修','upload/kaoqinyujing_思想政治1.jpg,upload/kaoqinyujing_思想政治2.jpg,upload/kaoqinyujing_思想政治3.jpg','2025-12-26','3001','宋琳','八班','107','李娜与','此处文本编辑框是内容详细的操作提示，您可对文字进行各类修改，比如调整语句先后顺序、更换文字内容等；插入图片时，支持同时上传多张图片，上传完成后图片会直接在编辑框中展示，不需要的图片可以一键删除。');
/*!40000 ALTER TABLE `kaoqinyujing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kebiaoxinix`
--

DROP TABLE IF EXISTS `kebiaoxinix`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kebiaoxinix` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `sectionnum` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '节数',
  `week` int(11) DEFAULT NULL COMMENT '星期',
  `banji` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  `didian` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地点',
  `jiaoshizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课表信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kebiaoxinix`
--

LOCK TABLES `kebiaoxinix` WRITE;
/*!40000 ALTER TABLE `kebiaoxinix` DISABLE KEYS */;
INSERT INTO `kebiaoxinix` VALUES (1,'2025-12-26 08:20:38','美术','1',1,'一班','地点1','3008'),(2,'2025-12-26 08:20:38','历史','2',2,'四班','地点2','3006'),(3,'2025-12-26 08:20:38','生物（选修1）','3',3,'九班','地点3','3003'),(4,'2025-12-26 08:20:38','语文','4',4,'七班','地点4','3005'),(5,'2025-12-26 08:20:38','化学（选修4）','5',5,'六班','地点5','3002'),(6,'2025-12-26 08:20:38','体育与健康','6',6,'二班','地点6','3004'),(7,'2025-12-26 08:20:38','物理（选修3-1）','7',7,'十班','地点7','3007'),(8,'2025-12-26 08:20:38','思想政治','8',1,'八班','地点8','3001');
/*!40000 ALTER TABLE `kebiaoxinix` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kechengkaoqin`
--

DROP TABLE IF EXISTS `kechengkaoqin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kechengkaoqin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengleixing` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程类型',
  `kechengfengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '课程封面',
  `kaoqinqingkuang` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '考勤情况',
  `dengjishijian` date DEFAULT NULL COMMENT '登记时间',
  `jiaoshizhanghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师账号',
  `jiaoshixingming` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  `xueshengzhanghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生账号',
  `xueshengxingming` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生姓名',
  `xiangqing` longtext COLLATE utf8mb4_unicode_ci COMMENT '详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程考勤';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kechengkaoqin`
--

LOCK TABLES `kechengkaoqin` WRITE;
/*!40000 ALTER TABLE `kechengkaoqin` DISABLE KEYS */;
INSERT INTO `kechengkaoqin` VALUES (1,'2025-12-26 08:20:38','美术','选修','upload/kechengkaoqin_美术1.jpg,upload/kechengkaoqin_美术2.jpg,upload/kechengkaoqin_美术3.jpg','出勤','2025-12-26','3008','罗琳','一班','102','王静','您看到的编辑框内默认提示可作为创作起点，支持对现有内容进行全面修改，包括删除冗余文字、替换表述方式等；插入图片时，既可以从本地上传，也能粘贴网络图片地址，图片插入后会直接在文本中展示，完成后点击空白处即可保存设置。​'),(2,'2025-12-26 08:20:38','历史','文科必修','upload/kechengkaoqin_历史1.jpg,upload/kechengkaoqin_历史2.jpg,upload/kechengkaoqin_历史3.jpg','出勤','2025-12-26','3006','周杰','四班','108','赵敏','此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​'),(3,'2025-12-26 08:20:38','生物（选修1）','理科选修','upload/kechengkaoqin_生物（选修1）1.jpg,upload/kechengkaoqin_生物（选修1）2.jpg,upload/kechengkaoqin_生物（选修1）3.jpg','出勤','2025-12-26','3003','朱琳','九班','104','陈明','此处文本编辑框是内容详细的操作提示，您可以对现有文字进行修改，包括删除多余文字、替换不合适的表述等；插入图片时，既可以从本地选择图片上传，图片插入后会直接展示在编辑框中，完成操作后点击空白区域即可保存。'),(4,'2025-12-26 08:20:38','语文','文科必修','upload/kechengkaoqin_语文1.jpg,upload/kechengkaoqin_语文2.jpg,upload/kechengkaoqin_语文3.jpg','出勤','2025-12-26','3005','胡伟','七班','101','刘强','此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​'),(5,'2025-12-26 08:20:38','化学（选修4）','理科选修','upload/kechengkaoqin_化学（选修4）1.jpg,upload/kechengkaoqin_化学（选修4）2.jpg,upload/kechengkaoqin_化学（选修4）3.jpg','出勤','2025-12-26','3002','刘强','六班','105','赵芳','此编辑框内已详情的内容，您可以直接对现有文字进行修改，无论是调整文字内容、更正表述还是增减文字数量都能实现；若需插入图片，只需点击上方工具栏的图片图标，选择本地文件或输入图片链接即可插入展示，所有操作完成后提交后系统会保存您的修改。​'),(6,'2025-12-26 08:20:38','体育与健康','必修','upload/kechengkaoqin_体育与健康1.jpg,upload/kechengkaoqin_体育与健康2.jpg,upload/kechengkaoqin_体育与健康3.jpg','出勤','2025-12-26','3004','黄军','二班','106','王明','编辑框中展示的默认内容仅为示例，您可任意修改其中的文字表述，比如更换词语、调整语句顺序等；当需要插入图片时，点击编辑框上方的 “插入图片” 按钮，上传图片后即可直接在编辑框中展示。'),(7,'2025-12-26 08:20:38','物理（选修3-1）','理科选修','upload/kechengkaoqin_物理（选修3-1）1.jpg,upload/kechengkaoqin_物理（选修3-1）2.jpg,upload/kechengkaoqin_物理（选修3-1）3.jpg','出勤','2025-12-26','3007','徐敏','十班','103','刘敏','此编辑框内的默认内容为可编辑状态，您可对文字进行修改，比如调整语句顺序、更换文字内容等；插入图片时，支持上传图片，上传后图片会直接在编辑框中展示，也能一键删除不需要的图片。'),(8,'2025-12-26 08:20:38','思想政治','必修','upload/kechengkaoqin_思想政治1.jpg,upload/kechengkaoqin_思想政治2.jpg,upload/kechengkaoqin_思想政治3.jpg','出勤','2025-12-26','3001','宋琳','八班','107','李娜与','此处文本编辑框是内容详细的操作提示，您可对文字进行各类修改，比如调整语句先后顺序、更换文字内容等；插入图片时，支持同时上传多张图片，上传完成后图片会直接在编辑框中展示，不需要的图片可以一键删除。');
/*!40000 ALTER TABLE `kechengkaoqin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kechengleixing`
--

DROP TABLE IF EXISTS `kechengleixing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kechengleixing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengleixing` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `kechengleixing` (`kechengleixing`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kechengleixing`
--

LOCK TABLES `kechengleixing` WRITE;
/*!40000 ALTER TABLE `kechengleixing` DISABLE KEYS */;
INSERT INTO `kechengleixing` VALUES (1,'2025-12-26 08:20:38','选修'),(2,'2025-12-26 08:20:38','文科必修'),(3,'2025-12-26 08:20:38','理科选修'),(4,'2025-12-26 08:20:38','必修'),(5,'2025-12-26 08:20:38','课程类型5'),(6,'2025-12-26 08:20:38','课程类型6'),(7,'2025-12-26 08:20:38','课程类型7'),(8,'2025-12-26 08:20:38','课程类型8');
/*!40000 ALTER TABLE `kechengleixing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kechengxinxi`
--

DROP TABLE IF EXISTS `kechengxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kechengxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengleixing` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程类型',
  `kechengfengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '课程封面',
  `jiaoshizhanghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师账号',
  `jiaoshixingming` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  `shangkeshijian` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上课时间',
  `shangkedidian` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上课地点',
  `kechengxiangqing` longtext COLLATE utf8mb4_unicode_ci COMMENT '课程详情',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kechengxinxi`
--

LOCK TABLES `kechengxinxi` WRITE;
/*!40000 ALTER TABLE `kechengxinxi` DISABLE KEYS */;
INSERT INTO `kechengxinxi` VALUES (1,'2025-12-26 08:20:38','美术','选修','upload/kechengxinxi_美术1.jpg,upload/kechengxinxi_美术2.jpg,upload/kechengxinxi_美术3.jpg','3008','罗琳','一班','16:00-17:40','艺术楼D102','涵盖素描、色彩、设计基础，培养审美能力与艺术创作能力',1),(2,'2025-12-26 08:20:38','历史','文科必修','upload/kechengxinxi_历史1.jpg,upload/kechengxinxi_历史2.jpg,upload/kechengxinxi_历史3.jpg','3006','周杰','四班','14:00-15:40','教学楼B102','梳理中外历史发展脉络，分析重要历史事件与人物，提升历史解释能力',2),(3,'2025-12-26 08:20:38','生物（选修1）','理科选修','upload/kechengxinxi_生物（选修1）1.jpg,upload/kechengxinxi_生物（选修1）2.jpg,upload/kechengxinxi_生物（选修1）3.jpg','3003','朱琳','九班','14:00-15:40','实验楼B203','开展微生物发酵、酶的应用等生物技术实践，提升实验操作与探究能力',3),(4,'2025-12-26 08:20:38','语文','文科必修','upload/kechengxinxi_语文1.jpg,upload/kechengxinxi_语文2.jpg,upload/kechengxinxi_语文3.jpg','3005','胡伟','七班','08:00-09:40','教学楼A101','聚焦古代诗文鉴赏、现代文阅读与写作，提升文学素养与表达能力',4),(5,'2025-12-26 08:20:38','化学（选修4）','理科选修','upload/kechengxinxi_化学（选修4）1.jpg,upload/kechengxinxi_化学（选修4）2.jpg,upload/kechengxinxi_化学（选修4）3.jpg','3002','刘强','六班','09:50-11:30','实验楼B202','聚焦化学反应原理，包括热化学、电化学、化学反应速率等核心知识',5),(6,'2025-12-26 08:20:38','体育与健康','必修','upload/kechengxinxi_体育与健康1.jpg,upload/kechengxinxi_体育与健康2.jpg,upload/kechengxinxi_体育与健康3.jpg','3004','黄军','二班','07:00-08:40','体育场','开展田径、球类、体操等项目训练，增强体质与团队协作能力',6),(7,'2025-12-26 08:20:38','物理（选修3-1）','理科选修','upload/kechengxinxi_物理（选修3-1）1.jpg,upload/kechengxinxi_物理（选修3-1）2.jpg,upload/kechengxinxi_物理（选修3-1）3.jpg','3007','徐敏','十班','08:00-09:40','实验楼B201','深入学习静电场、恒定电流、磁场等内容，为高考物理选考奠定基础',7),(8,'2025-12-26 08:20:38','思想政治','必修','upload/kechengxinxi_思想政治1.jpg,upload/kechengxinxi_思想政治2.jpg,upload/kechengxinxi_思想政治3.jpg','3001','宋琳','八班','10:00-11:40','教学楼B101','涵盖经济、政治、哲学基础，引导学生树立正确价值观',8);
/*!40000 ALTER TABLE `kechengxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kechengxuanke`
--

DROP TABLE IF EXISTS `kechengxuanke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kechengxuanke` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengleixing` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程类型',
  `kechengfengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '课程封面',
  `jiaoshizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师账号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  `shangkeshijian` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上课时间',
  `shangkedidian` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上课地点',
  `xuankeshijian` date DEFAULT NULL COMMENT '选课时间',
  `xueshengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生账号',
  `xueshengxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生姓名',
  `crossuserid` bigint(20) DEFAULT NULL COMMENT '跨表用户id',
  `crossrefid` bigint(20) DEFAULT NULL COMMENT '跨表主键id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程选课';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kechengxuanke`
--

LOCK TABLES `kechengxuanke` WRITE;
/*!40000 ALTER TABLE `kechengxuanke` DISABLE KEYS */;
INSERT INTO `kechengxuanke` VALUES (1,'2025-12-26 08:20:38','美术','选修','upload/kechengxuanke_美术1.jpg,upload/kechengxuanke_美术2.jpg,upload/kechengxuanke_美术3.jpg','3008','罗琳','一班','16:00-17:40','艺术楼D102','2025-12-26','102','王静',1,1),(2,'2025-12-26 08:20:38','历史','文科必修','upload/kechengxuanke_历史1.jpg,upload/kechengxuanke_历史2.jpg,upload/kechengxuanke_历史3.jpg','3006','周杰','四班','14:00-15:40','教学楼B102','2025-12-26','108','赵敏',2,2),(3,'2025-12-26 08:20:38','生物（选修1）','理科选修','upload/kechengxuanke_生物（选修1）1.jpg,upload/kechengxuanke_生物（选修1）2.jpg,upload/kechengxuanke_生物（选修1）3.jpg','3003','朱琳','九班','14:00-15:40','实验楼B203','2025-12-26','104','陈明',3,3),(4,'2025-12-26 08:20:38','语文','文科必修','upload/kechengxuanke_语文1.jpg,upload/kechengxuanke_语文2.jpg,upload/kechengxuanke_语文3.jpg','3005','胡伟','七班','08:00-09:40','教学楼A101','2025-12-26','101','刘强',4,4),(5,'2025-12-26 08:20:38','化学（选修4）','理科选修','upload/kechengxuanke_化学（选修4）1.jpg,upload/kechengxuanke_化学（选修4）2.jpg,upload/kechengxuanke_化学（选修4）3.jpg','3002','刘强','六班','09:50-11:30','实验楼B202','2025-12-26','105','赵芳',5,5),(6,'2025-12-26 08:20:38','体育与健康','必修','upload/kechengxuanke_体育与健康1.jpg,upload/kechengxuanke_体育与健康2.jpg,upload/kechengxuanke_体育与健康3.jpg','3004','黄军','二班','07:00-08:40','体育场','2025-12-26','106','王明',6,6),(7,'2025-12-26 08:20:38','物理（选修3-1）','理科选修','upload/kechengxuanke_物理（选修3-1）1.jpg,upload/kechengxuanke_物理（选修3-1）2.jpg,upload/kechengxuanke_物理（选修3-1）3.jpg','3007','徐敏','十班','08:00-09:40','实验楼B201','2025-12-26','103','刘敏',7,7),(8,'2025-12-26 08:20:38','思想政治','必修','upload/kechengxuanke_思想政治1.jpg,upload/kechengxuanke_思想政治2.jpg,upload/kechengxuanke_思想政治3.jpg','3001','宋琳','八班','10:00-11:40','教学楼B101','2025-12-26','107','李娜与',8,8);
/*!40000 ALTER TABLE `kechengxuanke` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ketangkaoqinfaqi`
--

DROP TABLE IF EXISTS `ketangkaoqinfaqi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ketangkaoqinfaqi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengleixing` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程类型',
  `kechengfengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '课程封面',
  `jiaoshizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师账号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  `fabushijian` date DEFAULT NULL COMMENT '发布时间',
  `youxiaoshijian` date DEFAULT NULL COMMENT '有效时间',
  `xueshengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生账号',
  `xueshengxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生姓名',
  `beizhu` longtext COLLATE utf8mb4_unicode_ci COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课堂考勤发起';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ketangkaoqinfaqi`
--

LOCK TABLES `ketangkaoqinfaqi` WRITE;
/*!40000 ALTER TABLE `ketangkaoqinfaqi` DISABLE KEYS */;
INSERT INTO `ketangkaoqinfaqi` VALUES (1,'2025-12-26 08:20:38','美术','选修','upload/ketangkaoqinfaqi_美术1.jpg,upload/ketangkaoqinfaqi_美术2.jpg,upload/ketangkaoqinfaqi_美术3.jpg','3008','罗琳','一班','2025-12-26','2025-12-26','102','王静','您看到的编辑框内默认提示可作为创作起点，支持对现有内容进行全面修改，包括删除冗余文字、替换表述方式等；插入图片时，既可以从本地上传，也能粘贴网络图片地址，图片插入后会直接在文本中展示，完成后点击空白处即可保存设置。​'),(2,'2025-12-26 08:20:38','历史','文科必修','upload/ketangkaoqinfaqi_历史1.jpg,upload/ketangkaoqinfaqi_历史2.jpg,upload/ketangkaoqinfaqi_历史3.jpg','3006','周杰','四班','2025-12-26','2025-12-26','108','赵敏','此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​'),(3,'2025-12-26 08:20:38','生物（选修1）','理科选修','upload/ketangkaoqinfaqi_生物（选修1）1.jpg,upload/ketangkaoqinfaqi_生物（选修1）2.jpg,upload/ketangkaoqinfaqi_生物（选修1）3.jpg','3003','朱琳','九班','2025-12-26','2025-12-26','104','陈明','此处文本编辑框是内容详细的操作提示，您可以对现有文字进行修改，包括删除多余文字、替换不合适的表述等；插入图片时，既可以从本地选择图片上传，图片插入后会直接展示在编辑框中，完成操作后点击空白区域即可保存。'),(4,'2025-12-26 08:20:38','语文','文科必修','upload/ketangkaoqinfaqi_语文1.jpg,upload/ketangkaoqinfaqi_语文2.jpg,upload/ketangkaoqinfaqi_语文3.jpg','3005','胡伟','七班','2025-12-26','2025-12-26','101','刘强','此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​'),(5,'2025-12-26 08:20:38','化学（选修4）','理科选修','upload/ketangkaoqinfaqi_化学（选修4）1.jpg,upload/ketangkaoqinfaqi_化学（选修4）2.jpg,upload/ketangkaoqinfaqi_化学（选修4）3.jpg','3002','刘强','六班','2025-12-26','2025-12-26','105','赵芳','此编辑框内已详情的内容，您可以直接对现有文字进行修改，无论是调整文字内容、更正表述还是增减文字数量都能实现；若需插入图片，只需点击上方工具栏的图片图标，选择本地文件或输入图片链接即可插入展示，所有操作完成后提交后系统会保存您的修改。​'),(6,'2025-12-26 08:20:38','体育与健康','必修','upload/ketangkaoqinfaqi_体育与健康1.jpg,upload/ketangkaoqinfaqi_体育与健康2.jpg,upload/ketangkaoqinfaqi_体育与健康3.jpg','3004','黄军','二班','2025-12-26','2025-12-26','106','王明','编辑框中展示的默认内容仅为示例，您可任意修改其中的文字表述，比如更换词语、调整语句顺序等；当需要插入图片时，点击编辑框上方的 “插入图片” 按钮，上传图片后即可直接在编辑框中展示。'),(7,'2025-12-26 08:20:38','物理（选修3-1）','理科选修','upload/ketangkaoqinfaqi_物理（选修3-1）1.jpg,upload/ketangkaoqinfaqi_物理（选修3-1）2.jpg,upload/ketangkaoqinfaqi_物理（选修3-1）3.jpg','3007','徐敏','十班','2025-12-26','2025-12-26','103','刘敏','此编辑框内的默认内容为可编辑状态，您可对文字进行修改，比如调整语句顺序、更换文字内容等；插入图片时，支持上传图片，上传后图片会直接在编辑框中展示，也能一键删除不需要的图片。'),(8,'2025-12-26 08:20:38','思想政治','必修','upload/ketangkaoqinfaqi_思想政治1.jpg,upload/ketangkaoqinfaqi_思想政治2.jpg,upload/ketangkaoqinfaqi_思想政治3.jpg','3001','宋琳','八班','2025-12-26','2025-12-26','107','李娜与','此处文本编辑框是内容详细的操作提示，您可对文字进行各类修改，比如调整语句先后顺序、更换文字内容等；插入图片时，支持同时上传多张图片，上传完成后图片会直接在编辑框中展示，不需要的图片可以一键删除。');
/*!40000 ALTER TABLE `ketangkaoqinfaqi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ketangkaoqinqiandao`
--

DROP TABLE IF EXISTS `ketangkaoqinqiandao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ketangkaoqinqiandao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengleixing` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程类型',
  `kechengfengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '课程封面',
  `jiaoshizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师账号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  `qiandaoshijian` date DEFAULT NULL COMMENT '签到时间',
  `xueshengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生账号',
  `xueshengxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生姓名',
  `dizhi` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `crossuserid` bigint(20) DEFAULT NULL COMMENT '跨表用户id',
  `crossrefid` bigint(20) DEFAULT NULL COMMENT '跨表主键id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课堂考勤签到';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ketangkaoqinqiandao`
--

LOCK TABLES `ketangkaoqinqiandao` WRITE;
/*!40000 ALTER TABLE `ketangkaoqinqiandao` DISABLE KEYS */;
INSERT INTO `ketangkaoqinqiandao` VALUES (1,'2025-12-26 08:20:38','美术','选修','upload/ketangkaoqinqiandao_美术1.jpg,upload/ketangkaoqinqiandao_美术2.jpg,upload/ketangkaoqinqiandao_美术3.jpg','3008','罗琳','一班','2025-12-26','102','王静','地址1',1,1),(2,'2025-12-26 08:20:38','历史','文科必修','upload/ketangkaoqinqiandao_历史1.jpg,upload/ketangkaoqinqiandao_历史2.jpg,upload/ketangkaoqinqiandao_历史3.jpg','3006','周杰','四班','2025-12-26','108','赵敏','地址2',2,2),(3,'2025-12-26 08:20:38','生物（选修1）','理科选修','upload/ketangkaoqinqiandao_生物（选修1）1.jpg,upload/ketangkaoqinqiandao_生物（选修1）2.jpg,upload/ketangkaoqinqiandao_生物（选修1）3.jpg','3003','朱琳','九班','2025-12-26','104','陈明','地址3',3,3),(4,'2025-12-26 08:20:38','语文','文科必修','upload/ketangkaoqinqiandao_语文1.jpg,upload/ketangkaoqinqiandao_语文2.jpg,upload/ketangkaoqinqiandao_语文3.jpg','3005','胡伟','七班','2025-12-26','101','刘强','地址4',4,4),(5,'2025-12-26 08:20:38','化学（选修4）','理科选修','upload/ketangkaoqinqiandao_化学（选修4）1.jpg,upload/ketangkaoqinqiandao_化学（选修4）2.jpg,upload/ketangkaoqinqiandao_化学（选修4）3.jpg','3002','刘强','六班','2025-12-26','105','赵芳','地址5',5,5),(6,'2025-12-26 08:20:38','体育与健康','必修','upload/ketangkaoqinqiandao_体育与健康1.jpg,upload/ketangkaoqinqiandao_体育与健康2.jpg,upload/ketangkaoqinqiandao_体育与健康3.jpg','3004','黄军','二班','2025-12-26','106','王明','地址6',6,6),(7,'2025-12-26 08:20:38','物理（选修3-1）','理科选修','upload/ketangkaoqinqiandao_物理（选修3-1）1.jpg,upload/ketangkaoqinqiandao_物理（选修3-1）2.jpg,upload/ketangkaoqinqiandao_物理（选修3-1）3.jpg','3007','徐敏','十班','2025-12-26','103','刘敏','地址7',7,7),(8,'2025-12-26 08:20:38','思想政治','必修','upload/ketangkaoqinqiandao_思想政治1.jpg,upload/ketangkaoqinqiandao_思想政治2.jpg,upload/ketangkaoqinqiandao_思想政治3.jpg','3001','宋琳','八班','2025-12-26','107','李娜与','地址8',8,8);
/*!40000 ALTER TABLE `ketangkaoqinqiandao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qingjiaxinxi`
--

DROP TABLE IF EXISTS `qingjiaxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qingjiaxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengleixing` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程类型',
  `jiaoshizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师账号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  `shenqingshijian` date DEFAULT NULL COMMENT '申请时间',
  `xueshengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生账号',
  `xueshengxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生姓名',
  `qingjialeixing` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请假类型',
  `kaishishijian` date DEFAULT NULL COMMENT '开始时间',
  `jieshushijian` date DEFAULT NULL COMMENT '结束时间',
  `zhengmingcailiao` longtext COLLATE utf8mb4_unicode_ci COMMENT '证明材料',
  `jutishiyou` longtext COLLATE utf8mb4_unicode_ci COMMENT '具体事由',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='请假信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qingjiaxinxi`
--

LOCK TABLES `qingjiaxinxi` WRITE;
/*!40000 ALTER TABLE `qingjiaxinxi` DISABLE KEYS */;
INSERT INTO `qingjiaxinxi` VALUES (1,'2025-12-26 08:20:38','美术','选修','3008','罗琳','一班','2025-12-26','102','王静','病假（传染性疾病休养）','2025-12-26','2025-12-26','upload/qingjiaxinxi_zhengmingcailiao1.jpg,upload/qingjiaxinxi_zhengmingcailiao2.jpg,upload/qingjiaxinxi_zhengmingcailiao3.jpg','腹痛难忍，急诊检查','是',''),(2,'2025-12-26 08:20:38','历史','文科必修','3006','周杰','四班','2025-12-26','108','赵敏','病假（慢性病复诊）','2025-12-26','2025-12-26','upload/qingjiaxinxi_zhengmingcailiao2.jpg,upload/qingjiaxinxi_zhengmingcailiao3.jpg,upload/qingjiaxinxi_zhengmingcailiao4.jpg','家中急事，需返乡处理','是',''),(3,'2025-12-26 08:20:38','生物（选修1）','理科选修','3003','朱琳','九班','2025-12-26','104','陈明','病假（精神压力调适）','2025-12-26','2025-12-26','upload/qingjiaxinxi_zhengmingcailiao3.jpg,upload/qingjiaxinxi_zhengmingcailiao4.jpg,upload/qingjiaxinxi_zhengmingcailiao5.jpg','参加亲属婚礼，前往赴宴','是',''),(4,'2025-12-26 08:20:38','语文','文科必修','3005','胡伟','七班','2025-12-26','101','刘强','事假（亲属婚丧事宜）','2025-12-26','2025-12-26','upload/qingjiaxinxi_zhengmingcailiao4.jpg,upload/qingjiaxinxi_zhengmingcailiao5.jpg,upload/qingjiaxinxi_zhengmingcailiao6.jpg','接种疫苗，需留观','是',''),(5,'2025-12-26 08:20:38','化学（选修4）','理科选修','3002','刘强','六班','2025-12-26','105','赵芳','病假（中医调理治疗）','2025-12-26','2025-12-26','upload/qingjiaxinxi_zhengmingcailiao5.jpg,upload/qingjiaxinxi_zhengmingcailiao6.jpg,upload/qingjiaxinxi_zhengmingcailiao7.jpg','感冒发烧，需居家休养','是',''),(6,'2025-12-26 08:20:38','体育与健康','必修','3004','黄军','二班','2025-12-26','106','王明','事假（家庭事务处理）','2025-12-26','2025-12-26','upload/qingjiaxinxi_zhengmingcailiao6.jpg,upload/qingjiaxinxi_zhengmingcailiao7.jpg,upload/qingjiaxinxi_zhengmingcailiao8.jpg','腰部扭伤，无法到校','是',''),(7,'2025-12-26 08:20:38','物理（选修3-1）','理科选修','3007','徐敏','十班','2025-12-26','103','刘敏','事假（职业资格考试）','2025-12-26','2025-12-26','upload/qingjiaxinxi_zhengmingcailiao7.jpg,upload/qingjiaxinxi_zhengmingcailiao8.jpg,upload/qingjiaxinxi_zhengmingcailiao1.jpg','预约体检，按时参与','是',''),(8,'2025-12-26 08:20:38','思想政治','必修','3001','宋琳','八班','2025-12-26','107','李娜与','事假（异地探亲出行）','2025-12-26','2025-12-26','upload/qingjiaxinxi_zhengmingcailiao8.jpg,upload/qingjiaxinxi_zhengmingcailiao1.jpg,upload/qingjiaxinxi_zhengmingcailiao2.jpg','奶奶大寿，返乡庆贺','是','');
/*!40000 ALTER TABLE `qingjiaxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheduletime`
--

DROP TABLE IF EXISTS `scheduletime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scheduletime` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sectionnum` int(11) DEFAULT NULL COMMENT '节数',
  `starttime` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '开始时间',
  `endtime` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课表时间';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheduletime`
--

LOCK TABLES `scheduletime` WRITE;
/*!40000 ALTER TABLE `scheduletime` DISABLE KEYS */;
INSERT INTO `scheduletime` VALUES (1,'2025-12-26 08:20:38',1,'08:00','09:00'),(2,'2025-12-26 08:20:38',2,'09:00','10:00'),(3,'2025-12-26 08:20:38',3,'10:00','11:00'),(4,'2025-12-26 08:20:38',4,'14:00','15:00'),(5,'2025-12-26 08:20:38',5,'15:00','16:00'),(6,'2025-12-26 08:20:38',6,'16:00','17:00'),(7,'2025-12-26 08:20:38',7,'20:00','21:00'),(8,'2025-12-26 08:20:38',8,'21:00','22:00');
/*!40000 ALTER TABLE `scheduletime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storeup`
--

DROP TABLE IF EXISTS `storeup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storeup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `refid` bigint(20) DEFAULT NULL COMMENT '商品id',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '类型',
  `inteltype` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收藏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeup`
--

LOCK TABLES `storeup` WRITE;
/*!40000 ALTER TABLE `storeup` DISABLE KEYS */;
/*!40000 ALTER TABLE `storeup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(255) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,11,'102','xuesheng','学生','gyvc0ou62wjdjexkrfl0u7j9uuj0k9rz','2025-12-26 08:21:22','2025-12-26 09:21:37');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tousufankui`
--

DROP TABLE IF EXISTS `tousufankui`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tousufankui` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `biaoti` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `fengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '封面',
  `dengjishijian` date DEFAULT NULL COMMENT '登记时间',
  `tousufankui` longtext COLLATE utf8mb4_unicode_ci COMMENT '投诉反馈',
  `xueshengzhanghao` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生账号',
  `xueshengxingming` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生姓名',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='投诉反馈';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tousufankui`
--

LOCK TABLES `tousufankui` WRITE;
/*!40000 ALTER TABLE `tousufankui` DISABLE KEYS */;
INSERT INTO `tousufankui` VALUES (1,'2025-12-26 08:20:38','美术','upload/tousufankui_美术1.jpg,upload/tousufankui_美术2.jpg,upload/tousufankui_美术3.jpg','2025-12-26','您看到的编辑框内默认提示可作为创作起点，支持对现有内容进行全面修改，包括删除冗余文字、替换表述方式等；插入图片时，既可以从本地上传，也能粘贴网络图片地址，图片插入后会直接在文本中展示，完成后点击空白处即可保存设置。​','102','王静','是',''),(2,'2025-12-26 08:20:38','历史','upload/tousufankui_历史1.jpg,upload/tousufankui_历史2.jpg,upload/tousufankui_历史3.jpg','2025-12-26','此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​','108','赵敏','是',''),(3,'2025-12-26 08:20:38','生物（选修1）','upload/tousufankui_生物（选修1）1.jpg,upload/tousufankui_生物（选修1）2.jpg,upload/tousufankui_生物（选修1）3.jpg','2025-12-26','此处文本编辑框是内容详细的操作提示，您可以对现有文字进行修改，包括删除多余文字、替换不合适的表述等；插入图片时，既可以从本地选择图片上传，图片插入后会直接展示在编辑框中，完成操作后点击空白区域即可保存。','104','陈明','是',''),(4,'2025-12-26 08:20:38','语文','upload/tousufankui_语文1.jpg,upload/tousufankui_语文2.jpg,upload/tousufankui_语文3.jpg','2025-12-26','此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​','101','刘强','是',''),(5,'2025-12-26 08:20:38','化学（选修4）','upload/tousufankui_化学（选修4）1.jpg,upload/tousufankui_化学（选修4）2.jpg,upload/tousufankui_化学（选修4）3.jpg','2025-12-26','此编辑框内已详情的内容，您可以直接对现有文字进行修改，无论是调整文字内容、更正表述还是增减文字数量都能实现；若需插入图片，只需点击上方工具栏的图片图标，选择本地文件或输入图片链接即可插入展示，所有操作完成后提交后系统会保存您的修改。​','105','赵芳','是',''),(6,'2025-12-26 08:20:38','体育与健康','upload/tousufankui_体育与健康1.jpg,upload/tousufankui_体育与健康2.jpg,upload/tousufankui_体育与健康3.jpg','2025-12-26','编辑框中展示的默认内容仅为示例，您可任意修改其中的文字表述，比如更换词语、调整语句顺序等；当需要插入图片时，点击编辑框上方的 “插入图片” 按钮，上传图片后即可直接在编辑框中展示。','106','王明','是',''),(7,'2025-12-26 08:20:38','物理（选修3-1）','upload/tousufankui_物理（选修3-1）1.jpg,upload/tousufankui_物理（选修3-1）2.jpg,upload/tousufankui_物理（选修3-1）3.jpg','2025-12-26','此编辑框内的默认内容为可编辑状态，您可对文字进行修改，比如调整语句顺序、更换文字内容等；插入图片时，支持上传图片，上传后图片会直接在编辑框中展示，也能一键删除不需要的图片。','103','刘敏','是',''),(8,'2025-12-26 08:20:38','思想政治','upload/tousufankui_思想政治1.jpg,upload/tousufankui_思想政治2.jpg,upload/tousufankui_思想政治3.jpg','2025-12-26','此处文本编辑框是内容详细的操作提示，您可对文字进行各类修改，比如调整语句先后顺序、更换文字内容等；插入图片时，支持同时上传多张图片，上传完成后图片会直接在编辑框中展示，不需要的图片可以一键删除。','107','李娜与','是','');
/*!40000 ALTER TABLE `tousufankui` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '管理员' COMMENT '角色',
  `image` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2025-12-26 08:20:38','admin','admin','管理员','upload/image1.jpg');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xuesheng`
--

DROP TABLE IF EXISTS `xuesheng`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xuesheng` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xueshengzhanghao` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学生账号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `xueshengxingming` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学生姓名',
  `xingbie` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `lianxifangshi` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系方式',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `zhuanye` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专业',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  `pquestion` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保问题',
  `panswer` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密保答案',
  PRIMARY KEY (`id`),
  UNIQUE KEY `xueshengzhanghao` (`xueshengzhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学生';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xuesheng`
--

LOCK TABLES `xuesheng` WRITE;
/*!40000 ALTER TABLE `xuesheng` DISABLE KEYS */;
INSERT INTO `xuesheng` VALUES (11,'2025-12-26 08:20:38','102','e10adc3949ba59abbe56e057f20f883e','王静','男','13512345678','upload/xuesheng_touxiang1.jpg','法学','一班','密保问题1','密保答案1'),(12,'2025-12-26 08:20:38','108','e10adc3949ba59abbe56e057f20f883e','赵敏','女','13467890123','upload/xuesheng_touxiang2.jpg','英语','四班','密保问题2','密保答案2'),(13,'2025-12-26 08:20:38','104','e10adc3949ba59abbe56e057f20f883e','陈明','男','13567890123','upload/xuesheng_touxiang3.jpg','计算机科学与技术','九班','密保问题3','密保答案3'),(14,'2025-12-26 08:20:38','101','e10adc3949ba59abbe56e057f20f883e','刘强','女','13589012345','upload/xuesheng_touxiang4.jpg','心理学','七班','密保问题4','密保答案4'),(15,'2025-12-26 08:20:38','105','e10adc3949ba59abbe56e057f20f883e','赵芳','女','13534567890','upload/xuesheng_touxiang5.jpg','戏剧影视文学','六班','密保问题5','密保答案5'),(16,'2025-12-26 08:20:38','106','e10adc3949ba59abbe56e057f20f883e','王明','男','13401234567','upload/xuesheng_touxiang6.jpg','生物学','二班','密保问题6','密保答案6'),(17,'2025-12-26 08:20:38','103','e10adc3949ba59abbe56e057f20f883e','刘敏','男','13445678901','upload/xuesheng_touxiang7.jpg','市场营销','十班','密保问题7','密保答案7'),(18,'2025-12-26 08:20:38','107','e10adc3949ba59abbe56e057f20f883e','李娜与','男','13456789012','upload/xuesheng_touxiang8.jpg','电气工程及其自动化','八班','密保问题8','密保答案8');
/*!40000 ALTER TABLE `xuesheng` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xuexijindu`
--

DROP TABLE IF EXISTS `xuexijindu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xuexijindu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `kechengleixing` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程类型',
  `kechengfengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '课程封面',
  `jiaoshizhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师账号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师姓名',
  `banji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '班级',
  `dengjishijian` date DEFAULT NULL COMMENT '登记时间',
  `xueshengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生账号',
  `xueshengxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生姓名',
  `xuexijindu` longtext COLLATE utf8mb4_unicode_ci COMMENT '学习进度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学习进度';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xuexijindu`
--

LOCK TABLES `xuexijindu` WRITE;
/*!40000 ALTER TABLE `xuexijindu` DISABLE KEYS */;
INSERT INTO `xuexijindu` VALUES (1,'2025-12-26 08:20:38','美术','选修','upload/xuexijindu_美术1.jpg,upload/xuexijindu_美术2.jpg,upload/xuexijindu_美术3.jpg','3008','罗琳','一班','2025-12-26','102','王静','您看到的编辑框内默认提示可作为创作起点，支持对现有内容进行全面修改，包括删除冗余文字、替换表述方式等；插入图片时，既可以从本地上传，也能粘贴网络图片地址，图片插入后会直接在文本中展示，完成后点击空白处即可保存设置。​'),(2,'2025-12-26 08:20:38','历史','文科必修','upload/xuexijindu_历史1.jpg,upload/xuexijindu_历史2.jpg,upload/xuexijindu_历史3.jpg','3006','周杰','四班','2025-12-26','108','赵敏','此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​'),(3,'2025-12-26 08:20:38','生物（选修1）','理科选修','upload/xuexijindu_生物（选修1）1.jpg,upload/xuexijindu_生物（选修1）2.jpg,upload/xuexijindu_生物（选修1）3.jpg','3003','朱琳','九班','2025-12-26','104','陈明','此处文本编辑框是内容详细的操作提示，您可以对现有文字进行修改，包括删除多余文字、替换不合适的表述等；插入图片时，既可以从本地选择图片上传，图片插入后会直接展示在编辑框中，完成操作后点击空白区域即可保存。'),(4,'2025-12-26 08:20:38','语文','文科必修','upload/xuexijindu_语文1.jpg,upload/xuexijindu_语文2.jpg,upload/xuexijindu_语文3.jpg','3005','胡伟','七班','2025-12-26','101','刘强','此处文本编辑框是内容详细的操作，您可以对框内现有的文字进行任意修改，无论是修改具体内容、调整语句逻辑还是增删文字都没问题；若要插入图片，点击上方工具栏的图片标识，选择本地图片文件，就能将图片插入并展示出来，所有操作都进行保存。​'),(5,'2025-12-26 08:20:38','化学（选修4）','理科选修','upload/xuexijindu_化学（选修4）1.jpg,upload/xuexijindu_化学（选修4）2.jpg,upload/xuexijindu_化学（选修4）3.jpg','3002','刘强','六班','2025-12-26','105','赵芳','此编辑框内已详情的内容，您可以直接对现有文字进行修改，无论是调整文字内容、更正表述还是增减文字数量都能实现；若需插入图片，只需点击上方工具栏的图片图标，选择本地文件或输入图片链接即可插入展示，所有操作完成后提交后系统会保存您的修改。​'),(6,'2025-12-26 08:20:38','体育与健康','必修','upload/xuexijindu_体育与健康1.jpg,upload/xuexijindu_体育与健康2.jpg,upload/xuexijindu_体育与健康3.jpg','3004','黄军','二班','2025-12-26','106','王明','编辑框中展示的默认内容仅为示例，您可任意修改其中的文字表述，比如更换词语、调整语句顺序等；当需要插入图片时，点击编辑框上方的 “插入图片” 按钮，上传图片后即可直接在编辑框中展示。'),(7,'2025-12-26 08:20:38','物理（选修3-1）','理科选修','upload/xuexijindu_物理（选修3-1）1.jpg,upload/xuexijindu_物理（选修3-1）2.jpg,upload/xuexijindu_物理（选修3-1）3.jpg','3007','徐敏','十班','2025-12-26','103','刘敏','此编辑框内的默认内容为可编辑状态，您可对文字进行修改，比如调整语句顺序、更换文字内容等；插入图片时，支持上传图片，上传后图片会直接在编辑框中展示，也能一键删除不需要的图片。'),(8,'2025-12-26 08:20:38','思想政治','必修','upload/xuexijindu_思想政治1.jpg,upload/xuexijindu_思想政治2.jpg,upload/xuexijindu_思想政治3.jpg','3001','宋琳','八班','2025-12-26','107','李娜与','此处文本编辑框是内容详细的操作提示，您可对文字进行各类修改，比如调整语句先后顺序、更换文字内容等；插入图片时，支持同时上传多张图片，上传完成后图片会直接在编辑框中展示，不需要的图片可以一键删除。');
/*!40000 ALTER TABLE `xuexijindu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xueyuan`
--

DROP TABLE IF EXISTS `xueyuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xueyuan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xueyuanmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学院名称',
  `xueyuantupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '学院图片',
  `fuzeren` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '负责人',
  `lianxidianhua` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `xueyuandizhi` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学院地址',
  `chengliriqi` date DEFAULT NULL COMMENT '成立日期',
  `xueyuanjianjie` longtext COLLATE utf8mb4_unicode_ci COMMENT '学院简介',
  PRIMARY KEY (`id`),
  UNIQUE KEY `xueyuanmingcheng` (`xueyuanmingcheng`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学院';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xueyuan`
--

LOCK TABLES `xueyuan` WRITE;
/*!40000 ALTER TABLE `xueyuan` DISABLE KEYS */;
INSERT INTO `xueyuan` VALUES (1,'2025-12-26 08:20:38','材料科学与工程学院','upload/xueyuan_0151.jpg,upload/xueyuan_0152.jpg,upload/xueyuan_0153.jpg','胡宇','13187654321','成都市双流区西航港街道川大路二段四川大学江安校区27栋304宿舍','2022-05-18','新型功能材料研发获多项国家专利'),(2,'2025-12-26 08:20:38','法学院','upload/xueyuan_0111.jpg,upload/xueyuan_0112.jpg,upload/xueyuan_0113.jpg','孙雨晴','13678901234','长沙市芙蓉区远大二路湖南农业大学东湖公寓12栋403宿舍','2025-08-20','法律援助与实践教学相结合'),(3,'2025-12-26 08:20:38','航空航天学院','upload/xueyuan_0161.jpg,upload/xueyuan_0162.jpg,upload/xueyuan_0163.jpg','郭阳','13654321098','武汉市洪山区珞喻路1037号华中科技大学韵苑宿舍18栋207','2023-07-11','飞行技术与航空服务人才培养具行业特色'),(4,'2025-12-26 08:20:38','外国语学院','upload/xueyuan_0081.jpg,upload/xueyuan_0082.jpg,upload/xueyuan_0083.jpg','李静','13987654321','北京市海淀区颐和园路5号清华大学紫荆公寓1号楼302宿舍','2024-06-30','翻译专业与多家企业合作就业率高'),(5,'2025-12-26 08:20:38','国际教育学院','upload/xueyuan_0091.jpg,upload/xueyuan_0092.jpg,upload/xueyuan_0093.jpg','徐婷','13890123456','杭州市滨江区江南大道100号浙江工业大学屏峰校区31栋508宿舍','2023-07-28','留学生培养规模居全省前列'),(6,'2025-12-26 08:20:38','文学院','upload/xueyuan_0011.jpg,upload/xueyuan_0012.jpg,upload/xueyuan_0013.jpg','王强','15212345678','天津市津南区海河教育园区同砚路38号天津大学北洋园校区11栋306宿舍','2025-08-05','以传统文化研究与地方文化挖掘为特色'),(7,'2025-12-26 08:20:38','体育学院','upload/xueyuan_0071.jpg,upload/xueyuan_0072.jpg,upload/xueyuan_0073.jpg','李思远','15187654321','上海市杨浦区淞沪路2005号同济大学四平路校区12栋406宿舍','2025-08-03','体育教育专业为省级特色专业竞技体育成绩突出'),(8,'2025-12-26 08:20:38','工学院','upload/xueyuan_0031.jpg,upload/xueyuan_0032.jpg,upload/xueyuan_0033.jpg','马超','13012345678','重庆市渝中区长江二路174号重庆医科大学袁家岗校区7栋205宿舍','2022-05-18','产学研结合紧密毕业生受制造企业青睐');
/*!40000 ALTER TABLE `xueyuan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zhuanye`
--

DROP TABLE IF EXISTS `zhuanye`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zhuanye` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhuanye` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专业',
  PRIMARY KEY (`id`),
  UNIQUE KEY `zhuanye` (`zhuanye`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='专业';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zhuanye`
--

LOCK TABLES `zhuanye` WRITE;
/*!40000 ALTER TABLE `zhuanye` DISABLE KEYS */;
INSERT INTO `zhuanye` VALUES (1,'2025-12-26 08:20:38','法学'),(2,'2025-12-26 08:20:38','英语'),(3,'2025-12-26 08:20:38','计算机科学与技术'),(4,'2025-12-26 08:20:38','心理学'),(5,'2025-12-26 08:20:38','戏剧影视文学'),(6,'2025-12-26 08:20:38','生物学'),(7,'2025-12-26 08:20:38','市场营销'),(8,'2025-12-26 08:20:38','电气工程及其自动化');
/*!40000 ALTER TABLE `zhuanye` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-26 17:30:05
