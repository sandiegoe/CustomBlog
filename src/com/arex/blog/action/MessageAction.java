package com.arex.blog.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.MessageDTO;
import com.arex.blog.dto.UserDTO;
import com.arex.blog.service.MessageService;
import com.arex.blog.service.UserService;
import com.arex.blog.utils.FileUtils;
import com.arex.blog.utils.LoginUtils;

@Component(value = "messageAction")
@Scope(value = "prototype")
public class MessageAction extends CommonAction<MessageDTO> {

	@Resource(name = "messageServiceImpl")
	private MessageService messageService;

	public String add() {

		MessageDTO messageDTO = super.getModel();
		UserDTO userDTO = (UserDTO) session.getAttribute("loginUser");

		// 检查当前用户是否已经登录
		if (!LoginUtils.checkUserIsAlreadyLogin(session)) {
			request.setAttribute("messageInfo", "请登录.");
			return "signInPage";
		}
		if (messageDTO == null) {
			request.setAttribute("messageInfo", "请填写接收者或者发送内容.");
			return "toMessage";
		}
		if (messageDTO.getReceiverId()==null || "".equals(messageDTO.getReceiverId()) || messageDTO.getMessageContent()==null || "".equals(messageDTO.getMessageContent())) {
			request.setAttribute("messageInfo", "请填写接收者或者发送内容.");
			request.setAttribute("receiverId", messageDTO.getReceiverId());
			request.setAttribute("messageContent", messageDTO.getMessageContent());
			return "addError";
		}
		
		// 手动设置消息的发送者为当前登录的用户
		messageDTO.setSenderId(userDTO.getUserId());
		// 设置IP
		String senderIp = ServletActionContext.getRequest().getRemoteAddr();
		messageDTO.setSenderIp(senderIp);
		//设置messageTitle 为messgaeContent的前20个字符
		if(messageDTO.getMessageContent().length() > 20) {
			messageDTO.setMessageTitle(messageDTO.getMessageContent().substring(0, 10));
		} else {
			messageDTO.setMessageTitle(messageDTO.getMessageContent());
		}
		// 保存消息
		messageService.saveMessage(messageDTO);

		return "add";
	}
}
