/**    
* @Title         SamplingMapper.java  
* @Package       com.uniplore.graph.sampling.mapper  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月8日 上午10:12:15  
* @version       1.0    
*/ 
package com.uniplore.graph.sampling.mapper;

import java.util.List;

import com.uniplore.graph.sampling.entity.Edges;
import com.uniplore.graph.sampling.entity.Nodes;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.sampling.mapper.SamplingMapper       
 * 创建人    朱君鹏
 * 创建时间  2017年8月8日 上午10:12:15     
 * 修改人  
 * 修改时间  2017年8月8日 上午10:12:15     
 * 修改备注     
 * @version  1.0      
 */

public interface SamplingMapper {

	/**  
	 * @Title  listNodeAllData  
	 * @Description TODO  
	 * @return   
	 */ 
	public List<Nodes> listNodeAllData()throws Exception;

	/**  
	 * @Title  listEdgeAllData  
	 * @Description TODO  
	 * @return   
	 */ 
	public List<Edges> listEdgeAllData() throws Exception;

}
