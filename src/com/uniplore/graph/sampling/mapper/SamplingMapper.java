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
import com.uniplore.graph.sampling.entity.SamplingEdges;
import com.uniplore.graph.sampling.entity.SamplingNodes;

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
	 * @Description TODO  列出所有点表当中的数据
	 * @return  返回点表中所有的点
	 */ 
	public List<Nodes> listNodeAllData()throws Exception;

	/**  
	 * @Title  listEdgeAllData  
	 * @Description TODO  列出所有边表当中的数据
	 * @return   返回边表中所有的边
	 */ 
	public List<Edges> listEdgeAllData() throws Exception;

	/**  
	 * @Title  selectOneEdge  
	 * @Description TODO 从边表中根据指定的编号选出一条边
	 * @param nextInt  参数指的是要选出的边的位置品偏移
	 * @return  返回选出的边的信息
	 */ 
	public Edges selectOneEdge(Integer nextInt)throws Exception;

	/**  
	 * @Title  getNeighbor  
	 * @Description TODO 得到某个指定点的邻居点  
	 * @param node   由用户指定的点
	 * @return    返回所有的邻居点
	 */ 
	public List<Edges> getNeighbor(Nodes node)throws Exception;

	/**  
	 * @Title  selectOneNode  
	 * @Description TODO 根据指定的随机数随机选择一个点  
	 * @param nextLong  随机程序产生
	 * @return  返回指定的一个点
	 */  
	public Nodes selectOneNode(Long nextLong) throws Exception;

	/**
	 * 
	 * @Title  selectByID  
	 * @Description TODO 根据当前节点的ID返回节点的全部信息  
	 * @param id
	 * @return 返回当前节点对象
	 * @throws Exception 统一异常处理
	 */
	public Nodes selectByID(String id) throws Exception;

	/**  
	 * @Title  selectDegree  
	 * @Description TODO 选出指定度的点  
	 * @param degree  指定的度
	 * @return   返回指定度的所有节点
	 * @throws Exception 统一异常处理
	 */ 
	public Nodes selectHighDegree(Integer degree) throws Exception;

	/**  
	 * @Title  selectDegree  
	 * @Description TODO 选出度较小的点 
	 * @param degree  指定的度
	 * @return   返回所有的节点
	 * @throws Exception 统一异常处理
	 */ 
	public List<Nodes> selectDegree(Integer degree) throws Exception;

	/**  
	 * @Title  insertSamplingNode  
	 * @Description TODO 将抽样后的结果插入到数据库中  
	 * @param value
	 * @return   
	 */ 
	public void insertSamplingNode(SamplingNodes value) throws Exception;

	/**  
	 * @Title  insertSamplingEdge  
	 * @Description TODO 将抽样后的结果插入到数据库中
	 * @param spEdges
	 */ 
	public void insertSamplingEdge(SamplingEdges spEdges) throws Exception;

	/**  
	 * @Title  deleteSamplingNodes  
	 * @Description TODO 清空抽样表     
	 */ 
	public void deleteSamplingNodes() throws Exception;

	/**  
	 * @Title  deleteSamplingNodes  
	 * @Description TODO 清空抽样表     
	 */ 
	public void deleteSamplingEdges() throws Exception;

}
