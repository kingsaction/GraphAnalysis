/**    
* @Title         Edge.java  
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
	private String sourceNode ;
	private String targetNode;
	public Edge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Edge(String id, String sourceNode, String targetNode) {
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
		return "Edge [id=" + id + ", sourceNode=" + sourceNode + ", targetNode=" + targetNode + "]";
	}
	
}
