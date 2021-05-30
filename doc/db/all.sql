insert into test (id,name) values('2','测试');

drop table if exists `chapter`;
create table `chapter` (
    id char(8) not null comment 'ID',
    course_id char(8) comment '课程ID',
    name varchar(50) comment '名称',
    primary key (id)
) engine = innodb default charset=utf8mb4 comment ='大章';

insert into chapter values ('00000','00000','测试大章一'),('00001','00001','测试大章二');