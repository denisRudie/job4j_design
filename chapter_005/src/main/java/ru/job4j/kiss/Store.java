package ru.job4j.kiss;

import ru.job4j.shop.Food;

import java.util.List;

public interface Store {

    boolean accept(Food food);

    void add(Food food);

    void remove(Food food);

    List<Food> getFoodList();
}
