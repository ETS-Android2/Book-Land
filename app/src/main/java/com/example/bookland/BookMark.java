package com.example.bookland;

public class BookMark {
    private String nameMark;
    private String authorMark;
    private String priceMark;
    private String imageUrlMark;
    private String ratingMark;
    private String descriptionMark;
    private String categoryMark;

    public BookMark() {
    }

    public BookMark(String nameMark, String authorMark, String priceMark, String imageUrlMark, String ratingMark, String descriptionMark, String categoryMark) {
        this.nameMark = nameMark;
        this.authorMark = authorMark;
        this.priceMark = priceMark;
        this.imageUrlMark = imageUrlMark;
        this.ratingMark = ratingMark;
        this.descriptionMark = descriptionMark;
        this.categoryMark = categoryMark;
    }

    public String getNameMark() {
        return nameMark;
    }

    public void setNameMark(String nameMark) {
        this.nameMark = nameMark;
    }

    public String getAuthorMark() {
        return authorMark;
    }

    public void setAuthorMark(String authorMark) {
        this.authorMark = authorMark;
    }

    public String getPriceMark() {
        return priceMark;
    }

    public void setPriceMark(String priceMark) {
        this.priceMark = priceMark;
    }

    public String getImageUrlMark() {
        return imageUrlMark;
    }

    public void setImageUrlMark(String imageUrlMark) {
        this.imageUrlMark = imageUrlMark;
    }

    public String getRatingMark() {
        return ratingMark;
    }

    public void setRatingMark(String ratingMark) {
        this.ratingMark = ratingMark;
    }

    public String getDescriptionMark() {
        return descriptionMark;
    }

    public void setDescriptionMark(String descriptionMark) {
        this.descriptionMark = descriptionMark;
    }

    public String getCategoryMark() {
        return categoryMark;
    }

    public void setCategoryMark(String categoryMark) {
        this.categoryMark = categoryMark;
    }
}