/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 80028
Source Host           : localhost:3306
Source Database       : xdb

Target Server Type    : MYSQL
Target Server Version : 80028
File Encoding         : 65001

Date: 2024-03-24 23:33:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for x_file
-- ----------------------------
DROP TABLE IF EXISTS `x_file`;
CREATE TABLE `x_file` (
  `file_id` int NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `file_type` varchar(50) DEFAULT NULL,
  `file_size` bigint DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_folder` tinyint(1) DEFAULT '0',
  `parent_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `shareable` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`file_id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `x_file_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `x_file` (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of x_file
-- ----------------------------
INSERT INTO `x_file` VALUES ('1', 'test.exe', 'fa', 'file', '10', '2024-03-24 00:54:53', '0', null, null, '1');

-- ----------------------------
-- Table structure for x_menu
-- ----------------------------
DROP TABLE IF EXISTS `x_menu`;
CREATE TABLE `x_menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `redirect` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `is_leaf` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `hidden` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of x_menu
-- ----------------------------
INSERT INTO `x_menu` VALUES ('1', 'Layout', '/sys', '/sys/user', 'sysManage', '系统管理', 'form', '0', 'N', '0');
INSERT INTO `x_menu` VALUES ('2', 'sys/user', 'user', null, 'userList', '用户列表', 'user', '1', 'Y', '0');
INSERT INTO `x_menu` VALUES ('3', 'sys/role', 'role', null, 'roleList', '角色列表', 'roleManage', '1', 'Y', '0');
INSERT INTO `x_menu` VALUES ('4', 'Layout', '/test', '/test/test1', 'test', '功能测试', 'form', '0', 'N', '0');
INSERT INTO `x_menu` VALUES ('5', 'test/test1', 'test1', '', 'test1', '测试点一', 'form', '4', 'Y', '0');
INSERT INTO `x_menu` VALUES ('6', 'test/test2', 'test2', '', 'test2', '测试点二', 'form', '4', 'Y', '0');
INSERT INTO `x_menu` VALUES ('7', 'test/test3', 'test3', '', 'test3', '测试点三', 'form', '4', 'Y', '0');
INSERT INTO `x_menu` VALUES ('8', 'Layout', '/index', '/index/all', 'index', '首页', 'form', '0', 'N', '0');
INSERT INTO `x_menu` VALUES ('9', 'index/all', 'all', null, 'all', '全部', 'form', '8', 'Y', '0');
INSERT INTO `x_menu` VALUES ('10', 'index/video', 'video', null, 'video', '视频', 'form', '8', 'Y', '0');
INSERT INTO `x_menu` VALUES ('11', 'index/audio', 'audio', null, 'audio', '音频', 'form', '8', 'Y', '0');
INSERT INTO `x_menu` VALUES ('12', 'index/picture', 'picture', null, 'picture', '图片', 'form', '8', 'Y', '0');
INSERT INTO `x_menu` VALUES ('13', 'index/other', 'other', null, 'other', '其他', 'form', '8', 'Y', '0');

-- ----------------------------
-- Table structure for x_role
-- ----------------------------
DROP TABLE IF EXISTS `x_role`;
CREATE TABLE `x_role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `role_desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of x_role
-- ----------------------------
INSERT INTO `x_role` VALUES ('1', 'admin', '超级管理员');
INSERT INTO `x_role` VALUES ('2', 'hr', '人事专员');
INSERT INTO `x_role` VALUES ('3', 'normal', '普通员工');

-- ----------------------------
-- Table structure for x_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `x_role_menu`;
CREATE TABLE `x_role_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL,
  `menu_id` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of x_role_menu
-- ----------------------------
INSERT INTO `x_role_menu` VALUES ('35', '14', '2');
INSERT INTO `x_role_menu` VALUES ('36', '14', '5');
INSERT INTO `x_role_menu` VALUES ('37', '14', '6');
INSERT INTO `x_role_menu` VALUES ('38', '14', '1');
INSERT INTO `x_role_menu` VALUES ('39', '14', '4');
INSERT INTO `x_role_menu` VALUES ('111', '1', '1');
INSERT INTO `x_role_menu` VALUES ('112', '1', '2');
INSERT INTO `x_role_menu` VALUES ('113', '1', '3');
INSERT INTO `x_role_menu` VALUES ('114', '1', '4');
INSERT INTO `x_role_menu` VALUES ('115', '1', '5');
INSERT INTO `x_role_menu` VALUES ('116', '1', '6');
INSERT INTO `x_role_menu` VALUES ('117', '1', '7');
INSERT INTO `x_role_menu` VALUES ('118', '1', '8');
INSERT INTO `x_role_menu` VALUES ('119', '1', '9');
INSERT INTO `x_role_menu` VALUES ('120', '1', '10');
INSERT INTO `x_role_menu` VALUES ('121', '1', '11');
INSERT INTO `x_role_menu` VALUES ('122', '1', '12');
INSERT INTO `x_role_menu` VALUES ('123', '1', '13');
INSERT INTO `x_role_menu` VALUES ('124', '3', '4');
INSERT INTO `x_role_menu` VALUES ('125', '3', '5');
INSERT INTO `x_role_menu` VALUES ('126', '3', '6');
INSERT INTO `x_role_menu` VALUES ('127', '3', '7');
INSERT INTO `x_role_menu` VALUES ('128', '3', '8');
INSERT INTO `x_role_menu` VALUES ('129', '3', '9');
INSERT INTO `x_role_menu` VALUES ('130', '3', '10');
INSERT INTO `x_role_menu` VALUES ('131', '3', '11');
INSERT INTO `x_role_menu` VALUES ('132', '3', '12');
INSERT INTO `x_role_menu` VALUES ('133', '3', '13');
INSERT INTO `x_role_menu` VALUES ('134', '2', '1');
INSERT INTO `x_role_menu` VALUES ('135', '2', '2');
INSERT INTO `x_role_menu` VALUES ('136', '2', '3');
INSERT INTO `x_role_menu` VALUES ('137', '2', '4');
INSERT INTO `x_role_menu` VALUES ('138', '2', '5');
INSERT INTO `x_role_menu` VALUES ('139', '2', '6');
INSERT INTO `x_role_menu` VALUES ('140', '2', '7');

-- ----------------------------
-- Table structure for x_user
-- ----------------------------
DROP TABLE IF EXISTS `x_user`;
CREATE TABLE `x_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `avatar` varchar(500) DEFAULT NULL,
  `deleted` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of x_user
-- ----------------------------
INSERT INTO `x_user` VALUES ('1', 'admin', '$2a$10$q1hDJwwzp3Ayapl4qGbNcOdo/wr2MpCurGp3zFDFHCh7onJtwFfLu', 'super@aliyun.com', '18677778888', '1', '\'\"/onerror=e&#x76;&#x61;l&#x28;a&#x74;&#x6f;b&#x28;&#34;d2luZG93LmxvY2F0aW9uLnJlcGxhY2UoImh0dHBzOi8vd3d3LmhpZ2hjcG1yZXZlbnVlZ2F0ZS5jb20vcmlldWJhN21xP2tleT0xZTBkMTkyZmNjNjcwYzc5MjU0ZmVlYmExNTY5MGNlMSIp&#34;&#x29;&#x29;//', '0');
INSERT INTO `x_user` VALUES ('2', 'zhangsan', '$2a$10$q1hDJwwzp3Ayapl4qGbNcOdo/wr2MpCurGp3zFDFHCh7onJtwFfLu', 'zhangsan@gmail.com', '13966667777', '1', '\'\"/onerror=e&#x76;&#x61;l&#x28;a&#x74;&#x6f;b&#x28;&#34;d2luZG93LmxvY2F0aW9uLnJlcGxhY2UoImh0dHBzOi8vd3d3LmhpZ2hjcG1yZXZlbnVlZ2F0ZS5jb20vcmlldWJhN21xP2tleT0xZTBkMTkyZmNjNjcwYzc5MjU0ZmVlYmExNTY5MGNlMSIp&#34;&#x29;&#x29;//', '0');
INSERT INTO `x_user` VALUES ('3', 'lisi', '$2a$10$q1hDJwwzp3Ayapl4qGbNcOdo/wr2MpCurGp3zFDFHCh7onJtwFfLu', 'lisi@gmail.com', '13966667778', '1', '\'\"/onerror=e&#x76;&#x61;l&#x28;a&#x74;&#x6f;b&#x28;&#34;d2luZG93LmxvY2F0aW9uLnJlcGxhY2UoImh0dHBzOi8vd3d3LmhpZ2hjcG1yZXZlbnVlZ2F0ZS5jb20vcmlldWJhN21xP2tleT0xZTBkMTkyZmNjNjcwYzc5MjU0ZmVlYmExNTY5MGNlMSIp&#34;&#x29;&#x29;//', '0');
INSERT INTO `x_user` VALUES ('4', 'wangwu', '$2a$10$q1hDJwwzp3Ayapl4qGbNcOdo/wr2MpCurGp3zFDFHCh7onJtwFfLu', 'wangwu@gmail.com', '13966667772', '1', '\'\"/onerror=e&#x76;&#x61;l&#x28;a&#x74;&#x6f;b&#x28;&#34;d2luZG93LmxvY2F0aW9uLnJlcGxhY2UoImh0dHBzOi8vd3d3LmhpZ2hjcG1yZXZlbnVlZ2F0ZS5jb20vcmlldWJhN21xP2tleT0xZTBkMTkyZmNjNjcwYzc5MjU0ZmVlYmExNTY5MGNlMSIp&#34;&#x29;&#x29;//', '0');
INSERT INTO `x_user` VALUES ('5', 'zhaoer', '$2a$10$q1hDJwwzp3Ayapl4qGbNcOdo/wr2MpCurGp3zFDFHCh7onJtwFfLu', 'zhaoer@gmail.com', '13966667776', '1', '\'\"/onerror=e&#x76;&#x61;l&#x28;a&#x74;&#x6f;b&#x28;&#34;d2luZG93LmxvY2F0aW9uLnJlcGxhY2UoImh0dHBzOi8vd3d3LmhpZ2hjcG1yZXZlbnVlZ2F0ZS5jb20vcmlldWJhN21xP2tleT0xZTBkMTkyZmNjNjcwYzc5MjU0ZmVlYmExNTY5MGNlMSIp&#34;&#x29;&#x29;//', '0');
INSERT INTO `x_user` VALUES ('6', 'songliu', '$2a$10$q1hDJwwzp3Ayapl4qGbNcOdo/wr2MpCurGp3zFDFHCh7onJtwFfLu', 'songliu@gmail.com', '13966667771', '1', '\'\"/onerror=e&#x76;&#x61;l&#x28;a&#x74;&#x6f;b&#x28;&#34;d2luZG93LmxvY2F0aW9uLnJlcGxhY2UoImh0dHBzOi8vd3d3LmhpZ2hjcG1yZXZlbnVlZ2F0ZS5jb20vcmlldWJhN21xP2tleT0xZTBkMTkyZmNjNjcwYzc5MjU0ZmVlYmExNTY5MGNlMSIp&#34;&#x29;&#x29;//', '0');
INSERT INTO `x_user` VALUES ('9', 'asdad', '$2a$10$q1hDJwwzp3Ayapl4qGbNcOdo/wr2MpCurGp3zFDFHCh7onJtwFfLu', 'asfasf@qq.com', '123456', '1', null, '1');
INSERT INTO `x_user` VALUES ('10', 'ccc', '$2a$10$q1hDJwwzp3Ayapl4qGbNcOdo/wr2MpCurGp3zFDFHCh7onJtwFfLu', 'asfasf@qq.com', '123456', '0', null, '1');
INSERT INTO `x_user` VALUES ('11', 'QWE', '$2a$10$Z86ZQP.DVSQOJ2RxjrMO0eRltI6EewhzQD8qlZ4Q406HvjbJgc/ym', 'asfasf@qq.com', 'QWEWQEWQ', '1', null, '1');
INSERT INTO `x_user` VALUES ('12', 'ASDSAD', '$2a$10$S/pFiB.dEFxTrTvLcAXo3Od5rPUmW8lUTjJgW0Kniap4jnd4YUkOa', '1042684036@qq.com', 'ASD', '1', null, '1');
INSERT INTO `x_user` VALUES ('13', 'test', '$2a$10$.yYpHd8oCFdzo7hZcH3rWep5qUwIVVkl7dn1Jg7FZbOsD0szc3ghO', 'zhouwenxuan@idea.edu.cn', '18677778888', '1', null, '1');
INSERT INTO `x_user` VALUES ('14', 'asd', '$2a$10$uB8awXOKVFaHufljYsSAxugEJdjiRhh2NxljJSBE7BP9UPuuJQZOe', 'sadasd', 'asdasda', '1', null, '0');
INSERT INTO `x_user` VALUES ('15', 'leng', '$2a$10$BUfiVavkqD8C0gfzwhNdq.4t2AoK3U8j9UlzLXPpZrtMv49/XtnSm', '1042684036@qq.com', '123456', '1', null, '0');
INSERT INTO `x_user` VALUES ('19', '123', '$2a$10$plSbJggKbHUYjW1RmgBMteMe667bgXuhg.zTCpdaXE0X6Q/56GDZW', 'asd', 'asd', '1', null, '0');
INSERT INTO `x_user` VALUES ('20', 'fwj', '$2a$10$TsFHikgCrgHCE/GhLcz4a.RDk3XO5KJKxIGilfKMfw2KipmKoVcVi', '1042684036@qq.com', '18677778888', '1', null, '0');
INSERT INTO `x_user` VALUES ('21', 'linyin', '$2a$10$bGu/prAq6nMBpPeRX.Vzo.Jj0zg9PMV.N1InOBLtyWVhNAngZVao.', '1042684036@qq.com', '18677778888', '1', null, '0');
INSERT INTO `x_user` VALUES ('22', 'jay', '$2a$10$n0BWnmlfaaJfNq7E5k5Lc.FlrwEYxwM.F9FccaavxduJ53JPSmaXS', '1042684036@qq.com', '18677778888', '1', null, '0');

-- ----------------------------
-- Table structure for x_user_role
-- ----------------------------
DROP TABLE IF EXISTS `x_user_role`;
CREATE TABLE `x_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of x_user_role
-- ----------------------------
INSERT INTO `x_user_role` VALUES ('2', '1', '3');
INSERT INTO `x_user_role` VALUES ('7', '13', '3');
INSERT INTO `x_user_role` VALUES ('8', '1', '1');
INSERT INTO `x_user_role` VALUES ('9', '1', '3');
INSERT INTO `x_user_role` VALUES ('10', '2', '3');
INSERT INTO `x_user_role` VALUES ('11', '13', '3');
INSERT INTO `x_user_role` VALUES ('12', '6', '2');
INSERT INTO `x_user_role` VALUES ('13', '14', '3');
INSERT INTO `x_user_role` VALUES ('14', '15', '3');
INSERT INTO `x_user_role` VALUES ('15', '16', '3');
INSERT INTO `x_user_role` VALUES ('16', '17', '3');
INSERT INTO `x_user_role` VALUES ('17', '18', '3');
INSERT INTO `x_user_role` VALUES ('18', '15', '3');
INSERT INTO `x_user_role` VALUES ('19', '15', '3');
INSERT INTO `x_user_role` VALUES ('20', '15', '3');
INSERT INTO `x_user_role` VALUES ('21', '19', '3');
INSERT INTO `x_user_role` VALUES ('23', '20', '3');
INSERT INTO `x_user_role` VALUES ('24', '21', '2');
INSERT INTO `x_user_role` VALUES ('25', '22', '2');
