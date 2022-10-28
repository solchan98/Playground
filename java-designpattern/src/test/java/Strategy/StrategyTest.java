package Strategy;

import org.junit.jupiter.api.Test;

public class StrategyTest {

    @Test
    void strategyTest() {
        Player sol = new Player("Sol", new WinningStrategy(1));
        Player kim = new Player("Kim", new ProbStrategy(3));

        for (int idx = 0; idx < 10; idx++) {
            System.out.println("---게임을 시작합니다---");

            Hand solHand = sol.nextHand();
            Hand kimHand = kim.nextHand();
            if (solHand.isStrongerThan(kimHand)) {
                System.out.println("Winner is " + sol.getName());
                sol.win();
                kim.lose();
            } else if (kimHand.isStrongerThan(solHand)) {
                System.out.println("Winner is " + kim.getName());
                kim.win();
                sol.lose();
            } else {
                System.out.println("Even...");
                sol.even();
                kim.even();
            }
            System.out.println("---게임이 종료되었습니다.---");
            System.out.println(sol.status());
            System.out.println(kim.status());
            System.out.println();
        }
    }
}
