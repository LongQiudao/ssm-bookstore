package bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;

import bookstore.mapper.AdminsMapperCustom;
import bookstore.po.Admins;

public class AdminsServiceImpl implements AdminsService{
	@Autowired
	private AdminsMapperCustom adminsMapperCustom;
	public Admins findByAname(String aname) throws Exception {
		
		return adminsMapperCustom.findByAname(aname);
	}

}
