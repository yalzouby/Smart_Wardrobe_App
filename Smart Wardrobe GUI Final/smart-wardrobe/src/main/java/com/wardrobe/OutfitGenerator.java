package com.wardrobe;
import java.util.*;
import java.util.stream.Collectors;

public class OutfitGenerator {
    public static Outfit generateRandomOutfit(List<WardrobeItem> wardrobe) {
        List<Shirt> shirts = new ArrayList<>();
        List<Pants> pants = new ArrayList<>();
        List<Shoes> shoes = new ArrayList<>();

        for (WardrobeItem item : wardrobe) {
            if (item instanceof Shirt) shirts.add((Shirt) item);
            else if (item instanceof Pants) pants.add((Pants) item);
            else if (item instanceof Shoes) shoes.add((Shoes) item);
        }

        System.out.println("Shirts available: " + shirts.size());
        System.out.println("Pants available: " + pants.size());
        System.out.println("Shoes available: " + shoes.size());

        if (shirts.isEmpty() || pants.isEmpty() || shoes.isEmpty()) return null;

        Random rand = new Random();
        return new Outfit(
            shirts.get(rand.nextInt(shirts.size())),
            pants.get(rand.nextInt(pants.size())),
            shoes.get(rand.nextInt(shoes.size()))
        );
    }

    public static Outfit generateRandomOutfitWithColor(List<WardrobeItem> wardrobe, String color) {
        Random rand = new Random();
        
        List<Shirt> filteredShirts = wardrobe.stream()
            .filter(item -> item instanceof Shirt && ((Shirt) item).getColor().equalsIgnoreCase(color))
            .map(item -> (Shirt) item)
            .collect(Collectors.toList());
    
        List<Pants> filteredPants = wardrobe.stream()
            .filter(item -> item instanceof Pants && ((Pants) item).getColor().equalsIgnoreCase(color))
            .map(item -> (Pants) item)
            .collect(Collectors.toList());
    
        List<Shoes> filteredShoes = wardrobe.stream()
            .filter(item -> item instanceof Shoes && ((Shoes) item).getColor().equalsIgnoreCase(color))
            .map(item -> (Shoes) item)
            .collect(Collectors.toList());
        
        // If ANY of the lists are empty, outfit cannot be formed
        if (filteredShirts.isEmpty() && filteredPants.isEmpty() && filteredShoes.isEmpty()) {
            return null;
        }
       
        // Fallback to random if no match for that category
        Shirt shirt = !filteredShirts.isEmpty() ? filteredShirts.get(rand.nextInt(filteredShirts.size())) 
        : getRandomShirt(wardrobe);

        Pants pants = !filteredPants.isEmpty() ? filteredPants.get(rand.nextInt(filteredPants.size())) 
        : getRandomPants(wardrobe);

        Shoes shoes = !filteredShoes.isEmpty() ? filteredShoes.get(rand.nextInt(filteredShoes.size())) 
        : getRandomShoes(wardrobe);

            return new Outfit(shirt, pants, shoes);
        }
    
    // Helper methods to get random items from the wardrobe
    private static Shirt getRandomShirt(List<WardrobeItem> wardrobe) {
        List<Shirt> shirts = wardrobe.stream()
            .filter(item -> item instanceof Shirt)
            .map(item -> (Shirt) item)
            .collect(Collectors.toList());
        return shirts.isEmpty() ? null : shirts.get(new Random().nextInt(shirts.size()));
    }

    private static Pants getRandomPants(List<WardrobeItem> wardrobe) {
        List<Pants> pants = wardrobe.stream()
            .filter(item -> item instanceof Pants)
            .map(item -> (Pants) item)
            .collect(Collectors.toList());
        return pants.isEmpty() ? null : pants.get(new Random().nextInt(pants.size()));
    }

    private static Shoes getRandomShoes(List<WardrobeItem> wardrobe) {
        List<Shoes> shoes = wardrobe.stream()
            .filter(item -> item instanceof Shoes)
            .map(item -> (Shoes) item)
            .collect(Collectors.toList());
        return shoes.isEmpty() ? null : shoes.get(new Random().nextInt(shoes.size()));
    }

}
