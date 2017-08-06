-- 边表的定义
create table edge(
id varchar(50) PRIMARY KEY,          -- 自增主键
source_node varchar(50),             -- 起始点
target_node varchar(50)              -- 终点
);