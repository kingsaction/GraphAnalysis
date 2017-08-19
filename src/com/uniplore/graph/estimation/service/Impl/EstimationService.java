/**    
* @Title         EstimationService.java  
* @Package       com.uniplore.graph.estimation.service.Impl  
* @Description   TODO 参数统计
* @author        朱君鹏     
* @date          2017年8月19日 上午9:14:49  
* @version       1.0    
*/ 
package com.uniplore.graph.estimation.service.Impl;


import java.util.List;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uniplore.graph.estimation.dao.IEstimationDao;
import com.uniplore.graph.estimation.service.IEstimationService;
import com.uniplore.graph.estimation.entity.*;
/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.estimation.service.Impl.EstimationService       
 * 创建人    朱君鹏
 * 创建时间  2017年8月19日 上午9:14:49     
 * 修改人  
 * 修改时间  2017年8月19日 上午9:14:49     
 * 修改备注     
 * @version  1.0      
 */

@Service
@Transactional
public class EstimationService implements IEstimationService {

	@Autowired
	private IEstimationDao estimateDao ;
	/**  
	 * @see com.uniplore.graph.estimation.service.IEstimationService#Estimation()
	 * 计算网络中的统计指标，目的是为了衡量抽样前后网络统计参数的变化
	 */  
	
	@Override
	public void Estimation() throws Exception {
		
		//首先评估未进行抽样时，图的参数情况，数据对应数据库中node和edge表
		Graph graph = new MultiGraph("Origin Graph");
		
		//开始遍历点表，并将点表按照当前GraphStream的要求生成对应的Graph
		int nodePage = 1 ;    //标识第几页，从第一页开始
		int nodePageSize = 1000;  //标识点表每一页包含的记录数，设置为1000
		long nodeTotal = 0; //保存点表中总的记录数
		long nodePageTotalNumber = 1 ; //记录点表分页之后的总页数
		
		while(nodePage <= nodePageTotalNumber){  //如果当前页数小于等于总的页数时，执行循环
			PageHelper.startPage(nodePage, nodePageSize);   //分页
			List<NodeEstimation> listNodeAllData = estimateDao.listNodeAllData();
			
	        //获取数据库中点表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(nodePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<NodeEstimation> pageInfo = new PageInfo<NodeEstimation>(listNodeAllData);
		        nodeTotal = pageInfo.getTotal(); //获取总记录数
		        //System.out.println("总记录数为:" + total);
		        nodePageTotalNumber = nodeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的点表总页数为:" + nodePageTotalNumber);
			}
			
			//处理每一页数据
			int nodePageSizePer = listNodeAllData.size();   //获取的每一页数据的实际大小
			for (int i = 0; i < nodePageSizePer; i++) {
				graph.addNode(listNodeAllData.get(i).getId());
			}   //点数据抽样完毕	
			nodePage++;
		}
		
		//开始遍历边表，并将边表按照当前GraphStream的要求生成对应的Graph
		int edgePage = 1;   //标识第几页，从第一页开始
		int edgePageSize = 1000; //标识边表每一页包含的记录数，初始设置为1000
		long edgeTotal = 0 ; //保存边表中的总记录数目
		long edgePageTotalNumber = 1 ; //记录边表分页之后的总页数
		
		while(edgePage <= edgePageTotalNumber){
			PageHelper.startPage(edgePage,edgePageSize);
			List<EdgeEstimation> listEdgeAllData = estimateDao.listEdgeAllData();
			
			//获取数据库中边表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(edgePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<EdgeEstimation> pageInfo = new PageInfo<EdgeEstimation>(listEdgeAllData);
		        edgeTotal = pageInfo.getTotal(); //获取边表中的总记录数
		        //System.out.println("总记录数为:" + total);
		        edgePageTotalNumber = edgeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的边表总页数为:" + edgePageTotalNumber);
			}
			
			int edgePageSizePer = listEdgeAllData.size();
			for (int i = 0; i < edgePageSizePer; i++) {
				EdgeEstimation edgeEstimation = listEdgeAllData.get(i);
				//System.out.println("读出的边数据为:" + edgeEstimation.toString());
				graph.addEdge(edgeEstimation.getId(), edgeEstimation.getSourceNodeID(), edgeEstimation.getTargetNodeID(),true);
			}
			edgePage++;
		}  //边表的抽样完毕
		
		//首先获取网络结构的度分布
		int[] degreeDistribution = Toolkit.degreeDistribution(graph);
		estimateDao.deleteDegree();   //将degree_distribution表中的数据全部删除
		for(int i = 0 ; i < degreeDistribution.length ; i++){
			//System.out.println("下标为:" + i + ",内容为:" + degreeDistribution[i]);
			DegreeDistribution degree = new DegreeDistribution(i,degreeDistribution[i]);
			estimateDao.insertDegreeDistribution(degree);   //将数据插入到度表中
		}
		
		//计算网络结构的平均度
		System.out.println("平均度为:" + Toolkit.averageDegree(graph));
		
		//计算平均度的偏差
		System.out.println("平均度的偏差为:" + Toolkit.degreeAverageDeviation(graph));
		
		//计算链接的平均密度
		System.out.println("链接的密度为:" + Toolkit.density(graph));
		
		//计算网络图的直径，该网络图的直径是所有最短路径的最大值
		System.out.println("网络图的直径为:" + Toolkit.diameter(graph));
		
		//计算聚类系数
		double[] clusteringCoefficients = Toolkit.clusteringCoefficients(graph);
		for(int i = 0 ; i < clusteringCoefficients.length; i++){
			System.out.println("下标为:" + i + "内容为:" + clusteringCoefficients[i]);
		}
		
		//计算平均聚类系数
		System.out.println("平均聚类系数为:" + Toolkit.averageClusteringCoefficient(graph));
	}
}
