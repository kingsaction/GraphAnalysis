/**    
* @Title         EstimationDao.java  
* @Package       com.uniplore.graph.estimation.dao.Impl  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月19日 上午9:15:27  
* @version       1.0    
*/ 
package com.uniplore.graph.estimation.dao.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uniplore.graph.estimation.dao.IEstimationDao;
import com.uniplore.graph.estimation.entity.DegreeDistribution;
import com.uniplore.graph.estimation.entity.EdgeEstimation;
import com.uniplore.graph.estimation.entity.NodeEstimation;
import com.uniplore.graph.estimation.mapper.DegreeDistributionMapper;
import com.uniplore.graph.estimation.mapper.EstimationMapper;
import com.uniplore.graph.sampling.entity.SamplingEdges;
import com.uniplore.graph.sampling.entity.SamplingNodes;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.estimation.dao.Impl.EstimationDao       
 * 创建人    朱君鹏
 * 创建时间  2017年8月19日 上午9:15:27     
 * 修改人  
 * 修改时间  2017年8月19日 上午9:15:27     
 * 修改备注     
 * @version  1.0      
 */

@Repository
public class EstimationDao implements IEstimationDao {

	@Autowired
	private EstimationMapper estimationMapper;
	@Autowired
	private DegreeDistributionMapper degreeDistributionMapper;
	/**  
	 * @see com.uniplore.graph.estimation.dao.IEstimationDao#listNodeAllData()  
	 */  
	
	@Override
	public List<NodeEstimation> listNodeAllData() throws Exception {
		return estimationMapper.listNodeAllData();
	}
	/**  
	 * @see com.uniplore.graph.estimation.dao.IEstimationDao#listEdgeAllData()  
	 */  
	
	@Override
	public List<EdgeEstimation> listEdgeAllData() throws Exception {
		return estimationMapper.listEdgeAllData();
	}
	/**  
	 * @see com.uniplore.graph.estimation.dao.IEstimationDao#insertDegreeDistribution()  
	 */  
	
	@Override
	public void insertDegreeDistribution(DegreeDistribution degree) throws Exception {
		degreeDistributionMapper.insertDegreeDistribution(degree);
	}
	/**  
	 * @see com.uniplore.graph.estimation.dao.IEstimationDao#deleteDegree()  
	 */  
	
	@Override
	public void deleteDegree() throws Exception {
		degreeDistributionMapper.deleteDegree();
	}
	/**  
	 * @see com.uniplore.graph.estimation.dao.IEstimationDao#listSamplingNodeAllData()  
	 */  
	
	@Override
	public List<SamplingNodes> listSamplingNodeAllData() throws Exception {
		return estimationMapper.listSamplingNodeAllData();
	}
	/**  
	 * @see com.uniplore.graph.estimation.dao.IEstimationDao#listSamplingEdgeAllData()  
	 */  
	
	@Override
	public List<SamplingEdges> listSamplingEdgeAllData() throws Exception {
		return estimationMapper.listSamplingEdgeAllData();
	}
	/**  
	 * @see com.uniplore.graph.estimation.dao.IEstimationDao#insertSamplingDegreeDistribution(com.uniplore.graph.estimation.entity.DegreeDistribution)  
	 */  
	
	@Override
	public void insertSamplingDegreeDistribution(DegreeDistribution degree) throws Exception {
		degreeDistributionMapper.insertSamplingDegreeDistribution(degree);
	}
	/**  
	 * @see com.uniplore.graph.estimation.dao.IEstimationDao#deleteSamplingDegree()  
	 */  
	
	@Override
	public void deleteSamplingDegree() throws Exception {
		degreeDistributionMapper.deleteSamplingDegree();
	}

}
