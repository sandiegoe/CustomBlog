package com.arex.blog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.arex.blog.dao.CommentDAO;
import com.arex.blog.dto.CommentDTO;
import com.arex.blog.dto.UserDTO;
import com.arex.blog.model.Comment;
import com.arex.blog.service.CommentService;
import com.arex.blog.service.UserService;

@Component(value="commentServiceImpl")
public class CommentServiceImpl implements CommentService {

	@Resource(name="commentDAOImpl")
	private CommentDAO commentDAO;
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@Override
	public void saveComment(CommentDTO commentDTO) {
		Comment comment = this.convertCommentVO2PO(commentDTO);
		commentDAO.save(comment);
		
		return;
	}

	private Comment convertCommentVO2PO(CommentDTO commentDTO) {
		Comment comment = null;
		if (commentDTO != null) {
			comment = new Comment();
			comment.setBlogId(commentDTO.getBlogId());
			comment.setCommentContent(commentDTO.getCommentContent());
			comment.setCommentDate(new Date());
			comment.setDeleteSign(commentDTO.getDeleteSign());
			comment.setCommentId(commentDTO.getCommentId());
			comment.setParentId(commentDTO.getParentId());
			comment.setUserId(commentDTO.getUserId());
		}
		return comment;
	}

	@Override
	public List<CommentDTO> searchAllCommentByBlogId(String blogId) {
		
		String hqlWhere = " where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (blogId !=null) {
			hqlWhere += " and o.blogId=? ";
			paramList.add(blogId);
		}
		//筛选出尚未删除的comment
		hqlWhere += " and o.deleteSign=0 ";
		Object[] objects = paramList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.commentDate", "asc");
		
		List<Comment> commentList = commentDAO.searchCollectionByConditionNoPage(hqlWhere, objects, orderby);
		List<CommentDTO> commentDTOList = this.convertCommentListPO2VO(commentList);
		
		return commentDTOList;
	}

	private List<CommentDTO> convertCommentListPO2VO(List<Comment> commentList) {
		
		List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
		CommentDTO commentDTO = null;
		
		for (int i=0; commentList!=null && i<commentList.size(); ++i) {
			commentDTO = new CommentDTO();
			Comment comment = commentList.get(i);
			commentDTO.setBlogId(comment.getBlogId());
			commentDTO.setCommentContent(comment.getCommentContent());
			commentDTO.setCommentDate(comment.getCommentDate());
			commentDTO.setCommentId(comment.getCommentId());
			commentDTO.setDeleteSign(comment.getDeleteSign());
			commentDTO.setParentId(comment.getParentId());
			commentDTO.setUserId(comment.getUserId());
			UserDTO searchUserDTO = userService.searchUserByUserId(comment.getUserId());
			String userName = searchUserDTO==null ? "" : searchUserDTO.getUserName();
			commentDTO.setUserName(userName);
			//取出当前评论的父评论
			Comment parentComment = commentDAO.searchById(comment.getParentId());
			String parentName = "";
			if (parentComment == null || parentComment.getUserId()==null || "".equals(parentComment.getUserId())) {
				parentName = "";
			} else {
				UserDTO searchUserDTO2 = userService.searchUserByUserId(parentComment.getUserId());
				parentName = searchUserDTO2==null ? "" : searchUserDTO2.getUserName();
			}
			commentDTO.setParentName(parentName);
			commentDTOList.add(commentDTO);
		}
		
		return commentDTOList;
	}

	@Override
	public void halfDelete(CommentDTO commentDTO) {
		Comment comment = commentDAO.searchById(commentDTO.getCommentId());
		comment.setDeleteSign(1);
		commentDAO.update(comment);
	}

	@Override
	public CommentDTO searchCommentByCommentId(String commentId) {
		Comment comment = commentDAO.searchById(commentId);
		CommentDTO commentDTO = this.convertCommentPO2VO(comment);
		return commentDTO;
	}

	private CommentDTO convertCommentPO2VO(Comment comment) {
		CommentDTO commentDTO = null;
		
		if (comment != null) {
			commentDTO = new CommentDTO();
			commentDTO.setBlogId(comment.getBlogId());
			commentDTO.setCommentContent(comment.getCommentContent());
			commentDTO.setCommentDate(comment.getCommentDate());
			commentDTO.setCommentId(comment.getCommentId());
			commentDTO.setDeleteSign(comment.getDeleteSign());
			commentDTO.setParentId(comment.getParentId());
			commentDTO.setUserId(comment.getUserId());
			UserDTO searchUserDTO = userService.searchUserByUserId(comment.getUserId());
			String userName = searchUserDTO==null ? "" : searchUserDTO.getUserName();
			commentDTO.setUserName(userName);
			//取出当前评论的父评论
			Comment parentComment = commentDAO.searchById(comment.getParentId());
			String parentName = "";
			if (parentComment == null || parentComment.getUserId()==null || "".equals(parentComment.getUserId())) {
				parentName = "";
			} else {
				UserDTO searchUserDTO2 = userService.searchUserByUserId(parentComment.getUserId());
				parentName = searchUserDTO2==null ? "" : searchUserDTO2.getUserName();
			}
			commentDTO.setParentName(parentName);
		}
		return commentDTO;
	}

	
}
