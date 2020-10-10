package ru.job4j.shop;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LogisticCompanyTest {
    private Food candidateToWarehouse;
    private Food candidateToShopAndDiscount;
    private Food candidateToShop;
    private Food candidateToTrash;

    /**
     * Add 4 types of food whose are candidates for different stores.
     */
    @Before
    public void prepareData() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.set(2020, Calendar.OCTOBER, 1);
        cal2.set(2020, Calendar.NOVEMBER, 30);
        candidateToWarehouse = new Food("potato",
                cal2.getTime(),
                cal1.getTime(),
                100.55D);

        cal1.set(2020, Calendar.OCTOBER, 2);
        cal2.set(2020, Calendar.OCTOBER, 11);
        candidateToShopAndDiscount = new Food("tomato",
                cal2.getTime(),
                cal1.getTime(),
                100.55D);

        cal1.set(2020, Calendar.OCTOBER, 4);
        cal2.set(2020, Calendar.OCTOBER, 16);
        candidateToShop = new Food("snickers",
                cal2.getTime(),
                cal1.getTime(),
                100.55D);

        cal1.set(2020, Calendar.OCTOBER, 6);
        cal2.set(2020, Calendar.OCTOBER, 10);
        candidateToTrash = new Food("juice",
                cal2.getTime(),
                cal1.getTime(),
                100.55D);
    }

    /**
     * Testing that products were transfer between stores correctly.
     */
    @Test
    public void test() {

        Store shop = new Shop();
        shop.add(candidateToTrash);

        Store warehouse = new Warehouse();
        warehouse.add(candidateToShopAndDiscount);
        warehouse.add(candidateToShop);

        Store trash = new Trash();
        trash.add(candidateToWarehouse);

        ControlQuality qualityService = ControlQuality.getInstance();
        qualityService.checkStores();

        assertThat(shop.getFoodList().size(), is(2));
        assertTrue(shop.getFoodList().contains(candidateToShop));
        assertTrue(shop.getFoodList().contains(candidateToShopAndDiscount));

        assertThat(warehouse.getFoodList().size(), is(1));
        assertTrue(warehouse.getFoodList().contains(candidateToWarehouse));

        assertThat(trash.getFoodList().size(), is(1));
        assertTrue(trash.getFoodList().contains(candidateToTrash));
    }
}