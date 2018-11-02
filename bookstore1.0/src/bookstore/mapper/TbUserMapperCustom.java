package bookstore.mapper;

import bookstore.po.TbUser;
<<<<<<< HEAD
import bookstore.po.TbUserCustom;

public interface TbUserMapperCustom {
	
		public TbUser findByUsername(String username)throws Exception;
		public TbUser findByCode(String code)throws Exception;
		public void updateState(String uid,boolean state)throws Exception;
=======


public interface TbUserMapperCustom {
		//按用户名查询用户
		public TbUser findByUsername(String username)throws Exception;
		//按code查询用户
		public TbUser findByCode(String code)throws Exception;
>>>>>>> 用户登录，图书查询，图书加载，购物车等功能的完成
}
