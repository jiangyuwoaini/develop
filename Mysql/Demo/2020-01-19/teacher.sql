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

 Date: 19/01/2020 10:24:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `Tno` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Tsex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Tbirthday` date NULL DEFAULT NULL,
  `Prof` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Depart` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('804', '李诚', '男', '1958-12-02', '副教授', '计算机系');
INSERT INTO `teacher` VALUES ('856', '张旭', '男', '1969-03-12', '讲师', '电子工程系');
INSERT INTO `teacher` VALUES ('825', '王萍', '女', '1972-05-05', '助教', '计算机系');
INSERT INTO `teacher` VALUES ('831', '刘冰', '女', '1977-08-14', '助教', '电子工程系');

SET FOREIGN_KEY_CHECKS = 1;
