/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : cloud_user

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 22/03/2022 15:52:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for local_transaction_log
-- ----------------------------
DROP TABLE IF EXISTS `local_transaction_log`;
CREATE TABLE `local_transaction_log` (
  `tx_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `type` int NOT NULL COMMENT '1:try,2:confirm, 3:cancel',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`tx_no`,`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of local_transaction_log
-- ----------------------------
BEGIN;
INSERT INTO `local_transaction_log` VALUES ('-7142599170598866944', 1, '2022-03-22 15:48:44');
INSERT INTO `local_transaction_log` VALUES ('-7142606600221200384', 1, '2022-03-22 15:47:49');
INSERT INTO `local_transaction_log` VALUES ('-7142638885782278144', 1, '2022-03-22 15:43:48');
INSERT INTO `local_transaction_log` VALUES ('-7142662999503163392', 1, '2022-03-22 15:40:48');
INSERT INTO `local_transaction_log` VALUES ('-7142747328635617280', 1, '2022-03-22 15:30:20');
INSERT INTO `local_transaction_log` VALUES ('-7142762438733217792', 1, '2022-03-22 15:28:27');
COMMIT;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) DEFAULT NULL,
  `balance` int DEFAULT '0' COMMENT '账户余额',
  `bank_card` varchar(60) DEFAULT NULL COMMENT '银行卡号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_account
-- ----------------------------
BEGIN;
INSERT INTO `t_account` VALUES (1, '1', 590, '1');
COMMIT;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) DEFAULT NULL,
  `commodity_code` varchar(50) DEFAULT NULL COMMENT '商品编号',
  `count` int DEFAULT '0',
  `amount` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_storage
-- ----------------------------
DROP TABLE IF EXISTS `t_storage`;
CREATE TABLE `t_storage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `commodity_code` varchar(50) DEFAULT NULL COMMENT '商品编号',
  `commodity_name` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `count` int DEFAULT '0' COMMENT '商品数量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `commodity_code` (`commodity_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_storage
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `age` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
