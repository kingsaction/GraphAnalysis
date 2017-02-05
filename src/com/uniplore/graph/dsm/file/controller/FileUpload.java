package com.uniplore.graph.dsm.file.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	 * 接着将该唯一的id返回给客户端，使用apache的fileUpload组件实现文件的上传保存过程，还需要当上传文件同名时，避免覆盖
	 * @param file
	 * @param response
	 * @param model
	 * @return  返回值为集合类型，加上@ResponseBody注解之后，将集合类型转换成JSON格式返回
	 * @throws Exception
	 */
	@RequestMapping(value="/Json",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> fileUploadJson(@RequestParam(value = "file",required = true) MultipartFile file,
			HttpServletResponse response, HttpServletRequest request,Model model)throws Exception{
		
		//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\graphanalysis\\WEB-INF\\upload\\文件夹中
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload"); //路径中的/代表tomcat中当前项目路径
		
		//避免文件被覆盖，也就是重名问题
		String id = UUID.randomUUID().toString();  
		String fileOriginalName = file.getOriginalFilename();   //得到上传的原始文件名
		System.out.println(fileOriginalName);
		String saveFileName = id+"#"+fileOriginalName;  //保存到服务器的文件名由两部分组成：生成的唯一id和原始的文件名
		
		//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, saveFileName)); 
		
        //将文件的唯一id(避免文件重名)和原始文件名以JSON形式返回给服务器端
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);   //将每个文件的唯一id编号传递给客户端
		map.put("fileName", fileOriginalName);   //将原始的文件名传递给客户端
		return map;
	}
	
	@RequestMapping(value="/ViewData",method=RequestMethod.POST)
	public String viewData(String id , String fileName){
		return id;
	}
}
