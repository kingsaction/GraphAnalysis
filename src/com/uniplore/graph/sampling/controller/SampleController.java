/**    
* @Title         SampleController.java  
* @Package       com.uniplore.graph.sampling  
* @Description   网络抽样算法  
* @author        朱君鹏     
* @date          2017年8月7日 下午8:38:11  
* @version       1.0    
*/ 
package com.uniplore.graph.sampling.controller;

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
	 * @Title  topologySampling  
	 * @Description TODO 拓扑抽样算法，由于NS和ES算法本身的局限性，研究者们都在研究其它的基于拓扑结构的抽样
	 *                     ，比如使用宽度优先遍历技术（无放回抽样）、随机游走（有放回抽样），在这个算法中主要是
	 *                     考虑基于宽度优先遍历的拓扑结构抽样算法。算法首先还是每次随机的抽取一个点，并且被抽取
	 *                     之后的点状态要设置为0，标志此次抽样是无放回抽样技术，并且每次抽样希望能够在抽取完一个点
	 *                     后，能够将该点的邻居点也抽取出来（注意只抽取其中的一部分邻居点，通常情况下是两个邻居点，
	 *                     目前基于拓扑结构的抽样技术中，最好的就是FFS算法，在2006年被提出）。本段代码主要实现基于拓扑结构
	 *                     的抽样算法，将宽度优先遍历思想和FFS算法的思想结合在一起。
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/TSampling", method = {RequestMethod.POST})
	public @ResponseBody String topologySampling() throws Exception{
		//System.out.println("选中了拓扑抽样算法");
		String topologySampling = sample.topologySampling();
		//将上述字符串重新解析
	    Object parse = JSON.parse(topologySampling);
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
	 * @Title  streamingNodeSampling  
	 * @Description TODO 流式点抽样算法  
	 * @return  返回JSON字符串，交给前端渲染展示
	 * @throws Exception  统一异常处理
	 */
	@RequestMapping(value = "/SNSampling", method = {RequestMethod.POST})
	public @ResponseBody String streamingNodeSampling() throws Exception{
		//System.out.println("流式点抽样算法");
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
		//System.out.println("流式边抽样算法");
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
		//System.out.println("流式拓扑结构抽样算法");
		return null;
	}
	
	
}
