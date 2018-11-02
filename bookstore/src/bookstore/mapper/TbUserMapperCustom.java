package bookstore.mapper;

import bookstore.po.TbUser;
import bookstore.po.TbUserCustom;

public interface TbUserMapperCustom {
	
		public TbUser findByUsername(String username)throws Exception;
		public TbUser findByCode(String code)throws Exception;
		public void updateState(String uid,boolean state)throws Exception;
}
