/**    
* @Title         Edges.java  
* @Package       com.uniplore.graph.sampling.entity  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月8日 上午10:09:53  
* @version       1.0    
*/ 
package com.uniplore.graph.sampling.entity;

  
/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.sampling.entity.Edges       
 * 创建人    朱君鹏
 * 创建时间  2017年8月8日 上午10:09:53     
 * 修改人  
 * 修改时间  2017年8月8日 上午10:09:53     
 * 修改备注     
 * @version  1.0      
 */

public class Edges {
	private String id;
	private String sourceNodeID;
	private String targetNodeID;
	private String sourceNodeName;
	private String targetNodeName;
	private Boolean edgeState;
	public Edges() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Edges(String id, String sourceNodeID, String targetNodeID, String sourceNodeName, String targetNodeName,
			Boolean edgeState) {
		super();
		this.id = id;
		this.sourceNodeID = sourceNodeID;
		this.targetNodeID = targetNodeID;
		this.sourceNodeName = sourceNodeName;
		this.targetNodeName = targetNodeName;
		this.edgeState = edgeState;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSourceNodeID() {
		return sourceNodeID;
	}
	public void setSourceNodeID(String sourceNodeID) {
		this.sourceNodeID = sourceNodeID;
	}
	public String getTargetNodeID() {
		return targetNodeID;
	}
	public void setTargetNodeID(String targetNodeID) {
		this.targetNodeID = targetNodeID;
	}
	public String getSourceNodeName() {
		return sourceNodeName;
	}
	public void setSourceNodeName(String sourceNodeName) {
		this.sourceNodeName = sourceNodeName;
	}
	public String getTargetNodeName() {
		return targetNodeName;
	}
	public void setTargetNodeName(String targetNodeName) {
		this.targetNodeName = targetNodeName;
	}
	public Boolean getEdgeState() {
		return edgeState;
	}
	public void setEdgeState(Boolean edgeState) {
		this.edgeState = edgeState;
	}
	@Override
	public String toString() {
		return "Edges [id=" + id + ", sourceNodeID=" + sourceNodeID + ", targetNodeID=" + targetNodeID
				+ ", sourceNodeName=" + sourceNodeName + ", targetNodeName=" + targetNodeName + ", edgeState="
				+ edgeState + "]";
	}	
}
