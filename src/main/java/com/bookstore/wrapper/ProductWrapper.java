package com.bookstore.wrapper;

import lombok.Data;

@Data
public class ProductWrapper {

    Integer id;

    String title;

    String author;

    String description;

    Integer price;

    String status;

    Integer categoryId;

    String categoryName;

    public ProductWrapper(){}

    public ProductWrapper(Integer id, String title, String author, String description, Integer price, String status, Integer categoryId, String categoryName) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public ProductWrapper(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public ProductWrapper(Integer id, String title, String author, String description, Integer price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
    }
}
