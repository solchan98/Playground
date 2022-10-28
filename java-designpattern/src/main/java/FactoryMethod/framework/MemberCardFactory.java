package FactoryMethod.framework;

import java.util.*;

public class MemberCardFactory extends Factory<MemberCard> {

    private final Set<Integer> memberCardSet;

    private int serial;

    public MemberCardFactory() {
        memberCardSet = new HashSet<>();
        serial = 0;
    }

    @Override
    protected MemberCard createProduct(String owner) {
        return new MemberCard(serial++, owner);
    }

    @Override
    protected synchronized void registerProduct(MemberCard product) {
        memberCardSet.add(product.getSerial());
    }

    @Override
    public boolean contains(MemberCard target) {
        return this.memberCardSet.contains(target.getSerial());
    }
}
