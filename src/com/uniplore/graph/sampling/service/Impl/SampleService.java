/**    
* @Title         SampleService.java  
* @Package       com.uniplore.graph.sampling.service.Impl  
* @Description   TODO(用一句话描述该文件做什么)  
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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
	 * @see com.uniplore.graph.sampling.service.ISampleService#nodeSamplig()
	 * 该抽样算法的实现针对static graph 和 large graph，数据刚开始全部存放在数据库中
	 * 并且需要说明的是抽样比例15%指的是从点表数据中抽出总体的15%，不是针对边表
     * 具体的实现思路是：首先扫描点表，统计整个点表中总共有多少个点，然后使用随机函数生成一个随机序列；
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
	 * @see com.uniplore.graph.sampling.service.ISampleService#edgeSampling()
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
	 * @see com.uniplore.graph.sampling.service.ISampleService#edgeISampling() 
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
	 * @see com.uniplore.graph.sampling.service.ISampleService#topologySampling() 
	 * 基于拓扑结构的抽样算法，主要采用了随机游走和宽度优先遍历技术，大体思路是：首先随机的选择一个种子点，
	 * 然后根据随机游走和宽度优先遍历技术，在该点的邻居点中随机的选择两个点，将新选出的点和边全部都加到样本中
	 * 迭代这个过程，直到满足样本的大小 
	 */ 
	@Override
	public String topologySampling() throws Exception {
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
		
		//将抽样出的点和边构造成JSON格式
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
}
