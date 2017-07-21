package com.arex.blog.utils;

public class FileUtils {
	
	public static String getExtendNameByContentType(String contentType) {
		
		String expandName = "";
		
		
		if ("image/pjpeg".equals(contentType) || "image/jpeg".equals(contentType)) {
			expandName = ".jpg";
		} else if ("image/png".equals(contentType) || "image/x-png".equals(contentType)) {
			expandName = ".png";
		} else if ("image/gif".equals(contentType)) {
			expandName = ".gif";
		} else if ("image/bmp".equals(contentType)) {
			expandName = ".bmp";
		} 
		
		return expandName;
	}
	
	public static boolean isPhotoByContentType(String contentType) {
		
		if ("image/pjpeg".equals(contentType) || "image/jpeg".equals(contentType) ||
				"image/png".equals(contentType) || "image/x-png".equals(contentType) ||
				"image/gif".equals(contentType) ||
				"image/bmp".equals(contentType)) {
			
			return true;
		}
		
		return false;
	}
}