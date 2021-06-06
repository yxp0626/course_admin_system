insert into test (id,name) values('2','测试');
#大章表
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


#小节表
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

#课程表
drop table if exists course;
create table course(
    id char(8) not null default '' comment 'id',
    name varchar(50) not null comment '名称',
    summary varchar(2000) comment '概述',
    time int default 0 comment '时长|单位秒',
    price decimal(8,2) default 0.00 comment '价格(元)',
    image varchar(100) comment '封面',
    level char(1) comment '级别|ONE("1","初级"),TWO("2","中级"),THREE("3","高级")',
    charge char(1) comment '收费|CHARGE("C","收费"),FREE("F","免费")',
    status char(1) comment '状态|PUBLISH("P","发布"),DRAFT("D","草稿")',
    enroll integer default 0 comment '报名数',
    sort int comment '顺序',
    created_at datetime(3) comment '创建时间',
    updated_at datetime(3) comment '修改时间',
    PRIMARY KEY (id)
) engine = innodb default charset = utf8mb4 comment ='课程表';

insert into course values ('000001','测试课程01','这是一门测试课程',7200,19.9,'',1,'C','D',100,0,now(),now());

select * from chapter;

#分类表
drop table if exists category;
create table category(
    id char(8) not null default '' comment 'id',
    parent char(8) not null default '' comment '父id',
    name varchar(50) not null comment '名称',
    sort int comment '顺序',
    primary key (id)
) engine = innodb default charset = utf8mb4 comment = '分类';


insert into category values ('0000100','0000000','前端技术',100);
insert into category values ('0000101','0000100','html/css',101);
insert into category values ('0000102','0000100','javascript',102);
insert into category values ('0000103','0000100','vue.js',103);
insert into category values ('0000104','0000100','react.js',104);
insert into category values ('0000105','0000100','angular',105);
insert into category values ('0000106','0000100','jquery',106);
insert into category values ('0000107','0000100','小程序',107);
insert into category values ('0000200','0000000','后端技术',200);
insert into category values ('0000201','0000200','java',201);
insert into category values ('0000202','0000200','springboot',202);
insert into category values ('0000203','0000200','springcloud',203);
insert into category values ('0000204','0000200','ssm',204);
insert into category values ('0000205','0000200','python',205);
insert into category values ('0000206','0000200','go',206);
insert into category values ('0000207','0000200','微服务',207);

#课程分类表
drop table if exists course_category;
create table course_category(
    id char(8) not null default '' comment 'id',
    course_id char(8) comment '课程|course.id',
    category_id char(8) comment '分类|category.id',
    primary key (id)
)   engine = innodb default charset = utf8mb4 comment = '课程分类';

#课程内容表(大的字段尽量单独成一张表)
drop table if exists course_content;
create table course_content(
    id char(8) not null default '' comment '课程id',
    content mediumtext not null comment '课程内容',
    primary key (id)
)   engine = innodb default charset = utf8mb4 comment = '课程内容';


