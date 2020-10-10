package ru.job4j.kiss;

import ru.job4j.shop.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private final List<Food> foodList;

    public Shop() {
        foodList = new ArrayList<>();
        QualityService.getInstance().addStore(this);
    }

    @Override
    public boolean accept(Food food) {
        return food.getExpiredPercentage() >= 25 && food.getExpiredPercentage() < 100;
    }

    /**
     * Checking if food required discount -> check is food already exist in foodlist, if true ->
     * check current discount, if hasn't discount -> set discount.
     *
     * @param food for checking.
     */
    @Override
    public void add(Food food) {
        if (food.getExpiredPercentage() >= 75) {
            if (foodList.contains(food)) {
                Food existFood = foodList.get(foodList.indexOf(food));
                if (existFood.getDiscount() == 0) {
                    existFood.setDiscount(50);
                }
            } else {
                if (food.getDiscount() == 0) {
                    food.setDiscount(50);
                    foodList.add(food);
                }
            }
        } else {
            if (!foodList.contains(food)) {
                foodList.add(food);
            }
        }
    }

    @Override
    public void remove(Food food) {
        foodList.remove(food);
    }

    @Override
    public List<Food> getFoodList() {
        return foodList;
    }
}
