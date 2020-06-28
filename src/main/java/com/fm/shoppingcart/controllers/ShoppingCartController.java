package com.fm.shoppingcart.controllers;

import com.fm.shoppingcart.model.Item;
import com.fm.shoppingcart.model.ShoppingCart;
import com.fm.shoppingcart.repository.ItemRepository;
import com.fm.shoppingcart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ShoppingCartController {

    private ShoppingCartRepository shoppingCartRepository;
    private ItemRepository itemRepository;

    @Autowired
    public ShoppingCartController(ItemRepository itemRepository, ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemRepository = itemRepository;
    }

    @CrossOrigin
    @GetMapping(value = "/health")
    public String health() {
        //update db

        return "Service is up & running. ";
    }

    @CrossOrigin
    @GetMapping(value = "/carts")
    private List<Long> allCarts() {
        List<Long> ids = new ArrayList<Long>();
        List<ShoppingCart> sc = shoppingCartRepository.findAll();
        for (ShoppingCart s : sc) {
            ids.add(s.getId());
        }
        return ids;
    }

    @CrossOrigin
    @GetMapping(value = "/carts/{cartId}")
    private ResponseEntity<ShoppingCart> cartById(@PathVariable Long cartId) {
        //get all the items with this cart id
        Optional<ShoppingCart> temp = this.shoppingCartRepository.findById(cartId);
        ShoppingCart sc = temp.get();
        List<Item> items = this.itemRepository.findAll();
        Set<Item> output = new HashSet<Item>();
        for (Item i : items) {
            if (i.getCart_id() == cartId) output.add(i);
        }
        sc.setItems(output);
        return
                temp.map(ResponseEntity.accepted()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PutMapping(value = "/carts/{cartId}")
    private ResponseEntity<ShoppingCart> updateShoppingCart(@RequestBody ShoppingCart cart, @PathVariable Long cartId) {
        return this.shoppingCartRepository
                .findById(cartId)
                .map(cartFound -> {
                    cartFound.mergeShoppingCart(cart);
                    return ResponseEntity
                            .accepted()
                            .body(this.shoppingCartRepository.save(cartFound));
                }).orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping(value = "/carts")
    private ResponseEntity<ShoppingCart> addShoppingCart(@RequestBody ShoppingCart cart) {
        return ResponseEntity
                .ok()
                .body(this.shoppingCartRepository.save(cart));
    }

    @CrossOrigin
    @DeleteMapping(value = "/carts/{cartId}")
    private ResponseEntity deleteShoppingCart(@PathVariable Long cartId) {
        return this.shoppingCartRepository
                .findById(cartId)
                .map(cartFound -> {
                    this.shoppingCartRepository.delete(cartFound);
                    return ResponseEntity
                            .accepted()
                            .build();
                }).orElse(ResponseEntity.notFound().build());
    }
}