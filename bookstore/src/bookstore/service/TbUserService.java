package bookstore.service;



import bookstore.po.TbUser;

public interface TbUserService {
	public TbUser findByUsername(String username) throws Exception;
	public void addTbUser(TbUser tbUser);
	public TbUser findByCode(String code)throws Exception;
	public void updateState(String uid,boolean state)throws Exception;
}
