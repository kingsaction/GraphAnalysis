/**    
* @Title         SamplingDao.java  
* @Package       com.uniplore.graph.sampling.dao.Impl  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月8日 上午10:06:24  
* @version       1.0    
*/ 
package com.uniplore.graph.sampling.dao.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uniplore.graph.sampling.dao.ISamplingDao;
import com.uniplore.graph.sampling.entity.Edges;
import com.uniplore.graph.sampling.entity.Nodes;
import com.uniplore.graph.sampling.mapper.SamplingMapper;

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

}
