/**    
* @Title         BufferNodeMapper.java  
* @Package       com.uniplore.graph.dsm.db.mapper  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月5日 下午4:06:42  
* @version       1.0    
*/ 
package com.uniplore.graph.dsm.db.mapper;

import com.uniplore.graph.dsm.db.entity.Node;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.dsm.db.mapper.BufferNodeMapper       
 * 创建人    朱君鹏
 * 创建时间  2017年8月5日 下午4:06:42     
 * 修改人  
 * 修改时间  2017年8月5日 下午4:06:42     
 * 修改备注     
 * @version  1.0      
 */

public interface BufferNodeMapper {

	/**  
	 * @Title  deleteNodeData  
	 * @Description TODO 在缓存数据之前首先将数据库中的所有数据删除     
	 */ 
	public void deleteNodeData();

	/**  
	 * @Title  deleteEdgeData  
	 * @Description TODO 在缓存数据之前首先将数据库中的所有数据删除     
	 */ 

	/**  
	 * @Title  insertNodeData  
	 * @Description TODO(这里用一句话描述这个方法的作用)  
	 * @param node   
	 */ 
	public void insertNodeData(Node node);

}
