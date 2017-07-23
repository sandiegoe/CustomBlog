package com.arex.blog.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.blog.dao.CommonDAO;
import com.arex.blog.dao.MessageDAO;
import com.arex.blog.model.Message;

@Component(value="messageDAOImpl")
public class MessageDAOImpl extends CommonDAOImpl<Message> implements MessageDAO {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void updateMessageStatus(final String messageId, final int messageStatus) {
		hibernateTemplate.execute(new HibernateCallback<String>() {

			@Override
			public String doInHibernate(Session session) throws HibernateException {
				session.createQuery("update Message message set message.messageStatus=:messageStatus where message.messageId=:messageId")
					.setParameter("messageStatus", messageStatus)
					.setParameter("messageId", messageId)
					.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public String searchMessageIdRecentlyAdd(final String senderId, final String receiverId, final String messageContent) {
		
		String messageId = null;
		List<Message> messageList = hibernateTemplate.execute(new HibernateCallback<List<Message>>() {

			@Override
			public List<Message> doInHibernate(Session session) throws HibernateException {
				List<Message> resultList = session.createQuery("from Message message where message.senderId=:senderId and message.receiverId =:receiverId and messageContent=:messageContent order by message.messageDate desc")
					.setParameter("senderId", senderId)
					.setParameter("receiverId", receiverId)
					.setParameter("messageContent", messageContent)
					.getResultList();
				return resultList;
			}
		});

		if (messageList!=null && messageList.size()>0) {
			messageId = messageList.get(0).getMessageId(); 
		}
		
		return messageId;
	}

	
}
