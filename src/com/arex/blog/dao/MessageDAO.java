package com.arex.blog.dao;

import java.util.List;

import com.arex.blog.dto.MessageDTO;
import com.arex.blog.model.Message;

public interface MessageDAO extends CommonDAO<Message>{

	public void updateMessageStatus(String messageId, int messageStatus);

	public String searchMessageIdRecentlyAdd(String senderId, String receiverId,
			String messageContent);

	public List<MessageDTO> getAllMessageByReceiverId(String userId);

	public List<MessageDTO> searchAllMessageByReceiverIdAndMessageStatus(
			String userId, int messageStatus);

	public void deleteAllMessage(String receiverId);

	public void readAllmessage(String receiverId);

}
