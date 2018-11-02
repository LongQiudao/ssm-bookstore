package bookstore.po;

import java.math.BigDecimal;



public class CartItem {
	/**
	 * 购物车条目类
	 * */
	private Book book;
	private int count ;
	/**
	 * 小计方法处理了二进制运算的误差问题
	 * */
	public double getSubtotal(){
		BigDecimal b1 = new BigDecimal(book.getPrice()+"");
		BigDecimal b2 = new BigDecimal(count+"");
		BigDecimal b3 =b1.multiply(b2);
		return b3.doubleValue();
	} 
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
	
	
}
