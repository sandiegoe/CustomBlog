package com.arex.blog.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.arex.blog.dto.PhotoDTO;

public interface PhotoService {

	public void savePhoto(PhotoDTO photoDTO);

	public List<PhotoDTO> searchAllPhotoByUserId(String userId);

	public void deletePhotoByPhotoId(String photoId);

	public List<PhotoDTO> searchAllPhotoByUserId(HttpServletRequest request,
			String userId);
}
