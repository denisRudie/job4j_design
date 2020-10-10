package ru.job4j.kiss;

import ru.job4j.shop.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private final List<Food> foodList;

    public Warehouse() {
        this.foodList = new ArrayList<>();
        QualityService.getInstance().addStore(this);
    }

    @Override
    public boolean accept(Food food) {
        return food.getExpiredPercentage() < 25;
    }

    @Override
    public void add(Food food) {
        if (!foodList.contains(food)) {
            foodList.add(food);
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
