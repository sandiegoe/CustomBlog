package com.arex.blog.service.impl;

import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.arex.blog.dao.MessageDAO;
import com.arex.blog.dto.MessageDTO;
import com.arex.blog.model.Message;
import com.arex.blog.service.MessageService;
import com.arex.blog.service.UserService;

@Component(value="messageServiceImpl")
public class MessageServiceImpl implements MessageService {

	@Resource(name="messageDAOImpl")
	private MessageDAO messageDAO;
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@Override
	public void saveMessage(MessageDTO messageDTO) {
		
		//如果receiverId不止一个，则需要分解出每一receiverId，分别发送，在Message表中插入对应的记录
		String receiverId = messageDTO.getReceiverId();
		receiverId = receiverId.replace('，', ',');
		String[] reIds = receiverId.split(",");
		System.out.println(Arrays.toString(reIds));
		
		//循环发送消息
		for (int i=0; i<reIds.length; ++i) {
			//messageDAO.save(message);
			Message message = this.convertMessageVO2PO(messageDTO, reIds[i].trim());
			messageDAO.save(message);
			//查找接收者是否存在，如果存在则设置Message表中的messageStatus ： 1  为发送成功
			if (userService.isExistsUserByUserId(message.getReceiverId())) {
				//根据senderId， receiverId,messageContent 查找最近的一条消息的messageId
				String messageId = this.searchMessageIdRecentlyAdd(message.getSenderId(), message.getReceiverId(), message.getMessageContent());
				//更新messageStatus 为 1
				this.updateMessageStatus(messageId, 1);
			}
		}
	}

	private Message convertMessageVO2PO(MessageDTO messageDTO, String receiverId) {
		
		Message message = null;
		
		if (messageDTO != null) {
			message = new Message();
			message.setMessageContent(messageDTO.getMessageContent());
			message.setMessageDate(new Date());
			message.setMessageIsDelete(messageDTO.getMessageIsDelete());
			message.setMessageStatus(messageDTO.getMessageStatus());
			message.setMessageTitle(messageDTO.getMessageTitle());
			message.setReceiverId(receiverId);
			message.setSenderId(messageDTO.getSenderId());
			message.setSenderIp(messageDTO.getSenderIp());
		}
		
		return message;
	}

	public String searchMessageIdRecentlyAdd(String senderId, String receiverId, String messageContent) {
		String messageId = messageDAO.searchMessageIdRecentlyAdd(senderId, receiverId, messageContent);
		return messageId;
	}
	
	public void updateMessageStatus(String messageId, int messageStatus) {
		//判断messageId不为空
		if (messageId!=null && !"".equals(messageId)) {
			messageDAO.updateMessageStatus(messageId, messageStatus);
		}
	}
}
