package bookstore.mapper;

import bookstore.po.OrderCustom;
import bookstore.po.Orderitem;
import bookstore.po.OrderitemCustom;
import bookstore.po.Orders;
import bookstore.po.OrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdersMapperCustom {
   public List<Orders> findByUid(String uid)throws Exception; 
   public List<OrderitemCustom> findOrderItemByOid(String oid)throws Exception;
   public List<OrderCustom>findOrderCustom(String oid)throws Exception;
   public List<Orders>findAll()throws Exception;
   public List<Orders>findUnpaid()throws Exception;
   public List<Orders>findPaid()throws Exception;
   public List<Orders>findUnreceive()throws Exception;
   public List<Orders>findSuccess()throws Exception;
}