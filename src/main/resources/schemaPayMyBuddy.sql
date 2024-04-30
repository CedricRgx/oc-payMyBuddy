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

-- Table paymybuddy. app_account
CREATE TABLE IF NOT EXISTS `app_account` (
`balance` double DEFAULT NULL,
`app_account_id` bigint NOT NULL AUTO_INCREMENT,
PRIMARY KEY (`app_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `app_account` (`balance`, `app_account_id`) VALUES
(157, 1),
(345, 2),
(9087, 3);

-- Table paymybuddy. assoc_user_friend
CREATE TABLE IF NOT EXISTS `assoc_user_friend` (
`friend_id` bigint NOT NULL,
`user_id` bigint NOT NULL,
KEY `FK7qxtavpiecs7ikd22rwffqhnw` (`friend_id`),
KEY `FKm1qogjemapss2g8vtmrcu0kgl` (`user_id`),
CONSTRAINT `FK7qxtavpiecs7ikd22rwffqhnw` FOREIGN KEY (`friend_id`) REFERENCES `user` (`user_id`),
CONSTRAINT `FKm1qogjemapss2g8vtmrcu0kgl` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `assoc_user_friend` (`friend_id`, `user_id`) VALUES
(1, 3),
(1, 2),
(3, 2),
(3, 1),
(2, 1);

-- Table paymybuddy. deposit
CREATE TABLE IF NOT EXISTS `deposit` (
`amount` double DEFAULT NULL,
`fee` double DEFAULT NULL,
`author_id` bigint NOT NULL,
`id` bigint NOT NULL AUTO_INCREMENT,
`transaction_date` datetime(6) DEFAULT NULL,
`description` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `FK5hjt3w60iwi4maro5xgnovewk` (`author_id`),
CONSTRAINT `FK5hjt3w60iwi4maro5xgnovewk` FOREIGN KEY (`author_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `deposit` (`amount`, `fee`, `author_id`, `id`, `transaction_date`, `description`) VALUES
(56, 5, 1, 1, '2024-02-03 18:33:00.000000', 'Dépôt 1'),
(76.5, 3, 3, 2, '2022-02-06 18:34:00.000000', 'Deuxième dépôt'),
(567.6, 25, 2, 3, '2023-02-06 18:39:00.000000', 'Deposit 3'),
(59.5, 3, 3, 2, '2023-10-06 18:34:00.000000', 'Quatrième dépôt'),
(100.0, 5, 2, 3, '2023-02-06 20:39:00.000000', 'Deposit 3');

-- Table paymybuddy. transfert
CREATE TABLE IF NOT EXISTS `transfert` (
`amount` double DEFAULT NULL,
`fee` double DEFAULT NULL,
`author_id` bigint NOT NULL,
`id` bigint NOT NULL AUTO_INCREMENT,
`recipient_id` bigint NOT NULL,
`transaction_date` datetime(6) DEFAULT NULL,
`description` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `FKms5nh1c4g3qc0wb8g7280i2h0` (`author_id`),
KEY `FKegcjgu9qp7u3h7e885cdmyxvb` (`recipient_id`),
CONSTRAINT `FKegcjgu9qp7u3h7e885cdmyxvb` FOREIGN KEY (`recipient_id`) REFERENCES `user` (`user_id`),
CONSTRAINT `FKms5nh1c4g3qc0wb8g7280i2h0` FOREIGN KEY (`author_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `transfert` (`amount`, `fee`, `author_id`, `id`, `recipient_id`, `transaction_date`, `description`) VALUES
(56, 3, 1, 1, 3, '2024-03-06 18:32:17.000000', 'ceci est une transaction'),
(314, 20, 3, 2, 1, '2023-03-06 18:32:00.000000', 'Transfert2'),
(4000, 50, 1, 3, 3, '2024-03-04 18:33:00.000000', 'Transfert trois'),
(70, 4, 1, 3, 3, '2024-03-03 16:41:57.000000', 'Transact'),
(455, 10, 1, 2, 2, '2024-03-03 16:45:53.000000', 'Transfert56'),
(65, 3, 1, 2, 3, '2024-03-03 16:46:19.000000', 'Transfert33');

-- Table paymybuddy. user
CREATE TABLE IF NOT EXISTS `user` (
`app_account_id` bigint DEFAULT NULL,
`birthdate` date DEFAULT NULL,
`user_account_id` bigint DEFAULT NULL,
`user_id` bigint NOT NULL AUTO_INCREMENT,
`firstname` varchar(255) DEFAULT NULL,
`lastname` varchar(255) DEFAULT NULL,
`phone` varchar(255) DEFAULT NULL,
`address` varchar(255) DEFAULT NULL,
PRIMARY KEY (`user_id`),
UNIQUE KEY `UK_of5ucevdpusnfngt7mub8lnac` (`app_account_id`),
UNIQUE KEY `UK_o0u0lb3mfcc21hog1f34omrii` (`user_account_id`),
CONSTRAINT `FKa2ixh18irxw16xxl1ka3gfth6` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`user_account_id`),
CONSTRAINT `FKsk6harw8nolriwfiuqosnri2` FOREIGN KEY (`app_account_id`) REFERENCES `app_account` (`app_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `user` (`app_account_id`, `birthdate`, `user_account_id`, `user_id`, `firstname`, `lastname`, `phone`, `address`) VALUES
(1, '1978-01-05', 1, 1, 'Charly', 'Martel', '6654332244', '22 rue de Poitiers, 90000 Ville'),
(2, '1977-08-02', 2, 2, 'Louis', 'Capet', '0022446677', 'Place de Versailles, 67009 Ville'),
(3, '1930-01-02', 3, 3, 'Léon', 'Napo', '1819191922', '13 Boulevard d\'Iéna, 78015 Paris');

-- Table paymybuddy. user_account
CREATE TABLE IF NOT EXISTS `user_account` (
`is_active` bit(1) DEFAULT NULL,
`last_connection_date` datetime(6) DEFAULT NULL,
`user_account_id` bigint NOT NULL AUTO_INCREMENT,
`email` varchar(255) DEFAULT NULL,
`password` varchar(255) DEFAULT NULL,
`role` varchar(255) DEFAULT NULL,
PRIMARY KEY (`user_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `user_account` (`is_active`, `last_connection_date`, `user_account_id`, `email`, `password`, `role`) VALUES
(b'1', '2024-04-22 19:24:36.000000', 1, 'charles.martel@email.com', '$2a$10$snji6Tf/l1tRfwaDsKJateUL177C/nUSKH62ZtPXC/4hplDQ3DUmW', 'USER'),
(b'1', '2024-03-16 18:09:10.727598', 2, 'louiscapet@email.com', '$2a$10$K3sIN3qDbtN7sXFYC93baO1CIcTFz/5v42VaABh6LSMj9dhpQrXue', 'USER'),
(b'1', '2024-03-16 18:17:23.604776', 3, 'leonnapo@email.com', '$2a$10$K3sIN3qDbtN7sXFYC93baO1CIcTFz/5v42VaABh6LSMj9dhpQrXue', 'USER');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
