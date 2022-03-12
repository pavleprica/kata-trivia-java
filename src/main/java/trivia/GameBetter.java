package trivia;

import trivia.model.game.GameBoard;
import trivia.model.game.Player;
import trivia.model.game.Purse;

// REFACTOR ME
public class GameBetter implements IGame {

    private final GameBoard gameBoard = new GameBoard(12, 50);
    boolean isGettingOutOfPenaltyBox;

    public GameBetter() {}

    public boolean add(String playerName) {
        Player player = new Player(playerName, new Purse());
        gameBoard.addPlayerToGame(player);

        System.out.println(playerName + " was added");
        System.out.println(
            "They are player number " + player.getPlayerNumber()
        );
        return true;
    }

    public void roll(int roll) {
        Player currentPlayer = gameBoard.getCurrentPlayerOnTurn();
        System.out.println(currentPlayer + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (isPrisonFreeingRoll(roll)) {
                isGettingOutOfPenaltyBox = true;
                System.out.println(
                    currentPlayer + " is getting out of the penalty box"
                );
                playTurnAndAskQuestion(roll);
            } else {
                System.out.println(
                    currentPlayer + " is not getting out of the penalty box"
                );
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            playTurnAndAskQuestion(roll);
        }
    }

    private void playTurnAndAskQuestion(int roll) {
        gameBoard.playTurn(roll);
        gameBoard.askQuestionForCurrentPlayer();
    }

    private boolean isPrisonFreeingRoll(int roll) {
        return roll % 2 != 0;
    }

    public boolean wasCorrectlyAnswered() {
        Player currentPlayer = gameBoard.getCurrentPlayerOnTurn();
        if (currentPlayer.isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                gameBoard.setCurrentPlayerToNextPlayer();
                return addCoinAndCheckIfFull(currentPlayer);
            } else {
                gameBoard.setCurrentPlayerToNextPlayer();
                return true;
            }
        } else {
            gameBoard.setCurrentPlayerToNextPlayer();
            return addCoinAndCheckIfFull(currentPlayer);
        }
    }

    private boolean addCoinAndCheckIfFull(Player player) {
        System.out.println("Answer was correct!!!!");
        player.getPurse().addCoin();
        System.out.println(
            player + " now has " + player.getPurse().getCoins() + " Gold Coins."
        );

        return player.getPurse().isPurseFull();
    }

    public boolean wrongAnswer() {
        Player currentPlayer = gameBoard.getCurrentPlayerOnTurn();
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer + " was sent to the penalty box");
        currentPlayer.setInPenaltyBox(true);
        gameBoard.setCurrentPlayerToNextPlayer();
        return true;
    }
}
