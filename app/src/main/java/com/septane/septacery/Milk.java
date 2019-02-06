package com.septane.septacery;

public class Milk {

    //private String category;
    private String item;
    private String image;

    public Milk(//String category,
                String item, String image) {
        //this.category = category;
        this.item = item;
        this.image = image;
    }

    public String  getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Milk() {
    }

    /*public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }*/

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
