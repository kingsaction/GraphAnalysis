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
	public String nodeSamplig() throws Exception {
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
		
		
		while(edgePage <= edgePageTotalNumber){
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
				if (nodeMap.containsKey(edges.getSourceNode()) && nodeMap.containsKey(edges.getTargetNode())) {
					//此时该条边应该被取出
					edgeList.add(edges);
				}else {
					continue;
				}
			}
			edgePage++;
		}  //边表的抽样完毕
		
		/*System.out.println("点表抽取出来的记录数为:" + nodeMap.size());  //逻辑正确
		System.out.println("边表抽取出来的记录数为:" + edgeList.size());*/
		
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
			EdgeDataVO data3 = new EdgeDataVO(edges.getId(), edges.getSourceNode(), edges.getTargetNode(), 1);
			EdgeVO edgeVo = new EdgeVO(data3, "edges",false,false,true,false,false,true,"");
			String jsonString1 = JSON.toJSONString(edgeVo);
			jsonString.append(jsonString1 + ",");
		}
		String jsonOutput = "[" + jsonString + "]" ;   
		return jsonOutput;
	}
	
}
