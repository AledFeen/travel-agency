-- MySQL Script generated by MySQL Workbench
-- Tue Feb 13 15:22:32 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema travelAgency
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema travelAgency
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `travelAgency` DEFAULT CHARACTER SET utf8 ;
USE `travelAgency` ;

-- -----------------------------------------------------
-- Table `travelAgency`.`Country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Country` (
  `idCountry` MEDIUMINT NOT NULL,
  `countryName` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`idCountry`),
  UNIQUE INDEX `idCountry_UNIQUE` (`idCountry` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Firm`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Firm` (
  `idGroup` MEDIUMINT NOT NULL,
  `firmName` VARCHAR(45) NOT NULL,
  `idCountry` MEDIUMINT NOT NULL,
  PRIMARY KEY (`idGroup`),
  INDEX `fk_Group_Country_idx` (`idCountry` ASC) VISIBLE,
  UNIQUE INDEX `idGroup_UNIQUE` (`idGroup` ASC) VISIBLE,
  CONSTRAINT `fk_Group_Country`
    FOREIGN KEY (`idCountry`)
    REFERENCES `travelAgency`.`Country` (`idCountry`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Hotel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Hotel` (
  `idHotel` MEDIUMINT NOT NULL AUTO_INCREMENT,
  `hotelName` VARCHAR(45) NOT NULL,
  `rank` TINYINT(1) NOT NULL,
  `city` VARCHAR(80) NOT NULL,
  `location` VARCHAR(100) NOT NULL,
  `idGroup` MEDIUMINT NOT NULL,
  PRIMARY KEY (`idHotel`),
  UNIQUE INDEX `idHotel_UNIQUE` (`idHotel` ASC) VISIBLE,
  INDEX `fk_Hotel_Group1_idx` (`idGroup` ASC) VISIBLE,
  CONSTRAINT `fk_Hotel_Group1`
    FOREIGN KEY (`idGroup`)
    REFERENCES `travelAgency`.`Firm` (`idGroup`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Establish_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Establish_type` (
  `idEstablishType` TINYINT NOT NULL,
  `estTypeName` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idEstablishType`),
  UNIQUE INDEX `idEstablishType_UNIQUE` (`idEstablishType` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Establishment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Establishment` (
  `idEstablishment` INT NOT NULL AUTO_INCREMENT,
  `estName` VARCHAR(45) NOT NULL,
  `estDescription` VARCHAR(255) NOT NULL,
  `idHotel` MEDIUMINT NOT NULL,
  `idType` TINYINT NOT NULL,
  PRIMARY KEY (`idEstablishment`),
  UNIQUE INDEX `idEstablishment_UNIQUE` (`idEstablishment` ASC) VISIBLE,
  INDEX `fk_Establishment_Hotel1_idx` (`idHotel` ASC) VISIBLE,
  INDEX `fk_Establishment_EstablishType1_idx` (`idType` ASC) VISIBLE,
  CONSTRAINT `fk_Establishment_Hotel1`
    FOREIGN KEY (`idHotel`)
    REFERENCES `travelAgency`.`Hotel` (`idHotel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Establishment_EstablishType1`
    FOREIGN KEY (`idType`)
    REFERENCES `travelAgency`.`Establish_type` (`idEstablishType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Facility`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Facility` (
  `idFacility` MEDIUMINT NOT NULL AUTO_INCREMENT,
  `facilityName` VARCHAR(45) NOT NULL,
  `facilityDescription` VARCHAR(255) NOT NULL,
  `idHotel` MEDIUMINT NOT NULL,
  PRIMARY KEY (`idFacility`),
  INDEX `fk_Facility_Hotel1_idx` (`idHotel` ASC) VISIBLE,
  CONSTRAINT `fk_Facility_Hotel1`
    FOREIGN KEY (`idHotel`)
    REFERENCES `travelAgency`.`Hotel` (`idHotel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Hotel_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Hotel_image` (
  `idImage` INT NOT NULL AUTO_INCREMENT,
  `content_type` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL,
  `isPreviewImage` TINYINT(1) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `original_file_name` VARCHAR(255) NOT NULL,
  `size` MEDIUMINT NOT NULL,
  `bytes` MEDIUMBLOB NOT NULL,
  `idParent` MEDIUMINT NOT NULL,
  PRIMARY KEY (`idImage`),
  INDEX `fk_HotelImage_Hotel1_idx` (`idParent` ASC) VISIBLE,
  UNIQUE INDEX `idImage_UNIQUE` (`idImage` ASC) VISIBLE,
  CONSTRAINT `fk_HotelImage_Hotel1`
    FOREIGN KEY (`idParent`)
    REFERENCES `travelAgency`.`Hotel` (`idHotel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Room` (
  `idRoom` MEDIUMINT NOT NULL AUTO_INCREMENT,
  `bed` VARCHAR(45) NOT NULL,
  `class` VARCHAR(45) NOT NULL,
  `roomsCount` VARCHAR(45) NOT NULL,
  `cleaning` VARCHAR(45) NOT NULL,
  `dailyPrice` SMALLINT NOT NULL,
  `idHotel` MEDIUMINT NOT NULL,
  PRIMARY KEY (`idRoom`),
  UNIQUE INDEX `idRoom_UNIQUE` (`idRoom` ASC) VISIBLE,
  INDEX `fk_Room_Hotel1_idx` (`idHotel` ASC) VISIBLE,
  CONSTRAINT `fk_Room_Hotel1`
    FOREIGN KEY (`idHotel`)
    REFERENCES `travelAgency`.`Hotel` (`idHotel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Room_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Room_image` (
  `idImage` INT NOT NULL AUTO_INCREMENT,
  `content_type` VARCHAR(255) NOT NULL,
  `isPreviewImage` TINYINT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `original_file_name` VARCHAR(255) NOT NULL,
  `size` MEDIUMINT NOT NULL,
  `bytes` MEDIUMBLOB NOT NULL,
  `idParent` MEDIUMINT NOT NULL,
  PRIMARY KEY (`idImage`),
  INDEX `fk_RoomImage_Room1_idx` (`idParent` ASC) VISIBLE,
  UNIQUE INDEX `idImage_UNIQUE` (`idImage` ASC) VISIBLE,
  CONSTRAINT `fk_RoomImage_Room1`
    FOREIGN KEY (`idParent`)
    REFERENCES `travelAgency`.`Room` (`idRoom`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Amenity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Amenity` (
  `idAmenity` SMALLINT NOT NULL,
  `amName` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idAmenity`),
  UNIQUE INDEX `idAmanities_UNIQUE` (`idAmenity` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Room_Amenity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Room_Amenity` (
  `idRoom` MEDIUMINT NOT NULL,
  `idAmenity` SMALLINT NOT NULL,
  INDEX `fk_Room_has_Amanities_Amanities1_idx` (`idAmenity` ASC) VISIBLE,
  INDEX `fk_Room_has_Amanities_Room1_idx` (`idRoom` ASC) VISIBLE,
  PRIMARY KEY (`idRoom`, `idAmenity`),
  CONSTRAINT `fk_Room_has_Amanities_Room1`
    FOREIGN KEY (`idRoom`)
    REFERENCES `travelAgency`.`Room` (`idRoom`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Room_has_Amanities_Amanities1`
    FOREIGN KEY (`idAmenity`)
    REFERENCES `travelAgency`.`Amenity` (`idAmenity`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`FoodType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`FoodType` (
  `idFoodType` SMALLINT NOT NULL,
  `ftName` VARCHAR(45) NOT NULL,
  `ftDescription` VARCHAR(100) NULL,
  PRIMARY KEY (`idFoodType`),
  UNIQUE INDEX `idFoodType_UNIQUE` (`idFoodType` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Hotel_FoodType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Hotel_FoodType` (
  `idHotel` MEDIUMINT NOT NULL,
  `idFoodType` SMALLINT NOT NULL,
  `id` MEDIUMINT NOT NULL AUTO_INCREMENT,
  `price` SMALLINT NOT NULL,
  INDEX `fk_Hotel_has_FoodType_FoodType1_idx` (`idFoodType` ASC) VISIBLE,
  INDEX `fk_Hotel_has_FoodType_Hotel1_idx` (`idHotel` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_Hotel_has_FoodType_Hotel1`
    FOREIGN KEY (`idHotel`)
    REFERENCES `travelAgency`.`Hotel` (`idHotel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Hotel_has_FoodType_FoodType1`
    FOREIGN KEY (`idFoodType`)
    REFERENCES `travelAgency`.`FoodType` (`idFoodType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Reservation` (
  `idReservation` INT NOT NULL AUTO_INCREMENT,
  `startData` DATE NOT NULL,
  `endData` DATE NOT NULL,
  `idHotel` MEDIUMINT NOT NULL,
  `minPriceReserv` MEDIUMINT NULL,
  `maxPriceReserv` MEDIUMINT NULL,
  PRIMARY KEY (`idReservation`),
  UNIQUE INDEX `idReservation_UNIQUE` (`idReservation` ASC) VISIBLE,
  INDEX `fk_Reservation_Hotel1_idx` (`idHotel` ASC) VISIBLE,
  CONSTRAINT `fk_Reservation_Hotel1`
    FOREIGN KEY (`idHotel`)
    REFERENCES `travelAgency`.`Hotel` (`idHotel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Route` (
  `idRoute` MEDIUMINT NOT NULL AUTO_INCREMENT,
  `rName` VARCHAR(100) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `peopleCount` TINYINT NOT NULL,
  `startData` DATE NOT NULL,
  `endData` DATE NOT NULL,
  `minPriceRoute` MEDIUMINT NULL,
  `maxPriceRoute` MEDIUMINT NULL,
  `transportPrice` MEDIUMINT NOT NULL,
  `publish` TINYINT NOT NULL,
  PRIMARY KEY (`idRoute`),
  UNIQUE INDEX `idRoute_UNIQUE` (`idRoute` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`StartPlace`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`StartPlace` (
  `idstartPlace` TINYINT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(80) NOT NULL,
  `type` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`idstartPlace`),
  UNIQUE INDEX `idstartPlace_UNIQUE` (`idstartPlace` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Route_Reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Route_Reservation` (
  `idRoute` MEDIUMINT NOT NULL,
  `idReservation` INT NOT NULL,
  PRIMARY KEY (`idRoute`, `idReservation`),
  INDEX `fk_Route_has_Reservation_Reservation1_idx` (`idReservation` ASC) VISIBLE,
  INDEX `fk_Route_has_Reservation_Route1_idx` (`idRoute` ASC) VISIBLE,
  CONSTRAINT `fk_Route_has_Reservation_Route1`
    FOREIGN KEY (`idRoute`)
    REFERENCES `travelAgency`.`Route` (`idRoute`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Route_has_Reservation_Reservation1`
    FOREIGN KEY (`idReservation`)
    REFERENCES `travelAgency`.`Reservation` (`idReservation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`User` (
  `username` VARCHAR(80) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `enabled` TINYINT NOT NULL,
  `role` VARCHAR(25) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `secondName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `idUser_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Rewiev`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Rewiev` (
  `idRewiev` MEDIUMINT NOT NULL AUTO_INCREMENT,
  `idRoute` MEDIUMINT NOT NULL,
  `userLogin` VARCHAR(80) NOT NULL,
  `rank` TINYINT(1) NOT NULL,
  `description` VARCHAR(60) NOT NULL,
  UNIQUE INDEX `idRewiev_UNIQUE` (`idRewiev` ASC) VISIBLE,
  PRIMARY KEY (`idRewiev`),
  INDEX `fk_Rewiev_Route1_idx` (`idRoute` ASC) VISIBLE,
  INDEX `fk_Rewiev_User1_idx` (`userLogin` ASC) VISIBLE,
  CONSTRAINT `fk_Rewiev_Route1`
    FOREIGN KEY (`idRoute`)
    REFERENCES `travelAgency`.`Route` (`idRoute`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rewiev_User1`
    FOREIGN KEY (`userLogin`)
    REFERENCES `travelAgency`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Status` (
  `idStatus` TINYINT NOT NULL AUTO_INCREMENT,
  `statusName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idStatus`),
  UNIQUE INDEX `idStatus_UNIQUE` (`idStatus` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Deal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Deal` (
  `idDeal` INT NOT NULL AUTO_INCREMENT,
  `idRoute` MEDIUMINT NOT NULL,
  `userLogin` VARCHAR(50) NOT NULL,
  `totalPrice` MEDIUMINT NOT NULL,
  `idStatus` TINYINT NOT NULL,
  `idstartPlace` TINYINT NOT NULL,
  `phoneNumber` TINYINT(20) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idDeal`),
  UNIQUE INDEX `idDeal_UNIQUE` (`idDeal` ASC) VISIBLE,
  INDEX `fk_Deal_Route1_idx` (`idRoute` ASC) VISIBLE,
  INDEX `fk_Deal_User1_idx` (`userLogin` ASC) VISIBLE,
  INDEX `fk_Deal_Status1_idx` (`idStatus` ASC) VISIBLE,
  INDEX `fk_Deal_StartPlace1_idx` (`idstartPlace` ASC) VISIBLE,
  CONSTRAINT `fk_Deal_Route1`
    FOREIGN KEY (`idRoute`)
    REFERENCES `travelAgency`.`Route` (`idRoute`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Deal_User1`
    FOREIGN KEY (`userLogin`)
    REFERENCES `travelAgency`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Deal_Status1`
    FOREIGN KEY (`idStatus`)
    REFERENCES `travelAgency`.`Status` (`idStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Deal_StartPlace1`
    FOREIGN KEY (`idstartPlace`)
    REFERENCES `travelAgency`.`StartPlace` (`idstartPlace`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Changed`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Changed` (
  `idChanged` INT NOT NULL AUTO_INCREMENT,
  `idDeal` INT NOT NULL,
  `idReservation` INT NOT NULL,
  `idFoodType` MEDIUMINT NOT NULL,
  `idRoom` MEDIUMINT NOT NULL,
  PRIMARY KEY (`idChanged`),
  INDEX `fk_ChangedRoom_Deal1_idx` (`idDeal` ASC) VISIBLE,
  INDEX `fk_Changed_Reservation1_idx` (`idReservation` ASC) VISIBLE,
  INDEX `fk_Changed_Hotel_FoodType1_idx` (`idFoodType` ASC) VISIBLE,
  INDEX `fk_Changed_Room1_idx` (`idRoom` ASC) VISIBLE,
  CONSTRAINT `fk_ChangedRoom_Deal1`
    FOREIGN KEY (`idDeal`)
    REFERENCES `travelAgency`.`Deal` (`idDeal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Changed_Reservation1`
    FOREIGN KEY (`idReservation`)
    REFERENCES `travelAgency`.`Reservation` (`idReservation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Changed_Hotel_FoodType1`
    FOREIGN KEY (`idFoodType`)
    REFERENCES `travelAgency`.`Hotel_FoodType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Changed_Room1`
    FOREIGN KEY (`idRoom`)
    REFERENCES `travelAgency`.`Room` (`idRoom`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travelAgency`.`Route_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelAgency`.`Route_image` (
  `idImage` INT NOT NULL AUTO_INCREMENT,
  `content_type` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL,
  `isPreviewImage` TINYINT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `original_file_name` VARCHAR(255) NOT NULL,
  `size` MEDIUMINT NOT NULL,
  `bytes` MEDIUMBLOB NOT NULL,
  `idParent` MEDIUMINT NOT NULL,
  PRIMARY KEY (`idImage`),
  UNIQUE INDEX `idHotelImage_UNIQUE` (`idImage` ASC) VISIBLE,
  INDEX `fk_Hotel_image_copy1_Route1_idx` (`idParent` ASC) VISIBLE,
  UNIQUE INDEX `idParent_UNIQUE` (`idParent` ASC) VISIBLE,
  CONSTRAINT `fk_Hotel_image_copy1_Route1`
    FOREIGN KEY (`idParent`)
    REFERENCES `travelAgency`.`Route` (`idRoute`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
