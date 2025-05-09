package com.wardrobe;
import java.util.*;

public class User {
    private String userID;
    private String name;
    private List<WardrobeItem> wardrobe;
    private List<Outfit> genOutfit;
    private List<Outfit> savedOutfits;

    private List<Shirt> shirts = new ArrayList<>();
    private List<Pants> pants = new ArrayList<>();
    private List<Shoes> shoes = new ArrayList<>();


    public User(String userID, String name) {
        this.userID = userID;
        this.name = name;
        this.wardrobe = new ArrayList<>();
        this.genOutfit = new ArrayList<>();
        this.savedOutfits = new ArrayList<>();
    }

    public void uploadItem(WardrobeItem item) {
        wardrobe.add(item);
        if (item instanceof Shirt) {
            shirts.add((Shirt) item);
        } else if (item instanceof Pants) {
            pants.add((Pants) item);
        } else if (item instanceof Shoes) {
            shoes.add((Shoes) item);
        }
    }
//
    public List<Shirt> getShirts() { return shirts; }
    public List<Pants> getPants() { return pants; }
    public List<Shoes> getShoes() { return shoes; }
//


    public void saveOutfit(Outfit genOutfit) {
        savedOutfits.add(genOutfit);
    }

    public List<Outfit> getSavedOutfits() {
        return savedOutfits;
    }

    public List<WardrobeItem> getWardrobe() {
        return wardrobe;
    }

    public boolean hasFullWardrobe() {
        boolean hasShirt = wardrobe.stream().anyMatch(i -> i instanceof Shirt);
        boolean hasPants = wardrobe.stream().anyMatch(i -> i instanceof Pants);
        boolean hasShoes = wardrobe.stream().anyMatch(i -> i instanceof Shoes);
        return hasShirt && hasPants && hasShoes;
    }
    
}
