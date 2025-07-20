/*
MySQL Data Transfer
Source Host: localhost
Source Database: l2jdb
Target Host: localhost
Target Database: l2jdb
Date: 30/05/2025 09:38:59
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for fort_staticobjects
-- ----------------------------
DROP TABLE IF EXISTS `fort_staticobjects`;
CREATE TABLE `fort_staticobjects` (
  `fortId` int(11) NOT NULL DEFAULT 0,
  `id` int(11) NOT NULL DEFAULT 0,
  `name` varchar(30) NOT NULL,
  `x` int(11) NOT NULL DEFAULT 0,
  `y` int(11) NOT NULL DEFAULT 0,
  `z` int(11) NOT NULL DEFAULT 0,
  `range_xmin` int(11) NOT NULL DEFAULT 0,
  `range_ymin` int(11) NOT NULL DEFAULT 0,
  `range_zmin` int(11) NOT NULL DEFAULT 0,
  `range_xmax` int(11) NOT NULL DEFAULT 0,
  `range_ymax` int(11) NOT NULL DEFAULT 0,
  `range_zmax` int(11) NOT NULL DEFAULT 0,
  `hp` int(11) NOT NULL DEFAULT 0,
  `pDef` int(11) NOT NULL DEFAULT 0,
  `mDef` int(11) NOT NULL DEFAULT 0,
  `openType` varchar(5) NOT NULL DEFAULT 'false',
  `commanderDoor` varchar(5) NOT NULL DEFAULT 'false',
  `objectType` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `id` (`fortId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `fort_staticobjects` VALUES ('101', '18220001', 'Gate_of_fort', '-50796', '155913', '-2102', '-50800', '155822', '-2102', '-50844', '155836', '-2102', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('101', '18220002', 'Gate_of_fort', '-53359', '156592', '-2081', '-53326', '156670', '-2081', '-53344', '156675', '-2081', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('101', '18220003', 'Gate_of_fort', '-53313', '156743', '-2081', '-53329', '156660', '-2081', '-53347', '156665', '-2081', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('101', '18220004', 'Gate_of_fort', '-52211', '156244', '-2081', '-52180', '156322', '-2081', '-52194', '156325', '-2081', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('101', '18220005', 'Gate_of_fort', '-52165', '156395', '-2081', '-52183', '156313', '-2081', '-52197', '156317', '-2081', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('101', '18220006', 'Gate_of_fort', '-52817', '157874', '-2053', '-52783', '157953', '-2053', '-52803', '157959', '-2053', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('101', '18220007', 'Gate_of_fort', '-52771', '158025', '-2053', '-52786', '157943', '-2053', '-52806', '157948', '-2053', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('101', '18220008', 'Gate_of_fort', '-54738', '157112', '-2102', '-54729', '157015', '-2102', '-54676', '157186', '-2102', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('101', '18220500', 'Shanty_flagpole', '-52752', '156493', '-1130', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('102', '19240001', 'Gate_of_fort', '-22326', '218062', '-3237', '-22337', '218141', '-3237', '-22315', '218141', '-3237', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('102', '19240002', 'Gate_of_fort', '-22326', '218220', '-3237', '-22337', '218138', '-3237', '-22315', '218138', '-3237', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('102', '19240003', 'Gate_of_fort', '-23550', '218784', '-3263', '-23545', '218773', '-3263', '-23557', '218772', '-3263', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('102', '19240004', 'Gate_of_fort', '-23550', '218940', '-3263', '-23558', '218852', '-3263', '-23542', '218852', '-3263', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('102', '19240005', 'Gate_of_fort', '-25126', '219880', '-3291', '-25137', '219960', '-3292', '-25115', '219960', '-3292', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('102', '19240006', 'Gate_of_fort', '-22106', '219722', '-3264', '-22114', '219801', '-3264', '-22097', '219801', '-3264', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('102', '19240007', 'Gate_of_fort', '-22106', '219880', '-3264', '-22114', '219801', '-3264', '-22097', '219801', '-3264', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('102', '19240008', 'Gate_of_fort', '-23305', '219722', '-3264', '-22114', '219801', '-3264', '-23297', '219801', '-3264', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('102', '19240009', 'Gate_of_fort', '-23305', '219880', '-3264', '-22114', '219801', '-3264', '-23297', '219801', '-3264', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('102', '19240010', 'Gate_of_fort', '-20241', '219888', '-3288', '-20250', '219967', '-3289', '-20230', '219968', '-3289', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('102', '19240011', 'Gate_of_fort', '-23211', '221582', '-3227', '-23221', '221661', '-3227', '-23201', '221661', '-3227', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('102', '19240012', 'Gate_of_fort', '-23211', '221740', '-3227', '-23221', '221661', '-3227', '-23202', '221661', '-3227', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('102', '19240500', 'Southern_flagpole', '-22702', '219801', '-2314', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('103', '20230003', 'Gate_of_fort', '15546', '186351', '-2972', '15440', '186380', '-2981', '15477', '186438', '-2981', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('103', '20230004', 'Gate_of_fort', '16180', '187607', '-2952', '16241', '187551', '-2954', '16255', '187574', '-2954', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('103', '20230005', 'Gate_of_fort', '16312', '187520', '-2952', '16235', '187554', '-2952', '16248', '187576', '-2952', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('103', '20230006', 'Gate_of_fort', '17540', '187015', '-2923', '17603', '186960', '-2923', '17618', '186980', '-2923', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('103', '20230007', 'Gate_of_fort', '17670', '186928', '-2923', '17594', '186964', '-2923', '17610', '186985', '-2923', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('103', '20230008', 'Gate_of_fort', '16848', '188606', '-2952', '16839', '188598', '-2952', '16851', '188617', '-2952', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('103', '20230009', 'Gate_of_fort', '16979', '188518', '-2952', '16904', '188554', '-2954', '16918', '188574', '-2954', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('103', '20230010', 'Gate_of_fort', '17826', '189764', '-2972', '17699', '189812', '-2977', '17727', '189869', '-2977', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('103', '20230500', 'Hive_flagpole', '16585', '188066', '-2002', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('104', '23210001', 'Gate_of_fort', '126069', '120674', '-2634', '125949', '120624', '-2634', '126177', '120729', '-2634', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('104', '23210002', 'Gate_of_fort', '126760', '121726', '-2613', '126752', '121718', '-2613', '126769', '121815', '-2613', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('104', '23210003', 'Gate_of_fort', '126760', '121884', '-2613', '126752', '121795', '-2613', '126770', '121895', '-2613', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('104', '23210004', 'Gate_of_fort', '124224', '122888', '-2583', '124225', '122879', '-2583', '124310', '122896', '-2583', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('104', '23210005', 'Gate_of_fort', '124382', '122888', '-2583', '124296', '122879', '-2583', '124393', '122898', '-2583', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('104', '23210006', 'Gate_of_fort', '126000', '123942', '-2613', '126001', '123933', '-2613', '126086', '123950', '-2613', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('104', '23210007', 'Gate_of_fort', '126158', '123942', '-2613', '126072', '123933', '-2613', '126169', '123952', '-2613', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('104', '23210008', 'Gate_of_fort', '127569', '123334', '-2571', '127561', '123326', '-2571', '127578', '123423', '-2571', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('104', '23210009', 'Gate_of_fort', '127569', '123492', '-2571', '127561', '123403', '-2571', '127579', '123503', '-2571', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('104', '23210010', 'Gate_of_fort', '126000', '122744', '-2613', '126001', '122735', '-2613', '126086', '122752', '-2613', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('104', '23210011', 'Gate_of_fort', '126158', '122744', '-2613', '126072', '122735', '-2613', '126169', '122754', '-2613', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('104', '23210012', 'Gate_of_fort', '126066', '125563', '-2634', '125946', '125513', '-2634', '126174', '125618', '-2634', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('104', '23210500', 'Valley_flagpole', '126084', '123350', '-1663', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('105', '22180001', 'Gate_of_fort', '74199', '2788', '-3093', '74131', '2726', '-3093', '74273', '2850', '-3093', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('105', '22180002', 'Gate_of_fort', '72418', '4689', '-3073', '72414', '4685', '-3073', '72483', '4745', '-3073', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('105', '22180003', 'Gate_of_fort', '72540', '4790', '-3073', '72474', '4734', '-3073', '72545', '4795', '-3073', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('105', '22180004', 'Gate_of_fort', '73178', '3764', '-3073', '73174', '3759', '-3073', '73245', '3819', '-3073', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('105', '22180005', 'Gate_of_fort', '73300', '3864', '-3073', '73235', '3808', '-3073', '73306', '3870', '-3073', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('105', '22180006', 'Gate_of_fort', '74141', '4890', '-3044', '74137', '4884', '-3044', '74208', '4946', '-3044', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('105', '22180007', 'Gate_of_fort', '74263', '4990', '-3044', '74197', '4934', '-3044', '74269', '4997', '-3044', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('105', '22180008', 'Gate_of_fort', '71596', '5960', '-3093', '71528', '5898', '-3093', '71670', '6022', '-3093', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('105', '22180500', 'Ivory_flagpole', '72856', '4281', '-2123', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('106', '24190005', 'Gate_of_fort', '153328', '56710', '-3303', '153263', '56636', '-3303', '153390', '56781', '-3303', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('106', '24190006', 'Gate_of_fort', '155311', '54864', '-3282', '155304', '54859', '-3282', '155369', '54931', '-3282', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('106', '24190007', 'Gate_of_fort', '155411', '54986', '-3282', '155354', '54919', '-3282', '155419', '54991', '-3282', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('106', '24190008', 'Gate_of_fort', '154384', '55624', '-3282', '154378', '55620', '-3282', '154440', '55690', '-3282', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('106', '24190009', 'Gate_of_fort', '154484', '55746', '-3282', '154427', '55680', '-3282', '154491', '55751', '-3282', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('106', '24190010', 'Gate_of_fort', '156501', '54106', '-3303', '156436', '54032', '-3304', '156564', '54179', '-3302', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('106', '24190011', 'Gate_of_fort', '156035', '56156', '-3253', '156029', '56151', '-3253', '156094', '56223', '-3253', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('106', '24190012', 'Gate_of_fort', '156135', '56278', '-3253', '156078', '56211', '-3253', '156142', '56284', '-3253', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('106', '24190500', 'Narsell_flagpole', '154894', '55308', '-2332', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('107', '25190001', 'Gate_of_fort', '189904', '37070', '-3460', '189784', '37020', '-3460', '190012', '37125', '-3460', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('107', '25190002', 'Gate_of_fort', '190612', '38122', '-3443', '190604', '38114', '-3443', '190621', '38211', '-3443', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('107', '25190003', 'Gate_of_fort', '190612', '38280', '-3443', '190604', '38191', '-3443', '190622', '38291', '-3443', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('107', '25190004', 'Gate_of_fort', '188076', '39284', '-3409', '188077', '39275', '-3409', '188162', '39292', '-3409', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('107', '25190005', 'Gate_of_fort', '188234', '39284', '-3409', '188148', '39275', '-3409', '188245', '39294', '-3409', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('107', '25190006', 'Gate_of_fort', '189852', '40340', '-3438', '189853', '40331', '-3438', '189938', '40348', '-3438', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('107', '25190007', 'Gate_of_fort', '190010', '40340', '-3438', '189924', '40331', '-3438', '190021', '40350', '-3438', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('107', '25190008', 'Gate_of_fort', '189852', '39139', '-3438', '189853', '39130', '-3438', '189938', '39147', '-3438', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('107', '25190009', 'Gate_of_fort', '190010', '39139', '-3438', '189924', '39130', '-3438', '190021', '39149', '-3438', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('107', '25190010', 'Gate_of_fort', '191421', '39730', '-3397', '191413', '39722', '-3397', '191430', '39819', '-3397', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('107', '25190011', 'Gate_of_fort', '191421', '39888', '-3397', '191413', '39799', '-3397', '191431', '39899', '-3397', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('107', '25190012', 'Gate_of_fort', '189913', '41955', '-3460', '189793', '41905', '-3460', '190021', '42010', '-3460', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('107', '25190500', 'Bayou_flagpole', '189933', '39745', '-2487', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('108', '23240001', 'Gate_of_fort', '116614', '203941', '-3382', '116564', '203865', '-3382', '116664', '204017', '-3382', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('108', '23240002', 'Gate_of_fort', '118905', '205272', '-3361', '118899', '205199', '-3361', '118949', '205276', '-3361', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('108', '23240003', 'Gate_of_fort', '118979', '205133', '-3361', '118935', '205130', '-3361', '118986', '205206', '-3361', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('108', '23240004', 'Gate_of_fort', '119430', '203983', '-3333', '119422', '203908', '-3333', '119476', '203987', '-3333', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('108', '23240005', 'Gate_of_fort', '119504', '203843', '-3333', '119459', '203839', '-3333', '119514', '203918', '-3333', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('108', '23240006', 'Gate_of_fort', '117847', '204708', '-3361', '117841', '204634', '-3361', '117892', '204712', '-3361', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('108', '23240007', 'Gate_of_fort', '117922', '204568', '-3361', '117877', '204565', '-3361', '117929', '204642', '-3361', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('108', '23240008', 'Gate_of_fort', '120248', '205879', '-3382', '120195', '205801', '-3382', '120298', '205955', '-3382', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('108', '23240500', 'WhiteSands_flagpole', '118419', '204919', '-2411', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('109', '24150001', 'Gate_of_fort', '158498', '-72104', '-2865', '158484', '-72116', '-2865', '158555', '-72026', '-2865', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('109', '24150002', 'Gate_of_fort', '158585', '-71973', '-2865', '158526', '-72051', '-2865', '158600', '-71959', '-2865', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('109', '24150003', 'Gate_of_fort', '157297', '-69092', '-2916', '157204', '-69194', '-2916', '157390', '-68988', '-2916', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('109', '24150004', 'Gate_of_fort', '159605', '-70707', '-2892', '159594', '-70715', '-2892', '159661', '-70632', '-2892', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('109', '24150005', 'Gate_of_fort', '159693', '-70576', '-2892', '159638', '-70652', '-2892', '159705', '-70567', '-2892', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('109', '24150006', 'Gate_of_fort', '158606', '-70040', '-2892', '158594', '-70049', '-2892', '158663', '-69962', '-2892', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('109', '24150007', 'Gate_of_fort', '158694', '-69909', '-2892', '158634', '-69986', '-2892', '158708', '-69899', '-2892', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('109', '24150008', 'Gate_of_fort', '161354', '-71814', '-2916', '161250', '-71924', '-2916', '161461', '-71702', '-2916', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('109', '24150009', 'Gate_of_fort', '160742', '-70553', '-2899', '160731', '-70612', '-2899', '160820', '-70539', '-2899', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('109', '24150010', 'Gate_of_fort', '160872', '-70640', '-2899', '160794', '-70651', '-2899', '160881', '-70584', '-2899', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('109', '24150011', 'Gate_of_fort', '159854', '-68987', '-2853', '159843', '-69042', '-2853', '159932', '-68974', '-2853', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('109', '24150012', 'Gate_of_fort', '159985', '-69075', '-2853', '159909', '-69087', '-2853', '159997', '-69016', '-2853', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('109', '24150500', 'Borderland_flagpole', '159145', '-70302', '-1942', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('110', '22160001', 'Gate_of_fort', '68834', '-63635', '-2834', '68719', '-63726', '-2834', '68952', '-63548', '-2834', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('110', '22160002', 'Gate_of_fort', '68643', '-62607', '-2814', '68633', '-62615', '-2814', '68683', '-62525', '-2814', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('110', '22160003', 'Gate_of_fort', '68703', '-62461', '-2814', '68663', '-62543', '-2814', '68713', '-62454', '-2814', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('110', '22160004', 'Gate_of_fort', '69991', '-60842', '-2814', '69988', '-60884', '-2814', '70074', '-60833', '-2814', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('110', '22160005', 'Gate_of_fort', '70137', '-60902', '-2814', '70055', '-60913', '-2814', '70141', '-60862', '-2814', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('110', '22160006', 'Gate_of_fort', '67849', '-61153', '-2776', '67847', '-61193', '-2776', '67930', '-61146', '-2776', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('110', '22160007', 'Gate_of_fort', '67995', '-61214', '-2776', '67913', '-61222', '-2776', '67998', '-61175', '-2776', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('110', '22160008', 'Gate_of_fort', '69532', '-61951', '-2814', '69527', '-61991', '-2814', '69612', '-61943', '-2814', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('110', '22160009', 'Gate_of_fort', '69678', '-62012', '-2814', '69596', '-62021', '-2814', '69682', '-61972', '-2814', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('110', '22160010', 'Gate_of_fort', '71440', '-61683', '-2786', '71437', '-61723', '-2786', '71521', '-61676', '-2786', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('110', '22160011', 'Gate_of_fort', '71586', '-61743', '-2786', '71505', '-61751', '-2786', '71590', '-61705', '-2786', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('110', '22160012', 'Gate_of_fort', '70698', '-59119', '-2834', '70583', '-59210', '-2834', '70816', '-59032', '-2834', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('110', '22160500', 'Swamp_flagpole', '69839', '-61422', '-1864', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('111', '23130001', 'Gate_of_fort', '107495', '-140524', '-3009', '107427', '-140617', '-3009', '107565', '-140435', '-3009', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('111', '23130002', 'Gate_of_fort', '110037', '-141474', '-2984', '110029', '-141477', '-2984', '110068', '-141395', '-2984', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('111', '23130003', 'Gate_of_fort', '110083', '-141323', '-2984', '110051', '-141403', '-2984', '110093', '-141319', '-2984', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('111', '23130004', 'Gate_of_fort', '108889', '-141126', '-2984', '108880', '-141130', '-2984', '108922', '-141046', '-2984', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('111', '23130005', 'Gate_of_fort', '108935', '-140975', '-2984', '108903', '-141054', '-2984', '108946', '-140970', '-2984', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('111', '23130006', 'Gate_of_fort', '111423', '-141714', '-3009', '111355', '-141807', '-3009', '111493', '-141625', '-3009', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('111', '23130007', 'Gate_of_fort', '110210', '-140001', '-2959', '110200', '-140006', '-2959', '110242', '-139921', '-2959', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('111', '23130008', 'Gate_of_fort', '110255', '-139851', '-2959', '110224', '-139930', '-2959', '110266', '-139846', '-2959', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('111', '23130500', 'Archaic_flagpole', '109478', '-141219', '-2034', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('112', '20220017', 'Gate_of_fort', '4738', '147966', '-2918', '4738', '147956', '-2918', '4818', '147977', '-2918', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('112', '20220018', 'Gate_of_fort', '4896', '147966', '-2918', '4817', '147954', '-2918', '4897', '147976', '-2918', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('112', '20220019', 'Gate_of_fort', '6381', '148169', '-2889', '6372', '148170', '-2889', '6389', '148248', '-2889', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('112', '20220020', 'Gate_of_fort', '6381', '148327', '-2889', '6373', '148247', '-2889', '6391', '148327', '-2889', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('112', '20220021', 'Gate_of_fort', '4016', '148732', '-2938', '4001', '148653', '-2938', '4031', '148811', '-2938', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('112', '20220022', 'Gate_of_fort', '6212', '149673', '-2917', '6204', '149673', '-2917', '6219', '149752', '-2917', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('112', '20220023', 'Gate_of_fort', '6212', '149831', '-2917', '6205', '149752', '-2917', '6219', '149831', '-2917', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('112', '20220024', 'Gate_of_fort', '5011', '149673', '-2917', '5004', '149673', '-2917', '5019', '149752', '-2917', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('112', '20220025', 'Gate_of_fort', '5011', '149831', '-2917', '5004', '149752', '-2917', '5019', '149831', '-2917', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('112', '20220026', 'Gate_of_fort', '7439', '150734', '-2938', '7421', '150655', '-2938', '7455', '150814', '-2938', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('112', '20220027', 'Gate_of_fort', '5135', '151876', '-2879', '5135', '151868', '-2879', '5215', '151885', '-2879', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('112', '20220028', 'Gate_of_fort', '5293', '151876', '-2879', '5213', '151868', '-2879', '5293', '151884', '-2879', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('112', '20220500', 'Floran_flagpole', '5606', '149756', '-1967', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('113', '18200001', 'Gate_of_fort', '-54247', '89585', '-2870', '-54367', '89535', '-2870', '-54139', '89640', '-2870', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('113', '18200002', 'Gate_of_fort', '-53312', '91823', '-2849', '-53311', '91814', '-2849', '-53226', '91831', '-2849', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('113', '18200003', 'Gate_of_fort', '-53154', '91823', '-2849', '-53240', '91814', '-2849', '-53143', '91833', '-2849', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('113', '18200004', 'Gate_of_fort', '-51513', '90259', '-2851', '-51521', '90251', '-2851', '-51504', '90348', '-2851', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('113', '18200005', 'Gate_of_fort', '-51513', '90417', '-2851', '-51521', '90328', '-2851', '-51503', '90428', '-2851', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('113', '18200006', 'Gate_of_fort', '-55123', '91775', '-2819', '-55131', '91763', '-2819', '-55114', '91863', '-2819', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('113', '18200007', 'Gate_of_fort', '-55123', '91933', '-2819', '-55131', '91845', '-2819', '-55114', '91944', '-2819', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('113', '18200008', 'Gate_of_fort', '-51146', '91961', '-2811', '-51220', '91945', '-2811', '-51130', '92035', '-2811', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('113', '18200009', 'Gate_of_fort', '-51259', '92074', '-2811', '-51274', '91999', '-2811', '-51187', '92091', '-2811', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('113', '18200010', 'Gate_of_fort', '-52256', '92985', '-2869', '-52376', '92935', '-2869', '-52148', '93040', '-2869', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('113', '18200011', 'Gate_of_fort', '-53318', '90621', '-2849', '-53317', '90612', '-2849', '-53232', '90629', '-2849', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('113', '18200012', 'Gate_of_fort', '-53160', '90621', '-2849', '-53246', '90612', '-2849', '-53149', '90631', '-2849', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('113', '18200500', 'CloudMountain_flagpole', '-53230', '91229', '-1899', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('114', '21220001', 'Gate_of_fort', '58984', '138135', '-1803', '58888', '138073', '-1803', '59082', '138199', '-1803', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('114', '21220002', 'Gate_of_fort', '60320', '140038', '-1782', '60317', '140018', '-1782', '60398', '140046', '-1782', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('114', '21220003', 'Gate_of_fort', '60474', '140007', '-1782', '60395', '139996', '-1782', '60481', '140031', '-1782', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('114', '21220004', 'Gate_of_fort', '61655', '138596', '-1754', '61649', '138567', '-1754', '61738', '138608', '-1754', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('114', '21220005', 'Gate_of_fort', '61809', '138565', '-1754', '61725', '138550', '-1754', '61816', '138593', '-1754', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('114', '21220006', 'Gate_of_fort', '60086', '138864', '-1782', '60081', '138833', '-1782', '60171', '138878', '-1782', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('114', '21220007', 'Gate_of_fort', '60240', '138833', '-1782', '60156', '138821', '-1782', '60246', '138862', '-1782', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('114', '21220008', 'Gate_of_fort', '61544', '140749', '-1803', '61449', '140681', '-1803', '61644', '140815', '-1803', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('114', '21220500', 'Tanor_flagpole', '60280', '139444', '-832', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('115', '20200001', 'Gate_of_fort', '12503', '93513', '-3475', '12383', '93463', '-3475', '12611', '93568', '-3475', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('115', '20200002', 'Gate_of_fort', '11458', '95587', '-3454', '11459', '95578', '-3454', '11544', '95595', '-3454', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('115', '20200003', 'Gate_of_fort', '11616', '95587', '-3454', '11530', '95578', '-3454', '11627', '95597', '-3454', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('115', '20200004', 'Gate_of_fort', '10128', '94936', '-3426', '10120', '94925', '-3426', '10133', '95024', '-3426', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('115', '20200005', 'Gate_of_fort', '10128', '95094', '-3426', '10120', '95006', '-3426', '10137', '95105', '-3426', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('115', '20200006', 'Gate_of_fort', '11458', '94386', '-3454', '11459', '94379', '-3454', '11543', '94393', '-3454', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('115', '20200007', 'Gate_of_fort', '11616', '94386', '-3454', '11530', '94377', '-3454', '11627', '94396', '-3454', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('115', '20200008', 'Gate_of_fort', '10493', '96565', '-3475', '10373', '96515', '-3475', '10601', '96619', '-3475', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('115', '20200500', 'Dragonspine_flagpole', '11546', '95030', '-2498', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('116', '22200001', 'Gate_of_fort', '78090', '89321', '-2934', '78027', '89241', '-2935', '78153', '89406', '-2933', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('116', '22200002', 'Gate_of_fort', '80522', '89326', '-2877', '80516', '89314', '-2877', '80599', '89373', '-2877', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('116', '22200003', 'Gate_of_fort', '80661', '89401', '-2877', '80585', '89351', '-2877', '80670', '89414', '-2877', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('116', '22200004', 'Gate_of_fort', '79730', '91401', '-2912', '79724', '91328', '-2912', '79773', '91405', '-2912', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('116', '22200005', 'Gate_of_fort', '79804', '91262', '-2912', '79761', '91259', '-2912', '79812', '91335', '-2912', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('116', '22200006', 'Gate_of_fort', '78673', '90835', '-2912', '78664', '90760', '-2912', '78720', '90840', '-2912', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('116', '22200007', 'Gate_of_fort', '78747', '90696', '-2912', '78701', '90692', '-2912', '78758', '90770', '-2912', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('116', '22200008', 'Gate_of_fort', '77815', '92082', '-2887', '77801', '92005', '-2887', '77865', '92090', '-2887', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('116', '22200009', 'Gate_of_fort', '77889', '91943', '-2887', '77838', '91936', '-2887', '77901', '92019', '-2887', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('116', '22200010', 'Gate_of_fort', '79034', '92957', '-2916', '79029', '92947', '-2916', '79110', '93006', '-2916', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('116', '22200011', 'Gate_of_fort', '79172', '93030', '-2916', '79096', '92984', '-2916', '79180', '93043', '-2916', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('116', '22200012', 'Gate_of_fort', '80167', '92700', '-2934', '80092', '92614', '-2935', '80234', '92787', '-2933', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('116', '22200500', 'Antharas_flagpole', '79252', '91052', '-1962', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('117', '23170001', 'Gate_of_fort', '112349', '-16973', '-1045', '113966', '-18062', '-1900', '114122', '-17839', '-1644', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('117', '23170002', 'Gate_of_fort', '109785', '-15919', '-995', '109778', '-15931', '-995', '109876', '-15905', '-995', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('117', '23170003', 'Gate_of_fort', '109943', '-15919', '-995', '109846', '-15931', '-995', '109952', '-15905', '-995', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('117', '23170004', 'Gate_of_fort', '111289', '-14550', '-1023', '111290', '-14559', '-1023', '111375', '-14542', '-1023', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('117', '23170005', 'Gate_of_fort', '111447', '-14550', '-1023', '111361', '-14559', '-1023', '111458', '-14540', '-1023', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('117', '23170006', 'Gate_of_fort', '109582', '-14432', '-1024', '109574', '-14444', '-1024', '109591', '-14344', '-1024', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('117', '23170007', 'Gate_of_fort', '109582', '-14274', '-1024', '109574', '-14362', '-1024', '109591', '-14263', '-1024', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('117', '23170008', 'Gate_of_fort', '111289', '-15750', '-1023', '111290', '-15759', '-1023', '111375', '-15742', '-1023', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('117', '23170009', 'Gate_of_fort', '111447', '-15750', '-1023', '111361', '-15759', '-1023', '111458', '-15740', '-1023', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('117', '23170010', 'Gate_of_fort', '113492', '-14829', '-987', '113484', '-14842', '-987', '113500', '-14734', '-987', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('117', '23170011', 'Gate_of_fort', '113492', '-14673', '-987', '113483', '-14761', '-987', '113502', '-14658', '-987', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('117', '23170012', 'Gate_of_fort', '110348', '-13549', '-1047', '110231', '-13599', '-1047', '110459', '-13494', '-1047', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('117', '23170500', 'Western_flagpole', '111371', '-15139', '-72', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('118', '23200001', 'Gate_of_fort', '124213', '93493', '-2191', '124093', '93443', '-2191', '124321', '93548', '-2191', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('118', '23200002', 'Gate_of_fort', '125167', '95734', '-2168', '125168', '95725', '-2168', '125253', '95742', '-2168', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('118', '23200003', 'Gate_of_fort', '125325', '95734', '-2168', '125239', '95725', '-2168', '125336', '95744', '-2168', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('118', '23200004', 'Gate_of_fort', '126966', '94171', '-2170', '126958', '94163', '-2170', '126975', '94260', '-2170', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('118', '23200005', 'Gate_of_fort', '126966', '94329', '-2170', '126958', '94240', '-2170', '126976', '94340', '-2170', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('118', '23200006', 'Gate_of_fort', '123356', '95687', '-2138', '123348', '95675', '-2138', '123365', '95775', '-2138', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('118', '23200007', 'Gate_of_fort', '123356', '95845', '-2138', '123348', '95757', '-2138', '123365', '95856', '-2138', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('118', '23200008', 'Gate_of_fort', '125167', '94534', '-2168', '125168', '94525', '-2168', '125253', '94542', '-2168', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('118', '23200009', 'Gate_of_fort', '125325', '94534', '-2168', '125239', '94525', '-2168', '125336', '94544', '-2168', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('118', '23200010', 'Gate_of_fort', '127221', '95985', '-2130', '127205', '95910', '-2130', '127293', '96002', '-2130', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('118', '23200011', 'Gate_of_fort', '127332', '95873', '-2130', '127258', '95858', '-2130', '127348', '95947', '-2130', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('118', '23200012', 'Gate_of_fort', '126193', '96897', '-2190', '126073', '96847', '-2190', '126301', '96952', '-2190', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('118', '23200500', 'Hunters_flagpole', '125248', '95143', '-1218', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('119', '22230001', 'Gate_of_fort', '73057', '184177', '-2630', '72951', '184083', '-2630', '73167', '184271', '-2630', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('119', '22230002', 'Gate_of_fort', '71908', '185170', '-2581', '71896', '185090', '-2581', '71965', '185181', '-2581', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('119', '22230003', 'Gate_of_fort', '71996', '185039', '-2581', '71939', '185030', '-2581', '72007', '185113', '-2581', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('119', '22230004', 'Gate_of_fort', '72714', '186440', '-2609', '72703', '186425', '-2609', '72792', '186500', '-2609', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('119', '22230005', 'Gate_of_fort', '72846', '186528', '-2609', '72767', '186470', '-2609', '72856', '186542', '-2609', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('119', '22230006', 'Gate_of_fort', '73381', '185443', '-2609', '73375', '185433', '-2609', '73456', '185498', '-2609', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('119', '22230007', 'Gate_of_fort', '73512', '185531', '-2609', '73439', '185476', '-2609', '73520', '185540', '-2609', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('119', '22230008', 'Gate_of_fort', '73037', '187834', '-2630', '72945', '187750', '-2630', '73130', '187924', '-2630', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('119', '22230500', 'Aaru_flagpole', '73113', '185988', '-1659', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('120', '23160001', 'Gate_of_fort', '99133', '-56363', '-693', '99087', '-56469', '-693', '99179', '-56267', '-693', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('120', '23160002', 'Gate_of_fort', '100609', '-56729', '-645', '100609', '-56739', '-645', '100698', '-56718', '-645', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('120', '23160003', 'Gate_of_fort', '100767', '-56727', '-645', '100680', '-56737', '-645', '100766', '-56718', '-645', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('120', '23160004', 'Gate_of_fort', '101315', '-55398', '-673', '101306', '-55405', '-673', '101327', '-55310', '-673', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('120', '23160005', 'Gate_of_fort', '101315', '-55240', '-673', '101306', '-55329', '-673', '101327', '-55233', '-673', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('120', '23160006', 'Gate_of_fort', '100114', '-55398', '-673', '100103', '-55401', '-673', '100125', '-55308', '-673', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('120', '23160007', 'Gate_of_fort', '100114', '-55241', '-673', '100102', '-55329', '-673', '100126', '-55235', '-673', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('120', '23160008', 'Gate_of_fort', '102190', '-54353', '-695', '102144', '-54459', '-695', '102236', '-54257', '-695', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('120', '23160500', 'Demon_flagpole', '100708', '-55315', '277', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
INSERT INTO `fort_staticobjects` VALUES ('121', '22150001', 'Gate_of_fort', '72491', '-96493', '-1478', '72376', '-96584', '-1478', '72609', '-96406', '-1478', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('121', '22150002', 'Gate_of_fort', '72324', '-94183', '-1456', '72320', '-94222', '-1456', '72405', '-94174', '-1456', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('121', '22150003', 'Gate_of_fort', '72470', '-94243', '-1456', '72389', '-94253', '-1456', '72477', '-94204', '-1456', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('121', '22150004', 'Gate_of_fort', '70847', '-94274', '-1428', '70837', '-94281', '-1428', '70888', '-94192', '-1428', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('121', '22150005', 'Gate_of_fort', '70907', '-94128', '-1428', '70866', '-94210', '-1428', '70918', '-94121', '-1428', '67884', '644', '518', 'true', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('121', '22150006', 'Gate_of_fort', '71864', '-95290', '-1456', '71859', '-95331', '-1456', '71945', '-95282', '-1456', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('121', '22150007', 'Gate_of_fort', '72010', '-95351', '-1456', '71928', '-95359', '-1456', '72016', '-95311', '-1456', '67884', '644', '518', 'true', 'true', '0');
INSERT INTO `fort_staticobjects` VALUES ('121', '22150008', 'Gate_of_fort', '71795', '-92905', '-1477', '71680', '-92996', '-1477', '71913', '-92818', '-1477', '67884', '644', '518', 'false', 'false', '0');
INSERT INTO `fort_staticobjects` VALUES ('121', '22150500', 'Monastic_flagpole', '72173', '-94761', '-506', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'false', 'false', '1');
