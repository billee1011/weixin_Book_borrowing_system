/*
Navicat MySQL Data Transfer

Source Server         : weixin
Source Server Version : 50718
Source Host           : 39.108.6.0:3306
Source Database       : weixin

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-06-18 10:45:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `phonenumber` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `weixin` varchar(255) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `setting1` varchar(255) DEFAULT '0',
  `setting2` varchar(255) DEFAULT '0',
  `setting3` varchar(255) DEFAULT 'week'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('13969341947', '李岳霖', '111', '', '37108119951212401x', '', '0', '0', 'week');
INSERT INTO `user` VALUES ('17865923520', '唐心想', '111111', '', '370683199505260616', '', '0', '0', 'week');
INSERT INTO `user` VALUES ('17865923521', '张丰业', '111111', 'oEyIL0QNGWdINl6uMcX3_dITr88Q', '370683199505260616', '1050677051@qq.com', '1', '1', 'week');
INSERT INTO `user` VALUES ('17865923528', '张天翼', '111111', '', '370683199505260616', '', '0', '0', 'week');
INSERT INTO `user` VALUES ('17865923568', '张锋发', '111111', '', '370683199505260616', '', '0', '0', 'week');
INSERT INTO `user` VALUES ('17865923625', '周恬怡', '111111', '', '370683199505260616', '', '0', '0', 'week');
INSERT INTO `user` VALUES ('17865922910', '纪淑雅', '123456', 'oKlQM0TaVyBk5NUhF3fV4JaOpMss', '371523199502016289', '528226249@qq.com', '1', '1', 'month');
INSERT INTO `user` VALUES ('-1', null, null, null, null, null, '0', '0', 'week');
INSERT INTO `user` VALUES ('-1', null, null, null, null, null, '0', '0', 'week');
INSERT INTO `user` VALUES ('-1', null, null, null, null, null, '0', '0', 'week');
INSERT INTO `user` VALUES ('-1', null, null, null, null, null, '0', '0', 'week');
INSERT INTO `user` VALUES ('-1', null, null, null, null, null, '0', '0', 'week');
INSERT INTO `user` VALUES ('-1', null, null, null, null, null, '0', '0', 'week');
