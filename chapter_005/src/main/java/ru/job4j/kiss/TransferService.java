package ru.job4j.kiss;

import ru.job4j.shop.Food;

import java.util.ArrayList;
import java.util.List;

public class TransferService {

    private static TransferService instance;
    private final List<Food> unsortedFood;
    private final List<Store> stores;

    private TransferService() {
        this.unsortedFood = new ArrayList<>();
        this.stores = new ArrayList<>();
    }

    public static TransferService getInstance() {
        if (instance == null) {
            instance = new TransferService();
        }
        return instance;
    }

    public void addUnsortedFood(Food food) {
        unsortedFood.add(food);
    }

    public void addStore(Store store) {
        stores.add(store);
    }

    public List<Store> getStores() {
        return stores;
    }

    /**
     * All food from declined foodlist checking for accepting by each list and move to
     * stores.
     */
    public void transferUnsortedFood() {
        for (Store store : stores) {
            for (Food food : unsortedFood) {
                if (store.accept(food)) {
                    store.add(food);
                }
            }
        }
    }
}
