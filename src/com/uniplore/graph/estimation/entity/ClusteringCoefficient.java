/**    
* @Title         ClusteringCoefficient.java  
* @Package       com.uniplore.graph.estimation.entity  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月29日 下午8:36:48  
* @version       1.0    
*/ 
package com.uniplore.graph.estimation.entity;

  
/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.estimation.entity.ClusteringCoefficient       
 * 创建人    朱君鹏
 * 创建时间  2017年8月29日 下午8:36:48     
 * 修改人  
 * 修改时间  2017年8月29日 下午8:36:48     
 * 修改备注     
 * @version  1.0      
 */

public class ClusteringCoefficient {
	private double coefficient;

	public ClusteringCoefficient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClusteringCoefficient(double coefficient) {
		super();
		this.coefficient = coefficient;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	@Override
	public String toString() {
		return "ClusteringCoefficient [coefficient=" + coefficient + "]";
	}
	
}
