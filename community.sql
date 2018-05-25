/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 80011
 Source Host           : localhost
 Source Database       : community

 Target Server Version : 80011
 File Encoding         : utf-8

 Date: 05/25/2018 19:09:09 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `activity`
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `originator_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '发起人',
  `name` varchar(50) NOT NULL COMMENT '活动名称',
  `introduction` varchar(500) NOT NULL COMMENT '活动简介',
  `img` varchar(255) NOT NULL,
  `sign_up_way` int(11) NOT NULL DEFAULT '1' COMMENT '报名方式（1-不需要验证、2-需要验证、3-仅组织成员）',
  `sign_up_question` varchar(255) NOT NULL DEFAULT '' COMMENT '报名活动问题',
  `post_permission` int(11) NOT NULL DEFAULT '1' COMMENT '发帖权限（1-不限、2-仅管理员、3-管理员、班委、4-管理员、班委、普通成员）',
  `latitude` decimal(10,10) NOT NULL DEFAULT '0.0000000000' COMMENT '纬度',
  `longitude` decimal(10,10) NOT NULL DEFAULT '0.0000000000' COMMENT '经度',
  `start_time` datetime NOT NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT '活动结束时间',
  `sign_up_num` int(11) NOT NULL DEFAULT '0' COMMENT '报名人数',
  `post_num` int(11) NOT NULL COMMENT '帖子数',
  `view_num` int(11) NOT NULL DEFAULT '0' COMMENT '浏览数',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动';

-- ----------------------------
--  Table structure for `activity_member`
-- ----------------------------
DROP TABLE IF EXISTS `activity_member`;
CREATE TABLE `activity_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '成员user_id',
  `act_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'activity表主键',
  `status` int(11) NOT NULL DEFAULT '2' COMMENT '状态（-1审核不通过、1-审核中、2-成功报名,3-取消报名）',
  `sign_up_reason` varchar(255) NOT NULL DEFAULT '' COMMENT '报名理由',
  `apply_num` int(11) NOT NULL DEFAULT '1' COMMENT '申请次数',
  `apply_last_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后申请时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动成员';

-- ----------------------------
--  Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '分类名称',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '分类类型(1-群)',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `is_show` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0-展示1-隐藏',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级节点',
  `level` bigint(20) NOT NULL DEFAULT '1' COMMENT '节点数',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='分类表';

-- ----------------------------
--  Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论用户',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '类型（1-回答、2-帖子）',
  `biz_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '表主键id',
  `comment_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `status` tinyint(4) NOT NULL DEFAULT '2' COMMENT '状态(-1-屏蔽、1-审核中、2-审核通过、3-精选)',
  `top` tinyint(4) NOT NULL DEFAULT '0' COMMENT '置顶（0-正常、1-置顶）',
  `content` varchar(1024) NOT NULL DEFAULT '' COMMENT '评论内容',
  `like_num` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `reply_num` int(11) NOT NULL DEFAULT '0' COMMENT '回复数',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论';

-- ----------------------------
--  Table structure for `content`
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '发表人',
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT '标题',
  `contents` text NOT NULL COMMENT '内容',
  `latitude` decimal(10,10) NOT NULL DEFAULT '0.0000000000' COMMENT '纬度',
  `longitude` decimal(10,10) NOT NULL DEFAULT '0.0000000000' COMMENT '经度',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '类型（1-组织帖子内容、2-活动帖子内容、3-问题内容、4、回答内容）',
  `biz_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '组织、问题id',
  `biz_child_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '活动id',
  `status` tinyint(4) NOT NULL DEFAULT '2' COMMENT '状态（-1-屏蔽、1-审核中、2-审核通过、3-加精）',
  `top` int(11) NOT NULL DEFAULT '0' COMMENT '置顶（0-正常、1-置顶）',
  `img_num` int(11) NOT NULL DEFAULT '0' COMMENT '图片个数',
  `comment_num` int(11) NOT NULL DEFAULT '0' COMMENT '评论数、(问题)回答数',
  `view_num` int(11) NOT NULL DEFAULT '0' COMMENT '浏览数',
  `like_num` int(11) NOT NULL DEFAULT '0' COMMENT '点赞、有用数',
  `favorite_num` int(11) NOT NULL DEFAULT '0' COMMENT '收藏数',
  `follow_num` int(11) NOT NULL DEFAULT '0' COMMENT '关注数',
  `tag_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '标签tag表主键',
  `tag_extend` varchar(20) NOT NULL DEFAULT '' COMMENT '自定义标签',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容';

-- ----------------------------
--  Table structure for `favorite`
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '收藏人',
  `status` int(11) NOT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '类型（1-帖子、2-回答）',
  `biz_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '帖子id或者问题id',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏';

-- ----------------------------
--  Table structure for `follow_info`
-- ----------------------------
DROP TABLE IF EXISTS `follow_info`;
CREATE TABLE `follow_info` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `biz_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '关注对象主键(问题等)',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '类型（1-问题）',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态（1-关注、2-取消关注）',
  `follow_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注信息除了人外';

-- ----------------------------
--  Table structure for `follow_user`
-- ----------------------------
DROP TABLE IF EXISTS `follow_user`;
CREATE TABLE `follow_user` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `follow_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '关注用户id',
  `follow_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '关注时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '关注状态（-1拉黑、1-关注、2-取消关注）',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注人';

-- ----------------------------
--  Table structure for `group_info`
-- ----------------------------
DROP TABLE IF EXISTS `group_info`;
CREATE TABLE `group_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '组织名称',
  `introduction` varchar(500) NOT NULL DEFAULT '' COMMENT '介绍',
  `notice` varchar(500) NOT NULL DEFAULT '' COMMENT '公告',
  `img` varchar(250) NOT NULL DEFAULT '' COMMENT '图片',
  `originator_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '发起人',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类型（1-组织、2-活动、3-分类）',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（-1-驳回、1-待审核 、2-审核通过）',
  `join_way` tinyint(4) NOT NULL DEFAULT '1' COMMENT '加入方式（1-不需要验证、2-需要验证）',
  `speak_permission` tinyint(4) NOT NULL DEFAULT '1' COMMENT '发言权限（1-不限、2-仅管理员、3-管理员、班委、4-管理员、班委、普通成员）',
  `join_question` varchar(250) NOT NULL DEFAULT '' COMMENT '加入组织问题',
  `member_num` int(11) NOT NULL DEFAULT '0' COMMENT '成员数',
  `latitude` double NOT NULL DEFAULT '0' COMMENT '纬度',
  `longitude` double NOT NULL DEFAULT '0' COMMENT '经度',
  `city_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '城市',
  `biz_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '分类ID、活动ID、组织ID',
  `member_num_limit` int(11) NOT NULL DEFAULT '500' COMMENT '群人数上限',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群信息表';

-- ----------------------------
--  Table structure for `group_member`
-- ----------------------------
DROP TABLE IF EXISTS `group_member`;
CREATE TABLE `group_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '群组id',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态（-1-待审核、1-成功加入、2-退出、3-拒绝加入）',
  `apply_num` int(11) NOT NULL DEFAULT '1' COMMENT '申请次数',
  `apply_last_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后申请时间',
  `join_reason` varchar(255) NOT NULL DEFAULT '' COMMENT '加入理由',
  `operator_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '操作人',
  `role` int(11) NOT NULL DEFAULT '3' COMMENT '角色（1-群主、2-管理员,3-普通用户）',
  `role_alias` varchar(10) NOT NULL DEFAULT '' COMMENT '角色别名',
  `interdiction_status` int(11) NOT NULL DEFAULT '3' COMMENT '禁言状态（-1-禁言中,1-正常）',
  `interdiction_expiry_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '禁言过期时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群成员用户表';

-- ----------------------------
--  Table structure for `group_message`
-- ----------------------------
DROP TABLE IF EXISTS `group_message`;
CREATE TABLE `group_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '群组id',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `at_info` varchar(255) NOT NULL DEFAULT '' COMMENT '@信息 json',
  `last_read_msg_detail_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后一次读取信息id',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群用户消息表';

-- ----------------------------
--  Table structure for `group_message_detail`
-- ----------------------------
DROP TABLE IF EXISTS `group_message_detail`;
CREATE TABLE `group_message_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'group_info 表id',
  `sender_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '发送人',
  `at_info` varchar(255) NOT NULL DEFAULT '' COMMENT '@信息 json',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '消息类型（1-系统消息、2-用户）',
  `is_anonymous` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否匿名（-1-是、1-否）',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '消息内容',
  `send_msg_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消息时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群用户消息详细表';

-- ----------------------------
--  Table structure for `like`
-- ----------------------------
DROP TABLE IF EXISTS `like`;
CREATE TABLE `like` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '类型（1-组织内容、2-活动内容、3-问题内容、4-回答内容、5-评论、6-回复）',
  `biz_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '表主键',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（-1-取消、1-点赞）',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点赞';

-- ----------------------------
--  Table structure for `message_system`
-- ----------------------------
DROP TABLE IF EXISTS `message_system`;
CREATE TABLE `message_system` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `msg_num` int(11) NOT NULL DEFAULT '0' COMMENT '消息数',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '消息类型（1-系统消息、2-点赞、2-评论、3-问答、4-活动）',
  `is_show` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否展示（-1-不展示、1-展示）',
  `is_read` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否已读（-1-未读、1-已读）',
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT '消息标题',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '消息内容',
  `send_msg_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消息时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统消息';

-- ----------------------------
--  Table structure for `message_system_detail`
-- ----------------------------
DROP TABLE IF EXISTS `message_system_detail`;
CREATE TABLE `message_system_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msg_sys_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '系统消息表主键',
  `sender_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '发送人',
  `receiver_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '接收人',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（-1-未读、2-已读）',
  `send_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  `receive_time` datetime NOT NULL COMMENT '接收时间',
  `msg_type` int(11) NOT NULL DEFAULT '0' COMMENT '消息表类型',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '具体类型',
  `biz_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '表主键',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统消息详细';

-- ----------------------------
--  Table structure for `organization`
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '组织名称',
  `introduction` varchar(500) NOT NULL DEFAULT '' COMMENT '简介',
  `img` varchar(250) NOT NULL DEFAULT '' COMMENT '图片',
  `originator_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '发起人',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类型（1-系统、2-用户自建）',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（-1-驳回、1-待审核 、2-审核通过）',
  `join_way` tinyint(4) NOT NULL DEFAULT '1' COMMENT '加入方式（1-不需要验证、2-需要验证）',
  `post_permission` tinyint(4) NOT NULL DEFAULT '1' COMMENT '发帖权限（1-不限、2-仅管理员、3-管理员、班委、4-管理员、班委、普通成员）',
  `join_question` varchar(250) NOT NULL DEFAULT '' COMMENT '加入组织问题',
  `post_num` int(11) NOT NULL DEFAULT '0' COMMENT '帖子数',
  `follower_num` int(11) NOT NULL DEFAULT '0' COMMENT '关注数',
  `view_num` int(11) NOT NULL DEFAULT '0' COMMENT '浏览数',
  `latitude` double NOT NULL DEFAULT '0' COMMENT '纬度',
  `longitude` double NOT NULL DEFAULT '0' COMMENT '经度',
  `city_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '城市',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='组织';

-- ----------------------------
--  Table structure for `organization_member`
-- ----------------------------
DROP TABLE IF EXISTS `organization_member`;
CREATE TABLE `organization_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '成员user_id',
  `org_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '组织表主键',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态（1-关注、2-取消关注3、拒绝加入）',
  `apply_num` int(11) NOT NULL DEFAULT '1' COMMENT '申请次数',
  `apply_last_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后申请时间',
  `join_reason` varchar(255) NOT NULL DEFAULT '' COMMENT '加入理由',
  `operator_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '操作人',
  `role` int(11) NOT NULL DEFAULT '3' COMMENT '角色（1-管理员、2-班委,3-普通用户）',
  `role_alias` varchar(10) NOT NULL DEFAULT '' COMMENT '角色别名',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织成员';

-- ----------------------------
--  Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论用户',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '类型（1-评论、2-回复）',
  `comment_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论id',
  `reply_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '回复id',
  `reply_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
  `status` int(11) NOT NULL DEFAULT '2' COMMENT '状态(-1-屏蔽、1-审核中、2-审核通过、3-精选)',
  `top` tinyint(4) NOT NULL DEFAULT '0' COMMENT '置顶（0-正常、1-置顶）',
  `content` varchar(1024) NOT NULL DEFAULT '' COMMENT '评论内容',
  `like_num` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复';

-- ----------------------------
--  Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '标签名称',
  `biz_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '业务主表id',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '标签类型(1-帖子、2-活动、3-群)',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `is_show` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0-展示1-隐藏',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='标签';

-- ----------------------------
--  Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '电子邮件',
  `introduction` varchar(1024) NOT NULL DEFAULT '' COMMENT '个人简介',
  `last_login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `login_ip` varchar(20) NOT NULL DEFAULT '' COMMENT 'ip',
  `follower_num` int(11) NOT NULL DEFAULT '0' COMMENT '粉丝数',
  `me_follow_num` int(11) NOT NULL DEFAULT '0' COMMENT '我关注数量',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
--  Table structure for `user_message`
-- ----------------------------
DROP TABLE IF EXISTS `user_message`;
CREATE TABLE `user_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `short_un_read_msg_num` int(11) NOT NULL DEFAULT '0' COMMENT '未读消息数',
  `long_un_read_msg_num` int(11) NOT NULL DEFAULT '0' COMMENT '未读消息数',
  `short_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '长userId',
  `long_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '短userId',
  `last_send_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后发送时间',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '消息内容',
  `short_last_msg_detail_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '长userId 上次阅读消息的最后一个Id',
  `long_last_msg_detail_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '短userId 上次阅读消息的最后一个Id',
  `is_short_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `is_long_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户消息表';

-- ----------------------------
--  Table structure for `user_message_detail`
-- ----------------------------
DROP TABLE IF EXISTS `user_message_detail`;
CREATE TABLE `user_message_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msg_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'user_message 表id',
  `sender_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '发送人',
  `receiver_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '接收人',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '消息类型（1-系统消息、2-用户）',
  `is_read` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否已读（-1-未读、1-已读）',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '消息内容',
  `send_msg_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消息时间',
  `is_sender_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `is_receiver_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除、1-已删除）',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人user_id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户消息详细表';

SET FOREIGN_KEY_CHECKS = 1;
