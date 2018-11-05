package bookstore.service;


import java.util.List;

import bookstore.po.Book;

public interface BookService {
	//查询所有图书
	public List<Book> findAllBook()throws Exception;
	//通过cid查询图书
	public List<Book> findByCategory(String cid)throws Exception;
}
