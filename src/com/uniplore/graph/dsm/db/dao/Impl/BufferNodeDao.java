/**    
* @Title         IBufferNodeDao.java  
* @Package       com.uniplore.graph.dsm.db.dao.Impl  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月5日 下午4:02:52  
* @version       1.0    
*/ 
package com.uniplore.graph.dsm.db.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uniplore.graph.dsm.db.dao.IBufferNodeDao;
import com.uniplore.graph.dsm.db.entity.Node;
import com.uniplore.graph.dsm.db.mapper.BufferNodeMapper;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.dsm.db.dao.Impl.BufferNodeDao       
 * 创建人    朱君鹏
 * 创建时间  2017年8月5日 下午4:02:52     
 * 修改人  
 * 修改时间  2017年8月5日 下午4:02:52     
 * 修改备注     
 * @version  1.0      
 */

@Repository
public class BufferNodeDao implements IBufferNodeDao{

	@Autowired
	private BufferNodeMapper bufferNodeMapper;
	/**  
	 * @see com.uniplore.graph.dsm.db.dao.IBufferNodeDao#deleteNodeData()  
	 */  
	
	@Override
	public void deleteNodeData() {
		bufferNodeMapper.deleteNodeData();
	}
	
	/**  
	 * @see com.uniplore.graph.dsm.db.dao.IBufferNodeDao#insertNodeData(com.uniplore.graph.dsm.db.entity.Node)  
	 */  
	
	@Override
	public void insertNodeData(Node node) {
		bufferNodeMapper.insertNodeData(node);
	}
	
}
