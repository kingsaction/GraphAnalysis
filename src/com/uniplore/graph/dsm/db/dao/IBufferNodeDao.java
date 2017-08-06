/**    
* @Title         IBufferNodeDao.java  
* @Package       com.uniplore.graph.dsm.db.dao  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月5日 下午4:02:21  
* @version       1.0    
*/ 
package com.uniplore.graph.dsm.db.dao;

import com.uniplore.graph.dsm.db.entity.Node;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.dsm.db.dao.IBufferNodeDao       
 * 创建人    朱君鹏
 * 创建时间  2017年8月5日 下午4:02:21     
 * 修改人  
 * 修改时间  2017年8月5日 下午4:02:21     
 * 修改备注     
 * @version  1.0      
 */

public interface IBufferNodeDao {

	/**  
	 * @Title  deleteNodeData  
	 * @Description TODO 在缓存数据之前首先将点表中存放的数据全部删除   
	 */ 
	public void deleteNodeData();

	/**  
	 * @Title  insertNodeData  
	 * @Description TODO 将node值插入到中间数据库层中 
	 * @param node   
	 */ 
	public void insertNodeData(Node node); 

}
