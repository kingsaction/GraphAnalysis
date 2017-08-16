/**    
* @Title         ISampleService.java  
* @Package       com.uniplore.graph.sampling.service  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月8日 上午9:57:19  
* @version       1.0    
*/ 
package com.uniplore.graph.sampling.service;

  
/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.sampling.service.ISampleService       
 * 创建人    朱君鹏
 * 创建时间  2017年8月8日 上午9:57:19     
 * 修改人  
 * 修改时间  2017年8月8日 上午9:57:19     
 * 修改备注     
 * @version  1.0      
 */

public interface ISampleService {

	/**
	 * 
	 * @Title  nodeSampling  
	 * @Description TODO 点随机抽样算法  
	 * @return  返回JSON字符串，用于前端展示
	 * @throws Exception  统一异常处理
	 */
	public String nodeSampling() throws Exception;

	/**  
	 * @Title  edgeSampling  
	 * @Description TODO 边抽样service方法
     * @return   返回JSON字符串，用于前端展示 
	 * @throws Exception  统一异常处理 
	 */ 
	public String edgeSampling() throws Exception;

	/**  
	 * @Title  edgeISampling  
	 * @Description TODO 改进边抽样service方法 
	 * @return   返回JSON字符串，用于前端展示
	 * @throws Exception  统一异常处理
	 */ 
	public String edgeISampling() throws Exception;

	/**  
	 * @Title  topologySampling  
	 * @Description TODO 随机邻居点抽样算法
	 * @return  返回JSON字符串，用于前端展示
	 * @throws Exception  统一异常处理
	 */ 
	public String randomNeighborNodeSampling() throws Exception;
	
	

	/**  
	 * @Title  randomDegreeNodeSampling  
	 * @Description TODO 随机度节点抽样算法  
	 * @return 返回JSON字符串，用于前端展示
	 * @throws Exception  统一异常处理
	 */ 
	public String randomDegreeNodeSampling() throws Exception;

	/**  
	 * @Title  randomPageRankNodeSampling  
	 * @Description TODO 随机PageRank节点抽样算法  
	 * @return   返回JSON字符串，用于前端渲染
	 * @throws Exception  统一异常处理
	 */ 
	public String randomPageRankNodeSampling() throws Exception;

	/**  
	 * @Title  randomWalkSampling  
	 * @Description TODO 基于随机游走的抽样算法
	 * @return  返回JSON字符串，用于前端渲染
	 * @throws Exception  统一异常处理
	 */ 
	public String randomWalkSampling() throws Exception;

	/**  
	 * @Title  mhrwSampling  
	 * @Description TODO 马尔科夫链蒙特卡洛方法
	 * @return   返回JSON数据，用于前端渲染
	 * @throws Exception  统一异常处理
	 */ 
	public String mhrwSampling() throws Exception;
	
	/**  
	 * @Title  forestFireSampling  
	 * @Description TODO 森林火灾抽样算法
	 * @return  返回JSON字符串，用于前端渲染
	 * @throws Exception  统一异常处理
	 */ 
	public String forestFireSampling() throws Exception;

	/**  
	 * @Title  streamingNodeSampling  
	 * @Description TODO 流式点抽样算法  
	 * @return   返回JSON字符串，用于前端渲染
	 * @throws Exception  统一异常处理
	 */ 
	public String streamingNodeSampling() throws Exception;

	/**  
	 * @Title  streamingEdgeSampling  
	 * @Description TODO 流式边抽样算法
	 * @throws Exception  统一异常处理
	 * @return   返回JSON字符串，用于前端渲染
	 */ 
	public String streamingEdgeSampling() throws Exception;

	/**  
	 * @Title  streamingTopologySampling  
	 * @Description TODO 流式拓扑结构抽样算法
	 * @throws Exception  统一异常处理
	 * @return    返回JSON字符串，用于前端渲染
	 */ 
	public String streamingTopologySampling() throws Exception;

	/**  
	 * @Title  randomBreadthFirstSearchSampling  
	 * @Description TODO 随机广度优先搜索抽样算法  
	 * @return    返回JSON格式数据，用于前端渲染
	 * @throws Exception  统一异常处理
	 */ 
	public String randomBreadthFirstSampling() throws Exception;

	/**  
	 * @Title  randomDepthFirstSampling  
	 * @Description TODO 随机深度优先搜索抽样算法 
	 * @return   返回JSON格式数据，用于前端渲染
	 * @throws Exception  统一异常处理
	 */ 
	public String randomDepthFirstSampling() throws Exception;

	/**  
	 * @Title  frontierSampling  
	 * @Description TODO FS 抽样算法，对RW算法的改进  
	 * @return   返回JSON格式数据，用于前端渲染
	 * @throws Exception  统一异常处理
	 */ 
	public String frontierSampling() throws Exception;


}
