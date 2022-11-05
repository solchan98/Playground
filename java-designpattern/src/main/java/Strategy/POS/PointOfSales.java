package Strategy.POS;

import Strategy.POS.discount.*;

import java.util.ArrayList;
import java.util.List;

public class PointOfSales {

    private final Calculator calculator = new Calculator(new FirstGuestDiscountStrategy());
    private final List<Item> itemList = new ArrayList<>();
    private int totalPrice = 0;

    public void onFirstGuestButtonClick() {
        calculator.changeStrategy(new FirstGuestDiscountStrategy());
    }

    public void onNightGuestButtonClick() {
        calculator.changeStrategy(new NightGuestDiscountStrategy());
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public int getTotalPrice() {
        this.totalPrice = calculator.calculate(itemList);
        return this.totalPrice;
    }
}
