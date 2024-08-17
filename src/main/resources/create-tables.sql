CREATE TABLE `people` (
  `person_id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `age` int NOT NULL,
  PRIMARY KEY (`person_id`)
)