package bookstore.po;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String,CartItem> map = new LinkedHashMap<String, CartItem>();
	public double getTotal(){
		double total = 0;
		for(CartItem cartItem : map.values()){
			BigDecimal b1 = new BigDecimal(total+"");
			BigDecimal b2 = cartItem.getSubtotal();
			 total = b1.add(b2).doubleValue();
			
		}
		return total;
	}
	/**
	 * 
	 * 添加条目到车中
	 * */
	public void add(CartItem cartItem){
			if(map.containsKey(cartItem.getBook().getBid())){
				CartItem item = map.get(cartItem.getBook().getBid());
				item.setCount(item.getCount()+cartItem.getCount());
				map.put(cartItem.getBook().getBid(), item);
			}else{
				map.put(cartItem.getBook().getBid(), cartItem);
			}
		
	}
	/**
	 * 
	 * 删除所有条目
	 * */
	public void clear(){
		map.clear();
	}
	/**
	 * 删除指定条目
	 * */
	public void delete(String bid){
		map.remove(bid);
	}
	/**
	 * 获取所有条目
	 * 
	 * */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
}
