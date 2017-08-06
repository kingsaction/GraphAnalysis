/**    
* @Title         IBufferEdgeDao.java  
* @Package       com.uniplore.graph.dsm.db.dao  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月5日 下午5:14:24  
* @version       1.0    
*/ 
package com.uniplore.graph.dsm.db.dao;

import com.uniplore.graph.dsm.db.entity.Edge;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.dsm.db.dao.IBufferEdgeDao       
 * 创建人    朱君鹏
 * 创建时间  2017年8月5日 下午5:14:24     
 * 修改人  
 * 修改时间  2017年8月5日 下午5:14:24     
 * 修改备注     
 * @version  1.0      
 */

public interface IBufferEdgeDao {

	/**  
	 * @Title  insertEdgeData  
	 * @Description TODO(这里用一句话描述这个方法的作用)  
	 * @param edge   
	 */ 
	public void insertEdgeData(Edge edge);

	/**  
	 * @Title  deleteEdgeData  
	 * @Description TODO(这里用一句话描述这个方法的作用)     
	 */ 
	public void deleteEdgeData();

}
