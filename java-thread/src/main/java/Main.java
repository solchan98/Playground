import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Account account = Account.getInstance();

        Client mobile = new Client(account);
        Client desktop = new Client(account);
        List<Thread> threadList = List.of(new Thread(mobile, "MOBILE"), new Thread(desktop, "DESKTOP"));
        startAll(threadList);
        joinAll(threadList);

        System.out.println(account.getBalance());
    }

    private static void startAll(List<Thread> threadList) {
        threadList.forEach(Thread::start);
    }

    private static void joinAll(List<Thread> threadList) throws InterruptedException {
        for (Thread thread : threadList) {
            thread.join();
        }
    }
}
