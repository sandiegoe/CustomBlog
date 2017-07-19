package com.arex.blog.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Component;

import com.arex.blog.dao.BlogDAO;
import com.arex.blog.dao.UserDAO;
import com.arex.blog.dto.BlogDTO;
import com.arex.blog.dto.UserDTO;
import com.arex.blog.model.Blog;
import com.arex.blog.model.User;
import com.arex.blog.service.BlogService;
import com.arex.blog.service.UserService;

@Component(value = "blogServiceImpl")
public class BlogServiceImpl implements BlogService {

	@Resource(name = "blogDAOImpl")
	private BlogDAO blogDAO;
	@Resource(name = "userDAOImpl")
	private UserDAO userDAO;
	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Override
	public List<BlogDTO> searchAllBlogByUserId(String userId) {

		String hqlWhere = " where 1=1 ";
		List<String> paramList = new ArrayList<String>();
		if (userId != null && !"".equals(userId)) {
			hqlWhere += " and o.userId=? ";
			paramList.add(userId);
		}
		Object[] objects = paramList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.blogCreateDate", "desc");

		List<Blog> blogList = blogDAO.searchCollectionByConditionNoPage(
				hqlWhere, objects, orderby);
		List<BlogDTO> blogDTOList = this.convertBlogPO2VO2(blogList);

		return blogDTOList;
	}

	@Deprecated
	/**
	 * 将Blog的集合转换为BlogDTO的集合
	 * @param blogList 博客集合
	 * @return List<BlogDTO>
	 * @author arex
	 * @Time2012-11-20 15:02:29
	 */
	private List<BlogDTO> convertBlogPO2VO(List<Blog> blogList) {

		List<BlogDTO> blogDTOList = new ArrayList<BlogDTO>();
		BlogDTO blogDTO = null;
		for (int i = 0; blogList != null && i < blogList.size(); ++i) {
			blogDTO = new BlogDTO();
			Blog blog = blogList.get(i);
			blogDTO.setBlogCommentCounts(blog.getBlogCommentCounts());
			// TODO blogDTO.setBlogContent(blog.getBlogContent());
			// TODO blogDTO.setBlogContentText(blog.getBlogContentText());
			blogDTO.setBlogCreateDate(blog.getBlogCreateDate());
			blogDTO.setBlogDescription(blog.getBlogDescription());
			blogDTO.setBlogId(blog.getBlogId());
			blogDTO.setBlogReadCounts(blog.getBlogReadCounts());
			blogDTO.setBlogTitle(blog.getBlogTitle());
			blogDTO.setKindId(blog.getKindId());
			blogDTO.setLastModifieDate(blog.getLastModifieDate());
			blogDTO.setUserId(blog.getUserId());
			blogDTOList.add(blogDTO);
		}
		return blogDTOList;
	}

	@Override
	public List<BlogDTO> searchAllBlogByUserName(String userName) {

		// 获取userName的userId
		UserDTO userDTO = userService.searchUserByUserName(userName);
		if (userDTO == null || userDTO.getUserId() == null
				|| "".equals(userDTO.getUserId())) {
			return null;
		}

		// 调用searchAllBlogByUserId
		List<BlogDTO> blogDTOList = this.searchAllBlogByUserId(userDTO
				.getUserId());

		return blogDTOList;
	}

	@Override
	public void saveBlog(BlogDTO blogDTO) {

		Blog blog = this.convertBlogVO2PO(blogDTO);

		blogDAO.save(blog);
	}

	private Blog convertBlogVO2PO(BlogDTO blogDTO) {

		Blog blog = null;

		if (blogDAO != null) {
			blog = new Blog();
			blog.setBlogCreateDate(new Date());
			blog.setBlogContent(getBlobFromString(blogDTO.getBlogContent()));
			blog.setBlogContentText(getBlobFromString(blogDTO.getBlogContentText()));
			blog.setBlogDescription("原创博客，请勿转载.");
			blog.setBlogTitle(blogDTO.getBlogTitle());
			blog.setLastModifieDate(new Date());
			blog.setUserId(blogDTO.getUserId());
			// TODO 博客分类
			blog.setKindId("1");
		}

		return blog;
	}

	@Override
	public BlogDTO searchBlogByBlogId(String blogId) {
		String hqlWhere = " where 1=1 ";
		List<String> paramList = new ArrayList<String>();
		if (blogId != null && !"".equals(blogId)) {
			hqlWhere += " and o.blogId=? ";
			paramList.add(blogId);
		}
		Object[] objects = paramList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.blogCreateDate", "desc");

		List<Blog> blogList = blogDAO.searchCollectionByConditionNoPage(
				hqlWhere, objects, orderby);
		List<BlogDTO> blogDTOList = this.convertBlogPO2VO2(blogList);

		if (blogDTOList != null && blogDTOList.size() > 0) {
			return blogDTOList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<BlogDTO> searchAllBlog() {

		String hqlWhere = " where 1=1 ";
		List<String> paramList = new ArrayList<String>();

		Object[] objects = null;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.blogCreateDate", "desc");

		List<Blog> blogList = blogDAO.searchCollectionByConditionNoPage(
				hqlWhere, objects, orderby);
		List<BlogDTO> blogDTOList = this.convertBlogPO2VO2(blogList);

		return blogDTOList;
	}

	private List<BlogDTO> convertBlogPO2VO2(List<Blog> blogList) {

		List<BlogDTO> blogDTOList = new ArrayList<BlogDTO>();
		BlogDTO blogDTO = null;
		for (int i = 0; blogList != null && i < blogList.size(); ++i) {
			blogDTO = new BlogDTO();
			Blog blog = blogList.get(i);
			blogDTO.setBlogCommentCounts(blog.getBlogCommentCounts());
			blogDTO.setBlogContent(getStringFromBlob(blog.getBlogContent()));
			blogDTO.setBlogContentText(getStringFromBlob(blog
					.getBlogContentText()));
			blogDTO.setBlogCreateDate(blog.getBlogCreateDate());
			blogDTO.setBlogDescription(blog.getBlogDescription());
			blogDTO.setBlogId(blog.getBlogId());
			blogDTO.setBlogReadCounts(blog.getBlogReadCounts());
			blogDTO.setBlogTitle(blog.getBlogTitle());
			blogDTO.setKindId(blog.getKindId());
			blogDTO.setLastModifieDate(blog.getLastModifieDate());
			blogDTO.setUserId(blog.getUserId());
			blogDTOList.add(blogDTO);
		}
		return blogDTOList;
	}

	public String getStringFromBlob(Blob blob) {
		String str = "";

		try {
			InputStream in = blob.getBinaryStream();
			ByteArrayInputStream bais = (ByteArrayInputStream) in;
			byte[] buffer = new byte[bais.available()];
			bais.read(buffer, 0, buffer.length);
			str = new String(buffer, "utf-8");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return str;
	}
	
	public Blob getBlobFromString(String str) {
		
		Blob blob = null;
		
		try {
			blob = new SerialBlob(str.getBytes());
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return blob;
	}
}
