/*
 Navicat Premium Data Transfer

 Source Server         : Apollo MySQL
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:13306
 Source Schema         : mybatis_plus_demo

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 19/07/2019 11:38:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mbp_video
-- ----------------------------
DROP TABLE IF EXISTS `mbp_video`;
CREATE TABLE `mbp_video` (
                             `id` int(11) NOT NULL,
                             `name` varchar(64) NOT NULL,
                             `description` varchar(2048) DEFAULT NULL,
                             `deleted` tinyint(4) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mbp_video
-- ----------------------------
BEGIN;
INSERT INTO `mbp_video` VALUES (1, 'avatar', 'nice movie', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
