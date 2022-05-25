/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.25 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ry`;

/*Table structure for table `spc_coach` */

DROP TABLE IF EXISTS `spc_coach`;

CREATE TABLE `spc_coach` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系方式',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Table structure for table `spc_coach_gym` */

DROP TABLE IF EXISTS `spc_coach_gym`;

CREATE TABLE `spc_coach_gym` (
  `id` bigint NOT NULL COMMENT '主键',
  `coach_id` bigint DEFAULT NULL COMMENT '教练编号',
  `gym_id` bigint DEFAULT NULL COMMENT '场地编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `coach_id` (`coach_id`) USING BTREE,
  KEY `gym_id` (`gym_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Table structure for table `spc_free_course` */

DROP TABLE IF EXISTS `spc_free_course`;

CREATE TABLE `spc_free_course` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程名称',
  `class_period` int DEFAULT NULL COMMENT '课时',
  `level` int DEFAULT '0' COMMENT '级别',
  `goal` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '目标',
  `calorie` int DEFAULT NULL COMMENT '卡路里',
  `sport_type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '相关运动（健身、跑步。。。）',
  `descript` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程介绍',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='公开课表';

/*Table structure for table `spc_free_course_video` */

DROP TABLE IF EXISTS `spc_free_course_video`;

CREATE TABLE `spc_free_course_video` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(16) DEFAULT NULL COMMENT '动作名称',
  `duration` int NOT NULL COMMENT '训练时间',
  `video_url` varchar(128) DEFAULT NULL COMMENT '视频路径',
  `status` char(1) DEFAULT NULL COMMENT '学习状态（完成、未完成）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='公开课视频列表';

/*Table structure for table `spc_gym` */

DROP TABLE IF EXISTS `spc_gym`;

CREATE TABLE `spc_gym` (
  `id` bigint NOT NULL COMMENT '主键',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '场地名称',
  `longitude` double(16,6) NOT NULL DEFAULT '0.000000' COMMENT '经度',
  `latitude` double(16,6) NOT NULL DEFAULT '0.000000' COMMENT '纬度',
  `city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在城市',
  `province` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在省份',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Table structure for table `spc_home_adsense_free_course` */

DROP TABLE IF EXISTS `spc_home_adsense_free_course`;

CREATE TABLE `spc_home_adsense_free_course` (
  `id` bigint NOT NULL COMMENT '主键',
  `course_id` bigint DEFAULT NULL COMMENT '公开课程编号',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='公开课广告位表';

/*Table structure for table `spc_home_adsense_vip_course` */

DROP TABLE IF EXISTS `spc_home_adsense_vip_course`;

CREATE TABLE `spc_home_adsense_vip_course` (
  `id` bigint NOT NULL COMMENT '主键',
  `course_id` bigint DEFAULT NULL COMMENT '冲刺课程编号',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='冲刺课广告位表';

/*Table structure for table `spc_home_banner` */

DROP TABLE IF EXISTS `spc_home_banner`;

CREATE TABLE `spc_home_banner` (
  `id` bigint NOT NULL COMMENT '主键',
  `img_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片地址',
  `order` int NOT NULL COMMENT '排序值',
  `redirect` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '跳转地址',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '当前状态',
  `from_time` datetime DEFAULT NULL COMMENT '开始时间',
  `to_time` datetime DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='轮播图';

/*Table structure for table `spc_home_icon` */

DROP TABLE IF EXISTS `spc_home_icon`;

CREATE TABLE `spc_home_icon` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能名称',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能图标',
  `redirect` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '跳转地址',
  `sort` int DEFAULT NULL COMMENT '功能排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='icon功能表';

/*Table structure for table `spc_member` */

DROP TABLE IF EXISTS `spc_member`;

CREATE TABLE `spc_member` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会员姓名',
  `birth_day` date DEFAULT NULL COMMENT '会员生日',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别-- 1：男 2：女',
  `height` int DEFAULT NULL COMMENT '当前身高（cm）',
  `weight` int DEFAULT NULL COMMENT '当前体重（kg）',
  `nick_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '住址',
  `city` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所在城市',
  `province` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所在省份',
  `avater` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '删除标识',
  `sport_pref` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '运动偏好（字典数组）',
  `goal` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '目标偏好',
  `level` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '强度偏好',
  `score` int DEFAULT '0' COMMENT '运动细胞',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='会员表';

/*Table structure for table `spc_member_appointment` */

DROP TABLE IF EXISTS `spc_member_appointment`;

CREATE TABLE `spc_member_appointment` (
  `id` bigint NOT NULL COMMENT '主键',
  `member_id` bigint NOT NULL COMMENT '会员主键',
  `appointment_time` datetime NOT NULL COMMENT '预约时间',
  `gym_id` bigint NOT NULL COMMENT '场地（健身房）ID',
  `coach_id` bigint NOT NULL COMMENT '教练主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='客户预约表';

/*Table structure for table `spc_member_height_history` */

DROP TABLE IF EXISTS `spc_member_height_history`;

CREATE TABLE `spc_member_height_history` (
  `id` bigint NOT NULL COMMENT '主键',
  `member_id` bigint NOT NULL COMMENT '会员编号',
  `height` float NOT NULL COMMENT '身高',
  `record_date` date DEFAULT NULL COMMENT '测量日期',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='会员身高变化表';

/*Table structure for table `spc_member_mark` */

DROP TABLE IF EXISTS `spc_member_mark`;

CREATE TABLE `spc_member_mark` (
  `id` bigint NOT NULL COMMENT '主键',
  `member_id` bigint DEFAULT NULL COMMENT '会员编号',
  `course_id` bigint DEFAULT NULL COMMENT '课程编号',
  `mark_date` datetime DEFAULT NULL COMMENT '收藏时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `member_id` (`member_id`) USING BTREE,
  KEY `course_id` (`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='会员收藏记录表';

/*Table structure for table `spc_member_training_plan` */

DROP TABLE IF EXISTS `spc_member_training_plan`;

CREATE TABLE `spc_member_training_plan` (
  `id` bigint NOT NULL COMMENT '主键',
  `member_id` bigint DEFAULT NULL COMMENT '会员主键',
  `date` date NOT NULL COMMENT '日期',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='训练计划表';

/*Table structure for table `spc_member_training_plan_course` */

DROP TABLE IF EXISTS `spc_member_training_plan_course`;

CREATE TABLE `spc_member_training_plan_course` (
  `id` bigint NOT NULL COMMENT '主键',
  `plan_id` bigint NOT NULL COMMENT '计划主键',
  `course_id` bigint DEFAULT NULL COMMENT '课程主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `plan_id` (`plan_id`) USING BTREE,
  KEY `course_id` (`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='训练计划课程表';

/*Table structure for table `spc_member_training_record` */

DROP TABLE IF EXISTS `spc_member_training_record`;

CREATE TABLE `spc_member_training_record` (
  `id` bigint NOT NULL COMMENT '主键',
  `member_id` bigint NOT NULL COMMENT '会员编号',
  `course_id` bigint NOT NULL COMMENT '课程编号',
  `training_time` datetime DEFAULT NULL COMMENT '训练时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='会员课程学习记录表';

/*Table structure for table `spc_member_weight_history` */

DROP TABLE IF EXISTS `spc_member_weight_history`;

CREATE TABLE `spc_member_weight_history` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `member_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会员编号',
  `weight` float DEFAULT NULL COMMENT '体重',
  `record_date` date DEFAULT NULL COMMENT '测量日期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='会员体重变化表';

/*Table structure for table `spc_order` */

DROP TABLE IF EXISTS `spc_order`;

CREATE TABLE `spc_order` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `member_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会员编号',
  `course_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程编号',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '订单状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='订单表';

/*Table structure for table `spc_training_plan` */

DROP TABLE IF EXISTS `spc_training_plan`;

CREATE TABLE `spc_training_plan` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '计划名称',
  `goal` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '计划目标',
  `difficulty` int DEFAULT NULL COMMENT '难度',
  `plan_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类别（有氧燃脂、气质提升）',
  `calorie` int DEFAULT NULL COMMENT '卡路里',
  `duration` int DEFAULT NULL COMMENT '训练周期（天）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标识',
  `min_weight` float(10,2) NOT NULL COMMENT '最低适配体重',
  `max_weight` float(10,2) DEFAULT NULL COMMENT '最高适配体重',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='系统推荐训练计划';

/*Table structure for table `spc_training_plan_course` */

DROP TABLE IF EXISTS `spc_training_plan_course`;

CREATE TABLE `spc_training_plan_course` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `plan_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '计划编号',
  `course_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程编号',
  `sort` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程顺序',
  `training_date` date NOT NULL COMMENT '训练日期',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `plan_id` (`plan_id`) USING BTREE,
  CONSTRAINT `spc_training_plan_course_ibfk_1` FOREIGN KEY (`plan_id`) REFERENCES `spc_training_plan` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='训练计划明细表';

/*Table structure for table `spc_vip_course` */

DROP TABLE IF EXISTS `spc_vip_course`;

CREATE TABLE `spc_vip_course` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程名称',
  `class_period` int DEFAULT NULL COMMENT '课时',
  `level` int DEFAULT '0' COMMENT '级别',
  `goal` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '目标',
  `calorie` int DEFAULT NULL COMMENT '卡路里',
  `price` decimal(10,2) NOT NULL COMMENT '课程价格',
  `sport_type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '相关运动（健身、跑步。。。）',
  `descript` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程介绍',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Table structure for table `spc_vip_course_lessions` */

DROP TABLE IF EXISTS `spc_vip_course_lessions`;

CREATE TABLE `spc_vip_course_lessions` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `week` int NOT NULL COMMENT '周次',
  `day` int NOT NULL COMMENT '第几天',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课时标题',
  `duration` int DEFAULT NULL COMMENT '持续时间（分钟）',
  `course_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属课程',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `course_id` (`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
