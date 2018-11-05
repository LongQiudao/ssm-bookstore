package bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bookstore.po.Category;
import	bookstore.service.CategoryService;

@Controller
public class CategoryContronller {
	@Autowired
	private  CategoryService CategoryService;
	/**
	 * 查询所有分类
	 * */
	@RequestMapping("/findAllCategory")
	public ModelAndView findAllCategory()throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		List<Category> categoryList=CategoryService.findAllCategory();
		modelAndView.addObject("categoryList", categoryList);
		modelAndView.setViewName("/jsps/left.jsp");
		return modelAndView;
	}
}
