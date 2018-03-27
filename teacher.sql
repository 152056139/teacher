/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50638
 Source Host           : localhost:3306
 Source Schema         : teacher

 Target Server Type    : MySQL
 Target Server Version : 50638
 File Encoding         : 65001

 Date: 27/03/2018 20:17:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(0) DEFAULT NULL,
  `course_date` date DEFAULT NULL,
  `course_address` varchar(0) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `course_score` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course_student_score
-- ----------------------------
DROP TABLE IF EXISTS `course_student_score`;
CREATE TABLE `course_student_score` (
  `course_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `note_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `note` text,
  `note_time` time DEFAULT NULL,
  `note_father_node` text COMMENT '是哪个笔记的评论',
  PRIMARY KEY (`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `user_password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '杨旭', 'admin');
INSERT INTO `user` VALUES (2, '郭浩卓', 'root');
INSERT INTO `user` VALUES (3, '王金', 'root');
COMMIT;

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work` (
  `word_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `work_title` varchar(0) DEFAULT NULL,
  `work_description` varchar(0) DEFAULT NULL,
  PRIMARY KEY (`word_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for work_score
-- ----------------------------
DROP TABLE IF EXISTS `work_score`;
CREATE TABLE `work_score` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `work_id` int(11) NOT NULL DEFAULT '0',
  `work_content` text,
  `work_score` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`work_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
