/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : coffee

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 25/10/2024 11:19:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(0) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE INDEX `aacount_unique`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456');

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `item_id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `stock` int(0) NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `press` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `press_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (1, '测试3', '23', 0, 11.00, ' 111', 'http://localhost:9999/images/u=9961343,878827833&fm=253&fmt=auto&app=138&f=JPEG.webp', '测试3', '测试3', '2023-10-10');
INSERT INTO `item` VALUES (2, '测试1', '23', 8, 11.00, ' 111', 'http://localhost:9999/images/u=1278571863,4256861999&fm=253&fmt=auto&app=138&f=JPEG.webp', '测试1', '测试1', '2023-10-10');
INSERT INTO `item` VALUES (3, '测试2', '23', 10, 10.00, ' ', 'http://localhost:9999/images/u=1657385061,804412773&fm=253&fmt=auto&app=138&f=JPEG.webp', '测试2', '测试2', '2023-10-10');
INSERT INTO `item` VALUES (4, '测试4', '23', 11, 22.00, ' 测试4测试4测试4测试4测试4', 'http://localhost:9999/images/u=2197814819,3625608665&fm=253&fmt=auto&app=138&f=JPEG.webp', '测试4', '测试4', '2023-10-10');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `order_total` decimal(10, 2) NULL DEFAULT NULL,
  `created_time` datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `fk_order_user_auth_1`(`user_id`) USING BTREE,
  CONSTRAINT `fk_order_user_auth_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (14, 1, 100.00, '2024-10-25 10:55:41.788417', '测试地址', '已完成');
INSERT INTO `order` VALUES (15, 1, 11.00, '2024-10-25 10:55:44.182077', NULL, '已完成');
INSERT INTO `order` VALUES (16, 1, 11.00, '2024-10-25 10:55:46.118403', NULL, '已完成');
INSERT INTO `order` VALUES (17, 1, 55.00, '2024-10-25 06:57:21.564181', NULL, '处理中');
INSERT INTO `order` VALUES (20, 1, 22.00, '2024-10-25 06:57:21.567965', NULL, '处理中');
INSERT INTO `order` VALUES (21, 1, 11.00, '2024-10-25 06:57:21.570703', NULL, '处理中');
INSERT INTO `order` VALUES (23, 1, 11.00, '2024-10-25 06:57:21.573864', '11', '处理中');
INSERT INTO `order` VALUES (24, 1, 11.00, '2024-10-25 06:57:21.576618', '222', '处理中');
INSERT INTO `order` VALUES (25, 1, 11.00, '2024-10-25 06:57:21.579364', '11', '处理中');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `order_id` int(0) NOT NULL,
  `item_id` int(0) NOT NULL,
  `number` int(0) NOT NULL,
  PRIMARY KEY (`order_id`, `item_id`) USING BTREE,
  INDEX `fk_order_detail_item_1`(`item_id`) USING BTREE,
  CONSTRAINT `fk_order_detail_item_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_detail_order_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (15, 1, 1);
INSERT INTO `order_detail` VALUES (16, 1, 1);
INSERT INTO `order_detail` VALUES (17, 1, 5);
INSERT INTO `order_detail` VALUES (20, 1, 2);
INSERT INTO `order_detail` VALUES (21, 1, 1);
INSERT INTO `order_detail` VALUES (23, 1, 1);
INSERT INTO `order_detail` VALUES (24, 2, 1);
INSERT INTO `order_detail` VALUES (25, 2, 1);

-- ----------------------------
-- Table structure for project_group
-- ----------------------------
DROP TABLE IF EXISTS `project_group`;
CREATE TABLE `project_group`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_group
-- ----------------------------
INSERT INTO `project_group` VALUES (23, '222');

-- ----------------------------
-- Table structure for shoppingcart_item
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart_item`;
CREATE TABLE `shoppingcart_item`  (
  `user_id` int(0) NOT NULL,
  `item_id` int(0) NOT NULL,
  `number` int(0) NOT NULL,
  PRIMARY KEY (`user_id`, `item_id`) USING BTREE,
  INDEX `fk_shoppingcart_item_1`(`item_id`) USING BTREE,
  CONSTRAINT `fk_shoppingcart_item_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_shoppingcart_user_auth_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `account_unique`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'test', '123456', '1', '1');
INSERT INTO `user` VALUES (3, 'test123456', 'test123456', '18320222222', '2777679537@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
