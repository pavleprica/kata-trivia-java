package trivia.model.game;

import java.util.*;
import trivia.model.question.*;

public class GameBoard {

    private final int placesCount;
    private final int questionStackCount;

    private final Deque<Question> popQuestions = new ArrayDeque<>();
    private final Deque<Question> scienceQuestions = new ArrayDeque<>();
    private final Deque<Question> sportQuestions = new ArrayDeque<>();
    private final Deque<Question> rockQuestions = new ArrayDeque<>();

    private final List<Place> places = new ArrayList<>();

    private final List<Player> players = new ArrayList<>();
    private Player currentPlayerOnTurn = null;

    private Map<Player, Place> placesOfPlayers = new HashMap<>();

    public GameBoard(int placesCount, int questionStackCount) {
        validateInput(placesCount, questionStackCount);
        this.placesCount = placesCount;
        this.questionStackCount = questionStackCount;

        initQuestionStack();
        initPlaces();
    }

    private void initQuestionStack() {
        for (int question = 0; question < questionStackCount; question++) {
            popQuestions.push(new PopQuestion(question));
            scienceQuestions.push(new ScienceQuestion(question));
            sportQuestions.push(new SportQuestion(question));
            rockQuestions.push(new RockQuestion(question));
        }
    }

    private void initPlaces() {
        for (
            int placeLocation = 0;
            placeLocation < placesCount;
            placeLocation++
        ) {
            places.add(
                new Place(
                    placeLocation,
                    getQuestionStackForPlace(placeLocation)
                )
            );
        }
    }

    private Deque<Question> getQuestionStackForPlace(int placeIndex) {
        switch (placeIndex) { // TODO Investigate how to improve
            case 0:
            case 4:
            case 8:
                return popQuestions;
            case 1:
            case 5:
            case 9:
                return scienceQuestions;
            case 2:
            case 6:
            case 10:
                return sportQuestions;
            default:
                return rockQuestions;
        }
    }

    private void validateInput(int placesCount, int questionStackCount) {
        if (placesCount < 0) {
            throw new IllegalArgumentException(
                "Places must be a positive number"
            );
        }
        if (placesCount % 2 != 0) {
            throw new IllegalArgumentException("Places must be an even number");
        }

        if (questionStackCount < 50 || questionStackCount > 350) {
            throw new IllegalArgumentException(
                "Question stack count should be between 50 and 350"
            );
        }
    }

    public void addPlayerToGame(Player player) {
        player.setPlayerNumber(players.size());

        if (players.isEmpty()) {
            currentPlayerOnTurn = player;
        }

        players.add(player);
        placesOfPlayers.put(player, places.get(0));
    }

    public boolean isGamePlayable() {
        return players.size() >= 2;
    }

    public void playTurn(int roll) {
        int newPlaceIndex = calculateNewPlaceForPlayer(
            placesOfPlayers.get(currentPlayerOnTurn),
            roll
        );
        Place newPlace = places.get(newPlaceIndex);
        placesOfPlayers.put(currentPlayerOnTurn, newPlace);
        System.out.println(
            currentPlayerOnTurn +
            "'s new location is " +
            newPlace.getPlaceLocation()
        );

        System.out.println(
            "The category is " + newPlace.getQuestionCategoryFromStack()
        );
    }

    public void askQuestionForCurrentPlayer() {
        String question = placesOfPlayers
            .get(currentPlayerOnTurn)
            .drawQuestionFromStack()
            .getQuestionSentence();
        System.out.println(question);
    }

    private int calculateNewPlaceForPlayer(Place currentPlace, int roll) {
        int currentIndex = currentPlace.getPlaceLocation() + roll;
        if (currentIndex > placesCount) {
            return currentIndex - placesCount;
        } else {
            return currentIndex;
        }
    }

    public Player getCurrentPlayerOnTurn() {
        return currentPlayerOnTurn;
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
