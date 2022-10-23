package Singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SingletonTest {

    @Test
    void ticketBoothTest() {
        TicketBooth ticketBooth = TicketBooth.getInstance();
        TicketBooth ticketBooth2 = TicketBooth.getInstance();

        assertEquals(ticketBooth, ticketBooth2);
    }

    @Test
    void tripleTest() {
        Triple tripleZero = Triple.getInstance(0);
        Triple newTripleZero = Triple.getInstance(0);
        Triple tripleOne = Triple.getInstance(1);
        Triple tripleTwo = Triple.getInstance(2);

        assertAll(
                () -> assertNotSame(tripleZero, tripleOne),
                () -> assertNotSame(tripleZero, tripleTwo),
                () -> assertSame(tripleZero, newTripleZero)
        );
    }


}
