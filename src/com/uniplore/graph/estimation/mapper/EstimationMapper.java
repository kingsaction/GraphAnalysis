/**    
* @Title         EstimationMapper.java  
* @Package       com.uniplore.graph.estimation.mapper  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月19日 上午9:10:58  
* @version       1.0    
*/ 
package com.uniplore.graph.estimation.mapper;

import java.util.List;

import com.uniplore.graph.estimation.entity.EdgeEstimation;
import com.uniplore.graph.estimation.entity.NodeEstimation;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.estimation.mapper.EstimationMapper       
 * 创建人    朱君鹏
 * 创建时间  2017年8月19日 上午9:10:58     
 * 修改人  
 * 修改时间  2017年8月19日 上午9:10:58     
 * 修改备注     
 * @version  1.0      
 */

public interface EstimationMapper {

	/**  
	 * @Title  listNodeAllData  
	 * @Description TODO 列出所有的点数据  
	 * @return   返回点数据
	 * throws Exception 统一异常处理
	 */ 
	public List<NodeEstimation> listNodeAllData() throws Exception;

	/**  
	 * @Title  listEdgeAllData  
	 * @Description TODO 列出所有的边数据  
	 * @return   返回边数据
	 * throws Exception 统一异常处理
	 */ 
	public List<EdgeEstimation> listEdgeAllData() throws Exception;
}
