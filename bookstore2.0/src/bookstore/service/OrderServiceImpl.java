package bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bookstore.mapper.OrdersMapperCustom;
import bookstore.po.OrderCustom;
import bookstore.po.Orderitem;
import bookstore.po.OrderitemCustom;
import bookstore.po.Orders;

public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrdersMapperCustom orderMapperCustom;
	public List<Orders> findByUid(String uid) throws Exception {
		
		return orderMapperCustom.findByUid(uid);
	}
	public List<OrderitemCustom> findOrderItemByOid(String oid) throws Exception {
		
		return orderMapperCustom.findOrderItemByOid(oid);
	}
	public List<OrderCustom> findOrderCustom(String oid) throws Exception {
		
		return orderMapperCustom.findOrderCustom(oid);
	}
	public List<Orders> findAll() throws Exception {
		
		return orderMapperCustom.findAll();
	}
	public List<Orders> findUnpaid() throws Exception {
		
		return orderMapperCustom.findUnpaid();
	}
	public List<Orders> findPaid() throws Exception {
		
		return orderMapperCustom.findPaid();
	}
	public List<Orders> findUnreceive() throws Exception {
		
		return orderMapperCustom.findUnreceive();
	}
	public List<Orders> findSuccess() throws Exception {
	
		return orderMapperCustom.findSuccess();
	}
	
}
