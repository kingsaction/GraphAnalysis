/**    
* @Title         SamplingNodes.java  
* @Package       com.uniplore.graph.sampling.entity  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月24日 下午9:06:23  
* @version       1.0    
*/ 
package com.uniplore.graph.sampling.entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.sampling.entity.SamplingNodes       
 * 创建人    朱君鹏
 * 创建时间  2017年8月24日 下午9:06:23     
 * 修改人  
 * 修改时间  2017年8月24日 下午9:06:23     
 * 修改备注     
 * @version  1.0      
 */

public class SamplingNodes {
	private String id;
	private String nodeName;
	public SamplingNodes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SamplingNodes(String id, String nodeName) {
		super();
		this.id = id;
		this.nodeName = nodeName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	@Override
	public String toString() {
		return "SamplingNodes [id=" + id + ", nodeName=" + nodeName + "]";
	}
	
	
}
