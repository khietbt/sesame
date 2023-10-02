-- `sesame-user-service`.users definition
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                       `id` bigint NOT NULL AUTO_INCREMENT,
                       `uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                       `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                       `fullname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                       `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
                       `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                       `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                       `created_at` datetime(6) NOT NULL DEFAULT (CURRENT_TIMESTAMP(6)),
                       `updated_at` datetime(6) NOT NULL DEFAULT (CURRENT_TIMESTAMP(6)),
                       PRIMARY KEY (`id`),
                       UNIQUE KEY `idx_users_01` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;