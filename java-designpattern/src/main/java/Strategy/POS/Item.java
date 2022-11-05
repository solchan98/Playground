package Strategy.POS;

public class Item {
    private final String name;
    private final int price;
    private final boolean discountable;

    public Item(String name, int price, boolean discountable) {
        this.name = name;
        this.price = price;
        this.discountable = discountable;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isDiscountable() {
        return discountable;
    }
}
