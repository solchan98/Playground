import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("메인 테스트임돵")
class Main {

//    /** 1 */
//    @BeforeAll
//    static void beforeAll() {
//        System.out.println("Before All\n");
//    }
//
//    @AfterAll
//    static void afterAll(){
//        System.out.println("After All\n");
//    }
//
//    @BeforeEach
//    void beforeEach() {
//        System.out.println("Before Each");
//    }
//
//    @AfterEach
//    void afterEach() {
//        System.out.println("After Each\n");
//    }
//
//    @Test
//    @DisplayName("테스트 1번 \uD83D\uDE32")
//    void test1() {
//        System.out.println("Test - 1 Success");
//    }
//
//    @Test
//    @DisplayName("테스트 2번 \uD83D\uDE32")
//    void test2() {
//        System.out.println("Test - 2 Success");
//    }

    /** 2 */
    @Test
    @DisplayName("실패 메세지 스트링")
    void test3() {
        int num = 3;
        assertEquals(5, num, num + "은 기대하는 값이 아니에요!");
    }

    @Test
    @DisplayName("실패 메세지 람다식")
    void test4() {
        int num = 3;
        assertEquals(5, num, () -> num + "은 기대하는 값이 아니에요!");
    }


    @Test
    @DisplayName("계좌 시나리오 - 나의 계좌인가?")
    void accountTest1() {
        Account account = new Account("SolChan", "123-456-789", 50000L);
        assertEquals("SolChan", account.getCustomerName(), () -> account.getCustomerName() + "의 계좌가 아닙니다.");
    }

    @Test
    @DisplayName("계좌 시나리오 - 계좌번호가 null이 아닌가?")
    void accountTest2() {
        Account account = new Account("SolChan", null, 50000L);
        assertNotNull(account.getAccountNumber(), () -> account.getCustomerName() + "의 계좌번호가 없습니다.");
    }

    @Test
    @DisplayName("계좌 시나리오 - 잔고가 마이너스가 아닌가?")
    void accountTest3() {
        Account account = new Account("SolChan", "123-456-789", -50000L);
        assertTrue(account.getBalance() >= 0L, () -> account.getCustomerName() + "의 잔고가 마이너스입니다.");
    }

    @Test
    @DisplayName("계좌 시나리오 - 잔고 이상의 출금을 요청하는가?")
    void accountTest4() {
        Account account = new Account("SolChan", "123-456-789", 50000L);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> account.withDraw(80000L));
        System.out.println(exception.getMessage());
    }
}
