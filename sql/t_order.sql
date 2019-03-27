--自增长主键
CREATE TABLE IF NOT EXISTS `t_order_2016` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_time` varchar(50) NOT NULL,
  `status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `t_order_2017` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `date_time` varchar(50) NOT NULL,
  `status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `t_order_2018` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_time` varchar(50) NOT NULL,
	`status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `t_order_2019` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_time` varchar(50) NOT NULL,
	`status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--分布式主键建表
CREATE TABLE IF NOT EXISTS `t_order_2016` (
  `order_id` bigint(20) NOT NULL ,
  `date_time` varchar(50) NOT NULL,
  `status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `t_order_2017` (
  `order_id` bigint(20) NOT NULL ,  
  `date_time` varchar(50) NOT NULL,
  `status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `t_order_2018` (
  `order_id` bigint(20) NOT NULL ,
  `date_time` varchar(50) NOT NULL,
	`status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `t_order_2019` (
  `order_id` bigint(20) NOT NULL ,
  `date_time` varchar(50) NOT NULL,
	`status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

----分布式主键绑定表
CREATE TABLE IF NOT EXISTS `t_order_item_2016` (
  `order_item_id` bigint(20) NOT NULL ,
	`order_id` bigint(20) NOT NULL ,
  `date_time` varchar(50) NOT NULL,
  `status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `t_order_item_2017` (
  `order_item_id` bigint(20) NOT NULL ,  
	`order_id` bigint(20) NOT NULL ,
  `date_time` varchar(50) NOT NULL,
  `status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `t_order_item_2018` (
  `order_item_id` bigint(20) NOT NULL ,
	`order_id` bigint(20) NOT NULL ,
  `date_time` varchar(50) NOT NULL,
	`status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `t_order_item_2019` (
  `order_item_id` bigint(20) NOT NULL ,
	`order_id` bigint(20) NOT NULL ,
  `date_time` varchar(50) NOT NULL,
	`status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;









