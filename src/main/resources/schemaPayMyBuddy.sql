-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           8.0.35 - MySQL Community Server - GPL
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour paymybuddy
CREATE DATABASE IF NOT EXISTS `paymybuddy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `paymybuddy`;

-- Listage de la structure de la table paymybuddy. app_account
CREATE TABLE IF NOT EXISTS `app_account` (
  `app_account_id` bigint NOT NULL AUTO_INCREMENT,
  `balance` double NOT NULL DEFAULT '0',
  `app_owner` bigint NOT NULL,
  PRIMARY KEY (`app_account_id`),
  KEY `app_account_id` (`app_account_id`),
  KEY `app_owner` (`app_owner`),
  CONSTRAINT `app_owner` FOREIGN KEY (`app_owner`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table paymybuddy. assoc_user_friend
CREATE TABLE IF NOT EXISTS `assoc_user_friend` (
  `user_id` bigint NOT NULL,
  `friend_id` bigint NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `friend_id` (`friend_id`),
  CONSTRAINT `friend_id` FOREIGN KEY (`friend_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `user_assoc_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table paymybuddy. deposit
CREATE TABLE IF NOT EXISTS `deposit` (
  `deposit_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `transaction_date` datetime NOT NULL,
  `fee` double NOT NULL,
  `depositor_id` bigint NOT NULL,
  PRIMARY KEY (`deposit_id`),
  UNIQUE KEY `deposit_id` (`deposit_id`),
  KEY `user_id` (`depositor_id`) USING BTREE,
  CONSTRAINT `user_id_deposit` FOREIGN KEY (`depositor_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table paymybuddy. transfert
CREATE TABLE IF NOT EXISTS `transfert` (
  `transfert_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `transaction_date` datetime NOT NULL,
  `fee` double NOT NULL,
  `sender_id` bigint NOT NULL,
  `recipient_id` bigint NOT NULL,
  PRIMARY KEY (`transfert_id`) USING BTREE,
  UNIQUE KEY `deposit_id` (`transfert_id`) USING BTREE,
  KEY `user_id` (`sender_id`) USING BTREE,
  KEY `recipient_id` (`recipient_id`),
  CONSTRAINT `recipient_id` FOREIGN KEY (`recipient_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `sender_id` FOREIGN KEY (`sender_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table paymybuddy. user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `firstname` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lastname` varchar(250) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table paymybuddy. user_account
CREATE TABLE IF NOT EXISTS `user_account` (
  `user_account_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(320) NOT NULL,
  `password` varchar(255) NOT NULL,
  `last_connection_date` datetime NOT NULL,
  `is_active` tinyint NOT NULL DEFAULT '1',
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`user_account_id`),
  UNIQUE KEY `user_account_id` (`user_account_id`),
  UNIQUE KEY `email` (`email`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Les données exportées n'étaient pas sélectionnées.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
