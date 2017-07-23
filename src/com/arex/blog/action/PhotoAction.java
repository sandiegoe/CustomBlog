package com.arex.blog.action;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.FileDTO;
import com.arex.blog.dto.PhotoDTO;
import com.arex.blog.dto.UserDTO;
import com.arex.blog.service.FileService;
import com.arex.blog.service.PhotoService;
import com.arex.blog.utils.FileUtils;
import com.arex.blog.utils.LoginUtils;

@Component(value="photoAction")
@Scope(value="prototype")
public class PhotoAction extends CommonAction<PhotoDTO> {

	@Resource(name="fileServiceImpl")
	private FileService fileService;
	@Resource(name="photoServiceImpl")
	private PhotoService photoService;
	
	public String upload() {
		
		PhotoDTO photoDTO = super.getModel();
		
		//检查当前用户是否已经登录
		if (!LoginUtils.checkUserIsAlreadyLogin(session)) {
			request.setAttribute("messageInfo", "请登录.");
			return "signInPage";
		}
		
		//判断
		if (photoDTO==null || photoDTO.getFile()==null) {
			request.setAttribute("messageInfo", "图片上传出错.");
			return "photoError";
		}
		if (photoDTO.getFileContentType()==null || !FileUtils.isPhotoByContentType(photoDTO.getFileContentType())) {
			request.setAttribute("messageInfo", "请上传图片文件.");
			return "photoError";
		}
		if (photoDTO.getFile().length() > 1024 * 1024) {
			request.setAttribute("messageInfo", "文件大小不得大于1024k.");
			return "photoError";
		}
	
		//将PhotoDTO转化为FileDTO
		FileDTO fileDTO = new FileDTO();
		fileDTO.setUpload(photoDTO.getFile());
		fileDTO.setUploadContentType(photoDTO.getFileContentType());
		fileDTO.setUploadFileName(photoDTO.getFileFileName());
		
		//上传图片
		fileDTO = fileService.uploadFile(fileDTO);
		
		//判断是否上传成功
		if (!fileDTO.isUploadSuccessFlags()) {
			request.setAttribute("messageInfo", "图片上传失败.");
			return "photoError";
		}
		//设置PhotoDTO保存的必要属性
		photoDTO.setUserId(((UserDTO)session.getAttribute("loginUser")).getUserId());
		photoDTO.setPhotoName(fileDTO.getFileName());
		photoDTO.setPhotoURL(fileDTO.getFileURL());
		photoDTO.setPhotoDate(new Date());
		photoDTO.setPhotoSize(String.valueOf(photoDTO.getFile().length()));
		//数据库中保存图片信息
		photoService.savePhoto(photoDTO);
		
		return "upload";
	}
	
	public String delete() {
		
		//检查当前用户是否已经登录
		if (!LoginUtils.checkUserIsAlreadyLogin(session)) {
			request.setAttribute("messageInfo", "请登录.");
			return "signInPage";
		}
		if (super.getModel()==null || super.getModel().getPhotoId()==null || "".equals(super.getModel().getPhotoId())) {
			request.setAttribute("messageInfo", "删除图片失败.");
			return "toPhoto";
		}
		
		//删除指定的photoId的图片
		photoService.deletePhotoByPhotoId(super.getModel().getPhotoId());
		
		return "delete";
	}
}
