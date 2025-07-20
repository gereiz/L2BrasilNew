/*
MySQL Data Transfer
Source Host: localhost
Source Database: l2jdb
Target Host: localhost
Target Database: l2jdb
Date: 30/05/2025 09:39:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for global_tasks
-- ----------------------------
DROP TABLE IF EXISTS `global_tasks`;
CREATE TABLE `global_tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task` varchar(50) NOT NULL DEFAULT '',
  `type` varchar(50) NOT NULL DEFAULT '',
  `last_activation` decimal(20,0) NOT NULL DEFAULT 0,
  `param1` varchar(100) NOT NULL DEFAULT '',
  `param2` varchar(100) NOT NULL DEFAULT '',
  `param3` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `global_tasks` VALUES ('1', 'olympiad_save', 'TYPE_FIXED_SHEDULED', '1662475936774', '900000', '1800000', '');
INSERT INTO `global_tasks` VALUES ('2', 'raid_points_reset', 'TYPE_GLOBAL_TASK', '1662261000742', '1', '00:10:00', '');
INSERT INTO `global_tasks` VALUES ('3', 'sp_recommendations', 'TYPE_GLOBAL_TASK', '1661875200189', '1', '13:00:00', '');
INSERT INTO `global_tasks` VALUES ('4', 'seven_signs_update', 'TYPE_FIXED_SHEDULED', '1278785265718', '1800000', '1800000', '');
INSERT INTO `global_tasks` VALUES ('5', 'clanleaderapply', 'TYPE_GLOBAL_TASK', '1662260400773', '1', '00:00:00', '');
