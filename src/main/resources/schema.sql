DROP TABLE if exists `crypto`;

CREATE TABLE `crypto` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `amount` decimal(19,2) DEFAULT NULL,
                          `created_at` datetime(6) DEFAULT NULL,
                          `name` varchar(150) DEFAULT NULL,
                          `purchase_market_value` decimal(19,2) DEFAULT NULL,
                          `wallet` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
