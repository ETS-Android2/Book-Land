package com.example.bookland;

public class Book {
    private String name;
    private String author;
    private String price;
    private String imageUrl;
    private String rating;
    private String description;

    public Book() {
    }

    public Book(String name, String author, String price, String imageUrl, String rating, String description) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
