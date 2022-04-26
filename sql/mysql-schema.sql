CREATE DATABASE IF NOT EXISTS lanSchool;
CREATE DATABASE IF NOT EXISTS keycloak;
use lanSchool;
-- ----------------------------
-- user info table
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
                          id           bigint(20)      not null auto_increment    comment 'id',
                          username         varchar(30)     not null                   comment 'username',
                          password          varchar(100)    default ''                 comment 'password',
                          primary key (id)
) engine=innodb auto_increment=1 comment = 'user info';

insert into sys_user(username,password) value ('admin','123456');
insert into sys_user(username,password) value ('test','123456');
-- ----------------------------
--
-- ----------------------------
drop table if exists class_room;
create table class_room(
        id           bigint(20)      not null auto_increment    comment 'id',
        room_status enum('staring','notStart','finish') comment 'status：staring｜noStart｜finish',
        name         varchar(30)     not null            comment 'name',
        primary key (id)
) engine=innodb auto_increment=1 comment = 'class room';
insert into class_room(room_status,name) value ('staring','class_room_name');
insert into class_room(room_status,name) value ('staring','class_room_test');

