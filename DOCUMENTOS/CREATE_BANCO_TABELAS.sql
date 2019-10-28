-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.17 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para sysmonitoria
CREATE DATABASE IF NOT EXISTS `sysmonitoria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sysmonitoria`;

-- Copiando estrutura para tabela sysmonitoria.cronograma_monitoria
CREATE TABLE IF NOT EXISTS `cronograma_monitoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_edital_fim` date NOT NULL,
  `data_edital_inicio` date NOT NULL,
  `entrega_certificado_fim` date NOT NULL,
  `entrega_certificado_inicio` date NOT NULL,
  `entrega_resultados_fim` date NOT NULL,
  `entrega_resultados_inicio` date NOT NULL,
  `data_inscricao_fim` date NOT NULL,
  `data_inscricao_inicio` date NOT NULL,
  `periodo_avaliacao_fim` date NOT NULL,
  `periodo_avaliacao_inicio` date NOT NULL,
  `periodo_letivo_fim` date NOT NULL,
  `periodo_letivo_inicio` date NOT NULL,
  `curso_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1f5dj10vh7jing21y8tyklhdu` (`curso_id`),
  CONSTRAINT `FK1f5dj10vh7jing21y8tyklhdu` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.cronograma_monitoria: ~0 rows (aproximadamente)
DELETE FROM `cronograma_monitoria`;
/*!40000 ALTER TABLE `cronograma_monitoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `cronograma_monitoria` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.curso
CREATE TABLE IF NOT EXISTS `curso` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.curso: ~0 rows (aproximadamente)
DELETE FROM `curso`;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.hibernate_sequence: ~2 rows (aproximadamente)
DELETE FROM `hibernate_sequence`;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(40),
	(40),
	(40);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.model_cronograma_geral
CREATE TABLE IF NOT EXISTS `model_cronograma_geral` (
  `id` bigint(20) NOT NULL,
  `entrega_dos_certificados_dt_fim` datetime(6) DEFAULT NULL,
  `entrega_dos_certificados_dt_inicio` datetime(6) DEFAULT NULL,
  `entrega_dos_resultados_dt_fim` datetime(6) DEFAULT NULL,
  `entrega_dos_resultados_dt_inicio` datetime(6) DEFAULT NULL,
  `periodo_avaliacao_dt_fim` datetime(6) DEFAULT NULL,
  `periodo_avaliacao_dt_inicio` datetime(6) DEFAULT NULL,
  `periodo_inscricao_dt_fim` datetime(6) DEFAULT NULL,
  `periodo_inscricao_dt_inicio` datetime(6) DEFAULT NULL,
  `periodo_letivo_dt_fim` datetime(6) DEFAULT NULL,
  `periodo_letivo_dt_inicio` datetime(6) DEFAULT NULL,
  `publicacao_edital_dt_fim` datetime(6) DEFAULT NULL,
  `publicacao_edital_dt_inicio` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.model_cronograma_geral: ~1 rows (aproximadamente)
DELETE FROM `model_cronograma_geral`;
/*!40000 ALTER TABLE `model_cronograma_geral` DISABLE KEYS */;
/*!40000 ALTER TABLE `model_cronograma_geral` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.model_usuario
CREATE TABLE IF NOT EXISTS `model_usuario` (
  `id` bigint(20) NOT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.model_usuario: ~2 rows (aproximadamente)
DELETE FROM `model_usuario`;
/*!40000 ALTER TABLE `model_usuario` DISABLE KEYS */;
INSERT INTO `model_usuario` (`id`, `matricula`, `nome`, `senha`) VALUES
	(1, 'admin', 'SENHA = 123', '$2a$10$YI3cwynq4Q.y6ZTzNzFjXOmlmLWgraKz5HwtfU.jqrRhp2CXLO1TS'),
	(2, '20171012000498', 'SENHA = 123', '$2a$10$YI3cwynq4Q.y6ZTzNzFjXOmlmLWgraKz5HwtfU.jqrRhp2CXLO1TS');
/*!40000 ALTER TABLE `model_usuario` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL,
  `nome_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.role: ~0 rows (aproximadamente)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `nome_role`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_ALUNO'),
	(3, 'ROLE_PROFESSOR'),
	(4, 'ROLE_CAEME');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.usuarios_role
CREATE TABLE IF NOT EXISTS `usuarios_role` (
  `usuario_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_krvk2qx218dxa3ogdyplk0wxw` (`role_id`),
  UNIQUE KEY `unique_role_user` (`usuario_id`,`role_id`),
  CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `usuario_fk` FOREIGN KEY (`usuario_id`) REFERENCES `model_usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.usuarios_role: ~0 rows (aproximadamente)
DELETE FROM `usuarios_role`;
/*!40000 ALTER TABLE `usuarios_role` DISABLE KEYS */;
INSERT INTO `usuarios_role` (`usuario_id`, `role_id`) VALUES
	(1, 2),
	(2, 1);
/*!40000 ALTER TABLE `usuarios_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
