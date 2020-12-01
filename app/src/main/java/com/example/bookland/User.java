package com.example.bookland;

public class User {
    private String name;
    private String author;
    private String price;
    private String category;
    private String rating;
    private String description;
    private String image;

    public User() {
    }

    public User(String name,String author, String price, String category, String rating, String description, String image) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
        this.rating = rating;
        this.description = description;
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

