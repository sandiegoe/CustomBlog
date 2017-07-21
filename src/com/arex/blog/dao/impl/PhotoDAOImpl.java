package com.arex.blog.dao.impl;

import org.springframework.stereotype.Component;

import com.arex.blog.dao.PhotoDAO;
import com.arex.blog.model.Photo;

@Component(value="photoDAOImpl")
public class PhotoDAOImpl extends CommonDAOImpl<Photo> implements PhotoDAO{
	
	
}
