-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dirs_and_files_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dirs_and_files_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dirs_and_files_db` DEFAULT CHARACTER SET utf8 ;
USE `dirs_and_files_db` ;

-- -----------------------------------------------------
-- Table `dirs_and_files_db`.`directory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dirs_and_files_db`.`directory` (
  `directory_id` INT NOT NULL AUTO_INCREMENT,
  `path` VARCHAR(400) NOT NULL,
  `added` DATETIME NOT NULL,
  PRIMARY KEY (`directory_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dirs_and_files_db`.`dir_file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dirs_and_files_db`.`dir_file` (
  `file_id` INT NOT NULL AUTO_INCREMENT,
  `directory_id` INT NOT NULL,
  `file_name` VARCHAR(300) NOT NULL,
  `is_directory` TINYINT(1) NOT NULL,
  `size` BIGINT(19) NULL,
  PRIMARY KEY (`file_id`),
  INDEX `fk_dir_file_directory_idx` (`directory_id` ASC) VISIBLE,
  CONSTRAINT `fk_dir_file_directory`
    FOREIGN KEY (`directory_id`)
    REFERENCES `dirs_and_files_db`.`directory` (`directory_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
