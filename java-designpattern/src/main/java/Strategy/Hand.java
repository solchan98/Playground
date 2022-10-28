package Strategy;

import java.util.Arrays;

public class Hand {

    private final int handValue;

    public static final int ROCK = 0;
    public static final int SCISSORS = 1;
    public static final int PAPER = 2;

    public static final Hand[] hands = {
            new Hand(ROCK),
            new Hand(SCISSORS),
            new Hand(PAPER),
    };

    private final String[] names = { "주먹", "가위", "보" };

    private Hand(int handValue) {
        this.handValue = handValue;
    }

    public static Hand getHand(int handValue) {
        return Arrays.stream(hands)
                .filter(hand -> hand.handValue == handValue)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("인자가 올바르지 않습니다."));
    }

    public boolean isStrongerThan(Hand hand) {
        return fight(hand) == 1;
    }

    public boolean isWeakerThan(Hand hand) {
        return fight(hand) == -1;
    }

    private int fight(Hand hand) {
        if (this.handValue == hand.handValue) {
            return 0;
        } else if ((this.handValue + 1) % 3 == hand.handValue) {
            return 1;
        } else {
            return -1;
        }
    }

    public String toString() {
        return names[this.handValue];
    }
}
