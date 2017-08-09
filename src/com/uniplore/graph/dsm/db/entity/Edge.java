/**    
* @Title         Edges.java  
* @Package       com.uniplore.graph.dsm.db.entity  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月5日 下午3:25:55  
* @version       1.0    
*/ 
package com.uniplore.graph.dsm.db.entity;

  
/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.dsm.db.entity.Edge       
 * 创建人    朱君鹏
 * 创建时间  2017年8月5日 下午3:25:55     
 * 修改人  
 * 修改时间  2017年8月5日 下午3:25:55     
 * 修改备注     
 * @version  1.0    
 * create table edge(
	id varchar(50) PRIMARY KEY,          -- 自增主键
	source_node varchar(50),             -- 起始点
	target_node varchar(50)              -- 终点
   );  
 */

public class Edge {
	private String id;
	private String sourceNodeID ;
	private String targetNodeID;
	private String sourceNodeName;
	private String targetNodeName;
	private Boolean edgeState;
	public Edge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Edge(String id, String sourceNodeID, String targetNodeID, String sourceNodeName, String targetNodeName,
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
		return "Edge [id=" + id + ", sourceNodeID=" + sourceNodeID + ", targetNodeID=" + targetNodeID
				+ ", sourceNodeName=" + sourceNodeName + ", targetNodeName=" + targetNodeName + ", edgeState="
				+ edgeState + "]";
	}
	
}
