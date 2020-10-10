package ru.job4j.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract class for stores of food.
 * Implements interface for checking expired date of food.
 */
public abstract class Store implements QualityCheck {

    /**
     * List of food.
     */
    private final List<Food> foodList;

    protected Store() {
        this.foodList = new ArrayList<>();
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void add(Food product) {
        foodList.add(product);
    }

    public void remove(Food product) {
        foodList.remove(product);
    }

    /**
     * Checks that current foodlist contains food with expiring index less than 25%.
     * All matches add to LogisticCompany's transfer list (to Warehouse) and remove from current
     * foodlist.
     */
    @Override
    public void lessThan25() {
        List<Food> candidatesToTransfer = getFoodList().stream()
                .filter(f -> f.getExpiredPercentage() < 25)
                .collect(Collectors.toList());

        candidatesToTransfer.forEach(f -> {
            LogisticCompany.getInstance().addToWarehouse(f);
            remove(f);
        });
    }

    /**
     * Checks that current foodlist contains food with expiring index between 25% and 75%.
     * All matches add to LogisticCompany's transfer list (to Shop) and remove from current
     * foodlist.
     */
    @Override
    public void lessFrom25To75() {
        List<Food> candidatesToTransfer = getFoodList().stream()
                .filter(f -> f.getExpiredPercentage() >= 25 && f.getExpiredPercentage() < 75)
                .collect(Collectors.toList());

        candidatesToTransfer.forEach(f -> {
            LogisticCompany.getInstance().addToShop(f);
            remove(f);
        });
    }

    /**
     * Checks that current foodlist contains food with expiring index between 75% and 100%.
     * All matches add to LogisticCompany's transfer list (to Shop with discount) and remove from
     * current foodlist.
     */
    @Override
    public void lessFrom75To100() {
        List<Food> candidatesToTransfer = getFoodList().stream()
                .filter(f -> f.getExpiredPercentage() >= 75 && f.getExpiredPercentage() < 100)
                .collect(Collectors.toList());

        candidatesToTransfer.forEach(f -> {
            f.setDiscount(50);
            LogisticCompany.getInstance().addToShop(f);
            remove(f);
        });
    }

    /**
     * Checks that current foodlist contains food with expiring index equals 100%.
     * All matches add to LogisticCompany's transfer list (to Trash) and remove from
     * current foodlist.
     */
    @Override
    public void exactly100() {
        List<Food> candidatesToTransfer =  getFoodList().stream()
                .filter(f -> f.getExpiredPercentage() == 100)
                .collect(Collectors.toList());

        candidatesToTransfer.forEach(f -> {
            f.setDiscount(50);
            LogisticCompany.getInstance().addToTrash(f);
            remove(f);
        });
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "foodList=" + foodList +
                '}';
    }
}
