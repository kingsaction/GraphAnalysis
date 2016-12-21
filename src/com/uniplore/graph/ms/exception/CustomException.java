package com.uniplore.graph.ms.exception;

/**
 * 
 * 版权所有  2016-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述    ms模块的异常处理机制，尤其是当出现运行时异常时，应该跳转到一个error页面，而不是向客户端抛出500
 * 类名称    com.uniplore.graph.ms.exception.CustomException       
 * 创建人    朱君鹏
 * 创建时间  2016年12月21日 下午3:03:22     
 * 修改人  
 * 修改时间  2016年12月21日 下午3:03:22     
 * 修改备注     
 * @version  1.0
 */
public class CustomException extends Exception {

	//异常信息
	public String message;

	public CustomException(String message) {
		super(message);  
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
