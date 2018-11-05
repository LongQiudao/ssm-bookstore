package bookstore.service;

import java.util.List;

import bookstore.po.OrderCustom;
import bookstore.po.Orderitem;
import bookstore.po.Orders;

public interface OrderService {
	public List<Orders> findByUid(String uid)throws Exception;
	public List<OrderCustom>findOrderCustom(String oid)throws Exception;
	public List<Orderitem>findOrderItemByOid(String oid)throws Exception;
}
