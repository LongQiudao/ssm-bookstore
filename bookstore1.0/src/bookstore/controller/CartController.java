package bookstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.mail.handlers.message_rfc822;



import bookstore.mapper.BookMapper;
import bookstore.po.Book;
import bookstore.po.Cart;
import bookstore.po.CartItem;

@Controller
public class CartController {
	@Autowired
	private BookMapper bookMapper;
	/**
	 * 向购物车中添加商品
	 * */
	@RequestMapping("/addCart")
	public ModelAndView addCart(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		String bid = request.getParameter("bid");
		Book book = bookMapper.selectByPrimaryKey(bid);
		int count = Integer.parseInt(request.getParameter("count"));
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setBook(book);
		cart.add(cartItem);
		modelAndView.setViewName("/jsps/cart/list.jsp");
		return modelAndView;
	}
	/**
	 * 删除购物车中的指定商品
	 * */
	@RequestMapping("/deleteCartItem")
	public ModelAndView deleteCartItem(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		Cart cart = (Cart) request.getSession().getAttribute("cart");	
		String bid = request.getParameter("bid");
		cart.delete(bid);
		modelAndView.setViewName("/jsps/cart/list.jsp");
		return modelAndView;
	}
	/**
	 * 删除购物车中的所有商品
	 * */
	@RequestMapping("/deleteAll")
	public ModelAndView deleteAll(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clear();
		modelAndView.setViewName("/jsps/cart/list.jsp");
		return modelAndView;
	}
}
