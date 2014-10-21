/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : dactiv

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2014-10-15 21:35:47
*/

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `fk_parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `name` varchar(100) NOT NULL COMMENT '区域名称',
  `type` char(1) DEFAULT NULL COMMENT '区域类型:1,国家;2,省,直辖市;3,地市',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `leaf` tinyint(1) DEFAULT NULL COMMENT '是否含有子资源;1,有;2,无',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_area_parent_id` (`fk_parent_id`),
  KEY `sys_area_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域表';

INSERT INTO `sys_area` VALUES ('402881e437d467d80136d46fc0e50001', '0', '01', '辽宁省', '2', 'sys', now(), '', null, '',  1, 0);
INSERT INTO `sys_area` VALUES ('402881e437d467d80136d46fc0e50002', '402881e437d467d80136d46fc0e50001', '10000', '大连市', '3', 'sys', now(), '', null, '',  0, 0);

-- ----------------------------
-- Table structure for sys_dictionary_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary_category`;
CREATE TABLE `sys_dictionary_category` (
  `id` varchar(32) NOT NULL COMMENT '唯一ID',
  `code` varchar(128) NOT NULL COMMENT '字段类别code',
  `name` varchar(256) NOT NULL COMMENT '字段类别名称',
  `remark` varchar(512) DEFAULT NULL COMMENT '字段类别描述',
  `fk_parent_id` varchar(32) DEFAULT NULL COMMENT '上级id',
  `leaf` tinyint(1) DEFAULT NULL COMMENT '是否含有子节点',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类别表';

-- ----------------------------
-- Records of sys_dictionary_category
-- ----------------------------
INSERT INTO `sys_dictionary_category` VALUES ('402881e437d467d80137d46fc0e50001', 'state', '状态', null, null, '0', 0);
INSERT INTO `sys_dictionary_category` VALUES ('402881e437d467d80137d4709b9c0002', 'resource-type', '资源类型', null, null, '0', 0);
INSERT INTO `sys_dictionary_category` VALUES ('402881e437d467d80137d4712ca70003', 'group-type', '组类型', null, null, '0', 0);
INSERT INTO `sys_dictionary_category` VALUES ('402881e437d47b250137d485274b0004', 'value-type', '值类型', null, null, '0', 0);
INSERT INTO `sys_dictionary_category` VALUES ('402881e437d47b250137d485274b0005', 'operating-state', '操作状态', null, null, '0', 0);
INSERT INTO `sys_dictionary_category` VALUES ('402881e437d47b250137d485274b0006', 'user-type', '用户类型', null, null, '0', 0);
INSERT INTO `sys_dictionary_category` VALUES ('402881e437d47b250137d485274b0007', 'user-level', '用户级别', null, null, '0', 0);
INSERT INTO `sys_dictionary_category` VALUES ('402881e437d47b250137d485274b0008', 'area-level', '区域等级', null, null, '0', 0);

-- ----------------------------
-- Table structure for sys_data_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_dictionary`;
CREATE TABLE `sys_data_dictionary` (
  `id` varchar(32) NOT NULL COMMENT '唯一ID',
  `name` varchar(256) NOT NULL COMMENT '字典名称',
  `remark` varchar(512) DEFAULT NULL COMMENT '字段描述',
  `type` varchar(1) NOT NULL COMMENT '字段类型',
  `value` varchar(32) NOT NULL COMMENT '与字段类型对应的值',
  `fk_category_id` varchar(32) NOT NULL COMMENT '与类别表对应关系',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_data_dictionary
-- ----------------------------
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d47b250137d481b6920001', '启用', null, 'I', '1', '402881e437d467d80137d46fc0e50001', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d47b250137d481dda30002', '禁用', null, 'I', '2', '402881e437d467d80137d46fc0e50001', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d47b250137d481f23a0003', '删除', null, 'I', '3', '402881e437d467d80137d46fc0e50001', 'sys', now(), null, null, 0);

INSERT INTO `sys_data_dictionary` VALUES ('402881e437d47b250137d4870b230005', 'String', null, 'S', 'S', '402881e437d47b250137d485274b0004', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d47b250137d487328e0006', 'Integer', null, 'S', 'I', '402881e437d47b250137d485274b0004', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d47b250137d487a3af0007', 'Long', null, 'S', 'L', '402881e437d47b250137d485274b0004', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d47b250137d487e23a0008', 'Double', null, 'S', 'N', '402881e437d47b250137d485274b0004', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d47b250137d488416d0009', 'Date', null, 'S', 'D', '402881e437d47b250137d485274b0004', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d47b250137d4885686000a', 'Boolean', null, 'S', 'B', '402881e437d47b250137d485274b0004', 'sys', now(), null, null, 0);

INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a5e8570003', '菜单类型', null, 'S', '01', '402881e437d467d80137d4709b9c0002', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a61cec0004', '资源类型', null, 'S', '02', '402881e437d467d80137d4709b9c0002', 'sys', now(), null, null, 0);

INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7783d0006', '外部机构', null, 'S', '01', '402881e437d467d80137d4712ca70003', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a6f1aa0005', '内部公司', null, 'S', '02', '402881e437d467d80137d4712ca70003', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0007', '权限组', null, 'S', '03', '402881e437d467d80137d4712ca70003', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0008', '课题组', null, 'S', '04', '402881e437d467d80137d4712ca70003', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a6f1aa0006', '内部部门', null, 'S', '05', '402881e437d467d80137d4712ca70003', 'sys', now(), null, null, 0);

INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7783d0008', '成功', null, 'I', '1', '402881e437d47b250137d485274b0005', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0009', '失败', null, 'I', '2', '402881e437d47b250137d485274b0005', 'sys', now(), null, null, 0);

INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0010', '外部客户', null, 'S', '01', '402881e437d47b250137d485274b0006', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0011', '内部职员', null, 'S', '02', '402881e437d47b250137d485274b0006', 'sys', now(), null, null, 0);

INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0012', '一级', '只针对外部客户', 'I', '1', '402881e437d47b250137d485274b0007', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0013', '二级', '只针对外部客户', 'I', '2', '402881e437d47b250137d485274b0007', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0014', '三级', '只针对外部客户', 'I', '3', '402881e437d47b250137d485274b0007', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0015', '四级', '只针对外部客户', 'I', '4', '402881e437d47b250137d485274b0007', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0016', '五级', '只针对外部客户', 'I', '5', '402881e437d47b250137d485274b0007', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0017', '六级', '只针对外部客户', 'I', '6', '402881e437d47b250137d485274b0007', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0018', '七级', '只针对外部客户', 'I', '7', '402881e437d47b250137d485274b0007', 'sys', now(), null, null, 0);

INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0019', '省,直辖市', null, 'S', '01', '402881e437d47b250137d485274b0008', 'sys', now(), null, null, 0);
INSERT INTO `sys_data_dictionary` VALUES ('402881e437d49e430137d4a7ba1a0020', '地市', null, 'S', '02', '402881e437d47b250137d485274b0008', 'sys', now(), null, null, 0);

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group` (
  `id` varchar(32) NOT NULL COMMENT '组ID',
  `area_code` varchar(32) NOT NULL COMMENT '组所属地市, 0为无地市;',
  `name` varchar(32) NOT NULL COMMENT '组名',
  `remark` varchar(512) DEFAULT NULL COMMENT '组名',
  `state` int(11) NOT NULL COMMENT '组状态: 1,有效;0,无效;',
  `type` varchar(2) NOT NULL COMMENT '组类型:对应 sys_dictionary_category.组类型; 01,外部机构;02,内部公司;03,权限组；04,课题组;05,内部部门',
  `fk_parent_id` varchar(32) DEFAULT NULL COMMENT '上级ID',
  `leaf` tinyint(1) DEFAULT NULL COMMENT '是否含有子集合:1,有;0,无',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组信息表';

-- ----------------------------
-- Records of sys_group
-- ----------------------------
INSERT INTO `sys_group` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0002', '0', '超级管理员', '超级管理员', '1', '03', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_group` VALUES ('402881c4408c7d2301408c86b7a80001', '0', '普通用户', '新注册用户-普通用户', '1', '03', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_group` VALUES ('402881c4408c7d2301408c86b7a80002', '0', '高校导师', '高校导师', '1', '03', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_group` VALUES ('402881c4408c7d2301408c870ed10002', '0', '高校PI', '高校PI', '1', '03', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_group` VALUES ('402881c4408c7d2301408c870ed10003', '0', '高校学生', '高校学生', '1', '03', null, '0', 'sys', now(), '', null, 0);

-- ----------------------------
-- Table structure for sys_group_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_resource`;
CREATE TABLE `sys_group_resource` (
  `fk_resource_id` varchar(32) NOT NULL COMMENT '资源ID',
  `fk_group_id` varchar(32) NOT NULL COMMENT '组ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组与资源对应信息表';

-- ----------------------------
-- Records of sys_group_resource
-- ----------------------------
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0003', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0004', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0005', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0006', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0007', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0008', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0009', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0010', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0011', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0012', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0013', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0014', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0015', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0016', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0017', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0018', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0019', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0020', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0021', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0022', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0023', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0024', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0025', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0026', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO `sys_group_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0027', 'SJDK3849CKMS3849DJCK2039ZMSK0002');

-- ----------------------------
-- Table structure for sys_group_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_user`;
CREATE TABLE `sys_group_user` (
  `fk_group_id` varchar(32) NOT NULL COMMENT '组ID',
  `fk_user_id` varchar(32) NOT NULL COMMENT '用户ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与组对应信息表';

-- ----------------------------
-- Records of sys_group_user
-- ----------------------------
INSERT INTO `sys_group_user` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0002', 'SJDK3849CKMS3849DJCK2039ZMSK0001');

-- ----------------------------
-- Table structure for sys_operating_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_operating_record`;
CREATE TABLE `sys_operating_record` (
  `id` varchar(32) NOT NULL COMMENT '编号',
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `fk_user_id` varchar(32) DEFAULT NULL COMMENT 'userId',
  `operating_target` varchar(512) NOT NULL COMMENT '操作路径',
  `start_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '操作时间',
  `username` varchar(32) DEFAULT NULL COMMENT '操作人',
  `function` varchar(128) DEFAULT NULL COMMENT '对应的操作方法',
  `ip` varchar(64) NOT NULL COMMENT 'ip地址',
  `method` varchar(256) NOT NULL COMMENT '对应的类中方法名',
  `module` varchar(128) DEFAULT NULL COMMENT '模块名',
  `remark` text COMMENT 'request参数',
  `state` int(11) NOT NULL COMMENT '1, 成功; 2, 失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户操作记录表';

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` varchar(32) NOT NULL COMMENT '编号',
  `permission` varchar(64) DEFAULT NULL COMMENT '许可',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `sort` bigint(11) NOT NULL COMMENT '排序',
  `name` varchar(32) NOT NULL COMMENT '资源名称',
  `type` varchar(2) NOT NULL COMMENT '资源类型:01,菜单类型; 02,安全类型',
  `value` varchar(256) DEFAULT NULL COMMENT 'URL',
  `fk_parent_id` varchar(32) DEFAULT NULL COMMENT '上级resourceID',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `leaf` tinyint(1) DEFAULT NULL COMMENT '是否含有子资源;1,有;2,无',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源信息表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0003', null, null, '1', '权限管理', '01', '#', null, 'glyphicon-briefcase', '1', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0004', 'perms[user:view]', null, '2', '用户管理', '01', '/account/user/view/**', 'SJDK3849CKMS3849DJCK2039ZMSK0003', 'glyphicon-user', '1', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0005', 'perms[user:create]', null, '3', '创建用户', '02', '/account/user/insert/**', 'SJDK3849CKMS3849DJCK2039ZMSK0004', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0006', 'perms[user:update]', null, '4', '修改用户', '02', '/account/user/update/**', 'SJDK3849CKMS3849DJCK2039ZMSK0004', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0007', 'perms[user:delete]', null, '5', '删除用户', '02', '/account/user/delete/**', 'SJDK3849CKMS3849DJCK2039ZMSK0004', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0008', 'perms[user:read]', null, '6', '查看用户', '02', '/account/user/read/**', 'SJDK3849CKMS3849DJCK2039ZMSK0004', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0009', 'perms[group:view]', null, '7', '组管理', '01', '/account/group/view/**', 'SJDK3849CKMS3849DJCK2039ZMSK0003', 'glyphicon-briefcase', '1', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0010', 'perms[resource:view]', null, '8', '资源管理', '01', '/account/resource/view/**', 'SJDK3849CKMS3849DJCK2039ZMSK0003', 'glyphicon-link', '1', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0011', 'perms[group:save]', null, '9', '创建和编辑组', '02', '/account/group/save/**', 'SJDK3849CKMS3849DJCK2039ZMSK0009', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0012', 'perms[group:read]', null, '10', '查看组', '02', '/account/group/read/**', 'SJDK3849CKMS3849DJCK2039ZMSK0009', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0013', 'perms[group:delete]', null, '11', '删除组', '02', '/account/group/delete/**', 'SJDK3849CKMS3849DJCK2039ZMSK0009', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0014', 'perms[resource:save]', null, '12', '创建和编辑资源', '02', '/account/resource/save/**', 'SJDK3849CKMS3849DJCK2039ZMSK0010', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0015', 'perms[resource:read]', null, '13', '查看资源', '02', '/account/resource/read/**', 'SJDK3849CKMS3849DJCK2039ZMSK0010', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0016', 'perms[resource:delete]', null, '14', '删除资源', '02', '/account/resource/delete/**', 'SJDK3849CKMS3849DJCK2039ZMSK0010', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0017', null, null, '15', '系统管理', '01', '#', null, 'glyphicon-cog', '1', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0018', 'perms[data-dictionary:view]', '', '16', '数据字典管理', '01', '/foundation/variable/data-dictionary/view/**', 'SJDK3849CKMS3849DJCK2039ZMSK0017', 'glyphicon-list-alt', '1', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0019', 'perms[dictionary-category:view]', null, '17', '字典类别管理', '01', '/foundation/variable/dictionary-category/view/**', 'SJDK3849CKMS3849DJCK2039ZMSK0017', 'glyphicon-folder-close', '1', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0020', 'perms[dictionary-category:save]', null, '18', '创建和编辑字典类别', '02', '/foundation/variable/dictionary-category/save/**', 'SJDK3849CKMS3849DJCK2039ZMSK0019', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0021', 'perms[dictionary-category:delete]', null, '19', '删除字典类别', '02', '/foundation/variable/dictionary-category/delete/**', 'SJDK3849CKMS3849DJCK2039ZMSK0019', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0022', 'perms[data-dictionary:save]', null, '20', '创建和编辑数据字典', '02', '/foundation/variable/data-dictionary/save/**', 'SJDK3849CKMS3849DJCK2039ZMSK0018', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0023', 'perms[data-dictionary:delete]', null, '21', '删除数据字典', '02', '/foundation/variable/data-dictionary/delete/**', 'SJDK3849CKMS3849DJCK2039ZMSK0018', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0024', 'perms[data-dictionary:read]', null, '22', '查看数据字典', '02', '/foundation/variable/data-dictionary/read/**', 'SJDK3849CKMS3849DJCK2039ZMSK0018', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0025', 'perms[dictionary-category:read]', '', '23', '查看字典类别', '02', '/foundation/variable/dictionary-category/read/**', 'SJDK3849CKMS3849DJCK2039ZMSK0019', null, '0', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0026', 'perms[operating-record:view]', null, '24', '操作记录管理', '01', '/foundation/audit/operating-record/view/**', 'SJDK3849CKMS3849DJCK2039ZMSK0017', 'glyphicon-eye-open', '1', 'sys', now(), '', null, 0);
INSERT INTO `sys_resource` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0027', 'perms[operating-record:read]', '', '25', '查看操作日志', '02', '/foundation/audit/operating-record/read/**', 'SJDK3849CKMS3849DJCK2039ZMSK0026', null, '0', 'sys', now(), '', null, 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT '编号',
  `area_code` varchar(32) NOT NULL COMMENT '区域编号',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT 'MD5加密后的密码',
  `user_code` varchar(32) DEFAULT '' COMMENT '内部员工号',
  `level` int(4) NOT NULL DEFAULT 1 COMMENT '等级',
  `type` int(4) NOT NULL DEFAULT 1 COMMENT '类型: 1,外部用户;2,内部用户',
  `state` int(11) NOT NULL DEFAULT 1 COMMENT '状态:0,禁用; 1,有效;',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `cell` varchar(32) DEFAULT NULL COMMENT '手机',
  `qq` varchar(32) DEFAULT NULL COMMENT 'QQ',
  `focus_on` varchar(64) DEFAULT NULL COMMENT '外部用户的研究方向',
  `portrait` varchar(256) DEFAULT NULL COMMENT '头像',
  `realname` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_external_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0001', '0', 'maurice', 'dd099c2f017f990e68f5c90a413abc5d', '1', 0, 2, 1, 'es.chenxiaobo@gmail.com', '138759320999', '19000001', null, null, 'maurice.chen', '', 'sys', now(), '', null, '', now(), 0);


