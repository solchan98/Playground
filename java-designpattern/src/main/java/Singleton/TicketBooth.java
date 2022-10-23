package Singleton;

public class TicketBooth {

    private static final TicketBooth instance = new TicketBooth();

    private int ticket = 100;

    private TicketBooth() {}

    public static TicketBooth getInstance() {
        return instance;
    }

    public boolean getTicket() {
        if (ticket <= 0) {
            System.out.println("티켓이 매진되었습니다.");
            return false;
        } else {
            System.out.println("티켓이 발급되었습니다.");
            ticket--;
            return true;
        }
    }
}
