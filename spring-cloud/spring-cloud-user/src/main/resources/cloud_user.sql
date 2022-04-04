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

 Date: 23/03/2022 16:59:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS `cloud_user` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
use cloud_user;
-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `balance` decimal(10,0) NOT NULL COMMENT '用户余额',
  `freeze_amount` decimal(10,0) NOT NULL COMMENT '冻结金额，扣款暂存余额',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` VALUES (1, '10000', 10000000, 0, '2017-09-18 14:54:22', NULL);
COMMIT;

-- ----------------------------
-- Table structure for local_transaction_log
-- ----------------------------
DROP TABLE IF EXISTS `local_transaction_log`;
CREATE TABLE `local_transaction_log` (
  `tx_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `type` int NOT NULL COMMENT '1:try,2:confirm, 3:cancel',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`tx_no`,`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of local_transaction_log
-- ----------------------------
BEGIN;
INSERT INTO `local_transaction_log` VALUES ('-7130450277925957632', 1, '2022-03-23 16:57:20');
INSERT INTO `local_transaction_log` VALUES ('-7130459827114651648', 1, '2022-03-23 16:56:09');
INSERT INTO `local_transaction_log` VALUES ('-7130462591731412992', 1, '2022-03-23 16:55:48');
INSERT INTO `local_transaction_log` VALUES ('-7130465471521857536', 1, '2022-03-23 16:55:27');
INSERT INTO `local_transaction_log` VALUES ('-7130471138194333696', 1, '2022-03-23 16:54:45');
INSERT INTO `local_transaction_log` VALUES ('-7130476522472710144', 1, '2022-03-23 16:54:13');
INSERT INTO `local_transaction_log` VALUES ('-7130478209857986560', 1, '2022-03-23 16:54:13');
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
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `balance` int DEFAULT '0' COMMENT '账户余额',
  `bank_card` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '银行卡号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of t_account
-- ----------------------------
BEGIN;
INSERT INTO `t_account` VALUES (1, '1', 1000, '1');
COMMIT;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `commodity_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品编号',
  `count` int DEFAULT '0',
  `amount` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
INSERT INTO `t_order` VALUES (1, '1', '1', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_storage
-- ----------------------------
DROP TABLE IF EXISTS `t_storage`;
CREATE TABLE `t_storage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `commodity_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品编号',
  `commodity_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品名称',
  `count` int DEFAULT '0' COMMENT '商品数量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `commodity_code` (`commodity_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

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
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `age` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, '1', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
