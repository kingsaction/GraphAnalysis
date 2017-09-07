-- 在使用时，将下面的livejournal换成SNAP对应的数据集即可，按照下面的步骤得到livejournal
-- 数据的边、点存储
-- 首先测试边有多少
-- select "count"(*) from livejournal   --使用时将表名改变为其它的即可

-- 接着取出所有不同的点，并插入到临时表node_temp中
-- DELETE from node_temp
-- insert into node_temp(col1) select DISTINCT col1  from livejournal  -- 将epinions表中的第一列插入到指定的临时表node_temp中
-- insert into node_temp(col1) select DISTINCT col2 from livejournal   -- 将epinions表中的第二列插入到指定的临时表node_temp中

-- node_temp中的数据去重，并插入到node_temp2当中
-- DELETE FROM node_temp2
-- insert into node_temp2(col1) select DISTINCT col1 from node_temp  --将node_temp表中的唯一列做去重处理，并将其插入到node_temp2表中，完成上述步骤后得到图数据中点的node_name

-- 得到边表
-- DELETE FROM edge    -- 首先清空edge表
-- INSERT INTO edge (id,source_node_id,source_node_name,target_node_id,target_node_name,edge_state) SELECT'e' || col1 || col2,'n' || col1,col1,'n' || col2,col2,TRUE FROM livejournal -- 从epinions表中得到总边表，并将边数据插入到指定的edge表中


-- 将node_temp2中的数据导入到node表中
-- INSERT INTO node (id, node_name, node_state) SELECT'n' || col1,col1,TRUE FROM node_temp2   -- 从node_temp2中得到node_name数据，并插入完成node表中的id,node_name,node_state

-- 将点表与边表连接，并按照node.id分组，得到所有点的度，并插入到度表medium_degree表中
-- DELETE FROM medium_degree
-- insert into medium_degree(id,count) select node.id , "count"(*) from node,edge where node.id = source_node_id or node.id = target_node_id GROUP BY node.id

-- 得到最后的点数据,最后将该select的SQL导出即可
-- select node.id,node.node_name,count node_degree,node.node_state  from node, medium_degree where node.id = medium_degree.id
-- 检查得到包含度的表是否正确
-- select count(*) from epinions where col1 = 5789 or col2 = 5789   --检查后正确
