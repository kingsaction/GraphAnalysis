/**    
* @Title         SampleController.java  
* @Package       com.uniplore.graph.sampling  
* @Description   网络抽样算法  
* @author        朱君鹏     
* @date          2017年8月7日 下午8:38:11  
* @version       1.0    
*/ 
package com.uniplore.graph.sampling.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.uniplore.graph.sampling.service.ISampleService;

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
	
	@Autowired
	private ISampleService sample;
	/**
	 * 
	 * @Title  nodeSampling  
	 * @Description TODO  点抽样算法，均匀随机的进行点抽样，假设抽样的规模为15%，后续会调整这个参数，
	 *                      测试不同抽样规模对抽样算法性能的影响。在经典的点抽样算法中，点被独立均匀随机的选择
	 *                      ，首先随机的得到所有的抽样点，然后将这些点组成的所有边抽出。点抽样是符合直觉的并且是相对
	 *                      比较直接的，这个工作在2005年被提出。相似的工作在2006年再次指出，点随机抽样能够很好的
	 *                      抓住不同的点的度，但是很明显的缺陷是，点随机抽样不能很好的保留原图的连通性，因此在图聚类中
	 *                      ，不推荐使用
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/NSampling", method = {RequestMethod.POST})
	public @ResponseBody String nodeSampling() throws Exception{
		//System.out.println("选中了点抽样算法");
		String nodeSamplig = sample.nodeSampling();
		
		//将上述字符串重新解析
	    Object parse = JSON.parse(nodeSamplig);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
		return outputString;
	}
	
	@RequestMapping(value = "/INSampling" , method = {RequestMethod.POST})
	public @ResponseBody String improveNodeSampling() throws Exception{
		//System.out.println("选中了改进点抽样算法");
		String improveNodeSampling = sample.improveNodeSampling();
		
		//将上述字符串重新解析
	    Object parse = JSON.parse(improveNodeSampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
		return outputString;
	}
	/**
	 * 
	 * @Title  randomPageRankNodeSampling  
	 * @Description TODO 随机PageRank节点抽样算法，有偏抽样算法，每个节点被取出的概率不同，根据PageRank
	 *                     计算节点的重要性，根据重要性进行抽样  
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/RPNSampling" , method = {RequestMethod.POST})
	public @ResponseBody String randomPageRankNodeSampling()throws Exception{
		//System.out.println("选中了随机PageRank点抽样算法");
		String randomPageRankNodeSampling = sample.randomPageRankNodeSampling();
		
		//将上述字符串重新解析
	    Object parse = JSON.parse(randomPageRankNodeSampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
		return outputString;
	}
	
	/**
	 * 
	 * @Title  randomDegreeNodeSampling  随机度点抽样算法
	 * @Description TODO   该算法是有偏抽样算法，根据节点的度来确定每个点
	 *                       被抽到的概率  
	 * @return 返回JSON字符串，用于前端渲染
	 * @throws Exception 统一异常处理
	 */ 
	@RequestMapping(value = "/RDNSampling" ,method = {RequestMethod.POST})
	public @ResponseBody String randomDegreeNodeSampling() throws Exception{
		//System.out.println("选中了随机度点抽样算法");
		String randomDegreeNodeSampling = sample.randomDegreeNodeSampling();
		
		//将上述字符串重新解析
	    Object parse = JSON.parse(randomDegreeNodeSampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
		return outputString;
	}
	/**
	 * 
	 * @Title  edgeSampling  
	 * @Description TODO 边抽样算法，均匀随机的进行边抽样，假设抽样的规模为15%，后续会调整这个参数
	 *                     测试不同抽样规模对抽样算法性能的影响。在经典的边随机抽样算法中，边被均匀、独立
	 *                     随机的抽取出来。因为边随机抽样主要集中在边的随机选择上，点只是当边被选择之后，
	 *                     包含的点也随之被加入到样本中。很显然，边随机抽样不能很好的保持图的属性，由于边被独立的选择
	 *                     ，因此对于聚类和连通性都不能很好的保留。
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception   统一异常处理
	 */
	@RequestMapping(value = "/ESampling", method = {RequestMethod.POST})
	public @ResponseBody String edgeSampling() throws Exception{
		//System.out.println("选中了边抽样算法");
		String edgeSampling = sample.edgeSampling();
		//将上述字符串重新解析
	    Object parse = JSON.parse(edgeSampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
		return outputString;
	}
	
	/**
	 * 
	 * @Title  edgeISampling  
	 * @Description TODO 改进后的边抽样算法，该抽样算法是在edgeSampling()算法的基础之上改进的，改进之后的效果
	 *                     相当的好，其实这个算法首先应用了EdgeSampling()算法的思想，之后有应用了nodeSampling()
	 *                     算法的思想，这样得出的图保留了更多图本身的属性
	 * @return 返回JSON字符串，交给前端渲染展示
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/EiSampling", method = {RequestMethod.POST})
	public @ResponseBody String edgeISampling() throws Exception{
		//System.out.println("改进后的边抽样算法");
		String edgeISampling = sample.edgeISampling();
		//将上述字符串重新解析
	    Object parse = JSON.parse(edgeISampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
	    //System.out.println("整个过程结束");
		return outputString;
	}
	
	/**
	 * 
	 * @Title  randomNeighborNodeSampling 
	 * @Description TODO 随机临近点抽样算法
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/RNNSampling", method = {RequestMethod.POST})
	public @ResponseBody String randomNeighborNodeSampling() throws Exception{
		//System.out.println("选中了拓扑抽样算法");
		String randomNeighborNodeSampling = sample.randomNeighborNodeSampling();
		//将上述字符串重新解析
	    Object parse = JSON.parse(randomNeighborNodeSampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
		return outputString;
	}
	
	/**
	 *  Random Breadth First Search Sampling
	 * @Title  randomWalkSampling  
	 * @Description TODO 基于随机游走的抽样算法  
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/RBFSampling", method = {RequestMethod.POST})
	public @ResponseBody String randomBreadthFirstSampling() throws Exception{
		//System.out.println("选中了随机广度优先遍历抽样算法");
		String randomBreadthFirstSampling = sample.randomBreadthFirstSampling();
		//将上述字符串重新解析
	    Object parse = JSON.parse(randomBreadthFirstSampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
		return outputString;
	}
	
	/**
	 * 
	 * @Title  randomDepthFirstSampling  
	 * @Description TODO 基于的深度优先搜索的抽样算法  
	 * @return 返回JSON格式数据，交给前端渲染显示
	 * @throws Exception 统一异常处理
	 */
	@RequestMapping(value = "/RDFSampling" , method = RequestMethod.POST)
	public @ResponseBody String randomDepthFirstSampling()throws Exception{
		//System.out.println("选中了随机深度优先遍历抽样算法");
		String randomDepthFirstSampling = sample.randomDepthFirstSampling();
		//将上述字符串重新解析
	    Object parse = JSON.parse(randomDepthFirstSampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
		return outputString;
	}
	
	/**
	 * 
	 * @Title  randomWalkSampling  
	 * @Description TODO 基于随机游走的抽样算法
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception
	 */
	@RequestMapping(value = "/RWSampling" , method = {RequestMethod.POST})
	public @ResponseBody String randomWalkSampling() throws Exception{
		//System.out.println("选中了拓扑抽样算法");
		String randomWalkSampling = sample.randomWalkSampling();
		//将上述字符串重新解析
	    Object parse = JSON.parse(randomWalkSampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
		return outputString;
	}
	
	@RequestMapping(value = "/MHRWSampling" , method = {RequestMethod.POST})
	public @ResponseBody String mhrwSampling() throws Exception{
		//System.out.println("选中了马尔科夫链门特卡罗抽样算法");
		String mhrwSampling = sample.mhrwSampling();
		//将上述字符串重新解析
	    Object parse = JSON.parse(mhrwSampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
		return outputString;
	}
	
	@RequestMapping(value = "/FSampling" , method = {RequestMethod.POST})
	public @ResponseBody String frontierSampling()throws Exception{
		//System.out.println("选中了FS抽样算法");
		String frontierSampling = sample.frontierSampling();
		//将上述字符串重新解析
	    Object parse = JSON.parse(frontierSampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
		return outputString;
	}
	/**
	 * 
	 * @Title  forestFireSampling  森林火灾抽样算法
	 * @Description TODO 森林火灾抽样算法  
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception
	 */
	@RequestMapping(value = "/FFSampling", method = {RequestMethod.POST})
	public @ResponseBody String forestFireSampling() throws Exception{
		//System.out.println("选中了森林火灾抽样算法");
		String forestFireSampling = sample.forestFireSampling();
		//将上述字符串重新解析
	    Object parse = JSON.parse(forestFireSampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
		return outputString;
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
		//System.out.println("流式点抽样算法");
		String streamingNodeSampling = sample.streamingNodeSampling();
		//将上述字符串重新解析
	    Object parse = JSON.parse(streamingNodeSampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
	    //System.out.println("整个过程结束");
		return outputString;
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
		//System.out.println("流式点抽样算法");
		String streamingEdgeSampling = sample.streamingEdgeSampling();
		//将上述字符串重新解析
	    Object parse = JSON.parse(streamingEdgeSampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
	    //System.out.println("整个过程结束");
		return outputString;
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
		//System.out.println("流式拓扑结构抽样算法");
		String streamingTopologySampling = sample.streamingTopologySampling();
		//将上述字符串重新解析
	    Object parse = JSON.parse(streamingTopologySampling);
	    String outputString = parse.toString();
	    //System.out.println("返回的字符串为：" + outputString);
	    //System.out.println("整个过程结束");
		return outputString;
	}
	
	
}
