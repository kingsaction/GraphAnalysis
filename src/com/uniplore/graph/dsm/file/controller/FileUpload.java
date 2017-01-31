package com.uniplore.graph.dsm.file.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	 * 
	 * @param file
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/Json",method=RequestMethod.POST)
	public void fileUploadJson(@RequestParam(value = "file",required = false) MultipartFile file,
			HttpServletResponse response, Model model)throws Exception{
		System.out.println("从客户端接收到文件"+file.getOriginalFilename());
		
		//获取到从客户端传来的数据，将其保存到一个指定的位置，将文件名保存到model中，接着跳转到一个成功的页面中
	}
	
}
