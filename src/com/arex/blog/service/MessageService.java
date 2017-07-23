package com.arex.blog.service;

import java.util.List;

import com.arex.blog.dto.MessageDTO;

public interface MessageService {

	public void saveMessage(MessageDTO messageDTO);

	public List<MessageDTO> searchAllMessageByReceiverId(String userId);

	public MessageDTO searchMessageByMessageId(String messageId);

	public void updateMessageStatus(String messageId, int i);

	public List<MessageDTO> searchAllMessageByReceiverIdAndMessageStatus(String userId, int messageStatus);

	public void deleteAllMessage(String receiverId);

	public void readAllMessage(String receiverId);

}
