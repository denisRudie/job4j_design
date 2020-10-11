package ru.job4j.kiss;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.shop.Food;

import java.time.LocalDate;

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
        LocalDate create1 = LocalDate.now().minusDays(9);
        LocalDate expire1 = LocalDate.now().plusMonths(2);
        candidateToWarehouse = new Food("candidateToWarehouse",
                expire1,
                create1,
                100.55D);

        LocalDate create2 = LocalDate.now().minusDays(8);
        LocalDate expire2 = LocalDate.now().plusDays(1);
        candidateToShopAndDiscount = new Food("candidateToShopAndDiscount",
                expire2,
                create2,
                100.55D);

        LocalDate create3 = LocalDate.now().minusDays(6);
        LocalDate expire3 = LocalDate.now().plusDays(6);
        candidateToShop = new Food("candidateToShop",
                expire3,
                create3,
                100.55D);

        LocalDate create4 = LocalDate.now().minusDays(4);
        LocalDate expire4 = LocalDate.now();
        candidateToTrash = new Food("candidateToTrash",
                expire4,
                create4,
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