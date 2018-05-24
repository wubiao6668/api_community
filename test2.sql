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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统消息详细'