/**    
* @Title         SampleService.java  
* @Package       com.uniplore.graph.sampling.service.Impl  
* @Description   TODO 抽样算法的实现类 
* @author        朱君鹏     
* @date          2017年8月8日 上午9:57:57  
* @version       1.0    
*/ 
package com.uniplore.graph.sampling.service.Impl;

import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uniplore.graph.dsm.db.entity.EdgeDataVO;
import com.uniplore.graph.dsm.db.entity.EdgeVO;
import com.uniplore.graph.dsm.db.entity.NodeDataVO;
import com.uniplore.graph.dsm.db.entity.NodeVO;
import com.uniplore.graph.sampling.dao.ISamplingDao;
import com.uniplore.graph.sampling.entity.Edges;
import com.uniplore.graph.sampling.entity.Nodes;
import com.uniplore.graph.sampling.service.ISampleService;
import com.uniplore.graph.util.samplingrandom.SampleRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.sampling.service.Impl.SampleService       
 * 创建人    朱君鹏
 * 创建时间  2017年8月8日 上午9:57:57     
 * 修改人  
 * 修改时间  2017年8月8日 上午9:57:57     
 * 修改备注     
 * @version  1.0      
 */

@Service
@Transactional
public class SampleService implements ISampleService {
	
	@Autowired
	private ISamplingDao samplingDao;
	/**  
	 * @see com.uniplore.graph.sampling.service.ISampleService#nodeSamplig() 均匀随机点抽样算法
	 * 该抽样算法的实现针对static graph 和 large graph，数据刚开始全部存放在数据库中
	 * 并且需要说明的是抽样比例15%指的是从点表数据中抽出总体的15%，不是针对边表
     * 具体的实现思路是：首先扫描点表，统计整个点表中总共有多少个点，然后使用随机函数生成一个均匀随机序列；
     * 完成上述操作之后，对照随机函数生成的序列，再次扫描点表，将上述随机序列中对应的点取出，构成抽样点表
	 * 接着对边表进行全表扫描，判断点表中的每一个source和target，只要包含在抽样点表中，则这条边被抽出
	 * 从上面的过程中我们看到，该算法对点表进行了两次全表扫描，并对边表进行了一次全表扫描，假设点表的大小为
	 * N，边表的大小为E，则该算法的时间复杂度为O(2N+E)  
	 */
	@Override
	public String nodeSampling() throws Exception {
		//首先对点表进行全表扫描，要注意，由于目前不知道点表的规模，所以在扫描时，考虑到可能点表
		//的记录数会很打，所以要对整个表分页查询结果
		int nodePage = 1 ;    //标识第几页，从第一页开始
		int nodePageSize = 1000;  //标识点表每一页包含的记录数，设置为1000
		long nodeTotal = 0; //保存点表中总的记录数
		long nodePageTotalNumber = 1 ; //记录点表分页之后的总页数
		
	    double proportion = 0.15;  //代表要取出的点的比例，目前设置为要取出15%的点
	    long nodeCount = 0 ; //取出点的连续计数，也就是说给所有的记录一个统一的计数
	    
	    Map<String, Nodes> nodeMap = new HashMap<String, Nodes>();  //在其中缓存抽样出来的点数据
	    List<Edges> edgeList = new ArrayList<Edges>();   //在其中缓存抽样出来的点数据
	    
		while(nodePage <= nodePageTotalNumber){  //如果当前页数小于等于总的页数时，执行循环
			PageHelper.startPage(nodePage, nodePageSize);   //分页
			List<Nodes> listNodeAllData = samplingDao.listNodeAllData();
			
	        //获取数据库中点表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(nodePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Nodes> pageInfo = new PageInfo<Nodes>(listNodeAllData);
		        nodeTotal = pageInfo.getTotal(); //获取总记录数
		        //System.out.println("总记录数为:" + total);
		        nodePageTotalNumber = nodeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的点表总页数为:" + nodePageTotalNumber);
			}
			
			//生成一组随机数，这组随机数表示要取出的点
			Long sampleNodeCount = (long) ((nodeTotal * proportion) + 1) ;
			HashSet<Long> randomSet = SampleRandom.randomSampling(sampleNodeCount, nodeTotal);  //数组中包含一组满足均匀分布的数
			
			//处理每一页数据
			int nodePageSizePer = listNodeAllData.size();   //获取的每一页数据的实际大小
			for (int i = 0; i < nodePageSizePer; i++) {  //点数据抽样开始
				if(randomSet.contains(nodeCount)){
					Nodes nodes = listNodeAllData.get(i);   //说明这组数据应该被取出得到数据
					nodeMap.put(nodes.getId(),nodes);
					nodeCount++;   //总的计数在任何情况下都要增加
				}else{
					nodeCount++;
					continue;
				}
				
			}   //点数据抽样完毕	
			nodePage++;
		}
		
		//开始边表的遍历，当sourceNode和targetNode都是上面抽样出来的点时，这条边要被抽出
		int edgePage = 1;   //标识第几页，从第一页开始
		int edgePageSize = 1000; //标识边表每一页包含的记录数，初始设置为1000
		long edgeTotal = 0 ; //保存边表中的总记录数目
		long edgePageTotalNumber = 1 ; //记录边表分页之后的总页数
		
		
		while(edgePage <= edgePageTotalNumber){  //边表抽样开始
			PageHelper.startPage(edgePage,edgePageSize);
			List<Edges> listEdgeAllData = samplingDao.listEdgeAllData();
			
			//获取数据库中边表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(edgePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Edges> pageInfo = new PageInfo<Edges>(listEdgeAllData);
		        edgeTotal = pageInfo.getTotal(); //获取边表中的总记录数
		        //System.out.println("总记录数为:" + total);
		        edgePageTotalNumber = edgeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的边表总页数为:" + edgePageTotalNumber);
			}
			
			int edgePageSizePer = listEdgeAllData.size();
			for (int i = 0; i < edgePageSizePer; i++) {
				Edges edges = listEdgeAllData.get(i);
				if (nodeMap.containsKey(edges.getSourceNodeID()) && nodeMap.containsKey(edges.getTargetNodeID())) {
					//此时该条边应该被取出
					edgeList.add(edges);
				}else {
					continue;
				}
			}
			edgePage++;
		}  //边表的抽样完毕
		
		//System.out.println("点表抽取出来的记录数为:" + nodeMap.size());  //逻辑正确
		//System.out.println("边表抽取出来的记录数为:" + edgeList.size());   //逻辑正确
		
		//构造出JSON字符串，并将结果返回给控制器用于展示
	    StringBuilder jsonString = new StringBuilder();
		NodeDataVO data1 = null;
		//遍历nodeMap，将所有的数据取出，构造成JSON
		Iterator<Entry<String, Nodes>> nodeIterator = nodeMap.entrySet().iterator();
		while(nodeIterator.hasNext()){
			Entry<String, Nodes> next = nodeIterator.next();
			String key = next.getKey();
			Nodes value = next.getValue();
			data1 = new NodeDataVO(key,value.getNodeName(),value.getNodeDegree());
			NodeVO nodeVo1 = new NodeVO(data1, "nodes",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(nodeVo1);
			jsonString.append(jsonString1 + ",");
		}  //抽样出的点添加完毕
		
	    //处理抽样出的边
		int edgeSize = edgeList.size();
		for (int i = 0; i < edgeSize ; i++) {
			Edges edges = edgeList.get(i);
			EdgeDataVO data3 = new EdgeDataVO(edges.getId(), edges.getSourceNodeID(), edges.getTargetNodeID(), 1);
			EdgeVO edgeVo = new EdgeVO(data3, "edges",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(edgeVo);
			jsonString.append(jsonString1 + ",");
		}
		String jsonOutput = "[" + jsonString + "]" ;   
		return jsonOutput;
	}
	
	/**  
	 * @see com.uniplore.graph.sampling.service.ISampleService#edgeSampling() 均匀随机边抽样算法
	 * 该抽样算法的实现针对static graph 和 large graph，数据刚开始全部存放在数据库中
	 * 并且需要说明的是抽样比例15%指的是从点表数据中抽出总体的15%，不是针对边表
     * 具体实现思路是：该算法主要是针对边表进行抽样，每次抽取一条边，将抽取出来的边包含其两个点都包含在样本中，
     * 因为要实现点的抽样比例是15%，这是这个算法实现最大的障碍，因为每次抽取不同的边，比如随机的两条边，可能其中包含的
     * 点是相同的，该算法在实现上和nodeSampling()算法有区别
     * 注意： 应该明白一个问题：select * from edge limit 1 offset number ，这个SQL的含义是：抽取edge表中第
     * number+1条记录，生成的均匀随机数也是从0开始，而且不能重复。
	 */
	@Override
	public String edgeSampling() throws Exception {
		
		Map<String, Edges> edgeMap = new HashMap<String, Edges>(); //用于存放抽取出来的边
		Map<String, Nodes> nodeMap = new HashMap<String, Nodes>(); //用于存放抽取出来的点
		
		//统计到底应该抽取多少点出来
		int nodePage = 1 ;    //标识第几页，从第一页开始
		int nodePageSize = 1000;  //标识点表每一页包含的记录数，设置为1000
		long nodeTotal = 0; //保存点表中总的记录数
		long nodePageTotalNumber = 1 ; //记录点表分页之后的总页数
		long sampleNodeCount = 0 ;
	    double proportion = 0.15;  //代表要取出的点的比例，目前设置为要取出15%的点
	    
		while(nodePage <= nodePageTotalNumber){  //如果当前页数小于等于总的页数时，执行循环
			PageHelper.startPage(nodePage, nodePageSize);   //分页
			List<Nodes> listNodeAllData = samplingDao.listNodeAllData();
			
	        //获取数据库中点表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(nodePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Nodes> pageInfo = new PageInfo<Nodes>(listNodeAllData);
		        nodeTotal = pageInfo.getTotal(); //获取总记录数
		        //System.out.println("总记录数为:" + total);
		        nodePageTotalNumber = nodeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的点表总页数为:" + nodePageTotalNumber);
			}
			
			//抽样规模的大小
			sampleNodeCount = (long) ((nodeTotal * proportion) + 1);
			nodePage++;
		}  //点表中点的总数统计完毕
		
		int edgePage = 1;   //标识第几页，从第一页开始
		int edgePageSize = 1000; //标识边表每一页包含的记录数，初始设置为1000
		long edgeTotal = 0 ; //保存边表中的总记录数目
		long edgePageTotalNumber = 1 ; //记录边表分页之后的总页数
		
		
		while(edgePage <= edgePageTotalNumber){  //边总数统计开始
			PageHelper.startPage(edgePage,edgePageSize);
			List<Edges> listEdgeAllData = samplingDao.listEdgeAllData();
			
			//获取数据库中边表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(edgePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Edges> pageInfo = new PageInfo<Edges>(listEdgeAllData);
		        edgeTotal = pageInfo.getTotal(); //获取边表中的总记录数
		        edgePageTotalNumber = edgeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
			}
			edgePage++;
		}  //边总数统计完毕
		  
		while(true){   //开始抽样
			if(nodeMap.size() == sampleNodeCount || edgeMap.size() == edgeTotal){
				//如果抽取出来的点的总数等于需要抽样的规模 或者 抽样出来的边的总数已经等于本身边表的大小
				//此时整个抽样的工作都需要结束
				break;
			}else {
				//完成抽样的逻辑
				long nextLong = ThreadLocalRandom.current().nextLong(edgeTotal); //从[0,edgeTotal)中随机的抽出一条边
			    Edges edges = samplingDao.selectOneEdge(nextLong);  //根据边的编号选出相应的边
			    edgeMap.put(edges.getId(), edges); //将edges放入到抽样出来的边表中
			    nodeMap.put(edges.getSourceNodeID(),new Nodes(edges.getSourceNodeID(), edges.getSourceNodeName())); //保存源点
			    nodeMap.put(edges.getTargetNodeID(),new Nodes(edges.getTargetNodeID(), edges.getTargetNodeName())); //保存目标点
			}
		}
		
		//构造出JSON字符串，并将结果返回给控制器用于展示
	    StringBuilder jsonString = new StringBuilder();
		//遍历nodeMap，将所有的数据取出，构造成JSON
		Iterator<Entry<String, Nodes>> nodeIterator = nodeMap.entrySet().iterator();
		while(nodeIterator.hasNext()){
			Entry<String, Nodes> entryNode = nodeIterator.next();
			String key = entryNode.getKey();
			Nodes value = entryNode.getValue();
			NodeDataVO data = new NodeDataVO(key,value.getNodeName(),value.getNodeDegree());
			NodeVO nodeVo1 = new NodeVO(data, "nodes",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(nodeVo1);
			jsonString.append(jsonString1 + ",");
		}
		
		Iterator<Entry<String, Edges>> edgeIterator = edgeMap.entrySet().iterator();
		while(edgeIterator.hasNext()){
			Entry<String, Edges> entryEdge = edgeIterator.next();
			String key = entryEdge.getKey();
			Edges value = entryEdge.getValue();
			
			EdgeDataVO data = new EdgeDataVO(value.getId(), value.getSourceNodeID(), value.getTargetNodeID(), 1);
			EdgeVO edgeVo = new EdgeVO(data, "edges",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(edgeVo);
			jsonString.append(jsonString1 + ",");
		}
		
		String jsonOutput = "[" + jsonString + "]" ;
		return jsonOutput;
	}

	/**  
	 * @see com.uniplore.graph.sampling.service.ISampleService#edgeISampling() 均匀随机边抽样改进算法
	 * 在上述算法的基础之上改进的算法，主要是在抽样完边之后，会再次根据抽样得到的点去扫描一次边表，将所有的边都
	 * 抽出
	 */  
	
	@Override
	public String edgeISampling() throws Exception {
		
		Map<String, Edges> edgeMap = new HashMap<String, Edges>(); //用于存放抽取出来的边
		Map<String, Nodes> nodeMap = new HashMap<String, Nodes>(); //用于存放抽取出来的点
		
		//统计到底应该抽取多少点出来
		int nodePage = 1 ;    //标识第几页，从第一页开始
		int nodePageSize = 1000;  //标识点表每一页包含的记录数，设置为1000
		long nodeTotal = 0; //保存点表中总的记录数
		long nodePageTotalNumber = 1 ; //记录点表分页之后的总页数
		long sampleNodeCount = 0 ;
	    double proportion = 0.15;  //代表要取出的点的比例，目前设置为要取出15%的点
	    
		while(nodePage <= nodePageTotalNumber){  //如果当前页数小于等于总的页数时，执行循环
			PageHelper.startPage(nodePage, nodePageSize);   //分页
			List<Nodes> listNodeAllData = samplingDao.listNodeAllData();
			
	        //获取数据库中点表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(nodePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Nodes> pageInfo = new PageInfo<Nodes>(listNodeAllData);
		        nodeTotal = pageInfo.getTotal(); //获取总记录数
		        //System.out.println("总记录数为:" + total);
		        nodePageTotalNumber = nodeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的点表总页数为:" + nodePageTotalNumber);
			}
			
			//抽样规模的大小
			sampleNodeCount = (long) ((nodeTotal * proportion) + 1);
			nodePage++;
		}  //点表中点的总数统计完毕
		
		int edgePage = 1;   //标识第几页，从第一页开始
		int edgePageSize = 1000; //标识边表每一页包含的记录数，初始设置为1000
		long edgeTotal = 0 ; //保存边表中的总记录数目
		long edgePageTotalNumber = 1 ; //记录边表分页之后的总页数
		
		
		while(edgePage <= edgePageTotalNumber){  //边总数统计开始
			PageHelper.startPage(edgePage,edgePageSize);
			List<Edges> listEdgeAllData = samplingDao.listEdgeAllData();
			
			//获取数据库中边表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(edgePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Edges> pageInfo = new PageInfo<Edges>(listEdgeAllData);
		        edgeTotal = pageInfo.getTotal(); //获取边表中的总记录数
		        edgePageTotalNumber = edgeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
			}
			edgePage++;
		}  //边总数统计完毕
		  
		while(true){   //开始抽样
			if(nodeMap.size() >= sampleNodeCount || edgeMap.size() >= edgeTotal){
				//如果抽取出来的点的总数等于需要抽样的规模 或者 抽样出来的边的总数已经等于本身边表的大小
				//此时整个抽样的工作都需要结束
				break;
			}else {
				//完成抽样的逻辑
				long nextLong = ThreadLocalRandom.current().nextLong(edgeTotal); //从[0,edgeTotal)中随机的抽出一条边
			    Edges edges = samplingDao.selectOneEdge(nextLong);  //根据边的编号选出相应的边
			    edgeMap.put(edges.getId(), edges); //将edges放入到抽样出来的边表中
			    nodeMap.put(edges.getSourceNodeID(),new Nodes(edges.getSourceNodeID(), edges.getSourceNodeName())); //保存源点
			    nodeMap.put(edges.getTargetNodeID(),new Nodes(edges.getTargetNodeID(), edges.getTargetNodeName())); //保存目标点
			}
		}
		
		//开始边表的遍历，当sourceNode和targetNode都是上面抽样出来的点时，这条边要被抽出
		int edgePage2 = 1;   //标识第几页，从第一页开始
		int edgePageSize2 = 1000; //标识边表每一页包含的记录数，初始设置为1000
		long edgeTotal2 = 0 ; //保存边表中的总记录数目
		long edgePageTotalNumber2 = 1 ; //记录边表分页之后的总页数
		List<Edges> edgeList = new ArrayList<Edges>();
		
		while(edgePage2 <= edgePageTotalNumber2){  //边表抽样开始
			PageHelper.startPage(edgePage2,edgePageSize2);
			List<Edges> listEdgeAllData = samplingDao.listEdgeAllData();
			
			//获取数据库中边表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(edgePage2 == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Edges> pageInfo = new PageInfo<Edges>(listEdgeAllData);
		        edgeTotal2 = pageInfo.getTotal(); //获取边表中的总记录数
		        //System.out.println("总记录数为:" + total);
		        edgePageTotalNumber2 = edgeTotal2/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的边表总页数为:" + edgePageTotalNumber);
			}
			
			int edgePageSizePer = listEdgeAllData.size();
			for (int i = 0; i < edgePageSizePer; i++) {
				Edges edges = listEdgeAllData.get(i);
				if (nodeMap.containsKey(edges.getSourceNodeID()) && nodeMap.containsKey(edges.getTargetNodeID())) {
					//此时该条边应该被取出
					edgeList.add(edges);
				}else {
					continue;
				}
			}
			edgePage2++;
		}  //边表的抽样完毕
		
		System.out.println("抽出点的个数为:" + nodeMap.size());
		System.out.println("抽取边的个数为:" + edgeList.size());
		//构造出JSON字符串，并将结果返回给控制器用于展示
	    StringBuilder jsonString = new StringBuilder();
		//遍历nodeMap，将所有的数据取出，构造成JSON
		Iterator<Entry<String, Nodes>> nodeIterator = nodeMap.entrySet().iterator();
		while(nodeIterator.hasNext()){
			Entry<String, Nodes> next = nodeIterator.next();
			String key = next.getKey();
			Nodes value = next.getValue();
			NodeDataVO data = new NodeDataVO(key,value.getNodeName(),value.getNodeDegree());
			NodeVO nodeVo1 = new NodeVO(data, "nodes",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(nodeVo1);
			jsonString.append(jsonString1 + ",");
		}  //抽样出的点添加完毕
		
	    //处理抽样出的边
		int edgeSize = edgeList.size();
		for (int i = 0; i < edgeSize ; i++) {
			Edges edges = edgeList.get(i);
			EdgeDataVO data3 = new EdgeDataVO(edges.getId(), edges.getSourceNodeID(), edges.getTargetNodeID(), 1);
			EdgeVO edgeVo = new EdgeVO(data3, "edges",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(edgeVo);
			jsonString.append(jsonString1 + ",");
		}
		String jsonOutput = "[" + jsonString + "]" ;   
		return jsonOutput;
	}

	/**  
	 * @see com.uniplore.graph.sampling.service.ISampleService#topologySampling() 基于拓扑结构的抽样算法
	 * 基于临近点的抽样算法，每次在抽取出点的同时，将其临近点的一部分也随机抽取出来
	 */ 
	@Override
	public String randomNeighborNodeSampling() throws Exception {
		int nodePage = 1 ;    //标识第几页，从第一页开始
		int nodePageSize = 1000;  //标识点表每一页包含的记录数，设置为1000
		long nodeTotal = 0; //保存点表中总的记录数
		long nodePageTotalNumber = 1 ; //记录点表分页之后的总页数
		long sampleNodeCount = 0;   //抽样的点的总数目
	    double proportion = 0.15;  //代表要取出的点的比例，目前设置为要取出15%的点
	         
		while(nodePage <= nodePageTotalNumber){  //如果当前页数小于等于总的页数时，执行循环
			PageHelper.startPage(nodePage, nodePageSize);   //分页
			List<Nodes> listNodeAllData = samplingDao.listNodeAllData();
			
	        //获取数据库中点表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(nodePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Nodes> pageInfo = new PageInfo<Nodes>(listNodeAllData);
		        nodeTotal = pageInfo.getTotal(); //获取总记录数
		        //System.out.println("总记录数为:" + total);
		        nodePageTotalNumber = nodeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的点表总页数为:" + nodePageTotalNumber);
			}
			
			//生成一组随机数，这组随机数表示要取出的点
		    sampleNodeCount = (long) ((nodeTotal * proportion) + 1) ;
		    nodePage++;  //加1结束整个while循环
		} //抽样点的个数统计完毕
		
		int edgePage = 1;   //标识第几页，从第一页开始
		int edgePageSize = 1000; //标识边表每一页包含的记录数，初始设置为1000
		long edgeTotal = 0 ; //保存边表中的总记录数目
		long edgePageTotalNumber = 1 ; //记录边表分页之后的总页数
		
		
		while(edgePage <= edgePageTotalNumber){  //边总数统计开始
			PageHelper.startPage(edgePage,edgePageSize);
			List<Edges> listEdgeAllData = samplingDao.listEdgeAllData();
			
			//获取数据库中边表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(edgePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Edges> pageInfo = new PageInfo<Edges>(listEdgeAllData);
		        edgeTotal = pageInfo.getTotal(); //获取边表中的总记录数
		        edgePageTotalNumber = edgeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
			}
			edgePage++;
		}  //边总数统计完毕
		
		//在点表当中随机抽取一个点，并且是不放回抽样
		Map<String, Nodes> nodeMap = new HashMap<String, Nodes>(); //将抽取出来的点存放在Map中
        Map<String, Edges> edgeMap = new HashMap<String, Edges>(); //将抽取出来的边存放在Map中
        
        Set<Long> randomSet = new HashSet<Long>();    //存放随机抽取的随机数
		while(true){
			if(nodeMap.size() == sampleNodeCount || edgeMap.size() == edgeTotal){
				//当点的总数得到抽样要求时，停止循环  或者 当抽取的边的数目等于边的总数时，停止抽样过程
				break;
			}else {
				Long nextLong = null ;
				while(true){
					nextLong = ThreadLocalRandom.current().nextLong(nodeTotal);  //产生一个随机数
					//根据该随机数，从指定的点表中抽取出一条记录
					if(!randomSet.contains(nextLong)){
						randomSet.add(nextLong);   //如果在当前不包含该随机数，则使用该随机数，否则继续抽取随机数
						break;
					}else {
						continue;
					}
				}  //选择随机数结束
				Nodes nodes = samplingDao.selectOneNode(nextLong);
				//将得到的点加入到点表中
				nodeMap.put(nodes.getId(), nodes);
				//获取该点所有的邻居边 
				List<Edges> neighbor = samplingDao.getNeighbor(nodes);
				
				//如果没有邻居点，则直接进行下一次循环
				if(neighbor.size() == 0){
					continue;
				}
				
				//如果邻居点个数等于1，将该邻居点取出即可
				if (neighbor.size() == 1) {
					Edges edges = neighbor.get(0);
					edgeMap.put(edges.getId(), edges);
					nodeMap.put(edges.getSourceNodeID(), new Nodes(edges.getSourceNodeID(), edges.getSourceNodeName()));
					nodeMap.put(edges.getTargetNodeID(), new Nodes(edges.getTargetNodeID(), edges.getTargetNodeName()));
				}
				
				//如果邻居点个数>=2，则只拿出两个就行
				if (neighbor.size() >= 2) {
					//遍历邻居点，随便拿出两个即可
					for(int i = 0 ; i < 2; i++){    
						Edges edges = neighbor.get(i);
						edgeMap.put(edges.getId(), edges);
						nodeMap.put(edges.getSourceNodeID(), new Nodes(edges.getSourceNodeID(), edges.getSourceNodeName()));
						nodeMap.put(edges.getTargetNodeID(), new Nodes(edges.getTargetNodeID(), edges.getTargetNodeName()));
					}  //for循环结束
				}
				
			}
		}   //抽样结束

		//构造出JSON字符串，并将结果返回给控制器用于展示
	    StringBuilder jsonString = new StringBuilder();
		//遍历nodeMap，将所有的数据取出，构造成JSON
		Iterator<Entry<String, Nodes>> nodeIterator = nodeMap.entrySet().iterator();
		while(nodeIterator.hasNext()){
			Entry<String, Nodes> entryNode = nodeIterator.next();
			String key = entryNode.getKey();
			Nodes value = entryNode.getValue();
			NodeDataVO data = new NodeDataVO(key,value.getNodeName(),value.getNodeDegree());
			NodeVO nodeVo1 = new NodeVO(data, "nodes",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(nodeVo1);
			jsonString.append(jsonString1 + ",");
		}
		
		Iterator<Entry<String, Edges>> edgeIterator = edgeMap.entrySet().iterator();
		while(edgeIterator.hasNext()){
			Entry<String, Edges> entryEdge = edgeIterator.next();
			String key = entryEdge.getKey();
			Edges value = entryEdge.getValue();
			
			EdgeDataVO data = new EdgeDataVO(value.getId(), value.getSourceNodeID(), value.getTargetNodeID(), 1);
			EdgeVO edgeVo = new EdgeVO(data, "edges",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(edgeVo);
			jsonString.append(jsonString1 + ",");
		}
		
		String jsonOutput = "[" + jsonString + "]" ;
		return jsonOutput;
	}
	
	/**
	 * 
	 * @Title  randomDegreeNodeSampling  随机度节点抽样算法，该算法为有偏抽样算法
	 * @Description TODO  随机度节点抽样算法，该算法偏向度更大的点，每个节点抽样的概率用节点的度来衡量
	 *                      目前该算法只实现针对小规模数据的有偏抽样，请注意，该算法与典型的NodeSampling算法不同
	 *                      ，之前算法是均匀随机的进行抽样。本段代码只适用于static graph，对于大规模的图数据实现
	 *                      有偏抽样比较复杂，后续会想办法改进。如果我能将构造出的新的数据存放到数据库中，这样就
	 *                      能够支持大规模的图数据不均匀抽样，该问题算是解决了，目前不会更新代码
	 * @return  返回JSON字符串，用于前端展示
	 * @throws Exception  统一异常处理
	 */
	@Override
	public String randomDegreeNodeSampling() throws Exception {
		//首先把点全部抽取出来，因为是不均匀抽样，要根据节点的度去构造一个新的整体
		List<Nodes> nodeList = new ArrayList<Nodes>();    //用来存放构造出的不均匀节点表整体
		
		int nodePage = 1 ;    //标识第几页，从第一页开始
		int nodePageSize = 1000;  //标识点表每一页包含的记录数，设置为1000
	    long nodeTotal = 0; //保存点表中总的记录数
		long nodePageTotalNumber = 1 ; //记录点表分页之后的总页数
		
	    double proportion = 0.15;  //代表要取出的点的比例，目前设置为要取出15%的点
		while(nodePage <= nodePageTotalNumber){  //如果当前页数小于等于总的页数时，执行循环
			PageHelper.startPage(nodePage, nodePageSize);   //分页
			List<Nodes> listNodeAllData = samplingDao.listNodeAllData();
			
	        //获取数据库中点表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(nodePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Nodes> pageInfo = new PageInfo<Nodes>(listNodeAllData);
		        nodeTotal = pageInfo.getTotal(); //获取总记录数
		        //System.out.println("总记录数为:" + total);
		        nodePageTotalNumber = nodeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的点表总页数为:" + nodePageTotalNumber);
			}
			
			//处理每一页数据
			int nodePageSizePer = listNodeAllData.size();   //获取的每一页数据的实际大小
			for (int i = 0; i < nodePageSizePer; i++) {  //点数据抽样开始
				Nodes node = listNodeAllData.get(i);   //取出当前点
				Integer nodeDegree = node.getNodeDegree();
				for(int j = 0 ; j < nodeDegree ;j++){
					nodeList.add(node);   //取出当前节点的度，并将其插入到不均匀点表中
				}
			}
			nodePage++;
		}
		//不均匀点表构造完毕，接下来采用随机均匀策略从nodeList中抽样
		Nodes[] nodeArray = new Nodes[nodeList.size()];
	    Nodes[] nodes = nodeList.toArray(nodeArray);   //将其转换为Nodes类型数组，数组下标作为抽样的参数
	    
	    int sampleNodeCount = (int)(nodeTotal * proportion) + 1 ;   //抽样比例
        HashSet<Integer> randomSet = SampleRandom.randomSamplingInt(sampleNodeCount, nodes.length);
		
        Map<String, Nodes> nodeMap = new HashMap<String, Nodes>();  //保存不重复的点
        //保存抽样的结果集
		Iterator<Integer> nodeIterator = randomSet.iterator();
		
		while(nodeIterator.hasNext()){
			int next = nodeIterator.next();
			nodeMap.put(nodes[next].getId(), nodes[next]);
		}  //将抽样的点保存在Map中即可
		
		//根据抽样好的点，从数据库中将所有的边抽取
		//开始边表的遍历，当sourceNode和targetNode都是上面抽样出来的点时，这条边要被抽出
		int edgePage = 1;   //标识第几页，从第一页开始
		int edgePageSize = 1000; //标识边表每一页包含的记录数，初始设置为1000
		long edgeTotal = 0 ; //保存边表中的总记录数目
		long edgePageTotalNumber = 1 ; //记录边表分页之后的总页数
		List<Edges> edgeList = new ArrayList<Edges>();    //用来存放抽取出来的边
		
		while(edgePage <= edgePageTotalNumber){  //边表抽样开始
			PageHelper.startPage(edgePage,edgePageSize);
			List<Edges> listEdgeAllData = samplingDao.listEdgeAllData();
			
			//获取数据库中边表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(edgePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Edges> pageInfo = new PageInfo<Edges>(listEdgeAllData);
		        edgeTotal = pageInfo.getTotal(); //获取边表中的总记录数
		        //System.out.println("总记录数为:" + total);
		        edgePageTotalNumber = edgeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的边表总页数为:" + edgePageTotalNumber);
			}
			
			int edgePageSizePer = listEdgeAllData.size();
			for (int i = 0; i < edgePageSizePer; i++) {
				Edges edges = listEdgeAllData.get(i);
				if(nodeMap.containsKey(edges.getSourceNodeID()) && nodeMap.containsKey(edges.getTargetNodeID())){
					//如果包含，这样的边需要被抽出
					edgeList.add(edges);
				}
			}
			edgePage++;
		}  //边表的抽样完毕
		
		//System.out.println("抽取出的点的个数为：" + nodeMap.size());
		//System.out.println("抽取出的边的个数为："+ edgeList.size());
		
		//构造出JSON字符串，并将结果返回给控制器用于展示
	    StringBuilder jsonString = new StringBuilder();
		NodeDataVO data1 = null;
		//遍历nodeMap，将所有的数据取出，构造成JSON
		Iterator<Entry<String, Nodes>> nodeIterators = nodeMap.entrySet().iterator();
		while(nodeIterators.hasNext()){
			Entry<String, Nodes> next = nodeIterators.next();
			String key = next.getKey();
			Nodes value = next.getValue();
			data1 = new NodeDataVO(key,value.getNodeName(),value.getNodeDegree());
			NodeVO nodeVo1 = new NodeVO(data1, "nodes",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(nodeVo1);
			jsonString.append(jsonString1 + ",");
		}  //抽样出的点添加完毕
		
	    //处理抽样出的边
		int edgeSize = edgeList.size();
		for (int i = 0; i < edgeSize ; i++) {
			Edges edges = edgeList.get(i);
			EdgeDataVO data3 = new EdgeDataVO(edges.getId(), edges.getSourceNodeID(), edges.getTargetNodeID(), 1);
			EdgeVO edgeVo = new EdgeVO(data3, "edges",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(edgeVo);
			jsonString.append(jsonString1 + ",");
		}
		String jsonOutput = "[" + jsonString + "]" ;   
		return jsonOutput;
	}

	/**  
	 * @see com.uniplore.graph.sampling.service.ISampleService#randomBreadthFirstSampling()
	 * 基于宽度优先遍历的抽样算法，众所周知，基于BFS的抽样算法是有偏于度较高的点，在BFS中，度较高的点
	 * 将会被频繁的访问，基于BFS的抽样算法比原始图有更高的聚类系数
	 */  
	
	@Override
	public String randomBreadthFirstSampling() throws Exception {
		int nodePage = 1 ;    //标识第几页，从第一页开始
		int nodePageSize = 1000;  //标识点表每一页包含的记录数，设置为1000
		long nodeTotal = 0; //保存点表中总的记录数
		long nodePageTotalNumber = 1 ; //记录点表分页之后的总页数
		long sampleNodeCount = 0;   //抽样的点的总数目
	    double proportion = 0.15;  //代表要取出的点的比例，目前设置为要取出15%的点
	         
		while(nodePage <= nodePageTotalNumber){  //如果当前页数小于等于总的页数时，执行循环
			PageHelper.startPage(nodePage, nodePageSize);   //分页
			List<Nodes> listNodeAllData = samplingDao.listNodeAllData();
			
	        //获取数据库中点表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(nodePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Nodes> pageInfo = new PageInfo<Nodes>(listNodeAllData);
		        nodeTotal = pageInfo.getTotal(); //获取总记录数
		        //System.out.println("总记录数为:" + total);
		        nodePageTotalNumber = nodeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的点表总页数为:" + nodePageTotalNumber);
			}
			
			//生成一组随机数，这组随机数表示要取出的点
		    sampleNodeCount = (long) ((nodeTotal * proportion) + 1) ;
		    nodePage++;  //加1结束整个while循环
		} //抽样点的个数统计完毕
		
		int edgePage = 1;   //标识第几页，从第一页开始
		int edgePageSize = 1000; //标识边表每一页包含的记录数，初始设置为1000
		long edgeTotal = 0 ; //保存边表中的总记录数目
		long edgePageTotalNumber = 1 ; //记录边表分页之后的总页数
		
		
		while(edgePage <= edgePageTotalNumber){  //边总数统计开始
			PageHelper.startPage(edgePage,edgePageSize);
			List<Edges> listEdgeAllData = samplingDao.listEdgeAllData();
			
			//获取数据库中边表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(edgePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Edges> pageInfo = new PageInfo<Edges>(listEdgeAllData);
		        edgeTotal = pageInfo.getTotal(); //获取边表中的总记录数
		        edgePageTotalNumber = edgeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
			}
			edgePage++;
		}  //边总数统计完毕
		
		//在点表当中随机抽取一个点，并且是不放回抽样
		Map<String, Nodes> nodeMap = new HashMap<String, Nodes>(); //将抽取出来的点存放在Map中
        Map<String, Edges> edgeMap = new HashMap<String, Edges>(); //将抽取出来的边存放在Map中
        
        Set<Long> randomSet = new HashSet<Long>();    //存放随机抽取的随机数
		while(true){
			if(nodeMap.size() >= sampleNodeCount || edgeMap.size() == edgeTotal){
				//当点的总数得到抽样要求时，停止循环  或者  当抽取的边的数目等于边的总数时，停止抽样过程
				break;
			}else {
				Long nextLong = null ;
				while(true){
					nextLong = ThreadLocalRandom.current().nextLong(nodeTotal);  //产生一个随机数
					//根据该随机数，从指定的点表中抽取出一条记录
					if(!randomSet.contains(nextLong)){
						randomSet.add(nextLong);   //如果在当前不包含该随机数，则使用该随机数，否则继续抽取随机数
						break;
					}else {
						continue;
					}
				}  //选择随机数结束
				
				Nodes nodes = samplingDao.selectOneNode(nextLong);
				//将得到的点加入到点表中
				nodeMap.put(nodes.getId(), nodes);
				//获取该点所有的邻居边 
				List<Edges> neighborList = samplingDao.getNeighbor(nodes);
				Queue<Nodes> nodeQueue = new LinkedList<Nodes>();   //将LinkedList当做队列来使用 
				
				//下面过程实际上使用了深度优先搜索技术
				//将邻居点放入Queue中
				for (Edges edges : neighborList) {
					//遍历上述list，并将点放入到Stack中
					nodeQueue.add(new Nodes(edges.getSourceNodeID(), edges.getSourceNodeName()));
					nodeQueue.add(new Nodes(edges.getTargetNodeID(), edges.getTargetNodeName()));
					
					//将所有的邻居点及其相应的边全部加入到nodeMap和edgeMap中
					nodeMap.put(edges.getSourceNodeID(), new Nodes(edges.getSourceNodeID(), edges.getSourceNodeName()));
					nodeMap.put(edges.getTargetNodeID(), new Nodes(edges.getTargetNodeID(), edges.getTargetNodeName()));
					edgeMap.put(edges.getId(), edges);
				}
				//System.out.println("第一次队列操作结束");
				
				//操作上述Queue，把并所有的目标点加入到nodeMap中，并把所有的边加入到edgeList中
				while(nodeMap.size() <= sampleNodeCount && !nodeQueue.isEmpty()){
					//按照进入队列的顺序将邻居点依次取出
					Nodes removeNode = nodeQueue.remove();   //弹出一个点
					//找到所有从队列中弹出的点的邻居边
					List<Edges> neighbor = samplingDao.getNeighbor(removeNode);
					//遍历上述所有的边，并取出其中所有的点和边
					for (Edges edges : neighbor) {
						//edgeMap.put(edges.getId(), edges); 
						if (nodeMap.containsKey(edges.getSourceNodeID())) {
							//说明该点已经存在，不再加入
						}else if(nodeMap.size() <= sampleNodeCount){
							nodeMap.put(edges.getSourceNodeID(), new Nodes(edges.getSourceNodeID(), edges.getSourceNodeName()));
							edgeMap.put(edges.getId(), edges);
							nodeQueue.add(new Nodes(edges.getSourceNodeID(), edges.getSourceNodeName()));
						}else{
							break;
						}
						
					    if (nodeMap.containsKey(edges.getTargetNodeID())) {
							//说明target存在，不再加入
						}else if(nodeMap.size() <= sampleNodeCount){
							nodeMap.put(edges.getTargetNodeID(), new Nodes(edges.getTargetNodeID(), edges.getTargetNodeName()));
							edgeMap.put(edges.getId(), edges);
							nodeQueue.add(new Nodes(edges.getTargetNodeID(), edges.getTargetNodeName()));
						}else {
							break;
						}
						
					}
					//System.out.println("队列操作正在循环");
				}
			}
		}   //抽样结束
		//开始边表的遍历，当sourceNode和targetNode都是上面抽样出来的点时，这条边要被抽出
		int edgePageNode = 1;   //标识第几页，从第一页开始
		int edgePageSizeNode = 1000; //标识边表每一页包含的记录数，初始设置为1000
		long edgeTotalNode = 0 ; //保存边表中的总记录数目
		long edgePageTotalNumberNode = 1 ; //记录边表分页之后的总页数
		
		
		while(edgePageNode <= edgePageTotalNumberNode){  //边表抽样开始
			PageHelper.startPage(edgePageNode,edgePageSizeNode);
			List<Edges> listEdgeAllData = samplingDao.listEdgeAllData();
			
			//获取数据库中边表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(edgePageNode == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Edges> pageInfo = new PageInfo<Edges>(listEdgeAllData);
		        edgeTotalNode = pageInfo.getTotal(); //获取边表中的总记录数
		        //System.out.println("总记录数为:" + total);
		        edgePageTotalNumberNode = edgeTotalNode/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的边表总页数为:" + edgePageTotalNumber);
			}
			
			int edgePageSizePer = listEdgeAllData.size();
			for (int i = 0; i < edgePageSizePer; i++) {
				Edges edges = listEdgeAllData.get(i);
				if (nodeMap.containsKey(edges.getSourceNodeID()) && nodeMap.containsKey(edges.getTargetNodeID())) {
					//此时该条边应该被取出
					edgeMap.put(edges.getId(), edges);
				}else {
					continue;
				}
			}
			edgePageNode++;
		}  //边表的抽样完毕
		
		/*System.out.println("抽出的点的个数为:" + nodeMap.size());
	    System.out.println("抽出的边的个数为:" + edgeMap.size());*/
		//System.out.println("抽样结束，开始生成JSON格式数据");
		
		//构造出JSON字符串，并将结果返回给控制器用于展示
	    StringBuilder jsonString = new StringBuilder();
		//遍历nodeMap，将所有的数据取出，构造成JSON
		Iterator<Entry<String, Nodes>> nodeIterator = nodeMap.entrySet().iterator();
		while(nodeIterator.hasNext()){
			Entry<String, Nodes> entryNode = nodeIterator.next();
			String key = entryNode.getKey();
			Nodes value = entryNode.getValue();
			NodeDataVO data = new NodeDataVO(key,value.getNodeName(),value.getNodeDegree());
			NodeVO nodeVo1 = new NodeVO(data, "nodes",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(nodeVo1);
			jsonString.append(jsonString1 + ",");
		}
		
		Iterator<Entry<String, Edges>> edgeIterator = edgeMap.entrySet().iterator();
		while(edgeIterator.hasNext()){
			Entry<String, Edges> entryEdge = edgeIterator.next();
			String key = entryEdge.getKey();
			Edges value = entryEdge.getValue();
			
			EdgeDataVO data = new EdgeDataVO(value.getId(), value.getSourceNodeID(), value.getTargetNodeID(), 1);
			EdgeVO edgeVo = new EdgeVO(data, "edges",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(edgeVo);
			jsonString.append(jsonString1 + ",");
		}
		
		String jsonOutput = "[" + jsonString + "]" ;
		return jsonOutput;
	}
	
	/**  
	 * @see com.uniplore.graph.sampling.service.ISampleService#randomDepthFirstSampling()
	 * 基于深度优先搜索的抽样算法，该算法针对Large Graph 和 Static Graph
	 * 具体实现思路：首先随机的选择一个节点u，然后在u的直接邻居中选择一个v，然后在v的邻居中选择节点w...
	 * 依次进行递归，节点的数目满足抽样的比例，性能比较差的算法
	 */  
	
	@Override
	public String randomDepthFirstSampling() throws Exception {
		
		int nodePage = 1 ;    //标识第几页，从第一页开始
		int nodePageSize = 1000;  //标识点表每一页包含的记录数，设置为1000
		long nodeTotal = 0; //保存点表中总的记录数
		long nodePageTotalNumber = 1 ; //记录点表分页之后的总页数
		long sampleNodeCount = 0;   //抽样的点的总数目
	    double proportion = 0.15;  //代表要取出的点的比例，目前设置为要取出15%的点
	         
		while(nodePage <= nodePageTotalNumber){  //如果当前页数小于等于总的页数时，执行循环
			PageHelper.startPage(nodePage, nodePageSize);   //分页
			List<Nodes> listNodeAllData = samplingDao.listNodeAllData();
			
	        //获取数据库中点表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(nodePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Nodes> pageInfo = new PageInfo<Nodes>(listNodeAllData);
		        nodeTotal = pageInfo.getTotal(); //获取总记录数
		        //System.out.println("总记录数为:" + total);
		        nodePageTotalNumber = nodeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的点表总页数为:" + nodePageTotalNumber);
			}
			
			//生成一组随机数，这组随机数表示要取出的点
		    sampleNodeCount = (long) ((nodeTotal * proportion) + 1) ;
		    nodePage++;  //加1结束整个while循环
		} //抽样点的个数统计完毕
		
		Map<String, Nodes> nodeMap = new HashMap<String, Nodes>();  //存放取出的点
		Map<String, Edges> edgeMap = new HashMap<String, Edges>();  //存放取出的边
		
		while(nodeMap.size() < sampleNodeCount){
			long nextLong = ThreadLocalRandom.current().nextLong(nodeTotal);   //从[0,nodeTotal)中随机选择一个数字
			Nodes node = samplingDao.selectOneNode(nextLong);   //得到该点
			while(node != null){
				if(nodeMap.containsKey(node.getId())){
					//当nodeMap中包含当前node的编号，说明该点已经被便利过
					break;
				}
				
				nodeMap.put(node.getId(), node);   //将当前取出的点加入到nodeMap中
				List<Edges> neighbor = samplingDao.getNeighbor(node);
			
				//将List转成Array
				Edges[] edges = new Edges[neighbor.size()];  //List转成Array，首先要构造相应类型的Array
				Edges[] edgesArray = neighbor.toArray(edges);  //将List转成指定类型的Array
				
				//在这些边中随机选择一条边，从而得到下一层点
				if (neighbor.size() != 0) {
					//说明有邻居点，此时生成一个随机数
					Random random = new Random();
					int nextInt = random.nextInt(neighbor.size());  //生成一个随机数，随机数范围[0,neighbor.size())
					Edges edge = edgesArray[nextInt];    //取出当前这条边
					if (!nodeMap.containsKey(edge.getSourceNodeID()) && nodeMap.size() < sampleNodeCount) {
						//说明nodeMap中不包含源点，此时源点将是下一个进行深度遍历的点
						node = new Nodes(edge.getSourceNodeID(),edge.getSourceNodeName());
						nodeMap.put(node.getId(), node);  //当前node放入到nodeMap中
						edgeMap.put(edge.getId(), edge);  //将边加入到edgeMap中
					}
					
					if (!nodeMap.containsKey(edge.getTargetNodeID()) && nodeMap.size() < sampleNodeCount) {
						//说明不包含终点ID
						node = new Nodes(edge.getTargetNodeID(),edge.getTargetNodeName());
						nodeMap.put(node.getId(), node);
						edgeMap.put(edge.getId(), edge);  //将边加入到edgeMap中
					}
				}else {   //neighbor.size() == 0 ，说明没有邻居点，此时node = null;
					//否则说明当前node没有邻居点，则下一个点就为null
					node = null;
				}
				
			}
		}
		
		//开始边表的遍历，当sourceNode和targetNode都是上面抽样出来的点时，这条边要被抽出
		int edgePage2 = 1;   //标识第几页，从第一页开始
		int edgePageSize2 = 1000; //标识边表每一页包含的记录数，初始设置为1000
		long edgeTotal2 = 0 ; //保存边表中的总记录数目
		long edgePageTotalNumber2 = 1 ; //记录边表分页之后的总页数
		List<Edges> edgeList = new ArrayList<Edges>();
		
		while(edgePage2 <= edgePageTotalNumber2){  //边表抽样开始
			PageHelper.startPage(edgePage2,edgePageSize2);
			List<Edges> listEdgeAllData = samplingDao.listEdgeAllData();
			
			//获取数据库中边表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(edgePage2 == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Edges> pageInfo = new PageInfo<Edges>(listEdgeAllData);
		        edgeTotal2 = pageInfo.getTotal(); //获取边表中的总记录数
		        //System.out.println("总记录数为:" + total);
		        edgePageTotalNumber2 = edgeTotal2/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的边表总页数为:" + edgePageTotalNumber);
			}
			
			int edgePageSizePer = listEdgeAllData.size();
			for (int i = 0; i < edgePageSizePer; i++) {
				Edges edges = listEdgeAllData.get(i);
				if (nodeMap.containsKey(edges.getSourceNodeID()) && nodeMap.containsKey(edges.getTargetNodeID())) {
					//此时该条边应该被取出
					edgeList.add(edges);
				}else {
					continue;
				}
			}
			edgePage2++;
		}  //边表的抽样完毕
		
		/*System.out.println("抽样出来的点的个数为:" + nodeMap.size());
		System.out.println("抽样出来的边的个数为:" + edgeMap.size());*/
		
		//构造出JSON字符串，并将结果返回给控制器用于展示
	    StringBuilder jsonString = new StringBuilder();
		//遍历nodeMap，将所有的数据取出，构造成JSON
		Iterator<Entry<String, Nodes>> nodeIterator = nodeMap.entrySet().iterator();
		while(nodeIterator.hasNext()){
			Entry<String, Nodes> entryNode = nodeIterator.next();
			String key = entryNode.getKey();
			Nodes value = entryNode.getValue();
			NodeDataVO data = new NodeDataVO(key,value.getNodeName(),value.getNodeDegree());
			NodeVO nodeVo1 = new NodeVO(data, "nodes",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(nodeVo1);
			jsonString.append(jsonString1 + ",");
		}
		
		Iterator<Entry<String, Edges>> edgeIterator = edgeMap.entrySet().iterator();
		while(edgeIterator.hasNext()){
			Entry<String, Edges> entryEdge = edgeIterator.next();
			String key = entryEdge.getKey();
			Edges value = entryEdge.getValue();
			
			EdgeDataVO data = new EdgeDataVO(value.getId(), value.getSourceNodeID(), value.getTargetNodeID(), 1);
			EdgeVO edgeVo = new EdgeVO(data, "edges",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(edgeVo);
			jsonString.append(jsonString1 + ",");
		}
		
		String jsonOutput = "[" + jsonString + "]" ;
		return jsonOutput;
	}
	
	
	/**  
	 * @see com.uniplore.graph.sampling.service.ISampleService#randomPageRankNodeSampling()
	 * 随机PageRank节点抽样算法实现，对节点进行不等概率抽样，属于有偏抽样的一种
	 */  
	
	@Override
	public String randomPageRankNodeSampling() throws Exception {
		
		return null;
	}

	/**  
	 * @see com.uniplore.graph.sampling.service.ISampleService#randomWalkSampling()  
	 * 随机游走抽样算法
	 */  
	
	@Override
	public String randomWalkSampling() throws Exception {
		
		return null;
	}

	/**  
	 * @see com.uniplore.graph.sampling.service.ISampleService#mhrwSampling()  
	 * 基于markov-chain monte carlo抽样算法，该算法是基于随机游走的随机点抽样算法的改进算法，
	 * 在该方法中关键是要提出一个概率函数该函数决定下一个点是否要加入到样本中，该函数会随机的
	 * 给出当前是接收该点还是拒绝该点，该算法会调整了随机点抽样会偏向于high-degree点的弊病，
	 * 使得整个算法性能更好
	 * 算法思想：首先随机选择一个点，并且该点的度不为0，当前选中的点被作为种子点seed，定义当前的代价函数为：
	 * Q(v) = k，其中k表示当前节点v的度；接着从v的邻居点中随机的选择一个节点w，接着从(0,1)的均匀分布中随机
	 * 生成一个数p，如果p<=Q(v)/Q(w)，则w被加入到样本中，否则仍然待在v点。
	 * 经过当前的测试来看，该算法的性能确实相当的好
	 */  
	
	@Override
	public String mhrwSampling() throws Exception {
		int nodePage = 1 ;    //标识第几页，从第一页开始
		int nodePageSize = 1000;  //标识点表每一页包含的记录数，设置为1000
		long nodeTotal = 0; //保存点表中总的记录数
		long nodePageTotalNumber = 1 ; //记录点表分页之后的总页数
		long sampleNodeCount = 0;   //抽样的点的总数目
	    double proportion = 0.15;  //代表要取出的点的比例，目前设置为要取出15%的点
	         
		while(nodePage <= nodePageTotalNumber){  //如果当前页数小于等于总的页数时，执行循环
			PageHelper.startPage(nodePage, nodePageSize);   //分页
			List<Nodes> listNodeAllData = samplingDao.listNodeAllData();
			
	        //获取数据库中点表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(nodePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Nodes> pageInfo = new PageInfo<Nodes>(listNodeAllData);
		        nodeTotal = pageInfo.getTotal(); //获取总记录数
		        //System.out.println("总记录数为:" + total);
		        nodePageTotalNumber = nodeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的点表总页数为:" + nodePageTotalNumber);
			}
			
			//生成一组随机数，这组随机数表示要取出的点
		    sampleNodeCount = (long) ((nodeTotal * proportion) + 1) ;
		    nodePage++;  //加1结束整个while循环
		} //抽样点的个数统计完毕
		
		Map<String, Nodes> nodeMap = new HashMap<String, Nodes>();   //存放抽样的Node
		List<Edges> edgeList = new ArrayList<Edges>();   //存放edge到List中
		Map<String, Nodes> outNodeMap = new HashMap<String, Nodes>();  //存放被淘汰的邻居点
		
		long nextLong = ThreadLocalRandom.current().nextLong(nodeTotal);   //从[0,nodeTotal)中随机选择一个数字
		Nodes node = samplingDao.selectOneNode(nextLong);   //得到该点
		
		while(nodeMap.size() < sampleNodeCount){	//核心抽样逻辑开始
			//如果没有得到抽样的总数 并且nodeMap不包含当前的key
			nodeMap.put(node.getId(), node);    //将当前点加入到nodeMap中
			List<Nodes> neighborNode = samplingDao.getNeighborNode(node);  //获取当前点的所有邻居点
		    //将上述List转换为Array，便于在邻居点中随意选择一个点
			Nodes[] nodes = new Nodes[neighborNode.size()];
			Nodes[] nodeArray = neighborNode.toArray(nodes);
			
			int size = 0 ;
			boolean flag = false;
		    //从邻居点中随机获取一个，并且该点的度不为0，也就是存在邻居点
		   while(neighborNode.size() != 0 && size <= neighborNode.size()){
				//说明存在邻居点，在邻居点中随便选择一个决定邻居点是否要被加入到nodeMap中
				Random random = new Random();
				int nextInt = random.nextInt(neighborNode.size());  //产生[0,neighbor.size)之间的随机数
				Nodes nodesneighbor = nodeArray[nextInt];   //随机取出其中的一个邻居点
				double randomNeighbor = Math.random();  //随机产生一个(0,1)之间的随机数
				double precision = (double)(node.getNodeDegree())/(double)(nodesneighbor.getNodeDegree());
				if(randomNeighbor <= precision){
					//说明该点要被加入到抽样节点中
					nodeMap.put(nodesneighbor.getId(), nodesneighbor);
					node = nodeArray[nextInt];   //此时node应该转变为当前的邻居点
					size++;
					flag = true;
					break;
	 			}else {
					outNodeMap.put(nodesneighbor.getId(), nodesneighbor);
					size++;
				}
			}
		   
		   //当判断完邻居点之后，此时应该做处理，究竟node是随机挑选还是上面已经指定好
		   if(flag){
			   //说明在上面对node进行了修改，不处理即可
		   }else {
			   //System.out.println("重新寻找种子点");
			   //否则说明我们应该自行生成一个随机的种子点
			   while(true){
				   nextLong = ThreadLocalRandom.current().nextLong(nodeTotal);   //从[0,nodeTotal)中随机选择一个数字
				   node = samplingDao.selectOneNode(nextLong);   //得到该点
				   if(nodeMap.containsKey(node.getId())){
					   continue;
				   }else {
					   break;
				}
			   }
		   }
		}   //核心抽样逻辑结束
		
		//开始边表的遍历，当sourceNode和targetNode都是上面抽样出来的点时，这条边要被抽出
		//结合NodeSampling()抽样方法，是的当前的随机游走算法性能更好
		int edgePage = 1;   //标识第几页，从第一页开始
		int edgePageSize = 1000; //标识边表每一页包含的记录数，初始设置为1000
		long edgeTotal = 0 ; //保存边表中的总记录数目
		long edgePageTotalNumber = 1 ; //记录边表分页之后的总页数
		
		while(edgePage <= edgePageTotalNumber){  //边表抽样开始
			PageHelper.startPage(edgePage,edgePageSize);
			List<Edges> listEdgeAllData = samplingDao.listEdgeAllData();
			
			//获取数据库中边表的总记录数，并且在整个循环中，该段代码只在获取第一页时被执行一次即可
			if(edgePage == 1){   //只有在获取第一页时，才计算总记录数
				PageInfo<Edges> pageInfo = new PageInfo<Edges>(listEdgeAllData);
		        edgeTotal = pageInfo.getTotal(); //获取边表中的总记录数
		        //System.out.println("总记录数为:" + total);
		        edgePageTotalNumber = edgeTotal/1000 + 1; //总页数要加1，因为可能有不满一页的情况存在
		        //System.out.println("当前查询的边表总页数为:" + edgePageTotalNumber);
			}
			
			int edgePageSizePer = listEdgeAllData.size();
			for (int i = 0; i < edgePageSizePer; i++) {
				Edges edges = listEdgeAllData.get(i);
				if (nodeMap.containsKey(edges.getSourceNodeID()) && nodeMap.containsKey(edges.getTargetNodeID())) {
					//此时该条边应该被取出
					edgeList.add(edges);
				}else {
					continue;
				}
			}
			edgePage++;
		}  //边表的抽样完毕
		
		/*System.out.println("抽出的点的个数为:" + nodeMap.size());
		System.out.println("抽出的边的个数为:" + edgeList.size());*/
		
		//构造出JSON字符串，并将结果返回给控制器用于展示
	    StringBuilder jsonString = new StringBuilder();
		NodeDataVO data1 = null;
		//遍历nodeMap，将所有的数据取出，构造成JSON
		Iterator<Entry<String, Nodes>> nodeIterators = nodeMap.entrySet().iterator();
		while(nodeIterators.hasNext()){
			Entry<String, Nodes> next = nodeIterators.next();
			String key = next.getKey();
			Nodes value = next.getValue();
			data1 = new NodeDataVO(key,value.getNodeName(),value.getNodeDegree());
			NodeVO nodeVo1 = new NodeVO(data1, "nodes",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(nodeVo1);
			jsonString.append(jsonString1 + ",");
		}  //抽样出的点添加完毕
		
	    //处理抽样出的边
		int edgeSize = edgeList.size();
		for (int i = 0; i < edgeSize ; i++) {
			Edges edges = edgeList.get(i);
			EdgeDataVO data3 = new EdgeDataVO(edges.getId(), edges.getSourceNodeID(), edges.getTargetNodeID(), 1);
			EdgeVO edgeVo = new EdgeVO(data3, "edges",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(edgeVo);
			jsonString.append(jsonString1 + ",");
		}
		String jsonOutput = "[" + jsonString + "]" ;   
		return jsonOutput;
	}
	
	/**  
	 * @see com.uniplore.graph.sampling.service.ISampleService#forestFireSampling()
	 * 森林火灾抽样算法  
	 */  
	
	@Override
	public String forestFireSampling() throws Exception {
		
		return null;
	}

	/**  
	 * @see com.uniplore.graph.sampling.service.ISampleService#streamingNodeSampling()  
	 */  
	
	@Override
	public String streamingNodeSampling() throws Exception {
		
		return null;
	}

	/**  
	 * @see com.uniplore.graph.sampling.service.ISampleService#streamingEdgeSampling()  
	 */  
	
	@Override
	public String streamingEdgeSampling() throws Exception {
		
		return null;
	}

	/**  
	 * @see com.uniplore.graph.sampling.service.ISampleService#streamingTopologySampling() 
	 */  
	
	@Override
	public String streamingTopologySampling() throws Exception {
		
		return null;
	}
}
