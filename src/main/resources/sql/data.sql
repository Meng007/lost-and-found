-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典名称',
  `dict_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典类型',
  `status` int(1) NULL DEFAULT NULL COMMENT ' 字典状态 1：可用，0：禁用',
  `create_user` int(10) NULL DEFAULT NULL COMMENT '创建用户',
  `update_user` int(10) NULL DEFAULT NULL COMMENT ' 更新用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '物品分类', '物品', 1, 1, NULL, '2020-11-26 00:09:38', NULL, '备注');
INSERT INTO `sys_dict_type` VALUES (2, '敏感词分类', '敏感词', 1, 1, NULL, '2020-11-26 00:37:56', NULL, '备注');
INSERT INTO `sys_dict_type` VALUES (3, '用户分类', '用户', 1, 1, NULL, '2020-11-26 00:38:36', NULL, '备注');

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sort` int(6) NULL DEFAULT NULL COMMENT '字典排序',
  `dict_label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典标签',
  `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典值',
  `status` int(1) NULL DEFAULT NULL COMMENT '字典数据状态 1：停用，0：可用',
  `dict_type` int(10) NULL DEFAULT NULL COMMENT '字典类型',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` int(10) NULL DEFAULT NULL COMMENT '创建用户',
  `update_user` int(10) NULL DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '卡', 'cart', NULL, 1, '2020-11-26 00:45:04', NULL, 1, NULL);
INSERT INTO `sys_dict_data` VALUES (2, 1, '自行车', 'bicycle', NULL, 1, '2020-11-26 00:45:55', NULL, 1, NULL);
INSERT INTO `sys_dict_data` VALUES (3, 1, '学生证', 'student_ID_card', NULL, 1, '2020-11-26 00:46:47', NULL, 1, NULL);
INSERT INTO `sys_dict_data` VALUES (4, 1, '钥匙', 'key', NULL, 1, '2020-11-26 00:47:38', NULL, 1, NULL);
INSERT INTO `sys_dict_data` VALUES (5, 1, '色情', '00', NULL, 2, '2020-11-26 00:48:18', NULL, 1, NULL);
INSERT INTO `sys_dict_data` VALUES (6, 1, '赌博', '00', NULL, 2, '2020-11-26 00:48:28', NULL, 1, NULL);
INSERT INTO `sys_dict_data` VALUES (7, 1, '政治', '00', NULL, 2, '2020-11-26 00:48:47', NULL, 1, NULL);
INSERT INTO `sys_dict_data` VALUES (8, 1, '学生', 'student', NULL, 3, '2020-11-26 00:49:32', NULL, 1, NULL);
INSERT INTO `sys_dict_data` VALUES (9, 1, '管理员', 'admin', NULL, 3, '2020-11-26 00:49:54', NULL, 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
