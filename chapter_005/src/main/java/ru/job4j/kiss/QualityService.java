package ru.job4j.kiss;

import ru.job4j.shop.Food;

import java.util.List;

public class QualityService {

    /**
     * Checks food from each store. If food doesn't accept by store - it's delete and put into
     * list of unsorted food to TransferService.
     * All food from declined foodlist checking for accepting by each list and destribute to stores.
     */
    public static boolean qualityCheck() {
        boolean checkStatus = true;
        TransferService ts = TransferService.getInstance();

        for (Store store : ts.getStores()) {
            List<Food> foodInStore = store.getFoodList();

            for (int i = 0; i < foodInStore.size(); i++) {
                Food food = foodInStore.get(i);
                if (store.accept(food)) {
                    store.add(food);
                } else {
                    ts.addUnsortedFood(food);
                    store.remove(food);
                    --i;
                    checkStatus = false;
                }
            }
        }
        return checkStatus;
    }
}
