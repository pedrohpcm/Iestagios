CREATE DATABASE  IF NOT EXISTS `iestagios_db`
USE `iestagios_db`;

DROP TABLE IF EXISTS `concedente`;
CREATE TABLE `concedente` (
  `idConcedente` int(11) NOT NULL AUTO_INCREMENT,
  `razaoSocial` varchar(45) DEFAULT NULL,
  `nome` varchar(45) NOT NULL,
  `cnpj` varchar(45) DEFAULT NULL,
  `ramoDeAtividade` varchar(45) NOT NULL,
  `tipoConcedente` varchar(20) NOT NULL,
  `representante` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `cargo` varchar(45) NOT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `idTelefone` int(11) NOT NULL,
  `idEndereco` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idConcedente`),
  UNIQUE KEY `cnpj_UNIQUE` (`cnpj`),
  KEY `fk_Concedente_Telefone1_idx` (`idTelefone`),
  KEY `fk_Concedente_Endereco1_idx` (`idEndereco`),
  KEY `cnpj` (`cnpj`),
  KEY `fk_concedente_usuario1_idx` (`idUsuario`),
  CONSTRAINT `fk_concedente_usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `conta` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `conta`;
CREATE TABLE `conta` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `estaCompleto` char(1) NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `curriculo`;
CREATE TABLE `curriculo` (
  `idCurriculo` int(11) NOT NULL AUTO_INCREMENT,
  `formacao` varchar(300) NOT NULL,
  `cursos` varchar(300) NOT NULL,
  `habilidades` varchar(300) NOT NULL,
  `sobre` varchar(300) NOT NULL,
  `idEstudante` int(11) NOT NULL,
  PRIMARY KEY (`idCurriculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `endereco`;
CREATE TABLE `endereco` (
  `idendereco` int(11) NOT NULL AUTO_INCREMENT,
  `rua` varchar(45) NOT NULL,
  `numero` varchar(15) NOT NULL,
  `bairro` varchar(45) NOT NULL,
  `cidade` varchar(45) NOT NULL,
  `estado` varchar(2) NOT NULL,
  `cep` varchar(45) NOT NULL,
  PRIMARY KEY (`idendereco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `estagio`;
CREATE TABLE `estagio` (
  `idEstagio` int(11) NOT NULL AUTO_INCREMENT,
  `dataInicio` varchar(45) NOT NULL,
  `dataFim` varchar(45) NOT NULL,
  `orientador` varchar(45) NOT NULL,
  `emailOrientador` varchar(45) NOT NULL,
  `supervisor` varchar(45) NOT NULL,
  `emailSupervisor` varchar(45) NOT NULL,
  `idVaga` int(11) NOT NULL,
  `idEstudante` int(11) NOT NULL,
  `idConcedente` int(11) NOT NULL,
  `idSeguro` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`idEstagio`),
  KEY `fk_estagio_vaga1_idx` (`idVaga`),
  KEY `fk_estagio_estudante1_idx` (`idEstudante`),
  KEY `fk_estagio_empresa1_idx` (`idConcedente`),
  CONSTRAINT `fk_estagio_empresa1` FOREIGN KEY (`idConcedente`) REFERENCES `concedente` (`idConcedente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_estagio_estudante1` FOREIGN KEY (`idEstudante`) REFERENCES `estudante` (`idEstudante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_estagio_vaga1` FOREIGN KEY (`idVaga`) REFERENCES `vaga` (`idVaga`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `estudante`;
CREATE TABLE `estudante` (
  `idEstudante` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `rg` varchar(20) NOT NULL,
  `orgaoExpeditor` varchar(45) NOT NULL,
  `dataDeNascimento` date NOT NULL,
  `curso` varchar(45) NOT NULL,
  `periodo` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `senha` varchar(20) NOT NULL,
  `ano` varchar(4) NOT NULL,
  `idEndereco` int(11) NOT NULL,
  `idTelefone` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idEstudante`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  KEY `fk_Estudante_Endereco_idx` (`idEndereco`),
  KEY `fk_Estudante_Telefone1_idx` (`idTelefone`),
  KEY `fk_estudante_usuario1_idx` (`idUsuario`),
  CONSTRAINT `fk_estudante_usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `conta` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `matricula`;
CREATE TABLE `matricula` (
  `idEstudante` int(11) NOT NULL,
  `idConcedente` int(11) NOT NULL,
  PRIMARY KEY (`idEstudante`,`idConcedente`),
  KEY `fk_estudante_has_concedente_concedente1_idx` (`idConcedente`),
  KEY `fk_estudante_has_concedente_estudante1_idx` (`idEstudante`),
  CONSTRAINT `fk_estudante_has_concedente_concedente1` FOREIGN KEY (`idConcedente`) REFERENCES `concedente` (`idConcedente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_estudante_has_concedente_estudante1` FOREIGN KEY (`idEstudante`) REFERENCES `estudante` (`idEstudante`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `processoseletivo`;
CREATE TABLE `processoseletivo` (
  `idVaga` int(11) NOT NULL,
  `idEstudante` int(11) NOT NULL,
  `dataInicio` varchar(45) DEFAULT NULL,
  `dataFim` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idVaga`,`idEstudante`),
  KEY `fk_Vaga_has_Estudante_Estudante1_idx` (`idEstudante`),
  KEY `fk_Vaga_has_Estudante_Vaga1_idx` (`idVaga`),
  CONSTRAINT `fk_Vaga_has_Estudante_Estudante1` FOREIGN KEY (`idEstudante`) REFERENCES `estudante` (`idEstudante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vaga_has_Estudante_Vaga1` FOREIGN KEY (`idVaga`) REFERENCES `vaga` (`idVaga`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `seguro`;
CREATE TABLE `seguro` (
  `idSeguro` int(11) NOT NULL AUTO_INCREMENT,
  `inicio` date NOT NULL,
  `fim` date NOT NULL,
  `taxa` double NOT NULL,
  `nomeDoSegurado` varchar(45) NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `dataDeNascimento` date NOT NULL,
  `estadoCivil` varchar(15) NOT NULL,
  PRIMARY KEY (`idSeguro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `telefone`;
CREATE TABLE `telefone` (
  `idTelefone` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(15) NOT NULL,
  PRIMARY KEY (`idTelefone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `vaga`;
CREATE TABLE `vaga` (
  `idVaga` int(11) NOT NULL AUTO_INCREMENT,
  `nomeDaVaga` varchar(100) NOT NULL,
  `valorDaBolsa` double NOT NULL,
  `beneficios` varchar(45) DEFAULT NULL,
  `auxilioTransporte` varchar(45) DEFAULT NULL,
  `planoDeAtividades` varchar(200) NOT NULL,
  `jornadaDiaria` varchar(45) NOT NULL,
  `totalHorasSemanais` varchar(45) DEFAULT NULL,
  `idConcedente` int(11) NOT NULL,
  PRIMARY KEY (`idVaga`),
  KEY `fk_Vaga_Concedente1_idx` (`idConcedente`),
  CONSTRAINT `fk_Vaga_Concedente1` FOREIGN KEY (`idConcedente`) REFERENCES `concedente` (`idConcedente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

