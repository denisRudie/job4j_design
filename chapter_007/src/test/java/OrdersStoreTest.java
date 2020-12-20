import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import ru.job4j.hibernate.OrdersStore;
import ru.job4j.model.Order;

import static org.junit.Assert.assertEquals;

public class OrdersStoreTest {

    private BasicDataSource pool = new BasicDataSource();

    /**
     * Testing OrderStore add() and findById() methods.
     */
    @Test
    public void whenAddOrderThanGetById() {
        OrdersStore store = OrdersStore.instOf();
        Order order = Order.of("name1", "desc1");
        int orderId = store.add(order);
        order.setId(orderId);
        assertEquals(order, store.findById(orderId));
    }

    /**
     * Testing OrderStore findAll method.
     */
    @Test
    public void whenAddThreeOrdersThanGetAll() {
        OrdersStore store = OrdersStore.instOf();

        store.add(Order.of("name1", "desc1"));
        store.add(Order.of("name2", "desc2"));
        store.add(Order.of("name3", "desc3"));

        assertEquals(3, store.findAll().size());
    }

    /**
     * Testing OrderStore update() method.
     */
    @Test
    public void whenAddOrderThanUpdateThanGet() {
        OrdersStore store = OrdersStore.instOf();

        Order order = Order.of("name1", "desc1");
        int orderId = store.add(order);
        order.setId(orderId);

        order.setName("name2");
        store.update(order);

        assertEquals(order, store.findById(orderId));
    }
}
