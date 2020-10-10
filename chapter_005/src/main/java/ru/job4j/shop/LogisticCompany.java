package ru.job4j.shop;

import java.util.ArrayList;
import java.util.List;

/**
 * Food transfer.
 * Singleton.
 */
public class LogisticCompany {
    private static LogisticCompany instance;

    /**
     * List of food which have to moving to Trash.
     */
    private final List<Food> toTrash;

    /**
     * List of food which have to moving to Shop.
     */
    private final List<Food> toShop;

    /**
     * List of food which have to moving to Warehouse.
     */
    private final List<Food> toWarehouse;

    private LogisticCompany() {
        this.toTrash = new ArrayList<>();
        this.toWarehouse = new ArrayList<>();
        this.toShop = new ArrayList<>();
    }

    /**
     * Singleton instance getter.
     *
     * @return instance of class.
     */
    public static LogisticCompany getInstance() {
        if (instance == null) {
            instance = new LogisticCompany();
        }
        return instance;
    }

    /**
     * Add food to Trash transfer list.
     *
     * @param product Food object.
     */
    public void addToTrash(Food product) {
        toTrash.add(product);
    }

    /**
     * Add food to Shop transfer list.
     *
     * @param product Food object.
     */
    public void addToShop(Food product) {
        toShop.add(product);
    }

    /**
     * Add food to Warehouse transfer list.
     *
     * @param product Food object.
     */
    public void addToWarehouse(Food product) {
        toWarehouse.add(product);
    }

    /**
     * Moving food from transfer lists to matching Store's foodlists.
     */
    public void moving() {
        toShop.forEach(ControlQuality.getInstance().getShop()::add);
        toWarehouse.forEach(ControlQuality.getInstance().getWarehouse()::add);
        toTrash.forEach(ControlQuality.getInstance().getTrash()::add);
    }
}
