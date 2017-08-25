/**    
* @Title         SamplingEdges.java  
* @Package       com.uniplore.graph.sampling.entity  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月24日 下午9:07:55  
* @version       1.0    
*/ 
package com.uniplore.graph.sampling.entity;


/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.sampling.entity.SamplingEdges       
 * 创建人    朱君鹏
 * 创建时间  2017年8月24日 下午9:07:55     
 * 修改人  
 * 修改时间  2017年8月24日 下午9:07:55     
 * 修改备注     
 * @version  1.0      
 */

public class SamplingEdges {
	private String id;
	private String sourceNodeID;
	private String sourceNodeName;
	private String targetNodeID;
	private String targetNodeName;
	public SamplingEdges() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SamplingEdges(String id, String sourceNodeID, String sourceNodeName, String targetNodeID,
			String targetNodeName) {
		super();
		this.id = id;
		this.sourceNodeID = sourceNodeID;
		this.sourceNodeName = sourceNodeName;
		this.targetNodeID = targetNodeID;
		this.targetNodeName = targetNodeName;
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
	public String getSourceNodeName() {
		return sourceNodeName;
	}
	public void setSourceNodeName(String sourceNodeName) {
		this.sourceNodeName = sourceNodeName;
	}
	public String getTargetNodeID() {
		return targetNodeID;
	}
	public void setTargetNodeID(String targetNodeID) {
		this.targetNodeID = targetNodeID;
	}
	public String getTargetNodeName() {
		return targetNodeName;
	}
	public void setTargetNodeName(String targetNodeName) {
		this.targetNodeName = targetNodeName;
	}
	@Override
	public String toString() {
		return "SamplingEdges [id=" + id + ", sourceNodeID=" + sourceNodeID + ", sourceNodeName=" + sourceNodeName
				+ ", targetNodeID=" + targetNodeID + ", targetNodeName=" + targetNodeName + "]";
	}
	
	
}
