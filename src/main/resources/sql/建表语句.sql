create database if not exists fluent_mybatis;
drop table if exists hello_world;
create table hello_world
(
    id           bigint unsigned auto_increment primary key,
    say_hello    varchar(100) null,
    your_name    varchar(100) null,
    gmt_created   datetime   DEFAULT NULL COMMENT '创建时间',
    gmt_modified datetime   DEFAULT NULL COMMENT '更新时间',
    is_deleted   tinyint(2) DEFAULT 0 COMMENT '是否逻辑删除'
) ENGINE = InnoDB CHARACTER SET = utf8 comment '简单演示表';

CREATE TABLE `test_fluent_mybatis` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name` varchar(255) DEFAULT NULL COMMENT '姓名',
    `age` int DEFAULT NULL COMMENT '年龄',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `del_flag` int DEFAULT NULL COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;