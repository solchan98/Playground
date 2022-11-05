package Strategy;

import Strategy.POS.Item;
import Strategy.POS.PointOfSales;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class POSTest {

    @Test
    void pointOfSalesTest() {
        PointOfSales pos = new PointOfSales();

        pos.onFirstGuestButtonClick(); // 10% discount
        pos.addItem(new Item("사과", 9000, true));
        pos.addItem(new Item("치약", 3000, true));
        pos.addItem(new Item("양송이 버섯", 4800, false));

        int totalPriceWhenFirstGuest = pos.getTotalPrice();

        pos.onNightGuestButtonClick(); // 40% discount
        int totalPriceWhenNightGuest = pos.getTotalPrice();

        assertEquals(8100 + 2700 + 4800, totalPriceWhenFirstGuest);
        assertEquals(5400 + 1800 + 4800, totalPriceWhenNightGuest);
    }
}
