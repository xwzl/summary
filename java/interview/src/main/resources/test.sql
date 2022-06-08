/*
 Navicat Premium Data Transfer

 Source Server         : txy
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 1.15.19.68:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 02/06/2022 13:48:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_prescription_order
-- ----------------------------
DROP TABLE IF EXISTS `biz_prescription_order`;
CREATE TABLE `biz_prescription_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `org_id` bigint DEFAULT NULL COMMENT '机构ID',
  `campus_id` bigint DEFAULT NULL COMMENT '院区ID',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '科室名称',
  `warehouse` varchar(50) DEFAULT NULL COMMENT '药房',
  `warehouse_id` bigint DEFAULT NULL COMMENT '药房主键',
  `prescription_id` bigint DEFAULT NULL COMMENT '处方ID',
  `prescription_time` datetime DEFAULT NULL COMMENT '处方开方日期',
  `prescription_no` varchar(64) DEFAULT NULL COMMENT '处方号',
  `prescription_type` varchar(50) DEFAULT NULL COMMENT '处方类型',
  `prescription_simple_info` varchar(200) DEFAULT NULL COMMENT '处方简略信息',
  `print_audit_time` datetime DEFAULT NULL COMMENT '打印请领单时间',
  `send_doctor_id` bigint DEFAULT NULL COMMENT '发药药师ID',
  `send_doctor_name` varchar(50) DEFAULT NULL COMMENT '发药药师姓名',
  `send_medicaiton_time` datetime DEFAULT NULL COMMENT '发药时间',
  `healthcare_record_id` bigint DEFAULT NULL COMMENT '诊疗记录主键',
  `patient_id` bigint DEFAULT NULL COMMENT '患者主键',
  `patient_name` varchar(50) DEFAULT NULL COMMENT '患者姓名',
  `gender` char(20) DEFAULT NULL COMMENT '性别',
  `age` varchar(50) DEFAULT NULL COMMENT '年龄',
  `patient_mobile_number` varchar(50) DEFAULT NULL COMMENT '患者手机号',
  `receiver_name` varchar(50) DEFAULT NULL COMMENT '收货人姓名',
  `receiver_mobile_number` varchar(50) DEFAULT NULL COMMENT '收货人手机号',
  `province_code` varchar(50) DEFAULT NULL COMMENT '省编码',
  `province_name` varchar(50) DEFAULT NULL COMMENT '省名称',
  `city_code` varchar(50) DEFAULT NULL COMMENT '市编码',
  `city_name` varchar(50) DEFAULT NULL COMMENT '市名称',
  `area_code` varchar(50) DEFAULT NULL COMMENT '区编码',
  `area_name` varchar(50) DEFAULT NULL COMMENT '区名称',
  `detail_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `postal_code` varchar(50) DEFAULT NULL COMMENT '邮政编码',
  `express_corp_code` varchar(64) DEFAULT NULL COMMENT '快递公司编码',
  `express_corp_name` varchar(64) DEFAULT NULL COMMENT '快递公司名称',
  `express_number` varchar(64) DEFAULT NULL COMMENT '快递单号',
  `print_express_time` datetime DEFAULT NULL COMMENT '打印快递时间',
  `express_status` tinyint DEFAULT NULL COMMENT '1. 打印快递单 (详细看美剧)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='处方物流单';

-- ----------------------------
-- Records of biz_prescription_order
-- ----------------------------
BEGIN;
INSERT INTO `biz_prescription_order` VALUES (1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for config_item
-- ----------------------------
DROP TABLE IF EXISTS `config_item`;
CREATE TABLE `config_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `source` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '原字段',
  `source_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '原字段名称',
  `source_type` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '原字段类型',
  `target` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '目标字段',
  `target_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '目标名称',
  `doc_target` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '目标文档字段',
  `target_type` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '目标类型',
  `rule` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '转换器配置',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='配置项';

-- ----------------------------
-- Records of config_item
-- ----------------------------
BEGIN;
INSERT INTO `config_item` VALUES (1, 'patientName', '患者名称', 'java.lang.String', 'patientName', '患者名称', 'FH01.01', 'java.lang.String', NULL, '2022-05-31 11:42:38', '2022-05-31 11:42:41', 0);
INSERT INTO `config_item` VALUES (2, 'age', '年龄', 'java.lang.String', 'age', '年龄', 'FH01.02', 'java.lang.String', NULL, '2022-05-31 11:42:38', '2022-05-31 11:42:41', 0);
INSERT INTO `config_item` VALUES (3, 'birth', '出生日期', 'java.time.LocalDate', 'birth', '年龄', 'FH01.03', 'java.time.LocalDate', 'yyyyMMdd', '2022-05-31 11:42:38', '2022-05-31 11:42:41', 0);
INSERT INTO `config_item` VALUES (4, 'birthDay', '出生日期', 'java.time.LocalDateTime', 'birthDay', '年龄', 'FH01.04', 'java.time.LocalDateTime', 'yyyyMMddHHmmss', '2022-05-31 11:42:38', '2022-05-31 11:42:41', 0);
INSERT INTO `config_item` VALUES (5, 'itemCode', '校验项', 'java.lang.String', 'itemCode', '患者名称', 'FH01.05', 'java.lang.String', NULL, '2022-05-31 11:42:38', '2022-05-31 11:42:41', 0);
INSERT INTO `config_item` VALUES (6, 'itemName', '校验项名称', 'java.lang.String', 'itemName', '年龄', 'FH01.06', 'java.lang.String', NULL, '2022-05-31 11:42:38', '2022-05-31 11:42:41', 0);
INSERT INTO `config_item` VALUES (7, 'birth', '出生日期', 'java.lang.String', 'birth', '年龄', 'FH01.03', 'java.lang.String', 'yyyy-MM-dd', '2022-05-31 11:42:38', '2022-05-31 11:42:41', 1);
INSERT INTO `config_item` VALUES (8, 'address', '地址', 'java.lang.String', 'address', '年龄', 'FH01.09', 'java.lang.String', NULL, '2022-05-31 11:42:38', '2022-05-31 11:42:41', 0);
INSERT INTO `config_item` VALUES (9, 'age', '年龄', 'java.lang.String', 'age', '年龄', 'FH01.02', 'java.lang.String', NULL, '2022-05-31 11:42:38', '2022-05-31 11:42:41', 0);
COMMIT;

-- ----------------------------
-- Table structure for config_unit
-- ----------------------------
DROP TABLE IF EXISTS `config_unit`;
CREATE TABLE `config_unit` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_config_id` int DEFAULT NULL COMMENT '模板配置主键',
  `config_item_id` bigint DEFAULT NULL COMMENT '配置项主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='配置项组合';

-- ----------------------------
-- Records of config_unit
-- ----------------------------
BEGIN;
INSERT INTO `config_unit` VALUES (1, 1, 1, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `config_unit` VALUES (2, 1, 2, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `config_unit` VALUES (3, 1, 3, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `config_unit` VALUES (4, 1, 4, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `config_unit` VALUES (5, 2, 5, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 1);
INSERT INTO `config_unit` VALUES (6, 2, 6, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 1);
INSERT INTO `config_unit` VALUES (7, 3, 7, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `config_unit` VALUES (8, 3, 8, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `config_unit` VALUES (9, 3, 1, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `config_unit` VALUES (10, 3, 2, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `config_unit` VALUES (11, 3, 3, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `config_unit` VALUES (12, 3, 4, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `config_unit` VALUES (13, 5, 1, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `config_unit` VALUES (14, 6, 2, '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
COMMIT;

-- ----------------------------
-- Table structure for gsmanage
-- ----------------------------
DROP TABLE IF EXISTS `gsmanage`;
CREATE TABLE `gsmanage` (
  `sysId` varchar(16) NOT NULL DEFAULT '' COMMENT '系统标识',
  `sysName` varchar(32) DEFAULT NULL COMMENT '系统名称',
  `privateKey` text COMMENT '私有key',
  `publicKey` text COMMENT '公有Key',
  `authedIp` varchar(256) DEFAULT NULL COMMENT '授权的ip，多个ip以英文分号隔开',
  `userName` varchar(32) DEFAULT NULL COMMENT '系统用户名称',
  `pwd` varchar(32) DEFAULT NULL COMMENT '系统用户密码',
  `notifyUrl` varchar(128) DEFAULT NULL COMMENT '消息推送路径',
  `notifyParam` varchar(32) DEFAULT NULL COMMENT '消息推送参数',
  `deleteFlag` varchar(4) DEFAULT NULL COMMENT '删除标识(预留)',
  `reserve1` varchar(64) DEFAULT NULL COMMENT '预留字段1',
  `reserve2` varchar(64) DEFAULT NULL COMMENT '预留字段2',
  PRIMARY KEY (`sysId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of gsmanage
-- ----------------------------
BEGIN;
INSERT INTO `gsmanage` VALUES ('MyTestSys', 'genSiClient', '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', NULL, NULL, NULL, 'http://localhost:8890/client/receiveMessage', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for gsrequest
-- ----------------------------
DROP TABLE IF EXISTS `gsrequest`;
CREATE TABLE `gsrequest` (
  `transId` varchar(64) NOT NULL,
  `reqBody` longtext,
  `rspBody` longtext,
  `intime` varchar(32) DEFAULT NULL,
  `serviceCode` varchar(32) NOT NULL,
  `rsptime` varchar(32) DEFAULT NULL,
  `sysId` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`transId`,`serviceCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of gsrequest
-- ----------------------------
BEGIN;
INSERT INTO `gsrequest` VALUES ('202106241000131', '{\"requestMsgBody\":{\"mobile\":\"15280975199\"},\"requestMsgHeader\":{\"inTime\":\"20220506045428\",\"respTime\":\"\",\"serviceCode\":\"search_mobile_tag\",\"sysId\":\"MyTestSys\",\"sysPwd\":\"testPwd\",\"sysUser\":\"testUser\",\"transId\":\"202106241000131\"}}', '{\"requestMsgHeader\":{\"inTime\":\"20220506045428\",\"respTime\":\"20220506045428\",\"serviceCode\":\"search_mobile_tag\",\"sysId\":\"MyTestSys\",\"sysPwd\":\"testPwd\",\"sysUser\":\"testUser\",\"transId\":\"202106241000131\"},\"responseMsgBody\":{\"mobile\":\"15280975199\",\"mobileTag\":\"该号码暂无标记\"}}', '20220506045428', 'search_mobile_tag', '20220506045428', 'MyTestSys');
INSERT INTO `gsrequest` VALUES ('2021125100028', '{\"mobile\":\"15280975199\",\"transId\":\"2021125100028\"}', '{\"body\":{\"mobile\":\"15280975199\",\"desc\":\"该号码暂无标记\"},\"header\":{\"intime\":\"20220427204212\",\"serviceCode\":\"search_mobile_mark\",\"transId\":\"2021125100028\"}}', '20220427204212', 'search_mobile_mark', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for template
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板名称',
  `data_set_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据集编码',
  `log_monitor` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '日志监听器',
  `switch_log_monitor` tinyint DEFAULT NULL COMMENT '是否开启监听日志',
  `switch_doc` tinyint DEFAULT NULL COMMENT '开启doc',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='模板名称';

-- ----------------------------
-- Records of template
-- ----------------------------
BEGIN;
INSERT INTO `template` VALUES (1, '检查申请单', '1', 'com.java.interview.java.report.monitor.LogMonitor', 1, 1, '2022-05-31 11:41:04', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for template_config
-- ----------------------------
DROP TABLE IF EXISTS `template_config`;
CREATE TABLE `template_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_id` bigint NOT NULL COMMENT '模板id',
  `template_config_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '组合名称',
  `config_suit_type` tinyint NOT NULL COMMENT '模块类型',
  `mapping_field` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '映射字段',
  `handler_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '处理名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='模板配置';

-- ----------------------------
-- Records of template_config
-- ----------------------------
BEGIN;
INSERT INTO `template_config` VALUES (1, 0, '健康档案', 2, 'healthcareRecordId', 'com.java.interview.java.report.handler.HealthcareRecord', '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `template_config` VALUES (2, 1, '病区日志上传', 1, 'id', 'com.java.interview.java.report.handler.AreaLogHandler', '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `template_config` VALUES (3, 1, '健康档案', 2, 'healthcareRecordId', 'com.java.interview.java.report.handler.HealthcareRecord', '2022-05-31 11:47:04', '2022-05-31 11:47:06', 1);
INSERT INTO `template_config` VALUES (4, 0, '诊疗信息', 3, 'test', 'com.java.interview.java.report.handler.TestHandler', '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `template_config` VALUES (5, 2, '检验上传', 1, 'id', 'com.java.interview.java.report.handler.CheckHandler', '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
INSERT INTO `template_config` VALUES (6, 2, '健康档案', 2, 'healthcareRecordId', 'com.java.interview.java.report.handler.HealthcareRecord', '2022-05-31 11:47:04', '2022-05-31 11:47:06', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
