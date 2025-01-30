-- -------------------------------------------------------------
-- -------------------------------------------------------------
-- TablePlus 1.2.2
--
-- https://tableplus.com/
--
-- Database: app
-- Generation Time: 2025-01-29 17:21:01.995908
-- -------------------------------------------------------------

DROP TABLE IF EXISTS `app`.`todos`;


CREATE TABLE `todos` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `is_done` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

