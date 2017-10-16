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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.album: ~7 rows (aproximadamente)
DELETE FROM `album`;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` (`idAlbum`, `nomeAlbum`, `ano`) VALUES
	(3, '1984', '1984'),
	(5, 'Van Halen II', '1979'),
	(6, 'Van Halen', '1978'),
	(7, 'Off the wall', '1979'),
	(8, 'Thriller', '1982'),
	(9, 'News of the World', '1977'),
	(10, 'War', '1983');
/*!40000 ALTER TABLE `album` ENABLE KEYS */;

-- Copiando estrutura para tabela memes.artista
DROP TABLE IF EXISTS `artista`;
CREATE TABLE IF NOT EXISTS `artista` (
  `idArtista` int(11) NOT NULL AUTO_INCREMENT,
  `nomeArtista` varchar(45) NOT NULL,
  PRIMARY KEY (`idArtista`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.artista: ~3 rows (aproximadamente)
DELETE FROM `artista`;
/*!40000 ALTER TABLE `artista` DISABLE KEYS */;
INSERT INTO `artista` (`idArtista`, `nomeArtista`) VALUES
	(1, 'Van Halen'),
	(2, 'Queen'),
	(4, 'Michael Jackson'),
	(5, 'U2');
/*!40000 ALTER TABLE `artista` ENABLE KEYS */;

-- Copiando estrutura para tabela memes.avaliacao
DROP TABLE IF EXISTS `avaliacao`;
CREATE TABLE IF NOT EXISTS `avaliacao` (
  `idAvaliacao` int(11) NOT NULL AUTO_INCREMENT,
  `nota` int(11) NOT NULL,
  `comentario` varchar(200) DEFAULT NULL,
  `revisao` bit(1) DEFAULT NULL,
  `idMusicaAvaliacao` int(11) NOT NULL,
  `idUsuarioAvaliacao` int(11) NOT NULL,
  PRIMARY KEY (`idAvaliacao`),
  KEY `fk_Avaliacao_Musica1_idx` (`idMusicaAvaliacao`),
  KEY `fk_Avaliacao_Usuario1_idx` (`idUsuarioAvaliacao`),
  CONSTRAINT `fk_Avaliacao_Musica1` FOREIGN KEY (`idMusicaAvaliacao`) REFERENCES `musica` (`idMusica`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Avaliacao_Usuario1` FOREIGN KEY (`idUsuarioAvaliacao`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.avaliacao: ~43 rows (aproximadamente)
DELETE FROM `avaliacao`;
/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
INSERT INTO `avaliacao` (`idAvaliacao`, `nota`, `comentario`, `revisao`, `idMusicaAvaliacao`, `idUsuarioAvaliacao`) VALUES
	(3, 1, '1', b'1', 2, 19),
	(4, 100000, 'melhor musica ja criada pelo ser humano', b'0', 1, 90),
	(6, 4, 'aaaaa', b'0', 1, 15),
	(7, 5, 'eu amo michael jackson <3', b'0', 12, 15),
	(8, 4, 'asd', b'0', 12, 15),
	(9, 1, 'musicas muito dificil de tocar na bateria', b'0', 11, 15),
	(10, 5, 'a', b'0', 12, 15),
	(11, 3, 'aa', b'0', 12, 15),
	(12, 3, 'a', b'0', 12, 15),
	(13, 4, 'aa', b'0', 12, 15),
	(17, 4, 'asd', b'0', 2, 15),
	(18, 3, 'asdasd', b'0', 2, 15),
	(19, 4, 'asdasdada', b'0', 2, 15),
	(20, 4, 'asdasdasd', b'0', 2, 15),
	(21, 4, 'dw', b'0', 2, 15),
	(22, 4, 'dsadawd', b'0', 2, 15),
	(23, 4, 'dasdasdad', b'0', 2, 15),
	(24, 4, 'asdasdad', b'0', 2, 15),
	(25, 5, 'asdasd', b'0', 2, 15),
	(26, 5, 'fasfdasdf', b'0', 2, 15),
	(27, 1, 'wowowowowow', b'0', 2, 15),
	(28, 4, 'asdasd', b'0', 2, 15),
	(29, 4, 'asdasdasda', b'0', 1, 15),
	(30, 4, 'asdasdasda', b'0', 1, 15),
	(31, 4, 'asdasdasda', b'0', 1, 15),
	(32, 4, 'asdasdasda', b'0', 1, 15),
	(33, 4, 'asdasdasda', b'0', 1, 15),
	(34, 5, 'asdasdasd', b'0', 1, 15),
	(35, 4, 'asdasd', b'0', 11, 15),
	(36, 5, 'dsasdsasd', b'0', 11, 15),
	(37, 5, 'asdf', b'0', 11, 15),
	(38, 2, 'asd', b'0', 11, 15),
	(39, 3, 'asdasd', b'0', 11, 15),
	(40, 3, 'asdasd', b'0', 11, 15),
	(41, 3, 'asdasd', b'0', 11, 15),
	(42, 3, 'asdasd', b'0', 11, 15),
	(43, 3, 'asdasd', b'0', 11, 15),
	(44, 3, 'asdasd', b'0', 11, 15),
	(45, 3, 'asdasd', b'0', 11, 15),
	(46, 3, 'asdasd', b'0', 11, 15),
	(47, 3, 'asdasd', b'0', 11, 15),
	(48, 3, 'asdasd', b'0', 11, 15),
	(49, 3, 'asdasd', b'0', 11, 15);
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

-- Copiando dados para a tabela memes.composicao: ~4 rows (aproximadamente)
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
  `faixa` int(11) DEFAULT NULL,
  `idAlbumMusica` int(11) NOT NULL,
  `idGeneroMusica` int(11) NOT NULL,
  PRIMARY KEY (`idMusica`),
  KEY `fk_Musica_Album1_idx` (`idAlbumMusica`),
  KEY `fk_Musica_Genero1_idx` (`idGeneroMusica`),
  CONSTRAINT `idAlbumMusica` FOREIGN KEY (`idAlbumMusica`) REFERENCES `album` (`idAlbum`),
  CONSTRAINT `idGeneroMusica` FOREIGN KEY (`idGeneroMusica`) REFERENCES `genero` (`idGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.musica: ~7 rows (aproximadamente)
DELETE FROM `musica`;
/*!40000 ALTER TABLE `musica` DISABLE KEYS */;
INSERT INTO `musica` (`idMusica`, `nomeMusica`, `score`, `letra`, `faixa`, `idAlbumMusica`, `idGeneroMusica`) VALUES
	(1, 'Jump', 10000, 'I get up, and nothing gets me down.\r\nYou got it tough. I\'ve seen the toughest all around.\r\nAnd I know, baby, just how you feel.\r\nYou\'ve got to roll with the punches to get to what\'s real\r\nOh can\'t you see me standing here,\r\nI\'ve got my back against the record machine\r\nI ain\'t the worst that you\'ve seen.\r\nOh can\'t you see what I mean?\r\nMight as well jump. Jump!\r\nMight as well jump.\r\nGo ahead, jump. Jump!\r\nGo ahead, jump.\r\nAaa-ohh Hey you! Who said that?\r\nBaby how you been?\r\nYou say you don\'t know, you won\'t know until you begin.\r\nWell can\'t you see me standing here,\r\nI\'ve got my back against the record machine\r\nI ain\'t the worst that you\'ve seen.\r\nOh can\'t you see what I mean?\r\nMight as well jump. Jump!\r\nGo ahead, jump.\r\nMight as well jump. Jump!\r\nGo ahead, jump.\r\n\r\n[Guitar solo]\r\n\r\n[Keyboard solo]\r\n\r\nMight as well jump. Jump!\r\nGo ahead, jump.\r\nGet it and jump. Jump!\r\nGo ahead, jump.', 2, 3, 65),
	(2, 'We Are The Champions', 0, 'I\'ve paid my dues\r\nTime after time\r\nI\'ve done my sentence\r\nBut committed no crime\r\n\r\nAnd bad mistakes\r\nI\'ve made a few\r\nI\'ve had my share of sand\r\nKicked in my face\r\nBut I\'ve come through\r\nAnd we mean to go on and on and on and on\r\n\r\nWe are the champions, my friends\r\nAnd we\'ll keep on fighting\r\n\'Till the end\r\nWe are the champions\r\nWe are the champions\r\nNo time for losers\r\n\'Cause we are the champions of the world\r\n\r\nI\'ve taken my bows\r\nAnd my curtain calls\r\nYou brought me fame and fortune\r\nAnd everything that goes with it\r\nI thank you all\r\n\r\nBut it\'s been no bed of roses\r\nNo pleasure cruise\r\nI consider it a challenge before\r\nThe whole human race\r\nAnd I ain\'t gonna lose\r\nAnd we mean to go on and on and on and on\r\n\r\nWe are the champions, my friends\r\nAnd we\'ll keep on fighting\r\n\'Till the end\r\nWe are the champions\r\nWe are the champions\r\nNo time for losers\r\n\'Cause we are the champions of the world\r\n\r\nWe are the champions, my friends\r\nAnd we\'ll keep on fighting\r\n\'Till the end\r\nWe are the champions\r\nWe are the champions\r\nNo time for losers\r\n\'Cause we are the champions', 2, 9, 65),
	(11, 'Sunday Bloody Sunday', 0, 'I can\'t believe the news today\r\nI can\'t close my eyes, make them go away\r\n\r\nHow long?\r\nHow long must we sing this song?\r\nHow long\r\nHow long?\r\nTonight we can be as one\r\nTonight\r\n\r\nBroken bottles under children\'s feet\r\nAnd bodies strewn across a dead end street\r\nBut I won\'t heed the battle call\r\nIt puts my back up, puts my back up\r\nAgainst the wall!\r\n\r\nSunday bloody sunday\r\nSunday bloody sunday\r\nSunday bloody sunday\r\n\r\nAnd the battles just begun\r\nThere\'s many lost\r\nBut tell me who has won?\r\nThe trenches dug within our hearts\r\nAnd mothers, children, brothers, sisters torn apart\r\n\r\nSunday bloody sunday\r\nSunday bloody sunday\r\n\r\nHow long?\r\nHow long must we sing this song?\r\nHow long\r\nHow long?\r\nCause tonight we can be as one\r\nTonight\r\n\r\nTonight\r\nTonight\r\nTonight\r\nTonight\r\n\r\nWipe the tears of your eyes\r\nWipe your tears away\r\nWipe your tears away\r\nWipe bloodshot eyes\r\n\r\nSunday bloody sunday\r\nSunday bloody sunday\r\nSunday bloody sunday\r\nSunday bloody sunday\r\nSunday bloody sunday\r\nSunday bloody sunday\r\n\r\nAnd it\'s true we are immune\r\nWhen fact is fiction and Tv is reality\r\nAnd today the millions cry\r\nWe eat and drink while tomorrow they die\r\nThe real battle just begun\r\nTo claim the victory Jesus won\r\nOn a sunday, bloody sunday\r\n\r\nSunday bloody sunday\r\nSunday bloody sunday\r\nSunday bloody sunday\r\nSunday bloody sunday\r\nSunday bloody sunday', 1, 10, 65),
	(12, 'Billie Jean', 0, 'She was more like a beauty queen\r\nFrom a movie scene\r\nI said, "Don\'t mind, but what do you mean\r\nI am the one\r\nWho will dance on the floor in the round?"\r\nShe said I am the one\r\nWho will dance on the floor in the round\r\n\r\nShe told me her name was Billie Jean\r\nAs she caused a scene\r\nThen every head turned with eyes that dreamed of being the one\r\nWho will dance on the floor in the round\r\n\r\nPeople always told me, "Be careful of what you do.\r\nAnd don\'t go around breaking young girls\' hearts."\r\nAnd mother always told me, "A-be careful of who you love,\r\nAnd be careful of what you do\r\n\'Cause the lie becomes the truth."\r\n\r\nBillie Jean is not my lover\r\nShe\'s just a girl who claims that I am the one\r\nBut the kid is not my son\r\nShe says I am the one\r\nBut the kid is not my son\r\n\r\nFor forty days and for forty nights\r\nLaw was on her side\r\nBut who can stand\r\nWhen she\'s in demand\r\nHer schemes and plans\r\n\'Cause we danced on the floor in the round\r\nSo take my strong advice\r\nJust remember to always think twice\r\n(Do think twice, do think twice.)\r\n\r\nShe told, "My baby, we\'d danced \'til three."\r\nThen she looked at me\r\nThen showed a photo of a baby cry\r\nHis eyes looked like mine, oh, no\r\nDo a dance on the floor in the round, baby\r\n\r\nA-people always told me, "Be careful of what you do\r\nAnd don\'t go around breaking young girls\' hearts."\r\n(Don\'t break no heart.)\r\nA-but she came and stood right by me\r\nAnd just the smell of sweet perfume\r\nAnd this happened much too soon\r\nAnd she called me to her room\r\n\r\nBillie Jean is not my lover\r\nShe\'s just a girl who claims that I am the one\r\nBut the kid is not my son\r\n(No, no, no, no, no, no, no, no.)\r\nBillie Jean is not my lover\r\nShe\'s just a girl who claims that I am the one\r\nBut the kid is not my son\r\nShe says I am the one\r\nBut the kid is not my son\r\n\r\nShe says I am the one\r\nBut the kid is not my son\r\n\r\nNo, no, no\r\n\r\nBillie Jean is not my lover\r\nShe\'s just a girl who claims that I am the one\r\n(No, there\'s not me, baby.)\r\nBut the kid is not my son\r\n(No, no, no, no, no, no, no.)\r\nShe says I am the one (No, babe.)\r\nBut the kid is not my son, no, no, no\r\n\r\nShe says I am the one\r\nYou know what you did\r\nShe says he is my son\r\nBreaking my heart, babe\r\nShe says I am the one\r\n\r\nBillie Jean is not my lover\r\nBillie Jean is not my lover\r\nBillie Jean is not my lover\r\nShe is the one\r\nBillie Jean is not my lover\r\nShe is the one\r\nDon\'t call me Billie Jean\r\nShe is the one\r\nBillie Jean is not my lover\r\nShe is the one\r\nBillie Jean is not my lover', 6, 8, 78),
	(13, 'Hot For Teacher', 0, '[Spoken:]\r\n"Oh wow, man !" \r\n"Wait a second man. Whaddaya think the teacher\'s gonna look like this year ?" \r\n"My butt, man !" \r\nT-T-Teacher stop that screaming, teacher don\'t you see ? \r\nDon\'t wanna be no uptown fool. \r\nMaybe I should go to hell, but I\'m doin\' well, teacher needs to see me after school. \r\n\r\n[Chorus:]\r\nI think of all the education that I missed. \r\nBut then my homework was never quite like this. \r\nGot it bad, got it bad, got it bad, \r\nI\'m hot for teacher. \r\nI got it bad, so bad, \r\nI\'m hot for teacher. \r\n\r\n[Spoken:]\r\n"Hey, I heard you missed us, we\'re back !" \r\n"I brought my pencil" \r\n"Gimme something to write on, man" \r\nI heard about your lessons, but lessons are so cold. \r\nI know about this school. \r\nLittle girl from cherry lane, how did you get so bold ? \r\nHow did you know that golden rule ? \r\n\r\n[Chorus:]\r\n\r\n[Guitar solo]\r\n\r\n"Oh man, I think the clock is slow" \r\n"I don\'t feel tardy" \r\n"Class dismissed"', 6, 3, 65),
	(14, 'Jamie\'s Cryin', 0, 'She saw the look in his eyes, \'n\' she knew better \r\nHe wanted her tonight, ah, and it was now or never \r\nHe made her feel so sad \r\nOh, whoa, whoa, Jamie\'s cryin\' \r\nOh, whoa, whoa, Jamie\'s cryin\' \r\nNow Jamie wouldn\'t say, "All right", ah, she knew he\'d forget her \r\n\'N\' so they said goodnight, ah, oh, \'n\' now he\'s gone forever \r\nShe want\'s to send him a letter, uh yeah yeah \r\nUh just to try to make herself feel better \r\nIt said "Gimme (gimme a call sometime") \r\nBut she knows what that\'ll get her \r\nOh, whoa, whoa, Jamie\'s cryin\' \r\nOh, whoa, whoa, Jamie\'s cryin\' \r\nNow Jamie\'s been in love before (ahhh) \r\nAnd she knows what love is for (ahhh) \r\nIt should mean, a little, a little more (ahhh) \r\nThan one night stands, whoo \r\nShe want\'s to send him a letter, uh yeah yeah \r\nJust to try to make herself feel better \r\nIt said "Gimme (gimme a call sometime") \r\nBut she knows what that\'ll get her \r\nOh, whoa, whoa, Jamie\'s cryin\' \r\nOh, whoa, whoa, Jamie\'s cryin\' \r\n[Repeat til outro]', 6, 6, 65),
	(15, 'Runnin\' With The Devil', 0, 'I live my life like there\'s no tomorrow\r\nAnd all I\'ve got I had to steal\r\nLeast I don\'t need to beg or borrow\r\nYes, I\'m living at a pace that kills\r\n\r\nRunnin\' with the devil\r\nRunnin\' with the devil\r\nI\'ll tell y\'all about it\r\n\r\nI found the simple life ain\'t so simple\r\nWhen I jumped out on that road\r\nI got no love, no love you\'d call real\r\nAin\'t got nobody waiting at home\r\n\r\nRunnin\' with the devil\r\nGod damn it lady, you know I ain\'t lying to ya\r\nI\'m wanna tell you one time\r\nAah-yeah\r\nRunning with the devil\r\nYes I am\r\nYeah\r\n\r\nWoo\r\nWoo\r\n\r\nYou know I, I found the simple life, weren\'t so simple, no\r\nWhen I jumped out on that road\r\nGot no love, no love you\'d call real\r\nGot nobody waiting at home\r\n\r\nRunnin\' with the devil\r\nRunnin\' with the devil\r\n\r\nRunnin\' with the devil\r\nRunnin\' with the devil\r\n[Repeat and fade]', 1, 6, 65);
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

-- Copiando dados para a tabela memes.participa: ~7 rows (aproximadamente)
DELETE FROM `participa`;
/*!40000 ALTER TABLE `participa` DISABLE KEYS */;
INSERT INTO `participa` (`Musica_idMusica`, `Artista_idArtista`, `papel`) VALUES
	(1, 1, '1'),
	(2, 2, '1'),
	(11, 5, '2'),
	(12, 4, '1'),
	(13, 1, '1'),
	(14, 1, '1'),
	(15, 1, '1');
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
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela memes.usuario: ~5 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idUsuario`, `email`, `idR`, `tipo`, `perfil`) VALUES
	(15, 'noubmaster@gmail.com', '114906338798410851834', 2, 'https://lh4.googleusercontent.com/-36es8WyUfY0/AAAAAAAAAAI/AAAAAAAAAMw/KTD0N0D7HzA/s96-c/photo.jpg'),
	(19, 'thenuubgamer@gmail.com', '108493248676886266125', 2, 'https://lh5.googleusercontent.com/-yDJi2MiItnA/AAAAAAAAAAI/AAAAAAAAAAA/ACnBePbqzr0wz0KkYzcoRMi2v_9AINnHjA/s96-c/photo.jpg'),
	(76, 'elishaphp@gmail.com', '107170213645449942722', 1, 'https://lh5.googleusercontent.com/-eTfe1uR0mnE/AAAAAAAAAAI/AAAAAAAAAHw/w1BgcRuJhT0/s96-c/photo.jpg'),
	(90, 'eliseuphp@gmail.com', '112355985130332857702', 0, 'https://lh3.googleusercontent.com/-snpJLNdEd6U/AAAAAAAAAAI/AAAAAAAAAAA/ACnBePbyntDY-nS4Z_lMbSjJToCLirOF7w/s96-c/photo.jpg');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
