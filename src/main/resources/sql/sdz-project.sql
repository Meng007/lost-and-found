/*
 Navicat MySQL Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : sdz-project

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 26/12/2020 20:32:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods_message
-- ----------------------------
DROP TABLE IF EXISTS `goods_message`;
CREATE TABLE `goods_message`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `message_id` int(10) NULL DEFAULT NULL COMMENT ' 消息id',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '留言人id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '留言时间',
  `goods_id` int(10) NULL DEFAULT NULL COMMENT '留言物品id',
  `status` int(1) NULL DEFAULT NULL COMMENT '留言状态：1通过，0待审核',
  `agree` int(10) NULL DEFAULT NULL COMMENT '点赞数',
  `message_content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '留言内容',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除 1删除，0为删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '物品留言表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for goods_message_agree
-- ----------------------------
DROP TABLE IF EXISTS `goods_message_agree`;
CREATE TABLE `goods_message_agree`  (
  `user_id` int(10) NOT NULL COMMENT '点赞用户id',
  `message_id` int(10) NULL DEFAULT NULL COMMENT '留言id',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '物品留言点赞记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_cate
-- ----------------------------
DROP TABLE IF EXISTS `sys_cate`;
CREATE TABLE `sys_cate`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cate_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类名称',
  `pid` int(10) NULL DEFAULT NULL COMMENT '父级id',
  `sort` int(4) NULL DEFAULT NULL COMMENT '排序',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态  1禁用 0可用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` int(10) NULL DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_comment
-- ----------------------------
DROP TABLE IF EXISTS `sys_comment`;
CREATE TABLE `sys_comment`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户id',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '留言内容',
  `tag_id` int(10) NULL DEFAULT NULL COMMENT '标签id',
  `views` int(10) NULL DEFAULT NULL COMMENT '浏览量',
  `message_num` int(10) NULL DEFAULT NULL COMMENT '评论数',
  `agree_num` int(10) NULL DEFAULT NULL COMMENT '点赞数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发布日期',
  `is_delete` int(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  `status` int(4) NULL DEFAULT NULL COMMENT '状态 1 正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '留言' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_goods
-- ----------------------------
DROP TABLE IF EXISTS `sys_goods`;
CREATE TABLE `sys_goods`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '物品id',
  `goods_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物品名称',
  `goods_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物品标题',
  `goods_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '物品内容描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `lose_time` datetime(0) NULL DEFAULT NULL COMMENT '丢失时间',
  `cover_image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面图',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态： 0： 待审核，1：通过，2：禁用',
  `goods_type` int(4) NULL DEFAULT NULL COMMENT '物品类型',
  `views` int(6) NULL DEFAULT NULL COMMENT '浏览量',
  `message_num` int(6) NULL DEFAULT NULL COMMENT '留言量',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '发布人id',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除： 1：删除，0：存在',
  `topping` bit(1) NULL DEFAULT NULL COMMENT '是否置顶',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `linkman` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `goods_status` int(1) NULL DEFAULT NULL COMMENT '物品状态： 1：丢失，0：拾到',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '物品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_goods_image
-- ----------------------------
DROP TABLE IF EXISTS `sys_goods_image`;
CREATE TABLE `sys_goods_image`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `goods_id` int(10) NULL DEFAULT NULL COMMENT '物品id',
  `image_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '物品图片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `pid` int(10) NULL DEFAULT NULL COMMENT '父级菜单',
  `order` int(4) NULL DEFAULT NULL COMMENT '菜单排序',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` tinyint(1) NULL DEFAULT NULL COMMENT '是否外链  1：是，0：否',
  `menu_type` tinyint(1) NULL DEFAULT NULL COMMENT '菜单类型 1：目录，2：菜单 3：按钮',
  `perms` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '操作用户id',
  `icon` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `visible` tinyint(1) NULL DEFAULT NULL COMMENT '是否隐藏 1：隐藏 0：显现',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态 1：停用，0正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(1) NULL DEFAULT NULL COMMENT '消息类型：1系统消息，2留言消息，',
  `message` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '消息内容',
  `create_user` int(10) NULL DEFAULT NULL COMMENT '发布消息人的id',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '接收消息人的id',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '消息状态，1已读，0未读',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除 1删除，0为删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消息通知表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` int(10) NOT NULL COMMENT '主键',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面/轮播',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接到别的物品内容',
  `type` int(10) NULL DEFAULT NULL COMMENT '类型',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态',
  `is_delete` int(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发布日期',
  `create_user` int(10) NULL DEFAULT NULL COMMENT '发布人id',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `update_user` int(10) NULL DEFAULT NULL COMMENT '更新人id',
  `views` int(10) NULL DEFAULT NULL COMMENT '浏览量',
  `agree` int(10) NULL DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `sort` int(4) NULL DEFAULT NULL COMMENT '角色排序',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '角色状态 1：禁用，0：可用',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除 1：是，0：否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(10) NOT NULL COMMENT '角色id',
  `menu_id` int(10) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色与菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_sensitive_word
-- ----------------------------
DROP TABLE IF EXISTS `sys_sensitive_word`;
CREATE TABLE `sys_sensitive_word`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `sensitive_type` int(10) NULL DEFAULT NULL COMMENT '敏感词类型',
  `sensitive_words` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '敏感词',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：1 启用、0 禁用',
  `create_user` int(10) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` int(10) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `sensitiveTopic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '敏感话题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `user_type` tinyint(1) NULL DEFAULT NULL COMMENT '用户类型：1：表示管理员，0：表示普通用户',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '用户状态：1：表示正常，0：表示停用',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除 1：表示删除，0：表示为删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `sex` int(1) NULL DEFAULT NULL COMMENT '用户性别 0： 女 1：男',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info`  (
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `school` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学校',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'qq',
  `weixin` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信',
  `dormitory` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '宿舍地址',
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '介绍',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `last_login` datetime(0) NULL DEFAULT NULL COMMENT ' 最后一次登录时间',
  `last_reset` datetime(0) NULL DEFAULT NULL COMMENT ' 最后一次重置密码时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `real_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `role_id` int(10) NOT NULL COMMENT '角色id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户和角色关系表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
