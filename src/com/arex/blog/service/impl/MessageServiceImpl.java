package com.arex.blog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.arex.blog.dao.MessageDAO;
import com.arex.blog.dto.BlogDTO;
import com.arex.blog.dto.MessageDTO;
import com.arex.blog.dto.UserDTO;
import com.arex.blog.model.Message;
import com.arex.blog.service.MessageService;
import com.arex.blog.service.UserService;
import com.arex.blog.utils.PageInfo;

@Component(value="messageServiceImpl")
public class MessageServiceImpl implements MessageService {

	@Resource(name="messageDAOImpl")
	private MessageDAO messageDAO;
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@Override
	public void saveMessage(MessageDTO messageDTO) {
		
		/*//如果receiverId不止一个，则需要分解出每一receiverId，分别发送，在Message表中插入对应的记录
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
		}*/
		
		//如果userNames不止一个，则需要分解出每一userName，分别发送，在Message表中插入对应的记录
		String userNames = messageDTO.getUserNames();
		userNames = userNames.replace('，', ',');
		String[] userNamesTemp = userNames.split(",");
		
		//循环发送消息
		for (int i=0; i<userNamesTemp.length; ++i) {
			String userName = userNamesTemp[i].trim();
			//获取这个用户名所对应的userId
			String userId;
			UserDTO userDTO = userService.searchUserByUserName(userName);
			int messageStatus = userDTO==null ? 0 :1;
			//更新messageDTO的messageStatus
			messageDTO.setMessageStatus(messageStatus);
			userId = userDTO==null ? "" : userDTO.getUserId();
			
			Message message = this.convertMessageVO2PO(messageDTO, userId);
			messageDAO.save(message);
			
			/*//查找接收者是否存在，如果存在则设置Message表中的messageStatus ： 1  为发送成功
			if (userService.isExistsUserByUserId(message.getReceiverId())) {
				//根据senderId， receiverId,messageContent 查找最近的一条消息的messageId
				String messageId = this.searchMessageIdRecentlyAdd(message.getSenderId(), message.getReceiverId(), message.getMessageContent());
				//更新messageStatus 为 1
				this.updateMessageStatus(messageId, 1);
			}*/
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

	@Deprecated
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

	/**
	 * 获取所有成功发送打消息
	 * @param receiverId
	 * @return
	 */
	@Override
	public List<MessageDTO> searchAllMessageByReceiverId(String receiverId) {
		String hqlWhere = " where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (receiverId!=null && !"".equals(receiverId)) {
			hqlWhere += " and o.receiverId = ? ";
			paramList.add(receiverId);
		}
		hqlWhere += " and o.messageIsDelete = ? ";
		paramList.add(0);
		hqlWhere += " and o.messageStatus <> ? ";
		paramList.add(0);
		
		Object[] objects = paramList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.messageDate", "desc");
		List<Message> messageList = messageDAO.searchCollectionByConditionNoPage(hqlWhere, objects, orderby);
		
		List<MessageDTO> messageDTOList = this.convertMessageListPO2VO(messageList);
		return messageDTOList;
	}
	
	@Override
	public List<MessageDTO> searchAllMessageByReceiverId(
			HttpServletRequest request, String receiverId) {
		
		String hqlWhere = " where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (receiverId!=null && !"".equals(receiverId)) {
			hqlWhere += " and o.receiverId = ? ";
			paramList.add(receiverId);
		}
		hqlWhere += " and o.messageIsDelete = ? ";
		paramList.add(0);
		hqlWhere += " and o.messageStatus <> ? ";
		paramList.add(0);
		
		Object[] objects = paramList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.messageDate", "desc");
		PageInfo pageInfo = new PageInfo(request);
		List<Message> messageList = messageDAO.searchCollectionByCondition(hqlWhere, objects, orderby, pageInfo);
		
		List<MessageDTO> messageDTOList = this.convertMessageListPO2VO(messageList);
		
		request.setAttribute("page", pageInfo.getPage());
		
		return messageDTOList;
	}
	/**
	 * 查询所有指定senderId发送未删除的消息，包括未成功，已发送和已阅读的消息
	 * 按照发送时间降序排列
	 */
	@Override
	public List<MessageDTO> searchAllMessageBySenderId(
			HttpServletRequest request, String senderId) {
		
		String hqlWhere = " where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (senderId!=null && !"".equals(senderId)) {
			hqlWhere += " and o.senderId=? ";
			paramList.add(senderId);
		}
		hqlWhere += " and o.messageIsDelete = ? ";
		paramList.add(0);
		
		Object[] objects = paramList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.messageDate", "desc");
		PageInfo pageInfo = new PageInfo(request);
		List<Message> messageList = messageDAO.searchCollectionByCondition(hqlWhere, objects, orderby, pageInfo);
		List<MessageDTO> messageDTOList = this.convertMessageListPO2VO(messageList);
		
		request.setAttribute("page", pageInfo.getPage());
		
		return messageDTOList;
	}
	
	private List<MessageDTO> convertMessageListPO2VO(List<Message> messageList) {
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
	public MessageDTO searchMessageByMessageId(String messageId) {
		Message message = messageDAO.searchById(messageId);
		MessageDTO messageDTO = this.convertMessagePO2VO(message);
		return messageDTO;
	}
	
	private MessageDTO convertMessagePO2VO(Message message) {
		MessageDTO messageDTO = null;
		if (message != null) {
			messageDTO = new MessageDTO();
			messageDTO.setMessageContent(message.getMessageContent());
			messageDTO.setMessageDate(message.getMessageDate());
			messageDTO.setMessageId(message.getMessageId());
			messageDTO.setMessageIsDelete(message.getMessageIsDelete());
			messageDTO.setMessageStatus(message.getMessageStatus());
			messageDTO.setMessageTitle(message.getMessageTitle());
			messageDTO.setReceiverId(message.getReceiverId());
			messageDTO.setSenderId(message.getSenderId());
			messageDTO.setSenderIp(message.getSenderIp());
			//获取该消息的发送人名字
			UserDTO senderUserDTO = userService.searchUserByUserId(message.getSenderId());
			String userName = senderUserDTO==null ? "" : senderUserDTO.getUserName();
			messageDTO.setUserNames(userName);
		}
		return messageDTO;
	}

	@Override
	public List<MessageDTO> searchAllMessageByReceiverIdAndMessageStatus(
			String receiverId, int messageStatus) {
		
		String hqlWhere = " where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (receiverId!=null && !"".equals(receiverId)) {
			hqlWhere += " and o.receiverId = ? ";
			paramList.add(receiverId);
		}
		hqlWhere += " and o.messageIsDelete = ? ";
		paramList.add(0);
		hqlWhere += " and o.messageStatus = ? ";
		paramList.add(messageStatus);
		
		Object[] objects = paramList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.messageDate", "desc");
		List<Message> messageList = messageDAO.searchCollectionByConditionNoPage(hqlWhere, objects, orderby);
		
		List<MessageDTO> messageDTOList = this.convertMessageListPO2VO(messageList);
		return messageDTOList;
	}
	
	/**
	 * 查询指定senderId发送和指定messageStatus的消息
	 */
	@Override
	public List<MessageDTO> searchAllMessageBySenderIdIdAndMessageStatus(
			String senderId, int messageStatus) {
		String hqlWhere = " where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (senderId!=null && !"".equals(senderId)) {
			hqlWhere += " and o.senderId = ? ";
			paramList.add(senderId);
		}
		hqlWhere += " and o.messageIsDelete = ? ";
		paramList.add(0);
		hqlWhere += " and o.messageStatus = ? ";
		paramList.add(messageStatus);
		
		Object[] objects = paramList.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.messageDate", "desc");
		List<Message> messageList = messageDAO.searchCollectionByConditionNoPage(hqlWhere, objects, orderby);
		
		List<MessageDTO> messageDTOList = this.convertMessageListPO2VO(messageList);
		return messageDTOList;
	}
	
	@Override
	public void deleteAllMessage(String receiverId) {
		messageDAO.deleteAllMessage(receiverId);
		return;
	}

	@Override
	public void readAllMessage(String receiverId) {
		messageDAO.readAllmessage(receiverId);
		return;
	}

	@Override
	public void deleteMessageByMessageId(MessageDTO messageDTO) {
		messageDAO.deleteById(messageDTO.getMessageId());
	}
}
