package com.arex.blog.dao;

import com.arex.blog.model.Message;

public interface MessageDAO extends CommonDAO<Message>{

	public void updateMessageStatus(String messageId, int messageStatus);

	public String searchMessageIdRecentlyAdd(String senderId, String receiverId,
			String messageContent);

}
