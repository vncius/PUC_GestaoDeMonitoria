-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.16 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para sysmonitoria
DROP DATABASE IF EXISTS `sysmonitoria`;
CREATE DATABASE IF NOT EXISTS `sysmonitoria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sysmonitoria`;

-- Copiando estrutura para tabela sysmonitoria.cronograma_geral
DROP TABLE IF EXISTS `cronograma_geral`;
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

-- Copiando dados para a tabela sysmonitoria.cronograma_geral: ~1 rows (aproximadamente)
DELETE FROM `cronograma_geral`;
/*!40000 ALTER TABLE `cronograma_geral` DISABLE KEYS */;
INSERT INTO `cronograma_geral` (`id`, `entrega_dos_certificados_dt_fim`, `entrega_dos_certificados_dt_inicio`, `entrega_dos_resultados_dt_fim`, `entrega_dos_resultados_dt_inicio`, `periodo_avaliacao_dt_fim`, `periodo_avaliacao_dt_inicio`, `periodo_inscricao_dt_fim`, `periodo_inscricao_dt_inicio`, `periodo_letivo_dt_fim`, `periodo_letivo_dt_inicio`, `publicacao_edital_dt_fim`, `publicacao_edital_dt_inicio`) VALUES
	(1, '2019-12-20 21:32:03.000000', '2019-12-03 21:32:11.000000', '2019-12-20 21:32:12.000000', '2019-12-03 21:32:13.000000', '2019-12-20 21:32:13.000000', '2019-12-03 21:32:14.000000', '2019-12-20 21:32:14.000000', '2019-12-03 21:32:15.000000', '2019-12-20 21:32:17.000000', '2019-12-03 21:32:18.000000', '2019-12-20 21:32:19.000000', '2019-12-03 21:32:21.000000');
/*!40000 ALTER TABLE `cronograma_geral` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.cronograma_monitoria
DROP TABLE IF EXISTS `cronograma_monitoria`;
CREATE TABLE IF NOT EXISTS `cronograma_monitoria` (
  `id` bigint(20) NOT NULL,
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

-- Copiando dados para a tabela sysmonitoria.cronograma_monitoria: ~15 rows (aproximadamente)
DELETE FROM `cronograma_monitoria`;
/*!40000 ALTER TABLE `cronograma_monitoria` DISABLE KEYS */;
INSERT INTO `cronograma_monitoria` (`id`, `data_edital_fim`, `data_edital_inicio`, `entrega_certificado_fim`, `entrega_certificado_inicio`, `entrega_resultados_fim`, `entrega_resultados_inicio`, `data_inscricao_fim`, `data_inscricao_inicio`, `periodo_avaliacao_fim`, `periodo_avaliacao_inicio`, `periodo_letivo_fim`, `periodo_letivo_inicio`, `curso_id`) VALUES
	(1, '2019-12-22', '2019-12-01', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-21', '2019-12-02', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 1),
	(2, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 2),
	(3, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 3),
	(4, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 4),
	(5, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 5),
	(6, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 6),
	(7, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 7),
	(8, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 8),
	(9, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 9),
	(10, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 10),
	(11, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 11),
	(12, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 12),
	(13, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 13),
	(14, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 14),
	(15, '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-20', '2019-12-03', '2019-12-30', '2019-12-03', '2019-12-20', '2019-12-03', 15);
/*!40000 ALTER TABLE `cronograma_monitoria` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.curso
DROP TABLE IF EXISTS `curso`;
CREATE TABLE IF NOT EXISTS `curso` (
  `id` bigint(20) NOT NULL,
  `descricao` text,
  `situacao_avaliacao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.curso: ~16 rows (aproximadamente)
DELETE FROM `curso`;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` (`id`, `descricao`, `situacao_avaliacao`) VALUES
	(0, 'NULL', 'NULL'),
	(1, 'Análise e Desen. de Sistemas', 'PENDENTE DE AVALIACAO'),
	(2, 'Administração', 'PENDENTE DE AVALIACAO'),
	(3, 'Agronomia', 'PENDENTE DE AVALIACAO'),
	(4, 'Arqueologia', 'PENDENTE DE AVALIACAO'),
	(5, 'Arquitetura', 'PENDENTE DE AVALIACAO'),
	(6, 'Artes Visuais', 'PENDENTE DE AVALIACAO'),
	(7, 'Biomedicina', 'PENDENTE DE AVALIACAO'),
	(8, 'Ciência da Computação', 'PENDENTE DE AVALIACAO'),
	(9, 'Ciências Biológicas', 'PENDENTE DE AVALIACAO'),
	(10, 'Ciências Contábeis', 'PENDENTE DE AVALIACAO'),
	(11, 'Ciências Econômicas', 'PENDENTE DE AVALIACAO'),
	(12, 'Ciências Naturais', 'PENDENTE DE AVALIACAO'),
	(13, 'Ciências Aeronaútica', 'PENDENTE DE AVALIACAO'),
	(14, 'Ciências Sociais', 'PENDENTE DE AVALIACAO'),
	(15, 'Cinema e Audiovisual', 'PENDENTE DE AVALIACAO');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.disciplina
DROP TABLE IF EXISTS `disciplina`;
CREATE TABLE IF NOT EXISTS `disciplina` (
  `id` bigint(20) NOT NULL,
  `codigo_disciplina` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `qtde_vg_monitoria` int(11) NOT NULL,
  `fk_curso_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb7epuwbrbyegcpoueg219at4k` (`fk_curso_id`),
  CONSTRAINT `FKb7epuwbrbyegcpoueg219at4k` FOREIGN KEY (`fk_curso_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.disciplina: ~34 rows (aproximadamente)
DELETE FROM `disciplina`;
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
INSERT INTO `disciplina` (`id`, `codigo_disciplina`, `descricao`, `qtde_vg_monitoria`, `fk_curso_id`) VALUES
	(1, 'CMP1010', 'Disciplina 1', 1, 1),
	(2, 'CMP1012', 'Disciplina 2', 2, 1),
	(3, 'CMP1013', 'Disciplina 3', 2, 2),
	(4, 'CMP1014', 'Disciplina 4', 2, 2),
	(5, 'CMP1015', 'Disciplina 5', 2, 2),
	(6, 'CMP1016', 'Disciplina 6', 2, 2),
	(7, 'CMP1022', 'Disciplina 7', 2, 2),
	(8, 'CMP1023', 'Disciplina 8', 2, 2),
	(9, 'CMP1023', 'Disciplina 9', 2, 2),
	(10, 'CMP1023', 'Disciplina 10', 2, 2),
	(11, 'CMP1024', 'Disciplina 11', 2, 3),
	(12, 'CMP1025', 'Disciplina 12', 2, 3),
	(13, 'CMP1026', 'Disciplina 13', 2, 4),
	(14, 'CMP1027', 'Disciplina 14', 2, 4),
	(15, 'CMP1028', 'Disciplina 15', 2, 5),
	(16, 'CMP1029', 'Disciplina 16', 2, 5),
	(17, 'CMP1030', 'Disciplina 17', 2, 6),
	(18, 'CMP1031', 'Disciplina 18', 2, 6),
	(19, 'CMP1032', 'Disciplina 19', 2, 7),
	(20, 'CMP1033', 'Disciplina 20', 2, 7),
	(21, 'CMP1034', 'Disciplina 21', 2, 8),
	(22, 'CMP1035', 'Disciplina 22', 2, 8),
	(23, 'CMP1036', 'Disciplina 23', 2, 9),
	(24, 'CMP1037', 'Disciplina 24', 2, 9),
	(25, 'CMP1038', 'Disciplina 25', 2, 10),
	(26, 'CMP1039', 'Disciplina 26', 2, 10),
	(27, 'CMP1040', 'Disciplina 27', 2, 11),
	(28, 'CMP1041', 'Disciplina 28', 2, 11),
	(29, 'CMP1042', 'Disciplina 29', 2, 12),
	(30, 'CMP1043', 'Disciplina 30', 2, 12),
	(31, 'CMP1044', 'Disciplina 31', 2, 13),
	(32, 'CMP1045', 'Disciplina 32', 2, 13),
	(33, 'CMP1046', 'Disciplina 33', 2, 14),
	(34, 'CMP1047', 'Disciplina 34', 2, 14);
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.hibernate_sequence
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.hibernate_sequence: ~7 rows (aproximadamente)
DELETE FROM `hibernate_sequence`;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(100),
	(100),
	(100),
	(100),
	(100),
	(100),
	(100);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.incricao_monitoria
DROP TABLE IF EXISTS `incricao_monitoria`;
CREATE TABLE IF NOT EXISTS `incricao_monitoria` (
  `id` bigint(20) NOT NULL,
  `carga_horaria_quarta` varchar(255) DEFAULT NULL,
  `carga_horaria_quinta` varchar(255) DEFAULT NULL,
  `carga_horaria_sabado` varchar(255) DEFAULT NULL,
  `carga_horaria_segunda` varchar(255) DEFAULT NULL,
  `carga_horaria_sexta` varchar(255) DEFAULT NULL,
  `carga_horaria_terca` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id_curso` bigint(20) NOT NULL,
  `id_disciplina` bigint(20) NOT NULL,
  `id_orientador` bigint(20) NOT NULL,
  `ja_possuiu_bolsa` bit(1) NOT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `nota_avaliacao` int(11) NOT NULL,
  `nota_coeficiente` int(11) NOT NULL,
  `possui_bolsa` bit(1) NOT NULL,
  `status_incricao` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.incricao_monitoria: ~23 rows (aproximadamente)
DELETE FROM `incricao_monitoria`;
/*!40000 ALTER TABLE `incricao_monitoria` DISABLE KEYS */;
INSERT INTO `incricao_monitoria` (`id`, `carga_horaria_quarta`, `carga_horaria_quinta`, `carga_horaria_sabado`, `carga_horaria_segunda`, `carga_horaria_sexta`, `carga_horaria_terca`, `email`, `id_curso`, `id_disciplina`, `id_orientador`, `ja_possuiu_bolsa`, `matricula`, `nome`, `nota_avaliacao`, `nota_coeficiente`, `possui_bolsa`, `status_incricao`, `telefone`) VALUES
	(1, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 1, 1, 1, b'1', 'MAT_ALUNO1', 'Maria', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(2, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 1, 2, 3, b'1', 'MAT_ALUNO2', 'Leonor', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(3, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 1, 1, 2, b'1', 'MAT_ALUNO3', 'Maria', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(4, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 1, 2, 4, b'1', 'MAT_ALUNO4', 'Mariana', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(5, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 1, 1, 1, b'1', 'MAT_ALUNO5', 'Carolina', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(6, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 2, 3, 3, b'1', 'MAT_ALUNO9', 'Margarida', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(7, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 3, 11, 18, b'1', 'MAT_ALUNO16', 'Madalenada', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(8, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 4, 13, 20, b'1', 'MAT_ALUNO18', 'Luana', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(9, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 5, 15, 22, b'1', 'MAT_ALUNO19', 'Guilherme', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(10, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 6, 17, 24, b'1', 'MAT_ALUNO20', 'Gabriel', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(11, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 7, 19, 26, b'1', 'MAT_ALUNO21', 'Salvador', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(12, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 8, 21, 28, b'1', 'MAT_ALUNO22', 'Simão', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(13, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 9, 23, 30, b'1', 'MAT_ALUNO23', 'David', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(14, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 10, 25, 32, b'1', 'MAT_ALUNO24', 'Lourenço', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(15, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 11, 27, 34, b'1', 'MAT_ALUNO25', 'Manuel', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(16, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 12, 29, 36, b'1', 'MAT_ALUNO26', 'Henrique', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(17, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 13, 31, 38, b'1', 'MAT_ALUNO27', 'Leonardo', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(18, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 14, 34, 41, b'1', 'MAT_ALUNO28', 'Bernardo', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(21, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 2, 3, 3, b'1', 'MAT_ALUNO10', 'Lara', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(22, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 2, 3, 3, b'1', 'MAT_ALUNO11', 'Joana', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(23, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 2, 3, 3, b'1', 'MAT_ALUNO12', 'Laura', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(24, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 2, 3, 3, b'1', 'MAT_ALUNO13', 'Francisca', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899'),
	(25, 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'Apartir das 16hrs as 22hrs', 'teste@teste.com.br', 2, 3, 3, b'1', 'MAT_ALUNO14', 'Diana', 0, 0, b'0', 'PENDENTE', '(62) 9 8161-8899');
/*!40000 ALTER TABLE `incricao_monitoria` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.orientador
DROP TABLE IF EXISTS `orientador`;
CREATE TABLE IF NOT EXISTS `orientador` (
  `id` bigint(20) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `fk_disciplina_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmhg7xu96n2ywwv9mia4g4yd9m` (`fk_disciplina_id`),
  CONSTRAINT `FKmhg7xu96n2ywwv9mia4g4yd9m` FOREIGN KEY (`fk_disciplina_id`) REFERENCES `disciplina` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.orientador: ~41 rows (aproximadamente)
DELETE FROM `orientador`;
/*!40000 ALTER TABLE `orientador` DISABLE KEYS */;
INSERT INTO `orientador` (`id`, `descricao`, `fk_disciplina_id`) VALUES
	(1, 'ORIENTADOR 1', 1),
	(2, 'ORIENTADOR 2', 1),
	(3, 'ORIENTADOR 3', 2),
	(4, 'ORIENTADOR 4', 2),
	(5, 'ORIENTADOR 5', 3),
	(6, 'ORIENTADOR 6', 3),
	(7, 'ORIENTADOR 7', 4),
	(8, 'ORIENTADOR 8', 4),
	(9, 'ORIENTADOR 9', 5),
	(10, 'ORIENTADOR 10', 5),
	(11, 'ORIENTADOR 11', 6),
	(12, 'ORIENTADOR 12', 6),
	(13, 'ORIENTADOR 13', 7),
	(14, 'ORIENTADOR 14', 8),
	(15, 'ORIENTADOR 15', 8),
	(16, 'ORIENTADOR 16', 9),
	(17, 'ORIENTADOR 17', 10),
	(18, 'ORIENTADOR 18', 11),
	(19, 'ORIENTADOR 19', 12),
	(20, 'ORIENTADOR 20', 13),
	(21, 'ORIENTADOR 21', 14),
	(22, 'ORIENTADOR 22', 15),
	(23, 'ORIENTADOR 23', 16),
	(24, 'ORIENTADOR 24', 17),
	(25, 'ORIENTADOR 25', 18),
	(26, 'ORIENTADOR 26', 19),
	(27, 'ORIENTADOR 27', 20),
	(28, 'ORIENTADOR 28', 21),
	(29, 'ORIENTADOR 29', 22),
	(30, 'ORIENTADOR 30', 23),
	(31, 'ORIENTADOR 31', 24),
	(32, 'ORIENTADOR 32', 25),
	(33, 'ORIENTADOR 33', 26),
	(34, 'ORIENTADOR 34', 27),
	(35, 'ORIENTADOR 35', 28),
	(36, 'ORIENTADOR 36', 29),
	(37, 'ORIENTADOR 37', 30),
	(38, 'ORIENTADOR 38', 31),
	(39, 'ORIENTADOR 39', 32),
	(40, 'ORIENTADOR 40', 33),
	(41, 'ORIENTADOR 41', 34);
/*!40000 ALTER TABLE `orientador` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL,
  `nome_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.role: ~6 rows (aproximadamente)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `nome_role`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_ALUNO'),
	(3, 'ROLE_PROFESSOR'),
	(4, 'ROLE_COORD_CAEME'),
	(5, 'ROLE_COORD_CURSO'),
	(6, 'ROLE_COORD_MONITORIA');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Copiando estrutura para tabela sysmonitoria.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `matricula` varchar(255) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `fk_curso_id` bigint(20) NOT NULL,
  `fk_role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`matricula`),
  KEY `FK8g0smkcd0i1udpaa05ueghdki` (`fk_curso_id`),
  KEY `FKkbvg8d8khgl82m82p00otpgn4` (`fk_role_id`),
  CONSTRAINT `FK8g0smkcd0i1udpaa05ueghdki` FOREIGN KEY (`fk_curso_id`) REFERENCES `curso` (`id`),
  CONSTRAINT `FKkbvg8d8khgl82m82p00otpgn4` FOREIGN KEY (`fk_role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela sysmonitoria.usuario: ~49 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`matricula`, `nome`, `senha`, `fk_curso_id`, `fk_role_id`) VALUES
	('ADMINISTRADOR', 'ADMINISTRADOR DO SISTEMA', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 0, 1),
	('COORDENADOR_CAEME', 'Oliveira Alves', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 0, 4),
	('COORDENADOR_CURSO1', 'Christian', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 1, 5),
	('COORDENADOR_CURSO10', 'Doriana', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 10, 5),
	('COORDENADOR_CURSO11', 'Muhammad', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 11, 5),
	('COORDENADOR_CURSO12', 'Cláudia', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 12, 5),
	('COORDENADOR_CURSO13', 'Matheus', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 13, 5),
	('COORDENADOR_CURSO14', 'Cristiana', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 14, 5),
	('COORDENADOR_CURSO15', 'Moisés', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 15, 5),
	('COORDENADOR_CURSO2', 'Fernando', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 2, 5),
	('COORDENADOR_CURSO3', 'Emanuel', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 3, 5),
	('COORDENADOR_CURSO4', 'Luciana', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 4, 5),
	('COORDENADOR_CURSO5', 'Renata', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 5, 5),
	('COORDENADOR_CURSO6', 'Áurea', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 6, 5),
	('COORDENADOR_CURSO7', 'Nelson', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 7, 5),
	('COORDENADOR_CURSO8', 'Márcio', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 8, 5),
	('COORDENADOR_CURSO9', 'Raissa', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 9, 5),
	('COORDENADOR_MONITORIA', 'Luiza Mota', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 0, 6),
	('MAT_ALUNO1', 'Maria', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 1, 2),
	('MAT_ALUNO10', 'Lara', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 2, 2),
	('MAT_ALUNO11', 'Joana', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 2, 2),
	('MAT_ALUNO12', 'Laura', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 2, 2),
	('MAT_ALUNO13', 'Francisca', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 2, 2),
	('MAT_ALUNO14', 'Diana', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 2, 2),
	('MAT_ALUNO15', 'Mafalda', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 2, 2),
	('MAT_ALUNO16', 'Madalena', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 3, 2),
	('MAT_ALUNO17', 'Clara', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 3, 2),
	('MAT_ALUNO18', 'Luana', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 4, 2),
	('MAT_ALUNO19', 'Guilherme', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 5, 2),
	('MAT_ALUNO2', 'Leonor', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 1, 2),
	('MAT_ALUNO20', 'Gabriel', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 6, 2),
	('MAT_ALUNO21', 'Salvador', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 7, 2),
	('MAT_ALUNO22', 'Simão', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 8, 2),
	('MAT_ALUNO23', 'David', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 9, 2),
	('MAT_ALUNO24', 'Lourenço', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 10, 2),
	('MAT_ALUNO25', 'Manuel', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 11, 2),
	('MAT_ALUNO26', 'Henrique', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 12, 2),
	('MAT_ALUNO27', 'Leonardo', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 13, 2),
	('MAT_ALUNO28', 'Bernardo', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 14, 2),
	('MAT_ALUNO29', 'Mateus', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 15, 2),
	('MAT_ALUNO3', 'Beatriz', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 1, 2),
	('MAT_ALUNO30', 'Eduardo', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 15, 2),
	('MAT_ALUNO4', 'Mariana', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 1, 2),
	('MAT_ALUNO5', 'Carolina', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 1, 2),
	('MAT_ALUNO6', 'Ana', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 1, 2),
	('MAT_ALUNO7', 'Inês', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 1, 2),
	('MAT_ALUNO8', 'Sofia', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 1, 2),
	('MAT_ALUNO9', 'Margarida', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 2, 2),
	('PROFESSOR', 'Leticia Ferreira', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 1, 3);
	('PROFESSOR', 'Leticia Ferreira', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 2, 3);
	('PROFESSOR', 'Leticia Ferreira', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 3, 3);
	('PROFESSOR', 'Leticia Ferreira', '$2a$10$PbeSDMyjcB7rENa1Hepuy.ZFGtkkCUAxQmsx7sno2Wm8deSN0aaF.', 4, 3);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
