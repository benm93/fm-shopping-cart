package com.fm.shoppingcart.model;


import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    private Long cart_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String description;

    private Integer quantity = 0;

    private Double price = 0.0;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
//    public ShoppingCart getCart() {
//        return cart;
//    }
//
//    public void setCart(ShoppingCart cart) {
//        this.cart = cart;
//    }
//
//    @ManyToOne
//    @JoinColumn(name="cart_id", nullable=true)
//    private ShoppingCart cart;

    public void mergeItem(Item item) {
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
    }
}
