/*
MySQL Data Transfer
Source Host: localhost
Source Database: l2jdb
Target Host: localhost
Target Database: l2jdb
Date: 30/05/2025 09:40:26
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for obj_restrictions
-- ----------------------------
DROP TABLE IF EXISTS `obj_restrictions`;
CREATE TABLE `obj_restrictions` (
  `entry_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `obj_Id` int(11) unsigned NOT NULL DEFAULT 0,
  `type` varchar(50) NOT NULL DEFAULT '',
  `delay` int(11) NOT NULL DEFAULT -1,
  `message` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
