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
  `balance` double DEFAULT NULL,
  `app_account_id` bigint NOT NULL AUTO_INCREMENT,
  `iban` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`app_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table paymybuddy.app_account : ~0 rows (environ)
INSERT INTO `app_account` (`balance`, `app_account_id`, `iban`) VALUES
	(1.1000000000000014, 1, 'FR7630006000011234567890199'),
	(345, 2, 'FR7630006000011234567890199'),
	(9087, 3, 'FR7630006000011234567890199');

-- Listage de la structure de la table paymybuddy. assoc_user_friend
CREATE TABLE IF NOT EXISTS `assoc_user_friend` (
  `friend_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  KEY `FK7qxtavpiecs7ikd22rwffqhnw` (`friend_id`),
  KEY `FKm1qogjemapss2g8vtmrcu0kgl` (`user_id`),
  CONSTRAINT `FK7qxtavpiecs7ikd22rwffqhnw` FOREIGN KEY (`friend_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKm1qogjemapss2g8vtmrcu0kgl` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table paymybuddy.assoc_user_friend : ~0 rows (environ)
INSERT INTO `assoc_user_friend` (`friend_id`, `user_id`) VALUES
	(1, 3),
	(1, 2),
	(3, 2),
	(2, 1),
	(3, 1);

-- Listage de la structure de la table paymybuddy. deposit
CREATE TABLE IF NOT EXISTS `deposit` (
  `amount` double DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `author_id` bigint NOT NULL,
  `transaction_date` datetime(6) DEFAULT NULL,
  `transaction_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `FK5hjt3w60iwi4maro5xgnovewk` (`author_id`),
  CONSTRAINT `FK5hjt3w60iwi4maro5xgnovewk` FOREIGN KEY (`author_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table paymybuddy.deposit : ~0 rows (environ)
INSERT INTO `deposit` (`amount`, `fee`, `author_id`, `transaction_date`, `transaction_id`, `description`) VALUES
	(56, 5, 1, '2024-02-03 18:33:00.000000', 1, 'Dépôt 1'),
	(76.5, 3, 3, '2022-02-06 18:34:00.000000', 2, 'Deuxième dépôt'),
	(567.6, 25, 2, '2023-02-06 18:39:00.000000', 3, 'Deposit 3');

-- Listage de la structure de la table paymybuddy. transfert
CREATE TABLE IF NOT EXISTS `transfert` (
  `amount` double DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `author_id` bigint NOT NULL,
  `recipient_id` bigint NOT NULL,
  `transaction_date` datetime(6) DEFAULT NULL,
  `transaction_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `FKms5nh1c4g3qc0wb8g7280i2h0` (`author_id`),
  KEY `FKegcjgu9qp7u3h7e885cdmyxvb` (`recipient_id`),
  CONSTRAINT `FKegcjgu9qp7u3h7e885cdmyxvb` FOREIGN KEY (`recipient_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKms5nh1c4g3qc0wb8g7280i2h0` FOREIGN KEY (`author_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table paymybuddy.transfert : ~0 rows (environ)
INSERT INTO `transfert` (`amount`, `fee`, `author_id`, `recipient_id`, `transaction_date`, `transaction_id`, `description`) VALUES
	(56, 3, 1, 3, '2024-02-06 18:32:17.000000', 1, 'ceci est une trasnaction'),
	(314, 20, 3, 1, '2023-02-06 18:32:00.000000', 2, 'Transfert Deux'),
	(4000, 50, 2, 3, '2024-02-04 18:33:00.000000', 3, 'Transfert trois');

-- Listage de la structure de la table paymybuddy. user
CREATE TABLE IF NOT EXISTS `user` (
  `birthdate` date DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `app_account_id` bigint DEFAULT NULL,
  `last_connection_date` datetime(6) DEFAULT NULL,
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_of5ucevdpusnfngt7mub8lnac` (`app_account_id`),
  CONSTRAINT `FKsk6harw8nolriwfiuqosnri2` FOREIGN KEY (`app_account_id`) REFERENCES `app_account` (`app_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table paymybuddy.user : ~0 rows (environ)
INSERT INTO `user` (`birthdate`, `is_active`, `app_account_id`, `last_connection_date`, `user_id`, `address`, `email`, `firstname`, `lastname`, `password`, `phone`, `role`) VALUES
	('1980-02-05', b'1', 1, '2024-05-06 21:53:58.346173', 1, '22 rue de Poitiers, 90000 Ville', 'charles.martel@email.com', 'Charles', 'Martel', '$2a$10$Wkmt8C4rNQFLQfWzvW6N.e3wPUovWBPS4.Z.ubmPOudvq9v0OgxL6', '6654332244', 'USER'),
	('1978-06-06', b'1', 2, '2022-03-06 18:29:00.000000', 2, 'Place de Versailles, 67009 Ville', 'louiscapet@email.com', 'Louis', 'Capet', '$2a$10$Wkmt8C4rNQFLQfWzvW6N.e3wPUovWBPS4.Z.ubmPOudvq9v0OgxL6', '0022446677', 'USER'),
	('1935-02-02', b'1', 3, '2024-02-06 18:29:30.000000', 3, '13 Boulevard d\'Iéna, 78015 Paris', 'leonnapo@email.com', 'Léon', 'Napo', '$2a$10$Wkmt8C4rNQFLQfWzvW6N.e3wPUovWBPS4.Z.ubmPOudvq9v0OgxL6', '1819191922', 'USER');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
