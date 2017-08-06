/**    
* @Title         BufferEdgeDao.java  
* @Package       com.uniplore.graph.dsm.db.dao.Impl  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月5日 下午5:14:35  
* @version       1.0    
*/ 
package com.uniplore.graph.dsm.db.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uniplore.graph.dsm.db.dao.IBufferEdgeDao;
import com.uniplore.graph.dsm.db.entity.Edge;
import com.uniplore.graph.dsm.db.mapper.BufferEdgeMapper;
import com.uniplore.graph.dsm.db.mapper.BufferNodeMapper;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.dsm.db.dao.Impl.BufferEdgeDao       
 * 创建人    朱君鹏
 * 创建时间  2017年8月5日 下午5:14:35     
 * 修改人  
 * 修改时间  2017年8月5日 下午5:14:35     
 * 修改备注     
 * @version  1.0      
 */

@Repository
public class BufferEdgeDao implements IBufferEdgeDao {

	@Autowired
	private BufferEdgeMapper bufferEdgeMapper;
	/**  
	 * @see com.uniplore.graph.dsm.db.dao.IBufferEdgeDao#insertEdgeData(com.uniplore.graph.dsm.db.entity.Edge)  
	 */  
	
	@Override
	public void insertEdgeData(Edge edge) {
		bufferEdgeMapper.insertEdgeData(edge);
	}
	/**  
	 * @see com.uniplore.graph.dsm.db.dao.IBufferEdgeDao#deleteEdgeData()  
	 */  
	
	@Override
	public void deleteEdgeData() {
		bufferEdgeMapper.deleteEdgeData();
	}

}
