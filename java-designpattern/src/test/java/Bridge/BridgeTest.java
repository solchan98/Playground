package Bridge;

import org.junit.jupiter.api.Test;

public class BridgeTest {

    @Test
    void bridgeTest() {
        Display display = new Display(new StringDisplayImpl("Hello?"));
        CountDisplay countDisplayV1 = new CountDisplay(new StringDisplayImpl("Count Display V1"));
        CountDisplay countDisplayV2 = new CountDisplay(new StringDisplayImpl("Count Display V2"));

        display.display();
        countDisplayV1.display();
        countDisplayV2.multiDisplay(3);
    }
}
