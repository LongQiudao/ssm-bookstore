package bookstore.mapper;

import java.util.List;

import bookstore.po.Book;


public interface BookMapperCustom {
	//查询所有图书
	public List<Book> findAllBook()throws Exception;
	//按cid查询图书
	public List<Book> findByCategory(String cid)throws Exception;
}
