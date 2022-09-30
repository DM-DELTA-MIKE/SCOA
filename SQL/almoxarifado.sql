-- MySQL Script generated by MySQL Workbench
-- Thu Sep 29 23:27:21 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Scoa
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Scoa
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Scoa` DEFAULT CHARACTER SET utf8 ;
USE `Scoa` ;

-- -----------------------------------------------------
-- Table `Scoa`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Scoa`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Telefone` VARCHAR(45) NOT NULL,
  `Cpf` VARCHAR(45) NOT NULL,
  `Tipo` VARCHAR(45) NOT NULL,
  `Data_Cadastro` DATETIME NOT NULL,
  `Data_Alteracao` DATETIME NOT NULL,
  `Senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Scoa`.`Bem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Scoa`.`Bem` (
  `idBem` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NULL,
  `Tombo` VARCHAR(45) NULL,
  `Setor` VARCHAR(45) NULL,
  `Tipo` VARCHAR(45) NULL,
  PRIMARY KEY (`idBem`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Scoa`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Scoa`.`Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `Estoque_Max` INT NULL,
  `Estoque_Min` INT NULL,
  `Referencia` VARCHAR(45) NULL,
  `Localizacao` VARCHAR(45) NULL,
  `Codigo_Barras` VARCHAR(45) NULL,
  `Nome` VARCHAR(45) NULL,
  `Tipo` VARCHAR(45) NULL,
  PRIMARY KEY (`idProduto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Scoa`.`Relatorio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Scoa`.`Relatorio` (
  `idRelatorio` INT NOT NULL AUTO_INCREMENT,
  `Data` DATETIME NULL,
  `Quantidade` INT NULL,
  `Tipo` VARCHAR(45) NULL,
  `idProduto` INT NULL,
  PRIMARY KEY (`idRelatorio`),
  INDEX `idProduto_idx` (`idProduto` ASC) VISIBLE,
  CONSTRAINT `idProduto`
    FOREIGN KEY (`idProduto`)
    REFERENCES `Scoa`.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
