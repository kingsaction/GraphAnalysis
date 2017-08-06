/**    
* @Title         Node.java  
* @Package       com.uniplore.graph.dsm.db.entity  
* @Description   中间数据库层点表持久层对象  
* @author        朱君鹏     
* @date          2017年8月5日 下午3:25:46  
* @version       1.0    
*/ 
package com.uniplore.graph.dsm.db.entity;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  中间数据库层，点表的持久层对象，该部分主要用于实现抽样功能
 * 类名称    com.uniplore.graph.dsm.db.entity.Node       
 * 创建人    朱君鹏
 * 创建时间  2017年8月5日 下午3:25:46     
 * 修改人  
 * 修改时间  2017年8月5日 下午3:25:46     
 * 修改备注     
 * @version  1.0   
 * create table node(
     id varchar(50) primary key,        -- 自增主键
     node_name varchar(50),             -- 节点名字
     node_degree  Integer,              -- 节点的度
     node_state   Boolean               -- 在抽样是使用的节点的状态
   );   
 */

public class Node {
	private String id;
	private String nodeName ;
	private Integer nodeDegree;
	private Boolean nodeState;
	public Node(String id, String nodeName, Integer nodeDegree, Boolean nodeState) {
		super();
		this.id = id;
		this.nodeName = nodeName;
		this.nodeDegree = nodeDegree;
		this.nodeState = nodeState;
	}
	public Node() {
		super();
		// TODO Auto-generated constructor stub
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
		return "Node [id=" + id + ", nodeName=" + nodeName + ", nodeDegree=" + nodeDegree + ", nodeState=" + nodeState
				+ "]";
	}
	
}
