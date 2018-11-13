package bookstore.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bookstore.mapper.BookMapperCustom;
import bookstore.po.Book;

public class BookServiceImpl implements BookService{
	@Autowired
	private BookMapperCustom bookMapperCustom;
	//查询所有图书
	public List<Book> findAllBook() throws Exception {	 	
		return bookMapperCustom.findAllBook();
	}
	//通过cid查询图书
	public List<Book> findByCategory(String cid) throws Exception {
		
		return bookMapperCustom.findByCategory(cid);
	}

}
