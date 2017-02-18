package io.pivotal.ezhang.entity;

/**
 * Created by ezhang on 2016/12/9.
 */


public class UserOrder {

    private Long id;
    private Long userid;
    private Long price;
    private Long productid;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return this.userid;
    }

    public void setUsername(Long userid) {
        this.userid = userid;
    }

    public Long getPrice() {
        return this.price;
    }

    public void setPrice(Long age) {
        this.price = price;
    }

    public Long getProductid() {
        return this.productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }
}
