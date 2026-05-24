CREATE DATABASE  IF NOT EXISTS `customer_management_application`;
USE `customer_management_application`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `customers`;

CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100),
  `email` varchar(100),
	`phone` varchar(50),
    `created_at` datetime default CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;