package io.pivotal.ezhang.domain;

/**
 * Created by ezhang on 2016/12/9.
 */

public class Product {

    private Long id;
    private String productname;
    private String color;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductname() {
        return this.productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
