/*
 Navicat Premium Data Transfer

 Source Server         : DavisJy_MYSQL
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : localhost:3306
 Source Schema         : myexampledemo

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 19/01/2020 10:24:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `Sno` int(11) NOT NULL,
  `Sname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Ssex` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Sbirthday` date NULL DEFAULT NULL,
  `Class` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (108, '曾华', '男', '1977-09-01', '95033');
INSERT INTO `student` VALUES (105, '匡明', '男', '1975-10-02', '95031');
INSERT INTO `student` VALUES (107, '王丽', '女', '1976-01-23', '95033');
INSERT INTO `student` VALUES (101, '李军', '男', '1976-02-20', '95033');
INSERT INTO `student` VALUES (109, '王芳', '女', '1975-02-10', '95031');
INSERT INTO `student` VALUES (103, '陆君', '男', '1974-06-03', '95031');

SET FOREIGN_KEY_CHECKS = 1;
