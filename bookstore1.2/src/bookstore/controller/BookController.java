package bookstore.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import bookstore.mapper.BookMapper;

import bookstore.po.Book;
import bookstore.service.BookService;
@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private BookMapper bookMapper;
	/**
	 * 查询所有图书
	 * */
	@RequestMapping("/findAllBook")
	public ModelAndView findAllBook()throws Exception{
		ModelAndView modelAndView= new ModelAndView();
		List<Book> bookList=bookService.findAllBook();
		modelAndView.addObject("bookList", bookList);
		modelAndView.setViewName("/jsps/book/list.jsp");
		return modelAndView;
	}
	/**
	 * 按cid查询图书
	 * */
	@RequestMapping("/findByCategory")
	public ModelAndView findByCategory(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView =  new ModelAndView();
		String cid = request.getParameter("cid");
		List<Book> bookList= bookService.findByCategory(cid);
		modelAndView.addObject("bookList", bookList);
		modelAndView.setViewName("/jsps/book/list.jsp");
		return modelAndView;
	}
	/**
	 * 通过bid查询图书
	 * */
	@RequestMapping("/findBookByBid")
	public ModelAndView findBookByBid(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		String bid= request.getParameter("bid");
		Book book = bookMapper.selectByPrimaryKey(bid);
		modelAndView.addObject("book",book);
		modelAndView.setViewName("/jsps/book/desc.jsp");
		return modelAndView;
	}
}
