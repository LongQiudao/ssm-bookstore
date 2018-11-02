package bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bookstore.mapper.CategoryMapperCustom;
import bookstore.po.Category;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapperCustom categoryMapperCustom;
	//查询所有分类
	public List<Category> findAllCategory() throws Exception {
		return categoryMapperCustom.findAllCategory();	
	}

}
