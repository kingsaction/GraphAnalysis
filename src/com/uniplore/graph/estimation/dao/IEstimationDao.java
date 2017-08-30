/**    
* @Title         IEstimationDao.java  
* @Package       com.uniplore.graph.estimation.dao  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月19日 上午9:15:09  
* @version       1.0    
*/ 
package com.uniplore.graph.estimation.dao;

import java.util.List;

import com.uniplore.graph.estimation.entity.DegreeDistribution;
import com.uniplore.graph.estimation.entity.EdgeEstimation;
import com.uniplore.graph.estimation.entity.NodeEstimation;
import com.uniplore.graph.sampling.entity.SamplingEdges;
import com.uniplore.graph.sampling.entity.SamplingNodes;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.estimation.dao.IEstimationDao       
 * 创建人    朱君鹏
 * 创建时间  2017年8月19日 上午9:15:09     
 * 修改人  
 * 修改时间  2017年8月19日 上午9:15:09     
 * 修改备注     
 * @version  1.0      
 */

public interface IEstimationDao {

	/**  
	 * @Title  listNodeAllData  
	 * @Description TODO 列出所有的点数据
	 * @return    返回点
	 * @throws Exception 统一异常处理
	 */  
	public List<NodeEstimation> listNodeAllData() throws Exception;

	/**  
	 * @Title  listEdgeAllData  
	 * @Description TODO 列出所有的边数据  
	 * @return   返回边
	 * @throws Exception 统一异常处理
	 */ 
	public List<EdgeEstimation> listEdgeAllData() throws Exception;

	/**  
	 * @Title  insertDegreeDistribution  
	 * @Description TODO 插入度分布数据 
	 * @throws Exception 统一异常处理    
	 */ 
	public void insertDegreeDistribution(DegreeDistribution degree) throws Exception;

	/**  
	 * @Title  deleteDegree  
	 * @Description TODO 删除degree_distribution表中所有的数据
	 * @throws Exception 统一异常处理
	 */ 
	public void deleteDegree() throws Exception;

	/**  
	 * @Title  listSamplingNodeAllData  
	 * @Description TODO 取出抽样后的点数据
	 * @return   
	 */ 
	public List<SamplingNodes> listSamplingNodeAllData() throws Exception;

	/**  
	 * @Title  listSamplingEdgeAllData  
	 * @Description TODO 列出所有抽样后的边数据  
	 * @return   
	 */ 
	public List<SamplingEdges> listSamplingEdgeAllData() throws Exception;

	/**  
	 * @Title  insertSamplingDegreeDistribution  
	 * @Description TODO 将抽样后的节点的度信息插入到数据库中 
	 * @param degree   
	 */ 
	public void insertSamplingDegreeDistribution(DegreeDistribution degree) throws Exception;

	/**  
	 * @Title  deleteSamplingDegree  
	 * @Description TODO 删除抽样后的度表中的数据    
	 */ 
	public void deleteSamplingDegree() throws Exception;

	/**  
	 * @Title  insertClusteringCoefficient  
	 * @Description TODO 未抽样数据的聚类系数分布  
	 * @param d   
	 */ 
	public void insertClusteringCoefficient(double d) throws Exception;

	/**  
	 * @Title  insertSamplingClusteringCoefficient  
	 * @Description TODO 抽样数据的聚类系数分布 
	 * @param d   
	 */ 
	public void insertSamplingClusteringCoefficient(double d) throws Exception;

	/**  
	 * @Title  deleteCoefficient  
	 * @Description TODO(这里用一句话描述这个方法的作用)     
	 */ 
	public void deleteCoefficient() throws Exception;

	/**  
	 * @Title  deleteSamplingCoefficient  
	 * @Description TODO(这里用一句话描述这个方法的作用)     
	 */ 
	public void deleteSamplingCoefficient() throws Exception;

}
