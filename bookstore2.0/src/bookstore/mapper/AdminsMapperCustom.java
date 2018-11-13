package bookstore.mapper;

import java.util.List;

import bookstore.po.Admins;
import bookstore.po.Category;


public interface AdminsMapperCustom {
	
	public Admins findByAname(String aname)throws Exception;	
}
