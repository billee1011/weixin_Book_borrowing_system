/*
Navicat MySQL Data Transfer

Source Server         : weixin
Source Server Version : 50718
Source Host           : 39.108.6.0:3306
Source Database       : weixin

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-06-18 10:45:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for historyforborrowbook
-- ----------------------------
DROP TABLE IF EXISTS `historyforborrowbook`;
CREATE TABLE `historyforborrowbook` (
  `userid` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `manager` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of historyforborrowbook
-- ----------------------------
INSERT INTO `historyforborrowbook` VALUES ('17865922910', '1497528443055', '0001', '97875113498350');
INSERT INTO `historyforborrowbook` VALUES ('17865922910', '1497535523574', '0001', '97875083711770');
INSERT INTO `historyforborrowbook` VALUES ('17865922910', '1497535705624', '0001', '97870304858090');
INSERT INTO `historyforborrowbook` VALUES ('17865922910', '1497535802783', '0001', '97875404634960');
INSERT INTO `historyforborrowbook` VALUES ('17865922910', '1497535889394', '0001', '97875113498350');
INSERT INTO `historyforborrowbook` VALUES ('17865922910', '1497536030959', '0001', '97873022137270');
INSERT INTO `historyforborrowbook` VALUES ('17865922910', '1497536364778', '0001', '97875113498351');
INSERT INTO `historyforborrowbook` VALUES ('17865922910', '1497536637361', '0001', '97875130051110');
INSERT INTO `historyforborrowbook` VALUES ('17865922910', '1497537084867', '0001', '97871114399500');
INSERT INTO `historyforborrowbook` VALUES ('17865923521', '1497613528778', '0001', '97871221880140');
INSERT INTO `historyforborrowbook` VALUES ('17865923521', '1497613699278', '0001', '97871221872910');
INSERT INTO `historyforborrowbook` VALUES ('17865923521', '1497613879059', '0001', '97871221872910');
INSERT INTO `historyforborrowbook` VALUES ('17865923521', '1497613906825', '0001', '97871221872910');
INSERT INTO `historyforborrowbook` VALUES ('17865923521', '1497613982278', '0001', '97871222384500');
INSERT INTO `historyforborrowbook` VALUES ('17865923521', '1497614664575', '0001', '97875113498352');
INSERT INTO `historyforborrowbook` VALUES ('17865923521', '1497614963841', '0001', '97875113498353');
INSERT INTO `historyforborrowbook` VALUES ('17865923521', '1497676774263', '0001', '97875113498354');
INSERT INTO `historyforborrowbook` VALUES ('17865922910', '1497681129278', '0001', '97871222384500');
INSERT INTO `historyforborrowbook` VALUES ('17865923521', '1497695205434', '0001', '97875113498353');
