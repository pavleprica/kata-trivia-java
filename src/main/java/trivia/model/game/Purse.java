package trivia.model.game;

public class Purse {

    private final int maxPurseSize;

    private int coins = 0;

    public Purse(int maxPurseSize) {
        this.maxPurseSize = maxPurseSize;
    }

    public Purse() {
        this.maxPurseSize = 6;
    }

    public void addCoin() {
        if (coins < maxPurseSize) {
            coins++;
        } else {
            System.err.println("Unable to add coin, purse full.");
        }
    }

    public boolean isPurseFull() {
        return coins == maxPurseSize;
    }
}
