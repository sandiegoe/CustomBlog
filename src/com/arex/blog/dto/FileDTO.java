package com.arex.blog.dto;

import java.io.File;

public class FileDTO {

	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	//ckeditor上传文件
	private String CKEditor;
	private String CKEditorFuncNum;
	private String ckCsrfToken;
	private String langCode;
	
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

}
