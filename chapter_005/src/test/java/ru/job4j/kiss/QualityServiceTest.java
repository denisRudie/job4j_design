package ru.job4j.kiss;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.shop.Food;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class QualityServiceTest {

    private Food candidateToWarehouse;
    private Food candidateToShopAndDiscount;
    private Food candidateToShop;
    private Food candidateToTrash;

    @Before
    public void prepareData() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.set(2020, Calendar.OCTOBER, 2);
        cal2.set(2020, Calendar.DECEMBER, 1);
        candidateToWarehouse = new Food("candidateToWarehouse",
                cal2.getTime(),
                cal1.getTime(),
                100.55D);

        cal1.set(2020, Calendar.OCTOBER, 3);
        cal2.set(2020, Calendar.OCTOBER, 12);
        candidateToShopAndDiscount = new Food("candidateToShopAndDiscount",
                cal2.getTime(),
                cal1.getTime(),
                100.55D);

        cal1.set(2020, Calendar.OCTOBER, 5);
        cal2.set(2020, Calendar.OCTOBER, 15);
        candidateToShop = new Food("candidateToShop",
                cal2.getTime(),
                cal1.getTime(),
                100.55D);

        cal1.set(2020, Calendar.OCTOBER, 7);
        cal2.set(2020, Calendar.OCTOBER, 11);
        candidateToTrash = new Food("candidateToTrash",
                cal2.getTime(),
                cal1.getTime(),
                100.55D);
    }

    @Test
    public void test() {
        Store shop = new Shop();
        shop.add(candidateToTrash);

        Store warehouse = new Warehouse();
        warehouse.add(candidateToShopAndDiscount);
        warehouse.add(candidateToShop);

        Store trash = new Trash();
        trash.add(candidateToWarehouse);

        QualityService qs = QualityService.getInstance();
        qs.qualityCheck();

        assertThat(shop.getFoodList().size(), is(2));
        assertTrue(shop.getFoodList().contains(candidateToShop));
        assertTrue(shop.getFoodList().contains(candidateToShopAndDiscount));

        assertThat(warehouse.getFoodList().size(), is(1));
        assertTrue(warehouse.getFoodList().contains(candidateToWarehouse));

        assertThat(trash.getFoodList().size(), is(1));
        assertTrue(trash.getFoodList().contains(candidateToTrash));
    }
}