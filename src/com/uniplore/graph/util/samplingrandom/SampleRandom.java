/**    
* @Title         SampleRandom.java  
* @Package       com.uniplore.graph.util.samplingrandom  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月8日 上午11:14:59  
* @version       1.0    
*/ 
package com.uniplore.graph.util.samplingrandom;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述    该类用来生成服从均匀分布的随机数，主要是在抽样算法中使用
 * 类名称    com.uniplore.graph.util.samplingrandom.SampleRandom       
 * 创建人    朱君鹏
 * 创建时间  2017年8月8日 上午11:14:59     
 * 修改人  
 * 修改时间  2017年8月8日 上午11:14:59     
 * 修改备注     
 * @version  1.0      
 */

public class SampleRandom {
	
	/**
	 * 参考： https://stackoverflow.com/questions/2546078/java-random-long-number-in-0-x-n-range
	 * @Title  randomSampling  
	 * @Description TODO 生成一组Long类型的随机数，java.util.Random包中只能生成Integer类型的均匀分布的
	 *                     伪随机数，在Java 7 新增了目前这个包  
	 * @param randomNumCount
	 * @param randomMAX
	 * @return
	 */
	public static HashSet<Long> randomSampling(Long randomNumCount , Long randomMAX){
		
		//生成随机数，服从均匀分布 http://blog.csdn.net/liyuanbhu/article/details/8630677
		HashSet<Long> randomSet = new HashSet<Long>();
		while(true){
			if(randomSet.size() == randomNumCount){   //如果生成的随机数指定的个数，整个循环结束
				break;
			}else{
				long nextLong = ThreadLocalRandom.current().nextLong(randomMAX); //生成的随机数范围在[0,randomMAX-1]范围内
				randomSet.add(nextLong);   //将上成的随机数加入到HashSet中，如果随机数重复，则不会被加入
			}
		}
		
		//遍历得到的随机数，为了调试代码
		Iterator<Long> iterator = randomSet.iterator();
		while(iterator.hasNext()){
			Long next = iterator.next();
			//System.out.println(next);
		}
		
		/*Object[] array = randomSet.toArray();
		Arrays.sort(array);   //原地(in-place)排序
		for (Object object : array) {
			System.out.println(object);
		}*/
		
		return randomSet;
	}
	
	//用于生成int类型的随机数
	public static HashSet<Integer> randomSamplingInt(int randomNumCount , int randomMAX){
		Random random = new Random();
		HashSet<Integer> randomSet = new HashSet<Integer>();  //保存随机的数据集
		
		while(true){
			if (randomSet.size() == randomNumCount) {
				break;
			}else {
				int nextInt = random.nextInt(randomMAX);
				randomSet.add(nextInt);
			}
		}
		
		return randomSet;
	}
	
	//随机数生成 http://www.cs.technion.ac.il/~azlotnik/RandomNumberGenerator.java.html
	//public static double getRandom(Random r, double geoSeed) { 
    public static double getRandomGeoDistribution(Random r) {
        //double p = 1.0 / ((double) geoSeed); 
        double p = 0.7;   //概率为0.7的几何分布
        return (int)(Math.ceil(Math.log(r.nextDouble())/Math.log(1.0-p))); 
    } 
	
	public static void main(String[] args) {
		//randomSampling((long)10, (long)100);
		//randomSamplingInt(10, 100);
		/*GeometricDistribution geometricDistribution =  new GeometricDistribution(0.7);
		System.out.println(geometricDistribution.inverseCumulativeProbability(0.7));*/
		for(int i = 0 ; i < 100 ; i++){
			System.out.println(getRandomGeoDistribution(new Random()));
		}
		
	}
}
