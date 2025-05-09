package com.wardrobe;
public class Outfit {
    private Shirt shirt;
    private Pants pants;
    private Shoes shoes;

    public Outfit(Shirt shirt, Pants pants, Shoes shoes) {
        this.shirt = shirt;
        this.pants = pants;
        this.shoes = shoes;
    }

    public Shirt getShirt() {
        return shirt;
    }

    public Pants getPants() {
        return pants;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public String getOutfitDetails() {
        return shirt.getDetails() + "\n" + pants.getDetails() + "\n" + shoes.getDetails();
    }

    public boolean matchesColor(String color) {        
        return shirt.getColor().equals(color) || pants.getColor().equals(color) || shoes.getColor().equals(color);
    }
}
