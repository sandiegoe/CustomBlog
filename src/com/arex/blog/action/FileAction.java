package com.arex.blog.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.FileDTO;

@Component(value="fileAction")
@Scope(value="prototype")
public class FileAction extends CommonAction<FileDTO> {
	
	public String uploadPhoto() throws Exception {
		
		FileDTO fileDTO = super.getModel();
		System.out.println(fileDTO);
		
		File upload = fileDTO.getUpload();
		String uploadContentType = fileDTO.getUploadContentType();
		String uploadFileName = fileDTO.getUploadFileName();
		
		String callback = ServletActionContext.getRequest().getParameter("CKEditorFuncNum");
		
		//获取响应的输出流
		PrintWriter writer = response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//判断文件类型
		String expandName = "";
		if ("image/pjpeg".equals(uploadContentType) || "image/jpeg".equals(uploadContentType)) {
			expandName = ".jpg";
		} else if ("image/png".equals(uploadContentType) || "image/x-png".equals(uploadContentType)) {
			expandName = ".png";
		} else if ("image/gif".equals(uploadContentType)) {
			expandName = ".gif";
		} else if ("image/bmp".equals(uploadContentType)) {
			expandName = ".bmp";
		} else {
			writer.println("<script type=\"text/javascript\">");
			writer.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");   
			writer.println("</script>");  
            return null;  
		}
		
		//判断文件大小
		if (upload.length() > 600*1024) {
			writer.println("<script type=\"text/javascript\">");    
			writer.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件大小不得大于600k');");   
			writer.println("</script>");  
            return null; 
		}
		
		//保存图片
		String fileName = UUID.randomUUID().toString() + expandName;
		//获取上传路径
		String uploadPath = ResourceBundle.getBundle("deploy", Locale.CHINA).getString("upload");
		uploadPath += "/img/uploadImage";
		
		File saveImageFile = new File(uploadPath, fileName);
		FileInputStream fis = new FileInputStream(upload);
		FileOutputStream fos = new FileOutputStream(saveImageFile);
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length=fis.read(buffer)) > 0 ) {
			fos.write(buffer, 0, length);
		}
		fos.flush();
		fis.close();
		fos.close();
		
		// 返回“图像”选项卡并显示图片  
		//String serverHost = request.getLocalAddr();
		//配置为服务器的地址,通过配置文件指定,通过上述方式获取的是内网IP地址,指定为公网IP地址
		ResourceBundle bundle = ResourceBundle.getBundle("deploy",Locale.CHINA);
		String serverHost = bundle.getString("serverhost");
		//获取上传路径
		String uploadAccess = bundle.getString("uploadPath");
		uploadAccess += "/Blog/img/uploadImage/";
		writer.println("<script type=\"text/javascript\">");    
		writer.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "http://" + serverHost + ":8080" + uploadAccess + fileName + "','')");    
		writer.println("</script>");
		
		return null;
	}

}
