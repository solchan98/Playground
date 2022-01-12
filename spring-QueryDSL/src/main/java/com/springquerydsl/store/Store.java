package com.springquerydsl.store;

import com.springquerydsl.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long score;

    @OneToMany(mappedBy = "store")
    private final List<Item> itemList = new ArrayList<>();

    public Store (String name, Long score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '}';
    }
}
