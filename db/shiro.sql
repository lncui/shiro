-- ----------------------------
-- 创建资源表
-- ----------------------------
CREATE TABLE `sys_resources` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) unsigned DEFAULT '0',
  `sort` int(10) unsigned DEFAULT NULL,
  `external` tinyint(1) unsigned DEFAULT NULL COMMENT '是否外部链接',
  `available` tinyint(1) unsigned DEFAULT '0',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sys_resource_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统资源信息表';


-- ----------------------------
-- 创建角色表
-- ----------------------------
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '角色名',
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统角色信息表';


-- ----------------------------
-- 创建角色资源表
-- ----------------------------
CREATE TABLE `sys_role_resources` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) unsigned NOT NULL,
  `resources_id` bigint(20) unsigned NOT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统角色_关联资源信息表';


-- ----------------------------
-- 创建用户表
-- ----------------------------
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `nickname` varchar(30) DEFAULT '' COMMENT '昵称',
  `mobile` varchar(30) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `gender` smallint(2) DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `user_type` enum('ROOT','ADMIN','USER') DEFAULT 'ADMIN' COMMENT '超级管理员、管理员、普通用户',
  `company` varchar(100) DEFAULT NULL COMMENT '公司',
  `location` varchar(255) DEFAULT NULL COMMENT '地址',
  `reg_ip` varchar(30) DEFAULT NULL COMMENT '注册IP',
  `last_login_ip` varchar(30) DEFAULT NULL COMMENT '最近登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `login_count` int(10) unsigned DEFAULT '0' COMMENT '登录次数',
  `remark` varchar(100) DEFAULT NULL COMMENT '用户备注',
  `status` int(1) unsigned DEFAULT NULL COMMENT '用户状态',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户信息表';


-- ----------------------------
-- 创建用户角色表
-- ----------------------------
CREATE TABLE `sys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统用户_角色关联信息表';




# 清空系统资源表
TRUNCATE TABLE `sys_resources`;
# 初始化系统资源
INSERT INTO `sys_resources` VALUES (1, '用户管理', 'menu', '', '', 0, 4, 0, 1, 'fa fa-users', now(), now());
INSERT INTO `sys_resources` VALUES (2, '用户列表', 'menu', '/users', 'users', 1, 1, 0, 1, NULL, now(), now());
INSERT INTO `sys_resources` VALUES (3, '新增用户', 'button', NULL, 'user:add', 2, 2, 0, 1, NULL, now(), now());
INSERT INTO `sys_resources` VALUES (4, '批量删除用户', 'button', NULL, 'user:batchDelete', 2, 3, 0, 1, NULL, now(), now());
INSERT INTO `sys_resources` VALUES (5, '编辑用户', 'button', NULL, 'user:edit,user:get', 2, 4, 0, 1, NULL, now(), now());
INSERT INTO `sys_resources` VALUES (6, '删除用户', 'button', NULL, 'user:delete', 2, 5, 0, 1, NULL, now(), now());
INSERT INTO `sys_resources` VALUES (7, '分配用户角色', 'button', NULL, 'user:allotRole', 2, 6, 0, 1, NULL, now(), now());

INSERT INTO `sys_resources` VALUES (8, '权限管理', 'menu', '', '', 0, 3, 0, 1, 'fa fa-cogs', now(), now());
INSERT INTO `sys_resources` VALUES (9, '资源管理', 'menu', '/resources', 'resources', 8, 1, 0, 1, NULL, now(), now());
INSERT INTO `sys_resources` VALUES (10, '新增资源', 'button', NULL, 'resource:add', 9, 2, 0, 1, NULL, now(), now());
INSERT INTO `sys_resources` VALUES (11, '批量删除资源', 'button', NULL, 'resource:batchDelete', 9, 3, 0, 1, NULL, now(), now());
INSERT INTO `sys_resources` VALUES (12, '编辑资源', 'button', NULL, 'resource:edit,resource:get', 9, 4, 0, 1, NULL, now(), now());
INSERT INTO `sys_resources` VALUES (13, '删除资源', 'button', NULL, 'resource:delete', 9, 5, 0, 1, NULL, now(), now());

INSERT INTO `sys_resources` VALUES (14, '角色管理', 'menu', '/roles', 'roles', 8, 2, 0, 1, '', now(), now());
INSERT INTO `sys_resources` VALUES (15, '新增角色', 'button', NULL, 'role:add', 14, 2, 0, 1, NULL, now(), now());
INSERT INTO `sys_resources` VALUES (16, '批量删除角色', 'button', NULL, 'role:batchDelete', 14, 3, 0, 1, NULL, now(), now());
INSERT INTO `sys_resources` VALUES (17, '编辑角色', 'button', NULL, 'role:edit,role:get', 14, 4, 0, 1, NULL, now(), now());
INSERT INTO `sys_resources` VALUES (18, '删除角色', 'button', NULL, 'role:delete', 14, 5, 0, 1, NULL, now(), now());
INSERT INTO `sys_resources` VALUES (19, '分配角色资源', 'button', NULL, 'role:allotResource', 14, 6, 0, 1, NULL, now(), now());



# 清空系统角色
TRUNCATE TABLE `sys_role`;
# 初始化系统角色
INSERT INTO `sys_role` VALUES ('1', 'role:root', '超级管理员', '1', now(), now());
INSERT INTO `sys_role` VALUES ('2', 'role:admin', '管理员', '1', now(), now());
INSERT INTO `sys_role` VALUES ('3', 'role:comment', '评论审核管理员', '1', now(), now());

# 清空角色->资源对应内容
TRUNCATE TABLE `sys_role_resources`;
# 初始化角色->资源对应内容
INSERT INTO `sys_role_resources` VALUES ('1', '1', '1', now(), now());
INSERT INTO `sys_role_resources` VALUES ('2', '1', '2', now(), now());
INSERT INTO `sys_role_resources` VALUES ('3', '1', '3', now(), now());
INSERT INTO `sys_role_resources` VALUES ('4', '1', '4', now(), now());
INSERT INTO `sys_role_resources` VALUES ('5', '1', '5', now(), now());
INSERT INTO `sys_role_resources` VALUES ('6', '1', '6', now(), now());
INSERT INTO `sys_role_resources` VALUES ('7', '1', '7', now(), now());
INSERT INTO `sys_role_resources` VALUES ('8', '1', '8', now(), now());
INSERT INTO `sys_role_resources` VALUES ('9', '1', '9', now(), now());
INSERT INTO `sys_role_resources` VALUES ('10', '1', '10', now(), now());
INSERT INTO `sys_role_resources` VALUES ('11', '1', '11', now(), now());
INSERT INTO `sys_role_resources` VALUES ('12', '1', '12', now(), now());
INSERT INTO `sys_role_resources` VALUES ('13', '1', '13', now(), now());
INSERT INTO `sys_role_resources` VALUES ('14', '1', '14', now(), now());
INSERT INTO `sys_role_resources` VALUES ('15', '1', '15', now(), now());
INSERT INTO `sys_role_resources` VALUES ('16', '1', '16', now(), now());
INSERT INTO `sys_role_resources` VALUES ('17', '1', '17', now(), now());
INSERT INTO `sys_role_resources` VALUES ('18', '1', '18', now(), now());
INSERT INTO `sys_role_resources` VALUES ('19', '1', '19', now(), now());


# 清空系统用户表
TRUNCATE TABLE `sys_user`;
# 初始化系统用户
INSERT INTO `sys_user` VALUES ('1', 'root', 'CGUx1FN++xS+4wNDFeN6DA==', '超级管理员', '15151551516', '843977358@qq.com', '843977358', null, null, 'https://static.zhyd.me/static/img/favicon.ico', 'ROOT', null, null, null, '0:0:0:0:0:0:0:1', '2019-06-01 11:59:23', '254', null, '1', '2019-10-21 14:15:07', '2019-10-21 14:15:07');
INSERT INTO `sys_user` VALUES ('2', 'admin', 'gXp2EbyZ+sB/A6QUMhiUJQ==', '管理员', '15151551516', '843977358@qq.com', '843977358', null, null, null, 'ADMIN', null, null, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '2019-10-21 14:15:07', '2', null, '1', '2019-10-21 14:15:07', '2019-10-21 14:15:07');
INSERT INTO `sys_user` VALUES ('3', 'comment-admin', 'x9qCx3yP05yWfIE5wXbCsg==', '评论审核管理员', '', '', '', null, null, null, 'ADMIN', null, null, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '2019-10-21 14:15:07', '1', null, '1', '2019-10-21 14:15:07', '2019-10-21 14:15:07');

# 清空用户角色关联数据
TRUNCATE TABLE `sys_user_role`;
# 初始化用户角色关联数据
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', now(), now());
INSERT INTO `sys_user_role` VALUES ('2', '2', '2', now(), now());
INSERT INTO `sys_user_role` VALUES ('3', '3', '3', now(), now());
