package ru.job4j.shop;

public class Shop extends Store {

    /**
     * Set instance of this in ControlQuality.
     */
    public Shop() {
        super();
        ControlQuality.getInstance().setShop(this);
    }

    /**
     * Checking and transfer for Shop.
     */
    @Override
    public void transfer() {
        lessThan25();
        lessFrom75To100();
        exactly100();
    }
}
