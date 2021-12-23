package com.springquerydsl.item;

import com.springquerydsl.store.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private Long quantity;

    @ManyToOne
    private Store store;

    public Item(String name, Long price, Long quantity, Store store) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        setThisStore(store);
    }

    private void setThisStore(Store store) {
        this.store = store;
        store.getItemList().add(this);
    }
}
