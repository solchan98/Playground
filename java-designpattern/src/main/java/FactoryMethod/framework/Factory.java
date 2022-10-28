package FactoryMethod.framework;

public abstract class Factory<T> {

    public final T create(String owner) {
        T product = createProduct(owner);
        registerProduct(product);
        return product;
    }

    protected abstract T createProduct(String owner);
    protected abstract void registerProduct(T product);

    public abstract boolean contains(T product);
}
