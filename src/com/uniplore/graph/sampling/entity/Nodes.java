/**    
* @Title         Nodes.java  
* @Package       com.uniplore.graph.sampling.entity  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月8日 上午10:08:15  
* @version       1.0    
*/ 
package com.uniplore.graph.sampling.entity;

  
/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.sampling.entity.Nodes       
 * 创建人    朱君鹏
 * 创建时间  2017年8月8日 上午10:08:15     
 * 修改人  
 * 修改时间  2017年8月8日 上午10:08:15     
 * 修改备注     
 * @version  1.0      
 */

public class Nodes {
	private String id; 
	private String nodeName;
	private Integer nodeDegree;
	private Boolean nodeState;
	public Nodes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Nodes(String id, String nodeName, Integer nodeDegree, Boolean nodeState) {
		super();
		this.id = id;
		this.nodeName = nodeName;
		this.nodeDegree = nodeDegree;
		this.nodeState = nodeState;
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
	public Integer getNodeDegree() {
		return nodeDegree;
	}
	public void setNodeDegree(Integer nodeDegree) {
		this.nodeDegree = nodeDegree;
	}
	public Boolean getNodeState() {
		return nodeState;
	}
	public void setNodeState(Boolean nodeState) {
		this.nodeState = nodeState;
	}
	@Override
	public String toString() {
		return "Nodes [id=" + id + ", nodeName=" + nodeName + ", nodeDegree=" + nodeDegree + ", nodeState=" + nodeState
				+ "]";
	}
	public Nodes(String id, String nodeName) {
		super();
		this.id = id;
		this.nodeName = nodeName;
	}
	
}
