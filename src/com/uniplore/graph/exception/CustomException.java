package com.uniplore.graph.exception;

/**
 * 版权所有  2016-ACMIS Lab .
 * 项目名称  graphanalysis       
 * 类描述    全局异常处理
 * 类名称    com.uniplore.graph.exception.CustomException       
 * 创建人    朱君鹏
 * 创建时间  2016年12月21日 下午9:42:03     
 * 修改人  
 * 修改时间  2016年12月21日 下午9:42:03     
 * 修改备注     
 * @version  1.0
 */
public class CustomException extends Exception {

  private static final long serialVersionUID = 1L;
  
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
