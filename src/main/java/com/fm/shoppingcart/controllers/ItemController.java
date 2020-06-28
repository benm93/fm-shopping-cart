package com.fm.shoppingcart.controllers;

import com.fm.shoppingcart.model.Item;
import com.fm.shoppingcart.model.ShoppingCart;
import com.fm.shoppingcart.repository.ItemRepository;
import com.fm.shoppingcart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

    private ItemRepository itemRepository;
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository, ShoppingCartRepository shoppingCartRepository) {
        this.itemRepository = itemRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @CrossOrigin
    @GetMapping(value = "/item_health")
    public String health() {
        //update db

        return "Service is up & running. ";
    }

    @CrossOrigin
    @GetMapping(value = "/items/{itemId}")
    private ResponseEntity<Item> itemById(@PathVariable Long itemId) {
        return this.itemRepository
                .findById(itemId)
                .map(ResponseEntity.accepted()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PutMapping(value = "/items/{itemId}")
    private ResponseEntity<Item> updateItem(@RequestBody Item item, @PathVariable Long itemId) {
        return this.itemRepository
                .findById(itemId)
                .map(itemFound -> {
                    itemFound.mergeItem(item);
                    return ResponseEntity
                            .accepted()
                            .body(this.itemRepository.save(itemFound));
                }).orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping(value = "/items")
    private ResponseEntity<Item> addItem(@RequestBody Item item) {
        return ResponseEntity
                .ok()
                .body(this.itemRepository.save(item));
    }

    @CrossOrigin
    @DeleteMapping(value = "/items/{itemId}")
    private ResponseEntity deleteItem(@PathVariable Long itemId) {
        return this.itemRepository
                .findById(itemId)
                .map(itemFound -> {
                    this.itemRepository.delete(itemFound);
                    return ResponseEntity
                            .accepted()
                            .build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
