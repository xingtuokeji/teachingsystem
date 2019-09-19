use teachingsystem;
-- 基于角色的系统权限管理RBAC <权限管理系统>
-- 创建用户表
create table user(
    `id` int not null auto_increment comment '用户id',
    `name` varchar(10) not null comment '用户姓名',
    `email` varchar(10) not null comment '用户邮箱',
    `is_admin` int not null default '0' comment '0 false 1 true',
    `status` int not null default '1' comment '记录是否有效 0无效 1有效',
    `create_time` timestamp not null comment '创建时间',
    `update_time` timestamp not null comment '记录的最后一次更新时间',
    primary key (`id`)
)engine=innodb auto_increment=1 default charset=utf8;

-- 创建角色表
create table role(
    `id` int not null auto_increment comment '角色id',
    `name` varchar(10) not null comment '角色名',
    `status` int not null default '1' comment '记录是否有效 0无效 1有效',
    `create_time` timestamp not null comment '记录的创建时间',
    `update_time` timestamp not null comment '记录的最后一次更新时间',
    primary key (`id`)
)engine=innodb auto_increment=1 default charset =utf8;

-- 创建权限表
create table access(
    `id` int not null auto_increment comment '权限id',
    `title` varchar(10) not null default '' comment '权限标题',
    `url` varchar(100) not null default '' comment '权限url',
    `status` int not null default '1' comment '0禁用 1可用',
    `create_time` timestamp not null comment '记录的创建时间',
    `update_time` timestamp not null comment '记录的最后一次更新时间',
    primary key (`id`)
)engine=innodb auto_increment=1 default charset =utf8;

-- 创建用户角色表
create table user_role(
    `id` int not null auto_increment comment '用户角色id',
    `user_id`  int comment '用户id',
    `role_id` int comment '角色id',
    `create_time` timestamp not null comment '记录的创建时间',
    `update_time` timestamp not null comment '记录的最后一次更新时间',
    primary key (`id`)
)engine=innodb auto_increment=1 default charset =utf8;

-- 创建角色权限表
create table role_access(
    `id` int not null auto_increment comment '角色权限id',
    `role_id` int comment '角色id',
    `access_id` int comment '权限id',
    `create_time` timestamp comment '记录的创建时间',
    `update_time` timestamp comment '记录的最后一次更新时间',
    primary key (`id`)
)engine=innodb auto_increment=1 default charset =utf8;

-- 用户记录操作表
create table operate_log_info(
    `id` int not null auto_increment comment '操作表主键',
    `user_id` int not null comment '操作的用户id',
    `name` varchar(10) not null comment '用户姓名',
    `from_ip` varchar(100) not null comment '操作人的机器ip',
    `operate_func` varchar(30) not null comment '操作的功能',
    `visit_method` varchar(100) not null comment '调用入口的方法',
    `method_cost_time` varchar(30) not null comment '访问方法所用的时间',
    `log_type` varchar(10) not null comment '日志类型',
    `uri` varchar(100) not null comment '访问的资源路径',
    `method` varchar(10) not null comment '请求方法',
    `visit_method_error_info` varchar(100) comment '访问方法的错误信息',
    `status` int not null default '1' comment '该记录是否有效 1有效 0无效',
    `login_out_time` timestamp not null comment '登录时间',
    `create_time` timestamp not null comment '记录的创建时间',
    `update_time` timestamp not null comment '记录的最后一次更新时间',
    primary key(`id`)
)engine=innodb auto_increment=1 default charset=utf8;