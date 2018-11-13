package bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;


import bookstore.mapper.TbUserMapper;
import bookstore.mapper.TbUserMapperCustom;
import bookstore.po.TbUser;


public class TbUserServiceImpl implements TbUserService{
	
	@Autowired
	private TbUserMapper tbUserMapper;
	@Autowired
	private TbUserMapperCustom tbUserMapperCustom;
	//按用户名查询
	public TbUser findByUsername(String username) throws Exception{
		return tbUserMapperCustom.findByUsername(username);
	}
	//添加用户
	public void addTbUser(TbUser tbUser){
		tbUserMapper.insert(tbUser);
	}
	//按激活码查询用户
	public TbUser findByCode(String code) throws Exception {
		
		return tbUserMapperCustom.findByCode(code);
	}
	
	
}
