package com.arex.blog.service;

import java.util.List;

import com.arex.blog.dto.CommentDTO;

public interface CommentService {

	public void saveComment(CommentDTO commentDTO);

	public List<CommentDTO> searchAllCommentByBlogId(String blogId);

	public void halfDelete(CommentDTO commentDTO);

	public CommentDTO searchCommentByCommentId(String parentId);
}
