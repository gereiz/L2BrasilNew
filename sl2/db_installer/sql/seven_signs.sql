/*
MySQL Data Transfer
Source Host: localhost
Source Database: l2jdb
Target Host: localhost
Target Database: l2jdb
Date: 30/05/2025 09:41:17
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for seven_signs
-- ----------------------------
DROP TABLE IF EXISTS `seven_signs`;
CREATE TABLE `seven_signs` (
  `charId` int(11) NOT NULL DEFAULT 0,
  `cabal` varchar(4) NOT NULL DEFAULT '',
  `old_cabal` varchar(4) NOT NULL DEFAULT '',
  `seal` int(1) NOT NULL DEFAULT 0,
  `red_stones` int(11) NOT NULL DEFAULT 0,
  `green_stones` int(11) NOT NULL DEFAULT 0,
  `blue_stones` int(11) NOT NULL DEFAULT 0,
  `ancient_adena_amount` decimal(20,0) NOT NULL DEFAULT 0,
  `contribution_score` decimal(20,0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`charId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
