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

-- Copiando estrutura para tabela sysmonitoria.cronograma_geral
CREATE TABLE IF NOT EXISTS `cronograma_geral` (
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

-- Copiando dados para a tabela sysmonitoria.cronograma_geral: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cronograma_geral` DISABLE KEYS */;
/*!40000 ALTER TABLE `cronograma_geral` ENABLE KEYS */;

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
  PRIMARY KEY (`id`),
  KEY `FK1f5dj10vh7jing21y8tyklhdu` (`curso_id`),
  CONSTRAINT `FK1f5dj10vh7jing21y8tyklhdu` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.cronograma_monitoria: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `cronograma_monitoria` DISABLE KEYS */;
REPLACE INTO `cronograma_monitoria` (`id`, `data_edital_fim`, `data_edital_inicio`, `entrega_certificado_fim`, `entrega_certificado_inicio`, `entrega_resultados_fim`, `entrega_resultados_inicio`, `data_inscricao_fim`, `data_inscricao_inicio`, `periodo_avaliacao_fim`, `periodo_avaliacao_inicio`, `periodo_letivo_fim`, `periodo_letivo_inicio`, `curso_id`, `entrega_dos_certificados_dt_fim`, `entrega_dos_certificados_dt_inicio`, `entrega_dos_resultados_dt_fim`, `entrega_dos_resultados_dt_inicio`, `periodo_avaliacao_dt_fim`, `periodo_avaliacao_dt_inicio`, `periodo_inscricao_dt_fim`, `periodo_inscricao_dt_inicio`, `periodo_letivo_dt_fim`, `periodo_letivo_dt_inicio`, `publicacao_edital_dt_fim`, `publicacao_edital_dt_inicio`) VALUES
	(44, '2019-11-01', '2019-10-31', '2019-12-17', '2019-12-16', '2019-12-13', '2019-12-12', '2019-12-09', '2019-11-07', '2019-12-11', '2019-12-10', '2019-12-15', '2019-12-14', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(45, '2019-10-30', '2019-10-29', '2019-11-23', '2019-11-22', '2019-11-19', '2019-11-18', '2019-11-16', '2019-11-06', '2019-11-17', '2019-11-16', '2019-11-21', '2019-11-20', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `cronograma_monitoria` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.curso
CREATE TABLE IF NOT EXISTS `curso` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` text,
  `eh_selecionado` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.curso: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
REPLACE INTO `curso` (`id`, `descricao`, `eh_selecionado`) VALUES
	(1, 'ANALISE E DESENVOLVIMENTO DE SISTEMAS', b'0'),
	(2, 'CIENCIAS DA COMPUTACAO', b'0'),
	(3, 'PUBLICIDADE', b'0'),
	(4, 'REDES', b'0');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.disciplina
CREATE TABLE IF NOT EXISTS `disciplina` (
  `id` bigint(20) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `eh_selecionado` bit(1) NOT NULL,
  `fk_curso_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb7epuwbrbyegcpoueg219at4k` (`fk_curso_id`),
  CONSTRAINT `FKb7epuwbrbyegcpoueg219at4k` FOREIGN KEY (`fk_curso_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.disciplina: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
REPLACE INTO `disciplina` (`id`, `descricao`, `eh_selecionado`, `fk_curso_id`) VALUES
	(1, 'BANCO DE DADOS', b'0', 1),
	(2, 'REDES', b'0', 1),
	(3, 'ARQUITETURA E DESIGN DE SOFTWARE', b'0', 1),
	(4, 'TESTE 2', b'0', 2),
	(5, 'TESTE 3', b'0', 3);
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.hibernate_sequence: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
REPLACE INTO `hibernate_sequence` (`next_val`) VALUES
	(51),
	(51),
	(51);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.incricao_monitoria
CREATE TABLE IF NOT EXISTS `incricao_monitoria` (
  `id` bigint(20) NOT NULL,
  `carga_horaria_quarta` varchar(255) DEFAULT NULL,
  `carga_horaria_quinta` varchar(255) DEFAULT NULL,
  `carga_horaria_segunda` varchar(255) DEFAULT NULL,
  `carga_horaria_sexta` varchar(255) DEFAULT NULL,
  `carga_horaria_terca` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id_curso` bigint(20) NOT NULL,
  `id_disciplina` bigint(20) NOT NULL,
  `id_orientador` bigint(20) NOT NULL,
  `ja_possuiu_bolsa` varchar(255) DEFAULT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `possui_bolsa` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `status_incricao` varchar(255) DEFAULT NULL,
  `carga_horaria_sabado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.incricao_monitoria: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `incricao_monitoria` DISABLE KEYS */;
REPLACE INTO `incricao_monitoria` (`id`, `carga_horaria_quarta`, `carga_horaria_quinta`, `carga_horaria_segunda`, `carga_horaria_sexta`, `carga_horaria_terca`, `email`, `id_curso`, `id_disciplina`, `id_orientador`, `ja_possuiu_bolsa`, `matricula`, `nome`, `possui_bolsa`, `telefone`, `status_incricao`, `carga_horaria_sabado`) VALUES
	(47, '17:00 as 18:00', '17:00 as 18:00', '17:00 as 18:00', '17:00 as 18:00', '17:00 as 18:00', 'vinicius6six@gmail.com', 1, 1, 1, '1', 'admin', 'Luiz Felipe', '1', '(62) 9 8161-7801', 'PENDENTE', '17:00 as 18:00'),
	(49, '', '', '', '', '', 'vinicius6six@gmail.com.br', 2, 4, 5, '1', 'admin2', 'Pedro Henrique', '1', '(62) 9 8161-7555', 'PENDENTE', ''),
	(50, '', '', '', '', '', 'vinicius6six@gmail.com', 1, 1, 1, '0', 'admin3', 'Pedro Henrique', '0', '(62) 9 8161-7801', 'CANCELADA', '');
/*!40000 ALTER TABLE `incricao_monitoria` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.orientador
CREATE TABLE IF NOT EXISTS `orientador` (
  `id` bigint(20) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `eh_selecionado` bit(1) NOT NULL,
  `fk_disciplina_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmhg7xu96n2ywwv9mia4g4yd9m` (`fk_disciplina_id`),
  CONSTRAINT `FKmhg7xu96n2ywwv9mia4g4yd9m` FOREIGN KEY (`fk_disciplina_id`) REFERENCES `disciplina` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.orientador: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `orientador` DISABLE KEYS */;
REPLACE INTO `orientador` (`id`, `descricao`, `eh_selecionado`, `fk_disciplina_id`) VALUES
	(1, 'JULIO XAVIER', b'0', 1),
	(2, 'PEDRO FERREIRA', b'0', 2),
	(3, 'JONATHA', b'0', 3),
	(4, 'HERNANDES', b'0', 1),
	(5, 'PEREIRA', b'0', 4),
	(6, 'FERNANDES FER', b'0', 5);
/*!40000 ALTER TABLE `orientador` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL,
  `nome_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.role: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
REPLACE INTO `role` (`id`, `nome_role`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_ALUNO'),
	(3, 'ROLE_PROFESSOR');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` bigint(20) NOT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `fk_curso_id` bigint(20) NOT NULL,
  `fk_role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8g0smkcd0i1udpaa05ueghdki` (`fk_curso_id`),
  KEY `FKkbvg8d8khgl82m82p00otpgn4` (`fk_role_id`),
  CONSTRAINT `FK8g0smkcd0i1udpaa05ueghdki` FOREIGN KEY (`fk_curso_id`) REFERENCES `curso` (`id`),
  CONSTRAINT `FKkbvg8d8khgl82m82p00otpgn4` FOREIGN KEY (`fk_role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.usuario: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
REPLACE INTO `usuario` (`id`, `matricula`, `nome`, `senha`, `fk_curso_id`, `fk_role_id`) VALUES
	(1, 'admin', 'Luiz Felipe', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 1, 1),
	(2, '20171012000498', 'Pedro Henrique', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 2, 1),
	(3, 'admin2', 'Pedro Henrique', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 2, 1),
	(4, 'admin3', 'Pedro Henrique', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 1, 1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
