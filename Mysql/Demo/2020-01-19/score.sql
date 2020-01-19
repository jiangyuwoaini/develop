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

 Date: 19/01/2020 10:24:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `Sno` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Cno` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Degree` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `index_Cno`(`Cno`) USING BTREE,
  CONSTRAINT `index_Cno` FOREIGN KEY (`Cno`) REFERENCES `course` (`Cno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('103', '3-245', '86');
INSERT INTO `score` VALUES ('105', '3-245', '75');
INSERT INTO `score` VALUES ('109', '3-245', '68');
INSERT INTO `score` VALUES ('103', '3-105', '92');
INSERT INTO `score` VALUES ('105', '3-105', '88');
INSERT INTO `score` VALUES ('109', '3-105', '76');
INSERT INTO `score` VALUES ('101', '3-105', '64');
INSERT INTO `score` VALUES ('107', '3-105', '91');
INSERT INTO `score` VALUES ('108', '3-105', '78');
INSERT INTO `score` VALUES ('101', '6-166', '85');
INSERT INTO `score` VALUES ('107', '6-166', '79');
INSERT INTO `score` VALUES ('108', '6-166', '81');

SET FOREIGN_KEY_CHECKS = 1;
