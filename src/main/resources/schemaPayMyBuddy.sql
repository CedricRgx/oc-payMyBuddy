-- Listage de la structure de la table paymybuddy.app_account
CREATE TABLE IF NOT EXISTS `app_account` (
  `app_account_id` bigint NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `iban` varchar(27) DEFAULT NULL,
  PRIMARY KEY (`app_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table paymybuddy.app_account
INSERT INTO `app_account` (`app_account_id`, `balance`, `iban`) VALUES
  (1, 42, 'FR7630006000011234567890199'),
  (2, 345, 'FR7630006000011234567890199'),
  (3, 9087, 'FR7630006000011234567890199');

-- Listage de la structure de la table paymybuddy.user
CREATE TABLE IF NOT EXISTS `user` (
  `app_account_id` bigint DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `last_connection_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_of5ucevdpusnfngt7mub8lnac` (`app_account_id`),
  CONSTRAINT `FKsk6harw8nolriwfiuqosnri2` FOREIGN KEY (`app_account_id`) REFERENCES `app_account` (`app_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table paymybuddy.user
INSERT INTO `user` (`app_account_id`, `birthdate`, `user_id`, `firstname`, `lastname`, `phone`, `address`, `is_active`, `last_connection_date`, `email`, `password`, `role`) VALUES
  (1, '1980-02-05', 1, 'Charles', 'Martel', '6654332244', '22 rue de Poitiers, 90000 Ville', b'1', '2024-02-05 19:17:30.000000', 'charles.martel@email.com', '$2a$10$Wkmt8C4rNQFLQfWzvW6N.e3wPUovWBPS4.Z.ubmPOudvq9v0OgxL6', 'USER'),
  (2, '1978-06-06', 2, 'Louis', 'Capet', '0022446677', 'Place de Versailles, 67009 Ville', b'1', '2022-03-06 18:29:00.000000', 'louiscapet@email.com', '$2a$10$Wkmt8C4rNQFLQfWzvW6N.e3wPUovWBPS4.Z.ubmPOudvq9v0OgxL6', 'USER'),
  (3, '1935-02-02', 3, 'Léon', 'Napo', '1819191922', '13 Boulevard d\'Iéna, 78015 Paris', b'1', '2024-02-06 18:29:30.000000', 'leonnapo@email.com', '$2a$10$Wkmt8C4rNQFLQfWzvW6N.e3wPUovWBPS4.Z.ubmPOudvq9v0OgxL6', 'USER');

-- Listage de la structure de la table paymybuddy.assoc_user_friend
CREATE TABLE IF NOT EXISTS `assoc_user_friend` (
  `friend_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  KEY `FK7qxtavpiecs7ikd22rwffqhnw` (`friend_id`),
  KEY `FKm1qogjemapss2g8vtmrcu0kgl` (`user_id`),
  CONSTRAINT `FK7qxtavpiecs7ikd22rwffqhnw` FOREIGN KEY (`friend_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKm1qogjemapss2g8vtmrcu0kgl` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table paymybuddy.assoc_user_friend
INSERT INTO `assoc_user_friend` (`friend_id`, `user_id`) VALUES
  (1, 3),
  (1, 2),
  (2, 1),
  (3, 2);

-- Listage de la structure de la table paymybuddy.deposit
CREATE TABLE IF NOT EXISTS `deposit` (
  `amount` double DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `author_id` bigint NOT NULL,
  `deposit_id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`deposit_id`),
  KEY `FK5hjt3w60iwi4maro5xgnovewk` (`author_id`),
  CONSTRAINT `FK5hjt3w60iwi4maro5xgnovewk` FOREIGN KEY (`author_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table paymybuddy.deposit : ~3 rows (environ)
INSERT INTO `deposit` (`amount`, `fee`, `author_id`, `deposit_id`, `transaction_date`, `description`) VALUES
  (56, 5, 1, 1, '2024-02-03 18:33:00.000000', 'Dépôt 1'),
  (76.5, 3, 3, 2, '2022-02-06 18:34:00.000000', 'Deuxième dépôt'),
  (567.6, 25, 2, 3, '2023-02-06 18:39:00.000000', 'Deposit 3');

-- Listage de la structure de la table paymybuddy. transfert
CREATE TABLE IF NOT EXISTS `transfert` (
  `amount` double DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `author_id` bigint NOT NULL,
  `transfert_id` bigint NOT NULL AUTO_INCREMENT,
  `recipient_id` bigint NOT NULL,
  `transaction_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transfert_id`),
  KEY `FKms5nh1c4g3qc0wb8g7280i2h0` (`author_id`),
  KEY `FKegcjgu9qp7u3h7e885cdmyxvb` (`recipient_id`),
  CONSTRAINT `FKegcjgu9qp7u3h7e885cdmyxvb` FOREIGN KEY (`recipient_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKms5nh1c4g3qc0wb8g7280i2h0` FOREIGN KEY (`author_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table paymybuddy.transfert
INSERT INTO `transfert` (`amount`, `fee`, `author_id`, `transfert_id`, `recipient_id`, `transaction_date`, `description`) VALUES
  (56, 3, 1, 1, 3, '2024-02-06 18:32:17.000000', 'ceci est une trasnaction'),
  (314, 20, 3, 2, 1, '2023-02-06 18:32:00.000000', 'Transfert2'),
  (4000, 50, 2, 3, 3, '2024-02-04 18:33:00.000000', 'Transfert trois');

