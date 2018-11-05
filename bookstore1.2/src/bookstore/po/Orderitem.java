package bookstore.po;

import java.math.BigDecimal;

public class Orderitem {
	private String iid;

    private Integer count;

    private BigDecimal subtotal;

    private String oid;

    private String bid;

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid == null ? null : iid.trim();
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

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }
}