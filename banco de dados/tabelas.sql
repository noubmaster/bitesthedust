-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.7.19-log - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para memes
DROP DATABASE IF EXISTS `memes`;
CREATE DATABASE IF NOT EXISTS `memes` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `memes`;

-- Copiando estrutura para tabela memes.album
DROP TABLE IF EXISTS `album`;
CREATE TABLE IF NOT EXISTS `album` (
  `idAlbum` int(11) NOT NULL AUTO_INCREMENT,
  `nomeAlbum` varchar(45) NOT NULL,
  `ano` year(4) NOT NULL,
  PRIMARY KEY (`idAlbum`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.album: ~5 rows (aproximadamente)
DELETE FROM `album`;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` (`idAlbum`, `nomeAlbum`, `ano`) VALUES
	(3, '1984', '1984'),
	(5, 'Van Halen II', '1979'),
	(6, 'Van Halen', '1978'),
	(7, 'Off the wall', '1979'),
	(8, 'Thriller', '1982');
/*!40000 ALTER TABLE `album` ENABLE KEYS */;

-- Copiando estrutura para tabela memes.artista
DROP TABLE IF EXISTS `artista`;
CREATE TABLE IF NOT EXISTS `artista` (
  `idArtista` int(11) NOT NULL AUTO_INCREMENT,
  `nomeArtista` varchar(45) NOT NULL,
  PRIMARY KEY (`idArtista`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.artista: ~3 rows (aproximadamente)
DELETE FROM `artista`;
/*!40000 ALTER TABLE `artista` DISABLE KEYS */;
INSERT INTO `artista` (`idArtista`, `nomeArtista`) VALUES
	(1, 'Van Halen'),
	(2, 'Han Valen'),
	(4, 'Michael Jackson');
/*!40000 ALTER TABLE `artista` ENABLE KEYS */;

-- Copiando estrutura para tabela memes.avaliacao
DROP TABLE IF EXISTS `avaliacao`;
CREATE TABLE IF NOT EXISTS `avaliacao` (
  `idAvaliacao` int(11) NOT NULL AUTO_INCREMENT,
  `nota` int(11) NOT NULL,
  `comentario` varchar(200) DEFAULT NULL,
  `revisao` tinyint(4) DEFAULT NULL,
  `idMusicaAvaliacao` int(11) NOT NULL,
  `idUsuarioAvaliacao` int(11) NOT NULL,
  PRIMARY KEY (`idAvaliacao`),
  KEY `fk_Avaliacao_Musica1_idx` (`idMusicaAvaliacao`),
  KEY `fk_Avaliacao_Usuario1_idx` (`idUsuarioAvaliacao`),
  CONSTRAINT `fk_Avaliacao_Musica1` FOREIGN KEY (`idMusicaAvaliacao`) REFERENCES `musica` (`idMusica`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Avaliacao_Usuario1` FOREIGN KEY (`idUsuarioAvaliacao`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.avaliacao: ~2 rows (aproximadamente)
DELETE FROM `avaliacao`;
/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
INSERT INTO `avaliacao` (`idAvaliacao`, `nota`, `comentario`, `revisao`, `idMusicaAvaliacao`, `idUsuarioAvaliacao`) VALUES
	(3, 1, '1', 1, 2, 2),
	(4, 100000, 'melhor musica ja criada pelo ser humano', 0, 1, 2);
/*!40000 ALTER TABLE `avaliacao` ENABLE KEYS */;

-- Copiando estrutura para tabela memes.composicao
DROP TABLE IF EXISTS `composicao`;
CREATE TABLE IF NOT EXISTS `composicao` (
  `Artista_idArtista` int(11) NOT NULL,
  `Musica_idMusica` int(11) NOT NULL,
  PRIMARY KEY (`Artista_idArtista`,`Musica_idMusica`),
  KEY `fk_Artista_has_Musica_Musica1_idx` (`Musica_idMusica`),
  KEY `fk_Artista_has_Musica_Artista1_idx` (`Artista_idArtista`),
  CONSTRAINT `fk_Artista_has_Musica_Artista1` FOREIGN KEY (`Artista_idArtista`) REFERENCES `artista` (`idArtista`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artista_has_Musica_Musica1` FOREIGN KEY (`Musica_idMusica`) REFERENCES `musica` (`idMusica`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.composicao: ~5 rows (aproximadamente)
DELETE FROM `composicao`;
/*!40000 ALTER TABLE `composicao` DISABLE KEYS */;
INSERT INTO `composicao` (`Artista_idArtista`, `Musica_idMusica`) VALUES
	(1, 1),
	(2, 1),
	(1, 2),
	(4, 12);
/*!40000 ALTER TABLE `composicao` ENABLE KEYS */;

-- Copiando estrutura para tabela memes.genero
DROP TABLE IF EXISTS `genero`;
CREATE TABLE IF NOT EXISTS `genero` (
  `idGenero` int(11) NOT NULL AUTO_INCREMENT,
  `nomeGenero` varchar(45) NOT NULL,
  PRIMARY KEY (`idGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.genero: ~4 rows (aproximadamente)
DELETE FROM `genero`;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` (`idGenero`, `nomeGenero`) VALUES
	(65, 'Rock'),
	(78, 'Pop'),
	(80, 'Classic');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;

-- Copiando estrutura para tabela memes.musica
DROP TABLE IF EXISTS `musica`;
CREATE TABLE IF NOT EXISTS `musica` (
  `idMusica` int(11) NOT NULL AUTO_INCREMENT,
  `nomeMusica` varchar(45) NOT NULL,
  `score` float NOT NULL DEFAULT '0',
  `letra` text NOT NULL,
  `idAlbumMusica` int(11) NOT NULL,
  `idGeneroMusica` int(11) NOT NULL,
  PRIMARY KEY (`idMusica`),
  KEY `fk_Musica_Album1_idx` (`idAlbumMusica`),
  KEY `fk_Musica_Genero1_idx` (`idGeneroMusica`),
  CONSTRAINT `idAlbumMusica` FOREIGN KEY (`idAlbumMusica`) REFERENCES `album` (`idAlbum`),
  CONSTRAINT `idGeneroMusica` FOREIGN KEY (`idGeneroMusica`) REFERENCES `genero` (`idGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.musica: ~4 rows (aproximadamente)
DELETE FROM `musica`;
/*!40000 ALTER TABLE `musica` DISABLE KEYS */;
INSERT INTO `musica` (`idMusica`, `nomeMusica`, `score`, `letra`, `idAlbumMusica`, `idGeneroMusica`) VALUES
	(1, 'Jump', 10000, 'I get up, and nothing gets me down.\r\nYou got it tough. I\'ve seen the toughest all around.\r\nAnd I know, baby, just how you feel.\r\nYou\'ve got to roll with the punches to get to what\'s real\r\nOh can\'t you see me standing here,\r\nI\'ve got my back against the record machine\r\nI ain\'t the worst that you\'ve seen.\r\nOh can\'t you see what I mean?\r\nMight as well jump. Jump!\r\nMight as well jump.\r\nGo ahead, jump. Jump!\r\nGo ahead, jump.\r\nAaa-ohh Hey you! Who said that?\r\nBaby how you been?\r\nYou say you don\'t know, you won\'t know until you begin.\r\nWell can\'t you see me standing here,\r\nI\'ve got my back against the record machine\r\nI ain\'t the worst that you\'ve seen.\r\nOh can\'t you see what I mean?\r\nMight as well jump. Jump!\r\nGo ahead, jump.\r\nMight as well jump. Jump!\r\nGo ahead, jump.\r\n\r\n[Guitar solo]\r\n\r\n[Keyboard solo]\r\n\r\nMight as well jump. Jump!\r\nGo ahead, jump.\r\nGet it and jump. Jump!\r\nGo ahead, jump.', 3, 65),
	(2, 'a', 0, 'a', 3, 78),
	(11, 'asdasd', 0, 'asdasd', 5, 65),
	(12, 'Billie Jean', 0, 'She was more like a beauty queen\r\nFrom a movie scene\r\nI said, "Don\'t mind, but what do you mean\r\nI am the one\r\nWho will dance on the floor in the round?"\r\nShe said I am the one\r\nWho will dance on the floor in the round\r\n\r\nShe told me her name was Billie Jean\r\nAs she caused a scene\r\nThen every head turned with eyes that dreamed of being the one\r\nWho will dance on the floor in the round\r\n\r\nPeople always told me, "Be careful of what you do.\r\nAnd don\'t go around breaking young girls\' hearts."\r\nAnd mother always told me, "A-be careful of who you love,\r\nAnd be careful of what you do\r\n\'Cause the lie becomes the truth."\r\n\r\nBillie Jean is not my lover\r\nShe\'s just a girl who claims that I am the one\r\nBut the kid is not my son\r\nShe says I am the one\r\nBut the kid is not my son\r\n\r\nFor forty days and for forty nights\r\nLaw was on her side\r\nBut who can stand\r\nWhen she\'s in demand\r\nHer schemes and plans\r\n\'Cause we danced on the floor in the round\r\nSo take my strong advice\r\nJust remember to always think twice\r\n(Do think twice, do think twice.)\r\n\r\nShe told, "My baby, we\'d danced \'til three."\r\nThen she looked at me\r\nThen showed a photo of a baby cry\r\nHis eyes looked like mine, oh, no\r\nDo a dance on the floor in the round, baby\r\n\r\nA-people always told me, "Be careful of what you do\r\nAnd don\'t go around breaking young girls\' hearts."\r\n(Don\'t break no heart.)\r\nA-but she came and stood right by me\r\nAnd just the smell of sweet perfume\r\nAnd this happened much too soon\r\nAnd she called me to her room\r\n\r\nBillie Jean is not my lover\r\nShe\'s just a girl who claims that I am the one\r\nBut the kid is not my son\r\n(No, no, no, no, no, no, no, no.)\r\nBillie Jean is not my lover\r\nShe\'s just a girl who claims that I am the one\r\nBut the kid is not my son\r\nShe says I am the one\r\nBut the kid is not my son\r\n\r\nShe says I am the one\r\nBut the kid is not my son\r\n\r\nNo, no, no\r\n\r\nBillie Jean is not my lover\r\nShe\'s just a girl who claims that I am the one\r\n(No, there\'s not me, baby.)\r\nBut the kid is not my son\r\n(No, no, no, no, no, no, no.)\r\nShe says I am the one (No, babe.)\r\nBut the kid is not my son, no, no, no\r\n\r\nShe says I am the one\r\nYou know what you did\r\nShe says he is my son\r\nBreaking my heart, babe\r\nShe says I am the one\r\n\r\nBillie Jean is not my lover\r\nBillie Jean is not my lover\r\nBillie Jean is not my lover\r\nShe is the one\r\nBillie Jean is not my lover\r\nShe is the one\r\nDon\'t call me Billie Jean\r\nShe is the one\r\nBillie Jean is not my lover\r\nShe is the one\r\nBillie Jean is not my lover', 8, 78);
/*!40000 ALTER TABLE `musica` ENABLE KEYS */;

-- Copiando estrutura para tabela memes.participa
DROP TABLE IF EXISTS `participa`;
CREATE TABLE IF NOT EXISTS `participa` (
  `Musica_idMusica` int(11) NOT NULL,
  `Artista_idArtista` int(11) NOT NULL,
  `papel` varchar(12) NOT NULL,
  PRIMARY KEY (`Musica_idMusica`,`Artista_idArtista`),
  KEY `fk_Musica_has_Artista_Artista1_idx` (`Artista_idArtista`),
  KEY `fk_Musica_has_Artista_Musica1_idx` (`Musica_idMusica`),
  CONSTRAINT `fk_Musica_has_Artista_Artista1` FOREIGN KEY (`Artista_idArtista`) REFERENCES `artista` (`idArtista`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Musica_has_Artista_Musica1` FOREIGN KEY (`Musica_idMusica`) REFERENCES `musica` (`idMusica`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.participa: ~4 rows (aproximadamente)
DELETE FROM `participa`;
/*!40000 ALTER TABLE `participa` DISABLE KEYS */;
INSERT INTO `participa` (`Musica_idMusica`, `Artista_idArtista`, `papel`) VALUES
	(1, 1, '1'),
	(1, 2, '1'),
	(2, 2, '2'),
	(12, 4, '1');
/*!40000 ALTER TABLE `participa` ENABLE KEYS */;

-- Copiando estrutura para tabela memes.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `idR` varchar(45) NOT NULL,
  `tipo` tinyint(4) NOT NULL DEFAULT '0',
  `perfil` text NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `idR` (`idR`)
) ENGINE=InnoDB AUTO_INCREMENT=572 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.usuario: ~6 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idUsuario`, `email`, `idR`, `tipo`, `perfil`) VALUES
	(1, 'memes', 'memes', 0, ''),
	(2, 'asd', '1', 0, ''),
	(3, 'Noub Master', '2', 0, ''),
	(15, 'noubmaster@gmail.com', '114906338798410851834', 2, 'https://lh4.googleusercontent.com/-36es8WyUfY0/AAAAAAAAAAI/AAAAAAAAAMw/KTD0N0D7HzA/s96-c/photo.jpg'),
	(19, 'thenuubgamer@gmail.com', '108493248676886266125', 2, 'https://lh5.googleusercontent.com/-yDJi2MiItnA/AAAAAAAAAAI/AAAAAAAAAAA/ACnBePbqzr0wz0KkYzcoRMi2v_9AINnHjA/s96-c/photo.jpg'),
	(76, 'elishaphp@gmail.com', '107170213645449942722', 1, 'https://lh5.googleusercontent.com/-eTfe1uR0mnE/AAAAAAAAAAI/AAAAAAAAAHw/w1BgcRuJhT0/s96-c/photo.jpg'),
	(90, 'eliseuphp@gmail.com', '112355985130332857702', 0, 'https://lh3.googleusercontent.com/-snpJLNdEd6U/AAAAAAAAAAI/AAAAAAAAAAA/ACnBePbyntDY-nS4Z_lMbSjJToCLirOF7w/s96-c/photo.jpg');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
