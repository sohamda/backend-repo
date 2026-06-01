package com.sohamda.backendrepo.controller;

import com.sohamda.backendrepo.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final AtomicLong idCounter = new AtomicLong(2);
    private final List<Product> products = new CopyOnWriteArrayList<>(
            List.of(new Product(1L, "Laptop", "15-inch laptop", new BigDecimal("1200.00"), "Electronics", true))
    );

    @GetMapping
    public List<Product> getProducts() {
        return products;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        Product created = new Product(
                idCounter.getAndIncrement(),
                product.name(),
                product.description(),
                product.price(),
                product.category(),
                product.inStock()
        );
        products.add(created);
        return created;
    }
}
