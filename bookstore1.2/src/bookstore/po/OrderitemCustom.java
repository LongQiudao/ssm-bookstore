package bookstore.po;

import java.math.BigDecimal;

public class OrderitemCustom extends Orderitem{
	private Book book;
	private BigDecimal Subtotal;
	private OrderCustom order;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public BigDecimal getSubtotal() {
		return Subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		Subtotal = subtotal;
	}
	public OrderCustom getOrder() {
		return order;
	}
	public void setOrder(OrderCustom order) {
		this.order = order;
	}
	
}
