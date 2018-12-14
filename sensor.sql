/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50521
Source Host           : 127.0.0.1:3306
Source Database       : sensor

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2016-08-20 21:38:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin_dic`
-- ----------------------------
DROP TABLE IF EXISTS `admin_dic`;
CREATE TABLE `admin_dic` (
  `DIC_ID` varchar(32) NOT NULL,
  `DIC_NAME` varchar(100) NOT NULL,
  `REMARK` varchar(500) NOT NULL,
  `DIC_CODE` varchar(32) NOT NULL,
  `DIC_TYPE_CODE` varchar(32) NOT NULL,
  `ATTR` varchar(200) DEFAULT NULL,
  `ORDER_NUM` int(11) DEFAULT NULL,
  `CREATE_USER` varchar(32) DEFAULT NULL,
  `CREATE_DATETIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(32) DEFAULT NULL,
  `UPDATE_DATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`DIC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin_dic
-- ----------------------------
INSERT INTO `admin_dic` VALUES ('20000', '正式员工', '', 'zsg', 'USER_TYPE', null, null, null, null, null, null);
INSERT INTO `admin_dic` VALUES ('20001', '临时员工', '', 'lsg', 'USER_TYPE', null, null, null, null, null, null);
INSERT INTO `admin_dic` VALUES ('20002', '外派员工', '', 'wpg', 'USER_TYPE', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `ID` varchar(32) CHARACTER SET utf8 NOT NULL,
  `MENU_NAME` varchar(100) CHARACTER SET gbk NOT NULL,
  `MENU_CODE` varchar(50) CHARACTER SET gbk DEFAULT NULL,
  `MENU_URL` varchar(512) CHARACTER SET gbk NOT NULL,
  `MENU_DESC` varchar(256) CHARACTER SET gbk DEFAULT NULL,
  `PARENT_ID` varchar(32) CHARACTER SET gbk NOT NULL,
  `ORDER_ID` varchar(11) CHARACTER SET gbk DEFAULT NULL,
  `RESOURCE_TYPE` varchar(20) CHARACTER SET gbk DEFAULT 'menu' COMMENT '1、menu 2、function',
  `IS_ACTIVE` char(1) CHARACTER SET gbk DEFAULT NULL,
  `IMAGE_URL` varchar(255) CHARACTER SET gbk DEFAULT NULL,
  `LABLE_COLOR` varchar(32) CHARACTER SET gbk DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `INX_ORDER_ID` (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '产品管理', 'cpgl', '', '产品管理', '0', '1000', 'menu', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('10', '角色新增', 'role:save', 'sys/role/saveSysRolePoAct.action', '角色新增功能', '7', '3003', 'function', 'Y', '', '');
INSERT INTO `sys_menu` VALUES ('11', '角色修改', 'role:update', 'sys/role/updateSysRolePoAct.action', '角色修改功能', '7', '3004', 'function', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('12', '用户管理', 'yhgl', '/pages/sys/user/userManage.jsp', '用户管理', '3', '4002', 'menu', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('13', '用户查询功能', 'user:list', '', '', '12', '4000', 'function', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('14', '用户删除功能', 'user:delete', '', '', '12', '4003', 'function', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('15', '用户新增功能', 'user:save', '', '', '12', '4004', 'function', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('16', '工作组管理', 'jggl', '/sys/organization/main.do', '工作组管理', '3', '7003', 'menu', 'N', null, null);
INSERT INTO `sys_menu` VALUES ('17', '参数管理', 'csgl', '/sys/param/updateSysParamPage.action', '参数管理', '3', '4005', 'menu', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('18', '产品数据', 'cpsj', '/pages/product/productDataList.jsp', '产品数据', '1', '1002', 'menu', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('19', '产品数据删除功能', 'productdata:delete', '', '产品数据删除功能', '18', '1003', 'function', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('2', '添加产品', 'tjcp', '/pages/product/addProduct.jsp', '添加产品', '1', '1001', 'menu', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('3', '系统管理', 'xtgl', '', '系统管理', '0', '1002', 'menu', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('4', '菜单管理', 'cdgl', '/sys/menu/main.do', '菜单管理', '3', '2000', 'menu', 'N', null, null);
INSERT INTO `sys_menu` VALUES ('5', '菜单删除功能', 'menu:delete', '', '', '4', '2001', 'function', 'N', null, null);
INSERT INTO `sys_menu` VALUES ('6', '菜单新增功能', 'menu:save', '', '', '4', '2002', 'function', 'N', null, null);
INSERT INTO `sys_menu` VALUES ('7', '角色管理', 'jsgl', '/sys/role/sysRoleView.action', '角色管理', '3', '3000', 'menu', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('8', '角色删除', 'role:delete', 'sys/role/deleteSysRolePoByRoleIdAct.action', '角色删除', '7', '3001', 'function', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('9', '角色授权', 'role:authorization', 'sys/role/saveAuthorizationAct.action', '角色授权', '7', '3002', 'function', 'Y', null, null);

-- ----------------------------
-- Table structure for `sys_menu_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role` (
  `MENU_ID` varchar(32) NOT NULL,
  `ROLE_ID` varchar(32) NOT NULL,
  PRIMARY KEY (`MENU_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES ('1', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('1', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_menu_role` VALUES ('10', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('10', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_menu_role` VALUES ('11', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('11', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_menu_role` VALUES ('12', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('12', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_menu_role` VALUES ('13', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('13', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_menu_role` VALUES ('14', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('14', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_menu_role` VALUES ('15', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('15', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_menu_role` VALUES ('17', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('17', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_menu_role` VALUES ('18', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('18', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_menu_role` VALUES ('19', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('2', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('2', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_menu_role` VALUES ('3', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('3', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_menu_role` VALUES ('7', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('7', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_menu_role` VALUES ('8', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('8', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_menu_role` VALUES ('9', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_menu_role` VALUES ('9', 'D58E22207C4B46C280CB26DFAC0D712A');

-- ----------------------------
-- Table structure for `sys_organization`
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `ORG_ID` varchar(32) CHARACTER SET utf8 NOT NULL,
  `ORG_NAME` varchar(60) CHARACTER SET utf8 DEFAULT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `PARENT_ID` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `CREATE_DATETIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `UPDATE_DATETIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `IS_ACTIVE` char(1) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`ORG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('DC668074AB9F4C848F2530895E77FEB0', '温湿度管理', '', '', '2014-10-28 00:00:00', null, '2016-06-21 16:29:15', null, 'Y');

-- ----------------------------
-- Table structure for `sys_param`
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `id` int(32) NOT NULL,
  `comm_interval_time` varchar(32) DEFAULT NULL COMMENT '指令间隔时间',
  `tem_allow_offset` double(10,2) DEFAULT NULL COMMENT '温度允许偏差',
  `hum_allow_offset` double(10,2) DEFAULT NULL COMMENT '湿度允许偏差',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_param
-- ----------------------------
INSERT INTO `sys_param` VALUES ('1', '500', '3.00', '22.33');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(32) NOT NULL,
  `ROLE_NAME` varchar(60) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `CREATE_DATETIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(40) DEFAULT NULL,
  `UPDATE_DATETIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(40) DEFAULT NULL,
  `IS_ACTIVE` char(1) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('AD5005D16B1E4201A0CC7BFBC6DB1094', '超级管理员', '超级管理员', '2016-06-21 16:23:49', '1', '2016-06-21 18:00:04', '1', 'Y');
INSERT INTO `sys_role` VALUES ('D58E22207C4B46C280CB26DFAC0D712A', '二级管理员', '二级管理员', '2016-07-16 22:03:51', 'admin', null, null, 'Y');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` varchar(32) CHARACTER SET utf8 NOT NULL,
  `ORG_ID` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `FIRST_NAME` varchar(60) CHARACTER SET utf8 DEFAULT NULL,
  `LAST_NAME` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `LOGIN_NAME` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `LOGIN_PASSWORD` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `EMAIL` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `PHONE` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `IP_ADDRESS` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `LAST_LOGIN` datetime DEFAULT NULL,
  `USER_TYPE` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `CREATE_DATETIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `UPDATE_DATETIME` datetime DEFAULT NULL,
  `UPDATE_USER` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `IS_ACTIVE` char(1) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'DC668074AB9F4C848F2530895E77FEB0', 'maker', 'jackson', 'admin', '0192023A7BBD73250516F069DF18B500', 'jackson@git.com.cn', '15911011011', '128.0.0.0', null, null, '2014-10-29 00:00:00', '超级管理员', '2016-07-16 21:38:14', null, 'Y');
INSERT INTO `sys_user` VALUES ('CF12262F333C4E2F9FDB34B266794BB2', 'DC668074AB9F4C848F2530895E77FEB0', '测', '试', 'test', '098F6BCD4621D373CADE4E832627B4F6', '', '', '', null, null, '2016-07-16 22:10:36', null, null, null, 'Y');
INSERT INTO `sys_user` VALUES ('D85735822C5C4C6E844C8496CA335752', 'DC668074AB9F4C848F2530895E77FEB0', '王', '军', 'wangjun', '1CD985F202645678FF1CE16BC14BCB9E', '', '', '', null, null, '2016-07-16 21:35:17', null, '2016-07-16 22:11:13', null, 'Y');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `USER_ID` varchar(32) CHARACTER SET utf8 NOT NULL,
  `ROLE_ID` varchar(32) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', 'AD5005D16B1E4201A0CC7BFBC6DB1094');
INSERT INTO `sys_user_role` VALUES ('CF12262F333C4E2F9FDB34B266794BB2', 'D58E22207C4B46C280CB26DFAC0D712A');
INSERT INTO `sys_user_role` VALUES ('D85735822C5C4C6E844C8496CA335752', 'D58E22207C4B46C280CB26DFAC0D712A');

-- ----------------------------
-- Table structure for `tb_bar_codes`
-- ----------------------------
DROP TABLE IF EXISTS `tb_bar_codes`;
CREATE TABLE `tb_bar_codes` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `product_id` int(32) NOT NULL,
  `address_code` varchar(32) NOT NULL,
  `bar_code` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_bar_codes
-- ----------------------------
INSERT INTO `tb_bar_codes` VALUES ('1', '1', '001', '2');
INSERT INTO `tb_bar_codes` VALUES ('2', '1', '002', '2');
INSERT INTO `tb_bar_codes` VALUES ('3', '2', '001', '2');
INSERT INTO `tb_bar_codes` VALUES ('4', '2', '002', '2');
INSERT INTO `tb_bar_codes` VALUES ('5', '3', '001', '2');
INSERT INTO `tb_bar_codes` VALUES ('6', '3', '002', '2');
INSERT INTO `tb_bar_codes` VALUES ('7', '4', '001', '2');
INSERT INTO `tb_bar_codes` VALUES ('8', '4', '002', '2');
INSERT INTO `tb_bar_codes` VALUES ('9', '5', '001', '1');
INSERT INTO `tb_bar_codes` VALUES ('10', '5', '002', '22');
INSERT INTO `tb_bar_codes` VALUES ('11', '6', '001', '1');
INSERT INTO `tb_bar_codes` VALUES ('12', '7', '001', '333');

-- ----------------------------
-- Table structure for `tb_product`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `p_date` varchar(32) NOT NULL,
  `p_number` varchar(32) NOT NULL,
  `p_batch` varchar(32) NOT NULL,
  `temperature_std` double(10,2) DEFAULT NULL,
  `humidity_std` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES ('1', '2016-08-02', '2', '1', null, null);
INSERT INTO `tb_product` VALUES ('2', '2016-08-02', '2', '1', null, null);
INSERT INTO `tb_product` VALUES ('3', '2016-08-02', '2', '1', '1.22', '22.00');
INSERT INTO `tb_product` VALUES ('4', '2016-08-02', '2', '1', '2.20', '3.00');
INSERT INTO `tb_product` VALUES ('5', '2016-08-02', '2', '1', '1.00', '1.00');
INSERT INTO `tb_product` VALUES ('6', '2016-08-09', '1', '1', '2.00', '2.00');
INSERT INTO `tb_product` VALUES ('7', '2016-08-02', '1', '1', '3.00', '3.00');

-- ----------------------------
-- Table structure for `tb_product_data`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_data`;
CREATE TABLE `tb_product_data` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `product_id` int(32) NOT NULL,
  `p_date` varchar(32) NOT NULL,
  `p_batch` varchar(32) NOT NULL,
  `address_code` varchar(32) NOT NULL,
  `bar_code` varchar(32) NOT NULL,
  `temperature_std` double(10,2) DEFAULT NULL,
  `temperature_data` double(10,2) DEFAULT NULL,
  `temperature_offset` double(10,2) DEFAULT NULL,
  `humidity_std` double(10,2) DEFAULT NULL,
  `humidity_data` double(10,2) DEFAULT NULL,
  `humidity_offset` double(10,2) DEFAULT NULL,
  `is_over_tem_allow_offset` varchar(32) DEFAULT NULL,
  `is_over_hem_allow_offset` varchar(32) DEFAULT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0:未删除、1:已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_product_data
-- ----------------------------
INSERT INTO `tb_product_data` VALUES ('1', '0', '2016-08-02', '1', '001', '1', '1.00', '0.00', '0.00', '1.00', '0.00', '0.00', '', '1', '1');
INSERT INTO `tb_product_data` VALUES ('2', '0', '2016-08-02', '1', '002', '22', '1.00', '0.00', '0.00', '1.00', '0.00', '0.00', '', '1', '1');
INSERT INTO `tb_product_data` VALUES ('3', '0', '2016-08-09', '1', '001', '1', '2.00', '0.00', '0.00', '2.00', '0.00', '0.00', '', '0', '0');
INSERT INTO `tb_product_data` VALUES ('4', '0', '2016-08-02', '1', '001', '333', '3.00', '0.00', '0.00', '3.00', '0.00', '0.00', '', '0', '0');
