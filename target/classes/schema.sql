CREATE TABLE IF NOT EXISTS `note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255),
  `content` VARCHAR(255),
  `create_date` DATETIME NULL,
  `modify_date` DATETIME NULL,
  PRIMARY KEY (`id`));