package trivia;

import java.util.*;
import trivia.model.game.Place;
import trivia.model.game.Player;
import trivia.model.game.Purse;
import trivia.model.question.*;

// REFACTOR ME
public class GameBetter implements IGame {

    private final int placesCount;
    private final int questionStackCount;

    private final LinkedList<Question> popQuestions = new LinkedList<>();
    private final LinkedList<Question> scienceQuestions = new LinkedList<>();
    private final LinkedList<Question> sportQuestions = new LinkedList<>();
    private final LinkedList<Question> rockQuestions = new LinkedList<>();

    private final List<Place> places = new ArrayList<>();

    private final List<Player> players = new ArrayList<>();
    private Player currentPlayerOnTurn = null;

    private Map<Player, Place> placesOfPlayers = new HashMap<>();

    public GameBetter() {
        this.placesCount = 12;
        this.questionStackCount = 50;

        initQuestionStack();
        initPlaces();
    }

    public GameBetter(int placesCount, int questionStackCount) {
        validateInput(placesCount, questionStackCount);
        this.placesCount = placesCount;
        this.questionStackCount = questionStackCount;

        initQuestionStack();
        initPlaces();
    }

    private void validateInput(int placesCount, int questionStackCount) {
        if (placesCount < 0) {
            throw new IllegalArgumentException("Places must be a positive number");
        }
        if (placesCount % 2 != 0) {
            throw new IllegalArgumentException("Places must be an even number");
        }

        if (questionStackCount < 50 || questionStackCount > 350) {
            throw new IllegalArgumentException("Question stack count should be between 50 and 350");
        }
    }

    private void initQuestionStack() {
        for (int question = 0; question < questionStackCount; question++) {
            popQuestions.add(new PopQuestion(question));
            scienceQuestions.add(new ScienceQuestion(question));
            sportQuestions.add(new SportQuestion(question));
            rockQuestions.add(new RockQuestion(question));
        }
    }

    private void initPlaces() {
        for (int placeLocation = 0; placeLocation < placesCount; placeLocation++) {
            places.add(new Place(placeLocation, getQuestionStackForPlace(placeLocation)));
        }
    }

    private LinkedList<Question> getQuestionStackForPlace(int placeIndex) {
        int categoryCount = 4;
        int placeRange = placeIndex % categoryCount;
        switch (placeRange) {
            case 0:
                return popQuestions;
            case 1:
                return scienceQuestions;
            case 2:
                return sportQuestions;
            case 3:
                return rockQuestions;
            default:
                throw new IllegalStateException("Shouldn't happen :)");
        }
    }

    public boolean add(String playerName) {
        Player player = new Player(playerName, new Purse());
        player.setPlayerNumber(players.size());

        if (players.isEmpty()) {
            currentPlayerOnTurn = player;
        }

        players.add(player);
        placesOfPlayers.put(player, places.get(0));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + (player.getPlayerNumber() + 1));
        return true;
    }

    public void roll(int roll) {
        Player currentPlayer = currentPlayerOnTurn;
        System.out.println(currentPlayer + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (isPrisonFreeingRoll(roll)) {
                currentPlayer.setGettingOutOfPenaltyBox(true);
                System.out.println(currentPlayer + " is getting out of the penalty box");
            } else {
                System.out.println(currentPlayer + " is not getting out of the penalty box");
                currentPlayer.setGettingOutOfPenaltyBox(false);
                return;
            }
        }
        playTurnAndAskQuestion(roll);
    }

    private boolean isPrisonFreeingRoll(int roll) {
        return roll % 2 != 0;
    }

    private void playTurnAndAskQuestion(int roll) {
        playTurn(roll);
        askQuestionForCurrentPlayer();
    }

    public void playTurn(int roll) {
        int newPlaceIndex = calculateNewPlaceForPlayer(placesOfPlayers.get(currentPlayerOnTurn), roll);
        Place newPlace = places.get(newPlaceIndex);
        placesOfPlayers.put(currentPlayerOnTurn, newPlace);
        System.out.println(currentPlayerOnTurn + "'s new location is " + newPlace.getPlaceLocation());

        System.out.println("The category is " + newPlace.getQuestionCategoryFromStack());
    }

    private int calculateNewPlaceForPlayer(Place currentPlace, int roll) {
        int currentIndex = currentPlace.getPlaceLocation() + roll;
        if (currentIndex >= placesCount) {
            return currentIndex - placesCount;
        } else {
            return currentIndex;
        }
    }

    public void askQuestionForCurrentPlayer() {
        String question = placesOfPlayers.get(currentPlayerOnTurn).drawQuestionFromStack().getQuestionSentence();
        System.out.println(question);
    }

    public boolean wasCorrectlyAnswered() {
        Player currentPlayer = currentPlayerOnTurn;
        if (currentPlayer.isInPenaltyBox()) {
            if (currentPlayer.isGettingOutOfPenaltyBox()) {
                System.out.println("Answer was correct!!!!");
                return addCoinAndUpdatePlayerAndCheckIfNotWinner(currentPlayer);
            } else {
                setCurrentPlayerToNextPlayer();
                return true;
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            return addCoinAndUpdatePlayerAndCheckIfNotWinner(currentPlayer);
        }
    }

    private boolean addCoinAndUpdatePlayerAndCheckIfNotWinner(Player player) {
        boolean isNotAWinner = addCoinAndCheckIfPurseHasSpace(player);
        setCurrentPlayerToNextPlayer();
        return isNotAWinner;
    }

    private boolean addCoinAndCheckIfPurseHasSpace(Player player) {
        player.getPurse().addCoin();

        System.out.println(player + " now has " + player.getPurse().getCoins() + " Gold Coins.");

        return player.getPurse().doesPurseHaveSpace();
    }

    public boolean wrongAnswer() {
        Player currentPlayer = currentPlayerOnTurn;
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer + " was sent to the penalty box");
        currentPlayer.setInPenaltyBox(true);
        setCurrentPlayerToNextPlayer();
        return true;
    }

    public void setCurrentPlayerToNextPlayer() {
        int indexOfCurrentPlayer = players.indexOf(currentPlayerOnTurn);
        if (indexOfCurrentPlayer == players.size() - 1) {
            currentPlayerOnTurn = players.get(0);
        } else {
            currentPlayerOnTurn = players.get(indexOfCurrentPlayer + 1);
        }
    }
}
