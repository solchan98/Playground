package Singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonTest {

    @Test
    void ticketBoothTest() {
        TicketBooth ticketBooth = TicketBooth.getInstance();
        TicketBooth ticketBooth2 = TicketBooth.getInstance();

        assertEquals(ticketBooth, ticketBooth2);
    }


}
