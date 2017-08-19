/**    
* @Title         DegreeDistribution.java  
* @Package       com.uniplore.graph.estimation.entity  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月19日 下午3:20:03  
* @version       1.0    
*/ 
package com.uniplore.graph.estimation.entity;

  
/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.estimation.entity.DegreeDistribution       
 * 创建人    朱君鹏
 * 创建时间  2017年8月19日 下午3:20:03     
 * 修改人  
 * 修改时间  2017年8月19日 下午3:20:03     
 * 修改备注     
 * @version  1.0      
 */

public class DegreeDistribution {
	private Integer degree;
	private Integer count;
	public DegreeDistribution() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DegreeDistribution(Integer degree, Integer count) {
		super();
		this.degree = degree;
		this.count = count;
	}
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "DegreeDistribution [degree=" + degree + ", count=" + count + "]";
	}
}
