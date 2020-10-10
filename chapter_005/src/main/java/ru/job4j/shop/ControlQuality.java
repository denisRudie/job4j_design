package ru.job4j.shop;

/**
 * Check expiration date of food.
 * Singleton.
 */
public class ControlQuality {

    private static ControlQuality instance;

    /**
     * Shop instance.
     */
    private Store shop;

    /**
     * Warehouse instance.
     */
    private Store warehouse;

    /**
     * Trash instance.
     */
    private Store trash;

    private ControlQuality() {
    }

    /**
     * Singleton instance getter.
     *
     * @return instance of class.
     */
    public static ControlQuality getInstance() {
        if (instance == null) {
            instance = new ControlQuality();
        }
        return instance;
    }

    public Store getShop() {
        return shop;
    }

    public void setShop(Store shop) {
        this.shop = shop;
    }

    public Store getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Store warehouse) {
        this.warehouse = warehouse;
    }

    public Store getTrash() {
        return trash;
    }

    public void setTrash(Store trash) {
        this.trash = trash;
    }

    /**
     * Checks stores for existing food which have to transfer to another store and transfer with
     * LogisticCompany.
     */
    public void checkStores() {
        shop.transfer();
        warehouse.transfer();
        trash.transfer();
        LogisticCompany.getInstance().moving();
    }
}
