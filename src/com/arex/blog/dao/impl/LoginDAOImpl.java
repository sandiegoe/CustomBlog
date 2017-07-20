package com.arex.blog.dao.impl;

import org.springframework.stereotype.Component;

import com.arex.blog.dao.LoginDAO;
import com.arex.blog.model.Login;

@Component(value="loginDAOImpl")
public class LoginDAOImpl extends CommonDAOImpl<Login> implements LoginDAO {



}
