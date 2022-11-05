package Strategy.POS.discount;

import Strategy.POS.Item;

public class NightGuestDiscountStrategy implements DiscountStrategy {

    private final static double discountRate = 0.4;

    @Override
    public int getDiscountPrice(Item item) {
        if (!item.isDiscountable()) return item.getPrice();
        return (int) (item.getPrice() -  item.getPrice() * discountRate);
    }
}
