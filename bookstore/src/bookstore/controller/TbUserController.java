package bookstore.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bookstore.po.TbUser;
import bookstore.service.TbUserService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

@Controller
public class TbUserController {
	@Autowired
	private TbUserService tbUserService;
	/**
	 * 激活功能
	 * */
	@RequestMapping("/active")
	public ModelAndView active(HttpServletRequest requset)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		String code	=requset.getParameter("code");
		TbUser user = tbUserService.findByCode(code);
		if(user==null){
			modelAndView.addObject("msg", "激活码无效");
			modelAndView.setViewName("/jsps/msg.jsp");
			return modelAndView;
		}
		if(user.getState()== false){
			modelAndView.addObject("msg", "改用户已被激活");
			modelAndView.setViewName("/jsps/msg.jsp");
			return modelAndView;
		}
			tbUserService.updateState(user.getUid(), true);
			modelAndView.addObject("msg", "恭喜注册成功，请马上登录");
			modelAndView.setViewName("/jsps/msg.jsp");
			return modelAndView;
	}
	/**
	 * 注册功能
	 * */
	@RequestMapping("/regist")
	public ModelAndView regist(HttpServletRequest requset,
			@Validated TbUser tbUSer, BindingResult bindingResult)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorslist = bindingResult.getAllErrors();
			for(ObjectError error: errorslist){
				System.out.println(error.getDefaultMessage());
			
			}
			modelAndView.addObject("errors", errorslist);
			modelAndView.setViewName("/jsps/user/regist.jsp");
			return modelAndView;
		}
		TbUser form = CommonUtils.toBean(requset.getParameterMap(),
				TbUser.class);
		form.setUid(CommonUtils.uuid());
		form.setCode(CommonUtils.uuid() + CommonUtils.uuid());
		form.setState(false);
		TbUser user = tbUserService.findByUsername(form.getUsername());
		if (user != null){
			modelAndView.addObject("msg","改用户名已被注册");
			modelAndView.setViewName("/jsps/user/regist.jsp");
			return modelAndView;
		}
		tbUserService.addTbUser(form);
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader()
				.getResourceAsStream("email_template.properties"));
		String host = props.getProperty("host");
		String uname= props.getProperty("uname");
		String pwd=props.getProperty("pwd");
		String from = props.getProperty("from");
		String to = form.getEmail();
		String subject =props.getProperty("subject");
		String content = props.getProperty("content");
		content = MessageFormat.format(content, form.getCode());//替换{0}
		Session session = MailUtils.createSession(host, uname, pwd);
		Mail mail = new Mail(from,to,subject,content);
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			
		}
		modelAndView.addObject("msg", "恭喜注册成功，请打开邮件激活");
		modelAndView.setViewName("/jsps/msg.jsp");
		return modelAndView;
	}
}
