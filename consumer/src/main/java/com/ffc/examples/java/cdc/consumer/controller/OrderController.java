package com.ffc.examples.java.cdc.consumer.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffc.examples.java.cdc.provider.controller.Product;

class Order {
    private String id;
    private Product product;
    private double price;

    public Order(String id, Product product, double price) {
        this.id = id;
        this.product = product;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

@RestController
public class OrderController {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OrderController(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrder(@PathVariable String id) throws JsonMappingException, JsonProcessingException {
        String productResponse = restTemplate.getForObject("http://127.0.0.1:8080/product/" + id, String.class);
        Product product = objectMapper.readValue(productResponse, new TypeReference<Product>() {});

        return new Order(id, product, 100.0);
    }
}
