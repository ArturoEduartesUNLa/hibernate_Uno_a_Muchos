CREATE DATABASE  IF NOT EXISTS `bd-hibernate-uno-a-muchos_1` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bd-hibernate-uno-a-muchos_1`;

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `dni` int(11) NOT NULL,
  `fechaDeNacimiento` date DEFAULT NULL,
  `baja` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


LOCK TABLES `cliente` WRITE;
INSERT INTO `cliente` VALUES (1,'Jaramillo','Ana María',14000000,'1960-09-10','\0');
UNLOCK TABLES;

DROP TABLE IF EXISTS `prestamo`;

CREATE TABLE `prestamo` (
  `idPrestamo` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `monto` double NOT NULL,
  `interes` double NOT NULL,
  `cantCuotas` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  PRIMARY KEY (`idPrestamo`),
  KEY `fkCliente_idx` (`idCliente`),
  CONSTRAINT `fkCliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

LOCK TABLES `prestamo` WRITE;

INSERT INTO `prestamo` VALUES (1,'2016-01-08 00:00:00',5000,25,10,1),(2,'2015-11-02 00:00:00',10000,15,15,1);
UNLOCK TABLES;
