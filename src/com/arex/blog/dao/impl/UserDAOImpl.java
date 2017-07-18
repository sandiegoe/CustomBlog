package com.arex.blog.dao.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.blog.dao.UserDAO;
import com.arex.blog.model.User;

@Component("userDAOImpl")
public class UserDAOImpl extends CommonDAOImpl<User> implements UserDAO {

	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	

	@Override
	public void changePassword(final String logonPassword, final String userId) {
		hibernateTemplate.execute(new HibernateCallback<String>() {

			@Override
			public String doInHibernate(Session session) throws HibernateException {
				session.createQuery("update User user set user.logonPassword=:logonPassword where user.userId=:userId")
					.setParameter("logonPassword", logonPassword)
					.setParameter("userId", userId)
					.executeUpdate();
				return null;
			}
		});
	}


	@Override
	public void setNewLastLogonDate(final String userId, final Date lastLogonDate) {
		hibernateTemplate.execute(new HibernateCallback<String>() {

			@Override
			public String doInHibernate(Session session) throws HibernateException {
				session.createQuery("update User user set user.lastLogonDate=:lastLogonDate where user.userId=:userId")
					.setParameter("lastLogonDate", lastLogonDate)
					.setParameter("userId", userId)
					.executeUpdate();
				return null;
			}
		});
	}

}
