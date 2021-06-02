insert into test (id,name) values('2','测试');

drop table if exists `chapter`;
create table `chapter` (
    id char(8) not null comment 'ID',
    course_id char(8) comment '课程ID',
    name varchar(50) comment '名称',
    primary key (id)
) engine = innodb default charset=utf8mb4 comment ='大章';


insert into chapter values ('00001','00000','测试大章01');
insert into chapter values ('00002','00000','测试大章02');
insert into chapter values ('00003','00000','测试大章03');
insert into chapter values ('00004','00000','测试大章04');
insert into chapter values ('00005','00000','测试大章05');
insert into chapter values ('00006','00000','测试大章06');
insert into chapter values ('00007','00000','测试大章07');
insert into chapter values ('00008','00000','测试大章08');
insert into chapter values ('00009','00000','测试大章09');
insert into chapter values ('00010','00000','测试大章10');
insert into chapter values ('00011','00000','测试大章11');
insert into chapter values ('00012','00000','测试大章12');
insert into chapter values ('00013','00000','测试大章13');
insert into chapter values ('00014','00000','测试大章14');
insert into chapter values ('00015','00000','测试大章15');
insert into chapter values ('00016','00000','测试大章16');
insert into chapter values ('00017','00000','测试大章17');
insert into chapter values ('00018','00000','测试大章18');



Drop table if exists section;
create table section
(
    id         char(8)    not null default '' comment 'ID',
    title      varchar(9) not null comment '标题',
    course_id  char(8) comment '课程|course.id',
    chapter_id char(8) comment '大章|chapter.id',
    video      varchar(200) comment '视频',
    time       int comment '时长|单位秒',
    charge     char(1) comment '收费|C 收费; F 免费',
    sort       int comment '顺序',
    created_at datetime(3) comment '创建时间',
    updated_at datetime(3) comment '修改时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB default charset=utf8mb4 comment = '小节';


insert into section values ('000001','测试小节1','00000001','00000000','',500,'F',1,now(),now());

SHOW FULL COLUMNS FROM section;
