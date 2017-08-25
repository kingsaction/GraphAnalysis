-- 将抽样出的点存储到该表中
CREATE TABLE sampling_nodes (
id  varchar(50),
node_name varchar(50)
);

-- 将抽样出的边存储到该表中
CREATE TABLE sampling_edges (
id varchar(50),
source_node_id varchar(50),
source_node_name varchar(50),
target_node_id varchar(50),
target_node_name varchar(50)
);