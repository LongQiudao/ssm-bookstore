package bookstore.po;

import java.math.BigDecimal;
import java.util.List;



public class OrderCustom extends Orders{
	
	private String iid;
	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}
	private  Orderitem orderitem;
	private Book book;
	private String bname;
	private String author;
	private String image;
	private BigDecimal price;
    private Integer count;
    private BigDecimal subtotal;
	public Orderitem getOrderitem() {
		return orderitem;
	}

	public void setOrderitem(Orderitem orderitem) {
		this.orderitem = orderitem;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	private TbUser user;
	private List<Orderitem> orderitemList;

	public List<Orderitem> getOrderitemList() {
		return orderitemList;
	}

	public void setOrderitemList(List<Orderitem> orderitemList) {
		this.orderitemList = orderitemList;
	}

	public TbUser getUser() {
		return user;
	}

	public void setUser(TbUser user) {
		this.user = user;
	}

	
}
