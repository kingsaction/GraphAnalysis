/**    
* @Title         ClusterAlgorithmsController.java  
* @Package       com.uniplore.graph.clusteralgorithms.controller  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月7日 下午9:14:40  
* @version       1.0    
*/ 
package com.uniplore.graph.clusteralgorithms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.clusteralgorithms.controller.ClusterAlgorithmsController       
 * 创建人    朱君鹏
 * 创建时间  2017年8月7日 下午9:14:40     
 * 修改人  
 * 修改时间  2017年8月7日 下午9:14:40     
 * 修改备注     
 * @version  1.0      
 */

@Controller
@RequestMapping(value = "/algorithms")
public class ClusterAlgorithmsController {
	
	/**
	 * 
	 * @Title  MCLAlgorithm  
	 * @Description TODO 实现增量马尔科夫聚类算法
	 * @return 返回JSON字符串，交给前端渲染结果
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/IMCLAlgorithm" , method = RequestMethod.POST)
	public @ResponseBody String IMCLAlgorithm() throws Exception{
		System.out.println("增量马尔科夫聚类算法");
		return null;
	}
	
	/**
	 * 
	 * @Title  MCLAlgorithm  
	 * @Description TODO 普通markov聚类算法  
	 * @return  返回JSON字符串，交给前端渲染结果
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/MCLAlgorithm" , method = RequestMethod.POST)
	public @ResponseBody String MCLAlgorithm() throws Exception{
		System.out.println("马尔科夫聚类算法");
		return null;
	}
	
	/**
	 * 
	 * @Title  SCAlgorithm  
	 * @Description TODO 谱聚类算法  
	 * @return  返回JSON字符串，交给前端渲染结果
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/SCAlgorithm" , method = RequestMethod.POST)
	public @ResponseBody String SCAlgorithm() throws Exception{
		System.out.println("谱聚类算法");
		return null;
	}
	
	
}
