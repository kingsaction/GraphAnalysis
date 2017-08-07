/**    
* @Title         JblasTest.java  
* @Package       com.uniplore.graph.matrix  
* @Description   测试Java中能够使用的矩阵工具  http://jblas.org/javadoc/index.html  
* @author        朱君鹏     
* @date          2017年8月7日 上午9:17:01  
* @version       1.0    
*/ 

/**
 * API 文档在线地址 http://jblas.org/javadoc/index.html
 * 目前能用到的包是org.jblas，在文档中总共有五个包，分别是:org.jblas,org.jblas.benchmark,
 * org.jblas.exception,org.jblas.ranges,org.jblas.util，但是这些目前写代码用不到
 * 在org.jblas包中能够用到的Classes为：
 * DoubleMatrix(构造矩阵，方法有很多), 
 * Eigen(计算一个矩阵的特征值与特征向量),
 * Geometry(按照矩阵的行或者列进行规范化，规范化是指每一行或列都是单位长度),该类非常有用,
 * MatrixFunction(求绝对值、平方、取近似值等函数都在这个包中)
 */
package com.uniplore.graph.matrix;

import java.io.IOException;

import org.jblas.DoubleMatrix;

/**     
 * 版权所有  2017-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述    该部分主要用于测试jblas的使用
 * 类名称    com.uniplore.graph.matrix.JblasTest       
 * 创建人    朱君鹏
 * 创建时间  2017年8月7日 上午9:17:01     
 * 修改人  
 * 修改时间  2017年8月7日 上午9:17:01     
 * 修改备注     
 * @version  1.0      
 */

public class JblasTest {
	public static void main(String[] args) throws IOException {
		//创建一个两行两列的矩阵，并且1,2,3,4四个数对矩阵进行了赋值操作，矩阵为[1,3;2,4]，可以看出是按列进行赋值操作
		/*DoubleMatrix a = new DoubleMatrix(2, 2, 1,2,3,4);
		System.out.println(a);*/
		
		//列向量
		/*DoubleMatrix b = new DoubleMatrix(10);
		System.out.println(b);*/
		
		//将list转成matrix
		/*List<Double> list = new ArrayList<Double>();
		list.add(1.0);
		list.add(2.0);
		DoubleMatrix c = new DoubleMatrix(list);
		System.out.println(c);*/
		
		//读取文件API不能使用，可能文件格式不正确
		/*DoubleMatrix d = new DoubleMatrix("matlab");
		System.out.println(d);*/
		
		//将以为数组转换成列向量
		/*DoubleMatrix e = new DoubleMatrix(new double[]{1.0,2.0});
		System.out.println(e);*/
		
		//将一个double类型的二维数组转换成矩阵
		/*DoubleMatrix f = new DoubleMatrix(new double[][]{{1.0,2.0},{3.0,4.0}});
		System.out.println(f);*/
		
		//产生三阶单位矩阵，参数代表单位矩阵的维度
		/*DoubleMatrix g = DoubleMatrix.eye(3);
		System.out.println(g);*/
		
		//产生全1矩阵
		/*DoubleMatrix h = DoubleMatrix.ones(3,4);   //产生3行4列全1矩阵
		System.out.println(h);*/
		
		//产生全0矩阵
		/*DoubleMatrix i = DoubleMatrix.zeros(3,4);
		System.out.println(i);
		System.out.println(i.length);
		System.out.println(i.rows);
		System.out.println(i.columns);
		System.out.println(i.argmin());
		System.out.println(i.distance1(i));*/
		
		//矩阵的转置运算
		/*DoubleMatrix a = new DoubleMatrix(5); 
		a.transpose();   //计算a矩阵的转置*/
		
		//1. 重点API 这个功能十分的有用，目前我的项目中就需要动态的给矩阵的每一个元素赋值，并得到这个矩阵
		/*DoubleMatrix a = new DoubleMatrix(10, 5);
		// accessing elements by row and column
		a.put(3, 2, 10.0);   //给第三行第二列赋值10.0
		System.out.println(a.get(3,2));   //得到第三行第二列元素的值*/
		
		//2. 得到一行或者一列，下标从0开始计数
		/*DoubleMatrix f = new DoubleMatrix(new double[][]{{1.0,2.0},{3.0,4.0}});
		System.out.println(f);
		DoubleMatrix row = f.getRow(0); //得到矩阵中的某一列，函数的返回值为一个行向量
		System.out.println(row);   
		DoubleMatrix column = f.getColumn(1);   //得到矩阵的一列，函数的返回值为一个列向量
		System.out.println(column);*/
		
		//3. 设置矩阵的一行或者一列
		/*DoubleMatrix a = new DoubleMatrix(5, 5);
		a.putRow(2, new DoubleMatrix(5));
		System.out.println(a);*/
		
		//4. 矩阵的基本运算
		//当前Jblas支持的矩阵运算有： add(加) sub(减) mul mmul div dot
		
		
	}
}
