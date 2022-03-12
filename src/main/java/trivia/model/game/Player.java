package trivia.model.game;

public class Player {

    private final String playerName;

    private final Purse purse;
    private boolean inPenaltyBox = false;

    public Player(String playerName, Purse purse, boolean inPenaltyBox) {
        this.playerName = playerName;
        this.purse = purse;
        this.inPenaltyBox = inPenaltyBox;
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
}
