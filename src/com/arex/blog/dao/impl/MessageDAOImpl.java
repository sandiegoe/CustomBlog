package com.arex.blog.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.blog.dao.MessageDAO;
import com.arex.blog.dto.MessageDTO;
import com.arex.blog.model.Message;
import com.arex.blog.utils.PageInfo;
import com.sun.istack.internal.FinalArrayList;

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

	@Override
	public List<MessageDTO> getAllMessageByReceiverId(final String userId) {
		
		List<Message> messageList = hibernateTemplate.execute(new HibernateCallback<List<Message>>() {

			@Override
			public List<Message> doInHibernate(Session session) throws HibernateException {
				//读取receiverId为userId的，且没有删除，不是发送失败的消息(包括发送成功和已经阅读的消息)
				List<Message> messageList = session.createQuery("from Message message where message.receiverId=:receiverId and message.messageIsDelete=0 and message.messageStatus<>0")
					.setParameter("receiverId", userId)
					.getResultList();
				return messageList;
			}
		});
		
		List<MessageDTO> messageDTOList = this.convertMessagePO2VO(messageList);
		
		return messageDTOList;
	}

	private List<MessageDTO> convertMessagePO2VO(List<Message> messageList) {
		List<MessageDTO> messageDTOList = new ArrayList<MessageDTO>();
		MessageDTO messageDTO = null;
		for (int i=0; messageList!=null && i<messageList.size(); ++i) {
			messageDTO = new MessageDTO();
			Message message = messageList.get(i);
			messageDTO.setMessageContent(message.getMessageContent());
			messageDTO.setMessageDate(message.getMessageDate());
			messageDTO.setMessageId(message.getMessageId());
			messageDTO.setMessageIsDelete(message.getMessageIsDelete());
			messageDTO.setMessageStatus(message.getMessageStatus());
			messageDTO.setMessageTitle(message.getMessageTitle());
			messageDTO.setReceiverId(message.getReceiverId());
			messageDTO.setSenderId(message.getSenderId());
			messageDTO.setSenderIp(message.getSenderIp());
//			messageDTO.setUserNames();  // message 中未定义userNames属性
			messageDTOList.add(messageDTO);
		}
		return messageDTOList;
	}

	@Override
	public List<MessageDTO> searchAllMessageByReceiverIdAndMessageStatus(
			final String userId, final int messageStatus) {
		List<Message> messageList = hibernateTemplate.execute(new HibernateCallback<List<Message>>() {

			@Override
			public List<Message> doInHibernate(Session session) throws HibernateException {
				//读取receiverId为userId的，且没有删除，不是发送失败的消息(包括发送成功和已经阅读的消息)
				List<Message> messageList = session.createQuery("from Message message where message.receiverId=:receiverId and message.messageIsDelete=0 and message.messageStatus=:messageStatus order by message.messageDate desc")
					.setParameter("receiverId", userId)
					.setParameter("messageStatus", messageStatus)
					.getResultList();
				return messageList;
			}
		});
		
		List<MessageDTO> messageDTOList = this.convertMessagePO2VO(messageList);
		
		return messageDTOList;
	}
	
	@Override
	public List<MessageDTO> searchAllMessageByReceiverIdAndMessageStatus(
			final String userId, final int messageStatus, final PageInfo pageInfo) {
		List<Message> messageList = hibernateTemplate.execute(new HibernateCallback<List<Message>>() {

			@Override
			public List<Message> doInHibernate(Session session) throws HibernateException {
				//读取receiverId为userId的，且没有删除，不是发送失败的消息(包括发送成功和已经阅读的消息)
				Query query = session.createQuery("from Message message where message.receiverId=:receiverId and message.messageIsDelete=0 and message.messageStatus=:messageStatus order by message.messageDate desc")
					.setParameter("receiverId", userId)
					.setParameter("messageStatus", messageStatus);
				pageInfo.setTotalResult(query.getResultList().size());
				query.setFirstResult(pageInfo.getBeginResult());
				query.setMaxResults(pageInfo.getPageSize());
				List<Message> messageList = query.getResultList();
				return messageList;
			}
		});
		
		List<MessageDTO> messageDTOList = this.convertMessagePO2VO(messageList);
		
		return messageDTOList;
	}

	@Override
	public void deleteAllMessage(final String receiverId) {
		hibernateTemplate.execute(new HibernateCallback<String>() {

			@Override
			public String doInHibernate(Session session) throws HibernateException {
				session.createQuery("delete from Message message where message.receiverId=:receiverId")
					.setParameter("receiverId", receiverId)
					.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public void readAllmessage(final String receiverId) {
		hibernateTemplate.execute(new HibernateCallback<String>() {

			@Override
			public String doInHibernate(Session session) throws HibernateException {
				session.createQuery("update Message message set message.messageStatus = 2 where message.receiverId=:receiverId")
					.setParameter("receiverId", receiverId)
					.executeUpdate();
				return null;
			}
		});
	}
}
