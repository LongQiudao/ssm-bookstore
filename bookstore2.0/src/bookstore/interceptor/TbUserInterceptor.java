package bookstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import bookstore.po.TbUser;

public class TbUserInterceptor implements HandlerInterceptor {
	/**
	 * 统一的异常处理，统一的日志处理
	 * */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	
		
	}
	/**
	 * 将公用的模型数据在这里传到视图
	 * */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	
		
	}
	/**
	 * 身份认证和身份授权
	 * */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String url=request.getRequestURI();
		if(url.indexOf("login.action")>0 || url.indexOf("regist.action")>0 || url.indexOf("findAllCategory.action")>0){
			return true;
		}
		TbUser user=(TbUser) request.getSession().getAttribute("session_user");
		if(user!=null){
			return true;
		}else{
			request.setAttribute("msg", "游客没有访问权限，请登录后重试！");
			request.getRequestDispatcher("jsps/user/login.jsp").forward(request, response);
			return false;
		}
	}

}
