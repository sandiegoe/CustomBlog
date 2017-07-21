package com.arex.blog.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.arex.blog.dao.PhotoDAO;
import com.arex.blog.dto.PhotoDTO;
import com.arex.blog.model.Photo;
import com.arex.blog.service.PhotoService;

@Component(value="photoServiceImpl")
public class PhotoServiceImpl implements PhotoService {

	@Resource(name="photoDAOImpl")
	private PhotoDAO photoDAO;
	
	@Override
	public void savePhoto(PhotoDTO photoDTO) {
		Photo photo = this.convertPhotoVO2PO(photoDTO);
		photoDAO.save(photo);
	}

	private Photo convertPhotoVO2PO(PhotoDTO photoDTO) {
		Photo photo = null;
		
		if (photoDTO != null) {
			photo = new Photo();
			photo.setPhotoDate(photoDTO.getPhotoDate());
			photo.setPhotoName(photoDTO.getPhotoName());
			photo.setPhotoSize(photoDTO.getPhotoSize());
			photo.setPhotoURL(photoDTO.getPhotoURL());
			photo.setUserId(photoDTO.getUserId());
		}
		
		
		return photo;
	}

	@Override
	public List<PhotoDTO> searchAllPhotoByUserId(String userId) {
		
		String hqlWhere = " where 1=1 ";
		List<String> paramList = new ArrayList<String>();
		if (userId!=null && !"".equals(userId)) {
			hqlWhere += " and o.userId = ? ";
			paramList.add(userId);
		}
		Object[] objects = paramList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.photoDate", "desc");
		List<Photo> photoList = photoDAO.searchCollectionByConditionNoPage(hqlWhere, objects, orderby);
		
		List<PhotoDTO> photoDTOList = this.convertPhotoListPO2VO(photoList);
		
		return photoDTOList;
	}

	private List<PhotoDTO> convertPhotoListPO2VO(List<Photo> photoList) {
		
		List<PhotoDTO> photoDTOList = new ArrayList<PhotoDTO>();
		PhotoDTO photoDTO = null;
		
		for (int i=0; photoList!=null && i<photoList.size(); ++i) {
			Photo photo = photoList.get(i);
			photoDTO = new PhotoDTO();
			photoDTO.setPhotoDate(photo.getPhotoDate());
			photoDTO.setPhotoId(photo.getPhotoId());
			photoDTO.setPhotoName(photo.getPhotoName());
			photoDTO.setPhotoSize(photo.getPhotoSize());
			photoDTO.setPhotoURL(photo.getPhotoURL());
			photoDTO.setUserId(photo.getUserId());
			photoDTOList.add(photoDTO);
		}
		
		return photoDTOList;
	}

	@Override
	public void deletePhotoByPhotoId(String photoId) {
		photoDAO.deleteById(photoId);
	}

}
