-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.51b-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sislodim
--

CREATE DATABASE IF NOT EXISTS sislodim;
USE sislodim;

--
-- Definition of table `sis_bairro`
--

DROP TABLE IF EXISTS `sis_bairro`;
CREATE TABLE `sis_bairro` (
  `BAI_CODIGO` int(11) NOT NULL auto_increment,
  `BAI_NOME` varchar(255) collate latin1_general_ci default NULL,
  PRIMARY KEY  (`BAI_CODIGO`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `sis_bairro`
--

/*!40000 ALTER TABLE `sis_bairro` DISABLE KEYS */;
INSERT INTO `sis_bairro` (`BAI_CODIGO`,`BAI_NOME`) VALUES 
 (1,'Água Fria'),
 (2,'Altiplano Cabo Branco'),
 (3,'Alto do Mateus'),
 (4,'Bessa'),
 (5,'Brisamar'),
 (6,'Cabo Branco'),
 (7,'Castelo Branco'),
 (8,'Centro'),
 (9,'Funcionários'),
 (10,'Cordão Encarnado'),
 (11,'Costa e Silva'),
 (12,'Cristo Redentor'),
 (13,'Cruz das Armas'),
 (14,'Distrito Industrial'),
 (15,'Ernani Sátiro'),
 (16,'Ernesto Geisel'),
 (17,'Estados'),
 (18,'Expedicionários'),
 (19,'Funcionários II'),
 (20,'Ilha do Bispo'),
 (21,'Indústrias'),
 (22,'Ipês'),
 (23,'Jaguaribe'),
 (24,'Jardim Cidade Universitária'),
 (25,'Jardim Planalto'),
 (26,'Jardim Veneza'),
 (27,'José Américo de Almeida'),
 (28,'Manaíra'),
 (29,'Mandacaru'),
 (30,'Mangabeira'),
 (31,'Marés'),
 (32,'Miramar'),
 (33,'Novais'),
 (34,'Rangel'),
 (35,'Roger'),
 (36,'São José'),
 (37,'Ponta do Seixas'),
 (38,'Tambaú'),
 (39,'Tambauzinho'),
 (40,'Tambiá'),
 (41,'Torre'),
 (42,'Treze de Maio'),
 (43,'Valentina de Figueiredo'),
 (44,'Varadouro'),
 (45,'Portal do Sol'),
 (46,'Muçumagro'),
 (47,'Jardim das Acácias'),
 (48,'Jardim São Paulo'),
 (49,'Anatólia'),
 (50,'Bancários'),
 (51,'Cidade dos Colibris'),
 (52,'Cuiá'),
 (53,'João Paulo II');
/*!40000 ALTER TABLE `sis_bairro` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
