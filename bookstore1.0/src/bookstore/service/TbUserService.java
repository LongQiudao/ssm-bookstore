package bookstore.service;



import bookstore.po.TbUser;

public interface TbUserService {
<<<<<<< HEAD
	public TbUser findByUsername(String username) throws Exception;
	public void addTbUser(TbUser tbUser);
	public TbUser findByCode(String code)throws Exception;
	public void updateState(String uid,boolean state)throws Exception;
=======
	//通过用户名查询用户
	public TbUser findByUsername(String username) throws Exception;
	//添加用户
	public void addTbUser(TbUser tbUser);
	//通过激活码查询用户
	public TbUser findByCode(String code)throws Exception;
>>>>>>> 用户登录，图书查询，图书加载，购物车等功能的完成
}
