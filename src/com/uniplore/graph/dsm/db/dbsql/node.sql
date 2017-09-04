-- 点表的定义
create table node(
id varchar(50) primary key,        -- 主键
node_name varchar(50),             -- 节点名字
node_degree  Integer,              -- 节点的度
node_state   Boolean               -- 在抽样是使用的节点的状态
);

-- 从数据中得到点表中的一部分数据
INSERT into node(ID,node_name,node_state) select 'n' || col1,col1,TRUE from t1,t2 where t1.col1 = t2.col2

CREATE TABLE "public"."edge" (
"id" varchar(50),
"source_node_id" varchar(50) ,
"target_node_id" varchar(50) ,
"source_node_name" varchar(50) ,
"target_node_name" varchar(50),
"edge_state" bool
)