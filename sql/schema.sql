

use lanSchool;
-- ----------------------------
-- 用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
                          id           bigint(20)      not null auto_increment    comment '用户ID',
                          user_name         varchar(30)     not null                   comment '用户账号',
                          email             varchar(50)     default ''                 comment '用户邮箱',
                          sex               char(1)         default '0'                comment '用户性别（0男 1女 2未知）',
                          password          varchar(100)    default ''                 comment '密码',
                          primary key (id)
) engine=innodb auto_increment=1 comment = '用户信息表';


-- ----------------------------
-- 用户信息表
-- ----------------------------
drop table if exists class_room;
create table class_room(
        id           bigint(20)      not null auto_increment    comment '用户ID',
        room_status enum('staring','not_Start','finish') comment '状态：进行中｜未开始｜已结束',
        name         varchar(30)     not null            comment '教室名称',
        primary key (id)
) engine=innodb auto_increment=1 comment = '教室信息表';
