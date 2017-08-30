/**    
* @Title         Dbscan.java  
* @Package       com.uniplore.graph.vectorclustering  
* @Description   TODO 基于密度的聚类函数 
* @author        朱君鹏     
* @date          2017年8月21日 下午2:52:27  
* @version       1.0    
*/ 
package com.uniplore.graph.vectorclustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import de.lmu.ifi.dbs.elki.algorithm.clustering.DBSCAN;
import de.lmu.ifi.dbs.elki.data.Cluster;
import de.lmu.ifi.dbs.elki.data.Clustering;
import de.lmu.ifi.dbs.elki.data.NumberVector;
import de.lmu.ifi.dbs.elki.data.model.Model;
import de.lmu.ifi.dbs.elki.data.type.TypeUtil;
import de.lmu.ifi.dbs.elki.database.Database;
import de.lmu.ifi.dbs.elki.database.StaticArrayDatabase;
import de.lmu.ifi.dbs.elki.database.ids.DBIDIter;
import de.lmu.ifi.dbs.elki.database.ids.DBIDRange;
import de.lmu.ifi.dbs.elki.database.relation.Relation;
import de.lmu.ifi.dbs.elki.datasource.ArrayAdapterDatabaseConnection;
import de.lmu.ifi.dbs.elki.datasource.DatabaseConnection;
import de.lmu.ifi.dbs.elki.distance.distancefunction.minkowski.SquaredEuclideanDistanceFunction;
import de.lmu.ifi.dbs.elki.logging.LoggingConfiguration;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.vectorclustering.Dbscan       
 * 创建人    朱君鹏
 * 创建时间  2017年8月21日 下午2:52:27     
 * 修改人  
 * 修改时间  2017年8月21日 下午2:52:27     
 * 修改备注     
 * @version  1.0      
 */

public class Dbscan {
	public static void main(String[] args) throws Exception{
		LoggingConfiguration.setStatistics();  //日志信息，没有实质性的所用
		
		//读取facebook数据，构造数据集
		File file = new File("F:/Java/抽样算法实验截图/算法测试数据集/facebook_combined.txt/degree.txt");
		FileInputStream inputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		/**************************************数据生成****************************************/
		// 生成一个随机的数据集
		double[][] data = new double[730][1];
		for(int i = 0 ; i < data.length;i++){
			for(int j = 0 ; j < data[i].length; j++){
				String readLine = reader.readLine();
				Double value = Double.valueOf(readLine);
				data[i][j] = value;
			}
	   }
		
		//将上述二位数组存放成一个一维数组
		List<Double> list = new ArrayList<Double>();
		for (double[] ds : data) {
			for (double d : ds) {
				list.add(d);
			}
		}
		
		//将List转成Array
		Double[] temp = new Double[list.size()];
		Double[] array = list.toArray(temp);
		
		// 从上面的数组中加载数据
		DatabaseConnection dbc = new ArrayAdapterDatabaseConnection(data);
		// 创建一个数据库，这是ELKI的特殊实现
		Database db = new StaticArrayDatabase(dbc,null);
		// 从数据库中加载数据
		db.initialize();
		
		
		/**************************************距离函数****************************************/
		// 距离函数
		SquaredEuclideanDistanceFunction dist = SquaredEuclideanDistanceFunction.STATIC;
	
		/**************************************基于密度DBSCAN的聚类****************************************/
		DBSCAN<NumberVector> dbscan = new DBSCAN<NumberVector>(dist, 0.0008, 30);
		// 选择数值关系进行聚类
		Clustering<Model> c = dbscan.run(db);
		
		
		
		/**************************************处理结果****************************************/
		//得到一组值为噪音，另外的几组值为聚类结果
		System.out.println("---------开始输出聚类结果---------");
		// 包含数字向量的关系
		Relation<NumberVector> rel = db.getRelation(TypeUtil.NUMBER_VECTOR_FIELD);
		//System.out.println(rel.toString());
		// 我们知道ids必须是一个连续的范围
		DBIDRange ids = (DBIDRange) rel.getDBIDs();
	    //System.out.println(ids);  //当前测试，范围从[1 to 1000]
		// 输出所有的聚类结果
	    int i = 0;
	    //for(Cluster<KMeansModel> clu : c.getAllClusters()) {
	    for(Cluster<Model> clu : c.getAllClusters()) {
	      // K-means will name all clusters "Cluster" in lack of noise support:
	      System.out.println("#" + i + ": " + clu.getNameAutomatic());
	      System.out.println("Size: " + clu.size());
	      
	      // Iterate over objects:
	      System.out.print("Objects: ");   //输出执行kmeans后的结果
	      for(DBIDIter it = clu.getIDs().iter(); it.valid(); it.advance()) {
	        // To get the vector use:
	        // NumberVector v = rel.get(it);
	    	  
	        // Offset within our DBID range: "line number"
	        final int offset = ids.getOffset(it);
	        //System.out.print(" " + offset);
	        System.out.print(" " + array[offset]);   //得到该聚类结果中的所有点
	        // Do NOT rely on using "internalGetIndex()" directly!
	      }
	      System.out.println();
	      ++i;
	    }
	    reader.close();
	}
}
