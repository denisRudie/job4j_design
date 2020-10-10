package ru.job4j.shop;

public class Warehouse extends Store {

    /**
     * Set instance of this in ControlQuality.
     */
    public Warehouse() {
        super();
        ControlQuality.getInstance().setWarehouse(this);
    }

    /**
     * Checking and transfer for Warehouse.
     */
    @Override
    public void transfer() {
        lessFrom25To75();
        lessFrom75To100();
        exactly100();
    }
}
