-- `sesame-user-service`.users definition
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                       `id` bigint NOT NULL AUTO_INCREMENT,
                       `username` varchar(255) COLLATE utf8mb4_bin NOT NULL,
                       `fullname` varchar(255) COLLATE utf8mb4_bin NOT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;