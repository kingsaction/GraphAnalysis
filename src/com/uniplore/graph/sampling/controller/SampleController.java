/**    
* @Title         SampleController.java  
* @Package       com.uniplore.graph.sampling  
* @Description   网络抽样算法  
* @author        朱君鹏     
* @date          2017年8月7日 下午8:38:11  
* @version       1.0    
*/ 
package com.uniplore.graph.sampling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述    该类中实现了8个已经被证明高效的网络抽样算法，之后可能会在这些算法的基础之上改进，最好能够提出一种
 *          更加高效的网络抽样算法
 * 类名称    com.uniplore.graph.sampling.Sample       
 * 创建人    朱君鹏
 * 创建时间  2017年8月7日 下午8:38:11     
 * 修改人  
 * 修改时间  2017年8月8日 上午9:03:15   
 * 修改备注     增加了新的抽样算法处理器借口
 * @version  1.0      
 */

@Controller
@RequestMapping(value = "/sampling")
public class SampleController {
	
	/**
	 * 
	 * @Title  nodeSampling  
	 * @Description TODO  点抽样算法  
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/NSampling", method = {RequestMethod.POST})
	public @ResponseBody String nodeSampling() throws Exception{
		System.out.println("哈哈，选中了点抽样算法");
		return null;
	}
	
	/**
	 * 
	 * @Title  edgeSampling  
	 * @Description TODO 边抽样算法
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception   统一异常处理
	 */
	@RequestMapping(value = "/ESampling", method = {RequestMethod.POST})
	public @ResponseBody String edgeSampling() throws Exception{
		System.out.println("哈哈，选中了边抽样算法");
		return null;
	}
	
	/**
	 * 
	 * @Title  topologySampling  
	 * @Description TODO 拓扑抽样算法  
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/TSampling", method = {RequestMethod.POST})
	public @ResponseBody String topologySampling() throws Exception{
		System.out.println("哈哈，选中了拓扑抽样算法");
		return null;
	}
	
	/**
	 * 
	 * @Title  randomWalkSampling  
	 * @Description TODO 随机游走抽样算法  
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/RWSampling", method = {RequestMethod.POST})
	public @ResponseBody String randomWalkSampling() throws Exception{
		System.out.println("哈哈，选中了随机游走抽样算法");
		return null;
	}
	
	/**
	 * 
	 * @Title  forestFireSampling  
	 * @Description TODO 森林火灾抽样算法  
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception   统一异常处理
	 */
	@RequestMapping(value = "/FFSampling", method = {RequestMethod.POST})
	public @ResponseBody String forestFireSampling() throws Exception{
		System.out.println("哈哈，选中了森林火灾抽样算法");
		return null;
	}
	
	/**
	 * 
	 * @Title  edgeISampling  
	 * @Description TODO 改进后的边抽样算法  
	 * @return 返回JSON字符串，交给前端渲染展示
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/EiSampling", method = {RequestMethod.POST})
	public @ResponseBody String edgeISampling() throws Exception{
		System.out.println("哈哈，改进后的边抽样算法");
		return null;
	}
	
	/**
	 * 
	 * @Title  streamingNodeSampling  
	 * @Description TODO 流式点抽样算法  
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/SNSampling", method = {RequestMethod.POST})
	public @ResponseBody String streamingNodeSampling() throws Exception{
		System.out.println("哈哈，流式点抽样算法");
		return null;
	}
	
	/**
	 * 
	 * @Title  streamingEdgeSampling  
	 * @Description TODO 流式边抽样算法
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/SESampling", method = {RequestMethod.POST})
	public @ResponseBody String streamingEdgeSampling() throws Exception{
		System.out.println("哈哈，流式边抽样算法");
		return null;
	}
	
	/**
	 * 
	 * @Title  streamingTopologySampling  
	 * @Description TODO 流式拓扑结构抽样算法 
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/STSampling", method = {RequestMethod.POST})
	public @ResponseBody String streamingTopologySampling() throws Exception{
		System.out.println("哈哈，流式拓扑结构抽样算法");
		return null;
	}
	
	
}
