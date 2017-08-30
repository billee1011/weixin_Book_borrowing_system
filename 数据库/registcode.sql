/*
Navicat MySQL Data Transfer

Source Server         : weixin
Source Server Version : 50718
Source Host           : 39.108.6.0:3306
Source Database       : weixin

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-06-18 10:45:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for registcode
-- ----------------------------
DROP TABLE IF EXISTS `registcode`;
CREATE TABLE `registcode` (
  `registcode` varchar(255) DEFAULT NULL,
  `employnum` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT '0',
  KEY `employnum` (`employnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of registcode
-- ----------------------------
INSERT INTO `registcode` VALUES ('123456', '0001', '1');
INSERT INTO `registcode` VALUES ('123457', '0002', '1');
INSERT INTO `registcode` VALUES ('012345', '0003', '0');
INSERT INTO `registcode` VALUES ('111111', '0004', '0');
