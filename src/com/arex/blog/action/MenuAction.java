package com.arex.blog.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arex.blog.dto.BlogDTO;
import com.arex.blog.dto.CategoryDTO;
import com.arex.blog.dto.CommentDTO;
import com.arex.blog.dto.MenuDTO;
import com.arex.blog.dto.MessageDTO;
import com.arex.blog.dto.PhotoDTO;
import com.arex.blog.dto.UserDTO;
import com.arex.blog.service.BlogService;
import com.arex.blog.service.CategoryService;
import com.arex.blog.service.CommentService;
import com.arex.blog.service.LoginService;
import com.arex.blog.service.MessageService;
import com.arex.blog.service.PhotoService;
import com.arex.blog.service.UserService;
import com.arex.blog.utils.LoginUtils;

@Component(value = "menuAction")
@Scope(value = "prototype")
public class MenuAction extends CommonAction<MenuDTO> {

	@Resource(name = "userServiceImpl")
	private UserService userService;
	@Resource(name = "loginServiceImpl")
	private LoginService loginService;
	@Resource(name = "blogServiceImpl")
	private BlogService blogService;
	@Resource(name="photoServiceImpl")
	private PhotoService photoService;
	@Resource(name="messageServiceImpl")
	private MessageService messageService;
	@Resource(name="commentServiceImpl")
	private CommentService commentService;
	@Resource(name = "categoryServiceImpl")
	private CategoryService categoryService;

	public String home() {

//		List<BlogDTO> blogDTOList = blogService.searchAllBlog();
		List<BlogDTO> blogDTOList = blogService.searchAllBlog((HttpServletRequest)request);
		List<CategoryDTO> categoryDTOList = categoryService.searchAllCategory();
		for (CategoryDTO categoryDTO : categoryDTOList) {
			int counts = blogService.searchBlogCountsByCategoryId(categoryDTO.getCategoryId());
			categoryDTO.setCounts(counts);
		}
		// 将categoryDTOList设置到application中
		application.setAttribute("categoryDTOList", categoryDTOList);
		request.setAttribute("blogDTOList", blogDTOList);

		return "home";
	}

	public String signInPage() {
		return "signInPage";
	}

	public String registerPage() {
		return "registerPage";
	}

	//判断是否已经登录
	//如果登录，显示个人照片
	//如果没有登录，则跳到登录页面
	public String photo() {
		
		boolean isLogin = LoginUtils.checkUserIsAlreadyLogin(session);
		if (!isLogin) {
			request.setAttribute("message", "请登录.");
			return "signInPage";
		}
		
		//查询当前用户所有图片
//		List<PhotoDTO> photoDTOList = photoService.searchAllPhotoByUserId(((UserDTO)session.getAttribute("loginUser")).getUserId());
		//启用分页查询
		List<PhotoDTO> photoDTOList = photoService.searchAllPhotoByUserId((HttpServletRequest)request, ((UserDTO)session.getAttribute("loginUser")).getUserId());
		//设置到request中去
		request.setAttribute("photoDTOList", photoDTOList);
		
		return "photo";
	}

	// 查询所有用户的博客
	// 跳转到博客主页面
	public String blog() {
		
		List<BlogDTO> blogDTOList = null;

		//判断是否登录
		boolean isLogin = LoginUtils.checkUserIsAlreadyLogin(session);
		if (isLogin) {
			//已经登录，显示个人博客列表
//			blogDTOList = blogService.searchAllBlogByUserId(((UserDTO)session.getAttribute("loginUser")).getUserId());
			blogDTOList = blogService.searchAllBlogByUserId((HttpServletRequest)request, ((UserDTO)session.getAttribute("loginUser")).getUserId());
		} else {
			//没有登录，显示所有博客列表
			//blogDTOList = blogService.searchAllBlog();
			request.setAttribute("messageInfo", "未登录，请登录...");
			return "signInPage";
		}
		
		//设置当前登陆用户的分类列别
		List<CategoryDTO> categoryDTOList = categoryService.searchAllCategoryByUserId(((UserDTO)session.getAttribute("loginUser")).getUserId());
		for (CategoryDTO categoryDTO : categoryDTOList) {
			int counts = blogService.searchBlogCountsByCategoryId(categoryDTO.getCategoryId());
			categoryDTO.setCounts(counts);
		}
		// 将categoryDTOList设置到application中
		application.setAttribute("myCategoryDTOList", categoryDTOList);
		
		//将blogDTOList设置到request中
		request.setAttribute("blogDTOList", blogDTOList);

		return "blog";
	}

	public String message() {
		
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		
		// 检查当前用户是否已经登录
		if (!LoginUtils.checkUserIsAlreadyLogin(session)) {
			request.setAttribute("messageInfo", "请登录.");
			return "signInPage";
		}
		
		//获取当前登录用户的所有信息
		//receiverId为当前登录用户userId的消息，messageStatus <> 1
		//List<MessageDTO> messageDTOList = messageService.searchAllMessageByReceiverId(loginUser.getUserId());
//		//获取新消息
//		//启用分页查询新消息
////		List<MessageDTO> messageDTOListWithNew = messageService.searchAllMessageByReceiverIdAndMessageStatus(loginUser.getUserId(), 1);
//		List<MessageDTO> messageDTOListWithNew = messageService.searchAllMessageByReceiverIdAndMessageStatus((HttpServletRequest)request, loginUser.getUserId(), 1);
//		//获取已读消息
//		//启用分页查询已读消息
//		if (messageDTOListWithNew.size() < Integer.parseInt((String)request.getAttribute("pageSize"))) {
//			request.setAttribute("pageSize", Integer.parseInt((String)request.getAttribute("pageSize"))-messageDTOListWithNew.size());
//			List<MessageDTO> messageDTOListWithRead = messageService.searchAllMessageByReceiverIdAndMessageStatus((HttpServletRequest)request, loginUser.getUserId(), 2);
//			request.setAttribute("messageDTOListWithRead", messageDTOListWithRead);
//		}
////		List<MessageDTO> messageDTOListWithRead = messageService.searchAllMessageByReceiverIdAndMessageStatus(loginUser.getUserId(), 2);
//		//设置到request中去
//		request.setAttribute("messageDTOListWithNew", messageDTOListWithNew);
//		//设置messages为新消息的个数
//		request.setAttribute("messages", messageService.searchAllMessageByReceiverIdAndMessageStatus(loginUser.getUserId(), 1));
//		
		
		//查询所有成功的消息，按照发送时间的降序排列   ！= 0  order by 时间 desc
		//启用分页查询
		List<MessageDTO> messageDTOList = messageService.searchAllMessageByReceiverId((HttpServletRequest)request, loginUser.getUserId());
		request.setAttribute("messageDTOList", messageDTOList);
		request.setAttribute("messages", messageService.searchAllMessageByReceiverIdAndMessageStatus(loginUser.getUserId(), 1).size());
		
		return "message";
	}
	
	public String sendMessageDisplayPage() {
		
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		
		// 检查当前用户是否已经登录
		if (!LoginUtils.checkUserIsAlreadyLogin(session)) {
			request.setAttribute("messageInfo", "请登录.");
			return "signInPage";
		}
		
		//查询所有成功的消息，按照发送时间的降序排列   ！= 0  order by 时间 desc
		//启用分页查询
		List<MessageDTO> messageDTOList = messageService.searchAllMessageBySenderId((HttpServletRequest)request, loginUser.getUserId());
		request.setAttribute("messageDTOList", messageDTOList);
		request.setAttribute("messages", messageService.searchAllMessageBySenderIdIdAndMessageStatus(loginUser.getUserId(), 1).size()
				+ messageService.searchAllMessageBySenderIdIdAndMessageStatus(loginUser.getUserId(), 2).size());
				
		
		return "sendMessageDisplayPage";
	}
	
	//私信页面
	public String messageAddPage() {
		// 检查当前用户是否已经登录
		if (!LoginUtils.checkUserIsAlreadyLogin(session)) {
			request.setAttribute("messageInfo", "请登录.");
			return "signInPage";
		}
		return "messageAddPage";
	}
	
	public String messageDetail() {
		
		MenuDTO menuDTO = super.getModel();
		
		// 检查当前用户是否已经登录
		if (!LoginUtils.checkUserIsAlreadyLogin(session)) {
			request.setAttribute("messageInfo", "请登录.");
			return "signInPage";
		}
		if (menuDTO==null || menuDTO.getMessageId()==null || "".equals(menuDTO.getMessageId())) {
			request.setAttribute("messageInfo", "获取消息详情出错");
			return "message";
		}
		
		//设置指定messageId的消息已读取
		messageService.updateMessageStatus(menuDTO.getMessageId(), 2);
		
		//获取指定messageId的MessageDTO对象
		MessageDTO messageDTO = messageService.searchMessageByMessageId(menuDTO.getMessageId());
		//设置到request中
		request.setAttribute("messageDTO", messageDTO);
		
		return "messageDetail";
	}

	public String changePasswordPage() {

		return "changePasswordPage";
	}

	// 个人中心
	// 添加上次登录时间
	public String personalPage() {

		// 判断用户是否已经登录
		boolean isLogin = LoginUtils.checkUserIsAlreadyLogin(session);
		if (!isLogin) {
			// 没有登录，跳转到signInPage.jsp
			return "signInPage";
		}
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		Date lastLoginDate = loginService.searchLastLoginDateByUserId(loginUser.getUserId());
		request.setAttribute("lastLoginDate", lastLoginDate);
		if (super.getModel().getKind()!=null && "edit".equals(super.getModel().getKind())) {
			//启用编辑
			request.setAttribute("kind", "");
		} else {
			//只读
			request.setAttribute("kind", "disabled");
		}
		return "personalPage";
	}

	/*
	 * 跳转到添加博客页面 需要判断是否登录，如果没有登录，则跳转到用户登录页面，让用户选择登录，如果已经登录，则直接跳转到博客添加页面
	 */
	public String blogAddPage() {
		// 判断用户是否已经登录
		boolean isLogin = LoginUtils.checkUserIsAlreadyLogin(session);

		if (!isLogin) {
			// 没有登录，跳转到signInPage.jsp
			return "signInPage";
		}

		return "blogAddPage";
	}

	public String blogEditPage() {

		// 获取列表页面传递的blogId
		String blogId = super.getModel().getBlogId();

		// 调用blogService查询指定blogId
		// 如果指定的blogId不存在，跳转到列表页面
		if (blogId == null || "".equals(blogId)) {
			request.setAttribute("messageInfo", "当前Blog不存在");
			return "toBlog";
		}
		BlogDTO blogDTO = blogService.searchBlogByBlogId(blogId);
		// 判断查询的blog是否存在
		if (blogDTO == null) {
			request.setAttribute("messageInfo", "当前Blog不存在");
			return "toBlog";
		}

		// 设置到request中"blogDTO" : blogDTO
		request.setAttribute("blogDTO", blogDTO);

		return "blogEditPage";
	}
	
	public String blogDetailPage() {
		
		//获取列表页面传递的blogId
		String blogId = super.getModel().getBlogId();
//		String blogId2 = request.getParameter("blogId");
		
		//调用blogService查询指定blogId
		//如果指定的blogId不存在，跳转到列表页面
		if (blogId==null || "".equals(blogId)) {
			request.setAttribute("messageInfo", "当前Blog不存在");
			return "toBlog";
		}
		BlogDTO blogDTO = blogService.searchBlogByBlogId(blogId);
		//判断查询的blog是否存在
		if (blogDTO == null) {
			request.setAttribute("messageInfo", "当前Blog不存在");
			return "toBlog";
		}
		
		//取出指定blogId的所有评论
		List<CommentDTO> commentDTOList = commentService.searchAllCommentByBlogId(blogId);
		//生成具有层次的评论
		List<List<CommentDTO>> commentDTOListWithLevel = new ArrayList<List<CommentDTO>>();
		for (int i=0; commentDTOList!=null && i<commentDTOList.size(); ++i) {
			CommentDTO commentDTO = commentDTOList.get(i);
			List<CommentDTO> eachList = new ArrayList<CommentDTO>();
			eachList.add(commentDTO);
			//判断其parentId是否为空，如果不为空，则继续加
			while (commentDTO!=null && commentDTO.getParentId()!=null && !"".equals(commentDTO.getParentId())) {
				commentDTO = commentService.searchCommentByCommentId(commentDTO.getParentId());
//				commentDTO = this.getCommentDTOByCommentId(commentDTOList, commentDTO.getParentId());
				eachList.add(0, commentDTO);
			}
			commentDTOListWithLevel.add(eachList);
		}
		
		//设置到request中"blogDTO" : blogDTO
		request.setAttribute("blogDTO", blogDTO);
		request.setAttribute("commentDTOListWithLevel", commentDTOListWithLevel);
		
		return "blogDetailPage";
	}
	
	@Deprecated
	private CommentDTO getCommentDTOByCommentId(List<CommentDTO> commentList, String parentId) {
		
		for (CommentDTO commentDTO : commentList) {
			if (commentDTO.getCommentId().equals(parentId)) {
				return commentDTO;
			}
		}
		
		return null;
	}

	public String uploadPhotoPage() {
		return "uploadPhotoPage";
	}
	
	public String changeAatarPage() {
		return "changeAatarPage";
	}
	
	public String deleted(){
		List<BlogDTO> blogDTODeletedList = blogService.searchAllDeletedBlogByUserId(((UserDTO)session.getAttribute("loginUser")).getUserId());
		
		//将blogDTOList设置到request中
		request.setAttribute("blogDTODeletedList", blogDTODeletedList);

		return "deleted";
	}
	
	public String categoryAddPage() {
		
		return "categoryAddPage";
	}
}
