package bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;

<<<<<<< HEAD
import bookstore.controller.TbUserController;
=======

>>>>>>> 用户登录，图书查询，图书加载，购物车等功能的完成
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
<<<<<<< HEAD
=======
	//按激活码查询用户
>>>>>>> 用户登录，图书查询，图书加载，购物车等功能的完成
	public TbUser findByCode(String code) throws Exception {
		
		return tbUserMapperCustom.findByCode(code);
	}
<<<<<<< HEAD
	public void updateState(String uid, boolean state) throws Exception {
		tbUserMapperCustom.updateState(uid,state);
		
	}
=======
	
>>>>>>> 用户登录，图书查询，图书加载，购物车等功能的完成
	
}
