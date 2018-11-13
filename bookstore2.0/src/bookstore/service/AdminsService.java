package bookstore.service;

import bookstore.po.Admins;

public interface AdminsService {
	public Admins findByAname(String aname)throws Exception;
}
