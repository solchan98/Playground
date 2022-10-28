package Strategy;

public class Player {

    private final String name;
    private Strategy strategy;
    private int winCount;
    private int loseCount;
    private int gameCount;

    public Player(String name, Strategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public String getName() {
        return this.name;
    }

    public Hand nextHand() {
        return strategy.nextHand();
    }

    public void win() {
        strategy.study(true);
        winCount++;
        gameCount++;
    }

    public void lose() {
        strategy.study(false);
        loseCount++;
        gameCount++;
    }

    public void even() {
        gameCount++;
    }

    public void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public String status() {
        return "[" + name + "] : " + " [ gameCount : " + gameCount + " ]," +
                " [ winCount : " + winCount + " ]," +
                " [ loseCount : " + loseCount + " ]";
    }
}
