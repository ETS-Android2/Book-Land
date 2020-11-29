package com.example.bookland;

public class BookCard {
    private String nameCard;
    private String authorCard;
    private String priceCard;
    private String imageUrlCard;
    private String ratingCard;
    private String descriptionCard;
    private String categoryCard;

    public BookCard() {
    }

    public BookCard(String nameCard, String authorCard, String priceCard, String imageUrlCard, String ratingCard, String descriptionCard, String categoryCard) {
        this.nameCard = nameCard;
        this.authorCard = authorCard;
        this.priceCard = priceCard;
        this.imageUrlCard = imageUrlCard;
        this.ratingCard = ratingCard;
        this.descriptionCard = descriptionCard;
        this.categoryCard = categoryCard;
    }

    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public String getAuthorCard() {
        return authorCard;
    }

    public void setAuthorCard(String authorCard) {
        this.authorCard = authorCard;
    }

    public String getPriceCard() {
        return priceCard;
    }

    public void setPriceCard(String priceCard) {
        this.priceCard = priceCard;
    }

    public String getImageUrlCard() {
        return imageUrlCard;
    }

    public void setImageUrlCard(String imageUrlCard) {
        this.imageUrlCard = imageUrlCard;
    }

    public String getRatingCard() {
        return ratingCard;
    }

    public void setRatingCard(String ratingCard) {
        this.ratingCard = ratingCard;
    }

    public String getDescriptionCard() {
        return descriptionCard;
    }

    public void setDescriptionCard(String descriptionCard) {
        this.descriptionCard = descriptionCard;
    }

    public String getCategoryCard() {
        return categoryCard;
    }

    public void setCategoryCard(String categoryCard) {
        this.categoryCard = categoryCard;
    }
}
