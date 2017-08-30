/*
Navicat MySQL Data Transfer

Source Server         : weixin
Source Server Version : 50718
Source Host           : 39.108.6.0:3306
Source Database       : weixin

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-06-18 10:45:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for historyforreturnbook
-- ----------------------------
DROP TABLE IF EXISTS `historyforreturnbook`;
CREATE TABLE `historyforreturnbook` (
  `userid` varchar(255) DEFAULT NULL,
  `ISBN` varchar(255) DEFAULT NULL,
  `manager` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of historyforreturnbook
-- ----------------------------
INSERT INTO `historyforreturnbook` VALUES ('17865923521', '123456789410', '0001', '1497528443055');
INSERT INTO `historyforreturnbook` VALUES ('17865923521', '97875113498353', '0001', '1497676882763');
INSERT INTO `historyforreturnbook` VALUES ('17865923521', '97871221872910', '0001', '1497676900216');
