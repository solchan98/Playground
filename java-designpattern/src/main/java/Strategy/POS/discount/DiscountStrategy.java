package Strategy.POS.discount;

import Strategy.POS.Item;

public interface DiscountStrategy {

    int getDiscountPrice(Item item);
}
