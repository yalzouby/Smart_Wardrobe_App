package com.wardrobe;
public class Shoes extends WardrobeItem {
    private String shoeType;

    public Shoes(String itemID, String color, String imagePath) {
        super(itemID, "shoes", color, imagePath);
    }

    @Override
    public String getDetails() {
        return "Shoes [Type: " + shoeType + ", Color: " + color + "]";
    }
}
