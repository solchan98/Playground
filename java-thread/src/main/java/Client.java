public class Client implements Runnable{

    private final Account account;

    public Client(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int idx = 0; idx < 10000; idx++) {
            try {
                long withdraw = account.withdraw(1L);
                if (withdraw == -1) break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
