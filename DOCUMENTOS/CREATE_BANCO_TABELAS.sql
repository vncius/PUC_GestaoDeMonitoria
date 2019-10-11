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

-- Copiando estrutura para tabela sysmonitoria.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.hibernate_sequence: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(4);
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

-- Copiando dados para a tabela sysmonitoria.model_cronograma_geral: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `model_cronograma_geral` DISABLE KEYS */;
/*!40000 ALTER TABLE `model_cronograma_geral` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL,
  `nome_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.role: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.usuarios_role
CREATE TABLE IF NOT EXISTS `usuarios_role` (
  `usuario_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_krvk2qx218dxa3ogdyplk0wxw` (`role_id`),
  UNIQUE KEY `unique_role_user` (`usuario_id`,`role_id`),
  CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `usuario_fk` FOREIGN KEY (`usuario_id`) REFERENCES `usuario_model` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.usuarios_role: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios_role` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.usuario_model
CREATE TABLE IF NOT EXISTS `usuario_model` (
  `id` bigint(20) NOT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.usuario_model: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario_model` DISABLE KEYS */;
INSERT INTO `usuario_model` (`id`, `matricula`, `nome`, `senha`) VALUES
	(1, 'vnc', 'vinicius', '$2a$10$YI3cwynq4Q.y6ZTzNzFjXOmlmLWgraKz5HwtfU.jqrRhp2CXLO1TS');
/*!40000 ALTER TABLE `usuario_model` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
