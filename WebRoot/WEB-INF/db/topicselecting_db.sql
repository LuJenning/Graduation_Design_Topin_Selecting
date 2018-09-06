/*
Navicat MySQL Data Transfer

Source Server         : localhost_3307
Source Server Version : 50720
Source Host           : localhost:3307
Source Database       : topicselecting_db

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-05-20 17:32:49
*/
create database topicselecting_db;
use topicselecting_db;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `institute_info`
-- ----------------------------
DROP TABLE IF EXISTS `institute_info`;
CREATE TABLE `institute_info` (
  `institute_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '学院，专业编号',
  `institute_name` varchar(50) DEFAULT NULL COMMENT '学院，专业名称',
  `institute_pid` int(10) DEFAULT NULL COMMENT '"父id、如果institute_id是专业则该字段为该专业所在院系的id',
  PRIMARY KEY (`institute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of institute_info
-- ----------------------------
INSERT INTO `institute_info` VALUES ('1', '信电学院', '0');
INSERT INTO `institute_info` VALUES ('2', '文法学院', '0');
INSERT INTO `institute_info` VALUES ('3', '师范学院', '0');
INSERT INTO `institute_info` VALUES ('4', '机化学院', '0');
INSERT INTO `institute_info` VALUES ('5', '软件工程', '1');
INSERT INTO `institute_info` VALUES ('6', '法学专业', '2');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL COMMENT '登录账号（学生学号，教师工号）',
  `user_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `user_password` varchar(250) NOT NULL COMMENT '登录密码',
  `user_sex` int(4) DEFAULT NULL COMMENT '性别',
  `user_age` int(4) DEFAULT NULL COMMENT '年龄',
  `user_type` int(4) DEFAULT NULL COMMENT '用户类型（1-管理员，2-教师，3-学生）',
  `user_phone` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `institute_id` int(4) DEFAULT NULL COMMENT '所在学院ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '11', null, '11', null, null, '1', null, null);
