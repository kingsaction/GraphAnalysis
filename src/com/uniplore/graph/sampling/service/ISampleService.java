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
	 * @return
	 * @throws Exception  统一异常处理
	 */
	public String nodeSampling() throws Exception;

	/**  
	 * @Title  edgeSampling  
	 * @Description TODO 边抽样service方法   
	 */ 
	public String edgeSampling() throws Exception;

	/**  
	 * @Title  edgeISampling  
	 * @Description TODO 改进边抽样service方法 
	 * @return   
	 */ 
	public String edgeISampling() throws Exception;

	/**  
	 * @Title  topologySampling  
	 * @Description TODO 基于拓扑结构的抽样技术
	 * @return   
	 */ 
	public String topologySampling() throws Exception;

}
