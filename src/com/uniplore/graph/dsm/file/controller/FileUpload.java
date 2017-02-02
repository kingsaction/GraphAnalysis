package com.uniplore.graph.dsm.file.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping(value="/file/Upload")
public class FileUpload {

	/**
	 * 
	 * @param file
	 * @param response
	 * @param model    
	 * @throws Exception
	 */
	@RequestMapping(value="/Text",method=RequestMethod.POST)
	public void fileUploadText(@RequestParam(value = "file",required = false) MultipartFile file,
			HttpServletResponse response, Model model)throws Exception{
		System.out.println("从客户端接收到文件"+file.getOriginalFilename());
		
	}
	
	/**
	 * 功能描述：将JSON文件上传之后，跳转到此链接，该部分完成将JSON文件保存的过程，保存之后将文件名、上传时间、id保存到数据库中
	 * 接着将该唯一的id返回给客户端
	 * @param file
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/Json")
	public @ResponseBody Integer fileUploadJson(@RequestParam(value = "file",required = true) MultipartFile file,
			HttpServletResponse response, Model model)throws Exception{
		//System.out.println("从客户端接收到JSON文件为:"+file.);
		
		//获取到从客户端传来的数据，将其保存到一个指定的位置，暂且保存到服务器的位置上
		
		
		//将文件名保存到数据库中
		
		
		//获取该文件的的文唯一id用户它的页面中使用，id采用整型
		
		
		
		return 0;
	}
	
}
