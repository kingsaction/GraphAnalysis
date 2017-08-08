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
	private String sourceNode;
	private String targetNode;
	public Edges() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Edges(String id, String sourceNode, String targetNode) {
		super();
		this.id = id;
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSourceNode() {
		return sourceNode;
	}
	public void setSourceNode(String sourceNode) {
		this.sourceNode = sourceNode;
	}
	public String getTargetNode() {
		return targetNode;
	}
	public void setTargetNode(String targetNode) {
		this.targetNode = targetNode;
	}
	@Override
	public String toString() {
		return "Edges [id=" + id + ", sourceNode=" + sourceNode + ", targetNode=" + targetNode + "]";
	}
	
}
