package io.pivotal.ezhang.domain;

/**
 * Created by ezhang on 2016/12/9.
 */

import javax.persistence.*;

@Entity
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Long userid;
    @Column
    private Long productid;
    @Column
    private Long price;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getProductid() {
        return this.productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public Long getPrice() {
        return this.price;
    }

    public void setPrice(Long age) {
        this.price = price;
    }
}
