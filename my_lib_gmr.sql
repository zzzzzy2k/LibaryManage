/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 5.7.33 : Database - my_lib_mgr
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`my_lib_mgr` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `my_lib_mgr`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`id`,`name`,`price`) values 
(1,'springboot',22),
(2,'java从入门到精通',80),
(3,'html5+css+js',61),
(4,'有效的Java',99),
(5,'Java并发编程实战',88),
(6,'设计模式',77),
(7,'spring mvc',66),
(8,'mybatis入门',55),
(9,'spring boot',44),
(10,'面试宝典',2234),
(11,'abc',22),
(12,'水电费',11),
(13,'请问',23),
(14,'好几款',45),
(15,'去玩儿',66),
(16,'问题',99),
(17,'于保存',34),
(18,'法国红酒',66),
(19,'各环节',88),
(20,'回家考虑',456),
(21,'阿萨德发',567),
(22,'python入门',999),
(23,'matalab宝典',777),
(26,'测试修改书籍s',888);

/*Table structure for table `permissions` */

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `perId` varchar(32) NOT NULL COMMENT '权限表id，作为表主键，用于关联',
  `permissionName` varchar(255) DEFAULT NULL COMMENT '权限名称',
  PRIMARY KEY (`perId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `permissions` */

insert  into `permissions`(`perId`,`permissionName`) values 
('M01','add'),
('M02','delete'),
('M03','update'),
('M04','query');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleId` varchar(32) NOT NULL COMMENT '角色id',
  `roleName` varchar(255) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `role` */

insert  into `role`(`roleId`,`roleName`) values 
('100','admin'),
('200','common');

/*Table structure for table `role_per` */

DROP TABLE IF EXISTS `role_per`;

CREATE TABLE `role_per` (
  `id` int(11) NOT NULL COMMENT '表主键id',
  `roleId` varchar(32) DEFAULT NULL COMMENT '角色表的主键id',
  `perId` varchar(32) DEFAULT NULL COMMENT '权限表的主键id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `role_per` */

insert  into `role_per`(`id`,`roleId`,`perId`) values 
(1,'100','M01'),
(2,'100','M02'),
(3,'100','M03'),
(4,'100','M04'),
(5,'200','M04');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(11) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userId`,`username`,`password`) values 
(10001,'admin','928bfd2577490322a6e19b793691467e'),
(10002,'zzy','c63a7ca966a24d9a3950d679fdb368ef'),
(20001,'guest','ec6accdccaa07c48553791cc20426c10');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL COMMENT '表主键id',
  `userId` int(11) DEFAULT NULL COMMENT '账号表的主键id',
  `roleId` varchar(32) DEFAULT NULL COMMENT '角色表的主键id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`userId`,`roleId`) values 
(1,10001,'100'),
(2,10002,'100'),
(3,20001,'200');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
