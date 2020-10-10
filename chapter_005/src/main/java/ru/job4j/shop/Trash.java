package ru.job4j.shop;

public class Trash extends Store {

    /**
     * Set instance of this in ControlQuality.
     */
    public Trash() {
        super();
        ControlQuality.getInstance().setTrash(this);
    }

    /**
     * Checking and transfer for Trash.
     */
    @Override
    public void transfer() {
        lessThan25();
        lessFrom25To75();
        lessFrom75To100();
    }
}
