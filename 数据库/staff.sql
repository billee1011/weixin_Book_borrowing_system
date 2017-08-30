/*
Navicat MySQL Data Transfer

Source Server         : weixin
Source Server Version : 50718
Source Host           : 39.108.6.0:3306
Source Database       : weixin

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-06-18 10:45:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `employnum` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `loginid` varchar(255) DEFAULT NULL,
  KEY `employnum` (`employnum`),
  CONSTRAINT `employnum` FOREIGN KEY (`employnum`) REFERENCES `registcode` (`employnum`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('0001', '张丰业', '17865923521', '123456', '178659235211497743015419');
INSERT INTO `staff` VALUES ('0002', 'zhang', '17865923520', '111111', null);
