package com.arex.blog.service;

import java.util.List;

import com.arex.blog.dto.PhotoDTO;

public interface PhotoService {

	public void savePhoto(PhotoDTO photoDTO);

	public List<PhotoDTO> searchAllPhotoByUserId(String userId);

	public void deletePhotoByPhotoId(String photoId);
}
