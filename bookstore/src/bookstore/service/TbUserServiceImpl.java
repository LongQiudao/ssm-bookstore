package bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;

import bookstore.controller.TbUserController;
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
	public TbUser findByCode(String code) throws Exception {
		
		return tbUserMapperCustom.findByCode(code);
	}
	public void updateState(String uid, boolean state) throws Exception {
		tbUserMapperCustom.updateState(uid,state);
		
	}
	
}
