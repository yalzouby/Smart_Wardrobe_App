package com.wardrobe;
public class Pants extends WardrobeItem {
    private String pantsLength;

    public Pants(String itemID, String color, String imagePath) {
        super(itemID, "pants", color, imagePath);
    }

    @Override
    public String getDetails() {
        return "Pants [Length: " + pantsLength + ", Color: " + color + "]";
    }
}
