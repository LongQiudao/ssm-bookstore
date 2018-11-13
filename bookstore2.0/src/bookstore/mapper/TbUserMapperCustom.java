package bookstore.mapper;

import bookstore.po.TbUser;


public interface TbUserMapperCustom {
		//按用户名查询用户
		public TbUser findByUsername(String username)throws Exception;
		//按code查询用户
		public TbUser findByCode(String code)throws Exception;
}
