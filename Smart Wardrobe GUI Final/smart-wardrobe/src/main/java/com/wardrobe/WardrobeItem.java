package com.wardrobe;
public abstract class WardrobeItem {
    protected String itemID;
    protected String category; // shirt, pants, shoes
    protected String color;
    protected String imagePath;

    public WardrobeItem(String itemID, String category, String color, String imagePath) {
        this.itemID = itemID;
        this.category = category;
        this.color = color;
        this.imagePath = imagePath;
    }

    public String getCategory() {
        return category;
    }

    public String getColor() {
        return color;
    }

    public String getImagePath() {
        return imagePath;
    }

    public abstract String getDetails();
}
