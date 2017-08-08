/**    
* @Title         ISamplingDao.java  
* @Package       com.uniplore.graph.sampling.dao  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月8日 上午10:05:58  
* @version       1.0    
*/ 
package com.uniplore.graph.sampling.dao;

import java.util.List;

import com.uniplore.graph.sampling.entity.Edges;
import com.uniplore.graph.sampling.entity.Nodes;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.sampling.dao.ISamplingDao       
 * 创建人    朱君鹏
 * 创建时间  2017年8月8日 上午10:05:58     
 * 修改人  
 * 修改时间  2017年8月8日 上午10:05:58     
 * 修改备注     
 * @version  1.0      
 */

public interface ISamplingDao {
	/**  
	 * @Title  listNodeAllData  
	 * @Description TODO 列出所有的点数据  
	 * @return  返回List
	 * @throws Exception  统一异常处理
	 */ 
	public List<Nodes> listNodeAllData() throws Exception;

	/**
	 * 
	 * @Title  listEdgeAllData  
	 * @Description TODO 列出所有的边数据  
	 * @return  返回List
	 * @throws Exception  统一异常处理
	 */
	public List<Edges> listEdgeAllData() throws Exception;

}
