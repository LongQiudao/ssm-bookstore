package bookstore.service;

import java.util.List;

import bookstore.po.Category;

public interface CategoryService {
	//查询所有分类
	public List<Category> findAllCategory()throws Exception;
}
