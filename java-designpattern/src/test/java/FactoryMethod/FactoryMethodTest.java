package FactoryMethod;

import FactoryMethod.framework.Factory;
import FactoryMethod.framework.MemberCard;
import FactoryMethod.framework.MemberCardFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactoryMethodTest {

    @Test
    void factoryMethodTest() {
        Factory<MemberCard> memberCardFactory = new MemberCardFactory();

        MemberCard sol = memberCardFactory.create("SOL");
        MemberCard kim = memberCardFactory.create("KIM");

        sol.use();
        kim.use();

        assertEquals(Boolean.TRUE, memberCardFactory.contains(sol));
        assertEquals(Boolean.TRUE, memberCardFactory.contains(kim));
    }
}
