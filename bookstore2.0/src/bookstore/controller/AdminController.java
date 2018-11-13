package bookstore.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.stat.TableStat.Mode;
import com.sun.org.apache.regexp.internal.recompile;
import com.sun.org.apache.xpath.internal.operations.Mod;

import bookstore.mapper.AdminsMapper;
import bookstore.mapper.BookMapper;
import bookstore.mapper.CategoryMapper;
import bookstore.mapper.TbUserMapper;
import bookstore.po.Admins;
import bookstore.po.AdminsExample;
import bookstore.po.Book;
import bookstore.po.Category;
import bookstore.po.OrderCustom;
import bookstore.po.OrderitemCustom;
import bookstore.po.Orders;
import bookstore.po.TbUser;
import bookstore.service.AdminsService;
import bookstore.service.BookService;
import bookstore.service.CategoryService;
import bookstore.service.OrderService;

import cn.itcast.commons.CommonUtils;

@Controller
public class AdminController {
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private BookService bookService;
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AdminsMapper adminsMapper;
	@Autowired
	private AdminsService adminService;
	@Autowired
	private OrderService orderService;
	/**
	 * 登录功能
	 * */
	@RequestMapping("adminlogin")
	public ModelAndView login(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView= new ModelAndView();
		Admins form=CommonUtils.toBean(request.getParameterMap(),Admins.class);
		Admins admins=adminService.findByAname(form.getAdminname());
	
		if(admins==null){
			modelAndView.addObject("msg", "你不是管理员");
			modelAndView.setViewName("/adminjsps/msg.jsp");
			return modelAndView;
		}
		if(!form.getPassword().equals(admins.getPassword())){
			modelAndView.addObject("msg", "密码错误");
			modelAndView.setViewName("/adminjsps/msg.jsp");
			return modelAndView;
	    }
		request.getSession().setAttribute("admin",admins);
		modelAndView.setViewName("/adminjsps/admin/main.jsp");
		return modelAndView;
	}
	/**
	 * 添加分类
	 * */
	@RequestMapping("/addCategory")
	public ModelAndView addCategory(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView= new ModelAndView();
		Category category =CommonUtils.toBean(request.getParameterMap(), Category.class);
		category.setCid(CommonUtils.uuid());
		categoryMapper.insert(category);
		List<Category> categoryList=categoryService.findAllCategory();
		modelAndView.addObject("categoryList", categoryList);
		modelAndView.setViewName("/jsps/left.jsp");
		return modelAndView;
	}
	/**
	 * 查询所有分类
	 * */
	@RequestMapping("/findCategory")
	public ModelAndView findCategory(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		List<Category> categoryList=categoryService.findAllCategory();
		modelAndView.addObject("categoryList", categoryList);
		modelAndView.setViewName("/adminjsps/admin/category/list.jsp");
		return modelAndView;
		
	}
	/**
	 * 修改分类前的加载工作
	 * */
	@RequestMapping("editPre")
	public ModelAndView editPre(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView= new ModelAndView();
		String cid = request.getParameter("cid");
		Category category=categoryMapper.selectByPrimaryKey(cid);
		modelAndView.addObject("category", category);
		modelAndView.setViewName("/adminjsps/admin/category/mod.jsp");
		return modelAndView;
	
	
	}
	/**
	 * 修改分类
	 * */
	@RequestMapping("edit")
	public ModelAndView edit(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		Category category=CommonUtils.toBean(request.getParameterMap(), Category.class);
		categoryMapper.updateByPrimaryKey(category);
		List<Category> categoryList=categoryService.findAllCategory();
		modelAndView.addObject("categoryList", categoryList);
		modelAndView.setViewName("/jsps/left.jsp");
		return modelAndView;
	}
	/**
	 * 删除分类
	 * */
	@RequestMapping("delete")
	public ModelAndView delete(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView= new ModelAndView();
		String cid = request.getParameter("cid");
		categoryMapper.deleteByPrimaryKey(cid);
		List<Category> categoryList=categoryService.findAllCategory();
		modelAndView.addObject("categoryList", categoryList);
		modelAndView.setViewName("/adminjsps/admin/category/list.jsp");
		return modelAndView;
	}
	/**
	 * 查询所有图书
	 * */
	@RequestMapping("findBook")
	public ModelAndView findBook(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView= new ModelAndView();
		List<Book> bookList=bookService.findAllBook();
		modelAndView.addObject("bookList", bookList);
		modelAndView.setViewName("adminjsps/admin/book/list.jsp");
		return modelAndView;
	}
	/**
	 * 加载图书
	 * 
	 * */
	@RequestMapping("loadBook")
	public ModelAndView loadBook(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView= new ModelAndView();
		String bid = request.getParameter("bid");
		Book book = bookMapper.selectByPrimaryKey(bid);
		List<Category>categoryList=categoryService.findAllCategory();
		modelAndView.addObject("book", book);
		modelAndView.addObject("categoryList", categoryList);
		modelAndView.setViewName("/adminjsps/admin/book/desc.jsp");
		return modelAndView;
	}
	/**
	 * 删除图书
	 * */
	@RequestMapping("deleteBook")
	public ModelAndView deleteBook(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView= new ModelAndView();
		String bid = request.getParameter("bid");
		Book book =bookMapper.selectByPrimaryKey(bid);
		book.setDel(true);
		List<Book> bookList=bookService.findAllBook();
		modelAndView.addObject("bookList", bookList);
		modelAndView.setViewName("adminjsps/admin/book/list.jsp");
		return modelAndView;
	}
	/**
	 * 编辑图书
	 * */
	@RequestMapping("editBook")
	public ModelAndView editBook(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView= new ModelAndView();
		Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		book.setCid(category.getCid());
		book.setDel(false);
		bookMapper.updateByPrimaryKey(book);
		List<Book> bookList=bookService.findAllBook();
		modelAndView.addObject("bookList", bookList);
		modelAndView.setViewName("adminjsps/admin/book/list.jsp");
		return modelAndView; 
	}
	/**
	 * 加载添加图书页面
	 * */
	@RequestMapping("loadAddBook")
	public ModelAndView loadAddBook(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		List<Category>categoryList=categoryService.findAllCategory();
		modelAndView.addObject("categoryList", categoryList);
		modelAndView.setViewName("/adminjsps/admin/book/add.jsp");
		return modelAndView;
		
	}
	/**
	 * 添加图书
	 * */
	@RequestMapping("addBook")
	public ModelAndView addBook(HttpServletRequest request,MultipartFile book_img )throws Exception{
		ModelAndView modelAndView= new ModelAndView();
		Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
		if(book_img !=null){
			String pic_path="E:\\tomcat\\apache-tomcat-7.0.88\\webapps\\bookstore\\book_img\\";
			String originalFilename =book_img.getOriginalFilename();
			String newFileName=CommonUtils.uuid()+originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile=new File(pic_path+newFileName);
			book.setImage("book_img/" + newFileName);
			book.setBid(CommonUtils.uuid());
			bookMapper.insert(book);
			book.setDel(false);
			book_img.transferTo(newFile);
		}
		List<Book> bookList=bookService.findAllBook();
		modelAndView.addObject("bookList", bookList);
		modelAndView.setViewName("adminjsps/admin/book/list.jsp");
		return modelAndView;
	}
	/**
	 * 查询所有订单
	 * */
	@RequestMapping("findAllOrder")
	public ModelAndView findAllOrder(HttpServletRequest request)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		List<Orders> orderList=orderService.findAll();
		List<OrderCustom> orderCustomsList = new ArrayList<OrderCustom>();
		for (Orders order : orderList) {
			List<OrderitemCustom > orderitemCustomList = orderService
					.findOrderItemByOid(order.getOid());
			OrderCustom orderCustom = new OrderCustom();
			orderCustom.setOrdercCustomList(orderitemCustomList);
			orderCustom.setOid(order.getOid());
			orderCustom.setState(order.getState());
			orderCustom.setOrdertime(order.getOrdertime());
			orderCustom.setTotal(order.getTotal());
			for(OrderitemCustom orderitem: orderitemCustomList){
				Book book =bookMapper.selectByPrimaryKey(orderitem.getBid());
				orderitem.setBook(book);
			}
			orderCustomsList.add(orderCustom);
		}
		modelAndView.addObject("orderList", orderCustomsList);
		modelAndView.setViewName("/adminjsps/admin/order/list.jsp");
		return modelAndView;
	}
	/**
	 * 查询未付款订单
	 * */
	@RequestMapping("findUnpaid")
	public ModelAndView findUnpaid()throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		List<Orders> orderList=orderService.findUnpaid();
		List<OrderCustom> orderCustomsList = new ArrayList<OrderCustom>();
		for (Orders order : orderList) {
			List<OrderitemCustom > orderitemCustomList = orderService
					.findOrderItemByOid(order.getOid());
			OrderCustom orderCustom = new OrderCustom();
			orderCustom.setOrdercCustomList(orderitemCustomList);
			orderCustom.setOid(order.getOid());
			orderCustom.setState(order.getState());
			orderCustom.setOrdertime(order.getOrdertime());
			orderCustom.setTotal(order.getTotal());
			for(OrderitemCustom orderitem: orderitemCustomList){
				Book book =bookMapper.selectByPrimaryKey(orderitem.getBid());
				orderitem.setBook(book);
			}
			orderCustomsList.add(orderCustom);
		}
		modelAndView.addObject("orderList", orderCustomsList);
		modelAndView.setViewName("/adminjsps/admin/order/list.jsp");
		return modelAndView;
	}
	/**
	 * 查询已付款订单 
	 *
	 * */
	@RequestMapping("findPaid")
	public ModelAndView findPaid()throws Exception{
		ModelAndView modelAndView= new ModelAndView();
		List<Orders> orderList=orderService.findPaid();
		List<OrderCustom> orderCustomsList = new ArrayList<OrderCustom>();
		for (Orders order : orderList) {
			List<OrderitemCustom > orderitemCustomList = orderService
					.findOrderItemByOid(order.getOid());
			OrderCustom orderCustom = new OrderCustom();
			orderCustom.setOrdercCustomList(orderitemCustomList);
			orderCustom.setOid(order.getOid());
			orderCustom.setState(order.getState());
			orderCustom.setOrdertime(order.getOrdertime());
			orderCustom.setTotal(order.getTotal());
			for(OrderitemCustom orderitem: orderitemCustomList){
				Book book =bookMapper.selectByPrimaryKey(orderitem.getBid());
				orderitem.setBook(book);
			}
			orderCustomsList.add(orderCustom);
		}
		modelAndView.addObject("orderList", orderCustomsList);
		modelAndView.setViewName("/adminjsps/admin/order/list.jsp");
		return modelAndView;
	}
	/**
	 * 查询未收货订单
	 * 
	 * */
	@RequestMapping("findUnreceive")
	public ModelAndView findUnreceive()throws Exception{
		ModelAndView modelAndView= new ModelAndView();
		List<Orders> orderList=orderService.findUnreceive();
		List<OrderCustom> orderCustomsList = new ArrayList<OrderCustom>();
		for (Orders order : orderList) {
			List<OrderitemCustom > orderitemCustomList = orderService
					.findOrderItemByOid(order.getOid());
			OrderCustom orderCustom = new OrderCustom();
			orderCustom.setOrdercCustomList(orderitemCustomList);
			orderCustom.setOid(order.getOid());
			orderCustom.setState(order.getState());
			orderCustom.setOrdertime(order.getOrdertime());
			orderCustom.setTotal(order.getTotal());
			for(OrderitemCustom orderitem: orderitemCustomList){
				Book book =bookMapper.selectByPrimaryKey(orderitem.getBid());
				orderitem.setBook(book);
			}
			orderCustomsList.add(orderCustom);
		}
		modelAndView.addObject("orderList", orderCustomsList);
		modelAndView.setViewName("/adminjsps/admin/order/list.jsp");
		return modelAndView;
	}
	/**
	 * 查询交易成功
	 * */
	@RequestMapping("findSuccess")
	public ModelAndView findSuccess()throws Exception{
		ModelAndView modelAndView= new ModelAndView();
		List<Orders> orderList=orderService.findSuccess();
		List<OrderCustom> orderCustomsList = new ArrayList<OrderCustom>();
		for (Orders order : orderList) {
			List<OrderitemCustom > orderitemCustomList = orderService
					.findOrderItemByOid(order.getOid());
			OrderCustom orderCustom = new OrderCustom();
			orderCustom.setOrdercCustomList(orderitemCustomList);
			orderCustom.setOid(order.getOid());
			orderCustom.setState(order.getState());
			orderCustom.setOrdertime(order.getOrdertime());
			orderCustom.setTotal(order.getTotal());
			for(OrderitemCustom orderitem: orderitemCustomList){
				Book book =bookMapper.selectByPrimaryKey(orderitem.getBid());
				orderitem.setBook(book);
			}
			orderCustomsList.add(orderCustom);
		}
		modelAndView.addObject("orderList", orderCustomsList);
		modelAndView.setViewName("/adminjsps/admin/order/list.jsp");
		return modelAndView;
	}
}
