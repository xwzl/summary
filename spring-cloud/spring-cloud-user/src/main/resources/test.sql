/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 123.57.107.76:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 26/01/2022 11:42:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for FILE_LISTENER
-- ----------------------------
DROP TABLE IF EXISTS `FILE_LISTENER`;
CREATE TABLE `FILE_LISTENER`  (
  `AREA_CODE` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '全国区划行政编码',
  `STATION` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '台站号',
  `NOW_FILE_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '当前处理文件',
  `FILE_INDEX` bigint NOT NULL COMMENT '当前文件游标',
  `TYPE` tinyint NOT NULL COMMENT '0:雨滴谱 1:微波辐射计',
  `CREATED_TIME` datetime(3) NOT NULL COMMENT '创建时间',
  `UPDATED_TIME` datetime(3) NOT NULL COMMENT '修改时间',
  `DELETE_LOGO` tinyint NOT NULL COMMENT '删除标志 1:存在 0:不存在'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of FILE_LISTENER
-- ----------------------------

-- ----------------------------
-- Table structure for account_info
-- ----------------------------
DROP TABLE IF EXISTS `account_info`;
CREATE TABLE `account_info`  (
  `id` int NOT NULL,
  `account_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `blance` double(20, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_info
-- ----------------------------
INSERT INTO `account_info` VALUES (1, '123456789', 340.00);

-- ----------------------------
-- Table structure for actor
-- ----------------------------
DROP TABLE IF EXISTS `actor`;
CREATE TABLE `actor`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of actor
-- ----------------------------
INSERT INTO `actor` VALUES (1, 'a', '2017-11-12 15:27:18');
INSERT INTO `actor` VALUES (2, 'b', '2017-12-12 15:27:18');
INSERT INTO `actor` VALUES (3, 'c1', '2021-04-01 18:23:27');
INSERT INTO `actor` VALUES (4, 'd', '2021-04-01 10:23:27');
INSERT INTO `actor` VALUES (5, 'e', '2021-04-02 10:24:00');
INSERT INTO `actor` VALUES (6, 'f', '2021-04-03 10:24:00');
INSERT INTO `actor` VALUES (7, 'h', '2021-04-04 10:24:00');
INSERT INTO `actor` VALUES (8, 'g', '2021-04-05 10:24:00');
INSERT INTO `actor` VALUES (9, 'i', '2021-04-06 10:24:00');
INSERT INTO `actor` VALUES (10, 'j', '2021-04-07 10:24:00');

-- ----------------------------
-- Table structure for actor_copy1
-- ----------------------------
DROP TABLE IF EXISTS `actor_copy1`;
CREATE TABLE `actor_copy1`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of actor_copy1
-- ----------------------------
INSERT INTO `actor_copy1` VALUES (3, 'c1', '2021-04-02 07:23:27');
INSERT INTO `actor_copy1` VALUES (5, 'e', '2021-04-02 23:24:00');
INSERT INTO `actor_copy1` VALUES (6, 'f', '2021-04-03 23:24:00');
INSERT INTO `actor_copy1` VALUES (7, 'h', '2021-04-04 23:24:00');
INSERT INTO `actor_copy1` VALUES (8, 'g', '2021-04-05 23:24:00');
INSERT INTO `actor_copy1` VALUES (9, 'i', '2021-04-06 23:24:00');
INSERT INTO `actor_copy1` VALUES (10, 'j', '2021-04-07 23:24:00');

-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int NOT NULL,
  `position` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hire_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_name_age_position`(`name`, `age`, `position`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employees
-- ----------------------------
INSERT INTO `employees` VALUES (1, 'LiLei', 22, 'manager', '2020-07-19 21:50:28');
INSERT INTO `employees` VALUES (2, 'HanMeiMei', 23, 'dev', '2020-07-19 21:50:28');
INSERT INTO `employees` VALUES (3, 'Lucy', 23, 'dev', '2020-07-19 21:51:35');

-- ----------------------------
-- Table structure for film
-- ----------------------------
DROP TABLE IF EXISTS `film`;
CREATE TABLE `film`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of film
-- ----------------------------
INSERT INTO `film` VALUES (3, 'film0');
INSERT INTO `film` VALUES (1, 'film1');
INSERT INTO `film` VALUES (2, 'film2');

-- ----------------------------
-- Table structure for film_actor
-- ----------------------------
DROP TABLE IF EXISTS `film_actor`;
CREATE TABLE `film_actor`  (
  `id` int NOT NULL,
  `film_id` int NOT NULL,
  `actor_id` int NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_film_actor_id`(`film_id`, `actor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of film_actor
-- ----------------------------
INSERT INTO `film_actor` VALUES (1, 1, 1, NULL);
INSERT INTO `film_actor` VALUES (2, 1, 2, NULL);
INSERT INTO `film_actor` VALUES (3, 2, 1, NULL);

-- ----------------------------
-- Table structure for insert_update_test
-- ----------------------------
DROP TABLE IF EXISTS `insert_update_test`;
CREATE TABLE `insert_update_test`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `c_id` int NULL DEFAULT NULL,
  `year` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `update_index`(`c_id`, `year`) USING BTREE COMMENT '插入或者更新限制'
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of insert_update_test
-- ----------------------------
INSERT INTO `insert_update_test` VALUES (27, 3, '2032', '2021');
INSERT INTO `insert_update_test` VALUES (31, 3, '2033', '2021');
INSERT INTO `insert_update_test` VALUES (33, 3, '2034', '2020');
INSERT INTO `insert_update_test` VALUES (37, 3, '2035', '2021');

-- ----------------------------
-- Table structure for kettle_config
-- ----------------------------
DROP TABLE IF EXISTS `kettle_config`;
CREATE TABLE `kettle_config`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `target_table` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `start_time` datetime NULL DEFAULT NULL,
  `length_days` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kettle_config
-- ----------------------------
INSERT INTO `kettle_config` VALUES (1, 'actor', '2021-04-07 23:24:01', 1);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `pid` int NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1524 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1508, 1, 'e934526b-ef0d-4864-81cb-ef3c6aa42ea4');
INSERT INTO `order` VALUES (1509, 1, '8c675483-d66b-4100-a902-fca628cead5c');
INSERT INTO `order` VALUES (1510, 1, '7098055c-a4fd-4c8b-aca7-dec77b88d1b0');
INSERT INTO `order` VALUES (1511, 1, '3ec6e225-0efa-4640-be36-52e95b4c9b03');
INSERT INTO `order` VALUES (1512, 1, '1b5b5335-b5c8-4b57-8a58-fbd45670e060');
INSERT INTO `order` VALUES (1513, 1, '0287ff26-cc47-4104-932e-8650bdbd3ae7');
INSERT INTO `order` VALUES (1514, 1, '45fba7e3-6757-4676-9c67-eeae4b48f884');
INSERT INTO `order` VALUES (1515, 1, 'a58e8899-16c8-4153-bc92-ffe8b60cd0891');
INSERT INTO `order` VALUES (1516, 1, '2173f43e-6458-4599-befd-4ee02c6cae13');
INSERT INTO `order` VALUES (1517, 1, '6519c573-adee-467d-a561-fb095a27fca5');
INSERT INTO `order` VALUES (1518, 1, 'a838761b-898e-4e57-97d9-f122be320b82');
INSERT INTO `order` VALUES (1519, 1, '223d9ae8-0a51-48ff-a0c3-543a296dfedb');
INSERT INTO `order` VALUES (1520, 1, '80d3d2f6-d564-4cc7-9a1a-c282e7ae230f');
INSERT INTO `order` VALUES (1521, 1, 'de6fbf99-451c-49df-8eb5-99b64fc0f857');
INSERT INTO `order` VALUES (1522, 1, 'b9e16948-8bc5-47ae-b693-894c076c3735');
INSERT INTO `order` VALUES (1523, 1, 'b4ac71cd-f701-41c3-a809-b30a4913d01a');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stock` int NULL DEFAULT NULL,
  `version` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '元旦大礼包', 5, 0);

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info`  (
  `product_id` int NOT NULL,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `store` int NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES (1, '123456789', -5);

-- ----------------------------
-- Table structure for sensor
-- ----------------------------
DROP TABLE IF EXISTS `sensor`;
CREATE TABLE `sensor`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `temperature` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sensor
-- ----------------------------
INSERT INTO `sensor` VALUES ('sensor_1', 29.72274163505337);
INSERT INTO `sensor` VALUES ('sensor_10', 96.46226484882108);
INSERT INTO `sensor` VALUES ('sensor_2', 89.09139968646265);
INSERT INTO `sensor` VALUES ('sensor_3', 21.59075053586946);
INSERT INTO `sensor` VALUES ('sensor_4', 96.0424820927063);
INSERT INTO `sensor` VALUES ('sensor_5', 87.86025516285946);
INSERT INTO `sensor` VALUES ('sensor_6', 68.17232869593374);
INSERT INTO `sensor` VALUES ('sensor_7', 79.16515479887737);
INSERT INTO `sensor` VALUES ('sensor_8', 13.577860365798607);
INSERT INTO `sensor` VALUES ('sensor_9', 59.14149223550222);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'dd', '2021-01-19 13:01:58');

-- ----------------------------
-- Table structure for user_base_info
-- ----------------------------
DROP TABLE IF EXISTS `user_base_info`;
CREATE TABLE `user_base_info`  (
  `residenter_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `org_id` bigint NULL DEFAULT 0 COMMENT '机构ID',
  `patient_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '居民主索引，和oudb的user表的id保持一致，内部标识',
  `siteid` bigint NULL DEFAULT 0 COMMENT '(暂时不管)',
  `resident_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'DE010000700:个人信息表编号，按照某一特定编码规则赋予个人信息的顺序号',
  `health_doc_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'DE010000900 健康档案编号，按照某一特定编码规则赋予个体健康档案的编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'DE020103900 姓名，个体在公安管理部门正式登记注册的姓氏和名称',
  `ub_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'DE020103914',
  `birthday` datetime NULL DEFAULT NULL COMMENT 'DE020100502 出生日期时间，个体出生当日的公元纪年日期和时间的完整描述',
  `gender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT 'DE020104000 性别代码，个体生理性别在特定编码体系中的代码，GB/T 2261.1 性别代码\r\n            0 性别未知，1 男，2女',
  `nationality` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'DE020101500 国籍，个体所属国籍在特定编码体系中的代码，GB/T 2659',
  `nation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'DE020102500 民族，个体所属民族类别在特定编码体系中的代码，GB/T 3304',
  `marital_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'DE020101800 婚姻状况代码，个体当前婚姻状况代码，GB/T 2261.2',
  `idcard_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患者证件类型',
  `idcard_number` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患者证件号',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_householder` tinyint NULL DEFAULT NULL COMMENT '是否户主，0.否 1.是',
  `contacts_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contacts_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `father_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `father_idcard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mother_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mother_idcard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_householder_reg` tinyint NULL DEFAULT 0 COMMENT '常住地址户籍标志，标识个体的常住地址是否为户籍所在地 0 为非户籍地，1为户籍地',
  `hh_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生地（户籍）详细地址，详细到门牌号码',
  `hh_reg_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生地（户籍）所属行政区划代码，行政区划代码到村、社区这一级别',
  `hh_postcode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_reg_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_postcode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_registration` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表示个体户籍类型在特定分类中的代码，0.户籍人口 1.省外流动 2.省内流动 3.市内流动',
  `hh_reg_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍类型代码，表示个体户籍类型在特定分类中的代码，1.非农业 0.农业',
  `workplace` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `to_work_date` date NULL DEFAULT NULL,
  `career_cate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业类别代码，ZZ0207.99 职业类别代码',
  `edu_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `abo_blood_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ABO血型代码，受检者按照ABO血型系统决定的血型在特定编码体系中的代码，CV04.50.005 AB0血型代码表',
  `rh_blood_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'RH血型代码，个体按照RH血型系统决定的血型在特定分类中的代码，1.RH阴性 2.RH阳性 3.不详',
  `is_allergy` tinyint NULL DEFAULT NULL COMMENT '0 无过敏，1 过敏',
  `allergy_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '    ' COMMENT '过敏史     英文逗号隔开',
  `allergy_other_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `family_dis_his` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `family_dh_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `health_cardno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `secret_level` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密级：0 最低，默认为0',
  `contact_phone_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '01：本人电话；02：配偶电话；03：监护人电话；04：家庭电话；05：本人工作单位电话；06：居委会电话；99：其他',
  `contact_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doc_state` smallint NOT NULL DEFAULT 0 COMMENT '建档状态， 0 待建档   1.正常  2.已归档,    3待迁出， 4：已迁出  5 待迁入  6 已转归（用于高危人群）',
  `doc_create_date` date NULL DEFAULT NULL,
  `doc_org_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doc_org_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mgr_org_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mgr_org_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `father_idcard_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mother_idcard_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `father_nationality` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mother_nationality` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `living_state` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '1：在世，2：死亡；0 未说明',
  `death_date` date NULL DEFAULT NULL,
  `pr_province_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_city_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_county_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_town_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_province` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_province_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_city` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_city_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_county` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_county_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_town` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_town_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pr_village_door_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doc_org_reg_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doc_org_reg_level` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `active` smallint NOT NULL COMMENT '0 无效，1有效',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_by` bigint NOT NULL DEFAULT 0,
  `gmt_create` datetime NOT NULL,
  `create_org_id` bigint NOT NULL DEFAULT 0,
  `modify_by` bigint NULL DEFAULT 0,
  `gmt_modify` datetime NULL DEFAULT NULL,
  `gender_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别名称',
  `age_text` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age_unit_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `marital_status_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '婚姻状况名称',
  `pr_reg_code_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_registration_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍流动分类代码描述',
  `rh_blood_type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'RH血型描述',
  `father_nationality_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mother_nationality_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `career_cate_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业类别描述',
  `abo_blood_type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ABO血型',
  `age` bigint NULL DEFAULT NULL COMMENT '年龄',
  `user_dis_history_tumour_remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '既往史疾病恶性肿瘤（填写框）',
  `user_dis_occ_remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '既往史疾病职业病（填写框）',
  `is_oper` bigint NULL DEFAULT NULL COMMENT '是否有手术',
  `is_his_diag` bigint NULL DEFAULT NULL COMMENT '是否有遗传病史',
  `genetic_disease_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '遗传病史疾病',
  `responsible_doctor_id` bigint NULL DEFAULT NULL COMMENT '责任医生ID',
  `responsible_doctor_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '责任医生',
  `archiving_doctor_id` bigint NULL DEFAULT NULL COMMENT '建档医生ID',
  `archiving_doctor_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建档医生',
  `archiving_date` datetime NULL DEFAULT NULL COMMENT '建档时间',
  `input_org_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入机构代码',
  `input_org_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入机构名称',
  `input_doc_id` bigint NULL DEFAULT NULL COMMENT '录入医生ID',
  `input_doctor_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入医生',
  `input_date` datetime NULL DEFAULT NULL COMMENT '录入时间',
  `his_pay_fee_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医疗费用支付方式',
  `his_pay_fee_type_other_remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医疗费用支付方式其他输入框',
  `disability_situation` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '残疾情况     英文逗号隔开',
  `exposure_history` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '暴露史     英文逗号隔开',
  `hh_province_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_city_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_county_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_town_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_province` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_province_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_city` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_city_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_county` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_county_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_town` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_town_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hh_village_door_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `life_env_kitchen_vent` bigint NULL DEFAULT NULL COMMENT '生活环境 厨房排风设施',
  `life_env_fuel_type` bigint NULL DEFAULT NULL COMMENT '生活环境 燃料类型',
  `life_env_water` bigint NULL DEFAULT NULL COMMENT '生活环境 饮水',
  `life_env_wc` bigint NULL DEFAULT NULL COMMENT '生活环境 厕所',
  `life_env_animal` bigint NULL DEFAULT NULL COMMENT '生活环境 禽蓄栏',
  `education_level` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文化程度',
  `month_age` bigint NULL DEFAULT NULL COMMENT '月龄',
  `day_age` bigint NULL DEFAULT NULL COMMENT '天龄',
  `hh_village_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍-村ID',
  `hh_village_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍-村CODE',
  `hh_village` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍-村',
  `pr_village_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍-村ID',
  `pr_village_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍-村CODE',
  `pr_village` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍-村',
  `family_record_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭档案ID',
  `life_env_kitchen_vent_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `life_env_fuel_type_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `life_env_water_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `life_env_wc_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `life_env_animal_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `disability_situation_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exposure_history_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `form_json_str` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `fixed_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定电话',
  `med_ins_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医保号',
  `nation_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族名称',
  `drug_allergy_source` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '药物过敏源 FH0387 码表  subcode',
  `version` bigint NULL DEFAULT 0 COMMENT '版本号',
  `householder_relation_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '与户主关系编码',
  `householder_relation_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '与户主关系名称',
  `social_card_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社保卡号',
  `cooperative_card_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合作医疗号',
  `height` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身高',
  `weight` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体重',
  `grid_id` bigint NULL DEFAULT 0 COMMENT '网格主键ID',
  `grid_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网格编码',
  `grid_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网格名称',
  `grid_doc_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网格医生主键ID',
  `grid_doc_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网格医生名称',
  `master_patient_index` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患者主索引号',
  `master_patient_index_version` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患者主索引版本',
  `medical_record_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医共体病历号',
  `is_qualified` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '质控是否合格 0否 1是',
  `un_qualified_reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '质控不合格原因',
  `is_normal_sign_type` smallint NULL DEFAULT 0 COMMENT '是否普通',
  `is_high_sign_type` bigint NULL DEFAULT 0 COMMENT '是否高血压',
  `is_sugar_sign_type` bigint NULL DEFAULT 0 COMMENT '是否糖尿病',
  `is_heavy_sign_type` bigint NULL DEFAULT 0 COMMENT '是否重度精神病',
  `is_child_sign_type` bigint NULL DEFAULT 0 COMMENT '是否儿童',
  `is_woman_sign_type` bigint NULL DEFAULT 0 COMMENT '是否孕产妇',
  `is_tuberculosis_sign_type` bigint NULL DEFAULT 0 COMMENT '是否肺结核',
  `sician_year_id` bigint NULL DEFAULT 0 COMMENT '最新的签约年度',
  `is_sician` smallint NULL DEFAULT 0 COMMENT '是否签约',
  `sician_year_ids_str` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签约年度拼接的字符串',
  `record_mgr_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '档案归档类型[FH0527]',
  `record_mgr_reason` smallint NULL DEFAULT NULL COMMENT '档案归档原因: 0 死亡归档 1 长期外出 2 拒绝监管',
  `create_grid_id` bigint NULL DEFAULT NULL COMMENT '创建网格ID',
  `record_refuse_mgr` tinyint NULL DEFAULT 0 COMMENT '档案管理是否拒绝监管 0 否 1 是',
  `is_open` tinyint NULL DEFAULT NULL COMMENT '档案上传--是否开放 0-不开放，1-开放',
  `open_oper` bigint NULL DEFAULT NULL COMMENT '档案上传--开放操作医生id',
  `open_time` datetime NULL DEFAULT NULL COMMENT '档案上传--操作日期	',
  `new_health_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新健康档案号',
  `health_upload_status` tinyint NULL DEFAULT NULL COMMENT '档案上传状态',
  `GMC_ID` bigint NULL DEFAULT 1 COMMENT '医共体id',
  `user_death_out_date` datetime NULL DEFAULT NULL COMMENT '死亡/外出/退出监管/恢复管理...时间',
  PRIMARY KEY (`residenter_id`) USING BTREE,
  INDEX `health_doc_no_index`(`health_doc_no`) USING BTREE,
  INDEX `name_index`(`name`) USING BTREE,
  INDEX `idcard_number_index`(`idcard_number`) USING BTREE,
  INDEX `archiving_date_index`(`archiving_date`) USING BTREE,
  INDEX `input_date_index`(`input_date`) USING BTREE,
  INDEX `mgr_org_code_index`(`mgr_org_code`(10)) USING BTREE,
  INDEX `user_birthday_index`(`birthday`) USING BTREE,
  INDEX `family_record_id_index`(`family_record_id`) USING BTREE,
  INDEX `grid_id_index`(`grid_id`) USING BTREE,
  INDEX `doc_create_date_index`(`doc_create_date`) USING BTREE,
  INDEX `gmt_create_index`(`gmt_create`) USING BTREE,
  INDEX `doc_state_index`(`doc_state`) USING BTREE,
  INDEX `birthday_index`(`birthday`) USING BTREE,
  INDEX `sician_year_id_index`(`sician_year_id`) USING BTREE,
  INDEX `pr_village_code_index`(`pr_village_code`) USING BTREE,
  INDEX `idx_record_mgr_type`(`record_mgr_type`) USING BTREE,
  INDEX `idx_create_grid_id`(`create_grid_id`) USING BTREE,
  INDEX `idx_record_mgr_reason`(`record_mgr_reason`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '个人基本信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_base_info
-- ----------------------------

-- ----------------------------
-- Table structure for user_relationship
-- ----------------------------
DROP TABLE IF EXISTS `user_relationship`;
CREATE TABLE `user_relationship`  (
  `relationship_id` bigint NOT NULL AUTO_INCREMENT,
  `family_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `residenter_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `siteid` bigint NULL DEFAULT 0,
  `member_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `relationship_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '和户主的血缘关系',
  `active` smallint NOT NULL COMMENT '0 无效，1有效',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_by` bigint NOT NULL DEFAULT 0,
  `gmt_create` datetime NOT NULL,
  `create_org_id` bigint NOT NULL DEFAULT 0,
  `modify_by` bigint NULL DEFAULT 0,
  `gmt_modify` datetime NULL DEFAULT NULL,
  `org_id` bigint NULL DEFAULT 0,
  `relationship_type` bigint NULL DEFAULT NULL COMMENT '关系类型:0:户主关系   1：血缘关系',
  `idcard_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件号码',
  `gender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别编码',
  `gender_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别描述',
  `age_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年龄文本',
  `GMC_ID` bigint NULL DEFAULT 1 COMMENT '医共体id',
  `current_residenter_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭档案当前血缘关系拥有者',
  PRIMARY KEY (`relationship_id`) USING BTREE,
  INDEX `family_id_index`(`family_id`) USING BTREE,
  INDEX `residenter_id_index`(`residenter_id`) USING BTREE,
  INDEX `idcard_number_index`(`idcard_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '居民血缘关系/家庭成员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_relationship
-- ----------------------------

-- ----------------------------
-- Table structure for user_test
-- ----------------------------
DROP TABLE IF EXISTS `user_test`;
CREATE TABLE `user_test`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_test
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
