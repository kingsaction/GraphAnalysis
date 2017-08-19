/**    
* @Title         DegreeDistributionMapper.java  
* @Package       com.uniplore.graph.estimation.mapper  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月19日 下午3:21:53  
* @version       1.0    
*/ 
package com.uniplore.graph.estimation.mapper;

import com.uniplore.graph.estimation.entity.DegreeDistribution;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.estimation.mapper.DegreeDistributionMapper       
 * 创建人    朱君鹏
 * 创建时间  2017年8月19日 下午3:21:53     
 * 修改人  
 * 修改时间  2017年8月19日 下午3:21:53     
 * 修改备注     
 * @version  1.0      
 */

public interface DegreeDistributionMapper {

	/**  
	 * @Title  insertDegreeDistribution  
	 * @Description TODO 插入度分布数据
	 * @throws Exception 统一异常处理
	 */ 
	public void insertDegreeDistribution(DegreeDistribution degree) throws Exception;

	/**  
	 * @Title  deleteDegree  
	 * @Description TODO 删除degree_distribution表中的数据  
	 * @throws Exception 统一异常处理
	 */ 
	public void deleteDegree() throws Exception;

}
