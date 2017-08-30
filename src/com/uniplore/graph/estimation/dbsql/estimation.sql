-- 创建表用来存储节点的度分布
CREATE TABLE degree_distribution (
degree INT ,
count INT)

CREATE TABLE sampling_degree_distribution (
degree INT ,
count INT)


create table cluster_coefficient(
cluster_coefficient double precision 
);

create table sampling_cluster_coefficient(
cluster_coefficient double precision 
);

SELECT count(*) FROM "public"."cluster_coefficient" WHERE cluster_coefficient <= 0.2;
SELECT count(*) FROM "public"."cluster_coefficient" WHERE cluster_coefficient > 0.2 and cluster_coefficient <= 0.4;
SELECT count(*) FROM "public"."cluster_coefficient" WHERE cluster_coefficient > 0.4 and cluster_coefficient <= 0.6;
SELECT count(*) FROM "public"."cluster_coefficient" WHERE cluster_coefficient > 0.6 and cluster_coefficient <= 0.8;
SELECT count(*) FROM "public"."cluster_coefficient" WHERE cluster_coefficient > 0.8 and cluster_coefficient <= 1;

SELECT count(*) FROM "public"."sampling_cluster_coefficient" WHERE cluster_coefficient <= 0.2;
SELECT count(*) FROM "public"."sampling_cluster_coefficient" WHERE cluster_coefficient > 0.2 and cluster_coefficient <= 0.4;
SELECT count(*) FROM "public"."sampling_cluster_coefficient" WHERE cluster_coefficient > 0.4 and cluster_coefficient <= 0.6;
SELECT count(*) FROM "public"."sampling_cluster_coefficient" WHERE cluster_coefficient > 0.6 and cluster_coefficient <= 0.8;
SELECT count(*) FROM "public"."sampling_cluster_coefficient" WHERE cluster_coefficient > 0.8 and cluster_coefficient <= 1;