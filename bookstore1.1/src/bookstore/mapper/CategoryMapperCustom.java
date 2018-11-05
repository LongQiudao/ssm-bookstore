package bookstore.mapper;

import java.util.List;

import bookstore.po.Category;


public interface CategoryMapperCustom {
	//查询所有分类
	public List<Category> findAllCategory()throws Exception;	
}
