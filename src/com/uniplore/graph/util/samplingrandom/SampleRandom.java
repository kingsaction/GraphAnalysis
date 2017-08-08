/**    
* @Title         SampleRandom.java  
* @Package       com.uniplore.graph.util.samplingrandom  
* @Description   TODO(用一句话描述该文件做什么)  
* @author        朱君鹏     
* @date          2017年8月8日 上午11:14:59  
* @version       1.0    
*/ 
package com.uniplore.graph.util.samplingrandom;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
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
				long nextInt = ThreadLocalRandom.current().nextLong(randomMAX); //生成的随机数范围在[0,randomMAX-1]范围内
				randomSet.add(nextInt);   //将上成的随机数加入到HashSet中，如果随机数重复，则不会被加入
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
	
	public static void main(String[] args) {
		randomSampling((long)10, (long)100);
	}
}
