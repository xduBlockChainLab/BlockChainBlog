/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - bcblog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bcblog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bcblog`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(64) NOT NULL,
  `user_password` varchar(64) NOT NULL,
  `user_token` varchar(64) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_email`,`user_password`,`user_token`) values (1,'324424398@qq.com','123456','123@sina.com?id=1');

/*Table structure for table `user_todolist` */

DROP TABLE IF EXISTS `user_todolist`;

CREATE TABLE `user_todolist` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_userid` int(11) NOT NULL,
  `task_content` varchar(128) NOT NULL,
  `task_type` int(11) NOT NULL,
  `task_createtime` datetime DEFAULT NULL,
  `task_finishtime` datetime DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `user_todolist` */

insert  into `user_todolist`(`task_id`,`task_userid`,`task_content`,`task_type`,`task_createtime`,`task_finishtime`) values (1,1,'区块链工作室留言板',0,'2021-08-21 15:58:58',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
