package bookstore.service;



import bookstore.po.TbUser;

public interface TbUserService {
	//通过用户名查询用户
	public TbUser findByUsername(String username) throws Exception;
	//添加用户
	public void addTbUser(TbUser tbUser);
	//通过激活码查询用户
	public TbUser findByCode(String code)throws Exception;
}
