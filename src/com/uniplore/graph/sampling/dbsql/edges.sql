-- 边表的定义 MySQL
create table edge (
id varchar(50) primary key,   -- id主键
source_node_id varchar(50),   -- 源点id
target_node_id varchar(50),   -- 目标点id
source_node_name varchar(50),  -- 源点名字
target_node_name varchar(50), -- 目标点名字
edge_state Boolean             -- 边状态
);