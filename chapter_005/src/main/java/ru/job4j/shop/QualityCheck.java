package ru.job4j.shop;

/**
 * Interface with methods for checking food expiration date.
 */
public interface QualityCheck {

    /**
     * Method for transfer food to another Store.
     */
    void transfer();

    void lessThan25();

    void lessFrom25To75();

    void lessFrom75To100();

    void exactly100();
}
