package com.arex.blog.dao.impl;

import org.springframework.stereotype.Component;

import com.arex.blog.dao.CommentDAO;
import com.arex.blog.dao.CommonDAO;
import com.arex.blog.model.Comment;

@Component(value="commentDAOImpl")
public class CommentDAOImpl extends CommonDAOImpl<Comment> implements CommentDAO{

}
