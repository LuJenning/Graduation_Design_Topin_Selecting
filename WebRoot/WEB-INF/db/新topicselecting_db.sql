/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : topicselecting_db

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 18:59:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `class_info`
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `class_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `institute_id` int(10) DEFAULT NULL COMMENT '专业id，专业id，专业id,查找学院信息',
  `class_name` varchar(50) DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES ('1', '9', '哲学2班');
INSERT INTO `class_info` VALUES ('2', '9', '哲学1班');
INSERT INTO `class_info` VALUES ('3', '9', '哲学4班');
INSERT INTO `class_info` VALUES ('4', '7', '数媒1班');
INSERT INTO `class_info` VALUES ('5', '7', '数媒6班');
INSERT INTO `class_info` VALUES ('6', '5', '软件9班');
INSERT INTO `class_info` VALUES ('7', '9', '哲学3班');
INSERT INTO `class_info` VALUES ('8', '7', '数媒2班');

-- ----------------------------
-- Table structure for `ideal_info`
-- ----------------------------
DROP TABLE IF EXISTS `ideal_info`;
CREATE TABLE `ideal_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ideal_id` varchar(255) DEFAULT NULL COMMENT '志愿id',
  `ideal_type` varchar(255) DEFAULT NULL COMMENT '志愿类型',
  `topic_id` varchar(255) DEFAULT NULL COMMENT '关联课题编号',
  `user_id` varchar(255) DEFAULT NULL COMMENT '关联用户id',
  `select_date` datetime DEFAULT NULL COMMENT '填报志愿时间',
  `institute_id` int(11) DEFAULT NULL COMMENT '学院id',
  `is_accept` int(11) DEFAULT NULL COMMENT '录取状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ideal_info
-- ----------------------------

-- ----------------------------
-- Table structure for `institute_info`
-- ----------------------------
DROP TABLE IF EXISTS `institute_info`;
CREATE TABLE `institute_info` (
  `institute_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '学院，专业编号',
  `institute_name` varchar(50) DEFAULT NULL COMMENT '学院，专业名称',
  `institute_pid` int(10) DEFAULT NULL COMMENT '"父id、如果institute_id是专业则该字段为该专业所在院系的id',
  PRIMARY KEY (`institute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of institute_info
-- ----------------------------
INSERT INTO `institute_info` VALUES ('1', '信电学院', '0');
INSERT INTO `institute_info` VALUES ('2', '文法学院', '0');
INSERT INTO `institute_info` VALUES ('3', '师范学院', '0');
INSERT INTO `institute_info` VALUES ('4', '机化学院', '0');
INSERT INTO `institute_info` VALUES ('5', '软件工程', '1');
INSERT INTO `institute_info` VALUES ('6', '法学专业', '2');
INSERT INTO `institute_info` VALUES ('7', '数媒班', '1');
INSERT INTO `institute_info` VALUES ('8', '马克思学院', '0');
INSERT INTO `institute_info` VALUES ('9', '哲学专业', '8');
INSERT INTO `institute_info` VALUES ('10', '经管学院', '0');
INSERT INTO `institute_info` VALUES ('11', '贸易专业', '10');

-- ----------------------------
-- Table structure for `login_info`
-- ----------------------------
DROP TABLE IF EXISTS `login_info`;
CREATE TABLE `login_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `login_date` datetime DEFAULT NULL COMMENT '登录时间',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型：1管理员，2教师，3学生',
  `week_day` varchar(255) DEFAULT NULL COMMENT '登录的星期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_info
-- ----------------------------
INSERT INTO `login_info` VALUES ('1', '11', '2018-05-30 22:47:29', '1', '星期一');
INSERT INTO `login_info` VALUES ('2', '11', '2018-05-30 23:57:48', '1', '星期三');
INSERT INTO `login_info` VALUES ('3', '11', '2018-05-31 14:59:17', '1', '星期四');
INSERT INTO `login_info` VALUES ('4', '11', '2018-05-31 15:04:22', '1', '星期四');
INSERT INTO `login_info` VALUES ('5', '11', '2018-05-31 15:10:29', '1', '星期四');
INSERT INTO `login_info` VALUES ('6', '2016002', '2018-05-31 15:12:11', '3', '星期四');
INSERT INTO `login_info` VALUES ('7', '11', '2018-05-31 15:12:35', '1', '星期四');
INSERT INTO `login_info` VALUES ('8', '201600203', '2018-05-31 15:14:06', '2', '星期四');
INSERT INTO `login_info` VALUES ('9', '11', '2018-05-31 15:14:25', '1', '星期四');
INSERT INTO `login_info` VALUES ('10', '11', '2018-05-31 15:18:54', '1', '星期四');
INSERT INTO `login_info` VALUES ('11', '11', '2018-05-31 15:22:49', '1', '星期四');
INSERT INTO `login_info` VALUES ('12', '11', '2018-06-02 00:24:32', '1', '星期六');
INSERT INTO `login_info` VALUES ('13', '201600203', '2018-06-02 17:58:46', '2', '星期六');
INSERT INTO `login_info` VALUES ('14', '2018', '2018-06-02 18:02:01', '3', '星期六');
INSERT INTO `login_info` VALUES ('15', '11', '2018-06-02 19:31:34', '1', '星期六');
INSERT INTO `login_info` VALUES ('16', '2016002', '2018-06-02 20:24:30', '3', '星期六');
INSERT INTO `login_info` VALUES ('17', '201600203', '2018-06-02 20:29:41', '2', '星期六');
INSERT INTO `login_info` VALUES ('18', '2018', '2018-06-02 20:30:08', '3', '星期六');
INSERT INTO `login_info` VALUES ('19', '2018', '2018-06-02 20:31:31', '3', '星期六');
INSERT INTO `login_info` VALUES ('20', '2018', '2018-06-02 20:34:34', '3', '星期六');
INSERT INTO `login_info` VALUES ('21', '2018', '2018-06-02 20:38:27', '3', '星期六');
INSERT INTO `login_info` VALUES ('22', '2018', '2018-06-02 20:43:22', '3', '星期六');
INSERT INTO `login_info` VALUES ('23', '2018', '2018-06-02 20:52:26', '3', '星期六');
INSERT INTO `login_info` VALUES ('24', '2016002', '2018-06-02 20:53:09', '3', '星期六');
INSERT INTO `login_info` VALUES ('25', '201600203', '2018-06-02 20:53:28', '2', '星期六');
INSERT INTO `login_info` VALUES ('26', '2018', '2018-06-02 20:56:39', '3', '星期六');
INSERT INTO `login_info` VALUES ('27', '2018', '2018-06-02 20:59:25', '3', '星期六');
INSERT INTO `login_info` VALUES ('28', '2018', '2018-06-06 13:47:02', '3', '星期三');
INSERT INTO `login_info` VALUES ('29', '201600203', '2018-06-06 13:47:38', '2', '星期三');
INSERT INTO `login_info` VALUES ('30', '201600203', '2018-06-06 14:00:31', '2', '星期三');
INSERT INTO `login_info` VALUES ('31', '11', '2018-06-06 17:58:04', '1', '星期三');
INSERT INTO `login_info` VALUES ('32', '201600203', '2018-06-06 21:54:59', '2', '星期三');
INSERT INTO `login_info` VALUES ('33', '201600203', '2018-06-07 14:33:25', '2', '星期四');
INSERT INTO `login_info` VALUES ('34', '201600203', '2018-06-07 15:41:45', '2', '星期四');
INSERT INTO `login_info` VALUES ('35', '201600203', '2018-06-07 15:42:05', '2', '星期四');
INSERT INTO `login_info` VALUES ('36', '201600203', '2018-06-07 20:18:39', '2', '星期四');
INSERT INTO `login_info` VALUES ('37', '201600203', '2018-06-07 20:28:23', '2', '星期四');
INSERT INTO `login_info` VALUES ('38', '201600203', '2018-06-07 20:32:06', '2', '星期四');
INSERT INTO `login_info` VALUES ('39', '201600203', '2018-06-07 22:48:04', '2', '星期四');
INSERT INTO `login_info` VALUES ('40', '201600203', '2018-06-07 23:09:52', '2', '星期四');
INSERT INTO `login_info` VALUES ('41', '201600203', '2018-06-07 23:19:50', '2', '星期四');
INSERT INTO `login_info` VALUES ('42', '201600203', '2018-06-08 12:50:31', '2', '星期五');
INSERT INTO `login_info` VALUES ('43', '201600203', '2018-06-08 13:05:51', '2', '星期五');
INSERT INTO `login_info` VALUES ('44', '201600203', '2018-06-08 13:08:32', '2', '星期五');
INSERT INTO `login_info` VALUES ('45', '201600203', '2018-06-08 13:12:20', '2', '星期五');
INSERT INTO `login_info` VALUES ('46', '201600203', '2018-06-08 13:16:05', '2', '星期五');
INSERT INTO `login_info` VALUES ('47', '201600203', '2018-06-08 13:18:28', '2', '星期五');
INSERT INTO `login_info` VALUES ('48', '201600203', '2018-06-08 13:19:38', '2', '星期五');
INSERT INTO `login_info` VALUES ('49', '201600203', '2018-06-08 13:27:38', '2', '星期五');
INSERT INTO `login_info` VALUES ('50', '201600203', '2018-06-08 13:44:18', '2', '星期五');
INSERT INTO `login_info` VALUES ('51', '201600203', '2018-06-08 16:57:56', '2', '星期五');
INSERT INTO `login_info` VALUES ('52', '201600203', '2018-06-08 17:56:29', '2', '星期五');
INSERT INTO `login_info` VALUES ('53', '201600203', '2018-06-08 18:09:44', '2', '星期五');
INSERT INTO `login_info` VALUES ('54', '201600203', '2018-06-08 18:21:52', '2', '星期五');
INSERT INTO `login_info` VALUES ('55', '201600203', '2018-06-08 18:43:28', '2', '星期五');
INSERT INTO `login_info` VALUES ('56', '201600203', '2018-06-08 18:49:42', '2', '星期五');
INSERT INTO `login_info` VALUES ('57', '201600203', '2018-06-08 18:58:47', '2', '星期五');

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
  `user_type` int(4) DEFAULT NULL COMMENT '用户类型（1-管理员，2-教师，3-学生）',
  `user_phone` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `institute_id` int(4) DEFAULT NULL COMMENT '所在学院ID',
  `class_id` int(10) DEFAULT NULL COMMENT '班级id',
  `user_birthday` datetime DEFAULT NULL COMMENT '用户出生日期',
  `teacher_title` varchar(255) DEFAULT NULL COMMENT '教师职称',
  `user_native` varchar(255) DEFAULT NULL COMMENT '用户籍贯',
  `student_score` double DEFAULT NULL COMMENT '学生成绩',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '11', '秦松', '11', '1', '1', '12', '5', '6', null, null, null, null);
INSERT INTO `sys_user` VALUES ('5', 'admin', '11', 'admin', '0', '1', '11111111111', '5', '6', null, null, null, null);
INSERT INTO `sys_user` VALUES ('9', '201600203', '123', '123', '1', '2', '10010', '5', '6', '1997-09-08 22:20:20', null, '广西梧州', '90');
INSERT INTO `sys_user` VALUES ('10', '232626262626232', '23232', '123', '1', '3', '10010', '7', '4', '1997-09-08 22:20:20', null, '广西梧州', '90');
INSERT INTO `sys_user` VALUES ('11', '23262626262623225', '222', '123234', '0', '3', '10010', '9', '2', '1997-09-08 22:20:20', null, '广西梧州', '99');
INSERT INTO `sys_user` VALUES ('12', '2016002', 'abc', '123', '1', '3', '10010', '9', '7', '2018-05-27 00:00:00', null, '广西梧州', '90');
INSERT INTO `sys_user` VALUES ('13', '201600205', '258', '123', '1', '3', '10010', '7', '5', '2018-05-27 00:00:00', null, '广西梧州', '90');
INSERT INTO `sys_user` VALUES ('14', '201600604', '哪啊', '123', '1', '3', '10010', '9', '2', '2018-05-27 00:00:00', null, '广西梧州', '99');
INSERT INTO `sys_user` VALUES ('15', '2018', '2018', '123', '1', '3', '10086', '5', '6', '2018-06-02 00:00:00', null, '广西梧州', '99');

-- ----------------------------
-- Table structure for `topic_info`
-- ----------------------------
DROP TABLE IF EXISTS `topic_info`;
CREATE TABLE `topic_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` varchar(255) DEFAULT NULL COMMENT '课题id',
  `topic_content` varchar(255) DEFAULT NULL COMMENT '课题内容',
  `topic_title` varchar(255) DEFAULT NULL COMMENT '课题标题',
  `topic_request` varchar(255) DEFAULT NULL COMMENT '课题要求',
  `topic_surplus` int(11) DEFAULT NULL COMMENT '课题余量',
  `issue_date` datetime DEFAULT NULL COMMENT '课题发布时间',
  `user_id` int(11) DEFAULT NULL COMMENT '关联教师的id',
  `institute_id` int(11) DEFAULT NULL COMMENT '专业id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic_info
-- ----------------------------
INSERT INTO `topic_info` VALUES ('1', '1000', '大使馆部分', '奥巴巴', '阿女噶', '100', '2018-06-07 20:45:23', '201600203', '5');
INSERT INTO `topic_info` VALUES ('2', '1001', '盎司的风格啊2232', '爱迪生噶的树干上', '大概啊', '100', '2018-06-07 20:20:33', '201600203', '6');
INSERT INTO `topic_info` VALUES ('3', '1002', '哦您奇偶UI比U币牛', '吧女比不123313', '哦no你哦', '100', '2018-06-07 21:59:54', '201600203', '7');
INSERT INTO `topic_info` VALUES ('6', '1003', '肉炒TVB努努一', '测试o', '出人才TV他', '1024', '2018-06-08 18:59:12', '201600203', '9');
