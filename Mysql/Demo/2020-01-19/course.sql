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

 Date: 19/01/2020 10:24:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `Cno` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Cname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Tno` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('3-105', '计算机导论', '825');
INSERT INTO `course` VALUES ('3-245', '操作系统', '804');
INSERT INTO `course` VALUES ('6-166', '数学电路', '856');
INSERT INTO `course` VALUES ('9-888', '高等数学', '831');

SET FOREIGN_KEY_CHECKS = 1;
