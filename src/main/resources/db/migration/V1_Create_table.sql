-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;
CREATE SCHEMA IF NOT EXISTS drone_feeder;
 USE drone_feeder;

CREATE TABLE `Drone` (
  `id` int NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Drone_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 CREATE TABLE `Position` (
   `id` int NOT NULL,
   `latitude` varchar(255) DEFAULT NULL,
   `longitude` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 CREATE TABLE `Position_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 CREATE TABLE `Video` (
   `id` int NOT NULL,
   `link` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 CREATE TABLE `Video_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Delivery` (
  `drone_id` int DEFAULT NULL,
  `id` int NOT NULL,
  `position_id` int DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `video_id` int DEFAULT NULL,
  `delivery_date` datetime(6) DEFAULT NULL,
  `schedule_delivery` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_83147990owwp4ifnvs635xsfm` (`position_id`),
  UNIQUE KEY `UK_p660eicmipvpvvk37b5xsd8mw` (`video_id`),
  KEY `FKk2rckwg2iu337eo22s6b05b80` (`drone_id`),
  CONSTRAINT `FKk2rckwg2iu337eo22s6b05b80` FOREIGN KEY (`drone_id`) REFERENCES `Drone` (`id`),
  CONSTRAINT `FKsms88seccdu6eid0tqilsc4ps` FOREIGN KEY (`position_id`) REFERENCES `Position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Delivery_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci