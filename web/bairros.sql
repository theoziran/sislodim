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
  `BAI_CIDADE` int(11) NOT NULL, 
  PRIMARY KEY  (`BAI_CODIGO`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `sis_bairro`
--

/*!40000 ALTER TABLE `sis_bairro` DISABLE KEYS */;
INSERT INTO `sis_bairro` (`BAI_CODIGO`,`BAI_NOME`,`BAI_CIDADE`) VALUES 
 (1,'Água Fria',1),
 (2,'Altiplano Cabo Branco',1),
 (3,'Alto do Mateus',1),
 (4,'Bessa',1),
 (5,'Brisamar',1),
 (6,'Cabo Branco',1),
 (7,'Castelo Branco',1),
 (8,'Centro',1),
 (9,'Funcionários',1),
 (10,'Cordão Encarnado',1),
 (11,'Costa e Silva',1),
 (12,'Cristo Redentor',1),
 (13,'Cruz das Armas',1),
 (14,'Distrito Industrial',1),
 (15,'Ernani Sátiro',1),
 (16,'Ernesto Geisel',1),
 (17,'Estados',1),
 (18,'Expedicionários',1),
 (19,'Funcionários II',1),
 (20,'Ilha do Bispo',1),
 (21,'Indústrias',1),
 (22,'Ipês',1),
 (23,'Jaguaribe',1),
 (24,'Jardim Cidade Universitária',1),
 (25,'Jardim Planalto',1),
 (26,'Jardim Veneza',1),
 (27,'José Américo de Almeida',1),
 (28,'Manaíra',1),
 (29,'Mandacaru',1),
 (30,'Mangabeira',1),
 (31,'Marés',1),
 (32,'Miramar',1),
 (33,'Novais',1),
 (34,'Rangel',1),
 (35,'Roger',1),
 (36,'São José',1),
 (37,'Ponta do Seixas',1),
 (38,'Tambaú',1),
 (39,'Tambauzinho',1),
 (40,'Tambiá',1),
 (41,'Torre',1),
 (42,'Treze de Maio',1),
 (43,'Valentina de Figueiredo',1),
 (44,'Varadouro',1),
 (45,'Portal do Sol',1),
 (46,'Muçumagro',1),
 (47,'Jardim das Acácias',1),
 (48,'Jardim São Paulo',1),
 (49,'Anatólia',1),
 (50,'Bancários',1),
 (51,'Cidade dos Colibris',1),
 (52,'Cuiá',1),
 (53,'João Paulo II',1);
/*!40000 ALTER TABLE `sis_bairro` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;