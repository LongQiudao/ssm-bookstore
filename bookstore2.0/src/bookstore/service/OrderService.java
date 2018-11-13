package bookstore.service;

import java.util.List;

import bookstore.po.OrderCustom;
import bookstore.po.Orderitem;
import bookstore.po.OrderitemCustom;
import bookstore.po.Orders;

public interface OrderService {
	public List<Orders> findByUid(String uid)throws Exception;
	public List<OrderCustom>findOrderCustom(String oid)throws Exception;
	public List<OrderitemCustom>findOrderItemByOid(String oid)throws Exception;
	public List<Orders> findAll()throws Exception;
	public List<Orders> findUnpaid()throws Exception;
	  public List<Orders>findPaid()throws Exception;
	   public List<Orders>findUnreceive()throws Exception;
	   public List<Orders>findSuccess()throws Exception;
}
