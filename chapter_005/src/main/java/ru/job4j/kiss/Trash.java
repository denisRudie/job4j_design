package ru.job4j.kiss;

import ru.job4j.shop.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private final List<Food> foodList;

    public Trash() {
        this.foodList = new ArrayList<>();
        TransferService.getInstance().addStore(this);
    }

    @Override
    public boolean accept(Food food) {
        return food.getExpiredPercentage() == 100;
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
