import org.junit.jupiter.api.*;

@DisplayName("메인 테스트임돵")
class Main {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All\n");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After All\n");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each\n");
    }

    @Test
    @DisplayName("테스트 1번 \uD83D\uDE32")
    void test1() {
        System.out.println("Test - 1 Success");
    }

    @Test
    @DisplayName("테스트 2번 \uD83D\uDE32")
    void test2() {
        System.out.println("Test - 2 Success");
    }
}