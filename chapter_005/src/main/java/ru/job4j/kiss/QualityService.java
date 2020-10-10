package ru.job4j.kiss;

import ru.job4j.shop.Food;

import java.util.ArrayList;
import java.util.List;

public class QualityService {
    private static QualityService instance;
    private final List<Store> stores;

    private QualityService() {
        stores = new ArrayList<>();
    }

    public static QualityService getInstance() {
        if (instance == null) {
            instance = new QualityService();
        }
        return instance;
    }

    public void addStore(Store store) {
        stores.add(store);
    }

    /**
     * Checks food from each store. If food doesn't accept by store - it's delete and put into
     * list of declined food.
     * All food from declined foodlist checking for accepting by each list and destribute to stores.
     */
    public void qualityCheck() {
        List<Food> removedFood = new ArrayList<>();

        for (Store store : stores) {
            List<Food> foodInStore = store.getFoodList();

            for (int i = 0; i < foodInStore.size(); i++) {
                Food food = foodInStore.get(i);
                if (store.accept(food)) {
                    store.add(food);
                } else {
                    removedFood.add(food);
                    store.remove(food);
                    --i;
                }
            }
        }

        for (Store store : stores) {

            for (Food delFood : removedFood) {
                if (store.accept(delFood)) {
                    store.add(delFood);
                }
            }
        }
    }
}
