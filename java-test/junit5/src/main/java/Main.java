public class Main {
    public static void main(String[] args) {

    }
}

/** 2 */
class Account {
    private String customerName;
    private String accountNumber;
    private Long balance;

    public Account(String customerName, String accountNumber, Long balance) {
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public Long getBalance() {
        return this.balance;
    }

    public void withDraw(Long balance) {
        if(this.balance - balance < 0) {
            throw new RuntimeException("잔고가 부족합니다.");
        } else {
            this.balance -= balance;
        }
    }
}
