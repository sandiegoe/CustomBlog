package com.arex.blog.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.arex.blog.dto.CommentDTO;
import com.arex.blog.service.CommentService;
import com.arex.blog.utils.LoginUtils;

@Component(value="commentAction")
public class CommentAction extends CommonAction<CommentDTO> {

	@Resource(name="commentServiceImpl")
	private CommentService commentService;
	
	public String add() {
		
		CommentDTO commentDTO = super.getModel();
		
		// 检查当前用户是否已经登录
		if (!LoginUtils.checkUserIsAlreadyLogin(session)) {
			request.setAttribute("messageInfo", "请登录.");
			return "signInPage";
		}
		if (commentDTO == null) {
			request.setAttribute("messageInfo", "请填写接收者或者发送内容.");
			return "addError";
		}
		if (commentDTO.getCommentContent()==null || "".equals(commentDTO.getCommentContent())) {
			request.setAttribute("messageInfo", "请填写接收者或者发送内容.");
			request.setAttribute("blogId", commentDTO.getBlogId());
			return "addError";
		}
		
		//保存一个评论
		commentService.saveComment(commentDTO);
		request.setAttribute("blogId", commentDTO.getBlogId());
		
		return "add";
	}
}
