/**    
* @Title         SamplingDao.java  
* @Package       com.uniplore.graph.sampling.dao.Impl  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月8日 上午10:06:24  
* @version       1.0    
*/ 
package com.uniplore.graph.sampling.dao.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uniplore.graph.sampling.dao.ISamplingDao;
import com.uniplore.graph.sampling.entity.Edges;
import com.uniplore.graph.sampling.entity.Nodes;
import com.uniplore.graph.sampling.mapper.SamplingMapper;

import jdk.nashorn.internal.ir.ReturnNode;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.sampling.dao.Impl.SamplingDao       
 * 创建人    朱君鹏
 * 创建时间  2017年8月8日 上午10:06:24     
 * 修改人  
 * 修改时间  2017年8月8日 上午10:06:24     
 * 修改备注     
 * @version  1.0      
 */

@Repository
public class SamplingDao implements ISamplingDao{

	@Autowired
	private SamplingMapper samplingMapper;
	/**  
	 * @see com.uniplore.graph.sampling.dao.ISamplingDao#listAllData()  
	 */  
	
	@Override
	public List<Nodes> listNodeAllData() throws Exception {
		return samplingMapper.listNodeAllData();
	}
	/**  
	 * @see com.uniplore.graph.sampling.dao.ISamplingDao#listEdgeAllData()  
	 */  
	
	@Override
	public List<Edges> listEdgeAllData() throws Exception {
		return samplingMapper.listEdgeAllData();
	}
	/**  
	 * @see com.uniplore.graph.sampling.dao.ISamplingDao#selectOneEdge(long)  
	 */  
	
	@Override
	public Edges selectOneEdge(Long nextLong) throws Exception {
		return samplingMapper.selectOneEdge(nextLong);
	}
	/**  
	 * @see com.uniplore.graph.sampling.dao.ISamplingDao#getNeighbor(com.uniplore.graph.sampling.entity.Nodes)  
	 */  
	
	@Override
	public List<Edges> getNeighbor(Nodes node) throws Exception {
		return samplingMapper.getNeighbor(node);
	}
	/**  
	 * @see com.uniplore.graph.sampling.dao.ISamplingDao#selectOneNode(long)  
	 */  
	
	@Override
	public Nodes selectOneNode(Long nextLong) throws Exception {
		return samplingMapper.selectOneNode(nextLong);
	}
	/**  
	 * @see com.uniplore.graph.sampling.dao.ISamplingDao#getNeighborNode(com.uniplore.graph.sampling.entity.Nodes)  
	 */  
	
	@Override
	public List<Nodes> getNeighborNode(Nodes node) throws Exception {
		List<Edges> neighbor = samplingMapper.getNeighbor(node);
		List<Nodes> nodeList = new ArrayList<Nodes>();
		//遍历上述neighbor，根据id找到所有的邻居点
		Iterator<Edges> iterator = neighbor.iterator();
		while(iterator.hasNext()){
			//开始遍历取出每一条边
			Edges next = iterator.next();
			if (next.getSourceNodeID() != node.getId()) {
				//说明source点是邻居点
				//根据sourceID去查找对应点的全部数据
				Nodes nodeByID = samplingMapper.selectByID(next.getSourceNodeID());
				nodeList.add(nodeByID);
			}
			
			if (next.getTargetNodeID() != node.getId()) {
				//说明source点是邻居点
				//根据sourceID去查找对应点的全部数据
				Nodes nodeByID = samplingMapper.selectByID(next.getTargetNodeID());
				nodeList.add(nodeByID);
			}
		}
		return nodeList;
	}

}
