package com.fooddelivery.onlinefooddelivery.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fooddelivery.onlinefooddelivery.model.Food;
import com.fooddelivery.onlinefooddelivery.model.Restaurant;
import com.fooddelivery.onlinefooddelivery.repository.FoodRepository;
import com.fooddelivery.onlinefooddelivery.repository.RestaurantRepository;

@Component
public class FoodDataInitializer implements CommandLineRunner {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public FoodDataInitializer(
            FoodRepository foodRepository,
            RestaurantRepository restaurantRepository) {

        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void run(String... args) {

        System.out.println();
        System.out.println("======================================================");
        System.out.println(" FOOD EXPRESS: BIRYANI HOUSE MENU UPDATE");
        System.out.println("======================================================");

        

        Restaurant restaurant =
                restaurantRepository
                        .findAll()
                        .stream()
                        .filter(r -> {

                            if (r == null
                                    || r.getName() == null) {

                                return false;
                            }

                            String name =
                                    normalizeName(
                                            r.getName()
                                    );

                            
                            return name.equals(
                                            "biryani house"
                                    )
                                    || name.equals(
                                            "biryani house premium"
                                    );
                        })
                        .findFirst()
                        .orElse(null);

        if (restaurant == null) {

            System.out.println(
                    "ERROR: Biryani House restaurant was not found."
            );

            System.out.println(
                    "No restaurant was created."
            );

            System.out.println(
                    "No restaurant was modified."
            );

            System.out.println(
                    "No food was deleted."
            );

            System.out.println(
                    "======================================================"
            );

            return;
        }

        String restaurantId =
                restaurant.getId();

        System.out.println(
                "Restaurant found: "
                        + restaurant.getName()
        );

        System.out.println(
                "Restaurant ID: "
                        + restaurantId
        );

        
        List<Food> existingFoods =
                foodRepository.findByRestaurantId(
                        restaurantId
                );

        if (existingFoods == null) {

            existingFoods =
                    new ArrayList<>();
        }

        System.out.println();

        System.out.println(
                "Existing dishes: "
                        + existingFoods.size()
        );

        
        Set<String> dishesToDelete =
                new HashSet<>();

        dishesToDelete.add(
                normalizeName(
                        "Mutton Mughlai"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Mutton Keema"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Mutton Do Pyaza"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Mutton Handi"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Mutton Curry"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Mutton Tikka"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Mutton Keema Pulao"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Mutton Shami Kebab"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Mutton Mandi"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Mutton Lucknowi Biryani"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Mutton Hyderabadi Biryani"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Mutton Handi Biryani"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Chicken Handi"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Butter Chicken"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Chicken Shashlik"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Chicken Seekh Kebab"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Chicken Handi Biryani"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Chicken Lucknowi Biryani"
                )
        );

        dishesToDelete.add(
                normalizeName(
                        "Chicken Dum Biryani"
                )
        );

        
        List<Food> foodsToDelete =
                new ArrayList<>();

        for (Food food : existingFoods) {

            if (food == null) {
                continue;
            }

            if (food.getName() == null) {
                continue;
            }

            String foodName =
                    normalizeName(
                            food.getName()
                    );

            if (dishesToDelete.contains(
                    foodName
            )) {

                foodsToDelete.add(
                        food
                );
            }
        }

        
        if (!foodsToDelete.isEmpty()) {

            foodRepository.deleteAll(
                    foodsToDelete
            );

            System.out.println();
            System.out.println(
                    "Deleted dishes:"
            );

            for (Food food :
                    foodsToDelete) {

                System.out.println(
                        "DELETE -> "
                                + food.getName()
                );
            }

        } else {

            System.out.println();
            System.out.println(
                    "No requested dishes were found."
            );
        }

        Set<String> deletedNames =
                new HashSet<>();

        for (Food food :
                foodsToDelete) {

            if (food != null
                    && food.getName() != null) {

                deletedNames.add(
                        normalizeName(
                                food.getName()
                        )
                );
            }
        }

        System.out.println();
        System.out.println(
                "Requested dishes not found:"
        );

        for (String dish :
                dishesToDelete) {

            if (!deletedNames.contains(
                    dish
            )) {

                System.out.println(
                        "NOT FOUND -> "
                                + dish
                );
            }
        }

        
        List<Food> finalFoods =
                foodRepository.findByRestaurantId(
                        restaurantId
                );

        if (finalFoods == null) {

            finalFoods =
                    new ArrayList<>();
        }

        System.out.println();
        System.out.println("======================================================");
        System.out.println(" BIRYANI HOUSE MENU UPDATE COMPLETE");
        System.out.println("======================================================");

        System.out.println(
                "Restaurant       : "
                        + restaurant.getName()
        );

        System.out.println(
                "Deleted records  : "
                        + foodsToDelete.size()
        );

        System.out.println(
                "Final dish count : "
                        + finalFoods.size()
        );

        System.out.println(
                "Other dishes     : NOT CHANGED"
        );

        System.out.println(
                "Restaurant data  : NOT CHANGED"
        );

        System.out.println(
                "Image URLs       : NOT CHANGED"
        );

        System.out.println(
                "Images moved     : NO"
        );

        System.out.println(
                "Images swapped   : NO"
        );

        System.out.println(
                "Other restaurants: NOT TOUCHED"
        );

        System.out.println("======================================================");
    }

    private String normalizeName(
            String name) {

        if (name == null) {
            return "";
        }

        return name
                .trim()
                .replaceAll(
                        "\\s+",
                        " "
                )
                .toLowerCase(
                        Locale.ROOT
                );
    }
}