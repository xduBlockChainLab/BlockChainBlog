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