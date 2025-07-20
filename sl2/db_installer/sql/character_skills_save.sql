/*
MySQL Data Transfer
Source Host: localhost
Source Database: l2jdb
Target Host: localhost
Target Database: l2jdb
Date: 30/05/2025 09:35:55
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for character_skills_save
-- ----------------------------
DROP TABLE IF EXISTS `character_skills_save`;
CREATE TABLE `character_skills_save` (
  `charId` int(10) unsigned NOT NULL DEFAULT 0,
  `skill_id` int(11) NOT NULL DEFAULT 0,
  `skill_level` int(3) NOT NULL DEFAULT 1,
  `effect_count` int(11) NOT NULL DEFAULT 0,
  `effect_cur_time` int(11) NOT NULL DEFAULT 0,
  `reuse_delay` int(8) NOT NULL DEFAULT 0,
  `systime` bigint(20) unsigned NOT NULL DEFAULT 0,
  `restore_type` int(1) NOT NULL DEFAULT 0,
  `class_index` int(1) NOT NULL DEFAULT 0,
  `buff_index` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`charId`,`skill_id`,`class_index`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
