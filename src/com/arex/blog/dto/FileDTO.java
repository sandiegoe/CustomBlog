package com.arex.blog.dto;

import java.io.File;

import jdk.nashorn.internal.ir.Flags;

public class FileDTO {

	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	//ckeditor上传文件
	private String CKEditor;
	private String CKEditorFuncNum;
	private String ckCsrfToken;
	private String langCode;
	
	//上传成功后在服务器中的绝对路径
	private String absolutePathOnServer;
	//上传成功后在服务器中的URL路径
	private String fileURL;
	//文件是否上传成功标志
	private boolean isUploadSuccessFlags;
	//上传之后文件名
	private String fileName;
	private String fileSize;
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getCKEditor() {
		return CKEditor;
	}

	public void setCKEditor(String cKEditor) {
		CKEditor = cKEditor;
	}

	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}

	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}

	public String getCkCsrfToken() {
		return ckCsrfToken;
	}

	public void setCkCsrfToken(String ckCsrfToken) {
		this.ckCsrfToken = ckCsrfToken;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getAbsolutePathOnServer() {
		return absolutePathOnServer;
	}

	public void setAbsolutePathOnServer(String absolutePathOnServer) {
		this.absolutePathOnServer = absolutePathOnServer;
	}

	public boolean isUploadSuccessFlags() {
		return isUploadSuccessFlags;
	}

	public void setUploadSuccessFlags(boolean isUploadSuccessFlags) {
		this.isUploadSuccessFlags = isUploadSuccessFlags;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

}
