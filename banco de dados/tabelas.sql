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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.album: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` (`idAlbum`, `nomeAlbum`, `ano`) VALUES
	(3, '1984', '1984'),
	(5, 'Van Halen II', '1979'),
	(6, 'Van Halen', '1978'),
	(7, 'Off the wall', '1979');
/*!40000 ALTER TABLE `album` ENABLE KEYS */;

-- Copiando estrutura para tabela memes.artista
DROP TABLE IF EXISTS `artista`;
CREATE TABLE IF NOT EXISTS `artista` (
  `idArtista` int(11) NOT NULL AUTO_INCREMENT,
  `nomeArtista` varchar(45) NOT NULL,
  PRIMARY KEY (`idArtista`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.artista: ~3 rows (aproximadamente)
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

-- Copiando dados para a tabela memes.composicao: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `composicao` DISABLE KEYS */;
INSERT INTO `composicao` (`Artista_idArtista`, `Musica_idMusica`) VALUES
	(1, 1),
	(2, 1),
	(1, 2);
/*!40000 ALTER TABLE `composicao` ENABLE KEYS */;

-- Copiando estrutura para tabela memes.genero
DROP TABLE IF EXISTS `genero`;
CREATE TABLE IF NOT EXISTS `genero` (
  `idGenero` int(11) NOT NULL AUTO_INCREMENT,
  `nomeGenero` varchar(45) NOT NULL,
  PRIMARY KEY (`idGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.genero: ~3 rows (aproximadamente)
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.musica: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `musica` DISABLE KEYS */;
INSERT INTO `musica` (`idMusica`, `nomeMusica`, `score`, `letra`, `idAlbumMusica`, `idGeneroMusica`) VALUES
	(1, 'Jump', 10000, 'I get up, and nothing gets me down.', 3, 65),
	(2, 'a', 0, 'a', 3, 78),
	(11, 'asdasd', 0, 'asdasd', 5, 65);
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

-- Copiando dados para a tabela memes.participa: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `participa` DISABLE KEYS */;
INSERT INTO `participa` (`Musica_idMusica`, `Artista_idArtista`, `papel`) VALUES
	(1, 1, '1'),
	(1, 2, '1'),
	(2, 2, '2');
/*!40000 ALTER TABLE `participa` ENABLE KEYS */;

-- Copiando estrutura para tabela memes.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `perfil` text NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.usuario: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idUsuario`, `login`, `senha`, `perfil`) VALUES
	(1, 'memes', 'memes', '1'),
	(2, 'Noub Master', NULL, 'noubmaster@gmail.com'),
	(3, 'Noub Master', NULL, 'noubmaster@gmail.com');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
