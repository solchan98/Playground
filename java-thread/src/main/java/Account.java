import java.util.concurrent.Semaphore;

public class Account {
    private static final Account INSTANCE = new Account();
    private long balance = 100000L;
    private static final Semaphore semaphore = new Semaphore(1);
//    private long resourceCount = 1L;

    private Account() {}

    public static Account getInstance() {
        return INSTANCE;
    }

    public long withdraw(long price) throws InterruptedException {
        semaphore.acquire();
        if (balance - price < 0) {
            System.out.println("출금 불가 - 잔액부족");
            return -1L;
        }

        balance -= price;
        semaphore.release();
        return price;
    }

//    public void useResource() {
//        while(resourceCount <= 0);
//        resourceCount -= 1;
//    }
//
//    public void returnResource() {
//        resourceCount += 1;
//    }

    public long getBalance() {
        return balance;
    }

}
