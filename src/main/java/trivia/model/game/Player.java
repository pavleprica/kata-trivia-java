package trivia.model.game;

public class Player {

    private final String playerName;

    private final Purse purse;
    private boolean inPenaltyBox = false;

    private int playerNumber = -1;

    public Player(String playerName, Purse purse) {
        this.playerName = playerName;
        this.purse = purse;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Purse getPurse() {
        return purse;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    @Override
    public String toString() {
        return playerName;
    }
}
