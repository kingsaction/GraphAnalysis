#### **数据集说明**
***

　　存放实验使用的数据集，数据集全部来自于SNAP，并且对数据集预先进行了处理，
将数据集从原先的形式转换成点、边关系存储，并对数据集所包含的信息进行了详细的说明。
所有数据集详细说明请参见： https://www.cise.ufl.edu/research/sparse/matrices/SNAP/

#### **处理数据集的全过程**
***
　　SNAP的数据集在处理时有特殊之处，能够全部使用SQL语句解决，简洁并
且高效，见下面SQL语句，对于不同的数据集，只需要将替换表名即可。

```sql
-- 首先测试边有多少
select "count"(*) from SNAP数据集表名   --使用时将表名改变为其它的即可

-- 接着取出所有不同的点，并插入到临时表node_temp中
insert into node_temp(col1) select DISTINCT col1  from SNAP数据集表名  -- 将SNAP数据集表名表中的第一列插入到指定的临时表node_temp中
insert into node_temp(col1) select DISTINCT col2 from SNAP数据集表名   -- 将SNAP数据集表名表中的第二列插入到指定的临时表node_temp中

-- node_temp中的数据去重，并插入到node_temp2当中
insert into node_temp2(col1) select DISTINCT col1 from node_temp  --将node_temp表中的唯一列做去重处理，并将其插入到node_temp2表中，完成上述步骤后得到图数据中点的node_name

-- 得到边表
DELETE FROM edge    -- 首先清空edge表
INSERT INTO edge (    
	id,
	source_node_id,
	source_node_name,
	target_node_id,
	target_node_name,
	edge_state
) SELECT
	'e' || col1 || col2,
	'n' || col1,
	col1,
	'n' || col2,
	col2,
	TRUE
FROM
	SNAP数据集表名   -- 从SNAP数据集表名表中得到总边表，并将边数据插入到指定的edge表中


-- 将node_temp2中的数据导入到node表中
INSERT INTO node (id, node_name, node_state) SELECT
	'n' || col1,
	col1,
	TRUE
FROM
	node_temp2   -- 从node_temp2中得到node_name数据，并插入完成node表中的id,node_name,node_state

-- 将点表与边表连接，并按照node.id分组，得到所有点的度，并插入到度表medium_degree表中
DELETE FROM medium_degree
insert into medium_degree(id,count) select node.id , "count"(*) from node,edge where node.id = source_node_id or node.id = target_node_id GROUP BY node.id

--得到最后的点数据,最后将该select的SQL导出即可
select node.id,node.node_name,count node_degree,node.node_state  from node, medium_degree where node.id = medium_degree.id

-- 检查得到包含度的表是否正确，主要是看得到的度是否正确
-- select count(*) from SNAP数据集表名 where col1 = 5789 or col2 = 5789   --检查后正确

```