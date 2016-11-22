-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 05 Septembre 2014 à 16:36
-- Version du serveur :  5.6.15-log
-- Version de PHP :  5.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `gestioncatalogue`
--

-- --------------------------------------------------------

--
-- Structure de la table `catalogue`
--

CREATE TABLE IF NOT EXISTS `catalogue` (
  `idCatalogue` int(6) NOT NULL AUTO_INCREMENT COMMENT 'Numéro d''identification du catalogue',
  `nom` varchar(15) NOT NULL COMMENT 'Nom du catalogue',
  `code` varchar(15) NOT NULL COMMENT 'Code secret',
  `dateCreation` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date de création',
  `idCreateur` int(6) NOT NULL COMMENT 'Créateur du catalogue',
  PRIMARY KEY (`idCatalogue`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `catalogue`
--

INSERT INTO `catalogue` (`idCatalogue`, `nom`, `code`, `dateCreation`, `idCreateur`) VALUES
(1, 'dep', 'dep', '2013-01-10 22:39:59', 5),
(2, 'dep2', 'dep2', '2013-01-10 22:39:59', 6),
(3, '', '', '2013-01-12 00:40:47', 6),
(4, 'java', 'mou', '2013-01-25 17:08:03', 18);

-- --------------------------------------------------------

--
-- Structure de la table `dossier`
--

CREATE TABLE IF NOT EXISTS `dossier` (
  `idDossier` int(6) NOT NULL AUTO_INCREMENT,
  `nom` varchar(15) NOT NULL,
  `type` varchar(15) NOT NULL,
  `etat` varchar(15) DEFAULT 'Actif',
  `idCreateur` int(6) NOT NULL,
  `dateCreation` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateFermeture` timestamp NULL DEFAULT NULL,
  `idCatalogue` int(6) NOT NULL,
  PRIMARY KEY (`idDossier`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `dossier`
--

INSERT INTO `dossier` (`idDossier`, `nom`, `type`, `etat`, `idCreateur`, `dateCreation`, `dateFermeture`, `idCatalogue`) VALUES
(1, 'do1', 'do1', 'actif', 5, '2013-01-10 22:44:24', NULL, 1),
(2, 'do2', 'do2', 'actif', 5, '2013-01-10 22:44:24', NULL, 1),
(3, 'do3', 'do3', 'actif', 6, '2013-01-10 22:46:03', NULL, 2),
(4, 'do4', 'do4', 'actif', 6, '2013-01-10 22:46:03', NULL, 2),
(5, 'asdasd', 'asdasd', 'Actif', 6, '2013-01-21 16:54:15', NULL, 2),
(6, 'tyutyu', 'tyutyu', 'Actif', 6, '2013-01-21 16:57:30', NULL, 2),
(7, 'education', 'edu', 'Actif', 18, '2013-01-25 17:08:40', NULL, 4);

-- --------------------------------------------------------

--
-- Structure de la table `membre`
--

CREATE TABLE IF NOT EXISTS `membre` (
  `idMembre` int(6) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `motDePasse` varchar(20) NOT NULL,
  `courriel` varchar(30) NOT NULL,
  `actif` tinyint(1) DEFAULT '1',
  `catalogueDefaut` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idMembre`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Contenu de la table `membre`
--

INSERT INTO `membre` (`idMembre`, `nom`, `motDePasse`, `courriel`, `actif`, `catalogueDefaut`) VALUES
(5, 'test', 'test', 'test', 1, '1'),
(6, 'p', 'p', 'p', 1, '2'),
(7, 't', 't', 't', 1, '2'),
(8, 'q', 'q', 'q', 1, NULL),
(9, 'h', 'h', 'h', 1, NULL),
(10, 'w', 'w', 'w', 1, NULL),
(11, 'noob', 'n', 'n', 1, '2'),
(16, 'j', 'j', 'j', 1, NULL),
(17, 'moderateur', 'm', 'm', 1, '2'),
(18, 'moumene', 'moumene', 'moumene', 1, '4');

-- --------------------------------------------------------

--
-- Structure de la table `membreoperation`
--

CREATE TABLE IF NOT EXISTS `membreoperation` (
  `idMembre` int(6) NOT NULL,
  `numReference` int(10) NOT NULL,
  `dateHeure` datetime NOT NULL,
  `description` varchar(100) NOT NULL,
  `idCatalogue` int(6) NOT NULL,
  PRIMARY KEY (`idMembre`,`numReference`,`dateHeure`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `miseajour`
--

CREATE TABLE IF NOT EXISTS `miseajour` (
  `idMAJ` int(6) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `nature` varchar(30) NOT NULL,
  `info` text NOT NULL,
  `idAuteur` int(6) NOT NULL,
  `idDossier` int(6) NOT NULL,
  PRIMARY KEY (`idMAJ`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE IF NOT EXISTS `operation` (
  `numReferance` int(10) NOT NULL,
  `nom` varchar(30) NOT NULL,
  PRIMARY KEY (`numReferance`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `permission`
--

CREATE TABLE IF NOT EXISTS `permission` (
  `idCatalogue` int(6) NOT NULL,
  `idMembre` int(6) NOT NULL,
  `valeurPermission` int(10) NOT NULL,
  PRIMARY KEY (`idCatalogue`,`idMembre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `permission`
--

INSERT INTO `permission` (`idCatalogue`, `idMembre`, `valeurPermission`) VALUES
(2, 6, 16777215),
(2, 7, 1),
(3, 11, 1),
(1, 11, 1),
(2, 11, 1),
(2, 17, 5794),
(4, 18, 16777215),
(4, 6, 1);

-- --------------------------------------------------------

--
-- Structure de la table `pointdiscussion`
--

CREATE TABLE IF NOT EXISTS `pointdiscussion` (
  `idPointDiscussion` int(6) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `type` varchar(15) NOT NULL,
  `etat` varchar(15) DEFAULT 'Actif',
  `idDossier` int(6) DEFAULT NULL,
  `idReunion` int(6) NOT NULL,
  PRIMARY KEY (`idPointDiscussion`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `pointdiscussion`
--

INSERT INTO `pointdiscussion` (`idPointDiscussion`, `nom`, `type`, `etat`, `idDossier`, `idReunion`) VALUES
(1, 'ppp', 'ppp', 'Actif', 3, 8),
(2, 'Tozo', 'Carlos', 'Actif', 4, 8),
(3, 'tyutyu', 'tyutyu', 'Actif', 3, 9),
(4, 'asda', 'asdasd', 'Actif', 4, 9),
(5, 'ASD', 'ASDASD', 'Actif', 0, 6),
(6, 'jkljkljkl', 'jkljkl', 'Actif', NULL, 9),
(7, 'informations', 'info', 'Actif', NULL, 11),
(8, 'educ', 'asdasd', 'Actif', 7, 11);

-- --------------------------------------------------------

--
-- Structure de la table `reunion`
--

CREATE TABLE IF NOT EXISTS `reunion` (
  `idReunion` int(6) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `titre` varchar(30) NOT NULL,
  `idCreateur` int(6) NOT NULL,
  `etat` varchar(15) DEFAULT 'Actif',
  `idCatalogue` int(6) NOT NULL,
  PRIMARY KEY (`idReunion`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `reunion`
--

INSERT INTO `reunion` (`idReunion`, `date`, `titre`, `idCreateur`, `etat`, `idCatalogue`) VALUES
(11, '2013-01-31 17:10:00', 'presentations', 18, 'Actif', 4),
(10, '2013-01-09 13:30:00', 'asdasd', 6, 'Ferme', 2),
(9, '2013-01-13 08:25:00', 'asdasd', 6, 'Actif', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
