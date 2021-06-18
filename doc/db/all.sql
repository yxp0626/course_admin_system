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

alter table course add column (teacher_id char(8) comment '讲师|teacher.id');


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

#讲师表
drop table if exists teacher;
create table teacher(
    `id` char(8) not null default '' comment 'id',
    name varchar(50) not null comment '姓名',
    nickname varchar(50) comment '昵称',
    image varchar(100) comment '头像',
    position varchar(50) comment '职位',
    motto varchar(50) comment '座右铭',
    intro varchar(500) comment '简介',
    primary key (id)
)   engine = innodb default charset = utf8mb4 comment = '讲师';

#文件表
drop table if exists `file`;
create table `file` (
  `id` char(8) not null default '' comment 'id',
  `path` varchar(100) not null comment '相对路径',
  `name` varchar(100) comment '文件名',
  `suffix` varchar(10) comment '后缀',
  `size` int comment '大小|字节B',
  `use` char(1) comment '用途|枚举[FileUseEnum]：COURSE("C", "讲师"), TEACHER("T", "课程")',
  `created_at` datetime(3) comment '创建时间',
  `updated_at` datetime(3) comment '修改时间',
  primary key (`id`),
  unique key `path_unique` (`path`)
) engine=innodb default charset=utf8mb4 comment='文件';

alter table `file` add column (`shard_index` int comment '已上传分片');
alter table `file` add column (`shard_size` int comment '分片大小|B');
alter table `file` add column (`shard_total` int comment '分片总数');
alter table `file` add column (`key` varchar(32) comment '文件标识');
alter table `file` add unique key key_unique (`key`);

-- 课程内容文件
drop table if exists `course_content_file`;
create table `course_content_file` (
  `id` char(8) not null default '' comment 'id',
  `course_id` char(8) not null comment '课程id',
  `url` varchar(100) comment '地址',
  `name` varchar(100) comment '文件名',
  `size` int comment '大小|字节b',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='课程内容文件';


drop table if exists `user`;
create table `user` (
  `id` char(8) not null default '' comment 'id',
  `login_name` varchar(50) not null comment '登陆名',
  `name` varchar(50) comment '昵称',
  `password` char(32) not null comment '密码',
  primary key (`id`),
  unique key `login_name_unique` (`login_name`)
) engine=innodb default charset=utf8mb4 comment='用户';

insert into `user` (id, login_name, name, password) values
('10000000', 'test', '测试', '202cb962ac59075b964b07152d234b70');


-- 资源表
drop table if exists `resource`;
create table `resource` (
  `id` char(6) not null default '' comment 'id',
  `name` varchar(100) not null comment '名称|菜单或按钮',
  `page` varchar(50) null comment '页面|路由',
  `request` varchar(200) null comment '请求|接口',
  `parent` char(6) comment '父id',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='资源';

insert into `resource` values ('01', '系统管理', null, null, null);
insert into `resource` values ('0101', '用户管理', '/system/user', null, '01');
insert into `resource` values ('010101', '保存', null, '["/system/admin/user/list", "/system/admin/user/save"]', '0101');
insert into `resource` values ('010102', '删除', null, '["/system/admin/user/delete"]', '0101');
insert into `resource` values ('010103', '重置密码', null, '["/system/admin/user/save-password"]', '0101');
insert into `resource` values ('0102', '资源管理', '/system/resource', null, '01');
insert into `resource` values ('010201', '保存/显示', null, '["/system/admin/resource"]', '0102');
insert into `resource` values ('0103', '角色管理', '/system/role', null, '01');
insert into `resource` values ('010301', '角色/权限管理', null, '["/system/admin/role"]', '0103');


#角色权限管理表
drop table if exists `role`;
create table `role` (
  `id` char(8) not null default '' comment 'id',
  `name` varchar(50) not null comment '角色',
  `desc` varchar(100) not null comment '描述',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='角色';

insert into `role` values ('00000000', '系统管理员', '管理用户、角色权限');
insert into `role` values ('00000001', '开发', '维护资源');
insert into `role` values ('00000002', '业务管理员', '负责业务管理');

#角色-资源关联表
drop table if exists `role_resource`;
create table `role_resource` (
  `id` char(8) not null default '' comment 'id',
  `role_id` char(8) not null comment '角色|id',
  `resource_id` char(6) not null comment '资源|id',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='角色资源关联';

insert into `role_resource` values ('00000000', '00000000', '01');
insert into `role_resource` values ('00000001', '00000000', '0101');
insert into `role_resource` values ('00000002', '00000000', '010101');
insert into `role_resource` values ('00000003', '00000000', '010102');
insert into `role_resource` values ('00000004', '00000000', '010103');
insert into `role_resource` values ('00000005', '00000000', '0102');
insert into `role_resource` values ('00000006', '00000000', '010201');
insert into `role_resource` values ('00000007', '00000000', '0103');
insert into `role_resource` values ('00000008', '00000000', '010301');

#用户角色关联表
drop table if exists `role_user`;
create table `role_user` (
  `id` char(8) not null default '' comment 'id',
  `role_id` char(8) not null comment '角色|id',
  `user_id` char(8) not null comment '用户|id',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='角色用户关联';

insert into `role_user` values ('00000000', '00000000', '10000000');
