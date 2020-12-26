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

 Date: 26/12/2020 20:33:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Records of goods_message
-- ----------------------------
INSERT INTO `goods_message` VALUES (1, 0, 1, NULL, 9, 1, 0, '这是一把好砍刀', 0);
INSERT INTO `goods_message` VALUES (2, 0, 1, NULL, 9, 1, 0, '这是一把好砍刀', 0);
INSERT INTO `goods_message` VALUES (3, 0, 1, NULL, 9, 1, 0, '这是一把好砍刀', 0);
INSERT INTO `goods_message` VALUES (4, 0, 1, NULL, 9, 1, 0, '这是一把好砍刀', 0);
INSERT INTO `goods_message` VALUES (5, 0, 1, NULL, 9, 1, 0, '这是一把好砍刀', 0);
INSERT INTO `goods_message` VALUES (6, 0, 1, NULL, 9, 1, 0, '这是一把好砍刀', 0);
INSERT INTO `goods_message` VALUES (7, 0, 1, NULL, 9, 1, 0, '这是一把好砍刀', 0);

-- ----------------------------
-- Records of sys_cate
-- ----------------------------
INSERT INTO `sys_cate` VALUES (1, '物品分类', 0, 1, 0, '2020-12-08 23:44:11', 1);
INSERT INTO `sys_cate` VALUES (2, '标签分类', 0, 1, 0, '2020-12-08 23:44:15', 1);
INSERT INTO `sys_cate` VALUES (3, '卡', 1, 1, 0, '2020-12-17 23:44:18', 1);
INSERT INTO `sys_cate` VALUES (4, '手机', 1, 1, 0, '2020-12-24 23:44:21', 1);
INSERT INTO `sys_cate` VALUES (5, '自行车', 1, 1, 0, '2020-12-17 23:44:25', 1);
INSERT INTO `sys_cate` VALUES (6, '美丽', 2, 1, 0, '2020-12-24 23:44:29', 1);
INSERT INTO `sys_cate` VALUES (7, '沙雕', 2, 1, 0, '2020-12-23 23:44:34', 1);
INSERT INTO `sys_cate` VALUES (8, '女人', 2, 1, 0, '2020-12-08 23:44:38', 1);
INSERT INTO `sys_cate` VALUES (9, '山地自行车', 5, 1, 0, '2020-12-23 23:44:41', 1);
INSERT INTO `sys_cate` VALUES (10, '淑女自行车', 5, 1, 0, '2020-12-16 23:44:44', 1);
INSERT INTO `sys_cate` VALUES (11, '包', 1, 0, 0, '2020-12-22 23:39:30', 1);
INSERT INTO `sys_cate` VALUES (13, '课本', 1, 1, 0, '2020-12-22 23:53:33', 1);
INSERT INTO `sys_cate` VALUES (14, '我的猪猪女友', 0, 2, 0, '2020-12-23 00:08:40', 1);
INSERT INTO `sys_cate` VALUES (15, '小猪猪', 14, 2, 0, '2020-12-23 00:10:10', 1);

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (8, 1, '学生', 'student', 0, 3, '2020-11-26 00:49:32', NULL, 1, NULL);
INSERT INTO `sys_dict_data` VALUES (9, 1, '管理员', 'admin', 1, 3, '2020-11-26 00:49:54', NULL, 1, 1);
INSERT INTO `sys_dict_data` VALUES (10, 2, '男', '1', 0, 4, '2020-12-21 23:46:20', NULL, 1, NULL);
INSERT INTO `sys_dict_data` VALUES (11, 2, '启用', '0', 0, 6, '2020-12-23 00:15:17', NULL, 1, NULL);
INSERT INTO `sys_dict_data` VALUES (12, 1, '禁用', '1', 0, 6, '2020-12-23 00:15:40', NULL, 1, NULL);
INSERT INTO `sys_dict_data` VALUES (13, 0, '女', '0', 0, 4, '2020-12-25 21:57:46', NULL, 6, NULL);

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '物品分类', '物品', 1, 1, NULL, '2020-11-26 00:09:38', NULL, '备注');
INSERT INTO `sys_dict_type` VALUES (2, '敏感词分类', '敏感词', 1, 1, NULL, '2020-11-26 00:37:56', NULL, '敏感词顶层');
INSERT INTO `sys_dict_type` VALUES (3, '用户分类', '用户', 1, 1, NULL, '2020-11-26 00:38:36', NULL, '备注');
INSERT INTO `sys_dict_type` VALUES (4, '用户性别', 'user_sex', 0, 1, NULL, '2020-12-08 19:52:02', NULL, '1 男 0 女');
INSERT INTO `sys_dict_type` VALUES (6, '系统通用状态', 'sys_status', 1, 1, NULL, '2020-12-23 00:14:35', NULL, '系统通用状态');

-- ----------------------------
-- Records of sys_goods
-- ----------------------------
INSERT INTO `sys_goods` VALUES (9, '四十米砍刀', '被遗忘的砍刀', '<p><span style=\"color: rgb(51, 51, 51);\">的价格高低决定的因素取决于搬家企业的规模和服务质量,那么我们先来说服务质量,一般上海搬家公司的服务质量,从实际的服务上来说,就是说搬家公司一般能够给予搬运</span><em><span style=\"color: rgb(247, 49, 49);\">物品</span></em><span style=\"color: rgb(51, 51, 51);\">..</span></p>', '2020-12-13 00:24:00', '2020-12-03 00:00:00', 'http://localhost:9090/profile/upload/2020/12/13/2fd4eeda51ad529d2777cffe43b98fb6.jpg', 1, 3, NULL, NULL, 1, 0, b'0', '17878192811', '小四', 1, '桃园');
INSERT INTO `sys_goods` VALUES (10, '123', '捡到一个狗头', '<h1 style=\"text-align: center;\"><span style=\"color: #03a9f4;\">狗偷人</span></h1><p style=\"text-align: center;\"><img src=\"http://localhost:9090/profile/upload/2020/12/13/2ac4c9b5cb343b74b8ff57a3b02e3f8b.png\" alt=\"\" title=\"\" width=\"200\" data-display=\"inline\"></p>', '2020-12-13 08:57:00', '2020-12-13 00:00:00', 'http://localhost:9090/profile/upload/2020/12/13/86b7a76908a25eebd30e9fce4e530c55.png', 1, 5, NULL, NULL, 1, 0, b'0', '17878192811', '123', 1, '桃园');
INSERT INTO `sys_goods` VALUES (11, 'tt', 't', '', '2020-12-13 09:01:00', '2020-12-11 00:00:00', 'http://localhost:9090/profile/upload/2020/12/13/affb109420955f69611c351093ac8411.jpg', 1, 4, NULL, NULL, 1, 0, b'0', '17878192811', 't他', 2, '桃园');
INSERT INTO `sys_goods` VALUES (12, 'q', 'q（修改版）', '<p>我的没能</p>', '2020-12-13 15:06:00', '2020-12-01 00:00:00', 'http://localhost:9090/profile/upload/2020/12/16/16d474872a19bfd5766e19c3703084ea.jpg', 1, 5, NULL, NULL, 2, 0, b'0', '17878192811', 'q', 1, '厚德3');
INSERT INTO `sys_goods` VALUES (13, '刀刀', '我捡到了你的到了', '<p>我见到看你的单靠啥子神佛i回复楼上了</p>', '2020-12-15 21:36:00', '2020-12-11 00:00:00', 'http://localhost:9090/profile/upload/2020/12/15/f0047b93eaeb45910cb9b9a89e32f801.jpg', 1, 4, NULL, NULL, 2, 0, b'0', '17878192811', '小三', 2, '厕所');
INSERT INTO `sys_goods` VALUES (15, 'q', 'q', '<p>我的小美女，哈哈</p>', '2020-12-16 22:15:00', '2020-12-01 00:00:00', 'http://localhost:9090/profile/upload/2020/12/16/ade7d7691a7fcee775dff3ddb1a1b9e5.jpg', 1, 5, NULL, NULL, 2, 0, b'0', '17878192811', 'q', 1, '图书馆');
INSERT INTO `sys_goods` VALUES (17, '无名之物2', '我的小妹妹', '<p>不解释就是不见了，哈哈 2333</p>', '2020-12-24 22:45:00', '2020-12-23 00:00:00', 'http://localhost:9090/profile/upload/2020/12/24/e290955211abbd32bbd4f03d718b2000.jpg', 1, 11, NULL, NULL, 6, 0, b'0', '17878192811', '大大怪2', 2, '校门');
INSERT INTO `sys_goods` VALUES (18, '武平删除', '武平删除', '<h2 style=\"text-align: center;\">哈哈 ，我要删除该武平</h2><p style=\"text-align: center;\"><img src=\"http://localhost:9090/profile/upload/2020/12/24/6c97d28fab66d00684403c2882bdf58f.jpg\" alt=\"\" title=\"\" width=\"200\" data-display=\"inline\"></p>', '2020-12-24 23:52:24', '2020-12-09 00:00:00', 'http://localhost:9090/profile/upload/2020/12/24/dab1146897093aacea9eb99985b070b0.jpg', 1, 5, NULL, NULL, 6, 0, b'0', '17878192811', '武平删除', 2, '西门无情');
INSERT INTO `sys_goods` VALUES (19, '女盆友', '我的失物', '<p>你砍刀我的女盆又了吗</p>', '2020-12-26 14:35:48', '2020-12-15 00:00:00', 'http://localhost:9090/profile/upload/2020/12/26/84098a4deba30b5f2ac211dc44b3190c.png', 1, 4, NULL, NULL, 1, 0, b'0', '1453463456', '法外张三', 1, '大学');
INSERT INTO `sys_goods` VALUES (20, '俺男人', '你的男盆友', '<h1 style=\"text-align: center;\">重金悬赏</h1>', '2020-12-26 14:41:00', '2020-12-09 00:00:00', 'http://localhost:9090/profile/upload/2020/12/26/2bc42363d2c7adb66dbd5f72e0b3ca26.jpg', 1, 11, NULL, NULL, 1, 0, b'0', '456456533', '狮王里斯', 1, '小树林');
INSERT INTO `sys_goods` VALUES (22, '测试数据', '测试数据', '<p>测试数据</p>', '2020-12-26 18:21:45', '2020-12-02 00:00:00', 'http://localhost:9090/profile/upload/2020/12/26/843ad2e800561bd9132d376d25aa866e.png', 1, 5, NULL, NULL, 1, 0, b'0', '测试数据', '测试数据', 1, '测试数据');


-- ----------------------------
-- Records of sys_goods_image
-- ----------------------------
INSERT INTO `sys_goods_image` VALUES (1, 7, 'https://www.baidu.com');
INSERT INTO `sys_goods_image` VALUES (2, 8, 'https://www.baidu.com');


-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '主目录', 0, 1, '#', NULL, NULL, 1, NULL, '2020-12-12 23:27:12', NULL, 1, NULL, 0, 0);

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 1, 0, 0);
INSERT INTO `sys_role` VALUES (2, '用户', 'user', 2, 0, 0);
INSERT INTO `sys_role` VALUES (3, '游客', 'tourist', 1, 0, 0);

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$cOvP2HIxlYdCagCuqYqAdOlIKHKHpeIsdxCOgHHXDOCVx1Vg.BjY2', '老王', 1, 'http://localhost:9090/profile/upload/2020/12/26/8dedbd8a948668e5ceb85ccef2ad5d65.png', 1, 0, '2020-11-22 19:17:11', NULL, 1);
INSERT INTO `sys_user` VALUES (2, 'user', '$2a$10$cOvP2HIxlYdCagCuqYqAdOlIKHKHpeIsdxCOgHHXDOCVx1Vg.BjY2', '小王', 2, 'http://localhost:9090/profile/upload/2020/12/26/99a987f5cdc2ed71c2af5fcbe9c74452.png', 1, 0, '2020-12-01 22:06:10', NULL, 0);
INSERT INTO `sys_user` VALUES (6, 'sdz502', '$2a$10$PFA1I7zQyG7zpSWxCFALheufpLYgI9Ggx.zEjQ9TYTYQlWaGvDoYG', '小小的我', 2, 'http://localhost:9090/profile/upload/2020/12/26/182db8c4f9ea7495f4afd943f78813c7.png', 1, 0, '2020-12-24 00:19:38', NULL, 0);

-- ----------------------------
-- Records of sys_user_info
-- ----------------------------
INSERT INTO `sys_user_info` VALUES (1, NULL, '3253464565643@163.com', '2533642018', 'weixin2019', '桃园四栋', '我是一个好人，哈哈', '17878192811', '2020-12-26 17:40:13', NULL, NULL, '蒙德世');
INSERT INTO `sys_user_info` VALUES (2, NULL, '4365343432@163.com', '4653464534', 'weixin_louying', '兰园10栋', '我要做公务员，爷爷奶奶可高兴了，给我爱吃的喜之郎。', '17878192811', '2020-12-26 12:12:17', NULL, NULL, '罗银');
INSERT INTO `sys_user_info` VALUES (6, '玉林师范学院', '5205454@163.com', '2533642018', 'sdz_wei_xin', '兰园10栋605', '我是一个小可爱', '17878192811', '2020-12-26 18:01:29', NULL, NULL, '史德竹');

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (6, 2);

SET FOREIGN_KEY_CHECKS = 1;
