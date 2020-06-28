package com.fm.shoppingcart.model;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//    @NotNull
//    @Size(min = 5, max = 100, message = "Title length should be between 5 and 100")
//    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    //@OneToMany(mappedBy="cart", fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private Set<Item> items;

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void mergeShoppingCart(ShoppingCart shoppingCart) {
        this.description = shoppingCart.getDescription();
        this.items = shoppingCart.getItems();
    }

    //private List<Item> items;
}