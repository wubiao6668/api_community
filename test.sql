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