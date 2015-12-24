-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: estudiofotografico
-- ------------------------------------------------------
-- Server version	5.6.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoriasprodutos`
--

DROP TABLE IF EXISTS `categoriasprodutos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoriasprodutos` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  `categoria` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UK_t48d7owbjs56qc4o3edoealpn` (`categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriasprodutos`
--

LOCK TABLES `categoriasprodutos` WRITE;
/*!40000 ALTER TABLE `categoriasprodutos` DISABLE KEYS */;
INSERT INTO `categoriasprodutos` VALUES (1,'','CAT1'),(2,'','CAT2');
/*!40000 ALTER TABLE `categoriasprodutos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoriasservicos`
--

DROP TABLE IF EXISTS `categoriasservicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoriasservicos` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  `categoria` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UK_4ftg9ovkce6ilqo8ob3hia946` (`categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriasservicos`
--

LOCK TABLES `categoriasservicos` WRITE;
/*!40000 ALTER TABLE `categoriasservicos` DISABLE KEYS */;
INSERT INTO `categoriasservicos` VALUES (1,'','SERV1'),(2,'','SERV 2');
/*!40000 ALTER TABLE `categoriasservicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `Endereco` varchar(100) NOT NULL,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  `bairro` varchar(40) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `cnpj` varchar(18) DEFAULT NULL,
  `complemento` varchar(15) DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `cpfResponsavel` varchar(14) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `nascimento` date DEFAULT NULL,
  `nome` varchar(100) NOT NULL,
  `numero` int(11) NOT NULL,
  `observacao` longtext,
  `pessoa` bit(1) NOT NULL,
  `responsavel` varchar(100) DEFAULT NULL,
  `rg` varchar(13) DEFAULT NULL,
  `rgResponsavel` varchar(13) DEFAULT NULL,
  `sexo` bit(1) DEFAULT NULL,
  `cadastrado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UK_mgyg34m52whan6mai4doelg68` (`cnpj`),
  UNIQUE KEY `UK_9j56at8usrmv9yisth6cbpex7` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (9,'Rua Aristides Thomaz Ballerini','','Jardim Ipê','37704-206','Poços de Caldas',NULL,'','101.042.346-01','   .   .   -  ',12,NULL,'sdasd',175,'','','','sdadad','',NULL,'\0'),(10,'Rua João Batista Pancini','','Nossa Senhora Aparecida','37701-127','Poços de Caldas',NULL,'','732.395.642-03','   .   .   -  ',12,NULL,'sdasdassdadsdsadasda',41,'','','','dsad','',NULL,'');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes_emailsclientes`
--

DROP TABLE IF EXISTS `clientes_emailsclientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes_emailsclientes` (
  `Clientes_codigo` bigint(20) NOT NULL,
  `emails_codigo` bigint(20) NOT NULL,
  UNIQUE KEY `UK_ct0opxnhfi120cr5b039ji9tr` (`emails_codigo`),
  KEY `FK_rstf50qr2a0b6pn3hprq9y4vq` (`Clientes_codigo`),
  CONSTRAINT `FK_ct0opxnhfi120cr5b039ji9tr` FOREIGN KEY (`emails_codigo`) REFERENCES `emailsclientes` (`codigo`),
  CONSTRAINT `FK_rstf50qr2a0b6pn3hprq9y4vq` FOREIGN KEY (`Clientes_codigo`) REFERENCES `clientes` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes_emailsclientes`
--

LOCK TABLES `clientes_emailsclientes` WRITE;
/*!40000 ALTER TABLE `clientes_emailsclientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientes_emailsclientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes_telefonesclientes`
--

DROP TABLE IF EXISTS `clientes_telefonesclientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes_telefonesclientes` (
  `Clientes_codigo` bigint(20) NOT NULL,
  `telefones_codigo` bigint(20) NOT NULL,
  UNIQUE KEY `UK_xuh1ykfw66sn4cn95ptpc0lb` (`telefones_codigo`),
  KEY `FK_k9esjhkge7t3j4d2nlsatdkry` (`Clientes_codigo`),
  CONSTRAINT `FK_k9esjhkge7t3j4d2nlsatdkry` FOREIGN KEY (`Clientes_codigo`) REFERENCES `clientes` (`codigo`),
  CONSTRAINT `FK_xuh1ykfw66sn4cn95ptpc0lb` FOREIGN KEY (`telefones_codigo`) REFERENCES `telefonesclientes` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes_telefonesclientes`
--

LOCK TABLES `clientes_telefonesclientes` WRITE;
/*!40000 ALTER TABLE `clientes_telefonesclientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientes_telefonesclientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emailsclientes`
--

DROP TABLE IF EXISTS `emailsclientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emailsclientes` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emailsclientes`
--

LOCK TABLES `emailsclientes` WRITE;
/*!40000 ALTER TABLE `emailsclientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `emailsclientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emailsfornecedores`
--

DROP TABLE IF EXISTS `emailsfornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emailsfornecedores` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emailsfornecedores`
--

LOCK TABLES `emailsfornecedores` WRITE;
/*!40000 ALTER TABLE `emailsfornecedores` DISABLE KEYS */;
/*!40000 ALTER TABLE `emailsfornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emailslocaiseventos`
--

DROP TABLE IF EXISTS `emailslocaiseventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emailslocaiseventos` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emailslocaiseventos`
--

LOCK TABLES `emailslocaiseventos` WRITE;
/*!40000 ALTER TABLE `emailslocaiseventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `emailslocaiseventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `cnpj` varchar(18) NOT NULL,
  `Endereco` varchar(100) NOT NULL,
  `bairro` varchar(40) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `complemento` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `fax` varchar(17) DEFAULT NULL,
  `nome` varchar(100) NOT NULL,
  `numero` int(11) NOT NULL,
  `site` varchar(100) DEFAULT NULL,
  `telefone` varchar(17) NOT NULL,
  PRIMARY KEY (`cnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES ('19.022.865/0001-87','Rua Aristides Thomaz Ballerini','Jardim Ipê','37704-206','Poços de Caldas','','rodu.pereira@gmail.com',12,'(  )       -     ','dsadsdsa',175,'www.google.com.br','(11) 11111 - 1111');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipamentos`
--

DROP TABLE IF EXISTS `equipamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipamentos` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  `dataCompra` date DEFAULT NULL,
  `equipamento` varchar(70) NOT NULL,
  `garantia` varchar(10) DEFAULT NULL,
  `localCompra` varchar(100) DEFAULT NULL,
  `numeroSerie` varchar(30) DEFAULT NULL,
  `obs` longtext,
  `obsSituacao` varchar(50) DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `situacao` int(11) NOT NULL,
  `marca_codigo` bigint(20) DEFAULT NULL,
  `tipo_codigo` bigint(20) DEFAULT NULL,
  `fornecedor_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_s1pj8j5ky30uwi49fkfjmpfq1` (`marca_codigo`),
  KEY `FK_at9dc9pbjayj5pmeb9vf2y0av` (`tipo_codigo`),
  KEY `FK_mbrdvgg4oxfdndk4vjputdyv3` (`fornecedor_codigo`),
  CONSTRAINT `FK_mbrdvgg4oxfdndk4vjputdyv3` FOREIGN KEY (`fornecedor_codigo`) REFERENCES `fornecedores` (`codigo`),
  CONSTRAINT `FK_at9dc9pbjayj5pmeb9vf2y0av` FOREIGN KEY (`tipo_codigo`) REFERENCES `tiposequipamentos` (`codigo`),
  CONSTRAINT `FK_s1pj8j5ky30uwi49fkfjmpfq1` FOREIGN KEY (`marca_codigo`) REFERENCES `marcasequipamentos` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipamentos`
--

LOCK TABLES `equipamentos` WRITE;
/*!40000 ALTER TABLE `equipamentos` DISABLE KEYS */;
INSERT INTO `equipamentos` VALUES (2,'',NULL,'eq','','','','','',30,0,1,1,1),(3,'',NULL,'eq2','','','','','',40,0,4,2,NULL),(4,'','2014-04-11','eq3','','','','','',30,3,4,2,1);
/*!40000 ALTER TABLE `equipamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedores`
--

DROP TABLE IF EXISTS `fornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedores` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `Endereco` varchar(100) NOT NULL,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  `bairro` varchar(40) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `cnpj` varchar(18) DEFAULT NULL,
  `complemento` varchar(15) DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `cpfResponsavel` varchar(14) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `nascimento` date DEFAULT NULL,
  `nome` varchar(100) NOT NULL,
  `numero` int(11) NOT NULL,
  `observacao` longtext,
  `pessoa` bit(1) NOT NULL,
  `responsavel` varchar(100) DEFAULT NULL,
  `rg` varchar(13) DEFAULT NULL,
  `rgResponsavel` varchar(13) DEFAULT NULL,
  `sexo` bit(1) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UK_ldvj7k2pwwjyf0r77t3gd2jho` (`cnpj`),
  UNIQUE KEY `UK_kp63e37d412pnlqo09h8m3913` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedores`
--

LOCK TABLES `fornecedores` WRITE;
/*!40000 ALTER TABLE `fornecedores` DISABLE KEYS */;
INSERT INTO `fornecedores` VALUES (1,'Rua Aristides Thomaz Ballerini','','Jardim Ipê','37704-206','Poços de Caldas','  .   .   /    -  ','','101.042.346-01','   .   .   -  ',12,NULL,'Rogério Eduardo Pererira',175,'','','','mg-19.092.714','',NULL);
/*!40000 ALTER TABLE `fornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedores_emailsfornecedores`
--

DROP TABLE IF EXISTS `fornecedores_emailsfornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedores_emailsfornecedores` (
  `Fornecedores_codigo` bigint(20) NOT NULL,
  `emails_codigo` bigint(20) NOT NULL,
  UNIQUE KEY `UK_8np7wb5dl9vstdw57epcr08q3` (`emails_codigo`),
  KEY `FK_2sd3v6v82hklyi8dju3xliapu` (`Fornecedores_codigo`),
  CONSTRAINT `FK_2sd3v6v82hklyi8dju3xliapu` FOREIGN KEY (`Fornecedores_codigo`) REFERENCES `fornecedores` (`codigo`),
  CONSTRAINT `FK_8np7wb5dl9vstdw57epcr08q3` FOREIGN KEY (`emails_codigo`) REFERENCES `emailsfornecedores` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedores_emailsfornecedores`
--

LOCK TABLES `fornecedores_emailsfornecedores` WRITE;
/*!40000 ALTER TABLE `fornecedores_emailsfornecedores` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornecedores_emailsfornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedores_telefonesfornecedores`
--

DROP TABLE IF EXISTS `fornecedores_telefonesfornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedores_telefonesfornecedores` (
  `Fornecedores_codigo` bigint(20) NOT NULL,
  `telefones_codigo` bigint(20) NOT NULL,
  UNIQUE KEY `UK_n9f79le4q9sfk7r2496kn51ii` (`telefones_codigo`),
  KEY `FK_cn56f0aabfhvi8nqvd8i64uj5` (`Fornecedores_codigo`),
  CONSTRAINT `FK_cn56f0aabfhvi8nqvd8i64uj5` FOREIGN KEY (`Fornecedores_codigo`) REFERENCES `fornecedores` (`codigo`),
  CONSTRAINT `FK_n9f79le4q9sfk7r2496kn51ii` FOREIGN KEY (`telefones_codigo`) REFERENCES `telefonesfornecedores` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedores_telefonesfornecedores`
--

LOCK TABLES `fornecedores_telefonesfornecedores` WRITE;
/*!40000 ALTER TABLE `fornecedores_telefonesfornecedores` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornecedores_telefonesfornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locaiseventos`
--

DROP TABLE IF EXISTS `locaiseventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locaiseventos` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `Endereco` varchar(100) NOT NULL,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  `bairro` varchar(40) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `complemento` varchar(15) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `numero` int(11) NOT NULL,
  `observacao` longtext,
  `responsavel` varchar(100) DEFAULT NULL,
  `site` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locaiseventos`
--

LOCK TABLES `locaiseventos` WRITE;
/*!40000 ALTER TABLE `locaiseventos` DISABLE KEYS */;
INSERT INTO `locaiseventos` VALUES (1,'Rua João Batista Pancini','','Nossa Senhora Aparecida','37701-127','Poços de Caldas','',12,'Local 1',41,'','','');
/*!40000 ALTER TABLE `locaiseventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locaiseventos_emailslocaiseventos`
--

DROP TABLE IF EXISTS `locaiseventos_emailslocaiseventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locaiseventos_emailslocaiseventos` (
  `LocaisEventos_codigo` bigint(20) NOT NULL,
  `emails_codigo` bigint(20) NOT NULL,
  UNIQUE KEY `UK_dxigyojh0xhhyjhciqqnxdbwe` (`emails_codigo`),
  KEY `FK_87r2ciupsqau6dsjvge1fljhb` (`LocaisEventos_codigo`),
  CONSTRAINT `FK_87r2ciupsqau6dsjvge1fljhb` FOREIGN KEY (`LocaisEventos_codigo`) REFERENCES `locaiseventos` (`codigo`),
  CONSTRAINT `FK_dxigyojh0xhhyjhciqqnxdbwe` FOREIGN KEY (`emails_codigo`) REFERENCES `emailslocaiseventos` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locaiseventos_emailslocaiseventos`
--

LOCK TABLES `locaiseventos_emailslocaiseventos` WRITE;
/*!40000 ALTER TABLE `locaiseventos_emailslocaiseventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `locaiseventos_emailslocaiseventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locaiseventos_telefoneslocaiseventos`
--

DROP TABLE IF EXISTS `locaiseventos_telefoneslocaiseventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locaiseventos_telefoneslocaiseventos` (
  `LocaisEventos_codigo` bigint(20) NOT NULL,
  `telefones_codigo` bigint(20) NOT NULL,
  UNIQUE KEY `UK_3tj8p87mee1vis7aoamhq8wo2` (`telefones_codigo`),
  KEY `FK_c8ytq6q58r1724nq1qrfum982` (`LocaisEventos_codigo`),
  CONSTRAINT `FK_3tj8p87mee1vis7aoamhq8wo2` FOREIGN KEY (`telefones_codigo`) REFERENCES `telefoneslocaiseventos` (`codigo`),
  CONSTRAINT `FK_c8ytq6q58r1724nq1qrfum982` FOREIGN KEY (`LocaisEventos_codigo`) REFERENCES `locaiseventos` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locaiseventos_telefoneslocaiseventos`
--

LOCK TABLES `locaiseventos_telefoneslocaiseventos` WRITE;
/*!40000 ALTER TABLE `locaiseventos_telefoneslocaiseventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `locaiseventos_telefoneslocaiseventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcasequipamentos`
--

DROP TABLE IF EXISTS `marcasequipamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marcasequipamentos` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  `marca` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UK_kdmlxkhytcuh3a3mh61guaaw4` (`marca`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcasequipamentos`
--

LOCK TABLES `marcasequipamentos` WRITE;
/*!40000 ALTER TABLE `marcasequipamentos` DISABLE KEYS */;
INSERT INTO `marcasequipamentos` VALUES (1,'','TESTE'),(4,'','TESTE4');
/*!40000 ALTER TABLE `marcasequipamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamentovendas`
--

DROP TABLE IF EXISTS `pagamentovendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagamentovendas` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `formaPagamento` int(11) NOT NULL,
  `obs` varchar(1000) DEFAULT NULL,
  `pago` bit(1) NOT NULL,
  `recebido` double DEFAULT NULL,
  `valor` double NOT NULL,
  `vencimento` date NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamentovendas`
--

LOCK TABLES `pagamentovendas` WRITE;
/*!40000 ALTER TABLE `pagamentovendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagamentovendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtos` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  `obs` longtext,
  `produto` varchar(70) NOT NULL,
  `valor` double DEFAULT NULL,
  `categoria_codigo` bigint(20) DEFAULT NULL,
  `fornecedor_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_od01eujw349xf8a9hgdx6flgh` (`categoria_codigo`),
  KEY `FK_lkpsv7tiug6ao1h42i6agmsps` (`fornecedor_codigo`),
  CONSTRAINT `FK_lkpsv7tiug6ao1h42i6agmsps` FOREIGN KEY (`fornecedor_codigo`) REFERENCES `fornecedores` (`codigo`),
  CONSTRAINT `FK_od01eujw349xf8a9hgdx6flgh` FOREIGN KEY (`categoria_codigo`) REFERENCES `categoriasprodutos` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (1,'','','PROD 1-1',30,1,NULL),(2,'','','PROD 2-1',35,1,1),(3,'','','PROD1-2',40,2,1),(4,'','','PROD2-2',45,2,NULL),(5,'','','grand',10000,1,NULL),(6,'','','prod 6',30,1,1);
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtosvendas`
--

DROP TABLE IF EXISTS `produtosvendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtosvendas` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `desconto` double DEFAULT NULL,
  `quantidade` int(11) NOT NULL,
  `valor` double NOT NULL,
  `produto_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_ln5dix0e7wcxk1olgawd3yqqb` (`produto_codigo`),
  CONSTRAINT `FK_ln5dix0e7wcxk1olgawd3yqqb` FOREIGN KEY (`produto_codigo`) REFERENCES `produtos` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtosvendas`
--

LOCK TABLES `produtosvendas` WRITE;
/*!40000 ALTER TABLE `produtosvendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `produtosvendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proprietario`
--

DROP TABLE IF EXISTS `proprietario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proprietario` (
  `cnpj` varchar(18) NOT NULL,
  `Endereco` varchar(100) NOT NULL,
  `bairro` varchar(40) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `complemento` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `fax` varchar(17) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `numero` int(11) NOT NULL,
  `site` varchar(100) DEFAULT NULL,
  `telefone` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`cnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proprietario`
--

LOCK TABLES `proprietario` WRITE;
/*!40000 ALTER TABLE `proprietario` DISABLE KEYS */;
/*!40000 ALTER TABLE `proprietario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicos`
--

DROP TABLE IF EXISTS `servicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicos` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  `obs` longtext,
  `servico` varchar(70) NOT NULL,
  `valor` double DEFAULT NULL,
  `categoria_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UK_qhu5lliy6vxjpxm5fgalkfkhk` (`servico`),
  KEY `FK_lm3m8owxfkwqe5ta1l76f5218` (`categoria_codigo`),
  CONSTRAINT `FK_lm3m8owxfkwqe5ta1l76f5218` FOREIGN KEY (`categoria_codigo`) REFERENCES `categoriasservicos` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicos`
--

LOCK TABLES `servicos` WRITE;
/*!40000 ALTER TABLE `servicos` DISABLE KEYS */;
INSERT INTO `servicos` VALUES (1,'','','SERV1-1',50,1),(2,'','','SERV2-1',55,1),(3,'','','SERV1-2',60,2),(4,'','','SERV2-2',65,2);
/*!40000 ALTER TABLE `servicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicosvendas`
--

DROP TABLE IF EXISTS `servicosvendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicosvendas` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `desconto` double DEFAULT NULL,
  `valor` double NOT NULL,
  `servico_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_1gaabfpj7ht5ghdpa68216hgq` (`servico_codigo`),
  CONSTRAINT `FK_1gaabfpj7ht5ghdpa68216hgq` FOREIGN KEY (`servico_codigo`) REFERENCES `servicos` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicosvendas`
--

LOCK TABLES `servicosvendas` WRITE;
/*!40000 ALTER TABLE `servicosvendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicosvendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefonesclientes`
--

DROP TABLE IF EXISTS `telefonesclientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefonesclientes` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `operadora` varchar(15) DEFAULT NULL,
  `ramal` int(11) NOT NULL,
  `telefone` varchar(17) NOT NULL,
  `tipo` int(11) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefonesclientes`
--

LOCK TABLES `telefonesclientes` WRITE;
/*!40000 ALTER TABLE `telefonesclientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefonesclientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefonesfornecedores`
--

DROP TABLE IF EXISTS `telefonesfornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefonesfornecedores` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `operadora` varchar(15) DEFAULT NULL,
  `ramal` int(11) NOT NULL,
  `telefone` varchar(17) NOT NULL,
  `tipo` int(11) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefonesfornecedores`
--

LOCK TABLES `telefonesfornecedores` WRITE;
/*!40000 ALTER TABLE `telefonesfornecedores` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefonesfornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefoneslocaiseventos`
--

DROP TABLE IF EXISTS `telefoneslocaiseventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefoneslocaiseventos` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `operadora` varchar(15) DEFAULT NULL,
  `ramal` int(11) NOT NULL,
  `telefone` varchar(17) NOT NULL,
  `tipo` int(11) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefoneslocaiseventos`
--

LOCK TABLES `telefoneslocaiseventos` WRITE;
/*!40000 ALTER TABLE `telefoneslocaiseventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefoneslocaiseventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiposequipamentos`
--

DROP TABLE IF EXISTS `tiposequipamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiposequipamentos` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  `tipo` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UK_mqgojdu7at3kk65isd7hhtaev` (`tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiposequipamentos`
--

LOCK TABLES `tiposequipamentos` WRITE;
/*!40000 ALTER TABLE `tiposequipamentos` DISABLE KEYS */;
INSERT INTO `tiposequipamentos` VALUES (1,'','TIPO EQ 1'),(2,'','TIPO EQ 2');
/*!40000 ALTER TABLE `tiposequipamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiposeventos`
--

DROP TABLE IF EXISTS `tiposeventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiposeventos` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  `tipo` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UK_py6l7ouqa4s5mtk7dnmbjfuna` (`tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiposeventos`
--

LOCK TABLES `tiposeventos` WRITE;
/*!40000 ALTER TABLE `tiposeventos` DISABLE KEYS */;
INSERT INTO `tiposeventos` VALUES (1,'','EVT1'),(2,'','EVT2');
/*!40000 ALTER TABLE `tiposeventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  `nivel` bit(1) NOT NULL DEFAULT b'0',
  `nome` varchar(100) NOT NULL,
  `senha` varchar(32) NOT NULL,
  `usuario` varchar(15) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UK_rsfip2lvddb3ef5qh8j1cbs7l` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'','','SUPORTE','4cbc09ecc636973143150b349902efa7','SUPORTE'),(3,'','','Rogério Eduardo Pereira','9e2d5db6636d788c6e6c700a37914e2b','rodu_pereira'),(5,'','\0','User Simples','6688b6c0bdeda4168a39067f7f7ccf93','simples');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendas`
--

DROP TABLE IF EXISTS `vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendas` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `desconto` double DEFAULT NULL,
  `dia` date NOT NULL,
  `hora` time NOT NULL,
  `obs` longtext,
  `porcentagemDesconto` double DEFAULT NULL,
  `cliente_codigo` bigint(20) DEFAULT NULL,
  `localEvento_codigo` bigint(20) DEFAULT NULL,
  `tipoEvento_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_iyaqc7618lws1y6l14r5b4y5t` (`cliente_codigo`),
  KEY `FK_h2lywhnyiidsgrltti46oleh` (`localEvento_codigo`),
  KEY `FK_f2p54a4a9ymangct99av00vpv` (`tipoEvento_codigo`),
  CONSTRAINT `FK_f2p54a4a9ymangct99av00vpv` FOREIGN KEY (`tipoEvento_codigo`) REFERENCES `tiposeventos` (`codigo`),
  CONSTRAINT `FK_h2lywhnyiidsgrltti46oleh` FOREIGN KEY (`localEvento_codigo`) REFERENCES `locaiseventos` (`codigo`),
  CONSTRAINT `FK_iyaqc7618lws1y6l14r5b4y5t` FOREIGN KEY (`cliente_codigo`) REFERENCES `clientes` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas`
--

LOCK TABLES `vendas` WRITE;
/*!40000 ALTER TABLE `vendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendas_pagamentovendas`
--

DROP TABLE IF EXISTS `vendas_pagamentovendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendas_pagamentovendas` (
  `Vendas_codigo` bigint(20) NOT NULL,
  `pagamentos_codigo` bigint(20) NOT NULL,
  UNIQUE KEY `UK_2w4gh37leoksxcup4onuy9o05` (`pagamentos_codigo`),
  KEY `FK_t8q0pvrn83b75ek352e0fiebx` (`Vendas_codigo`),
  CONSTRAINT `FK_2w4gh37leoksxcup4onuy9o05` FOREIGN KEY (`pagamentos_codigo`) REFERENCES `pagamentovendas` (`codigo`),
  CONSTRAINT `FK_t8q0pvrn83b75ek352e0fiebx` FOREIGN KEY (`Vendas_codigo`) REFERENCES `vendas` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas_pagamentovendas`
--

LOCK TABLES `vendas_pagamentovendas` WRITE;
/*!40000 ALTER TABLE `vendas_pagamentovendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendas_pagamentovendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendas_produtosvendas`
--

DROP TABLE IF EXISTS `vendas_produtosvendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendas_produtosvendas` (
  `Vendas_codigo` bigint(20) NOT NULL,
  `produtos_codigo` bigint(20) NOT NULL,
  UNIQUE KEY `UK_6e47rw53ljoabesuda2um1yem` (`produtos_codigo`),
  KEY `FK_m0bqty99rjrklwnn2plrlv0ga` (`Vendas_codigo`),
  CONSTRAINT `FK_6e47rw53ljoabesuda2um1yem` FOREIGN KEY (`produtos_codigo`) REFERENCES `produtosvendas` (`codigo`),
  CONSTRAINT `FK_m0bqty99rjrklwnn2plrlv0ga` FOREIGN KEY (`Vendas_codigo`) REFERENCES `vendas` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas_produtosvendas`
--

LOCK TABLES `vendas_produtosvendas` WRITE;
/*!40000 ALTER TABLE `vendas_produtosvendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendas_produtosvendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendas_servicosvendas`
--

DROP TABLE IF EXISTS `vendas_servicosvendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendas_servicosvendas` (
  `Vendas_codigo` bigint(20) NOT NULL,
  `servicos_codigo` bigint(20) NOT NULL,
  UNIQUE KEY `UK_2rbu41p7capf837st1g0n7vik` (`servicos_codigo`),
  KEY `FK_ps4bguwa4bvymaeygepj0hfme` (`Vendas_codigo`),
  CONSTRAINT `FK_2rbu41p7capf837st1g0n7vik` FOREIGN KEY (`servicos_codigo`) REFERENCES `servicosvendas` (`codigo`),
  CONSTRAINT `FK_ps4bguwa4bvymaeygepj0hfme` FOREIGN KEY (`Vendas_codigo`) REFERENCES `vendas` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas_servicosvendas`
--

LOCK TABLES `vendas_servicosvendas` WRITE;
/*!40000 ALTER TABLE `vendas_servicosvendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendas_servicosvendas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-16 12:08:33
