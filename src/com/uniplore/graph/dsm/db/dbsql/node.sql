-- 点表的定义
create table node(
id varchar(50) primary key,        -- 主键
node_name varchar(50),             -- 节点名字
node_degree  Integer,              -- 节点的度
node_state   Boolean               -- 在抽样是使用的节点的状态
);