ALTER TABLE `表名称` ADD `del_flag` CHAR(1) NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）' AFTER `最后的字段`, ADD `create_by` VARCHAR(64) NULL COMMENT '创建者' AFTER `del_flag`, ADD `create_time` DATETIME NULL COMMENT '创建时间' AFTER `create_by`, ADD `update_by` VARCHAR(64) NULL COMMENT '更新者' AFTER `create_time`, ADD `update_time` DATETIME NULL COMMENT '更新时间' AFTER `update_by`, ADD `remark` VARCHAR(500) NULL COMMENT '备注' AFTER `update_time`;