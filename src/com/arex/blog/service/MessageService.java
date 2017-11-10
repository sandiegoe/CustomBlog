package com.arex.blog.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.arex.blog.dto.BlogDTO;
import com.arex.blog.dto.MessageDTO;
import com.arex.blog.dto.UserDTO;

public interface MessageService {

	public void saveMessage(MessageDTO messageDTO);

	public List<MessageDTO> searchAllMessageByReceiverId(String userId);

	public MessageDTO searchMessageByMessageId(String messageId);

	public void updateMessageStatus(String messageId, int i);

	public List<MessageDTO> searchAllMessageByReceiverIdAndMessageStatus(String userId, int messageStatus);

	public void deleteAllMessage(String receiverId);

	public void readAllMessage(String receiverId);

	public void deleteMessageByMessageId(MessageDTO messageDTO);

	public List<MessageDTO> searchAllMessageByReceiverId(
			HttpServletRequest request, String userId);

	public List<MessageDTO> searchAllMessageBySenderId(
			HttpServletRequest request, String userId);

	public List<MessageDTO> searchAllMessageBySenderIdIdAndMessageStatus(
			String senderId, int messageStatus);

}
