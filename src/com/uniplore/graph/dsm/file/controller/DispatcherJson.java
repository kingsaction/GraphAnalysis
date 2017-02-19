package com.uniplore.graph.dsm.file.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class DispatcherJson {
  
  /** 功能： 将页面跳转到JSON数据展示页面.
   * @param request 接收请求参数
   * @return  返回值为model
   * @throws Exception  抛出异常，在该程序中使用全局异常处理机制
   */
  @RequestMapping(value = "/JsonTypeDataAnalysis",method = RequestMethod.GET)
  public String dispatcherJson(HttpServletRequest request) throws Exception {
    /*request.setCharacterEncoding("UTF-8");*/
    String id = request.getParameter("id");
    String fileName = request.getParameter("fileName");
    System.out.println("接受到的文件的唯一识别id为" + id + "--" + "接受到的文件名为" + fileName);  //判断是否成功的接收到文件参数信息
    return "/dsm/file/json/json";
  }
}
