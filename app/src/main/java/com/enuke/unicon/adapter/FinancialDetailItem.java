package com.enuke.unicon.adapter;

public class FinancialDetailItem {
    private String title, genre, image;

    public FinancialDetailItem() {
    }

    public FinancialDetailItem(String title, String genre, String img) {
        this.title = title;
        this.genre = genre;
        this.image = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
