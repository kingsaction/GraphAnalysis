/**    
* @Title         EstimationController.java  
* @Package       com.uniplore.graph.estimation.controller  
* @Description   TODO 对网络参数进行评估，评估项目包括： 节点的度分布，平均度，平均度偏差，边的密度
*                       直径，聚类系数，平均聚类系数
* @author        朱君鹏     
* @date          2017年8月19日 上午9:13:53  
* @version       1.0    
*/ 
package com.uniplore.graph.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniplore.graph.estimation.service.IEstimationService;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.estimation.controller.EstimationController       
 * 创建人    朱君鹏
 * 创建时间  2017年8月19日 上午9:13:53     
 * 修改人  
 * 修改时间  2017年8月19日 上午9:13:53     
 * 修改备注     
 * @version  1.0      
 */

@Controller
@RequestMapping(value = "/estimations")
public class EstimationController {

	@Autowired 
	private IEstimationService estimationService;
	
	/**
	 * 
	 * @Title  Estimation   
	 * @Description TODO 计算网络中的统计参数 
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/ParaEstimation",method={RequestMethod.POST})
	public void Estimation() throws Exception{
		 estimationService.Estimation();
	}
}
