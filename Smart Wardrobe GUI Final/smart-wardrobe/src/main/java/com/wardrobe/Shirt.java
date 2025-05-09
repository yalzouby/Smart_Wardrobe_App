package com.wardrobe;
public class Shirt extends WardrobeItem {
    private String sleeveLength;

    public Shirt(String itemID, String color, String imagePath) {
        super(itemID, "shirt", color, imagePath);
    }

    @Override
    public String getDetails() {
        return "Shirt [Sleeve: " + sleeveLength + ", Color: " + color + "]";
    }
}
